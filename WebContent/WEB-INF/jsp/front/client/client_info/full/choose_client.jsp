<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<jsp:include page="/WEB-INF/jsp/include/common_css.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/include/common_js.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/include/common_flexigrid.jsp"></jsp:include>

<script type="text/javascript"
	src="${ctx }/resources/bootbox-master/bootbox.js"></script>

</head>

<body>
	<input type="hidden" name="ctx" value="<%=request.getContextPath()%>" />

	<div id="choose_div">
		<input type="hidden" id="f_id" name="f_id"
			value="${f_id}">
		<input type="hidden" id="f_name" name="f_name"
			value="${f_name}">
		<input type="hidden" id="f_sex" name="f_sex"
			value="${f_sex}">

		<div class="navbar navbar-default">
			<form class="navbar-form navbar-left">
				<div class="form-group form-group-sm">
					<input class="form-control " style="width: 300px" type="text"
						id="search_condition" name="search_condition"
						placeholder="输入名称/拼音进行查询">
				</div>
				<button class="btn btn-info btn-sm" type="button" id="btn_search">
					查询</button>
				<button class="btn btn-primary btn-sm" type="button"
					id="btn_confirm">确定</button>
			</form>
		</div>

		<div id="data_manage">
			<table id="list"></table>
		</div>
	</div>

	<script>
		$().ready(function() {

			data_manage.init();

			$("#btn_search").bind("click", data_manage.search);
			$("#btn_confirm").bind("click", function() {
				var selected = mou_grid_ux.getSelectedAllGrid("list");
				if (selected.length == 0) {
					bootbox.alert("<span style='color:red'>请选择一个客户</span>");
					return;
				}
				
				var selectedObj = selected[0];
				
				if ($("#f_id").val() == selectedObj["_id_m"]){
					bootbox.alert("<span style='color:red'>不能选择自己</span>");
					return;
				}
				//$.logJson(selectedObj);
				parent.setSelectedClient(selectedObj);
			});

			document.onkeydown = function(event) {
				if (event.keyCode == 13) {
					return false;
				}
			}
		});

		function refresh_parent() {
			parent.refreshFamillyWindow();
			parent.closeAddFamillyWindow();
		}

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
				var table_h = mou_grid_ux.getTableH($("#choose_div"), inPage_h - nav_h - btnbar_h - 2 - 8);
				mou_grid_ux.resetHeight_grid($("#choose_div"), table_h);
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
				title : '',
				useRp : true,
				rp : 20, // 默认分页条数
				pagestat : '共有{total}条记录，显示{from} - {to}条记录',
				procmsg : '正在查询，请稍候 ...',
				nomsg : '没有符合条件的数据',
				colModel : [ {
					display : '选择',
					hide : false,
					m_type : 'radio',
					align : 'center',
					select : [ "_id_m", "client_name", "sex" ]
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
					select : [ "_id_m" ],
				}, {
					display : '性别',
					name : 'sex',
					width : 40,
					condition : {
						'0' : '女',
						'1' : '男'
					}
				}, {
					display : '生日',
					name : 'birth_date',
					width : 70
				}, {
					display : '年龄',
					name : 'age',
					width : 40
				}, {
					display : '地区',
					name : 'region_name',
					width : 180
				}, {
					display : '男孩',
					name : 'boy_num',
					width : 30
				}, {
					display : '女孩',
					name : 'girl_num',
					width : 30
				}, {
					display : '子女数',
					name : 'children_num',
					width : 45
				}, {
					display : '介绍人',
					name : 'introducer_name',
					width : 120
				}, {
					display : '与介绍人关系',
					name : 'introducer_relationship',
					width : 120,
					condition : $.getConstants("INTRODUCER_RELATIONSHIP")
				} ]
			}
		}
	</script>
</body>
</html>