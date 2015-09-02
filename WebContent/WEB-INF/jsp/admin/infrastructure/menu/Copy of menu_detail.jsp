<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>菜单管理</title>

<jsp:include page="/WEB-INF/jsp/include/common_css_gentelella.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/include/common_js_gentelella.jsp"></jsp:include>
<%@ include file="/WEB-INF/jsp/include/common_flexigrid.jsp"%>
<script type="text/javascript" src="${ctx}/js/dev/menu_detail.js"></script>

<script type="text/javascript">

	$().ready(function() {
		if ($("input[name=refreshTree]").val() == "true") {
			parent.loadTree();
		}
	});
	
</script>
</head>

<body class="innerPage">

<input type="hidden" name="refreshTree" value="${refreshTree}" />
<input type="hidden" name="LEAFFLG" value="${menu.leafflg}" />
<input type="hidden" name="ACTIONSUPMNUNAM" value="${menu.mnunam}" />
<input type="hidden" name="ACTIONSUPMNUCOD" value="${menu.mnucod}" />
<input type="hidden" name="ACTIONSUPMNULVL" value="${menu.mnulvl}" />


<div class="btnbar">
	<div>
		<c:if test="${not (menu.mnucod eq 'ROOT')}">
			<input id="btn_toEdit" type="button" class="btn_wd1 child_btn " value="编辑" />
		</c:if> 
		<c:if test="${menu.leafflg eq '0'}">
			<input id="btn_toAddSubMnu" type="button" class="btn_wd3 child_btn  " value="添加子菜单" />
			<a id="toaddlink" href="" onclick="submitAdd();"></a>
		</c:if> <c:if test="${menu.leafflg eq '1'}">
			<input id="btn_toAddAction" type="button" class="btn_wd3 child_btn  " value="添加动作" />
			<a href="${ctx}/menu/toAddAction.do?mnucod=${menu.mnucod}&mnunam=${menu.mnunam}"></a>
		</c:if>
	</div>
	<div class="clr"></div>
</div>

<div class='formdiv'>
<cu:formTitleBar text="主信息 " />
<form>
<table width="100%" align="center" class="inputTable" >
	<tr>
	    <th>菜单名称</th><td><span style="font-weight: bold; color: #000099">${menu.mnunam}</span></td>
		<th>菜单类型</th><td>${menu.leafflgnam}</td>
		<th>菜单级次</th><td>${menu.mnulvl}</td>
		<th>父菜单</th>
		<td>
		    <a href="${ctx}/menu/toDetail.do?mnucod=${parentMenu.mnucod}">${parentMenu.mnunam}</a>
		</td>
	</tr>
	<tr>
		<th>同级菜单序号</th><td>${menu.samlvlmnusno}</td>
	    <th>是否启用</th><td>${menu.struseflgnam}</td>
		<th>所属模块</th><td>${menu.mdlcod}</td>
		<th>菜单动作</th><td>${menu.itfpgmnam}</td>
	</tr>
	<tr>
		<th>备注</th>
		<td colspan="7" rowspan="3">${menu.mnucmt }</td>
	</tr>
</table>
</form>
</div>


	<table id="submnutable" style="display: none"></table>
	<c:if test="${hasmenuchildren != null}">
</c:if>


<c:if test="${normactions != null}">
	<table id="normacctiontable" style="display: none"></table>
</c:if>

<c:if test="${authactions != null && fn:length(authactions)>0 }">	
	<table width="98%" align="center" border="0" style="margin-top: 15px">
		<tr>
			<td>授权动作列表：</td>
		</tr>
	</table>
	<table width="98%" align="center" class="table">
		<tr>
			<th>动作</th>
			<th>动作类型</th>
			<th>所属模块</th>
			<th>所属菜单码</th>
			<th>是否启用</th>
			<th>删除标志</th>
			<th>说明</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${authactions}" var="authorAction">
			<tr>
				<td <c:if test="${authorAction.actionpath eq menu.itfpgmnam}">style="color:red"</c:if>>
					${authorAction.actionpath}
				</td>
				<td>${authorAction.autflgnam}</td>
				<td>${authorAction.mdlcodnam}</td>
				<td>${authorAction.mnucod}</td>
				<td>${authorAction.struseflgnam}</td>
				<td>${authorAction.delflgnam}</td>
				<td>${authorAction.actioncmt}</td>
				<td><c:if
					test="${normalAction.actionpath ne menu.itfpgmnam}">
					<a
						href="${ctx}/menu/toEditAction.do?actionid=${authorAction.oid}&mnunam=${menu.mnunam}">编辑</a>
					</c:if>
					<c:if test="${authorAction.actionpath ne menu.itfpgmnam && authorAction.delflg ne '1'}">
					<a
						href="${ctx}/menu/delAction.do?actionid=${authorAction.oid}&mnucod=${menu.mnucod}"
						onclick="return confirm('确定要删除吗?');">删除</a>
				</c:if></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>
