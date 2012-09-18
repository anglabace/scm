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

		function check(){
			var id=document.getElementById('purchaseOrderDTO_orderNo').value;
			$("#purchaseOrderDTO_orderNo").attr("value",$.trim(id));
			var purchaseOrderDTO_Vendors_VendorName = document.getElementById('purchaseOrderDTO_Vendors_VendorName').value;
			if(isNaN(id)){
				alert("OrderNo must is number !");
				return false;
			}else {
				document.userSearchForm.submit();
			}
		}
		function check2(){
			var id=document.getElementById('workOrderDTO_orderNo').value;
			var workOrderDTO_WorkGroup_Name = document.getElementById('workOrderDTO_WorkGroup_Name').value;
			if(isNaN(id)){
				alert("OrderNo must is number !");
				return false;
			}else {
				document.userSearchForm.submit();
			}
		}

		function openSearchDlg() {
			parent.$('#vendor_search_dlg').dialog("option", "open", function() {	
		         var htmlStr = '<iframe src="work_order!vendorManageList.action" height="410" width="620" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		         parent.$('#vendor_search_dlg').html(htmlStr);
			});
			parent.$('#vendor_search_dlg').dialog('open');
		
		}		
		function selectBranch(){
  				var type=document.getElementById('type').value;
  				if(type=='MANUFACTURING'){
  					document.getElementById('div1').style.display='block';
  					document.getElementById('div2').style.display='none';
  					document.getElementById('div3').style.display='block';
  					document.getElementById('div4').style.display='none';
  					document.getElementById('div5').style.display='none';
  					document.getElementById('div6').style.display='block';
  					document.getElementById('div7').style.display='none';
  					document.getElementById('div8').style.display='block';
  					document.getElementById('div9').style.display='none';
  					document.getElementById('div10').style.display='block';
  				}else if(type=='SALES'){
  					document.getElementById('div1').style.display='none';
  					document.getElementById('div2').style.display='block';
  					document.getElementById('div3').style.display='none';
  					document.getElementById('div4').style.display='block';
  					document.getElementById('div5').style.display='block';
  					document.getElementById('div6').style.display='none';
  					document.getElementById('div7').style.display='block';
  					document.getElementById('div8').style.display='none';
  					document.getElementById('div9').style.display='block';
  					document.getElementById('div10').style.display='none';
  				}
  				document.forms[0].action = "work_order!getWorkOrderManageList.action?type='"+type+"'";
  				document.forms[0].submit();
  			}
</script>
	</head>
	<body>
		<form name="userSearchForm"
			action="work_order!getWorkOrderManageList.action" target="userInfo">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="General_table" >
				<tr>
					<td width="217" height="19" align="right">
						Vendor
					</td>
					<td width="213">
						<div id="div5">
						<c:if test="${type=='MANUFACTURING'}">
						<input name="woname" type="text"
								id="workOrderDTO_WorkGroup_Name"
								size="20" readonly="readonly"/><img src="images/search.gif" width="16" height="16" border="0px" />
						</c:if>
						<c:if test="${type=='SALES'||type==''}">
							<input name="poname" type="text"
								class="NFText" id="purchaseOrderDTO_Vendors_VendorName"
								value="${vendorNames }" size="20" />
							<a href="javascript:void(0);" onclick="openSearchDlg();"><img
									src="images/search.gif" width="16" height="16" border="0px" />
							</a>
						</c:if>
						</div>
						<div id="div6" style="display: none">
							<input name="woname" type="text"
								id="workOrderDTO_WorkGroup_Name"
								size="20" readonly="readonly"/>
							<img src="images/search.gif" width="16" height="16" border="0px" />
						</div>

					</td>
					<td width="138" align="right">
						<div id="paramName">
							Warehouse
						</div>
					</td>
					<td width="167">
						<select name="type" id="type" onchange="selectBranch();" style="width: 150px">
							<c:forEach var="wh" items="${whList}">
								<option value="${wh.type }" ${wh.type==type ? 'selected':''}>
									${wh.name}
								</option>
							</c:forEach>
						</select>
					</td>
					<td width="182" align="right">
						<div id="paramLoginName">
							Receiving Clerk
						</div>
					</td>
					<td width="184">
						<select name="rc" id="select">
							
							<c:if test="${isProductionManagerRole==true}">
									<option value="">
										All
									</option>
							</c:if>
							<c:forEach items="${rcList}" var="rc">
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
					<td width="130">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="19" align="right">
						<div id="div1" style="display: none">
						
							Work Order No
						</div>
						<div id="div2">
						<c:if test="${type=='MANUFACTURING'}">
						Work Order No
						</c:if>
						<c:if test="${type=='SALES'||type==''}">
						Purchase Order No
						</c:if>
						</div>
					</td>
					<td>
						<div id="div3" style="display: none">
							<input name="workOrderDTO.orderNo" type="text" class="NFText"
								id="workOrderDTO_orderNo" size="20"
								onKeyUp="value=value.replace(/\D+/g,'')"/>
						</div>
						<div id="div4">
						<c:if test="${type=='MANUFACTURING'}">
						<input name="workOrderDTO.orderNo2" type="text" class="NFText"
								id="workOrderDTO_orderNo" size="20" 
								onKeyUp="value=value.replace(/\D+/g,'')"/>
						</c:if>
						<c:if test="${type=='SALES'||type==''}">
							<input name="purchaseOrderDTO.orderNo" type="text" class="NFText"
								id="purchaseOrderDTO_orderNo" size="20" 
								onKeyUp="value=value.replace(/\D+/g,'')"/>
								</c:if>
						</div>
					</td>
					<td align="right">
						Status
					</td>
					<td>
						<div id="div8" style="display: none">
							<select name="workOrderDTO.status" id="select2" style="width: 150px">
								<option value="">
									All
								</option>
								<option value="CC">
									CC
								</option>
								<option value="VC">
									VC
								</option>
								<option value="IP">
									IP
								</option>
							</select>
						</div>
						<div id="div7">
						<c:if test="${type=='MANUFACTURING'}">
						<select name="workOrderDTO.status" id="select2"  style="width: 150px">
								<option value="">
									All
								</option>
								<c:if test="${wolist!=null}">
									<c:forEach items="${wolist}" var="wolist">
										<option value="${wolist }">
											${wolist}
										</option>
									</c:forEach>
								</c:if>
							</select>
						</c:if>
						<c:if test="${type=='SALES'||type==''}">
							<select name="purchaseOrderDTO.status" id="select2"  style="width: 150px">
								<option value="">
									All
								</option>
								<c:if test="${polist!=null}">
									<c:forEach items="${polist}" var="polist">
										<option value="${polist}">
											${polist }
										</option>
									</c:forEach>
								</c:if>
							</select>
							</c:if>
						</div>
					</td>
					<th>
						US PO No
					</th>
					<td>
						<input name="purchaseOrderDTO.usPOrderNo" type="text" class="NFText"
								id="purchaseOrderDTO_pOrderNo" size="20" 
								onKeyUp="value=value.replace(/\D+/g,'')"/>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align="right">
						Order Reference
					</td>
					<td>
						<input name="purchaseOrderDTO.usSOrderNo" type="text" class="NFText"
								id="purchaseOrderDTO_sOrderNo" size="20" 
								onKeyUp="value=value.replace(/\D+/g,'')"/>
					</td>
				</tr>
				<tr>
					<td height="5" colspan="7" align="center"><div id="div9">
						<c:if test="${type=='MANUFACTURING'}">
						<input type="button" name="searchButton" value="Search"
								onclick="check2();" class="search_input" />
						</c:if>
						<c:if test="${type=='SALES'||type==''}">
							<input type="button" name="searchButton" value="Search"
								onclick="check();" class="search_input" />
						</c:if>
						</div>
						<div id="div10" style="display: none">
							<input type="button" name="searchButton" value="Search"
								onclick="check2();" class="search_input" />
						</div></td>
				</tr>
			</table>
			<br />
		</form>
	</body>
</html>