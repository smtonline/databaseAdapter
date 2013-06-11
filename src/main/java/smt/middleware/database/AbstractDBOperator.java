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

/**
 * 数据库抽象操作对象,负责处理数据库查询,修改,更新,删除,存储过程执行操作
 *
 */
public abstract class AbstractDBOperator {

	private final Logger log = Logger.getLogger(AbstractDBOperator.class);
	protected Connection connection;

	public AbstractDBOperator(Connection connection) {
		this.connection = connection;
	}

	/**
	 * 提交删除命令
	 */
	public String deleteBySql(String strsql) {
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
	
	/**
	 * 执行sql语句查询操作.
	 * 
	 * @param sql
	 * @param sqlParam
	 * @return
	 * @throws SQLException
	 */
	public String getQuery(String sql, Map<String, String> sqlParam,
			boolean isJsonFormat) throws SQLException {
		NamedParameterStatement pre = new NamedParameterStatement(connection,
				sql);
		try {
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
			if (isJsonFormat) {
				return new DataTable(connection, pre.getStatement(),
						pre.executeQuery()).toJson();
			} else {
				return new DataTable(connection, pre.getStatement(),
						pre.executeQuery()).toString();
			}
		} finally {
			DBConnection.close(connection, pre.getStatement(),
					pre.executeQuery());
		}
	}
	
	/**
	 * update delete insert语句执行方法
	 * @param sql
	 * @param sqlParam
	 * @return
	 * @throws SQLException 
	 */
	public int dmlSql(String sql, Map<String, String> sqlParam) throws SQLException {
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
		} finally{
			DBConnection.close(connection, preStatment, null);
		}
		return count;
	}
	
	/**
	 * 存储过程执行方法
	 * @param callSql 存储过程名及参数
	 * @param inParams 存储过程输入值.key表示输入值名,value表示输入值结果
	 * @param outParams 存储过程输出值.key表述输出参数名,value表示数据结构类型
	 * @return msd文件描述的结果结构
	 * @throws SQLException
	 */
	public abstract String executeCall(String callSql,Map<String, String> inParams, Map<String, Integer> outParams) throws SQLException ;
}
