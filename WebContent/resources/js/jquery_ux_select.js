jQuery.fn.size = function() {
	return jQuery(this).get(0).options.length;
};
// 获得选中项的索引
jQuery.fn.getSelectedIndex = function() {
	return jQuery(this).get(0).selectedIndex;
};
// 获得当前选中项的文本
jQuery.fn.getSelectedText = function() {
	if (this.size() == 0) {
		return "";
	} else {
		var index = this.getSelectedIndex();
		return jQuery(this).get(0).options[index].text;
	}
};
// 获得当前选中项的值
jQuery.fn.getSelectedValue = function() {
	if (this.size() == 0) {
		return "";
	} else {
		return jQuery(this).val();
	}
};
// 设置select中值为value的项为选中
jQuery.fn.setSelectedValue = function(value) {
	
	//console.log(value);
	
	jQuery(this).get(0).value = value;
};
// 设置select中文本为text的项被选中
jQuery.fn.setSelectedText = function(text) {
	var isExist = false;
	var count = this.size();
	for (var i = 0; i < count; i++) {
		if (jQuery(this).get(0).options[i].text == text) {
			jQuery(this).get(0).options[i].selected = true;
			isExist = true;
			// break; (只设置第一个被选中)
		}
	}
	if (!isExist) {
		alert("下拉框中不存在该项");
	}
};
// 设置选中指定索引项
jQuery.fn.setSelectedIndex = function(index) {
	var count = this.size();
	if (index >= count || index < 0) {
		alert("选中项索引超出范围");
	} else {
		jQuery(this).get(0).selectedIndex = index;
	}
};
// 判断select项中是否存在值为value的项
jQuery.fn.isExistItem = function(value) {
	var isExist = false;
	var count = this.size();
	for (var i = 0; i < count; i++) {
		if (jQuery(this).get(0).options[i].value == value) {
			isExist = true;
			break;
		}
	}
	return isExist;
};
// 向select中添加一项，显示内容为text，值为value,如果该项值已存在，则提示
jQuery.fn.addOptionLast = function(text, value) {
	if (this.isExistItem(value)) {
		// alert("待添加项的值已存在");
		return;
	} else {
		jQuery(this).get(0).options.add(new Option(text, value));
	}
};
// 为Select插入一个Option(第一个位置)，显示内容为text，值为value
jQuery.fn.addOptionFirst = function(text, value) {
	if (this.isExistItem(value)) {
		// alert("待添加项的值已存在");
		return;
	} else {
		jQuery(this).prepend(new Option(text, value));
	}
};
// 删除select中值为value的项
jQuery.fn.removeItem = function(value) {

	var count = this.size();
	for (var i = 0; i < count; i++) {
		if (jQuery(this).get(0).options[i].value == value) {
			jQuery(this).get(0).remove(i);
			break;
		}
	}
};
// 删除select中指定索引的项
jQuery.fn.removeIndex = function(index) {
	var count = this.size();
	if (index >= count || index < 0) {
		alert("待删除项索引超出范围");
	} else {
		jQuery(this).get(0).remove(index);
	}
};
// 删除select中选定的项
jQuery.fn.removeSelected = function() {
	var index = this.getSelectedIndex();
	this.removeIndex(index);
};
// 清除select中的所有项
jQuery.fn.clearAll = function() {
	jQuery(this).get(0).options.length = 0;
};

// 用给定的JSON数据初始化下拉列表
jQuery.fn.iniSelect_noAll = function(data, setting) {

	var text = "text";
	var value = "value";

	this.clearAll();
	if (setting != null && setting != undefined) {
		text = setting[text];
		value = setting[value];
	}
	var dropdownList = this;

	$.each(data, function() {
		dropdownList.addOptionLast(this[text], this[value]);
	});
};

// 添加请选择
jQuery.fn.iniSelect_All = function(data, setting) {

	//console.log(JSON.stringify(data));
	
	var text = "text";
	var value = "value";

	this.clearAll();
	if (setting != null && setting != undefined) {
		text = setting[text];
		value = setting[value];
	}
	var dropdownList = this;
	dropdownList.addOptionLast("请选择", "-1");

	$.each(data, function() {
		
		dropdownList.addOptionLast(this[text], this[value]);
	});
};

/*******************************************************************************
 * <select id="education_type" name="education_type" data-src="constant"
 * data-typecode="SYS_MODULE" data-allownull class="form-control"
 * data-value="${clientbaseinfo.education_type}"></select>
 * 
 * data-allownull -- 只要有该属性，就包含 请选择
 * 没有该属性，不包含请选择
 */
;;
(function($) {
	$.extend({
		iniPageSelectConstant : function() {
			$("select").each(function(e) {
				$this = $(this);

				var data_src = $this.attr("data-src");
				var id = $this.attr("id");
				var typecode = $this.attr("data-typecode");
				var allow_null = $this.attr("data-allownull");
				if (allow_null != null) {
					allow_null = "1";
				} else {
					allow_null = "0";
				}

				var config = {
					"TYPECODE" : typecode,
					"ID" : id,
					"ALLOW_NULL" : allow_null
				};

				if (data_src != null && data_src == "constant") {
					$.iniSelectConstant(config);
				}
			});
		},
		/***********************************************************************
		 * 从系统常量中，初始化select
		 * 
		 * TYPECODE - 常量类型码 <br>
		 * ID - 元素ID <br>
		 * ALLOW_NULL - 是否提示（请选择）
		 */
		iniSelectConstant : function(config) {
			
			//$.alertObjJson(config);

			var typecode = config["TYPECODE"];
			var url = $.getSitePath() + '/backend/sysconst/all_const_of_consttype?typecode=#TYPECODE#';
			url = url.replace('#TYPECODE#', typecode);

			var _id = config["ID"];
			var _obj = $("#" + _id);
			var empty_select_flag = config["ALLOW_NULL"];

			$.ajax({
				type : 'POST',
				url : url,
				type : 'POST',
				dataType : 'json',
				success : function(data) {

					if (!$.isArray(data)) {
						alert(data["message"]);
						return;
					}
					var data_remote = data;
					var setting = {
						"text" : "dspval",
						"value" : "val"
					}

					if (empty_select_flag == "1") {
						_obj.iniSelect_All(data_remote, setting);
					} else {
						_obj.iniSelect_noAll(data_remote, setting);
					}

					// 设置值
					var val = _obj.attr("data-value");

					_obj.setSelectedValue(val);
				},
				complete : function(XMLHttpRequest, textStatus) {
				}
			});
		}
	});
})(jQuery);
;;

$().ready(function() {
	$.iniPageSelectConstant();
});
