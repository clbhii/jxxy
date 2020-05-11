package com.cl.dao;

import com.cl.common.Contants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.HashMap;
import java.util.Map;

/**
 * QOS
 *   a.level 0：最多一次的传输
 *   b.level 1：至少一次的传输
 *   c.level 2：只有一次的传输
 * by cl at 2020/3/26 0026
 */
public class MqttClientTest {
    private static final String HOST = "tcp://127.0.0.1:1883"; //服务器 IP:端口
    private static final String CLIENTID = "client2"; //客户端唯一标识 一旦重复服务器会踢掉之前连接的客户端

    private static final String USERNAME = "test"; //用户名
    private static final String PASSWORD = "test"; //密码
    private static final String DEV_NO = "001";
    private MqttClient mqttClient; //客户端实例

    public void connect() throws MqttException {
        // 新建客户端实例
        // MemoryPersistence 设置客户端实例的保存形式，默认为以内存保存，此 处以 以内存保存
        mqttClient = new MqttClient(HOST,CLIENTID,new MemoryPersistence());
        // 设置连接时的参数
        MqttConnectOptions options = new MqttConnectOptions();
        // 是否清空 session,如果设置为 false 表示服务器会保留客户端的连接记录
        //设置为 true 表示每次连接到服务器都以新的身份连接*/
        options.setCleanSession(true);
        // 用户名
        options.setUserName(USERNAME);
        // 密码
        options.setPassword(PASSWORD.toCharArray());
        // 连接超时时间
        options.setConnectionTimeout(100);
        // 心跳间隔时间
        options.setKeepAliveInterval(180);
        // 掉线自动重连
        options.setAutomaticReconnect(true);
        /* 遗嘱消息：当连接断开时发送的死亡预告，此客户端连接断开后，
        //服务器会把此消息推送给订阅了此主题的客户机*/
        options.setWill(DEV_NO + Contants.MQTT_UP,"offline".getBytes(),0,true);
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
                System.out.println("收到消息主题:"+topic);
                System.out.println("收到消息内容:"+message);
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

    public void sub(String topic) throws MqttException {
        //订阅
        mqttClient.subscribe(topic,2);
    }

    public void pub(String topic, String msg) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(0);
        mqttMessage.setPayload(msg.getBytes());
        MqttTopic mqttTopic = mqttClient.getTopic(topic);
        MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
        token.waitForCompletion();
    }
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static void main(String[] args) throws Exception {
        MqttClientTest client = new MqttClientTest();
        client.connect();
//        String topic = DEV_NO + Contants.MQTT_UP;
//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("status", 1);
//        client.pub(topic, objectMapper.writeValueAsString(dataMap));
        String topic = DEV_NO + Contants.MQTT_DW;
        client.sub(topic);
    }
}
