/**
 * Copyright (C) 2012 Shenzhen SMT online Co., Ltd.
 * 
 * 项目名称:SMT移动信息化解决方案 (消息推送)
 * 创建日期:2013-5-28
 */
package smt.middleware.database;

import org.springframework.stereotype.Component;

/**
 * 修改日期:2013-5-28
 * @author gus@sinomaster.com
 * @version 1.0.0
 */
@Component
public class MysqlDBAdapter {
	public String getDatabaseName() {
		return "mysql";
	}
}
