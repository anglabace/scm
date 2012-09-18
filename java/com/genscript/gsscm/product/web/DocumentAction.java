package com.genscript.gsscm.product.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.privilege.web.LoginAction;
import com.genscript.gsscm.product.dto.DocumentsDTO;
import com.genscript.gsscm.product.entity.DocumentPro;
import com.genscript.gsscm.product.entity.Documents;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.service.ProductService;

@Results({
		@Result(name = LoginAction.SUCCESS, location = "product/product/setups/document_list.jsp"),
		@Result(name = "edit", location = "product/product/setups/document_detail.jsp") })
public class DocumentAction extends BaseAction<DocumentsDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProductService productService;
	@Autowired
	private PublicService publicService;

	/*
	 * 页面数据；
	 */
	private Page<Documents> page;

	/*
	 * list 页面查询数据。
	 */
	private String catalogNo;
	private String name;
	private String type;
	private String dec;
	/*
	 * 详细信息参数
	 */
	private Integer docId;
	private DocumentsDTO documentDTO;
	private Documents document;

	private List<PbDropdownListOptions> pbOptionItemList;

	// 文件处理
	@Autowired
	private FileService fileService;
	/**
	 * 实际上传文件
	 */
	private File upload;
	private File imageFile;
	/**
	 * 文件的内容类型
	 */
	private String uploadContentType;
	private String imageFileContentType;

	/**
	 * 上传文件名
	 */
	private String uploadFileName;
	private String imageFileFileName;
	private String productFilesType;// 对应product_documents doc_type;

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {

		documentDTO = new DocumentsDTO();
		Documents docment = null;

		List<Product> product = null;
		if (docId != null) {
			docment = this.productService.getDocument(docId);
			product = this.productService.searchProductByDocumentId(docId);
			if (product == null) {
				product = new ArrayList<Product>();
			}

			/*
			 * String docNameStr=document.getDocName(); String
			 * imageNameStr=document.getImageFileName(); String
			 * docpath=document.getDocFilePath(); String
			 * docType=document.getDocFileType();
			 * 
			 * String imagepath=document.getImageFilePath(); String
			 * imagetype=document.getImageFileType();
			 */

		}
		if (docment == null) {
			docment = new Documents();
			product = new ArrayList<Product>();
		} else {
			if (docment.getModifiedBy() != null) {
				User user = this.productService
						.getUser(docment.getModifiedBy());
				if (user != null) {
					documentDTO.setModifiedByName(user.getLoginName());
				}
			}
			/*
			 * if(docment.getDocName()!=null){
			 * docment.setDocName(URLConUtil.getUrl(docment.getDocName())); }
			 * if(docment.getImageFileName()!=null){
			 * docment.setImageFileName(URLConUtil
			 * .getUrl(docment.getImageFileName())); }
			 */
		}

		this.pbOptionItemList = this.publicService
				.getDropdownList("PRODUCT_FILE_TYPE");
		this.documentDTO.setDocuments(docment);
		this.documentDTO.setProductId(product);
		return "edit";
	}

	@Override
	public String list() throws Exception {
		PagerUtil<Documents> pagerUtil = new PagerUtil<Documents>();
		page = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null || page.getPageSize().intValue() < 1) {
			page.setPageSize(15);
		}
		if (!page.isOrderBySetted()) {
			page.setOrderBy("docId");
			page.setOrder(Page.DESC);
		}

		if (catalogNo != null && !"".equals(catalogNo)) {
			catalogNo = catalogNo.trim();
		}
		if (name != null && !"".equals(name)) {
			name = name.trim();
		}
		if (dec != null && !"".equals(dec)) {
			dec = dec.trim();
		}
		page = this.productService.searchAllDocument(page, catalogNo, name,
				type, dec);
		this.pbOptionItemList = this.publicService
				.getDropdownList("PRODUCT_FILE_TYPE");
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return SUCCESS;
	}

	public String saveProductFilesSession() throws Exception {
		List<Integer> productIdList = new ArrayList<Integer>();
		List<Integer> delProductIdList = new ArrayList<Integer>();
		Integer userId = SessionUtil.getUserId();
		String productId = ServletActionContext.getRequest().getParameter(
				"productId");
		String delProductId = ServletActionContext.getRequest().getParameter(
				"delProductId");
		System.out.println(imageFileFileName);
		System.out.println(uploadFileName);
		if (productId != null && !productId.equals("")) {
			String[] ids = productId.split(",");
			for (int i = 0; i < ids.length; i++) {
				productIdList.add(Integer.valueOf(ids[i]));
			}
		}
		if (delProductId != null && !delProductId.equals("")) {
			String[] ids = delProductId.split(",");
			for (int i = 0; i < ids.length; i++) {
				delProductIdList.add(Integer.valueOf(ids[i]));
			}
		}

		if (upload != null && !upload.equals("")) {
			String newFileName = SessionUtil.generateTempId();
			String defExt = fileService.getExtension(uploadFileName);
			if (defExt == null) {
				Struts2Util.renderHtml("The 'File' extension is not correct!!");
				return null;
			} else {
				document.setDocFileName(uploadFileName);
				document.setDocFileType(defExt);
				document.setDocFilePath("productFile_notes/" + newFileName
						+ "." + defExt);
				// 保存document;
				fileService.uploadFile(upload, uploadContentType, newFileName
						+ "." + defExt, "productFile_notes");

			}
		}
		if (this.imageFile != null && !upload.equals("")) {
			String newFileName = SessionUtil.generateTempId();
			String defExt = fileService.getExtension(imageFileFileName);
			if (defExt == null) {
				Struts2Util.renderHtml("The 'File(300dpi)' is not correct!!");
				return null;
			} else {
				System.out.println(imageFileFileName);
				document.setImageFileName(imageFileFileName);
				document.setImageFileType(defExt);
				document.setImageFilePath("productFile_notes/" + newFileName
						+ "." + defExt);
				// 保存document;
				fileService.uploadFile(imageFile, imageFileContentType,
						newFileName + "." + defExt, "productFile_notes");
			}
		}
		if (document.getInternalFlag() == null
				|| !document.getInternalFlag().equals("Y")) {
			document.setInternalFlag("N");
		}
		if (document.getValidateFlag() == null
				|| !document.getValidateFlag().equals("Y")) {
			document.setValidateFlag("N");
		}
		Integer id = this.productService.saveProductDocument(document,
				productIdList, delProductIdList, userId);
		Struts2Util.renderHtml(SUCCESS + "," + id);
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

	public List<PbDropdownListOptions> getPbOptionItemList() {
		return pbOptionItemList;
	}

	public void setPbOptionItemList(List<PbDropdownListOptions> pbOptionItemList) {
		this.pbOptionItemList = pbOptionItemList;
	}

	public String getCatalogNo() {
		return catalogNo;
	}

	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDec() {
		return dec;
	}

	public void setDec(String dec) {
		this.dec = dec;
	}

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public DocumentsDTO getDocumentDTO() {
		return documentDTO;
	}

	public void setDocumentDTO(DocumentsDTO documentDTO) {
		this.documentDTO = documentDTO;
	}

	public Documents getDocument() {
		return document;
	}

	public void setDocument(Documents document) {
		this.document = document;
	}

	public Page<Documents> getPage() {
		return page;
	}

	public void setPage(Page<Documents> page) {
		this.page = page;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public String getImageFileContentType() {
		return imageFileContentType;
	}

	public void setImageFileContentType(String imageFileContentType) {
		this.imageFileContentType = imageFileContentType;
	}

	public String getImageFileFileName() {
		return imageFileFileName;
	}

	public void setImageFileFileName(String imageFileFileName) {
		this.imageFileFileName = imageFileFileName;
	}

	public String getProductFilesType() {
		return productFilesType;
	}

	public void setProductFilesType(String productFilesType) {
		this.productFilesType = productFilesType;
	}

}
