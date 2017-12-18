package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tarena.ht.pojo.Dept;

public interface DeptMapper {
	/**
	 * 部门列表的查询
	 * @return
	 */
	public List<Dept> findAll();
	
	//部门删除
	public void deleteDepts(String[] deptId);
	
	//修改部门状态
	public void updateState(@Param("deptIds")String[] deptId, @Param("state") int state);
	
	//查询上级部门列表
	public List<Dept> findParentList();
	
	//新增部门
	public void saveDept(Dept dept);
	
	//根据id查询部门信息
	public Dept findDeptById(String deptId);
	
	public List<Dept> findParentRemove(String deptId);
	
	public void updateDept(Dept dept);
}
