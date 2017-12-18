package com.bxsurvey.sys.params.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bxsurvey.sys.params.model.SysParams;

import net.framework.base.dao.BaseDao;
import net.framework.httpModel.PageResults;
/**
 * 
 * @ClassName: SysParamsDao 
 * @Description: 系统参数表Dao
 * @author cqc
 * @date 2015-5-11 下午5:27:26 
 *
 */
@Repository
public class SysParamsDao extends BaseDao<SysParams, Integer> {
	
	public static final String FORIGN="forign_id";//设备所属类型
	/**
	 * 
	 * @Title: findDeviBelongType 
	 * @Description: 获取设备所属类型的封装参数
	 * @param @return 
	 * @return List<SysParams> 
	 * @throws
	 */
	public List<SysParams> findDeviBelongType(){
		SysParams paramsExp=new SysParams();
		paramsExp.setParamsType(SysParamsDao.FORIGN);
		paramsExp.setParamsValidity(true);
		return this.findByExample(paramsExp);
	}
	
	/*
	 * 通过类型获取对象集合
	 */
	public List<SysParams> findListByType(String type) {
		Criteria criteria = this.getSession().createCriteria(SysParams.class);
		criteria.add(Restrictions.eq("paramsType", type))
				.add(Restrictions.eq("paramsValidity", true));
		return (List<SysParams>)criteria.list();
	}
	/*
	 * 获取所有对象集合
	 */
	public List<SysParams> getAll() {
		Criteria criteria = this.getSession().createCriteria(SysParams.class);
		return (List<SysParams>)criteria.list();
	}
	/*
	 * 通过各种参数获取对象集合
	 */
	public List<SysParams> findListByParams(Map<String, Object> params) {
		Criteria criteria = this.getSession().createCriteria(SysParams.class);
		Iterator iterator = params.entrySet().iterator();
		while(iterator.hasNext()){
			String key = (String)iterator.next();
			Object value = params.get(key);
			criteria.add(Restrictions.eq(key, value));
		}
		return (List<SysParams>)criteria.list();
	}

	public SysParams findSysParamsById(String id){
		StringBuilder sb = new StringBuilder();
		sb.append("from SysParams s where s.paramsId= "+id);
		List<SysParams> list = this.find(sb.toString());
		if(list.size()!=-1){
			return list.get(0);
		}
		return null;		
	}

	/*
	 * 通过类型查找数据，并排序
	 */
	public List<SysParams> getDictionaryByTypeOrder(String type) {
		StringBuilder sb = new StringBuilder();
		sb.append("select s from SysParams s where s.paramsType='"+type+"'");
		sb.append(" order by s.paramsIndex desc");
		//只得到id,name;
		List<SysParams> list = this.find(sb.toString());
		List<SysParams> newList = new ArrayList<SysParams>();
		for(SysParams sp : list){
			SysParams sysparam = new SysParams();
			sysparam.setParamsId(sp.getParamsId());
			sysparam.setParamsName(sp.getParamsName());
			newList.add(sysparam);
		}
		return newList;
	}

	
}
