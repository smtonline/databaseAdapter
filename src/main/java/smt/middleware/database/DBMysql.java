package smt.middleware.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;

public class DBMysql extends DBConnect {

	private final Logger log = Logger.getLogger(DBMysql.class);

	public DBMysql(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeNoParams(String callableName) {
		// TODO Auto-generated method stub
		java.sql.CallableStatement call = null;
		try {
			call = connection.prepareCall("{call " + callableName + "(?)}");
			call.registerOutParameter(1, Types.INTEGER);
			call.execute();
			//connection.commit();
			log.debug("//////" + call.getInt(1));
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//DBConnection.close(connection, pre, rs);
		}

	}

	@Override
	public void executeInParams(String callableName, String[] sqlParam) {
		// TODO Auto-generated method stub
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			pre = connection.prepareStatement("{call " + callableName + "}");
			if (sqlParam != null) {
				for (int i = 0; i < sqlParam.length; i++) {
					pre.setObject(i + 1, sqlParam[i]);
				}
			}
			rs = pre.executeQuery();
		} catch (SQLException ex) {

		} finally {
			DBConnection.close(connection, pre, rs);
		}
	}

	@Override
	public void executeOutParams(String callableName, String[] sqlParam) {
		// TODO Auto-generated method stub
		try {
			CallableStatement cstmt = connection.prepareCall("{call "
					+ callableName + "}");
			if (sqlParam != null) {
				for (int i = 0; i < sqlParam.length; i++) {
					cstmt.setObject(i + 1, sqlParam[i]);
				}
			}
			cstmt.execute();
			System.out.println("MANAGER ID: " + cstmt.getInt(1));
			cstmt.close();
		} catch (SQLException ex) {

		} finally {
			DBConnection.close(connection);
		}
	}

	@Override
	public void executeReturnParams(String callableName, String[] sqlParam) {
		// TODO Auto-generated method stub
		try {
			CallableStatement cstmt = connection.prepareCall("{? = call "
					+ callableName + "}");
			if (sqlParam != null) {
				for (int i = 0; i < sqlParam.length; i++) {
					cstmt.setObject(i + 1, sqlParam[i]);
				}
			}
			cstmt.execute();
			System.out.println("MANAGER ID: " + cstmt.getInt(1));
			cstmt.close();
		} catch (SQLException ex) {

		} finally {
			DBConnection.close(connection);
		}
	}

	@Override
	public void executeUpdateCount(String callableName, String[] sqlParam) {
		// TODO Auto-generated method stub
		try {
			CallableStatement cstmt = connection.prepareCall("{call "
					+ callableName + "}");
			if (sqlParam != null) {
				for (int i = 0; i < sqlParam.length; i++) {
					cstmt.setObject(i + 1, sqlParam[i]);
				}
			}
			int count = cstmt.getUpdateCount();
			cstmt.close();
			System.out.println("ROWS AFFECTED: " + count);
		} catch (SQLException ex) {

		} finally {
			DBConnection.close(connection);
		}
	}
}
