package com.server.utils.mailUtil;


import com.server.api.common.GlobalConfig;
import com.server.api.common.ReporterLogger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.nio.file.Paths;

/**
 * 邮件发送配置信息加载类
 *
 * @date 2019年7月01日 下午3:30:04
 * @author yun
 * @Description:
 * @project mailUtil
 */
public class ConfigLoader {
    // 邮件发送主题
    private String subject;
    // 邮件发送SMTP主机
    private String server;
    // 发件人邮箱地址
    private String sender;
    // 发件人邮箱账号
    private String username;
    // 发件人邮箱密码
    private String password;
    // 发件人显示昵称
    private String nickname;
    // 收件人
    private String to;
    // 抄送人
    private String cc;

    private String addressee;

    private String from;

	private static final ReporterLogger LOGGER = new ReporterLogger();
	public ConfigLoader() {
		SAXReader reader = new SAXReader();
		String configFilePath = Paths.get(GlobalConfig.getRootDir(), GlobalConfig.getResourcesDirPath()+"/emailConfig.xml").toString();
		LOGGER.INFO(configFilePath);
        Document document = null;
        try {
            document = reader.read(configFilePath);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootElement = document.getRootElement();

		subject = rootElement.element("subject").getTextTrim();

		server = rootElement.element("server").getTextTrim();//Suite

		sender = rootElement.element("sender").getTextTrim();

		username = rootElement.element("username").getTextTrim();

		password = rootElement.element("password").getTextTrim();

		nickname = rootElement.element("nickname").getTextTrim();
		if (GlobalConfig.isOSLinux())
			to = rootElement.element("to").getTextTrim();
		else
			to = rootElement.element("sender").getTextTrim();

		cc = rootElement.element("cc").getTextTrim();

		addressee = rootElement.element("addressee").getTextTrim();

		from = rootElement.element("from").getTextTrim();
	}

	public String getSubject() { return subject; }

	public String getServer(){return server;}

	public String getSender(){return sender;}

	public String getUsername(){return username;}

	public String getPassword(){return password;}

	public String getNickname(){return nickname;}

	public String getTo(){return to;}

	public String getCc(){return cc;}

	public String getaddressee(){return addressee;}

	public String getFrom(){return from;}

}
