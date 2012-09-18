<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<form id="codonForm" name="codonForm">
 <input id="itemId" type="hidden" name="itemId" value="${itemId}" />
<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table">
         <tr>
           <th width="21%"><span class="important">*</span> Host expression organism</th>
           <td colspan="2">
	             <s:select name="hostExpsOrganism" list="specDropDownMap['HOST_EXPS_ORGANISM'].dropDownDTOs" listKey="name" listValue="name"></s:select>
	             &nbsp;&nbsp;&nbsp;<input type="text" id="hostExpsOrgOther" name="hostExpsOrgOther" style="display: none;" class="NFText" size="20" />       
             </td>
           <th width="21%">Secondary expression organism</th>
           <td width="29%">
	           <s:select name="scndExpsOrganism" list="specDropDownMap['HOST_EXPS_ORGANISM'].dropDownDTOs" listKey="name" listValue="name" headerKey="" headerValue=""></s:select>
	           <span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:50px;" onmouseover="tooltip.show('(If the secondary expression host is not closely related to the primary host,the optimization may result in a sequence with compromised codon usage index for both hosts. Selecting only the primary host will generate the most optimum sequence expression organism.) ');" onmouseout="tooltip.hide();"> <img src="images/qa.jpg" width="12" height="12" /></span>
	           &nbsp;&nbsp;&nbsp;<input type="text" id="scndExpsOrganismOther" name="scndExpsOrganismOther" style="display: none;" class="NFText" size="20" />
		 	   </td>
         </tr>
         
         <tr>
           <th>Optimization start position </th>
           <td colspan="2">
           	    <input name="optimizationStart" type="text" class="NFText" size="10" />
             	<s:select name="opStartPosUom" cssStyle="width:40px" list="dropDownMap['SEQ_POSITION_UOM']" listKey="value" listValue="value" ></s:select>
           </td>
           <th>end position </th>
           <td>
           		<input name="optimizationEnd" type="text" class="NFText" size="10" />
	            <s:select name="opEndPosUom" cssStyle="width:40px" list="dropDownMap['SEQ_POSITION_UOM']" listKey="value" listValue="value"></s:select>
             </td>
         </tr>
         <tr>
           <th>ORF start position </th>
           <td colspan="2">
           	 <input name="orfStart" type="text" class="NFText" size="10" />
             <s:select name="orfStartUom" cssStyle="width:40px" list="dropDownMap['SEQ_POSITION_UOM']" listKey="value" listValue="value"></s:select>
           </td>
           <th>end position </th>
           <td>
            	<input name="orfEnd" type="text" class="NFText" size="10" />
            	<s:select name="orfEndUom" cssStyle="width:40px" list="dropDownMap['SEQ_POSITION_UOM']" listKey="value" listValue="value"></s:select>
            </td>
         </tr>
         <tr>
           <th>Restriction sites to avoid </th>
           <td colspan="4">
           		<input name="rstSitesAvoid" type="text" class="NFText" size="37" />
             (example:BamHI,Hindlll(AAGCTT)) </td>
         </tr>
         <tr>
           <th>Restriction sites to keep </th>
           <td colspan="4">
           	 <input name="rstSitesKeep" type="text" class="NFText" size="37" />
             (example:BamHI,Hindlll(AAGCTT))</td>
         </tr>
         <tr>
           <th rowspan="2" valign="top">Stop codon needed </th>
           <td width="9%">
           	<input name="stopCodonFlag" type="radio" value="Y" checked="checked" />
             Yes</td>
           <td width="20%">Sequence:
             <input name="stopCodon" type="text" class="NFText" size="15" />           
           </td>
           <td colspan="2" valign="top">&nbsp;</td>
         </tr>
         <tr>
           <td>
           	<input type="radio" name="stopCodonFlag" value="N" />
           No</td>
           <td>&nbsp;</td>
           <td colspan="2" valign="top">&nbsp;</td>
         </tr>
         <tr>
           <th>Comments</th>
           <td colspan="4">
           	<input name="comment" type="text" class="NFText" size="100" />
           </td>
         </tr>
       </table>
 </form>