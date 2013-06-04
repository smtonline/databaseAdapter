package smt.middleware.entity;

import java.util.List;

public class DataParameter {

	// / 数据库类型
	private String dataType;

	// / 数据库地址
	private String serverName;

	// / 数据库名称
	private String dataName;

	// / 数据库ID
	private String dataID;

	// / 数据库密码
	private String dataPassword;

	// / 数据库端口
	private String dataPort;

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDataID() {
		return dataID;
	}

	public void setDataID(String dataID) {
		this.dataID = dataID;
	}

	public String getDataPassword() {
		return dataPassword;
	}

	public void setDataPassword(String dataPassword) {
		this.dataPassword = dataPassword;
	}

	public String getDataPort() {
		return dataPort;
	}

	public void setDataPort(String dataPort) {
		this.dataPort = dataPort;
	}

	public class DataTypeFlag
    {
    	public static final String oracle="oracle";
        public static final String mssql="mssql";
        public static final String mysql="mysql";
    }
	
	public class DataTableAndParamter
	{
	    public List<TableInfo> tableInfo ;
	    public DataParameter dataParameter;
		public List<TableInfo> getTableInfo() {
			return tableInfo;
		}
		public void setTableInfo(List<TableInfo> tableInfo) {
			this.tableInfo = tableInfo;
		}
		public DataParameter getDataParameter() {
			return dataParameter;
		}
		public void setDataParameter(DataParameter dataParameter) {
			this.dataParameter = dataParameter;
		}
		
	}
}
