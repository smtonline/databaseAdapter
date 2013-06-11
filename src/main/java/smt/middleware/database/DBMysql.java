package smt.middleware.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import smt.middleware.entity.DataTable;

public class DBMysql extends DBConnect {

	private final Logger log = Logger.getLogger(DBMysql.class);

	public DBMysql(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}
	
	public String executeCall(String callableName,Map<String, String> inParams, Map<String, Integer> outParams) throws SQLException {
		CallableStatement call = null;
		call = connection.prepareCall("{call "+callableName+"}");
		//
		if (inParams != null) {
			Set<Entry<String, String>> inSetEntry = inParams.entrySet();
			for (Entry<String, String> entry : inSetEntry) {
				call.setObject(entry.getKey(), entry.getValue());
			}
		}
		//
		if (outParams != null) {
			Set<Entry<String, Integer>> outSetEntry = outParams.entrySet();
			for (Entry<String, Integer> entry : outSetEntry) {
				call.registerOutParameter(entry.getKey(), entry.getValue());
			}
		}
		StringBuffer buff = new StringBuffer();
		boolean callResult = call.execute();
		if (outParams != null) {
			Set<Entry<String, Integer>> outSetEntry = outParams.entrySet();
			for (Entry<String, Integer> entry : outSetEntry) {
				buff.append(call.getObject(entry.getKey()));
			}
		}
		if (callResult) {
			ResultSet rs = call.getResultSet();
			buff.append(new DataTable(null, null, rs).toXml());
			while(call.getMoreResults()){
				ResultSet rs1 = call.getResultSet();
				buff.append(new DataTable(null, null, rs1).toXml());
			}
			return buff.toString();
		}		
		
		return null;
	}
}
