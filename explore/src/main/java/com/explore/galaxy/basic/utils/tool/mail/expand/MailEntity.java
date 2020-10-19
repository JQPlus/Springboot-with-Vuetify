package com.explore.galaxy.basic.utils.tool.mail.expand;

import org.springframework.web.multipart.MultipartFile;

public class MailEntity {
    private String[] to;
    private String from;
    private String[] cc;
    private String title;
    private String content;
    private MultipartFile[] mailFile;

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile[] getMailFile() {
        return mailFile;
    }

    public void setMailFile(MultipartFile[] mailFile) {
        this.mailFile = mailFile;
    }
}
