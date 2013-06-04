package smt.middleware.database;


public class ResolveDataSource {

	private String type; //数据库类型
	private String driverClassName;  //连接
	private String url; //连接地址
	private String userName; //用户名
	private String password;  //密码
	private int maxActive; //最大连接数
	private int maxIdle ; //最大等待连接数
	private long maxWait ; //最大等待秒数
	

	public void setUrl(String url) {
		this.url = url;
	}


	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}


	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public String getType(){
		return "mysql";
	}
	
	public String getDriverClassName(){
		return "com.mysql.jdbc.Driver";
	}
	
	public String getUrl(){
		return "jdbc:mysql://192.168.1.107:3306/test";
	}
	
	public String getUserName(){
		return "root";
	}
	
	public String getPassword(){
		return "123456";
	}
	
	public int getMaxActive(){
		return 1;
	}
	
	public int getMaxIdle() {
		return 1;
	}
	
	public long getMaxWait() {
		return 100;
	}
}
