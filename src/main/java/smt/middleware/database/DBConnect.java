package smt.middleware.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import smt.middleware.entity.DataTable;

public abstract class DBConnect {

	private final Logger log = Logger.getLogger(DBConnect.class);
	protected Connection connection;

	public DBConnect(Connection connection) {
		this.connection = connection;
	}

	/**
	 * 提交删除命令
	 */
	public String deleteBySql(String strsql) {
		// TODO Auto-generated method stub
		PreparedStatement pre = null;
		try {
			pre = connection.prepareStatement(strsql);
			pre.executeUpdate();
			return "ok";
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			DBConnection.close(connection, pre, null);
		}
	}

	/**
	 * 提交更新命令
	 */
	public String updateBySql(String strsql) {
		PreparedStatement pre = null;
		try {
			pre = connection.prepareStatement(strsql);
			pre.executeUpdate();
			return "ok";
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			DBConnection.close(connection, pre, null);
		}
	}

	public String insertBySql(String strsql) {
		PreparedStatement pre = null;
		try {
			pre = connection.prepareStatement(strsql);
			pre.executeUpdate();
			return "ok";
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			DBConnection.close(connection, pre, null);
		}
	}

	public DataTable getQuery(String sql) {
		String strSql = sql;
		DataTable dataTable = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			pre = connection.prepareStatement(strSql);
			rs = pre.executeQuery();
			dataTable = new DataTable(connection,pre,rs);
			return dataTable;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBConnection.close(connection, pre, rs);
		}
		return dataTable;
	}

	public int dmlSql(String sql) {
		PreparedStatement pre = null;
		int result = 0;
		try {
			pre = connection.prepareStatement(sql);
			result = pre.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBConnection.close(connection, pre, null);
		}
		return result;
	}

	public DataTable getQuery(String sql, Map<String, String> sqlParam) {
		try{
			NamedParameterStatement pre = new NamedParameterStatement(connection, sql);
			Set<Entry<String, String>> set = sqlParam.entrySet();
			Iterator<Entry<String, String>> iterator = set.iterator();
			List<String> list = new ArrayList<String>();
			for (Iterator iterator2 = set.iterator(); iterator2.hasNext();) {
				Entry<String, String> entry = (Entry<String, String>) iterator2
						.next();
				String key = entry.getKey().replace("@", "");
				String value = entry.getValue();
				pre.setObject(key, value);
			}
			return new DataTable(connection,pre.getStatement(),pre.executeQuery());
		}catch(SQLException ex){
			log.error("Query Erro", ex);
		}
		return null;
	}

	public int dmlSql(String sql, Map<String, String> sqlParam) {
		PreparedStatement preStatment = null;
		int count = 0;
		try{
			NamedParameterStatement pre = new NamedParameterStatement(connection, sql);
			Set<Entry<String, String>> set = sqlParam.entrySet();
			Iterator<Entry<String, String>> iterator = set.iterator();
			List<String> list = new ArrayList<String>();
			for (Iterator iterator2 = set.iterator(); iterator2.hasNext();) {
				Entry<String, String> entry = (Entry<String, String>) iterator2
						.next();
				String key = entry.getKey().replace("@", "");
				String value = entry.getValue();
				pre.setObject(key, value);
			}
			preStatment = pre.getStatement();
			count = pre.executeUpdate();
		}catch(SQLException ex){
			log.error("Query Erro", ex);
		}finally{
			DBConnection.close(connection, preStatment, null);
		}
		return count;
	}
	
	
	public abstract String executeCall(String callableName,Map<String, String> inParams, Map<String, Integer> outParams) throws SQLException ;
}
