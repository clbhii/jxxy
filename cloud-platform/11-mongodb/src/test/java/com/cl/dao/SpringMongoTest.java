package com.cl.dao;

import com.cl.model.DeviceDO;
import com.cl.model.DeviceDataDO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * by cl at 2020/4/9 0009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class SpringMongoTest {
    private static final Logger log = LoggerFactory.getLogger(SpringMongoTest.class);
    @Autowired
    private MongoOperations  mongoTemplate;
    private static ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void insert() throws JsonProcessingException {
        DeviceDataDO deviceDataDO = new DeviceDataDO();
        deviceDataDO.setDeviceId(6l);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("status", 1);
        deviceDataDO.setData(objectMapper.writeValueAsString(dataMap));
        deviceDataDO.setUploadDate(new Date());
        mongoTemplate.insert(deviceDataDO);
        log.info("insert:" + objectMapper.writeValueAsString(deviceDataDO));
    }
    @Test
    public void select() throws JsonProcessingException {
        DeviceDataDO deviceDataDO  = mongoTemplate.findOne(Query.query(where("deviceId").is(6l)), DeviceDataDO.class);
        log.info("Found: " + objectMapper.writeValueAsString(deviceDataDO));
    }

    @Test
    public void update() throws JsonProcessingException {
        mongoTemplate.updateFirst(Query.query(where("deviceId").is(6l)), Update.update("uploadDate", new Date()), DeviceDataDO.class);
        select();
    }

    @Test
    public void delete() throws JsonProcessingException {
        mongoTemplate.remove(Query.query(where("deviceId").is(6l)), DeviceDataDO.class);
        select();
    }
}
