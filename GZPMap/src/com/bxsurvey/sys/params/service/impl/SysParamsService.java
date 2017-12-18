/**  
 * @Title: SysParamsService.java
 * @Package com.bxsurvey.service.sys.impl
 * @Description: TODO
 * @author yokoboy
 * @date 2016-1-13
 */
package com.bxsurvey.sys.params.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.framework.httpModel.Tree;

import org.springframework.stereotype.Service;

import com.bxsurvey.sys.params.dao.SysParamsDao;
import com.bxsurvey.sys.params.dao.SysParamsTestDao;
import com.bxsurvey.sys.params.dao.SysParamsTypeDao;
import com.bxsurvey.sys.params.model.SysParams;
import com.bxsurvey.sys.params.model.SysParamsTest;
import com.bxsurvey.sys.params.model.SysParamsType;
import com.bxsurvey.sys.params.service.SysParamsServiceI;

/**
 * ClassName: SysParamsService 
 * @Description: TODO
 * @author czj
 * @date 2016-1-13
 */
@Service
public class SysParamsService implements SysParamsServiceI {

	/* (non-Javadoc)
	 * @see com.bxsurvey.service.sys.SysParamsServiceI#findListByType(java.lang.String)
	 * 根据paramsType找到所有的报警类型
	 */
	@Resource
	private SysParamsDao sysParamsDao;
	@Resource
	private SysParamsTypeDao sysParamsTypeDao;
	@Resource
	private SysParamsTestDao sysParamsTestDao;
	
	public List<SysParams> findListByType(String type) {
		List<SysParams> list = sysParamsDao.findListByType(type);
		return list;
	}

	@Override
	public SysParams findListById(String id) {
		return sysParamsDao.get(Integer.parseInt(id));
	}

	@Override
	public List findByHql(String hql) {

		return sysParamsDao.getListMapbyQuery(hql);
	}

	/**
	 * 通过类型查找数据，并排序
	 */
	public List<SysParams> getDictionaryByTypeOrder(String type) {
		List<SysParams> list = sysParamsDao.getDictionaryByTypeOrder(type);
		return list;
	}

	/**
	 * 查询数据字典
	 */
	public List<Tree> getParamsType() {
		List<Tree> res = new ArrayList<Tree>();
		
		Tree root = new Tree();
		root.setName("数据字典");
		root.setId("0");
		root.setOpen(true);
		root.setPid("");
		root.setData(new ArrayList());
		root.setIsParent(true);
		res.add(root);
		
		List<SysParamsType> list = sysParamsTypeDao.findAll();
		for(SysParamsType spt : list){
			Tree tempTree = new Tree();
			tempTree.setName(spt.getName());
			tempTree.setId(spt.getId().toString());
			tempTree.setPid("0");
			tempTree.setType(spt.getParamsType());
			tempTree.setOpen(true);
			res.add(tempTree);
		}
		return res;
	}

	/**
	 * 添加数据
	 */
	public void save(SysParamsTest obj) {
		sysParamsTestDao.save(obj);
	}

	/**
	 * 修改数据
	 */
	public void update(SysParamsTest obj) {
		sysParamsTestDao.update(obj);
		
	}

	/**
	 * 删除数据
	 */
	public void deleteById(String ids) {
		String[] strIds = ids.split(",");
		for(String id : strIds){
			sysParamsTestDao.deleteById(Integer.valueOf(id));
		}
	}

	/**
	 * 根据节点id查询对应的数据
	 */
	public List<SysParamsTest> getParamsList(String id) {
		return sysParamsTestDao.getParamsList(Integer.valueOf(id));
	}

	/**
	 * 根据id获取对象
	 */
	public SysParamsTest getById(String id) {
		return sysParamsTestDao.get(Integer.valueOf(id));
	}

	/**
	 * 添加参数类型
	 */
	public void saveParamsType(SysParamsType obj) {
		sysParamsTypeDao.save(obj);
	}

	/**
	 * 编辑参数类型
	 */
	public void updateParamsType(SysParamsType obj) {
		sysParamsTypeDao.update(obj);
	}

	/**
	 * 删除参数类型
	 */
	public void deleteParamsType(String id) {
		sysParamsTypeDao.deleteById(Integer.valueOf(id));
	}

}












