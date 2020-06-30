package com.mqtt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mqtt.bean.MqttMessage;
import com.mqtt.bean.response.MqttPayQRCodeResponse;
import com.mqtt.service.IMqttService;
import com.mqtt.util.MqttConnectCallback;
import com.mqtt.util.MqttListener;
import com.mqtt.util.MqttSubscribeCallback;
import org.apache.commons.lang.StringUtils;
import org.fusesource.mqtt.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.Random;

import static org.fusesource.mqtt.client.QoS.AT_MOST_ONCE;

/**
 * @author bo bo
 * @Package com.mqtt.service.impl
 * @date 2020/6/30 19:43
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @Description: impl
 */
@Service
public class MqttService implements IMqttService {
    public static byte[] payload;

    @Value("${mqtt.url}")
    private String mqttHost;
    @Value("${mqtt.username}")
    private String mqttUsername;
    @Value("${mqtt.password}")
    private String mqttPassword;
    @Value("${mqtt.topic:5}")
    private String mqttTopic;

    private CallbackConnection connection;
    @Autowired
    private MqttListener mqttListener;
    @Autowired
    private MqttConnectCallback mqttConnectCallback;
    @Autowired
    private MqttSubscribeCallback mqttSubscribeCallback;
    private Logger logger = LoggerFactory.getLogger("mqttLog");

    @PostConstruct
    private void init() {
        try {
            this.connection = this.getMQTTConnection(null);
            this.subscrebieAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void publish(String topic, String message, QoS qoS) {
        try {

            payload = message.getBytes("UTF-8");
            connection.publish(topic, payload, qoS == null ? QoS.AT_MOST_ONCE : qoS, false, new Callback<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    logger.info("发送消息成功topic:[{}],message:[{}]", topic, message);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    logger.info("发送消息失败topic:[{}],message:[{}]:error:[{}]", new Object[]{topic, message, throwable.getMessage()});
                    throwable.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param topic
     * @param mqttMessage
     */
    @Override
    public void publish(String topic, MqttMessage mqttMessage) {
        this.setMType(mqttMessage);
        int sn = new Random().nextInt(65535) + 1;
        mqttMessage.setSn(sn + "");
        this.publish(topic, JSONObject.toJSONString(mqttMessage), null);
    }

    /**
     * @param topic
     * @param mqttMessage
     * @param qoS
     */
    @Override
    public void publish(String topic, MqttMessage mqttMessage, QoS qoS) {
        this.setMType(mqttMessage);
        int sn = new Random().nextInt(65535) + 1;
        mqttMessage.setSn(sn + "");
        this.publish(topic, JSONObject.toJSONString(mqttMessage), qoS);
    }

    /**
     * @param topic
     * @param mqttMessage
     */
    @Override
    public void publish(String topic, String mqttMessage) {
        this.publish(topic, mqttMessage, null);
    }

    @Override
    public void subscrebieAll() {
        connection.subscribe(new Topic[]{new Topic("5", AT_MOST_ONCE)}, mqttSubscribeCallback);
    }

    @Override
    public void subscrebie(String topic) {
        Topic tc = new Topic(topic, AT_MOST_ONCE);
        connection.subscribe(new Topic[]{tc}, mqttSubscribeCallback);

    }

    @Override
    public boolean isConnected() {
        return true;
    }

    private void setMType(MqttMessage message) {
        String mtype = "";
        for (Field field : message.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getName().equalsIgnoreCase("msgType")) {
                field.setAccessible(true);

                try {
                    Object properties = field.get(message);
                    if (properties != null) {
                        mtype = properties.toString();
                        break;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        message.setmType(mtype);
    }


    /**
     * 获取点对点的连接
     *
     * @param clientId
     * @return
     */
    private CallbackConnection getMQTTConnection(String clientId) {
        MQTT mqtt = new MQTT();
        try {
            mqtt.setHost(mqttHost);
            mqtt.setUserName(mqttUsername);
            mqtt.setPassword(mqttPassword);
            mqtt.setCleanSession(true);
            if (StringUtils.isNotBlank(clientId)) {
                mqtt.setClientId(clientId);
            }
            CallbackConnection callbackConnection = mqtt.callbackConnection();
            callbackConnection.listener(mqttListener);
            callbackConnection.connect(mqttConnectCallback);
            return callbackConnection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    class MqttCallback implements Callback {

        public String topic, message;

        public MqttCallback(String topic, String message) {
            this.topic = topic;
            this.message = message;
        }

        @Override
        public void onSuccess(Object o) {
            logger.info("发送消息成功topic:[{}],message:[{}]", topic, message);
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    }
}
