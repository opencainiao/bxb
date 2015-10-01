<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script type="text/javascript"
	src="http://cdn.bootcss.com/jquery/1.11.1/jquery.js"></script>
<script>
	window.jQuery || document.write('<script src="${ctx}/resources/gentelella/production/js/jquery.min.js" type="text/javascript"><\/script>');
</script>

<script type="text/javascript"
	src="http://cdn.bootcss.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<!-- gauge js -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/gauge/gauge.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/gauge/gauge_demo.js"></script>
<!-- chart js -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/chartjs/chart.min.js"></script>
<!-- bootstrap progress js -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/progressbar/bootstrap-progressbar.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/nicescroll/jquery.nicescroll.min.js"></script>
<!-- icheck -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/icheck/icheck.min.js"></script>
<!-- daterangepicker -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/moment.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/datepicker/daterangepicker.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/flot/jquery.flot.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/flot/jquery.flot.pie.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/flot/jquery.flot.orderBars.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/flot/jquery.flot.time.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/flot/date.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/flot/jquery.flot.spline.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/flot/jquery.flot.stack.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/flot/curvedLines.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/flot/jquery.flot.resize.js"></script>
<!-- worldmap -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/maps/jquery-jvectormap-2.0.1.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/maps/gdp-data.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/maps/jquery-jvectormap-world-mill-en.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/maps/jquery-jvectormap-us-aea-en.js"></script>

<!-- skycons -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/skycons/skycons.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/gentelella/production/js/custom.js"></script>

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

	});
</script>

