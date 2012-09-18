<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Production</title>
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
			<div class="title">Production</div>
		</div>
		<div class="input_box">
			<table width="998" border="0" cellspacing="0" cellpadding="0"
				class="tableurl">
				<tr>
					<th>System Name</th>
					<th width="348">Entry</th>
					<th width="242">Description</th>
				</tr>
			</table>
			<table width="998" border="0" cellspacing="0" cellpadding="0"
				class="tableurl" id="table_new">
		
				<tr>
					<td>&nbsp;&nbsp;&nbsp;Antibody Product
						Management System</td>
					<td width="348"><a
						href="https://www.genscript.com/ssl-bin/antibody_project/antibody_project.cgi"
						target="_blank">https://www.genscript.com/ssl-bin/antibody_project/antibody_project.cgi</a>
					</td>
					<td  width="242">This system is to save antibody product research data,
						there is some hard code privilege control</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;Antibody Service
						Management System</td>
					<td><a
						href="https://www.genscript.com/antibody_supply_manager"
						target="_blank">https://www.genscript.com/antibody_supply_manager</a>
					</td>
					<td>For antibody order production, schedule can be set and
						tracked, and every work group can see their own working list</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;Antibody Make Label
						(link is in Antibody Service Management system)</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/site2/make_label_antibody.pl"
						target="_blank">https://www.genscript.com/ssl-bin/site2/make_label_antibody.pl</a>
					</td>
					<td>For antibody to print labels, the difficult part is for
						different catalog, need to print different size of labels, can
						send generated label to corresponding person</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;Antibody Elisa
						report(link is in Antibody Service Management system)</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/site2/report/antibody_elisa.cgi"
						target="_blank">https://www.genscript.com/ssl-bin/site2/report/antibody_elisa.cgi</a>
					</td>
					<td>For antibody department to generate Elisa report,
						different catalog has different template</td>
				</tr>
			<!-- 	 class="tableurl_tdb" -->
				<tr>
					<td>&nbsp;&nbsp;&nbsp;Gene Make Label
						(Already written?)</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/supply/make_label"
						target="_blank">https://www.genscript.com/ssl-bin/supply/make_label</a>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;Oligo Order Process</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/site2/oligo_order_process.cgi"
						target="_blank">https://www.genscript.com/ssl-bin/site2/oligo_order_process.cgi</a>
					</td>
					<td></td>
				</tr>

				<tr>
					<td>&nbsp;&nbsp;&nbsp;Peptide form
						download</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/supply/peptide_excel"
						target="_blank">https://www.genscript.com/ssl-bin/supply/peptide_excel</a>
					</td>
					<td></td>
				</tr>

				<tr>
					<td>&nbsp;&nbsp;&nbsp;Retrieve Peptide
						Info</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/supply/peptide_info"
						target="_blank">https://www.genscript.com/ssl-bin/supply/peptide_info</a>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;Create Peptide COA
						File</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/supply/coa_generated_peptide.cgi"
						target="_blank">https://www.genscript.com/ssl-bin/supply/coa_generated_peptide.cgi</a>
					</td>
					<td></td>
				</tr>

				<tr>
					<td>&nbsp;&nbsp;&nbsp;Peptide Make Label</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/site2/make_label_peptide.pl"
						target="_blank">https://www.genscript.com/ssl-bin/site2/make_label_peptide.pl</a>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;Peptide Order
						Manager</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/site2/peptide_order_manager"
						target="_blank">https://www.genscript.com/ssl-bin/site2/peptide_order_manager</a>
					</td>
					<td></td>
				</tr>
			
				<tr>
					<td>My Request/Internal Quotation</td>
					<td><a href="https://www.genscript.com/ssl-bin/my_request"
						target="_blank">https://www.genscript.com/ssl-bin/my_request</a>
					</td>
					<td>Monitor cost of pre quote</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/pre_quote?op=search_pre_quote"
						target="_blank">https://www.genscript.com/ssl-bin/pre_quote?op=search_pre_quote</a>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
