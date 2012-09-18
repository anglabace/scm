<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<form id="oligoDetailForm" method="post">
<input type="hidden" name="itemId" />
<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table" style="margin-bottom:0px;">
   <tr>
     <th width="17%"><span class="important">*</span>Oligo Name</th>
     <td width="22%"><input name="oligoName" type="text" class="NFText" size="25" /></td>
     <th width="27%">Aliquoting into</th>
     <td width="34%"><input name="aliquotingInto" type="text" class="NFText" size="25" /></td>
   </tr>
   <tr>
     <th><span class="important">*</span>Backbone</th>
     <td>
     	<select name="backbone" id="backbone" style="width:157px">
	     	<s:if test="orderItem.catalogNo == 'SC1516'"><option value="DNA">DNA</option></s:if>
	     	<s:elseif test="orderItem.catalogNo == 'SC1517'"><option value="Phosphorothioated DNA">Phosphorothioated DNA</option></s:elseif>
	     	<s:elseif test="orderItem.catalogNo == 'SC1518'"><option value="RNA">RNA</option></s:elseif>
	     	<s:elseif test="orderItem.catalogNo == 'SC1519'"><option value="Phosphorothioated RNA">Phosphorothioated RNA</option></s:elseif>
	     	<s:elseif test="orderItem.catalogNo == 'SC1527'"><option value="2'-OMe-RNA">2'-OMe-RNA</option></s:elseif>
	     	<s:elseif test="orderItem.catalogNo == 'SC1528'"><option value="Phosphorothioated 2'-OMe-RNA">Phosphorothioated 2'-OMe-RNA</option></s:elseif>
     	</select>
     </td>
     <th>&nbsp;</th>
     <td>tubes(Extra $0.2/tube for aliquoting into over 5 Tubes.) </td>
   </tr>
   <tr>
     <th><span class="important">*</span>Purification</th>
     <td>
     <div id="rnaPurificationSel" style="display: none;">
     	<s:select cssStyle="width:157px;" 
   			  name="purification" 
   			  list="dropDownMap['OLIGO_RNA_PURIFICATION']" 
   			  listKey="text" 
   			  listValue="value" 
   			  id="purification"
   			  value="purification">
   	 	</s:select>
   	 </div>
   	 <div id="otherPurificationSel" style="display: none;">	
   	 	<s:select cssStyle="width:157px" 
   			  name="purification" 
   			  list="dropDownMap['OLIGO_OTHER_PURIFICATION']" 
   			  listKey="text" 
   			  listValue="value" 
   			  id="purification"
   			  value="purification">
   	 	</s:select>
   	 </div>	
   	 </td>
     <th>Synthesis Scales</th>
     <td><s:select cssStyle="width:157px" 
   			  name="synthesisScales" 
   			  list="dropDownMap['OLIGO_SYNTHESIS_SCALES']" 
   			  listKey="text" 
   			  listValue="value" 
   			  id="synthesisScales"
   			  value="synthesisScales" headerKey="" headerValue="">
   	 	</s:select></td>
   </tr>
   <tr>
   		<th>nMol / Vial</th>
  		<td><input name="aliquotingSize" type="text" class="NFText" size="25" /></td>
     	<th>&nbsp;</th>
     	<td>&nbsp;</td>
   </tr>
   <tr>
     <td colspan="3">   　　　　　　　　Note:For   Special base refer to <a href="http://qa.genscript.com/oligo_codes.html" target="_blank">GenScript's defined   codes</a></td>
     <td>&nbsp;</td>
   </tr>
   <tr>
     <th><span class="important">*</span>Sequence  5'</th>
     <td colspan="2"><textarea name="sequence" class="content_textarea2"></textarea>3'</td>
     <td>Sequence Length&nbsp;&nbsp;
     	<s:if test="orderItem.oligo != null && orderItem.oligo.seqLength != null">
     		<input id="seqLength" type="text" class="NFText" size="25" value="${orderItem.oligo.seqLength}" disabled="disabled"/>
     	</s:if>
     	<s:else>
     		<input id="seqLength" type="text" class="NFText" size="25" disabled="disabled"/>
     	</s:else>
     </td>
   </tr>
   <tr>
     <th>&nbsp;</th>
     <td colspan="3"><a id="oligourl" href="javascript:void(0)">Click to hide the modification menus</a></td>
   </tr>
</table>
       
     <div id="oligoa" class="oligo">
   <table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table" style="margin-top:0px;margin-bottom:0px;">
           <tr>
             <th width="17%" valign="top">&nbsp;</th>
             <td width="83%" colspan="3">Please click to add the selected chimeric bases,mixed bases and modifications   into the sequence. </td>
           </tr>
         
           <tr>
             <th valign="top">&nbsp;</th>
             <td colspan="3"><table border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <td width="148">5' Modification</td>
                 <td width="143">Internal Modification</td>
                 <td width="394">3' Modification</td>
               </tr>
               <tr>
                 <td>
                 <div id="rnaModification5Sel" style="display: none;">
                   <select size="5" id="rnaModification5" style="width:150px;height:100px;">
                   <s:iterator value="omMap.RNA5MODIFICATION" id="modificationList">
                   	<s:if test="mCode == null"><optgroup label="${mName}"></optgroup></s:if>
                    <s:else><option value="${mCode}">&nbsp;&nbsp;&nbsp;${mName}</option></s:else>
                   </s:iterator>
                   </select></div>
                 <div id="otherModification5Sel" style="display: none;">	
                   <select size="5" id="selOtherModification5" style="width:150px;height:100px;">
                   <s:iterator value="omMap.DNA5MODIFICATION" id="modificationList">
                   	<s:if test="mCode == null"><optgroup label="${mName}"></optgroup></s:if>
                    <s:else><option value="${mCode}">&nbsp;&nbsp;&nbsp;${mName}</option></s:else>
                   </s:iterator>
                   </select></div>
                 </td>
                 <td>
                 <div id="rnaInternalModificationSel" style="display: none;">
                   <select size="5" id="rnaInternalModification" style="width:150px;height:100px;">
                   <s:iterator value="omMap.RNAINTERNALMODIFICATION" id="modificationList">
                   	<s:if test="mCode == null"><optgroup label="${mName}"></optgroup></s:if>
                    <s:else><option value="${mCode}">&nbsp;&nbsp;&nbsp;${mName}</option></s:else>
                   </s:iterator>
                   </select></div>
                 <div id="otherInternalModificationSel" style="display: none;">
                   <select size="5" id="otherInternalModification" style="width:150px;height:100px;">
                   <s:iterator value="omMap.DNAINTERNALMODIFICATION" id="modificationList">
                   	<s:if test="mCode == null"><optgroup label="${mName}"></optgroup></s:if>
                    <s:else><option value="${mCode}">&nbsp;&nbsp;&nbsp;${mName}</option></s:else>
                   </s:iterator>
                   </select></div>
                 </td>
                 <td>
                 <div id="rnaModification3Sel" style="display: none;">
                   <select size="5" id="rnaModification3" style="width:150px;height:100px;">
                   <s:iterator value="omMap.RNA3MODIFICATION" id="modificationList">
                   	<s:if test="mCode == null"><optgroup label="${mName}"></optgroup></s:if>
                    <s:else><option value="${mCode}">&nbsp;&nbsp;&nbsp;${mName}</option></s:else>
                   </s:iterator>
                   </select></div>
                 <div id="otherModification3Sel" style="display: none;">	
                   <select size="5" id="selOtherModification3" style="width:150px;height:100px;">
                   <s:iterator value="omMap.DNA3MODIFICATION" id="modificationList">
                   	<s:if test="mCode == null"><optgroup label="${mName}"></optgroup></s:if>
                    <s:else><option value="${mCode}">&nbsp;&nbsp;&nbsp;${mName}</option></s:else>
                   </s:iterator>
                   </select></div>
                 </td>
               </tr>
               <tr>
                 <td><input type="checkbox" name="othermodificationFlag5" id="othermodificationFlag5" />
                   5' Other modification </td>
                 <td><input type="checkbox" name="interOtherModificationFlag" id="interOtherModificationFlag" />
                   Internal other modification</td>
                 <td><input type="checkbox" name="othermodificationFlag3" id="othermodificationFlag3" />
                   3' other modification </td>
               </tr>
               <tr>
                 <td><textarea rows="1" id="othermodification5" name="othermodification5" style="width:150px;height:20px;"></textarea></td>
                 <td><textarea rows="1" id="interOtherModification" name="interOtherModification" style="width:150px;height:20px;"></textarea></td>
                 <td><textarea rows="1" id="othermodification3" name="othermodification3" style="width:150px;height:20px;"></textarea></td>
               </tr>
               <tr>
                 <td>Chimeric Bases</td>
                 <td>Standard Mixed Bases</td>
                 <td>Custom Mixed Bases</td>
               </tr>
               <tr>
                 <td rowspan="2"><select onclick="" size="5" name="chimericBases" style="width:150px;height:100px;">
                   <s:iterator value="oligoChimericBasesList">
                   <s:if test='cCode == null'>
                   		<optgroup label="${cBase}"></optgroup>
                   </s:if>
                   <s:else>
                   		<option value="${cCode}">&nbsp;&nbsp;&nbsp;${cBase}</option>
                   </s:else>
                   </s:iterator>
                 </select></td>
                 <td rowspan="2"><select onclick="" size="5" name="standardMixedMases" style="width:150px;height:100px;">
                   <s:iterator value="oligoMixedBasesList">
                   		<option value="${sCode}">${sName}</option>
                   </s:iterator>
                 </select></td>
                 <td>Please note:An additional charge is applied for hand mixing these custom bases. </td>
               </tr>
               <tr>
                 <td valign="top"><table border="0" cellspacing="0" cellpadding="0">
                   <tr>
                     <td align="center">dA</td>
                     <td align="center">dC</td>
                     <td align="center">dG</td>
                     <td align="center">dT</td>
                   </tr>
                   <tr>
                     <td><input name="daPercent" type="text" class="NFText" size="5" />
                       %</td>
                     <td><input name="dcPercent" type="text" class="NFText" size="5" />
                       %</td>
                     <td><input name="dgPercent" type="text" class="NFText" size="5" />
                       %</td>
                     <td><input name="dtPercent" type="text" class="NFText" size="5" />
                       %</td>
                   </tr>
                   <tr>
                     <td colspan="4" align="center"><input id="useMixedBase" type="button" class="style_botton2" value="Use Mixed Base"/></td>
                     </tr>
                 </table></td>
               </tr>
             </table></td>
           </tr>
         </table>
     </div>
     <div class="oligo">    
         <table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table" style="margin-top:0px;">
           <tr>
             <th width="17%" valign="top">Comment</th>
             <td width="83%" colspan="3"><textarea name="comments" class="content_textarea2"></textarea></td>
           </tr>
         </table>
     </div>
</form>