$().ready(function() {

	var menu_code = $("#menu_code").val();

	if (menu_code == "ROOT") {
		$("#btn_save").hide();
	}

	document.onkeydown = function(event) {
		if (event.keyCode == 13) {
			return false;
		}
	}
	
	$("#btn_add").click(function() {
		var url_to = $.getSitePath() + '/backend/menu/add';

		var params = [];
		params.push("supmnulvl=" + $("#menu_level").val());
		params.push("sup_menu_code=" + $("#menu_code").val());
		params.push("sup_menu_name=" + $("#menu_name").val());
		params.push("ts=" + new Date().getTime());

		url_to = url_to + "?" + params.join("&");

		parent.popUpAddSubMenu(url_to);
	});

	$("#btn_save").click(function() {
		
		// 控制按钮为禁用
	    $.disableButton("btn_save");

	    var paramForm = $('form').getFormParam_ux();

	    var successstr = "修改成功";

	    var url_to = $.getSitePath() + "/backend/menu/"+ $("#_id").val() +"/update";
	    
	    var params = [];
		params.push("ts=" + new Date().getTime());
		
		url_to = url_to + "?" + params.join("&");

	    $.ajax({
	        type: 'POST',
	        url: url_to,
	        data: $.extend({
	            ts: new Date().getTime()
	        },
	        paramForm),
	        type: 'POST',
	        dataType: 'json',
	        success: function(data) {

	            if (data['success'] == 'n') {
	                if (data['brErrors']) {
	                    $.showBRErrors_mou_abs(data['brErrors'], $("#add_div"));
	                } else {
	                	$.alertError(data['message'],['100px', '20%']);
	                }
	            } else {
	            	parent.ReloadNode($("#menu_code").val());
	            	
	            	$.alertSuccess("成功", successstr,['100px', '20%']);
	            }
	        },
	        complete: function(XMLHttpRequest, textStatus) {
	            $.enableButton("btn_save");
	        }
	    });
	});
	
	data_manage.init();
});


var data_manage_functions = {

	/***************************************************************************
	 * 删除
	 * 
	 * @param data
	 */
	delOne : function(data) {

		var url_to = $.getSitePath() + '/backend/menu/' + data["_id_m"] + '/delete';

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
				
				parent.ReloadNode($("#menu_code").val());
			}
		});
	},
	/***************************************************************************
	 * 进入修改页面
	 * 
	 * @param data
	 */
	toEdit : function(data) {

		var url = $.getSitePath() + '/backend/menu/' + data["_id_m"] + "/update";

		$.popUpWindow("编辑材料类型信息", url, "800px", "400px", "edit", $("#data_manage"));
	},
	/***************************************************************************
	 * 进入详细信息页面
	 * 
	 * @param data
	 * @returns {Boolean}
	 */
	toDetail : function(data) {

		var url = $.getSitePath() + '/backend/menu/' + data["_id_m"];

		$.showDetailWindow("材料类型信息", url, "600px", "300px");
	},
	/***************************************************************************
	 * 关闭编辑窗口
	 */
	closeEditWindow : function() {
		$.closeWindow("edit", $("#data_manage"));
	},
	refreshPage : function() {
		window.location.reload();
		// data_manage.search();
	}
};


var data_manage = {

	/***************************************************************************
	 * 页面加载后的初始化方法
	 */
	init : function() {

		var url = $.getSitePath() + '/backend/menu/sub_menus';

		var params = [];
		params.push("menu_code=" + $("#menu_code").val());
		params.push("ts=" + new Date().getTime());

		url = url + "?" + params.join("&");

		data_manage.gridsetting.url = url;

		// alert(data_manage.gridsetting.url);
		$("#list").flexigrid(data_manage.gridsetting);

		//data_manage.pageLayout();
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
		title : '子菜单列表',
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
			width : 150,
			hide : true,
			callback : data_manage_functions.toDetail
		}, {
			display : '菜单码',
			name : 'menu_code',
			width : 120,
			hide : true
		}, {
			display : '上级菜单码',
			name : 'sup_menu_code',
			width : 120,
			hide : true
		},{
			display : '名称',
			name : 'menu_name',
			width : 120,
			hide : false,
		}, {
			display : '级次',
			name : 'menu_level',
			width : 30
		}, {
			display : '序号',
			name : 'menu_sno',
			width : 30
		}, {
			display : '是否叶子节点',
			name : 'leaf_flg_name',
			width : 90
		}, {
			display : '模块',
			name : 'module_code_name',
			width : 120
		}, {
			display : '图标类',
			name : 'iclass',
			width : 120
		},
		{
			display : '启用状态',
			name : 'useflg',
			width : 60,
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
				text : '删除',
				callback : data_manage_functions.delOne,
				paramConfig : ["_id_m", "menu_code", "sup_menu_code" ]
			}]
		} ]
	}
};
