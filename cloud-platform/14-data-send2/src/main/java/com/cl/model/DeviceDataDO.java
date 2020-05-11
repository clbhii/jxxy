package com.cl.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * by cl at 2020/4/9 0009
 */
public class DeviceDataDO {

    private Long deviceId;

    private String  data;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadDate;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }



}
