package smt.middleware.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import smt.middleware.config.Config;
import util.Environment;
import util.Global;
/**
 * 数据库连接处理类,负责开启及关闭数据库连接
 *
 */
public class DBConnection {
	private static final Logger log = Logger.getLogger(DBConnection.class);
	
	public static DBConnection dbConnection = null;
	private BasicDataSource mDatasource;
	private DBConnection(){
		initConnection();
	}
	
	public synchronized static DBConnection getDBConnection(){
		if(dbConnection == null)
			dbConnection = new DBConnection();
		return dbConnection;
	}
	
	private void initConnection(){

		log.info("start connection data........");
        // 获取从数据库连接   
    	Environment environment = Environment.getInstance();
    	mDatasource = new BasicDataSource();
    	String post = environment.getDBPort();
    	if(environment.getDBType().equals(Global.ORACLE_TYPE)){
    		mDatasource.setDriverClassName(Global.ORACLE_DRIVER);
    		if(environment.getDBPort() == null || environment.getDBPort().equals("")){
    			post = "1521";
        	}
    		mDatasource.setUrl(Global.ORACLE_URL + environment.getDBAddress() +":"+ post +":"+ environment.getDBName());
    	}else if(environment.getDBType().equals(Global.MYSQL_TYPE)){
    		mDatasource.setDriverClassName(Global.MYSQL_DRIVER);
    		if(environment.getDBPort() == null || environment.getDBPort().equals("")){
    			post = "3306";
        	}
    		mDatasource.setUrl(Global.MYSQL_URL + environment.getDBAddress() +":"+ post +"/"+ environment.getDBName());
    	}else if(environment.getDBType().equals(Global.MSSQL_TYPE)){
    		mDatasource.setDriverClassName(Global.MSSQL_DRIVER);
    		if(environment.getDBPort() == null || environment.getDBPort().equals("")){
    			post = "1433";
        	}
    		mDatasource.setUrl(Global.MSSQL_URL + environment.getDBAddress() +":"+ post +";DatabaseName="+ environment.getDBName());
    	}    	
    	mDatasource.setUsername(environment.getDBUserName());
    	mDatasource.setPassword(environment.getDBPassword());
    	mDatasource.setMaxActive(Config.getInt("jdbcMaxActive"));
    	mDatasource.setMaxIdle(Config.getInt("jdbcMaxIdle"));
    	mDatasource.setMaxWait(Config.getInt("jdbcMaxWait"));
    	log.info("datasource init finish........");
    	
	}
	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException{
		return mDatasource.getConnection(); 
	}
	/**
	 * 释放数据库资源
	 * @param conn
	 * @param statement
	 * @param rs
	 */
	public static void close(Connection conn,Statement statement,ResultSet rs){
		closeResult(rs);
		closeStatement(statement);
		closeConnection(conn);
	}
	/**
	 * 关闭数据库连接
	 * @param conn
	 */
	public static void closeConnection(Connection conn){
		try{
			if(conn != null){
				conn.close();
			}
		}catch(SQLException ex){
			log.error("close data connection error", ex);
		}		
	}
	
	/**
	 * 关闭Statement
	 * @param statement
	 */
	public static void closeStatement(Statement statement){
		try{
			if(statement != null){
				statement.close();
			}
		}catch(SQLException ex){
			log.error("close data preparedStatement error", ex);
		}
	}
	/**
	 * 关闭resultSet对象
	 * @param rs
	 */
	public static void closeResult(ResultSet rs){
		try{
			if(rs != null){
				rs.close();
			}
		}catch(SQLException ex){
			log.error("close data resultSet error", ex);
		}
	}
}
