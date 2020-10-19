package com.explore.galaxy.basic.utils.tool.mail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource(value = "classpath:mail.properties")
public class MailConfiguration {

    public static JavaMailSenderImpl commonMailConfig;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.username}")
    private String userName;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.defaultEncoding}")
    private String defaultEncoding;
    @Value("${spring.mail.properties.mail.smtp.ssl.enable}")
    private String SSL;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String SMTPAuth;

    @Bean
    JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.host);
//        mailSender.setPort(this.port);
        mailSender.setUsername(this.userName);
        mailSender.setPassword(this.password);
        mailSender.setDefaultEncoding(this.defaultEncoding);
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.ssl.enable", this.SSL);
        javaMailProperties.put("mail.smtp.auth", this.SMTPAuth);
        mailSender.setJavaMailProperties(javaMailProperties);
        this.commonMailConfig = mailSender;
        return mailSender;
    }
}
