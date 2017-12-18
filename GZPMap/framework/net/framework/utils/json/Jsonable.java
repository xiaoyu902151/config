package net.framework.utils.json;


import net.sf.json.JSONObject;

/** 可序列化为JSONObject */
public interface Jsonable {
	
	/** 把对象序列化为JSONObject */
	public JSONObject toJSONObject();
}
	