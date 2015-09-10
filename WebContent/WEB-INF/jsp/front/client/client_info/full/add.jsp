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

	<ul class="breadcrumb">
		<li><a href="<%=request.getContextPath()%>/front/client/list">客户管理</a>
			<span class="divider"></span></li>
		<li class="active">添加用户</li>
	</ul>
	<div id="add_div">

		<input type="hidden" name="_id" />
		<sf:form modelAttribute="client" class="form-horizontal">
			<div class="container-fluid" style="margin-top: 30px">
				<div class="panel panel-info">
					<div class="panel-heading">基本信息</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-6">
								<div class="form-group form-group-sm  ">
									<label for="owner_user_id" class="col-sm-3 control-label">
										归属用户id </label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="owner_user_id"
											name="owner_user_id" value="${clientbaseinfo.owner_user_id}"
											placeholder="">
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-6">
								<div class="row">
									<div class="col-md-12 form-horizontal">
										<div class="form-group form-group-sm  ">
											<label for="client_name" class="col-sm-3 control-label">
												客户姓名 </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="client_name"
													name="client_name" value="${clientbaseinfo.client_name}"
													placeholder="">
											</div>
										</div>
										<div class="form-group form-group-sm  ">
											<label for="birth_date" class="col-sm-3 control-label">
												生日 </label>
											<div class="col-sm-8">
												<input id="birth_date" name="birth_date" placeholder="请输入日期"
													class="laydate-icon form-control dateipt"
													value="${clientbaseinfo.birth_date}" onclick="laydate()">
											</div>
										</div>
										<div class="form-group form-group-sm  ">
											<label for="region_code" class="col-sm-3 control-label">
												地区 </label>
											<div class="col-sm-8">
												<div class="input-group">
													<select id="province" name="province" class="form-control"></select>
												</div>
												<div class="input-group" style="margin-top: 3px;">
													<select id="city" name="city" class="form-control"></select>
												</div>
											</div>
										</div>
									</div>

								</div>
							</div>
							<div class="col-xs-6">
								<div class="row">
									<div class="col-md-12 form-horizontal">
										<div class="form-group form-group-sm  ">
											<label for="sex" class="col-sm-3 control-label">性别 </label>
											<div class="col-sm-8">
												<select id="sex" name="sex" class="form-control"
													data-value="${clientbaseinfo.sex}">
													<option value="1">男</option>
													<option value="0">女</option>
												</select>
											</div>
										</div>
										<div class="form-group form-group-sm  ">
											<label for="id_number" class="col-sm-3 control-label">
												身份证号 </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="id_number"
													name="id_number" value="${clientbaseinfo.id_number}"
													placeholder="">
											</div>
										</div>
										<div class="form-group form-group-sm  ">
											<label for="education_type" class="col-sm-3 control-label">
												教育程度 </label>
											<div class="col-sm-8">
												<select id="education_type" name="education_type"
													class="form-control"
													data-value="${clientbaseinfo.education_type}"></select>
											</div>
										</div>
										<div class="form-group form-group-sm  ">
											<label for="region_type" class="col-sm-3 control-label">
												地区分类 </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="region_type"
													name="region_type" value="${clientbaseinfo.region_type}"
													placeholder="">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-xs-6">
								<div class="row">
									<div class="col-md-12 form-horizontal">
										<div class="form-group form-group-sm  ">
											<label for="phone_info" class="col-xs-3 control-label">
												电话 </label>
											<div class="col-xs-9">
												<div class="row" id="row_phone">
													<div
														class="input-group input-group-xs  online-input col-md-12"
														style="padding-left: 15px;">
														<button type="button" id="add_phone" class="btn btn-default btn-sm">添加</button>
													</div>

													<div
														class="input-group input-group-xs  online-input col-md-12"
														style="padding-left: 15px; margin-top: 8px">
														<span class="input-group-btn" style="width: 80px;">
															<select id="type_phone" name="type_phone"
															class="form-control">
																<option value="1">公司</option>
																<option value="0">个人</option>
																<option value="2">其他</option>
														</select>
														</span> <input type="text" class="form-control"
															style="margin-left: 8px; width: 180px;" /> <span
															class="input-group-btn">
															<button class="btn btn-default btn-sm btn-rm" type="button">删除</button>
														</span>
													</div>

													<div
														class="input-group input-group-xs  online-input col-md-12"
														style="padding-left: 15px; margin-top: 8px">
														<span class="input-group-btn" style="width: 80px;">
															<select id="type_phone" name="type_phone"
															class="form-control">
																<option value="1">公司</option>
																<option value="0">个人</option>
																<option value="2">其他</option>
														</select>
														</span> <input type="text" class="form-control"
															style="margin-left: 8px; width: 180px;" /> <span
															class="input-group-btn">
															<button class="btn btn-default btn-sm btn-rm" type="button">删除</button>
														</span>
													</div>
												</div>
											</div>
										</div>

										<div class="form-group form-group-sm  ">
											<label for="email_info" class="col-xs-3 control-label">
												邮箱 </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="email_info"
													name="email_info" value="${clientbaseinfo.email_info}"
													placeholder="">
											</div>
										</div>
										<div class="form-group form-group-sm  ">
											<label for="address_info" class="col-xs-3 control-label">
												地址 </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="address_info"
													name="address_info" value="${clientbaseinfo.address_info}"
													placeholder="">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<hr />

					<div class="col-sm-12">
						<button type="button" id="btn_save"
							class="btn btn-primary btn-lg center-block">提交</button>
					</div>
				</div>
				<div class="form-group ">
					<label for="owner_user_id" class="col-sm-2 control-label">
						归属用户id </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="owner_user_id"
							name="owner_user_id" placeholder="" value="${owner_user_id }">
					</div>
				</div>
				<div class="form-group ">
					<label for="client_name" class="col-sm-2 control-label"> 姓名
					</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="client_name"
							name="client_name" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="sex" class="col-sm-2 control-label"> 性别 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="sex" name="sex"
							placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="id_number" class="col-sm-2 control-label"> 身份证号
					</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="id_number"
							name="id_number" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="birth_date" class="col-sm-2 control-label"> 生日
					</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="birth_date"
							name="birth_date" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="age" class="col-sm-2 control-label"> 年龄 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="age" name="age"
							placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="address_info" class="col-sm-2 control-label">
						客户的地址信息 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="address_info"
							name="address_info" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="phone_info" class="col-sm-2 control-label">
						客户的电话信息 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="phone_info"
							name="phone_info" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="email_info" class="col-sm-2 control-label">
						客户的邮箱信息 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="email_info"
							name="email_info" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="region_code" class="col-sm-2 control-label">
						地区码 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="region_code"
							name="region_code" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="region_name" class="col-sm-2 control-label">
						地区名 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="region_name"
							name="region_name" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="region_type" class="col-sm-2 control-label">
						地区分类 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="region_type"
							name="region_type" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="education_type" class="col-sm-2 control-label">
						教育程度分类 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="education_type"
							name="education_type" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="name_card_id" class="col-sm-2 control-label">
						名片id </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="name_card_id"
							name="name_card_id" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="company" class="col-sm-2 control-label"> 工作单位 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="company"
							name="company" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="company_nature" class="col-sm-2 control-label">
						企业性质 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="company_nature"
							name="company_nature" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="trade_type" class="col-sm-2 control-label">
						行业类型 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="trade_type"
							name="trade_type" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="career_type" class="col-sm-2 control-label">
						职业类型 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="career_type"
							name="career_type" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="job_position" class="col-sm-2 control-label">
						职位 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="job_position"
							name="job_position" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="job_level" class="col-sm-2 control-label"> 职级 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="job_level"
							name="job_level" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="marital_status" class="col-sm-2 control-label">
						婚姻状况 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="marital_status"
							name="marital_status" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="wedding_date" class="col-sm-2 control-label">
						结婚日期 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="wedding_date"
							name="wedding_date" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="boy_num" class="col-sm-2 control-label"> 男孩数 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="boy_num"
							name="boy_num" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="girl_num" class="col-sm-2 control-label"> 女孩数 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="girl_num"
							name="girl_num" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="children_num" class="col-sm-2 control-label">
						子女数 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="children_num"
							name="children_num" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="annual_income_personal" class="col-sm-2 control-label">
						个人年收入 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control"
							id="annual_income_personal" name="annual_income_personal"
							placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="annual_income_personal_type"
						class="col-sm-2 control-label"> （个人）年收入分类码 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control"
							id="annual_income_personal_type"
							name="annual_income_personal_type" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="annual_income_family" class="col-sm-2 control-label">
						（家庭）年收入 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="annual_income_family"
							name="annual_income_family" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="annual_income_family_type"
						class="col-sm-2 control-label"> （家庭）年收入分类码 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control"
							id="annual_income_family_type" name="annual_income_family_type"
							placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="family_income_feature" class="col-sm-2 control-label">
						家庭收入特点 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="family_income_feature"
							name="family_income_feature" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="family_financial_standing"
						class="col-sm-2 control-label"> 财务状况码 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control"
							id="family_financial_standing" name="family_financial_standing"
							placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="source_type" class="col-sm-2 control-label">
						客户来源码 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="source_type"
							name="source_type" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="introducer_name" class="col-sm-2 control-label">
						介绍人 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="introducer_name"
							name="introducer_name" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="introducer_relationship" class="col-sm-2 control-label">
						与介绍人关系 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control"
							id="introducer_relationship" name="introducer_relationship"
							placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="introducer_closeness" class="col-sm-2 control-label">
						与介绍人亲密度 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="introducer_closeness"
							name="introducer_closeness" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="introducer_evaluation" class="col-sm-2 control-label">
						介绍人评价 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="introducer_evaluation"
							name="introducer_evaluation" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="contact_type" class="col-sm-2 control-label">
						可接触度 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="contact_type"
							name="contact_type" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="contact_attention" class="col-sm-2 control-label">
						联系注意问题 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="contact_attention"
							name="contact_attention" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="birth_ages" class="col-sm-2 control-label">
						出生年代 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="birth_ages"
							name="birth_ages" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="age_group" class="col-sm-2 control-label"> 年龄段
					</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="age_group"
							name="age_group" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="constellation" class="col-sm-2 control-label">
						星座 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="constellation"
							name="constellation" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="blood_group" class="col-sm-2 control-label"> 血型
					</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="blood_group"
							name="blood_group" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="temperament_type" class="col-sm-2 control-label">
						性格特点 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="temperament_type"
							name="temperament_type" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="pdp_type" class="col-sm-2 control-label"> PDP类型
					</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="pdp_type"
							name="pdp_type" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="hobbies" class="col-sm-2 control-label"> 兴趣爱好 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="hobbies"
							name="hobbies" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="status" class="col-sm-2 control-label"> 状态 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="status" name="status"
							placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="interesting_service" class="col-sm-2 control-label">
						关注的服务 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="interesting_service"
							name="interesting_service" placeholder="">
					</div>
				</div>
				<div class="form-group ">
					<label for="service_served" class="col-sm-2 control-label">
						已提供的服务 </label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="service_served"
							name="service_served" placeholder="">
					</div>
				</div>

				<hr />
				<div class="col-sm-7">
					<button type="button" id="btn_save"
						class="btn btn-primary btn-lg center-block">提交</button>
				</div>
		</sf:form>
	</div>

	<script>
    $().ready(function() {
    	
        $("#btn_save").bind("click", save);
        
        document.onkeydown = function(event) {
    		if (event.keyCode == 13) {
    			return false;
    		}
    	}
        
        $("#add_phone").click(function(){
        	addPhone();
        })
        
        registRemovePhone();
    });
    
    var addPhone=function(){
		var toAdd =   '<div                                                                              '
					+ '			class="input-group input-group-xs  online-input col-md-12"                   '
					+ '			style="padding-left: 15px; margin-top: 8px">                                 '
					+ '			<span class="input-group-btn" style="width: 80px;">                          '
					+ '				<select id="type_phone" name="type_phone"                                  '
					+ '				class="form-control">                                                      '
					+ '					<option value="1">公司</option>                                          '
					+ '					<option value="0">个人</option>                                          '
					+ '					<option value="2">其他</option>                                          '
					+ '			</select>                                                                    '
					+ '			</span> <input type="text" class="form-control"                              '
					+ '				style="margin-left: 8px; width: 180px;" />                                 '
					+ '			<span                                                                        '
					+ '				class="input-group-btn">                                                   '
					+ '				<button class="btn btn-default btn-sm btn-rm"  type="button">删除</button>        '
					+ '			</span>                                                                      '
					+ '</div>                                                                            ';
					
		$("#row_phone").append(toAdd);
		
		registRemovePhone();
    }
    
    function registRemovePhone(){
    	$(".btn-rm",$("#row_phone")).click(function(){
			$(this).parent().parent().remove();
			
		});
    }
    
    

    //保存
    var save = function() {

        // 控制按钮为禁用
        $.disableButton("btn_save");

        var paramForm = $('form').getFormParam_ux();

        var successstr = "新增成功";

        var url_to = "<%=request.getContextPath()%>/front/client/add";
        var url_success = "<%=request.getContextPath()%>/front/client/list";

			$.ajax({
				type : 'POST',
				url : url_to,
				data : $.extend({
					ts : new Date().getTime()
				}, paramForm),
				type : 'POST',
				dataType : 'json',
				success : function(data) {

					if (data['success'] == 'n') {
						if (data['brErrors']) {
							$.showBRErrors_mou_abs(data['brErrors'], $("#add_div"));
						} else {
							$.alertError(data['message']);
						}
					} else {
						$.alertSuccessNewPage("成功", successstr, url_success);
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					$.enableButton("btn_save");
				}
			});
		};
	</script>
</body>
</html>