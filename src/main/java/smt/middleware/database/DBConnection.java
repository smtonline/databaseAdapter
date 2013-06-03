package smt.middleware.database;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DBConnection {

	public Connection getConnection() throws SQLException{
		
		// 获取上下文   
    	ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        // 获取从数据库连接   
    	BasicDataSource curr_ds = (BasicDataSource) context   
                .getBean("dataSource");
    	ResolveDataSource resolve = (ResolveDataSource) context.getBean("resolve");

    	curr_ds.setDriverClassName(resolve.getDriverClassName());
    	curr_ds.setUrl(resolve.getUrl());
    	curr_ds.setUsername(resolve.getUserName());
    	curr_ds.setPassword(resolve.getPassword());
    	curr_ds.setMaxActive(resolve.getMaxActive());
    	curr_ds.setMaxIdle(resolve.getMaxIdle());
    	curr_ds.setMaxWait(resolve.getMaxWait());
    	
        Connection conn = curr_ds.getConnection();   
        
        System.out.println(conn);
        
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
