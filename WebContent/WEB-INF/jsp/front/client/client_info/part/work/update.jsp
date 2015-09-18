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
	<sf:form modelAttribute="client" class="form-horizontal">
		<input type="hidden" id="_id" name="_id" value="${_id}" />
		<input type="hidden" id="owner_user_id" name="owner_user_id"
			value="${owner_user_id}">
		<div class="container-fluid" style="margin-top: 10px">
			<jsp:include
				page="/WEB-INF/jsp/front/client/client_info/include_seg/work_info.jsp"></jsp:include>

			<div class="col-sm-12">
				<button type="button" id="btn_save"
					class="btn btn-primary btn-lg center-block">提交</button>
			</div>
		</div>
	</sf:form>


	<script>
		$().ready(function() {
			$("#btn_save").bind("click", save);
			
			var height = $('form').height() + 70;
			parent.setPWindowH(2,height);
		});

		var closeEditWindow = function() {
			parent.refreshWork();
			parent.closeEditWork();
		}
		//保存
		var save = function() {

			var paramForm = $('form').getFormParam_ux();

			$.logJson(paramForm, "修改--提交服务器的参数");
			var successstr = "修改成功";

			var url_to = $.getSitePath() + "/front/client/${_id}/update_part?part_flg=2";

			$.logJson(url_to, "提交url");

			//return;
			// 控制按钮为禁用
			$.disableButton("btn_save");

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
							$.showBRErrors_mou_abs(data['brErrors'], $("#edit_div"));
						} else {
							$.alertError(data['message']);
						}
					} else {
						$.alertSuccessCallback("修改成功", successstr, closeEditWindow);
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