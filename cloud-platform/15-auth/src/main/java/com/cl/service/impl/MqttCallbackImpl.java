package com.cl.service.impl;

import com.cl.common.Contants;
import com.cl.dao.DeviceDAO;
import com.cl.dao.DeviceFieldDAO;
import com.cl.enums.DeviceStatusEnum;
import com.cl.model.DeviceDO;
import com.cl.model.DeviceDataDO;
import com.cl.model.DeviceFieldDO;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * by cl at 2020/4/11 0011
 */
@Service
public class MqttCallbackImpl implements MqttCallback {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DeviceDAO deviceDAO;
    @Autowired
    private MongoOperations mongoTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private DeviceFieldDAO deviceFieldDAO;
    //在断开连接时调用
    @Override
    public void connectionLost(Throwable cause) {
        logger.info("连接断开，可以做重连");
    }
    //收到下推消息时的回调
    @Transactional
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        logger.info("收到消息主题:" + topic);
        logger.info("收到消息内容:" + message);
        String devNo = topic.split(Contants.MQTT_UP)[0];
        DeviceDO deviceDO = deviceDAO.selectByDevNo(devNo);
        if(deviceDO == null) {
            throw new RuntimeException("找不到对应的设备");
        }
        String data = message.toString();
        DeviceDataDO deviceDataDO = new DeviceDataDO();
        deviceDataDO.setDeviceId(deviceDO.getId());
        deviceDataDO.setData(data);
        deviceDataDO.setUploadDate(new Date());
        mongoTemplate.insert(deviceDataDO);
        if(data.contains(DeviceStatusEnum.offline.name())) {
            deviceDO.setDevStatus(DeviceStatusEnum.offline.getValue());
        }else {
            //更新设备属性
            deviceFieldDAO.deleteByDevId(deviceDO.getId());
            Map<String, String> map = objectMapper.readValue(data, Map.class);
            for(Map.Entry<String, String> entry : map.entrySet()){
                DeviceFieldDO deviceFieldDO = new DeviceFieldDO();
                deviceFieldDO.setDevId(deviceDO.getId());
                deviceFieldDO.setFieldKey(entry.getKey());
                deviceFieldDO.setFieldValue(entry.getValue());
                deviceFieldDAO.insert(deviceFieldDO);
            }
            deviceDO.setDevStatus(DeviceStatusEnum.online.getValue());
        }
        deviceDAO.updateByPrimaryKey(deviceDO);

    }
    //消息发送成功时的回调
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info("发布完成");
    }
}
