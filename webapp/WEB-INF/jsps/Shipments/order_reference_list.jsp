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
		<script language="javascript" type="text/javascript">	
		 function listAll(obj, chk){
              if (chk == null)
			  {
			    chk = 'checkboxes';
			  }
			
			  var elems = obj.form.getElementsByTagName("INPUT");
			
			  for (var i=0; i < elems.length; i++)
			  {
			    if (elems[i].name == chk || elems[i].name == chk + "[]")
			    {
			      elems[i].checked = obj.checked;
			    }
			  }
          }
		
 function selects(){ 
		var cksObj = $("input:checked");
		var cksObjs = new Array();
		cksObj.each(
			function()
			{
				if( $(this).attr( 'checked' ) == true ) 
				{
					cksObjs.push($( this ).val() ) ;
				}
			}
		) ;
		document.forms[0].action = "shipments!shipmentListSelect.action?orderNos="+cksObjs;
		document.forms[0].submit();
	  parent.$('#vendor_search_dlg3').dialog('close');
	} 		
    </script>
	</head>
	<body class="content">
	<form action="shipments!shipmentListSelect.action" method="post">
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
								
								<th width="90">
									OrderNo
								</th>
								<th width="100">
									OrderType
								</th>
							</tr>
						</table>
					</div>
					<div class="list_box" style="height: 340px;">
						<div id="userOfRoleDiv" name="userOfRoleDiv">
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="list_table">
								<c:set var="rowcount" value="1"></c:set>
								<s:iterator value="orderList">
									<c:if test="${rowcount mod 2 == 0}">
										<c:set var="tdclass" value=" class='list_td2'"></c:set>
									</c:if>
									<c:if test="${rowcount mod 2 == 1}">
										<c:set var="tdclass" value=""></c:set>
									</c:if>
									<tr>
										
										<td style="width: 80px"${tdclass}>
											${orderNo }
										</td>
										<td style="width: 90px"${tdclass}>
											${orderType}
										</td>
									</tr>
									<c:set var="rowcount" value="${rowcount+1}"></c:set>
								</s:iterator>
							</table>
						</div>
					</div>
					
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div align="center">
						
						<input type="submit" name="Submit622" value="Cancel"
							class="style_botton"
							onclick="parent.$('vendor_search_dlg3').dialog('close');" />
					</div>
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>