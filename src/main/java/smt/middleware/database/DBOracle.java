package smt.middleware.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import smt.middleware.entity.DataTable;
import smt.middleware.entity.MetadataInfo;
import smt.middleware.entity.ProjectInfo;
import smt.middleware.entity.TableInfo;

public class DBOracle extends DBConnect {

	private Connection connection; //全局数据库连接
	private String MyConnectString; //全局连接字符串
	@Autowired
	private DBConnection dbConnection ;
	
	public DBOracle(String connectstring) throws SQLException{
		connection = dbConnection.getConnection();
		MyConnectString = connectstring;
	}
	
	@Override
	public String Connect() { //创建数据库连接
		// TODO Auto-generated method stub
		try{
			if(connection.isClosed()){
				connection = dbConnection.getConnection();
			}
			return "ok";
		}catch(SQLException ex){
			return ex.getMessage();
		}
	}

	@Override
	public String CheckConn() { //数据连接检查
		// TODO Auto-generated method stub
		try{
			if(connection.isClosed()){
				this.Connect();
			}
			return "ok";
		}catch(SQLException ex){
			return ex.getMessage();
		}
	}

	/**
	 * 提交删除命令
	 */
	@Override
	public String DeleteBySql(String strsql) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pre = connection.prepareStatement(strsql);
			pre.executeUpdate();
			return "ok";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	@Override
	public String UpdateBySql(String strsql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String InsertBySql(String strsql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableInfo GetUserTableSchema(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TableInfo> GetUserDBSchema() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int MetadataSave(MetadataInfo info) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int MetadataDelete(String metadataID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String GetMoblieTemplate(String DeviceType, String ModelID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int DMLSql(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int DMLSql(String sql, Dictionary<String, String> sqlParam) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean ReleaseDBSave(List<ProjectInfo> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DataTable GetUserTables() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTable GetMetadataByModelName(String modelName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTable GetQuery(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTable GetQuery(String sql, Dictionary<String, String> sqlParam) {
		// TODO Auto-generated method stub
		return null;
	}

}
