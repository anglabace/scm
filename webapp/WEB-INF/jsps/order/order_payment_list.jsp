<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<s:include value="order_config.jsp"></s:include>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}quoteorder/order_quotation_payment.js" type="text/javascript"></script>

</head>

<body class="content" style="background-image:none;background-color:white;">
<input type="hidden" id="quorderStatus" value="${quorderStatus}"/>
    <div id="dhtmlgoodies_tabView2">
	    <!-------------------------------Credit Card-------------------------------------->
	      <div class="dhtmlgoodies_aTab">
		      <div id="ccDiv">
		         <form id="ccForm" name="ccForm">
		         	<input name="sessVoucherId" id="sessVoucherId" type="hidden" value=""  />
		          	<input name="rowNumber" id="rowNumber" type="hidden" value=""  />
		          	<input name="paymentType" type="hidden" id="paymentType" value="CC" />
		          	<input name="custNo" type="hidden" value="${custNo}" />
		          	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="General_table">
			          <tr>
			            <td colspan="4">Fields marked with an asterisk (<span class="red_font">*</span>) are required.</td>
			          </tr>
			          <tr>
			            <th><span class="red_font"></span>Cards in Account</th>
			            <td colspan="3">
				           <s:select cssStyle="width:114px;" name="" id="cardList" list="cardList" listKey="'id:'+id+',ccType:'+cardType+',ccExprDate:'+exprDate+',ccNo:'+cardNo+',ccCvc:'+cvc+',ccHolder:'+cardHolder" listValue="cardNo" headerKey="" headerValue="Please select"></s:select>   
			            </td>
			          </tr>
			          <tr>
			            <th width=""><span class="red_font">*</span>Card Type </th>
			            <td width="" colspan="3">
				            <s:select cssStyle="width:114px;" name="ccType" id="ccType" list="cardTypeList" listKey="value" listValue="text" ></s:select>
			            </td>
			          </tr>
			          <tr>
			          	<th>
			            	<span class="red_font">*</span>Expiration Date
			            </th>
			            <td width="" colspan="3">
			            	 <input type="hidden" name="ccExprDate" id="ccExprDate" />
		                     <s:select cssStyle="width: 52px;" id="ccExprDateMonth" name="ccExprDateMonth" list="#{'01':'01', '02':'02', '03':'03','04':'04', '05':'05', '06':'06', '07':'07', '08':'08', '09':'09', '10':'10', '11':'11', '12':'12'}"></s:select>
	                         <s:select list="tenYearList" cssStyle="width: 53px;" id="ccExprDateYear" name="ccExprDateYear"></s:select>
			            </td>
			          </tr>
			          <tr>
			            <th><span class="red_font">*</span>Card Number</th>
			            <td colspan="3"><input name="ccNo" id="ccNo" type="text" class="NFText" value="" size="16" onkeypress="cardNoValid();"/></td>
			          </tr>
			          <tr>
			          	<th>CVC</th>
			            <td width=""  colspan="3">
			            	<input name="ccCvc" id="ccCvc" type="password" class="NFText" value="" size="16" />
			            </td>
			          </tr>
			          <tr>
			            <th><span class="red_font">*</span>Name on the Card</th>
			            <td colspan="3">
			            	<input name="ccHolder" id="ccHolder" type="text" class="NFText" value="" size="16" />
			            	<input type="checkbox" name="saveCardFlag" value="1" /> Save To Customer Account
			            </td>
			          </tr>
			          <tr>
			            <td colspan="4">
						<s:if test="quorderStatus != 'CN' && quorderStatus != 'CC'">
			            <div id="ccButtonDiv">
							<div id="buttonDiv1" align="center" style="display:none;">
							    <input formId="ccForm" name="modPayment" id="modPayment" type="button" class="style_botton" value="Modify" />
							    <input formId="ccForm" name="cancelPayment" id="cancelPayment" type="button" class="style_botton" value="Cancel" />
							    <input formId="ccForm" name="delPayment" id="delPayment" type="button" class="style_botton" value="Delete" />
							    <input formId="ccForm" name="newPayment" id="newPayment" type="button" class="style_botton" value="New" />
							</div>
							<div id="buttonDiv2" align="center">
							<s:if test="orderNoShow != null && orderNoShow != '' && ccPayAllowFlag != 1">
							    <input formId="ccForm" name="" id="addPayment" type="button" class="style_botton" value="Add" />
							</s:if>
							<s:else>
								<input type="button" class="style_botton" value="Add" disabled="disabled" />
							</s:else>
							    <input name="" id="cancelAddPayment" type="button" class="style_botton" value="Cancel" />
							</div>
			            </div>
						</s:if>
			            </td>
			          </tr>
			        </table>
		         </form>
		        </div>
	      </div>
	      
	    <!----------------------PO---------------------->
	      <div class="dhtmlgoodies_aTab">	
		      <div id="poDiv">
		      	<form id="poForm" name="poForm" enctype="multipart/form-data" method="post">
		      		<input name="sessVoucherId" id="sessVoucherId" type="hidden" value=""  />
		            <input name="rowNumber" id="rowNumber" type="hidden" value=""  />
		            <input name="paymentType" id="paymentType" type="hidden" value="PO" />
		          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="pay_table">
		            <tr>
		              <th width="20%">Due On Date </th>
		              <td width="80%"><input name="poDueDate" id="poDueDate" class="ui-datepicker" type="text" class="NFText" style="width:60px;;border:1px solid #A7AAB9;padding: 0px 3px;margin:0px 2px" value="" size="20"  readonly="readonly" /></td>
		            </tr>
		            <tr>
		              <th>Terms</th>
		              <td width="80%">
			            <select name="poPaymentTerm" id="poPaymentTerm">
			            	<s:iterator value="termList">
			            		<option value="${termId}" dueDays="${dueDays}" netDays="${netDays}">${name}</option>
			            	</s:iterator>
			            </select>
		              	<input name="paymentTermInfoStr" id="paymentTermInfoStr" value="${paymentTermInfoStr}" type="hidden" />
		                <input name="poPaymentTerm_duedays" id="poPaymentTerm_duedays" type="text" class="NFText2" value="${paymentTermList[0].dueDays}" size="2" readonly="readonly" />
						Days,Net
						<input type="text" name="poPaymentTerm_netdays" id="poPaymentTerm_netdays" class="NFText" style="width:20px" value="${paymentTermList[0].netDays}" readonly="readonly" />
					  </td>
		            </tr>
		            <tr>
		              <th><span class="red_font">*</span>P.O. Number </th>
		              <td><input name="poNumber" id="poNumber" type="text" class="NFText" value="" size="20" /></td>
		            </tr>
		            <tr>
		              <th>P.O. Amount </th>
		              <td><input name="poAmount" id="poAmount" type="text" class="NFText" value="0" size="20" /></td>
		            </tr>
		            <tr>
	                  <th>PO Document</th>
	                  <td style="padding-left:4px;">
	                  <span id="fileSpan">
	                  	<input type="file" name="upload" id="poDocument" size="20" />
	                  </span>
	                  <span id="removeSpan" style="display:none;">
	                 	<a id="poHref" href="" target="_blank"></a>
	                 	<input type="hidden" id="poId" value="" />
	                 	<input type="hidden" id="docId" value="" />
	                 	<input type="hidden" id="docName" value="" />
	                  	<input type="button" value="remove" id="removePoDoc" />
	                  </span>
	                  <input type="hidden" id="poDocId" value="" />
	                  </td>
	                </tr>
		            <tr>
		              <td colspan="2"><br /><br />
			                <div id="poButtonDiv">
								<div align="center" id="buttonDiv1" style="display:none;">
								    <input formId="poForm" name="" id="modPayment" type="button" class="style_botton" value="Modify" />
								    <input formId="poForm" name="" id="cancelPayment" type="button" class="style_botton" value="Cancel" />
								    <input formId="poForm" name="" id="delPayment" type="button" class="style_botton" value="Delete" />
								    <input formId="poForm" name="" id="newPayment" type="button" class="style_botton" value="New" />
								</div>
								<s:if test="quorderStatus != 'CN' && quorderStatus != 'CC'">
								<div align="center" id="buttonDiv2">
								<s:if test="orderNoShow != null && orderNoShow != ''">
								    <input formId="poForm" name="" id="addPayment" type="button" class="style_botton" value="Add" />
								</s:if>
								<s:else>
									<input type="button" class="style_botton" value="Add" disabled="disabled"/>
								</s:else>
								    <input formId="poForm" name="" id="cancelAddPayment" type="button" class="style_botton" value="Cancel" />
								</div>
								</s:if>
			                </div>
		               </td>
		            </tr>
		    	  </table>
		    	</form>
		        <br />
		      </div>
	      </div>
	      <!--------------------------------- payment list  --------------------->
	      <div class="dhtmlgoodies_aTab">
	        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table" id="paymentListBody" style="display: none;">
	          <tr>
	            <td  style="border:0px;">
	            <table width="428" border="0" cellpadding="0" cellspacing="0" class="list_table">
	                <tr>
	                  <th width="65">Pay Type</th>
	                  <th width="290" >Description</th>
	                </tr>
	              </table>
	                <div style="width:445px; overflow-y:scroll; height:80px;">
	                  <table id="paymentListTable" width="428" border="0" cellspacing="0" cellpadding="0" class="list_table">
		                  <s:iterator value="paymentMap">
							  <tr sessVoucherId="${key}">
							  	<s:if test="value.paymentType == 'CC'">	
								    <td width="65">
								    	<div id="paymentType" align="center">${value.paymentType}-${value.ccType}</div>
								    </td>
								    <td width="290">
								    	<span sessVoucherId="${key}" id="descClick" style="color:#0066CC;cursor:pointer" mce_style="cursor: pointer">${value.ccNo} customer check </span>
								    </td>
								    <input name="payInfo" id="payInfo" type="hidden" value="voucherId:${value.voucherId},ccType:${value.ccType},ccExprDate:${value.ccExprDateStr},ccNo:${value.ccNo},ccHolder:${value.ccHolder},ccCvc:${value.ccCvc}" />
								</s:if>
								<s:elseif test="value.paymentType == 'PO'">
								    <td width="65" >
								    	<div id="paymentType" align="center">${value.paymentType}</div>
								    </td>
								    <td width="290" >
								    	<span sessVoucherId="${key}" id="descClick" style="color:#0066CC;cursor:pointer" mce_style="cursor: pointer">${value.poNumber} validated</span>
								    	<span id="${key}_payDocument">
									    	<input name="filePath" type="hidden" value="${value.document.filePath}" />
									    	<input name="docName" type="hidden" value="${value.document.docName}" />
									    	<input name="docId" type="hidden" value="${value.document.docId}" />
									    	<input name="poId" type="hidden" value="${value.voucherId}" />
								        </span>
								    </td>
								    <input name="payInfo" id="payInfo" type="hidden" value="voucherId:${value.voucherId},poDueDate:${value.poDueDateStr},poPaymentTerm:${value.poPaymentTerm},poNumber:${value.poNumber},poAmount:${value.poAmount}" />
								</s:elseif>
							  </tr>
						  	</s:iterator>
						</table>
						</div>
	             </td>
	          </tr>
	        </table>
	        <!-- 没有结果则显示以下  -->
	        <table width="100%" height="95%" border="0" cellpadding="0" cellspacing="0" id="noPlaymentListBody" style="display:none" class="pay_table">
	      		<tr>
	      			<td align="center" valign="middle">Select Payment Method</td>
	      		</tr>
	      	</table>
	      </div>
	    </div>
    <script>
    	initTabs('dhtmlgoodies_tabView2',Array('Credit Card','PO','All Payments'), 2, 450, 175);
        function cardNoValid(){
            if($("#ccNo").val().length>29) event.returnValue = false;
        }
    </script>
</body>
</html>