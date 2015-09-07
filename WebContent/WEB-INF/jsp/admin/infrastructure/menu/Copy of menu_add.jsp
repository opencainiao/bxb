<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>添加菜单</title>

<%@ include file="/WEB-INF/jsp/include/common_include.jsp"%>
<script type="text/javascript" src="${ctx}/js/dev/menu.js"></script>
</head>

<body>
<cu:navigation percent="98" text="菜单管理 >> 添加菜单" />
<input type="hidden" name="ctx" value="${ctx}" />
<input type="hidden" name="skinpath" value="${ctx}/skin/${skin}" />
<form action="${ctx}/menu/create.do" method="post">
<table width="500" align="center" class="inputTable"
	style="margin-top: 15px">
	<tr >
		<th>父菜单码：</th>
		<td> <input class="text" name="SUPMNUCOD"
			 value="${SUPMNUCOD}" readonly="readonly" /></td>
	</tr>
	<tr >
		<th>父菜单：</th>
		<td> ${SUPMNUCODNAM} </td>
	</tr>
	<tr style="display : none">
		<th>菜单码：</th>
		<td><input class="text" name="MNUCOD" readonly="readonly" /></td>
	</tr>
	<tr>
		<th>菜单名称：</th>
		<td><input name="MNUNAM" class="text" /></td>
	</tr>
	<tr>
		<th>菜单类型：</th>
		<td><select name="LEAFFLG" class="text">
			<option value="1" selected="selected">无子菜单</option>
			<option value="0">有子菜单</option>
		</select></td>
	</tr>
	<tr>
		<th>菜单级次：</th>
		<td>  <input class="text" name="MNULVL" value="${THISMNULVL}" readonly="readonly"></input> </td>
	</tr>
	<tr>
		<th>同级菜单序号：</th>
		<td>
		    <select name="SAMLVLMNUSNO" class="text">
			    <c:forEach var="i" begin="1" end="10" step="1">
			    	<option value=${i}> ${i}</option>
			    </c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<th>是否启用：</th>
		<td><select name="STRUSEFLG" class="text">
			<option value="0" >不启用</option>
			<option value="1" selected="selected">启用</option>
		</select></td>
	</tr>
	<tr>
		<th>所属模块：</th>
		<td><select name="MDLCOD" class="text">
			<option value="01" selected="selected">基础</option>
			<option value="02" >费用</option>
		</select></td>
	</tr>
	<tr>
		<th>菜单动作</th>
		<td><input name="ITFPGMNAM" class="text" /></td>
	</tr>
	<tr style="display : none">
		<th>启用日期</th>
		<td><input name="STRUSEDTE" class="text" readonly="readonly" /></td>
	</tr>
	<tr style="display : none">
		<th>最后维护日期</th>
		<td><input name="LASMTADTE" class="text" readonly="readonly" /></td>
	</tr>
	<tr style="display : none">
		<th>最后维护员工号</th>
		<td><input name="LASMTAEMPNUM" class="text" readonly="readonly" /></td>
	</tr>
	<tr>
		<th>菜单说明</th>
		<td><textarea name="MNUCMT" class="text"
			style="width: 300px; height: 56px"></textarea></td>
	</tr>
</table>
<table width="500" align="center" style="margin-top: 15px">
	<tr>
		<td align="center"><input type="submit" class="btn ok" value="确定" />
		<input type="button" class="btn cancel" value="取消"
			onclick="location='${ctx}/menu/toDetail.do?mnucod=${SUPMNUCOD}'" />
		</td>
	</tr>
</table>
</form>
</body>
</html>
