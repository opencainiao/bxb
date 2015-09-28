<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<jsp:include page="/WEB-INF/jsp/include/common_css.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/include/common_js.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/include/common_flexigrid.jsp"></jsp:include>

</head>

<body>
	<input type="hidden" name="ctx" value="<%=request.getContextPath()%>" />
	<input type="hidden" name="_id_m" value="${_id_m }" />
	

	<ul class="breadcrumb">
		<li class="active">客户管理</li>
	</ul>

	<div id="content_inner_page" class="innercontent" >
		<div class="navbar navbar-default">
		    <form class="navbar-form navbar-left" >
		        <div class="form-group " >
		            <input class="form-control " style="width:300px" type="text" id="search_condition" name="search_condition"  placeholder="">
		        </div>
		        <button class="btn btn-info" type="button" id="btn_search">
		            查询
		        </button>
		        <button class="btn btn-primary" type="button" id="btn_add" style="margin-left: 50px ;">
		            添加客户
		        </button>
		        <button class="btn btn-primary" type="button" id="btn_download" style="margin-left: 50px ;">
		            <span class="glyphicon glyphicon-download-alt" aria-hidden="true"  style="margin-right: 8px;"></span>下载客户信息
		        </button>
		    </form>
		</div>

		<div id="data_manage" >
			<table id="list"></table>
		</div>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/front/client/client_info/full/list.js"></script>

</body>

</html>