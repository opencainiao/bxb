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

	<ul class="breadcrumb" id="add_spec_breadcrumb">
		<li><input type="button" id="btn_confirm"
			class="btn btn-nm btn-primary " value="确定"> <input
			type="button" id="btn_cancel" class="btn btn-nm btn-primary "
			value="取消"></li>
	</ul>

	<input type="hidden" name="ctx" value="<%=request.getContextPath()%>" />

	<div id="content_inner_page" class="innercontent">
		<div id="data">
			<table id="list"></table>
		</div>
	</div>


	<script>
		$().ready(function() {

			$("#btn_confirm").click(function() {
				province_functions.confirm_choose_unit();
			});

			$("#btn_cancel").click(function() {
				window.top.closeChoose_cityWindow();
			});

			province_search.search();
		});

		var province_functions = {
			pageLayout : function() {
				var inPage_h = $(window).height();
				var table_h = mou_grid_ux.getTableH($("#data"), inPage_h - 150);

				$(".bDiv").height(table_h);
			},

			confirm_choose_unit : function() {

				$.disableButton("btn_confirm");

				// 1.如果没有选择，报错：必须选择一个规格
				var checked = $('input:radio[name="radioname"]:checked');
				var val = $('input:radio[name="radioname"]:checked').val();
				if (val == null) {
					layer.alert("请选择一个!");
					$.enableButton("btn_confirm");
					return false;
				}

				var _grid = $("#data");
				_grid.data("SELECTFLG", "radio");
				var selected = mou_grid_ux.getSelectedAllGrid("data");

				//alert(JSON.stringify(selected));
				var item_choosed = selected[0];

				window.top.setCity(item_choosed);
				window.top.closeChoose_cityWindow();
			}
		};

		var province_search = {

			/***************************************************************************
			 * 页面加载后的初始化方法
			 */
			search : function() {

				var province = window.top.province.value;
				//alert(province);
					
				province_search.gridsetting.url = $.getSitePath() + '/backend/city/list_by_pid?parent_id='+ province +'&ts=' + new Date().getTime();

				$("#data").flexigrid(province_search.gridsetting);
			},

			/***************************************************************************
			 * 表格配置
			 */
			gridsetting : {
				showTableToggleBtn : true,
				resizable : false,
				grid_id : 'list',
				url : '',
				method : 'POST',
				dataType : 'json',
				nowrap : true, // 是否不换行
				autoload : true,// 自动加载
				usepager : false,
				title : '',
				useRp : false,
				pagestat : '共有{total}条记录，显示{from} - {to}条记录',
				procmsg : '正在查询，请稍候 ...',
				nomsg : '没有符合条件的数据',
				onSuccess : province_functions.pageLayout,
				colModel : [ {
					display : '选择',
					hide : false,
					m_type : 'radio',
					align : 'center',
					select : [ "id","code" ,"name"]
				}, {
					display : '序号',
					m_type : 'sno',
					align : 'center',
					hide : true
				}, {
					display : 'id',
					name : 'id',
					width : 100
				} , {
					display : '编码',
					name : 'code',
					width : 100
				} , {
					display : '名称',
					name : 'name',
					width : 200
				} ]
			}
		};
	</script>

</body>

</html>