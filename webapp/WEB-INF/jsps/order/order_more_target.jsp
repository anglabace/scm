<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
 <s:if test="show == 'muta'">
 <form id="targetForm">
 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
         <tr>
           <th width="150"><span class="important">*</span>Target Insert Name</th>
           <td><input name="variantName" type="text" class="NFText" size="20" />
				(if different from the template insert name) </td>
         </tr>
         <tr>
           <th rowspan="2">
           		<span class="important">*</span>Target Insert Sequence</th>
         </tr>
         <tr>
           <td><textarea name="variantSequence" class="content_textarea2" style="width: 330px;"></textarea></td>
         </tr>
         <tr>
           <th><span class="important">*</span>Target vector</th>
           <td>
	           	<input name="cloningFlag" type="radio" value="N" />
				Same as template 
		  <br />
		<input type="radio" name="cloningFlag" value="Y" />
		Other
		<div  id="div" style="display:none;margin-left:30px;">
		  <table width="100%"  border="0" cellpadding="0" cellspacing="0" class="General_table">
		    <tr> </tr>
		  </table>
		</div></td>
         </tr>
         <tr>
           <th>Plasmid Preparation </th>
           <td>
           		<input name="plasmidPrepFlag" type="radio" value="N" />
				Standard delivery:
			   	<input name="" type="text" class="NFText" value="4 ug" size="20" disabled="disabled"/>
			   	(Free of charge)
			   	<input name="stdPlasmidWt" type="hidden" value="4" />
			   	<input name="stdPlasmidWtUom" type="hidden" value="ug" />
			   <br />
			<input type="radio" name="plasmidPrepFlag" value="Y" />
			Other
		</td>
         </tr>
       </table>
</form>
</s:if>
<!-- ---------------------------------------------------------------------------- -->
<s:elseif test="show == 'cloning'">
 <form id="targetForm">
 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
         <tr>
           <th width="150">Target Insert Name</th>
           <td><input name="tgtInsertName" type="text" class="NFText" size="20" />
				(if different from the template insert name) </td>
         </tr>
		 <tr>
          <th><span class="important">*</span> Cloning method</th>
           <td colspan="3"><input name="tgtCloningMethod" type="radio" checked="checked" value="Standard" /> 
             Cloning site :
               <input name="tgtCloningSite" type="text" class="NFText" size="45"/> 
             (Example:BamHI-HindIII)
           <input name="tgtCloningMethod" type="radio" value="Gateway" />Gateway <sup>TM</sup></td>
         </tr>
         <tr>
           <th rowspan="2">
           		<span class="important">*</span>Target Insert Sequence</th>
           <td>
           		<input type="checkbox" name="tgtSeqSameFlag" value="Y" />
				Same as template insert
			</td>
         </tr>
         <tr>
           <td><textarea name="tgtSequence" class="content_textarea2" style="width: 330px;"></textarea></td>
         </tr>
         <tr>
           <th><span class="important">*</span>Target vector</th>
           <td>
	           	<input name="cloningFlag" type="radio" value="N" />
				Same as template 
		  <br />
		<input type="radio" name="cloningFlag" value="Y" />
		Other
		<div  id="div" style="display:none;margin-left:30px;">
		  <table width="100%"  border="0" cellpadding="0" cellspacing="0" class="General_table">
		    <tr> </tr>
		  </table>
		</div></td>
         </tr>
         <tr>
           <th>Plasmid Preparation </th>
           <td>
           		<input name="plasmidPrepFlag" type="radio" value="N" />
				Standard delivery:
			   	<input name="" type="text" class="NFText" value="4 ug" size="20" disabled="disabled"/>
			   	(Free of charge)
			   	<input name="stdPlasmidWt" type="hidden" value="4" />
			   	<input name="stdPlasmidWtUom" type="hidden" value="ug" />
			   <br />
			<input type="radio" name="plasmidPrepFlag" value="Y" />
			Other
		   </td>
        </tr>
</table>
</form>
</s:elseif>