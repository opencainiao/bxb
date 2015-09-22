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
	<div class="page-title">
		<div class="title_left">
			<h3>用户信息</h3>
		</div>
	</div>
	<div class="clearfix"></div>

	<div id="page_content" class="row">
		<div class="col-md-3 col-sm-3 col-xs-12 profile_left">
			<div id="profile_img" class="profile_img" style="margin-left: 15px;">
				<div class="avatar-view" title="Change the avatar">
					<img id="head_img" src="images/picture.jpg" alt="头像">
				</div>
			</div>
		</div>
		<div class="col-md-9 col-sm-9 col-xs-12">
			<div class="profile_title">
				<div class="col-md-6">
					<h4>User Activity Report</h4>
				</div>
			</div>
		</div>
	</div>


	<!-- image cropping -->
	<script
		src="<%=request.getContextPath()%>/resources/gentelella/production/js/cropping/cropper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/gentelella/production/js/cropping/main.js"></script>

	<!-- datepicker -->
	<script type="text/javascript">
		
		function refreshHeadImg(_id_m){
			
			var new_src = $.getSitePath() + "/attachment/" + _id_m;
			//console.log(new_src);
			//alert(new_src);
			$("#head_img").attr("src", new_src);
		}
		
		function closeUploadHeadImg(){
			$.closeWindow("profile_img", $("#page_content"));
		}
	
		function toUploadHeadImg() {
			var url = $.getSitePath() + '/profile/to_upload_head_img';
			//alert(url);
			$.popUpWindow("上传用户头像", url, "90%", "90%", "profile_img", $("#page_content"));
		}

		$(document).ready(function() {
			$("#profile_img").click(function(){
				toUploadHeadImg();
			})
		});
	</script>
	<!-- /datepicker -->
</body>

</html>