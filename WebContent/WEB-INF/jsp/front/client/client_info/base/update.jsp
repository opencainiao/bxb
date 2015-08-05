<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<jsp:include page="/WEB-INF/jsp/include/common_css.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/include/common_js.jsp"></jsp:include>

<jsp:include page="/WEB-INF/jsp/include/common_datepicker.jsp"></jsp:include>

</head>

<body>
	<input type="hidden" name="ctx" value="<%=request.getContextPath()%>" />
	<input type="hidden" name="_id" value="${clientbaseinfo._id}" />
	
	<sf:form modelAttribute="clientbaseinfo">
		<div class="container-fluid" style="margin-top: 30px">
			<div class="row">
				<div class="col-xs-6">
					<div class="row">
						<div class="col-md-12 form-horizontal">
							<div class="form-group form-group-sm  ">
								<label for="owner_user_id" class="col-sm-3 control-label">
									归属用户id </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="owner_user_id"
										name="owner_user_id" value="${clientbaseinfo.owner_user_id}"
										placeholder="">
								</div>
							</div>
							<div class="form-group form-group-sm  ">
								<label for="sex" class="col-sm-3 control-label">性别 </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="sex" name="sex"
										value="${clientbaseinfo.sex}" placeholder="">
								</div>
							</div>
							<div class="form-group form-group-sm  ">
								<label for="birth_date" class="col-sm-3 control-label">
									生日 </label>
								<div class="col-sm-8">
									<input id="birth_date" name="birth_date" placeholder="请输入日期"
										class="laydate-icon form-control dateipt"
										value="${clientbaseinfo.birth_date}" onclick="laydate()">
								</div>
							</div>
							<div class="form-group form-group-sm  ">
								<label for="phone_info" class="col-sm-3 control-label">
									电话 </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="phone_info"
										name="phone_info" value="${clientbaseinfo.phone_info}"
										placeholder="">
								</div>
							</div>
							<div class="form-group form-group-sm  ">
								<label for="region_code" class="col-sm-3 control-label">
									地区 </label>
								<div class="col-md-4">
									<div class="input-group">
										<select id="province" name="province" class="form-control"></select>
									</div>
								</div>

								<div class="col-md-4">
									<div class="input-group">
										<select id="city" name="city" class="form-control"></select>
									</div>
								</div>
							</div>
							<div class="form-group form-group-sm  ">
								<label for="education_type" class="col-sm-3 control-label">
									教育程度 </label>
								<div class="col-sm-8">
									<select id="education_type" name="education_type"
										class="form-control"
										data-value="${clientbaseinfo.education_type}"></select>
								</div>
							</div>
						</div>

					</div>
				</div>
				<div class="col-xs-6">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-12 form-horizontal">
								<div class="form-group form-group-sm  ">
									<label for="client_name" class="col-sm-3 control-label">
										客户姓名 </label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="client_name"
											name="client_name" value="${clientbaseinfo.client_name}"
											placeholder="">
									</div>
								</div>
								<div class="form-group form-group-sm  ">
									<label for="id_number" class="col-sm-3 control-label">
										身份证号 </label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="id_number"
											name="id_number" value="${clientbaseinfo.id_number}"
											placeholder="">
									</div>
								</div>
								<div class="form-group form-group-sm  ">
									<label for="email_info" class="col-sm-3 control-label">
										邮箱 </label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="email_info"
											name="email_info" value="${clientbaseinfo.email_info}"
											placeholder="">
									</div>
								</div>
								<div class="form-group form-group-sm  ">
									<label for="address_info" class="col-sm-3 control-label">
										地址 </label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="address_info"
											name="address_info" value="${clientbaseinfo.address_info}"
											placeholder="">
									</div>
								</div>
								<div class="form-group form-group-sm  ">
									<label for="region_type" class="col-sm-3 control-label">
										地区分类 </label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="region_type"
											name="region_type" value="${clientbaseinfo.region_type}"
											placeholder="">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<hr />
			<div class="col-sm-12">
				<button type="button" id="btn_save"
					class="btn btn-primary btn-lg center-block">提交</button>
			</div>
	</sf:form>
	<script>
		function iniProvince() {

			var url = $.getSitePath() + '/backend/city/list_by_pid';

			$.ajax({
				type : 'POST',
				url : url,
				data : {
					ts : new Date().getTime()
				},
				type : 'POST',
				dataType : 'json',
				success : function(data) {

					//$.alertObjJson(data);
					var data_remote = data["rows"];

					if (data['success'] == 'n') {

					} else {
						var setting = {
							"text" : "name",
							"value" : "id"
						}

						$("#province").iniSelect_All(data_remote, setting);

						// 设置监听方法

						$('#province')
								.change(
										function() {

											var text = $('#province')
													.getSelectedText();
											var province_id = $('#province')
													.getSelectedValue();

											//alert(text + "[---]"  + province_id);

											if (province_id == "-1") {
												$("#city").clearAll();
											} else {
												//初始化市的下拉列表
												iniCity(province_id);
											}
										});
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
				}
			});
		}

		function iniCity(province_id) {

			var url = $.getSitePath() + '/backend/city/list_by_pid?parent_id=' 
					+ province_id + '&ts=' + new Date().getTime();

			$.ajax({
				type : 'POST',
				url : url,
				data : {
					ts : new Date().getTime()
				},
				type : 'POST',
				dataType : 'json',
				success : function(data) {

					//$.alertObjJson(data);
					var data_remote = data["rows"];

					if (data['success'] == 'n') {

					} else {
						var setting = {
							"text" : "name",
							"value" : "id"
						}

						$("#city").iniSelect_noAll(data_remote, setting);
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
				}
			});
		}

		function iniEducationType() {

			var url = $.getSitePath() + '/backend/sysconst/all_const_of_consttype?typecode=EDUCATION_TYPE';

			$.ajax({
				type : 'POST',
				url : url,
				data : {
					ts : new Date().getTime()
				},
				type : 'POST',
				dataType : 'json',
				success : function(data) {

					if (!$.isArray(data)){
						alert(data["message"]);
						return;
					}
					//$.alertObjJson(data);
					var data_remote = data;
					var setting = {
						"text" : "dspval",
						"value" : "val"
					}

					$("#education_type").iniSelect_noAll(data_remote,
							setting);

					// 设置值
					var val = $("#education_type").attr("data-value");

					$("#education_type").setSelectedValue(val);
				},
				complete : function(XMLHttpRequest, textStatus) {
				}
			});
		}

		$().ready(function() {

			iniProvince();

			iniEducationType();

			$("#btn_save").bind("click", save);

			document.onkeydown = function(event) {
				if (event.keyCode == 13) {
					return false;
				}
			}

			$("#choose_province").click(function() {
				pupUpChoose_province();
			});

			$("#choose_city").click(function() {

				var province = $("#province").val().trim();

				if (province == "") {
					$.alertError("请选择省");
					return;
				}

				pupUpChoose_city();
			});
		});

		var closeEditWindow = function() {
			parent.refreshBase();
			parent.closeEditBase();
		}
		//保存
		var save = function() {

			// 控制按钮为禁用
			$.disableButton("btn_save");

			var paramForm = $('form').getFormParam_ux();

			var successstr = "修改成功";

			var url_to = window.location.href;

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
							$.showBRErrors_mou_abs(data['brErrors'],
									$("#edit_div"));
						} else {
							$.alertError(data['message']);
						}
					} else {
						$.alertSuccessCallback("修改成功", successstr,
								closeEditWindow);
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					$.enableButton("btn_save");
				}
			});
		};
	</script>
</body>
</html>