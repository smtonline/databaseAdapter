package smt.middleware.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import smt.middleware.config.Config;
import util.Environment;
import util.Global;

public class DBConnection {
	
	private static final Logger log = Logger.getLogger(DBConnection.class);
	
	public static DBConnection dbConnection = null;
	private Connection connection = null;
	private BasicDataSource mDatasource;
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
    	log.info("datasource init finish........");
    	
	}
	
	public Connection getConnection(){
		try{
    		return connection = mDatasource.getConnection(); 
    	}catch(SQLException ex){
    		log.error("data connection error:"+ ex);
    	} 
		return null;
	}
	
	public static void close(Connection conn,PreparedStatement pre,ResultSet rs){
		close(conn);
		close(pre);
		close(rs);
	}
	
	public static void close(Connection conn){
		try{
			if(conn != null){
				conn.close();
			}
		}catch(SQLException ex){
			log.error("close data connection error", ex);
		}		
	}
	
	public static void close(PreparedStatement pre){
		try{
			if(pre != null){
				pre.close();
			}
		}catch(SQLException ex){
			log.error("close data preparedStatement error", ex);
		}
	}
	
	public static void close(ResultSet rs){
		try{
			if(rs != null){
				rs.close();
			}
		}catch(SQLException ex){
			log.error("close data resultSet error", ex);
		}
	}
}
