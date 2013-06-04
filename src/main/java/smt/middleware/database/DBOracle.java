package smt.middleware.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import smt.middleware.entity.ColumnInfo;
import smt.middleware.entity.DataTable;
import smt.middleware.entity.MetadataInfo;
import smt.middleware.entity.ProjectInfo;
import smt.middleware.entity.TableInfo;

public class DBOracle extends DBConnect {

	private final Logger log = Logger.getLogger(DBOracle.class);
	
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

	/**
	 * 提交更新命令
	 * @param strsql
	 * @return
	 */
	public String UpdateBySql(String strsql){
		try {
			PreparedStatement pre = connection.prepareStatement(strsql);
			pre.executeUpdate();
			return "ok";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}
	
	/**
	 * 提交插入命令
	 * @param strsql
	 * @return
	 */
	public String InsertBySql(String strsql){
		try {
			PreparedStatement pre = connection.prepareStatement(strsql);
			pre.executeUpdate();
			return "ok";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}
	
	/**
	 * 获取指定表的结构
	 * @param tableName
	 * @return
	 * @throws SQLException 
	 */
	public TableInfo GetUserTableSchema(String tableName){
		ResultSet dtTab = GetUserTalbeByTableName(tableName);
		ResultSet dt = GetUserTableColumns(tableName);
		TableInfo table = null;
		try{
			while(dtTab.next()){
				table = new TableInfo();
				table.setName(dtTab.getString("table_name"));
				table.setComments(dtTab.getString("comments"));
				List<ColumnInfo> colList = new ArrayList<ColumnInfo>();
				ResultSet dtCol = GetUserTableColumns(table.getName());
				while(dtCol.next()){
					ColumnInfo column = new ColumnInfo();
					column.setName(dtCol.getString("COLUMN_NAME"));
					column.setComments(dtCol.getString("comments"));
					column.setType(dtCol.getString("data_type"));
					column.setLenght(dtCol.getInt("DATA_LENGTH"));
					column.setNull(dtCol.getString("NULLABLE").equals("Y") ? true : false);
					colList.add(column);
				}
				table.setColumnList(colList);
			}
		}catch(SQLException ex){
			log.error(ex);
		}		
		return table;
	}
	
	/**
	 * 根据表名查询
	 * @param tableName
	 * @return
	 */
	private ResultSet GetUserTalbeByTableName(String tableName){
		 StringBuilder strSql = new StringBuilder();
         strSql.append(" select t.table_name,c.comments from user_tables t ");
         strSql.append(" join all_tab_comments c ");
         strSql.append(" on t.table_name=c.table_name ");
         strSql.append(" where t.table_name= ? ");
         try {
			PreparedStatement pre = connection.prepareStatement(strSql.toString());
			pre.setString(0, tableName);
			ResultSet re = pre.executeQuery();
			return re;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	/**
	 * 查询数据库中用户表的字段
	 * @param tableName
	 * @return
	 */
	private ResultSet GetUserTableColumns(String tableName){
		StringBuilder strSql = new StringBuilder();

        strSql.append(" select t.COLUMN_NAME,c.comments,t.DATA_TYPE,t.DATA_LENGTH,t.NULLABLE from user_tab_columns t ");
        strSql.append(" join user_col_comments c ");
        strSql.append(" on t.COLUMN_NAME=c.column_name and t.TABLE_NAME=c.table_name ");
        strSql.append(" where t.table_name = ? ");
        
        try {
			PreparedStatement pre = connection.prepareStatement(strSql.toString());
			pre.setString(0, tableName);
			ResultSet re = pre.executeQuery();
			return re;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
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
