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
							name="owner_id" value="${address.owner_id}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="type_value" class="col-sm-3 control-label"> 地址类型值 </label>
					<div>
						<input type="text" class="form-control" id="type_value"
							name="type_value" value="${address.type_value}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="type_name" class="col-sm-3 control-label"> 地址类型名 </label>
					<div>
						<input type="text" class="form-control" id="type_name"
							name="type_name" value="${address.type_name}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="province" class="col-sm-3 control-label"> 省代码 </label>
					<div>
						<input type="text" class="form-control" id="province"
							name="province" value="${address.province}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="city" class="col-sm-3 control-label"> 市代码 </label>
					<div>
						<input type="text" class="form-control" id="city"
							name="city" value="${address.city}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="district" class="col-sm-3 control-label"> 区代码 </label>
					<div>
						<input type="text" class="form-control" id="district"
							name="district" value="${address.district}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="detail_address" class="col-sm-3 control-label"> 详细地址 </label>
					<div>
						<input type="text" class="form-control" id="detail_address"
							name="detail_address" value="${address.detail_address}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="mainflg" class="col-sm-3 control-label"> 是否常用地址 </label>
					<div>
						<input type="text" class="form-control" id="mainflg"
							name="mainflg" value="${address.mainflg}" readonly>
					</div>
				</div>
		</div>
	</div>
</body>
</html>