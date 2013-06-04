package smt.middleware.entity;

/**
 *  要改变这种模板请点击 工具|选项|代码编写|编辑标准头文件
 * @author shuwei
 *
 */
public class ProjectInfo {
	//唯一标识
	private String recordID ;
    
    /// 解决方案ID
	private String solutionID ;
    
    /// 项目ID
	private String projectID ;
    
    /// 设计器表单ID
	private String formID ;
   
    /// 设备类型：iPhone、Android、IPad等
	private String deviceType ;
    
    /// 虚拟路径
	private String path ;
    
    /// 文件内容
	private byte[] content ;
    
    /// 文件类型
	private String fileType ;
    
    /// 模块ID号
	private String modelID ;
    
    /// 文件内容
	private String stringContent ;
    
    public String getRecordID() {
		return recordID;
	}

	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}

	public String getSolutionID() {
		return solutionID;
	}

	public void setSolutionID(String solutionID) {
		this.solutionID = solutionID;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getFormID() {
		return formID;
	}

	public void setFormID(String formID) {
		this.formID = formID;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getModelID() {
		return modelID;
	}

	public void setModelID(String modelID) {
		this.modelID = modelID;
	}

	public String getStringContent() {
		return stringContent;
	}

	public void setStringContent(String stringContent) {
		this.stringContent = stringContent;
	}
}
