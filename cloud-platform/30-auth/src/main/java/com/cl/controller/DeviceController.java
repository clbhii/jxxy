package com.cl.controller;

import com.cl.common.Contants;
import com.cl.common.Page;
import com.cl.common.Result;
import com.cl.common.ResultSupport;
import com.cl.model.DeviceDO;
import com.cl.model.UserDO;
import com.cl.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * by cl at 2020/3/23 0023
 */
@Controller
@RequestMapping("/device")
public class DeviceController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
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
        Result<Void> result = new ResultSupport<>(true);
        try{
            DeviceDO deviceDO = deviceService.selectByPrimaryKey(id);
            deviceDO.setDevNo(devNo);
            deviceDO.setDevName(devName);
            deviceService.updateByPrimaryKey(deviceDO);
        }catch(Exception e) {
            log.error("更新失败", e);
            result.setSuccess(false);
            result.setMessage("更新失败,请联系管理员");
        }

        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result<Void> delete(Long id) {
        deviceService.deleteByPrimaryKey(id);
        return new ResultSupport<>(true);
    }

    @RequestMapping("/pageDevice")
    @ResponseBody
    public Map<String, Object> pageDevice(@RequestParam(value = "devNo", required = false) String devNo,
                                          @RequestParam(value = "page", required = false, defaultValue = "1") int curPage,
                                          @RequestParam(value = "rows", required = false, defaultValue = "10") int pageSize
                                          , HttpSession session) {
        UserDO userDO = (UserDO)session.getAttribute(Contants.USER_SESSION);
        Page<DeviceDO> pageDevice = deviceService.pageDevice(devNo, userDO.getId(), curPage, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("total", pageDevice.getTotalNumber());
        map.put("rows", pageDevice.getItems());
        return map;
    }
}
