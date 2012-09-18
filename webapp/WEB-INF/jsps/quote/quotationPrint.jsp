<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<base href="<%=basePath%>" />

<link href="${global_css_url}print_new.css" rel="stylesheet"
	type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
	language="javascript"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js"
	type="text/javascript"></script>
<script>
function print() {
	parent.$("#printOrderQuDialog").dialog("close");
	return true;
}
</script>
</head>
<body style="font-family: Arial;">
	<s:form method="post" action="quote_extra!print.action"
		onsubmit="return print();">
		<s:hidden name="quoteNo"></s:hidden>
		<s:if test='editFlg!=null&&editFlg.equals("Y")'>
			<div style="width: 800px; margin: 0px auto;">
		</s:if>
		<s:else>
			<div
				style="width: 760px; margin: 0px auto;background-image: url(${global_image_url}confidential.gif);background-repeat:no-repeat; ">
		</s:else>
		<table width="100%" cellpadding="0"
			style="line-height: 20px; font-size: 15px;">
			<tr>
				<td width="80%" align="right">Quote No:&nbsp;&nbsp;</td>
				<td align="left"><s:property value="quotationPrintDTO.qId" />
				</td>
			</tr>
			<tr>
				<td width="80%" align="right">Prepared Date:&nbsp;&nbsp;</td>
				<td align="left"><s:property value="quotationPrintDTO.initDate" />
				</td>
			</tr>
			<tr>
				<td width="80%" align="right">Expiration Date:&nbsp;&nbsp;</td>
				<td align="left"><s:property value="quotationPrintDTO.expDate" />
				</td>
			</tr>
		</table>

		<br />
		<br />

		<h1 align="center">
			<span style="font-size: 30px; font-weight: bold; font-family: Arial">Project
				Proposal</span>
		</h1>
		<br /> 
		<table width="100%" cellpadding="0">
			<tr valign="top">
				<td width="20%" align="right"><span
					style="font-size: 14px; font-weight: bold;">Prepared
						For:&nbsp;&nbsp;</span>
				</td>
				<td width="30%" align="left"><span
					style="font-size: 14px; font-weight: bold; line-height: 13px">
						<s:property value="quotationPrintDTO.firstName" />&nbsp; <s:property
							value="quotationPrintDTO.lastName" /> </span><br /> <s:if
						test='editFlg!=null&&editFlg.equals("Y")'>
					Email:<span style="font-size: 14px;"><s:textfield
								name="quotationPrintDTO.email" /> </span>
						<br />
					Department:<span style="font-size: 14px;"><s:textfield
								name="quotationPrintDTO.department" /> </span>
						<br />
					Institution:<span style="font-size: 8px;"><s:textfield
								name="quotationPrintDTO.institution" /> </span>
						<br />
					State:<span style="font-size: 14px;"><s:textfield
								name="quotationPrintDTO.state" /> </span>
						<br />
					Country:<span style="font-size: 14px;"><s:textfield
								name="quotationPrintDTO.country" /> </span>
					</s:if> <s:else>
						<span style="font-size: 14px; font-weight: bold;"><s:property
								value="quotationPrintDTO.email" escape="false" /> </span>
						<br />
						<span style="font-size: 14px; font-weight: bold;"><s:property
								value="quotationPrintDTO.department" escape="false" /> </span>
						<br />
						<span style="font-size: 14px; font-weight: bold;"><s:property
								value="quotationPrintDTO.institution" escape="false" /> </span>
						<br />
						<span style="font-size: 14px; font-weight: bold;"><s:property
								value="quotationPrintDTO.state" escape="false" /> </span>
						<br />
						<span style="font-size: 14px; font-weight: bold;"><s:property
								value="quotationPrintDTO.country" escape="false" /> </span>
					</s:else></td>

				<td width="20%" align="right"><span
					style="font-size: 14px; font-weight: bold; line-height: 13px">Prepared
						By:&nbsp;&nbsp;</span>
				</td>
				<td width="30%" align="left"><span style="font-size: 14px; line-height: 15px"><s:property
							value="quotationPrintDTO.preparedByName" /> <br /> <s:property
							value="quotationPrintDTO.techManager" /> <br /> <s:if
						test='editFlg!=null&&editFlg.equals("Y")'>
			Company:<s:textfield name="quotationPrintDTO.companyName" />
						<br />
			Address1:<s:textfield name="quotationPrintDTO.address1" />
						<br />
			Address2:<s:textfield name="quotationPrintDTO.address2" />
						<br />
			Tel:<s:textfield name="quotationPrintDTO.telephone" />
						<br />
			Fax:<s:textfield name="quotationPrintDTO.fax" />
						<br />
			Email:<s:textfield name="quotationPrintDTO.custEmail" />
						<br />
			WebSite:<s:textfield name="quotationPrintDTO.web" />
						<br />
					</s:if> <s:else>
						<s:property value="quotationPrintDTO.companyName" escape="false" />
						<br />
						<s:property value="quotationPrintDTO.address1" escape="false" />
						<br />
						<s:property value="quotationPrintDTO.address2" escape="false" />
						<br />
						<s:property value="quotationPrintDTO.telephone" escape="false" />
						<br />
						<s:property value="quotationPrintDTO.fax" escape="false" />
						<br />
						<s:property value="quotationPrintDTO.custEmail" escape="false" />
						<br />
						<s:property value="quotationPrintDTO.web" escape="false" />
						<br />
					</s:else> </span></td>
			</tr>
		</table>
		<br /> 
		<table width="800px" cellpadding="0" style="line-height: 14px;"> 
			<tr>
				<td><span style="font-weight: bold;font-size: 15px;"><s:property
							value="quotationPrintDTO.companyName" /> </span><span  style="font-size: 13px;"> is an
						internationally recognized contract research organization (CRO).
						We are specialized in providing customized solutions and
						pioneering products aiming for cost-effective and efficient
						biological research and drug discovery.</span>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><span style="font-size: 15px">GenScript's</span><span
					style="font-size: 13px;"> service
						portfolio includes bio-reagent services, assay development
						&&nbsp;screening, lead optimization, and antibody drug
						development. Our renowned One-stop bio-reagent services encompass
						custom gene synthesis and molecular biology, custom protein
						expression and purification, custom peptide synthesis, antibody
						production, and custom cell line development. Over the years,
						GenScript has established many innovative technology platforms and
						research products including Optimum Gene&trade; codon
						optimization, CloneEZ&reg; kit, ONE-HOUR Western&trade; Kits, and
						THE&trade; Epitope Tag Antibodies.</span>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><span style="font-size: 13px;">With
						a global customer base ranging from academic and government
						research institutions to pharmaceutical and biotech companies,
						GenScript shares with you not only a goal-alignment business
						relationship but also a mission to save people's lives by
						accelerating human disease research and drug discovery.</span>
				</td>
			</tr>
		</table>

		<br />
		<table width="800px" border="1" cellspacing="5" cellpadding="0">
			<tr>
				<td> 
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="20"><span class="css_17_b">To place an
									order, you may:</span>
							</td>
						</tr>
						<tr>
							<td><table width="100%" border="0" cellspacing="0"
									cellpadding="0" style="font-size: 13px">
									<tr>
										<td width="3%">1.</td>
										<td width="97%">If ordering by purchase order, fax your
											official PO along with this page to 732-210-0262</td>
									</tr>
									<tr>
										<td>2.</td>
										<td>If ordering by credit card*, fill info below and fax
											the form back: Visa card / Master card</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>CC No.: _______________________ Card Holder Name:
											_____________________</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>Exp. Date: _____________________ Security Code:
											_________________________</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>* Please check with our customer service for our
											conditions for accepting credit cards.</td>
									</tr>
									<tr>
										<td align="left" valign="top">3.</td>
										<td>For peptides or catalog products, you may also visit
											<span class="css_un">www.genscript.com</span> to log into
											your account and place your order on-line.</td>
									</tr>
									<tr>
										<td>4.</td>
										<td>If there are more questions, call our customer
											service at 732-885-9188 or 877-436-7274</td>
									</tr>
								</table>
							</td>
						</tr>
					</table> 
				</td>
			</tr>
		</table>
		</div>
		<div STYLE="page-break-after: always;">&nbsp;</div>
		<s:if test='editFlg!=null&&editFlg.equals("Y")'>
			<div style="width: 800px; margin: 0px auto;">
		</s:if>
		<s:else>
			<div
				style="width: 760px; margin: 0px auto;background-image: url(${global_image_url}confidential.gif);background-repeat:no-repeat;">
		</s:else>
		<h3 align="center">
			<span style="font-size: 25px; font-weight: bold; font-family: Arial">Quotation</span>
		</h3>
		<br />  
		<table width="800px" cellpadding="0"> 
			<tr>
				<TD align="left"><s:property
						value="quotationPrintDTO.firstName" escape="false" /> <s:property
						value="quotationPrintDTO.lastName" escape="false" />
				</TD>
				<TD align="right" width="40%" valign="bottom">Quote Date: <s:property
						value="quotationPrintDTO.initDate" />
				</TD>
			</tr>

			<tr>
				<TD align="left"><s:property
						value="quotationPrintDTO.institution" escape="false" />
				</TD>
				<TD align="right" width="40%" valign="bottom">Valid Through: <s:property
						value="quotationPrintDTO.expDate" />
				</TD>
			</tr>

		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tableBorder2">
			<tr>
				<td align="center" width="20%">Quote No.</td>
				<td align="center" width="40%">Currency</td>
				<td align="center" width="20%">Terms</td>
				<td align="center" width="20%">Ship Via</td>
			</tr>

			<tr>
				<td align="center" width="20%"><s:property
						value="quotationPrintDTO.qId" />
				</td>
				<td align="center" width="40%"><s:property
						value="quotationPrintDTO.name" /> (<s:property
						value="quotationPrintDTO.symbol" />)</td>
				<td align="center" width="20%">Net 30</td>
				<td align="center" width="20%"><s:property
						value="quotationPrintDTO.shipVia" />
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tableBorder3">
			<tr>
				<td align="center" width="10%">Quantity</td>
			<!-- 	<td align="center" width="10%">Item No</td> -->
				<td align="center" width="50%">Description</td>
				<td align="center" width="15%">Unit Price</td>
				<td align="center" width="15%">Unit Discount</td>
				<td align="center" width="15%">Extended Price</td>
			</tr>
			<s:property value="quotationPrintDTO.tableBody" escape="false" />
			<tr>
				<td colspan="2" align="right" width="55%">Subtotal(<s:property
						value="quotationPrintDTO.subTotalName" />) </td>
				<td align="right" width="15%"><s:property
						value="quotationPrintDTO.symbol" /> <s:property
						value="quotationPrintDTO.subprice" />&nbsp;</td>
				<td align="right" width="15%"><s:property
						value="quotationPrintDTO.symbol" /> <s:property
						value="quotationPrintDTO.subDiscount" />&nbsp;</td>
				<td align="right" width="15%"><s:property
						value="quotationPrintDTO.symbol" /> <s:property
						value="quotationPrintDTO.subTotal" />&nbsp;</td>
			</tr>

			<s:property value="quotationPrintDTO.shipPriceDepartment"
				escape="false" />
			<s:property value="quotationPrintDTO.taxDepartment" escape="false" />
			<tr>
				<td colspan="4" align="right" width="85%">Total Quote (<s:property
						value="quotationPrintDTO.subTotalName" />)&nbsp;</td>
				<td align="right" width="15%"><s:property
						value="quotationPrintDTO.symbol" /> <s:property
						value="quotationPrintDTO.total" />&nbsp;</td>
			</tr>
			<tr>
				<td colspan="5"><span
					style="font-size: 15px; font-weight: bold;">Comments:</span><br />
					<s:if test='editFlg!=null&&editFlg.equals("Y")'>
						<textarea name="quotationPrintDTO.comments" class="inputa"
							style="height: 50px; width: 790px;">
			<s:property value="quotationPrintDTO.comments" escape="false" />
		</textarea>
					</s:if> <s:else>
						<s:property value="quotationPrintDTO.comments" escape="false" />
					</s:else></td>
			</tr>
		</table>
		<br />  
		</div>
		<s:if test="quotationPrintDTO.showChild">
			<div STYLE="page-break-after: always;">&nbsp;</div>
			<s:if test='editFlg!=null&&editFlg.equals("Y")'>
				<div style="width: 800px; margin: 150px auto;">
			</s:if> 
			<s:else>
				<div
					style="width: 760px; margin: 0px auto;background-image: url(${global_image_url}confidential.gif);background-repeat:no-repeat;">
			</s:else>

			<h3 align="center">
				<span style="font-size: 20px; font-weight: bold;">Quotation&nbsp;&nbsp;Details</span>
			</h3>
			<br />
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="60%"><s:property
							value="quotationPrintDTO.firstName" /> <s:property
							value="quotationPrintDTO.lastName" />
					</td>
					<td align="right">Quote Date:<s:property
							value="quotationPrintDTO.initDate" />
					</td>
				</tr>
				<tr>
					<td><s:property value="quotationPrintDTO.institution"
							escape="false" />
					</td>
					<td align="right">Valid Through:<s:property
							value="quotationPrintDTO.expDate" />
					</td>
				</tr>
			</table>
			<table width="800px" border="0" cellspacing="0" cellpadding="0"
				class="tableBorder2">
				<tr>
					<td width="20%" align="center">Quote No.</td>
					<td width="20%" align="center">Currency</td>
					<td width="26%" align="center">Estimated Turnaround Time</td>
					<td width="14%" align="center">Terms</td>
					<td width="20%" align="center">Ship Via</td>
				</tr>
				<tr>
					<td align="center"><s:property value="quotationPrintDTO.qId" />
					</td>
					<td align="center"><s:property value="quotationPrintDTO.name" />
						(<s:property value="quotationPrintDTO.symbol" />)</td>
					<td align="center"><s:property
							value="quotationPrintDTO.turnAround" />
					</td>
					<td align="center">Net 30</td>
					<td align="center"><s:property
							value="quotationPrintDTO.shipVia" />
					</td>
				</tr>
				<tr>
					<td colspan="5" align="left">Item Details:</td>
				</tr>
				<s:property value="quotationPrintDTO.detail" escape="false" />
			</table>
			</div>
		</s:if>
		<s:if test='editFlg!=null&&editFlg.equals("Y")'>
			<div style="width: 800px; margin: 200px auto;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center"><input name="Submit2" type="submit"
							class="style_botton" value="Print" /> <input name="Submit3"
							type="button" class="style_botton" value="Close"
							onclick="javascript:parent.$('#printOrderQuDialog').dialog('close');" />
						</td>
					</tr>
				</table>
			</div>
		</s:if>
	</s:form>
</body>
</html>
