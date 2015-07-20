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

<div id="edit_div" class="onepage" style="margin-top: 30px">
    <input type="hidden" name="_id" value="${clientbaseinfo._id}"/>
    <sf:form modelAttribute="clientbaseinfo" class="form-horizontal center-block " style="width: 500px">
  			<div class="form-group ">
	            <label for="owner_user_id" class="col-sm-3 control-label">
	                归属用户id
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="owner_user_id" name="owner_user_id" value="${clientbaseinfo.owner_user_id}"  placeholder="" >
	            </div>
	        </div>
  			<div class="form-group ">
	            <label for="client_name" class="col-sm-3 control-label">
	                客户姓名
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="client_name" name="client_name" value="${clientbaseinfo.client_name}"  placeholder="" >
	            </div>
	        </div>
  			<div class="form-group ">
	            <label for="sex" class="col-sm-3 control-label">
	                性别
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="sex" name="sex" value="${clientbaseinfo.sex}"  placeholder="" >
	            </div>
	        </div>
  			<div class="form-group ">
	            <label for="id_number" class="col-sm-3 control-label">
	                身份证号
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="id_number" name="id_number" value="${clientbaseinfo.id_number}"  placeholder="" >
	            </div>
	        </div>
  			<div class="form-group ">
	            <label for="birth_date" class="col-sm-3 control-label">
	                生日
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="birth_date" name="birth_date" value="${clientbaseinfo.birth_date}"  placeholder="" >
	            </div>
	        </div>
  			<div class="form-group ">
	            <label for="age" class="col-sm-3 control-label">
	                年龄
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="age" name="age" value="${clientbaseinfo.age}"  placeholder="" >
	            </div>
	        </div>
  			<div class="form-group ">
	            <label for="email_info" class="col-sm-3 control-label">
	                邮箱
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="email_info" name="email_info" value="${clientbaseinfo.email_info}"  placeholder="" >
	            </div>
	        </div>
  			<div class="form-group ">
	            <label for="phone_info" class="col-sm-3 control-label">
	                电话
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="phone_info" name="phone_info" value="${clientbaseinfo.phone_info}"  placeholder="" >
	            </div>
	        </div>
  			<div class="form-group ">
	            <label for="address_info" class="col-sm-3 control-label">
	                地址
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="address_info" name="address_info" value="${clientbaseinfo.address_info}"  placeholder="" >
	            </div>
	        </div>
  			<div class="form-group ">
	            <label for="region_code" class="col-sm-3 control-label">
	                地区码
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="region_code" name="region_code" value="${clientbaseinfo.region_code}"  placeholder="" >
	            </div>
	        </div>
  			<div class="form-group ">
	            <label for="region_name" class="col-sm-3 control-label">
	                地区名
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="region_name" name="region_name" value="${clientbaseinfo.region_name}"  placeholder="" >
	            </div>
	        </div>
  			<div class="form-group ">
	            <label for="region_type" class="col-sm-3 control-label">
	                地区分类
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="region_type" name="region_type" value="${clientbaseinfo.region_type}"  placeholder="" >
	            </div>
	        </div>
  			<div class="form-group ">
	            <label for="education_type" class="col-sm-3 control-label">
	                教育程度
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="education_type" name="education_type" value="${clientbaseinfo.education_type}"  placeholder="" >
	            </div>
	        </div>
  			<div class="form-group ">
	            <label for="name_card_id" class="col-sm-3 control-label">
	                名片id
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="name_card_id" name="name_card_id" value="${clientbaseinfo.name_card_id}"  placeholder="" >
	            </div>
	        </div>
      	
        <hr />
        <div class="col-sm-12">
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

    var closeEditWindow=function(){
    	parent.data_manage_functions.refreshPage();
    	parent.data_manage_functions.closeEditWindow();
    }
    //保存
    var save = function() {

        // 控制按钮为禁用
        $.disableButton("btn_save");

        var paramForm = $('form').getFormParam_ux();

        var successstr = "修改成功";

        var url_to = window.location.href ;

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
                        $.showBRErrors_mou_abs(data['brErrors'], $("#edit_div"));
                    } else {
                    	$.alertError(data['message']);
                    }
                } else {
                    $.alertSuccessCallback("修改成功", successstr, closeEditWindow);
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