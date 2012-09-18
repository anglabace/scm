<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>

<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
	type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/product/manager_task.js?v=2"></script>
<script src="${global_js_url}util/util.js" type="text/javascript"></script>
<script src="${global_js_url}/recordTime.js" type="text/javascript"></script>
<link href="stylesheet/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
 <script   language="JavaScript" type="text/javascript">  
  function   cc(e)  
  {  
      var   a   =   document.getElementsByName("pl2");  
      for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
  }  
  function   dd(e)  
  {  
      var   a   =   document.getElementsByName("as");  
      for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
  }  

  $(function() {            
  	$('#printFrame').dialog({
			autoOpen: false,
			height: 380,
			width: 800,
			modal: true,
			bgiframe: true,
			buttons: {
			}
		});
  });
  
	function openPrintFrame(orderNo,srcSoNo) {
		$('#printFrame').dialog("option", "open", function() {	
		var htmlStr = '<iframe src="warehousing_shipment!searchPurchaseOrderItemPrint.action?orderNo='+orderNo+'&srcSoNo='+srcSoNo+'" height="330" width="720" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		$('#printFrame').html(htmlStr);
		});
		$('#printFrame').dialog('open');
	}
 </script>
          <script type="text/javascript">
        var GB_ROOT_DIR = "./greybox/";
    </script>
 
    <script type="text/javascript" src="greybox/AJS.js"></script>
    <script type="text/javascript" src="greybox/AJS_fx.js"></script>
    <script type="text/javascript" src="greybox/gb_scripts.js"></script>
    <link href="greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
 
<style type="text/css"> 
<!--
.hidlayer {
	font-size: 12px;
	height: 370px;
	width: 666px;
	position: absolute;
	z-index: 9999;
	left: 20%;
	top: 20%;
	display:none;
}
-->
</style>
 
 
<script language="javascript" type="text/javascript" src="js/newwindow.js"></script>
</head>
 
<body class="content" style="background-image:none;">
<div id="frame12" style="display:none;" class="hidlayer1">
<iframe id="hidkuan" name="hidkuan" src="kuang.html" width="668" height="425" frameborder="0"  allowtransparency="true"></iframe>
</div>
<div class="hidlayer" id="hidlayer1">
<iframe id="hidfra" src="add_item_1.html" width="668" height="425" frameborder="0"></iframe>
</div>
 
 
<div class="hidlayer" id="hidlayer">
<iframe id="hidfra" src="paystatus.html" width="666" height="370" frameborder="0"></iframe>
</div>
 
 
 
<div class="scm">
<div class="title_content">
  <div class="title">Shipment Preparement (Document Printing)</div>
</div><div class="input_box">
  <table width="1010" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>
     
        <table width="993" border="0" cellspacing="0" cellpadding="0" class="list_table">
          <tr>
            <th width="300" height="22">Order Reference</th>
            <th width="300" height="22">Purchase Order No </th>
            <th width="100">Green Account</th>
            <th>Print Packing List</th>
            </tr>
            </table>
             <div style="width:1010px;height:400px;overflow:scroll;">
        <table width="993" border="0" cellspacing="0" cellpadding="0" class="list_table">
        <s:iterator value="page.result">
          <tr>
            <td width="300">${srcSoNo }</td>
            <td width="300">${orderNo }</td>
            <td width="100">
            	<c:if test="${greenAccFlag=='Y'||greenAccFlag=='y'}">
						<div align="center"><img src="images/green_icon.gif" width="27" height="23" /></div>
				</c:if>
			</td>
            <td><div align="center"><a href="javascript:void(0)" onclick="openPrintFrame('${orderNo}','${srcSoNo}');"><img src="images/printer.gif" width="16" height="14" /></a></div></td>
            </tr>
        </s:iterator>
        
        </table>
        </div>
     </td>
    </tr>
    <tr>
      <td><div class="grayr">
						<jsp:include page="/common/db_pager.jsp">
							<jsp:param value="${ctx}/warehousing_shipment!searchPurchaseOrderPrint.action"
								name="moduleURL" />
						</jsp:include>
					</div></td>
    </tr>
  </table>
</div>
  
</div>
 <div id="printFrame" title="Down"
			style="visible: hidden" />
 
 
</body>
</html>
