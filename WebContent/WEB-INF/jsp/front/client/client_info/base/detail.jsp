<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
dl {
    border-bottom: 1px solid #eee;
    bottom: -1px;
    position: relative;
    margin-bottom: 8px!important;
}

dl dt {
    clear: left;
    color: #888;
    float: left;
    line-height: 1.5;
    width: 150px;
}

dl dd {
    line-height: 1.5;
    margin-left: 60px;
    min-height: 18px;
    width: 320px;
}
</style>
<body>
	<div class="container-fluid inlineone" >
		<div class="col form-horizontal center-block " >
				<dl >
					<dt> 归属用户id </dt>
					<dd>
						${client.owner_user_id}
					</dd>
				</dl>
				<dl >
					<dt> 客户姓名 </dt>
					<dd>
						${client.client_name}
					</dd>
				</dl>
				<div class="form-group ">
					<label for="sex" class="col-sm-3 control-label"> 性别 </label>
					<div>
						<input type="text" class="form-control" id="sex"
							name="sex" value="${client.sex}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="id_number" class="col-sm-3 control-label"> 身份证号 </label>
					<div>
						<input type="text" class="form-control" id="id_number"
							name="id_number" value="${client.id_number}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="birth_date" class="col-sm-3 control-label"> 生日 </label>
					<div>
						<input type="text" class="form-control" id="birth_date"
							name="birth_date" value="${client.birth_date}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="age" class="col-sm-3 control-label"> 年龄 </label>
					<div>
						<input type="text" class="form-control" id="age"
							name="age" value="${client.age}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="email_info" class="col-sm-3 control-label"> 邮箱 </label>
					<div>
						<input type="text" class="form-control" id="email_info"
							name="email_info" value="${client.email_info}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="phone_info" class="col-sm-3 control-label"> 电话 </label>
					<div>
						<input type="text" class="form-control" id="phone_info"
							name="phone_info" value="${client.phone_info}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="address_info" class="col-sm-3 control-label"> 地址 </label>
					<div>
						<input type="text" class="form-control" id="address_info"
							name="address_info" value="${client.address_info}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="region_code" class="col-sm-3 control-label"> 地区码 </label>
					<div>
						<input type="text" class="form-control" id="region_code"
							name="region_code" value="${client.region_code}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="region_name" class="col-sm-3 control-label"> 地区名 </label>
					<div>
						<input type="text" class="form-control" id="region_name"
							name="region_name" value="${client.region_name}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="region_type" class="col-sm-3 control-label"> 地区分类 </label>
					<div>
						<input type="text" class="form-control" id="region_type"
							name="region_type" value="${client.region_type}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="education_type" class="col-sm-3 control-label"> 教育程度 </label>
					<div>
						<input type="text" class="form-control" id="education_type"
							name="education_type" value="${client.education_type}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="name_card_id" class="col-sm-3 control-label"> 名片id </label>
					<div>
						<input type="text" class="form-control" id="name_card_id"
							name="name_card_id" value="${client.name_card_id}" readonly>
					</div>
				</div>
		</div>
	</div>
