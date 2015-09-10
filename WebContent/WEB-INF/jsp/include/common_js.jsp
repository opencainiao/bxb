<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script type="text/javascript"
	src="http://cdn.bootcss.com/jquery/2.1.3/jquery.js"></script>
<script>
	window.jQuery || document.write('<script src="<%=request.getContextPath()%>/resources/js/jquery-2.1.3.min.js" type="text/javascript"><\/script>');
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

<script>
	$().ready(function() {

		document.onkeydown = function(event) {
			if (event.keyCode == 13) {
				return false;
			}
		}

		//处理面板的折叠
		$(".spncollapse", $(".panel-heading")).click(function() {

			alert("11");
			//$(this).parent().parent().remove();
		});
	});
</script>