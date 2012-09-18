<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>scm</title>
		<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
		<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
		<script>function aCheck1(){

document.getElementById("Periodical_date").style.display="none";
}

function bCheck2(){

document.getElementById("Periodical_date").style.display="block";  
}  
</script>
		<style type="text/css" media="all">
@import "Upimg/thickbox.css";
</style>


		<script src="Upimg/jquery-1.1.3.1.pack.js" type="text/javascript"></script>
		<script src="Upimg/thickbox-compressed.js" type="text/javascript"></script>
	</head>
	<link href="stylesheet/openwin.css" rel="stylesheet" type="text/css" />
	<link type="text/css" href="stylesheet/ui.all.css" rel="stylesheet" />
	<script language="javascript" type="text/javascript" src="js/jquery.js"></script>
	<script src="js/ui.datepicker.js" type="text/javascript"></script>
	<script language="javascript" type="text/javascript"> 

		function selectBranch(){
  				var trackingNo=document.getElementById('trackingNo').value;
  			if(trackingNo==""){
  					alert("Please choose the Tracking No");
  					return false;
  			}else{
  					//parent.$('#vendor_search_dlg2').dialog('close');
  					//parent.location.href= //"work_order!getPackageInformation.action?trackingNo="+trackingNo+"";
  					//document.forms[0].submit();
					window.open('work_order!getPackageInformation.action?trackingNo='+trackingNo+'','Sample','toolbar=yes,location=no,directories=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,copyhistory=yes,width=1024,height=625,left=100,top=100');
  					}
  				}
  				
		function selectBranch2(){
  				var chks=document.getElementById('chks').value;
  				var warehouse=document.getElementById('warehouse').value;
  				var trackingNo=document.getElementById('trackingNo').value;
  				if((chks==null || chks.length==0)&&(trackingNo==null || trackingNo.length==0)){
  					alert('Please select order or select Tracking No！');
  					return false;
  				}
  					parent.$('#vendor_search_dlg2').dialog('close');
  					parent.location.href= "work_order!getBatchReceiving.action?chks="+chks+"&trackingNo="+trackingNo+"&warehouse="+warehouse+"";
  					document.forms[0].submit();
  				}
  				
  		function selectBranch3(){
  				var chks=document.getElementById('chks').value;
  				var warehouse=document.getElementById('warehouse').value;
  				var batchNos = document.getElementById('batchNos').value;
  				if((chks==null || chks.length==0)&&(batchNos==null || batchNos.length==0)){
  					alert('Please select order or select Batch No！');
  					return false;
  				}
  					parent.$('#vendor_search_dlg2').dialog('close');
  					parent.location.href= "work_order!getBatchReceiving.action?chks="+chks+"&batchNos="+batchNos+"&warehouse="+warehouse+"";
  					document.forms[0].submit();
  				}
</script>
	<body>
		<form action="work_order!getBatchOrderReceivingDetail.action">
			<input type="hidden" value="${chks}" name="chks" id="chks" />
			<input type="hidden" value="${warehouses}" name="warehouse" id="warehouse" />
						<table width="100%" border="0" cellpadding="0" cellspacing="0">

							<tr>
								<td>
									<br />
									<table width="620" border="0" cellpadding="0" cellspacing="0"
										class="General_table" style="margin: 10px auto 0px auto;">
										<tr>
											<th>
												Vendor
											</th>
											<td>
												<c:if test="${warehouses=='SALES'}">
													<input name="vendor" type="text" id="vendor" value="${vendorName }" readonly="readonly"/>
												</c:if>
												<c:if test="${warehouses=='MANUFACTURING'}">
													<input name="vendor2" type="text" readonly="readonly"
														class="NFText" id="vendor2"  value="${vendorName }"/>
												</c:if>
											</td>
											<th>
												Warehouse
											</th>
											<td>

												<select name="select" id="select_area" readonly="readonly">
													<option>
														${warehouse}
													</option>
												</select>


											</td>
										</tr>
										<tr>
											<th>
												Receiving Clerk
											</th>
											<td>
												<input name="rf" value="${loginName }" readonly="readonly"/>
											</td>
											<th>
												&nbsp;
											</th>
											<td>
												&nbsp;
											</td>
										</tr>
										<tr>
											<td colspan="4">
												<div class="invoice_title">
													Package Info
												</div>
											</td>
										</tr>
										<tr>
											<th>
												<c:if test="${warehouses=='MANUFACTURING'}">
												Batch ID
											</c:if>
												<c:if test="${warehouses=='SALES'}">
												Tracking No
												</c:if>
											</th>
											<td>
												<c:if test="${warehouses=='MANUFACTURING'}">
													<select name="batchNos" id="batchNos">
														<option value="">

														</option>
														<c:forEach items="${BaList}" var="tks">
															<option value="${tks. batchNo}">
																${tks.batchNo }
															</option>
														</c:forEach>
													</select>
												</c:if>
												<c:if test="${warehouses=='SALES'}">
													<select name="shipPackages.trackingNo" id="trackingNo">
														<option value="">

														</option>
														<c:forEach items="${TnLists}" var="tk">
															<option value="${tk }">
																${tk }
															</option>
														</c:forEach>
													</select>
												</c:if>
											</td>
											<th>
												Bar Code
											</th>
											<td>
												<select name="select3">
												</select>
											</td>
										</tr>
										<tr>
											<td colspan="4" valign="top">
												<br />
												<br />
												<div class="botton_box">
													<c:if test="${warehouses=='SALES'}">
														<input name="close" type="button" class="style_botton2"
															value="Process" onclick="selectBranch2();" id="btn_usa" />
													</c:if>
													<c:if test="${warehouses=='MANUFACTURING'}">
														<input name="close" type="button" class="style_botton2"
															value="Process" onclick="selectBranch3();" id="btn_usa" />
													</c:if>
													<input type="submit" name="Submit622" value="Cancel"
														class="style_botton"
														onclick="parent.$('#vendor_search_dlg2').dialog('close');" />
													<c:if test="${warehouses=='SALES'}">
														<input type="button" name="close" value="View Package"
															class="style_botton2" onclick="selectBranch();" />
													</c:if>
												</div>
												<br />
												<br />
											</td>
										</tr>
									</table>
									<br />
									<br />
								</td>
							</tr>
						</table>
		</form>
	</body>

</html>