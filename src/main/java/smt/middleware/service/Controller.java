package smt.middleware.service;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;

import smt.middleware.core.DataSourceParse;

/**
 * 数据库适配服务接口
 *
 */
public class Controller {
	private static final String XML_FORMAT_ERROR = "formatErr";
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
		String result = executeSql(sqlFileName, parametersMap,
				StringUtils.equalsIgnoreCase("json", responseType));
		
		return result;
	}
	
	/**
	 * 执行sql语句
	 * @param sqlFileName msd模板文件
	 * @param parametersMap 
	 * @param isJsonFormat true返回json格式数据
	 * @return
	 */
	private String executeSql(String sqlFileName, Map<String, String> parametersMap, boolean isJsonFormat) {
		return "";
	}
}
