package com.genscript.gsscm.ws.impl;


import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.manufacture.dao.ManuDocumentDao;
import com.genscript.gsscm.manufacture.entity.ManuDocument;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.manufacture.service.WorkOrderEntryService;
import com.genscript.gsscm.ws.api.OrderUploadFileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import javax.xml.crypto.Data;
import javax.xml.ws.Holder;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebService(serviceName = "OrderUploadFileService", portName = "OrderUploadFileServicePort", endpointInterface = "com.genscript.gsscm.ws.api.OrderUploadFileService", targetNamespace = WsConstants.NS)
public class OrderUploadFileServiceImp implements OrderUploadFileService {
    @Autowired
    private WorkOrderEntryService workOrderEntryService;

    /*
 * path 为传人参数， 文件拷贝路径
 * result 为返回结果，
 * */
    public boolean changeUploadFileNum(String fullpath, Holder<String> result){
        //读取path下的文件，将所有的文件信息记录到数据库中，并将其所有文件拷贝到另一个路径下，
        try{
            //读取文件目录path
            String path = fullpath.trim().split("@")[0];
            //保存文件目录path
            String savepath = fullpath.trim().split("@")[1];
            File filePath = new File(path);
            if(!filePath.isDirectory()){
                result.value="Directory is wrong!";
                return false;
            }else{
                String[] filesList = filePath.list();
                for(int i = 0; i < filesList.length; i++){
                    File file = new File(path + "/" + filesList[i]);
                    ManuDocument doc = new ManuDocument();
                    //解析name，文件name包括单据号等单据信息,这里限定以 - 分割
                    String info[] = file.getName().split("-");
                    if(info.length < 4){
                        result.value = "File's name is wrong!";
                        return false;
                    }
                    if(info[0]==null || "".equals(info[0].trim())){
                        result.value = "File's naming no Order No！";
                        return false;
                    }else if(info[1] == null || "".equals(info[1].trim())){
                        result.value = "File's naming no Item No!";
                        return false;
                    }else if(info[2] == null || "".equals(info[2].trim())){
                        result.value = "File's type no name!";
                        return false;
                    }else if(info[3] == null || "".equals(info[3].trim())){
                        result.value = "File's naming no name!";
                        return false;
                    }
                   //doc.setDocName(info[1].trim());
                    doc.setDocName(info[2] + ":" + file.getName().substring(file.getName().indexOf(info[3]), file.getName().length()));
                    String docType = info[2];
                    if(!"Image & Graph".equals(docType) || !"Reference".equals(docType) || !"Other".equals(docType)){
                          docType = "Document-" + docType;
                    }
                    doc.setDocType(docType);
                    doc.setModifyUser("webservice");
                    doc.setCreatedBy(-1);
                    doc.setModifiedBy(-1);
                    doc.setCreationDate(new Date());
                    doc.setDocId(null);
                    Integer soNo = Integer.valueOf(info[0].trim());
                    Integer sOItem = Integer.valueOf(info[1].trim());
                    List<WorkOrder> workOrders = this.workOrderEntryService.getWorkNos(soNo, sOItem);
                    int index = 1;
                    for (WorkOrder workOrder : workOrders) {
                        String newName = UUID.randomUUID() + file.getName();
                        doc.setFilePath(savepath + "/" + newName);
                        if (index < workOrders.size()) {
                            FileUtils.copyFile(new File(path + "/" + filesList[i]), new File(path + "/" + newName));
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
        }catch (Exception e){
            e.printStackTrace();
            result.value = "webService fail !" + e.toString();
            return false;
        }
        result.value = "success!";
        return true;
    }
}
