package com.bxsurvey.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.util.StringUtils;



import com.bxsurvey.sys.params.model.SysParams;
import com.bxsurvey.sys.params.service.SysParamsServiceI;
import com.bxsurvey.util.SpringWiredBean;

public class RelationSelectTag extends TagSupport{
	private String tableName;
    private boolean defaultValue;
    private String value;
    private String saveField;
    private String disField;
    private String name;
    private String id;
    private String cssClass;
    private String styleClass;
    private String multiple;
    private String onChange;
    
    
    
    public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getMultiple() {
        return multiple;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    public String getOnChange() {
        return onChange;
    }

    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSaveField() {
        return saveField;
    }

    public void setSaveField(String saveField) {
        this.saveField = saveField;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    public boolean isDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }
    
    
    public String getDisField() {
		return disField;
	}

	public void setDisField(String disField) {
		this.disField = disField;
	}

	@Override
    public int doEndTag() throws JspException{
    	SysParamsServiceI sysParamsService = (SysParamsServiceI)SpringWiredBean.getInstance().getBeanById("sysParamsService");
        String tableName = this.getTableName();
        String name = this.getDisField();
        String value = this.getSaveField();
        String hql = "select "+value+","+name+" from "+ tableName;
    	List list = sysParamsService.findByHql(hql);
        JspWriter out = pageContext.getOut();
        StringBuffer sb = new StringBuffer();
        sb.append("<select name='"+this.getName()+"' id='"+this.getId()+"'");
        if(!StringUtils.isEmpty(this.getCssClass())){
            sb.append("class=\"" + this.getCssClass() + "\" ");
        }
        if(!StringUtils.isEmpty(this.getStyleClass())){
            sb.append("style=\"" + this.getStyleClass() + "\" ");
        }
        if(!StringUtils.isEmpty(this.getMultiple())){
            sb.append("multiple=\"" + this.getMultiple() + "\" ");
        }
        if(!StringUtils.isEmpty(this.getOnChange())){
            sb.append("onchange=\"" + this.getOnChange() + "\" ");
        }
        sb.append(">");
        sb.append("<option value=''>--请选择--</option>");  
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
        	Object[] tm = (Object[]) iterator.next();
        	String saveValue = tm[0].toString();
        	String display = tm[1].toString();
        	if(saveValue.equals(this.getValue())){
                sb.append("<option value='"+saveValue+"' selected='selected'>");
            }else{
                sb.append("<option value='"+saveValue+"'>");
            }
            sb.append(display+"</option>");
        }
        sb.append("</select>");
        try {
            out.write(sb.toString());
        } catch (IOException e) {
            throw new JspException(e);
        }
        return TagSupport.EVAL_PAGE;
    }
}
