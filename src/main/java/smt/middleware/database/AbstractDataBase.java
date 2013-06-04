package smt.middleware.database;
/**
 * 数据库抽象类,负责装载数据库连接,并调用数据库select insert update delete操作
 *
 */
public abstract class AbstractDataBase {
	private DBConnection mConnection;
	/**
	 * 数据库初始化
	 * @param connection 数据库连接
	 */
	public AbstractDataBase(DBConnection connection) {
		mConnection = connection;
	}
	
	public abstract String deleteBySql(String sqlStr);
	
	public abstract String UpdateBySql(String sqlStr);
	
	 public abstract String InsertBySql(String sqlStr);
	
}
