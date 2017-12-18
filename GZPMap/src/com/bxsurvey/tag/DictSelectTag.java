package com.bxsurvey.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.util.StringUtils;



import com.bxsurvey.sys.params.model.SysParams;
import com.bxsurvey.sys.params.service.SysParamsServiceI;
import com.bxsurvey.util.SpringWiredBean;

public class DictSelectTag extends TagSupport{
	private String dictName;
    private boolean defaultValue;
    private String value;
    private String name;
    private String id;
    private String cssClass;
    private String styleClass;
    private String multiple;
    private String onChange;
    
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
    
    public boolean isDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }
    
    
    @Override
    public int doEndTag() throws JspException{
    	SysParamsServiceI sysParamsService = (SysParamsServiceI)SpringWiredBean.getInstance().getBeanById("sysParamsService");
        List<SysParams> dictList = sysParamsService.findListByType(this.getDictName());
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
        for(SysParams sp:dictList){
            if(sp.getParamsId().toString().equals(this.getValue())){
                sb.append("<option value='"+sp.getParamsId()+"' selected='selected'>");
            }else{
                sb.append("<option value='"+sp.getParamsId()+"'>");
            }
            sb.append(sp.getParamsName()+"</option>");
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
