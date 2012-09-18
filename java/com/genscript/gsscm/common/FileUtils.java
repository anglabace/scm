package com.genscript.gsscm.common;


import java.io.File;

/**
 * 文件处理的通用类.
 * 
 * @version 1.0
 * @author wangsf
 * 
 */
public class FileUtils {


	/**
	 * 删除一文件.
	 * @param fullName
	 */
	public static void delFile(String fullName) {
		File file = new File(fullName);
		if (file.exists()) {
			file.delete();
		}
	}
	
	public static void renameFile(String srcFullName, String tagFullName) {
		File srcFile = new File(srcFullName);
		File tagFile = new File(tagFullName);
        srcFile.renameTo(tagFile);
	}

}
