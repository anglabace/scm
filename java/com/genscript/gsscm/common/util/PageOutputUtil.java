package com.genscript.gsscm.common.util;

import org.apache.commons.lang.StringUtils;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.constant.Constants;

/**
 * Ajax方式调用返回分页信息
 * @author Golf
 *
 */
public class PageOutputUtil {


	@SuppressWarnings("unchecked")
	public static String outputPageInfo(Page page, String url){
		String query = Struts2Util.getRequest().getQueryString();
		String regex = Constants.PAGERNO_PARMNAME + "=" + Struts2Util.getRequest().getParameter(Constants.PAGERNO_PARMNAME);
		if (query!=null && query.indexOf(Constants.PAGERNO_PARMNAME) != -1) {
			query = query.replaceFirst(regex, "");
		}
		regex = Constants.PAGERSIZE_PARMNAME + "=" + Struts2Util.getRequest().getParameter(Constants.PAGERSIZE_PARMNAME);// this.pageSize
		if (query!=null && query.indexOf(Constants.PAGERSIZE_PARMNAME) != -1) {
			Integer pageSize = Integer.parseInt(Struts2Util.getRequest().getParameter(Constants.PAGERSIZE_PARMNAME));
			query = query.replaceFirst(regex, Constants.PAGERSIZE_PARMNAME + "=" + pageSize);
		}
		if (query != null && query.length()>0) {
			if (query.startsWith("&")) {
				query = query.substring(1);
			}
			if (query.endsWith("&")) {
				query = query.substring(0, query.length()-1);
			}
		}
		
		if (query != null) {
		  url = url + "?" + query;
		}
		if (url.indexOf("?") != -1) {
			url = url + "&";
		} else {
			url = url + "?";
		}
	    url = url + Constants.PAGERNO_PARMNAME;
		//request.setAttribute("url", url);
	    
		StringBuilder sb = new StringBuilder();
		if(page.getPageNo() <= 4){
			if(page.getPageNo() <= 1){
				sb.append("<span class=\"disabled\">&lt;</span>");
			}
			if(page.getPageNo() > 1){
				System.out.println("" + url);
				sb.append("<a href=\"").append(url).append("=").append(page.getPageNo()-1).append("\">&lt;</a>");
			}
			for(int i=1; i<page.getPageNo()-1;i++){
				sb.append("<a href=\"").append(url).append("=").append(i).append("\">").append(i).append("</a>");
			}
			sb.append("<span class=\"current\">").append(page.getPageNo()).append("</span>");
			for(int i=page.getPageNo()+1; i< (page.getTotalPage()>9?9:page.getTotalPage());i++){
				sb.append("<a href=\"").append(url).append("=").append(i).append(i).append("\">").append(i).append("</a>");
			}
			if(page.getPageNo() >= page.getTotalPage()){
				sb.append("<span class=\"disabled\">&gt;</span>");
			}
			if(page.getPageNo() < page.getTotalPage()){
				sb.append("<a href=\"").append(url).append("=").append(page.getPageNo()+1).append("\">&gt;</a>");
			}
		}
		if(page.getPageNo() > 4){
			sb.append("<a href=\"").append(page.getPageNo()-1).append("\">&lt;</a>");
			for(int i=page.getPageNo()-4; i< (page.getPageNo()+4>page.getTotalPage()?page.getTotalPage():page.getPageNo()+4);i++){
				if(page.getPageNo() == i){
					sb.append("<span class=\"current\">").append(page.getPageNo()).append("</span>");
				}
				if(page.getPageNo() != i){
					sb.append("<a href=\"").append(url).append("=").append("\">").append(i).append("</a>");
				}
			}
			if(page.getPageNo() >= page.getTotalPage()){
				sb.append("<span class=\"disabled\">&gt;</span>");
			}
			if(page.getPageNo() < page.getTotalPage()){
				sb.append("<a href=\"").append(url).append("=").append(page.getPageNo()+1).append("\">&gt;</a>");
			}
		}
		//System.out.println("++++++++++++" + sb.toString());
		return sb.toString();
	}
	
	public static final String PAGE_NAME = "p_no";
	public static final String NEXT_PAGE = ">";
	public static final String PRE_PAGE = "<";
	public static final String FIRST_PAGE = "First";
	public static final String LAST_PAGE = "Last";
	
	private int pageSize = 10;
	private int totalPage = 0;
	private int currentPage = 1;
	private int offset = 0;
	private String url = "";
	private boolean isSpecialUrl = false;
	
	@SuppressWarnings("unused")
	private PageOutputUtil(){}

	
	@SuppressWarnings("unchecked")
	public PageOutputUtil(Page page, String url, boolean isSpecialUrl) {
		this.isSpecialUrl = isSpecialUrl;
		this.pageSize = page.getPageSize();
		this.totalPage = page.getTotalPage().intValue();
		this.currentPage = page.getPageNo();
		this.offset = (currentPage-1)*pageSize;
		this.url = setUrl(url);
	}



	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getUrl(int pageNo) {
		if(isSpecialUrl()){
			return StringUtils.replace(url, "{?}", String.valueOf(pageNo));
		}else{
			return url+pageNo;
		}
	}

	public String setUrl(String url) {
		StringBuilder urlSb = new StringBuilder();
		if(StringUtils.isNotBlank(url)){
			if(isSpecialUrl){
				this.url = url;
			}else{
				if(url.indexOf("?") != -1){
					urlSb.append(url).append("?").append(PAGE_NAME).append("=");
				}else{
					urlSb.append(url).append("&").append(PAGE_NAME).append("=");
				}
				this.url = urlSb.toString();
			}
			return url;
		}else{
			if(StringUtils.isEmpty(Struts2Util.getRequest().getQueryString())){
				urlSb.append(Struts2Util.getRequest().getRequestURI()).append("?").append(PAGE_NAME).append("=");
			}else{
				if(Struts2Util.getRequest().getRequestURI().contains(PAGE_NAME)){
					urlSb.append(StringUtils.replace(Struts2Util.getRequest().getRequestURI(), PAGE_NAME+"="+getCurrentPage(), ""));
					String last = StringUtils.replace(Struts2Util.getRequest().getRequestURI(), PAGE_NAME+"="+getCurrentPage(), "").substring(-1);
					if(last.equals("?") || last.equals("&")){
						urlSb.append(PAGE_NAME).append("=");
					}else{
						urlSb.append("&").append(PAGE_NAME).append("=");
					}
				}else{
					urlSb.append(Struts2Util.getRequest().getRequestURI()).append("&").append(PAGE_NAME).append("=");
				}
			}
		}
		return urlSb.toString();
	}

	public String nextPage(String style){
		if(currentPage < totalPage){
			return getLink(getUrl(currentPage+1), NEXT_PAGE, style);
		}
		return new StringBuilder().append("<span class=\"disabled\">").append(NEXT_PAGE).append("</span>").toString();
	}
	
	public String prePage(String style){
		if(currentPage > 1){
			return getLink(getUrl(currentPage-1), PRE_PAGE, style);
		}
		return new StringBuilder().append("<span class=\"disabled\">").append(PRE_PAGE).append("</span>").toString();
	}
	
	public String firstPage(String style){
		if(currentPage == 1){
			return new StringBuilder().append("<span class=\"").append(style).append("\">").append(FIRST_PAGE).append("</span>").toString();
		}
		return getLink(getUrl(1), FIRST_PAGE, style);
	}
	
	public String lastPage(String style){
		if(currentPage == totalPage){
			return new StringBuilder().append("<span class=\"").append(style).append("\">").append(LAST_PAGE).append("</span>").toString();
		}
		return getLink(getUrl(totalPage), LAST_PAGE, style);
	}
	
	public String nowBar(String nowindexStyle){
		Double plus = Math.ceil(pageSize/2);
		if(pageSize-plus+currentPage > totalPage){
			plus = Double.valueOf(pageSize-totalPage + currentPage);
		}
		int begin = (int)(currentPage-plus+1);
		if(begin < 1){
			begin =1;
		}
		StringBuilder retStr = new StringBuilder();
		for(int i=begin; i < begin + pageSize; i++){
			if(i<=totalPage){
				if(i!=currentPage){
					retStr.append(getLink(getUrl(i), i+"", ""));
				}else{
					retStr.append("<span class=\"").append(nowindexStyle).append("\">").append(i).append("</span>");
				}
			}else{
				break;
			}
			retStr.append("\n");
		}
		return retStr.toString();
	}
	
	public String show(){
		try{
			String show = new StringBuilder().append(prePage("")).append(nowBar("current")).append(nextPage("")).toString();
			return show;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getLink(String url, String text, String style){
		if(StringUtils.isEmpty(style)){
			style = "";
		}else{
			style = new StringBuilder().append("class=\"").append(style).append("\"").toString();
		}
		return new StringBuilder().append("<a ").append(style).append(" href=\"").append(url).append("\">").append(text).append("</a>").toString();
	}
	
	public boolean isSpecialUrl() {
		return isSpecialUrl;
	}

	public void setSpecialUrl(boolean isSpecialUrl) {
		this.isSpecialUrl = isSpecialUrl;
	}
	
	
}
