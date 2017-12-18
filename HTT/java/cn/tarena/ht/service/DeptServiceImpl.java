package cn.tarena.ht.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.DeptMapper;
import cn.tarena.ht.pojo.Dept;
@Service 
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	private DeptMapper deptMapper;
	
	
	//查询部门列表信息
	@Override
	public List<Dept> findAll() {
		
		return deptMapper.findAll();
	}


	@Override
	public void deleteDepts(String[] deptId) {
		
		deptMapper.deleteDepts(deptId);
		
	}

	
	//修改部门状态
	@Override
	public void updateState(String[] deptId, int state) {
		
		deptMapper.updateState(deptId,state);
		
	}


	@Override
	public List<Dept> findParentList() {
		
		return deptMapper.findParentList();
	}


	@Override
	public void saveDept(Dept dept) {
		
		deptMapper.saveDept(dept);
	}


	@Override
	public Dept findDeptById(String deptId) {
		
		return deptMapper.findDeptById(deptId);
	}


	@Override
	public List<Dept> findParentRemove(String deptId) {
		
		return deptMapper.findParentRemove(deptId);
	}


	@Override
	public void updateDept(Dept dept) {
		
		deptMapper.updateDept(dept);
		
	}

}
