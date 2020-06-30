package com.mqtt;

import com.mqtt.bean.response.MqttPayQRCodeResponse;
import com.mqtt.service.IMqttService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMqttApplicationTests {

    @Autowired
    private IMqttService mqttService;


    @Test
    public void testmqSend() {
        //模拟服务处理端向消费端发消息
        MqttPayQRCodeResponse mqtt = new MqttPayQRCodeResponse();
        mqtt.setmType("AAB");
        mqtt.setOrderNo("20200630xxxxxx");
        mqttService.publish("123",mqtt);
    }

}
