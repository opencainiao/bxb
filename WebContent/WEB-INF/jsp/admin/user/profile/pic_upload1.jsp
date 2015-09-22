<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>jquery+html5实现图片选取裁剪并上传功能</title>
<!-- add styles -->

<link href="${ctx }/resources/pic-upload/css/main.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx }/resources/pic-upload/css/jquery.Jcrop.min.css"
	rel="stylesheet" type="text/css" />

<!-- add scripts -->
<jsp:include page="/WEB-INF/jsp/include/common_js.jsp"></jsp:include>
<script src="${ctx }/resources/pic-upload/js/jquery.Jcrop.min.js"></script>
<script src="${ctx }/resources/pic-upload/js/script.js"></script>
</head>
<body>
	<div class="demo" style="margin-top: 60px;">
		<div class="bheader">
			<h2>——图像上传表单——</h2>
		</div>
		<div class="bbody">

			<!-- upload form -->
			<form id="upload_form" enctype="multipart/form-data" method="post"
				action="upload.php" onSubmit="return checkForm()">
				<!-- hidden crop params -->
				<input type="hidden" id="x1" name="x1" /> <input type="hidden"
					id="y1" name="y1" /> <input type="hidden" id="x2" name="x2" /> <input
					type="hidden" id="y2" name="y2" />

				<h2>第一步:请选择图像文件</h2>
				<div>
					<input type="file" name="image_file" id="image_file"
						onChange="fileSelectHandler()" />
				</div>

				<div class="error"></div>

				<div class="step2">
					<h2>请选择需要截图的部位,然后按上传</h2>
					<img id="preview" />

					<div class="info">
						<label>文件大小</label> <input type="text" id="filesize"
							name="filesize" /> <label>类型</label> <input type="text"
							id="filetype" name="filetype" /> <label>图像尺寸</label> <input
							type="text" id="filedim" name="filedim" /> <label>宽度</label> <input
							type="text" id="w" name="w" /> <label>高度</label> <input
							type="text" id="h" name="h" />
					</div>

					<button type="button" id="btn_up" >提交</button>
				</div>
			</form>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			
			$("#btn_up").click(function() {

				// 控制按钮为禁用
				$.disableButton("btn_up");

				var paramForm = $('form').getFormParam_ux();

				var successstr = "头像上传成功";
				
				$.logJson(paramForm);

				var url_to = "${ctx}/attachment/upload_pic_cj";

				$.ajax({
					type : 'POST',
					url : url_to,
					data : $.extend({
						ts : new Date().getTime()
					}, paramForm),
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						
						$.logJson(data);

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
						$.enableButton("btn_up");
					}
				});
			})
		});
	</script>

</body>
</html>