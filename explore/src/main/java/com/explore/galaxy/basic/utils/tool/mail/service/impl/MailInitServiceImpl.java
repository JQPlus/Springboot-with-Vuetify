package com.explore.galaxy.basic.utils.tool.mail.service.impl;

import com.explore.galaxy.basic.utils.tool.mail.expand.MailEntity;
import com.explore.galaxy.basic.utils.tool.mail.service.IMailInitService;
import com.explore.galaxy.basic.utils.tool.mail.service.IMailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Combine the mail information
 */
@Service
public class MailInitServiceImpl implements IMailInitService {

    @Autowired
    private IMailSendService iMailSendService;

    @Override
    public void sendBasicMail(MailEntity entity) {
        try {
            String[] to = entity.getTo();
            String[] cc = entity.getCc();
            String title = entity.getTitle();
            String content = entity.getContent();
            iMailSendService.sendMail(to, cc, title, content);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
