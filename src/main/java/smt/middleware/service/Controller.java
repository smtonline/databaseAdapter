package smt.middleware.service;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import smt.middleware.core.DataSourceParse;
import util.DomUtils;
import util.Environment;

/**
 * 数据库适配服务接口
 *
 */
public class Controller {
	private static final Logger log = Logger.getLogger(Controller.class);
	private static final String XML_FORMAT_ERROR = "requestXmlFormatErr";
	private static final String MSD_FORMAT_ERROR = "msdFormatErr";
	private Environment environment = Environment.getInstance();
	/**
	 * 客户端请求服务接口
	 */
	public String dataBus(String xml) {
		if (StringUtils.isEmpty(xml)) {
			return XML_FORMAT_ERROR;
		}
		DataSourceParse dsParse;
		try {
			dsParse = new DataSourceParse(xml);
		} catch (DocumentException e) {
			return XML_FORMAT_ERROR;
		}
		String responseType = dsParse.getResponseType();
		String sqlFileName = dsParse.getSqlFileName();
		Map<String, String> parametersMap = dsParse.getParametersMap();
		String result = null;
		try {
			result = executeSql(sqlFileName, parametersMap,
					StringUtils.equalsIgnoreCase("json", responseType));
		} catch (DocumentException e) {
			log.debug("");
			return 
		}
		return result;
	}
	
	/**
	 * 执行sql语句
	 * @param msdFileName msd模板文件
	 * @param parametersMap 
	 * @param isJsonFormat true返回json格式数据
	 * @return
	 * @throws DocumentException 
	 */
	private String executeSql(String msdFileName, Map<String, String> parametersMap, boolean isJsonFormat) throws DocumentException {
		String dsFolderPath = environment.getDatasourceFolderPath();
		File msdFile = new File(dsFolderPath, msdFileName);
		Element rootElement = DomUtils.getDocmentByFile(msdFile.getAbsolutePath()).getRootElement();
		return "";
	}
	
}
