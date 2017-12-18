package net.framework.httpModel.page;

/**
 * 
 * @ClassName: Page 
 * @Description: 分页实体
 * @author cqc
 * @date 2015-5-22 下午4:34:52 
 *
 */
public class Page {

	private int page; //当前第几页
	
	private int rows;//每页显示的记录数

	private int totalCount;//总记录数

	private int totalPage; //总页数

	private int beginIndex; //起始点

	private boolean hasPrePage;//是否有上一页

	private boolean hasNextPage;//是否有下一页

	public Page(int rows, int totalCount, int totalPage, int page,
			int beginIndex, boolean hasPrePage, boolean hasNextPage) {
		this.rows = rows;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.page = page;
		this.beginIndex = beginIndex;
		this.hasPrePage = hasPrePage;
		this.hasNextPage = hasNextPage;
	}

	//构造函数，默认
	public Page() {
	}

	//构造方法，对所有属性进行设置

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public boolean isHasPrePage() {
		return hasPrePage;
	}

	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

}