package com.explore.galaxy.basic.utils.tool.mail.service;

import org.springframework.web.multipart.MultipartFile;


public interface IMailSendService {
    /**
     * send mail
     */
    void sendMail(String[] to, String[] cc, String title, String content) throws InterruptedException;

    void sendMailWithFile(String[] to, String[] cc, String title, String content,MultipartFile[] mailFile) throws InterruptedException;
}
