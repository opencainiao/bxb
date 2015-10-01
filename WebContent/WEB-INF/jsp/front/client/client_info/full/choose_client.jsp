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
		<input type="hidden" id="f_id" name="f_id"
			value="${clientrelationship.f_id}">
		<input type="hidden" id="f_name" name="f_name"
			value="${clientrelationship.f_name}">
		<input type="hidden" id="f_sex" name="f_sex"
			value="${clientrelationship.f_sex}">

		<div class="navbar navbar-default">
			<form class="navbar-form navbar-left">
				<div class="form-group ">
					<input class="form-control " style="width: 300px" type="text"
						id="search_condition" name="search_condition"
						placeholder="输入名称进行查询">
				</div>
				<button class="btn btn-info" type="button" id="btn_search">
					查询</button>
			</form>
		</div>

		<div id="data_manage">
			<table id="list"></table>
		</div>
	</div>

	<script>
		$().ready(function() {

			$("#btn_search").bind("click", search_client);

			$("#family_choose_div").bind("click", parent.popUpChooseClient);

			document.onkeydown = function(event) {
				if (event.keyCode == 13) {
					return false;
				}
			}
		});

		function refresh_parent() {
			parent.refreshFamillyWindow();
			parent.closeAddFamillyWindow();
		}

		function search_client() {
			alert("查询");
		}
	</script>
</body>
</html>