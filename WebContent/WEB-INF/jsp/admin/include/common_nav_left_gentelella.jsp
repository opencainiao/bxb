<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<style>
.top_left_right {
	position: fixed;
	left: 170px;
	top: 0px;
}

.top_left_right_sm {
	position: fixed;
	left: 80px;
	top: 0px;
}
</style>
<div class="col-md-3 left_col">
	<div class="left_col scroll-view">

		<div class="navbar nav_title hidden" style="border: 0;">
			<a href="index.html" class="site_title"><i class="fa fa-paw"></i>
				<span>Gentellela Alela!</span> </a>
		</div>
		<div class="clearfix hidden"></div>

		<div class="nav toggle top_left_right">
			<a id="menu_toggle"><i class="fa fa-bars"></i></a>
		</div>

		<!-- menu prile quick info -->
		<div class="profile">
			<div class="profile_pic">
				<img id="head_img_left"
					src="${ctx }/attachment/${user_head_img }" 
					src1="<%=request.getContextPath()%>/resources/gentelella/production/images/img.jpg"
					alt="..." class="img-circle profile_img">
			</div>
			<div class="profile_info">
				<span>欢迎</span>
				<h2>${nickname}</h2>
			</div>
		</div>
		<!-- /menu prile quick info -->

		<br />

		<!-- sidebar menu -->
		<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
			<div class="menu_section">
				<h3>&nbsp;</h3>
				<ul id="side-menu" class="nav side-menu">
				</ul>
			</div>
			<div class="menu_section hide" >
				<h3>Live On</h3>
				<ul class="nav side-menu">
					<li><a><i class="fa fa-bug"></i> Additional Pages <span
							class="fa fa-chevron-down"></span> </a>
						<ul class="nav child_menu" style="display: none">
							<li><a href="e_commerce.html">E-commerce</a></li>
							<li><a href="projects.html">Projects</a></li>
							<li><a href="project_detail.html">Project Detail</a></li>
							<li><a href="contacts.html">Contacts</a></li>
							<li><a href="profile.html">Profile</a></li>
						</ul></li>
					<li><a><i class="fa fa-windows"></i> Extras <span
							class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu" style="display: none">
							<li><a href="page_404.html">404 Error</a></li>
							<li><a href="page_500.html">500 Error</a></li>
							<li><a href="plain_page.html">Plain Page</a></li>
							<li><a href="login.html">Login Page</a></li>
							<li><a href="pricing_tables.html">Pricing Tables</a></li>

						</ul></li>
					<li><a><i class="fa fa-laptop"></i> Landing Page <span
							class="label label-success pull-right">Coming Soon</span></a></li>
				</ul>
			</div>

		</div>
		<!-- /sidebar menu -->

		<!-- /menu footer buttons -->
		<div class="sidebar-footer hidden-small">
			<a data-toggle="tooltip" data-placement="top" title="Settings"> <span
				class="glyphicon glyphicon-cog" aria-hidden="true"></span>
			</a> <a data-toggle="tooltip" data-placement="top" title="FullScreen">
				<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
			</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span
				class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
			</a> <a data-toggle="tooltip" data-placement="top" title="Logout"> <span
				class="glyphicon glyphicon-off" aria-hidden="true"></span>
			</a>
		</div>
		<!-- /menu footer buttons -->
	</div>
</div>

<script>
	/** ******  left menu  *********************** **/
	
	function clearActiveClass() {
		
		$("a", $("#sidebar-menu")).each(function(e) {
			
            $this = $(this);
            
            if ($this.attr("data-link") && $this.attr("data-link").indexOf("#") < 0) {
            	
                $this.parent('li').removeClass('current-page');
			}
		});
    }
	
	function initLeftMenuBehave() {
		
		$("body").attr("toggle-menu",1);
		
		$('#sidebar-menu li ul').slideUp();
		$('#sidebar-menu li').removeClass('active');

		$('#sidebar-menu li').click(function() {
			if ($(this).is('.active')) {
				$(this).removeClass('active');
				$('ul', this).slideUp();
				$(this).removeClass('nv');
				$(this).addClass('vn');
			} else {
				$('#sidebar-menu li ul').slideUp();
				$(this).removeClass('vn');
				$(this).addClass('nv');
				$('ul', this).slideDown();
				$('#sidebar-menu li').removeClass('active');
				$(this).addClass('active');
			}
		});
		
		
		$('#menu_toggle').click(function () {
			
			var _this = $(this);
			
	        if ($('body').hasClass('nav-md')) {
	            $('body').removeClass('nav-md');
	            $('body').addClass('nav-sm');
	            $('.left_col').removeClass('scroll-view');
	            $('.left_col').removeAttr('style');
	            $('.sidebar-footer').hide();

	            if ($('#sidebar-menu li').hasClass('active')) {
	                $('#sidebar-menu li.active').addClass('active-sm');
	                $('#sidebar-menu li.active').removeClass('active');
	            }
	            
	            _this.removeClass('top_left_right');
	            _this.addClass('top_left_right_sm');
	        } else {
	            $('body').removeClass('nav-sm');
	            $('body').addClass('nav-md');
	            $('.sidebar-footer').show();
	            
	            _this.removeClass('top_left_right_sm');
	            _this.addClass('top_left_right');

	            if ($('#sidebar-menu li').hasClass('active-sm')) {
	                $('#sidebar-menu li.active-sm').addClass('active');
	                $('#sidebar-menu li.active-sm').removeClass('active-sm');
	            }
	        }
	    });
		
		$("a", $("#sidebar-menu")).click(function(e) {
			
			var $this = $(this);
			
			var data_link = $this.attr("data-link");
			if (data_link == undefined){
				return;
			}
			
			clearActiveClass();
            
            if ($this.attr("data-link") && $this.attr("data-link").indexOf("#") < 0) {
            	
            	e.stopPropagation(); 
                
                $this.parent('li').addClass('current-page').parent('ul').parent().addClass('active');
                
                var url = "${ctx}/" + $this.attr("data-link");
                //alert(url);

				$("#frame_content_id")[0].src = url;
			}
		});

	};

	function loadTree() {
		// 远程读取数据
		var url_to = "${ctx}/backend/menu/load_sys_menu_admin_home";

		$.ajax({
			url : url_to,
			data : {
				ts : new Date().getTime()
			},
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				if (data['success'] == 'n') {
					if (data['brErrors']) {
						$.showBRErrors(data['brErrors'], $("#manageuser_add_div"));
					} else {
						layer.alert(data['message']);
					}
				} else {
					var menu_data = data['message'];

					$('#side-menu').html(menu_data);

					initLeftMenuBehave();
				}
			},
			complete : function(XMLHttpRequest, textStatus) {
				//layer.alert(JSON.stringify(XMLHttpRequest));
			}
		});
	}
</script>
