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
<s:include value="quote_config.jsp"></s:include>
<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script src="${global_js_url}quoteorder/order_quotation_packaging.js" type="text/javascript" language="javascript"></script>
<script>
$(document).ready(function(){
	if (parent.operation_method != "edit") {
		$('input').attr("disabled",true);
		$('button').attr("disabled",true);
		$('select').attr("disabled",true);
	}
	$("#delPackageImg").click(function(){  
		if (parent.operation_method != "edit") {
			return;
		}
		var delIds = [];
		$("[name='packageId']").each(function(i, n){
			if($(n).attr("checked")){
				delIds.push("&packageIdList="+$(n).val());
			}
		});
		var delIdStr = delIds.join("");
		if(delIdStr == ""){
			alert("Please select the item to continue your operation.");
			return;
		}
		$.ajax({
			url:orderquoteObj.url("packageDel")+delIdStr,
			type:"get",
			dataType:"text",
			success:function(data){
				if(data == "SUCCESS"){
					//alert("Delete successfully.");
					parent.document.getElementById("totalIframe").src = orderquoteObj.url("showTotal");
					window.location.reload();
				}else{
					if(data)
						alert(data);
					else
						alert("System error! Please contact system administrator for help.");
				}
			},
			error:function(data){
				if(data){
					alert(data);
				}else{
					alert("Failed to remove the package.");
				}
			},
			async:false
		});
	});
});
</script>

</head>

<body class="content" style="background-image:none;">
<input id="quorderStatus" type="hidden" value="${quorderStatus}" />
       <table width="960" border="0" cellpadding="0" cellspacing="0" class="list_table">
         <tr>
           <th width="46">
           <div align="left">
           <s:if test="tempStatusStr == 'CO'">
				 <input type="checkbox" name="checkbox11" onclick="checkAll(this, 'packageId');" disabled="disabled"/>
           		<img src="images/file_delete.gif" width="16" height="16"  />
		</s:if>
		<s:else>
				 <input type="checkbox" name="checkbox11" onclick="checkAll(this, 'packageId');"/>
           		<a href="javascript:void(0);"><img src="images/file_delete.gif" width="16" height="16" id="delPackageImg" /></a>
		</s:else>
           
              
           	</div>
           </th>
           <th width="64">Pkg ID </th>
           <th width="40">Qty</th>
           <th width="45">Size</th>
           <th width="50">Package</th>
           <th width="80">Status</th>
           <th width="60">Invoice</th>
           <th width="70">Ship Via </th>
           <th width="120">Ship To </th>
           <th width="80">Warehouse</th>
           <th width="110">Tracking # </th>
           <th width="60">Weight</th>
           <th>Customer Charge </th>
         </tr>
       </table>
	   <div  class="frame_box3" style="height:280px">
         <table width="960" border="0" cellpadding="0" cellspacing="0" class="list_table" id="packageList">
	         <s:iterator value="packageMap" status="pm">
	           <tr>
	           <s:if test="tempStatusStr == 'CO'">
				 <td width="46">&nbsp;
	             	<input name="packageId" type="checkbox" value="${key}" disabled="disabled"/>
	             </td>
	             <td width="64">&nbsp;
	             	<span packageId="${key}"  style="color:#0066CC;cursor: pointer" mce_style="cursor: pointer" title="Package ${value.packageId} Information">
	             		<s:property value="#pm.index+1"/>
	             	</span>
	             </td>
				</s:if>
				<s:else>
				 <td width="46">&nbsp;
	             	<input name="packageId" type="checkbox" value="${key}" />
	             </td>
	             <td width="64">&nbsp;
	             	<span packageId="${key}" id="packageIdLink" style="color:#0066CC;cursor: pointer" mce_style="cursor: pointer" title="Package ${value.packageId} Information">
	             		<s:property value="#pm.index+1"/>
	             	</span>
	             </td>
				</s:else>
	            
	             <td width="40">&nbsp;${value.totalQty}</td>
	             <td width="45">&nbsp;${value.size}</td>
	             <td width="50">&nbsp;${value.pkgBatchSeq} of ${value.pkgBatchCount}</td>
	             <td width="80">&nbsp;${value.status}</td>
	             <td width="60">
	             	<div align="center">&nbsp;
	             		${value.invoiceNo}
	             	</div>
	             </td>
	             <td width="70">&nbsp;${value.shipVia}</td>
	             <td width="120" title="${value.shiptoAddress}">
	             	<s:if test="value.shiptoAddress.length() > 15">&nbsp;
		             	<s:property value="value.shiptoAddress.substring(0, 15)+'...'"/>
		            </s:if>
		            <s:else>&nbsp;${value.shiptoAddress}</s:else></td>
	             <td width="80">&nbsp;${value.warehouseName}</td>
	             <td width="110">
	             	<div align="right">&nbsp;${value.trackingNo}</div>
	             </td>
	             <td width="60">
	             	<div align="right">&nbsp;${value.billableWeight}</div>
	             </td>
	             <td>&nbsp;${value.customerCharge}</td>
	           </tr>
	         </s:iterator>
         </table>
       </div>
	   <div class="botton_box">
	   <s:if test="tempStatusStr == 'CO'">
			 <input type="button" class="style_botton2" value="View Packages" id="viewPackages" disabled="disabled"/>
         <input type="button" class="style_botton4" value="View Package for Item" id="viewPackagesForItems" disabled="disabled"/>
         <input type="button" class="style_botton2" value="Modify Packaging" id="" disabled="disabled"/>
		</s:if>
		<s:else>
				 <input type="button" class="style_botton2" value="View Packages" id="viewPackages" />
         <input type="button" class="style_botton4" value="View Package for Item" id="viewPackagesForItems" />
         <input type="button" class="style_botton2" value="Modify Packaging" id="" />
		</s:else>
        
       </div>
</body>
</html>