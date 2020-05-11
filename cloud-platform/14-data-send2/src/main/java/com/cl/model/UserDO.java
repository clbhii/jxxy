package com.cl.model;

import java.util.Date;

public class UserDO {
    /**
     * 
     */
    private Long id;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private Date dateCreate;

    /**
     * 修改时间
     */
    private Date dateUpdate;

    /**
     * 获取
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取用户邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置用户邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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
     * 获取修改时间
     */
    public Date getDateUpdate() {
        return dateUpdate;
    }

    /**
     * 设置修改时间
     */
    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}