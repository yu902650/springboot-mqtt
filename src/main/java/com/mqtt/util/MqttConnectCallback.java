package com.mqtt.util;

import org.fusesource.mqtt.client.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author bo bo
 * @Package com.mqtt.util
 * @date 2020/6/30
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @Description:
 */
@Component
public class MqttConnectCallback implements Callback<Void> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onSuccess(Void aVoid) {
        logger.info("mqtt connnect success");
//        mqttService.subscrebieAll();
    }

    @Override
    public void onFailure(Throwable throwable) {
        logger.debug("connection failure:[{}]", throwable.getMessage());
        throwable.printStackTrace();
    }
}
