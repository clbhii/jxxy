package com.cl.controller;

import com.cl.common.Result;
import com.cl.common.ResultSupport;
import com.cl.model.DeviceDO;
import com.cl.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * by cl at 2020/3/23 0023
 */
@Controller
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private IDeviceService deviceService;

    @RequestMapping("/insert")
    public String insert(String devNo, String devName) {
        DeviceDO deviceDO = new DeviceDO();
        deviceDO.setDevNo(devNo);
        deviceDO.setDevName(devName);
        deviceService.insert(deviceDO);
        return "ok";
    }
}
