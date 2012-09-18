<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>

<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css"/>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css"/>

 

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript">
	function confirm(){
		var input=document.getElementsByTagName("input");
		var rdoValues=new Array();
		var woBatchIds=new Array();
		var orderNos=new Array();
		var p=0;
		var q=0;
		var w=0;
		for(var i=0;i<input.length;i++){
			if(input[i].type=="radio" && input[i].checked){
				rdoValues[p]=input[i].value;
				p++;
			}
			if(input[i].type=="hidden" && input[i].name=="woBatchId"){
				woBatchIds[q]=input[i].value;
				q++;
			}
			if(input[i].type=="hidden" && input[i].name=="orderNo"){
				orderNos[w]=input[i].value;
				w++;
			}
		}
		parent.location.href="work_order!workOrderSave.action?miss="+rdoValues+"&woBatchIds="+woBatchIds+"&orderNos="+orderNos;
	}
</script>

<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css"/>
</head><body>
<table id="table11" width="790" bgcolor="#96bdea" border="0" cellpadding="0" cellspacing="3">
  <tbody><tr>
    <td bgcolor="#ffffff"><table width="790" border="0" cellpadding="0" cellspacing="0">
      <tbody><tr>
        <td align="left" background="images/header_bg.gif" height="39" valign="top"><div class="line_l_new">Unmatched Package Items</div>
          <div class="line_r_new" onclick="window.parent.closeiframe()"><img src="images/w_close.gif" width="11" height="11"/>Close</div></td>
      </tr>
      <tr>
        <td style="padding-left: 40px;"><br/>
          <table border="0" cellpadding="0" cellspacing="0">
          <tbody><tr>
            <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tbody><tr>
                <td>Following unmatched package items need your attention:</td>
                </tr>
            </tbody></table>
              <table width="700" border="0" cellpadding="0" cellspacing="0">
                <tbody><tr>
                  <td colspan="4" style="padding-top: 10px;"><table class="list_table" width="700" border="0" cellpadding="0" cellspacing="0">
                    <tbody><tr>
                      <th width="110">Batch No</th>
                      <th width="100">Work Order No</th>
                      <th width="65">Item No</th>
                      <th width="80">Qty Shipped</th>
                      <th width="90">Qty Received</th>
                      <th width="80">Size Shipped</th>
                      <th width="90">Size Received</th>
                      <th width="50">Missing</th>
                      <th>Unreceived</th>
                      </tr>
                  </tbody></table></td>
                </tr>
                <c:set var="tag" value="_"/>
                <tr>
                  <td colspan="4"><div style="width: 750px; height: 210px; overflow: scroll;">
                    <table class="list_table" width="700" border="0" cellpadding="0" cellspacing="0">
                      <tbody>
                      <c:set var="rowcount" value="0"></c:set>
                      <c:forEach var="woDTO" items="${list2}" varStatus="tally">
                      			<c:forEach items="${str}" var="str">
									<c:if test="${str==woDTO.orderNo}">
									<c:set var="qtyReceived" value="${woDTO.quantity}"></c:set>
									</c:if>
								</c:forEach>
								
								<c:forEach var="temp" items="${listTemp2}">
									<c:if test="${temp[0] == woDTO.orderNo}">
										<c:set var="qtyReceived" value="${temp[1]}"></c:set>
									</c:if>
								</c:forEach>
								
					 			 <c:forEach items="${str}" var="str">
									<c:if test="${str==woDTO.orderNo}">
									<c:set var="sizeReceived" value="${woDTO.size}"></c:set>
									</c:if>
								</c:forEach>
								
								<c:forEach var="temp" items="${listTemp2}">
									<c:if test="${temp[0] == woDTO.orderNo}">
										<c:set var="sizeReceived" value="${temp[2]}"></c:set>
									</c:if>
								</c:forEach>
								
                      <c:if test="${qtyReceived!=null && qtyReceived != '' || sizeReceived!=null && sizeReceived!=''}">
								<c:set var="qtyR" value="${woDTO.quantity-qtyReceived}"></c:set>
							</c:if>
							<c:if test="${qtyReceived==null || qtyReceived == ''}">
								<c:set var="qtyR" value="error"></c:set>
							</c:if>
							<c:if test="${sizeReceived==null || sizeReceived == ''}">
								<c:set var="qtyS" value="error"></c:set>
							</c:if>
							<c:if test="${sizeReceived!=null && sizeReceived != ''}">
								<c:set var="qtyS" value="${woDTO.size-sizeReceived}"></c:set>
							</c:if>
                      <c:if test="${qtyReceived!=woDTO.quantity || sizeReceived!=woDTO.size}">
                      <input type="hidden" name="woBatchId" id="woBatchId" value="${woDTO.woBatchId }"/>
                      <input type="hidden" name="orderNo" id="woBatchId" value="${woDTO.orderNo }"/>
	                      <tr>
	                        <td width="92">${woDTO.batchNo }</td>
	                        <td width="86">${woDTO.orderNo }</td>
	                        <td width="56">${woDTO.soItemNo }</td>
	                        <td width="72">${woDTO.quantity }</td>
	                        <td width="82">
	                        	${qtyReceived }
							</td>
							<td width="72">${woDTO.size }</td>
                     	    <td width="81">
                     	    	${sizeReceived }
							</td>
							
			                <td width="49"><input name="radio_${tally.index }" id="radio_${tally.index }" value="${qtyR }_${qtyS }" type="radio"/></td>
			                <td><input name="radio_${tally.index }" id="radio_${tally.index }" value="Unreceived" checked="checked" type="radio"/></td>
	                        </tr>
	                    </c:if>    
	                        <c:set var="qtyReceived" value=""></c:set>
	                    <c:set var="sizeReceived" value=""></c:set>
	                    <c:set var="qtyR" value=""></c:set>
	                    <c:set var="qtyS" value=""></c:set>
	                 </c:forEach>   
	             	
                    </tbody></table>
                  </div></td>
                </tr>
                <tr>
                  <td colspan="4" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                  
                  </table></td>
                </tr>
                <tr>
                  <td colspan="4" height="40"><div align="center">
                  <c:if test="${receivewo1=='woReceive1'}">
                    <input id="sub1" name="Submit2" value="Confirm" class="style_botton" onclick="confirm();" type="button"/>
                  </c:if>
                  <c:if test="${receivewo2=='woReceive2'}">
                  	<input id="sub2" name="Submit2" value="Confirm" class="style_botton" onclick="confirm2();" type="button"/>
                  </c:if>
                  </div></td>
                </tr>
              </tbody></table></td>
          </tr>
        </tbody></table>          <br></td>
      </tr>
    </tbody></table></td>
  </tr>
</tbody></table>

</body></html>