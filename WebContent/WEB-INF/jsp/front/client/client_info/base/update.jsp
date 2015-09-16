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
	<input type="hidden" name="_id" value="${clientbaseinfo._id}" />

	<div id="add_div">

		<sf:form modelAttribute="client" class="form-horizontal">
			<div class="container-fluid" style="margin-top: 10px">

				<jsp:include
					page="/WEB-INF/jsp/front/client/client_info/include_seg/base_info.jsp"></jsp:include>
				<hr />

				<div class="col-sm-12">
					<button type="button" id="btn_save"
						class="btn btn-primary btn-lg center-block">提交</button>
				</div>
		</sf:form>
	</div>
	
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

						$('#province').change(function() {

							var text = $('#province').getSelectedText();
							var province_id = $('#province').getSelectedValue();

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

			var url = $.getSitePath() + '/backend/city/list_by_pid?parent_id=' + province_id + '&ts=' + new Date().getTime();

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
				type : 'POST',
				dataType : 'json',
				success : function(data) {

					if (!$.isArray(data)) {
						alert(data["message"]);
						return;
					}
					var data_remote = data;
					var setting = {
						"text" : "dspval",
						"value" : "val"
					}

					$("#education_type").iniSelect_noAll(data_remote, setting);

					// 设置值
					var val = $("#education_type").attr("data-value");

					$("#education_type").setSelectedValue(val);
				},
				complete : function(XMLHttpRequest, textStatus) {
				}
			});
		}

		function initWidth() {

			var sm3_w = $(".col-sm-3:nth-child(1)").width();
			var xs2_w = $(".col-xs-2:nth-child(1)").width();
			//	alert(sm3_w + "---" + xs2_w);
			$(".col-xs-2").width(sm3_w);

		}
		$().ready(function() {

		
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