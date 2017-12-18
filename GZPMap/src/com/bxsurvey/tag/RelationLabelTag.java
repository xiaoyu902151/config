package com.bxsurvey.tag;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.bxsurvey.sys.params.model.SysParams;
import com.bxsurvey.sys.params.service.SysParamsServiceI;
import com.bxsurvey.util.SpringWiredBean;

public class RelationLabelTag extends TagSupport {
	private String tableName="";
    private String saveField = "";
    private String disField = "";
    private String value = "";
    

 

    public String getSaveField() {
		return saveField;
	}



	public void setSaveField(String saveField) {
		this.saveField = saveField;
	}



	public String getDisField() {
		return disField;
	}



	public void setDisField(String disField) {
		this.disField = disField;
	}



	public String getTableName() {
		return tableName;
	}



	public void setTableName(String tableName) {
		this.tableName = tableName;
	}



	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}



	@Override
    public int doEndTag() throws JspException {
		SysParamsServiceI sysParamsService = (SysParamsServiceI)SpringWiredBean.getInstance().getBeanById("sysParamsService");
        String tableName = this.getTableName();
        String dis = this.getDisField();
        String save = this.getSaveField();
        String value = this.getValue();
        String hql = "select "+save+","+dis+" from "+ tableName + " where "+save+" = '"+value+"'";
        List list = sysParamsService.findByHql(hql);
        JspWriter out = pageContext.getOut();
        
        try {
        	if(list.size() > 0){
        		Iterator iterator = list.iterator();
        		Object[] tm = (Object[]) iterator.next();
        		String display = tm[1].toString();
        		out.write(display);//通过id获取显示值
        	}else{
        		out.write(this.getValue());
        	}
        } catch (IOException e) {
            throw new JspException(e);
        }
        return TagSupport.EVAL_PAGE;
    }
}

