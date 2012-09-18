<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url }SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url }tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script>
var type = "Service";
</script>

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script>
function editSupplier(id, vendorName){
	$("#supplierEditTrigger").attr("vendorName", vendorName);
	$("#supplierEditTrigger").attr("supplierId", id);
	$("#supplierEditTrigger").trigger("click");
}
$(document).ready(function(){
	 $('#supplierTable tr:odd >td').addClass('list_td2');
	 $('#orderTable tr:odd >td').addClass('list_td2');
	 $("#delSupplierTrigger").click(function(){
		var tmpArr = [];
		$("#supplierTable :checkbox[checked]").each(function(i, n){
			tmpArr.push($(n).val());
		});
		var supplierIdStr = tmpArr.toString();
		if(supplierIdStr == ""){
			alert("Please select one item to continue your operation.");
			return;
		}
		if(!confirm("Are you sure to delete?")){
			return;
		}
		var psId = $("#psId").val();
		$.ajax({
			url: "${ctx}/serv/serv!delSupplierSession.action",
			type: "post",
			dataType: "text", 
			data: "id="+psId+"&type=product&supplierIdStr="+supplierIdStr+"&sessionServiceId=${sessionServiceId}",
			success : function(data){
				if(data == "success"){
					var del = supplierIdStr.split(",");
					for(var i=0;i<del.length;i++){
						$("#del_"+del[i]).remove();
					}
				}else{
					alert("System error! Please contact system administrator for help.");
				}
				
			},
			error: function(data){
				alert("System error! Please contact system administrator for help.");
			}
		});
	});

	$("#vendorListCheckAll").click(function(){
		if($(this).attr("checked") == true){
			$("[name='supplierId']").attr("checked", true);
		}else{
			$("[name='supplierId']").attr("checked", false);
		}
	});

		var psId = $("#psId").val();
		//ship area add trigger
		$("#supplierAddTrigger").click(function(){
			parent.$('#supplierEditDialog').attr("title", "Add New Supplier");	
			parent.$('#supplierEditDialog').dialog("option","open", function(){
				var url = "${ctx}/serv/serv!showEditSuppler.action?id="+psId+"&type="+type+"&sessionServiceId=${sessionServiceId}";
				var htmlStr = '<iframe src="'+url+'" height="300" width="570" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				parent.$('#supplierEditDialog').html(htmlStr);
			});	
			parent.$('#supplierEditDialog').dialog("option", "title", "Add New Supplier");	
			parent.$('#supplierEditDialog').dialog("open");	
		});
		//ship area edit trigger
		$("#supplierEditTrigger").click(function(){
			var vendorName = $(this).attr("vendorName");
			var supplierId = $(this).attr("supplierId");
			parent.$('#supplierEditDialog').attr("title", "Supplier-"+vendorName);
			parent.$('#supplierEditDialog').dialog("option", "open",function(){
				var supplierInfo = $("#"+supplierId+"_supplierInfo").val();
				var url = "${ctx}/serv/serv!showEditSuppler.action?id="+psId+"&type="+type+"&supplierId="+supplierId+"&supplierInfo="+supplierInfo+"&sessionServiceId=${sessionServiceId}";
				var htmlStr = '<iframe src="'+url+'" height="300" width="570" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				parent.$('#supplierEditDialog').html(htmlStr);
			});
			parent.$('#supplierEditDialog').dialog("option", "title", "Supplier-"+vendorName);
			parent.$('#supplierEditDialog').dialog("open");
		});
		if(type == "Service") {
			$("#saftyStock").attr("disabled",true);
			$("#minOrderQty").attr("disabled",true);
			$("#unitCost").attr("disabled",true);
		}
});
</script>
</head>

<body>
  	  <table width="970" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="70"><table width="700" border="0" align="center" cellpadding="0" cellspacing="0" class="General_table">
            <tr>
              <th width="300"><span class="important">*</span> Lead Times(Days) </th>
              <td><input id="leadTime" name="leadTime" type="text" class="NFText" size="20" value="${leadTime}" /></td>
              <th width="300">Next Expected Delivery Date </th>
              <td><input name="delivery" type="text" class="pickdate NFText" value="" size="20" disabled="disabled" /></td>
            </tr>
            <tr>
              <th> Re-Order When Stock Is Below</th>
              <td><input id="saftyStock" name="saftyStock" type="text" class="NFText" value="${saftyStock}" size="20"/></td>
              <th>Minimum Re-Order Quantity </th>
              <td><input id="minOrderQty" name="minOrderQty" type="text" class="NFText" value="${minOrderQty}" size="20" /></td>
            </tr>
            <tr>
              <th>Units On Order For In-House Stock </th>
              <td><input name="house" type="text" class="NFText" value="" size="20" disabled="disabled" /></td>
              <th> Current Unit Cost Basis </th>
              <td><input id="unitCost" name="unitCost" type="text" class="NFText2" value="${unitCost}" size="6" />
					<input type="button" value="Modify" class="style_botton" id="modifyTrigger" name="modifyTrigger" /></td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td><table width="940" border="0" cellpadding="0" cellspacing="0" class="list_table">
            <tr>
              <th width="80">Order No </th>
              <th width="80">Order Date </th>
              <th>Supplier</th>
              <th width="80">Qty.</th>
              <th width="80">PO No </th>
              <th width="80">PO Date</th>
              <th width="100">Expected Date</th>
            </tr>
          </table> 
            <div  class="frame_pr2">
              <table width="940" border="0" cellpadding="0" cellspacing="0" class="list_table" id="orderTable">
               <s:iterator value="purchaseOrderList">

				<tr>
                  <td width="80">
                  	<div align="center">
                  		<a href=""  target="mainFrame">${orderNo}</a>
                  	</div>
                  </td>
                  <td width="80"><div align="center"><s:date name="orderDate" format="yyyy-MM-dd" /></div></td>
                  <td>${vendorName}</td>
                  <td width="80">${catalogQty}</td>
                  <td width="80">${poNo}</td>
                  <td width="80"><div align="center"><s:date name="poDate" format="yyyy-MM-dd" /></div></td>
                  <td width="100"><div align="center"><s:date name="expectedDate" format="yyyy-MM-dd" /></div></td>
                </tr>
                               
               </s:iterator>
              </table>
           </div>
          </td>
        </tr>
        <tr>
          <td height="35" valign="bottom"  class="blue_price"><div align="center">Purchasing Level and Supplier List </div></td>
        </tr>
        <tr>
          <td>
          <table width="940" border="0" cellpadding="0" cellspacing="0" class="list_table">
            <tr>
              <th width="46">
              	<div align="left">
                	<input type="checkbox" id="vendorListCheckAll" />
                	<img id="delSupplierTrigger" src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" />
                </div>
                </th>
              <th width="140">Supplier</th>
              <th width="120">Catalog No </th>
              <th width="100">Name</th>
              <th width="50">Sell Qty</th>
              <th width="50">Buy Qty </th>
              <th width="80">Unit Price </th>
              <th width="80">Discount</th>
              <th width="80">Lead Time </th>
              <th>Comments</th>
            </tr>
          </table>
          <div class="frame_pr2">
              <table width="940" border="0" cellpadding="0" cellspacing="0" class="list_table2" id="supplierTable">
                <s:iterator value="verdonServiceList">
                <tr id="del_${id }">
                  <td width="46">&nbsp;                    
                	<input type="checkbox" name="supplierId" value="${id}" />                  
                  </td>
                  <td  width="140">&nbsp;
                  	<a href="javascript:void(0);" onclick="editSupplier('${id}', '${vendorName}');" title="${vendorName}">
                  		${vendorName}
                  	</a>
                  	<input type="hidden" value="${supplierInfo}" id="${id}_supplierInfo" />
                  </td>
                  <td width="120">&nbsp;${vendorCatalogNo}</td>
                  <td width="100">&nbsp;${name}</td>
                  <td width="50">&nbsp;${sellQuantity}</td>
                  <td width="50">&nbsp;${buyQuantity}</td>
                  <td width="80">
                  	<div align="right">&nbsp;
                  	${standardPrice}
                  	</div>
                  </td>
                  <td width="80">&nbsp;${discount}</td>
                  <td width="80">&nbsp;${leadTime}</td>
                  <td>&nbsp;${comment}</td>
                </tr>
              </s:iterator>
              </table>
            </div>
            </td>
        </tr>
        <tr>
          <td height="35" valign="bottom">
          	<div align="center">
          		<input id="psId" type="hidden" value="${id}" />
           		<input id="supplierEditTrigger" type="hidden" class="style_botton" value="" />
           		<input id="supplierAddTrigger" type="button" class="style_botton" value="New" />
           	</div>
           </td>
        </tr>
      </table>
</body>
</html>