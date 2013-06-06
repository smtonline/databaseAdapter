package smt.middleware.service;

import java.io.File;
import java.sql.SQLException;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import smt.middleware.core.DataSourceParse;
import smt.middleware.database.DBConnect;
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
			log.debug("msc文件格式错误", e);
			return MSD_FORMAT_ERROR;
		}
		
		return result;
	}
	
//	public string DMTSql(string tableName, Dictionary<string, string> param)
//    {
//        string databaseType = string.Empty;
//        string strConn = DBBase.GetConnectString(ref databaseType);
//        DBFactory myFactory = new DBFactory();
//        DBConnect dbConnect = myFactory.CreatConnect(databaseType, strConn);
//        string msdurl = HostingEnvironment.ApplicationPhysicalPath + @"\datasource\" + tableName;
//        XmlDocument xmlDoc = new XmlDocument();
//        xmlDoc.Load(msdurl);
//        XmlNode root = xmlDoc.SelectSingleNode("system/sql");//查找 
//        string sql = root.InnerXml;
//        int result = dbConnect.DMLSql(sql.Replace("<![CDATA[", "").Replace("]]>", ""), param);
//        if (result >= 1)
//        {
//            return "1";
//        }
//        else
//        {
//            return "0";
//        }
//        
//
//    }
	
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
		//获取数据库脚本
		Element sqlElement = (Element)rootElement.selectSingleNode("//system/sql");
		String sql = sqlElement.getText();
		//TODO:执行sql语句
		String result = null;
		if (isJsonFormat) {
			
		} else {
			
		}
		return result;
	}
	
	public String test() throws SQLException {
		DBConnect connect = DBFactory.CreatConnect("oracle");
		return connect.updateBySql("update UserInfo set name='ttt' where name='name'");
	}
	
}
