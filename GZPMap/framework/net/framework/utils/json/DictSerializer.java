package net.framework.utils.json;

import java.io.IOException;
import java.util.Date;

import com.bxsurvey.sys.params.model.SysParams;
import com.bxsurvey.sys.params.service.SysParamsServiceI;
import com.bxsurvey.util.SpringWiredBean;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DictSerializer extends JsonSerializer<String>{

	@Override
	public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {   
    	SysParamsServiceI sysParamsService = (SysParamsServiceI)SpringWiredBean.getInstance().getBeanById("sysParamsService");
    	SysParams sp = sysParamsService.findListById(value);
    	jgen.writeStartObject();
    	jgen.writeStringField("paramsId", sp.getParamsId().toString());
    	jgen.writeStringField("paramsName", sp.getParamsName());
    	jgen.writeEndObject();
	}

}
