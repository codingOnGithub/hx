package com.hotent.platform.mail;

import java.io.File;
import java.security.Security;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

import com.hotent.core.util.CertUtil;
import com.sun.mail.pop3.POP3Folder;

public class PopMailTest {
	public static void main(String[] args) {
		OutMailUserSeting o=new OutMailUserSeting();
		o.setMailAddress("1121613304@qq.com");
		o.setMailPass("1111");
		o.setPopHost("pop.qq.com");
		o.setPopPort("110");
		connect(o);
	}
	public  static void connect(OutMailUserSeting outMailUserSeting){
		Properties props = new Properties();

		props.setProperty("mail.pop3.socketFactory.fallback", "false");
		System.out.println(outMailUserSeting.getPopPort());
		props.setProperty("mail.pop3.port", outMailUserSeting.getPopPort());
		props.setProperty("mail.pop3.socketFactory.port", outMailUserSeting.getPopPort());
		File cert = null;

		cert = CertUtil.get(outMailUserSeting.getPopHost(), Integer
				.parseInt(outMailUserSeting.getPopPort()));
		
		if (cert != null) {
			Security
					.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
			System.setProperty("javax.net.ssl.trustStore", cert
					.getAbsolutePath());
			props.put("javax.net.ssl.trustStore", cert.getAbsolutePath());// 证书
		} else {
			props.put("mail.smtp.starttls.enable", "true");
		}
		final String address=outMailUserSeting.getMailAddress();
		final String password=outMailUserSeting.getMailPass();
		Session session = Session.getInstance(props,  new Authenticator() {
		
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(address,password );
			}
		});
		// 请将红色部分对应替换成你的邮箱帐号和密码
					URLName urln = new URLName("pop3", outMailUserSeting.getPopHost(),
							Integer.parseInt(outMailUserSeting.getPopPort()), null,
							outMailUserSeting.getMailAddress(), outMailUserSeting
									.getMailPass());

					Store store = null;
//					POP3Folder inbox = null;

//					OutMailFolder fectchFolder = outMailFolderService
//							.get(FOLDER_ID_RECEIVE);// 要移到收件箱

					try {
						store = session.getStore(urln);
						store.connect();
//						inbox = (POP3Folder) store.getFolder("INBOX");// 主文件夹
//						inbox.open(Folder.READ_ONLY);// 只读打开
//						FetchProfile profile = new FetchProfile();// 感兴趣的信息
//						// profile.add(FetchProfile.Item.ENVELOPE);
//						profile.add(UIDFolder.FetchProfileItem.UID);// 邮件标识id
//						Message[] messages = inbox.getMessages();
//						inbox.fetch(messages, profile);
//						// 邮箱中已下载的邮件uid
//
//						Map uidMail = outMailService.getUidByUserId(ContextUtil
//								.getCurrentUserId());

						// 邮件列表

//						int count = messages.length;
//						logger.debug("mail counts :	" + count);
//
//						for (int i = 0; i < count; i++) {
//							try {
//								if (uidMail.get(inbox.getUID(messages[i])) == null) {// 判断是否已下载该uid
//									try {
//										outMail.setFileIds(f_id);
//										outMail.setFileNames(f_name);
//										outMailService.save(outMail);
//										logger.debug("接收邮件成功:	"
//												+ (messages[i].getSubject()));
//									} catch (IOException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									} finally {
//										System.gc();
//									}
//
//								}
//							} catch (MessagingException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
//
//						}

					} catch (NoSuchProviderException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {

						try {
//							inbox.close(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							store.close();
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}
//
//				setJsonString("{success:true,msg:'收取邮件完成！'}");
//				logger.debug("fectch end...");
//				return SUCCESS;

//	}
}
