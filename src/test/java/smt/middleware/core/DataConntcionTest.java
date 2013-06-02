package smt.middleware.core;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class DataConntcionTest extends TestCase {
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
	
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
}
