package smt.middleware.database;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import smt.middleware.config.Config;
import util.Environment;
import util.Global;

public class DBConnection {
	
	private final Logger log = Logger.getLogger(DBConnection.class);
	
	public static DBConnection dbConnection = null;
	private Connection connection = null;
	
	private DBConnection(){
		initConnection();
	}
	
	public synchronized static DBConnection getDBConnection(){
		if(dbConnection == null)
			dbConnection = new DBConnection();
		return dbConnection;
	}
	
	public void initConnection(){

		log.info("start connection data........");
        // 获取从数据库连接   
    	BasicDataSource curr_ds = new BasicDataSource();
    	Environment environment = Environment.getInstance();
    	
    	String post = environment.getDBPort();
    	if(environment.getDBType().equals(Global.ORACLE_TYPE)){
    		curr_ds.setDriverClassName(Global.ORACLE_DRIVER);
    		if(environment.getDBPort() == null || environment.getDBPort().equals("")){
    			post = "1521";
        	}
        	curr_ds.setUrl(Global.ORACLE_URL + environment.getDBAddress() +":"+ post +":"+ environment.getDBName());
    	}else if(environment.getDBType().equals(Global.MYSQL_TYPE)){
    		curr_ds.setDriverClassName(Global.MYSQL_DRIVER);
    		if(environment.getDBPort() == null || environment.getDBPort().equals("")){
    			post = "3306";
        	}
        	curr_ds.setUrl(Global.MYSQL_URL + environment.getDBAddress() +":"+ post +"/"+ environment.getDBName());
    	}else if(environment.getDBType().equals(Global.MSSQL_TYPE)){
    		curr_ds.setDriverClassName(Global.MSSQL_DRIVER);
    		if(environment.getDBPort() == null || environment.getDBPort().equals("")){
    			post = "1433";
        	}
        	curr_ds.setUrl(Global.MSSQL_URL + environment.getDBAddress() +":"+ post +";DatabaseName="+ environment.getDBName());
    	}    	
    	curr_ds.setUsername(environment.getDBUserName());
    	curr_ds.setPassword(environment.getDBPassword());
    	curr_ds.setMaxActive(Config.getInt("jdbcMaxActive"));
    	curr_ds.setMaxIdle(Config.getInt("jdbcMaxIdle"));
    	curr_ds.setMaxWait(Config.getInt("jdbcMaxWait"));
    	
    	try{
    		connection = curr_ds.getConnection(); 
    		log.info("data connection success........");
    	}catch(SQLException ex){
    		log.error("data connection error:"+ ex);
    	} 
	}
	
	public Connection getConnection(){
		return connection;
	}
	
}
