<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<form id="oligoButchOligosForm" method="post">
<%--<input type="hidden" name="itemId" />--%>
<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table">
            <tr>
        <td colspan="4"><table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="5%">&nbsp;</td>
            <td width="95%">Please   choose only ONE kind of method to upload your oligos.<br />
You can EDIT each oligo   to modify backbone/scales/purification/Modification after save.<br />
For <span class="important">unmodified DNA oligo</span>, we only provide large-scale order (at least 96   DNA-Oligos) </td>
          </tr>
        </table></td>
      </tr>
            <tr>
              <th>Backbone</th>
              <td width="31%">
              	<s:if test="oligoBackbonesList != null && oligoBackbonesList.size >0">
		     	<s:select cssStyle="width:157px" 
		       			  name="batchBackbone" 
		       			  list="oligoBackbonesList" 
		       			  listKey="bName" 
		       			  listValue="bNameShow" 
		       			  id="batchBackbone"
		       			  value="bName">
		        </s:select>
		        </s:if>
              </td>
              <th width="19%">Synthesis scales</th>
              <td width="33%">
              <s:if test="dropDownMap['OLIGO_SYNTHESIS_SCALES'] != null">
              	<s:select cssStyle="width:157px" 
		   			  name="batchSynthesisScales" 
		   			  list="dropDownMap['OLIGO_SYNTHESIS_SCALES']" 
		   			  listKey="text" 
		   			  listValue="value" 
		   			  id="batchSynthesisScales"
		   			  value="batchSynthesisScales">
		   	 	</s:select></s:if></td>
            </tr>
            <tr>
              <th>Purification</th>
              <td><div id="batchRnaPurificationSel" style="display: none;">
              <s:if test="dropDownMap['OLIGO_RNA_PURIFICATION'] != null">
			     	<s:select cssStyle="width:157px;" 
			   			  name="batchRnaPurification" 
			   			  list="dropDownMap['OLIGO_RNA_PURIFICATION']" 
			   			  listKey="text" 
			   			  listValue="value" 
			   			  id="batchRnaPurification"
			   			  value="batchRnaPurification">
			   	 	</s:select></s:if>
			   	 </div>
			   	 <div id="batchOtherPurificationSel" style="display: none;">	
			   	 <s:if test="dropDownMap['OLIGO_OTHER_PURIFICATION'] != null">
			   	 	<s:select cssStyle="width:157px" 
			   			  name="batchOtherPurification" 
			   			  list="dropDownMap['OLIGO_OTHER_PURIFICATION']" 
			   			  listKey="text" 
			   			  listValue="value" 
			   			  id="batchOtherPurification"
			   			  value="batchOtherPurification">
			   	 	</s:select></s:if>
			   	 </div></td>
              <th width="27%">Aliquoting into</th>
     		  <td width="34%"><input name="batchAliquotingInto" id="batchAliquotingInto" type="text" class="NFText" size="25" /></td>
            </tr>
      <tr>
        <th>5' Modification</th>
        <td>
        <div id="batchRnaModification5Sel" style="display: none;">
          <select id="batchRnaModification5" style="width:157px;">
          <option value="">Select 5' Modification</option>
          <s:iterator value="omMap.RNA5MODIFICATION" id="modificationList">
          	<s:if test="mCode == null"><optgroup label="${mName}"></optgroup></s:if>
           <s:else><option value="${mCode}">&nbsp;&nbsp;&nbsp;${mName}</option></s:else>
          </s:iterator>
          </select></div>
        <div id="batchOtherModification5Sel" style="display: none;">	
          <select id="batchOtherModification5" style="width:157px;">
          <option value="">Select 5' Modification</option>
          <s:iterator value="omMap.DNA5MODIFICATION" id="modificationList">
          	<s:if test="mCode == null"><optgroup label="${mName}"></optgroup></s:if>
           <s:else><option value="${mCode}">&nbsp;&nbsp;&nbsp;${mName}</option></s:else>
          </s:iterator>
          </select></div>
        </td>
        <th>3' Modification  </th>
        <td>
        <div id="batchRnaModification3Sel" style="display: none;">
          <select id="batchRnaModification3" style="width:157px;">
          <option value="">Select 3' Modification</option>
          <s:iterator value="omMap.RNA3MODIFICATION" id="modificationList">
          	<s:if test="mCode == null"><optgroup label="${mName}"></optgroup></s:if>
           <s:else><option value="${mCode}">&nbsp;&nbsp;&nbsp;${mName}</option></s:else>
          </s:iterator>
          </select></div>
        <div id="batchOtherModification3Sel" style="display: none;">	
          <select id="batchOtherModification3" style="width:157px;">
          <option value="">Select 3' Modification</option>
          <s:iterator value="omMap.DNA3MODIFICATION" id="modificationList">
          	<s:if test="mCode == null"><optgroup label="${mName}"></optgroup></s:if>
           <s:else><option value="${mCode}">&nbsp;&nbsp;&nbsp;${mName}</option></s:else>
          </s:iterator>
          </select></div>
        </td>
      </tr>
      <tr>
      	<th>nMol / Vial</th>
     	<td><input name="batchAliquotingSize" id="batchAliquotingSize" type="text" class="NFText" size="25" /></td>
        <th>&nbsp;</th>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <th width="17%">Oligo Sequence File </th>
        <td colspan="3"><input type="file" name="upload" id="oligoFile" /> 
          (.xls, .xlsx <a href="${global_url}order/order!download.action" target="_blank">standard from</a>) </td>
      </tr>
         <tr>
        <td colspan="4"><table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="17%">&nbsp;</td>
            <td width="83%">or just paste your oligos here (Enter sequences in   multiple FASTA txt format):</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <th valign="top">&nbsp;</th>
        <td colspan="3"><textarea name="batchOligoSequence" id="batchOligoSequence" class="content_textarea2"></textarea></td>
      </tr>
   
       <tr>
        <td height="40" colspan="4" align="center" valign="bottom"><input id="batchOrderBtn" type="button" class="style_botton" value="Save" /></td>
      </tr>
    </table>
</form>