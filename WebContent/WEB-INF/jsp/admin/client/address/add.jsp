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
    <li><a href="<%=request.getContextPath()%>/backend/address/list">地址管理</a> <span class="divider"></span></li>
    <li class="active">添加地址</li>
</ul>
<div id="add_div" class="onepage">
    <input type="hidden" name="_id" />
    <sf:form modelAttribute="address" class="form-horizontal">
    	
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
	                地址类型值
	            </label>
	            <div class="col-sm-5">
	                <input type="text" class="form-control" id="type_value" name="type_value" value="${default_address_type_value }" >
	            </div>
	        </div>
      		<div class="form-group ">
	            <label for="type_name" class="col-sm-2 control-label">
	                地址类型 
	            </label>
	            <div class="col-md-5">
					<div class="input-group">
						<input name="type_name" id="type_name" type="text" class="form-control"
							value="${default_address_type_name }" placeholder="选择地址类型" readonly> <span
							class="input-group-btn">
							<button id="choose_addresstype" class="btn btn-default" type="button">
								<span class="glyphicon glyphicon-play" aria-hidden="true"></span>
							</button>
						</span>
					</div>
				</div>
	        </div>
      		<div class="form-group ">
	            <label for="province" class="col-sm-2 control-label">
	                省
	            </label>
	            <input type="hidden" id="province" name="province"/>
	            <div class="col-md-5">
					<div class="input-group">
						<input name="province_name" id="province_name" type="text" class="form-control"
							value="" placeholder="选择" readonly> <span
							class="input-group-btn">
							<button id="choose_province" class="btn btn-default" type="button">
								<span class="glyphicon glyphicon-play" aria-hidden="true"></span>
							</button>
						</span>
					</div>
				</div>
	        </div>
      		<div class="form-group ">
	            <label for="city" class="col-sm-2 control-label">
	                市
	            </label>
	            <input type="hidden" id="city" name="city"/>
	            <div class="col-md-5">
					<div class="input-group">
						<input name="city_name" id="city_name" type="text" class="form-control"
							value="" placeholder="选择" readonly> <span
							class="input-group-btn">
							<button id="choose_city" class="btn btn-default" type="button">
								<span class="glyphicon glyphicon-play" aria-hidden="true"></span>
							</button>
						</span>
					</div>
				</div>
	        </div>
      		<div class="form-group ">
	            <label for="district" class="col-sm-2 control-label">
	                区
	            </label>
	            <input type="hidden" id="district" name="district"/>
	            <div class="col-md-5">
					<div class="input-group">
						<input name="district_name" id="district_name" type="text" class="form-control"
							value="" placeholder="选择" readonly> <span
							class="input-group-btn">
							<button id="choose_district" class="btn btn-default" type="button">
								<span class="glyphicon glyphicon-play" aria-hidden="true"></span>
							</button>
						</span>
					</div>
				</div>
	        </div>
      		<div class="form-group ">
	            <label for="detail_address" class="col-sm-2 control-label">
	                详细地址
	            </label>
	            <div class="col-sm-5">
	                <input type="text" class="form-control" id="detail_address" name="detail_address" placeholder="" >
	            </div>
	        </div>
      		<div class="form-group ">
	            <label for="mainflg" class="col-sm-2 control-label">
	                是否常用地址
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
        
        $("#choose_addresstype").click(function(){
			pupUpChooseAddresstype();
		});
        
        $("#choose_province").click(function(){
			pupUpChoose_province();
		});
        
        $("#choose_city").click(function(){
        	
        	var province = $("#province").val().trim();
        	
        	if (province == ""){
        		$.alertError("请选择省");
        		return;
        	}
        	
        	pupUpChoose_city();
		});
        
 		$("#choose_district").click(function(){
        	
        	var city = $("#city").val().trim();
        	
        	if (city == ""){
        		$.alertError("请选择市");
        		return;
        	}
        	
        	pupUpChoose_district();
		});
    });
    
	// 关闭选择地址类型窗口
	function closeChooseAddresstypeWindow(){
		var $choose_addresstype = $("#choose_addresstype");
		var index = $choose_addresstype.attr("index");
				
		layer.close(index);
	}
	
    /****
	* 弹出选择单位框
	*/
	function pupUpChooseAddresstype() {
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
				src : "<%=request.getContextPath()%>/backend/addresstype/addresstype_reference?ts=" + new Date().getTime()
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
				var $choose_addresstype = $("#choose_addresstype");
				$choose_addresstype.attr("index", index);
			}
		})
	}
    
	function setAddressType(address_type_choosed){
		
		$("#type_value").val(address_type_choosed.type_value);
		$("#type_name").val(address_type_choosed.type_name);
	}

    //保存
    var save = function() {

        // 控制按钮为禁用
        $.disableButton("btn_save");

        var paramForm = $('form').getFormParam_ux();

        var successstr = "新增成功";

        var url_to = "<%=request.getContextPath()%>/backend/address/add";
        var url_success = "<%=request.getContextPath()%>/backend/address/list";

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
    
 	// 关闭选择省市类型窗口
	function closeChoose_provinceWindow(){
		var $choose_province = $("#choose_province");
		var index = $choose_province.attr("index");
				
		layer.close(index);
	}
	
    /****
	* 弹出选择省市框
	*/
	function pupUpChoose_province() {
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
				src : "<%=request.getContextPath()%>/backend/city/province_reference?ts=" + new Date().getTime()
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
				var $choose_province = $("#choose_province");
				$choose_province.attr("index", index);
			}
		})
	}
    
	function setProvince(province_choosed){
		
		$("#province").val(province_choosed.id);
		$("#province_name").val(province_choosed.name.trim());
	}
	
	
	//-----------------------市
	// 关闭选择省市类型窗口
	function closeChoose_cityWindow(){
		var $choose_city = $("#choose_city");
		var index = $choose_city.attr("index");
				
		layer.close(index);
	}
	
    /****
	* 弹出选择省市框
	*/
	function pupUpChoose_city() {
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
				src : "<%=request.getContextPath()%>/backend/city/city_reference?ts=" + new Date().getTime()
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
				var $choose_city = $("#choose_city");
				$choose_city.attr("index", index);
			}
		})
	}
    
	function setCity(city_choosed){
		
		$("#city").val(city_choosed.id);
		$("#city_name").val(city_choosed.name.trim());
	}
	
	//-----------------------区
	// 关闭选择省市类型窗口
	function closeChoose_districtWindow(){
		var $choose_district = $("#choose_district");
		var index = $choose_district.attr("index");
				
		layer.close(index);
	}
	
    /****
	* 弹出选择省市框
	*/
	function pupUpChoose_district() {
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
				src : "<%=request.getContextPath()%>/backend/city/district_reference?ts=" + new Date().getTime()
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
				var $choose_district = $("#choose_district");
				$choose_district.attr("index", index);
			}
		})
	}
    
	function setDistrict(district_choosed){
		
		$("#district").val(district_choosed.id);
		$("#district_name").val(district_choosed.name.trim());
	}
</script>
</body>
</html>