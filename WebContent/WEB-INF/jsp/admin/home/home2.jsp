<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title></title>

<jsp:include page="/WEB-INF/jsp/include/common_css_gentelella.jsp"></jsp:include>
</head>

<script type="text/javascript">
function handIframeLoad() {
    autoHeight();
}

function autoHeight() {
    var iframe = document.getElementById("frame_content_id");

    var toShow = [];

    var iframeContentH = $(window.frames["frame_content_id"].document).height();

    toShow.push("iframeContentH[" + iframeContentH + "]");

    var toSetH = 0;

    var iframeContentH = 0;

    if (iframe.Document) { //ie自有属性
        iframeContentH = iframe.Document.documentElement.scrollHeight;
    } else if (iframe.contentDocument) { //ie,firefox,chrome,opera,safari
        iframeContentH = iframe.contentDocument.body.offsetHeight;
    }

    toShow.push("iframeContentH[" + iframeContentH + "]");

    var divH = $("#home_content").height();

    toShow.push("divH[" + divH + "]");

    if (iframeContentH > divH) {
        toSetH = iframeContentH;
    } else {
        toSetH = divH - 5;
    }
    
     alert(toShow.join("\n"));
    iframe.height = toSetH;
    
    if (iframe.Document) { //ie自有属性
        iframeDoc = iframe.Document;
    } else if (iframe.contentDocument) { //ie,firefox,chrome,opera,safari
        iframeDoc = iframe.contentDocument;
    }
    
    iframeDoc.height = toSetH - 5;
}

</script>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<jsp:include
				page="/WEB-INF/jsp/admin/include/common_nav_left_gentelella.jsp"></jsp:include>
			<jsp:include
				page="/WEB-INF/jsp/admin/include/common_nav_top_gentelella.jsp"></jsp:include>

			<!-- page content -->
			<div id="home_content" class="right_col" role="main">
				<iframe id="frame_content_id" name="frame_content_id"
					class="frame_content_container" width="100%"
					style="min-height: 500px" frameborder="0"> </iframe>

				<jsp:include
					page="/WEB-INF/jsp/admin/include/common_nav_footer_gentelella.jsp"></jsp:include>
			</div>
			<!-- /page content -->
		</div>
	</div>

	<div id="custom_notifications" class="custom-notifications dsp_none">
		<ul class="list-unstyled notifications clearfix"
			data-tabbed_notifications="notif-group">
		</ul>
		<div class="clearfix"></div>
		<div id="notif-group" class="tabbed_notifications"></div>
	</div>

	<jsp:include page="/WEB-INF/jsp/include/common_js_gentelella.jsp"></jsp:include>

	<script type="text/javascript">
    	
	    function refreshHeadImg(_id_m) {
	
			var new_src = $.getSitePath() + "/attachment/" + _id_m;
			
			$("#head_img_top").attr("src", new_src);
			$("#head_img_left").attr("src", new_src);
		}
    
        $(document).ready(function () {
            $("#frame_content_id").load(function(){  
            	var iframe_h = $.autoHeight("frame_content_id");
            });  
            
        	loadTree();
        	
        	initTopNavBehave();
        	
        	$.setSitePath("${ctx}");
        	$.setAllConstants(eval(${ALLCONSTANT}));
        	
        	var sysmodule = $.getConstants("SYS_MODULE");
        	//$.alertObjJson(sysmodule);
        	
        	//$.alertObjJson($.getConstantName("SYS_MODULE","02"));
        });
    </script>
</body>

</html>
