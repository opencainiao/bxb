<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="panel panel-info">
	<div class="panel-heading">收入情况</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-xs-6">
				<div class="row">
					<div class="form-group form-group-sm  ">
						<label for="annual_income_personal" class="col-sm-3 control-label">
							个人年收入 </label>
						<div class="col-sm-8">
							<input type="text" class="form-control"
								id="annual_income_personal" name="annual_income_personal"
								value="${client.annual_income_personal}" placeholder="">
						</div>
					</div>
					<div class="form-group form-group-sm  ">
						<label for="annual_income_personal_type"
							class="col-sm-3 control-label">年收入类型（个人） </label>
						<div class="col-sm-8">
							<select id="annual_income_personal_type"
								name="annual_income_personal_type" class="form-control"
								data-src="constant" data-typecode="ANNUAL_INCOME_PERSONAL_TYPE"
								data-value="${client.annual_income_personal_type}">
							</select>
						</div>
					</div>
					<div class="form-group form-group-sm  ">
						<label for="family_income_feature" class="col-sm-3 control-label">家庭收入特点
						</label>
						<div class="col-sm-8">
							<select id="family_income_feature" name="family_income_feature"
								class="form-control" data-src="constant"
								data-typecode="FAMILY_INCOME_FEATURE"
								data-value="${client.family_income_feature}">
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="row">
					<div class="col-md-12 form-horizontal">
						<div class="form-group form-group-sm  ">
							<label for="annual_income_family" class="col-sm-3 control-label">
								（家庭）年收入 </label>
							<div class="col-sm-8">
								<input type="text" class="form-control"
									id="annual_income_family" name="annual_income_family"
									value="${client.annual_income_family}" placeholder="">
							</div>
						</div>
						<div class="form-group form-group-sm  ">
							<label for="annual_income_family_type"
								class="col-sm-3 control-label">（家庭）年收入分类 </label>
							<div class="col-sm-8">
								<select id="annual_income_family_type"
									name="annual_income_family_type" class="form-control"
									data-src="constant" data-typecode="ANNUAL_INCOME_FAMILY_TYPE"
									data-value="${client.annual_income_family_type}">
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm  ">
							<label for="family_financial_standing"
								class="col-sm-3 control-label">（家庭）财务状况 </label>
							<div class="col-sm-8">
								<select id="family_financial_standing"
									name="family_financial_standing" class="form-control"
									data-src="constant" data-typecode="FAMILY_FINANCIAL_STANDING"
									data-value="${client.family_financial_standing}">
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
