package com.hotent.platform.mail;

import java.io.File;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotent.core.util.CertUtil;

public class MailTest {
	static String sendType = "smtp";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OutMailUserSeting os=new OutMailUserSeting();
		os.setSmtpHost("smtp.sina.com");
		os.setSmtpPort("25");
		os.setMailAddress("junius8172@sina.com");
		os.setMailPass("****");
		os.setUserName("admin");
		
		
		OutMail om=new OutMail();
		
		om.setContent("outMail");
		om.setReceiverNames("james8172@foxmail.com");
		om.setTitle("zhf back2");


		
		EmailAddress emailAddress=new EmailAddress();
		emailAddress.setName(om.getReceiverNames());
		emailAddress.setAddress(om.getReceiverNames());
		List<EmailAddress> reviceList=new ArrayList<EmailAddress>();
		reviceList.add(emailAddress);
		
		
		try {
			send(om,reviceList,null,os);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	protected static void send(OutMail outMail_, List reviceList, List ccList,
			OutMailUserSeting _outMailUserSeting) throws Exception {
		Logger logger=LoggerFactory.getLogger(MailTest.class);
		logger.debug("send start...");
		// 取得通道session
		String user = _outMailUserSeting.getMailAddress();
		String pass = _outMailUserSeting.getMailPass();
		String smtpHost = _outMailUserSeting.getSmtpHost();
		String smtpPort = _outMailUserSeting.getSmtpPort();
		String smtpAuth = "true";
		//logger.debug(_outMailUserSeting);

		Properties props = new Properties();
		props.setProperty("mail.smtp.host", smtpHost);
		props.setProperty("mail.smtp.port", smtpPort);
		props.put("mail.smtp.auth", smtpAuth);

		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", smtpPort);

		File cert = null;

		cert = CertUtil.get(smtpHost, Integer.parseInt(smtpPort));

		if (cert != null) {
			logger.debug("ssl connection...");
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
			System.setProperty("javax.net.ssl.trustStore", cert
					.getAbsolutePath());
			props.put("javax.net.ssl.trustStore", cert.getAbsolutePath());// 证书
		} else {
			final String TLS_FACTORY = "javax.net.SocketFactory";
			props.setProperty("mail.smtp.socketFactory.class", TLS_FACTORY);
			
			logger.debug("plaintext connection or tls connection...");
			//props.put("mail.smtp.starttls.enable", "true");
		}

		final String username = user;
		final String password = pass;

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		logger.debug("connetion session:" + session);
		EmailAddress sender = new EmailAddress(_outMailUserSeting
				.getMailAddress(), _outMailUserSeting.getUserName()); // 发送人
		BodyPart contentPart = new MimeBodyPart();// 内容
		// 邮件正文
		contentPart.setHeader("Content-Transfer-Encoding", "base64");
		contentPart
				.setContent(outMail_.getContent(), "text/html;charset=utf-8");

		MimeMessage message = new MimeMessage(session);
		Multipart multipart = new MimeMultipart();
		message.setSubject(outMail_.getTitle(), "utf-8");

		message.setText("utf-8", "utf-8");
		message.setSentDate(outMail_.getMailDate() == null ? new Date()
				: outMail_.getMailDate());

		multipart.addBodyPart(contentPart);// 邮件正文

		// 添加发件人
		
		
		InternetAddress aa=toInternetAddress(_outMailUserSeting.getUserName(),_outMailUserSeting.getMailAddress());
		message.setFrom(aa);

		// 添加收件人
		InternetAddress address[] = getAddressByType(reviceList);
		if (address != null)
			message.addRecipients(Message.RecipientType.TO, address);
		// 添加抄送人
		if (ccList != null && ccList.size() > 0) {
			address = getAddressByType(ccList);
			if (address != null)
				message.addRecipients(Message.RecipientType.CC, address);
		}
		// 添加暗送人
		// address = this.getAddressByType(this.邮件.get暗送人());
		// if (address != null)
		// message.addRecipients(Message.RecipientType.BCC,address);

		if (sendType == null)
			sendType = "smtp";

		// session.getTransport(sendType).send(message);
		Transport transport = session.getTransport(sendType);
		transport.connect(_outMailUserSeting.getSmtpHost().toString(),
				username, password);
		transport.send(message);
		transport.close();
		logger.debug("send end");

	}
	
	public static InternetAddress toInternetAddress(String name,String address) throws Exception {
		if (name != null && !name.trim().equals("")) {
			return new InternetAddress(address, MimeUtility.encodeWord(
					name, "utf-8", "Q"));
		}
		return new InternetAddress(address);
	}
	/**
	 * 将地地址址转化为 可输送的网络地址
	 */
	protected static InternetAddress[] getAddressByType(List<EmailAddress> list)
			throws Exception {
		if (list != null) {
			InternetAddress address[] = new InternetAddress[list.size()];
			for (int i = 0; i < list.size(); i++) {

				if (list.get(i).toInternetAddress() != null) {
					address[i] = list.get(i).toInternetAddress();

				}
			}
			return address;
		}
		return null;
	}
}
