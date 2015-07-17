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
    <input type="hidden" name="_id" value="${phone._id}"/>
    <sf:form modelAttribute="phone" class="form-horizontal center-block " style="width: 500px">
  			<div class="form-group ">
	            <label for="owner_id" class="col-sm-3 control-label">
	                所有者id
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="owner_id" name="owner_id" value="${phone.owner_id}"  placeholder=""  readonly>
	            </div>
	        </div>
  			<div class="form-group hide">
	            <label for="type_value" class="col-sm-3 control-label">
	                电话类型值
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="type_value" name="type_value" value="${phone.type_value}"  placeholder="" >
	            </div>
	        </div>
  			<div class="form-group ">
	            <label for="type_name" class="col-sm-3 control-label">
	                电话类型
	            </label>
	            <div class="col-md-8">
					<div class="input-group" style="width: 303px; margin-left: 125px;">
						<input name="type_name" id="type_name" type="text" class="form-control"
							value="${phone.type_name }" placeholder="选择电话类型" readonly> <span
							class="input-group-btn">
							<button id="choose_phonetype" class="btn btn-default" type="button">
								<span class="glyphicon glyphicon-play" aria-hidden="true"></span>
							</button>
						</span>
					</div>
				</div>
	        </div>
  			<div class="form-group ">
	            <label for="phone_number" class="col-sm-3 control-label">
	                联系电话
	            </label>
	            <div class="col-sm-8">
	                <input type="text" class="form-control" id="phone_number" name="phone_number" value="${phone.phone_number}"  placeholder="" >
	            </div>
	        </div>
  			<div class="form-group ">
	            <label for="mainflg" class="col-sm-3 control-label">
	                是否常用电话
	            </label>
	            <div class="col-sm-8">
	            	<select  id="mainflg" name="mainflg"  class="form-control">
					  <option value="1">是</option>
					  <option value="0"  selected = "selected">否</option>
					</select>
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
        
        $("#choose_phonetype").click(function(){
        	window.top.pupUpChoosephonetype();
		});
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