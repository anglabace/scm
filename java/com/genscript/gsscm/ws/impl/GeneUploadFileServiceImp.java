package com.genscript.gsscm.ws.impl;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.manufacture.entity.ManuDocument;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.manufacture.service.WorkOrderEntryService;
import com.genscript.gsscm.ws.api.OrderUploadFileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import javax.xml.ws.Holder;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 12/1/11
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */

@WebService(serviceName = "GeneUploadFileService", portName = "GeneUploadFileServicePort", endpointInterface = "com.genscript.gsscm.ws.api.GeneUploadFileService", targetNamespace = WsConstants.NS)
public class GeneUploadFileServiceImp implements OrderUploadFileService {
    @Autowired
    private WorkOrderEntryService workOrderEntryService;

    /*
 * path 为传人参数， 文件拷贝路径
 * result 为返回结果，
 * */
    public boolean changeUploadFileNum(String fullpath, Holder<String> result){
        Process p = null;
        //读取path下的文件，将所有的文件信息记录到数据库中，并将其所有文件拷贝到另一个路径下，
        try{
            /*
            *执行服务器端的bash文件 ,fullpath 的第三个参数是服务器端shell的执行目录
            * */
            /*String plPath = fullpath.trim().split("@")[2];
			ProcessBuilder pb = new ProcessBuilder(plPath);
			pb.redirectErrorStream(true);
			p = pb.start();
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
            if(input == null || !"success".equals(input.readLine())){
                result.value = "bash exec is Error!";
                return false;
            }*/
            //读取文件目录path
            String path = fullpath.trim().split("@")[0];
            //保存文件目录path
            String savepath = fullpath.trim().split("@")[1];
            File filePath = new File(path);
            if(!filePath.isDirectory()){
                result.value="Directory is wrong!";
                return false;
            }else{
                /*
                * 根据上传目录结构，上传的文件肯定是一层目录，而且gene的为order_id为目录名，SQD为JST开头的目录名
                * */

                String[] filesList = filePath.list();
                for(int i = 0; i < filesList.length; i++){
                    //取得目录
                    File directory = new File(path + "/" + filesList[i]);
                    String directoryName = filesList[i];
                    if(directory.isDirectory()){
                        String[] dirFiles = directory.list();
                        for (int j = 0; j < dirFiles.length; j++) {
                            ManuDocument doc = new ManuDocument();
                            File file = new File(directory.getAbsolutePath()  + "/" + dirFiles[j]);
                            Integer soNo = null;
                            Integer sOItem = null;
                            if (directoryName.startsWith("JST")) {
                                //解析name，文件name包括单据号等单据信息,这里限定以 - 分割
                                String info[] = file.getName().split("-");
                                if (info.length < 3) {
                                    result.value = "File's name is wrong!";
                                    return false;
                                }
                                if (info[0] == null || "".equals(info[0].trim())) {
                                    result.value = "File's naming no Order No！";
                                    return false;
                                } else if (info[1] == null || "".equals(info[1].trim())) {
                                    result.value = "File's naming no Item No!";
                                    return false;
                                }
                                soNo = Integer.valueOf(info[0].trim());
                                sOItem = Integer.valueOf(info[1].trim());
                            } else {
                                soNo = Integer.valueOf(directoryName.split("_")[0]);
                                sOItem = Integer.valueOf(directoryName.split("_")[1]);
                            }

                            doc.setDocName(file.getName());
                            /*String docType = info[2];
                            if (!"Image & Graph".equals(docType) || !"Reference".equals(docType) || !"Other".equals(docType)) {
                                docType = "Document-" + docType;
                            }*/
                            doc.setDocType("");
                            doc.setModifyUser("webservice");
                            doc.setCreatedBy(-1);
                            doc.setModifiedBy(-1);
                            doc.setCreationDate(new Date());
                            doc.setDocId(null);
                            List<WorkOrder> workOrders = this.workOrderEntryService.getWorkNos(soNo, sOItem);
                            int index = 1;
                            for (WorkOrder workOrder : workOrders) {
                                String newName = UUID.randomUUID() + file.getName();
                                doc.setFilePath(savepath + "/" + filesList[i] + "/" + newName);
                                if (index < workOrders.size()) {
                                    FileUtils.copyFile(new File(file.getParent() + file.getName()), new File(file.getParent() + "/" + newName));
                                } else {
                                    //修改文件名称
                                    if (!file.renameTo(new File(file.getParent() + "/" + newName))) {
                                        result.value = "File rename is failed";
                                    }
                                }
                                this.workOrderEntryService.saveDocument(doc, workOrder.getOrderNo(), -1);
                                index++;
                            }
                        }
                    }

                }
            }
            /*
            * 移动文件shell
            * */
            ProcessBuilder pb = new ProcessBuilder(fullpath.trim().split("@")[3]);
			pb.redirectErrorStream(true);
			p = pb.start();
        }catch(IOException e){
            e.printStackTrace();
            if (p != null) {
				p.destroy();
			}
			result.value = "Process Error exec!";
        }catch (Exception e){
            e.printStackTrace();
            result.value = "webService fail !" + e.toString();
            return false;
        }
        result.value = "success!";
        return true;
    }
}
