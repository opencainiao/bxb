var cur_ipt_controll_config;
$().ready(function() {

	initForPage();

	// 注册编辑菜单信息方法
	$("#btn_toEdit").bind("click", toEditMnuInf);

	// 注册添加子菜单方法
	$("#btn_toAddSubMnu").bind("click", toAddSubMnu);

	// 注册添加动作方法
	$("#btn_toAddAction").bind("click", toAddAction);

	var linkflg = $("input[name=LINKFLG]").val();
	if (linkflg == "") {
		$("#btn_back").toggleClass('hiddenEle');
	}

	pageLayout();
});

function toEditMnuInf() {
	var mnucod = $("input:hidden[name=ACTIONSUPMNUCOD]").val();

	var paramArr = [];
	paramArr.push("mnucod=" + mnucod);

	var url = globalspace.ctx + "/menu/toEdit.do?" + paramArr.join("");

	// alert(url);
	// 跳转进入编辑菜单信息页面
	window.location.href = url;
}

function toAddSubMnu() {
	var mnucod = $("input:hidden[name=ACTIONSUPMNUCOD]").val();
	var mnunam = $("input:hidden[name=ACTIONSUPMNUNAM]").val();
	var mnulvl = $("input:hidden[name=ACTIONSUPMNULVL]").val();
	

	var paramArr = [];
	paramArr.push("supmnucod=" + mnucod);
	paramArr.push("&supmnunam=" + mnunam);
	paramArr.push("&supmnulvl=" + mnulvl);

	var url = globalspace.ctx + "/menu/toAdd.do?" + paramArr.join("");

	// alert(url);
	// 跳转进入添加子菜单页面
	window.location.href = url;
}

function toAddAction() {
	var mnucod = $("input:hidden[name=ACTIONSUPMNUCOD]").val();
	var mnunam = $("input:hidden[name=ACTIONSUPMNUNAM]").val();

	var paramArr = [];
	paramArr.push("mnucod=" + mnucod);
	paramArr.push("&mnunam=" + mnunam);

	var url = globalspace.ctx + "/menu/toAddAction.do?" + paramArr.join("");

	// alert(url);
	// 跳转进入添加动作页面
	window.location.href = url;
}

function pageLayout() {

	var arr = [];
	arr.push("top_h   --" + $(top.window).height());
	arr.push("window_h--" + $(window).height());
	arr.push("innerPage_h--" + $(".innerPage").height());
	arr.push("btnbar_h--" + $(".btnbar").height());
	arr.push("inputtable_h--" + $(".inputTable").height());
	arr.push("navTd1_h--" + $(".navTd1").height());
	arr.push("hideBody_h--" + $(".hideBody").height());

	arr.push("constants_FLEXIGRID_HEIGHT" + constants.FLEXIGRID_HEIGHT);

	var window_h = $(window).height();
	var btnbar_h = $(".btnbar").height();
	var inputtable_h = $(".inputTable").height();
	var hideBody_h = $(".hideBody").height();

	// var table_h = window_h - btnbar_h - inputtable_h - 15 -
	// constants.FLEXIGRID_HEIGHT;

	// alert("table_h: " + table_h);
	// var table_h_normaction = 200;
	// normacctiontsetting.height = table_h_normaction;
	var mnucod = $("input:hidden[name=ACTIONSUPMNUCOD]").val();

	submnusetting.url = globalspace.ctx + '/menu/findSubMnus.do?supmnucod=' + mnucod + '&viewtyp=table';
	$("#submnutable").flexigrid(submnusetting);
	//	
	normacctiontsetting.url = globalspace.ctx + '/menu/findNormActions.do?mnucod=' + mnucod + '&viewtyp=table';
	$("#normacctiontable").flexigrid(normacctiontsetting);

	// alert(arr.join("\n"));
}

// 子菜单列表
var submnusetting = {
	name : "submnu",
	tableid : "submnutable",
	sortname : "oid",
	sortorder : "asc",
	checkbox : false,// 是否要多选框
	showRowNum : true,// 是否显示行号
	rowId : 'id',// 多选框绑定行的id
	showTableToggleBtn : true,
	height : 200,
	url : '',
	method : 'POST',
	dataType : 'json',
	nowrap : true, // 是否不换行
	autoload : true,// 自动加载
	usepager : false,
	title : '子菜单列表',
	useRp : false,
	rp : 5, // 默认分页条数
	pagestat : '共有{total}条记录，显示{from} - {to}条记录',
	procmsg : '正在查询，请稍候 ...',
	nomsg : '没有符合条件的数据',
	nodatashow : false,
	colModel : [ {
		display : '菜单码',
		name : 'mnucod',
		width : 60,
		sortable : false,
		align : 'left'
	}, {
		display : '菜单名称',
		name : 'mnunam',
		width : 100,
		sortable : false,
		align : 'left',
		link : true,
		callback : toMnuDetail,
		linkConfig : [ "mnucod" ]
	}, {
		hide : true,
		display : 'SUPMNUCOD',
		name : 'supmnucod',
		width : 120,
		sortable : false,
		align : 'left'
	},{
		display : '菜单类型',
		name : 'leafflgnam',
		width : 50,
		sortable : false,
		align : 'center'
	}, {
		display : '级次',
		name : 'mnulvl',
		width : 30,
		sortable : false,
		align : 'left',
		hide : false
	}, {
		display : '序号',
		name : 'samlvlmnusno',
		width : 30,
		sortable : false,
		align : 'center'
	}, {
		display : '是否启用',
		name : 'struseflgnam',
		width : 50,
		sortable : false,
		align : 'center',
		hide : false
	}, {
		display : '所属模块',
		name : 'mdlcodnam',
		width : 50,
		sortable : false,
		align : 'left'
	}, {
		display : '菜单动作',
		name : 'itfpgmnam',
		width : 180,
		sortable : false,
		align : 'left'
	}, {
		display : '操作',
		name : 'action',
		width : 150,
		sortable : false,
		align : 'left',
		buttons : [ {
			text : '删除',
			callback : deleteMenu,
			paramConfig : [ "mnucod", "supmnucod" ]
		}, {
			text : '上移',
			callback : moveupMenu,
			paramConfig : [ "mnucod", "supmnucod" ]
		}, {
			id : "deleteMenu",
			text : '下移',
			callback : movedownMenu,
			paramConfig : [ "mnucod", "supmnucod" ]
		} ]
	} ]
};

function deleteMenu(obj) {

	var mnucod = obj.mnucod;
	var supmnucod = obj.supmnucod;

	var paramArr = [];
	paramArr.push("mnucod=" + mnucod);
	paramArr.push("&supmnucod=" + supmnucod);

	var url = globalspace.ctx + "/menu/delMnu.do?" + paramArr.join("");

	if (confirm('此操作将删除所有相关子菜单或动作,确定要删除吗?')) {
		// 跳转进入添加动作页面
		window.location.href = url;
	}
}
function moveupMenu(obj) {

	var mnucod = obj.mnucod;
	var supmnucod = obj.supmnucod;

	var paramArr = [];
	paramArr.push("mnucod=" + mnucod);
	paramArr.push("&supmnucod=" + supmnucod);

	var url = globalspace.ctx + "/menu/upMenu.do?" + paramArr.join("");

	// 跳转进入添加动作页面
	window.location.href = url;
}

function movedownMenu(obj) {

	var mnucod = obj.mnucod;
	var supmnucod = obj.supmnucod;

	var paramArr = [];
	paramArr.push("mnucod=" + mnucod);
	paramArr.push("&supmnucod=" + supmnucod);

	var url = globalspace.ctx + "/menu/downMenu.do?" + paramArr.join("");

	// 跳转进入添加动作页面
	window.location.href = url;
}


// 普通动作列表
var normacctiontsetting = {
	name : "normaction",
	tableid : "normacctiontable",
	sortname : "oid",
	sortorder : "asc",
	checkbox : true,// 是否要多选框
	showRowNum : true,// 是否显示行号
	rowId : 'id',// 多选框绑定行的id
	showTableToggleBtn : true,
	height : 300,
	url : '',
	method : 'POST',
	dataType : 'json',
	nowrap : true, // 是否不换行
	autoload : true,// 自动加载
	usepager : false,
	title : '普通动作列表',
	useRp : false,
	rp : 5, // 默认分页条数
	pagestat : '共有{total}条记录，显示{from} - {to}条记录',
	procmsg : '正在查询，请稍候 ...',
	nomsg : '没有符合条件的数据',
	colModel : [ {
		display : 'oid',
		name : 'oid',
		width : 60,
		sortable : false,
		align : 'left'
	}, {
		display : '动作',
		name : 'actionpath',
		width : 150,
		sortable : false,
		align : 'left',
		link : true,
		callback : toActionDetail,
		linkConfig : [ "oid", "mnucod" ]
	}, {
		display : '所属模块',
		name : 'mdlcodnam',
		width : 50,
		sortable : false,
		align : 'center'
	}, {
		display : '所属菜单码',
		name : 'mnucod',
		width : 60,
		sortable : false,
		align : 'left',
		hide : false
	}, {
		display : '是否启用',
		name : 'struseflgnam',
		width : 50,
		sortable : false,
		align : 'center'
	}, {
		display : '删除标志',
		name : 'delflgnam',
		width : 50,
		sortable : false,
		align : 'center',
		hide : false
	}, {
		display : '说明',
		name : 'actioncmt',
		width : 100,
		sortable : false,
		align : 'left'
	} ],
	buttons : [ {
		// 设置分割线
		separator : true
	}, {
		r_name : 'del',
		text : '删除',
		callback : deleteMenu,
		paramConfig : [ "_id_m" ],
		css : "btn btn-xs btn-danger"
	}, {
		separator : true
	} ]
};

function action(com, grid, setting) {
	switch (com.id) {
	case 'delete': {
		var colConfigs = [ "oid", "mnucod", "actioncmt" ];
		var confirmConfig = {
			name : "actioncmt",
			id : "oid"
		};
		deleProxy(grid, colConfigs, confirmConfig, delEles, setting);
		break;
	}
	}
}

function delEles(ids, grid, setting) {

	var muncod = $("input:hidden[name=ACTIONSUPMNUCOD]").val();

	$.ajax({
		url : globalspace.ctx + "/menu/delAction.do?mnucod=" + muncod + "&ids=" + ids,
		data : {
			ts : new Date().getTime()
		},
		type : 'POST',
		dataType : 'json',
		success : function() {
			if (setting.name == "normaction") {
				// 正常动作
				$('#normacctiontable').flexReload();
			}

			// 异步重新加载父节点
			parent.ReloadNode(supdptnum);
		}
	});
}

function toMnuDetail(o) {
	var mnucod = o["mnucod"];

	var paramArr = [];
	paramArr.push("mnucod=" + mnucod);

	var url = globalspace.ctx + "/menu/toDetail.do?" + paramArr.join("");
	// alert(url);
	// 跳转进入菜单明细页面
	window.location.href = url;
}

var inputControlSetting = {
	eleNoMap : {}, // 框架用
	eles : [ {
		name : 'SUPDPTNUM', // 对应的页面的input元素
		vType : "grid", // 参照的类型
		width : 450, // 参照宽度
		height : 300, // 参照高度
		title : "部门参照", // 参照的标题
		writein : [ "dptnum", "dptnam" ], // 要读取的表格的列元素name
		writeout : [ "SUPDPTNAM", "SUPDPTNUM" ], // 要写入的页面的input元素的name
		writemode : "add",// 追加方式（默认为覆盖页面元素值）
		no : -1, // 存储该对象的序号位置
		config : {
			url : "/dpt/findBatch.do",
			colModel : [ {
				display : '部门号',
				name : 'dptnum',
				width : 40,
				sortable : false,
				align : 'center'
			}, {
				display : '部门名称',
				name : 'dptnam',
				width : 80,
				sortable : false,
				align : 'left'
			} ],
			radiobox : true
		// 是否单选
		}
	} ]
};
