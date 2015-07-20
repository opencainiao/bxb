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
				<div class="form-group ">
					<label for="owner_user_id" class="col-sm-3 control-label"> 归属用户id </label>
					<div>
						<input type="text" class="form-control" id="owner_user_id"
							name="owner_user_id" value="${clientbaseinfo.owner_user_id}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="client_name" class="col-sm-3 control-label"> 客户姓名 </label>
					<div>
						<input type="text" class="form-control" id="client_name"
							name="client_name" value="${clientbaseinfo.client_name}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="sex" class="col-sm-3 control-label"> 性别 </label>
					<div>
						<input type="text" class="form-control" id="sex"
							name="sex" value="${clientbaseinfo.sex}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="id_number" class="col-sm-3 control-label"> 身份证号 </label>
					<div>
						<input type="text" class="form-control" id="id_number"
							name="id_number" value="${clientbaseinfo.id_number}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="birth_date" class="col-sm-3 control-label"> 生日 </label>
					<div>
						<input type="text" class="form-control" id="birth_date"
							name="birth_date" value="${clientbaseinfo.birth_date}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="age" class="col-sm-3 control-label"> 年龄 </label>
					<div>
						<input type="text" class="form-control" id="age"
							name="age" value="${clientbaseinfo.age}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="email_info" class="col-sm-3 control-label"> 邮箱 </label>
					<div>
						<input type="text" class="form-control" id="email_info"
							name="email_info" value="${clientbaseinfo.email_info}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="phone_info" class="col-sm-3 control-label"> 电话 </label>
					<div>
						<input type="text" class="form-control" id="phone_info"
							name="phone_info" value="${clientbaseinfo.phone_info}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="address_info" class="col-sm-3 control-label"> 地址 </label>
					<div>
						<input type="text" class="form-control" id="address_info"
							name="address_info" value="${clientbaseinfo.address_info}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="region_code" class="col-sm-3 control-label"> 地区码 </label>
					<div>
						<input type="text" class="form-control" id="region_code"
							name="region_code" value="${clientbaseinfo.region_code}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="region_name" class="col-sm-3 control-label"> 地区名 </label>
					<div>
						<input type="text" class="form-control" id="region_name"
							name="region_name" value="${clientbaseinfo.region_name}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="region_type" class="col-sm-3 control-label"> 地区分类 </label>
					<div>
						<input type="text" class="form-control" id="region_type"
							name="region_type" value="${clientbaseinfo.region_type}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="education_type" class="col-sm-3 control-label"> 教育程度 </label>
					<div>
						<input type="text" class="form-control" id="education_type"
							name="education_type" value="${clientbaseinfo.education_type}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="name_card_id" class="col-sm-3 control-label"> 名片id </label>
					<div>
						<input type="text" class="form-control" id="name_card_id"
							name="name_card_id" value="${clientbaseinfo.name_card_id}" readonly>
					</div>
				</div>
		</div>
	</div>
</body>
</html>