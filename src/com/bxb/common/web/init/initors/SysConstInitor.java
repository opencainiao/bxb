package com.bxb.common.web.init.initors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mou.common.JsonUtil;
import org.springframework.stereotype.Component;

import com.bxb.common.web.init.Initializable;
import com.bxb.modules.infrastructure.service.ISysConstService;
import com.mongodb.DBObject;

/****
 * 初始化系统环境变量数据（系统常量）
 * 
 * @author NBQ
 *
 */
@Component("sysConstInitor")
public class SysConstInitor implements Initializable {
	@Resource(name = "sysConstService")
	private ISysConstService sysConstService;
	private static final Logger logger = LogManager.getLogger(SysConstInitor.class);

	/****
	 * 初始化系统内存数据，避免硬编码
	 */
	public void init(ServletContext servletContext) {

		// 系统名称

		List<DBObject> allConstants = sysConstService.findAllConstBySysconstTypecode(null);

		Map<String, HashMap<String, String>> allConstantsMap = new HashMap<String, HashMap<String, String>>();

		for (DBObject dbo : allConstants) {
			String typecode = (String) dbo.get("typecode");
			String val = (String) dbo.get("val");
			String dspval = (String) dbo.get("dspval");

			if (allConstantsMap.get(typecode) == null) {
				allConstantsMap.put(typecode, new HashMap<String, String>());
			}

			allConstantsMap.get(typecode).put(val, dspval);
		}

		logger.debug("SYSCONST-[{}]\n{}", allConstantsMap.size(), JsonUtil.getPrettyJsonStr(allConstantsMap));

		// 全局上下文路径
		servletContext.setAttribute("ALLCONSTANT", JsonUtil.toJsonStr(allConstantsMap));

	}
}
