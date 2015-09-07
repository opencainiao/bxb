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
	<input type="hidden" name="ctx" value="${ctx}" />
	<input type="hidden" id="menu_code" name="menu_code"
		value="${menu_code}" />
	<div id="content_inner_page">
		<ul class="breadcrumb">
			<li class="active"><div class="btn-group">
					<button class="btn btn-info btn-sm" type="button" id="btn_save">
						保存</button>
					<button class="btn btn-primary btn-sm" type="button"
						id="btn_add" style="margin-left: 5px">添加子菜单</button>
				</div></li>
		</ul>

		<sf:form modelAttribute="menu">

			<div class="container-fluid" style="margin-top: 10px">
				<div class="row">
					<div class="col-xs-6">
						<div class="row">
							<div class="col-md-12 form-horizontal">
								<div class="form-group form-group-sm  ">
									<label for="menu_name" class="col-sm-3 control-label">
										菜单名称 </label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="menu_name"
											name="menu_name" value="${menu.menu_name}" placeholder="">
									</div>
								</div>
								<div class="form-group form-group-sm  ">
									<label for="menu_level" class="col-sm-3 control-label">
										级次 </label>
									<div class="col-sm-8">
										<input id="menu_level" name="menu_level" placeholder="请输入日期"
											class="laydate-icon form-control dateipt"
											value="${menu.menu_level}" readonly>
									</div>
								</div>
								<div class="form-group form-group-sm  ">
									<label for="leaf_flg_name" class="col-sm-3 control-label">
										子节点 </label>
									<div class="col-sm-8">
										<input id="leaf_flg_name" name="leaf_flg_name"
											placeholder="请输入日期" class="laydate-icon form-control dateipt"
											value="${menu.leaf_flg_name}" readonly>
									</div>
								</div>
							</div>

						</div>
					</div>
					<div class="col-xs-6">
						<div class="row">
							<div class="col-md-12 form-horizontal">
								<div class="form-group form-group-sm  ">
									<label for="iclass" class="col-sm-3 control-label">
										图标class </label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="iclass"
											name="iclass" value="${menu.iclass}" placeholder="">
									</div>
								</div>
								<div class="form-group form-group-sm  ">
									<label for="menu_sno" class="col-sm-3 control-label">
										序号 </label>
									<div class="col-sm-8">
										<input id="menu_sno" name="menu_sno" placeholder="请输入日期"
											class="laydate-icon form-control dateipt"
											value="${menu.menu_sno}" readonly>
									</div>
								</div>
								<div class="form-group form-group-sm  ">
									<label for="module_code" class="col-sm-3 control-label">
										所属模块</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="module_code"
											name="module_code" value="${menu.module_code}" placeholder="">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-xs-6">
						<div class="row">
							<div class="col-md-12 form-horizontal">
								<div class="form-group form-group-sm  ">
									<label for="path" class="col-xs-3 control-label"> 链接 </label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="path" name="path"
											value="${menu.path}" placeholder="" style="width: 500px;">
									</div>
								</div>
								<div class="form-group form-group-sm  ">
									<label for="remark" class="col-xs-3 control-label"> 备注
									</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="remark"
											name="remark" value="${menu.remark}" placeholder=""
											style="width: 500px;">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<hr />
			</div>
		</sf:form>

		<div id="data_manage">
			<table id="list"></table>
		</div>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/admin/infrastructure/menu/list.js"></script>
</body>

</html>