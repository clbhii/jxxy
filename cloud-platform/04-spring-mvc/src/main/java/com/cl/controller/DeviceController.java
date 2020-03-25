package com.cl.controller;

import com.cl.common.Result;
import com.cl.common.ResultSupport;
import com.cl.model.DeviceDO;
import com.cl.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * by cl at 2020/3/23 0023
 */
@Controller
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private IDeviceService deviceService;

    @RequestMapping("/insert")
    @ResponseBody
    public Result<Void> insert(String devNo, String devName) {
        DeviceDO deviceDO = new DeviceDO();
        deviceDO.setDevNo(devNo);
        deviceDO.setDevName(devName);
        deviceService.insert(deviceDO);
        Result<Void> result = new ResultSupport<>(true);
        return result;
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public Result<DeviceDO> get(@PathVariable("id") Long id) {
        DeviceDO deviceDO = deviceService.selectByPrimaryKey(id);
        Result<DeviceDO> result = new ResultSupport<>(true, deviceDO);
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result<Void> update(Long id, String devNo, String devName) {
        DeviceDO deviceDO = deviceService.selectByPrimaryKey(id);
        deviceDO.setDevNo(devNo);
        deviceDO.setDevName(devName);
        deviceService.updateByPrimaryKey(deviceDO);
        Result<Void> result = new ResultSupport<>(true);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result<Void> delete(Long id) {
        deviceService.deleteByPrimaryKey(id);
        return new ResultSupport<>(true);
    }
}
