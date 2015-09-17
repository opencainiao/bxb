// 创建一个闭包    
/*******************************************************************************
 * 多选面板插件
 * 
 * NBQ 2015-09-12
 * 
 * @param $
 */
(function($) {
	// 插件的定义
	$.fn.multiselectpanel = function(options) {
		// build main options before element iteration
		var opts = $.extend({}, $.fn.multiselectpanel.defaults, options);

		var checkbox_name = opts["name"];
		var ipt_id = opts["ipt_id"];
		
		var $ipt = $("#" + ipt_id);
		
		$ipt.data("checkbox_name",checkbox_name);
		$ipt.data("checked",{});
		// iterate and reformat each matched element
		return this.each(function() {
			$this = $(this);

			var chtml = createMultiCheckHtml(opts);

			// 创建一个 dialog弹出框(第三方插件有兴趣可以看下 超赞的一款插件
			// http://www.planeart.cn/demo/artDialog/) 把创建好的弹出框隐藏起来
			var dia = $.dialog({
				title : "请选择",
				width : "600px",
				content : chtml,
				close : function() {
					this.hide();
					return false;
				},
				follow : $this[0]
			}).hide();

			$this.click(function() {
				dia.show();
			})

			// 事件 获取checkbox点击时候的父元素的值 添加到text 如果点击收的选中状态为checked 则添加 否则 删除
			$("input[type=checkbox][name=" + checkbox_name + "]").click(
					
					function() {
						var $ipt = $("#" + ipt_id);
						try {
							
							var text = $(this).attr("data-value");
							var value = $(this).attr("value");
							
							if ($(this)[0].checked) {
								
								$ipt.val($ipt.val() + $(this).parent().text()
										+ ";");
								
								$ipt.data("checked")[value] = 1;
							} else {
								$ipt.val($ipt.val().replaceAll(
										$(this).parent().text() + ';', ""));
								
								$ipt.data("checked")[value] = 0;
							}
							
							//$ipt.getSelected();
						} catch (e) {
							$ipt.val("");
						}
					});
		});
	};
	
	$.fn.getSelected = function() {
		
		var data = $(this).data("checked");
		
		var data_checked = [];
		
		if (data){
			
			for(var item in data){
				var value = data[item];
				
				if (value == 1){
					data_checked.push(item);
				}
			}
		}
		
		//$.logJson(data_checked);
		
		return data_checked;
	};
	
	function createMultiCheckHtml(config) {

		var chtml = "";
		var arr = config["data"]
		var text_key = config["text"];
		var value_key = config["value"];
		var name_key = config["name"];

		var ipt_id = config["ipt_id"];
		var $ipt = $("#" + ipt_id);
		$ipt.data("values", arr);
		$ipt.data("text_key", text_key);
		$ipt.data("value_key", value_key);
		
		for (var i = 0; i < arr.length; i++) {

			chtml += "<div style='word-wrap:break-word; width:450px; '>";
			chtml += '<label style="float:left;padding:15px"><input type="checkbox" name="#NAME#" value="#VALUE#" class="{required:true}" data-value="#TEXT#"/><span style="margin-left:10px">#TEXT#</span></label>';
			chtml += "</div>";

			chtml = chtml.replaceAll("#TEXT#", arr[i][text_key]);
			chtml = chtml.replaceAll("#VALUE#", arr[i][value_key]);
			chtml = chtml.replaceAll("#NAME#", name_key);
		}

		//$.logJson(chtml);
		return chtml;
	}
	
	$.fn.initVal = function(values) {
		
		var checkbox_name = $(this).data("checkbox_name");
		var arr = $(this).data("values");
		var text_key = $(this).data("text_key") ;
		var value_key = $(this).data("value_key") ;
		
		if (values && values.length>0){
			
			for (var i=0 ; i<values.length; ++i){
				var val_this = values[i];
				
				$("input[type=checkbox][name=" + checkbox_name + "][value="+ val_this +"]").trigger('click');
			}
		}
	}
	
	;
	// 定义暴露format函数
	$.fn.multiselectpanel.format = function(txt) {
		return '<strong>' + txt + '</strong>';
	};
	// 插件的defaults
	$.fn.multiselectpanel.defaults = {
		"data" : [],
		"text" : "text",
		"value" : "value",
		"name" : "name"
	};
	// 闭包结束
})(jQuery);
;;
(function($) {
	$.extend({
		/***********************************************************************
		 * 从系统常量中，初始化select
		 * 
		 * TYPECODE - 常量类型码 <br>
		 * ID - 元素ID <br>
		 * ALLOW_NULL - 是否提示（请选择）
		 */
		iniMultySelectPanelConstant : function(config) {
			
			$(".multiselectpanel").each(function(){
				
				var _id = $(this).attr("id");
				var name = $(this).attr("data-name");
				var typecode = $(this).attr("data-typecode");
				
				//$.logJson(data_remote);
				//return;
				var url = $.getSitePath() + '/backend/sysconst/all_const_of_consttype?typecode=#TYPECODE#';
				url = url.replace('#TYPECODE#', typecode);

				$.ajax({
					type : 'POST',
					url : url,
					dataType : 'json',
					async : false,
					success : function(data) {

						if (!$.isArray(data)) {
							alert(data["message"]);
							return;
						}
						var data_remote = data;
						//$.logJson(data_remote);
						$("#"+ _id).multiselectpanel({
							"ipt_id":_id,
							"name" : name,
							"data" : data_remote,
							"text" : "dspval",
							"value" : "val"
						});
					},
					complete : function(XMLHttpRequest, textStatus) {
					}
				});
			});
		}
	});
})(jQuery);
;;

$().ready(function() {
	$.iniMultySelectPanelConstant();
});