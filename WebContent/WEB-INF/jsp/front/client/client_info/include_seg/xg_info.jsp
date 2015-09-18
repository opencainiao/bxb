<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="panel panel-info">
	<div class="panel-heading">性格相关</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-xs-6">
				<div class="row">
					<div class="form-group form-group-sm  ">
						<label for="birth_ages" class="col-sm-3 control-label">出生年代
						</label>
						<div class="col-sm-8">
							<select id="birth_ages" name="birth_ages" class="form-control"
								data-src="constant" data-typecode="BIRTH_AGES"
								data-value="${client.birth_ages}">
							</select>
						</div>
					</div>
					<div class="form-group form-group-sm  ">
						<label for="blood_group" class="col-sm-3 control-label">血型
						</label>
						<div class="col-sm-8">
							<select id="blood_group" name="blood_group" class="form-control"
								data-src="constant" data-typecode="BLOOD_GROUP"
								data-value="${client.blood_group}">
							</select>
						</div>
					</div>
					<div class="form-group form-group-sm  ">
						<label for="pdp_type" class="col-sm-3 control-label">PDP类型
						</label>
						<div class="col-sm-8">
							<select id="pdp_type" name="pdp_type" class="form-control"
								data-src="constant" data-typecode="PDP_TYPE"
								data-value="${client.pdp_type}">
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="row">
					<div class="col-md-12 form-horizontal">
						<div class="form-group form-group-sm  ">
							<label for="age_group" class="col-sm-3 control-label">年龄段
							</label>
							<div class="col-sm-8">
								<select id="age_group" name="age_group" class="form-control"
									data-src="constant" data-typecode="AGE_GROUP"
									data-value="${client.age_group}">
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm  ">
							<label for="constellation" class="col-sm-3 control-label">星座
							</label>
							<div class="col-sm-8">
								<select id="constellation" name="constellation"
									class="form-control" data-src="constant"
									data-typecode="CONSTELLATION"
									data-value="${client.constellation}">
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
								性格特点 </label>
							<div class="col-xs-9">
								<input type="text" class="form-control" id="temperament_type"
									name="temperament_type"
									value="${client.temperament_type}" placeholder=""
									style="width: 480px;">
							</div>
						</div>
						<div class="form-group form-group-sm  ">
							<label for="phone_info" class="col-xs-3 control-label">
								兴趣爱好 </label>
							<div class="col-xs-9">
								<input type="text" class="form-control" id="hobbies"
									name="hobbies" value="${client.hobbies}" placeholder=""
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
