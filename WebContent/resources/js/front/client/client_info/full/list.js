$().ready(function() {

	data_manage.init();

	$("#btn_add").click(function() {
		var url_to = $.getSitePath() + '/front/client/add';

		window.location.href = url_to;
	});

	$("#btn_search").click(function() {
		data_manage.search();
	});
});

var data_manage_functions = {

	/***************************************************************************
	 * 删除
	 * 
	 * @param data
	 */
	delOne : function(data) {

		var url_to = $.getSitePath() + '/front/client/' + data["_id_m"] + '/delete';

		$.ajax({
			type : 'POST',
			url : url_to,
			data : $.extend({
				ts : new Date().getTime()
			}, data),
			dataType : 'json',
			success : function(data) {
				data_manage.gridsetting.params = [ {
					name : 'reload',
					value : true
				} ];
				$("#list").flexReload(data_manage.gridsetting);
			}
		});
	},
	/***************************************************************************
	 * 进入修改页面
	 * 
	 * @param data
	 */
	toEdit : function(data) {

		var url = $.getSitePath() + '/front/client/' + data["_id_m"] + "/update";

		$.popUpWindow("编辑用户信息", url, "800px", "400px", "edit", $("#data_manage"));
	},
	/***************************************************************************
	 * 进入详细信息页面
	 * 
	 * @param data
	 * @returns {Boolean}
	 */
	toDetail : function(data) {

		var url = $.getSitePath() + '/front/client/' + data["_id_m"];

		$.loadPage(url);

		// $.showDetailWindow("用户信息", url, "600px", "300px");
	},
	/***************************************************************************
	 * 关闭编辑窗口
	 */
	closeEditWindow : function() {
		$.closeWindow("edit", $("#data_manage"));
	},
	refreshPage : function() {
		data_manage.search();
	}
};

var data_manage = {

	search : function() {
		var searchcondition = {};
		searchcondition["name"] = "search_condition";
		searchcondition["value"] = $("#search_condition").val().trim();

		var params = [];
		params.push(searchcondition);

		data_manage.gridsetting.url = $.getSitePath() + '/front/client/list?ts=' + new Date().getTime();

		if ($("#data_manage").attr("s_times")) {
			params.push({
				name : 'reload',
				value : true
			});
			data_manage.gridsetting.params = params;

			$("#list").flexReload(data_manage.gridsetting);
		} else {
			data_manage.gridsetting.params = params;
			$("#list").flexigrid(data_manage.gridsetting);
		}

		$("#data_manage").attr("s_times", 1);
	},
	/***************************************************************************
	 * 页面加载后的初始化方法
	 */
	init : function() {

		data_manage.gridsetting.url = $.getSitePath() + '/front/client/list';
		// alert(data_manage.gridsetting.url);
		$("#list").flexigrid(data_manage.gridsetting);

		data_manage.pageLayout();

		$("#data_manage").attr("s_times", 1);
	},
	pageLayout : function() {

		var inPage_h = $(parent.window).height();
		var nav_h = 160;
		var btnbar_h = 40;
		var table_h = mou_grid_ux.getTableH($("#content_inner_page"), inPage_h - nav_h - btnbar_h - 2 - 8);
		mou_grid_ux.resetHeight_grid($("#content_inner_page"), table_h);
	},
	/***************************************************************************
	 * 表格配置
	 */
	gridsetting : {
		showTableToggleBtn : true,
		resizable : false,
		url : '',
		method : 'POST',
		dataType : 'json',
		nowrap : true, // 是否不换行
		autoload : true,// 自动加载
		usepager : true,
		title : '客户列表',
		useRp : true,
		rp : 20, // 默认分页条数
		pagestat : '共有{total}条记录，显示{from} - {to}条记录',
		procmsg : '正在查询，请稍候 ...',
		nomsg : '没有符合条件的数据',
		colModel : [ {
			display : '选择',
			hide : true,
			m_type : 'radio1',
			align : 'center',
			select : [ "_id_m", "name" ]
		}, {
			display : '序号',
			m_type : 'sno',
			align : 'center'
		}, {
			display : '逻辑主键',
			name : '_id_m',
			hide : true
		}, {
			display : '归属用户id',
			name : 'owner_user_id',
			width : 120,
			hide : true
		}, {
			display : '姓名',
			name : 'client_name',
			width : 110,
			m_type : 'link',
			select : [ "_id_m" ],
			hide : false,
			callback : data_manage_functions.toDetail
		}, {
			display : '性别',
			name : 'sex',
			width : 40,
			condition : {
				'0' : '女',
				'1' : '男'
			}
		}, {
			display : '身份证号',
			name : 'id_number',
			width : 130
		}, {
			display : '生日',
			name : 'birth_date',
			width : 70
		}, {
			display : '年龄',
			name : 'age',
			width : 40
		}, {
			display : '地址信息',
			name : 'address_info_name',
			width : 160
		}, {
			display : '电话信息',
			name : 'phone_info_name',
			width : 120
		}, {
			display : '邮箱',
			name : 'email_info',
			width : 120
		}, {
			display : '地区码',
			name : 'region_code',
			width : 120,
			hide:true
		}, {
			display : '地区',
			name : 'region_name',
			width : 180
		}, {
			display : '地区分类',
			name : 'region_type',
			width : 120,
			condition: $.getConstants("REGION_TYPE")
		}, {
			display : '教育程度分类',
			name : 'education_type',
			width : 120,
			condition: $.getConstants("EDUCATION_TYPE")
		}, {
			display : '名片id',
			name : 'name_card_id',
			width : 120
		}, {
			display : '工作单位',
			name : 'company',
			width : 120
		}, {
			display : '企业性质',
			name : 'company_nature',
			width : 120,
			condition: $.getConstants("COMPANY_NATURE")
		}, {
			display : '行业类型',
			name : 'trade_type',
			width : 120,
			condition: $.getConstants("TRADE_TYPE")
		}, {
			display : '职业类型',
			name : 'career_type',
			width : 120,
			condition: $.getConstants("CAREER_TYPE")
		}, {
			display : '职位',
			name : 'job_position',
			width : 120,
			condition: $.getConstants("JOB_POSITION")
		}, {
			display : '职级',
			name : 'job_level',
			width : 120,
			condition: $.getConstants("JOB_LEVEL")
		}, {
			display : '婚姻状况',
			name : 'marital_status',
			width : 120,
			condition: $.getConstants("MARITAL_STATUS")
		}, {
			display : '结婚日期',
			name : 'wedding_date',
			width : 120
		}, {
			display : '男孩数',
			name : 'boy_num',
			width : 120
		}, {
			display : '女孩数',
			name : 'girl_num',
			width : 120
		}, {
			display : '子女数',
			name : 'children_num',
			width : 120
		}, {
			display : '个人年收入',
			name : 'annual_income_personal',
			width : 120
		}, {
			display : '（个人）年收入分类码',
			name : 'annual_income_personal_type',
			width : 120,
			condition: $.getConstants("ANNUAL_INCOME_PERSONAL_TYPE")
		}, {
			display : '（家庭）年收入',
			name : 'annual_income_family',
			width : 120
		}, {
			display : '（家庭）年收入分类码',
			name : 'annual_income_family_type',
			width : 120,
			condition: $.getConstants("ANNUAL_INCOME_FAMILY_TYPE")
		}, {
			display : '家庭收入特点',
			name : 'family_income_feature',
			width : 120,
			condition: $.getConstants("FAMILY_INCOME_FEATURE")
		}, {
			display : '财务状况码',
			name : 'family_financial_standing',
			width : 120,
			condition: $.getConstants("FAMILY_FINANCIAL_STANDING")
		}, {
			display : '客户来源码',
			name : 'source_type',
			width : 120,
			condition: $.getConstants("SOURCE_TYPE")
		}, {
			display : '介绍人',
			name : 'introducer_name',
			width : 120
		}, {
			display : '与介绍人关系',
			name : 'introducer_relationship',
			width : 120,
			condition: $.getConstants("INTRODUCER_RELATIONSHIP")
		}, {
			display : '与介绍人亲密度',
			name : 'introducer_closeness',
			width : 120,
			condition: $.getConstants("INTRODUCER_CLOSENESS")
		}, {
			display : '介绍人评价',
			name : 'introducer_evaluation',
			width : 120,
			condition: $.getConstants("INTRODUCER_EVALUATION")
		}, {
			display : '可接触度',
			name : 'contact_type',
			width : 120,
			condition: $.getConstants("CONTACT_TYPE")
		}, {
			display : '联系注意问题',
			name : 'contact_attention',
			width : 120
		}, {
			display : '出生年代',
			name : 'birth_ages',
			width : 120,
			condition: $.getConstants("BIRTH_AGES")
		}, {
			display : '年龄段',
			name : 'age_group',
			width : 120,
			condition: $.getConstants("AGE_GROUP")
		}, {
			display : '星座',
			name : 'constellation',
			width : 120,
			condition: $.getConstants("CONSTELLATION")
		}, {
			display : '血型',
			name : 'blood_group',
			width : 120,
			condition: $.getConstants("BLOOD_GROUP")
		}, {
			display : '性格特点',
			name : 'temperament_type',
			width : 120
		}, {
			display : 'PDP类型',
			name : 'pdp_type',
			width : 120,
			condition: $.getConstants("PDP_TYPE")
		}, {
			display : '兴趣爱好',
			name : 'hobbies',
			width : 120
		}, {
			display : '状态',
			name : 'status',
			width : 120
		}, {
			display : '关注的服务',
			name : 'interesting_service',
			width : 120
		}, {
			display : '已提供的服务',
			name : 'service_served',
			width : 120
		}, {
			display : '启用状态',
			name : 'useflg',
			width : 120,
			condition : {
				'0' : '已停用',
				'1' : '启用',
			}
		}, {
			display : '操作',
			name : 'operation',
			m_type : 'buttons',
			width : 200,
			buttons : [ {
				r_name : 'del',
				text : '删除',
				callback : data_manage_functions.delOne,
				paramConfig : [ "_id_m" ],
				css : "btn btn-xs btn-danger"
			} ]
		} ]

	}
};
