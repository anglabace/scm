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
		window.location.href = "product/product!showRoyaltiesList.action?miscName="+name;
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
			alert("Please select one item to continue your operation.");
			return;
		}
		parent.$("#miscIframe").contents().find("#royaltyName").val(vendorName);
		parent.$("#miscIframe").contents().find("#royaltyId").val(vendorNo);
		parent.$("#miscPickerDialog").dialog("close");
	});
});
</script>
</head>

<body> 
		  <table width="645" border="0" cellpadding="0" cellspacing="0">
            <tr>
				<td>
				<b>Name</b>
				<input type="text" id="name" name="name" size="50" class="NFText" value="${miscName}" />
				<input type="button" id="searchSupplier" class="style_botton" value="Search" />
				</td>
            </tr>
          </table>
          <br />
          <table width="645" border="0" cellpadding="0" cellspacing="0" class="list_table">
            <tr>
              <th width="40">&nbsp;</th>
              <th width="40">ID</th>
              <th width="200">Name</th>
            </tr>
          </table>
              <table width="645" border="0" cellpadding="0" cellspacing="0" class="list_table2" id="supplierTable">
               <s:iterator value="royaltyList">
                <tr>
                  <td width="40">
                  <input type="radio" value="${id}" name="vendorNo" vendorName="${name}" />
                  </td>
                  <td width="40">${id}</td>
                  <td width="200">${name}</td>
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
                	<br />
                	<input id="selectSupplierBtn" type="button" class="style_botton" value="Select" />
                </td>
                </tr>
              </table>
              
</body>
</html>