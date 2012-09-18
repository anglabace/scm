package com.genscript.gsscm.privilege.web;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.common.constant.OperationType;
import com.genscript.gsscm.common.util.MailCampaignWSUtil;
import com.genscript.gsscm.common.util.PrivilegeServletUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringToXMLUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.privilege.dto.PrivilegeDTO;
import com.genscript.gsscm.privilege.dto.UserDTO;
import com.genscript.gsscm.privilege.entity.Privilege;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.privilege.service.PrivilegeService;

/*
 *2010-8-2 上午08:42:42
 *by mingrs
 */

//定义URL映射对应pvlg/privilege_tree.jsp
@Results({
		@Result(name = LoginAction.RELOAD, location = "login.action", type = "redirect"),
		@Result(name = "tree", location = "pvlg/privilege_tree.jsp"),
		@Result(name = "mappingUI", location = "pvlg/privilege_mappingUI.jsp"),
		@Result(name = "treeBody", location = "user/left.jsp"),
		@Result(name = "top", location = "user/top.jsp"),
		@Result(name = "middle", location = "user/adjust.jsp"),
		@Result(name = "main", location = "user/main.jsp"),
		@Result(name = "mail", location = "user/mail.jsp") })
public class PrivilegeAction extends BaseAction<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String INDENTATIONSPACE = "&nbsp;&nbsp;&nbsp;&nbsp;";

	private String treeBody;
	private UserDTO user;
	private Integer userId;
	private String ip;
	private String sessionUserId;
	@Autowired
	private PrivilegeService privilegeService;

	private Map<String, String> actionNameAndMethodMap;
	private String moduleCode;
	private String actionNames;

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String middle() {
		return "middle";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genscript.gsscm.common.web.BaseAction#list()
	 * 
	 * @auth mingrs 将 privilegeList 转换成XML，前台页面根据XML生成树。
	 */
	@Override
	public String list() throws Exception {
		Integer userId = SessionUtil.getUserId();
		// 获取文件路径；
		String path = ServletActionContext.getServletContext().getRealPath(
				"/xml");
		if (userId != null) {
			// XML文件名。
			String fileName = "privilege.xml";
			// 获取List数据
			List<Privilege> privilegeList = privilegeService
					.getTreeAllOrderByCode();
			// 主结点
			String xmlBoduStr = "";
			// 子结点
			String subXmlBodyStr = "";
			for (Privilege privilege : privilegeList) {
				String privilegeName = privilege.getPrivilegeName();
				String privilegeLink = privilege.getPrivilegeAttr();
				String pivilegeAction = privilege.getPrivilegeAction();
				// 将单引号转义；
				privilegeName = privilegeName.replaceAll("&", "&amp;");
				privilegeName = privilegeName.replaceAll("'", "&apos;");
				privilegeName = privilegeName.replaceAll("\"", "&quot;");
				privilegeName = privilegeName.replaceAll("<", "&lt;");
				privilegeName = privilegeName.replaceAll(">", "&gt;");

				if (privilegeLink != null) {
					privilegeLink = privilegeLink.replaceAll("&", "&amp;");
					privilegeLink = privilegeLink.replaceAll("'", "&apos;");
					privilegeLink = privilegeLink.replaceAll("\"", "&quot;");
					privilegeLink = privilegeLink.replaceAll("<", "&lt;");
					privilegeLink = privilegeLink.replaceAll(">", "&gt;");
				}

				if (pivilegeAction != null) {
					pivilegeAction = pivilegeAction.replaceAll("&", "&amp;");
					pivilegeAction = pivilegeAction.replaceAll("'", "&apos;");
					pivilegeAction = pivilegeAction.replaceAll("\"", "&quot;");
					pivilegeAction = pivilegeAction.replaceAll("<", "&lt;");
					pivilegeAction = pivilegeAction.replaceAll(">", "&gt;");

				}

				String im0 = null;
				String im1 = null;
				String im2 = null;
				if (privilege.getPrivilegeType().equals("MENU")) {
					im0 = "folderClosed";
					im1 = "folderOpen";
					im2 = "folderClosed";
				} else {
					im0 = "book_titel";
					im1 = "book";
					im2 = "book_titel";
				}
				if (privilege.getParentCode().equals("0000")) {
					// 设置父结点。
					xmlBoduStr += "<item text='" + privilegeName + " ("
							+ privilege.getPrivilegeCode() + ")'" + " id='"
							+ privilege.getPrivilegeCode() + "' im0='" + im0
							+ ".gif' im1='" + im1 + ".gif' im2='" + im2
							+ ".gif'>" + "<!--{" + privilege.getPrivilegeCode()
							+ "}-->" + "<userdata name='privilegeType'>"
							+ privilege.getPrivilegeType() + "</userdata>"
							+ "<userdata name='parentCode'>"
							+ privilege.getParentCode() + "</userdata>"
							+ "<userdata name='privilegeAttr'>" + privilegeLink
							+ "</userdata>"
							+ "<userdata name='privilegeAction'>"
							+ pivilegeAction + "</userdata>"
							+ "<userdata name='privilegeId'>"
							+ privilege.getPrivilegeId() + "</userdata>"
							+ "</item>";
				} else {
					// 设置子结点。
					subXmlBodyStr = "<item text='" + privilegeName + " ("
							+ privilege.getPrivilegeCode() + ")'" + " id='"
							+ privilege.getPrivilegeCode() + "' im0='" + im0
							+ ".gif' im1='" + im1 + ".gif' im2='" + im2
							+ ".gif'>" + "<!--{" + privilege.getPrivilegeCode()
							+ "}-->" + "<userdata name='privilegeType'>"
							+ privilege.getPrivilegeType() + "</userdata>"
							+ "<userdata name='parentCode'>"
							+ privilege.getParentCode() + "</userdata>"
							+ "<userdata name='privilegeAttr'>" + privilegeLink
							+ "</userdata>"
							+ "<userdata name='privilegeAction'>"
							+ pivilegeAction + "</userdata>"
							+ "<userdata name='privilegeId'>"
							+ privilege.getPrivilegeId() + "</userdata>"
							+ "</item>";
					String[] subStr = new String[2];
					// 将子结点放入对应的地方；
					subStr = xmlBoduStr
							.split("<!--\\{" + privilege.getParentCode()
									+ "\\}-->", 2);
					xmlBoduStr = subStr[0].toString() + subXmlBodyStr + "<!--{"
							+ privilege.getParentCode() + "}-->"
							+ (subStr.length == 2 ? subStr[1].toString() : "");
				}
			}
			// 去除XML文件中的子结点标识符;
			xmlBoduStr = xmlBoduStr.replaceAll("<!--\\{[^}]+\\}-->", " ");
			// 增加头和尾;
			String strHead = "<?xml version='1.0' encoding='utf-8'?><tree id='0'><item text='privilege' id='privilege' open='1' im0='tombs.gif' im1='tombs.gif' im2='iconSafe.gif' call='1' select='1'>";
			String strFoot = "</item></tree>";
			String xmlStr = strHead + xmlBoduStr + strFoot;
			// 将String 转成XML文件；
			// System.out.println(xmlStr);
			StringToXMLUtil.strChangeXML(xmlStr, path, fileName);
		}
		return "tree";
	}

	/**
	 * 将模块与Action及方法映射关系保存到数据库中
	 * 
	 * @author zhangyong
	 */
	public String mappingUI() {
		try {
			Integer userId = SessionUtil.getUserId();
			// 获取文件路径；
			String path = ServletActionContext.getServletContext().getRealPath(
					"/xml");
			if (userId != null) {
				// XML文件名。
				String fileName = "privilege.xml";
				// 获取List数据
				List<Privilege> privilegeList = privilegeService
						.getTreeAllOrderByCode();
				// 主结点
				String xmlBoduStr = "";
				// 子结点
				String subXmlBodyStr = "";
				for (Privilege privilege : privilegeList) {
					String privilegeName = privilege.getPrivilegeName();
					// 将单引号转义；
					privilegeName = privilegeName.replaceAll("&", "&amp;");
					privilegeName = privilegeName.replaceAll("'", "&apos;");
					privilegeName = privilegeName.replaceAll("\"", "&quot;");
					privilegeName = privilegeName.replaceAll("<", "&lt;");
					privilegeName = privilegeName.replaceAll(">", "&gt;");
					String im0 = null;
					String im1 = null;
					String im2 = null;
					if (privilege.getPrivilegeType().equals("MENU")) {
						im0 = "folderClosed";
						im1 = "folderOpen";
						im2 = "folderClosed";
					} else {
						im0 = "book_titel";
						im1 = "book";
						im2 = "book_titel";
					}
					if (privilege.getParentCode().equals("0000")) {
						// 设置父结点。
						xmlBoduStr += "<item text='" + privilegeName + " ("
								+ privilege.getPrivilegeCode() + ")'" + " id='"
								+ privilege.getPrivilegeCode() + "' im0='"
								+ im0 + ".gif' im1='" + im1 + ".gif' im2='"
								+ im2 + ".gif'>" + "<!--{"
								+ privilege.getPrivilegeCode() + "}-->"
								+ "<userdata name='privilegeType'>"
								+ privilege.getPrivilegeType() + "</userdata>"
								+ "<userdata name='parentCode'>"
								+ privilege.getParentCode() + "</userdata>"
								+ "<userdata name='privilegeAttr'>"
								+ privilege.getPrivilegeAttr() + "</userdata>"
								+ "<userdata name='privilegeAction'>"
								+ privilege.getPrivilegeAction()
								+ "</userdata>"
								+ "<userdata name='privilegeId'>"
								+ privilege.getPrivilegeId() + "</userdata>"
								+ "</item>";
					} else {
						// 设置子结点。
						subXmlBodyStr = "<item text='" + privilegeName + " ("
								+ privilege.getPrivilegeCode() + ")'" + " id='"
								+ privilege.getPrivilegeCode() + "' im0='"
								+ im0 + ".gif' im1='" + im1 + ".gif' im2='"
								+ im2 + ".gif'>" + "<!--{"
								+ privilege.getPrivilegeCode() + "}-->"
								+ "<userdata name='privilegeType'>"
								+ privilege.getPrivilegeType() + "</userdata>"
								+ "<userdata name='parentCode'>"
								+ privilege.getParentCode() + "</userdata>"
								+ "<userdata name='privilegeAttr'>"
								+ privilege.getPrivilegeAttr() + "</userdata>"
								+ "<userdata name='privilegeAction'>"
								+ privilege.getPrivilegeAction()
								+ "</userdata>"
								+ "<userdata name='privilegeId'>"
								+ privilege.getPrivilegeId() + "</userdata>"
								+ "</item>";
						String[] subStr = new String[2];
						// 将子结点放入对应的地方；
						subStr = xmlBoduStr.split(
								"<!--\\{" + privilege.getParentCode()
										+ "\\}-->", 2);
						xmlBoduStr = subStr[0].toString() + subXmlBodyStr
								+ "<!--{" + privilege.getParentCode() + "}-->"
								+ subStr[1].toString();
					}
				}
				// 去除XML文件中的子结点标识符;
				xmlBoduStr = xmlBoduStr.replaceAll("<!--\\{[^}]+\\}-->", " ");
				// 增加头和尾;
				String strHead = "<?xml version='1.0' encoding='utf-8'?><tree id='0'><item text='privilege' id='privilege' open='1' im0='tombs.gif' im1='tombs.gif' im2='iconSafe.gif' call='1' select='1'>";
				String strFoot = "</item></tree>";
				String xmlStr = strHead + xmlBoduStr + strFoot;
				// 将String 转成XML文件；
				StringToXMLUtil.strChangeXML(xmlStr, path, fileName);
			}
			// 获取所有的数据库中未被记录的Action
			// xml文件路径。
			String xmlPath = ServletActionContext.getServletContext()
					.getRealPath("/xml");
			actionNameAndMethodMap = privilegeService.getAllActionAndMethod(
					xmlPath, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mappingUI";
	}

	/**
	 * 保存模块与Action的映射关系
	 * 
	 * @author zhangyong
	 */
	public String saveMappingUI() {
		Map<String, Object> rt = new HashMap<String, Object>();
		String rtnMessage = null;
		try {
			Integer userId = SessionUtil.getUserId();
			rtnMessage = privilegeService.saveMappingUI(moduleCode,
					actionNames, userId);
			if (rtnMessage != null) {
				rt.put("rtnMessage", rtnMessage);
			}
			rt.put("hasException", false);
		} catch (Exception e) {
			if (rtnMessage != null) {
				rt.put("rtnMessage", rtnMessage);
			}
			rt.put("hasException", true);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 校验要映射的是否为最小级别的模块
	 * 
	 * @author zhangyong
	 */
	public String checkModuleCode() {
		Map<String, Object> rt = new HashMap<String, Object>();
		if (privilegeService.checkModuleCode(moduleCode)) {
			rt.put("rtnMessge", true);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 通过privilegeName查询privilege，并以json格式数据传到前台页面 主要功能是替换topIframe的当前位置URL
	 * 
	 * @author zhangyong
	 * @return
	 */
	public String findPrivilege() {
		String privName = Struts2Util.getParameter("privName");
		Map<String, Object> rt = new HashMap<String, Object>();
		Privilege prv = this.privilegeService.getPrivilegeByPrvName(privName);
		if (prv != null) {
			rt.put("privilege", prv);
			List<PrivilegeDTO> prvdtolist = this.privilegeService
					.getParentPath(prv.getPrivilegeCode());
			if (prvdtolist != null) {
				rt.put("prvdtolist", prvdtolist);
			}
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
	 * auth mingrs 对树型操作生成XML文件。 有 DEL,ADD,EDIT等操作方法。
	 */
	public String recordOperation() throws IOException {
		// 主结点
		String xmlBody = "";
		// 返回页面的XML；
		String reXMLBody = "";
		// 头结点。
		String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?><root>";
		// 尾结点。
		String xmlFoot = "</root>";
		String xmlSt = "";
		// 文件路径。
		String path = ServletActionContext.getServletContext().getRealPath(
				"/xml");
		Integer userId = SessionUtil.getUserId();
		if (userId != null) {
			String fileName = userId.toString() + ".xml";
			// 以下是将操作转化为XML。
			String operationType = ServletActionContext.getRequest()
					.getParameter("operationType");
			if (operationType != null) {
				String privilegeId = ServletActionContext.getRequest()
						.getParameter("privilegeId");
				String temp = ServletActionContext.getRequest().getParameter(
						"temp");
				xmlBody += "<operation_record>";
				xmlBody += "<operationType>" + operationType
						+ "</operationType>";
				xmlBody += "<privilegeId>" + privilegeId + "</privilegeId>";
				xmlBody += "<temp>" + temp + "</temp>";
				if (!(operationType.equals("DEL"))) {
					String privilegeName = ServletActionContext.getRequest()
							.getParameter("privilegeName");
					String privilegeAttr = ServletActionContext.getRequest()
							.getParameter("privilegeAttr");
					String privilegeAction = ServletActionContext.getRequest()
							.getParameter("privilegeAction");
					privilegeName = privilegeName.replaceAll("&", "&amp;");
					privilegeName = privilegeName.replaceAll("'", "&apos;");
					privilegeName = privilegeName.replaceAll("\"", "&quot;");
					privilegeName = privilegeName.replaceAll("<", "&lt;");
					privilegeName = privilegeName.replaceAll(">", "&gt;");

					privilegeAttr = privilegeAttr.replaceAll("&", "&amp;");
					privilegeAttr = privilegeAttr.replaceAll("'", "&apos;");
					privilegeAttr = privilegeAttr.replaceAll("\"", "&quot;");
					privilegeAttr = privilegeAttr.replaceAll("<", "&lt;");
					privilegeAttr = privilegeAttr.replaceAll(">", "&gt;");

					privilegeAction = privilegeAction.replaceAll("&", "&amp;");
					privilegeAction = privilegeAction.replaceAll("'", "&apos;");
					privilegeAction = privilegeAction
							.replaceAll("\"", "&quot;");
					privilegeAction = privilegeAction.replaceAll("<", "&lt;");
					privilegeAction = privilegeAction.replaceAll(">", "&gt;");

					xmlBody += "<privilegeName>" + privilegeName
							+ "</privilegeName>";
					xmlBody += "<privilegeAttr>" + privilegeAttr
							+ "</privilegeAttr>";
					xmlBody += "<privilegeAction>" + privilegeAction
							+ "</privilegeAction>";
					if (operationType.equals("ADD")) {
						String privilegeCode = ServletActionContext
								.getRequest().getParameter("privilegeCode");
						String parentCode = ServletActionContext.getRequest()
								.getParameter("parentCode");
						String privilegeType = ServletActionContext
								.getRequest().getParameter("privilegeType");
						xmlBody += "<privilegeCode>" + privilegeCode
								+ "</privilegeCode>";
						xmlBody += "<parentCode>" + parentCode
								+ "</parentCode>";
						xmlBody += "<privilegeType>" + privilegeType
								+ "</privilegeType>";
					}
				}
				xmlBody += "</operation_record>";
				reXMLBody += "<operation_info>";
				xmlSt = "<operation_result>1</operation_result>";
			} else {
				xmlSt = "<operation_result>0</operation_result>";
			}
			reXMLBody += "<operation>" + operationType + "</operation>";
			reXMLBody += xmlSt;
			reXMLBody += "</operation_info>";
			reXMLBody = xmlHeader + reXMLBody + xmlFoot;
			String xml = xmlHeader + xmlBody + xmlFoot;
			// 获取XML文件内容。
			Document strXML = StringToXMLUtil.checkingXML(path, fileName);
			// 判断XML文件是否存在。是，则将新生成的XML放入其后面。否则生成新的XML文件。
			if (strXML != null) {
				String str = strXML.asXML();
				str = str.replaceAll("</root>", xmlBody + "</root>");
				StringToXMLUtil.strChangeXML(str, path, fileName);
			} else {
				StringToXMLUtil.strChangeXML(xml, path, fileName);
			}
			Struts2Util.renderXml(reXMLBody);
		}
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 生成页面user/left.jsp中的树开型。 按HTML格式生成。
	 */
	public String menuListForUser() throws Exception {
		// 获取UserId，判断用户是否登陆;
		Integer userId = SessionUtil.getUserId();
		if (userId != null) {
			// 获取树形结构按LIST类型排列;
			List<PrivilegeDTO> treeList = this.privilegeService
					.getMenuListForUser(userId);
			if (treeList != null) {
				String link = "";
				String linkS = "";
				String linkE = "";
				String treeBodyHtml = "";
				// 设置 树前的空格;
				Map<String, String> space_privilegeCode = new HashMap<String, String>();
				for (PrivilegeDTO tree : treeList) {
					String privilegeName = tree.getPrivilegeName();
					String privilegeAttr = tree.getPrivilegeAttr();
					if (tree.getPrivilegeAttr() != null) {
						if (tree.getPrivilegeAttr().indexOf("?") != -1) {
							privilegeAttr += "&treeId=" + tree.getPrivilegeId();
							// System.out.println("包含");
						} else {
							privilegeAttr += "?treeId=" + tree.getPrivilegeId();
						}
					}
					if (tree.getParentCode().equals("0000")) {
						// 设置父结点
						if (this.findChild(tree.getPrivilegeCode(), treeList)) {
							link = privilegeAttr;
							if (link != null) {
								linkS = "<a href="
										+ link
										+ " id='tree_"
										+ tree.getPrivilegeId()
										+ "' onclick=\"c('subTree"
										+ tree.getPrivilegeId()
										+ "');setSubTopLink('"
										+ tree.getPrivilegeCode()
										+ "','"
										// 对单引号转义,一定要用八个反斜线;
										+ privilegeName.replaceAll("\\'",
												"\\\\\\\\") + "','"
										+ privilegeAttr
										+ "');\" target='mainFram'>";
								linkE = "</a>";
							} else {
								linkS = "<a href=\"javascript:c(\'subTree_"
										+ tree.getPrivilegeId()
										+ "\');setSubTopLink(\'"
										+ tree.getPrivilegeCode()
										+ "\',\'"
										// 对单引号转义,一定要用八个反斜线;
										+ privilegeName.replaceAll("\\'",
												"\\\\\\\\")
										+ "\',\'\');\" id='tree_"
										+ tree.getPrivilegeId() + "'>";
								linkE = "</a>";
							}
							treeBodyHtml += "<div class='L1 color1'>" + linkS
									+ "<span>" + privilegeName + "</span>"
									+ linkE + "</div>";
							treeBodyHtml += "<div id='subTree_"
									+ tree.getPrivilegeId()
									+ "' style='display: none;'><!--{"
									+ tree.getPrivilegeCode() + "}--></div>";
						} else {
							link = privilegeAttr;
							if (link != null) {
								linkS = "<a href='"
										+ link
										+ "' id='tree_"
										+ tree.getPrivilegeId()
										+ "' onclick='setTopLink( '"
										+ tree.getPrivilegeCode()
										+ "' , '"
										+ privilegeName.replaceAll("\\'",
												"\\\\\\\\") + "' , '"
										+ privilegeAttr
										+ "');'  target='mainFrame'>";
								linkE = "</a>";
							} else {
								linkS = "<a href='javascript:void(0);' id='tree_"
										+ tree.getPrivilegeId() + "'>";
								linkE = "</a>";
							}
							treeBodyHtml += "<div class='L1 color1'>" + linkS
									+ "<span>" + privilegeName + "</span>"
									+ linkE + "</div>";
						}
						// 四个空格;
						space_privilegeCode.put(tree.getPrivilegeCode(),
								INDENTATIONSPACE);
					} else {
						// 设置子结点
						if (treeBodyHtml != null) {
							String subTreeBodyHtml = "";
							String subTreeClass = "";
							String pic = "";
							String clickFunc = "";
							String spanTagS = "";
							String spanTagE = "";
							if (this.findChild(tree.getPrivilegeCode(),
									treeList)) {
								pic = "ar.gif";
								subTreeBodyHtml = "<div id='subTree_"
										+ tree.getPrivilegeId()
										+ "' style='display:none'><!--{"
										+ tree.getPrivilegeCode()
										+ "}--></div>";
								subTreeClass = "M1";
								clickFunc = "onclick=\"c('subTree_"
										+ tree.getPrivilegeId() + "');\"";
							} else {
								pic = "text.gif";
								subTreeClass = "M1";
								clickFunc = "";
								spanTagS = "";
								spanTagE = "";
							}
							if (tree.getPrivilegeAttr() == null
									|| tree.getPrivilegeAttr().equals("")) {
								link = "javascript:void(0);";
								privilegeAttr = "";
							} else {
								link = privilegeAttr;
							}
							space_privilegeCode.put(
									tree.getPrivilegeCode(),
									INDENTATIONSPACE
											+ space_privilegeCode.get(tree
													.getParentCode()));
							if (!("").equals(privilegeAttr)) {
								privilegeAttr += "&out_lock=true";
							}
							// 将子结点放入对应的地方；
							// 将子结点放入对应的地方；
							subTreeBodyHtml = "<div class='"
									+ subTreeClass
									+ "' "
									+ clickFunc
									+ "><table><tr><td>"
									+ space_privilegeCode.get(tree
											.getParentCode())
									+ "<img id='pic_subTree_"
									+ tree.getPrivilegeId()
									+ "' src='images/"
									+ pic
									+ "'/></td><td><a id='tree_"
									+ tree.getPrivilegeId()
									+ "' href=\""
									+ link
									+ "\"  onclick=\"setTopLink( \'"
									+ tree.getPrivilegeCode()
									+ "\' , \'"
									+ privilegeName.replaceAll("\\'",
											"\\\\\\\\") + "\' , \'"
									+ privilegeAttr + "\'  ) ;cur(\'tree_"
									+ tree.getPrivilegeId()
									+ "\')\" target='mainFrame'>" + spanTagS
									+ privilegeName + spanTagE
									+ "</a></td></tr></table></div>"
									+ subTreeBodyHtml;
							subTreeBodyHtml += "<!--{" + tree.getParentCode()
									+ "}-->";
							treeBodyHtml = treeBodyHtml.replaceAll("<!--\\{"
									+ tree.getParentCode() + "\\}-->",
									subTreeBodyHtml);
						}
					}
				}
				List<PrivilegeDTO> treeByECList = this.privilegeService
						.getECListForUser(userId);
				if (treeByECList != null) {
					for (PrivilegeDTO tree : treeByECList) {
						if (tree.getParentCode().equals("0000")) {
							treeBodyHtml += "<div class=\"L1 color1\">"
									+ "<a href= \"javascript:void(0)\"  onclick= \"javascript:document.form"
									+ tree.getPrivilegeId()
									+ ".submit(); \">"
									+ "<span>"
									+ tree.getPrivilegeName()
									+ "</span></a>"
									+ "<form   name= \"form"
									+ tree.getPrivilegeId()
									+ "\" action= \"privilege/privilege!mail.action\"   method= \"post\" target='_blank'>"
									+ "<input  name= \"ip\"   type= \"hidden\"   value= \""
									+ tree.getPrivilegeAction() + "\">"
									+ "</form>" + "</div>";
							/*
							 * treeBodyHtml += "<div class=\"L1 color1\">" +
							 * "<a id=\"tree_"+tree.getPrivilegeId() +
							 * "\" href=\"privilege/privilege!mail.action?ip="
							 * +tree.getPrivilegeAction()+"\" target='_blank'>"
							 * +
							 * "<span>"+tree.getPrivilegeName()+"</span></a></div>"
							 * ;
							 */
						}
					}
				}
				treeBodyHtml = treeBodyHtml
						.replaceAll("<!--\\{[^}]+\\}-->", "");
				this.setTreeBody(treeBodyHtml);
			}
		}
		return "treeBody";
	}

	/*
	 * 获取某个结点上面所有的父结点;
	 */
	public String parentPath() {
		// 获取JQuery客户端动态生成的callback函数名.
		String callbackName = ServletActionContext.getRequest().getParameter(
				"callback");
		String pvlgCode = ServletActionContext.getRequest().getParameter(
				"privilegeCode");
		String myPrvCode = ServletActionContext.getRequest().getParameter(
				"myPrvCode");
		// 获取其父结点,用户LIST类型
		List<PrivilegeDTO> privilegeList = new ArrayList<PrivilegeDTO>();
		if (pvlgCode != null && !("").equals(pvlgCode.trim())) {
			privilegeList = this.privilegeService.getParentPath(pvlgCode);
		} else {
			privilegeList = this.privilegeService.getParentPath(myPrvCode);
		}
		// 渲染返回结果.
		Struts2Util.renderJsonp(callbackName, privilegeList);
		return null;
	}

	/*
	 * 对XML中的操作分析,并进行数据库操作.
	 */
	public String savePrivilegeList() {
		// 获取UserId，判断用户是否登陆;
		Integer userId = SessionUtil.getUserId();
		String path = ServletActionContext.getServletContext().getRealPath(
				"/xml");
		String reXML = "";
		try {
			if (userId != null) {
				// 获取XML文件内容。
				Document xml = StringToXMLUtil.checkingXML(path, userId
						+ ".xml");
				if (xml != null) {
					List<HashMap<String, String>> xmlMap = StringToXMLUtil
							.XMLToList(xml.asXML());
					List<PrivilegeDTO> dtoList = new ArrayList<PrivilegeDTO>();
					Class<?> clazz = Class
							.forName("com.genscript.gsscm.privilege.dto.PrivilegeDTO");
					for (HashMap<String, String> map : xmlMap) {
						PrivilegeDTO p = new PrivilegeDTO();
						// 将Map中的内容与privilegeDTO生成对应对象。
						for (Entry<String, String> entry : map.entrySet()) {
							if (!(entry.getKey().equals("operationType"))
									&& !(entry.getKey().equals("temp"))) {
								if (entry.getKey().equals("privilegeId")) {
									if (!(map.get("privilegeId") == null
											|| map.get("privilegeId").equals(
													"undefined")
											|| map.get("privilegeId").equals(
													"null") || map.get(
											"privilegeId").equals(""))) {
										p.setPrivilegeId(Integer.valueOf(map
												.get("privilegeId")));
									}
								} else {
									Field field = clazz.getDeclaredField(entry
											.getKey());
									field.setAccessible(true);
									field.set(p, entry.getValue());
								}
							}
						}
						if ("".equals(p.getPrivilegeAttr())
								|| "null".equals(p.getPrivilegeAttr())) {
							p.setPrivilegeAttr(null);
						}
						if ("".equals(p.getPrivilegeAction())
								|| "null".equals(p.getPrivilegeAction())) {
							p.setPrivilegeAction(null);
						}
						if (map.get("operationType").equals("EDIT")) {
							p.setOperationType(OperationType.EDIT);
						} else if (map.get("operationType").equals("DEL")) {
							p.setOperationType(OperationType.DEL);
						} else {
							p.setOperationType(OperationType.ADD);
						}
						dtoList.add(p);
					}
					// 根据生成的对象，进行操作。
					this.privilegeService.savePrivilege(dtoList, userId);
					PrivilegeServletUtil.setPrivilegeList(this.privilegeService
							.getAllPrivilege());
					reXML = "<result>1</result>";
					StringToXMLUtil.delete(path, userId + ".xml");
				}
			}
			// 如果未进行操作刚返回"0" XML
			if (reXML == null || reXML.equals("")) {
				reXML = "<result>0</result>";
			}
			reXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?><root>" + reXML
					+ "</root>";
			// 渲染返回结果.返回内容为XML格式；
			Struts2Util.renderXml(reXML);
		} catch (Exception e) {
			e.printStackTrace();
			StringToXMLUtil.delete(path, userId + ".xml");
		}
		return null;
	}

	public String cancelPrivilegeAct() {
		// 获取UserId，判断用户是否登陆;
		Integer userId = SessionUtil.getUserId();
		// 获取文件路径
		String path = ServletActionContext.getServletContext().getRealPath(
				"/xml");
		String reXML = "";
		if (userId != null) {
			String fileName = userId + ".xml";
			if (StringToXMLUtil.delete(path, fileName)) {
				reXML = "<result>1</result>";
			}
		}
		if (reXML == null || reXML.equals("")) {
			reXML = "<result>0</result>";
		}
		reXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?><root>" + reXML
				+ "</root>";
		// 渲染返回结果.返回内容为XML格式；
		Struts2Util.renderXml(reXML);
		return null;
	}

	/*
	 * mail
	 */
	public String mail() {
		if (ip == null) {
			return "main";
		}
		userId = SessionUtil.getUserId();
		try {
			sessionUserId = SessionUtil.generateTempId();
			MailCampaignWSUtil.setUserIdMap(userId.toString(), sessionUserId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(userId + "<><><><><><><>" + sessionUserId);
		return "mail";
	}

	public String top() {
		Integer userId = SessionUtil.getUserId();
		if (userId != null) {
			user = this.privilegeService.getUser(userId);
		}
		return "top";
	}

	public String main() {
		return "main";
	}

	public String getTreeBody() {
		return treeBody;
	}

	public void setTreeBody(String treeBody) {
		this.treeBody = treeBody;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	private boolean findChild(String parentCode, List<PrivilegeDTO> codeArrList) {
		if (parentCode == null || parentCode.equals("") || codeArrList == null) {
			return false;
		}

		for (PrivilegeDTO codeArr : codeArrList) {
			if (parentCode.equals(codeArr.getParentCode())) {
				return true;
			}
		}
		return false;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSessionUserId() {
		return sessionUserId;
	}

	public void setSessionUserId(String sessionUserId) {
		this.sessionUserId = sessionUserId;
	}

	public Map<String, String> getActionNameAndMethodMap() {
		return actionNameAndMethodMap;
	}

	public void setActionNameAndMethodMap(
			Map<String, String> actionNameAndMethodMap) {
		this.actionNameAndMethodMap = actionNameAndMethodMap;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getActionNames() {
		return actionNames;
	}

	public void setActionNames(String actionNames) {
		this.actionNames = actionNames;
	}

}
