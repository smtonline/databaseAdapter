package smt.middleware.database;

import java.util.Dictionary;
import java.util.Map;

import smt.middleware.entity.DataTable;

public abstract class DBConnect {

	public DBConnect() { }

    //填充dataset
    public abstract String deleteBySql(String strsql);
    //提交删除命令 
    public abstract String updateBySql(String strsql);//提交更新命令   
    public abstract String insertBySql(String strsql);//提交插入命令

    /// <summary>
    /// 根据IDE的查询分析器的sql语句获取数据
    /// </summary>
    /// <param name="sql">sql语句</param>
    /// <returns>返回数据集</returns>
    public abstract DataTable getQuery(String sql);
    
    /// <summary>
    /// 根据IDE的查询分析器的sql语句操作数据(insert/update/delete)
    /// </summary>
    /// <param name="sql">sql语句</param>
    /// <returns>返回int</returns>
    public abstract int dmlSql(String sql);
    
    /// <summary>
    /// 根据IDE的查询分析器的sql语句获取数据
    /// </summary>
    /// <param name="sql">sql语句</param>
    ///  <param name="sql">参数</param>
    /// <returns>返回数据集</returns>
    public abstract DataTable getQuery(String sql,Map<String,String> sqlParam);

    /// <summary>
    /// 根据IDE的查询分析器的sql语句操作数据(insert/update/delete)
    /// </summary>
    /// <param name="sql">sql语句</param>
    /// <param name="sqlParam">参数</param>
    /// <returns>返回int</returns>
    public abstract int dmlSql(String sql,Map<String,String> sqlParam);
}
