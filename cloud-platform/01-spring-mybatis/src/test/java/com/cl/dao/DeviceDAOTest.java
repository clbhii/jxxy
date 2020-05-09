package com.cl.dao;

import com.cl.model.DeviceDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * by cl at 2020/3/22 0022
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class DeviceDAOTest {
    @Autowired
    private DeviceDAO deviceDAO;
    @Test
    public void insert() {
        DeviceDO deviceDO = new DeviceDO();
        deviceDO.setDevNo("z003");
        deviceDO.setDevName("智能电灯003");
        deviceDAO.insert(deviceDO);
    }
}
