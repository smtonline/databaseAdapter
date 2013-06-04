package smt.middleware.entity;

public class MetadataInfo {

    /// 元数据主键ID
	private String metadataID ;
    
    /// 系统编码
	private String systemCode ;
    
    /// 实体编码
	private String modelCode ;
    
    /// 实体名称
	private String modelName ;
    
    /// 元数据xml
	private byte[] metadataSample;

	public String getMetadataID() {
		return metadataID;
	}

	public void setMetadataID(String metadataID) {
		this.metadataID = metadataID;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public byte[] getMetadataSample() {
		return metadataSample;
	}

	public void setMetadataSample(byte[] metadataSample) {
		this.metadataSample = metadataSample;
	}
    
}
