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
    <input type="hidden" name="_id" value="${addresstype._id}"/>
    <sf:form modelAttribute="addresstype" class="form-horizontal center-block " style="width: 500px">
  			<div class="form-group ">
	            <label for="owner_user_id" class="col-sm-3 control-label">
	                所属用户id
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="owner_user_id" name="owner_user_id" value="${addresstype.owner_user_id}"  placeholder=""  readonly>
	            </div>
	        </div>
	        <div class="form-group ">
		            <label for="address_type_value" class="col-sm-3 control-label">
		                地址类型值
		            </label>
		            <div class="col-sm-8">
		                <input type="text" class="form-control" id="address_type_value" name="address_type_value" value="${addresstype.address_type_value}"  >
		            </div>
		        </div>
  			<div class="form-group ">
	            <label for="address_type_name" class="col-sm-3 control-label">
	                地址类型名称
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="address_type_name" name="address_type_name" value="${addresstype.address_type_name}"  placeholder="" >
	            </div>
	        </div>
  			<div class="form-group ">
	            <label for="address_type_character" class="col-sm-3 control-label">
	                地址类型性质
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="address_type_character" name="address_type_character" value="${addresstype.address_type_character}"  placeholder=""  readonly>
	            </div>
	        </div>
      	<br>
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

        var paramForm = $("form").getFormParam_ux();

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