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

public class DBOracle extends DBConnect {

	private final Logger log = Logger.getLogger(DBOracle.class);

	private Connection connection; // 全局数据库连接

	public DBOracle(Connection connection) throws SQLException {
		this.connection = connection;

	}

	/**
	 * 提交删除命令
	 */
	@Override
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
	@Override
	public String updateBySql(String strsql) {
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

	@Override
	public String insertBySql(String strsql) {
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

	@Override
	public DataTable getQuery(String sql) {
		// TODO Auto-generated method stub
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

	@Override
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

	@Override
	public DataTable getQuery(String sql, Map<String, String> sqlParam) {
		// TODO Auto-generated method stub
		PreparedStatement pre = null;
		ResultSet rs = null;
		try{
			Set<Entry<String, String>> set = sqlParam.entrySet();
			Iterator<Entry<String, String>> iterator = set.iterator();
			List<String> list = new ArrayList<String>();
			for (Iterator iterator2 = set.iterator(); iterator2.hasNext();) {
				Entry<String, String> entry = (Entry<String, String>) iterator2
						.next();
				String key = entry.getKey();
				String value = entry.getValue();
				sql = sql.replaceAll(key, "?");
				list.add(value);
			}
			pre = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			log.debug(sql);
			log.debug(list.toString());
			for (String str : list) {
				pre.setObject(list.indexOf(str) + 1, str);
			}
			return new DataTable(connection,pre,pre.executeQuery());
		}catch(SQLException ex){
			log.error("Query Erro", ex);
		}finally{
			//DBConnection.close(connection, pre, rs);
		}
		return null;
	}

	@Override
	public int dmlSql(String sql, Map<String, String> sqlParam) {
		// TODO Auto-generated method stub
		PreparedStatement pre = null;
		int count = 0;
		try{
			Set<Entry<String, String>> set = sqlParam.entrySet();
			Iterator<Entry<String, String>> iterator = set.iterator();
			List<String> list = new ArrayList<String>();
			for (Iterator iterator2 = set.iterator(); iterator2.hasNext();) {
				Entry<String, String> entry = (Entry<String, String>) iterator2
						.next();
				String key = entry.getKey();
				String value = entry.getValue();
				sql = sql.replaceAll(key, "?");
				list.add(value);
			}
			pre = connection.prepareStatement(sql);
			for (String str : list) {
				pre.setObject(list.indexOf(str) + 1, str);
			}
			count = pre.executeUpdate();
		}catch(SQLException ex){
			log.error("Query Erro", ex);
		}finally{
			DBConnection.close(connection, pre, null);
		}
		return count;
	}
	

}
