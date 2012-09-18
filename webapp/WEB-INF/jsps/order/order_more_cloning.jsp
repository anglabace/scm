<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<form id="cloningForm" enctype="multipart/form-data">
<input type="hidden" name="itemId" />
<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="General_table">
         <tr>
           <th width="150"><span class="important">*</span>Vector name </th>
           <td width="350">
           		<s:select name="tgtVector" list="specDropDownMap['VECTOR'].dropDownDTOs" listKey="name" listValue="name"></s:select>           
           		&nbsp;&nbsp;&nbsp;<input type="text" id="tgtVectorOther" name="tgtVectorOther" style="display: none;" class="NFText" size="20" />
           </td>
           <th width="100">Vector size(kb) </th>
           <td><input name="tgtVectorSize" type="text" class="NFText" size="20" onkeypress="return vectorSizeValid(event);"/></td>
         </tr>
         <tr>
           <th><span class="important">*</span>Resistance</th>
           <td>
	           <s:select name="tgtResistance" list="dropDownMap['VECTOR_RESISTANCE']" listKey="value" listValue="value" headerKey="" headerValue=""></s:select>
	           &nbsp;&nbsp;&nbsp;<input type="text" id="tgtResistanceOther" name="tgtResistanceOther" style="display: none;" class="NFText" size="20" />          
           </td>
           <th>
           		<span class="important">*</span>Copy number 
           </th>
	           <td>
	           	 <input type="radio" name="tgtCopyNo" value="High" checked="checked" />
	             High
	             <input name="tgtCopyNo" type="radio" value="Low" />
	             Low
	            </td>
         </tr>
         <tr>
           <th>Vector sequence </th>
           <td><textarea name="tgtVectorSeq" class="content_textarea2" style="width:330px;"></textarea></td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th>Vector map </th>
           <td>
           	  <label>
           		<input type="file" name="upload" />
           	   </label>
               <input name="cloningUploadBtn" type="button" class="style_botton" value="Upload" />
               <input name="tgtMapDocFlag" type="hidden" id="" />
            </td>
           <td align="left">&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
         	<th>&nbsp;</th>
         	<td colspan="1">
	         	<table id="fileListTable" name="fileListTable"></table>
           	</td>
         </tr>
         <s:if test="show != 'cloning'">
         <tr>
           <th><span class="important">*</span>Cloning method </th>
           <td><input name="tgtCloningMethod" type="radio" value="Standard" />
	             Cloning site :
	             <input name="tgtCloningSite" type="text" class="NFText" size="45" />
				(Example:BamHI-HindIII)
			</td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th>&nbsp;</th>
           <td><input name="tgtCloningMethod" type="radio" value="Gateway" checked="checked" />Gateway <sup>TM</sup></td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         </s:if>
       </table>
</form>