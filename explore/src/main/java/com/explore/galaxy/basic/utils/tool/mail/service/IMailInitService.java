package com.explore.galaxy.basic.utils.tool.mail.service;

import com.explore.galaxy.basic.utils.tool.mail.expand.MailEntity;

public interface IMailInitService {
    void sendBasicMail(MailEntity entity) throws InterruptedException;
}
