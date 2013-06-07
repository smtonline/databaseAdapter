package smt.middleware.database;

import java.sql.SQLException;
import org.apache.log4j.Logger;
import util.Global;
/**
 * 数据库工厂
 */
public class DBFactory {
	private final Logger log = Logger.getLogger(DBFactory.class);

	
	public synchronized static DBConnect CreatConnect(String dbType) throws SQLException{
		if(dbType.equals(Global.ORACLE_TYPE)){
			 return new DBOracle(DBConnection.getDBConnection().getConnection());
    	}else if(dbType.equals(Global.MYSQL_TYPE)){
    		 return new DBMysql(DBConnection.getDBConnection().getConnection());
    	}else if(dbType.equals(Global.MSSQL_TYPE)){
    		 return new DBOracle(DBConnection.getDBConnection().getConnection());
    	}
		return null;    	
	}
	
	
	
	
}
