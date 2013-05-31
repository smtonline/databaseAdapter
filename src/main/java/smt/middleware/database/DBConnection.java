package smt.middleware.database;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DBConnection {
	
	private static String MYSQL = "ds1";   
    
    private static String SLAVEDS = "ds2";  

	public Connection getConnection() throws SQLException{
		// 获取上下文   
    	ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        // 获取从数据库连接   
    	BasicDataSource curr_ds = (BasicDataSource) context   
                .getBean(MYSQL);   

        Connection conn = curr_ds.getConnection();   
        
        return conn;
	}
	
	public static void main(String[] args){
		DBConnection conn = new DBConnection();
		try {
			conn.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
