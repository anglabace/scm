package com.genscript.gsscm.common.zip;

import java.io.File;

public class ZipUtil2 {
	//public static final String password = "123456";   
    public static final String winrarPath = "C:\\Program Files\\WinRAR\\WinRAR.exe";   
    public static ZipUtil instance;
    
    public static ZipUtil getInstance(){
    	if(instance==null)instance = new ZipUtil();
    	return instance;
    }
    /**  
     * 将指定的压缩文件解压缩到指定的路径下 解压缩后在指定的路径下生成一个以压缩文件名的文件夹，文件下即为压缩文件的文件  
     *  
     * @param zipFile  
     *            压缩文件路径  
     * @param folder  
     *            要解压到何处的路径  
     * @return  
     */  
    public static boolean unzip(String zipFile, String folder) {   
        boolean bool = false;   
        folder = folder + stringUtil(zipFile);   
        String cmd = winrarPath + " x -iext -ow -ver -- " + zipFile + " "  
                + folder;   
        int source = zipFile.lastIndexOf("\\") + 1;   
        String newPath = zipFile.substring(source);   
        if (FileUtil.isFileExist(folder , newPath)) {   
            bool = false;   
            String msg = "在" + folder + "下文件" + newPath + "已经存在";   
        } else {   
            try {   
                Process proc = Runtime.getRuntime().exec(cmd);   
                if (proc.waitFor() != 0) {   
                    if (proc.exitValue() == 0) {   
                        bool = false;   
                    }   
                } else {   
                    bool = true;   
                }   
            } catch (Exception e) {   
                e.printStackTrace();   
            }   
        }   
        return bool;   
    }   
    public static boolean zip(String zipFile, String sourceFile,String password){
    	boolean bool = false;
    	String cmd = winrarPath + " a -p"+password + " " +  zipFile + " " + sourceFile;

    	if(FileUtil.isFileExist(zipFile)){
    		String msg =  "在" + zipFile + "下文件" + zipFile + "已经存在";  
    		//文件存在，先删除
    		FileUtil.getInstance().delFile(zipFile);
    	}
    	try {   
    		Process proc = Runtime.getRuntime().exec(cmd);   
    		if (proc.waitFor() != 0) {   
    			if (proc.exitValue() == 0) {   
    				bool = false;   
    			}   
    		} else {   
    			bool = true;   
    		}   
    	} catch (Exception e) {   
    		e.printStackTrace();   
    	}  

    	return bool;
    }
  
    /**  
     * 解压带有密码的压缩文件 默认密码为123456，如果需要更改则只要修改password的值  
     *  
     * @param zipFile  
     * @param folder  
     * @return  
     */  
    public static boolean zipForPassword(String zipFile, String folder, String password) {   
        boolean bool = false;   
        String _folder = "\"" + folder + stringUtil(zipFile) + "\\" +  "\"";   
                zipFile = "\"" + zipFile + "\"";   
        String cmd = winrarPath + " x -p" + password + " " + zipFile + " "  
                + _folder;   
        int source = zipFile.lastIndexOf("\\") + 1;   
        String newPath = zipFile.substring(source);   
        String folderName = stringUtil(newPath);   
  
        if (FileUtil.isFileExist(folder, folderName)) {   
            bool = false;   
            String msg = "在" + folder + "下文件" + newPath + "已经存在";   
        } else {   
            try {   
                Process proc = Runtime.getRuntime().exec(cmd);   
                if (proc.waitFor() != 0) {   
                    if (proc.exitValue() == 0) {   
                        bool = false;   
                    }   
                } else {   
                    bool = true;   
                }   
            } catch (Exception e) {   
                e.printStackTrace();   
            }   
        }   
        return bool;   
    }   
  
  
  
    /**  
     * String的方法工具，主要是针对路径中取得压缩文件的名称，不包括后缀名  
     *  
     * @param str  
     * @return 压缩文件的名称  
     */  
    public static String stringUtil(String filePath) {   
        String fileName = new File(filePath).getName();   
        String fileRealName = null;   
        int indexStr = fileName.lastIndexOf(".");   
        fileRealName = fileName.substring(0, indexStr);   
        return fileRealName;   
    }   
  
    /**  
     * 测试类  
     *  
     * @param args  
     */  
    public static void main(String[] args) {   
        String zipFile = "D:\\让元素时隐时现.rar";   
        String sourceFile = "D:\\让元素时隐时现.html";   
        boolean b = ZipUtil2.zip(zipFile, sourceFile,"123456");   
        // String path = folder + ZipUtil.stringUtil(zipFile);   
        // boolean d = ZipUtil.deleteFolder(path);   
        System.out.println(b);   
        // System.out.println(d);   
    }   
}  

