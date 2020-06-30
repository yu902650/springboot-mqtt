package com.mqtt.bean.response;


import com.mqtt.bean.MqttMessage;

/**
 * 返回二维码支付结果
 * AAB
 * Created by ken on 2019/10/16.
 */
public class MqttPayQRCodeResponse extends MqttMessage {

    private String mType = "AAB";
    private String clientNo;
    //支付宝二维码地址
    private String ZhifubaoQR;
    //微信二维码地址
    private String WeixinQR;
    //订单号
    private String orderNo;
    private String unified;

    public MqttPayQRCodeResponse() {
    }

    public String getmType() {
        return mType;
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getZhifubaoQR() {
        return ZhifubaoQR;
    }

    public void setZhifubaoQR(String zhifubaoQR) {
        ZhifubaoQR = zhifubaoQR;
    }

    public String getWeixinQR() {
        return WeixinQR;
    }

    public void setWeixinQR(String weixinQR) {
        WeixinQR = weixinQR;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUnified() {
        return unified;
    }

    public void setUnified(String unified) {
        this.unified = unified;
    }
}
