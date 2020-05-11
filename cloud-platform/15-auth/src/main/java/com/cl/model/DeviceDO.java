package com.cl.model;

import java.util.Date;

/**
 * 设备
 */
public class DeviceDO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 设备类型
     */
    private Integer devType;
    /**
     * 设备编号
     */
    private String devNo;

    /**
     * 设备名称
     */
    private String devName;
    /**
     * 设备状态
     */
    private Integer devStatus;
    /**
     * 设备图片
     */
    private String devImg;

    /**
     * 创建时间
     */
    private Date dateCreate;

    /**
     * 更新时间
     */
    private Date dateUpdate;

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
     * 获取设备编号
     */
    public String getDevNo() {
        return devNo;
    }

    /**
     * 设置设备编号
     */
    public void setDevNo(String devNo) {
        this.devNo = devNo == null ? null : devNo.trim();
    }

    /**
     * 获取设备名称
     */
    public String getDevName() {
        return devName;
    }

    /**
     * 设置设备名称
     */
    public void setDevName(String devName) {
        this.devName = devName == null ? null : devName.trim();
    }

    public Integer getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(Integer devStatus) {
        this.devStatus = devStatus;
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

    /**
     * 获取更新时间
     */
    public Date getDateUpdate() {
        return dateUpdate;
    }

    /**
     * 设置更新时间
     */
    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getDevImg() {
        return devImg;
    }

    public void setDevImg(String devImg) {
        this.devImg = devImg;
    }

    public Integer getDevType() {
        return devType;
    }

    public void setDevType(Integer devType) {
        this.devType = devType;
    }
}