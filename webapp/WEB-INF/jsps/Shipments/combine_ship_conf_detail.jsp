
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<link href="stylesheet/tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<link href="stylesheet/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset {
	margin: 4px;
}
-->
</style>

		
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>

		
		
		

<script type="text/javascript">
// save to database
function combineSave(){
	document.forms[0].action = "shipments!combineSave.action";
  	document.forms[0].submit();
}

function openSearchDlgss(orderNos) {
	
	
	}
</script>
</head>
<body class="content">
<div id="frame12" style="display:none;" class="hidlayer1">
<iframe id="hidkuan" name="hidkuan" src="" width="668" height="425" frameborder="0"  allowtransparency="true"></iframe>
</div>
<div class="scm">
<div class="title_content">
  <div class="title">Shipment - # ${so.shipmentNo} </div>
</div>
<div class="input_box">
		  <div class="content_box">
 
		    <form class="niceform">
		      <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                  <th width="160">Shipment No</th>
                  <td><input name="textfield" type="text" class="NFText" value="${so.shipmentNo }" size="25" readonly="readonly" /></td>
                  <th width="150">Status</th>
                  <td><input name="textfield9" type="text" disabled="disabled" class="NFText" value="${so.status }" size="25" readonly="readonly" /></td>
                </tr>
                <tr>
                  <th rowspan="2" valign="top">Ship to</th>
                  <td rowspan="2"><textarea name="textarea2" readonly="readonly" class="content_textarea2">${so.shipTo }</textarea></td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                      <tr>
                  <th>Priority</th>
                  <td>
                 <s:select name="priority" list="allShipPriority"
			listKey="value" listValue="value" value="so.priority" />
                  </td>
                  <th>Order Reference</th>
                  <td><input name="textfield4" type="text" class="NFText" value="${so.orderNo }" size="25" readonly="readonly" />
								                    <a href="javascript:void(0);" onclick="openSearchDlgss('${so.orderNo }');"><img
								src="images/search.gif" width="16" height="16" border="0px" />
								</a></td>
                </tr>
                  <tr>
                  <th valign="top">Shipping Amount</th>
                  <td><input name="textfield3" type="text" class="NFText" value="${so.shipAmt }" size="25" readonly="readonly" /></td>
                  <th>Currency</th>
                  <td>
                  <s:select name="currency" list="allCurrency"
			listKey="name" listValue="name" value="so.currency"/>
                  
                  </td>
                </tr>
                  <tr>
                  <th valign="top">Shipping Type</th>
                  <td><select name="select2" style="width:157px;" id="select">
                    <option>${so.shippingType }</option>
                  </select></td>
                  <th>Shipping Rule</th>
                  <td><select name="select4" style="width:157px;" id="select4">
                    <option>${so.shippingRule }</option>
                  </select></td>
                </tr>
                <tr>
                  <th valign="top">Description</th>
                  <td><textarea name="textarea3" readonly="readonly" class="content_textarea2">${so.description }</textarea></td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <th>Shipping Clerk</th>
                  <td>
                  <input name="textfield2" type="text" class="NFText" value="${so.shippingClerk }" size="25" readonly="readonly" />
                  </td>
                  <th>Created Date</th>
                  <td><input name="createionDatestr" type="text" class="NFText"
										value="${so.creationDateStr }" size="25" readonly="readonly"/>
                  	  <input name="createionDate" type ="hidden" value="${so.creationDate}"/></td>
                  </tr>
                  <tr>
			      <th>Modified By </th>
			      <td><input name="textfield2" type="text" class="NFText" value="${so.modifyName }" size="25" readonly="readonly" /></td>
			      <th>Modified Date</th>
			      <td><input name="modifyDatestr" type="text" class="NFText"
										value="${so.modifyDateStr }" size="25" readonly="readonly"/>
										<input name="modifyDate" type ="hidden" value="${so.modifyDate}"/>
										</td>
			    </tr>
            
              </table>
            </form>
		</div>
  </div>

<div id="dhtmlgoodies_tabView1">
	
    <div class="dhtmlgoodies_aTab">
      <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
        <tr>
          <th width="50">Line No</th>
		  <th width="50">Status</th>
          <th width="100">Order No</th>
          <th width="100">Item No</th>
          <th width="261">Proudct/Service</th>
          <th width="84">Order Qty</th>
          <th width="84">Ship Qty</th>
          <th width="104">Order Size</th>
          <th>Ship Size</th>
        </tr>
      </table>
	  <div class="frame_box" style="height:240px; ">
	 
      <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">	
      <c:set var="rowcount" value="1"></c:set>
      <c:forEach var="pageslDTO" items="${list}">
     	 <c:if test="${rowcount mod 2 == 0}">
		 	<c:set var="tdclass" value=" class='list_td2'"></c:set>
		 </c:if>	
		 <c:if test="${rowcount mod 2 == 1}">
		    <c:set var="tdclass" value=""></c:set>
		 </c:if>              
        <tr>
          <td width="50" align="center"><a href="shipments!getSlinedetail.action?lineId=${pageslDTO.lineId }" target="mainFrame" >${pageslDTO.lineId }</a></td>
          <td width="50" align="center">
											${pageslDTO.status }
										</td>
										<td width="100" align="center">
											${pageslDTO.order.orderNo }
										</td>
										<td width="100" align="center">
											${pageslDTO.itemNo }
										</td>
										<td width="261" align="center">
											${pageslDTO.itemName }
										</td>
										<td width="84" align="center">
											${pageslDTO.orderQty }
										</td>
										<td width="84" align="center">
											${pageslDTO.quantity }
										</td>
										<td width="104" align="center">
											${pageslDTO.orderSize }
										</td>
										<td align="center">
											${pageslDTO.size }
										</td>
        </tr>
        <c:set var="rowcount" value="${rowcount+1}"></c:set>
        </c:forEach>
      </table>
    </div>
    <div class="grayr">
				<jsp:include page="/common/db_pager.jsp">
					<jsp:param value="${ctx}/shipments!combineView.action" name="moduleURL" />
				</jsp:include>
			</div>
    </div>
  </div>
<div id="vendor_search_dlg3" title=" Order Reference "
				style="visible: hidden;">
<script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('Shipment Lines'),0,998,320);
</script>
<div class="button_box">
      <input type="button" name="Submit123"  value="Save" class="search_input" onclick="combineSave();"; />
      <input type="submit" name="Submit124" value="Cancel" class="search_input" onclick="javascript:history.go(-1);" />
</div>
</div>	
</div>
</body>
</html>
