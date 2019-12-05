package com.server.utils.mailUtil;

import com.server.api.common.ReporterLogger;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 邮件内容模板生产工厂类
 *
 * @date 2019年7月01日 下午3:30:04
 * @author yun
 * @Description:
 * @project mailUtil
 */
public class TemplateFactory {
	// 日志记录对象
	private static ReporterLogger LOGGET = new ReporterLogger();
	// 模板文件路径
	private static String templatePath = "/template";
	// 模板文件内容编码
	private static final String ENCODING = "utf-8";
	// 模板生成配置
	private static Configuration conf = new Configuration();
	// 邮件模板缓存池
	private static Map<String, Template> tempMap = new HashMap<String, Template>();
	static {
		// 设置模板文件路径
		conf.setClassForTemplateLoading(TemplateFactory.class, templatePath);
	}

	/**
	 * 通过模板文件名称获取指定模板
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @param name
	 *            模板文件名称
	 * @return Template 模板对象
	 * @throws IOException
	 * @Description:
	 * @return Template
	 */
	private static Template getTemplateByName(String name) throws IOException {
		if (tempMap.containsKey(name)) {
			LOGGET.INFO("the template is already exist in the map,template name :"
					+ name);
			// 缓存中有该模板直接返回
			return tempMap.get(name);
		}
		// 缓存中没有该模板时，生成新模板并放入缓存中
		Template temp = conf.getTemplate(name);
		tempMap.put(name, temp);
		LOGGET.INFO("the template is not found  in the map,template name :"
				+ name);
		return temp;
	}

	/**
	 * 根据指定模板将内容输出到控制台
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @param name
	 *            模板文件名称
	 * @param map
	 *            与模板内容转换对象
	 * @Description:
	 * @return void
	 */
	public static void outputToConsole(String name, Map<String, String> map) {
		try {
			// 通过Template可以将模板文件输出到相应的流
			Template temp = getTemplateByName(name);
			temp.setEncoding(ENCODING);
			temp.process(map, new PrintWriter(System.out));
		} catch (TemplateException e) {
			LOGGET.ExceptionToString(e);
		} catch (IOException e) {
			LOGGET.ExceptionToString(e);
		}
	}

	/**
	 * 根据指定模板将内容直接输出到文件
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @param name
	 *            模板文件名称
	 * @param map
	 *            与模板内容转换对象
	 * @param outFile
	 *            输出的文件绝对路径
	 * @Description:
	 * @return void
	 */
	public static void outputToFile(String name, Map<String, String> map,
			String outFile) {
		FileWriter out = null;
		try {
			out = new FileWriter(new File(outFile));
			Template temp = getTemplateByName(name);
			temp.setEncoding(ENCODING);
			temp.process(map, out);
		} catch (IOException e) {
			LOGGET.ExceptionToString(e);
		} catch (TemplateException e) {
			LOGGET.ExceptionToString(e);
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				LOGGET.ExceptionToString(e);
			}
		}
	}

	/**
	 *
	 * @date 2019年7月01日 下午3:30:04
	 * @author yun
	 * @param name
	 *            模板文件的名称
	 * @param map
	 *            与模板内容转换对象
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 * @Description:
	 * @return String
	 */
	public static String generateHtmlFromFtl(String name,
			Map<String, String> map) throws IOException, TemplateException {
		Writer out = new StringWriter(2048);
		Template temp = getTemplateByName(name);
		temp.setEncoding(ENCODING);
		temp.process(map, out);
		return out.toString();
	}
	public static String generateMyHtmlFromFtl(String name, Map<String, String> map) throws IOException, TemplateException {

		Writer out = new StringWriter(2048);
		Template temp = getTemplateByName(name);
		temp.setEncoding(ENCODING);
		temp.process(map, out);
		return out.toString();
	}
}
