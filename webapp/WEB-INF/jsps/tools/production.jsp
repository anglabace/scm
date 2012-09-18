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
					<th width="255">System Name	</th>
						<th width="420">Entry </th>
						<th width="323">Description	</th>
				</tr>
			</table>
			<table width="998" border="0" cellspacing="0" cellpadding="0"
				class="tableurl" id="table_new">

				<tr>
					<td width="283">My Request/Internal Quotation</td>
					<td width="419"><a
						href="https://www.genscript.com/ssl-bin/my_request"
						target="_blank">https://www.genscript.com/ssl-bin/my_request</a>
					</td>
					<td width="296">&nbsp;</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/pre_quote?op=search_pre_quote"
						target="_blank">https://www.genscript.com/ssl-bin/pre_quote?op=search_pre_quote</a>
					</td>

					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Antibody Product Management System</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/antibody_project/antibody_project.cgi"
						target="_blank">https://www.genscript.com/ssl-bin/antibody_project/antibody_project.cgi</a><br />
					</td>
					<td>This system is to save antibody product research data,
						there is some hard code privilege control</td>
				</tr>

				<tr>
					<td>Antibody Elisa report(link is in Antibody Service
						Management system)</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/site2/report/antibody_elisa.cgi"
						target="_blank">https://www.genscript.com/ssl-bin/site2/report/antibody_elisa.cgi</a><br />
					</td>
					<td>For antibody department to generate Elisa report,
						different catalog has different template</td>
				</tr>
				<tr>
					<td>Search MGC</td>

					<td><a
						href="http://www.genscript.com/cgi-bin/site2/search_refseq_mgc.pl"
						target="_blank">http://www.genscript.com/cgi-bin/site2/search_refseq_mgc.pl</a><br />
					</td>
					<td>For gene department to search mgc clone information</td>
				</tr>
			
				<tr>
					<td>Oligo Order Process</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/site2/oligo_order_process.cgi"
						target="_blank">https://www.genscript.com/ssl-bin/site2/oligo_order_process.cgi</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Sequencing Plasmid Backup</td>

					<td><a href="http://10.168.2.9/sysgrp/plasmid_backup.cgi"
						target="_blank">http://10.168.2.9/sysgrp/plasmid_backup.cgi</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Create Protein COA File/Label</td>
					<td><a
						href="https://192.168.1.22/ssl-bin/supply/coa_generated_protein.cgi"
						target="_blank">https://192.168.1.22/ssl-bin/supply/coa_generated_protein.cgi</a>
					</td>
					<td>&nbsp;</td>

				</tr>
				<tr>
					<td>Create Gene COA File</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/supply/coa_generated.cgi"
						target="_blank">https://www.genscript.com/ssl-bin/supply/coa_generated.cgi</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Create Gene MAP File</td>

					<td><a
						href="http://www.genscript.com/ssl-bin/supply/map_generated.cgi"
						target="_blank">www.genscript.com/ssl-bin/supply/map_generated.cgi</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Peptide form download</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/supply/peptide_excel"
						target="_blank">https://www.genscript.com/ssl-bin/supply/peptide_excel</a><br />
					</td>
					<td>For peptide department generate operation table, qc
						table, sequence file, peptide synthesis doc</td>

				</tr>
				<tr>
					<td>Retrieve Peptide Info</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/supply/peptide_info"
						target="_blank">https://www.genscript.com/ssl-bin/supply/peptide_info</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Batch Update Peptide Qty/Theoretical MW (Peptide)<br />

						Batch Update Peptide Purity (Peptide QC)<br /> Batch Update
						Peptide Purity/Theoretical MW (GL)</td>
					<td><a
						href="https://192.168.1.22/ssl-bin/supply/updatepeptide_batch?department=peptide"
						target="_blank">https://192.168.1.22/ssl-bin/supply/updatepeptide_batch?department=peptide</a><br />
					<a
						href="https://www.genscript.com/ssl-bin/supply/updatepeptide_batch?department=qc"
						target="_blank">https://www.genscript.com/ssl-bin/supply/updatepeptide_batch?department=qc</a><br />
					<a
						href="https://www.genscript.com/ssl-bin/supply/updatepeptide_batch?company=GL"
						target="_blank">https://www.genscript.com/ssl-bin/supply/updatepeptide_batch?company=GL</a>
					</td>
					<td>For peptide production, GL and qc to upload peptide
						production data</td>
				</tr>
				<tr>
					<td>Create Peptide COA File</td>

					<td><a
						href="https://192.168.1.22/ssl-bin/supply/coa_generated_peptide.cgi"
						target="_blank">https://192.168.1.22/ssl-bin/supply/coa_generated_peptide.cgi</a>
					</td>
					<td>&nbsp;</td>
				</tr>
			

				<tr>
					<td>Key Project Management System</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/nj_spel_proj/project_management_list"
						target="_blank">https://www.genscript.com/ssl-bin/nj_spel_proj/project_management_list</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>My Request/Internal Quotation</td>

					<td><a href="https://www.genscript.com/ssl-bin/my_request"
						target="_blank">https://www.genscript.com/ssl-bin/my_request</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Manufacture Process Record System</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/site2/manufacture_process.cgi"
						target="_blank">https://www.genscript.com/ssl-bin/site2/manufacture_process.cgi</a>
					</td>
					<td>&nbsp;</td>

				</tr>
				<tr>
					<td>Gene analysis tool</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/app/gene_analysis_new.cgi"
						target="_blank">https://www.genscript.com/ssl-bin/app/gene_analysis_new.cgi</a>
					</td>
					<td>&nbsp;</td>

				</tr>
				<tr>
					<td>Primer Designer</td>
					<td><a href="https://www.genscript.com/ssl-bin/app/primer"
						target="_blank">https://www.genscript.com/ssl-bin/app/primer</a><br />
						<a
						href="http://www.genscript.com/cgi-bin/tools/sequencing_primer_design"
						target="_blank">http://www.genscript.com/cgi-bin/tools/sequencing_primer_design</a>
					</td>
					<td>&nbsp;</td>

				</tr>

<tr>
					<td>Antigen design tool</td>
					<td><a href="http://web.expasy.org/compute_pi/">http://web.expasy.org/compute_pi/</a>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>PI/MW</td>
					<td><a
						href="https://10.168.2.5/ssl-bin/app/epitope_prediction">https://10.168.2.5/ssl-bin/app/epitope_prediction</a>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>Assign Supply Manager for Peptide</td>
					<td><a
						href="https://10.168.2.225/ssl-bin/order_process/updatesupplymanager?fromlist=1&type=peptide"
						target="_blank">https://10.168.2.225/ssl-bin/order_process/updatesupplymanager?fromlist=1&type=peptide</a>
					</td>

					<td>&nbsp;Assign Supply Manager for Peptide</td>
				</tr>
				<tr>
					<td>Peptide Order Manager</td>
					<td><a
						href="https://10.168.2.225/ssl-bin/site2/peptide_order_manager"
						target="_blank">https://10.168.2.225/ssl-bin/site2/peptide_order_manager</a>
					</td>
					<td>&nbsp;Peptide Order Manager</td>
				</tr>
				<tr>
					<td>Make Label(Peptide)</td>
					<td><a
						href="https://10.168.2.225/ssl-bin/site2/make_label_peptide.pl"
						target="_blank">https://10.168.2.225/ssl-bin/site2/make_label_peptide.pl</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Peptide Uploading Rule</td>
					<td><a href="https://10.168.2.225/ssl-bin/supply/pepdocrule"
						target="_blank">https://10.168.2.225/ssl-bin/supply/pepdocrule</a>
					</td>

					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Peptide Property Calculator</td>
					<td><a
						href="https://10.168.2.225/ssl-bin/site2/peptide_calculation.cgi"
						target="_blank">https://10.168.2.225/ssl-bin/site2/peptide_calculation.cgi</a>
					</td>

					<td>&nbsp;</td>
				</tr>	 
				<tr>
					<td>Order Delivery Status</td>
					<td><a
						href="https://10.168.2.225/www/index.php"
						target="_blank">https://10.168.2.225/www/index.php</a>
					</td>

					<td>&nbsp;</td>
				</tr>
			</table>
		</div>

	</div>


	</div>
</body>
</html>