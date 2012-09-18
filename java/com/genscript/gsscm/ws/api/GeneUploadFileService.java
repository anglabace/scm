package com.genscript.gsscm.ws.api;

import com.genscript.gsscm.common.constant.WsConstants;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Holder;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 12/1/11
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */

@WebService(name = "GeneUploadFileService", targetNamespace = WsConstants.NS)
public interface GeneUploadFileService {
    public boolean changeUploadFileNum(@WebParam(name="path", mode=WebParam.Mode.IN) String path , @WebParam(name="result", mode = WebParam.Mode.OUT) Holder<String> result);
}
