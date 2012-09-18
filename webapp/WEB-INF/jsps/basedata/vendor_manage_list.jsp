<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User list</title>
		<base href="${global_url}" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet"
			type="text/css" media="all" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet"
			type="text/css" />
		<script language="javascript" type="text/javascript"
			src="${global_js_url}ajax.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}tab-view.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}TabbedPanels.js"></script>
		<script type="text/javascript">
    var GB_ROOT_DIR = "./greybox/";
</script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript">
		<c:if test="${request.sign == 1}">
			alert(" You search's role not exist !");
		</c:if>
		
		function selects(){
  				
  				var wos = $(":radio[id='vendorName']:checked").val().split("-");		
  				$('#vendorNo').val(wos);
  				$(window.parent.document).find('#filter_EQS_vendorName').val(wos[1]);
  				parent.$('#purchaseOrderDialog').find("#newPurcharseOrderIframe").contents().find( '#vendorName' ).val(wos[1]) ;
  				parent.$('#purchaseOrderDialog').find("#newPurcharseOrderIframe").contents().find( '#vendorNo' ).val(wos[0]) ;
  				parent.$('#vendor_search_dlg').dialog('close');
  				//document.forms[0].action ="vendor_picker!pickValue.action?vendorName="+wos+"";
  				//	document.forms[0].submit();
  				//	parent.$('#vendor_search_dlg').dialog('close');
  				}
	</script>
	</head>
	<body class="content">
		<form action="" name="wo" id="wo">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td style="padding-right: 17px;"></td>
				</tr>
				<tr>
					<td>
						<div style="margin-right: 17px;">
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="list_table">
								<tr>
									<th width="20">
										<div align="left">
										</div>
									</th>
									<th width="90">
										Name
									</th>
									<th width="100">
										Status
									</th>
								</tr>
							</table>
						</div>
						<div class="list_box" style="height: 340px;">
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0" class="list_table">
									<c:set var="rowcount" value="1"></c:set>
									<s:iterator value="vendorDTOList">
										<c:if test="${rowcount mod 2 == 0}">
											<c:set var="tdclass" value=" class='list_td2'"></c:set>
										</c:if>
										<c:if test="${rowcount mod 2 == 1}">
											<c:set var="tdclass" value=""></c:set>
										</c:if>
										<tr>
											<td width="20" style="height: 20px;"${tdclass}>
												<input type="radio" id="vendorName" name="vendorName" value="${vendorNo}-${vendorName}" />
											</td>
											<td style="width: 90px"${tdclass}>
												&nbsp;${vendorName }
											</td>
											<td style="width: 100px"${tdclass}>
												&nbsp;${status}
											</td>
										</tr>
										<c:set var="rowcount" value="${rowcount+1}"></c:set>
									</s:iterator>
								</table>
							</div>
						</div>
						<div class="grayr">
							<jsp:include page="/common/db_pager.jsp">
								<jsp:param value="${ctx}/vendor_picker.action"
									name="moduleURL" />
							</jsp:include></div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div align="center">
							<input name="Submit3" type="button" class="style_botton"
								value="Select" onclick="selects();" />
							<input type="submit" name="Submit622" value="Cancel"
								class="style_botton"
								onclick="parent.$('#vendor_search_dlg').dialog('close');" />
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
