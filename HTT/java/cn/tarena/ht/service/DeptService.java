package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Dept;

public interface DeptService {
	//查询部门列表信息
	public List<Dept> findAll();
	
	//根据部门ID删除部门
	public void deleteDepts(String[] deptId);
	
	//修改部门状态
	public void updateState(String[] deptId, int state);
	
	//查询上级部门信息
	public List<Dept> findParentList();
	
	//部门保存
	public void saveDept(Dept dept);
	
	//根据id查询部门信息
	public Dept findDeptById(String deptId);
	
	//查询排除自己之外的数据
	public List<Dept> findParentRemove(String deptId);
	
	//修改部门信息
	public void updateDept(Dept dept);
}
