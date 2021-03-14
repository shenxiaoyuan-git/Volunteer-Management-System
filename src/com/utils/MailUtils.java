package com.utils;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtils {

	
	public static void sendMail(String to,String code) throws Exception{
		//1.创建连接对象,连接到邮箱服务器
		
		 Properties pro = new Properties(); 
		pro.setProperty("mail.debug", "true");
		// 发送服务器需要身份验
		pro.setProperty("mail.smtp.auth", "true");
		// 设置邮件服务器主机名
		pro.setProperty("mail.host", "smtp.126.com");
		// 发送邮件协议名称
		pro.setProperty("mail.transport.protocol", "smtp");
		
		// pro.put("mail.smtp.host", "smtp.126.com");  
		 //pro.put("mail.smtp.auth", "true");   // SSL加密  
		 
		 
		 //MailSSLSocketFactory sf = null;
		// sf = new MailSSLSocketFactory();   // 设置信任所有的主机  
		// sf.setTrustAllHosts(true);
		// pro.put("mail.smtp.ssl.enable", "true");
		 //pro.put("mail.smtp.ssl.socketFactory", sf);
		 // 根据邮件的会话属性构造一个发送邮件的Session，这里需要注意的是用户名那里不能加后缀，否则便不是用户名了   
		 //还需要注意的是，这里的密码不是正常使用邮箱的登陆密码，而是客户端生成的另一个专门的授权码   
	//	 MailAuthenticator authenticator = new MailAuthenticator("tuzongxun123",     "客户端授权码");   Session session = Session.getInstance(pro, authenticator);   // 根据Session 构建邮件信息   Message message = new MimeMessage(session);   // 创建邮件发送者地址   Address from = new InternetAddress("tuzongxun123@163.com");   // 设置邮件消息的发送者   message.setFrom(from); 
		
	//	Properties props = new Properties();
		
	//	Session session = Session.getInstance(pro,new Authenticator(){
			
	//		protected PasswordAuthentication getPasswordAuthentication(){
				
	//			return new PasswordAuthentication("sxy_1020@126.com","sxy121007");
	//		}
			
	//	});
		 Session session = Session.getInstance(pro);
		
		//2.创建邮件对象
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("sxy_1020@126.com"));
	//	message.setRecipient(RecipientType.TO, new InternetAddress(to));
		message.setSubject("来自志愿者管理系统的激活邮件");
		message.setContent("<h1>来自志愿者管理系统的激活邮件，激活请点击以下链接：</h1><h3><a href='http://localhost:8080/Volunteer/active.action?code="+code+"'>http://localhost:8080/Volunteer/active.action?code="+code+"</a></h3>","text/html;charset=UTF-8");
		Transport tran = session.getTransport();

		tran.connect("smtp.126.com","sxy_1020@126.com", "sxy121007");//连接到新浪邮箱服务器
		tran.sendMessage(message, new Address[]{ new InternetAddress(to)});//设置邮件接收人
		//Transport.send(message);
		tran.close();
		//Transport.send(message);
		
		
	
		
	}
	
	
}
