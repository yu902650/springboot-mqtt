package com.mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.IOException;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SpringbootMqttApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SpringbootMqttApplication.class, args);
        //阻塞下，别让他结束哦
        System.in.read();
    }

}
