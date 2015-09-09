<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
	<div id="content_inner_page">
		<ul class="breadcrumb">
			<li class="active"><div class="btn-group">
					<button class="btn btn-info btn-sm" type="button" id="btn_save">
						保存</button>
				</div></li>
		</ul>

		<sf:form modelAttribute="menu">
			<input type="hidden" id="sup_menu_code" name="sup_menu_code"
				value="${SUPMNUCOD}" />
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
									<label for="module_code"
										class="col-sm-3 col-xs-3 control-label"> 所属模块</label>
									<div class="col-sm-8 col-xs-8">
										<select id="module_code" name="module_code"
											class="form-control" data-src="constant"
											data-typecode="SYS_MODULE" 
											data-value="${menu.module_code}"></select> </select>
									</div>
								</div>
								<div class="form-group form-group-sm  ">
									<label for="leaf_flg_name" class="col-sm-3 control-label">
										是否启用 </label>
									<div class="col-sm-8">
										<select name="useflg" class="form-control">
											<option value="0">不启用</option>
											<option value="1" selected="selected">启用</option>
										</select>
									</div>
								</div>
							</div>

						</div>
					</div>
					<div class="col-xs-6">
						<div class="row">
							<div class="col-md-12 form-horizontal">
								<div class="form-group form-group-sm  ">
									<label for="SUPMNUCODNAM" class="col-sm-3 control-label">
										父菜单 </label>
									<div class="col-sm-8">
										<input id="SUPMNUCODNAM" name="SUPMNUCODNAM"
											placeholder="请输入日期" class="laydate-icon form-control dateipt"
											value="${SUPMNUCODNAM}" readonly>
									</div>
								</div>
								<div class="form-group form-group-sm  ">
									<label for="menu_level" class="col-sm-3 control-label">
										级次 </label>
									<div class="col-sm-8">
										<input id="menu_level" name="menu_level" placeholder="请输入日期"
											class="laydate-icon form-control dateipt"
											value="${THISMNULVL}" readonly>
									</div>
								</div>
								<div class="form-group form-group-sm  ">
									<label for="iclass" class="col-sm-3 control-label">
										图标class </label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="iclass"
											name="iclass" value="${menu.iclass}" placeholder="">
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
	</div>

	<script>
		$("#btn_save").click(function() {

			// 控制按钮为禁用
			$.disableButton("btn_save");

			var paramForm = $('form').getFormParam_ux();

			var successstr = "添加菜单成功";

			var url_to = $.getSitePath() + "/backend/menu/add";

			var params = [];
			params.push("ts=" + new Date().getTime());

			
			//url_success = url_success + "?" + params.join("&");

			$.ajax({
				type : 'POST',
				url : url_to,
				data : $.extend({
					ts : new Date().getTime()
				}, paramForm),
				type : 'POST',
				dataType : 'json',
				success : function(data) {

					if (data['success'] == 'n') {
						if (data['brErrors']) {
							$.showBRErrors_mou_abs(data['brErrors'], $("#content_inner_page"));
						} else {
							$.alertError(data['message']);
						}
					} else {
						var callback = parent.closeAddSubMenuWindow;
	                	
	                	$.alertSuccessCallback("成功", successstr,callback);
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					$.enableButton("btn_save");
				}
			});
		});
	</script>
</body>

</html>