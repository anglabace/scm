<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div class="dhtmlgoodies_aTab">
	<form id="stdPeptideForm">
		<input type="hidden" name="itemId" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="General_table">
			<tr>
				<th width="17%"><span class="important">*</span>Name</th>
				<td width="31%"><input name="name" type="text" class="NFText"
					size="25" />
				</td>
				<th width="23%">Quantity</th>
				<td width="29%"><s:select cssStyle="width:157px"
						name="quantity"
						list="specDropDownMap['PEPTIDE_QUANTITY'].dropDownDTOs"
						listKey="name" listValue="name"></s:select></td>
			</tr>

			<tr>
				<th>Purity</th>
				<td><s:select cssStyle="width:157px" name="purity"
						list="specDropDownMap['PEPTIDE_PURITY'].dropDownDTOs"
						listKey="name" listValue="name"></s:select>
				</td>
				<th>Sequence Length</th>
				<td><input name="seqLength" type="text" class="NFText"
					size="25" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<th><span class="important">*</span>Sequence</th>
				<td colspan="3">
				<textarea name="sequence" id="stSequence" class="content_textarea2"></textarea>
					<span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:50px;"
					onmouseover="tooltip.show('To enter the peptide sequence, please use only a single-letter code or a multiple-letter code specified in the Amino Acid Code Table. If a multiple-letter code is selected, please place braces {} around this code, e.g.AC{GLY}{ORN}{d-GLY}. ');" onmouseout="tooltip.hide();">
					<img src="images/qa.jpg" width="12" height="12" /></span> <input type="button"
					id="sequenceSelectTrigger" class="style_botton" value="Select" />
					<input id="sequenceValidateTrigger" type="button"
					class="style_botton" value="Validate" /><span id="warnSeqCheck"></span>
				</td>
			</tr>
			<tr>
				<th>&nbsp;</th>
				<td colspan="3">Convert all amino acids in the sequence to
					D-amino acids? <s:radio name="aminoChangeFlag"
						list="#{'Y':'Yes','N':'No'}" value="aminoChangeFlag"></s:radio>
				</td>
			</tr>
			<tr>
				<th valign="top">N-Terminal Modification</th>
				<td><s:select cssStyle="width:157px" name="nTerminal"
						list="specDropDownMap['N_TERMINAL'].dropDownDTOs" listKey="name"
						listValue="name" headerKey="" headerValue="No N-Terminal Modification"></s:select>
				</td>
				<th>C-Terminal Modification</th>
				<td><s:select cssStyle="width:157px" name="cTerminal"
						list="specDropDownMap['C_TERMINAL'].dropDownDTOs" listKey="name"
						listValue="name" headerKey="" headerValue="No C-Terminal Modification"></s:select>
				</td>
			</tr>
			<tr>
				<th>Other Modifications</th>
				<td><s:select cssStyle="width:157px"
						name="modification"
						list="specDropDownMap['MODIFICATION'].dropDownDTOs" listKey="id"
						listValue="name" headerKey="" headerValue="None"></s:select> <input
					type="button" id="modificationsAddTrigger1" class="style_botton"
					value="Add" /> <input type="button" id="modificationsViewTrigger1"
					class="style_botton2" value="View/Modify" />
				</td>
				<th>Aliquoting to</th>
				<td><input name="aliquoteVialQty" type="text" class="NFText"
					size="25" />vials</td>	
			</tr>
			<tr>
				<th>Disulfide Bridge</th>
				<td colspan="3"><input name="disulfideBridge" type="text"
					class="NFText" size="25" /> (e.g.,5-12)</td>
			</tr>
			<tr>
				<th>Comment</th>
				<td colspan="3"><input name="comments" type="text"
					class="NFText" size="80" />
				</td>
			</tr>
		</table>
		<input type="hidden" id="modifications1"></input>
		<div class="interest2" id="modificationDlg1" title="View/Modify"
			style="display: none;">
			<table class="General_table" border="0" cellpadding="0"
				cellspacing="0" height="180px;">
				<tr>
					<td valign="top">
						<ul id="modificationContainer1">
							<s:iterator value="modificationList" status="dex">
								<s:set name="index" value="#dex.getIndex()" />
								<li><input type="checkbox" name="modificationList"
									value="<s:property value="modificationList.get(#index)"/>"
									checked="checked"/>
								<s:property value="modificationList.get(#index)" />
								</li>
							</s:iterator>
						</ul></td>
				</tr>

				<tr>
					<td></td>
				</tr>
			</table>
			<div align="center">
				<input type="button" name="modificationsSaveTrigger1"
					id="modificationsSaveTrigger1" class="style_botton" value="Modify" />
				<input type="button" name="close" value="Cancel"
					class="style_botton"
					onclick="$('#modificationDlg1').dialog('close');" />
			</div>
		</div>
	</form>
</div>

<div class="dhtmlgoodies_aTab">
	<form id="batchPeptideForm">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="General_table">
			<tr>
				<th width="17%">Purity</th>
				<td width="31%"><s:select cssStyle="width:157px" name="purity"
						list="specDropDownMap['PEPTIDE_PURITY'].dropDownDTOs"
						listKey="name" listValue="name"></s:select>
				</td>
				<th width="23%">Quantity</th>
				<td width="29%"><s:select cssStyle="width:157px"
						name="quantity"
						list="specDropDownMap['PEPTIDE_QUANTITY'].dropDownDTOs"
						listKey="name" listValue="name"></s:select>
				</td>
			</tr>
			<tr>
				<th>Aliquoting to</th>
				<td><input name="aliquoteVialQty" type="text" class="NFText"
					size="25" />
				</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>Sequence</th>
				<td colspan="3"><textarea name="sequence"
						class="content_textarea2"></textarea> <span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:50px;"
					onmouseover="tooltip.show('Each line can contain only one peptide name and one sequence. The maximum 200 peptides can be entered once. The name and sequence must be seperated by a pipe (\'|\'), e.g., MyPeptide|ACGTGGGTTTCAAAA. Click the \'Formatting\' button, the default name (e.g., \'Peptide#1\', \'Peptide#2\'...) will be added for each sequence in quote and the blank spaces will be removed from the sequence. ');" onmouseout="tooltip.hide();">
					<img src="images/qa.jpg" width="12" height="12" /></span> <input name="formattingTrigger" id="formattingTrigger"
					type="button" class="style_botton" value="Formatting" />
				</td>
			</tr>
			<tr>
				<th valign="top">N-Terminal Modification</th>
				<td><s:select cssStyle="width:157px" name="nTerminal"
						list="specDropDownMap['N_TERMINAL'].dropDownDTOs" listKey="name"
						listValue="name" headerKey="" headerValue="No N-Terminal Modification"></s:select>
				</td>
				<th>C-Terminal Modification</th>
				<td><s:select cssStyle="width:157px" name="cTerminal"
						list="specDropDownMap['C_TERMINAL'].dropDownDTOs" listKey="name"
						listValue="name" headerKey="" headerValue="No C-Terminal Modification"></s:select>
				</td>
			</tr>
			<tr>
				<th>Other Modifications</th>
				<td colspan="3"><s:select cssStyle="width:157px"
						name="modification"
						list="specDropDownMap['MODIFICATION'].dropDownDTOs" listKey="name"
						listValue="name" headerKey="" headerValue="None"></s:select> <input
					type="button" id="modificationsAddTrigger2" class="style_botton"
					value="Add" /> <input type="button" id="modificationsViewTrigger2"
					class="style_botton2" value="View/Modify" />
				</td>
			</tr>
			<tr>
				<th>Disulfide Bridge</th>
				<td colspan="3"><input name="disulfideBridge" type="text"
					class="NFText" size="25" /> (e.g.,5-12)</td>
			</tr>
			<tr>
				<th>Comment</th>
				<td colspan="3"><input name="comments" type="text"
					class="NFText" size="80" />
				</td>
			</tr>
			<tr>
				<td height="40" colspan="4" align="center" valign="bottom"><input
					id="batchPeptideCreateTrigger" name="batchPeptideCreateTrigger"
					type="button" class="style_botton" value="Create" />
				</td>
			</tr>
		</table>
		<input type="hidden" id="modifications2"></input>
		<div class="interest2" id="modificationDlg2" title="View/Modify"
			style="display: none;">
			<table class="General_table" border="0" cellpadding="0"
				cellspacing="0" height="180px;">
				<tr>
					<td valign="top">
						<ul id="modificationContainer2">
						</ul></td>
				</tr>

				<tr>
					<td></td>
				</tr>
			</table>
			<div align="center">
				<input type="button" name="modificationsSaveTrigger2"
					id="modificationsSaveTrigger2" class="style_botton" value="Modify" />
				<input type="button" name="close" value="Cancel"
					class="style_botton"
					onclick="$('#modificationDlg2').dialog('close');" />
			</div>
		</div>
	</form>
</div>

<div class="dhtmlgoodies_aTab">
	<form id="libraryPeptideForm">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="General_table">
			<!--
	<tr>
		<th width="17%">Catalog No</th>
		<td width="31%"><s:radio name="peptide.catalogNo" id="libraryCatalogNo"
			list="#{'SC1177':'SC1177','SC1487':'SC1487'}"></s:radio></td>
		<th width="23%">&nbsp;</th>
		<td width="29%">&nbsp;</td>
	</tr>
	-->
			<tr>
				<th width="17%">Name</th>
				<td width="31%"><input name="peptide.name" type="text"
					class="NFText" size="25" />
				</td>
				<th width="23%">Quantity</th>
				<td width="29%"><s:select cssStyle="width:157px"
						name="peptide.quantity" list="dropDownMap['PEPTIDE_QUANTITY']"
						listKey="value" listValue="value"></s:select>
				</td>
			</tr>
			<tr>
				<th>Purity</th>
				<td><s:select cssStyle="width:157px" name="peptide.purity"
						list="dropDownMap['PEPTIDE_PURITY']" listKey="value"
						listValue="value"></s:select>
				</td>
				<th>Modification</th>
				<td><s:select cssStyle="width:157px"
						name="peptide.modification"
						list="dropDownMap['PEPTIDE_LIB_MOD']" listKey="value"
						listValue="value" headerKey="" headerValue=""></s:select>
				</td>
			</tr>
			<tr>
				<th>QC Report</th>
				<td><s:select cssStyle="width:157px" name="peptide.qcReport"
						list="dropDownMap['PEPTIDE_QC_REPORT']" listKey="value"
						listValue="value"></s:select>
				</td>
			</tr>
			<tr>
				<th>Sequence</th>
				<td colspan="3"><textarea name="peptide.sequence"
						id="libSequence" class="content_textarea2"></textarea> <img
					src="images/qa.jpg"
					title='Each line can contain only one peptide sequence. The maximum 500 sequences can be entered.'
					width="12" height="12" />
				</td>
			</tr>
			<tr>
				<th valign="top">Sequence Document</th>
				<td colspan="3"><label> <input type="file"
						name="upload" id="libPeptideFile" /> (.xls, .xlsx, .txt, .csv)</label>
				</td>
			</tr>
			<tr>
				<th>Comment</th>
				<td colspan="3"><input name="peptide.comments" type="text"
					class="NFText" size="80" />
				</td>
			</tr>
			<tr>
				<td height="86" colspan="4" align="center" valign="bottom"><input
					id="libPeptideCreateTrigger" type="button" class="style_botton"
					value="Create" /> <input id="libPeptideUpdateTrigger"
					type="button" class="style_botton" value="Update" />
				</td>
			</tr>
		</table>
	</form>
</div>

<div class="dhtmlgoodies_aTab">
	<form id="arrayPeptideForm">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="General_table">
			<tr>
				<th width="17%">Name</th>
				<td width="31%"><input name="peptide.name" type="text"
					class="NFText" size="25" />
				</td>
				<th width="23%">Quantity</th>
				<td width="29%"><input name="peptide.quantity" type="text"
					class="NFText" size="25" /> nmol</td>
			</tr>
			<tr>
				<th>Purity</th>
				<td><s:select cssStyle="width:157px" name="peptide.purity"
						list="specDropDownMap['PEPTIDE_PURITY_ARRAY'].dropDownDTOs"
						listKey="name" listValue="name"></s:select>
				</td>
				<th>Modification</th>
				<td><s:select cssStyle="width:157px"
						name="peptide.modification"
						list="dropDownMap['PEPTIDE_ARRAY_MOD']" listKey="value"
						listValue="value" headerKey="" headerValue=""></s:select>
				</td>
			</tr>
			<tr>
			<th>QC Report</th>
		<td><s:select cssStyle="width:157px" name="peptide.qcReport" list="dropDownMap['PEPTIDE_QC_REPORT_ARRAY']" listKey="value" listValue="value"></s:select></td>
				<th>Synthesis Membrane</th>
				<td><s:select cssStyle="width:157px" name="peptide.synMembrane"
						list="dropDownMap['PEPTIDE_SYNTHESIS_MEMBRANE']" listKey="value"
						listValue="value"></s:select>
				</td>
			</tr>
			<tr>
				<th>Delivert Format</th>
				<td><s:select cssStyle="width:157px"
						name="peptide.deliveryFormat"
						list="dropDownMap['PEPTIDE_DELIVERY_FORMAT']" listKey="value"
						listValue="value"></s:select>
				</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>Sequence</th>
				<td colspan="3"><textarea name="peptide.sequence"
						id="arraySequence" class="content_textarea2"></textarea> <img
					src="images/qa.jpg"
					title='Each line can contain only one peptide sequence. The maximum 500 sequences can be entered.'
					width="12" height="12" />
				</td>
			</tr>
			<tr>
				<th valign="top">Sequence Document</th>
				<td colspan="3"><label> <input type="file"
						name="upload" id="arrayPeptideFile" /> (.xls, .xlsx, .txt, .csv)</label>
				</td>
			</tr>
			<tr>
				<th>Comment</th>
				<td colspan="3"><input name="peptide.comments" type="text"
					class="NFText" size="80" />
				</td>
			</tr>
			<tr>
				<td height="42" colspan="4" align="center" valign="bottom"><input
					id="arrayPeptideCreateTrigger" name="arrayPeptideCreateTrigger"
					type="button" class="style_botton" value="Create" /> <input
					id="arrayPeptideUpdateTrigger" type="button" class="style_botton"
					value="Update" />
				</td>
			</tr>
		</table>
	</form>
</div>


<script type="text/javascript">
	
</script>
<script language="javascript" type="text/javascript">
	$(function() {
		/********************** set other modification of add/remove event *****************************/
		$('#modificationsAddTrigger1')
				.click(
						function() {
							var modificationSel = $('#stdPeptideForm').find(
									"[id='modification']").get(0);
							var modificationOpt = modificationSel.options[modificationSel.selectedIndex];
							var newModification = '<li><input  type="checkbox" value="' + modificationOpt.text + '" name="modificationList" checked="true"/>'
									+ modificationOpt.text + '</li>';
							if (modificationOpt.value == '')
								return;

							var existFlag = false;
							$('#modificationContainer1 :input')
									.each(
											function() {
												if (this.value == modificationOpt.text) {
													existFlag = true;
												}
											});
							if (existFlag === false)
								$('#modificationContainer1').append(
										newModification);
							else
								alert("The  Modification is duplicated!");
							var modificationStr = "";
							$('#modificationContainer1 :input')
									.each(
											function() {
												if (this.checked == true) {
													modificationStr += this.value
															+ ",";
												}
											});
							$('#modifications1').val(
									modificationStr.substring(0,
											modificationStr.length - 1));
							//alert($('#modifications1').val());
						});

		$('#modificationsAddTrigger2')
				.click(
						function() {
							var modificationSel = $('#batchPeptideForm').find(
									"[id='modification']").get(0);
							var modificationOpt = modificationSel.options[modificationSel.selectedIndex];
							var newModification = '<li><input  type="checkbox" value="' + modificationOpt.text + '" name="modificationList" checked="true"/>'
									+ modificationOpt.text + '</li>';
							if (modificationOpt.value == '')
								return;

							var existFlag = false;
							$('#modificationContainer2 :input')
									.each(
											function() {
												if (this.value == modificationOpt.text) {
													existFlag = true;
												}
											});
							if (existFlag === false)
								$('#modificationContainer2').append(
										newModification);
							else
								alert("The  Modification is duplicated!");
							var modificationStr = "";
							$('#modificationContainer2 :input')
									.each(
											function() {
												if (this.checked == true) {
													modificationStr += this.value
															+ ",";
												}
											});
							$('#modifications2').val(
									modificationStr.substring(0,
											modificationStr.length - 1));
							//alert($('#modifications2').val());
						});

		$('#modificationsViewTrigger1').click(function() {
			$('#modificationDlg1').dialog("option", "open", function() {

			});
			$('#modificationDlg1').dialog('open');
		});
		$('#modificationsViewTrigger2').click(function() {
			$('#modificationDlg2').dialog("option", "open", function() {

			});
			$('#modificationDlg2').dialog('open');
		});

		$('#modificationsSaveTrigger1').click(
				function() {
					var modificationStr = "";
					$('#modificationContainer1 :input').each(function() {
						if (this.checked == true) {
							modificationStr += this.value + ",";
						}
					});
					//alert(modificationStr.substring(0, modificationStr.length-1));
					$('#modifications1').val(
							modificationStr.substring(0,
									modificationStr.length - 1));
					//$('#modification').val(modificationStr.substring(0, modificationStr.length-1));
					$('#modificationDlg1').dialog('close');
				});

		$('#modificationsSaveTrigger2').click(
				function() {
					var modificationStr = "";
					$('#modificationContainer2 :input').each(function() {
						if (this.checked == true) {
							modificationStr += this.value + ",";
						}
					});
					$('#modifications2').val(
							modificationStr.substring(0,
									modificationStr.length - 1));
					$('#modificationDlg2').dialog('close');
				});

        $('#batchPeptideCreateTrigger').click(function() {
            //批量的时候直接刷新itemList页面，所以要先保存主服务
            var tmpFlag = checkOtherPeptide();
            if (tmpFlag != true) {
                var rtFlag = saveStdPeptide("Y");
                if (rtFlag) {
                    saveBatchPeptide();
                }
            }
        });

		$('#libPeptideCreateTrigger').click(
				function() {
					var refType = getRefType("libraryPeptide", 0);
					saveLibraryPeptide(itemId, "libraryPeptide",
							"libraryPeptideForm", refType);
					//saveLibraryPeptide();
				});
		$('#arrayPeptideCreateTrigger').click(
				function() {
					var refType = getRefType("arrayPeptide", 0);
					saveArrayPeptide(itemId, "arrayPeptide",
							"arrayPeptideForm", refType);
				});

		$('#libPeptideUpdateTrigger').click(
				function() {
					var refType = getRefType("libraryPeptide", 0);
					emptyPeptide(itemId);
					updateLibraryPeptide(itemId, "libraryPeptide",
							"libraryPeptideForm", refType);
				});
		$('#arrayPeptideUpdateTrigger').click(
				function() {
					var refType = getRefType("arrayPeptide", 0);
					emptyPeptide(itemId);
					updateArrayPeptide(itemId, "arrayPeptide",
							"arrayPeptideForm", refType);
				});

		$(function() {
			$('#modificationDlg1').dialog({
				autoOpen : false,
				height : 310,
				width : 620,
				modal : true,
				bgiframe : true,
				buttons : {}
			});
			$('#modificationDlg2').dialog({
				autoOpen : false,
				height : 310,
				width : 620,
				modal : true,
				bgiframe : true,
				buttons : {}
			});
		});

	});
</script>
