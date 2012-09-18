<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sales</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script>
	$(function() {
		$("#table_new tr").each(function(i) {
			this.style.backgroundColor = [ "#ffffff", "#eee" ][i % 2];
		});

	});
</script>
</head>
<body class="content" style="background-image: none;">
	<div class="scm">
		<div class="title_content">
			<div class="title">Sales</div>
		</div>
		<div class="input_box">
			<table width="998" border="0" cellspacing="0" cellpadding="0"
				class="tableurl">
				<tr>
					<th width="150">System Name</th>
					<th width="348">Entry</th>
					<th width="">Description</th>

				</tr>
			</table>
			<table width="998" border="0" cellspacing="0" cellpadding="0"
				class="tableurl" id="table_new">
				<tr>
					<td width="150"><a href="tools!Mysearch.action" target="_self">Order
							Followup</a>
					</td>
					<td width="348"></td>
					<td width="">Follow up the order change,manufacturing and
						shipping status</td>

				</tr>

				<tr>
					<td width="150">Codon Usage Format Transform</td>
					<td width="348"><a
						href="www.genscript.com/ssl-bin/app/codon_usage_format.pl"
						target="_blank">www.genscript.com/ssl-bin/app/codon_usage_format.pl
					</a>
					</td>
					<td width="">Gene Analysis Tool</td>

				</tr>
				<tr>
					<td>BLAST Two Sequence</td>
					<td><a href="www.genscript.com/ssl-bin/blast_seq"
						target="_blank">www.genscript.com/ssl-bin/blast_seq </a>
					</td>
					<td>Codon Usage Format Transform tool</td>

				</tr>
				<tr>
					<td>Peptide Property Calculator</td>
					<td><a
						href="www.genscript.com/ssl-bin/site2/peptide_calculation.cgi"
						target="_blank">www.genscript.com/ssl-bin/site2/peptide_calculation.cgi
					</a>
					</td>
					<td>Pairwise BLAST alignment</td>

				</tr>
				<tr>
					<td>Codon Optimization</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/app/codon_opt10"
						target="_blank">https://www.genscript.com/ssl-bin/app/codon_opt10
					</a>
					</td>
					<td>Property Calculator Codon Tool</td>

				</tr>
				<tr>
					<td>BLAST Search</td>
					<td><a href="https://www.genscript.com/ssl-bin/blast_gc"
						target="_blank">https://www.genscript.com/ssl-bin/blast_gc</a>
					</td>
					<td>Blast search tool against mgc, synthesized gene db…</td>

				</tr>
				<tr>
					<td>Shipment Tracking</td>
					<td><a
						href="www.genscript.com/ssl-bin/shipjob/shipment_tracking.cgi"
						target="_blank">www.genscript.com/ssl-bin/shipjob/shipment_tracking.cgi</a>
					</td>
					<td>Tracking package shipment</td>
				</tr>

				<tr>
					<td>Customer Manager</td>
					<td><a
						href="www.genscript.com/ssl-bin/customer_contact/customermanager"
						target="_blank">www.genscript.com/ssl-bin/customer_contact/customermanager</a>
					</td>
					<td>Add New Customer, Add Invalid Emails, Unsubscribe/Update
						Email, Not assigned Account List, Shipping Address Change Log</td>

				</tr>

				<tr>
					<td>My Request/Internal Quotation</td>
					<td><a href="www.genscript.com/ssl-bin/my_request"
						target="_blank">www.genscript.com/ssl-bin/my_request</a>
					</td>
					<td>Communication tool for sales and production for difficult
						cases</td>

				</tr>
				<tr>
					<td>Order Material Management</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/supply/material_shipping.cgi"
						target="_blank">https://www.genscript.com/ssl-bin/supply/material_shipping.cgi</a>
					</td>
					<td>Order Material tracking tool</td>

				</tr>
				<tr>
					<td>Secure Information</td>
					<td><a href="www.genscript.com/ssl-bin/fetch_info"
						target="_blank">www.genscript.com/ssl-bin/fetch_info</a>
					</td>
					<td>Backend interface to manage Secure Messaging System from
						our external website</td>

				</tr>
				<tr>
					<td>IGene Synthesis Bps calculation Tool</td>
					<td><a
						href="www.genscript.com/ssl-bin/site2/bps_calculation.cgi"
						target="_blank">www.genscript.com/ssl-bin/site2/bps_calculation.cgi</a>
					</td>
					<td>A tool for sales to calculate all gene synthesis bps for
						ceratin domains.</td>

				</tr>
			 
				<tr>
					<td>Communication Tool</td>
					<td><a href="www.genscript.com/ssl-bin/site2/email.cgi"
						target="_blank">www.genscript.com/ssl-bin/site2/email.cgi</a>
					</td>
					<td>A tool to send emails, subject will be attached with to
						full name if one email is sent to mutiple receivers</td>

				</tr>
				<tr>
					<td>Peptide Lead</td>
					<td><a
						href="www.genscript.com/ssl-bin/site2/gs_peptide_lead.cgi"
						target="_blank">www.genscript.com/ssl-bin/site2/gs_peptide_lead.cgi</a>
					</td>
					<td>&nbsp;</td>

				</tr>
				<tr>
					<td>Search Email</td>
					<td><a href="www.genscript.com/ssl-bin/update_email"
						target="_blank">www.genscript.com/ssl-bin/update_email</a>
					</td>
					<td>Search customer contact history</td>

				</tr>
				<tr>
					<td>CC Payment Unblock</td>
					<td><a
						href="www.genscript.com/ssl-bin/invoice/cc_payment_unblock"
						target="_blank">www.genscript.com/ssl-bin/invoice/cc_payment_unblock</a>
					</td>
					<td>Block/unblock credit card payment for customer account</td>

				</tr>
	 
				<tr>
					<td>Time Management System</td>
					<td><a href="https://www.genscript.com/ssl-bin/employee/gtm"
						target="_blank">https://www.genscript.com/ssl-bin/employee/gtm</a>
					</td>
					<td>&nbsp;</td>

				</tr>
				<tr>
					<td>Mailflow</td>
					<td><a href="http://www.genscript.com:8686/mailflow"
						target="_blank">http://www.genscript.com:8686/mailflow</a>
					</td>
					<td>&nbsp;</td>

				</tr>
				<tr>
					<td>FAQ system</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/site2/faq/myquestion.cgi"
						target="_blank">https://www.genscript.com/ssl-bin/site2/faq/myquestion.cgi</a>
					</td>
					<td>Faq record by service types…</td>

				</tr>
				<tr>
					<td>Phone tracking system</td>
					<td><a href="https://www.genscript.com/webcall/bin/login.cgi"
						target="_blank">https://www.genscript.com/webcall/bin/login.cgi</a>
					</td>
					<td>Record phone calls (not connected to any hardware, records
						are manually entered)</td>

				</tr>
		 
			</table>
		</div>

	</div>


</body>
</html>
