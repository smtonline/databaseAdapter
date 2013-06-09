package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;

import smt.middleware.core.RequestParse;

import junit.framework.TestCase;

public class RequestParseTest extends TestCase {
	public void testRequestXml() throws IOException, DocumentException {
		InputStream is = getClass().getResourceAsStream("/request.xml");
		int length = is.available();
		byte [] b = new byte[length];
		is.read(b, 0, length);
		RequestParse r = new RequestParse(new String(b));
		assertEquals(r.getRef(), "sql");
		assertEquals(r.getSqlFile(), "T_MG_TestUpdate.msd");
		Map<String, String> map = new HashMap<String, String>();
		map.put("@UserID", "1234");
		map.put("@UserName", "");
		map.put("@Password", "Password");
		assertEquals(r.getParametersMap(), map);
	}
}
