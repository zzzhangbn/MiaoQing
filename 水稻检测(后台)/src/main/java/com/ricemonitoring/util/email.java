package com.ricemonitoring.util;

import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 9:30
 */
@Component
public class email {
    /*
        邮件发送功能
        param:
            address 收件人的邮箱地址
            title 邮件标题
            content 邮件内容

     */
    public void sendEmail(String address,String title,String content) throws Exception{
                Properties properties = new Properties();
                properties.put("mail.transport.protocol", "smtp");// 连接协议
                properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
                properties.put("mail.smtp.port", 465);// 端口号
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
                properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
                // 得到回话对象
                Session session = Session.getInstance(properties);
                // 获取邮件对象
                Message message = new MimeMessage(session);
                // 设置发件人邮箱地址
                message.setFrom(new InternetAddress("631041505@qq.com"));
                // 设置收件人邮箱地址
                message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(address)});
                //message.setRecipient(Message.RecipientType.TO, new InternetAddress("xxx@qq.com"));//一个收件人
                // 设置邮件标题
                message.setSubject(title);
                // 设置邮件内容
                message.setText(content);
                // 得到邮差对象
                Transport transport = session.getTransport();
                // 连接自己的邮箱账户
                transport.connect("631041505@qq.com", "rrdetndwjpvlbfec");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
                // 发送邮件
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
    }


}
