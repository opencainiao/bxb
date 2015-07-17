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
					<label for="owner_id" class="col-sm-3 control-label"> 所有者id </label>
					<div>
						<input type="text" class="form-control" id="owner_id"
							name="owner_id" value="${phone.owner_id}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="phone_type_value" class="col-sm-3 control-label"> 电话类型值 </label>
					<div>
						<input type="text" class="form-control" id="phone_type_value"
							name="phone_type_value" value="${phone.phone_type_value}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="phone_type_name" class="col-sm-3 control-label"> 电话类型名称 </label>
					<div>
						<input type="text" class="form-control" id="phone_type_name"
							name="phone_type_name" value="${phone.phone_type_name}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="phone_number" class="col-sm-3 control-label"> 联系电话 </label>
					<div>
						<input type="text" class="form-control" id="phone_number"
							name="phone_number" value="${phone.phone_number}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="mainflg" class="col-sm-3 control-label"> 是否常用电话 </label>
					<div>
						<input type="text" class="form-control" id="mainflg"
							name="mainflg" value="${phone.mainflg}" readonly>
					</div>
				</div>
		</div>
	</div>
</body>
</html>