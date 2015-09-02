<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="/WEB-INF/jsp/include/common_css_gentelella.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/include/common_js_gentelella.jsp"></jsp:include>
<%@ include file="/WEB-INF/jsp/include/tree.jsp"%>
<script type="text/javascript" src="${ctx}/resources/js/admin/infrastructure/menu/menu_manage.js"></script>

</head>

<body class="whole_content">
	<input type="hidden" name="ctx" value="${ctx}" />

	<ul class="breadcrumb">
		<li class="active">菜单管理</li>
	</ul>

	<div id="content_inner_page" class="right_col" >
		<div class="mainContentLayout">
		    <div class="leftMenuLayout" >
		    	<div id="menutree" class="ztree"></div>
			</div>
		    <div class="contentLayout">
		   		<iframe id="contentframeid" name="contentframe"  width="100%" height="100%"  frameborder="0" src="${ctx}/backend/menu/toDetail.do?menu_code=ROOT"></iframe>
		    </div>
	  	</div>
	</div>
</body>

</html>
