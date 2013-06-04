package smt.middleware.service;

import java.io.File;
import java.net.URL;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import util.DomUtils;
import junit.framework.TestCase;

public class MsdTest extends TestCase {
	public void testSql() throws DocumentException {
		URL msdFile = getClass().getResource("/");
		
		Element msdRootElement = DomUtils.getDocmentByFile(msdFile.getPath() + "/T_MG_Test.msd").getRootElement();
		//获取sql语句
		Element sqlElement = (Element)msdRootElement.selectSingleNode("//system/sql");
		String sql = sqlElement.getText();
		assertEquals(sql, "select * from T_MG_Test");
	}
}
