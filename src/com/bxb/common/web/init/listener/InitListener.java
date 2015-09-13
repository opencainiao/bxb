package com.bxb.common.web.init.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bxb.common.util.WebContextUtil;
import com.bxb.common.web.init.Initializable;
import com.bxb.common.web.init.initors.SysInfoInitor;

public class InitListener implements ServletContextListener {

	private List<Initializable> initors = null;

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent event) {

		initors = new ArrayList<Initializable>();

		// 系统信息初始化器
		SysInfoInitor sysinfoinitor = new SysInfoInitor();
		initors.add(sysinfoinitor);
		initors.add((Initializable) WebContextUtil.getBean("sysConstInitor"));

		// 系统缓存初始化器
		// SysCashInitor syscashinitor = new SysCashInitor();
		// initors.add(syscashinitor);

		for (Initializable initor : initors) {
			initor.init(event.getServletContext());
		}
	}
}
