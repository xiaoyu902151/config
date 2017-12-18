package net.framework.annotation;
import java.lang.annotation.Documented;   
import java.lang.annotation.ElementType;   
import java.lang.annotation.Retention;   
import java.lang.annotation.RetentionPolicy;   
import java.lang.annotation.Target; 

//自定义注解相关设置
@Target({ElementType.FIELD})   
@Retention(RetentionPolicy.RUNTIME)   
@Documented
public @interface JoinTableAnno {
	//自定义注解的属性，default是设置默认值
    String className() default "SysParams";//关联表类名
    String queryField() default "id";//关联属性
    String displayField() default "paramsName";//显示属性
}
