/**
 * Copyright (C) 2012 Shenzhen SMT online Co., Ltd.
 * 
 * 项目名称:SMT移动信息化解决方案 (消息推送)
 * 创建日期:2013-5-28
 */
package smt.middleware.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import smt.middleware.database.MysqlDBAdapter;

/**
 * 修改日期:2013-5-28
 * @author gus@sinomaster.com
 * @version 1.0.0
 */
public class DatabaseService {
	public String getDatabaseName() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		MysqlDBAdapter mysqlDB = (MysqlDBAdapter)context.getBean("mysqlDB");
		return mysqlDB.getDatabaseName();
	}
}
