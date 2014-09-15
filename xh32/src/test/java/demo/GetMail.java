package demo;

import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.security.Security;  
import java.util.Date;  
import java.util.Enumeration;  
import java.util.Map;  
import java.util.Properties;  
import java.util.UUID;  
  
import javax.mail.BodyPart;  
import javax.mail.Folder;  
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.Multipart;  
import javax.mail.NoSuchProviderException;  
import javax.mail.Part;  
import javax.mail.Session;  
import javax.mail.Store;  
import javax.mail.internet.MimeMultipart;  
import javax.mail.internet.MimeUtility;  
   
    
public class GetMail {  
    private Properties props;  
    private String dir = "D:" + File.separator +"temp" + File.separator;  
    boolean ssl;  
      
    public GetMail(){  
        props = new Properties();  
        this.setProps();  
          
    }  
      
   
   public static void main(String args[]) throws MessagingException,     
           IOException {     
         
          
	   GetMail mail = new GetMail();  
            mail.parseMail();  
    }    
     
    public void parseMail(){  
        try {  
            Session session = Session.getInstance(props);  
            Store store = session.getStore("pop3");     
            store.connect("yg_zhangyg","zhangyg0906");   //在此处设置用户名和密码  
            Folder folder = store.getFolder("INBOX");   
            System.out.println(folder.exists());  
            folder.open(Folder.READ_ONLY);   
              
            Message[] messages = folder.getMessages();  
              
            for(Message message:messages){  
                System.out.println("主题：" + this.getSubject(message));  
                System.out.println("发送时间：" + this.getSentTime(message));  
                this.parseContent((Multipart)message.getContent());  
            }  
  
        } catch (NoSuchProviderException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (MessagingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
           
    }  
    /** 
     * 设置属性 
     */  
    private void setProps(){  
          
        if(ssl){  
            this.handleSSL(props);  
        }  
          
         props.setProperty("mail.store.protocol", "pop3");     
         props.setProperty("mail.pop3.host", "pop.126.com");    
           
        // props.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
         props.setProperty("mail.pop3.socketFactory.fallback", "false");  
         props.setProperty("mail.pop3.port", "110");  
         props.setProperty("mail.pop3.socketFactory.port", "110");  
    }  
      
      
    private String getSubject(Message message) throws MessagingException{  
        return message.getSubject();  
    }  
      
    private Date getSentTime(Message message) throws MessagingException{  
        return message.getSentDate();  
    }  
      
    /** 
     * @param props 
     */  
    private void handleSSL(Properties props){  
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());  
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";    
        props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);    
        props.setProperty("mail.pop3.socketFactory.fallback", "false");    
        props.setProperty("mail.pop3.port", "995");    
        props.setProperty("mail.pop3.socketFactory.port", "995");    
    
        props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);    
        props.setProperty("mail.imap.socketFactory.fallback", "false");    
        props.setProperty("mail.imap.port", "993");    
        props.setProperty("mail.imap.socketFactory.port", "993");   
    }  
      
    /** 
     * 处理邮件内容 
     * @param multipart 
     */  
    private void parseContent(Multipart multipart){  
          
        try {  
            for(int i = 0;i<multipart.getCount();i++){  
                BodyPart message = multipart.getBodyPart(i);  
            if((message.isMimeType("text/html"))||(message.isMimeType("text/plain"))){  
                System.out.println("邮件内容：" + message.getContent());  
            }  
            if(message.isMimeType("multipart/*")){  
                Multipart msg = (Multipart)message.getContent();  
                parseContent(msg);  
            }  
            if(message.isMimeType("application/octet-stream")){  
                String disposition = message.getDisposition();  
                if(disposition == null || disposition.equalsIgnoreCase(Part.ATTACHMENT)){  
                    saveToFile(message);  
                }  
            }  
            }  
              
        } catch (MessagingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
    }   
      
    /**  
     * 存到文件中  
     * @param message  
     */  
    private void saveToFile(BodyPart message){  
        InputStream is = null;  
        OutputStream os = null;  
        File file = new File(dir);  
        if(!file.exists()&&!file.isDirectory()){  
            file.mkdirs();  
        }  
        try {  
            String fileName = message.getFileName();  
              
            fileName = MimeUtility.decodeText(fileName);        //处理附件名乱码问题  
              
            System.out.println(fileName);  
            File output = new File(dir + fileName);  
  
            is = message.getInputStream();  
            os = new FileOutputStream(output);  
            byte[] bytes = new byte[1024];  
             int len = 0;     
                while ((len=is.read(bytes)) != -1 ) {     
                    os.write(bytes, 0, len);     
                }    
              
        } catch (MessagingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally{  
            try {  
                  
                os.flush();  
                is.close();  
                os.close();  
                  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
                  
        }  
          
    }  
}   