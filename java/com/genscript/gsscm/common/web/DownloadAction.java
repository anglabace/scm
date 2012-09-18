package com.genscript.gsscm.common.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用于下载文件
 * 
 * @author Administrator
 * 
 */
public class DownloadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7789154975751152038L;

	@Autowired
	private FileService fileService;
	/**
	 * 文件名称
	 */
	private String fileName;

	public String getWwwstr() {
		return wwwstr;
	}

	public void setWwwstr(String wwwstr) {
		this.wwwstr = wwwstr;
	}

	/**
	 * 数据库里存储的相对路径
	 */
	private String filePath;

	private String wwwstr;

	private String oldFlag;

	public String execute() throws Exception {
		// 这里可加入权限控制
		return "SUCCESS";
	}

	/*
	 * public InputStream getDownloadFile() throws FileNotFoundException { //
	 * 相对路径 String str = filePath; String donPath = "";
	 * System.out.println(oldFlag); if(oldFlag!=null&&oldFlag.equals("1")){
	 * donPath = fileService.getOldUploadPath(); String[] strs =
	 * str.split("\\."); String str1 = "";
	 * //System.out.println(strs.length+"<<<<<<<<<"); if(strs.length>1){ for(int
	 * i = 0 ;i<strs.length;i++){ if(i==strs.length-1){
	 * str1+="."+strs[i].toUpperCase(); }else{ if(i==0){ str1+=strs[i]; }else{
	 * str1+="."+strs[i]; }
	 * 
	 * } System.out.println(str+"<<<<<<<<<"); } str = str1; } }else{ donPath =
	 * fileService.getUploadPath(); }
	 * 
	 * return new FileInputStream(donPath+str);
	 * 
	 * }
	 */

	public InputStream getDownloadFile() throws FileNotFoundException {
		// 相对路径
		String str = filePath;
		String donPath = "";
		System.out.println(oldFlag+">>");
		// 说明： zhougang 2011 9 8
		// oldflag 1: bigfile and smallfile ===old
		// oldflag 2:bigfile change to new and smallfile ==oldfile
		// oldflag 3:bigfile is old and smallfile ==new
		// oldflag 4 bigfile and smallfile are new
		//
		System.out.println(wwwstr+"<>>");
		if(wwwstr == null||wwwstr.equals("")){
			wwwstr = "doc";
		}
		if (oldFlag != null ) {
			if (wwwstr.equals("doc")) {
				if (oldFlag.equals("1") || oldFlag.equals("3")) {
					donPath = fileService.getOldUploadPath();
				} else if (oldFlag.equals("2") || oldFlag.equals("4")) {
					donPath = fileService.getUploadPath();
				}else{
					donPath = fileService.getUploadPath();
				}
			} else if (wwwstr.equals("img")) {
				if (oldFlag.equals("1") || oldFlag.equals("2")) {
					donPath = fileService.getOldUploadPath();
				} else if (oldFlag.equals("3") || oldFlag.equals("4")) {
					donPath = fileService.getUploadPath();
				}else{
					donPath = fileService.getUploadPath();
				}
			}else{
				donPath = fileService.getUploadPath();
			}
		} else {
			donPath = fileService.getUploadPath();
		}
		
		return new FileInputStream(donPath + str);

	}

	public String getFileName() throws UnsupportedEncodingException {
		fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOldFlag() {
		return oldFlag;
	}

	public void setOldFlag(String oldFlag) {
		this.oldFlag = oldFlag;
	}

}
