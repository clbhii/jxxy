package com.cl.service.impl;

import com.cl.dao.DeviceDAO;
import com.cl.model.DeviceDO;
import com.cl.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * by cl at 2020/3/23 0023
 */
@Service
public class DeviceServiceImpl implements IDeviceService {
    @Autowired
    private DeviceDAO deviceDAO;


    @Override
    public int insert(DeviceDO record) {
        return deviceDAO.insert(record);
    }

    @Override
    public DeviceDO selectByPrimaryKey(Long id) {
        return deviceDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(DeviceDO record) {
        return deviceDAO.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return deviceDAO.deleteByPrimaryKey(id);
    }
}
