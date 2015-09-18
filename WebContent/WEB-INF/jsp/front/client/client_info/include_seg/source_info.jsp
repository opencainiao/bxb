<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="panel panel-info">
	<div class="panel-heading">来源信息</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-xs-6">
				<div class="row">
					<div class="form-group form-group-sm  ">
						<label for="introducer_name" class="col-sm-3 control-label">
							介绍人 </label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="introducer_name"
								name="introducer_name" value="${client.introducer_name}"
								placeholder="">
						</div>
					</div>
					<div class="form-group form-group-sm  ">
						<label for="source_type" class="col-sm-3 control-label">客户来源
						</label>
						<div class="col-sm-8">
							<select id="source_type" name="source_type" class="form-control"
								data-src="constant" data-typecode="SOURCE_TYPE"
								data-value="${client.source_type}">
							</select>
						</div>
					</div>
					<div class="form-group form-group-sm  ">
						<label for="contact_type" class="col-sm-3 control-label">可接触度
						</label>
						<div class="col-sm-8">
							<select id="contact_type" name="contact_type" data-src="constant"
								data-typecode="CONTACT_TYPE" class="form-control"
								data-value="${client.contact_type}">
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="row">
					<div class="col-md-12 form-horizontal">
						<div class="form-group form-group-sm  ">
							<label for="introducer_relationship"
								class="col-sm-3 control-label">与介绍人关系 </label>
							<div class="col-sm-8">
								<select id="introducer_relationship"
									name="introducer_relationship" class="form-control"
									data-src="constant" data-typecode="INTRODUCER_RELATIONSHIP"
									data-value="${client.introducer_relationship}">
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm  ">
							<label for="introducer_closeness" class="col-sm-3 control-label">与介绍人亲密度
							</label>
							<div class="col-sm-8">
								<select id="introducer_closeness" name="introducer_closeness"
									class="form-control"
									data-src="constant" data-typecode="INTRODUCER_CLOSENESS"
									data-value="${client.introducer_closeness}">
								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="row">
					<div class="col-md-12 form-horizontal">
						<div class="form-group form-group-sm  ">
							<label for="phone_info" class="col-xs-3 control-label">
								介绍人评价 </label>
							<div class="col-xs-9">
								<input type="text" class="form-control"
									id="introducer_evaluation" name="introducer_evaluation"
									value="${client.introducer_evaluation}" placeholder=""
									style="width: 480px;">
							</div>
						</div>
						<div class="form-group form-group-sm  ">
							<label for="phone_info" class="col-xs-3 control-label">
								联系注意问题 </label>
							<div class="col-xs-9">
								<input type="text" class="form-control" id="contact_attention"
									name="contact_attention"
									value="${client.contact_attention}" placeholder=""
									style="width: 480px;">
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
