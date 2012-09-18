package com.genscript.gsscm.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Common File Service
 * 
 * @author zouyulu
 * 
 */
@Service
@Transactional
public class FileService {

	/**
	 * 绝对路径
	 */
	private String uploadPath;
	private String uploadQuote;
	private String oldUploadPath; 
	/**
	 * 临时路径
	 */
	private String uploadTmpPath;

	/**
	 * 相对路径
	 */
	private String uploadRelaPath;

	public FileService() {

	}

	public FileService(String uploadPath, String uploadTmpPath,
			String uploadRelaPath,String oldUploadPath) {
		this.setUploadPath(uploadPath);
		this.setUploadTmpPath(uploadTmpPath);
		this.setUploadRelaPath(uploadRelaPath);
		this.setOldUploadPath(oldUploadPath);
	}
	
	/**
	 * 上传文件至正式文件夹
	 * 
	 * @param files
	 * @param uploadContentTypes
	 * @param uploadFileNames
	 * @param relaPath
	 */
	public void uploadFile(List<File> files,
			List<String> uploadContentTypes, List<String> uploadFileNames,
			String relaPath) {
		if(files !=null && !files.isEmpty()){
			for (int i = 0; i < files.size(); i++) {
				String targetDirectory = this.getUploadPath() + relaPath;
				String targetFileName = uploadFileNames.get(i);
				File target = new File(targetDirectory, targetFileName);
				try {
					//如果是空文件，则默认写入一个空格，避免出错。
					if(files.get(i).length() == 0.0){
						BufferedWriter bw = new BufferedWriter(new FileWriter(files.get(i), true));
						bw.write(" ");
						bw.close();
					}
					FileUtils.copyFile(files.get(i), target);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 上传文件至正式文件夹
	 * 
	 * @param files
	 * @param uploadContentTypes
	 * @param uploadFileNames
	 * @param relaPath
	 */
	public void uploadFile(File file,
		String uploadContentTypes, String uploadFileNames,String relaPath) {
		if(file !=null){
			String targetDirectory = this.getUploadPath() + relaPath;
			 
			File target = new File(targetDirectory, uploadFileNames);
			try {
				FileUtils.copyFile(file, target);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 获取文件扩展名；
	 */
	public String getExtension(String filename){
		if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');

            if ((i > 0) && (i < (filename.length() - 1))) {
                return filename.substring(i + 1);
            }
        }
        return null;


	}
	
	/**
	 * 上传文件至临时文件夹
	 * 
	 * @param files
	 * @param uploadContentTypes
	 * @param uploadFileNames
	 * @param relaPath
	 */
	public void uploadFileToTmpDir(List<File> files,
			List<String> uploadContentTypes, List<String> uploadFileNames,
			String relaPath) {
		if(files !=null && !files.isEmpty()){
			for (int i = 0; i < files.size(); i++) {
				String targetDirectory = this.getUploadTmpPath() + relaPath;
				String targetFileName = uploadFileNames.get(i);
				File target = new File(targetDirectory, targetFileName);
				try {
					FileUtils.copyFile(files.get(i), target);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadRelaPath(String uploadRelaPath) {
		this.uploadRelaPath = uploadRelaPath;
	}

	public String getUploadRelaPath() {
		return uploadRelaPath;
	}

	public String getUploadTmpPath() {
		return uploadTmpPath;
	}

	public void setUploadTmpPath(String uploadTmpPath) {
		this.uploadTmpPath = uploadTmpPath;
	}

	public String getUploadQuote() {
		return uploadQuote;
	}

	public void setUploadQuote(String uploadQuote) {
		this.uploadQuote = uploadQuote;
	}

	public String getOldUploadPath() {
		return oldUploadPath;
	}

	public void setOldUploadPath(String oldUploadPath) {
		this.oldUploadPath = oldUploadPath;
	}

}