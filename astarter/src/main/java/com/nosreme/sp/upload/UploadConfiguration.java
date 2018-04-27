package com.nosreme.sp.upload;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.nosreme.svc.upload.StorageProperties;
import com.nosreme.svc.upload.StorageService;

@Configuration
@ComponentScan("com.nosreme.sp.upload.protocol.http,com.nosreme.svc.upload")
@EnableConfigurationProperties(StorageProperties.class)
public class UploadConfiguration {

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
