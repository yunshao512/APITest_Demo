package com.server.utils.mailUtil;

import com.server.api.common.ReporterLogger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 邮件发送实现类
 *
 * @date 2019年7月01日 下午3:30:04
 * @author yun
 * @Description:
 * @project mailUtil
 */
public class MailSender {
	private MimeMessage mimeMsg; // MIME邮件对象
	private Session session; // 邮件会话对象
	private Properties props; // 系统属性
	private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成
	private String username;// 发件人的用户名
	private String password;// 发件人的密码
	private String nickname;// 发件人的昵称

	private static final ReporterLogger LOGGER = new ReporterLogger();

	/**
	 * 有参构造器
	 * 
	 * @param smtp
	 */
	public MailSender(String smtp) {
		setSmtpHost(smtp);
		createMimeMessage();
	}

	/**
	 * 设置邮件发送的SMTP主机
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @param hostName
	 *            SMTP 发送主机
	 * @Description:
	 * @return void
	 */
	public void setSmtpHost(String hostName) {
		if (props == null)
			props = System.getProperties();
		props.put("mail.smtp.host", hostName);
		LOGGER.INFO("set system properties success ：mail.smtp.host= " + hostName);

	}

	/**
	 * 创建邮件对象
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @return
	 * @Description:
	 * @return boolean
	 */
	public void createMimeMessage() {
		// 获得邮件会话对象
		session = Session.getDefaultInstance(props, null);
		// 创建MIME邮件对象
		mimeMsg = new MimeMessage(session);
		mp = new MimeMultipart();
		LOGGER.INFO(" create session and mimeMessage success");
	}

	/**
	 * 设置权限鉴定配置
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @param need
	 *            是否需要权限
	 * @Description:
	 * @return void
	 */
	public void setNeedAuth(boolean need) {
		if (props == null)
			props = System.getProperties();
		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
		LOGGER.INFO("set smtp auth success：mail.smtp.auth= " + need);

	}

	/**
	 * 设置发送邮件的主题
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @param subject
	 *            邮件的主题
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	public void setSubject(String subject) throws UnsupportedEncodingException,
			MessagingException {
		mimeMsg.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
		LOGGER.INFO("set mail subject success, subject= " + subject);

	}

	/**
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @param mailBody
	 *            邮件的正文内容
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	public void setBody(String mailBody) throws MessagingException {
		BodyPart bp = new MimeBodyPart();
		bp.setContent("" + mailBody, "text/html;charset=utf-8");
		mp.addBodyPart(bp);
		LOGGER.INFO("set mail body content success,mailBody= " + mailBody);
	}

	/**
	 * 添加邮件附件
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @param filePath
	 *            文件绝对路径
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	public void addFileAffix(String filePath) throws MessagingException {
		BodyPart bp = new MimeBodyPart();
		FileDataSource fileds = new FileDataSource(filePath);
		bp.setDataHandler(new DataHandler(fileds));
		bp.setFileName(fileds.getName());
		mp.addBodyPart(bp);
		LOGGER.INFO("mail add file success,filename= " + filePath);
	}

	/**
	 * 设置发件人邮箱地址
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @param sender
	 *            发件人邮箱地址
	 * @throws UnsupportedEncodingException
	 * @throws AddressException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	public void setSender(String sender) throws UnsupportedEncodingException,
			AddressException, MessagingException {
		nickname = MimeUtility.encodeText(nickname, "utf-8", "B");
		mimeMsg.setFrom(new InternetAddress(nickname + " <" + sender + ">"));
		LOGGER.INFO(" set mail sender and nickname success , sender= " + sender
				+ ",nickname=" + nickname);
	}

	/**
	 * 设置收件人邮箱地址
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @param receiver
	 *            收件人邮箱地址
	 * @throws AddressException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	public void setReceiver(String receiver) throws AddressException,
			MessagingException {
		mimeMsg.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(receiver));
		LOGGER.INFO("set mail receiver success,receiver = " + receiver);
	}

	/**
	 * 设置抄送人的邮箱地址
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @param copyto
	 *            抄送人邮箱地址
	 * @throws AddressException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	public void setCopyTo(String copyto) throws AddressException,
			MessagingException {
		mimeMsg.setRecipients(Message.RecipientType.CC,
				InternetAddress.parse(copyto));
		LOGGER.INFO("set mail copyto receiver success,copyto = " + copyto);
	}

	/**
	 * 设置发件人用户名密码进行发送邮件操作
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	public void sendout() throws MessagingException {
		mimeMsg.setContent(mp);
		mimeMsg.saveChanges();
		Session mailSession = Session.getInstance(props, null);
		Transport transport = mailSession.getTransport("smtp");
		transport.connect("smtp.exmail.qq.com",587, username,
				password);
		transport.sendMessage(mimeMsg,
				mimeMsg.getRecipients(Message.RecipientType.TO));
		transport.close();
		LOGGER.INFO(" send mail success");
	}

	/**
	 * 注入发件人用户名 ，密码 ，昵称
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @param username
	 *            发件人邮箱登录用户名
	 * @param password
	 *            发件人邮箱密码
	 * @param nickname
	 *            发件人显示的昵称
	 * @Description:
	 * @return void
	 */
	public void setNamePass(String username, String password, String nickname) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;

	}

}
