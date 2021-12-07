package com.QCINE.Main;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;

import java.util.Date;

@SpringBootApplication
public class Application_RUN {
    public static void main(String[] args) {
        SpringApplication.run(Application_RUN.class, args);

    }
}
