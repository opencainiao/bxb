<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- top navigation -->

<div class="top_nav">
	<div class="nav_menu" id="nav_menu">
		<nav class="" role="navigation">
			<ul class="nav navbar-nav navbar-right">
				<li class=""><a href="javascript:;"
					class="user-profile dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false"> <img id="head_img_top"
						src="${ctx }/attachment/${user_head_img }" alt="">${nickname}
						<span class=" fa fa-angle-down"></span>
				</a>
					<ul
						class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
						<li><a href="javascript:;"
							data-link="profile/${userid}/update"> 个人信息</a></li>
						<li class='hide'><a href="javascript:;"> <span
								class="badge bg-red pull-right">50%</span> <span>Settings</span>
						</a></li>
						<li class='hide'><a href="javascript:;">Help</a></li>
						<li><a href="javascript:;" data-link="adminlogout"
							id="logout"><i class="fa fa-sign-out pull-right"></i> 退出</a></li>
					</ul></li>

				<li role="presentation" class="dropdown"><a href="javascript:;"
					class="hide dropdown-toggle info-number" data-toggle="dropdown"
					aria-expanded="false"> <i class="fa fa-envelope-o"></i> <span
						class="badge bg-green">6</span>
				</a>
					<ul id="menu1"
						class=" dropdown-menu list-unstyled msg_list animated fadeInDown"
						role="menu">
						<li><a> <span class="image"> <img
									src="<%=request.getContextPath()%>/resources/gentelella/production/images/img.jpg"
									alt="Profile Image" />
							</span> <span> <span>John Smith</span> <span class="time">3
										mins ago</span>
							</span> <span class="message"> Film festivals used to be
									do-or-die moments for movie makers. They were where... </span>
						</a></li>
						<li><a> <span class="image"> <img
									src="<%=request.getContextPath()%>/resources/gentelella/production/images/img.jpg"
									alt="Profile Image" />
							</span> <span> <span>John Smith</span> <span class="time">3
										mins ago</span>
							</span> <span class="message"> Film festivals used to be
									do-or-die moments for movie makers. They were where... </span>
						</a></li>
						<li><a> <span class="image"> <img
									src="<%=request.getContextPath()%>/resources/gentelella/production/images/img.jpg"
									alt="Profile Image" />
							</span> <span> <span>John Smith</span> <span class="time">3
										mins ago</span>
							</span> <span class="message"> Film festivals used to be
									do-or-die moments for movie makers. They were where... </span>
						</a></li>
						<li><a> <span class="image"> <img
									src="<%=request.getContextPath()%>/resources/gentelella/production/images/img.jpg"
									alt="Profile Image" />
							</span> <span> <span>John Smith</span> <span class="time">3
										mins ago</span>
							</span> <span class="message"> Film festivals used to be
									do-or-die moments for movie makers. They were where... </span>
						</a></li>
						<li>
							<div class="text-center">
								<a> <strong><a href="inbox.html">See All Alerts</strong>
									<i class="fa fa-angle-right"></i>
								</a>
							</div>
						</li>
					</ul></li>
			</ul>
		</nav>
	</div>
</div>

<script>
	function initTopNavBehave() {

		$("a", $("#nav_menu")).click(function(e) {

			var $this = $(this);

			var data_link = $this.attr("data-link");
			if (data_link == undefined) {
				return;
			}

			if ($this.attr("data-link") && $this.attr("data-link").indexOf("#") < 0) {

				e.preventDefault();
				e.stopPropagation();

				var url = $.getSitePath() + "/" + $this.attr("data-link");
				//alert(url);

				if ($this.attr("id") == "logout") {
					$.loadPage(url);
					//window.top.location.href = url;
				} else {
					$("#frame_content_id")[0].src = url;
				}

				$("#nav_menu").trigger("click");
			}
		});

	}
</script>
<!-- /top navigation -->