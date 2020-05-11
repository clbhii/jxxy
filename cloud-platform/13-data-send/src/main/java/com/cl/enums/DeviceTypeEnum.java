package com.cl.enums;

public enum DeviceTypeEnum {
    LIGHT(1, "css/img/1.jpg", "智能电灯"),
    AIR_CONDITIONING(2, "css/img/2.jpg", "智能空调")
    ;

    /**
     * 值
     */
    private Integer value;
    /**
     * 图片地址
     */
    private String img;
    /**
     * 描述
     */
    private String desc;

    DeviceTypeEnum(Integer value, String img, String desc) {
        this.value = value;
        this.img = img;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static DeviceTypeEnum getDeviceTypeEnum(Integer value) {
        for(DeviceTypeEnum deviceTypeEnum : DeviceTypeEnum.values()) {
            if(deviceTypeEnum.getValue().equals(value)){
                return  deviceTypeEnum;
            }
        }
        throw new RuntimeException("设备类型有误");
    }

}
