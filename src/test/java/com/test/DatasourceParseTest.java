package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;

import smt.middleware.core.DataSourceParse;

import junit.framework.TestCase;

public class DatasourceParseTest extends TestCase{
	public void testDatasourceParse() throws IOException, DocumentException {
		InputStream is = getClass().getResourceAsStream("/datasource.xml");
		int length = is.available();
		byte [] b = new byte[length];
		is.read(b, 0, length);
		DataSourceParse d = new DataSourceParse(new String(b));
		assertEquals(d.getSqlFileName(), "T_MG_Test.msd");
		assertEquals(d.getResponseType(), "json");
		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("@name", "123");
		map.put("@password", "#123");
		assertEquals(d.getParametersMap(), map);
		
	}
}
