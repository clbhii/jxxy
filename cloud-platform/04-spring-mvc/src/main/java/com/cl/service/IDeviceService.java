package com.cl.service;

import com.cl.model.DeviceDO;

public interface IDeviceService {

    int insert(DeviceDO record);

    DeviceDO selectByPrimaryKey(Long id);

    int updateByPrimaryKey(DeviceDO record);

    int deleteByPrimaryKey(Long id);

}
