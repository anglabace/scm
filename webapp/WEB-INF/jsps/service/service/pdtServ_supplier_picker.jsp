<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script>
$(document).ready(function(){
	$("#searchSupplier").click(function(){
		var name = $("#name").val();
		window.location.href = "${ctx}/serv/serv!showSupplierPikcerAct.action?vendorName="+name;
	});

	$("#enterNewBtn").click(function(){
		if($(this).val() == "Enter New"){
			$(this).val("Cancel");
			$("#inputSpan").show();
			$(":radio").attr("checked", false);
		}else{
			$(this).val("Enter New");
			$("#inputSpan").hide();
			$("#newSupplierName").val("");
		}
	});

	$("#selectSupplierBtn").click(function(){
		var vendorName,vendorNo = "";
		if($("#inputSpan").get(0).style.display != "none" && $("#newSupplierName").val() != ""){
			vendorName = $("#newSupplierName").val();
		}else{
			vendorName =$(":radio[checked]").attr("vendorName");
			vendorNo =$(":radio[checked]").val();
		}
		if(!vendorName){
			alert("Please select a supplier item to continue your operation.");
			return;
		}
		parent.$("#supplierEditDialog Iframe").contents().find("#vendorName").val(vendorName);
		parent.$("#supplierEditDialog Iframe").contents().find("#vendorNo").val(vendorNo);
		parent.$("#supplierPickerDialog").dialog("close");
	});
});
</script>
</head>

<body> 
		  <table width="645" border="0" cellpadding="0" cellspacing="0" class="list_table">
            <tr>
				<td>
				<b>Name</b>
				<input type="text" id="name" name="vendorName" size="50" class="NFText" value="${vendorName}" />
				<input type="button" id="searchSupplier" class="style_botton" value="Search" />
				</td>
            </tr>
          </table>	
          <table width="645" border="0" cellpadding="0" cellspacing="0" class="list_table">
            <tr>
              <th width="40">&nbsp;</th>
              <th width="40">ID</th>
              <th width="100">Name</th>
              <th width="">Description</th>
              <th width="100">Status</th>
            </tr>
          </table>
              <table width="645" border="0" cellpadding="0" cellspacing="0" class="list_table2" id="supplierTable">
                <s:iterator value="vendorDTOList">
                <tr>
                  <td width="40">
                  	<input type="radio" value="${vendorNo}" name="vendorNo" vendorName="${vendorName}" />
                  </td>
                  <td width="40">${vendorNo}</td>
                  <td width="100">${vendorName}</td>
                  <td width="">${description}&nbsp;</td>
                  <td width="100">${status}</td>
                </tr>
                </s:iterator>
              </table>
              
               <table width="645" border="0" cellpadding="0" cellspacing="0" class="" id="supplierTable">
                <tr style="display:none;">
                	<td colspan="">
                		<b>Can not find it?</b> 
                		<input value="Enter New" type="button" id="enterNewBtn" class="style_botton" />
						<span id="inputSpan" style="display:none">
							<input type="text" name="newSupplierName" id="newSupplierName" class="NFText" />
						</span>
                	</td>
                </tr>
                <tr>
                	<td align="center">
                		<input id="selectSupplierBtn" type="button" class="style_botton" value="Select" />
                	</td>
                </tr>
              </table>
              
</body>
</html>