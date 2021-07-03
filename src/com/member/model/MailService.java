package com.member.model;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
	
	public final String SUBJECT = "UNDERWATER 密碼驗證通知";
	
	
	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String to, String messageText) {
			
	   try {
		   // 設定使用SSL連線至 Gmail smtp Server
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
       // ●須將myGmail的【安全性較低的應用程式存取權】打開
	     final String myGmail = "b024011037@cm.nsysu.edu.tw";
	     final String myGmail_password = "thefeelmustbegreat";
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		  
		   //設定信中的主旨  
		   message.setSubject(SUBJECT);
		   //設定信中的內容 
		   message.setText(messageText);

		   Transport.send(message);
		   System.out.println("傳送成功!");
     }catch (MessagingException e){
	     System.out.println("傳送失敗!");
	     e.printStackTrace();
     }
   }
	public String genRandomNum(){
		// 35是因為陣列是從0開始的,26個字母+10個數字
		final int maxNum = 36;
		int i; 				// 生成的隨機數
		int count = 0; 		// 生成的密碼的長度
		int pwd_len = 7;	// 密碼長度
		
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			
			// 生成隨機數,取絕對值,防止生成負數,
			i = Math.abs(r.nextInt(maxNum)); // 生成的數最大為36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}
	
//	// FOR TEST 
//	public static void main (String args[]){
//
//      String to = "ks369108@gmail.com";
//      
//      String subject = "UNDERWATER 密碼驗證通知";
//      
//      String ch_name = "David";			// userName
//      String passRandom = "111";		// 隨機
//      String messageText = ch_name + "您好，\n " + " 您的密碼如下: " + passRandom + "\n" +" (已經啟用)"; 
//       
//      MailService mailService = new MailService();
//      mailService.sendMail(to, subject, messageText);
//
//   }


}
