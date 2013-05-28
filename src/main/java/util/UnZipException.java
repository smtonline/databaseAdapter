/*
 * Copyright (C) 2012 Shenzhen SMT online Co., Ltd.
 * 
 * 项目名称:SMT移动信息化解决方案系统
 * 创建日期:2013-1-28
 */
package util;


/**
 * 解压异常类
 * 修改日期:2013-1-28
 * @author gus@sinomaster.com
 * @version 1.0.0
 */
public class UnZipException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnZipException() {
		super();
	}

	public UnZipException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnZipException(String message) {
		super(message);
	}

	public UnZipException(Throwable cause) {
		super(cause);
	}

	

}
