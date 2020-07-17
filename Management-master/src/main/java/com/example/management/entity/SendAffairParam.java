package com.example.management.entity;

public class SendAffairParam {


    private int limit;

    private String memCondition;

    private String templateType;

    private String senderName;

    private Long senderId;

    private String receiver;

    private Affair content;

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setMemCondition(String memCondition) {
        this.memCondition = memCondition;
    }

    public String getMemCondition() {
        return memCondition;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setContent(Affair content) {
        this.content = content;
    }

    public Affair getContent() {
        return content;
    }
}
