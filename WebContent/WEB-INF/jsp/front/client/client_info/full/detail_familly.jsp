<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<input type="hidden" name="_id_m" value="${_id_m }" />

<div id="content_inner_page" class="innercontent">
	<div class="navbar navbar-default">
		<form class="navbar-form navbar-left">
			<button class="btn btn-primary" type="button" id="btn_add" 
				style="margin-left: 1px!important;">添加亲属</button>
		</form>
	</div>

	<div id="data_manage">
		<table id="list_familly_relationship"></table>
	</div>
</div>

<script type="text/javascript"
	src="${ctx }/resources/js/front/client/client_info/full/list_familly.js"></script>
