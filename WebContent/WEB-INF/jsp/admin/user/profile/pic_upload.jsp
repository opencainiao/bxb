<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- add styles -->

<link href="${ctx }/resources/pic-upload/css/main.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx }/resources/pic-upload/css/jquery.Jcrop.min.css"
	rel="stylesheet" type="text/css" />

<!-- add scripts -->
<jsp:include page="/WEB-INF/jsp/include/common_js.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/include/common_css.jsp"></jsp:include>
<script src="${ctx }/resources/pic-upload/js/jquery.Jcrop.min.js"></script>
<script src="${ctx }/resources/pic-upload/js/script.js"></script>
<script src="${ctx }/resources/js/jquery.form.js"></script>

</head>
<body>
	<form id="upload_form" enctype="multipart/form-data">
		<input type="hidden" id="_id" name="_id" value=${userid } />
		<div class="input-group" style="padding: 8px; padding-left: 10px">
			<input type="file" name="image_file" id="image_file"
				style="width: 88px;" onChange=fileSelectHandler() class="pull-left"
				required />

			<div class="input-group-btn pull-left" style="margin-left: 10px">
				<button name="btn_up" id="btn_up" type="button"
					class="btn btn-primary"
					style="border-radius: 4px; margin-top: 2px;">提交</button>
			</div>
		</div>
		<div class="bbody" style="border-top: 1px solid black">
			<input type="hidden" id="x1" name="x1" /> <input type="hidden"
				id="y1" name="y1" /> <input type="hidden" id="x2" name="x2" /> <input
				type="hidden" id="y2" name="y2" />

			<div class="error"></div>

			<div class="step2">
				<img id="preview" />

				<div class="info">
					<label>文件大小</label> <input type="text" id="filesize"
						name="filesize" /> <label>类型</label> <input type="text"
						id="filetype" name="filetype" /> <label>图像尺寸</label> <input
						type="text" id="filedim" name="filedim" /> <label>宽度</label> <input
						type="text" id="w" name="w" /> <label>高度</label> <input
						type="text" id="h" name="h" />
				</div>
			</div>
		</div>
	</form>


	<script>
	
	</script>
	<script>
		function validate() {

			// 控制按钮为禁用
			$.disableButton("btn_up");

			if (!checkForm()) {
				$.enableButton("btn_up");
				return false;
			}

			return true;
		}

		function uploadFile() {

			if (!validate()) {
				return;
			}

			var xhr = new XMLHttpRequest();
			var fd = new FormData(document.getElementById('upload_form'));

			//$.logJson(fd, "参数");
			xhr.addEventListener("load", uploadComplete, false);
			xhr.addEventListener("error", uploadFailed, false);
			xhr.open("POST", "${ctx}/profile/${userid}/update_head_img");

			xhr.send(fd);
		}

		function uploadComplete(evt) {
			
			$.enableButton("btn_up");
			
			var data = JSON.parse(evt.target.responseText);
			
			$.logJson(data);

			if (data['success'] == 'n') {
				
				$.alertError(data['message']);
				
			} else {
				var _id_m = data["attach_id"];
				parent.refreshHeadImg(_id_m);
				window.top.refreshHeadImg(_id_m);
				parent.closeUploadHeadImg();
			}
		}

		function uploadFailed(evt) {
			alert("上传出错");
		}

		$(document).ready(function() {
			$("#btn_up").click(function(e) {

				uploadFile();
			})
		});
	</script>

</body>
</html>