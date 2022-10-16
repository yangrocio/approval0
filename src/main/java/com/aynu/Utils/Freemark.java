package com.aynu.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 使用freemark生成word
 * @author stormwy
 *
 */
public class Freemark {
	
	public  File UseFreemark(HashMap<String,String> hashMap,String filePath) throws IOException {

		Freemark freemark = new Freemark();
		freemark.configuration = new Configuration();
		freemark.configuration.setDefaultEncoding("utf-8");
//		这个配置是在windows上的一个模板文件
//		freemark.configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\DELL\\Desktop"));

//		下面这个文件配置 是在 linux服务器上的 一个 word 模板文件
//		freemark.configuration.setDirectoryForTemplateLoading(new File("/usr/"));



		freemark.configuration.setClassForTemplateLoading(this.getClass(),"/");


		freemark.setTemplateName("old.ftl");
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		freemark.setFileName(uuid+".doc");
//		freemark.setFilePath("C:\\Users\\DELL\\Desktop\\wordtemplate\\");
		freemark.setFilePath(filePath);
//		freemark.setFilePath("/");
//		freemark.setFilePath("wordtemplate\\");
//		freemark.setFilePath("/usr/word/");
		File file = freemark.createWord(hashMap);
		
		return file;
	}
	//下载生成的代码
	private File createWord(Map map){
		Template t = null;
		File outFile = new File(filePath+fileName);
		Writer out = null;
		try {
			t = configuration.getTemplate(templateName);
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
			t.process(map, out);
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outFile;
	}
	

	
	/**
	 * freemark模板配置
	 */
	private Configuration configuration;
	/**
	 * freemark模板的名字
	 */
	private String templateName;
	/**
	 * 生成文件名
	 */
	private String fileName;
	/**
	 * 生成文件路径
	 */
	private String filePath;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
}
