package net.framework.utils;

import java.io.Serializable;

import net.framework.utils.json.JsonUtil;
import net.framework.utils.json.Jsonable;
import net.sf.json.JSONObject;

public class BaseModel<T> implements Jsonable,Serializable{
	
	private static final long serialVersionUID = 1L;

	/** 默认的序列化JSONObject，子类可根据实际情况重写该方法 */
	public JSONObject toJSONObject() {
		return JsonUtil.bean2JSONObject(this);
	}
	
}
