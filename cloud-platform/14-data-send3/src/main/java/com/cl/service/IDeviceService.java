package com.cl.service;

import com.cl.common.Page;
import com.cl.model.DeviceDO;
import com.cl.model.dto.DeviceInfoDTO;

public interface IDeviceService {

    void insert(DeviceInfoDTO deviceInfoDTO);

    DeviceInfoDTO selectByPrimaryKey(Long id);

    void updateByPrimaryKey(DeviceInfoDTO deviceInfoDTO);

    void deleteByPrimaryKey(Long id);

    Page<DeviceDO> pageDevice(String devNo, Integer curPage, Integer pageSize);

}
