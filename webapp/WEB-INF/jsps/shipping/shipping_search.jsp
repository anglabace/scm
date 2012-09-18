<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Order Management</title>
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
		<script type="text/javascript">var GB_ROOT_DIR = "./greybox/";</script>
		<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
		<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
		<script type="text/javascript"
			src="${global_js_url}greybox/gb_scripts.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript" src="greybox/AJS.js"></script>

		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
		<script
			src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.core.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.draggable.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.resizable.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.dialog.js"
			type="text/javascript"></script>

		<script type="text/javascript" language="javascript">
			 $(function() {            
            	parent.$('#vendor_search_dlg').dialog({
					autoOpen: false,
					height: 460,
					width: 660,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });

			function openSearchDlg() {
				parent.$('#vendor_search_dlg').dialog("option", "open", function() {	
			         var htmlStr = '<iframe src="work_order!vendorManageList.action" height="440" width="620" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			         parent.$('#vendor_search_dlg').html(htmlStr);
				});
				parent.$('#vendor_search_dlg').dialog('open');
			
			}		
			function selectBranch(){
				document.forms[0].action = "work_order!getWorkOrderManageList.action";
				document.forms[0].submit();
  			}
  			function search(){
  				var id=$("#shipschDTO.orderNo").val();
  				$("#shipschDTO.orderNo").attr("value",$.trim(id));
  				$("#shippingForm").attr("action","shipping!shippingList.action");
  				$("#shippingForm").submit();
  	  		}
		</script>
	</head>
	<body>
		<form name="shippingForm" id="shippingForm"
			action="shipping!shippingList.action" target="appInfo">
			<table border="0" cellpadding="0" cellspacing="0"
				class="General_table" style="TABLE-LAYOUT: fixed">
				<tr>
					<td>
						Warehouse
					</td>
					<td width="130">
						<span class="single_search_css">
							<select name="warehouseType" id="warehouseType" 
								onchange="shippingForm.action='shipping!shippingList.action';shippingForm.submit();"  style="width: 150px">
								<c:forEach items="${wList}" var="o">
									<option <c:if test="${o.type == warehouseType }">selected</c:if> value="${o.type }">
										${o.name}
									</option>
								</c:forEach>
							</select>
						</span>
					</td>
					<td>
						Shipping Clerk
					</td>
					<td width="130">
						<select name="shipschDTO.shippingClerk" id="shippingClerk"  style="width: 130px">
						<c:if test="${isProductionManagerRole==true}">
									<option value="0">
										All
									</option>
							</c:if>
							<c:forEach items="${nList}" var="rc">
								<c:if test="${rc.userId==userId}">
									<option value="${rc.userId }" selected="selected">
										${rc.employee.employeeName }
									</option>
								</c:if>
								<c:if test="${rc.userId!=userId}">
									<option value="${rc.userId }">
										${rc.employee.employeeName }
									</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td>
						Product/Service Type
					</td>
					<td width="180">
					<select name="shipschDTO.type" id="shipschDTO.type" style="width: 180px;">
								<option value="">
											All
								</option>
								<c:forEach items="${allcls}" var="cls">
									
										<option value="${cls.key}" title="${cls.value }">
											${cls.value}
										</option>
									
									</c:forEach>
							</select>
						<!--<select name="shipschDTO.type" id="shipschDTO.type">
							<option value="">All</option>
							<c:forEach items="${tList}" var="ty">
								<option value="${ty.orderType }">
									${ty.orderType }
								</option>
							</c:forEach>
						</select>-->
						</td>
				</tr>
				<tr>
					<td align="right">
						Order No
					</td>
					<td width="130">
						<input id="shipschDTO.orderNo" name="shipschDTO.orderNo" type="text" class="NFText" style="width: 143px" onKeyUp="value=value.replace(/\D+/g,'')"  />
					</td>
					<td align="right">
						
					</td>
					<td width="130">
						
					</td>
					<td align="right">
						US PO NO
					</td>
					<td width="130">
						<input type="text" name="shipschDTO.poNo" class="NFText" style="width: 143px" onKeyUp="value=value.replace(/\D+/g,'')"/>
					</td>
				</tr>
				<tr>
					<th></th>
					<td width="120"></td>
					<th></th>
					<td width="120"><input type="button" value="Search" class="search_input" 
							onclick="search();" /></td>
					<th></th>
					<td></td>
					<td>
						
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>