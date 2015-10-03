$().ready(function() {

	data_manage_familly_relationship.init();

	$("#btn_add",$("#familly")).css("margin-left","1px");
	$("#btn_add",$("#familly")).click(function() {
		var url_to = $.getSitePath() + '/front/familly/add?user_id=#USERID#&user_name=#USERNAME#&user_sex=#USERSEX#';
		
		url_to = url_to.replaceAll("#USERID#",$("#_id_m").val());
		url_to = url_to.replaceAll("#USERNAME#",$("#client_name").val());
		url_to = url_to.replaceAll("#USERSEX#",$("#client_sex").val());

		$.popUpWindow("添加亲属", url_to, "90%", "90%", "add", $("#btn_add"));
	});
});

/****
 * 弹出选择客户窗口
 */
var popUpChooseClient = function(){
	var url_to = $.getSitePath() + '/front/familly/choose_client?user_id=#USERID#&user_name=#USERNAME#&user_sex=#USERSEX#';
	
	url_to = url_to.replaceAll("#USERID#",$("#_id_m").val());
	url_to = url_to.replaceAll("#USERNAME#",$("#client_name").val());
	url_to = url_to.replaceAll("#USERSEX#",$("#client_sex").val());

	$.popUpWindow("选择客户", url_to, "90%", "90%", "choose_client", $("#btn_add"));
}

/****
 * 设置选择的客户信息（在弹出的选择客户页面）
 */
var setSelectedClient=function(selectedObj){
	
	// 设置选择客户信息
	var layerIndex = $("#btn_add").attr("add");
	$.callLayerFunc(layerIndex, "setSelectedClient", selectedObj) ;
	
	// 关闭选择客户弹出窗口
	$.closeWindow("choose_client", $("#btn_add"));
}

/****
 * 关闭添家亲属弹出框
 */
var closeAddFamillyWindow = function() {
	$.closeWindow("add", $("#btn_add"));
};

var refreshFamillyWindow = function() {
	$("#list_familly_relationship").flexReload(data_manage_familly_relationship.gridsetting);
};

var data_manage_functions = {

	/***************************************************************************
	 * 删除
	 * 
	 * @param data
	 */
	delOne : function(data) {

		var url_to = $.getSitePath() + '/front/familly/' + data["_id_m"] + '/delete';

		$.ajax({
			type : 'POST',
			url : url_to,
			data : $.extend({
				ts : new Date().getTime()
			}, data),
			dataType : 'json',
			success : function(data) {
				data_manage_familly_relationship.gridsetting.params = [ {
					name : 'reload',
					value : true
				} ];
				$("#list_familly_relationship").flexReload(data_manage_familly_relationship.gridsetting);
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

		$.popUpWindow("编辑用户信息", url, "800px", "400px", "edit", $("#data_manage_familly_relationship"));
	},
	/***************************************************************************
	 * 进入详细信息页面
	 * 
	 * @param data
	 * @returns {Boolean}
	 */
	toDetail : function(data) {

		var url = $.getSitePath() + '/front/client/' + data["s_id"];

		$.loadPage(url);

		// $.showDetailWindow("用户信息", url, "600px", "300px");
	},
	/***************************************************************************
	 * 关闭编辑窗口
	 */
	closeEditWindow : function() {
		$.closeWindow("edit", $("#data_manage_familly_relationship"));
	},
	refreshPage : function() {
		data_manage_familly_relationship.search();
	}
};

/****
 * 亲属关系表grid
 */
var data_manage_familly_relationship = {

	/***************************************************************************
	 * 页面加载后的初始化方法
	 */
	init : function() {

		data_manage_familly_relationship.gridsetting.url = $.getSitePath() + '/front/familly/list?client_id=' + $("#_id_m").val();
		// alert(data_manage_familly_relationship.gridsetting.url);
		$("#list_familly_relationship").flexigrid(data_manage_familly_relationship.gridsetting);

		data_manage_familly_relationship.pageLayout();

		$("#data_manage_familly_relationship").attr("s_times", 1);
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
		showTableToggleBtn : false,
		resizable : false,
		url : '',
		method : 'POST',
		dataType : 'json',
		nowrap : true, // 是否不换行
		autoload : true,// 自动加载
		usepager : true,
		title : '',
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
			display : 's_id',
			name : 's_id',
			width : 120,
			hide : true
		}, {
			display : '姓名',
			name : 's_name',
			width : 110,
			m_type : 'link',
			select : [ "s_id" ],
			hide : false,
			callback : data_manage_functions.toDetail
		}, {
			display : '性别',
			name : 's_sex',
			width : 40,
			condition : {
				'0' : '女',
				'1' : '男'
			}
		},  {
			display : '关系',
			name : 'relationship',
			width : 120,
			condition: $.getConstants("FAMILLY_RELATIONSHIP")
		}, {
			display : '说明',
			name : 'relationship_cmt',
			width : 120
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
