<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<input type="hidden" id="owner_user_id" name ="owner_user_id" value="${userid}" />
<div class="panel panel-info">
	<div class="panel-heading">基本信息</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-xs-6">
				<div class="row">
					<div class="col-md-12 form-horizontal">
						<div class="form-group form-group-sm  ">
							<label for="client_name" class="col-sm-3 control-label">
								客户姓名 </label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="client_name"
									name="client_name" value="${client.client_name}"
									placeholder="">
							</div>
						</div>
						<div class="form-group form-group-sm  ">
							<label for="birth_date" class="col-sm-3 control-label">
								生日 </label>
							<div class="col-sm-8">
								<input id="birth_date" name="birth_date" placeholder="请输入日期"
									class="laydate-icon form-control dateipt"
									value="${client.birth_date}">
							</div>
						</div>
						<div class="form-group ">
							<label for="age" class="col-sm-3 control-label"> 年龄 </label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="age" name="age"
									value="${client.age}">
							</div>
						</div>
						<div id="region" class="form-group form-group-sm regin-container">
							<label class="col-sm-3 control-label"> 地区 </label>
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
									data-value="${client.sex}">
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
									name="id_number" value="${client.id_number}"
									placeholder="">
							</div>
						</div>
						<div class="form-group form-group-sm  ">
							<label for="email_info" class="col-xs-3 control-label">
								邮箱 </label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="email_info"
									name="email_info" value="${client.email_info}"
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
									data-value="${client.education_type}"></select>
							</div>

						</div>
						<div class="form-group form-group-sm  ">
							<label for="region_type" class="col-sm-3 control-label">
								地区分类 </label>
							<div class="col-sm-8">
								<select id="region_type" name="region_type" class="form-control"
									data-src="constant" data-typecode="REGION_TYPE"
									data-value="${client.region_type}"></select>
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
								<textarea type="text" class="form-control multiselectpanel"
									id="interesting_service_ipt" name="interesting_service_ipt"
									data-name="interesting_service"
									data-typecode="INTERESTING_SERVICE" placeholder="" readonly
									style="height: 50px"></textarea>
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

		var order = $(".one_box", $("#phone_info")).length + 1;
		//console.log(size);

		var p = $.extend({ // apply default properties
			ipt_w : '180px', // 输入框的宽度
			type : '0',
			ipt_val : '',
			margin_l_button_w : '15px' // 按钮至输入框的边距
		}, config);

		var toAdd = '<div   data-order= "#ORDER#"                                                                            '
				+ '			class="input-group input-group-xs  online-input col-md-12 one_box"                   '
				+ '			style="padding-left: 15px; margin-top: 8px; width: 450px">                                 '
				+ '			<span class="input-group-btn" >                          '
				+ '				<select id="type_phone" name="type_phone"                                  '
				+ '				class="form-control" style="width: 80px;">                                                      '
				+ '					<option value="1">公司</option>                                          '
				+ '					<option value="0">个人</option>                                          '
				+ '					<option value="2">其他</option>                                          '
				+ '			</select>                                                                    '
				+ '			</span> <input type="text" class="form-control"                              '
				+ '				style="margin-left: 8px; width: #IPT_W#" value="#IPT_VAL#"/>                                 '
				+ '			<span                                                                        '
				+ '				class="pull-right">                                                   '
				+ '				<button class="btn btn-danger btn-sm btn-rm-box"  type="button" style="margin-left: #MARGIN_L_BUTTON_W#">删除</button>        '
				+ '			</span>                                                                      '
				+ '</div>                                                                            ';

		toAdd = toAdd.replace("#IPT_W#", p.ipt_w);
		toAdd = toAdd.replace("#MARGIN_L_BUTTON_W#", p.margin_l_button_w);
		toAdd = toAdd.replace("#IPT_VAL#", p.ipt_val);
		toAdd = toAdd.replace("#ORDER#", order);

		$("#phone_info").append(toAdd);

		registRemoveOne();

		if (p.type) {

			//console.log(JSON.stringify(p));
			$("select[name=type_phone]", $($("div[data-order=" + order + "]"), "#phone_info")).setSelectedValue(p.type);
		}
	}

	var addAddress = function(config) {

		//console.log(JSON.stringify(config));

		var order = $(".one_box", $("#address_info")).length + 1;

		var p = $.extend({ // apply default properties
			div_w : '580px',// div的宽度
			type : '0',
			province : -1,
			city : -1,
			district : -1,
			detail_address : ''
		}, config);

		//console.log("p---" + JSON.stringify(p));

		var toAdd = '<div data-order= "#ORDER#" class="input-group input-group-xs  online-input col-md-12 one_box regin-container"                           '
			    +'	style="padding-left: 15px; margin-top: 8px; width:#DIV_W#">                                       '
				+ '	<div class="row" >                                                                              ' + '		<div class="col-xs-4 control-label">                                                          '
				+ '			<span class="input-group-btn">                                                              '
				+ '				<select id="type_address"                                                                 '
				+'					name="type_address" class="form-control" style="width: 80px;">                          '
				+ '						<option value="1">公司</option>                                                       ' + '						<option value="0">个人</option>                                                       '
				+ '						<option value="2">其他</option>                                                       ' + '				</select>                                                                                 '
				+ '			</span>                                                                                     ' + '		</div>                                                                                        '
				+ '		<div class="col-xs-5 control-label pull-right">                                               ' + '			<span>                                                                  '
				+ '				<button class="btn btn-danger btn-sm btn-rm-box " type="button"                          '
				+'					style="margin-left: 15px;">删除</button>                                                '
				+ '			</span>                                                                                     ' + '		</div>                                                                                        '
				+ '	</div>                                                                                          ' + '	<div class="row" >                                                                              '
				+ '		<span class="input-group input-group-btn col-xs-3 control-label" style="padding-left:15px">   ' + '			<select id="province" name="province" class="form-control" ></select>                       '
				+ '		</span>                                                                                       ' + '		<span class="input-group input-group-btn col-xs-3 control-label" style="padding-left:8px">    '
				+ '			<select id="city" name="city" class="form-control" ></select>                               ' + '		</span>                                                                                       '
				+ '		<span class="input-group input-group-btn col-xs-3 control-label" style="padding-left:8px">    ' + '			<select id="district" name="district" class="form-control" ></select>                             '
				+ '		</span>                                                                                       ' + '	</div>                                                                                          '
				+ '	<div class="row" >                                                                              ' + '		<span class="col-sm-12 control-label" style="padding-left:15px ;padding-right: 0px">          '
				+ '			<input type="text" class="form-control" id="detail_address" name="detail_address"           '
				+'				placeholder="请输入详细地址">                                                             '
				+ '		</span>                                                                                       ' + '	</div>                                                                                          '
				+ '</div>                                                                                            ';

		toAdd = toAdd.replace("#DIV_W#", p.div_w);
		toAdd = toAdd.replace("#ORDER#", order);

		var _thisNew = $(toAdd);
		_thisNew.appendTo($("#address_info"));
		//$("#address_info").append(toAdd);

		//console.log('p.province.length-' + p.province);
		if (p.province != -1) {

			//console.log("pppppp----" + JSON.stringify(p));
			var context = $($("div[data-order=" + order + "]"), "#address_info");
			$("select[name=type_address]", context).setSelectedValue(p.type);

			$.iniRegion(_thisNew, p);
		} else {
			$.iniRegion(_thisNew);
		}

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
			var type_name = $("#type_phone", $(this)).getSelectedText();
			var value_phone = $("input", $(this)).val().trim();

			if (value_phone == "") {
				return;
			}

			var phone_this = {
				"type_value" : type_phone,
				"type_name" : type_name,
				"phone_number" : value_phone
			}

			phone_info.push(phone_this);
		});

		return {
			"phone_info" : JSON.stringify(phone_info)
		};
	}

	function getReginInfo() {

		var regin_div = $("#region");

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

		if (province == "-1") {
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
			var type_name = $("#type_address", $(this)).getSelectedText();

			var province = $("#province", $(this)).val();
			var city = $("#city", $(this)).val();
			var district = $("#district", $(this)).val();

			var detail_address = $("#detail_address", $(this)).val().trim();

			if (province == "-1") {
				return;
			}

			var address_this = {
				"type_value" : type_address,
				"type_name" : type_name,
				"province" : province,
				"city" : city,
				"district" : district,
				"detail_address" : detail_address
			}

			address_info.push(address_this);
		});

		return {
			"address_info" : JSON.stringify(address_info)
		};
	}

	// 初始化电话信息
	function iniPhone() {

		var data_phone = eval('${client.phone_info }');
		if (data_phone && data_phone.length > 0) {
			for ( var item in data_phone) {
				var phone_temp = data_phone[item];

				var type = phone_temp["type_value"];
				var value = phone_temp["phone_number"];

				addPhone({
					type : type,
					ipt_val : value
				});
			}
		} else {
			addPhone();
		}

		$("#add_phone").click(function() {
			addPhone();
		})
	}

	// 初始化地址信息
	function iniAddress() {

		var address_info = eval('${client.address_info }');
		//console.log(JSON.stringify(address_info))
		if (address_info && address_info.length > 0) {
			for ( var item in address_info) {
				var address_temp = address_info[item];

				var type = address_temp["type"];
				var province = address_temp["province"];
				var city = address_temp["city"];
				var district = address_temp["district"];
				var detail_address = address_temp["detail_address"];

				addAddress({
					type : type,
					province : province,
					city : city,
					district : district,
					detail_address : detail_address
				});
			}
		} else {
			addAddress();
		}

		$("#add_address").click(function() {
			addAddress();
		})
	}

	// 初始化所属地区
	function iniRegion() {

		var region_code = '${client.region_code }';
		//$.logJson(region_code);

		if (region_code != '') {
			var codes = region_code.split("-");

			var province = codes[0];
			var city = codes[1];
			var district = codes[2];

			$.iniRegion($("#region"), {
				province : province,
				city : city,
				district : district
			});
		}
	}

	// 页面初始化感兴趣的服务
	function iniInteresting_service() {
		var interesting_service = eval('${interesting_service}');

		//$.logJson("interesting_service--["+ $.toJsonStr(interesting_service) +"]")

		$("#interesting_service_ipt").initVal(interesting_service);
	}

	// 获取感兴趣的服务
	function getIntrestedService() {
		var selected = $("#interesting_service_ipt").getSelected();

		$.logJson(selected, "感兴趣的服务");

		return {
			"interesting_service" : $.toJsonStr(selected)
		};
	}

	function getValues() {

		var rtnValue = {};

		var phone_info = getPhoneInfo();
		var address_info = getAddressInfo();
		var region_info = getReginInfo();
		var interesting_service = getIntrestedService();

		rtnValue = $.extend(rtnValue, phone_info);
		rtnValue = $.extend(rtnValue, address_info);
		rtnValue = $.extend(rtnValue, region_info);
		rtnValue = $.extend(rtnValue, interesting_service);

		$.logJson(rtnValue, "复合对象的值");

		return rtnValue;
	}

	$().ready(function() {

		$("#birth_date").click(function() {
			laydate({
				elem : '#birth_date',
				format : 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
				festival : false, //显示节日
				choose : function(datas) { //选择日期完毕的回调

					var birth_date = datas;

					//  var birth_date = $(this).val();

					if (birth_date != '') {
						var age = $.calAge(birth_date);

						//$.logJson(age);
						$("#age").val(age);
					}
				}
			});
		});

		$("#birth_date").blur(function() {
			var birth_date = $(this).val();

			if (birth_date != '') {
				var age = $.calAge(birth_date);
				//$.logJson(age);
				$("#age").val(age);
			}
		});

		registRemoveOne();

		// 放在address前面，否则会覆盖iniaddress的数据
		$.iniRegions();

		iniPhone();
		iniAddress();
		iniRegion();
		iniInteresting_service();
	});
</script>
