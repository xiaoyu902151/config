package net.framework.httpModel.page;

/**
 * 
 * @ClassName: PageUtil 
 * @Description: 分页实体工具类
 * @author cqc
 * @date 2015-5-22 下午4:35:13 
 *
 */
public class PageUtil {

	
	public static Page createPage(int rows, int totalCount, int page) {
		rows = getrows(rows);
		page = getPage(page);
		int totalPage = getTotalPage(rows, totalCount);
		int beginIndex = getBeginIndex(rows, page);
		boolean hasPrePage = getHasPrePage(page);
		boolean hasNextPage = getHasNextPage(totalPage, page);
		return new Page(rows, totalCount, totalPage, page,
				beginIndex, hasPrePage, hasNextPage);
	}


	// 设置每页显示记录数
	public static int getrows(int rows) {
		return rows == 0 ? 10 : rows;
	}

	// 设置当前页
	public static int getPage(int page) {
		return page == 0 ? 1 : page;
	}

	// 设置总页数,需要总记录数，每页显示多少
	public static int getTotalPage(int rows, int totalCount) {
		int totalPage = 0;
		if (totalCount % rows == 0) {
			totalPage = totalCount / rows;
		} else {
			totalPage = totalCount / rows + 1;
		}
		return totalPage;
	}

	// 设置起始点，需要每页显示多少，当前页
	public static int getBeginIndex(int rows, int page) {
		return (page - 1) * rows;
	}

	// 设置是否有上一页，需要当前页
	public static boolean getHasPrePage(int page) {
		return page == 1 ? false : true;
	}

	// 设置是否有下一个，需要总页数和当前页
	public static boolean getHasNextPage(int totalPage, int page) {
		return page == totalPage || totalPage == 0 ? false : true;
	}

}