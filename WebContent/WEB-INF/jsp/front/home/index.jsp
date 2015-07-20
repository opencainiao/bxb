<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>UMC</title>

<jsp:include page="/WEB-INF/jsp/include/common_css.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/include/common_js.jsp"></jsp:include>
<link media="screen" type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/Flexigrid-master/css/flexigrid.css" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lte IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<style>
.xubox_tab_main {
	padding-left: 0px;
}

.navbar-brand {
	font-size: 38px;
}

.jumbotron {
	padding: 65px 0px 2px 30px;
}

.jumbotron p {
	font-size: 18px;
}

.jumbotron div[class*="col-"] {
	padding-left: 6px;
	padding-right: 6px;
}

.span_center {
	float: left;
	font-size: 18px;
	height: 50px;
	line-height: 20px;
	padding: 15px;
}

.flexigrid {
	font-size: 14px;
}

.Center-Container.is-Inline {
	text-align: center;
	overflow: auto;
}

.Center-Container.is-Inline:after, .is-Inline .Center-Block {
	display: inline-block;
	vertical-align: middle;
}

.Center-Container.is-Inline:after {
	content: '';
	height: 100%;
	margin-left: -0.25em; /* To offset spacing. May vary by font */
}

.is-Inline .Center-Block {
	max-width: 99%;
	/* Prevents issues with long content causes the content block to be pushed to the top */
	/* max-width: calc(100% - 0.25em) /* Only for IE9+ */
}

.form-control {
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	color: #555;
	font-size: 16px;
	height: 36px;
	line-height: 1.42857;
	padding: 6px 12px;
	transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s
		ease-in-out 0s;
	width: 100%;
}
</style>
</head>

<body>
	<input type="hidden" name="ctx" value="<%=request.getContextPath()%>" />
	
	<jsp:include
		page="/WEB-INF/jsp/include/common_navlogin_low_browser.jsp"></jsp:include>
	<div class="Center-Container is-Inline" style="margin-top: 265px;">
	
		<div class="Center-Block">
			<div class="input-group" style="width: 500px;">
				<input  id="search_condition" name="search_condition"  type="text" class="form-control" placeholder="输入材料名称、别名或编号">
				<span class="input-group-btn">
					<button id="btn_search" class="btn btn-primary" type="button" style="padding-top: 7px; padding-bottom: 7px;!important">Go!</button>
				</span>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/Flexigrid-master/js/flexigrid.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/Flexigrid-master/js/mou.fleligrid.ux.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/mou_grid.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/front/login/login.js"></script>
	
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/front/home/index.js"></script>
	<script>
		//$.alertSuccess("成功测试","11");		
	</script>
</body>
</html>
