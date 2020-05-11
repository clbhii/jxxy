package com.cl.model.dto;

import com.cl.model.DeviceDO;
import com.cl.model.DeviceFieldDO;

import java.util.List;

/**
 * by cl at 2020/4/24 0024
 */
public class DeviceInfoDTO {

    private DeviceDO deviceDO;

    private List<DeviceFieldDO> deviceFieldDOList;

    public DeviceDO getDeviceDO() {
        return deviceDO;
    }

    public void setDeviceDO(DeviceDO deviceDO) {
        this.deviceDO = deviceDO;
    }

    public List<DeviceFieldDO> getDeviceFieldDOList() {
        return deviceFieldDOList;
    }

    public void setDeviceFieldDOList(List<DeviceFieldDO> deviceFieldDOList) {
        this.deviceFieldDOList = deviceFieldDOList;
    }
}
