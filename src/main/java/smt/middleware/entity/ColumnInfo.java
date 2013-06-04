package smt.middleware.entity;

public class ColumnInfo {

	/// <summary>
    /// 列名
    /// </summary>
    public string Name { get; set; }
    /// <summary>
    /// 列描述
    /// </summary>
    public string Comments { get; set; }
    /// <summary>
    /// 类型
    /// </summary>
    public string Type { get; set; }
    /// <summary>
    /// 长度
    /// </summary>
    public int Lenght { get; set; }
    /// <summary>
    /// 是否为空
    /// </summary>
    public bool IsNull { get; set; }
}
