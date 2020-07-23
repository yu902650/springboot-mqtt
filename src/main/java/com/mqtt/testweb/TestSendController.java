package com.mqtt.testweb;

import com.mqtt.service.IMqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bo bo
 * @Package com.mqtt.testweb
 * @date 2020/7/19 0:22
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @Description:
 */
@RestController
@RequestMapping("send")
public class TestSendController {

    @Autowired
    private IMqttService mqttService;

    @RequestMapping("500")
    public String send100() throws InterruptedException {

        long s = System.currentTimeMillis();

        for (int i = 0; i < 500; i++) {
            mqttService.publish("5",
                    "  {\"payMethod\":1,\"price\":1,\"mType\":\"AAA\",\"ch\":110,\"version\":\"20102\",\"sn\":\"44474\",\"clientID\":\"867361028915062\"}");
            Thread.sleep(50);
        }

        long e = System.currentTimeMillis();

        return "send 500 success 共花费 =========== 》》》》》》》》  "+ (e-s);
    }

}
