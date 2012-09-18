package com.genscript.gsscm.common.util;

import org.apache.commons.beanutils.BeanUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 10/27/11
 * Time: 9:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReadRTF {

    //rtf 文档打印的分页符
    private static String rTFPageCut = "<br clear=all style='mso-special-character:line-break;page-break-before:always'/>";

    public String getRTFPageCut(){
        return rTFPageCut;
    }

    public String generateDoc(String filePath, Object beanObj){
       String templateRTF = readRtf(filePath);
       String docStr  = "";
       if(!"".equals(templateRTF)){
           Map pros = getBeanProperty(beanObj);
           if(pros != null){
               for(Object keyObj : pros.keySet()){
                   String key = keyObj.toString();
                   String value = pros.get(key) == null ? "" : pros.get(key).toString();
                   if("".equals(docStr)){
                        docStr = templateRTF.replace("$" + key + "$", value);
                   }else{
                       docStr = docStr.replace("$" + key + "$", value);
                   }
               }
           }else {
               docStr = "Template data is wrong!";
           }
       }else {
           docStr = "Template document is not exits";
       }
       return docStr;
    }
    public String readRtf(String filePath){
        InputStream in = null;
        String templateStr = "";
        try{
        File file = new File(filePath);
            in = new FileInputStream(file);
            byte[] templateByte = new byte[in.available()];
            int byteRead = 0;
            while (byteRead != -1){
                 byteRead = in.read(templateByte);
            }
            templateStr = new String(templateByte, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(in != null){
                try{
                   in.close();
                }catch (Exception e){
                    System.out.println("输入流无法关闭!" + e.getMessage());
                }

            }
        }
        return templateStr;
    }

    /**
     * 字符串转换为rtf编码
     * @param content
     * @return
     */
    public String strToRtf(String content) {

        StringBuffer sb = new StringBuffer("");
        try {
            char[] digital = "0123456789ABCDEF".toCharArray();
            byte[] bs = null;
            bs = content.getBytes("GB2312");
            int bit;
            for (int i = 0; i < bs.length; i++) {
                bit = (bs[i] & 0x0f0) >> 4;
                sb.append("\\'");
                sb.append(digital[bit]);
                bit = bs[i] & 0x0f;
                sb.append(digital[bit]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private Map getBeanProperty(Object beanObj) {
        BeanUtils beanUtils = new BeanUtils();
        Map propertys = null;
        try {
            propertys = BeanUtils.describe(beanObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return propertys;
    }
}
