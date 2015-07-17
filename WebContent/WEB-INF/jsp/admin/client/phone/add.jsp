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
    <li><a href="<%=request.getContextPath()%>/backend/phone/list">电话管理</a> <span class="divider"></span></li>
    <li class="active">添加电话</li>
</ul>
<div id="add_div" class="onepage">
    <input type="hidden" name="_id" />
    <sf:form modelAttribute="phone" class="form-horizontal">
    	
      		<div class="form-group ">
	            <label for="owner_id" class="col-sm-2 control-label">
	                所有者id
	            </label>
	            <div class="col-sm-5">
	                <input type="text" class="form-control" id="owner_id" name="owner_id" placeholder="" >
	            </div>
	        </div>
      		<div class="form-group hide">
	            <label for="type_value" class="col-sm-2 control-label">
	                电话类型值
	            </label>
	            <div class="col-sm-5">
	                <input type="text" class="form-control" id="type_value" name="type_value" placeholder="" >
	            </div>
	        </div>
      		<div class="form-group ">
	            <label for="type_name" class="col-sm-2 control-label">
	                电话类型
	            </label>
	            <div class="col-md-5">
					<div class="input-group">
						<input name="type_name" id="type_name" type="text" class="form-control"
							value="${default_phone_type_name }" placeholder="选择电话类型" readonly> <span
							class="input-group-btn">
							<button id="choose_phonetype" class="btn btn-default" type="button">
								<span class="glyphicon glyphicon-play" aria-hidden="true"></span>
							</button>
						</span>
					</div>
				</div>
	        </div>
      		<div class="form-group ">
	            <label for="phone_number" class="col-sm-2 control-label">
	                联系电话
	            </label>
	            <div class="col-sm-5">
	                <input type="text" class="form-control" id="phone_number" name="phone_number" placeholder="" >
	            </div>
	        </div>
      		<div class="form-group ">
	            <label for="mainflg" class="col-sm-2 control-label">
	                是否常用电话
	            </label>
	            <div class="col-sm-5">
	            	<select  id="mainflg" name="mainflg"  class="form-control">
					  <option value="1">是</option>
					  <option value="0"  selected = "selected">否</option>
					</select>
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
        
        $("#choose_phonetype").click(function(){
			pupUpChoosephonetype();
		});
    });

 // 关闭选择地址类型窗口
	function closeChoosephonetypeWindow(){
		var $choose_addresstype = $("#choose_phonetype");
		var index = $choose_addresstype.attr("index");
				
		layer.close(index);
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
			closeBtn: [0, true],
			yes: function(index){
				layer.close(index);
				return;
			},
			no: function(index){
			},success : function(layero) {
				var popid = layero.selector;
				var index = $.getLayerIndex(popid);
				var $choose_addresstype = $("#choose_phonetype");
				$choose_addresstype.attr("index", index);
			}
		})
	}
    
	function setPhoneType(phone_type_choosed){
		
		$("#type_value").val(phone_type_choosed.type_value);
		$("#type_name").val(phone_type_choosed.type_name);
	}
	
    //保存
    var save = function() {

        // 控制按钮为禁用
        $.disableButton("btn_save");

        var paramForm = $('form').getFormParam_ux();

        var successstr = "新增成功";

        var url_to = "<%=request.getContextPath()%>/backend/phone/add";
        var url_success = "<%=request.getContextPath()%>/backend/phone/list";

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