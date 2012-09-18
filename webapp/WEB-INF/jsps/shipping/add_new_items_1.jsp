<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<%@ include file="/common/taglib.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Order Management</title>
<link href="stylesheet/scm.css" rel="stylesheet" type="text/css"/>
<link href="stylesheet/table.css" rel="stylesheet" type="text/css"/>
<script language="javascript" type="text/javascript" src="js/ajax.js"></script>
<script language="javascript" type="text/javascript" src="js/tab-view.js"></script>
<link href="stylesheet/tab-view.css" rel="stylesheet" type="text/css"/>
<script language="javascript" type="text/javascript" src="js/TabbedPanels.js"></script>
<link href="stylesheet/SpryTabbedPanels.css" rel="stylesheet" type="text/css"/>
 <script language="JavaScript" type="text/javascript">  
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
 </script>
          <script type="text/javascript">
        var GB_ROOT_DIR = "./greybox/";
    </script>

    <script type="text/javascript" src="js/AJS.js"></script>
    <script type="text/javascript" src="js/AJS_fx.js"></script>
    <script type="text/javascript" src="js/gb_scripts.js"></script>
    <link href="stylesheet/gb_styles.css" rel="stylesheet" type="text/css" media="all"/>

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

<script type="text/javascript">
function sub(){
	var tags1=document.getElementsByTagName("input");
	var ids="";
	for(var j=0;j<tags1.length;j++){
		if(tags1[j].name=="ids"&tags1[j].checked){
			//ids[j]=tags1[j].value;
			ids+=tags1[j].value+",";
		}
	}
	if(ids.length<1){
		alert("Please choose one");
		return false;
	}
	location.href="shipping!doAddNewItems.action?ids="+ids;
}

function listAll(){
	var tags=document.getElementsByTagName("input");
	for(var i=0;i<tags.length;i++){
		if(tags[i].name=="ids"){
			tags[i].checked=tags['isAll'].checked;
		}
	}
}

function openwin1()
{
   document.getElementById("hidlayer").style.display="block";
}

function openwin2()
{
   document.getElementById("hidlayer1").style.display="block";
}



function closelay()
{
	document.getElementById("hidlayer").style.display="none";
}
function closelay2()
{
	document.getElementById("hidlayer1").style.display="none";
}

function closelay3()
{
	document.getElementById("hidlayer3").style.display="none";
}


</script>

<link type="text/css" href="stylesheet/ui.base.css" rel="stylesheet"/>
<link type="text/css" href="stylesheet/ui.theme.css" rel="stylesheet"/>




<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	$('.pickdate').each(function(){
		$(this).datepicker(
			{
				dateFormat: 'yy-mm-dd',
				changeMonth: true,
				changeYear: true,
				yearRange: '-100:+0'
			});
	})
});
</script>


<script language="javascript" type="text/javascript" src="add_new_items_1_files/newwindow.js"></script>

</head><body class="content" style="background-image: none;">

<div id="frame12" style="display: none;" class="hidlayer1">
<iframe id="hidkuan" name="hidkuan" src="add_new_items_1_files/kuang.htm" allowtransparency="true" width="668" frameborder="0" height="425"></iframe>
</div>



<div class="hidlayer" id="hidlayer">
<iframe id="hidfra" src="add_new_items_1_files/paystatus.htm" width="666" frameborder="0" height="370"></iframe>
</div>


<div class="scm">
<div class="title_content">
  <div class="title">Order Information</div>
</div><div class="input_box">
  <table border="0" cellpadding="0" cellspacing="0" width="998">
    <tbody><tr>
      <td><div style="margin-right: 17px;">
        <table class="list_table" border="0" cellpadding="0" cellspacing="0" width="981">
          <tbody><tr>
            <th width="46"><div align="left">
              <input onclick="listAll()" name="isAll" type="checkbox"/>
              <a href="http://www.genscriptcorp.com/demo/delete_invoice.html" title="Delete Invoice Line" rel="gb_page_center[600,  180]"><img src="add_new_items_1_files/file_delete.gif" alt="Delete" border="0" width="16" height="16"></a></div></th>
           
            <th width="60">Item No</th>
            <th width="124">Catalog No</th>
            <th width="172">Name</th>
            <th width="104">Status</th>
            <th width="112">Qty</th>
            <th width="124">UOM</th>
            <th width="66">Size</th>
            <th>Price</th>
            </tr>
        </tbody></table>
      </div></td>
    </tr>
    <tr>
      <td><div class="list_box" style="height: 100px;">
        <table class="list_table2" border="0" cellpadding="0" cellspacing="0" width="981">
          <tbody>
          <c:forEach var="orderItems" items="${list}">
          <tr>
            <td width="46"><input name="ids" id="ids" value="${orderItems.itemNo }" type="checkbox"></td>
            <td width="60">${orderItems.itemNo }</td>
            <td width="124">${orderItems.catalogNo }</td>
            <td width="172">${orderItems.name }</td>
            <td width="104">${orderItems.status }</td>
            <td width="112">${orderItems.quantity }</td>
            <td width="124">${orderItems.qtyUom }</td>
            <td width="66">${orderItems.size }&nbsp;${orderItems.sizeUom }</td>
            <td align="right">$50.00</td>
          </tr>
          </c:forEach>
          
        </tbody></table>
      </div></td>
    </tr>
    <tr>
      <td><div class="grayr">
				<jsp:include page="/common/db_pager.jsp">
					<jsp:param value="${ctx}/shipping!searchItems.action" name="moduleURL" />
				</jsp:include>
			</div>
	  </td>
    </tr>
    <!--<tr>

    <td align="center"><input type="submit" name="Submit5" value="Create Purchase Order" class="search_input2" onclick="window.location.href='purchase_order_info.html'"/>

    </td>

    </tr>-->
  </tbody></table>
</div>
  <div style="width: 998px; height: 320px;" id="dhtmlgoodies_tabView1">
    <div class="dhtmlgoodies_tabPane"><div style="left: 0px;" class="tabActive" id="tabTabdhtmlgoodies_tabView1_0"><span style="position: relative; padding-left: 4px; padding-right: 4px;" id="Item Detail">Item Detail</span><img src="add_new_items_1_files/tab_active2.gif"></div><div style="left: 0px;" class="tabInactive" id="tabTabdhtmlgoodies_tabView1_1"><span style="position: relative; padding-left: 3px; padding-right: 3px;" id="More Detail">More Detail</span><img src="add_new_items_1_files/tab_inactive2.gif"></div><div style="left: 2px;" class="tabInactive" id="tabTabdhtmlgoodies_tabView1_2"><span style="position: relative; padding-left: 3px; padding-right: 3px;" id="Sales Information">Sales Information</span><img src="add_new_items_1_files/tab_inactive2.gif"></div><div style="left: 4px;" class="tabInactive" id="tabTabdhtmlgoodies_tabView1_3"><span style="position: relative; padding-left: 3px; padding-right: 3px;" id="Instructions/Notes">Instructions/Notes</span><img src="add_new_items_1_files/tab_inactive2.gif"></div></div><div id="tabViewdhtmlgoodies_tabView1_0" style="height: 320px; display: block;" class="dhtmlgoodies_aTab">
      <div class="blue_price"> Item No:<span class="item_no"># 1</span></div>
      <table class="General_table" style="margin-left: 15px;" border="0" cellpadding="0" cellspacing="0">
        <tbody><tr>
          <th width="111"> Item Status </th>
          <td width="208"><input name="textfield42232" class="NFText" value="Customer Confirmation" size="34" type="text"></td>
          <td width="206">&nbsp;</td>
          <th rowspan="2" valign="top" width="104">Description </th>
          <td colspan="2" rowspan="2" valign="top" width="121"><textarea name="textarea" class="content_textarea2" style="width: 250px;"></textarea></td>
        </tr>
        <tr>
          <th>Other Info </th>
          <td><input name="textfield424" class="NFText" size="34" type="text"></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <th>Catalog</th>
          <td><input name="textfield4223" class="NFText" value="201002: 2010 Genscript Catalog 02" size="34" type="text"></td>
          <td><a href="http://www.genscriptcorp.com/demo/catalog_general.html" title="Catalog Info" rel="gb_page_center[560, 300]">
            <input name="Submit63" class="style_botton" value="More Info" type="submit"/>
          </a></td>
          <th rowspan="2" valign="top">Full Description</th>
          <td colspan="2" rowspan="2" valign="top"><textarea name="textarea4" class="content_textarea2" style="width: 250px;"></textarea></td>
        </tr>
        <tr>
          <th>Type</th>
          <td><select name="select14" style="width: 200px;">
            <option selected="selected">Product - Gene</option>
            <option>Product - Peptide</option>
            <option>Service - Gene</option>
            <option>Service - Peptide</option>
          </select></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <th>Tax Status </th>
          <td><select name="select14" style="width: 200px;">
            <option selected="selected">Taxable Item</option>
          </select></td>
          <td>&nbsp;</td>
          <th rowspan="2" valign="top">Comment </th>
          <td colspan="2" rowspan="2" valign="top"><textarea name="textarea3" class="content_textarea2" style="width: 250px;"></textarea></td>
        </tr>
        <tr>
          <th>Pick Location</th>
          <td><select name="select14" style="width: 200px;">
            <option selected="selected">USA Sporting Goods,Inc,Aisle</option>
          </select></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <th>Schedule Shipment</th>
          <td colspan="2"><input id="dp1288231432381" name="textfield14223222222" class="pickdate NFText hasDatepicker" style="width: 125px;" value="     -   -  " size="20" type="text"></td>
          <th>&nbsp;</th>
          <td width="139">&nbsp;</td>
        </tr>
        <tr>
          <th valign="top">Selling Note</th>
          <td colspan="5"><textarea name="textarea" class="content_textarea2" style="color: rgb(153, 0, 0); width: 325px;" disabled="disabled">3 OR MORE DISCOUNT PRICE $121 ANY OTHER FAMILY MEMBERS?</textarea></td>
        </tr>
        <tr>
          <td colspan="6"><div class="botton_box"><a href="http://www.genscriptcorp.com/demo/codon_opt.html" title="All Line Items Detail in Order #1372" rel="gb_page_center[750,  400]">
            <input name="Submit142" class="style_botton2" value="All Items Detail" type="button"/>
          </a></div></td>
        </tr>
      </tbody></table>
    </div>
    <div id="tabViewdhtmlgoodies_tabView1_1" style="height: 320px; display: none;" class="dhtmlgoodies_aTab"><iframe id="mored" src="add_new_items_1_files/Copy%20of%20more_info3.htm" width="100%" frameborder="0" height="338"></iframe></div>
    <div id="tabViewdhtmlgoodies_tabView1_2" style="height: 320px; display: none;" class="dhtmlgoodies_aTab">
      <table class="General_table" style="margin: 0px auto;" border="0" cellpadding="0" cellspacing="0">
        <tbody><tr>
          <th width="158"> Status </th>
          <td colspan="3"><input name="textfield923" class="NFText" size="30" value="New" type="text"></td>
        </tr>
        <tr>
          <th>Source</th>
          <td colspan="3"><select name="select4" style="width: 182px;">
            <option selected="selected">HG-00-10 BETTER HOMES AND GAROEN OCT</option>
          </select></td>
        </tr>
        <tr>
          <th>Order No Referred </th>
          <td width="248"><input name="textfield72" class="NFText" size="30" type="text"></td>
          <th width="190">Quotation No Converted From</th>
          <td width="249"><input name="textfield722" class="NFText" value="#157" size="30" type="text"></td>
        </tr>
        <tr>
          <th> Priority </th>
          <td colspan="3"><select name="select" style="width: 182px;">
            <option>Urgent</option>
            <option>High</option>
            <option selected="selected">Medium</option>
            <option>Low</option>
            <option>Minor</option>
          </select></td>
        </tr>
        <tr>
          <th>Sales Contact</th>
          <td><input name="textfield9" class="NFText" size="30" value="Joseph Baird" type="text"></td>
          <th>Alternative Sales Contact</th>
          <td><select name="select17" style="width: 182px;">
            <option selected="selected">zhengyu</option>
            <option>limei</option>
            <option>zhouyang</option>
            <option>Other</option>
          </select></td>
        </tr>
        <tr>
          <th>Technical Support</th>
          <td><input name="textfield2" class="NFText" size="30" type="text"></td>
          <th>Alternative  Technical Support</th>
          <td><select name="select5" style="width: 182px;">
            <option selected="selected">zhengyu</option>
            <option>limei</option>
            <option>zhouyang</option>
            <option>Other</option>
          </select></td>
        </tr>
        <tr>
          <th>Project Support</th>
          <td><input name="textfield3" class="NFText" size="30" type="text"></td>
          <th>Alternative  Project Support</th>
          <td><select name="select13" style="width: 182px;">
            <option selected="selected">zhengyu</option>
            <option>limei</option>
            <option>zhouyang</option>
            <option>Other</option>
          </select></td>
        </tr>
        <tr>
          <th valign="top">Order Type</th>
          <td colspan="3"><select name="select15" style="width: 182px;">
            <option selected="selected">Email</option>
            <option>Phone</option>
            <option>Web</option>
          </select></td>
        </tr>
        <tr>
          <th valign="top">Order Memo </th>
          <td colspan="3"><select name="select7" style="width: 182px;" id="con_select2" onchange="document.getElementById('con_text2').value=document.getElementById('con_select2').options[document.getElementById('con_select2').selectedIndex].value">
            <option value="&nbsp;" selected="selected">&nbsp;</option>
            <option value="This is Order Memo1,You can add it when you click the button of 'Use Template Memo'.">Order Memo1</option>
            <option value="This is Order Memo2,You can add it when you click the button of 'Use Template Memo'.">Order Memo2</option>
            <option value="This is Order Memo3,You can add it when you click the button of 'Use Template Memo'.">Order Memo3</option>
            <option value="This is Order Memo4,You can add it when you click the button of 'Use Template Memo'.">Order Memo4</option>
            <option value="&nbsp;">Other</option>
          </select>
            <br/>
            <textarea name="textarea6" cols="" rows="" class="content_textarea" style="margin-top: 5px; height: 50px;" id="con_text2"></textarea></td>
        </tr>
      </tbody></table>
    </div>
    <div id="tabViewdhtmlgoodies_tabView1_3" style="height: 320px; display: none;" class="dhtmlgoodies_aTab">
      <table border="0" cellpadding="0" cellspacing="0" width="600">
        <tbody><tr>
          <td valign="top">View Instruction/Notes with Type:</td>
          <td><select name="select18" style="width: 250px;" id="selecttb" onchange="a(this)">
            <option value="1" selected="selected">All Instruction/Notes</option>
            <option value="2">Sales Notes</option>
            <option value="3">Customer Confirmation Emails</option>
            <option value="6">Vendor Confirmation Emails</option>
            <option value="4">Shipment Instruction</option>
            <option value="5">Production Instruction</option>
            <option value="7">Accounting Instruction</option>
          </select></td>
          <td><input name="Submit42" class="style_botton" value="Search" type="button"></td>
          <td><a href="http://www.genscriptcorp.com/demo/new_note2.html" title="Add Instruction/Note" rel="gb_page_center[580, 260]">
            <input name="Submit4" value="Add" class="style_botton" type="button"/>
          </a></td>
        </tr>
      </tbody></table>
      <br/>
      <div id="tb1" style="display: block;">
        <table class="list_table" border="0" cellpadding="0" cellspacing="0" width="960">
          <tbody><tr>
            <th width="75">Date</th>
            <th width="152">Type</th>
            <th width="66">Source</th>
            <th width="75">Status</th>
            <th width="172">Subject</th>
            <th width="324">Description/Content</th>
            <th>Created By </th>
          </tr>
        </tbody></table>
        <div class="frame_box3" style="height: 250px;">
          <table class="list_table" border="0" cellpadding="0" cellspacing="0" width="960">
            <tbody><tr>
              <td width="75"><div align="center">2009-08-05</div></td>
              <td width="152">Order Notes</td>
              <td width="66">&nbsp;</td>
              <td width="75">&nbsp;</td>
              <td width="172">&nbsp;</td>
              <td width="324"><a href="http://www.genscriptcorp.com/demo/Order_Notes.html" title="Instruction/Notes for #12567" rel="gb_page_center[600,200]">Pack the Warm Up Suit last</a></td>
              <td>Jeffrey Ma</td>
            </tr>
            <tr>
              <td class="list_td2"><div align="center">2009-09-12</div></td>
              <td class="list_td2">Customer Confirmation Emails</td>
              <td class="list_td2">&nbsp;</td>
              <td class="list_td2">COMPLETE</td>
              <td class="list_td2">Delivery time</td>
              <td class="list_td2"><a href="http://www.genscriptcorp.com/demo/Customer_up.html" title="Instruction/Notes for #12567" rel="gb_page_center[640,330]">Delivery time can postpone for 10 days?</a></td>
              <td class="list_td2">Lihaicheng</td>
            </tr>
            <tr>
              <td><div align="center">2009-08-05</div></td>
              <td>Vendor Confirmation Emails</td>
              <td>&nbsp;</td>
              <td>INCOMPLETE</td>
              <td>Can the use of alternative products</td>
              <td><a href="http://www.genscriptcorp.com/demo/Vendor_up.html" title="Instruction/Notes for #12567" rel="gb_page_center[640,340]"><span class="list_td2"><img src="add_new_items_1_files/link.gif" width="12" height="12"></span> Can the use of alternative products</a></td>
              <td>Jeffrey Ma</td>
            </tr>
            <tr>
              <td class="list_td2"><div align="center">2009-09-12</div></td>
              <td class="list_td2">Order Notes</td>
              <td class="list_td2">&nbsp;</td>
              <td class="list_td2">&nbsp;</td>
              <td class="list_td2">&nbsp;</td>
              <td class="list_td2"><img src="add_new_items_1_files/link.gif" width="12" height="12"/> In the September 15 pre-shipping</td>
              <td class="list_td2">Lihaicheng</td>
            </tr>
            <tr>
              <td><div align="center">2009-08-05</div></td>
              <td>Order Notes</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>Please leave package on back</td>
              <td>Jeffrey Ma</td>
            </tr>
            <tr>
              <td class="list_td2"><div align="center">2009-09-12</div></td>
              <td class="list_td2">Shipment Instruction</td>
              <td class="list_td2">Customer</td>
              <td class="list_td2">&nbsp;</td>
              <td class="list_td2">&nbsp;</td>
              <td class="list_td2"><a href="http://www.genscriptcorp.com/demo/ship_instruction.html" title="Instruction/Notes for #12567" rel="gb_page_center[600,200]">Proch if customer is not home</a></td>
              <td class="list_td2">Lihaicheng</td>
            </tr>
            <tr>
              <td><div align="center">2009-08-05</div></td>
              <td>Shipment Instruction</td>
              <td>Organization</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>Proch if customer is not home</td>
              <td>Jeffrey Ma</td>
            </tr>
            <tr>
              <td class="list_td2"><div align="center">2009-09-12</div></td>
              <td class="list_td2">Customer Confirmation Emails</td>
              <td class="list_td2">&nbsp;</td>
              <td class="list_td2">COMPLETE</td>
              <td class="list_td2">Modify the number of products</td>
              <td class="list_td2">Modify the number of products</td>
              <td class="list_td2">Jeffrey Ma</td>
            </tr>
            <tr>
              <td><div align="center">2009-08-05</div></td>
              <td>Shipment Instruction</td>
              <td>Division</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>Quot #1242 Prod Inc .doc</td>
              <td>Jeffrey Ma</td>
            </tr>
            <tr>
              <td class="list_td2"><div align="center">2009-09-12</div></td>
              <td class="list_td2">Follow-up Date</td>
              <td class="list_td2">&nbsp;</td>
              <td class="list_td2">&nbsp;</td>
              <td class="list_td2">&nbsp;</td>
              <td class="list_td2"><a href="http://www.genscriptcorp.com/demo/ful_instruction.html" title="Instruction/Notes for #12567" rel="gb_page_center[600,200]">UB-2009-10-12 04:24:04 PM Added Item 3-5,Released Order for ...</a></td>
              <td class="list_td2">Jeffrey Ma</td>
            </tr>
            <tr>
              <td><div align="center">2009-08-05</div></td>
              <td>Shipment Instruction</td>
              <td>Department</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>UB-2009-10-12 04:24:04 PM 
                Added Item 3-5,Released Order for...</td>
              <td>Jeffrey Ma</td>
            </tr>
            <tr>
              <td class="list_td2"><div align="center">2009-09-12</div></td>
              <td class="list_td2">Order Notes</td>
              <td class="list_td2">&nbsp;</td>
              <td class="list_td2">&nbsp;</td>
              <td class="list_td2">&nbsp;</td>
              <td class="list_td2">Please leave package on back</td>
              <td class="list_td2">Jeffrey Ma</td>
            </tr>
          </tbody></table>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="button_box">
  <input name="Submit" value="Add" class="search_input" onclick="sub()" type="button"/>
  <input name="Submit124" value="Cancel" class="search_input" onclick="javascript:location.href='shipping!cancelDoNewItem.action'" type="submit"/>
</div>

<div id="ui-datepicker-div" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-helper-hidden-accessible"></div></body></html>