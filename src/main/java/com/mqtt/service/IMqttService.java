package com.mqtt.service;

import com.mqtt.bean.MqttMessage;
import org.fusesource.mqtt.client.QoS;

/**
 * @author bo bo
 * @Package com.mqtt.service
 * @date 2020/6/30 19:43
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @Description: impl
 */
public interface IMqttService {

    /**
     * 发布消息
     *
     * @param topic
     * @param string
     */
    void publish(String topic, String string);

    /**
     *
     * @param topic
     * @param string
     * @param qoS
     */
    void publish(String topic, String string, QoS qoS);

    /**
     * 发布消息并且指定消息等级
     * @param topic
     * @param mqttMessage
     * @param qoS
     */
    void publish(String topic, MqttMessage mqttMessage, QoS qoS);
    /**
     * 发布消息
     *
     * @param topic
     * @param mqttMessage
     */
    void publish(String topic, MqttMessage mqttMessage);


    /**
     * 订阅所有消息
     */
    void subscrebieAll();

    /**
     * 订阅消息
     */
    void subscrebie(String topic);

    /**
     * 是否连接状态
     *
     * @return
     */
    boolean isConnected();



}
