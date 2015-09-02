
// 勾选zTree树上，checkedstr的部分
function checktree(zTree, checkedstr) {
	var rootNodes = zTree.getNodes();
	
	for ( var i = 0, l = rootNodes.length; i < l; ++i) {
		checkNodeTree(zTree,rootNodes[i], checkedstr);
	}
}

// 勾选节点及其子节点
function checkNodeTree(zTree,node,checkedstr) {
	if (!node)
		return;
	
	// 处理子节点
	if (node["children"]) {
		for ( var i = 0, l = node["children"].length; i < l; i++) {
			checkNodeTree(zTree,node["children"][i], checkedstr);
		}
	}
	// 处理节点自身
	if (checkedstr.indexOf("," + node.mnucod + ",") >= 0){
		// alert(node.mnucod);
		var thisNode = node;
		zTree.checkNode(thisNode, true, false, false);
		thisNode.checked = true;
	}
}

// 判断是否叶子节点
function isLeafNode(node){
	
	if (!node["children"] || node["children"].length==0){
		return true;
	}
	
	return false;
}

// 展开第一个节点
function expandFirstNode(tree){
	var fistNode = tree.getNodes()[0];

	var curNode = fistNode["children"][0];
	tree.selectNode(curNode);
	tree.expandNode(curNode,true,true,true,false);	
}
// 清空选择的节点
function clearCheckedTree(treeId){
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	zTree.checkAllNodes(false);
}
