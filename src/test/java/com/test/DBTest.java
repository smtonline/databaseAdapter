package com.test;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import smt.middleware.database.DBConnect;
import smt.middleware.database.DBFactory;
import smt.middleware.entity.DataTable;
import junit.framework.TestCase;

public class DBTest extends TestCase{
	private final Logger log = Logger.getLogger(DBTest.class);
	public void testQuery() throws SQLException {
		DBConnect c = DBFactory.CreatConnect("mysql");
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("@name", "name");
		DataTable db= c.getQuery("select * from userinfo where name=@name", map);
		log.debug(db.toXml());
	}
}
