package com.cl.enums;

/**
 * 设备状态
 */
public enum DeviceStatusEnum {
    online(1, "在线"),
    offline(0, "离线")
    ;
    /**
     * 值
     */
    private Integer value;
    /**
     * 描述
     */
    private String desc;

    DeviceStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
