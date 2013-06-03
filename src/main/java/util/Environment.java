/*
 * Copyright (C) 2012 Shenzhen SMT online Co., Ltd.
 * 
 * 项目名称:SMT移动信息化解决方案系统
 * 创建日期:2013-6-3
 */
package util;

import java.io.File;
import java.net.URL;


/**
 * 环境工具
 */
public class Environment {

	public String getDatasourceFolderPath() {
		URL url = getClass().getResource("/");
		String path = url.getPath();
		File classRootPath = new File(path);
		File datasourceFolder = classRootPath.getParentFile().getParentFile();
		return datasourceFolder.getAbsolutePath();
	}
}
