<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<jsp:include page="/WEB-INF/jsp/include/common_css.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/include/common_js.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/include/common_flexigrid.jsp"></jsp:include>

</head>

<body>
	<input type="hidden" name="ctx" value="<%=request.getContextPath()%>" />
	<input type="hidden" name="choose_phonetype_div"
		id="choose_phonetype_div" />
	<ul class="breadcrumb">
		<li class="active">电话管理</li>
	</ul>

	<div id="content_inner_page" class="innercontent">
		<div class="navbar navbar-default">
			<form class="navbar-form navbar-left">
				<div class="form-group ">
					所有者_id <input class="form-control " style="width: 300px"
						type="text" id="owner_id" name="owner_id" placeholder="">
				</div>
				<button class="btn btn-info" type="button" id="btn_search">
					查询</button>
				<button class="btn btn-primary" type="button" id="btn_add"
					style="margin-left: 50px;">添加电话</button>
			</form>
		</div>

		<div id="data_manage">
			<table id="list"></table>
		</div>

	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/admin/client/phone/list.js"></script>

	<script>
		// 关闭选择选择电话类型窗口
		function closeChoosephonetypeWindow(){
			var $choose_addresstype = $("#choose_phonetype_div");
			var index = $choose_addresstype.attr("index");
					
			layer.close(index);
		}
		
		// 设置修改页面的电话类型信息
		function setPhoneType(phone_type_choosed){
			
			var idx = $("#data_manage").attr("edit");
			
			var id = "xubox_layer" + idx;
			var iframeid= "xubox_iframe" + idx;
			
			$("#" + iframeid).contents().find("#type_value").val(phone_type_choosed.type_value);
			$("#" + iframeid).contents().find("#type_name").val(phone_type_choosed.type_name);
		}
		
	    /****
		* 弹出选择电话类型框
		*/
		function pupUpChoosephonetype() {
			$.layer({
				type : 2,
				title : [ '选择地址类型',
						'background:#2B2E37; height:40px; color:#fff; border:none;' //自定义标题样式
				],
				border : [ 0 ],
				area : [ '60%', '60%' ],
				btns:0,
				btn: [],
				iframe : {
					src : "<%=request.getContextPath()%>/backend/phonetype/phonetype_reference?ts=" + new Date().getTime()
				},
				closeBtn : [ 0, true ],
				yes : function(index) {
					layer.close(index);
					return;
				},
				no : function(index) {
				},
				success : function(layero) {
					var popid = layero.selector;
					var index = $.getLayerIndex(popid);
					var $choose_addresstype = $("#choose_phonetype_div");
					$choose_addresstype.attr("index", index);
				}
			})
		}
	</script>
</body>

</html>