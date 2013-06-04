package smt.middleware.entity;

public class ColumnInfo {

    /// 列名
	private String name ;
    
    /// 列描述
	private String comments ;
    
    /// 类型
	private String type ;
    
    /// 长度
	private int lenght ;
    
    /// 是否为空
	private boolean isNull ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}
    
}
