<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Print Quotation Information</title>
		<base id="myBaseId" href="${global_url}" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet"
			type="text/css" />
		<script src="${global_js_url}select.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
		<s:include value="quote_config.jsp"></s:include>
		<style type="text/css">
<!--
body {
	width: 640px;
	padding: 10px 0px 0px 10px;
}  
-->
</style>
	</head>
<script type="text/javascript">
$(document).ready(function(){
	$( '#printSave' ).click(
		function()
		{
		var printForm=document.getElementById("printForm");
		printForm.submit();
		}
	) ;
	
	$( '#print' ).click(
		function()
		{
			window.print(); 
		}
	) ;
	
	$( '#printCancel' ).click(
		function()
		{
			parent.window.$( '#printDialog' ).dialog( 'close' ) ;
			parent.window.$( '#viewOrderDialog' ).dialog( 'close' ) ;
		}
	) ;
}) ;
</script>
	<body>
	<form name="printForm" id="printForm" action="quote_extra!convertFile.action" method="post">
		<div id="quorderBody">
			<img src="images/doc_top.jpg" width="640" height="51" />
			<table width="602" id="quorderBody" border="0" cellpadding="0"
				cellspacing="0" class="General_table"
				style="padding: 10px; margin: 0px auto;">
				<tr>
					<td>
						<div align="center" class="blue_price">
							Project Proposal
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="23%">
									Quote No:
								</th>
								<td width="77%">
									${quote.quoteNo}
								</td>
							</tr>
							<tr>
								<th>
									Quote Date:
								</th>
								<td>
									 <s:date name="quote.quoteDate" format="yyyy-MM-dd" />
								</td>
							</tr>
							<tr>
								<th>
									Expire Date:
								</th>
								<td>
								   <s:date name="quote.exprDate" format="yyyy-MM-dd" />
								</td>
							</tr>
							<tr>
								<th>
									Prepared Date:
								</th>
								<td>
								   <s:date name="quote.exchRateDate" format="yyyy-MM-dd" />
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<th valign="top">
									Prepared For:
								</th>
								<td>
									<p>
										Tusuya Taneda
										<br />
										taneda@tainestu.com
									</p>
									<p>
										Thermostable Enzyme Laboratory Co.,Ltd
										<br />
										Hyogo
										<br />
										japan
									</p>
								</td>
							</tr>
							<tr>
								<th>
									......
								</th>
								<td>
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
			<table width="602" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<div class="botton_box">
							<input type="hidden" name="sessQuoteNo" id="sessQuoteNo" value=${quote.quoteNo} />
							<input type="button" name="printSave" id="printSave"
								class="style_botton" value="Save" />
							<input type="button" name="print" id="print" class="style_botton"
								value="Print" />
							<input type="button" name="printCancel" id="printCancel"
								class="style_botton" value="Cancel" />
							<input type="hidden" name="quote.quoteNo"
								value="${quote.quoteNo}" />
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
