package com.genscript.gsscm.order.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.genscript.gsscm.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.MailContentTemplateDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.OrderInstructionType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.privilege.service.PrivilegeService;
import com.genscript.gsscm.quoteorder.dto.InstructionDTO;
import com.genscript.gsscm.system.entity.MailGroup;
import com.genscript.gsscm.system.entity.MailTemplates;

@Results( {
		@Result(name = "note_list", location = "order/order_note_list.jsp"),
		@Result(name = "note_edit", location = "order/order_note_edit.jsp"), 
		@Result(name = "view_order_note", location = "order/view_order_note.jsp"),
		@Result(name = "view_production", location = "manufacture/view_production.jsp"),
		@Result(name = "production_ins_detail", location = "manufacture/production_ins_detail.jsp")
})
public class OrderNoteAction extends BaseAction<InstructionDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sessOrderNo;
	private InstructionDTO orderNote;
	private String noteKey;
	private String searchNoteType;
	private Map<String, InstructionDTO> noteMap;// 用于返回列表页面时显示数据
	private Map<String, InstructionDTO> noteListMap;// 用于返回列表页面时显示数据
	private String contactDate;// 用于接受页面String，再转为Date
	private String scheduleDate;// 用于接受页面String，再转为Date
	//
	private List<MailContentTemplateDTO> mailTmplList;
	private List<MailGroup> mailGroupList;
	// 文件处理
	@Autowired
	private FileService fileService;
	private String docDelIndexs;
	private Date curDate;
	private String tmpStr;
	/**
	 * 实际上传文件
	 */
	private List<File> upload;
	/**
	 * 文件的内容类型
	 */
	private List<String> uploadContentType;
	
	private String status;
	/**
	 * 上传文件名
	 */
	private List<String> uploadFileName;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private PrivilegeService privilegeService;
    @Autowired(required = false)
	private DozerBeanMapper dozer;
	
	private String custNo;
	private MailTemplates custConfirmMailTemplate;

	public String list() {
        noteListMap = new HashMap<String, InstructionDTO>();
		noteMap = this.getCurrentNoteMap(searchNoteType);
		if (noteMap == null) {
			noteMap = new HashMap<String, InstructionDTO>();
		}
		OrderMainDTO order = (OrderMainDTO)SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
		status = order.getStatus();
        //add by zhanghuibin
        @SuppressWarnings("rawtypes")
		Iterator it = noteMap.keySet().iterator();
        while (it.hasNext()) {
            String key = (String)it.next();
            InstructionDTO instructionDTO = noteMap.get(key);
            InstructionDTO dtoTemp = new InstructionDTO();
            dozer.map(instructionDTO, dtoTemp);
            dtoTemp.setDescription(StringUtil.splitAndFilterString(instructionDTO.getDescription(), 30));
            dtoTemp.setContent(StringUtil.splitAndFilterString(instructionDTO.getContent(), 30));
            noteListMap.put(key, dtoTemp);
        }
		return "note_list";
	}
	
	public String viewProductionIns() {
		noteMap = this.getCurrentNoteMap(searchNoteType);
		return "view_production";
	}
	
	public String loadProductionIns() {
		try {
			orderNote = this.orderService.getInstructionDTO(Integer
					.valueOf(this.noteKey), this.searchNoteType);
		} catch (Exception ex) {
			return "production_ins_detail";
		}
		return "production_ins_detail";
	}
	
	public String getEmailNoteFlag() throws Exception{
		noteMap = this.getCurrentNoteMap("CUST_CONFIRM_EMAIL");
		if(noteMap == null){
			Struts2Util.renderText("N");
			return null;
		}
		Iterator<Entry<String, InstructionDTO>> it = noteMap.entrySet().iterator();
		boolean completeFlag = true;
		while(it.hasNext()){
			Entry<String, InstructionDTO> entry = it.next();
			if(!entry.getValue().getStatus().equalsIgnoreCase("COMPLETE")){
				completeFlag = false;
				break;
			}
		}
		if(completeFlag == true){
			Struts2Util.renderText("Y");
		}else{
			Struts2Util.renderText("N");
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, InstructionDTO> getCurrentNoteMap(String searchNoteType){
		Map<String, InstructionDTO> dbNoteMap = null;
		OrderInstructionType instructionType = null;
		if (StringUtils.isNotBlank(searchNoteType)) {
			instructionType = OrderInstructionType
					.fromValue(searchNoteType);
		}
		// 获得数据库中的
		if (StringUtils.isNumeric(sessOrderNo)) {
			List<InstructionDTO> dtoList = this.orderService
					.getInstructionList(Integer.valueOf(sessOrderNo),
							instructionType);
			dbNoteMap = new LinkedHashMap<String, InstructionDTO>();
			for (InstructionDTO dto : dtoList) {
				dbNoteMap.put(dto.getId() + "", dto);
			}
			if (custNo != null && StringUtils.isNumeric(custNo)) {
				List<InstructionDTO> dtoInCustomerList = this.orderService
					.getInstructionListInCustomer(Integer.valueOf(custNo),
							sessOrderNo, searchNoteType);
				if (dtoInCustomerList != null && dtoInCustomerList.size() > 0) {
					for (InstructionDTO dto : dtoInCustomerList) {
						dbNoteMap.put(dto.getCustNoteId(), dto);
					}
				}
			}
		}
		// 取得session中的
		Map<String, InstructionDTO> sessNoteMap = (Map<String, InstructionDTO>) SessionUtil
				.getRow(SessionConstant.OrderNote.value(), sessOrderNo);	
		Map<String, InstructionDTO> searchedSessionMap = this
				.searchSessionNote(sessNoteMap, instructionType);
		Map<String, InstructionDTO> noteMap = SessionUtil.mergeList(searchedSessionMap, dbNoteMap, 1);
		return noteMap;
	}

	/**
	 * 对Session中的OrderNote根据查找条件进行过滤, 本方法中没有考虑删除OrderNote的情况.
	 * 
	 * @param sessNoteMap
	 * @param instType
	 * @return
	 */
	private Map<String, InstructionDTO> searchSessionNote(
			final Map<String, InstructionDTO> sessNoteMap,
			OrderInstructionType instType) {
		if(sessNoteMap == null){
			return null;
		}
		Map<String, InstructionDTO> searchedMap = new HashMap<String, InstructionDTO>();
		if (instType == null) {
			return sessNoteMap;
		}
		for (Entry<String, InstructionDTO> entry : sessNoteMap.entrySet()) {
			InstructionDTO tempDTO = (InstructionDTO) entry.getValue();
			if (instType.value().equals(
					tempDTO.getType())) {
				searchedMap.put(entry.getKey(), tempDTO);// 符合条件的.
			}
		}
		return searchedMap;
	}

	public String edit() throws Exception {
		System.out.println("edit ==================");
		this.prepareModel();
		this.mailGroupList = publicService.getMailGroupList(Constants.MAIL_GROUP_ORDER_CONFIRMATION);// 获得已配置的邮箱列表模板
		this.mailTmplList = publicService.getContentTmplDTOList();// 获得已配置的邮箱内容列表.
		this.curDate=new Date();
		if (StringUtils.isNotBlank(sessOrderNo) && StringUtils.isNumeric(sessOrderNo.trim())) {
			custConfirmMailTemplate = this.orderService.findConfirmTemp(Integer.parseInt(sessOrderNo.trim()));
			if(orderNote.getOrderNo()==null){
				orderNote.setOrderNo(Integer.valueOf(sessOrderNo.trim()));
			}
		}
		System.out.println("edit orderNote: " + orderNote);
		return "note_edit";
	}

	public String save() throws Exception {
		System.out.println("save method: " + orderNote);
		System.out.println("sessOrderNo: " + sessOrderNo);
		System.out.println("noteKey: " + noteKey);
		List<Integer> delDocIdList = null;
		List<Document> documentList = new ArrayList<Document>();
		if (StringUtils.isBlank(this.noteKey)) {// 新增产生唯一主健
			noteKey = SessionUtil.generateTempId();
		} else {// 获取DocumentList, 及delDocList.
			try {
				InstructionDTO sessOrderNote = (InstructionDTO) SessionUtil
						.getOneRow(SessionConstant.OrderNote.toString(),
								sessOrderNo, noteKey);
				List<Document> sessDocumentList = sessOrderNote
						.getDocumentList();
				delDocIdList = sessOrderNote.getDelDocIdList();
				if (sessDocumentList != null && !sessDocumentList.isEmpty()) {
					List<String> indexList = new ArrayList<String>();
					System.out.println("docDelIndexs====" + docDelIndexs);
					if (!StringUtils.isEmpty(docDelIndexs)) {
						String[] tmpArr = StringUtils.split(docDelIndexs, ",");
						for (String s : tmpArr) {
							indexList.add(s);
						}
					}
					if (delDocIdList == null) {
						delDocIdList = new ArrayList<Integer>();
					}
					for (int i = 0; i < sessDocumentList.size(); i++) {
						System.out.println("sessDocumentList.get(i): "
								+ sessDocumentList.get(i));
						if (indexList.contains(Integer.toString(i))) {
							if (sessDocumentList.get(i).getDocId() != null) {
								delDocIdList.add(sessDocumentList.get(i)
										.getDocId());
							}
						} else {
							documentList.add(sessDocumentList.get(i));
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		orderNote.setCreateUser(SessionUtil.getUserName());
		orderNote.setInstructionDate(new Date());// 设置列表页面显示的Date.
		// 把页面传来的字符串值转为Date;
		if (StringUtils.isNotBlank(this.contactDate)) {
			orderNote.setSendDate(DateUtils.formatStr2Date(contactDate,
					"yyyy-MM-dd"));
			orderNote.setInstructionDate(orderNote.getSendDate());
		}
		// 把页面传来的字符串值转为Date;
		if (StringUtils.isNotBlank(this.scheduleDate)) {
			orderNote.setScheduleDate(DateUtils.formatStr2Date(scheduleDate,
					"yyyy-MM-dd"));
			orderNote.setInstructionDate(orderNote.getScheduleDate());
		}
		// 更新documentList列表里的内容(追加要上传的document对象).
		if (upload != null && !upload.isEmpty()) {
			for (int i = 0; i < upload.size(); i++) {
				Document doc = new Document();
				doc.setDocName(uploadFileName.get(i));
				String srcFileName = uploadFileName.get(i);
				uploadFileName.set(i, SessionUtil.generateTempId()
						+ srcFileName.substring(srcFileName.lastIndexOf(".")));
				doc.setFilePath("order_notes/" + uploadFileName.get(i));
				documentList.add(doc);
			}
		}
		// 重新关联并保存或更新Order模块下Instruction/Note标签.
		orderNote.setDocumentList(documentList);
		orderNote.setDelDocIdList(delDocIdList);
		SessionUtil.updateOneRow(SessionConstant.OrderNote.toString(),
				this.sessOrderNo, this.noteKey, orderNote);
		System.out.println("noteKey="+noteKey);
		// 上传文件
		fileService.uploadFile(upload, uploadContentType, uploadFileName,
				"order_notes");
		if(orderNote.getId()==null){
			
			Struts2Util.renderText("<a href=\"javascript:void(0)\" onclick=\"show_edit('"+noteKey+"', 'SHIPMENT','"+sessOrderNo+"')\"><font color=\"red\">"+orderNote.getDescription()+"</font>&nbsp;("+sessOrderNo+")</a>;&nbsp;&nbsp;&nbsp;");// ajaxForm方式提交返回需要用html标记包含进去.
		}else{
			Struts2Util.renderText("");// ajaxForm方式提交返回需要用html标记包含进去.
		}
		
		return null;
	}

	public String delete() {
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String viewOrderNote () {
		try {
			orderNote = this.orderService.getInstructionDTO(Integer
					.valueOf(this.noteKey), this.searchNoteType);
		} catch (Exception ex) {
			return "view_order_note";
		}
		return "view_order_note";
	}

	@Override
	protected void prepareModel() throws Exception {
		System.out.println("order note prepareModel===============");
		if (StringUtils.isBlank(this.noteKey)) {// 新增时
			orderNote = new InstructionDTO();
			if (StringUtils.isNotBlank(sessOrderNo)) {
				OrderMainDTO sessOrder = (OrderMainDTO)SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo.trim());
				if (sessOrder != null) {
					String receipt = null;
					Customer customer = orderService.getCustomerDao().getById(sessOrder.getCustNo());
					if (StringUtils.isNotBlank(customer.getBusEmail()) && customer.getTechSupport() != null) {
						receipt = customer.getBusEmail();
						User userById = privilegeService.findUserByUserId(customer.getTechSupport());
						if (userById != null && StringUtils.isNotBlank(userById.getEmail())) {
							receipt += "," + userById.getEmail();
						}
					}
					orderNote.setReceipt(receipt);
				}
			}
		} else {// 修改时
			if (noteKey.contains(OrderInstructionType.CUSTOMER.value()+"-")) {
				orderNote = orderService.getInstructByCustNoteId(noteKey, sessOrderNo);
				return;
			}
			Object obj = SessionUtil.getOneRow(SessionConstant.OrderNote
					.value(), this.sessOrderNo, this.noteKey);
			if (obj != null) {
				orderNote = (InstructionDTO) obj;
			} else {
				if (StringUtils.isNumeric(this.noteKey)) {
					orderNote = this.orderService.getInstructionDTO(Integer
							.valueOf(this.noteKey), this.searchNoteType);
					SessionUtil.updateOneRow(SessionConstant.OrderNote
							.toString(), this.sessOrderNo, this.noteKey,
							orderNote);
				} else {
					orderNote = (InstructionDTO) obj;
				}
			}
		}
	}

	@Override
	public InstructionDTO getModel() {
		return this.orderNote;
	}

	public String getSessOrderNo() {
		return sessOrderNo;
	}

	public void setSessOrderNo(String sessOrderNo) {
		this.sessOrderNo = sessOrderNo;
	}

	public InstructionDTO getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(InstructionDTO orderNote) {
		this.orderNote = orderNote;
	}

	public String getNoteKey() {
		return noteKey;
	}

	public void setNoteKey(String noteKey) {
		this.noteKey = noteKey;
	}

	public Map<String, InstructionDTO> getNoteMap() {
		return noteMap;
	}

	public void setNoteMap(Map<String, InstructionDTO> noteMap) {
		this.noteMap = noteMap;
	}

	public String getSearchNoteType() {
		return searchNoteType;
	}

	public void setSearchNoteType(String searchNoteType) {
		this.searchNoteType = searchNoteType;
	}

	public String getContactDate() {
		return contactDate;
	}

	public void setContactDate(String contactDate) {
		this.contactDate = contactDate;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public List<MailContentTemplateDTO> getMailTmplList() {
		return mailTmplList;
	}

	public void setMailTmplList(List<MailContentTemplateDTO> mailTmplList) {
		this.mailTmplList = mailTmplList;
	}

	public List<MailGroup> getMailGroupList() {
		return mailGroupList;
	}

	public void setMailGroupList(List<MailGroup> mailGroupList) {
		this.mailGroupList = mailGroupList;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getDocDelIndexs() {
		return docDelIndexs;
	}

	public void setDocDelIndexs(String docDelIndexs) {
		this.docDelIndexs = docDelIndexs;
	}

	public Date getCurDate() {
		return curDate;
	}

	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getTmpStr() {
		return tmpStr;
	}

	public void setTmpStr(String tmpStr) {
		this.tmpStr = tmpStr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MailTemplates getCustConfirmMailTemplate() {
		return custConfirmMailTemplate;
	}

	public void setCustConfirmMailTemplate(MailTemplates custConfirmMailTemplate) {
		this.custConfirmMailTemplate = custConfirmMailTemplate;
	}

    public Map<String, InstructionDTO> getNoteListMap() {
        return noteListMap;
    }

    public void setNoteListMap(Map<String, InstructionDTO> noteListMap) {
        this.noteListMap = noteListMap;
    }
}
