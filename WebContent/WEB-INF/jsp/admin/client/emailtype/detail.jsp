<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<jsp:include page="/WEB-INF/jsp/include/common_css.jsp"></jsp:include>

</head>

<body>
	<div class="container-fluid inlineone" style="margin-top: 30px">
		<div class="col form-horizontal center-block " style="width: 400px">
							<div class="form-group ">
					<label for="owner_user_id" class="col-sm-3 control-label"> 所属用户id </label>
					<div>
						<input type="text" class="form-control" id="owner_user_id"
							name="owner_user_id" value="${phonetype.owner_user_id}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="type_value" class="col-sm-3 control-label"> 邮箱类型值 </label>
					<div>
						<input type="text" class="form-control" id="type_value"
							name="type_value" value="${phonetype.type_value}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="type_name" class="col-sm-3 control-label"> 邮箱类型名称 </label>
					<div>
						<input type="text" class="form-control" id="type_name"
							name="type_name" value="${phonetype.type_name}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="type_character" class="col-sm-3 control-label"> 邮箱类型性质 </label>
					<div>
						<input type="text" class="form-control" id="type_character"
							name="type_character" value="${phonetype.type_character}" readonly>
					</div>
				</div>
		</div>
	</div>
</body>
</html>