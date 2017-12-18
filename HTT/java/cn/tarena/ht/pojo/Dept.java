package cn.tarena.ht.pojo;
//部门对象
public class Dept extends BaseEntity{
	
	private Dept parentDept;  //一对一关联(自关联)
	
	
	private String deptId;
	private String deptName;  //部门名称
	private Integer state;    //状态 0停用 1启用
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public Dept getParentDept() {
		return parentDept;
	}
	public void setParentDept(Dept parentDept) {
		this.parentDept = parentDept;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	   
}
