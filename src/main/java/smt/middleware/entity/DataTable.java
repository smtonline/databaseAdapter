package smt.middleware.entity;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DataTable {
	private static final Logger log = Logger.getLogger(DataTable.class);
//	private String[] columnsName;
//	private String[] dataType;
	private ResultSet resultSet;
	private ResultSetMetaData metaData;
	public DataTable(ResultSet rs) {
		try {
			resultSet = rs;
			metaData = rs.getMetaData();
		} catch (SQLException e) {
			log.error("获取此 ResultSet 对象的列的编号、类型和属性失败", e);
		}
	}
	/**
	 * 查询到的数据以xml格式返回给客户端
	 * @return
	 * @throws SQLException 
	 */
	public String toXml() throws SQLException {
		StringBuffer xmlBuffer = new StringBuffer();
		int rows = resultSet.last() ? resultSet.getRow(): 0;
		int columnCount = metaData.getColumnCount();
		
		xmlBuffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		xmlBuffer.append("<data name=\"TableName\">\n");
		
		xmlBuffer.append("<columns count=\"" + rows + "\">\n");
		for (int i = 1; i <= columnCount; i++)
        {
            String name = metaData.getColumnName(i);
            String dataType = metaData.getColumnTypeName(i);
			xmlBuffer.append("<column name=\"" + name + "\" DataType=\""
					+ dataType + "\" value=\"\" />\n");
        }
		xmlBuffer.append("</columns>\n");
        if (resultSet.first() && rows != 0)
        {
        	
			xmlBuffer.append("<rows count=\"" + rows + "\">\n");
            for (int i = 0; i < rows; i++)
            {
            	xmlBuffer.append("<row>\n");
                for (int j = 1; j <= columnCount; j++)
                {
                	String columnName = metaData.getColumnName(j);
                	String columnValue = resultSet.getString(columnName);
                	xmlBuffer.append("<" + columnName + " control=\"label\">" + columnValue + "</" +columnName+ ">\n");
                }
                xmlBuffer.append("</row>\n");
                
                if (!resultSet.next()) {
                	break;
                }
            }
            xmlBuffer.append("</rows>\n");
        }
        else
        {
        	xmlBuffer.append("<rows count=\"0\">\n");
        	xmlBuffer.append("</rows>\n");
        }
        xmlBuffer.append("</data>");
		return xmlBuffer.toString();
	}
	/**
	 * 查询到的数据以json格式返回给客户端
	 * @return
	 * @throws SQLException 
	 */
	public String toJson() throws SQLException {
		StringBuilder jsonString = new StringBuilder();
		int rows = resultSet.last() ? resultSet.getRow(): 0;
		int columnCount = metaData.getColumnCount();
        jsonString.append("[");
		if (resultSet.first()) {
			for (int i = 1; i <= rows; i++) {
				jsonString.append("[");
				for (int j = 1; j <= columnCount; j++) {
					String columnName = metaData.getColumnName(j);
					String columnValue = resultSet.getString(columnName);
					jsonString.append("{\"" + columnName + "\":\""
							+ columnValue + "\"}");
					if (j != columnCount) {
						jsonString.append(",");
					}
				}
				jsonString.append("]");
				if (!resultSet.next()) {
					break;
				}
				if (i != rows) {
					jsonString.append(",");
				}
			}
		}
        jsonString.append("]");
        return jsonString.toString();
	}
}
