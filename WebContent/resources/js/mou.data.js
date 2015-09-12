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
		iniRegion : function(container) {

			var data_p = $.getProvince(); // 省一级数据
			// $.alertObjJson(data_p);

			var setting = {
				"text" : "name",
				"value" : "id"
			};

			$("select[name=province]", container).each(
					function() {

						$(this).iniSelect_All(data_p, setting);

						// 设置监听方法
						$(this).change(
								function() {

									var text = $(this).getSelectedText();
									var province_id = $(this)
											.getSelectedValue();

									// alert(text + "[---]" + province_id);

									$("#city", container).clearAll();
									$("#city2", container).clearAll();

									if (province_id != "-1") {
										// 初始化市的下拉列表
										var data_city1 = $
												.getCitys(province_id); // 市一级数据

										// $.alertObjJson(data_city1);
										$("#city", container).iniSelect_All(
												data_city1, setting);
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

					$("#city2", container).iniSelect_All(data_city2, setting);
				});
			});
		},

		/***********************************************************************
		 * 初始化页面的所有区域的级联行为
		 */
		iniRegions : function() {

			$(".regin-container").each(function() {
				$.iniRegion($(this));
			})
		}
	});
})(jQuery);
;
