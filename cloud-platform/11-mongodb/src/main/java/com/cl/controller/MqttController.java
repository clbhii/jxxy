package com.cl.controller;

import com.cl.common.Result;
import com.cl.common.ResultSupport;
import com.cl.service.IMqttService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * by cl at 2020/4/2 0002
 */
@Controller
@RequestMapping("/mqtt")
public class MqttController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IMqttService mqttService;

    public Result<Void> pub(@RequestParam(value = "topic", required = true) String topic
                            ,@RequestParam(value = "msg", required = true) String msg) {
        Result<Void> result = new ResultSupport<>(true);
        try {
            mqttService.pub(topic, msg);
        } catch (MqttException e) {
            log.error("发布失败", e);
            result.setSuccess(false);
            result.setMessage("发布失败,请联系管理员");
        }
        return result;
    }


}
