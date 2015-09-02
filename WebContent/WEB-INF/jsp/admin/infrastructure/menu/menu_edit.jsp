<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>菜单管理</title>   
    <%@ include file="/WEB-INF/jsp/include/common_include.jsp"%> 
    <script type="text/javascript" src="${ctx}/js/dev/menu.js"></script> 
    
	<script type="text/javascript">
		$().ready(function() {
			$("select[name=LEAFFLG]").setSelectedValue("${menu.leafflg}");
			$("select[name=MNULVL]").setSelectedValue("${menu.mnulvl}");
			$("select[name=SAMLVLMNUSNO]").setSelectedValue("${menu.samlvlmnusno}");
			$("select[name=STRUSEFLG]").setSelectedValue("${menu.struseflg}");
			$("select[name=MDLCOD]").setSelectedValue("${menu.mdlcod}");
		});
	</script>
    
  </head>

  <body>
  	<cu:navigation percent="98" text="菜单管理 >> 编辑菜单"/>
    <form action="${ctx}/menu/save.do" method="post">
    <table width="500" align="center" class="inputTable" style="margin-top:15px">
      <tr >
		<th>父菜单：</th>
		<td> <input class="text" name="SUPMNUCOD"
			 readonly="readonly" value="${menu.supmnucod}"/></td>
	</tr>
	<tr style="display : none">
		<th>菜单码：</th>
		<td><input class="text" name="MNUCOD" readonly="readonly" value="${menu.mnucod}"/></td>
	</tr>
	<tr>
		<th>菜单名称：</th>
		<td><input name="MNUNAM" class="text" value="${menu.mnunam}"/></td>
	</tr>
	<tr>
		<th>菜单类型：</th>
		<td><select name="LEAFFLG" class="text">
			<option value="0" selected="selected">有子菜单</option>
			<option value="1">无子菜单</option>
		</select></td>
	</tr>
	<tr>
		<th>菜单级次：</th>
		<td> <input class="text" name="MNULVL"
			 readonly="readonly" value="${menu.mnulvl}"/>
		</td>
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
		<td><input name="ITFPGMNAM" class="text" value="${menu.itfpgmnam}"/></td>
	</tr>
	<tr style="display : none">
		<th>启用日期</th>
		<td><input name="STRUSEDTE" class="text" readonly="readonly" value="${menu.strusedte }"/></td>
	</tr>
	<tr style="display : none">
		<th>最后维护日期</th>
		<td><input name="LASMTADTE" class="text" readonly="readonly" value="${menu.lasmtadte}"/></td>
	</tr>
	<tr style="display : none">
		<th>最后维护员工号</th>
		<td><input name="LASMTAEMPNUM" class="text" readonly="readonly" value="${menu.lasmtaempnum }"/></td>
	</tr>
	<tr>
		<th>菜单说明</th>
		<td><textarea name="MNUCMT" class="text"
			style="width: 300px; height: 56px">${menu.mnucmt}</textarea></td>
	</tr>
    </table>
    <table width="500" align="center" style="margin-top:15px">
      <tr><td align="center">
        <input type="submit" class="btn ok" value="确定"/>
        <input type="button" class="btn cancel" value="取消" onclick="location='${ctx}/menu/toDetail.do?mnucod=${menu.mnucod}'"/>
      </td></tr>
    </table>
    </form>
  </body>
</html>
