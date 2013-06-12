package util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

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
	public static Document getDocmentByFile(String fullPath) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		return saxReader.read(fullPath);
	}
	
	/**
	 * 通过xml字符串,初始化Document对象
	 * @param documentXml
	 * @return
	 * @throws DocumentException
	 */
	public static Document getDocumentByXml(String documentXml) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		InputStream saxInputStream = new ByteArrayInputStream(documentXml.getBytes());
		return saxReader.read(saxInputStream);
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
