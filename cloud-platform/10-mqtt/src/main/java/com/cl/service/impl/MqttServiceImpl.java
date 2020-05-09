package com.cl.service.impl;

import com.cl.common.Contants;
import com.cl.dao.DeviceDAO;
import com.cl.model.DeviceDO;
import com.cl.service.IMqttService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * by cl at 2020/4/1 0001
 */
@Service
public class MqttServiceImpl implements IMqttService, InitializingBean {
    @Value("${mqtt.host}")
    private String host; //服务器 IP:端口
    @Value("${mqtt.clientId}")
    private String clientId ; //客户端唯一标识 一旦重复服务器会踢掉之前连接的客户端
    @Value("${mqtt.username}")
    private String username ; //用户名
    @Value("${mqtt.password}")
    private String password; //密码
    private MqttClient mqttClient; //客户端实例
    @Autowired
    private DeviceDAO deviceDAO;
    @Override
    public void init() throws MqttException {
        connect();
        List<DeviceDO> deviceList =  deviceDAO.selectList(new HashMap<>());
        for(DeviceDO deviceDO : deviceList) {
            sub(deviceDO.getDevNo() + Contants.MQTT_UP);
        }
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    @Override
    public void sub(String topic) throws MqttException {
        //订阅 sub时指定的qos表示订阅者可以接收到更低等级的消息(min(sub.qos, pub.qos))。
        mqttClient.subscribe(topic,0);
    }

    @Override
    public void pub(String topic, String msg) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage();
        //pub时指定的qos是服务器肯定按此规则接收，但是最终订阅者不一定。
        mqttMessage.setQos(2);
        mqttMessage.setPayload(msg.getBytes());
        MqttTopic mqttTopic = mqttClient.getTopic(topic);
        MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
        token.waitForCompletion();
    }



    private void connect() throws MqttException {
        mqttClient = new MqttClient(host, clientId, new MemoryPersistence());
        // 设置连接时的参数
        MqttConnectOptions options = new MqttConnectOptions();
        // 是否清空 session,如果设置为 false 表示服务器会保留客户端的连接记录
        //设置为 true 表示每次连接到服务器都以新的身份连接*/
        options.setCleanSession(true);
        // 用户名
        options.setUserName(username);
        // 密码
        options.setPassword(password.toCharArray());
        // 连接超时时间
        options.setConnectionTimeout(100);
        // 心跳间隔时间
        options.setKeepAliveInterval(180);
        // 掉线自动重连
        options.setAutomaticReconnect(true);
        /* 遗嘱消息：当连接断开时发送的死亡预告，此客户端连接断开后，
        //服务器会把此消息推送给订阅了此主题的客户机*/
        options.setWill("close", "offline".getBytes(), 0, true);
        // 设置回调函数
        mqttClient.setCallback(new MqttCallback() {
            //在断开连接时调用
            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("连接断开，可以做重连");
            }

            //收到下推消息时的回调
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("收到消息主题:" + topic);
                System.out.println("收到消息内容:" + message);
            }

            //消息发送成功时的回调
            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("发布完成");
            }
        });

        mqttClient.connect(options);
        System.out.println("连接成功");
    }
}
