package smt.middleware.database;

import java.util.Dictionary;
import java.util.List;

import smt.middleware.entity.DataTable;
import smt.middleware.entity.MetadataInfo;
import smt.middleware.entity.ProjectInfo;
import smt.middleware.entity.TableInfo;

public abstract class DBConnect {

	public DBConnect() { }

//    public abstract String FillDataset(String strsql, String TableName, DataSet DS);
    //填充dataset
    public abstract String DeleteBySql(String strsql);
    //提交删除命令 
    public abstract String UpdateBySql(String strsql);//提交更新命令   
    public abstract String InsertBySql(String strsql);//提交插入命令

    /// <summary>
    /// 查询数据库中所有用户表
    /// </summary>
    /// <returns>DataTable</returns>
    public abstract DataTable GetUserTables();

     /// <summary>
    /// 获取指定表的结构
    /// </summary>
    /// <param name="tableName">表名</param>
    /// <returns>返回表结构类型</returns>
    public abstract TableInfo GetUserTableSchema(String tableName);

    /// <summary>
    /// 获取数据库架构
    /// </summary>
    /// <returns></returns>
    public abstract List<TableInfo> GetUserDBSchema();

    /// <summary>
    /// 元数据获取
    /// </summary>
    /// <param name="modelName">元数据标识名称</param>
    /// <returns>返回数据集</returns>
    public abstract DataTable GetMetadataByModelName(String modelName);

    /// <summary>
    /// 增加元数据信息
    /// </summary>
    /// <param name="info">元数据实体</param>
    /// <returns>操作影响行数</returns>
    public abstract int MetadataSave(MetadataInfo info);

    /// <summary>
    /// 删除元数据信息
    /// </summary>
    /// <param name="metadataID">元数据信息ID</param>
    /// <returns>操作影响行数</returns>
    public abstract int MetadataDelete(String metadataID);

    /// <summary>
    /// 获取元数据模版
    /// </summary>
    /// <param name="DeviceType">设备</param>
    /// <param name="ModelID">modelid</param>
    /// <returns>string</returns>
    public abstract String GetMoblieTemplate(String DeviceType, String ModelID);

    /// <summary>
    /// 根据IDE的查询分析器的sql语句获取数据
    /// </summary>
    /// <param name="sql">sql语句</param>
    /// <returns>返回数据集</returns>
    public abstract DataTable GetQuery(String sql);
    
    /// <summary>
    /// 根据IDE的查询分析器的sql语句操作数据(insert/update/delete)
    /// </summary>
    /// <param name="sql">sql语句</param>
    /// <returns>返回int</returns>
    public abstract int DMLSql(String sql);
    
    /// <summary>
    /// 根据IDE的查询分析器的sql语句获取数据
    /// </summary>
    /// <param name="sql">sql语句</param>
    ///  <param name="sql">参数</param>
    /// <returns>返回数据集</returns>
    public abstract DataTable GetQuery(String sql,Dictionary<String,String> sqlParam);

    /// <summary>
    /// 根据IDE的查询分析器的sql语句操作数据(insert/update/delete)
    /// </summary>
    /// <param name="sql">sql语句</param>
    /// <param name="sqlParam">参数</param>
    /// <returns>返回int</returns>
    public abstract int DMLSql(String sql,Dictionary<String,String> sqlParam);
    
    /// <summary>
    /// 保存
    /// </summary>
    /// <param name="list">要保存的项目信息集合</param>
    /// <returns>返回bool值</returns>
    public abstract boolean ReleaseDBSave(List<ProjectInfo> list);
}
