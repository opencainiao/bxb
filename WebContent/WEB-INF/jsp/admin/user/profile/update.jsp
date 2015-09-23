<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link
	href="<%=request.getContextPath()%>/resources/gentelella/production/fonts/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="<%=request.getContextPath()%>/resources/gentelella/production/css/animate.min.css"
	rel="stylesheet" type="text/css">

<!-- Custom styling plus plugins -->
<link
	href="<%=request.getContextPath()%>/resources/gentelella/production/css/custom.css"
	rel="stylesheet" type="text/css">
<link
	href="<%=request.getContextPath()%>/resources/gentelella/production/css/icheck/flat/green.css"
	rel="stylesheet" type="text/css" />

<jsp:include page="/WEB-INF/jsp/include/common_css.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/include/common_js_gentelella.jsp"></jsp:include>



</head>
<body class="nav-md">
	<sf:form modelAttribute="user" class="form-horizontal center-block">
		<input type="hidden" id="_id_m" name="_id_m" value="${_id }" />
		<div class="page-title" style="height: 55px;padding-left: 15px; border-bottom: 1px solid #f5f7fa; margin-bottom: 10px;">
			<div class="title_left">
				<h4 style="margin-bottom: 0px;margin-top: 8px ">用户信息</h4>
			</div>
			<div class="pull-right">
				<button name="btn_save" id="btn_save" type="button"
					class="btn btn-primary" style="border-radius: 4px">保存</button>
			</div>
		</div>
		<div class="clearfix"></div>

		<div id="page_content" class="row">
			<div class="col-md-3 col-sm-3 col-xs-12 profile_left">
				<div id="profile_img" class="profile_img" style="margin-left: 15px;">
					<div class="avatar-view" title="Change the avatar">
						<input type="hidden" id="headImageId" name="headImageId"
							value="${user.headImageId }" /> <img id="head_img"
							src="${ctx }/attachment/${user.headImageId }" alt="头像">
					</div>
				</div>
			</div>
			<div class="col-md-9 col-sm-9 col-xs-12">
				<div class="profile_title">
					<div class="col-md-6">
						<h4>${user.username}</h4>
					</div>
				</div>
				<div class="form-group "  style="margin-top: 15px;">
					<label for="nick" class="col-sm-2 control-label">
						昵称 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="nick"
							name="nick" value="${user.nick }">
					</div>
				</div>
				<div class="form-group ">
					<label for="email" class="col-sm-2 control-label">
						邮箱 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="email"
							name="email" value="${user.email }">
					</div>
				</div>
				<div class="form-group ">
					<label for="phone" class="col-sm-2 control-label">
						联系电话 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="phone"
							name="phone" value="${user.phone }">
					</div>
				</div>
			</div>
		</div>

	</sf:form>
	<!-- image cropping -->
	<script
		src="<%=request.getContextPath()%>/resources/gentelella/production/js/cropping/cropper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/gentelella/production/js/cropping/main.js"></script>

	<!-- datepicker -->
	<script type="text/javascript">
		function refreshHeadImg(_id_m) {

			var new_src = $.getSitePath() + "/attachment/" + _id_m;
			//console.log(new_src);
			//alert(new_src);
			$("#headImageId").val(_id_m);
			$("#head_img").attr("src", new_src);
		}

		function closeUploadHeadImg() {
			$.closeWindow("profile_img", $("#page_content"));
		}

		function toUploadHeadImg() {
			var url = $.getSitePath() + '/profile/to_upload_head_img';
			//alert(url);
			$.popUpWindow("上传用户头像", url, "90%", "90%", "profile_img", $("#page_content"));
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
				dataType : 'json',
				success : function(data) {

					if (data['success'] == 'n') {
						if (data['brErrors']) {
							$.showBRErrors_mou_abs(data['brErrors'], $("#edit_div"));
						} else {
							$.alertError(data['message']);
						}
					} else {
						$.alertSuccessCallback("修改成功", successstr, closeUploadHeadImg);
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					$.enableButton("btn_save");
				}
			});
		};

		$(document).ready(function() {
			$("#profile_img").click(function() {
				toUploadHeadImg();
			})

			$("#btn_save").bind("click", save);
		});
	</script>
	<!-- /datepicker -->
</body>

</html>