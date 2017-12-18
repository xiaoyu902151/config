package com.bxsurvey.comd.ComdExpert.service;

import java.math.BigDecimal;
import java.util.List;

import com.bxsurvey.comd.ComdExpert.model.ComdExpert;


import net.framework.httpModel.Json;
import net.framework.httpModel.PageResults;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



/** 
 * @ClassName: ComdScheduServiceI 
 * @Description: 事件调度服务接口
 * @author cqc
 * @date 2015-7-20 下午2:22:17 
 *  
 */
public interface ComdExpertServiceI{
	public void save(ComdExpert experts);
	public void update(ComdExpert experts);
	public void deleteById(String ids);
	public List<ComdExpert> getExpertsByXY(double distance,BigDecimal x,BigDecimal y);
	public PageResults<ComdExpert> getListForJson(PageResults<ComdExpert> page, ComdExpert obj);
	public List<ComdExpert> getListNoPage(ComdExpert obj);

}
