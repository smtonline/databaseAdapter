package smt.middleware.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import smt.middleware.entity.DataTable;

import junit.framework.TestCase;

public class DataConntcionTest extends TestCase {
	Logger log = Logger.getLogger(DataConntcionTest.class);
	private ApplicationContext context;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		context = new ClassPathXmlApplicationContext("spring-test.xml");
	}
	
	public void testMysqlConnection() throws SQLException {
		BasicDataSource ds = (BasicDataSource)context.getBean("ds1");
		Connection c = ds.getConnection();
		assertNotNull(c);
		c.close();
		assertTrue(c.isClosed());
//		Statement statement = c.createStatement();
//		ResultSet rs = statement.executeQuery("select * from userinfo");
//		while(rs.next()) {
//			assertEquals("testname", rs.getString("name"));
//		}
//		statement.execute("insert into userinfo value('n1','p1')");
	}
	
	public void testMysqlColumnTypeName() throws SQLException {
		BasicDataSource ds = (BasicDataSource)context.getBean("ds1");
		Connection c = ds.getConnection();
		ResultSet rs = c.createStatement().executeQuery("select * from UserInfo");
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		String[] columnsName = new String[columnCount];
		String [] dataType = new String[columnCount];
		for (int index = 1; index<= columnCount; index++) {
			columnsName[index-1] = metaData.getColumnName(index); 
			dataType[index-1] = metaData.getColumnTypeName(index);
		}
		
//		assertEquals(columnsName[0], "name");
//		assertEquals(columnsName[1], "password");
//		assertEquals(dataType[0], "VARCHAR");
//		assertEquals(dataType[1], "INT");
		
	}
	
	public void testToXml() throws SQLException {
		BasicDataSource ds = (BasicDataSource)context.getBean("ds1");
		Connection c = ds.getConnection();
		ResultSet rs = c.createStatement().executeQuery("select * from UserInfo");
		String xml = new DataTable(rs).toXml();
		log.debug(xml);
	}
	
	public void testToJson() throws SQLException {
		BasicDataSource ds = (BasicDataSource)context.getBean("ds1");
		Connection c = ds.getConnection();
		ResultSet rs = c.createStatement().executeQuery("select * from UserInfo");
		String json = new DataTable(rs).toJson();
		log.debug(json);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
}
