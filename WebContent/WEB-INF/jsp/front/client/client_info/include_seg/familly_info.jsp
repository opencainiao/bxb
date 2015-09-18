<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="panel panel-info">
	<div class="panel-heading">家庭信息</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-xs-6">
				<div class="row">
					<div class="col-md-12 form-horizontal">
						<div class="form-group form-group-sm   ">
							<label for="marital_status" class="col-sm-3 control-label">婚姻
							</label>
							<div class="col-sm-8">
								<select id="marital_status" name="marital_status"
									class="form-control" data-src="constant"
									data-typecode="MARITAL_STATUS"
									data-value="${client.marital_status}">
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm   ">
							<label for="wedding_date" class="col-sm-3 control-label">结婚纪念日
							</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="wedding_date"
									name="wedding_date" value="${client.marital_status}"
									placeholder="">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="row">
					<div class="col-md-12 form-horizontal">
						<div class="form-group form-group-sm ">
							<label for="boy_num" class="col-sm-3 control-label"> 男孩数
							</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="boy_num"
									name="boy_num" value="${client.boy_num}">
							</div>
						</div>
						<div class="form-group form-group-sm ">
							<label for="girl_num" class="col-sm-3 control-label"> 女孩数
							</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="girl_num"
									name="girl_num" value="${client.girl_num}">
							</div>
						</div>
						<div class="form-group form-group-sm hide">
							<label for="children_num" class="col-sm-3 control-label">
								子女数 </label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="children_num"
									name="children_num" value="${client.children_num}" readonly>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	
</script>