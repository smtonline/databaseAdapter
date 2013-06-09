package smt.middleware.core;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import util.DomUtils;

public class DataSourceParse {
	private Element rootElement;
	private String responseType;
	private String sqlFileName;
	private Map<String, String> parametersMap;
	public DataSourceParse(String xml) throws DocumentException {
		Document rootDocument = DomUtils.getDocumentByXml(xml);
		rootElement = rootDocument.getRootElement();
		responseType = parseResponseType();
		sqlFileName = parseSqlFileName();
		parametersMap = parseParameters();
		//解析完成后释放资源
		rootDocument.clearContent();
	}
	
	
	/**
	 * 获取服务返回的数据格式类型
	 * @return xml或json格式数据类型
	 */
	public String getResponseType() {
		return responseType;
	}
	
	/**
	 * 获取数据源配置文件中设置sql配置文件名
	 * @return sql文件名(msd文件名)
	 */
	public String getSqlFileName() {
		return sqlFileName;
	}
	/**
	 * 获取数据源parameter节点所有name和value
	 * @return
	 */
	public Map<String, String> getParametersMap() {
		return parametersMap;
	}


	private String parseResponseType() {
		Node datasourceNode = rootElement.selectSingleNode("//datasource");
		Element datasourceElement = (Element)datasourceNode;
		String responseType =  datasourceElement.attributeValue("responsetype");
		return responseType != null ? responseType : "";		
	}
	private String parseSqlFileName() {
		Node sqlFileNode = rootElement.selectSingleNode("//datasource/function");
		Element sqlFileElement = (Element)sqlFileNode;
		String sqlFileName = sqlFileElement.attributeValue("file");
		return sqlFileName != null ? sqlFileName : "";
	}
	
	private Map<String, String> parseParameters() {
		Map<String, String> parametersMap = new LinkedHashMap<String, String>();
		List nodeLists = rootElement.selectNodes("//datasource/parameters/parameter");
		Iterator iter = nodeLists.iterator();
		for (Iterator iterator = nodeLists.iterator(); iterator.hasNext();) {
			Element parameterElement = (Element) iterator.next();
			String parameterName = parameterElement.attributeValue("name");
			String parameterValue = parameterElement.attributeValue("value");
			parametersMap.put(parameterName, parameterValue);
		}
		return parametersMap;
	}
	
}
