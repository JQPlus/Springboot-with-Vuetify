package com.explore.galaxy;

import com.explore.galaxy.basic.utils.tool.mail.expand.MailEntity;
import com.explore.galaxy.basic.utils.tool.mail.service.impl.MailInitServiceImpl;
import com.explore.galaxy.basic.utils.tool.mail.service.impl.MailSendServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
public class TestUnit {

    @Autowired
    private MailInitServiceImpl mailService;

    @Test
    public void test() throws InterruptedException {
        MailEntity entity = new MailEntity();
        entity.setTo(new String[]{"543796673@qq.com"});
        entity.setCc(null);
        entity.setTitle("test");
        entity.setContent("test");
        entity.setMailFile(null);
        mailService.sendBasicMail(entity);
    }
}
