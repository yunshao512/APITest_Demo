package com.server.utils.mailUtil;


import com.server.api.common.ReporterLogger;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

/**
 * 邮件发送工具类
 * 
 * @date 2019年7月01日 下午3:30:04
 * @author yun
 * @Description:
 * @project mailUtil
 */
public class MailUtil {

	private static final ReporterLogger LOGGER = new ReporterLogger();
	/**
	 * 根据模板名称查找模板，加载模板内容后发送邮件,根据当前suite发送不同的邮件
	 *
     * @date 2019年7月01日 下午3:30:04
     * @author yun
	 * @param receiver
	 *            收件人地址
	 * @param subject
	 *            邮件主题
	 * @param map
	 *            邮件内容与模板内容转换对象
	 * @param templateName
	 *            模板文件名称
	 * @throws IOException
	 * @throws TemplateException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	public static void sendMailByTemplate(String receiver, String subject,
			Map<String, String> map, String templateName) throws IOException,
            TemplateException, MessagingException {
		String maiBody = "";
        ConfigLoader con = new ConfigLoader();
		String server = con.getServer();
		String sender = con.getSender();
		String username = con.getUsername();
		String password = con.getPassword();
		String nickname = con.getNickname();
		MailSender mail = new MailSender(server);
		mail.setNeedAuth(true);
		mail.setNamePass(username, password, nickname);
		maiBody = TemplateFactory.generateHtmlFromFtl(templateName, map);
		mail.setSubject(subject);
		mail.setBody(maiBody);
		mail.setReceiver(receiver);
		mail.setSender(sender);
		mail.sendout();
	}

	/**
	 * 根据模板名称查找模板，加载模板内容后发送邮件
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @param receiver
	 *            收件人地址
	 * @param subject
	 *            邮件主题
	 * @param map
	 *            邮件内容与模板内容转换对象
	 * @param templateName
	 *            模板文件名称
	 * @throws IOException
	 * @throws TemplateException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	public static void sendMailAndFileByTemplate(String receiver,
			String subject, String filePath, Map<String, String> map,
			String templateName) throws IOException, TemplateException,
			MessagingException {
		String maiBody = "";
        ConfigLoader con = new ConfigLoader();
        String server = con.getServer();
        String sender = con.getSender();
        String username = con.getUsername();
        String password = con.getPassword();
        String nickname = con.getNickname();
		MailSender mail = new MailSender(server);
		mail.setNeedAuth(true);
		mail.setNamePass(username, password, nickname);
		maiBody = TemplateFactory.generateHtmlFromFtl(templateName, map);
		mail.setSubject(subject);
		mail.addFileAffix(filePath);
		mail.setBody(maiBody);
		mail.setReceiver(receiver);
		mail.setSender(sender);
		mail.sendout();
	}
	/**
	 * (新)根据模板名称查找模板，加载模板内容后发送邮件,读取XML发送信息
	 *
	 * @Date:2019年7月01日 上午11:02:58
	 * @author yun
	 * @param templateName
	 *            模板文件名称
	 * @throws IOException
	 * @throws TemplateException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	public static void sendMyMailAndFileByTemplate(String filePath,Map<String, String> map,
												 String templateName) throws IOException, TemplateException,
			MessagingException {
		String maiBody = "";
        ConfigLoader con = new ConfigLoader();
        String server = con.getServer();
        String sender = con.getSender();
        String username = con.getUsername();
        String password = con.getPassword();
        String nickname = con.getNickname();
        String subject = con.getSubject();
		String receiver = con.getTo();
		MailSender mail = new MailSender(server);
		mail.setNeedAuth(true);
		mail.setNamePass(username, password, nickname);
		maiBody = TemplateFactory.generateMyHtmlFromFtl(templateName,map);
		mail.setSubject(subject);
		mail.addFileAffix(filePath);
		mail.setBody(maiBody);
		mail.setReceiver(receiver);
		mail.setSender(sender);
		mail.sendout();
	}

	/**
	 * 普通方式发送邮件内容
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @param receiver
	 *            收件人地址
	 * @param subject
	 *            邮件主题
	 * @param maiBody
	 *            邮件正文
	 * @throws IOException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	public static void sendMail(String receiver, String subject, String maiBody)
			throws IOException, MessagingException {
        ConfigLoader con = new ConfigLoader();
        String server = con.getServer();
        String sender = con.getSender();
        String username = con.getUsername();
        String password = con.getPassword();
        String nickname = con.getNickname();
		MailSender mail = new MailSender(server);
		mail.setNeedAuth(true);
		mail.setNamePass(username, password, nickname);
		mail.setSubject(subject);
		mail.setBody(maiBody);
		mail.setReceiver(receiver);
		mail.setSender(sender);
		mail.sendout();
	}

	/**
	 * 普通方式发送邮件内容，并且附带文件附件
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @param receiver
	 *            收件人地址
	 * @param subject
	 *            邮件主题
	 * @param filePath
	 *            文件的绝对路径
	 * @param maiBody
	 *            邮件正文
	 * 
	 * @throws IOException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	public static void sendMailAndFile(String receiver, String subject,
			String filePath, String maiBody) throws IOException,
			MessagingException {
        ConfigLoader con = new ConfigLoader();
        String server = con.getServer();
        String sender = con.getSender();
        String username = con.getUsername();
        String password = con.getPassword();
        String nickname = con.getNickname();
		MailSender mail = new MailSender(server);
		mail.setNeedAuth(true);
		mail.setNamePass(username, password, nickname);
		mail.setSubject(subject);
		mail.setBody(maiBody);
		mail.addFileAffix(filePath);
		mail.setReceiver(receiver);
		mail.setSender(sender);
		mail.sendout();
	}

}
