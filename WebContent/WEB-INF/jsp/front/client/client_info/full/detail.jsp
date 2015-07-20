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
<ul class="breadcrumb">
    <li><a href="<%=request.getContextPath()%>/front/client/list">客户管理</a> <span class="divider"></span></li>
    <li class="active">客户信息</li>
</ul>

<div class="container-fluid">
  <div class="row">
   	<div class="col-md-6">
   		<div class="row">
		   	<div class="col-md-12">
		   		<div class="row">
		   			<div class="col-md-9 col-md-offset-3">
		   				<div class="panel panel-info">
							<div class="panel-heading">基本信息</div>
							<div class="panel-body">
								<jsp:include page="/WEB-INF/jsp/front/client/client_info/base/detail.jsp" flush="true"/>
							</div>
						</div>
		   			</div>
		   		</div>
		   		<div class="row">
		   			<div class="col-md-9 col-md-offset-3">
		   				<div class="panel panel-info">
							<div class="panel-heading">基本信息</div>
							<div class="panel-body">
								2
							</div>
						</div>
		   			</div>
		   		</div>
		   		<div class="row">
		   			<div class="col-md-9 col-md-offset-3">
		   				<div class="panel panel-info">
							<div class="panel-heading">基本信息</div>
							<div class="panel-body">
								3
							</div>
						</div>
		   			</div>
		   		</div>
		   	</div>
		</div>
   	</div>
  	<div class="col-md-6">
		<div class="row">
		   	<div class="col-md-12">
		   		<div class="row">
		   			<div class="col-md-9 ">
		   				<div class="panel panel-info">
							<div class="panel-heading">基本信息</div>
							<div class="panel-body">
								<jsp:include page="/WEB-INF/jsp/front/client/client_info/base/detail.jsp" flush="true"/>
							</div>
						</div>
		   			</div>
		   		</div>
		   		<div class="row">
		   			<div class="col-md-9 ">
		   				<div class="panel panel-info">
							<div class="panel-heading">基本信息</div>
							<div class="panel-body">
								2
							</div>
						</div>
		   			</div>
		   		</div>
		   		<div class="row">
		   			<div class="col-md-9 ">
		   				<div class="panel panel-info">
							<div class="panel-heading">基本信息</div>
							<div class="panel-body">
								3
							</div>
						</div>
		   			</div>
		   		</div>
		   	</div>
		</div>
	</div>
  </div>
</div>



	<div class="container-fluid inlineone" style="margin-top: 30px">
		<div class="col form-horizontal center-block " style="width: 400px">
							<div class="form-group ">
					<label for="owner_user_id" class="col-sm-3 control-label"> 归属用户id </label>
					<div>
						<input type="text" class="form-control" id="owner_user_id"
							name="owner_user_id" value="${client.owner_user_id}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="client_name" class="col-sm-3 control-label"> 姓名 </label>
					<div>
						<input type="text" class="form-control" id="client_name"
							name="client_name" value="${client.client_name}" readonly>
					</div>
				</div>
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
					<label for="address_info" class="col-sm-3 control-label"> 客户的地址信息 </label>
					<div>
						<input type="text" class="form-control" id="address_info"
							name="address_info" value="${client.address_info}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="phone_info" class="col-sm-3 control-label"> 客户的电话信息 </label>
					<div>
						<input type="text" class="form-control" id="phone_info"
							name="phone_info" value="${client.phone_info}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="email_info" class="col-sm-3 control-label"> 客户的邮箱信息 </label>
					<div>
						<input type="text" class="form-control" id="email_info"
							name="email_info" value="${client.email_info}" readonly>
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
					<label for="education_type" class="col-sm-3 control-label"> 教育程度分类 </label>
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
				<div class="form-group ">
					<label for="company" class="col-sm-3 control-label"> 工作单位 </label>
					<div>
						<input type="text" class="form-control" id="company"
							name="company" value="${client.company}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="company_nature" class="col-sm-3 control-label"> 企业性质 </label>
					<div>
						<input type="text" class="form-control" id="company_nature"
							name="company_nature" value="${client.company_nature}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="trade_type" class="col-sm-3 control-label"> 行业类型 </label>
					<div>
						<input type="text" class="form-control" id="trade_type"
							name="trade_type" value="${client.trade_type}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="career_type" class="col-sm-3 control-label"> 职业类型 </label>
					<div>
						<input type="text" class="form-control" id="career_type"
							name="career_type" value="${client.career_type}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="job_position" class="col-sm-3 control-label"> 职位 </label>
					<div>
						<input type="text" class="form-control" id="job_position"
							name="job_position" value="${client.job_position}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="job_level" class="col-sm-3 control-label"> 职级 </label>
					<div>
						<input type="text" class="form-control" id="job_level"
							name="job_level" value="${client.job_level}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="marital_status" class="col-sm-3 control-label"> 婚姻状况 </label>
					<div>
						<input type="text" class="form-control" id="marital_status"
							name="marital_status" value="${client.marital_status}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="wedding_date" class="col-sm-3 control-label"> 结婚日期 </label>
					<div>
						<input type="text" class="form-control" id="wedding_date"
							name="wedding_date" value="${client.wedding_date}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="boy_num" class="col-sm-3 control-label"> 男孩数 </label>
					<div>
						<input type="text" class="form-control" id="boy_num"
							name="boy_num" value="${client.boy_num}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="girl_num" class="col-sm-3 control-label"> 女孩数 </label>
					<div>
						<input type="text" class="form-control" id="girl_num"
							name="girl_num" value="${client.girl_num}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="children_num" class="col-sm-3 control-label"> 子女数 </label>
					<div>
						<input type="text" class="form-control" id="children_num"
							name="children_num" value="${client.children_num}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="annual_income_personal" class="col-sm-3 control-label"> 个人年收入 </label>
					<div>
						<input type="text" class="form-control" id="annual_income_personal"
							name="annual_income_personal" value="${client.annual_income_personal}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="annual_income_personal_type" class="col-sm-3 control-label"> （个人）年收入分类码 </label>
					<div>
						<input type="text" class="form-control" id="annual_income_personal_type"
							name="annual_income_personal_type" value="${client.annual_income_personal_type}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="annual_income_family" class="col-sm-3 control-label"> （家庭）年收入 </label>
					<div>
						<input type="text" class="form-control" id="annual_income_family"
							name="annual_income_family" value="${client.annual_income_family}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="annual_income_family_type" class="col-sm-3 control-label"> （家庭）年收入分类码 </label>
					<div>
						<input type="text" class="form-control" id="annual_income_family_type"
							name="annual_income_family_type" value="${client.annual_income_family_type}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="family_income_feature" class="col-sm-3 control-label"> 家庭收入特点 </label>
					<div>
						<input type="text" class="form-control" id="family_income_feature"
							name="family_income_feature" value="${client.family_income_feature}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="family_financial_standing" class="col-sm-3 control-label"> 财务状况码 </label>
					<div>
						<input type="text" class="form-control" id="family_financial_standing"
							name="family_financial_standing" value="${client.family_financial_standing}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="source_type" class="col-sm-3 control-label"> 客户来源码 </label>
					<div>
						<input type="text" class="form-control" id="source_type"
							name="source_type" value="${client.source_type}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="introducer_name" class="col-sm-3 control-label"> 介绍人 </label>
					<div>
						<input type="text" class="form-control" id="introducer_name"
							name="introducer_name" value="${client.introducer_name}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="introducer_relationship" class="col-sm-3 control-label"> 与介绍人关系 </label>
					<div>
						<input type="text" class="form-control" id="introducer_relationship"
							name="introducer_relationship" value="${client.introducer_relationship}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="introducer_closeness" class="col-sm-3 control-label"> 与介绍人亲密度 </label>
					<div>
						<input type="text" class="form-control" id="introducer_closeness"
							name="introducer_closeness" value="${client.introducer_closeness}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="introducer_evaluation" class="col-sm-3 control-label"> 介绍人评价 </label>
					<div>
						<input type="text" class="form-control" id="introducer_evaluation"
							name="introducer_evaluation" value="${client.introducer_evaluation}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="contact_type" class="col-sm-3 control-label"> 可接触度 </label>
					<div>
						<input type="text" class="form-control" id="contact_type"
							name="contact_type" value="${client.contact_type}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="contact_attention" class="col-sm-3 control-label"> 联系注意问题 </label>
					<div>
						<input type="text" class="form-control" id="contact_attention"
							name="contact_attention" value="${client.contact_attention}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="birth_ages" class="col-sm-3 control-label"> 出生年代 </label>
					<div>
						<input type="text" class="form-control" id="birth_ages"
							name="birth_ages" value="${client.birth_ages}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="age_group" class="col-sm-3 control-label"> 年龄段 </label>
					<div>
						<input type="text" class="form-control" id="age_group"
							name="age_group" value="${client.age_group}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="constellation" class="col-sm-3 control-label"> 星座 </label>
					<div>
						<input type="text" class="form-control" id="constellation"
							name="constellation" value="${client.constellation}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="blood_group" class="col-sm-3 control-label"> 血型 </label>
					<div>
						<input type="text" class="form-control" id="blood_group"
							name="blood_group" value="${client.blood_group}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="temperament_type" class="col-sm-3 control-label"> 性格特点 </label>
					<div>
						<input type="text" class="form-control" id="temperament_type"
							name="temperament_type" value="${client.temperament_type}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="pdp_type" class="col-sm-3 control-label"> PDP类型 </label>
					<div>
						<input type="text" class="form-control" id="pdp_type"
							name="pdp_type" value="${client.pdp_type}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="hobbies" class="col-sm-3 control-label"> 兴趣爱好 </label>
					<div>
						<input type="text" class="form-control" id="hobbies"
							name="hobbies" value="${client.hobbies}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="status" class="col-sm-3 control-label"> 状态 </label>
					<div>
						<input type="text" class="form-control" id="status"
							name="status" value="${client.status}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="interesting_service" class="col-sm-3 control-label"> 关注的服务 </label>
					<div>
						<input type="text" class="form-control" id="interesting_service"
							name="interesting_service" value="${client.interesting_service}" readonly>
					</div>
				</div>
				<div class="form-group ">
					<label for="service_served" class="col-sm-3 control-label"> 已提供的服务 </label>
					<div>
						<input type="text" class="form-control" id="service_served"
							name="service_served" value="${client.service_served}" readonly>
					</div>
				</div>
		</div>
	</div>
</body>
</html>