package com.mqtt.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.mqtt.bean.enums.RequestMessageTypeEnum;
import com.mqtt.bean.request.MqttPayQRCodeRequest;
import com.mqtt.bean.response.MqttPayQRCodeResponse;
import com.mqtt.service.IMqttService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author bo bo
 * @Package com.mqtt.util
 * @date 2020/6/30 19:40
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @Description: 消息处理类
 */
@Component
public class MqttMessageHandler {

    @Autowired
    private IMqttService mqttService;

    private Logger logger = LoggerFactory.getLogger("mqttLog");

    //自动创建线程池，有缺点。消息发送时如果过多，会导致日志打印MQ发送消息失败问题，但是其实不是MQBroken的问题，是线程吃的问题
    //private ExecutorService threadPools = Executors.newFixedThreadPool(300);

    //手动创建线程池，阿里开发规范推荐
    private ThreadPoolExecutor threadPools = new ThreadPoolExecutor(10, 20, 120, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>());

    /**
     * 消息处理
     *
     * @param topic
     * @param str
     */
    public void handler(String topic, String str) {

        try {

            JSONObject jsonObject = JSON.parseObject(str);
            String msgType = jsonObject.getString("mType");

            RequestMessageTypeEnum mqttMessage = RequestMessageTypeEnum.getByMsgType(msgType);
            if (mqttMessage == null) {
                return;
            }

            switch (mqttMessage) {

                //业务示例
                case PAY_QRCODE:
                    // 终端设备发起支付请求
                    MqttPayQRCodeRequest payQRCodeRequest = JSON.toJavaObject(jsonObject, MqttPayQRCodeRequest.class);
                    threadPools.submit(new Runnable() {
                        @Override
                        public void run() {
                            createOrder(payQRCodeRequest);
                        }
                    });
                    break;

                //TODO 继续你的业务处理

                default:
                    throw new RuntimeException("消息类型不正确");
            }

        } catch (JSONException ex) {
            logger.info("MQTT内容转JSON失败");
            ex.printStackTrace();
        }
    }

    //创建订单
    private void createOrder(MqttPayQRCodeRequest payQRCodeRequest) {

        /**
         *      MqttPayQRCodeRequest AAA
         *      以这个消息实体为例，你可以去处理自己业务中的消息
         *          a类型.要返回对应消息给客户端
         *          b类型.自己跑业务，跑完就可以了，不用返回的
         */
        /** a 类型 要返回数据给客户端的  */
        String clientNo = payQRCodeRequest.getClientNo(); //终端设备号从发来的消息中获取。


        MqttPayQRCodeResponse response = new MqttPayQRCodeResponse();
        response.setmType("AAB"); //收到客户端的AAA,我们返回一条AAB的消息
        response.setClientNo(clientNo);  //把发消息设备的clientno放到服务端返回的消息中
        //...TODO 处理完后，把response返回给终端
        mqttService.publish(clientNo,response); //结束
    }

}

