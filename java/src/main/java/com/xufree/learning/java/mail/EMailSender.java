package com.xufree.learning.java.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * 发送邮件工具类
 *
 * @author zhangmingxu ON 15:37 2019-08-22
 **/
public class EMailSender {

    /**
     * 使用smtp协议发送邮件
     *
     * @param from    发送人邮箱
     * @param user    发送人登陆服务器用户名
     * @param pwd     发送人登陆服务器用密码
     * @param to      收件人
     * @param subject 主题
     * @param context 内容
     */
    public static void SmtpSend(String from, String user, String pwd, String to, String subject, String context) {
        Properties props = new Properties();
        String HOST = "smtp.163.com";
        props.put("mail.smtp.host", HOST);//设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
        props.put("mail.smtp.auth", "true");  //需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        Session session = Session.getDefaultInstance(props);//用props对象构建一个session
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);//用session为参数定义消息对象
        try {
            message.setFrom(new InternetAddress(from));// 加载发件人地址
            InternetAddress[] sendTo = new InternetAddress[1]; // 加载收件人地址
            sendTo[0] = new InternetAddress(to);
            message.addRecipients(Message.RecipientType.TO, sendTo);
            message.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(from));//设置在发送给收信人之前给自己（发送方）抄送一份，不然会被当成垃圾邮件，报554错
            message.setSubject(subject);//加载标题
            Multipart multipart = new MimeMultipart();//向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            BodyPart contentPart = new MimeBodyPart();//设置邮件的文本内容
            contentPart.setText(context);
            multipart.addBodyPart(contentPart);
            message.setContent(multipart);//将multipart对象放到message中
            message.saveChanges(); //保存邮件
            Transport transport = session.getTransport("smtp");//发送邮件
            transport.connect(HOST, user, pwd);//连接服务器的邮箱
            transport.sendMessage(message, message.getAllRecipients());//把邮件发送出去
            transport.close();//关闭连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
