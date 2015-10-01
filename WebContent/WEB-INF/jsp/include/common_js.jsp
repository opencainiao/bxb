<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script type="text/javascript"
	src="http://cdn.bootcss.com/jquery/2.1.3/jquery.js"></script>
<script>
	window.jQuery || document.write('<script src="${ctx}/resources/js/jquery-2.1.3.min.js" type="text/javascript"><\/script>');
</script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/mou.ajax.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/layer/layer.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/layer/extend/layer.ext.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.nbq.ux.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery_ux_select.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/mou.data.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/mou.multiselectpanel.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.artDialog.js"></script>


<script
	src="<%=request.getContextPath()%>/resources/laydate-v1.1/laydate/laydate.js"></script>

<style>
#laydate_YY {
	width: 123px !important;
	height: 26px !important;
}

#laydate_MM {
	width: 101px !important;
	height: 26px !important;
}

#laydate_table {
	border-top-width: 0px !important;
}

#laydate_box {
	width: 242px !important;
}

.laydate_ym   label {
	margin-right: -3px !important;
}

#laydate_ys {
	width: 124px !important;
}

div.laydate_yms {
	width: 124px !important;
}

.laydate_bottom {
	height: 30px !important;
}
</style>


<script>
	$().ready(function() {
		document.onkeydown = function(event) {
			if (event.keyCode == 13) {
				return false;
			}
		}

		$("a").bind("focus", function() {
			if (this.blur) {
				this.blur();
			}
		});

		//给面板添加折叠
		$(".panel-heading").each(function() {
			$(this).append('<span class="glyphicon glyphicon-chevron-down pull-right spncollapse"></span>');
		});

		//处理面板的折叠
		$(".spncollapse", $(".panel-heading")).click(function() {
			var content = $(this).parent().next();
			content.slideToggle(200);
		});

		// 将页面的按钮图标，换成小图标
		$("button", $(".navbar")).each(function(e) {
			$(this).addClass("btn-sm");
		})

	});
</script>