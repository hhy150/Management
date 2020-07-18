package com.example.management.entity;

public class SendAffairParam extends Department {


    public static final String SEND_RANDOMLY="random";

    public static final String SEND_PERSONAL="personal";

    public static final String SEND_CONDITIONAL="conditional";

    public static final String SEND_ALL="all";

    private int limit;

    private String memCondition; //从上面四个选

    private String templateType; //从UTIL里面的字符串选

    private String senderName;

    private String senderId;

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderId() {
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
}
