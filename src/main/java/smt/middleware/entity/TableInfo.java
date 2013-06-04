package smt.middleware.entity;

import java.util.List;

public class TableInfo {

    /// 表名称
	private String name;
    
    /// 表描述
	private String comments ;

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
	private List<ColumnInfo> columnList ;

	public List<ColumnInfo> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<ColumnInfo> columnList) {
		this.columnList = columnList;
	}
}
