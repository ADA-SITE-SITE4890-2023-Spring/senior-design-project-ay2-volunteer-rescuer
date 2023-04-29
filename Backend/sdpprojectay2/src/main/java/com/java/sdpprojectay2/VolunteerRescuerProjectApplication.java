package com.java.sdpprojectay2;

import com.java.sdpprojectay2.config.MediaStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication
@EnableConfigurationProperties({MediaStorageProperties.class})
@EnableScheduling
public class VolunteerRescuerProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(VolunteerRescuerProjectApplication.class, args);
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
