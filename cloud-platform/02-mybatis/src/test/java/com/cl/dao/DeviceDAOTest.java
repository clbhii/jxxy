package com.cl.dao;

import com.cl.model.DeviceDO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * by cl at 2020/3/22 0022
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class DeviceDAOTest {
    @Autowired
    private DeviceDAO deviceDAO;
    private static ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void insert() {
        DeviceDO deviceDO = new DeviceDO();
        deviceDO.setDevNo("dd");
        deviceDO.setDevName("设备111");
        deviceDAO.insert(deviceDO);
    }
    @Test
    public void updateByPrimaryKey() {
        DeviceDO deviceDO = new DeviceDO();
        deviceDO.setId(30l);
        deviceDO.setDevNo("dd11");
        deviceDO.setDevName("设备333");
        int i = deviceDAO.updateByPrimaryKey(deviceDO);
        System.out.println("dd"+i);
    }
    @Test
    public void selectByPrimaryKey() {
        DeviceDO deviceDO = deviceDAO.selectByPrimaryKey(30l);
        System.out.println("设备编号:" + deviceDO.getDevNo());
    }
    @Test
    public void selectList() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("devNo","11");
        List<DeviceDO> list = deviceDAO.selectList(map);
        System.out.println(objectMapper.writeValueAsString(list));
    }
    @Test
    public void deleteByPrimaryKey() {
        deviceDAO.deleteByPrimaryKey(30l);
    }

}
