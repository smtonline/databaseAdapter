package smt.middleware.database;

import java.sql.Connection;

public class DBMysql extends DBConnect{

	public DBMysql(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeNoParams(String callableName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeInParams(String callableName, String[] sqlParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeOutParams(String callableName, String[] sqlParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeReturnParams(String callableName, String[] sqlParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeUpdateCount(String callableName, String[] sqlParam) {
		// TODO Auto-generated method stub
		
	}

}
