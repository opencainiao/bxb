<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<style>
dl {
    border-bottom: 1px solid #eee;
    bottom: -1px;
    position: relative;
    margin-bottom: 8px!important;
}

dl dt {
    clear: left;
    color: #888;
    float: left;
    line-height: 1.5;
    width: 150px;
}

dl dd {
    line-height: 1.5;
    margin-left: 60px;
    min-height: 18px;
    width: 320px;
}

.panel-heading button{
	margin-top: -4px!important; 
	padding-bottom: 3px!important; 
	padding-top: 3px!important;"
}

</style>

</head>

<script>

var client = {};

<c:if test="${client != null}">
	client = ${client};
</c:if>
//$.alertObjJson(client);

// 生成内容
function genContent(client,title,name){
	
	var content = "";

	for(var i=0;i<name.length;i++){
		
		var item_name =  name[i];
		var item_title =  title[i];
		
		var item_value = client[item_title];
		
		if (item_value != undefined){
			
			var type_item = typeof(item_value);
			
			if (type_item == "string"){
				
				if (item_value != ""){
					var item_str = "<dl><dt>" + item_name + "</dt><dd style='margin-left: 150px;'>" + item_value + "</dd></dl>";
					
					content = content + item_str;
				}
			}else{
				var item_str = "<dl><dt>" + item_name + "</dt><dd  style='margin-left: 150px;'>" + item_value + "</dd></dl>";
				
				content = content + item_str;
			}
		}
	}
	
	return content;
}

var base_prop_title = [];
<c:forEach var="title" items="${base_prop_title}">
	base_prop_title.push("${title}");
</c:forEach>
//alert(base_prop_title.join("\n"));

var base_prop_name = [];
<c:forEach var="title_name" items="${base_prop_name}">
	base_prop_name.push("${title_name}");
</c:forEach>
//$.alertObjJson(base_prop_name);

// 生成基本模块内容
function genBaseContent(client){
	var content = genContent(client,base_prop_title,base_prop_name);
	
	$("#base_info_content").html(content);
}

function toEditBase(){
	
	
	var url = $.getSitePath() + '/clientpartinfo/' + $("#_id_m").val() + "/update?part_flg=0";
	
	//alert(url);

	$.popUpWindow("编辑客户基本信息", url, "90%", "90%", "edit", $("#edit_base"));
}

function closeEditBase(){
	
	$.closeWindow("edit", $("#edit_base"));
}
function initBase(){
	genBaseContent(client);
	
	$('#edit_base').unbind();
	$("#edit_base").bind("click", toEditBase);
}

function refreshBase(){
	
	var url_to = $.getSitePath() + '/clientpartinfo/${client._id_m }';
	
	 $.ajax({
        type: 'POST',
        url: url_to,
        data: {
            ts: new Date().getTime()
        },
        type: 'POST',
        dataType: 'json',
        success: function(data) {
       	   client = data;
       	   initBase();
        },
        complete: function(XMLHttpRequest, textStatus) {
        }
    });
}

//-----------------------------------------------------------------------------------------------income

var income_prop_title = [];
<c:forEach var="title" items="${income_prop_title}">
	income_prop_title.push("${title}");
</c:forEach>
//alert(base_prop_title.join("\n"));

var income_prop_name = [];
<c:forEach var="title_name" items="${income_prop_name}">
	income_prop_name.push("${title_name}");
</c:forEach>
//$.alertObjJson(base_prop_name);

// 生成模块内容
function genIncomeContent(client){
	var content = genContent(client,income_prop_title,income_prop_name);
	$("#income_info_content").html(content);
}

function toEditIncome(){
	
	var url = $.getSitePath() + '/clientpartinfo/' + $("#_id_m").val() + "/update?part_flg=3";
	
	$.popUpWindow("编辑客户收入信息", url, "90%", "80%", "edit", $("#edit_income"));
}

function closeEditIncome(){
	$.closeWindow("edit", $("#edit_income"));
}
function initIncome(){
	genIncomeContent(client);
	
	$('#edit_income').unbind();
	$("#edit_income").bind("click", toEditIncome);
}

function refreshIncome(){
	
	var url_to = $.getSitePath() + '/clientpartinfo/${client._id_m }';
	
	 $.ajax({
        type: 'POST',
        url: url_to,
        data: {
            ts: new Date().getTime()
        },
        type: 'POST',
        dataType: 'json',
        success: function(data) {
       	   client = data;
       	   initIncome();
        },
        complete: function(XMLHttpRequest, textStatus) {
        }
    });
}

//-----------------------------------------------------------------------------------------------family

var family_prop_title = [];
<c:forEach var="title" items="${family_prop_title}">
	family_prop_title.push("${title}");
</c:forEach>
//alert(base_prop_title.join("\n"));

var family_prop_name = [];
<c:forEach var="title_name" items="${family_prop_name}">
	family_prop_name.push("${title_name}");
</c:forEach>
//$.alertObjJson(base_prop_name);

// 生成基本模块内容
function genFamilyContent(client){
	var content = genContent(client,family_prop_title,family_prop_name);
	$("#family_info_content").html(content);
}

function toEditFamily(){
	
	var url = $.getSitePath() + '/clientpartinfo/' + $("#_id_m").val() + "/update?part_flg=1";
	
	//alert(url);
	$.popUpWindow("编辑客户家庭信息", url, "90%", "60%", "edit", $("#edit_family"));
}

function closeEditFamily(){
	
	$.closeWindow("edit", $("#edit_family"));
}
function initFamily(){
	genFamilyContent(client);
	
	$('#edit_family').unbind();
	$("#edit_family").bind("click", toEditFamily);
}

function refreshFamily(){
	
	var url_to = $.getSitePath() + '/clientpartinfo/${client._id_m }';
	
	 $.ajax({
        type: 'POST',
        url: url_to,
        data: {
            ts: new Date().getTime()
        },
        type: 'POST',
        dataType: 'json',
        success: function(data) {
       	   client = data;
       	   initFamily();
        },
        complete: function(XMLHttpRequest, textStatus) {
        }
    });
}

//-----------------------------------------------------------------------------------------------source

var source_prop_title = [];
<c:forEach var="title" items="${source_prop_title}">
	source_prop_title.push("${title}");
</c:forEach>

var source_prop_name = [];
<c:forEach var="title_name" items="${source_prop_name}">
	source_prop_name.push("${title_name}");
</c:forEach>

// 生成模块内容
function genSourceContent(client){
	var content = genContent(client,source_prop_title,source_prop_name);
	$("#source_info_content").html(content);
}

function toEditSource(){
	
	var url = $.getSitePath() + '/clientpartinfo/' + $("#_id_m").val() + "/update?part_flg=4";
	
	$.popUpWindow("编辑客户收入信息", url, "90%", "70%", "edit", $("#edit_source"));
}

function closeEditSource(){
	$.closeWindow("edit", $("#edit_source"));
}
function initSource(){
	genSourceContent(client);
	
	$('#edit_source').unbind();
	$("#edit_source").bind("click", toEditSource);
}

function refreshSource(){
	
	var url_to = $.getSitePath() + '/clientpartinfo/${client._id_m }';
	
	 $.ajax({
        type: 'POST',
        url: url_to,
        data: {
            ts: new Date().getTime()
        },
        type: 'POST',
        dataType: 'json',
        success: function(data) {
       	   client = data;
       	   initSource();
        },
        complete: function(XMLHttpRequest, textStatus) {
        }
    });
}

//-----------------------------------------------------------------------------------------------work

var work_prop_title = [];
<c:forEach var="title" items="${work_prop_title}">
	work_prop_title.push("${title}");
</c:forEach>

var work_prop_name = [];
<c:forEach var="title_name" items="${work_prop_name}">
	work_prop_name.push("${title_name}");
</c:forEach>

// 生成模块内容
function genWorkContent(client){
	var content = genContent(client,work_prop_title,work_prop_name);
	$("#work_info_content").html(content);
}

function toEditWork(){
	
	var url = $.getSitePath() + '/clientpartinfo/' + $("#_id_m").val() + "/update?part_flg=2";
	
	$.popUpWindow("编辑客户收入信息", url, "90%", "60%", "edit", $("#edit_work"));
}

function closeEditWork(){
	$.closeWindow("edit", $("#edit_work"));
}
function initWork(){
	genWorkContent(client);
	
	$('#edit_work').unbind();
	$("#edit_work").bind("click", toEditWork);
}

function refreshWork(){
	
	var url_to = $.getSitePath() + '/clientpartinfo/${client._id_m }';
	
	 $.ajax({
        type: 'POST',
        url: url_to,
        data: {
            ts: new Date().getTime()
        },
        type: 'POST',
        dataType: 'json',
        success: function(data) {
       	   client = data;
       	   initWork();
        },
        complete: function(XMLHttpRequest, textStatus) {
        }
    });
}

//-----------------------------------------------------------------------------------------------xg

var xg_prop_title = [];
<c:forEach var="title" items="${xg_prop_title}">
	xg_prop_title.push("${title}");
</c:forEach>

var xg_prop_name = [];
<c:forEach var="title_name" items="${xg_prop_name}">
	xg_prop_name.push("${title_name}");
</c:forEach>

// 生成模块内容
function genXgContent(client){
	var content = genContent(client,xg_prop_title,xg_prop_name);
	$("#xg_info_content").html(content);
}

function toEditXg(){
	
	var url = $.getSitePath() + '/clientpartinfo/' + $("#_id_m").val() + "/update?part_flg=5";
	
	$.popUpWindow("编辑客户收入信息", url, "90%", "80%", "edit", $("#edit_xg"));
}

function closeEditXg(){
	$.closeWindow("edit", $("#edit_xg"));
}
function initXg(){
	genXgContent(client);
	
	$('#edit_xg').unbind();
	$("#edit_xg").bind("click", toEditXg);
}

function refreshXg(){
	
	var url_to = $.getSitePath() + '/clientpartinfo/${client._id_m }';
	
	 $.ajax({
        type: 'POST',
        url: url_to,
        data: {
            ts: new Date().getTime()
        },
        type: 'POST',
        dataType: 'json',
        success: function(data) {
       	   client = data;
       	   initXg();
        },
        complete: function(XMLHttpRequest, textStatus) {
        }
    });
}

function setPWindowH(part_flag,H){
	
	var key = "edit";
	var $obj = null;
	
	if (part_flag == 0){
		$obj = $("#edit_base");
	}else if (part_flag == 1){
		$obj = $("#edit_family");
	}else if (part_flag == 2){
		$obj = $("#edit_work");
	}else if (part_flag == 3){
		$obj = $("#edit_income");
	}else if (part_flag == 4){
		$obj = $("#edit_source");
	}else if (part_flag == 5){
		$obj = $("#edit_xg");
	}else if (part_flag == 6){
		$obj = $("#edit_source");
	}
	
	$.setPWindowH(key,$obj,H);
}

$().ready(function() {
	
	$("button").each(function(e){
		$(this).css("margin-left","10px");
		$(this).addClass("btn-sm");
		$(this).addClass("btn-primary");
	})
	
	initBase();
	initFamily();
	initIncome();
	initSource();
	initWork();
	initXg();
});

</script>
<input type="hidden" name="ctx" value="<%=request.getContextPath()%>" />
<input type='hidden' id="_id_m" value="${client._id_m }" />
<input type='hidden' id="client_name" value="${client.client_name }" />
<input type='hidden' id="client_sex" value="${client.sex }" />
<div class="container-fluid">
  <div class="row">
   	<div class="col-md-6">
   		<div class="row">
		   	<div class="col-md-12">
		   		<div class="row">
		   			<div class="col-md-11 col-md-offset-1">
		   				<div class="panel panel-info">
							<div class="panel-heading">基本信息<button type="button" id="edit_base" class="btn btn-default pull-right"  >编辑</button></div>
							<div class="panel-body" id="base_info_content">
							</div>
						</div>
		   			</div>
		   		</div>
		   		<div class="row">
		   			<div class="col-md-11 col-md-offset-1">
		   				<div class="panel panel-info">
							<div class="panel-heading">家庭信息<button type="button" id="edit_family" class="btn btn-default pull-right">编辑</button></div>
							<div class="panel-body" id="family_info_content">
							</div>
						</div>
		   			</div>
		   		</div>
		   		<div class="row">
		   			<div class="col-md-11 col-md-offset-1">
		   				<div class="panel panel-info">
							<div class="panel-heading">收入相关<button type="button" id="edit_income" class="btn btn-default pull-right">编辑</button></div>
							<div class="panel-body" id="income_info_content">
							</div>
						</div>
		   			</div>
		   		</div>
		   	</div>
		</div>
   	</div>
  	<div class="col-md-6">
		<div class="row">
		   	<div class="col-md-12">
		   		<div class="row">
		   			<div class="col-md-11 ">
		   				<div class="panel panel-info">
							<div class="panel-heading">来源信息<button type="button" id="edit_source" class="btn btn-default pull-right">编辑</button></div>
							<div class="panel-body" id="source_info_content">
							</div>
						</div>
		   			</div>
		   		</div>
		   		<div class="row">
		   			<div class="col-md-11 ">
		   				<div class="panel panel-info">
							<div class="panel-heading">工作信息<button type="button" id="edit_work" class="btn btn-default pull-right">编辑</button></div>
							<div class="panel-body" id="work_info_content">
							</div>
						</div>
		   			</div>
		   		</div>
		   		<div class="row">
		   			<div class="col-md-11 ">
		   				<div class="panel panel-info">
							<div class="panel-heading">性格相关<button type="button" id="edit_xg" class="btn btn-default pull-right">编辑</button></div>
							<div class="panel-body" id="xg_info_content">
							</div>
						</div>
		   			</div>
		   		</div>
		   	</div>
		</div>
	</div>
  </div>
</div>



