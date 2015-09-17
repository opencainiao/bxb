// 封装全局数据
;
(function($) {
	$.extend({
		/***********************************************************************
		 * 本地存储JSON对象
		 */
		saveJsonLocal : function(key, jsonData) {
			if (jsonData == null) {
				return;
			}
			localStorage.setItem(key, JSON.stringify(jsonData));
		},
		/***********************************************************************
		 * 取本地JSON对象
		 */
		getJsonLocal : function(key) {
			var local_data = localStorage.getItem(key);

			if (local_data == null) {
				return null;
			}

			return JSON.parse(local_data);
		},
		/***********************************************************************
		 * 获取省一级数据(同步)
		 * 
		 * @returns {Boolean}
		 */
		getProvince : function() {

			String
			key = "provinces";

			var local_data = $.getJsonLocal(key);
			if (local_data != null) {
				return local_data;
			}

			var url = $.getSitePath() + '/backend/city/provinces';

			$.ajax({
				type : 'POST',
				async : false,
				url : url,
				data : {
					ts : new Date().getTime()
				},
				dataType : 'json',
				success : function(data) {

					// $.alertObjJson(data);
					$.saveJsonLocal(key, data);
				}
			});

			return $.getJsonLocal(key);
		},
		/***********************************************************************
		 * 获取下一级子节点（市）
		 */
		getCitys : function(pId) {

			var key = "city_children_" + pId;

			var local_data = $.getJsonLocal(key);
			if (local_data != null) {
				return local_data;
			}

			var url = $.getSitePath() + '/backend/city/citys?parent_id=' + pId
					+ '&ts=' + new Date().getTime();

			$.ajax({
				type : 'POST',
				async : false,
				url : url,
				data : {
					ts : new Date().getTime()
				},
				dataType : 'json',
				success : function(data) {
					// $.alertObjJson(data);
					$.saveJsonLocal(key, data);
				}
			});

			return $.getJsonLocal(key);
		},
		/***********************************************************************
		 * 初始化一个地区区域的js对象片段的级联行为
		 */
		iniRegion : function(container,config) {

			var data_p = $.getProvince(); // 省一级数据
			//console.log("config" + JSON.stringify(config));

			var setting = $.extend({
				"text" : "name",
				"value" : "id",
				province : -1,
				city : -1,
				district : -1,
				detail_address : ''
			}, config);
			
			//console.log("setting---" +JSON.stringify(setting));
			
			$("select[name=province]", container).each(
					function() {
						$(this).iniSelect_All(data_p,setting);
						
						//console.log(setting.province);
						$(this).setSelectedValue(setting.province);
						
						
						// 设置监听方法
						$(this).change(
								function() {
									var text = $(this).getSelectedText();
									var province_id = $(this)
											.getSelectedValue();

									// alert(text + "[---]" + province_id);

									$("#city", container).clearAll();
									$("#district", container).clearAll();

									if (province_id != "-1") {
										// 初始化市的下拉列表
										var data_city1 = $
												.getCitys(province_id); // 市一级数据

										// $.alertObjJson(data_city1);
										$("#city", container).iniSelect_All(
												data_city1, setting);
										
										if (setting.province == province_id){
											$("#city", container).setSelectedValue(setting.city);
											$("#city", container).trigger('change');
										}
									}
								});
					});

			$("select[name=city]", container).each(function() {
 
				// 设置监听方法
				$(this).change(function() {

					var text = $(this).getSelectedText();
					var city_id = $(this).getSelectedValue();

					// alert(text + "[---]" + province_id);

					// 初始化市的下拉列表
					var data_city2 = $.getCitys(city_id); // 区县一级数据

					$("#district", container).iniSelect_All(data_city2, setting);
					
					if (setting.city == city_id){
						$("#district", container).setSelectedValue(setting.district);
					}
				});
			});
			
			if ($("#detail_address", container)){
				$("#detail_address", container).val(setting.detail_address);
			}
			
			$("select[name=province]", container).trigger('change');
		},

		/***********************************************************************
		 * 初始化页面的所有区域的级联行为
		 */
		iniRegions : function() {

			$(".regin-container").each(function() {
				$.iniRegion($(this));
			})
		},
		/***********************************************************************
		 * 初始化所有常量
		 */
		setAllConstants:function(data){
			$.saveJsonLocal("ALLCONSTANT",data);
		},
		/***********************************************************************
		 * 取typecode得所有常量<br>
		 * var sysmodule = $.getConstants("SYS_MODULE");
		 * $.alertObjJson(sysmodule);
		 */
		getConstants(typecode){
			
			var constant_all_typecode = $.getJsonLocal("ALLCONSTANT")[typecode];
			
			/* 远程获取下来的是数组（有序）
			 * 本地存储的是json对象（无序）
			 * 
			if (constant_all_typecode == null){
				
				$.logJson(constant_all_typecode,"从远程获取" + typecode);
				
				var url = $.getSitePath() + '/backend/sysconst/all_const_of_consttype?typecode=#TYPECODE#';
				url = url.replace('#TYPECODE#', typecode);

				$.ajax({
					type : 'POST',
					url : url,
					dataType : 'json',
					async : false,
					success : function(data) {
						if (!$.isArray(data)) {
							constant_all_typecode = [];
						}else{
							constant_all_typecode = data;
						}
					},
					complete : function(XMLHttpRequest, textStatus) {
					}
				});
			}
			*/
			
			return constant_all_typecode;
		},
		/***********************************************************************
		 * 取typecode的常量值为val的显示值 例如：
		 * $.alertObjJson($.getConstantName("SYS_MODULE","02"));
		 */
		getConstantName(typecode,val){
			return $.getConstants(typecode)[val];
		}
	});
})(jQuery);
;
