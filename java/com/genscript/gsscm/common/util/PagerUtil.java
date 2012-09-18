package com.genscript.gsscm.common.util;

import org.apache.struts2.ServletActionContext;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.PageDTO;

public class PagerUtil<T> {
	/**
	 * 根据请求参数获得Page具体类的相关数据.
	 * 
	 * @return
	 */
	public Page<T> getRequestPage() {
		Page<T> requestPage = new Page<T>();
//		String pageNo = Struts2Util
//				.getParameter(com.genscript.gsscm.common.constant.Constants.PAGERNO_PARMNAME);
		String pageNo = ServletActionContext.getRequest().getParameter(com.genscript.gsscm.common.constant.Constants.PAGERNO_PARMNAME);
		if (pageNo != null) {
			requestPage.setPageNo(Integer.parseInt(pageNo.trim()));
		} 
		String pageSize =  ServletActionContext.getRequest()
				.getParameter(com.genscript.gsscm.common.constant.Constants.PAGERSIZE_PARMNAME);
		if (pageSize != null) {
			requestPage.setPageSize(Integer.parseInt(pageSize.trim()));
		}
		requestPage.setTotalCount(0L);//解决默认值为-1的bug.
		return requestPage;
	}
	
	public PageDTO formPage(Page<T> page) {
		PageDTO dto = new PageDTO();
		dto.setAutoCount(page.isAutoCount());
		dto.setOrder(page.getOrder());
		dto.setOrderBy(page.getOrderBy());
		dto.setPageNo(page.getPageNo());
		dto.setPageSize(page.getPageSize());
		dto.setTotalCount(page.getTotalCount());
		// TODO 总页数:
		if (dto.getTotalCount() != null) {
			int iTemp = dto.getTotalCount().intValue() / dto.getPageSize();
			int totalPage = (dto.getTotalCount().intValue() % dto.getPageSize()) == 0 ? iTemp : (iTemp + 1);
			dto.setTotalPage(Long.valueOf(totalPage));
		}
		return dto;
	}
}
