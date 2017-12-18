package com.bxsurvey.util;
import java.lang.annotation.Documented;   
import java.lang.annotation.ElementType;   
import java.lang.annotation.Retention;   
import java.lang.annotation.RetentionPolicy;   
import java.lang.annotation.Target; 

import com.bxsurvey.sys.user.model.SysUser;

//自定义注解相关设置
@Target({ElementType.METHOD})   
@Retention(RetentionPolicy.RUNTIME)   
@Documented
public @interface OperateAnno {
	//自定义注解的属性，default是设置默认值
    String operateName() default "登录操作";//操作名称
    Class operateClass() default SysUser.class;//操作的对象
}
