package com.genscript.gsscm.tools.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.tools.entity.FollowUp;
import com.genscript.gsscm.tools.service.FollowUpService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Results({ @Result(name = "input", location = "tools/FollowUp_input.jsp"),
		@Result(name = "update", location = "tools/FollowUp_update.jsp") })
public class FollowUpAction extends ActionSupport {
	@Autowired
	private FollowUpService followUpService;
	private FollowUp followUp;
	private List<FollowUp> fluplist;
	private Date date;
	private Integer OpenFlag;
	private Integer followBy;
	private String productiontargetDate;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String list() {
		return "list";

	}

	public Integer getFollowBy() {
		return followBy;
	}

	public void setFollowBy(Integer followBy) {
		this.followBy = followBy;
	}

	public String getProductiontargetDate() {
		return productiontargetDate;
	}

	public void setProductiontargetDate(String productiontargetDate) {
		this.productiontargetDate = productiontargetDate;
	}

	public static String getCurrentTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	public static int timeCompare(String t1, String t2) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(formatter.parse(t1));
			c2.setTime(formatter.parse(t2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int result = c1.compareTo(c2);
		return result;
	}

	private String nextfollowUpdate = "";
	private String nowtime = "";
	private int flagtime;
	private String message = "";

	public String getNextfollowUpdate() {
		return nextfollowUpdate;
	}

	public void setNextfollowUpdate(String nextfollowUpdate) {
		this.nextfollowUpdate = nextfollowUpdate;
	}

	public String getNowtime() {
		return nowtime;
	}

	public void setNowtime(String nowtime) {
		this.nowtime = nowtime;
	}

	public int getFlagtime() {
		return flagtime;
	}

	public void setFlagtime(int flagtime) {
		this.flagtime = flagtime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private int flagoff = 0;

	public int getFlagoff() {
		return flagoff;
	}

	public void setFlagoff(int flagoff) {
		this.flagoff = flagoff;
	}

	public String input() {
		String orderNo = ServletActionContext.getRequest().getParameter(
				"orderNo");
		String inputoff = "";
		System.out.println(orderNo);
		if (orderNo != null && !"".equals(orderNo)) {
			fluplist = followUpService.getallFollowups(Integer
					.parseInt(orderNo));
			if (fluplist.size() == 0) {
				OpenFlag = 1;
				followUp = new FollowUp();
				followUp.setStatus("open");
			}
			int sizes = fluplist.size();
			if (sizes == 1) {
				// 第2次进来 或者第三次进来
				followUp = followUpService.getLastOne1(Integer
						.parseInt(orderNo));
				nextfollowUpdate = followUp.getNextFollowupDate();
				nowtime = getCurrentTime();
				message = followUp.getMessage();
				if (!"".equals(message) && message != null) {
					// 说明当前的 第三次进来 follow up 一次结束了 可以从新follow up
					OpenFlag = 1;
				} else {
					if (nextfollowUpdate != null
							&& !"".equals(nextfollowUpdate)) {
						flagtime = timeCompare(nextfollowUpdate, nowtime);
						if (flagtime >= 0) {
							flagoff = 2;// 第二次进来 Message不可修改的
						} else {
							flagoff = 1;// Message可修改了
						}
					}
				}

			} else if(sizes > 1){
				List a = followUpService.getLastOne2(Integer.parseInt(orderNo));
				if (a.get(0) != null) {
					FollowUp followUp = (FollowUp) a.get(0);
					nextfollowUpdate = followUp.getNextFollowupDate();
					nowtime = getCurrentTime();
					message = followUp.getMessage();
					if (!"".equals(message) && message != null) {
						// 说明当前的 第三次进来 follow up 一次结束了 可以从新follow up
						OpenFlag = 1;
					} else {
						if (nextfollowUpdate != null
								&& !"".equals(nextfollowUpdate)) {
							flagtime = timeCompare(nextfollowUpdate, nowtime);
							if (flagtime >= 0) {
								flagoff = 2;// 第二次进来 Message不可修改的
							} else {
								flagoff = 1;// Message可修改了
							}
						}
					}
					OpenFlag = 2;
				}
			}
		}
		productiontargetDate = followUpService.getptdate(Integer
				.parseInt(orderNo));
		ServletActionContext.getRequest().setAttribute("orderNo", orderNo);
		ServletActionContext.getRequest().setAttribute("fluplist", fluplist);
		System.out.println("fluplist==" + fluplist.size());
		if (OpenFlag == 1) {
			inputoff = "input";
		} else {
			inputoff = "update";
		}
		return inputoff;
	}

	/*
	 * if (fluplist.size() > 1) { followUp = followUpService.getLastOne2(Integer
	 * .parseInt(orderNo)); OpenFlag = 2; }
	 */
	public String save() {
		System.out.println(id);
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			followBy = SessionUtil.getUserId();
			followUp.setFollowupBy(followBy);
			followUp.setFollowupDate(new Date());
			this.followUpService.saveFollowUp(followUp);
			rt.put("message", "success");

		} catch (Exception w) {
			w.printStackTrace();
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public FollowUp getFollowUp() {
		return followUp;
	}

	public void setFollowUp(FollowUp followUp) {
		this.followUp = followUp;
	}

	public List<FollowUp> getFluplist() {
		return fluplist;
	}

	public void setFluplist(List<FollowUp> fluplist) {
		this.fluplist = fluplist;
	}

	public Integer getOpenFlag() {
		return OpenFlag;
	}

	public void setOpenFlag(Integer openFlag) {
		OpenFlag = openFlag;
	}
}
