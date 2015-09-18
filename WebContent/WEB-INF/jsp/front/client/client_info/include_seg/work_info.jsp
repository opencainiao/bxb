<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="panel panel-info">
	<div class="panel-heading">工作信息</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-xs-6">
				<div class="row">
					<div class="form-group form-group-sm  ">
						<label for="company" class="col-sm-3 control-label"> 工作单位
						</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="company"
								name="company" value="${client.company}" placeholder="">
						</div>
					</div>
					<div class="form-group form-group-sm  ">
						<label for="trade_type" class="col-sm-3 control-label">行业类型
						</label>
						<div class="col-sm-8">
							<select id="trade_type" name="trade_type" class="form-control"
								data-src="constant" data-typecode="TRADE_TYPE"
								data-value="${client.trade_type}">
							</select>
						</div>
					</div>
					<div class="form-group form-group-sm  ">
						<label for="job_position" class="col-sm-3 control-label">职位
						</label>
						<div class="col-sm-8">
							<select id="job_position" name="job_position" data-src="constant"
								data-typecode="JOB_POSITION" class="form-control"
								data-value="${client.job_position}">
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="row">
					<div class="col-md-12 form-horizontal">
						<div class="form-group form-group-sm  ">
							<label for="company_nature" class="col-sm-3 control-label">企业性质
							</label>
							<div class="col-sm-8">
								<select id="company_nature" name="company_nature"
									class="form-control" data-src="constant"
									data-typecode="COMPANY_NATURE"
									data-value="${client.company_nature}">
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm  ">
							<label for="career_type" class="col-sm-3 control-label">职业类型
							</label>
							<div class="col-sm-8">
								<select id="career_type" name="career_type" class="form-control"
									data-src="constant" data-typecode="CAREER_TYPE"
									data-value="${client.career_type}">
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm  ">
							<label for="job_level" class="col-sm-3 control-label">职级
							</label>
							<div class="col-sm-8">
								<select id="job_level" name="job_level" class="form-control"
									data-src="constant" data-typecode="JOB_LEVEL"
									data-value="${client.job_level}">
								</select>
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
