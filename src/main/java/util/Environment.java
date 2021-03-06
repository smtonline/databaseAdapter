package util;

import java.io.File;
import java.net.URL;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;

/**
 * 环境类,负责获取数据库配置和获取系统的文件夹路径
 */
public class Environment {
	private final Logger log = Logger.getLogger(Environment.class);
	private static final Environment environment = new Environment();
	private String mDatasourceFolderPath;
	// 数据库类型:mysql,oracle,mssql
	private String mDBType;
	// 数据库访问地址
	private String mDBAddress;
	// 数据库用户名
	private String mDBUserName;
	// 数据库密码
	private String mDBPassword;
	private String mDBName;
	private String mDBPort;

	private Environment() {
		findDatasourceFolderPath();
		initDatabaseConfig();
	}

	/**
	 * 获取环境对象实例
	 * 
	 * @return
	 */
	public static Environment getInstance() {
		return environment;
	}

	/**
	 * 获取数据源(msd)文件夹路径
	 * 
	 * @return
	 */
	public String getDatasourceFolderPath() {
		return mDatasourceFolderPath;
	}

	/**
	 * 数据库类型:mysql,oracle,mssql
	 * 
	 * @return
	 */
	public String getDBType() {
		return mDBType;
	}

	/**
	 * 数据库访问地址
	 * 
	 * @return
	 */
	public String getDBAddress() {
		return mDBAddress;
	}

	/**
	 * 数据库用户名
	 * 
	 * @return
	 */
	public String getDBUserName() {
		return mDBUserName;
	}

	/**
	 * 数据库密码
	 * 
	 * @return
	 */
	public String getDBPassword() {
		return mDBPassword;
	}

	/**
	 * 获取数据库名
	 * 
	 * @return
	 */
	public String getDBName() {
		return mDBName;
	}
	public String getDBPort() {
		return mDBPort;
	}

	/**
	 * web项目解压后的根目录:~/webapp/SMT_DataAdapter/
	 * 
	 * @return
	 */
	public File getProjectRootFolder() {
		URL url = getClass().getResource("/");
		String path = url.getPath();
		File classesRootPath = new File(path);
		File projectRootFolder = classesRootPath.getParentFile()
				.getParentFile();
		return projectRootFolder;
	}

	private void findDatasourceFolderPath() {
		File projectRootFolder = getProjectRootFolder();
		StringBuffer dsFolderPath = new StringBuffer();
		dsFolderPath.append(projectRootFolder.getAbsolutePath());
		dsFolderPath.append(File.separator);
		dsFolderPath.append("datasource");
		dsFolderPath.append(File.separator);
		mDatasourceFolderPath = dsFolderPath.toString();
	}

	private void initDatabaseConfig() {
		File projectRootFolder = getProjectRootFolder();
		File databaseConfigFile = new File(projectRootFolder, "Web.config");
		Element rootElement;
		try {
			Document document = DomUtils.getDocmentByFile(databaseConfigFile
					.getAbsolutePath());
			rootElement = document.getRootElement();
		} catch (DocumentException e) {
			log.error("loading Web.config err.", e);
			return;
		}
		// 获取数据库类型
		Node dbTypeNode = rootElement
				.selectSingleNode("//configuration/appSettings/add[@key='DBType']");
		Element dbTypeElement = (Element) dbTypeNode;
		mDBType = dbTypeElement.attributeValue("value");
		log.debug("Init DataBase Type:" + mDBType);

		// 获取数据连接地址
		Node dbAddressNode = rootElement
				.selectSingleNode("//configuration/appSettings/add[@key='DBAddress']");
		Element dbAddressElement = (Element) dbAddressNode;
		mDBAddress = dbAddressElement.attributeValue("value");
		log.debug("Init DataBase Type:" + mDBType);
		
		// 获取数据库名
		Node dbNameNode = rootElement
				.selectSingleNode("//configuration/appSettings/add[@key='DBName']");
		Element dbNameElement = (Element) dbNameNode;
		mDBName = dbNameElement.attributeValue("value");
		log.debug("Init DataBase name:" + mDBName);

		// 获取数据库名
		Node dbPortNode = rootElement
				.selectSingleNode("//configuration/appSettings/add[@key='DBPost']");
		Element dbPortElement = (Element) dbPortNode;
		mDBPort = dbPortElement.attributeValue("value");
		log.debug("Init DataBase port:" + mDBPort);

		// 获取数据库用户名
		Node dbUserNameNode = rootElement
				.selectSingleNode("//configuration/appSettings/add[@key='DBUserName']");
		Element dbUserNameElement = (Element) dbUserNameNode;
		mDBUserName = dbUserNameElement.attributeValue("value");
		log.debug("Init DataBase username:" + mDBUserName);
		
		// 获取数据库用户密码
		Node dbPasswordNode = rootElement
				.selectSingleNode("//configuration/appSettings/add[@key='DBPassword']");
		Element dbPasswordElement = (Element) dbPasswordNode;
		mDBPassword = dbPasswordElement.attributeValue("value");
		log.debug("Init DataBase password:" + mDBPassword);
		
	}

}
