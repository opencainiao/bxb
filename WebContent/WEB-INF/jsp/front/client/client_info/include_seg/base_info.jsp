<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

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
							name="owner_user_id" value="${owner_user_id}"
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
						<div id="regin" class="form-group form-group-sm regin-container">
							<label  class="col-sm-3 control-label"> 地区 </label>
							<div class="row col-sm-8" style="padding-right: 0px;">
								<span class="input-group input-group-btn col-xs-3 control-label"
									style="padding-left: 15px"> <select id="province"
									name="province" class="form-control"></select>
								</span> <span
									class="input-group input-group-btn col-xs-3 control-label"
									style="padding-left: 8px"> <select id="city" name="city"
									class="form-control"></select>
								</span> <span
									class="input-group input-group-btn col-xs-3 control-label"
									style="padding-left: 8px"> <select id="district"
									name="district" class="form-control"></select>
								</span>
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
									class="form-control" data-src="constant"
									data-typecode="EDUCATION_TYPE"
									data-value="${clientbaseinfo.education_type}"></select>
							</div>

						</div>
						<div class="form-group form-group-sm  ">
							<label for="region_type" class="col-sm-3 control-label">
								地区分类 </label>
							<div class="col-sm-8">
								<select id="region_type" name="region_type" class="form-control"
									data-src="constant" data-typecode="REGION_TYPE"
									data-value="${clientbaseinfo.region_type}"></select>
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
							<label for="interesting_service_ipt"
								class="col-xs-3 control-label"> 关注的服务 </label>
							<div class="col-sm-8">
								<textarea  type="text" class="form-control multiselectpanel"
									id="interesting_service_ipt" name="interesting_service_ipt" 
									data-name="interesting_service"
									data-typecode = "INTERESTING_SERVICE"
									placeholder="" readonly
									style="height:50px"
									></textarea>
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
								<div class="row" id="phone_info">
									<div class="input-group input-group-xs  online-input col-md-12"
										style="padding-left: 15px;">
										<button type="button" id="add_phone"
											class="btn btn-info btn-sm">添加</button>
									</div>

									<div
										class="input-group input-group-xs  online-input col-md-12 one_box"
										style="padding-left: 15px; margin-top: 8px">
										<span class="input-group-btn"> <select id="type_phone"
											name="type_phone" class="form-control" style="width: 80px;">
												<option value="1">公司</option>
												<option value="0">个人</option>
												<option value="2">其他</option>
										</select>
										</span> <input type="text" class="form-control"
											style="margin-left: 8px; width: 180px;" /> <span
											class="pull-right">
											<button class="btn btn-danger btn-sm btn-rm-box"
												type="button" style="margin-left: 15px;">删除</button>
										</span>
									</div>
								</div>
							</div>
						</div>


						<div class="form-group form-group-sm  ">
							<label for="address_info" class="col-xs-3 control-label">
								地址 </label>
							<div class="col-xs-9">
								<div class="row" id="address_info">
									<div class="input-group input-group-xs  online-input col-md-12"
										style="padding-left: 15px;">
										<button type="button" id="add_address"
											class="btn btn-info btn-sm">添加</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	
</script>
<script>
	var addPhone = function(config) {

		var p = $.extend({ // apply default properties
			ipt_w : '180px', // 输入框的宽度
			margin_l_button_w : '15px' // 按钮至输入框的边距
		}, config);

		var toAdd = '<div                                                                              '
				+ '			class="input-group input-group-xs  online-input col-md-12 one_box"                   '
				+ '			style="padding-left: 15px; margin-top: 8px">                                 '
				+ '			<span class="input-group-btn" >                          '
				+ '				<select id="type_phone" name="type_phone"                                  '
				+ '				class="form-control" style="width: 80px;">                                                      '
				+ '					<option value="1">公司</option>                                          '
				+ '					<option value="0">个人</option>                                          '
				+ '					<option value="2">其他</option>                                          '
				+ '			</select>                                                                    '
				+ '			</span> <input type="text" class="form-control"                              '
				+ '				style="margin-left: 8px; width: #IPT_W#" />                                 '
				+ '			<span                                                                        '
				+ '				class="pull-right">                                                   '
				+ '				<button class="btn btn-danger btn-sm btn-rm-box"  type="button" style="margin-left: #MARGIN_L_BUTTON_W#">删除</button>        '
				+ '			</span>                                                                      '
				+ '</div>                                                                            ';

		toAdd = toAdd.replace("#IPT_W#", p.ipt_w);
		toAdd = toAdd.replace("#MARGIN_L_BUTTON_W#", p.margin_l_button_w);

		$("#phone_info").append(toAdd);

		registRemoveOne();
	}

	var addAddress = function(config) {

		var p = $.extend({ // apply default properties
			div_w : '580px' // div的宽度
		}, config);

		var toAdd = '<div class="input-group input-group-xs  online-input col-md-12 one_box regin-container"                           '
			+'	style="padding-left: 15px; margin-top: 8px; width:#DIV_W#">                                       '
				+ '	<div class="row" >                                                                              '
				+ '		<div class="col-xs-4 control-label">                                                          '
				+ '			<span class="input-group-btn">                                                              '
				+ '				<select id="type_address"                                                                 '
				+'					name="type_address" class="form-control" style="width: 80px;">                          '
				+ '						<option value="1">公司</option>                                                       '
				+ '						<option value="0">个人</option>                                                       '
				+ '						<option value="2">其他</option>                                                       '
				+ '				</select>                                                                                 '
				+ '			</span>                                                                                     '
				+ '		</div>                                                                                        '
				+ '		<div class="col-xs-5 control-label pull-right">                                               '
				+ '			<span>                                                                  '
				+ '				<button class="btn btn-danger btn-sm btn-rm-box " type="button"                          '
				+'					style="margin-left: 15px;">删除</button>                                                '
				+ '			</span>                                                                                     '
				+ '		</div>                                                                                        '
				+ '	</div>                                                                                          '
				+ '	<div class="row" >                                                                              '
				+ '		<span class="input-group input-group-btn col-xs-3 control-label" style="padding-left:15px">   '
				+ '			<select id="province" name="province" class="form-control" ></select>                       '
				+ '		</span>                                                                                       '
				+ '		<span class="input-group input-group-btn col-xs-3 control-label" style="padding-left:8px">    '
				+ '			<select id="city" name="city" class="form-control" ></select>                               '
				+ '		</span>                                                                                       '
				+ '		<span class="input-group input-group-btn col-xs-3 control-label" style="padding-left:8px">    '
				+ '			<select id="district" name="district" class="form-control" ></select>                             '
				+ '		</span>                                                                                       '
				+ '	</div>                                                                                          '
				+ '	<div class="row" >                                                                              '
				+ '		<span class="col-sm-12 control-label" style="padding-left:15px ;padding-right: 0px">          '
				+ '			<input type="text" class="form-control" id="detail_address" name="detail_address"           '
				+'				placeholder="请输入详细地址">                                                             '
				+ '		</span>                                                                                       '
				+ '	</div>                                                                                          '
				+ '</div>                                                                                            ';

		toAdd = toAdd.replace("#DIV_W#", p.div_w);

		var _thisNew = $(toAdd);
		_thisNew.appendTo($("#address_info"));
		//$("#address_info").append(toAdd);

		$.iniRegion(_thisNew);

		registRemoveOne();
	}

	function registRemoveOne() {
		$(".btn-rm-box").click(function() {
			$(this).css("border-radius", "3px!important");
			$(this).closest('div.one_box').remove();
		});
	}

	function getPhoneInfo() {

		var phone_info = [];
		var phone_div = $("#phone_info");

		$(".one_box", phone_div).each(function() {

			var type_phone = $("#type_phone", $(this)).val();
			var value_phone = $("input", $(this)).val().trim();

			if (value_phone == "") {
				return;
			}

			var phone_this = {
				"type_value" : type_phone,
				"phone_number" : value_phone
			}

			phone_info.push(phone_this);
		});

		//$.alertObjJson(phone_info);
		return {
			"phone_info" :  JSON.stringify(phone_info) 
		};
	}
	
	function getReginInfo(){

		var regin_div = $("#regin");

		var code = [];
		var name = [];
		
		var province = $("#province", regin_div).val();
		var city = $("#city", regin_div).val();
		var district = $("#district", regin_div).val();
		var province_name = $("#province", regin_div).getSelectedText();
		var city1_name = $("#city", regin_div).getSelectedText();
		var city2_name = $("#district", regin_div).getSelectedText();
		
		code.push(province);
		code.push(city);
		code.push(district);
		
		name.push(province_name);
		name.push(city1_name);
		name.push(city2_name);
		
		if (province == "-1"){
			return null;
		}
		
		return {
			"region_code" : code.join("-"),
			"region_name" : name.join(" ")
		};
	}
	
	function getAddressInfo() {

		var address_info = [];
		var address_div = $("#address_info");

		$(".one_box", address_div).each(function() {

			var type_address = $("#type_address", $(this)).val();
			
			var province = $("#province", $(this)).val();
			var city = $("#city", $(this)).val();
			var district = $("#district", $(this)).val();
			
			var detail_address = $("#detail_address", $(this)).val().trim();

			if (province == "-1"){
				return;
			}
			
			var address_this = {
				"type_value":type_address,
				"province":province,
				"city":city,
				"district":district,
				"detail_address" : detail_address
			}

			address_info.push(address_this);
		});

		//$.alertObjJson(address_info);
		return {
			"address_info" : JSON.stringify(address_info)  
		};
	}

	$().ready(function() {
		registRemoveOne();

		addAddress();
		$("#add_address").click(function() {
			addAddress();
		})

		addPhone();
		$("#add_phone").click(function() {
			addPhone();
		})

		$.iniRegions();
		//iniProvince();
	});
</script>
