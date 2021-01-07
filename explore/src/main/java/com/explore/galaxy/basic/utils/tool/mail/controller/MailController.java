package com.explore.galaxy.basic.utils.tool.mail.controller;

import com.explore.galaxy.basic.utils.tool.mail.expand.MailEntity;
import com.explore.galaxy.basic.utils.tool.mail.service.IMailInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private IMailInitService iMailInitService;
    @PostMapping("send")
    public void send(@RequestBody MailEntity entity) throws InterruptedException {
        iMailInitService.sendBasicMail(entity);
    }
}
