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

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/admin/infrastructure/sysconst/list.js"></script>
<style>
.content_label {
	padding-bottom: 7px;
	background-color: #eee;
}
</style>
</head>

<body>
	<input type="hidden" name="ctx" value="<%=request.getContextPath()%>" />

	<ul class="breadcrumb">
		<li><a
			href="<%=request.getContextPath()%>/backend/sysconsttype/list">系统常量类型管理</a>
			<span class="divider"></span></li>
		<li class="active">管理常量值</li>
	</ul>

	<div id="content_inner_page" class="innercontent">
		<div class="navbar navbar-default">
			<form class="navbar-form navbar-left">
				<button class="btn btn-primary" type="button" id="btn_add" style="">
					添加常量</button>
			</form>
		</div>

		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-6 col-sm-6 col-xs-6 form-horizontal">
						<div class="form-group-sm  ">
							<label for="typecode" class="col-sm-3 col-xs-3 control-label">
								类型编码 </label>
							<div class="col-sm-6 col-xs-6">
								<input type="text" name="typecode" id="typecode"
									class="form-control" value="${sysconsttype.typecode }">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6 form-horizontal">
						<div class="form-group-sm  ">
							<label for="typename" class="col-sm-3 col-xs-3 control-label">
								类型名称 </label>
							<div class="col-sm-6 col-xs-6">
								<input type="text" name="typename" id="typename"
									class="form-control" value="${sysconsttype.typename  }">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div id="data_manage" style="margin-top: 20px;">
			<table id="list"></table>
		</div>
	</div>

</body>
</html>