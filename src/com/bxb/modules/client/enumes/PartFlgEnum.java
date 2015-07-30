package com.bxb.modules.client.enumes;

import org.mou.common.StringUtil;

/****
 * 性别
 * 
 * @author NBQ
 *
 */
public enum PartFlgEnum {

	BASE("0", "基本信息"), //
	FAMILLY("1", "家庭"), //
	WORK("2", "工作"), //
	INCOME("3", "收入"), //
	SOURCE("4", "来源"), //
	XG("5", "性格相关"), //
	SERVICE("6", "服务"), //
	OTHER("7", "其他"); //

	private String code;
	private String name;

	private PartFlgEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static boolean isValidPartFlg(String part_flg) {

		if (StringUtil.isEmpty(part_flg)) {
			return false;
		}

		part_flg = part_flg.trim();

		if (part_flg.equals(PartFlgEnum.BASE.getCode())) {
			return true;
		}
		if (part_flg.equals(PartFlgEnum.FAMILLY.getCode())) {
			return true;
		}
		if (part_flg.equals(PartFlgEnum.WORK.getCode())) {
			return true;
		}
		if (part_flg.equals(PartFlgEnum.INCOME.getCode())) {
			return true;
		}
		if (part_flg.equals(PartFlgEnum.SOURCE.getCode())) {
			return true;
		}
		if (part_flg.equals(PartFlgEnum.XG.getCode())) {
			return true;
		}
		if (part_flg.equals(PartFlgEnum.SERVICE.getCode())) {
			return true;
		}
		if (part_flg.equals(PartFlgEnum.OTHER.getCode())) {
			return true;
		}

		return false;
	}
}
