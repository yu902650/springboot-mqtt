package com.mqtt.util;

import org.fusesource.mqtt.client.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by ouyang on 2017/10/9.
 */

@Component
public class MqttSubscribeCallback implements Callback<byte[]> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onSuccess(byte[] o) {
        logger.info("订阅成功");
    }

    @Override
    public void onFailure(Throwable throwable) {
        logger.info("订阅主题失败 :[{}]", throwable.getMessage());
        throwable.printStackTrace();
    }
}
