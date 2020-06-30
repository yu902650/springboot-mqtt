package com.mqtt.bean;

import java.io.Serializable;

/**
 * @author bo bo
 * @Package com.mqtt.bean
 * @date 2020/6/30 19:40
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @Description:
 */
public class MqttMessage implements Serializable {

    private String clientID;
    private String mType;
    private String sn;
    private String rsn;
    private String result;

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getRsn() {
        return rsn;
    }

    public void setRsn(String rsn) {
        this.rsn = rsn;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
