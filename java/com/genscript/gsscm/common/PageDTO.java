package com.genscript.gsscm.common;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

public class PageDTO {
	
	// Public Variables //
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	//Paging parameters //
	protected Integer pageNo = 1;
	protected Integer pageSize = 0;
	protected String orderBy = null;
	protected String order = null;
	protected Boolean autoCount = true;

	//Return result //
	protected Long totalCount = -1L;
	protected Long totalPage = 1L;

	// Constructor //

	public PageDTO() {
	}

	public PageDTO(final Integer pageSize) {
		setPageSize(pageSize);
	}

	public PageDTO(final Integer pageSize, final Boolean autoCount) {
		setPageSize(pageSize);
		setAutoCount(autoCount);
	}

	// Query parameters access function //

	/**
	 * Access to the current page the page number, serial number starting at 1, the default is 1.
	 */
	public Integer getPageNo() {
		return pageNo;
	}

	/**
	 * Set the current page the page number, serial number from a start of less than 1 automatically adjusted to 1.
	 */
	public void setPageNo(final Integer pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	/**
	 * To obtain the number of records per page, default is 1.
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * Set the number of records per page, less than 1 automatically adjusted to 1.
	 */
	public void setPageSize(final Integer pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 0) {
			this.pageSize = 0;
		}
	}

	/**
	 * According to calculation of the current page pageNo, and pageSize first recorded in the overall results of a centralized location, serial number starting from 1.
	 */
	public Integer getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	/**
	 * Get sort field, no default value. More sort fields to use ',' separated.
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * Set sort field, a number of sort field with ',' separated.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * Whether it has set up sorting field, no default value.
	 */
	public Boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order));
	}

	/**
	 * Get sort direction.
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * Set sort direction.
	 * 
	 * @param order Optional value of desc or asc, multiple sort fields with ',' separated.
	 */
	public void setOrder(final String order) {
		if (order == null) {
			return;
		}
		//Check the legal value of the string order
		String[] orders = StringUtils.split(StringUtils.lowerCase(order), ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr))
				throw new IllegalArgumentException("Sort direction " + orderStr + " is not a valid value");
		}
		this.order = StringUtils.lowerCase(order);
	}

	/**
	 * Check whether they are automatically also the implementation of an object query to obtain the total number of records count, default is false.
	 */
	public Boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * Query object whether the implementation of automatic additional count query to obtain the total number of records.
	 */
	public void setAutoCount(final Boolean autoCount) {
		this.autoCount = autoCount;
	}

	// Access to query results function //


	/**
	 * To obtain the total number of records, the default value is -1.
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(final Long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * TotalCount calculated according to pageSize and the total number of pages, the default value of -1.
	 */
	public Long getTotalPages() {
		if (totalCount < 0)
			return -1L;

		Long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * Are there any page.
	 */
	public Boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * Get the next page the page number, serial number starting at 1.
	 * Current page is last page still return to last page number.
	 */
	public Integer getNextPage() {
		if (isHasNext())
			return pageNo + 1;
		else
			return pageNo;
	}

	/**
	 * Are there any previous.
	 */
	public Boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * To obtain the page number on the page, the serial number starting at 1.
	 * The current page number for the index returns index.
	 */
	public Integer getPrePage() {
		if (isHasPre())
			return pageNo - 1;
		else
			return pageNo;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}
}
