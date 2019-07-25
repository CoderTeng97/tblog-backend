package com.tg.blog.base.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * 邮箱发送工具
 */
public class SendEmailUtils {
	public static void sendVerificationCode(String email,String content,String title) throws GeneralSecurityException{
		  // 
        String to = email;

        // 
        String from = "18716664880@163.com";

        //
        String host = "smtp.163.com";  //

        // 
        Properties properties = System.getProperties();

        // 
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("18716664880@163.com", "mymumu01"); //
            }
        });

        try{
            // 
            MimeMessage message = new MimeMessage(session);

            // 
            message.setFrom(new InternetAddress(from));

            // 
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //设置邮件标题
            message.setSubject(title);

            //设置邮件内容
            message.setText(content);

            // 
            Transport.send(message);
            System.out.println("Sent message successfully");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
	}
}
