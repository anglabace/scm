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
</script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript">
		
		function selects(){
  				
  				var wos = $(":radio[id='custNo']:checked").val().split("-");
  				$('#custNo').val(wos);
  				if(wos.length == 3){
  				$(window.parent.document).find('#filter_EQI_vendorNo').val("-"+wos[1]);
  				parent.$('#salesOrderDialog').find("#newPurcharseOrderIframe").contents().find( '#custNo' ).val("-"+wos[1]) ;
  				parent.$('#salesOrderDialog').find("#newPurcharseOrderIframe").contents().find( '#custName' ).val(wos[2]) ;
  				
  				parent.$('#promotion_edit_dialog').find("#promotion_edit_iframe").contents().find( '#custNo' ).val("-"+wos[1]) ;
  				parent.$('#promotion_add_dialog').find("#promotion_add_iframe").contents().find( '#custNo' ).val("-"+wos[1]) ;
  				}else{
  				$(window.parent.document).find('#filter_EQI_vendorNo').val(wos[0]);
  				parent.$('#salesOrderDialog').find("#newPurcharseOrderIframe").contents().find( '#custNo' ).val(wos[0]) ;
  				parent.$('#salesOrderDialog').find("#newPurcharseOrderIframe").contents().find( '#custName' ).val(wos[1]) ;
  				
  				parent.$('#promotion_edit_dialog').find("#promotion_edit_iframe").contents().find( '#custNo' ).val(wos[0]) ;
  				parent.$('#promotion_add_dialog').find("#promotion_add_iframe").contents().find( '#custNo' ).val(wos[0]) ;
  				}
  				//document.forms[0].action ="vendor_picker!pickValue.action?vendorName="+wos+"";
  				//	document.forms[0].submit();
  				//	parent.$('#vendor_search_dlg').dialog('close');
  				parent.$('#customer_search_dlg').dialog('close');
  				}
	</script>
	</head>
	<body class="content">
		<form action="" name="wo" id="wo">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td style="padding-right: 17px;"><table width="537" border="0" cellpadding="0" cellspacing="0">

          <tr>
            <td><br /><table border="0" cellpadding="0" cellspacing="0" class="Customer_table">
              <tr>
                <th>Customer No</th>
                <td><input name="custNo" type="text"  class="NFText" size="20" value="${ custNo}"/></td>
                <th>Customer Name</th>
                <td><input name="custName" type="text"  class="NFText" size="20" value="${custName }"/></td>
                <td><input name="Submit3" type="submit" class="style_botton" value="Search" /></td>

              </tr>
            </table>
</td>

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
										No
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
									<s:iterator value="customerDTOList">
										<c:if test="${rowcount mod 2 == 0}">
											<c:set var="tdclass" value=" class='list_td2'"></c:set>
										</c:if>
										<c:if test="${rowcount mod 2 == 1}">
											<c:set var="tdclass" value=""></c:set>
										</c:if>
										<tr>
											<td width="20" style="height: 20px;"${tdclass}>
												<input type="radio" id="custNo" name="custNo" value="${custNo }-${custName }" />
											</td>
											<td style="width: 90px"${tdclass}>
												&nbsp;${custNo }
											</td>
											<td style="width: 90px"${tdclass}>
												&nbsp;${custName }
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
								<jsp:param value="${ctx}/customer_picker.action"
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
								onclick="parent.$('#customer_search_dlg').dialog('close');" />
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
