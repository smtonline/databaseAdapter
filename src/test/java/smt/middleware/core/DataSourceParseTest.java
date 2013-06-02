package smt.middleware.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;

import junit.framework.TestCase;

public class DataSourceParseTest extends TestCase {
	private DataSourceParse dsParse;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		InputStream fis = getClass().getResourceAsStream("/ds.xml");
		byte [] b = new byte[fis.available()];
		fis.read(b);
		dsParse = new DataSourceParse(new String(b));
	}


	public void testDatasouceRequestType() throws IOException, DocumentException {
		assertEquals(dsParse.getResponseType(), "json");
	}
	
 	public void testDatasourceParameters() {
 		Map<String, String> parameterMap = dsParse.getParametersMap();
 		Map<String, String> map = new HashMap<String, String>();
 		map.put("strUserID", "{@UserInfo.UserID}");
 		map.put("strStatus", "1");
 		map.put("messageid", "");
 		assertEquals(parameterMap, map);
 	}
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
}
