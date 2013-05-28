/*
 * Copyright (C) 2012 Shenzhen SMT online Co., Ltd.
 * 
 * 项目名称:SMT移动信息化解决方案系统
 * 创建日期:2013-1-29
 */
package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * xml文件解析工具类
 * 修改日期:2013-1-29
 * 
 * @author gus@sinomaster.com
 * @version 1.0.0
 */
public class DomUtils {
	/**
	 * 读取xml文件,初始化xml文件Document对象
	 * @param fullPath xml完整路径
	 * @return
	 * @throws DocumentException
	 */
	public static Document getDocment(String fullPath) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		return saxReader.read(fullPath);
	}
	
	/**
	 * 将document对象写入到指定文件中
	 * @param document 
	 * @param filePathStr 文件路径
	 * @throws IOException 
	 */
	public static void writeToXmlWithDocument(Document document, String filePathStr) throws IOException {
		File writeFile = new File(filePathStr);
		XMLWriter writer = new XMLWriter(new FileWriter(writeFile));
		writer.write(document);
		writer.close();
	}
}
