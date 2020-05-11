package com.cl.service;

import com.cl.common.Page;
import com.cl.model.DeviceDO;
import com.cl.model.DeviceFieldDO;

import java.util.List;

public interface IDeviceService {

    int insert(DeviceDO record);

    DeviceDO selectByPrimaryKey(Long id);

    int updateByPrimaryKey(DeviceDO record);

    int deleteByPrimaryKey(Long id);

    Page<DeviceDO> pageDevice(String devNo, Integer curPage, Integer pageSize);

    List<DeviceFieldDO> selectByDevId(Long devId);

}
