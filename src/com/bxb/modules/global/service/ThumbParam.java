package com.bxb.modules.global.service;

import org.mou.common.JsonUtil;
import org.mou.common.StringUtil;

/****
 * 对图片文件进行压缩的压缩参数
 * 
 * @author NBQ
 *
 */
public class ThumbParam {

	private int width; // 宽
	private int height; // 高
	private String thumbParmPath; // 压缩文件的生成路径
	private ThumbType thumbType; // 压缩裁剪类型

	private double x1;
	private double x2;
	private double y1;
	private double y2;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getThumbParmPath() {
		return thumbParmPath;
	}

	public void setThumbParmPath(String thumbParmPath) {
		this.thumbParmPath = thumbParmPath;
	}

	/****
	 * 取该压缩比对应的目录名
	 * 
	 * @return
	 */
	public String getFolderName() {
		return width + "x" + height;
	}

	public ThumbType getThumbType() {
		return thumbType;
	}

	public void setThumbType(ThumbType thumbType) {
		this.thumbType = thumbType;
	}

	public double getX1() {
		return x1;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public double getY1() {
		return y1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}

	public double getY2() {
		return y2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}

	public void setThumbType(String thumbType) {

		if (StringUtil.isEmpty(thumbType)) {
			this.thumbType = ThumbType.COMPRESS_W_H_INNER;
		}

		if (thumbType.equals(ThumbType.COMPRESS_CAIJIAN.getCode())) {
			this.setThumbType(ThumbType.COMPRESS_CAIJIAN);
		} else if (thumbType.equals(ThumbType.NO_COMPRESS_CAIJIAN.getCode())) {
			this.setThumbType(ThumbType.NO_COMPRESS_CAIJIAN);
		} else if (thumbType.equals(ThumbType.COMPRESS_W_H_INNER.getCode())) {
			this.setThumbType(ThumbType.COMPRESS_W_H_INNER);
		} else {
			this.setThumbType(ThumbType.COMPRESS_W_H_INNER);
		}
	}

	@Override
	public String toString() {
		return JsonUtil.toJsonStr(this);
	}

}
