package com.cl.model;

import java.util.Date;

public class DeviceFieldDO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 设备id
     */
    private Long devId;

    /**
     * 字段key
     */
    private String fieldKey;

    /**
     * 字段值
     */
    private String fieldValue;

    /**
     * 创建时间
     */
    private Date dateCreate;

    /**
     * 获取主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取设备id
     */
    public Long getDevId() {
        return devId;
    }

    /**
     * 设置设备id
     */
    public void setDevId(Long devId) {
        this.devId = devId;
    }

    /**
     * 获取字段key
     */
    public String getFieldKey() {
        return fieldKey;
    }

    /**
     * 设置字段key
     */
    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey == null ? null : fieldKey.trim();
    }

    /**
     * 获取字段值
     */
    public String getFieldValue() {
        return fieldValue;
    }

    /**
     * 设置字段值
     */
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue == null ? null : fieldValue.trim();
    }

    /**
     * 获取创建时间
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * 设置创建时间
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
}