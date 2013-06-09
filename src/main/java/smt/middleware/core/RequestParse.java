package smt.middleware.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import util.DomUtils;

/**
 * Request 请求数据解析
 */
public class RequestParse {
	private Element rootElement;
	

    //sql文件
    private String sqlFile;
    
    // 是否选择了sql方式
    private String ref;
    
    private Map<String, String> parametersMap;
    /**
     * 请求数据解析器
     * @param xml xml格式字符串
     */ 
    public RequestParse(String xml) throws DocumentException {
    	Document rootDocument = DomUtils.getDocumentByXml(xml);
		rootElement = rootDocument.getRootElement();
		parseRefNode();
		parametersMap = getParameters();
		//解析完成后释放资源
		rootDocument.clearContent();
    }

    /**
     * 解析value属性节点
     * @param rootElement
     * @return
     */
    private Map<String, String> getParameters() {
    	Map<String, String> resultMap = new HashMap<String, String>();
    	List valueNodeList = rootElement.selectNodes("//value");
    	for (Object item : valueNodeList) {
    		Element valueElement = (Element)item;
    		String name = valueElement.attributeValue("name");
    		String value = valueElement.attributeValue("value");
    		resultMap.put(name, value);
    	}
        return resultMap;
    }
    
    /**
     * 解析ref节点
     * @return
     */
    private void parseRefNode()
    {
    	Node refNode = rootElement.selectSingleNode("/request/ref");
		Element refElement = (Element)refNode;
		ref =  refElement.getText();
		sqlFile = refElement.attributeValue("file");
    }

	public String getSqlFile() {
		return sqlFile;
	}

	public String getRef() {
		return ref;
	}

	public Map<String, String> getParametersMap() {
		return parametersMap;
	}
    
    
     
}
