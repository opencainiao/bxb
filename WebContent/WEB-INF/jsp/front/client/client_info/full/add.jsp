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

	<ul class="breadcrumb">
		<li><a href="<%=request.getContextPath()%>/front/client/list">客户管理</a>
			<span class="divider"></span></li>
		<li class="active">添加用户</li>
	</ul>
	<div id="add_div">

		<input type="hidden" name="_id" />
		<sf:form modelAttribute="client" class="form-horizontal">
			<div class="container-fluid" style="margin-top: 30px">

				<jsp:include
					page="/WEB-INF/jsp/front/client/client_info/include_seg/base_info.jsp"></jsp:include>
				<jsp:include
					page="/WEB-INF/jsp/front/client/client_info/include_seg/work_info.jsp"></jsp:include>
				<jsp:include
					page="/WEB-INF/jsp/front/client/client_info/include_seg/familly_info.jsp"></jsp:include>
				<jsp:include
					page="/WEB-INF/jsp/front/client/client_info/include_seg/income_info.jsp"></jsp:include>
				<jsp:include
					page="/WEB-INF/jsp/front/client/client_info/include_seg/source_info.jsp"></jsp:include>
				<jsp:include
					page="/WEB-INF/jsp/front/client/client_info/include_seg/xg_info.jsp"></jsp:include>

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

			document.onkeydown = function(event) {
				if (event.keyCode == 13) {
					return false;
				}
			}
		});

		//保存
		var save = function() {
			
			var phone_info = getPhoneInfo();
			var address_info = getAddressInfo();
			var region_info = getReginInfo();
			var interesting_service = getIntrestedService();
			
			var paramForm = $('form').getFormParam_ux();
			
			paramForm =  $.extend(paramForm,phone_info);
			paramForm =  $.extend(paramForm,address_info);
			paramForm =  $.extend(paramForm,region_info);
			paramForm =  $.extend(paramForm,interesting_service);
			
			//console.log(JSON.stringify(paramForm));
			
			//return;

			// 控制按钮为禁用
			$.disableButton("btn_save");
			var successstr = "新增成功";

			var url_to = $.getSitePath() + "/front/client/add";
			var url_success = $.getSitePath() + "/front/client/list";

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
							$.showBRErrors_mou_abs(data['brErrors'], $("#add_div"));
						} else {
							$.alertError(data['message']);
						}
					} else {
						$.alertSuccessNewPage("成功", successstr, url_success,[0.5, '#000']);
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