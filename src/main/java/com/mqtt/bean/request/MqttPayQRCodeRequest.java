package com.mqtt.bean.request;

import com.mqtt.bean.MqttMessage;
import com.sun.scenario.effect.impl.prism.PrImage;

/**
 * @author bo bo
 * @Package com.mqtt.bean.request
 * @date 2020/6/30 19:19
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @Description:  消息类型AAA
 */
public class MqttPayQRCodeRequest extends MqttMessage {

    private String clientNo;
    private String ch;
    private Long price;             // 商品的单价
    private Integer payMethod;      // 支付方式 1二维码
    private Integer count = 1;
    private String[] channelNoArr;  //
    private int payType = 0;        // 指定支付方式,默认支付宝和微信
    private Long orderAmount;       // 订单的总金额(一个订单多个商品)
    private String discountVoucher; // 折扣的凭证,如邀请码编号

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public MqttPayQRCodeRequest() {
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String[] getChannelNoArr() {
        return channelNoArr;
    }

    public void setChannelNoArr(String[] channelNoArr) {
        this.channelNoArr = channelNoArr;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getDiscountVoucher() {
        return discountVoucher;
    }

    public void setDiscountVoucher(String discountVoucher) {
        this.discountVoucher = discountVoucher;
    }
}
