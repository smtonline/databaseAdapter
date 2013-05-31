package smt.middleware.database;

public class DbContextHolder {
	private static final ThreadLocal contextHolder = new ThreadLocal();

	public static void setDbType(String dbType) {
		contextHolder.set(dbType);
	}

	public static String getDbType() {
		String str = (String) contextHolder.get();
		System.out.println("test:"+str);
		if (null == str || "".equals(str))
			str = "1";
		return str;
	}

	public static void clearDbType() {
		contextHolder.remove();
	}
}
