/**  
 * @Title: SysParamsServiceI.java
 * @Package com.bxsurvey.service.sys
 * @Description: TODO
 * @author yokoboy
 * @date 2016-1-13
 */
package com.bxsurvey.sys.params.service;

import java.util.List;
import java.util.Map;

import net.framework.httpModel.Tree;

import com.bxsurvey.sys.params.model.SysParams;
import com.bxsurvey.sys.params.model.SysParamsTest;
import com.bxsurvey.sys.params.model.SysParamsType;


/**
 * ClassName: SysParamsServiceI 
 * @Description: TODO
 * @author czj
 * @date 2016-1-13
 */
public interface SysParamsServiceI {
	List<SysParams> findListByType(String type);
	
	SysParams findListById(String id);
	
	List<Map<String, Object>> findByHql(String hql);
	
	List<SysParams> getDictionaryByTypeOrder(String type);

	List<Tree> getParamsType();

	void save(SysParamsTest obj);

	void update(SysParamsTest obj);

	void deleteById(String ids);

	List<SysParamsTest> getParamsList(String id);

	SysParamsTest getById(String id);

	void saveParamsType(SysParamsType obj);

	void updateParamsType(SysParamsType obj);

	void deleteParamsType(String id);

	
}
