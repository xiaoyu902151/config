package com.bxsurvey.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.bxsurvey.sys.params.model.SysParams;
import com.bxsurvey.sys.params.service.SysParamsServiceI;
import com.bxsurvey.util.SpringWiredBean;

public class DictLabelTag extends TagSupport {

    private String dictName = "";
    private String itemCode;

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public int doEndTag() throws JspException {
    	SysParamsServiceI sysParamsService = (SysParamsServiceI)SpringWiredBean.getInstance().getBeanById("sysParamsService");
    	SysParams sp = sysParamsService.findListById(this.getItemCode());
        JspWriter out = pageContext.getOut();
        try {
        	if(sp!= null){
        		out.write(sp.getParamsName());//通过id获取显示值
        	}else{
        		out.write(this.getDictName());
        	}
        } catch (IOException e) {
            throw new JspException(e);
        }
        return TagSupport.EVAL_PAGE;
    }
}

