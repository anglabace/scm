<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />


<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript" src="${global_js_url}jquery/ui/ui.datepicker.js"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}tools.js" type="text/javascript"></script>    <script>
  
function openc(str1,str2)
{
	  if (document.getElementById(str1).style.display=="none")
	  {
	    document.getElementById(str2).src="${ctx}/images/ad.gif";
	    document.getElementById(str1).style.display="block";
	  }
	  else
	  {
		  document.getElementById(str2).src="${ctx}/images/ar.gif";
	    document.getElementById(str1).style.display="none";
	  }  
}
function onclick1()
{
    document.cookie="work_o_Antibody";
    window.location.href="work_order_trans.html";
}


  $(function() {
	$('#catalog_list_dlg').dialog({
	autoOpen: false,
	height: 480,
	width: 700,
	modal: true,
	bgiframe: true,
	buttons: {
	}
    });
   });

function submitAction(){
 
  var status='${apInvoice.status}';
	if(status != 'New'){
	   alert("Only the inovice status is 'New' you can change the invoiceLine information!"); 
	   return;  
	}

  if(!checkFormNotNull('invoice_editLine')){ //进行数据检验，判断是否为空
     return;
  }
   if( !checkFormByReq('invoice_editLine')){ //进行数据检验，判断数据格式是否正确
   return;
   }
   var orderNo = ${apInvoice.orderNo};
   var itemNo = ${apInvoiceLine.itemNo};
   if(itemNo == 0){
   $("#invoice_editLine").attr("action","ap_invoice!afteredit_invoiceline.action");
        $("#invoice_editLine").submit();
        }else{
    var max = 0;
    var qty = parseFloat($("#qty").val());
    var size = parseFloat($("#size").val());
    max = qty>size ? qty : size;
   $.ajax({
      url : 'ap_invoice_line!checkQtyOrSize.action',
      type:'post',
      data:{orderNo:orderNo,itemNo:itemNo},
      dataType:'json',
      success:function(res){
       if(max > res.max){
        alert("The  quantity(size) is bigger than the item  quantity(size) ");
        return;
       }else{
        $("#invoice_editLine").attr("action","ap_invoice!afteredit_invoiceline.action");
        $("#invoice_editLine").submit();
      }
      },
      error:function(){
        alert("query error");
      }
    }); 
    }
}



function cancelAction()
{
	 $("#invoice_editLine").attr("action","ap_invoice!cancel.action");
	 $("#invoice_editLine").submit();
}

//检查qty/size
function checkQtyOrSzie(){
   
}

$(document).ready(function(){  
computeAmount();
}); 

function computeAmount(){
   var p = $("#price").val();
   var q = $("#qty").val();
   var flag = isFloat(p);
   var f2 = isInt(q);
   var price = 0; 
   var qty = 0;
   if(flag && f2 ){
     qty = parseFloat(q);
     price = parseFloat(p);
     $("#amount").val(qty * price);
     $("#save1").attr("disabled","");
   }else{
      $("#save1").attr("disabled","disabled");
     if(!flag){
       alert("Price is invalid");
        $("#price").val('');
     }else{
       alert("Quantity is invalid");
        $("#qty").val('');
     }
   }
}


function catalogNoSearch () {
		var openurl = "genscript!catalogNoSearch.action";
        $('#catalog_list_dlg').dialog("option", "open", function() {	
     		 var htmlStr = '<iframe src="'+openurl+'" height="520" width="740" scrolling="no" style="border:0px" frameborder="0"></iframe>';
	         $('#catalog_list_dlg').html(htmlStr);
		});
		$('#catalog_list_dlg').dialog('open');
	}


function setCatalogInfo(catalogNo,name,uom){
    $("#catalogNo").val(catalogNo);
    $("#catalogName").val(name);
    $("#uom").val(uom);
}

</script> 
   
<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset {
	margin: 4px;
}

.disp{
   display:none;
   margin-left:40px;
}
-->
</style>

</head>

<body class="scm">

<div class="scm">
<div class="title_content" >
  <div class="title" style="bold 16px/33px Arial,Helvetica,sans-serif">Invoice Line Information</div>
</div>
<div class="input_box">
		  <div class="content_box">

		    <form enctype="multipart/form-data" class="niceform" id="invoice_editLine" action=''    >
		    <input name='apInvoiceLine.itemNo' type="hidden" value="${apInvoiceLine.itemNo}" />
		    <input name='apInvoiceLine.invoiceId'  type="hidden"   value="${apInvoice.invoiceId}" />
		    <input name="PaidAmount"  value="<%=request.getParameter("PaidAmount") %>" type="hidden" />  
		    <input name='dispatch'  type="hidden"   value="<%=request.getParameter("dispatch")%>" />
		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td><table  border="0" cellpadding="0" cellspacing="0" class="Customer_table">
		            <tr>
		              <th width="169">Invoice No</th>
		              <td width="474"><input name='' type="text" value="${apInvoice.invoiceNo}"  class="NFText" size="35"  readonly /></td>
		              <th>Status</th>
		              <td><select  name="apInvoiceLine.status" style="width:207px;">                        
                        <option value="${apInvoice.status }">${apInvoice.status }</option>
	                  </select>
	                  </td>
	                </tr>
		            <tr>
		              <th>Line No</th>
		              <td><input name="apInvoiceLine.lineNo" value="${apInvoiceLine.lineNo}"     type="text"  class="NFText"  size="35" readonly /></td>
		              <th>Catalog No</th>
		              <td><input name="apInvoiceLine.catalogNo" type="text" readonly="readonly"  id="catalogNo" value="${apInvoiceLine.catalogNo}"  class="NFText"  size="35" desc="Catalog no" required="true"  />
		                 <img src="${ctx}/images/search.gif" width="16" height="16" onclick="catalogNoSearch()" align="absmiddle" style="cursor:hand" />
		              </td>
	                </tr>
		            <tr>
		              <th valign="top">Name</th>
		              <td colspan="3"><input name="apInvoiceLine.name" id="catalogName" readonly="readonly" value="${apInvoiceLine.name}"  type="text" desc="name" required="true" class="NFText"  size="70"/></td>
	                </tr>
		            <tr>
		              <th>Quantity</th>
		              <td><input name="apInvoiceLine.qty" type="text" id="qty" value="${apInvoiceLine.qty}"  class="NFText"  size="35"  onblur="computeAmount()" checkType="integer" desc=" Quantity " required="true"    /></td>
		              <th>UOM</th>
		              <td><select name="apInvoiceLine.qtyUom" id="uom" style="width:207px;">
		                  <option value="${apInvoiceLine.qtyUom }"  >${apInvoiceLine.qtyUom }</option>
		                  </select></td>
	                </tr>
		            <tr>
		              <th>Size</th>
		              <td><input name="apInvoiceLine.size" readonly="readonly" type="text" id="size" value="${apInvoiceLine.size}"   class="NFText"  size="35"   checkType='float' desc='Size' required='true'    /></td>
		              <th>&nbsp;</th>
		              <td>&nbsp;</td>
	                </tr>
	              </table></td>
	            </tr>
		        <tr>
		          <td>
                
              <div class="invoice_title" ><a href="javascript:void(0);" onclick="openc('Contact_Info','image1')"><img src="${ctx}/images/ad.gif" width="11" height="11" id="image1"/></a>&nbsp;Amounts</div>
<div id="Contact_Info" class="disp" style="display:block;">
<table  border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th width="130">Price</th>
    <td><input name="apInvoiceLine.unitPrice" id="price" type="text" class="NFText2"  onblur="computeAmount()" value="${apInvoiceLine.unitPrice}"   size="35" checkType='float' desc='Price' required='true' />${apInvoice.symbol }</td>
    <th width="320">Tax</th>
    <td><input name="apInvoiceLine.tax" type="text" class="NFText2" id="tax"  value="${apInvoiceLine.tax}"   size="35"   checkType='float' desc='Tax' required='true'  />${apInvoice.symbol }</td>
  </tr>
 
  <tr>
    <th>Amount</th>
    <td><input name="apInvoiceLine.amount" type="text" id="amount" class="NFText2" readonly="readonly"  value="${apInvoiceLine.amount}"  size="35"   />${apInvoice.symbol }</td>
    <th>&nbsp;</th>
    <td>&nbsp;</td>
    </tr>
</table>
</div>


  
                
                
                  
                  </td>
	            </tr>
	          </table>
		    </form>
		</div>
  </div>
<script type="text/javascript">
//initTabs('dhtmlgoodies_tabView1',Array('Invoice Line'),0,998,320);

</script>
<div class="button_box">
      <input type="button" name="Submit123"  value="Save" class="search_input" id="save1" onclick="submitAction()"   />
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="cancelAction()" id="cancel1"/>
</div>
</div>	

<div id="catalog_list_dlg" title=" Catalog List "></div>
</body>
</html>
