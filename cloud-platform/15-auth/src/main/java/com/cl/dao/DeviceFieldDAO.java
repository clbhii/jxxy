package com.cl.dao;


import com.cl.model.DeviceFieldDO;

import java.util.List;

public interface DeviceFieldDAO {
    List<DeviceFieldDO> selectByDevId(Long devId);

    int deleteByDevId(Long devId);

    int insert(DeviceFieldDO record);
}