$().ready(function() {
	// 获取全局参数
	var ctx = $("input:hidden[name=ctx]").val();

	$("#contentframeid").load(function() {
		// 设置iframe自适应
		var iframe_h = $.autoHeight("contentframeid");

		var toSet_h = iframe_h;

		$("#leftMenuLayout").css("height", toSet_h + 5);
	});

	loadTree();

	$("#contentframeid").attr("menu_code", "ROOT");
});

var zTree_Menu;
var curMenu;

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

	var url = $.getSitePath() + "/backend/menu/toDetail?menu_code=" + node.menu_code + "&time=" + new Date().getTime();
	$("#contentframeid").attr("src", url);
	$("#contentframeid").attr("menu_code", node.menu_code);
};

function loadTree(menu_code) {
	// 加载树
	var url = $("input:hidden[name=ctx]").val() + "/backend/menu/loadallmenu";
	$.getJSON(url, {
		ts : new Date().getTime()
	}, function(menudata) {

		// alert(menudata);

		var zNodes = menudata;
		$.fn.zTree.init($("#menutree"), setting, zNodes);
		zTree_Menu = $.fn.zTree.getZTreeObj("menutree");

		if (menu_code) {

			var node = zTree_Menu.getNodeByParam("menu_code", menu_code, null);
			zTree_Menu.selectNode(node);
			zTree_Menu.expandNode(node);
			var a = $("#" + node.tId + "_a");
			a.addClass("cur");

		} else {
			curMenu = zTree_Menu.getNodes()[0];

			zTree_Menu.selectNode(curMenu);
			zTree_Menu.expandNode(curMenu);
			var a = $("#" + zTree_Menu.getNodes()[0].tId + "_a");
			a.addClass("cur");
		}
	});
}

// 重新加载指定部门的节点(异步)
function ReloadNode(menu_code) {
	loadTree(menu_code);
}

function popUpAddSubMenu(url) {
	// alert(url);

	$.popUpWindow("添加子菜单", url, "780px", "450px", "add", $("#content_inner_page"), [ '100px', '' ]);
}

function closeAddSubMenuWindow() {

	var url = $("#contentframeid").attr("src");
	$("#contentframeid").attr("src", url);

	var menu_code = $("#contentframeid").attr("menu_code");
	ReloadNode(menu_code);
	// loadTree();

	$.closeWindow("add", $("#content_inner_page"));
}
