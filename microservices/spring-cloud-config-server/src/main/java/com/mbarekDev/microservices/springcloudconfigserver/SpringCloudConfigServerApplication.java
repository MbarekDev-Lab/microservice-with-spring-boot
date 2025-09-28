package com.mbarekDev.microservices.springcloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication(exclude = {org.springframework.cloud.config.client.ConfigClientAutoConfiguration.class})
public class SpringCloudConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigServerApplication.class, args);
    }

    //E:\Code\JAVA_Code\spring-microservices-imp\microservices\spring-cloud-config-server\src\main\java\com\mbarekDev\microservices\springcloudconfigserver

}
