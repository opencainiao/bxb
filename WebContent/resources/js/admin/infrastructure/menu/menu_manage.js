$().ready(function() {
	// 获取全局参数
	var ctx = $("input:hidden[name=ctx]").val();

	// 页面布局
	pageLayout();
	$(window).resize(pageLayout);

	loadTree();
});

var zTree_Menu;
var curMenu;

function pageLayout() {

	var mainHeight = $(window).height() - 30;

	// alert(mainHeight);
	$("div.mainContentLayout").css("height", mainHeight);

	$("div.contentLayout").css("width",
			$("div.mainContentLayout").width() - 200 - 6);
	$("div.contentLayout").css("height", mainHeight - 8);
	$("div.contentLayout").css("float", "right");

	$("div.leftMenuLayout").css("height", mainHeight - 8);

	// 设置iframe自适应
	$("#contentframeid").css("height", mainHeight - 8);
}

var setting = {
	view : {
		showLine : true,
		selectedMulti : false,
		dblClickExpand : false
	},
	data : {
		key : {
			name : "menu_name"
		},
		simpleData : {
			enable : true,
			idKey : "menu_code",
			pIdKey : "sup_menu_code",
			rootPId : "ROOT"
		}
	},
	callback : {
		onNodeCreated : this.onNodeCreated,
		beforeClick : this.beforeClick,
		onClick : this.onClick
	}
};

function beforeClick(treeId, node) {
	if (node.isParent) {
		if (node.level === 0) {
			var pNode = curMenu;
			while (pNode && pNode.level !== 0) {
				pNode = pNode.getParentNode();
			}
			if (pNode !== node) {
				var a = $("#" + pNode.tId + "_a");
				a.removeClass("cur");
				zTree_Menu.expandNode(pNode, false);
			}
			a = $("#" + node.tId + "_a");
			a.addClass("cur");

			var isOpen = false;
			for ( var i = 0, l = node.children.length; i < l; i++) {
				if (node.children[i].open) {
					isOpen = true;
					break;
				}
			}
			if (isOpen) {
				zTree_Menu.expandNode(node, true);
				curMenu = node;
			} else {
				zTree_Menu.expandNode(
						node.children[0].isParent ? node.children[0] : node,
						true);
				curMenu = node.children[0];
			}
		} else {
			zTree_Menu.expandNode(node);
		}
	}
//	return !node.isParent;
	return true;
};

function onClick(e, treeId, node) {

	var url = $("input:hidden[name=ctx]").val() + "/backend/menu/toDetail?menu_code="
			+ node.menu_code + "&time=" + new Date().getTime();
	$("#contentframeid").attr("src", url);
};

function loadTree() {
	// 加载树
	var url = $("input:hidden[name=ctx]").val() + "/backend/menu/loadallmenu";
	$.getJSON(url,{ts: new Date().getTime()}, function(menudata) {
		
		alert(menudata);
		
		var zNodes = menudata;
		$.fn.zTree.init($("#menutree"), setting, zNodes);
		zTree_Menu = $.fn.zTree.getZTreeObj("menutree");
		
		curMenu = zTree_Menu.getNodes()[0];
		if (curMenu.children[0] == null || curMenu.children[0]=="undifined"){
		}else{
			curMenu = curMenu.children[0];
		}
		
		zTree_Menu.selectNode(curMenu);
		zTree_Menu.expandNode(curMenu);
		var a = $("#" + zTree_Menu.getNodes()[0].tId + "_a");
		a.addClass("cur");
	});
}
