package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 
 * @author yanni
 * @time   2017年5月28日下午7:23:41
 */
public class MyUtils
{
	/**
	 * 生成32位长UUID
	 * @return String
	 */
	public static String UUID()
	{
		return UUID.randomUUID().toString().replace("-","");
	}

	/**
	 * 发送指定的邮件
	 * @param mail
	 * @param activitionCode
	 */
	public static void sendMail(final Mail mail ,String activitionCode)
	{
		//加载配置文件
		Properties prop=new Properties();
		try
		{
			prop.load(MyUtils.class
					.getResourceAsStream("/email_params.properties"));
		}
		catch(FileNotFoundException e)
		{
			throw new RuntimeException(e);
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}

		String host=prop.getProperty("host");//服务器主机名
		String username=prop.getProperty("username");//登录名
		String password=prop.getProperty("password");//登录密码		
		String from=prop.getProperty("from");//来自	
		String to=mail.getToAddress();//发送给
		String subject=prop.getProperty("subject");//主题
		String content=MessageFormat.format(prop.getProperty("content"),
				activitionCode);//内容
		prop.setProperty("mail.host",host);// 指定主机
		prop.setProperty("mail.smtp.auth","true");// 指定验证为true

		// 创建验证器
		Authenticator auth=new Authenticator()
		{
			public PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(username,password);
			}
		};
		Session session=Session.getInstance(prop,auth);// 获取session对象
		//创建mail对象
		MimeMessage msg=new MimeMessage(session);// 创建邮件对象
		try
		{
			msg.setFrom(new InternetAddress(from));// 设置发件人
			msg.addRecipients(RecipientType.TO,to);// 设置收件人
			msg.setSubject(subject);// 设置主题

			// 设置抄送
			String cc=mail.getCcAddress();
			if(!cc.isEmpty())
			{
				msg.addRecipients(RecipientType.CC,cc);
			}

			// 设置暗送
			String bcc=mail.getBccAddress();
			if(!bcc.isEmpty())
			{
				msg.addRecipients(RecipientType.BCC,bcc);
			}
			MimeMultipart parts=new MimeMultipart();// 创建部件集对象
			MimeBodyPart part=new MimeBodyPart();// 创建一个部件

			part.setContent(content,"text/html;charset=utf-8");// 设置邮件具体文本内容
			parts.addBodyPart(part);// 把部件添加到部件集中

			///////////////////////////////////////////

			// 添加附件
			List<AttachBean> attachBeanList=mail.getAttachs();// 获取所有附件
			if(attachBeanList!=null)
			{
				for(AttachBean attach:attachBeanList)
				{
					MimeBodyPart attachPart=new MimeBodyPart();// 创建一个部件
					attachPart.attachFile(attach.getFile());// 设置附件文件
					attachPart.setFileName(
							MimeUtility.encodeText(attach.getFileName()));// 设置附件文件名
					String cid=attach.getCid();
					if(cid!=null)
					{
						attachPart.setContentID(cid);
					}
					parts.addBodyPart(attachPart);
				}
			}

			msg.setContent(parts);// 给邮件设置内容
			Transport.send(msg);// 发邮件
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
