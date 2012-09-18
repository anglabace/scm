<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}TabbedPanels.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}show_tag.js"></script>

<script>
	function aCheck1() {

		document.getElementById("yes").style.display = "block";

		document.getElementById("no").style.display = "none";

	}

	function bCheck2() {

		document.getElementById("yes").style.display = "none";

		document.getElementById("no").style.display = "block";

	}

	function cCheck1() {

		document.getElementById("other").style.display = "none";

		document.getElementById("puc").style.display = "block";

	}

	function dCheck1() {

		document.getElementById("other").style.display = "block";

		document.getElementById("puc").style.display = "none";

	}

	function usage1() {

		document.getElementById("usage").style.display = "block";

		document.getElementById("promo").style.display = "none";

	}

	function usage2() {

		document.getElementById("usage").style.display = "none";

		document.getElementById("promo").style.display = "block";

	}

	function usage3() {

		document.getElementById("usage").style.display = "none";

		document.getElementById("promo").style.display = "block";

	}

	function usage4() {

		document.getElementById("usage").style.display = "none";

		document.getElementById("promo").style.display = "block";

	}
	function usage5() {

		document.getElementById("usage").style.display = "none";

		document.getElementById("promo").style.display = "block";

	}

	function usage6() {

		document.getElementById("usage").style.display = "none";

		document.getElementById("promo").style.display = "block";

	}

	function usage7() {

		document.getElementById("usage").style.display = "none";

		document.getElementById("promo").style.display = "block";

	}

	$(function() {

		$("#oligourl").toggle(function() {

			$("#oligoa").hide();

		},

		function() {
			;
			$("#oligoa").show();

		});

	});
</script>



<style type="text/css">
<!--
body {
	width: 930px;
}

.hidlayer1 {
	font-size: 12px;
	position: absolute;
	z-index: 9999;
	left: 20%;
	top: 20%;
	display: none;
	height: 200px;
	width: 200px;
}
-->
</style>


</head>

<body>
	<div id="dhtmlgoodies_tabView1">
		<div class="dhtmlgoodies_aTab">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="General_table" style="margin-bottom: 0px;"> 
				<tr> 
					<th width="17%"><span class="important">*</span>Oligo Name</th>

					<td width="22%"><input name="textfield" type="text"
						class="NFText" size="25" /></td>

					<th width="27%">Aliquoting into</th>

					<td width="34%"><input name="textfield2" type="text"
						class="NFText" value="1" size="25" /></td>

				</tr>

				<tr>

					<th><span class="important">*</span>Backbone</th>

					<td><select name="select2" style="width: 157px">

							<option value="1">DNA</option>

							<option selected="selected" value="2">Phosphorothioated
								DNA</option>

					</select></td>

					<th>&nbsp;</th>

					<td>tubes(Extra $0.2/tube for aliquoting into over 5 Tubes.)</td>

				</tr>

				<tr>

					<th><span class="important">*</span>Purification</th>

					<td><select name="select8" style="width: 157px">

							<option selected="selected" value="7">Desalt</option>

							<option value="8">RPC</option>

							<option value="9">PAGE</option>

							<option value="10">HPLC</option>

					</select></td>

					<th><span class="important">*</span>Synthesis Scales</th>

					<td><select name="select5" style="width: 157px">

							<option selected="selected" value="price_50nm">50 nmol</option>

							<option value="price_100nm">100 nmol</option>

							<option value="price_200nm">200 nmol</option>

							<option value="price_1um">1 µmol</option>

							<option value="price_5um">5 µmol</option>

							<option value="price_10um">10 µmol</option>

					</select></td>

				</tr>
				<tr>
					<td colspan="3"></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">Note:For Special base refer to <a
						href="http://qa.genscript.com/oligo_codes.html" target="_blank">GenScript's
							defined codes</a></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<th><span class="important">*</span>Sequence</th>
					<td colspan="3"><textarea name="textarea"
							class="content_textarea2"></textarea></td>
				</tr>
				<tr>
					<th>&nbsp;</th>
					<td colspan="3"><a id="oligourl" href="javascript:void(0)">Click
							to hide the modification menus</a></td>
				</tr>
			</table>

			<div id="oligoa" class="oligo">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="General_table" style="margin-top: 0px; margin-bottom: 0px;">
					<tr>
						<th width="17%" valign="top">&nbsp;</th>
						<td width="83%" colspan="3">Please click to add the selected
							chimeric bases,mixed bases and modifications into the sequence.</td>
					</tr>
					<tr>
						<th valign="top">&nbsp;</th>
						<td colspan="3"><table border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td width="148">5' Modification</td>
									<td width="143">Internal Modification</td>
									<td width="394">3' Modification</td>
								</tr>
								<tr>
									<td><select onclick="addExtra(this,'front');" size="5"
										name="modification_5" style="width: 150px; height: 100px;">
											<option value="0">Fluorescent labeling</option>

											<option value="/{5'CY3}/">5' CY3</option>

											<option value="/{5'CY5}/">5' CY5</option>

											<option value="/{5'FAM}/">5' FAM</option>

											<option value="/{5'HEX}/">5' HEX</option>

											<option value="/{5'6JOE}/">5' 6JOE</option>

											<option value="/{5'ROX}/">5' ROX</option>

											<option value="/{5'TAMRA}/">5' TAMRA</option>

											<option value="/{5'TET}/">5' TET</option>

											<option value="0">Non-Fluorescent conjugates</option>

											<option value="/{5'Biotin}/">5' Biotin</option>

											<option value="/{5'BiotinTEG}/">5' Biotin TEG</option>

											<option value="/{5'DualBiotin}/">5' Dual Biotin</option>

											<option value="/{5'BiotindT}/">5' Biotin dT</option>

											<option value="/{5'PcBiotin}/">5' PC Biotin</option>

											<option value="/{5'Digoxin}/">5' Digoxin</option>

											<option value="0">Terminal Phosphorylation</option>

											<option value="/{5'Phosphate}/">5' Phosphorylation</option>

											<option value="0">Linkers</option>

											<option value="/{5'AminoC3}/">5' Amino Linker C3</option>

											<option value="/{5'AminoC6}/">5' Amino Linker C6</option>

											<option value="/{5'AminoC7}/">5' Amino Linker C7</option>

											<option value="/{5'AminoC12}/">5' Amino Linker C12</option>

											<option value="/{5' AminoC6dT}/">5' Amino Linker C6
												dT</option>

											<option value="/{5'C3 S-S}/">5' Disulfide Linker C3</option>

											<option value="/{5'C6 S-S}/">5' Disulfide Linker C6</option>

											<option value="0">Spachers</option>

											<option value="/{5'SpacerC3}/">5' Spacer C3</option>

											<option value="/{5'SpacerC6}/">5' Spacer C6</option>

											<option value="/{5'Spacer9}/">5' Spacer 9</option>

											<option value="/{5'Spacer18}/">5' Spacer 18</option>

											<option value="/{5'dSpacer}/">5' dSpacer</option>

											<option value="0">Modified bases</option>

											<option value="/{5'deoxyI}/">5' DeoxyInosine</option>

											<option value="/{5'deoxyU}/">5' DeoxyUridine</option>

											<option value="/{5'fA}/">5' 2'-Fluoro A</option>

											<option value="/{5'fC}/">5' 2'-Fluoro C</option>

											<option value="/{5'fG}/">5' 2'-Fluoro G</option>

											<option value="/{5'fU}/">5' 2'-Fluoro U</option>

											<option value="0">Electrochemicals</option>

											<option value="/{5' Ferrocene }/">5' Ferrocene</option>

									</select></td>

									<td><select onclick="addExtra(this,'center');" size="5"
										name="modification_in" style="width: 150px; height: 100px;">

											<option value="0">Fluorescent labeling</option>
											<option value="/{iCY3}/">Internal CY3</option>
											<option value="/{iCY5}/">Internal CY5</option>
											<option value="/{iFAM}/">Internal FAM</option>
											<option value="/{iHEX}/">Internal HEX</option>
											<option value="/{i6JOE}/">Internal 6JOE</option>
											<option value="/{iROX}}/">Internal ROX</option>
											<option value="/{iTAMRA}/">Internal TAMRA</option>
											<option value="/{iTET}/">Internal TET</option>
											<option value="0eqe">Non-Fluorescent conjugates</option>
											<option value="/{iBiotin}/">Internal Biotin</option>
											<option value="/{iBiotinTEG}/">Internal Biotin TEG</option>
											<option value="/{iDualBiotin}/">Internal Dual Biotin</option>
											<option value="/{iBiotindT}/">Internal Biotin dT</option>
											<option value="/{iPcBiotin}/">Internal PC Biotin</option>
											<option value="/{iDigoxin}/">Internal Digoxin</option>
											<option value="0rrr">Linkers</option>
											<option value="/{iAminoC3}">Internal Amino Linker C3</option>
											<option value="/{iAminoC6}">Internal Amino Linker C6</option>
											<option value="/{iAminoC7}">Internal Amino Linker C7</option>

											<option value="/{iAminoC12}">Internal Amino Linker
												C12</option>
											<option value="/{iAminoC6dT}">Internal Amino Linker
												C6 dT</option>

											<option value="/{iC3 S-S}/">Internal Disulfide
												Linker C3</option>

											<option value="/{iC6 S-S}/">Internal Disulfide
												Linker C6</option>

											<option value="0">Spachers</option>

											<option value="/{iSpacerC3}">Internal Spacer C3</option>


											<option value="/{iSpacerC6}">Internal Spacer C6</option>


											<option value="/{iSpacer9}">Internal Spacer 9</option>

											<option value="/{iSpacer18}">Internal Spacer 18</option>

											<option value="/{idSpacer}">Internal dSpacer</option>

											<option value="0">Modified bases</option>

											<option value="/{ideoxyI}">Internal DeoxyInosine</option>

											<option value="/{ideoxyU}">Internal DeoxyUridine</option>

											<option value="/{ifA}">Internal 2'-Fluoro A</option>

											<option value="/{ifC}">Internal 2'-Fluoro C</option>

											<option value="/{ifG}">Internal 2'-Fluoro G</option>

											<option value="/{ifU}">Internal 2'-Fluoro U</option>

											<option value="0">Electrochemicals</option>

											<option value="/{iFerrocene }/">Internal Ferrocene</option>

									</select></td>

									<td><select onclick="addExtra(this);" size="5"
										name="modification_3" style="width: 150px; height: 100px;">

											<option value="0">Fluorescent labeling</option>

											<option value="/{3'CY3}/">3' CY3</option>

											<option value="/{3'CY5}/">3' CY5</option>

											<option value="/{3'FAM}/">3' FAM</option>

											<option value="/{3'HEX}/">3' HEX</option>

											<option value="/{3'6JOE}/">3' 6JOE</option>

											<option value="/{3'ROX}/">3' ROX</option>

											<option value="/{3'TAMRA}/">3' TAMRA</option>

											<option value="/{3'TET}/">3' TET</option>

											<option value="0">Quenchers</option>

											<option value="/{3'DABCYL}/">3' DABCYL</option>

											<option value="/{3'BHQ 1}/">3' BHQ 1</option>

											<option value="/{3'BHQ 2}/">3' BHQ 2</option>

											<option value="/{3'ECLIPSE}/">3' ECLIPSE</option>

											<option value="0">Non-Fluorescent conjugates</option>

											<option value="/{3'Biotin}/">3' Biotin</option>

											<option value="/{3'BiotinTEG}/">3' Biotin TEG</option>

											<option value="/{3'DualBiotin}/">3' Dual Biotin</option>

											<option value="/{3'BiotindT}/">3' Biotin dT</option>

											<option value="/{3'PcBiotin}/">3' PC Biotin</option>

											<option value="/{3'Digoxin}/">3' Digoxin</option>

											<option value="0">Terminal Phosphorylation</option>

											<option value="/{3'Phosphate}/">3' Phosphorylation</option>

											<option value="0">Linkers</option>

											<option value="/{3'AminoC3}/">3' Amino Linker C3</option>

											<option value="/{3'AminoC6}/">3' Amino Linker C6</option>

											<option value="/{3'AminoC7}/">3' Amino Linker C7</option>

											<option value="/{3'AminoC12}/">3' Amino Linker C12</option>

											<option value="/{3' AminoC6dT}/">3' Amino Linker C6
												dT</option>

											<option value="/{3'C3 S-S}/">3' Disulfide Linker C3</option>

											<option value="/{3'C6 S-S}/">3' Disulfide Linker C6</option>

											<option value="0">Spachers</option>

											<option value="/{3'SpacerC3}/">3' Spacer C3</option>

											<option value="/{3'SpacerC6}/">3' Spacer C6</option>

											<option value="/{3'Spacer9}/">3' Spacer 9</option>

											<option value="/{3'Spacer18}/">3' Spacer 18</option>

											<option value="/{3'dSpacer}/">3' dSpacer</option>

											<option value="0">Modified bases</option>

											<option value="/{5'deoxyI}/">3' DeoxyInosine</option>

											<option value="/{5'deoxyU}/">3' DeoxyUridine</option>

											<option value="/{3'fA}/">3' 2'-Fluoro A</option>

											<option value="/{3'fC}/">3' 2'-Fluoro C</option>

											<option value="/{3'fG}/">3' 2'-Fluoro G</option>

											<option value="/{3'fU}/">3' 2'-Fluoro U</option>

											<option value="0">Electrochemicals</option>

											<option value="/{3' Ferrocene }/">3' Ferrocene</option>

									</select></td>

								</tr>

								<tr>

									<td><input type="checkbox" name="checkbox" id="checkbox" />

										5' Other modification</td>

									<td><input type="checkbox" name="checkbox2" id="checkbox2" />
										Internal other modification</td>

									<td><input type="checkbox" name="checkbox3" id="checkbox3" />
										3' other modification</td>

								</tr>

								<tr>

									<td><textarea disabled="disabled" rows="1"
											name="extra_5_text" style="width: 150px; height: 100px;"></textarea>
									</td>

									<td><textarea disabled="disabled" rows="1"
											name="extra_5_text2" style="width: 150px; height: 100px;"></textarea>
									</td>

									<td><textarea disabled="disabled" rows="1"
											name="extra_5_text3" style="width: 150px; height: 100px;"></textarea>
									</td>

								</tr>

								<tr>

									<td>Chimeric Bases</td>

									<td>Standard Mixed Bases</td>

									<td>Custom Mixed Bases</td>

								</tr>

								<tr>

									<td rowspan="2"><select onclick="addExtra(this,'center');"
										size="5" name="chimeric_base"
										style="width: 150px; height: 100px;">

											<option value="0">DNA</option>

											<option value="/[dA]">-base A (dA)</option>

											<option value="/[dC]">-base C (dC)</option>

											<option value="/[dG]">-base G (dG)</option>

											<option value="/[dT]">-base T (dT)</option>

											<option value="0">Phosphorothioated DNA</option>

											<option value="/[dA*]">-base A (dA*)</option>

											<option value="/[dC*]">-base C (dC*)</option>

											<option value="/[dG*]">-base G (dG*)</option>

											<option value="/[dT*]">-base T (dT*)</option>

											<option value="0">RNA</option>

											<option value="/[rA]">-base A (rA)</option>

											<option value="/[rC]">-base C (rC)</option>

											<option value="/[rG]">-base G (rG)</option>

											<option value="/[rU]">-base U (rU)</option>

											<option value="0">Phosphorothioated RNA</option>

											<option value="/[rA*]">-base A (rA*)</option>

											<option value="/[rC*]">-base C (rC*)</option>

											<option value="/[rG*]">-base G (rG*)</option>

											<option value="/[rU*]">-base U (rU*)</option>

											<option value="0">2'-OMe-RNA</option>

											<option value="/[mA]">-base A (mA)</option>



											<option value="/[mC]">-base C (mC)</option>


											<option value="/[mG]">-base G (mG)</option>



											<option value="/[mU]">-base U (mU)</option>



											<option value="0">Phosphorothioated 2'-OMe-RNA</option>

											<option value="/[mA*]">-base A (mA*)</option>



											<option value="/[mC*]">-base C (mC*)</option>


											<option value="/[mG*]">-base G (mG*)</option>


											<option value="/[mU*]">-base U (mU*)</option>


									</select></td>

									<td rowspan="2"><select onclick="addExtra(this,'center');"
										size="5" name="mixed_base"
										style="width: 150px; height: 100px;">

											<option value="/|N|">N (dA+dG+dC+dT)</option>

											<option value="/|B|">B (dT+dC+dG)</option>


											<option value="/|D|">D (dA+dT+dG)</option>


											<option value="/|H|">H (dA+dT+dC)</option>


											<option value="/|V|">V (dA+dC+dG)</option>


											<option value="/|M|">M (dA+dC)</option>



											<option value="/|R|">R (dA+dG)</option>



											<option value="/|W|">W (dA+dT)</option>

											<option value="/|S|">S (dC+dG)</option>


											<option value="/|Y|">Y (dC+dT)</option>



											<option value="/|K|">K (dT+dG)</option>



									</select></td>

									<td>Please note:An additional charge is applied for hand
										mixing these custom bases.</td>

								</tr>

								<tr>

									<td valign="top"><table border="0" cellspacing="0"
											cellpadding="0">

											<tr>

												<td align="center">dA</td>

												<td align="center">dC</td>

												<td align="center">dG</td>

												<td align="center">dT</td>

											</tr>

											<tr>

												<td><input name="textfield3" type="text" class="NFText"
													size="5" /> %</td>

												<td><input name="textfield7" type="text" class="NFText"
													size="5" /> %</td>

												<td><input name="textfield8" type="text" class="NFText"
													size="5" /> %</td>

												<td><input name="textfield9" type="text" class="NFText"
													size="5" /> %</td>

											</tr>

											<tr>

												<td colspan="4" align="center"><input name="Submit3"
													type="submit" class="style_botton2" value="Use Mixed Base"
													onclick="alert('Please type the correct percent!')" /></td>

											</tr>

										</table></td>

								</tr>

							</table></td>

					</tr>

				</table>

			</div>

			<div class="oligo">

				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="General_table" style="margin-top: 0px;">

					<tr>

						<th width="17%" valign="top">Comment</th>

						<td width="83%" colspan="3"><textarea name="textarea3"
								class="content_textarea2"></textarea></td>

					</tr>

				</table>

			</div>

		</div>
		<div class="dhtmlgoodies_aTab">

			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="General_table">

				<tr>

					<th width="17%">Total number of oligos</th>

					<td width="33%"><input name="textfield11" type="text"
						class="NFText" size="25" /></td>

					<th width="17%">Backbone</th>

					<td width="33%"><select name="select" style="width: 157px">

							<option value="1">DNA</option>

							<option selected="selected" value="2">Phosphorothioated
								DNA</option>

					</select></td>

				</tr>

				<tr>

					<th>Synthesis scales</th>

					<td><select name="select3" style="width: 157px">

							<option selected="selected" value="price_50nm">50 nmol</option>

							<option value="price_100nm">100 nmol</option>

							<option value="price_200nm">200 nmol</option>

							<option value="price_1um">1 µmol</option>

							<option value="price_5um">5 µmol</option>

							<option value="price_10um">10 µmol</option>

					</select></td>

					<th>Purification</th>

					<td><select name="select4" style="width: 157px">

							<option selected="selected" value="7">Desalt</option>

							<option value="8">RPC</option>

							<option value="9">PAGE</option>

							<option value="10">HPLC</option>
					</select></td>

				</tr>

				<tr>

					<td height="60" colspan="4" align="center"><input
						name="Submit2" type="submit" class="style_botton3"
						value="Customize This Form" /></td>

				</tr>



			</table>

		</div>
		<div class="dhtmlgoodies_aTab">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="General_table">
				<tr>
					<td colspan="4"><table width="100%" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="5%">&nbsp;</td>
								<td width="95%">Please choose only ONE kind of method to
									upload your oligos.<br /> You can EDIT each oligo to modify
									backbone/scales/purification/Modification after save.<br />

									For <span class="important">unmodified DNA oligo</span>, we
									only provide large-scale order (at least 96 DNA-Oligos)</td>
							</tr>
						</table></td>
				</tr>
				<tr>
					<th>Backbone</th>
					<td width="31%"><select name="select6" style="width: 157px">
							<option value="1">DNA</option>

							<option selected="selected" value="2">Phosphorothioated
								DNA</option>

					</select></td>

					<th width="19%">Synthesis scales</th>

					<td width="33%"><select name="select9" style="width: 157px">

							<option selected="selected" value="price_50nm">50 nmol</option>

							<option value="price_100nm">100 nmol</option>

							<option value="price_200nm">200 nmol</option>

							<option value="price_1um">1 µmol</option>

							<option value="price_5um">5 µmol</option>

							<option value="price_10um">10 µmol</option>

					</select></td>

				</tr>

				<tr>

					<th>Purification</th>

					<td><select name="select10" style="width: 157px">

							<option selected="selected" value="7">Desalt</option>

							<option value="8">RPC</option>

							<option value="9">PAGE</option>

							<option value="10">HPLC</option>

					</select></td>

					<th>&nbsp;</th>

					<td>&nbsp;</td>

				</tr>

				<tr>

					<th>5' Modification</th>

					<td><select name="select11" style="width: 157px">

							<option selected="selected" value="0">Please select</option>

							<option value="0">Fluorescent labeling</option>

							<option value="/{5'CY3}/">5' CY3</option>

							<option value="/{5'CY5}/">5' CY5</option>

							<option value="/{5'FAM}/">5' FAM</option>

							<option value="/{5'HEX}/">5' HEX</option>

							<option value="/{5'6JOE}/">5' 6JOE</option>

							<option value="/{5'ROX}/">5' ROX</option>

							<option value="/{5'TAMRA}/">5' TAMRA</option>

							<option value="/{5'TET}/">5' TET</option>

							<option value="0">Non-Fluorescent conjugates</option>

							<option value="/{5'Biotin}/">5' Biotin</option>

							<option value="/{5'BiotinTEG}/">5' Biotin TEG</option>

							<option value="/{5'DualBiotin}/">5' Dual Biotin</option>

							<option value="/{5'BiotindT}/">5' Biotin dT</option>

							<option value="/{5'PcBiotin}/">5' PC Biotin</option>

							<option value="/{5'Digoxin}/">5' Digoxin</option>

							<option value="0">Terminal Phosphorylation</option>

							<option value="/{5'Phosphate}/">5' Phosphorylation</option>

							<option value="0">Linkers</option>

							<option value="/{5'AminoC3}/">5' Amino Linker C3</option>

							<option value="/{5'AminoC6}/">5' Amino Linker C6</option>

							<option value="/{5'AminoC7}/">5' Amino Linker C7</option>

							<option value="/{5'AminoC12}/">5' Amino Linker C12</option>

							<option value="/{5' AminoC6dT}/">5' Amino Linker C6 dT</option>

							<option value="/{5'C3 S-S}/">5' Disulfide Linker C3</option>
							<option value="/{5'C6 S-S}/">5' Disulfide Linker C6</option>
							<option value="0">Spachers</option>
							<option value="/{5'SpacerC3}/">5' Spacer C3</option>
							<option value="/{5'SpacerC6}/">5' Spacer C6</option>
							<option value="/{5'Spacer9}/">5' Spacer 9</option>
							<option value="/{5'Spacer18}/">5' Spacer 18</option>
							<option value="/{5'dSpacer}/">5' dSpacer</option>
							<option value="0">Modified bases</option>
							<option value="/{5'deoxyI}/">5' DeoxyInosine</option>
							<option value="/{5'deoxyU}/">5' DeoxyUridine</option>
							<option value="/{5'fA}/">5' 2'-Fluoro A</option>
							<option value="/{5'fC}/">5' 2'-Fluoro C</option>
							<option value="/{5'fG}/">5' 2'-Fluoro G</option>
							<option value="/{5'fU}/">5' 2'-Fluoro U</option>
							<option value="0">Electrochemicals</option>
							<option value="/{5' Ferrocene }/">5' Ferrocene</option>
					</select></td>
					<th>3' Modification</th>
					<td><select name="select12" style="width: 157px">
							<option selected="selected" value="0">Please select</option>
							<option value="0">Fluorescent labeling</option>
							<option value="/{3'CY3}/">3' CY3</option>
							<option value="/{3'CY5}/">3' CY5</option>
							<option value="/{3'FAM}/">3' FAM</option>
							<option value="/{3'HEX}/">3' HEX</option>
							<option value="/{3'6JOE}/">3' 6JOE</option>
							<option value="/{3'ROX}/">3' ROX</option>
							<option value="/{3'TAMRA}/">3' TAMRA</option>
							<option value="/{3'TET}/">3' TET</option>
							<option value="0">Quenchers</option>
							<option value="/{3'DABCYL}/">3' DABCYL</option>
							<option value="/{3'BHQ 1}/">3' BHQ 1</option>
							<option value="/{3'BHQ 2}/">3' BHQ 2</option>
							<option value="/{3'ECLIPSE}/">3' ECLIPSE</option>
							<option value="0">Non-Fluorescent conjugates</option>
							<option value="/{3'Biotin}/">3' Biotin</option>
							<option value="/{3'BiotinTEG}/">3' Biotin TEG</option>
							<option value="/{3'DualBiotin}/">3' Dual Biotin</option>
							<option value="/{3'BiotindT}/">3' Biotin dT</option>
							<option value="/{3'PcBiotin}/">3' PC Biotin</option>
							<option value="/{3'Digoxin}/">3' Digoxin</option>
							<option value="0">Terminal Phosphorylation</option>
							<option value="/{3'Phosphate}/">3' Phosphorylation</option>
							<option value="0">Linkers</option>
							<option value="/{3'AminoC3}/">3' Amino Linker C3</option>
							<option value="/{3'AminoC6}/">3' Amino Linker C6</option>
							<option value="/{3'AminoC7}/">3' Amino Linker C7</option>
							<option value="/{3'AminoC12}/">3' Amino Linker C12</option>
							<option value="/{3' AminoC6dT}/">3' Amino Linker C6 dT</option>
							<option value="/{3'C3 S-S}/">3' Disulfide Linker C3</option>
							<option value="/{3'C6 S-S}/">3' Disulfide Linker C6</option>
							<option value="0">Spachers</option>
							<option value="/{3'SpacerC3}/">3' Spacer C3</option>
							<option value="/{3'SpacerC6}/">3' Spacer C6</option>
							<option value="/{3'Spacer9}/">3' Spacer 9</option>
							<option value="/{3'Spacer18}/">3' Spacer 18</option>
							<option value="/{3'dSpacer}/">3' dSpacer</option>
							<option value="0">Modified bases</option>
							<option value="/{5'deoxyI}/">3' DeoxyInosine</option>
							<option value="/{5'deoxyU}/">3' DeoxyUridine</option>
							<option value="/{3'fA}/">3' 2'-Fluoro A</option>
							<option value="/{3'fC}/">3' 2'-Fluoro C</option>
							<option value="/{3'fG}/">3' 2'-Fluoro G</option>
							<option value="/{3'fU}/">3' 2'-Fluoro U</option>
							<option value="0">Electrochemicals</option>
							<option value="/{3' Ferrocene }/">3' Ferrocene</option>
					</select></td>
				</tr>
				<tr>
					<th width="17%">Oligo Sequence File</th>
					<td colspan="3"><input id="up_file" size="25" type="file"
						name="up_file" /> (.xls, .xlsx <a
						href="images/Oligo_Batch_Order.zip" target="_blank">standard
							from</a>)</td>
				</tr>
				<tr>
					<td colspan="4"><table width="100%" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="17%">&nbsp;</td>
								<td width="83%">or just paste your oligos here (Enter
									sequences in multiple FASTA txt format):</td>
							</tr>
						</table></td>
				</tr>
				<tr>
					<th valign="top">&nbsp;</th>
					<td colspan="3"><textarea name="textarea2"
							class="content_textarea2"></textarea></td>
				</tr>
				<tr>
					<td height="40" colspan="4" align="center" valign="bottom"><input
						name="Submit" type="submit" class="style_botton" value="Save" />
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		initTabs('dhtmlgoodies_tabView1', Array('Details', 'Custom Oligos',
				'Batch Orders'), 0, 968, 285);
	</script>
</body>
</html>
