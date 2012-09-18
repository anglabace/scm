<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<table width="950" border="0"  cellpadding="0" cellspacing="0" class="General_table">
	<tr>
		<th width="17%">
			<span class="important">*</span><s:if test='orderItem == null || orderItem.catalogNo != "SC1110"'>Antibody Name</s:if><s:else>Hybridoma cell name</s:else>
		</th>
		<td width="26%">
			<input id="antibodyName" name="antibodyName" type="text" class="NFText" size="25" />
		</td>
		<td width="57%">Comment&nbsp;&nbsp;&nbsp;<input id="antibodyComments" name="comments" type="text" class="NFText" size="80" value="${antibody.comments}" /></td>
	</tr>
	<tr>
		<th width="17%">Antigen Type</th>
		<td width="26%">
			<span class="css_b">
			  <select style="width:250px;" id="antigenType" name="antigenType">
			    <option value="GenScript Synthesized Peptide">GenScript Synthesized Peptide</option>
			    <option value="Customer Provided Peptide">Customer Provided Peptide</option>
			    <option value="Protein">Protein</option>
			    <option value="Other">Other</option>
			  </select>
			</span>
		</td>
		<td width="57%" id="newPeptideTd">
			<span class="css_b">
			  <input type="hidden" id="antibodyItemId" value="${antibodyItemId}" />
			  <s:hidden id="itemId" name="itemId"/>
			  <input type="button" class="style_botton" value="New" id="antibodyNewPeptideBtn" />
			</span>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" id="antibodyPeptideTable">
  <tr>
    <td colspan="3">
             <table width="950" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <td id="antibodyPeptideContainer">
                 <s:iterator value="peptideMap" status="mapStatus">
                 <c:if test="${value.status == 'CC' || value.status == 'CN'}">
                 <div name="peptideMapDiv" itemId="${key}">
                 	<form name="peptideForm">
                 	<div class="invoice_title" style="padding-top:0px;cursor: pointer;">
                 		<img src="${global_image_url}ar.gif" width="11" height="11" />
                 		<span id="peptideSeqTitleSpan_${key}">Peptide #${mapStatus.index + 1}-${antibody.antigenType}:</span>
                 	</div>
             		<div style="display:none;">
                       <table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table">
                         <tr>
                           <th width="17%">
                           	<span class="important">*</span>Name
                           </th>
                           <td width="31%">
                           		<input name="name" type="text" class="NFText" size="25" value="${value.name}" />
                           </td>
                           <th width="23%">Quantity</th>
                           <td width="29%">
	                           <s:select cssStyle="width:157px" name="quantity" list="specDropDownMap['PEPTIDE_QUANTITY'].dropDownDTOs" listKey="name" listValue="name" headerKey="" headerValue="None" value="value.quantity"></s:select>
                           </td>
                         </tr>
                         <tr>
                           <th>Purity</th>
                           <td>
                           		<s:select cssStyle="width:157px" name="purity" list="specDropDownMap['PEPTIDE_PURITY'].dropDownDTOs" listKey="name" listValue="name" headerKey="" headerValue="None" value="value.purity"></s:select>
                           	</td>
                           <th>Sequence Length</th>
                           <td>
                           <c:if test="${! empty value.sequence}">
                           		<input name="seqLength" type="text" class="NFText" size="25" value="${value.seqLength}" readonly="readonly"/>
                           </c:if><c:if test="${empty value.sequence}">
                           		<input name="seqLength" type="text" class="NFText" size="25" value="${value.seqLength}"/>
                           </c:if>
                           </td>
                         </tr>
                         <tr>
                           <th><span class="important">*</span>Sequence</th>
                           <td colspan="3">
	                             <textarea name="sequence" class="content_textarea2" id="${key}_sequence" >${value.sequence}</textarea>
	                             <span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:50px;" 
								 	onmouseover="tooltip.show('To enter the peptide sequence, please use only a single-letter code or a multiple-letter code specified in the Amino Acid Code Table. If a multiple-letter code is selected, please place braces {} around this code, e.g.AC{GLY}{ORN}{d-GLY}. ');" onmouseout="tooltip.hide();">
								 <img src="${global_image_url}qa.jpg"" width="12" height="12" /></span>
	                             <input id="antibodyPeptideSequenceSelectTrigger" type="button" class="style_botton" value="Select" />
	                             <input id="antibodyPeptideSequenceValidateTrigger" type="button" class="style_botton" value="Validate" />
	                             <span id="warnSeqCheck"></span>
                            </td>
                         </tr>
                         <tr>
                           <th>&nbsp;</th>
                           <td colspan="3">Convert all amino acids in the sequence to D-amino acids?
                            <s:radio name="aminoChangeFlag" list="#{'Y':'Yes','N':'No'}" value="value.aminoChangeFlag"></s:radio>
                           </td>
                         </tr>
                         <tr>
                           <th valign="top">N-Terminal Modification</th>
                           <td>
	                           <s:select cssStyle="width:157px;" name="NTerminal" list="specDropDownMap['N_TERMINAL'].dropDownDTOs" listKey="name" listValue="name" headerKey="" headerValue="None" value="value.NTerminal"></s:select>
                           </td>
                           <th>C-Terminal Modification</th>
                           <td>                           	
                           	<s:select cssStyle="width:157px;" name="CTerminal" list="specDropDownMap['C_TERMINAL'].dropDownDTOs" listKey="name" listValue="name" headerKey="" headerValue="None" value="value.CTerminal"></s:select>
                           </td>
                         </tr>
                         <tr>
                           <th>Other Modifications</th>
                           <td colspan="3">
                           	 <s:select cssStyle="width:157px;" name="" list="specDropDownMap['MODIFICATION'].dropDownDTOs" listKey="id" listValue="name" headerKey="" headerValue="None" value="value.modification"></s:select>
                           	 <input type="hidden" name="modification" value="${value.modification}" id="${key}_modification" />
                             <input id="modificationsAddTrigger" type="button" class="style_botton" value="Add" />
                             <input id="modificationsViewTrigger" type="button" class="style_botton2" value="ViewModify" />
                           </td>
                         </tr>
                         <tr>
                           <th>Disulfide Bridge</th>
                           <td colspan="3">
                           	<input name="disulfideBridge" type="text" class="NFText" size="25" value="${value.disulfideBridge}" />
                             (e.g.,5-12)
                            </td>
                         </tr>
                         <tr>
                           <th>Comment</th>
                           <td colspan="3">
                           	<input name="comments" type="text" class="NFText" size="80" value="${value.comments}" />
                           </td>
                         </tr>
          			    </table>
                     </div>
                     </form>
                  </div>
                 </c:if>
                 <c:if test="${value.status != 'CC' && value.status != 'CN'}">
                 <div name="peptideMapDiv" itemId="${key}" >
                 	<form name="peptideForm">
                 	<div class="invoice_title" style="padding-top:0px;cursor: pointer;">
                 		<img src="${global_image_url}ar.gif" width="11" height="11" />
                 		<span id="peptideSeqTitleSpan_${key}">Peptide #${mapStatus.index + 1}-${antibody.antigenType}:</span>
                 	</div>
             		<div style="display:none;">
                       <table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table">
                         <tr>
                           <th width="17%">
                           	<span class="important">*</span>Name
                           </th>
                           <td width="31%">
                           		<input name="name" type="text" class="NFText" size="25" value="${value.name}" />
                           </td>
                           <th width="23%">Quantity</th>
                           <td width="29%">
	                           <s:select cssStyle="width:157px" name="quantity" list="specDropDownMap['PEPTIDE_QUANTITY'].dropDownDTOs" listKey="name" listValue="name" headerKey="" headerValue="None" value="value.quantity"></s:select>
                           </td>
                         </tr>
                         <tr>
                           <th>Purity</th>
                           <td>
                           		<s:select cssStyle="width:157px" name="purity" list="specDropDownMap['PEPTIDE_PURITY'].dropDownDTOs" listKey="name" listValue="name" headerKey="" headerValue="None" value="value.purity"></s:select>
                           	</td>
                           <th>Sequence Length</th>
                           <td>
                           <c:if test="${! empty value.sequence}">
                           		<input name="seqLength" type="text" class="NFText" size="25" value="${value.seqLength}" readonly="readonly"/>
                           </c:if><c:if test="${empty value.sequence}">
                           		<input name="seqLength" type="text" class="NFText" size="25" value="${value.seqLength}"/>
                           </c:if>
                           </td>
                         </tr>
                         <tr>
                           <th><span class="important">*</span>Sequence</th>
                           <td colspan="3">
	                             <textarea name="sequence" class="content_textarea2" id="${key}_sequence" >${value.sequence}</textarea>
	                             <span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:50px;" 
								 	onmouseover="tooltip.show('To enter the peptide sequence, please use only a single-letter code or a multiple-letter code specified in the Amino Acid Code Table. If a multiple-letter code is selected, please place braces {} around this code, e.g.AC{GLY}{ORN}{d-GLY}. ');" onmouseout="tooltip.hide();">
								 <img src="${global_image_url}qa.jpg"" width="12" height="12" /></span>
	                             <input id="antibodyPeptideSequenceSelectTrigger" type="button" class="style_botton" value="Select" />
	                             <input id="antibodyPeptideSequenceValidateTrigger" type="button" class="style_botton" value="Validate" />
	                             <span id="warnSeqCheck"></span>
                            </td>
                         </tr>
                         <tr>
                           <th>&nbsp;</th>
                           <td colspan="3">Convert all amino acids in the sequence to D-amino acids?
                            <s:radio name="aminoChangeFlag" list="#{'Y':'Yes','N':'No'}" value="value.aminoChangeFlag"></s:radio>
                           </td>
                         </tr>
                         <tr>
                           <th valign="top">N-Terminal Modification</th>
                           <td>
	                           <s:select cssStyle="width:157px;" name="NTerminal" list="specDropDownMap['N_TERMINAL'].dropDownDTOs" listKey="name" listValue="name" headerKey="" headerValue="None" value="value.NTerminal"></s:select>
                           </td>
                           <th>C-Terminal Modification</th>
                           <td>                           	
                           	<s:select cssStyle="width:157px;" name="CTerminal" list="specDropDownMap['C_TERMINAL'].dropDownDTOs" listKey="name" listValue="name" headerKey="" headerValue="None" value="value.CTerminal"></s:select>
                           </td>
                         </tr>
                         <tr>
                           <th>Other Modifications</th>
                           <td colspan="3">
                           	 <s:select cssStyle="width:157px;" name="" list="specDropDownMap['MODIFICATION'].dropDownDTOs" listKey="id" listValue="name" headerKey="" headerValue="None" value="value.modification"></s:select>
                           	 <input type="hidden" name="modification" value="${value.modification}" id="${key}_modification" />
                             <input id="modificationsAddTrigger" type="button" class="style_botton" value="Add" />
                             <input id="modificationsViewTrigger" type="button" class="style_botton2" value="ViewModify" />
                           </td>
                         </tr>
                         <tr>
                           <th>Disulfide Bridge</th>
                           <td colspan="3">
                           	<input name="disulfideBridge" type="text" class="NFText" size="25" value="${value.disulfideBridge}" />
                             (e.g.,5-12)
                            </td>
                         </tr>
                         <tr>
                           <th>Comment</th>
                           <td colspan="3">
                           	<input name="comments" type="text" class="NFText" size="80" value="${value.comments}" />
                           </td>
                         </tr>
          			    </table>
                     </div>
                     </form>
                  </div>
                  </c:if>
                  </s:iterator>                
                 </td>
               </tr>
             </table>
    </td>
  </tr>
</table>
<div>
 <div>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
      <tr style="display: none;">
        <th width="17%" id="peptideSeqTh">Peptide Sequence</th>
        <td>
        	<textarea id="customSequence" name="customSequence" class="content_textarea2" style="width:422px;">${antibody.customSequence}</textarea>
        </td>
      </tr>
  </table>
 </div>
</div>
<s:if test="antibody != null && antibody.specialCatalogNoMap != null">
<div>
<div class="invoice_title" id="peptideSeqTitleDiv" style="cursor: pointer;"> </div>
<div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
	<s:iterator value="antibody.specialCatalogNoMap" status="specialCatalog">
	<s:if test="key == 'SC1088'">
		<tr>
        	<td><input type="checkbox" checked="checked" onclick= "this.checked=!this.checked"/> SC1088 pre-immune serum</td>
      	</tr>
	</s:if>
	<s:else>
		<tr><td>
	        <input type="checkbox" checked="checked" onclick= "this.checked=!this.checked"/> ${key} Western guaranteed package antigen peptide delivery</td>
	    </tr>
	</s:else>
	</s:iterator>
  	</table>
</div>
</div> 
</s:if>
 <div id="peptideExampleDiv" style="display: none;">
 	<form name="peptideForm">
	<div class="invoice_title" style="padding-top:0px;cursor: pointer;">
		<img src="${global_image_url}ad.gif" width="11" height="11" />
	  		<span id="peptideSeqTitleSpan0"></span>
  	</div>
	<div>
	        <table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table">
	          <tr>
	            <th width="17%">
	            	<span class="important">*</span>Name
	            </th>
	            <td width="31%">
	            		<input name="name" id="myName" onblur="changeAntiName();" type="text" class="NFText" size="25" value="${value.name}" />
	  </td>
	  <th width="23%">Quantity</th>
	  <td width="29%">
	   <s:select cssStyle="width:157px" name="quantity" list="specDropDownMap['PEPTIDE_QUANTITY'].dropDownDTOs" listKey="name" listValue="name" headerKey="" headerValue="None" value="value.quantity"></s:select>
	  </td>
	</tr>
	<tr>
	  <th>Purity</th>
	  <td>
	  		<s:select cssStyle="width:157px" name="purity" list="specDropDownMap['PEPTIDE_PURITY'].dropDownDTOs" listKey="name" listValue="name" headerKey="" headerValue="None" value="value.purity"></s:select>
	  	</td>
	  <th>Sequence Length</th>
	  <td>
	  		<c:if test="${! empty value.sequence}">
            	<input name="seqLength" type="text" class="NFText" size="25" value="${value.seqLength}" readonly="readonly"/>
            </c:if><c:if test="${empty value.sequence}">
            	<input name="seqLength" type="text" class="NFText" size="25" value="${value.seqLength}"/>
            </c:if>
	  </td>
	</tr>
	<tr>
	  <th><span class="important">*</span>Sequence</th>
	  <td colspan="3">
	     <textarea name="sequence" class="content_textarea2"></textarea>
	     <span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:50px;" 
			 onmouseover="tooltip.show('To enter the peptide sequence, please use only a single-letter code or a multiple-letter code specified in the Amino Acid Code Table. If a multiple-letter code is selected, please place braces {} around this code, e.g.AC{GLY}{ORN}{d-GLY}. ');" onmouseout="tooltip.hide();">
		 <img src="${global_image_url}qa.jpg" width="12" height="12" /></span>
	     <input id="antibodyPeptideSequenceSelectTrigger" type="button" class="style_botton" value="Select" />
	     <input id="antibodyPeptideSequenceValidateTrigger" type="button" class="style_botton" value="Validate" />
	     <span id="warnSeqCheck"></span>
	   </td>
	</tr>
	<tr>
	  <th>&nbsp;</th>
	  <td colspan="3">Convert all amino acids in the sequence to D-amino acids?
	   <s:radio name="aminoChangeFlag" list="#{'Y':'Yes','N':'No'}" value="aminoChangeFlag"></s:radio>
	  </td>
	</tr>
	<tr>
	  <th valign="top">N-Terminal Modification</th>
	  <td>
	   <s:select cssStyle="width:157px;" name="NTerminal" list="specDropDownMap['N_TERMINAL'].dropDownDTOs" listKey="name" listValue="name" headerKey="" headerValue="None" value="value.NTerminal"></s:select>
	  </td>
	  <th>C-Terminal Modification</th>
	  <td>                           	
	  	<s:select cssStyle="width:157px;" name="CTerminal" list="specDropDownMap['C_TERMINAL'].dropDownDTOs" listKey="name" listValue="name" headerKey="" headerValue="None" value="value.CTerminal"></s:select>
	  </td>
	</tr>
	<tr>
	  <th>Other Modifications</th>
	  <td colspan="3">
	  	  <s:select cssStyle="width:157px;" name="" list="specDropDownMap['MODIFICATION'].dropDownDTOs" listKey="id" listValue="name" headerKey="" headerValue="None"></s:select>
	  	  <input type="hidden" name="modification" value="" id="" />
          <input id="modificationsAddTrigger" type="button" class="style_botton" value="Add" />
          <input id="modificationsViewTrigger" type="button" class="style_botton2" value="ViewModify" />
	   </td>
	</tr>
       <tr>
         <th>Disulfide Bridge</th>
         <td colspan="3">
         	<input name="disulfideBridge" type="text" class="NFText" size="25" />
           (e.g.,5-12)
          </td>
       </tr>
       <tr>
         <th>Comment</th>
         <td colspan="3">
         	<input name="comments" type="text" class="NFText" size="80" />
         </td>
       </tr>
	</table>
	</div>
	</form>
 </div>

<div class="interest2" id="modificationDlg" title="View/Modify" style="display: none;">
	<table class="General_table" border="0" cellpadding="0" cellspacing="0" height="180px;">
		<tr>
			<td valign="top">
			<ul id="modificationContainer1"></ul>
			</td>
		</tr>
		<tr>
			<td>
			</td>
		</tr>
	</table>
	<div align="center">
		<input type="button" name="modificationsSaveTrigger1" id="modificationsSaveTrigger1" class="style_botton" value="Modify" /> 
		<input type="button" name="close" value="Cancel" class="style_botton" onclick="$('#modificationDlg').dialog('close');" />
	</div>
</div>

<script language="javascript" type="text/javascript">
$(function(){
/********************** set other modification of add/remove event *****************************/

	$("#peptideSeqTitleSpan_${itemId}").parent().trigger("click");
	$('[id="modificationsAddTrigger"]').live('click',function(){
			var modificationSel = $(this).prevAll("select").get(0);
			var modificationOpt = modificationSel.options[modificationSel.selectedIndex];
			if(modificationOpt.value == '')	{
				return;
			}
			var modificationHid = $(this).prev("input[name='modification']").get(0);
			var modification = modificationHid.value;
			var modificationArr = [];
			if(modification != ""){
				modificationArr = modification.split(",");
			}
			var existFlag = false;
			for(var i=0; i<modificationArr.length; i++){
				if( modificationArr[i] == modificationOpt.text){
					existFlag = true;
					break;
				}
			}
			if(existFlag === false){
				modificationArr.push(modificationOpt.text);
			}else{
				alert("The Modification is duplicated.");
			}
			modificationHid.value = modificationArr.toString();
	});
	
	$('[id="modificationsViewTrigger"]').live('click', function(){
		var modificationHid = $(this).prevAll("input[name='modification']").get(0);
		var modificationId = modificationHid.id;
		var modification = modificationHid.value;
		var modificationArr = [];
		if(modification != ""){
			modificationArr = modification.split(",");
		}
		var liStr = "";
		for(var i=0; i<modificationArr.length; i++){
			liStr += '<li><input  type="checkbox" value="' + modificationArr[i] + '" name="modificationList" checked="true"/>'+ modificationArr[i] +'</li>';
		}
		$("#modificationContainer1").html(liStr);
		$('#modificationDlg').dialog('option', 'modificationId', modificationId);
		$('#modificationDlg').dialog('open');
	});
	
	$('#modificationsSaveTrigger1').click(function(){
		var modificationArr = [];
		$('#modificationContainer1 :input').each(function(){
			if(this.checked == true){
				modificationArr.push(this.value);
			}
		});
		var modification = modificationArr.toString();
		var modificationId = $('#modificationDlg').dialog('option', 'modificationId');
		$('#'+modificationId).val(modification);
		$('#modificationDlg').dialog('close');
	});
	          
   	$('#modificationDlg').dialog({
		autoOpen: false,
		height: 310,
		width: 620,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
});
</script>