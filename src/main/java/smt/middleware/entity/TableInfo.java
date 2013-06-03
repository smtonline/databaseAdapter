package smt.middleware.entity;

import java.util.List;

public class TableInfo {

    /// 表名称
    public String name;
    
    /// 表描述
    public String comments ;

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
    
    /// 该表中的所有列
  //  public List<ColumnInfo> columnList ;
}
