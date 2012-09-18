package com.genscript.gsscm.common.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 解压zip压缩文件
 * @author 丁腾飞
 * @date   2010/11/18
 */
public class UnZipFile {

    /**
     * 解压zip文件
     * @param targetPath	解压路径
     * @param zipFilePath	*.zip压缩文件绝对路径
     */
    public static void unZipFile(String targetPath, String zipFilePath) {

        try {
            File zipFile = new File(zipFilePath);
            InputStream is = new FileInputStream(zipFile);
            ZipInputStream zis = new ZipInputStream(is);
            ZipEntry entry = null;
            System.out.println("开始解压:" + zipFile.getName() + "...");
            
            while ((entry = zis.getNextEntry()) != null) {
                String zipPath = entry.getName();
                try {
                    if (entry.isDirectory()) {
                        File zipFolder = new File(targetPath + File.separator + zipPath);
                        if (!zipFolder.exists()) {
                            zipFolder.mkdirs();
                        }
                    } else {
                        File file = new File(targetPath + File.separator + zipPath);
                        if (!file.exists()) {
                            File pathDir = file.getParentFile();
                            pathDir.mkdirs();
                            file.createNewFile();
                        }
                        FileOutputStream fos = new FileOutputStream(file);
                        int bread;
                        while ((bread = zis.read()) != -1) {
                            fos.write(bread);
                        }
                        fos.close();
                    }
                    System.out.println("成功解压:" + zipPath);

                } catch (Exception e) {
                    System.out.println("解压" + zipPath + "失败");
                    continue;
                }
            }
            zis.close();
            is.close();
            System.out.println("解压结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @param args	String[]数组类型参数
     */
    public static void main(String[] args) {
        String targetPath = "D:\\test\\unzip";
        String zipFile = "D:\\test\\test.zip";
        UnZipFile.unZipFile(targetPath, zipFile);
    }

}