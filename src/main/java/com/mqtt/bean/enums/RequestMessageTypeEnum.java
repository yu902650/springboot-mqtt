package com.mqtt.bean.enums;

import com.mqtt.bean.request.MqttPayQRCodeRequest;

/**
 * @author bo bo
 * @Package com.mqtt.bean.enums
 * @date 2020/6/30 19:17
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @Description: MQTT 消息类型
 */
public enum RequestMessageTypeEnum {

    PAY_QRCODE("AAA", MqttPayQRCodeRequest.class, "请求支付"),
    ;
    private String msgType;
    private Class _class;
    private String msgName;

    RequestMessageTypeEnum(String msgType, Class _class, String msgName) {
        this.msgType = msgType;
        this._class = _class;
        this.msgName = msgName;
    }

    public static RequestMessageTypeEnum getByMsgType(String msgType) {
        for (RequestMessageTypeEnum item : RequestMessageTypeEnum.values()) {
            if (item.getMsgType().equalsIgnoreCase(msgType)) {
                return item;
            }
        }
        return null;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Class get_class() {
        return _class;
    }

    public void set_class(Class _class) {
        this._class = _class;
    }

    public String getMsgName() {
        return msgName;
    }

    public void setMsgName(String msgName) {
        this.msgName = msgName;
    }

    public static void main(String[] args) {
    }
}

