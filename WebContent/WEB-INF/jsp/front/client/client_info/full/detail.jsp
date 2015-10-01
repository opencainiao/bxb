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
<jsp:include page="/WEB-INF/jsp/include/common_flexigrid.jsp"></jsp:include>

</head>

<body>
	<ul class="breadcrumb">
		<li>
			<a href="<%=request.getContextPath()%>/front/client/list">客户管理</a>
			<span class="divider"></span>
		</li>
		<li class="active">客户信息</li>
	</ul>
	<div>
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active">
				<a href="#personal" aria-controls="home" role="tab"
					data-toggle="tab">个人信息</a>
			</li>
			<li role="presentation">
				<a href="#familly" aria-controls="profile" role="tab"
					data-toggle="tab">家庭成员</a>
			</li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="personal">
				<jsp:include
					page="/WEB-INF/jsp/front/client/client_info/full/detail_persinal.jsp"></jsp:include>
			</div>
			<div role="tabpanel" class="tab-pane" id="familly">
				<jsp:include
					page="/WEB-INF/jsp/front/client/client_info/full/detail_familly.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<script>
		$().ready(function() {
			$("div.tab-pane").css("padding-top", "15px");
		});
	</script>
</body>
</html>