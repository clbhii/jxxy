package com.cl.service.impl;

import com.cl.common.Contants;
import com.cl.dao.DeviceDAO;
import com.cl.enums.DeviceStatusEnum;
import com.cl.model.DeviceDO;
import com.cl.model.DeviceDataDO;
import com.cl.service.IDeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * by cl at 2020/4/10 0010
 */
@Service
public class DeviceDataServiceImpl implements IDeviceDataService {
    @Autowired
    private MongoOperations mongoTemplate;
    @Autowired
    private DeviceDAO deviceDAO;
    @Override
    public void reportData(String topic, String data) {

    }
}
