<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="panel panel-info">
	<div class="panel-heading">基本信息<span class="glyphicon glyphicon-chevron-down pull-right spncollapse"></span></div>
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
						<div class="form-group ">
							<label for="age" class="col-sm-3 control-label"> 年龄 </label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="age" name="age"
									placeholder="">
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
							<label for="email_info" class="col-xs-3 control-label">
								邮箱 </label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="email_info"
									name="email_info" value="${clientbaseinfo.email_info}"
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
							<label for="interesting_service" class="col-xs-3 control-label">
								关注的服务 </label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="interesting_service"
									name="interesting_service" value="${clientbaseinfo.interesting_service}"
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
								关注的服务 </label>
							<div class="col-xs-9">
								<div class="row" id="phone_info">
									<div class="input-group input-group-xs  online-input col-md-12"
										style="padding-left: 15px;">
										<button type="button" id="add_phone"
											class="btn btn-default btn-sm">添加</button>
									</div>

									<div class="input-group input-group-xs  online-input col-md-12"
										style="padding-left: 15px; margin-top: 8px">
										<span class="input-group-btn"> <select id="type_phone"
											name="type_phone" class="form-control" style="width: 80px;">
												<option value="1">公司</option>
												<option value="0">个人</option>
												<option value="2">其他</option>
										</select>
										</span> <input type="text" class="form-control"
											style="margin-left: 8px; width: 180px;" /> <span
											class="input-group-btn">
											<button class="btn btn-default btn-sm btn-rm" type="button"
												style="margin-left: 15px;">删除</button>
										</span>
									</div>
								</div>
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
</div>

<script>

	var addPhone = function(config) {

		var p = $.extend({ // apply default properties
			ipt_w : '180px', // 输入框的宽度
			margin_l_button_w : '15px' // 按钮至输入框的边距
		}, config);

		var toAdd = '<div                                                                              '
				+ '			class="input-group input-group-xs  online-input col-md-12"                   '
				+ '			style="padding-left: 15px; margin-top: 8px">                                 '
				+ '			<span class="input-group-btn" >                          ' + '				<select id="type_phone" name="type_phone"                                  '
				+ '				class="form-control" style="width: 80px;">                                                      '
				+ '					<option value="1">公司</option>                                          ' + '					<option value="0">个人</option>                                          ' + '					<option value="2">其他</option>                                          '
				+ '			</select>                                                                    ' + '			</span> <input type="text" class="form-control"                              '
				+ '				style="margin-left: 8px; width: #IPT_W#" />                                 '
				+ '			<span                                                                        '
				+ '				class="input-group-btn">                                                   '
				+ '				<button class="btn btn-default btn-sm btn-rm"  type="button" style="margin-left: #MARGIN_L_BUTTON_W#">删除</button>        ' + '			</span>                                                                      '
				+ '</div>                                                                            ';

		toAdd = toAdd.replace("#IPT_W#", p.ipt_w);
		toAdd = toAdd.replace("#MARGIN_L_BUTTON_W#", p.margin_l_button_w);

		$("#phone_info").append(toAdd);

		registRemovePhone();
	}

	function registRemovePhone() {
		$(".btn-rm", $("#phone_info")).click(function() {
			$(this).parent().parent().remove();

		});
	}
</script>
