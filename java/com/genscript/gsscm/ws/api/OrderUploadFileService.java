package com.genscript.gsscm.ws.api;

import com.genscript.gsscm.common.constant.WsConstants;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Holder;

@WebService(name = "OrderUploadFileService", targetNamespace = WsConstants.NS)
public interface OrderUploadFileService {
    public boolean changeUploadFileNum(@WebParam(name="path", mode=WebParam.Mode.IN) String path , @WebParam(name="result", mode = WebParam.Mode.OUT) Holder<String> result);
}
