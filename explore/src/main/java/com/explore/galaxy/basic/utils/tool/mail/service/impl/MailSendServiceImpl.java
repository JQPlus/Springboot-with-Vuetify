package com.explore.galaxy.basic.utils.tool.mail.service.impl;

import com.explore.galaxy.basic.utils.tool.mail.expand.MailEntity;
import com.explore.galaxy.basic.utils.tool.mail.service.IMailSendService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static org.apache.logging.log4j.LogManager.getLogger;

@Component
public class MailSendServiceImpl implements IMailSendService, Runnable {

    private final Logger logger = getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String from;

    private String alias = "T";

    private BlockingQueue<MailEntity> queue = new LinkedBlockingDeque<>(3000);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    @Qualifier(value = "mailThreadPool")
    private ExecutorService executorService;

    @Autowired
    private MailSendServiceImpl mailSendService;

    /**
     * @auther: Huang Jiaqi
     * @description: request method has to be post
     */
    @Override
    public void sendMailWithFile(@NotNull @NotEmpty String[] to,
                                 String[] cc,
                                 @NotNull @NotEmpty String title,
                                 String content,
                                 @NotNull MultipartFile[] mailFile) throws InterruptedException {
        MailEntity entity = new MailEntity();
        entity.setTo(to);
        entity.setCc(cc);
        entity.setTitle(title);
        entity.setContent(content);
        entity.setMailFile(mailFile);
        //如果队列已满，那么会等待1000ms后(循环)直到插入队列
        queue.offer(entity, 1000, TimeUnit.MILLISECONDS);
        executorService.execute(mailSendService);
    }

    @Override
    public void sendMail(@NotNull @NotEmpty String[] to, String[] cc, @NotNull @NotEmpty String title, String content) throws InterruptedException {
        MailEntity entity = new MailEntity();
        entity.setTo(to);
        entity.setCc(cc);
        entity.setTitle(title);
        entity.setContent(content);
        entity.setMailFile(null);
        queue.offer(entity, 1000, TimeUnit.MILLISECONDS);
        executorService.execute(mailSendService);
    }

    /**
     * @description: sending mail with thread poll
     */
    @Override
    public void run() {
        MailEntity entity = null;
        try {
            entity = queue.poll(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("邮件发送失败", e.getMessage());
        }
        sendRealMail(entity);
    }

    private MimeMessage sendRealMail(MailEntity entity) {
        String[] toAddr = entity.getTo();
        String[] cc = entity.getCc();
        String title = entity.getTitle();
        String content = entity.getContent();
        MultipartFile[] mailFile = entity.getMailFile();
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setTo(toAddr);
            if (cc != null && cc.length > 0) {
                helper.setCc(cc);
            }
            helper.setFrom(new InternetAddress(from, alias, "UTF-8").toString());
            helper.setSubject(title);
            helper.setText(content, true);
            if (mailFile != null && mailFile.length > 0) {
                for (MultipartFile file : mailFile) {
                    //上传时的初始文件名
                    String OriginalFileName = file.getOriginalFilename();
                    //取到后缀
                    String suffix = OriginalFileName.substring(OriginalFileName.lastIndexOf('.'));
                    //以时间戳作为文件名保存在服务器上
                    String customFileName = new Date().getTime() + suffix;
                    //服务器的自定义文件夹
                    String filePath = "C:\\Users\\Experionadmin\\Desktop\\test\\" + customFileName;
                    //服务器上创建文件
                    File newFile = new File(filePath);
                    try {
                        file.transferTo(newFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
                    //以初始名命名添加到helper中进行邮件发送
                    helper.addAttachment(OriginalFileName, fileSystemResource);
                }
            }
            javaMailSender.send(message);
            logger.info("邮件发送成功");
            return message;
        } catch (Exception e) {
            logger.error("邮件发送失败", e);
            return null;
        }
    }

    private String ReadFile(InputStream input, String str) {
        try {
            Reader reader = new InputStreamReader(input);
            BufferedReader htmlReader = new BufferedReader(reader);
            String line;
            while ((line = htmlReader.readLine()) != null) {
                str = str + line + "\n";
            }
            htmlReader.close();
        } catch (Exception e) {
            logger.error("html错误");
        }
        return str;
    }
}


