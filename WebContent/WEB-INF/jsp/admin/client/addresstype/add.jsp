<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<jsp:include page="/WEB-INF/jsp/include/common_css.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/include/common_js.jsp"></jsp:include>

</head>

<body>
<input type="hidden" name="ctx" value="<%=request.getContextPath()%>" />

<ul class="breadcrumb">
    <li><a href="<%=request.getContextPath()%>/backend/addresstype/list">地址类型管理</a> <span class="divider"></span></li>
    <li class="active">添加地址类型</li>
</ul>
<div id="add_div" class="onepage">
    <input type="hidden" name="_id" />
    <sf:form modelAttribute="addresstype" class="form-horizontal">
    	
      		<div class="form-group hide">
	            <label for="owner_user_id" class="col-sm-2 control-label">
	                所属用户id
	            </label>
	            <div class="col-sm-5">
	                <input type="text" class="form-control" id="owner_user_id" name="owner_user_id" placeholder="" >
	            </div>
	        </div>
	        <div class="form-group ">
	            <label for="type_value" class="col-sm-2 control-label">
	                地址类型值
	            </label>
	            <div class="col-sm-5">
	                <input type="text" class="form-control" id="type_value" name="type_value" placeholder="" >
	            </div>
	        </div>
      		<div class="form-group ">
	            <label for="type_name" class="col-sm-2 control-label">
	                地址类型名称
	            </label>
	            <div class="col-sm-5">
	                <input type="text" class="form-control" id="type_name" name="type_name" placeholder="" >
	            </div>
	        </div>
      		<div class="form-group hide">
	            <label for="type_character" class="col-sm-2 control-label">
	                地址类型性质
	            </label>
	            <div class="col-sm-5">
	                <input type="text" class="form-control" id="type_character" name="type_character" placeholder="" >
	            </div>
	        </div>
        
        <hr/>
        <div class="col-sm-7">
        	<button type="button" id="btn_save" class="btn btn-primary btn-lg center-block">提交</button>
        </div>
    </sf:form>
</div>
	
<script>
    $().ready(function() {
    	
        $("#btn_save").bind("click", save);
        
        document.onkeydown = function(event) {
    		if (event.keyCode == 13) {
    			return false;
    		}
    	}
    });

    //保存
    var save = function() {

        // 控制按钮为禁用
        $.disableButton("btn_save");

        var paramForm = $('form').getFormParam_ux();

        var successstr = "新增成功";

        var url_to = "<%=request.getContextPath()%>/backend/addresstype/add";
        var url_success = "<%=request.getContextPath()%>/backend/addresstype/list";

        $.ajax({
            type: 'POST',
            url: url_to,
            data: $.extend({
                ts: new Date().getTime()
            },
            paramForm),
            type: 'POST',
            dataType: 'json',
            success: function(data) {

                if (data['success'] == 'n') {
                    if (data['brErrors']) {
                        $.showBRErrors_mou_abs(data['brErrors'], $("#add_div"));
                    } else {
                    	$.alertError(data['message']);
                    }
                } else {
                    $.alertSuccessNewPage("成功", successstr, url_success);
                }
            },
            complete: function(XMLHttpRequest, textStatus) {
                $.enableButton("btn_save");
            }
        });
    };
</script>
</body>
</html>