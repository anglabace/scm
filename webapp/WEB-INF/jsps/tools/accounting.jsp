<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Accounting</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />


<script language="javascript" type="text/javascript"
	src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script>
	$(function() {
		$("#table_new tr").each(function(i) {

			this.style.backgroundColor = [ "#ffffff", "#eee" ][i % 2]
		})

	})
</script>


</head>

<body class="content" style="background-image: none;">





	<div class="scm">
		<div class="title_content">
			<div class="title">Accounting</div>
		</div>
		<div class="input_box">
			<table width="998" border="0" cellspacing="0" cellpadding="0"
				class="tableurl">
				<tr>
					<th width="210">System Name</th>
					<th width="395">Entry</th>
					<th>Description</th>
				</tr>
			</table>
			<table width="998" border="0" cellspacing="0" cellpadding="0"
				class="tableurl" id="table_new">
				<tr>
					<td width="210"><a
						href="${ctx}/customer/cust_credit_card!cardChanger.action"
						target="_self">Credit Card Charger</a></td>
					<td width="395"></td>
					<td>&nbsp;Charge or refund to customer's credit card</td>
				</tr>
				<tr>
					<td width="210"><a
						href="${ctx}/tools/customer_invoice!list.action"
						target="_self">Customer Credit Monitor</a></td>
					<td width="395"></td>
					<td>Create customer blacklist or watching list and adjust
						credit status</td>
				</tr>
				<tr>
					<td width="210" class="tableurl_tda">Invoice Manager</td>
					<td width="395"><a
						href="www.genscript.com/ssl-bin/invoice/invoicemanager"
						target="_blank">www.genscript.com/ssl-bin/invoice/invoicemanager</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">Create Invoices Based on Shippment</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/invoice/invoicejob"
						target="_blank">https://www.genscript.com/ssl-bin/invoice/invoicejob</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">Bad Debt Manager</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/invoice/baddebt"
						target="_blank">https://www.genscript.com/ssl-bin/invoice/baddebt</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">Wire Manager</td>
					<td><a href="https://www.genscript.com/ssl-bin/wiremanager"
						target="_blank">https://www.genscript.com/ssl-bin/wiremanager</a>
					</td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td class="tableurl_tdb">Check Manager</td>
					<td><a href="https://www.genscript.com/ssl-bin/checkmanager"
						target="_blank">https://www.genscript.com/ssl-bin/checkmanager</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">Payment Manager</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/invoice/paymentmanager"
						target="_blank">https://www.genscript.com/ssl-bin/invoice/paymentmanager</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">Wire check manager query</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/invoice/wire_check_manager_query.cgi"
						target="_blank">https://www.genscript.com/ssl-bin/invoice/wire_check_manager_query.cgi</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">Show Overdue invoice</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/invoice/showoverdue_invoice"
						target="_blank">https://www.genscript.com/ssl-bin/invoice/showoverdue_invoice</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">Check invoices/account receivable</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/rbcd/checkorder"
						target="_blank">https://www.genscript.com/ssl-bin/rbcd/checkorder</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">Tax exempt accounts</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/invoice/tax_exempt_accounts"
						target="_blank">https://www.genscript.com/ssl-bin/invoice/tax_exempt_accounts</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">CC Payment Unblock</td>
					<td><a
						href="www.genscript.com/ssl-bin/invoice/cc_payment_unblock"
						target="_blank">www.genscript.com/ssl-bin/invoice/cc_payment_unblock</a>
					</td>
					<td>Block/unblock credit card payment for customer account</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">Institute Black List</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/invoice/institution_black_list"
						target="_blank">https://www.genscript.com/ssl-bin/invoice/institution_black_list</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">Cost Manager</td>
					<td><a href="www.genscript.com/ssl-bin/invoice/costmanager"
						target="_blank">www.genscript.com/ssl-bin/invoice/costmanager</a>
					</td>
					<td>To manager cost of our suppliers, saved at po.paid_cost</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">Tax Manager</td>
					<td><a
						href="www.genscript.com/ssl-bin/order_process/tax_manager.cgi"
						target="_blank">www.genscript.com/ssl-bin/order_process/tax_manager.cgi</a>
					</td>
					<td>To manager orders with tax, saved at po.paid_tax</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">FastCharge credit card transaction</td>
					<td><a href="www.genscript.com/ssl-bin/chargemanager?cc=1"
						target="_blank">www.genscript.com/ssl-bin/chargemanager?cc=1</a></td>
					<td>www.genscript.com/ssl-bin/chargemanager?cc=1</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">Royalty Manager</td>
					<td><a
						href="www.genscript.com/ssl-bin/invoice/royalty_manager"
						target="_blank">www.genscript.com/ssl-bin/invoice/royalty_manager</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="tableurl_tdc">Invoice Data to QuickBook</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/rbcd/invoicelist"
						target="_blank">https://www.genscript.com/ssl-bin/rbcd/invoicelist</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Shipment Tracking</td>
					<td><a
						href="www.genscript.com/ssl-bin/shipjob/shipment_tracking.cgi"
						target="_blank">www.genscript.com/ssl-bin/shipjob/shipment_tracking.cgi</a>
					</td>
					<td>Tracking package shipment</td>
				</tr>






			</table>
		</div>

	</div>

</body>
</html>
