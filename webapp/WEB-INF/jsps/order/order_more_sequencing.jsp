<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!-- no cache headers -->
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="no-cache">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Cache-Control" content="no-cache">
<!-- end no cache headers --> 
<link rel="stylesheet" type="text/css" media="handheld, print"
	href="${global_css_url}dnasequencing/3.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="${global_css_url}dnasequencing/all.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${global_css_url}dnasequencing/4.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${global_css_url}dnasequencing/mask.css" />
<link rel="stylesheet" type="text/css"
	media="aural, braille, embossed, projection, screen, tty, tv"
	href="${global_css_url}dnasequencing/2.css" />
<link href="${global_css_url}dnasequencing/thinklist.css"
	rel="stylesheet" type="text/css" />
<link
	href="${global_css_url}dnasequencing/SpryTabbedPanels.css"
	rel="stylesheet" type="text/css" />
<link href="${global_css_url}dnasequencing/clones_tab.css"
	rel="stylesheet" type="text/css" />
<s:if test="show == 'sequencing'">
	<div
		style="width: 960px; height: 350px; overflow-x: auto; overflow-y: auto;">
		<!-- start header -->
		<div class="Tube">
			<div class="tube_ge">
				* are required fields <span>Special buttons:</span>
				Click&nbsp;&nbsp;<img src="${global_image_url}/deng.jpg" /> to fill
				in the same content in specific cells, Click&nbsp;&nbsp;<img
					src="${global_image_url}/down.jpg" /> to add sequential numbering
				surfix to sample and/or primer names in the specific cells,
				Click&nbsp;&nbsp;<img src="${global_image_url}/x.jpg" /> to clear
				all data in the current row
			</div>
			<!--切换层-->
			<div class="tube_ge">
				<div class="webone_contm_tab">
					<!--标签-->
					<div class="webone_contm_tabt">
						<div class="webone_contm_tabtb" id="tab01"
							onclick="showDiv(1,this);">Tube Entry</div>
						<div class="webone_contm_tabta" id="tab02"
							onclick="showDiv(2,this);">Plate Entry</div>
					</div>
					<!--显示层-->
					<div class="webone_contm_tabmb">
						<!--Tube Entry Start-->
						<div class="webone_contm_tabm" id="ctab01">
							<div>
								<form action="" method="post" onsubmit="return tubeSubmit();">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<s:if test="tubeMap ==null || tubeMap.size ==0">
										<tr>
											<td>Total Reactions <input class="NFText" id="t_nums" type="text"
											    value="5" /> <input value="Customize" type="button"
												name="op" onclick="customTube(this);" class="style_botton" />
											</td>
										</tr>
										</s:if>
										<tr>
											<td>Set the effective range of <img
												src="${global_image_url}/deng.jpg"> and <img
												src="${global_image_url}/down.jpg"> : rows <input
												class="NFText" style="width: 30px;" value="1" type="text" 
												id="range_from" />to<input class="NFText" type="text" 
												style="width: 30px;" value="<s:if test='tubeMap != null && tubeMap.size >0'><s:property value='tubeMap.size'/></s:if><s:else>5</s:else>" id="range_to" />.</td>
										</tr>
										<tr>
											<td><input type="image" src="${global_image_url}/submit_gene.jpg" />
											</td>
										</tr>
										<tr>
											<td>
												<table border="0" cellpadding="0" cellspacing="1"
													bgcolor="#CCCCCC" id="tube_table">
													<tr>
														<td width="22" bgcolor="#FFFFFF"><center>#</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please type the sample name as you want to name it, maximum 8 characters, use only letters and numbers, do not use space, this is the name that you will use to label your tubes or plates.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Sample Name*<br /> <a href="javascript:void(0);" 
																	onclick="setEQSampleName();" valign="bottom"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>&nbsp;&nbsp;<a
																	href="javascript:void(0);" onclick="setPSSampleName();"><img
																	src="${global_image_url}/down.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please choose from Plasmid, Purified PCR product and Unpurified PCR product.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Sample Type*<br /> <a href="javascript:void(0);"
																	onclick="setEQSampleType();"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="50" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please enter your sample concentration by ng/&micro;l, please follow our sample submission guidelines to dilute your sample, if you are not sure about the concentration of your sample, choose Concentration Measurement option and we will measure it for you.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Sample Conc.<br /> ng/&micro;l<br /> <a
																	href="javascript:void(0);" onclick="setEQSampleConc();"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'If this option is chosen, Genscript will perform concentration analysis on your sample and make dilution before loading it to reaction to make sure best reaction condition is reached, additional charges apply, see details in Pricing, terms and conditions page.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Concentration Measurement<br /> <a
																	href="javascript:void(0);" onclick="setEQConc();"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please choose from premixed, standard or enclosed primers, if you already added primers to the samples, please select Premixed, if you would like to use the large selection of our Universal primers free of charge, select Standard, if you provide your own primers in a separate tube, please select Enclosed, please follow our sample submission guidelines for best primer concentration.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Primer Type*<br /> <a href="javascript:void(0);"
																	onclick="setEQPrimerType();"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please enter your primer name, maximum 8 characters, use only letters and numbers, do not use space, this is the name you will use to label your primer tubes; if you select to use Genscriptstandard primers, choose the primer from dropdown list.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Primer Name*<br /> <a href="javascript:void(0);"
																	onclick="setEQPrimerName();"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>&nbsp;&nbsp;<a
																	href="javascript:void(0);" onclick="setPSPrimerName();"><img
																	src="${global_image_url}/down.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="42" bgcolor="#FFFFFF"
															onmouseover="create(this,'If this option is chosen, Genscript will try different conditions to read your sample should it fails on first try, additional charges apply, see details in Pricing, terms and conditions page');"
															style="cursor: pointer;" onMouseOut="del();"
															valign="bottom"><center>
																Power Read<br /> <a href="javascript:void(0);"
																	onclick="setEQPorwerRead();"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="46" bgcolor="#FFFFFF"
															onmouseover="create(this,'If this option is chosen, Genscript will analyze your sequencing result, provide alignment and deliver the contig, you may provide reference sequence for the alignment by copy it into the box, additional charges apply, see details in Pricing, terms and conditions page');"
															style="cursor: pointer;" onMouseOut="del();"
															valign="bottom"><center>
																<a href="#" style="color: #000; text-decoration: none;">Data
																	Analysis</a>
															</center>
														</td>
														<td width="80" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please let us know if the sequence need any special handling or it is known to be difficult to sequence, Genscript have developed special technology to work around known issues such as high GC content or secondary structures.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Special Request<br /> <a href="javascript:void(0);"
																	onclick="setEQNotes();"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="40" bgcolor="#FFFFFF"
															onmouseover="create(this,'Please provide us the length of your template in base pairs (bp), this helps us in choosing the right reaction condition in order to get best reading');"
															style="cursor: pointer;" onMouseOut="del();"
															valign="bottom"><center>
																Template size(bp)<br /> <a href="javascript:void(0);"
																	onclick="setEQSampleSize();"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please provide the name of the vector for plasmid DNA, this helps Genscript to choose best reaction condition for your sample.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Vector Name<br /> <a href="javascript:void(0);"
																	onclick="setEQVectorName();"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom" style="cursor: pointer;"><center>
																Primer Conc<br /> <a href="javascript:void(0);"
																	onclick="setEQPrimerConc();"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom" style="cursor: pointer;"><center>
																Resistance<br /><a href="javascript:void(0);"
																	onclick="setEQResistance();"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom" style="cursor: pointer;"><center>
																Price<br /><a href="javascript:void(0);"
																	onclick="setEQPrice();"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td bgcolor="#FFFFFF">&nbsp;</td>
													</tr>
													<%int dsTubeNum = 1; %>
													<s:iterator value="tubeMap" id="tubeItemMap">
													<tr>
														<td bgcolor="#FFFFFF"><center><%=dsTubeNum%></center>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_name_<%=dsTubeNum%>" value="${value.dnaSequencing.sampleName}" style="width: 100px;" />
														</td>
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_SAMPLE_TYPE'] != null">
													   	 	<select name="sample_type_<%=dsTubeNum%>" class="custClon"
																id="sample_type_<%=dsTubeNum%>"
																style="width:100px;">
																<s:iterator value="dropDownMap['DS_SAMPLE_TYPE']" id="TsmpTypeMap">
																<option value="${TsmpTypeMap.text}"
																<s:if test="#TsmpTypeMap.text == #tubeItemMap.value.dnaSequencing.sampleType">
																	 selected="selected"
																</s:if>>${TsmpTypeMap.value}</option>
																</s:iterator>
															</select>
													   	</s:if>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_conc_<%=dsTubeNum%>" style="width: 50px;" value="${value.dnaSequencing.sampleConc}" />
														</td>
														<td bgcolor="#FFFFFF"><input class="sInput" value="1" <c:if test='${value.dnaSequencing.flagConcMeas==1}'> checked="checked"</c:if>
															type="checkbox" name="conc_<%=dsTubeNum%>" style="width: 30px;" />
														</td>
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_PRIMER_TYPE'] != null">
													   	 	<select name="primer_type_<%=dsTubeNum%>"  class="custClon" 
																id="primer_type_<%=dsTubeNum%>"
																style="width:100px;" onchange="selectPrimerName(this);">
																<option value="0">Select Type...</option>
																<s:iterator value="dropDownMap['DS_PRIMER_TYPE']" id="TprimerTypeMap">
																<option value="${TprimerTypeMap.text}" 
																<s:if test="#TprimerTypeMap.text == #tubeItemMap.value.dnaSequencing.primerType">
																	selected="selected"
																</s:if>>${TprimerTypeMap.value}</option>
																</s:iterator>
															</select>
													   	</s:if>
														</td>
														<td bgcolor="#FFFFFF">
														<s:if test="specDropDownMap['GET_PRIMER_NAME'] != null && specDropDownMap['GET_PRIMER_NAME'].dropDownDTOs != null && #tubeItemMap.value.dnaSequencing.primerType == 'Standard (Free)'">	
															<select name="primer_name_<%=dsTubeNum%>" 
																id="primer_name_<%=dsTubeNum%>"
																style="width:100px;" class="custClon myplist">
																<option value="0">Select Type...</option>
																<s:iterator value="specDropDownMap['GET_PRIMER_NAME'].dropDownDTOs" id="TprimerNameMap">
																<option value="${TprimerNameMap.name}" 
																<s:if test="#TprimerNameMap.name == #tubeItemMap.value.dnaSequencing.primerName">
																	selected="selected"
																</s:if>>${TprimerNameMap.name}</option>
																</s:iterator>
															</select>	
														</s:if>		
														<s:else>
															<input class="NFText myplist"
																type="text" name="primer_name_<%=dsTubeNum%>" style="width: 100px;" value="${value.dnaSequencing.primerName}" />
														</s:else>
														</td>
														<td bgcolor="#FFFFFF"><input type="checkbox"
															name="pwrwer_read_<%=dsTubeNum%>" class="sInput" style="width: 30px;" 
															 <c:if test='${value.dnaSequencing.flagPowerRead==1}'> checked="checked"</c:if> 
															value="1" />
														</td>
														<td bgcolor="#FFFFFF">
														<c:if test='${value.dnaSequencing.flagDataAnas==1}'>
															<input type="checkbox" name="data_analysis_<%=dsTubeNum%>" class="sInput"
																style="width: 30px;" onclick="referenceSequence(this);" 
																checked="checked" value="1" />
															<span id="span_<%=dsTubeNum%>">
																<input class="NFText" type="text" onblur="hideSpan('<%=dsTubeNum%>');" 
																	name="ref_seq_<%=dsTubeNum%>" style="height: 50px;" value="${value.dnaSequencing.dataAnas}">
															</span>	
														</c:if>
														<c:if test="${value.dnaSequencing.flagDataAnas != 1}">
															<input type="checkbox" name="data_analysis_<%=dsTubeNum%>" class="sInput"
																style="width: 30px;" onclick="referenceSequence(this);" 
																value="1" />
															<span id="span_<%=dsTubeNum%>" style="display: none;">
																<input class="NFText" type="text" onblur="hideSpan('<%=dsTubeNum%>');" 
																	name="ref_seq_<%=dsTubeNum%>" style="height: 50px;" value="">
															</span>		
														</c:if>	
														</td>
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_SPECIAL_REQUEST'] != null">
															<select name="notes_<%=dsTubeNum%>" cssClass="custClon" 
																id="notes_<%=dsTubeNum%>"
																style="width:107px;">
																<option value="0">Select Type...</option>
																<s:iterator value="dropDownMap['DS_SPECIAL_REQUEST']" id="TnotesMap">
																<option value="${TnotesMap.text}" 
																<s:if test="#TnotesMap.text == #tubeItemMap.value.dnaSequencing.specialRequest">
																	selected="selected"
																</s:if>>${TnotesMap.value}</option>
																</s:iterator>
															</select>
													   	</s:if>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_size_<%=dsTubeNum%>" value="${value.dnaSequencing.templateSize}" style="width: 40px;" />
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="vector_name_<%=dsTubeNum%>" value="${value.dnaSequencing.vectorName}" style="width: 70px;" />
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="primer_conc_<%=dsTubeNum%>" value="${value.dnaSequencing.primerConc}" style="width: 70px;" />
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="resistance_<%=dsTubeNum%>" value="${value.dnaSequencing.resistance}" style="width: 70px;" />
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="price_<%=dsTubeNum%>" value="${value.unitPrice}" style="width: 70px;" />
														</td>
														<td bgcolor="#FFFFFF"><img
															src="${global_image_url}/x.jpg" onclick="delTr(this);" />
															<input type="hidden" name="sessItemKey_<%=dsTubeNum%>" value="${tubeItemMap.key}">
														</td>
													</tr>
													<%dsTubeNum++; %>
													</s:iterator>
													<s:if test="tubeMap == null || tubeMap.size ==0">
													<%while (dsTubeNum<6) {%>
													<tr>
														<td bgcolor="#FFFFFF"><center><%=dsTubeNum%></center>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_name_<%=dsTubeNum%>" value="" style="width: 100px;" />
														</td>
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_SAMPLE_TYPE'] != null">
													   	 	<select name="sample_type_<%=dsTubeNum%>" class="custClon"
																id="sample_type_<%=dsTubeNum%>" style="width:100px;">
																<s:iterator value="dropDownMap['DS_SAMPLE_TYPE']" id="TsmpTypeMap">
																<option value="${TsmpTypeMap.text}">${TsmpTypeMap.value}</option>
																</s:iterator>
															</select>
													   	</s:if>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_conc_<%=dsTubeNum%>" style="width: 50px;" value="" />
														</td>
														<td bgcolor="#FFFFFF"><input class="sInput" value="1" 
															type="checkbox" name="conc_<%=dsTubeNum%>" style="width: 30px;" />
														</td>
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_PRIMER_TYPE'] != null">
													   	 	<select name="primer_type_<%=dsTubeNum%>"  class="custClon" 
																id="primer_type_<%=dsTubeNum%>"
																style="width:100px;" onchange="selectPrimerName(this);">
																<option value="0" selected="selected">Select Type...</option>
																<s:iterator value="dropDownMap['DS_PRIMER_TYPE']" id="TprimerTypeMap">
																<option value="${TprimerTypeMap.text}">${TprimerTypeMap.value}</option>
																</s:iterator>
															</select>
													   	</s:if>
														</td>
														<td bgcolor="#FFFFFF">
															<input class="NFText myplist"
																type="text" name="primer_name_<%=dsTubeNum%>" style="width: 100px;" value="" />
														</td>
														<td bgcolor="#FFFFFF"><input type="checkbox"
															name="pwrwer_read_<%=dsTubeNum%>" class="sInput" style="width: 30px;" value="1" />
														</td>
														<td bgcolor="#FFFFFF">
															<input type="checkbox" name="data_analysis_<%=dsTubeNum%>" class="sInput"
																style="width: 30px;" onclick="referenceSequence(this);" 
																value="1" />
															<span id="span_<%=dsTubeNum%>" style="display: none;">
																<input class="NFText" type="text" onblur="hideSpan('<%=dsTubeNum%>');" 
																	name="ref_seq_<%=dsTubeNum%>" style="height: 50px;" value="">
															</span>			
														</td>
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_SPECIAL_REQUEST'] != null">
															<select name="notes_<%=dsTubeNum%>" cssClass="custClon" 
																id="notes_<%=dsTubeNum%>"
																style="width:107px;">
																<option value="0" selected="selected">Select Type...</option>
																<s:iterator value="dropDownMap['DS_SPECIAL_REQUEST']" id="TnotesMap">
																<option value="${TnotesMap.text}" >${TnotesMap.value}</option>
																</s:iterator>
															</select>
													   	</s:if>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_size_<%=dsTubeNum%>" value="" style="width: 40px;" />
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="vector_name_<%=dsTubeNum%>" value="" style="width: 70px;" />
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="primer_conc_<%=dsTubeNum%>" value="" style="width: 70px;" />
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="resistance_<%=dsTubeNum%>" value="" style="width: 70px;" />
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="price_<%=dsTubeNum%>" value="" style="width: 70px;" />
														</td>
														<td bgcolor="#FFFFFF"><img
															src="${global_image_url}/x.jpg" onclick="delTr(this);" />
															<input type="hidden" name="sessItemKey_<%=dsTubeNum%>" value="">
														</td>
													</tr>
													<%dsTubeNum++;} %>
													</s:if>
												</table></td>
										</tr>
										<tr>
											<td><input value="Add" type="button"
												onclick="addTube();" class="style_botton" /> <input
												name="add_tube_nums" id="add_tube_nums" type="text" value=""
												class="NFText" size="5" /> more rows</td>
										</tr>
										<tr>
											<td align="right">&nbsp;</td>
										</tr>
										<tr>
											<td>Comments</td>
										</tr>
										<tr>
											<td><textarea name="comment" id="comment"
													 class="content_textarea2" name="textarea">${tubeComment}</textarea>
											</td>
										</tr>
										<tr>
											<td><input type="hidden" value="0" id="tube_len"
												name="tube_len" /> <input type="hidden" value="add_tube"
												name="op" /> <input type="hidden" name="labe_name"
												id="labe_name" /> <input type="hidden" name="labe_code"
												id="labe_code" /> <input type="image" src="${global_image_url}/submit_gene.jpg" />
											</td>
										</tr>
										<tr id="enclose_tips" style="display: none;">
											<td><span class=subTitle>Enclosed Materlal</span>
												(Please write the below <span class=subTitle>Prlmer
													Code</span> on your enclosed prime tubes)</td>
										</tr>
										<tr id="enclose_show" style="display: none;">
											<td><table border="0" cellspacing="1" cellpadding="0"
													bgcolor="#CCCCCC">
													<tr>
														<td width="100" bgcolor="#FFFFFF"
															onmouseover="create(this,'Please enter your primer name, maximum 8 characters, use only letters and numbers, do not use space, this is the name you will use to label your primer tubes; if you select to use Genscriptstandard primers, choose the primer from dropdown list.');"
															style="cursor: pointer;" onMouseOut="del();"><center>Primer
																Name</center>
														</td>
														<td width="100" bgcolor="#FFFFFF"><center>Primer
																Code</center>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
									</table>
								</form>
							</div>
							<!--拷贝Tube-->
							<table style="display: none" id="copy_tr">
								<tr>
									<td bgcolor="#FFFFFF">&nbsp;</td>
									<td bgcolor="#FFFFFF">&nbsp;</td>
									<td bgcolor="#FFFFFF">
									<s:if test="dropDownMap['DS_SAMPLE_TYPE'] != null">
						           		<s:select cssStyle="width:100px;" cssClass="custClon"
								   			  list="dropDownMap['DS_SAMPLE_TYPE']" 
								   			  listKey="text" 
								   			  listValue="value">
								   	 	</s:select>
								   	</s:if>
									</td>
									<td bgcolor="#FFFFFF">&nbsp;</td>
									<td bgcolor="#FFFFFF">&nbsp;</td>
									<td bgcolor="#FFFFFF">
									<s:if test="dropDownMap['DS_PRIMER_TYPE'] != null">
						           		<s:select cssStyle="width:100px;" cssClass="custClon" 
								   			  list="dropDownMap['DS_PRIMER_TYPE']" 
								   			  listKey="text" 
								   			  listValue="value" 
								   			  headerKey="0"
								   			  headerValue="Select Type..."
								   			  onchange="selectPrimerName(this);">
								   	 	</s:select>
								   	</s:if>
									</td>
									<td bgcolor="#FFFFFF">&nbsp;</td>
									<td bgcolor="#FFFFFF">&nbsp;</td>
									<td bgcolor="#FFFFFF">&nbsp;</td>
									<td bgcolor="#FFFFFF">
									<s:if test="dropDownMap['DS_SPECIAL_REQUEST'] != null">
						           		<s:select cssStyle="width:107px;" cssClass="custClon" 
								   			  list="dropDownMap['DS_SPECIAL_REQUEST']" 
								   			  listKey="text" 
								   			  listValue="value"
								   			  headerKey="0"
								   			  headerValue="Select Type..." >
								   	 	</s:select>
								   	</s:if>
								   	</td>
									<td bgcolor="#FFFFFF">&nbsp;</td>
									<td bgcolor="#FFFFFF">&nbsp;</td>
									<td bgcolor="#FFFFFF">&nbsp;</td>
									<td bgcolor="#FFFFFF">&nbsp;</td>
									<td bgcolor="#FFFFFF">&nbsp;</td>
									<td bgcolor="#FFFFFF"><img src="${global_image_url}/x.jpg"
										onclick="delTr(this);" />
									</td>
								</tr>
							</table>
							<!--Primer Name List-->
							<div id="primer_list" style="display: none;">
							<s:if test="specDropDownMap['GET_PRIMER_NAME'] != null && specDropDownMap['GET_PRIMER_NAME'].dropDownDTOs != null">	
								<s:select cssStyle="width: 100px;" cssClass="custClon myplist"
									name="primer_name_"
									list="specDropDownMap['GET_PRIMER_NAME'].dropDownDTOs" 
									listKey="name" 
									listValue="name"
									headerKey="0"
								   	headerValue="Select Type..." >
								</s:select>
							</s:if>
							</div>
						</div>
						<!--Tube Entry End-->
						<!--Plate Entry Start-->
						<div class="webone_contm_tabm" id="ctab02" style="display: none;">
							<div>
								<!--Plate Start-->
								<table border="0" cellspacing="0" cellpadding="0" id="tab_new1">
									<tr>
										<td>Total Plates <input class="NFText"
											style="width: 30px;" id="plate_nums" value="2" /> <input
											value="Customize" type="button" class="style_botton" onclick="customPlate();" />
										</td>
									</tr>
									<tr>
										<td>
											<div class="tube_linea" id="div_plate">
												<!--Sign Plate Start-->
												<%int dsPlateNum = 1; %>
												<s:if test="dsPlatesList != null && dsPlatesList.size !=0">
												<s:iterator value="dsPlatesList">
												<div class="tube_line" id="plate_div_<%=dsPlateNum%>">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td width="210" plateid="<%=dsPlateNum%>">Plate <%=dsPlateNum %></td>
														</tr>
														<tr>
															<td align="left" valign="top">
															<%if (dsPlateNum ==1) {%>
																<div class="plate_new2" onclick="showDetail(this)">
																	Name:${name}<br>Total reactions:${nums}
																</div>
															<%} else {%>
																<div class="plate_new" onclick="showDetail(this)">
																	Name:${name}<br>Total reactions:${nums}
																</div>
															<%} %>
															</td>
														</tr>
														<tr>
															<td>*Click on plate for more information<br />
															</td>
														</tr>
													</table>
												</div>
												<%dsPlateNum++; %>
												</s:iterator>
												</s:if>
												<s:else>
												<div class="tube_line" id="plate_div_<%=dsPlateNum%>">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td width="210" plateid="<%=dsPlateNum%>">Plate <%=dsPlateNum %></td>
														</tr>
														<tr>
															<td align="left" valign="top">
															<%if (dsPlateNum ==1) {%>
																<div class="plate_new2" onclick="showDetail(this)"></div>
															<%} else {%>
																<div class="plate_new" onclick="showDetail(this)"></div>
															<%} %>
															</td>
														</tr>
														<tr>
															<td>*Click on plate for more information<br />
															</td>
														</tr>
													</table>
												</div>
												</s:else>
											</div></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
								</table>
								<!--Plate End-->
								<!--Plate Detail Start-->
								<form action="" method="post"
									onsubmit="return checkPlateForm();" id="plate_form">
									<%int dsPlateDetailNum = 1; %>
									<s:if test="dsPlatesList != null && dsPlatesList.size !=0">
									<s:iterator value="dsPlatesList" id="ds_Plate">
									<table border="0" cellspacing="0" cellpadding="0"
										id="tab_detail<%=dsPlateDetailNum%>">
										<tr>
											<td>Plate Name*</td>
											<td><input name="p_name_<%=dsPlateDetailNum%>" id="p_name_<%=dsPlateDetailNum%>" class="NFText" 
												style="width: 100px;" value="${name}" />
											</td>
											<td colspan="4">Set the effective range of <img
												src="${global_image_url}/deng.jpg"> and <img
												src="${global_image_url}/down.jpg"> : rows <input
												class="NFText" style="width: 30px;" value="1"
												id="p_range_from_<%=dsPlateDetailNum%>" />to<input class="NFText"
												style="width: 30px;" value="96" id="p_range_to_<%=dsPlateDetailNum%>" />.</td>
										</tr>
										<tr>
											<td width="75">Plate Layout</td>
											<td><select name="plate_layout_<%=dsPlateDetailNum%>" class="custClon"
												id="plate_layout_<%=dsPlateDetailNum%>" onchange="calcNums(this);">
												<c:if test="${layout == 2}">
													<option value="1">By Rows</option>
													<option value="2" selected="selected">By Columins</option>
												</c:if>	
												<c:if test="${layout != 2}">
													<option value="1" selected="selected">By Rows</option>
													<option value="2">By Columins</option>
												</c:if>	
											</select></td>
											<td width="215">&nbsp;</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td><span class="subTitle">Total reactions:</span>
											</td>
										</tr>
										<tr>
											<td><input type="image" src="${global_image_url}/submit_gene.jpg" />
											</td>
										</tr>
										<tr>
											<td colspan="6"><table border="0" cellpadding="0"
													cellspacing="1" bgcolor="#CCCCCC" id="plate_table_<%=dsPlateDetailNum%>">
													<tr>
														<td width="45" bgcolor="#FFFFFF"><center>Well
																Position</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please type the sample name as you want to name it, maximum 8 characters, use only letters and numbers, do not use space, this is the name that you will use to label your tubes or plates.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Sample Name*<br /> <a href="javascript:void(0);"
																	onclick="setPEQSampleName(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>&nbsp;&nbsp;<a
																	href="javascript:void(0);"
																	onclick="setPPSSampleName(this);"><img
																	src="${global_image_url}/down.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please choose from Plasmid, Purified PCR product and Unpurified PCR product.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Sample Type*<br /> <a href="javascript:void(0);"
																	onclick="setPEQSampleType(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please enter your sample concentration by ng/&micro;l, please follow our sample submission guidelines to dilute your sample, if you are not sure about the concentration of your sample, choose Concentration Measurement option and we will measure it for you.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Sample Conc.<br /> ng/&micro;l<br /> <a
																	href="javascript:void(0);"
																	onclick="setPEQSampleConc(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'If this option is chosen, Genscript will perform concentration analysis on your sample and make dilution before loading it to reaction to make sure best reaction condition is reached, additional charges apply, see details in Pricing, terms and conditions page.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Concentration Measurement<br /> <a
																	href="javascript:void(0);" onclick="setPEQConc(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>

														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please choose from premixed, standard or enclosed primers, if you already added primers to the samples, please select Premixed, if you would like to use the large selection of our Universal primers free of charge, select Standard, if you provide your own primers in a separate tube, please select Enclosed, please follow our sample submission guidelines for best primer concentration.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Primer Type*<br /> <a href="javascript:void(0);"
																	onclick="setPEQPrimerType(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please enter your primer name, maximum 8 characters, use only letters and numbers, do not use space, this is the name you will use to label your primer tubes; if you select to use Genscriptstandard primers, choose the primer from dropdown list.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Primer Name*<br /> <a href="javascript:void(0);"
																	onclick="setPEQPrimerName(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>&nbsp;&nbsp;<a
																	href="javascript:void(0);"
																	onclick="setPPSPrimerName(this);"><img
																	src="${global_image_url}/down.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="42" valign="bottom" bgcolor="#FFFFFF"
															onmouseover="create(this,'If this option is chosen, Genscript will try different conditions to read your sample should it fails on first try, additional charges apply, see details in Pricing, terms and conditions page');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Power Read<br /> <a href="javascript:void(0);"
																	onclick="setPEQPorwerRead(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="46" valign="bottom" bgcolor="#FFFFFF"
															onmouseover="create(this,'If this option is chosen, Genscript will analyze your sequencing result, provide alignment and deliver the contig, you may provide reference sequence for the alignment by copy it into the box, additional charges apply, see details in Pricing, terms and conditions page');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																<a href="#" style="color: #000; text-decoration: none;">Data
																	Analysis</a>
															</center>
														</td>
														<td width="80" valign="bottom" bgcolor="#FFFFFF"
															onmouseover="create(this,'Please let us know if the sequence need any special handling or it is known to be difficult to sequence, Genscript have developed special technology to work around known issues such as high GC content or secondary structures.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Special Request<br /> <a href="javascript:void(0);"
																	onclick="setPEQNotes(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="50" bgcolor="#FFFFFF"
															onmouseover="create(this,'Please provide us the length of your template in base pairs (bp), this helps us in choosing the right reaction condition in order to get best reading');"
															style="cursor: pointer;" onMouseOut="del();"
															valign="bottom"><center>
																Template size(bp)<br /> <a href="javascript:void(0);"
																	onclick="setPEQSampleSize(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="19" bgcolor="#FFFFFF">&nbsp;</td>
													</tr>
													<%int plateByRowNums=1; 
													  String Position = "A";
													%>
													<s:iterator value="plateMap" id="plateItemMap">
													<s:if test='#ds_Plate.sessPlateId == #plateItemMap.value.dnaSequencing.sessPlateId'>
													<s:if test="#ds_Plate.layout != 2">
													<%
														if(plateByRowNums%8==1) {
															Position = "A"+((plateByRowNums/8)+1);
														} else if(plateByRowNums%8==2) {
															Position = "B"+((plateByRowNums/8)+1);
														} else if(plateByRowNums%8==3) {
															Position = "C"+((plateByRowNums/8)+1);
														} else if(plateByRowNums%8==4) {
															Position = "D"+((plateByRowNums/8)+1);;
														} else if(plateByRowNums%8==5) {
															Position = "E"+((plateByRowNums/8)+1);
														} else if(plateByRowNums%8==6) {
															Position = "F"+((plateByRowNums/8)+1);
														} else if(plateByRowNums%8==7) {
															Position = "G"+((plateByRowNums/8)+1);
														} else if(plateByRowNums%8==0) {
															Position = "H"+(plateByRowNums/8);
														}
													%>
													</s:if>
													<s:else>
													<%
														if(plateByRowNums<=12) {
															Position = "A"+plateByRowNums;
														} else if(plateByRowNums<=24) {
															Position = "B"+plateByRowNums;
														} else if(plateByRowNums<=36) {
															Position = "C"+plateByRowNums;
														} else if(plateByRowNums<=48) {
															Position = "D"+plateByRowNums;
														} else if(plateByRowNums<=60) {
															Position = "E"+plateByRowNums;
														} else if(plateByRowNums<=72) {
															Position = "F"+plateByRowNums;
														} else if(plateByRowNums<=84) {
															Position = "G"+plateByRowNums;
														} else if(plateByRowNums<=96) {
															Position = "H"+plateByRowNums;
														}
													%>
													</s:else>
													<tr>
														<td bgcolor="#FFFFFF"><center>
																<%=Position %><input type="hidden" value="<%=Position %>" name="num_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" />
															</center>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_name_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 100px;" value="${value.dnaSequencing.sampleName}" />
														</td>
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_SAMPLE_TYPE'] != null">
															<select name="sample_type_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" 
																id="sample_type_<%=dsPlateDetailNum%>_<%=plateByRowNums%>"
																style="width:100px;">
																<s:iterator value="dropDownMap['DS_SAMPLE_TYPE']" id="smpTypeMap">
																<option value="${smpTypeMap.text}" 
																<s:if test="#smpTypeMap.text == #plateItemMap.value.dnaSequencing.sampleType">
																	selected="selected"
																</s:if>>${smpTypeMap.value}</option>
																</s:iterator>
															</select>
														</s:if>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_conc_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 50px;" value="${value.dnaSequencing.sampleConc}" />
														</td>
														<td bgcolor="#FFFFFF"><input class="sInput" <c:if test='${value.dnaSequencing.flagConcMeas==1}'> checked="checked"</c:if>
															type="checkbox" name="conc_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 40px;" />
														</td>
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_PRIMER_TYPE'] != null">
															<select name="primer_type_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" 
																id="primer_type_<%=dsPlateDetailNum%>_<%=plateByRowNums%>"
																style="width:100px;" onchange="selectPrimerName(this);">
																<option value="0">Select Type...</option>
																<s:iterator value="dropDownMap['DS_PRIMER_TYPE']" id="primerTypeMap">
																<option value="${primerTypeMap.text}" 
																<s:if test="#primerTypeMap.text == #plateItemMap.value.dnaSequencing.primerType">
																	selected="selected"
																</s:if>>${primerTypeMap.value}</option>
																</s:iterator>
															</select>
														</s:if>
														</td>
														<td bgcolor="#FFFFFF">
														<s:if test="specDropDownMap['GET_PRIMER_NAME'] != null && specDropDownMap['GET_PRIMER_NAME'].dropDownDTOs != null && #plateItemMap.value.dnaSequencing.primerType == 'Standard (Free)'">	
															<select name="primer_name_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" 
																id="primer_name_<%=dsPlateDetailNum%>_<%=plateByRowNums%>"
																style="width:100px;" class="custClon myplist">
																<option value="0">Select Type...</option>
																<s:iterator value="specDropDownMap['GET_PRIMER_NAME'].dropDownDTOs" id="primerNameMap">
																<option value="${primerNameMap.name}" 
																<s:if test="#primerNameMap.name == #plateItemMap.value.dnaSequencing.primerName">
																	selected="selected"
																</s:if>>${primerNameMap.name}</option>
																</s:iterator>
															</select>
														</s:if>	
														<s:else>
															<input class="NFText myplist" 
																type="text" name="primer_name_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 100px;" value="${value.dnaSequencing.primerName}" />
														</s:else>
														</td>
														<td bgcolor="#FFFFFF"><input type="checkbox"
															name="pwrwer_read_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" class="sInput"
															style="width: 30px;" value="1" <c:if test='${value.dnaSequencing.flagPowerRead==1}'> checked="checked"</c:if> />
														</td>
														<td bgcolor="#FFFFFF">
														<c:if test='${value.dnaSequencing.flagDataAnas==1}'>
															<input type="checkbox" name="data_analysis_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" class="sInput"
																style="width: 30px;" onclick="referenceSequence(this);" 
																checked="checked" value="1" />
															<span id="span_<%=dsPlateDetailNum%>_<%=plateByRowNums%>">
																<input class="NFText" type="text" onblur="hideSpan('<%=dsPlateDetailNum%>_<%=plateByRowNums%>');" 
																	name="ref_seq_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="height: 50px;" value="${value.dnaSequencing.dataAnas}">
															</span>
														</c:if>
														<c:if test="${value.dnaSequencing.flagDataAnas != 1}">
															<input type="checkbox" name="data_analysis_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" class="sInput"
																style="width: 30px;" onclick="referenceSequence(this);" 
																value="1" />
															<span id="span_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="display: none;">
																<input class="NFText" type="text" onblur="hideSpan('<%=dsPlateDetailNum%>_<%=plateByRowNums%>');" 
																	name="ref_seq_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="height: 50px;" value="">
															</span>	
														</c:if>	
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_SPECIAL_REQUEST'] != null">
															<select name="notes_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" 
																id="notes_<%=dsPlateDetailNum%>_<%=plateByRowNums%>"
																style="width:107px;">
																<option value="0">Select Type...</option>
																<s:iterator value="dropDownMap['DS_SPECIAL_REQUEST']" id="notesMap">
																<option value="${notesMap.text}" 
																<s:if test="#notesMap.text == #plateItemMap.value.dnaSequencing.specialRequest">
																	selected="selected"
																</s:if>>${notesMap.value}</option>
																</s:iterator>
															</select>
														</s:if>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_size_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 40px;" value="${value.dnaSequencing.templateSize}" />
														</td>
														<td bgcolor="#FFFFFF"><img
															src="${global_image_url}/x.jpg" onclick="delPTr(this);" />
															<input type="hidden" name="sessItemKey_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" value="${plateItemMap.key}">
														</td>
													</tr>
													<%plateByRowNums++; %>
													</s:if>
													</s:iterator>
													<s:if test="#ds_Plate.layout != 2">
													<%
														while (plateByRowNums<97) {
															if(plateByRowNums%8==1) {
																Position = "A"+((plateByRowNums/8)+1);
															} else if(plateByRowNums%8==2) {
																Position = "B"+((plateByRowNums/8)+1);
															} else if(plateByRowNums%8==3) {
																Position = "C"+((plateByRowNums/8)+1);
															} else if(plateByRowNums%8==4) {
																Position = "D"+((plateByRowNums/8)+1);;
															} else if(plateByRowNums%8==5) {
																Position = "E"+((plateByRowNums/8)+1);
															} else if(plateByRowNums%8==6) {
																Position = "F"+((plateByRowNums/8)+1);
															} else if(plateByRowNums%8==7) {
																Position = "G"+((plateByRowNums/8)+1);
															} else if(plateByRowNums%8==0) {
																Position = "H"+(plateByRowNums/8);
															}
													%>
													<tr>
														<td bgcolor="#FFFFFF"><center>
																<%=Position %><input type="hidden" value="<%=Position %>" name="num_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" />
															</center>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_name_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 100px;" value="" />
														</td>
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_SAMPLE_TYPE'] != null">
															<select name="sample_type_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" 
																id="sample_type_<%=dsPlateDetailNum%>_<%=plateByRowNums%>"
																style="width:100px;">
																<s:iterator value="dropDownMap['DS_SAMPLE_TYPE']" id="smpTypeMap">
																<option value="${smpTypeMap.text}">${smpTypeMap.value}</option>
																</s:iterator>
															</select>
														</s:if>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_conc_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 50px;" value="" />
														</td>
														<td bgcolor="#FFFFFF"><input class="sInput" 
															type="checkbox" name="conc_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 40px;" />
														</td>
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_PRIMER_TYPE'] != null">
															<select name="primer_type_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" 
																id="primer_type_<%=dsPlateDetailNum%>_<%=plateByRowNums%>"
																style="width:100px;" onchange="selectPrimerName(this);">
																<option value="0">Select Type...</option>
																<s:iterator value="dropDownMap['DS_PRIMER_TYPE']" id="primerTypeMap">
																<option value="${primerTypeMap.text}">${primerTypeMap.value}</option>
																</s:iterator>
															</select>
														</s:if>
														</td>
														<td bgcolor="#FFFFFF">
															<input class="NFText myplist" 
																type="text" name="primer_name_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 100px;" value="" />
														</td>
														<td bgcolor="#FFFFFF"><input type="checkbox"
															name="pwrwer_read_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" class="sInput"/>
														</td>
														<td bgcolor="#FFFFFF">
															<input type="checkbox" name="data_analysis_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" class="sInput"
																style="width: 30px;" onclick="referenceSequence(this);" 
																value="1" />
															<span id="span_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="display: none;">
																<input class="NFText" type="text" onblur="hideSpan('<%=dsPlateDetailNum%>_<%=plateByRowNums%>');" 
																	name="ref_seq_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="height: 50px;" value="">
															</span>		
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_SPECIAL_REQUEST'] != null">
															<select name="notes_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" 
																id="notes_<%=dsPlateDetailNum%>_<%=plateByRowNums%>"
																style="width:107px;">
																<option value="0">Select Type...</option>
																<s:iterator value="dropDownMap['DS_SPECIAL_REQUEST']" id="notesMap">
																<option value="${notesMap.text}">${notesMap.value}</option>
																</s:iterator>
															</select>
														</s:if>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_size_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 40px;" value="" />
														</td>
														<td bgcolor="#FFFFFF"><img
															src="${global_image_url}/x.jpg" onclick="delPTr(this);" />
															<input type="hidden" name="sessItemKey_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" value="">
														</td>
													</tr>	
												<%plateByRowNums++;}%>
													</s:if>
													<s:else>
													<%
														while (plateByRowNums<97) {
															if(plateByRowNums<=12) {
																Position = "A"+plateByRowNums;
															} else if(plateByRowNums<=24) {
																Position = "B"+plateByRowNums;
															} else if(plateByRowNums<=36) {
																Position = "C"+plateByRowNums;
															} else if(plateByRowNums<=48) {
																Position = "D"+plateByRowNums;
															} else if(plateByRowNums<=60) {
																Position = "E"+plateByRowNums;
															} else if(plateByRowNums<=72) {
																Position = "F"+plateByRowNums;
															} else if(plateByRowNums<=84) {
																Position = "G"+plateByRowNums;
															} else if(plateByRowNums<=96) {
																Position = "H"+plateByRowNums;
															}
													%>
													<tr>
														<td bgcolor="#FFFFFF"><center>
																<%=Position %><input type="hidden" value="<%=Position %>" name="num_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" />
															</center>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_name_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 100px;" value="" />
														</td>
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_SAMPLE_TYPE'] != null">
															<select name="sample_type_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" 
																id="sample_type_<%=dsPlateDetailNum%>_<%=plateByRowNums%>"
																style="width:100px;">
																<s:iterator value="dropDownMap['DS_SAMPLE_TYPE']" id="smpTypeMap">
																<option value="${smpTypeMap.text}">${smpTypeMap.value}</option>
																</s:iterator>
															</select>
														</s:if>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_conc_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 50px;" value="" />
														</td>
														<td bgcolor="#FFFFFF"><input class="sInput" 
															type="checkbox" name="conc_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 40px;" />
														</td>
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_PRIMER_TYPE'] != null">
															<select name="primer_type_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" 
																id="primer_type_<%=dsPlateDetailNum%>_<%=plateByRowNums%>"
																style="width:100px;" onchange="selectPrimerName(this);">
																<option value="0">Select Type...</option>
																<s:iterator value="dropDownMap['DS_PRIMER_TYPE']" id="primerTypeMap">
																<option value="${primerTypeMap.text}">${primerTypeMap.value}</option>
																</s:iterator>
															</select>
														</s:if>
														</td>
														<td bgcolor="#FFFFFF">
															<input class="NFText myplist" 
																type="text" name="primer_name_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 100px;" value="" />
														</td>
														<td bgcolor="#FFFFFF"><input type="checkbox"
															name="pwrwer_read_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" class="sInput"/>
														</td>
														<td bgcolor="#FFFFFF">
															<input type="checkbox" name="data_analysis_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" class="sInput"
																style="width: 30px;" onclick="referenceSequence(this);" 
																value="1" />
															<span id="span_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="display: none;">
																<input class="NFText" type="text" onblur="hideSpan('<%=dsPlateDetailNum%>_<%=plateByRowNums%>');" 
																	name="ref_seq_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="height: 50px;" value="">
															</span>	
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_SPECIAL_REQUEST'] != null">
															<select name="notes_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" 
																id="notes_<%=dsPlateDetailNum%>_<%=plateByRowNums%>"
																style="width:107px;">
																<option value="0">Select Type...</option>
																<s:iterator value="dropDownMap['DS_SPECIAL_REQUEST']" id="notesMap">
																<option value="${notesMap.text}">${notesMap.value}</option>
																</s:iterator>
															</select>
														</s:if>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_size_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 40px;" value="" />
														</td>
														<td bgcolor="#FFFFFF"><img
															src="${global_image_url}/x.jpg" onclick="delPTr(this);" />
															<input type="hidden" name="sessItemKey_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" value="">
														</td>
													</tr>	
												<%plateByRowNums++;}%>
													</s:else>
												</table>
											</td>
										</tr>
										</table>
										<%dsPlateDetailNum++; %>
										</s:iterator>
										</s:if>
										<s:else>
										<table border="0" cellspacing="0" cellpadding="0"
										id="tab_detail<%=dsPlateDetailNum%>">
										<tr>
											<td>Plate Name*</td>
											<td><input name="p_name_<%=dsPlateDetailNum%>" id="p_name_<%=dsPlateDetailNum%>" class="NFText" 
												style="width: 100px;" value="" />
											</td>
											<td colspan="4">Set the effective range of <img
												src="${global_image_url}/deng.jpg"> and <img
												src="${global_image_url}/down.jpg"> : rows <input
												class="NFText" style="width: 30px;" value="1"
												id="p_range_from_<%=dsPlateDetailNum%>" />to<input class="NFText"
												style="width: 30px;" value="96" id="p_range_to_<%=dsPlateDetailNum%>" />.</td>
										</tr>
										<tr>
											<td width="75">Plate Layout</td>
											<td><select name="plate_layout_<%=dsPlateDetailNum%>" class="custClon"
												id="plate_layout_<%=dsPlateDetailNum%>" onchange="calcNums(this);">
													<option value="1" selected="selected">By Rows</option>
													<option value="2">By Columins</option>
											</select></td>
											<td width="215">&nbsp;</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td><span class="subTitle">Total reactions:</span>
											</td>
										</tr>
										<tr>
											<td><input type="image" src="${global_image_url}/submit_gene.jpg" />
											</td>
										</tr>
										<tr>
											<td colspan="6"><table border="0" cellpadding="0"
													cellspacing="1" bgcolor="#CCCCCC" id="plate_table_<%=dsPlateDetailNum%>">
													<tr>
														<td width="45" bgcolor="#FFFFFF"><center>Well
																Position</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please type the sample name as you want to name it, maximum 8 characters, use only letters and numbers, do not use space, this is the name that you will use to label your tubes or plates.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Sample Name*<br /> <a href="javascript:void(0);"
																	onclick="setPEQSampleName(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>&nbsp;&nbsp;<a
																	href="javascript:void(0);"
																	onclick="setPPSSampleName(this);"><img
																	src="${global_image_url}/down.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please choose from Plasmid, Purified PCR product and Unpurified PCR product.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Sample Type*<br /> <a href="javascript:void(0);"
																	onclick="setPEQSampleType(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please enter your sample concentration by ng/&micro;l, please follow our sample submission guidelines to dilute your sample, if you are not sure about the concentration of your sample, choose Concentration Measurement option and we will measure it for you.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Sample Conc.<br /> ng/&micro;l<br /> <a
																	href="javascript:void(0);"
																	onclick="setPEQSampleConc(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'If this option is chosen, Genscript will perform concentration analysis on your sample and make dilution before loading it to reaction to make sure best reaction condition is reached, additional charges apply, see details in Pricing, terms and conditions page.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Concentration Measurement<br /> <a
																	href="javascript:void(0);" onclick="setPEQConc(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>

														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please choose from premixed, standard or enclosed primers, if you already added primers to the samples, please select Premixed, if you would like to use the large selection of our Universal primers free of charge, select Standard, if you provide your own primers in a separate tube, please select Enclosed, please follow our sample submission guidelines for best primer concentration.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Primer Type*<br /> <a href="javascript:void(0);"
																	onclick="setPEQPrimerType(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="70" bgcolor="#FFFFFF" valign="bottom"
															onmouseover="create(this,'Please enter your primer name, maximum 8 characters, use only letters and numbers, do not use space, this is the name you will use to label your primer tubes; if you select to use Genscriptstandard primers, choose the primer from dropdown list.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Primer Name*<br /> <a href="javascript:void(0);"
																	onclick="setPEQPrimerName(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>&nbsp;&nbsp;<a
																	href="javascript:void(0);"
																	onclick="setPPSPrimerName(this);"><img
																	src="${global_image_url}/down.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="42" valign="bottom" bgcolor="#FFFFFF"
															onmouseover="create(this,'If this option is chosen, Genscript will try different conditions to read your sample should it fails on first try, additional charges apply, see details in Pricing, terms and conditions page');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Power Read<br /> <a href="javascript:void(0);"
																	onclick="setPEQPorwerRead(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="46" valign="bottom" bgcolor="#FFFFFF"
															onmouseover="create(this,'If this option is chosen, Genscript will analyze your sequencing result, provide alignment and deliver the contig, you may provide reference sequence for the alignment by copy it into the box, additional charges apply, see details in Pricing, terms and conditions page');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																<a href="#" style="color: #000; text-decoration: none;">Data
																	Analysis</a>
															</center>
														</td>
														<td width="80" valign="bottom" bgcolor="#FFFFFF"
															onmouseover="create(this,'Please let us know if the sequence need any special handling or it is known to be difficult to sequence, Genscript have developed special technology to work around known issues such as high GC content or secondary structures.');"
															style="cursor: pointer;" onMouseOut="del();"><center>
																Special Request<br /> <a href="javascript:void(0);"
																	onclick="setPEQNotes(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="50" bgcolor="#FFFFFF"
															onmouseover="create(this,'Please provide us the length of your template in base pairs (bp), this helps us in choosing the right reaction condition in order to get best reading');"
															style="cursor: pointer;" onMouseOut="del();"
															valign="bottom"><center>
																Template size(bp)<br /> <a href="javascript:void(0);"
																	onclick="setPEQSampleSize(this);"><img
																	src="${global_image_url}/deng.jpg" border="0" /> </a>
															</center>
														</td>
														<td width="19" bgcolor="#FFFFFF">&nbsp;</td>
													</tr>
													<%	int plateByRowNums=1; 
													  	String Position = "A";
														while (plateByRowNums<97) {
															if(plateByRowNums%8==1) {
																Position = "A"+((plateByRowNums/8)+1);
															} else if(plateByRowNums%8==2) {
																Position = "B"+((plateByRowNums/8)+1);
															} else if(plateByRowNums%8==3) {
																Position = "C"+((plateByRowNums/8)+1);
															} else if(plateByRowNums%8==4) {
																Position = "D"+((plateByRowNums/8)+1);;
															} else if(plateByRowNums%8==5) {
																Position = "E"+((plateByRowNums/8)+1);
															} else if(plateByRowNums%8==6) {
																Position = "F"+((plateByRowNums/8)+1);
															} else if(plateByRowNums%8==7) {
																Position = "G"+((plateByRowNums/8)+1);
															} else if(plateByRowNums%8==0) {
																Position = "H"+(plateByRowNums/8);
															}
													%>
														<tr>
														<td bgcolor="#FFFFFF"><center>
																<%=Position %><input type="hidden" value="<%=Position %>" name="num_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" />
															</center>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_name_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 100px;" value="" />
														</td>
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_SAMPLE_TYPE'] != null">
															<select name="sample_type_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" 
																id="sample_type_<%=dsPlateDetailNum%>_<%=plateByRowNums%>"
																style="width:100px;">
																<s:iterator value="dropDownMap['DS_SAMPLE_TYPE']" id="smpTypeMap">
																<option value="${smpTypeMap.text}">${smpTypeMap.value}</option>
																</s:iterator>
															</select>
														</s:if>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_conc_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 50px;" value="" />
														</td>
														<td bgcolor="#FFFFFF"><input class="sInput" 
															type="checkbox" name="conc_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 40px;" />
														</td>
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_PRIMER_TYPE'] != null">
															<select name="primer_type_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" 
																id="primer_type_<%=dsPlateDetailNum%>_<%=plateByRowNums%>"
																style="width:100px;" onchange="selectPrimerName(this);">
																<option value="0">Select Type...</option>
																<s:iterator value="dropDownMap['DS_PRIMER_TYPE']" id="primerTypeMap">
																<option value="${primerTypeMap.text}">${primerTypeMap.value}</option>
																</s:iterator>
															</select>
														</s:if>
														</td>
														<td bgcolor="#FFFFFF">
															<input class="NFText myplist" 
																type="text" name="primer_name_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 100px;" value="" />
														</td>
														<td bgcolor="#FFFFFF"><input type="checkbox"
															name="pwrwer_read_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" class="sInput" />
														</td>
														<td bgcolor="#FFFFFF">
															<input type="checkbox" name="data_analysis_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" class="sInput"
																style="width: 30px;" onclick="referenceSequence(this);" 
																value="1" />
															<span id="span_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="display: none;">
																<input class="NFText" type="text" onblur="hideSpan('<%=dsPlateDetailNum%>_<%=plateByRowNums%>');" 
																	name="ref_seq_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="height: 50px;" value="">
															</span>	
														<td bgcolor="#FFFFFF">
														<s:if test="dropDownMap['DS_SPECIAL_REQUEST'] != null">
															<select name="notes_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" 
																id="notes_<%=dsPlateDetailNum%>_<%=plateByRowNums%>"
																style="width:107px;">
																<option value="0">Select Type...</option>
																<s:iterator value="dropDownMap['DS_SPECIAL_REQUEST']" id="notesMap">
																<option value="${notesMap.text}">${notesMap.value}</option>
																</s:iterator>
															</select>
														</s:if>
														</td>
														<td bgcolor="#FFFFFF"><input class="NFText"
															type="text" name="sample_size_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" style="width: 40px;" value="" />
														</td>
														<td bgcolor="#FFFFFF"><img
															src="${global_image_url}/x.jpg" onclick="delPTr(this);" />
															<input type="hidden" name="sessItemKey_<%=dsPlateDetailNum%>_<%=plateByRowNums%>" value="">
														</td>
													</tr>	
												<%plateByRowNums++;}%>
												</table>
											</td>
										</tr>
										</table>
										</s:else>
										<!--Two Plate End-->
										<table border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td><input type="hidden" name="plate_id_nums"
													id="plate_id_nums" value="0" /><input type="hidden"
													name="plate_item_nums" id="plate_item_nums" /><input
													type="hidden" name="op" value="add_plate" /><input
													type="image" src="${global_image_url}/imgSave.gif" />
												</td>
											</tr>
										</table>
										</form>
										<!--Plate Detail End-->
										<div class="tube_line" id="copy_plate" style="display: none;">
											<table border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="210"></td>
												</tr>
												<tr>
													<td align="left" valign="top">
														<div class="plate_new" onclick="showDetail(this)"></div>
													</td>
												</tr>
												<tr>
													<td>*Click on plate for more information<br /> <a
														href="javascript:void(0);" onclick="removePlate(this);">(Remove)</a>
													</td>
												</tr>
											</table>
										</div>
										<table style="display: none" id="copy_p_tr">
											<tr>
												<td bgcolor="#FFFFFF">&nbsp;</td>
												<td bgcolor="#FFFFFF">&nbsp;</td>
												<td bgcolor="#FFFFFF">
												<s:if test="dropDownMap['DS_SAMPLE_TYPE'] != null">
									           		<s:select cssStyle="width:100px;" cssClass="width:100px;"
									           			  name="" 
											   			  list="dropDownMap['DS_SAMPLE_TYPE']" 
											   			  listKey="text" 
											   			  listValue="value">
											   	 	</s:select>
											   	</s:if>
												</td>
												<td bgcolor="#FFFFFF">&nbsp;</td>
												<td bgcolor="#FFFFFF">&nbsp;</td>
												<td bgcolor="#FFFFFF">
												<s:if test="dropDownMap['DS_PRIMER_TYPE'] != null">
									           		<s:select cssStyle="width:100px;" cssClass="width:100px;"
											   			  list="dropDownMap['DS_PRIMER_TYPE']" 
											   			  listKey="text" 
											   			  listValue="value" 
											   			  headerKey="0"
											   			  headerValue="Select Type..."
											   			  onchange="selectPrimerName(this);">
											   	 	</s:select>
											   	</s:if>
											   	</td>
												<td bgcolor="#FFFFFF">&nbsp;</td>
												<td bgcolor="#FFFFFF">&nbsp;</td>
												<td bgcolor="#FFFFFF">&nbsp;</td>
												<td bgcolor="#FFFFFF">
												<s:if test="dropDownMap['DS_SPECIAL_REQUEST'] != null">
									           		<s:select cssStyle="width:107px;" cssClass="custClon"
											   			  list="dropDownMap['DS_SPECIAL_REQUEST']" 
											   			  listKey="text" 
											   			  listValue="value" 
											   			  headerKey="0"
											   			  headerValue="Select Type...">
											   	 	</s:select>
											   	</s:if>
												</td>
												<td bgcolor="#FFFFFF">&nbsp;</td>
												<td bgcolor="#FFFFFF"><img
													src="${global_image_url}/x.jpg" onclick="delPTr(this);" />
												</td>
											</tr>
										</table>
										<!--copy plate detail start-->
										<div id="plate_detail" style="display: none;">
											<table border="0" cellspacing="0" cellpadding="0" name="tab_detail">
												<tr>
													<td>Plate Name*</td>
													<td><input name="p_name" class="NFText"
														style="width: 100px;" />
													</td>
													<td colspan="4">Set the effective range of <img
														src="${global_image_url}/deng.jpg"> and <img
														src="${global_image_url}/down.jpg"> : rows <input
														class="NFText" style="width: 30px;" value="1"
														name="p_range_from" />to<input class="NFText"
														style="width: 30px;" value="96" name="p_range_to" />.</td>
												</tr>
												<tr>
													<td width="75">Plate Layout</td>
													<td><select name="plate_layout" class="custClon"
														onchange="calcNums(this);">
															<option selected="selected" value="1">By Rows</option>
															<option value="2">By Columins</option>
													</select></td>
													<td width="215">&nbsp;</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td><span class="subTitle">Total reactions:</span>
													</td>
												</tr>
												<tr>
													<td><input type="image" src="${global_image_url}/imgSave.gif"/>
													</td>
												</tr>
												<tr>
													<td colspan="6"><table border="0" cellpadding="0"
															cellspacing="1" bgcolor="#CCCCCC" name="plate_table">
															<tr>
																<td width="45" bgcolor="#FFFFFF"><center>Well
																		Position</center>
																</td>
																<td width="70" bgcolor="#FFFFFF" valign="bottom"
																	onmouseover="create(this,'Please type the sample name as you want to name it, maximum 8 characters, use only letters and numbers, do not use space, this is the name that you will use to label your tubes or plates.');"
																	style="cursor: pointer;" onMouseOut="del();"><center>
																		Sample Name*<br /> <a href="javascript:void(0);"
																			onclick="setPEQSampleName(this);"><img
																			src="${global_image_url}/deng.jpg" border="0" /> </a>&nbsp;&nbsp;<a
																			href="javascript:void(0);"
																			onclick="setPPSSampleName(this);"><img
																			src="${global_image_url}/down.jpg" border="0" /> </a>
																	</center>
																</td>
																<td width="70" valign="bottom" bgcolor="#FFFFFF"
																	onmouseover="create(this,'Please choose from Plasmid, Purified PCR product and Unpurified PCR product.');"
																	style="cursor: pointer;" onMouseOut="del();"><center>
																		Sample Type*<br /> <a href="javascript:void(0);"
																			onclick="setPEQSampleType(this);"><img
																			src="${global_image_url}/deng.jpg" border="0" /> </a>
																	</center>
																</td>
																<td width="70" valign="bottom" bgcolor="#FFFFFF"
																	onmouseover="create(this,'Please enter your sample concentration by ng/&micro;l, please follow our sample submission guidelines to dilute your sample, if you are not sure about the concentration of your sample, choose Concentration Measurement option and we will measure it for you.');"
																	style="cursor: pointer;" onMouseOut="del();"><center>
																		Sample Conc.<br /> ng/&micro;l<br /> <a
																			href="javascript:void(0);"
																			onclick="setPEQSampleConc(this);"><img
																			src="${global_image_url}/deng.jpg" border="0" /> </a>
																	</center>
																</td>
																<td width="70" bgcolor="#FFFFFF" valign="bottom"
																	onmouseover="create(this,'If this option is chosen, Genscript will perform concentration analysis on your sample and make dilution before loading it to reaction to make sure best reaction condition is reached, additional charges apply, see details in Pricing, terms and conditions page.');"
																	style="cursor: pointer;" onMouseOut="del();"><center>
																		Concentration Measurement<br /> <a
																			href="javascript:void(0);"
																			onclick="setPEQConc(this);"><img
																			src="${global_image_url}/deng.jpg" border="0" /> </a>
																	</center>
																</td>

																<td width="70" valign="bottom" bgcolor="#FFFFFF"
																	onmouseover="create(this,'Please choose from premixed, standard or enclosed primers, if you already added primers to the samples, please select Premixed, if you would like to use the large selection of our Universal primers free of charge, select Standard, if you provide your own primers in a separate tube, please select Enclosed, please follow our sample submission guidelines for best primer concentration.');"
																	style="cursor: pointer;" onMouseOut="del();"><center>
																		Primer Type*<br /> <a href="javascript:void(0);"
																			onclick="setPEQPrimerType(this);"><img
																			src="${global_image_url}/deng.jpg" border="0" /> </a>
																	</center>
																</td>
																<td width="70" valign="bottom" bgcolor="#FFFFFF"
																	onmouseover="create(this,'Please enter your primer name, maximum 8 characters, use only letters and numbers, do not use space, this is the name you will use to label your primer tubes; if you select to use Genscriptstandard primers, choose the primer from dropdown list.');"
																	style="cursor: pointer;" onMouseOut="del();"><center>
																		Primer Name*<br /> <a href="javascript:void(0);"
																			onclick="setPEQPrimerName(this);"><img
																			src="${global_image_url}/deng.jpg" border="0" /> </a>&nbsp;&nbsp;<a
																			href="javascript:void(0);"
																			onclick="setPPSPrimerName(this);"><img
																			src="${global_image_url}/down.jpg" border="0" /> </a>
																	</center>
																</td>
																<td width="42" valign="bottom" bgcolor="#FFFFFF"
																	onmouseover="create(this,'If this option is chosen, Genscript will try different conditions to read your sample should it fails on first try, additional charges apply, see details in Pricing, terms and conditions page');"
																	style="cursor: pointer;" onMouseOut="del();"><center>
																		Power Read<br /> <a href="javascript:void(0);"
																			onclick="setPEQPorwerRead(this);"><img
																			src="${global_image_url}/deng.jpg" border="0" /> </a>
																	</center>
																</td>
																<td width="46" valign="bottom" bgcolor="#FFFFFF"
																	onmouseover="create(this,'If this option is chosen, Genscript will analyze your sequencing result, provide alignment and deliver the contig, you may provide reference sequence for the alignment by copy it into the box, additional charges apply, see details in Pricing, terms and conditions page');"
																	style="cursor: pointer;" onMouseOut="del();"><center>
																		<a href="#"
																			style="color: #000; text-decoration: none;">Data
																			Analysis</a>
																	</center>
																</td>
																<td width="80" valign="bottom" bgcolor="#FFFFFF"
																	onmouseover="create(this,'Please let us know if the sequence need any special handling or it is known to be difficult to sequence, Genscript have developed special technology to work around known issues such as high GC content or secondary structures.');"
																	style="cursor: pointer;" onMouseOut="del();"><center>
																		Special Request<br /> <a href="javascript:void(0);"
																			onclick="setPEQNotes(this);"><img
																			src="${global_image_url}/deng.jpg" border="0" /> </a>
																	</center>
																</td>
																<td width="50" valign="bottom" bgcolor="#FFFFFF"
																	onmouseover="create(this,'Please provide us the length of your template in base pairs (bp), this helps us in choosing the right reaction condition in order to get best reading');"
																	style="cursor: pointer;" onMouseOut="del();"><center>
																		Template size(bp)<br /> <a href="javascript:void(0);"
																			onclick="setPEQSampleSize(this);"><img
																			src="${global_image_url}/deng.jpg" border="0" /> </a>
																	</center>
																</td>
																<td width="19" bgcolor="#FFFFFF">&nbsp;</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<!--copy plate detail end-->
										</div>
										</div>
										<!--Plate Entry End-->
										</div>
										</div>
										</div>
										</div>
										</div>
										</s:if>

<script language="javascript" type="text/javascript"
	src="${global_js_url}quoteorder/order_quotation_sequencing_dss.js"></script>
<script>
	function refreshInput(obj, boxId) {
		var selectedIndexValue = obj.selectedIndex;
		document.getElementById(boxId).value = obj.options[selectedIndexValue].text;
	}
</script>										