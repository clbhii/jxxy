package com.cl.dao;


import com.cl.model.DeviceDO;

import java.util.List;
import java.util.Map;

public interface DeviceDAO {

    int insert(DeviceDO record);

    int updateByPrimaryKey(DeviceDO record);

    DeviceDO selectByPrimaryKey(Long id);

    List<DeviceDO> selectList(Map<String, Object> map);

    int deleteByPrimaryKey(Long id);
}