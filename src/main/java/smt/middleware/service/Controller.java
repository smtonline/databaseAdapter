package smt.middleware.service;

import java.io.File;
import java.sql.SQLException;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import smt.middleware.core.DataSourceParse;
import smt.middleware.core.RequestParse;
import smt.middleware.database.AbstractDBOperator;
import smt.middleware.database.DBFactory;
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
	private static final String SQL_EXECUTE_ERROR = "sqlExecuteErr";
	private static final String OTHER_ERROR = "otherErr";
	private Environment environment = Environment.getInstance();
	
	/**
	 * 客户端请求服务接口
	 */
	public String dataBus(String xml) {
		
		if (StringUtils.isEmpty(xml)) {
			return getErroXml(XML_FORMAT_ERROR, new Exception("xml文本为空"));
		}
		DataSourceParse dsParse;
		try {
			dsParse = new DataSourceParse(xml);
		} catch (DocumentException e) {
			log.error("busEntry服务接口异常:xml格式错误", e);
			return getErroXml(XML_FORMAT_ERROR, e);
		}
		String responseType = dsParse.getResponseType();
		String sqlFileName = dsParse.getSqlFileName();
		Map<String, String> parametersMap = dsParse.getParametersMap();
		String result = null;
		try {
			result = executeSql(sqlFileName, parametersMap,
					StringUtils.equalsIgnoreCase("json", responseType));
		} catch (DocumentException e) {
			log.error("dataBus服务接口异常:xml或msd格式错误", e);
			return getErroXml(MSD_FORMAT_ERROR, e);
		} catch (SQLException e) {
			log.error("dataBus服务接口异常:sql语句执行错误", e);
			return getErroXml(SQL_EXECUTE_ERROR, e);
		} catch (Exception e) {
			log.error("dataBus服务接口异常:服务异常", e);
			return getErroXml(OTHER_ERROR, e);
		}
		log.debug("dataBus result:" + result);
		return result;
	}

	
	/**
	 * request请求
	 * @param xml
	 * @return
	 */
	public String busEntry(String xml) {
		if (StringUtils.isEmpty(xml)) {
			return getErroXml(XML_FORMAT_ERROR, new Exception("xml文件为空"));
		}
		String result = null;
		RequestParse request;
		try {
			request = new RequestParse(xml);
		} catch (DocumentException e) {
			log.error("busEntry服务接口异常:xml文件格式错误", e);
			return getErroXml(XML_FORMAT_ERROR, e);
		}
		try {
			if (StringUtils.equalsIgnoreCase("sql", request.getRef())) {
				result = dmtSql(request.getSqlFile(), request.getParametersMap());
			}
		} catch (DocumentException e) {
			log.error("busEntry服务接口异常:msd文件格式错误", e);
			return getErroXml(MSD_FORMAT_ERROR, e);
		} catch (SQLException e) {
			log.error("busEntry服务接口异常:sql语句执行错误", e);
			return getErroXml(SQL_EXECUTE_ERROR, e);
		} catch (Exception e) {
			log.error("busEntry服务接口异常:服务异常", e);
			return getErroXml(OTHER_ERROR, e);
		}
		log.debug("dataBus result:" + result);
		return result;
	}
	
	
	/**
	 * 执行sql语句
	 * @param msdFileName msd模板文件
	 * @param parametersMap 
	 * @param isJsonFormat true返回json格式数据
	 * @return
	 * @throws DocumentException 
	 * @throws SQLException 
	 */
	private String executeSql(String msdFileName, Map<String, String> parametersMap, boolean isJsonFormat) throws DocumentException, SQLException {
		String dsFolderPath = environment.getDatasourceFolderPath();
		File msdFile = new File(dsFolderPath, msdFileName);
		Element rootElement = DomUtils.getDocmentByFile(msdFile.getAbsolutePath()).getRootElement();
		//获取数据库脚本
		Element sqlElement = (Element)rootElement.selectSingleNode("//system/sql");
		String sql = sqlElement.getText();
		AbstractDBOperator dbConnect = DBFactory.CreatConnect(Environment.getInstance().getDBType());
		return dbConnect.getQuery(sql, parametersMap, isJsonFormat);
	}
	/**
	 * 执行dml语句
	 * @param msdFileName
	 * @param param
	 * @return 返回值为影响条数
	 * @throws SQLException
	 * @throws DocumentException
	 */
	private String dmtSql(String msdFileName, Map<String, String> param) throws SQLException, DocumentException
    {
		String dsFolderPath = environment.getDatasourceFolderPath();
		File msdFile = new File(dsFolderPath, msdFileName);
		Element rootElement = DomUtils.getDocmentByFile(msdFile.getAbsolutePath()).getRootElement();
		//获取数据库脚本
		Element sqlElement = (Element)rootElement.selectSingleNode("//system/sql");
		String sql = sqlElement.getText();
		AbstractDBOperator dbConnect = DBFactory.CreatConnect(Environment.getInstance().getDBType());
		int result = dbConnect.dmlSql(sql, param);
		return String.valueOf(result);
    }
	
	/**
	 * 自定义异常信息
	 * @param msgCode
	 * @param e
	 * @return
	 */
	private String getErroXml(String msgCode, Exception e) {
		StringBuffer errStringBuf = new StringBuffer();
		errStringBuf.append("<erroCode>");
		errStringBuf.append(msgCode);
		errStringBuf.append("</erroCode>");
		errStringBuf.append("<errMsg>");
		errStringBuf.append(e.getMessage());
		errStringBuf.append("</errMsg>");
		return errStringBuf.toString();
	}
}
