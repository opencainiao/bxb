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

</head>

<body>
	<input type="hidden" name="ctx" value="<%=request.getContextPath()%>" />

	<div id="add_div">
		<sf:form modelAttribute="clientrelationship" class="form-horizontal">
			<input type="hidden" id="f_id" name="f_id"
				value="${clientrelationship.f_id}">
			<input type="hidden" id="f_name" name="f_name"
				value="${clientrelationship.f_name}">
			<input type="hidden" id="f_sex" name="f_sex"
				value="${clientrelationship.f_sex}">

			<input type="hidden" id="s_id" name="s_id"
				value="${clientrelationship.s_id}">
			<input type="hidden" id="s_name" name="s_name"
				value="${clientrelationship.s_name}">
			<input type="hidden" id=s_sex name="s_sex"
				value="${clientrelationship.s_sex}">

			<div class="container-fluid" style="margin-top: 30px">

				<div class="panel panel-info">
					<div class="panel-heading hide">添加家庭成员</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-6">
								<div class="row">
									<div class="form-group form-group-sm  ">
										<label for="relationship" class="col-sm-3 control-label">姓名
										</label>
										<div class="col-sm-8">
											<input class="form-control"
												value="${clientrelationship.f_name}" readonly>
										</div>
									</div>
								</div>
							</div>
							<div class="col-xs-6">
								<div class="row">
									<div class="col-md-12 form-horizontal">
										<div class="form-group form-group-sm  ">
											<label for="relationshi_f" class="col-sm-3 control-label">身份
											</label>
											<div class="col-sm-8">
												<select id="relationship_f" name="relationship_f"
													class="form-control" data-src="constant"
													data-typecode="FAMILLY_RELATIONSHIP"
													data-value="${clientrelationship.relationship}">
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-6">
								<div class="row">
									<div class="form-group form-group-sm  ">
										<label for="family_income_feature"
											class="col-sm-3 control-label">选择 </label>
										<div class="col-sm-8">
											<div class="input-group" id="family_choose_div">
												<input type="text" id="family_choose" name="family_choose"
													class="form-control" readonly> <span
													class="input-group-btn">
													<button class="btn btn-default btn-sm" type="button">
														<span class="glyphicon glyphicon-chevron-right"
															aria-hidden="true"></span>
													</button>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group form-group-sm  ">
										<label for="relationship_cmt" class="col-sm-3 control-label">
											关系说明 </label>
										<div class="col-sm-8">
											<input type="text" class="form-control" id="relationship_cmt"
												name="relationship_cmt"
												value="${clientrelationship.relationship_cmt}"
												placeholder="">
										</div>
									</div>
								</div>
							</div>
							<div class="col-xs-6">
								<div class="row">
									<div class="col-md-12 form-horizontal">
										<div class="form-group form-group-sm  ">
											<label for="relationship_s" class="col-sm-3 control-label">身份
											</label>
											<div class="col-sm-8">
												<select id="relationship_s" name="relationship_s"
													class="form-control" data-src="constant"
													data-typecode="FAMILLY_RELATIONSHIP"
													data-value="${clientrelationship.relationship}">
												</select>
											</div>
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
	</div>

	<script>
		$().ready(function() {

			$("#btn_save").bind("click", save);

			$("#family_choose_div").bind("click", parent.popUpChooseClient);

			document.onkeydown = function(event) {
				if (event.keyCode == 13) {
					return false;
				}
			}
		});

		//设置选择的客户		
		function setSelectedClient(obj) {
			//$.alertObjJson(obj);

			$("#s_id").val(obj["_id_m"]);
			$("#s_name").val(obj["client_name"]);
			$("#s_sex").val(obj["sex"] == '男' ? 1 : 0);

			$("#family_choose").val(obj["client_name"]);
		}

		function refresh_parent() {
			parent.refreshFamillyWindow();
			parent.closeAddFamillyWindow();
		}

		//保存
		// 同步（因为要添加成功后，刷新父页面的亲属列表）
		var save = function() {

			var paramForm = $('form').getFormParam_ux();

			//console.log(JSON.stringify(paramForm));
			//return;

			// 控制按钮为禁用
			$.disableButton("btn_save");
			var successstr = "新增成功";

			var url_to = $.getSitePath() + '/front/familly/add';
			$.ajax({
				type : 'POST',
				url : url_to,
				data : $.extend({
					ts : new Date().getTime()
				}, paramForm),
				dataType : 'json',
				async : false,
				success : function(data) {

					if (data['success'] == 'n') {
						if (data['brErrors']) {
							$.showBRErrors_mou_abs(data['brErrors'], $("#add_div"));
						} else {
							$.alertError(data['message']);
						}
					} else {
						var callback = refresh_parent;

						$.alertSuccessCallback("成功", successstr, callback, [ '30px', '' ]);
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