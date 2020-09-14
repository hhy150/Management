package com.example.management.util;


import com.example.management.entity.MemAffair;
import com.example.management.entity.Student;
import com.example.management.mapper.MemAffairMapper;
//import com.sun.security.sasl.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Security;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

    @Autowired
    private MemAffairMapper memAffairMapper;

    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static Properties props = null;

    private static final String FROM_MAIL = "1405679773@qq.com";

    public static final String FROM_MAIL_KEY = "rvrakeejfeszfibh";


    /**
     * 在构造函数中初始化相关的参数。这是固定用法。
     */
    public MailUtil() {
        // 设置SSL连接、邮件环境
    //    Security.addProvider(new Provider());
        props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.auth", "true");
    }

    private Map<String,String> template(String type, String senderName, String affairName, LocalDateTime time){
        Map<String,String> result = new HashMap<>();
        switch (type){
            case com.example.management.util.AffairUtil.TYPE_REMIND_TASK:
                result.put("subject","来自 "+ senderName +" 的任务ddl提醒");
                result.put("content","您的任务"+affairName+"将于"+time.toString()+"截止，快去看看自己有没有完成鸭");
                break;
            case com.example.management.util.AffairUtil.TYPE_NEW_TASK:
                result.put("subject","来自 "+ senderName +" 的新任务派发");
                result.put("content","您的新任务"+affairName+"已经派发，截止日期为"+time.toString()+"，不要再摸鱼啦");
                break;
            default:
                System.out.println("you didn't chose any template!");
                return null;
        }
        return result;

    }

    public String sendMail(String type, String senderName, String affairName,Long affairId,
                           LocalDateTime time, List<Student> students){
        Map<String, String> template = template(type, senderName,affairName,time);
        if (template == null){
            return "派发失败：选择的模板不正确";
        }
        for (Student student:students){
            if ("发送失败".equals(sendMailCore(template,student.getStuMail()))){
                return "派发异常：可能有部分成员未收到信息";
            }
            MemAffair memAffair = new MemAffair();
            memAffair.setAffairId(affairId);
            memAffair.setMemId(student.getId());
            memAffairMapper.insert(memAffair);
        }
        return "派发成功";
    }

    /**
     * 发送邮件
     * @param template
     * @param receiver
     */
    public String sendMailCore(Map template,String receiver) {
        try {
            // 建立邮件会话com.sun.net.ssl.jar
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                // 身份认证
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 账户 授权码
                    return new PasswordAuthentication(FROM_MAIL, FROM_MAIL_KEY);
                }
            });

            // 建立邮件对象
            MimeMessage message = new MimeMessage(session);
            // 设置邮件的发件人、收件人、主题
            // 附带发件人名字
            message.setFrom(new InternetAddress(FROM_MAIL));
            message.setRecipients(Message.RecipientType.TO, receiver);
            message.setSubject(template.get("subject").toString());
            // 文本部分
            message.setContent(template.get("content").toString(), "text/html;charset=UTF-8");
            message.saveChanges();
            // 发送邮件
            Transport.send(message);

            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败";
        }
    }
}
