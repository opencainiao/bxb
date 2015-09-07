$().ready(function() {
	// 获取全局参数
	var ctx = $("input:hidden[name=ctx]").val();

	// 页面布局
	pageLayout();

	loadTree();
});

var zTree_Menu;
var curMenu;

function pageLayout() {

	var mainHeight = $(window).height() - 60;

	$("#leftMenuLayout").css("height", mainHeight - 8);
	$("#menu_detail").css("height", mainHeight - 8);

	// 设置iframe自适应
	$("#contentframeid").css("height", mainHeight - 13);

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
			for (var i = 0, l = node.children.length; i < l; i++) {
				if (node.children[i].open) {
					isOpen = true;
					break;
				}
			}
			if (isOpen) {
				zTree_Menu.expandNode(node, true);
				curMenu = node;
			} else {
				zTree_Menu.expandNode(node.children[0].isParent ? node.children[0] : node, true);
				curMenu = node.children[0];
			}
		} else {
			zTree_Menu.expandNode(node);
		}
	}
	// return !node.isParent;
	return true;
};

function onClick(e, treeId, node) {

	var url = $("input:hidden[name=ctx]").val() + "/backend/menu/toDetail?menu_code=" + node.menu_code + "&time=" + new Date().getTime();
	$("#contentframeid").attr("src", url);
};

function loadTree() {
	// 加载树
	var url = $("input:hidden[name=ctx]").val() + "/backend/menu/loadallmenu";
	$.getJSON(url, {
		ts : new Date().getTime()
	}, function(menudata) {

		// alert(menudata);

		var zNodes = menudata;
		$.fn.zTree.init($("#menutree"), setting, zNodes);
		zTree_Menu = $.fn.zTree.getZTreeObj("menutree");

		curMenu = zTree_Menu.getNodes()[0];
		if (curMenu.children[0] == null || curMenu.children[0] == "undifined") {
		} else {
			curMenu = curMenu.children[0];
		}

		zTree_Menu.selectNode(curMenu);
		zTree_Menu.expandNode(curMenu);
		var a = $("#" + zTree_Menu.getNodes()[0].tId + "_a");
		a.addClass("cur");
	});
}

function popUpAddSubMenu(url) {
	// alert(url);

	$.popUpWindow("添加子菜单", url, "780px", "450px", "add", $("#content_inner_page"));
}

function closeAddSubMenuWindow() {

	var url = $("#contentframeid").attr("src");
	$("#contentframeid").attr("src", url);
	
	loadTree();

	$.closeWindow("add", $("#content_inner_page"));
}
