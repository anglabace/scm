<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div style="width: 960px; height: 360px; overflow-x: auto; overflow-y: auto;">
	<table width="850" border="0" cellpadding="0" cellspacing="0"
		class="General_table" style="margin-left: 35px; align: center">
		<tr>
			<td colspan="2"><font
				style="color: black; font-size: 15px; font-weight: 800; padding-right: 20px;">
					ORF Cloning </font> (* Required fields)<br /></td>
		</tr>
		<tr>
			<td colspan="2">The GenPool&trade; ORF clones database contains
				over 2.55 million open reading frames (ORFs).<br /> Find and order
				your ORF of interest!<br /></td>
		</tr>
		<tr>
			<td colspan="2"><font style="font-size: 13px; font-weight: 700;">Search:</font>
			</td>
		</tr>
		<tr>
			<th width="30%">Search By Accession Number (eg., NM_005098):</th>
			<td width="70%"><input name="search_accessionNo" id="search_accessionNo"
				type="text" class="NFText" size="25" /> <input name="search"
				type="button" id="searchOrfCloneTrigger" class="style_botton"
				value="Go" /></td>
		</tr>
		<tr>
			<td colspan="2"><br />
			<font style="font-size: 13px; font-weight: 700;">ORF Information:</font>
			</td>
		</tr>
	</table>
<form id="orfCloneForm" method="post">
	<input type="hidden" name="itemId" />	
	<div id="orfCloneFormItemId" style="display: none;">${itemId}</div>
	<input type="hidden" name="orfClone_cloning_itemId" />
	<input type="hidden" name="orfClone_pla_itemId" />
	<table width="850" border="0" cellpadding="0" cellspacing="0" id="orfCloneTable"
		class="General_table" style="margin-left: 35px; align: center">
		<tbody id="orfCloneTbody">
			<tr class="list_table">
				<th valign="middle" align="center"><input type="checkbox" id="serviceAllCheck" name="serviceAllCheck"></th>
				<th valign="middle" align="center">Gene Information<br>(Accession No.)</th>
				<th valign="middle" align="center">Sequence</th>
				<th valign="middle" align="center">Price</th>
				<th valign="middle" align="center" style="cursor: pointer;">Subcloning into Other Vector</th>
				<th valign="middle" align="center" style="cursor: pointer;">Cloning Detail</th>
				<th valign="middle" align="center">Bundle Discounted <br>Subcloning Price</th>
			</tr>
		</tbody>
	</table>
	<!-- Plasmid Preparation start -->
		<table width="850" border="0" cellpadding="0" cellspacing="0"
			class="General_table" style="margin-left: 35px; align: center">
			<tbody>
				<tr>
					<td colspan="2"><font style="font-size: 13px; font-weight: 700;">Plasmid Preparation:</font><br/></td>
				</tr>
				<tr>
					<td valign="top" align="left" width="6%">&nbsp;</td>
					<td width="97%">
						<input type="radio" value="Standard delivery"
							name="pla_type_orf">Standard delivery: 10 ug (Free of charge)
					</td>
				</tr>
				<tr>
					<td valign="top" align="left" width="6%">&nbsp;</td>
					<td>
						<input type="radio" checked="checked" value="Other" name="pla_type_orf">
						<span>Custom plasmid preparation:</span> 
						<select id="prepWeightSel">
							<option value="100 ug">100 ug</option>
							<option value="200 ug">200 ug</option>
							<option value="500 ug">500 ug</option>
							<option value="1 mg">1 mg</option>
							<option value="2 mg">2 mg</option>
							<option value="10 mg">10 mg</option>
							<option value="20 mg">20 mg</option>
							<option value="50 mg">50 mg</option>
							<option value="100 mg">100 mg</option>
							<option value="200 mg">200 mg</option>
							<option value="500 mg">500 mg</option>
							<option value="1000 mg">1000 mg</option>
							<option value="1500 mg">1500 mg</option>
							<option value="2000 mg">2000 mg</option>
							<option value="Other">Other</option>
						</select>
						<s:hidden name="prepWeightStr" id="prepWeightStr"></s:hidden>
				           <input id="txt_04" type="text" class="NFText" size="20" style="display:none;"/>
				           <span id="prepUom" style="display:none;">mg</span>
					</td>
				</tr>
				<tr>
					<td valign="top" align="left" width="6%">&nbsp;</td>
					<td width="97%">&nbsp;</td>
				</tr>
				<tr>
					<td valign="top" align="left" width="6%">&nbsp;</td>
					<td width="97%">
						<span style="text-align: center; float: left;">Quality grade: </span>
						<div style="text-align: center; float: left;">
							<a target="_blank" style="color: #3385CF; cursor: pointer"
								  onclick="quality_grade_help()">Help</a>
						</div>
					</td>
				</tr>
				<tr>
					<td valign="top" align="left" width="6%">&nbsp;</td>
					<td width="97%">
						<input type="radio" value="Research Grade" name="qualityGrade">
							<span>Research Grade&nbsp;&nbsp;</span><br/> 
						<input type="radio" value="SC Grade"
							checked="checked" name="qualityGrade"> 
							<span>SC Grade (Endotoxin level &lt;= 0.03 EU/μg)&nbsp;&nbsp;</span>
							<span style="color: green;" id="recommed_grade">[GenScript recommended]</span><br/> 
						<input type="radio" value="Advanced SC Grade"
							name="qualityGrade"> 
							<span>Advanced SC Grade (Endotoxin level &lt;= 0.005 EU/μg)&nbsp;&nbsp;</span>
					</td>
				</tr>
				<tr>
					<td valign="top" align="left" width="6%">Comments:&nbsp;</td>
					<td><textarea class="content_textarea2" id="comment" name="comment"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input name="save_orf_clone_btn" type="button" value="Save" class="style_botton"/></td>
				</tr>
			</tbody>
		</table><!-- Plasmid Preparation end -->
	</form>
</div>
<!-- copy tr start -->
<table id="copy_orf_tr" style="display: none;">
	<tr class="list_table">
		<td><input type="checkbox" name="service_check_orfclone" value="" checked="checked">
		</td>
		<td><span name="accessionTd">&nbsp;</span></td>
		<td><span name="sequenceTypeTd">&nbsp;</span></td>
		<td><span name="priceTd">&nbsp;</span></td>
		<td><select name="tgtVector" onChange="orf_detail_vector_nameChange(this)">
				<s:iterator value="vectorList" id="orfVector">
				<option value="${vectorName}" clonesite="${cloningSites}" <c:if test="${vectorName == 'pUC57'}"> selected="selected"</c:if>>${vectorNameShow}</option>
				</s:iterator>
			</select>
		<br>&nbsp;</td>
		<td><div><form id="orfClone_cloning_Form" name="orfClone_cloning_Form" method="post">
				<table name="other_vector" style="display: none;">
					<tbody>
						<tr>
							<td valign="middle" align="left" width="27%"><span class="important">*</span>Vector name:</td>
							<td valign="middle" align="left"><input type="text" class="NFText" size="20"
								name="tgtVectorOther" id="tgtVectorOther">
							</td>
						</tr>
						<tr>
							<td valign="middle" align="left"><span class="important">*</span>Vector size (<b>kb</b>):</td>
							<td valign="middle" align="left"><input type="text" name="tgtVectorSize" 
								id="tgtVectorSize" class="NFText" size="20" onkeypress="return vectorSizeValid(event);">
							</td>
						</tr>
						<tr>
							<td valign="middle" align="left"><span class="important">*</span>Resistance:</td>
							<td valign="middle" align="left">
								<s:select name="tgtResistance" 
									list="dropDownMap['VECTOR_RESISTANCE']" 
									listKey="value" 
									listValue="value" 
									headerKey="" 
									headerValue=""
									onchange="orf_detail_tgtResistance_change(this)"/>&nbsp;&nbsp;&nbsp;
									<input type="text" id="tgtResistanceOther" 
										name="tgtResistanceOther" style="display: none;" 
										class="NFText" size="20" />       
							</td>
						</tr>
						<tr>
							<td valign="middle" align="left"><span class="important">*</span>Copy number:</td>
							<td valign="middle" align="left">
								<input type="radio" name="tgtCopyNo" value="High" checked="checked" />
						             High
						             <input name="tgtCopyNo" type="radio" value="Low" />
						             Low
							</td>
						</tr>
						<tr>
							<td valign="middle" align="left">Vector sequence:</td>
							<td valign="middle" align="left">
								<textarea name="tgtVectorSeq" class="content_textarea2" style="width:240px;"></textarea>
							</td>
						</tr>
						<tr>
							<td valign="middle" align="left">Vector map:</td>
							<td valign="middle" align="left">
								<label><input type="file" name="upload" /></label>
					               <input name="cloningUploadBtn" type="button" class="style_botton" value="Upload" />
					               <input name="tgtMapDocFlag" type="hidden" id="" /><br/>
							</td>
						</tr>
					</tbody>
				</table>
				<table name="clone_site_sel">
					<tbody>
						<tr>
							<td valign="middle" align="left" rowspan="2" width="38%">Cloning Site:</td>
							<td valign="middle" align="left">5'&nbsp;
								<s:select name="tgtCloningSite"
									list="orfCloneSiteList"
									headerKey=""
									headerValue=""/>
							</td>
						</tr>
			
						<tr>
							<td valign="middle" align="left">3'&nbsp;
								<s:select name="tgtCloningSite3"
									list="orfCloneSiteList"
									headerKey=""
									headerValue=""/>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
			</div>
		</td>
		<td><span name="display_sub_price">&nbsp;</span></td>
	</tr>
</table>
<!-- copy tr end -->