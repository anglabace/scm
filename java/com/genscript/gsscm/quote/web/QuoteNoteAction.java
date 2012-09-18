package com.genscript.gsscm.quote.web;

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
import com.genscript.gsscm.system.entity.MailTemplates;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.MailContentTemplateDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.QuoteInstructionType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.privilege.service.PrivilegeService;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.service.QuoteService;
import com.genscript.gsscm.quoteorder.dto.InstructionDTO;
import com.genscript.gsscm.system.entity.MailGroup;

@Results( {
		@Result(name = "note_list", location = "quote/quote_note_list.jsp"),
		@Result(name = "note_edit", location = "quote/quote_note_edit.jsp") })
public class QuoteNoteAction extends BaseAction<InstructionDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sessQuoteNo;
	private InstructionDTO quoteNote;
	private String noteKey;
	private String searchNoteType;
	private Map<String, InstructionDTO> noteMap;// 用于返回列表页面时显示数据
	private Map<String, InstructionDTO> noteListMap;// add by zhanghuibin 用于返回列表页面时显示数据
	private String contactDate;// 用于接受页面String，再转为Date
	private String scheduleDate;// 用于接受页面String，再转为Date
	private String noteDate;
	private Date curDate;
	private String tmpStr;
	//  
	private List<MailContentTemplateDTO> mailTmplList;
	private List<MailGroup> mailGroupList;
	// 文件处理
	@Autowired
	private FileService fileService;
	private String docDelIndexs;
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
	private QuoteService quoteService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PrivilegeService privilegeService;
	private String tempStatusStr;
	private String custNo;
    private MailTemplates custConfirmMailTemplate;
    @Autowired(required = false)
	private DozerBeanMapper dozer;


	public String edit() throws Exception {
		this.prepareModel();
		this.mailGroupList = publicService.getMailGroupList(Constants.MAIL_GROUP_QUOTE_FOLLOWUP);// 获得已配置的邮箱列表模板
		this.mailTmplList = publicService.getContentTmplDTOList();// 获得已配置的邮箱内容列表.
		curDate=new Date();
		//获取quote的状态
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.
			getRow(SessionConstant.Quote.value(), sessQuoteNo);
		if (quote != null) {
			tempStatusStr = quote.getStatus();
			// 新增时
			if (StringUtils.isBlank(this.noteKey)) {
				quoteNote = quoteNote==null?new InstructionDTO():quoteNote;
				if (quote.getCustNo() != null) {
					String receipt = quoteNote.getReceipt();
					CustomerDTO cust = customerService.getCustomerBase(quote.getCustNo());
					receipt = cust.getBusEmail();
					if (cust.getTechSupport() != null) {
						User userById = privilegeService.findUserByUserId(cust.getTechSupport());
						if (userById != null && StringUtils.isNotBlank(userById.getEmail())) {
							receipt += "," + userById.getEmail();
						}
					}
					quoteNote.setReceipt(receipt);
				}
			}
		}
        //取得template
        if (StringUtils.isNotBlank(sessQuoteNo) && StringUtils.isNumeric(sessQuoteNo.trim())) {
			custConfirmMailTemplate = this.quoteService.findConfirmTemp(Integer.parseInt(sessQuoteNo.trim()));
		}
		return "note_edit";
	}

	public String save()throws Exception {
		System.out.println("save method: " + quoteNote);
		System.out.println("sessQuoteNo: " + sessQuoteNo);
		System.out.println("noteKey: " + noteKey);
		List<Integer> delDocIdList = null;
		List<Document> documentList = new ArrayList<Document>();
		if (StringUtils.isBlank(this.noteKey)) {// 新增产生唯一主健
			noteKey = SessionUtil.generateTempId();
		} else {// 获取DocumentList, 及delDocList.
			try {
				InstructionDTO sessQuoteNote = (InstructionDTO) SessionUtil
						.getOneRow(SessionConstant.QuoteNote.toString(),
								sessQuoteNo, noteKey);
				List<Document> sessDocumentList = sessQuoteNote
						.getDocumentList();
				delDocIdList = sessQuoteNote.getDelDocIdList();
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
		quoteNote.setCreateUser(SessionUtil.getUserName());
		quoteNote.setInstructionDate(new Date());// 设置列表页面显示的Date.
		// 把页面传来的字符串值转为Date;
		if (StringUtils.isNotBlank(this.contactDate)) {
			quoteNote.setSendDate(DateUtils.formatStr2Date(contactDate,
					"yyyy-MM-dd"));
			quoteNote.setInstructionDate(quoteNote.getSendDate());
			quoteNote.setNoteDate(quoteNote.getSendDate());
		}
		// 把页面传来的字符串值转为Date;
		if (StringUtils.isNotBlank(this.scheduleDate)) {
			Date sDate = DateUtils.formatStr2Date(scheduleDate,
			"yyyy-MM-dd");
			quoteNote.setScheduleDate(sDate);
			quoteNote.setInstructionDate(quoteNote.getScheduleDate());
			quoteNote.setNoteDate(quoteNote.getScheduleDate());
		}
		if (StringUtils.isNotBlank(noteDate)) {
			Date nDate = DateUtils.formatStr2Date(noteDate,
			"yyyy-MM-dd");
			quoteNote.setNoteDate(nDate);

			quoteNote.setInstructionDate(nDate);
		}
		
		// 更新documentList列表里的内容(追加要上传的document对象).
		if (upload != null && !upload.isEmpty()) {
			for (int i = 0; i < upload.size(); i++) {
				Document doc = new Document();
				doc.setDocName(uploadFileName.get(i));
				String srcFileName = uploadFileName.get(i);
				uploadFileName.set(i, SessionUtil.generateTempId()
						+ srcFileName.substring(srcFileName.lastIndexOf(".")));
				doc.setFilePath("quote_notes/" + uploadFileName.get(i));
				documentList.add(doc);
			}
		}
		// 重新关联并保存或更新Quote模块下Instruction/Note标签.
		quoteNote.setDocumentList(documentList);
		quoteNote.setDelDocIdList(delDocIdList);
		SessionUtil.updateOneRow(SessionConstant.QuoteNote.toString(),
				this.sessQuoteNo, this.noteKey, quoteNote);
		// 上传文件
		fileService.uploadFile(upload, uploadContentType, uploadFileName,
				"quote_notes");
		Struts2Util.renderText("<pre>" + sessQuoteNo + "</pre>");// ajaxForm方式提交返回需要用html标记包含进去.
		return null;
	}

	public String delete() {
		return null;
	}
	
	public String list() {
        noteListMap = new HashMap<String, InstructionDTO>();
		noteMap = this.getCurrentNoteMap(searchNoteType);
		if (noteMap == null) {
			noteMap = new HashMap<String, InstructionDTO>();
		}
		QuoteMainDTO quote = (QuoteMainDTO)SessionUtil.getRow(SessionConstant.Quote
				.value(), sessQuoteNo);
		status = quote.getStatus();
        //add by zhanghuibin
        @SuppressWarnings("rawtypes")
		Iterator it = noteMap.keySet().iterator();
        while (it.hasNext()) {
            String key = (String)it.next();
            InstructionDTO instructionDTO = (InstructionDTO)noteMap.get(key);
            InstructionDTO dtoTemp = new InstructionDTO();
            dozer.map(instructionDTO, dtoTemp);
            dtoTemp.setDescription(StringUtil.splitAndFilterString(instructionDTO.getDescription(), 30));
            dtoTemp.setContent(StringUtil.splitAndFilterString(instructionDTO.getContent(), 30));
            noteListMap.put(key, dtoTemp);
        }
		return "note_list";
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
		QuoteInstructionType instructionType = null;
		if (StringUtils.isNotBlank(searchNoteType)) {
			instructionType = QuoteInstructionType
					.fromValue(searchNoteType);
		}
		// 获得数据库中的
		if (StringUtils.isNumeric(sessQuoteNo)) {
			List<InstructionDTO> dtoList = this.quoteService
					.getInstructionList(Integer.valueOf(sessQuoteNo),
							instructionType);
			dbNoteMap = new LinkedHashMap<String, InstructionDTO>();
			for (InstructionDTO dto : dtoList) {
				dbNoteMap.put(dto.getId() + "", dto);
			}
			if (custNo != null && StringUtils.isNumeric(custNo)) {
				List<InstructionDTO> dtoInCustomerList = this.quoteService
					.getInstructionListInCustomer(Integer.valueOf(custNo),
							sessQuoteNo, searchNoteType);
				if (dtoInCustomerList != null && dtoInCustomerList.size() > 0) {
					for (InstructionDTO dto : dtoInCustomerList) {
						dbNoteMap.put(dto.getCustNoteId(), dto);
					}
				}
			}
		}
		// 取得session中的
		Map<String, InstructionDTO> sessNoteMap = (Map<String, InstructionDTO>) SessionUtil
				.getRow(SessionConstant.QuoteNote.value(), sessQuoteNo);	
		Map<String, InstructionDTO> searchedSessionMap = this
				.searchSessionNote(sessNoteMap, instructionType);
		Map<String, InstructionDTO> noteMap = SessionUtil.mergeList(searchedSessionMap, dbNoteMap, 1);
		return noteMap;
	}

	/**
	 * 对Session中的QuoteNote根据查找条件进行过滤, 本方法中没有考虑删除QuoteNote的情况.
	 * 
	 * @param sessNoteMap
	 * @param instType
	 * @return
	 */
	private Map<String, InstructionDTO> searchSessionNote(
			final Map<String, InstructionDTO> sessNoteMap,
			QuoteInstructionType instType) {
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

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isBlank(this.noteKey)) {// 新增时
			quoteNote = new InstructionDTO();
		} else {// 修改时
			if (noteKey.contains(QuoteInstructionType.CUSTOMER.value()+"-")) {
				quoteNote = quoteService.getInstructByCustNoteId(noteKey, sessQuoteNo);
				return;
			}
			Object obj = SessionUtil.getOneRow(SessionConstant.QuoteNote
					.value(), this.sessQuoteNo, this.noteKey);
			if (obj != null) {
				quoteNote = (InstructionDTO) obj;
			} else {
				if (StringUtils.isNumeric(this.noteKey)) {
					quoteNote = this.quoteService.getInstructionDTO(Integer
							.valueOf(this.noteKey), this.searchNoteType);
					SessionUtil.updateOneRow(SessionConstant.QuoteNote
							.toString(), this.sessQuoteNo, this.noteKey,
							quoteNote);
				} else {
					quoteNote = (InstructionDTO) obj;
				}
			}
		}
	}

	@Override
	public InstructionDTO getModel() {
		return this.quoteNote;
	}

	public String getSessQuoteNo() {
		return sessQuoteNo;
	}

	public void setSessQuoteNo(String sessQuoteNo) {
		this.sessQuoteNo = sessQuoteNo;
	}

	public InstructionDTO getQuoteNote() {
		return quoteNote;
	}

	public void setQuoteNote(InstructionDTO quoteNote) {
		this.quoteNote = quoteNote;
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

	public String getTempStatusStr() {
		return tempStatusStr;
	}

	public void setTempStatusStr(String tempStatusStr) {
		this.tempStatusStr = tempStatusStr;
	}

	public Date getCurDate() {
		return curDate;
	}

	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}

	public String getTmpStr() {
		return tmpStr;
	}

	public void setTmpStr(String tmpStr) {
		this.tmpStr = tmpStr;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getNoteDate() {
		return noteDate;
	}

	public void setNoteDate(String noteDate) {
		this.noteDate = noteDate;
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
