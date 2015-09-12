package com.bxb.modules.base;

import org.mou.common.JsonUtil;
import org.mou.common.StringUtil;

import com.mongodb.ReflectionDBObject;

/****
 * 所有域模型对象的基础类，包含公用的属性和方法
 * 
 * @author NBQ
 *
 */
public class BaseModel extends ReflectionDBObject {

	// protected MongoObjectId _id;// mongo主键
	protected String collectionname; // 对象对应的collection名字

	protected String cdate; // 创建日期
	protected String ctime; // 创建时间
	protected String cuserid; // 创建用户id
	protected String cusername;// 创建用户姓名
	protected String last_op_user_id;// 最后编辑用户id
	protected String last_op_user_name;// 最后编辑用户姓名
	protected String last_op_date; // 最后操作日期
	protected String last_op_time; // 最后一次操作时间

	protected String delflg; // 删除标志(用于逻辑删除)
	protected String useflg; // 启用标志

	protected String _id_m; // _id的字符串表示

	public String getUseflg() {
		return useflg;
	}

	public void setUseflg(String useflg) {
		this.useflg = useflg;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getCuserid() {
		return cuserid;
	}

	public void setCuserid(String cuserid) {
		this.cuserid = cuserid;
	}

	public String getCusername() {
		return cusername;
	}

	public void setCusername(String cusername) {
		this.cusername = cusername;
	}

	public String getCollectionname() {
		return collectionname;
	}

	public void setCollectionname(String collectionname) {
		this.collectionname = collectionname;
	}

	public String getDelflg() {
		return delflg;
	}

	public void setDelflg(String delflg) {
		this.delflg = delflg;
	}

	public String getLast_op_user_id() {
		return last_op_user_id;
	}

	public void setLast_op_user_id(String last_op_user_id) {
		this.last_op_user_id = last_op_user_id;
	}

	public String getLast_op_user_name() {
		return last_op_user_name;
	}

	public void setLast_op_user_name(String last_op_user_name) {
		this.last_op_user_name = last_op_user_name;
	}

	public String getLast_op_date() {
		return last_op_date;
	}

	public void setLast_op_date(String last_op_date) {
		this.last_op_date = last_op_date;
	}

	public String getLast_op_time() {
		return last_op_time;
	}

	public void setLast_op_time(String last_op_time) {
		this.last_op_time = last_op_time;
	}

	public String get_id_m() {

		if (!StringUtil.isEmpty(this._id_m)) {
			return this._id_m;
		}

		if (super.get_id() == null) {
			return null;
		}

		String[] _ids = super.get_id().toString().split("=");

		String _id = "";

		if (_ids.length == 1) {
			_id = _ids[0];
		} else {
			_id = _ids[1].substring(0, _ids[1].length() - 1);
		}

		return _id;
	}

	public void set_id_m(String _id_m) {
		this._id_m = _id_m;
	}

	public String toString() {
		return JsonUtil.toJsonStr(this);
	}
}
