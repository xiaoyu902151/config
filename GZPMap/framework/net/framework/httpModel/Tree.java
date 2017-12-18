package net.framework.httpModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * 
 ***********************************************
 * Copyright (c) 2014 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.httpModel.Tree.java 
 * Created On: 2014-12-1 下午5:03:11
 * Description: 模块管理树形结构
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
public class Tree extends HashMap<String,Object>{
	
	/* TODO */
	private static final long serialVersionUID = 1L;
	private static final String id="id";
	private static final String pid="pid";
	private static final String name="name";
	private static final String isParent="isParent";
	private static final String checked = "checked";
	private static final String nocheck = "nocheck";
	private static final String open = "open";
	
	private static final String children = "children";
	private static final String data = "data";
	private static final String plotGeo = "plotGeo";
	private static final String type = "type";
	private static final String icon = "icon";
	private static final String iconClose = "iconClose";
	private static final String iconOpen = "iconOpen";
//	private  List children = new ArrayList();
	
	public String getIconClose() {
		return get(iconClose)!=null?(String)get(iconClose):"";
	}
	
	public void setIconClose(String iconClose){
		put(this.iconClose,iconClose);
	}
	
	public String getIconOpen() {
		return get(iconOpen)!=null?(String)get(iconOpen):"";
	}
	
	public void setIconOpen(String iconOpen){
		put(this.iconOpen,iconOpen);
	}
	
	public String getIcon() {
		return get(icon)!=null?(String)get(icon):"";
	}
	
	public void setIcon(String icon){
		put(this.icon,icon);
	}
	
	public String getType() {
		return get(type)!=null?(String)get(type):"";
	}
	
	public void setType(String type) {
		put(this.type,type);
	}
	
	public String getPlotGeo() {
		return get(plotGeo)!=null?(String)get(plotGeo):"";
	}
	
	public void setPlotGeo(String plotGeo){
		put(this.plotGeo,plotGeo);
	}
	
	public void setOpen(Boolean isOpen ){
		put(open,isOpen);
	}
	public   Boolean getOpen() {
		return  get(nocheck)!=null?(Boolean)get(nocheck):false;
	}
	
	public void setNocheck(Boolean isCheck ){
		put(nocheck,isCheck);
	}
	public   Boolean getNocheck() {
		return get(nocheck)!=null?(Boolean)get(nocheck):false;
	}

	public boolean getChecked(){
		return get(checked)!=null?Boolean.parseBoolean(String.valueOf(get(checked))):false;
	}
	
	public void setChecked(boolean isCheck){
		put(checked,isCheck);
	}
	
	public String getPId() {
		return get(pid)!=null?(String)get(pid):"";
	}
	public void setPid(String pid) {
		put(this.pid, pid);
	}
	
	public String getId() {
		return get(id)!=null?(String)get(id):"";
	}

	public void setId(String id) {
		put(this.id, id);
	}
	
	public void setName(String name) {
		put(this.name, name);
	}
	
	public String getName() {
		return get(name)!=null?(String)get(name):"";
	}
	
	public boolean getIsParent() {
		Object object=get(isParent);
		return object.toString().equals("true")?true:false;
	}
	public void setIsParent(boolean isParent) {
		put(this.isParent, isParent);
	}
	public   List getChildren() {
		Object object = get(children);
		if (object instanceof List){
			return (List) object;
		}else{
			return new ArrayList();
		}
	}
 
	public void setChildren(List list){
		put(this.children, list);
	}
	
	public   List getData() {
		Object object = get(data);
		if (object instanceof List){
			return (List) object;
		}else{
			return new ArrayList();
		}
	}
 
	public void setData(List list){
		put(this.data, list);
	}
	
	
}
