package net.framework.httpModel;

import net.framework.utils.BaseModel;



/**
 * JSON模型
 * 
 * @author donic
 * 
 */
public class Json extends BaseModel<Json> {
	
	private boolean success = false;// 是否成功
	private String msg = "";// 提示信息
	private Object obj = null;// 前端用到的其他信息
	private Integer state; //状态码
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "Json [success=" + success + ", msg=" + msg + ", obj=" + obj
				+ "]";
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	
}
