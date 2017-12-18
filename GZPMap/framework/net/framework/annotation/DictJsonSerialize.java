package net.framework.annotation;
import java.lang.annotation.Documented;   
import java.lang.annotation.ElementType;   
import java.lang.annotation.Retention;   
import java.lang.annotation.RetentionPolicy;   
import java.lang.annotation.Target; 

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.framework.utils.json.DictSerializer;
//自定义注解相关设置
@Target({ElementType.METHOD})   
@Retention(RetentionPolicy.RUNTIME)   
@JacksonAnnotationsInside
@JsonSerialize(using = DictSerializer.class)
@Documented
public @interface DictJsonSerialize {
	//自定义注解的属性，default是设置默认值
    String queryType() default "SysParams";//关联表类名
    String queryField() default "id";//关联属性
    String displayField() default "paramsName";//显示属性
}
