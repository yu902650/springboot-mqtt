package com.mqtt.util;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MqttListener implements Listener {
    @Autowired
    private MqttMessageHandler mqttMessageHandler;
    @Value("${mqtt.handler:0}")
    private String mqttHandler;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onConnected() {
        logger.info("mqtt connected....");
    }

    @Override
    public void onDisconnected() {
        logger.info("mqtt disconnected....");
    }

    @Override
    public void onPublish(UTF8Buffer utf8Buffer, Buffer buffer, Runnable ack) {


        if ("1".equals(mqttHandler)) {

            String record = buffer.utf8().toString();
            //  加密数据处理开始
            if (!JsonUtil.isJson(record)) {
                logger.info("指令不是合法的json");
                return;
            }
            String tips = utf8Buffer.toString();
            mqttMessageHandler.handler(tips, record);
            logger.info("recevie topic:[{}],data:[{}]", utf8Buffer.toString(), record);
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        logger.info("mqtt onFailure:[{}]....", throwable.getMessage());
        throwable.printStackTrace();
    }
}
