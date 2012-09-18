<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
  <form id="tmplForm" name="tmplForm">  
  <input type="hidden" name="itemId" />
  <input type="hidden" id="parentId" />
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
         <tr>
           <th width="150"><span class="important">*</span> Insert name</th>
           <td colspan="3">
           		<input name="tmplInsertName" type="text" class="NFText" size="20" />
           </td>
         </tr>
         <tr>
           <th><span class="important">*</span>Cloning method </th>
           <td colspan="3"><input name="tmplCloningMethod" type="radio" value="Standard" />
	             Cloning site :
	             <input name="tmplCloningSite" type="text" class="NFText" size="45" />
				(Example:BamHI-HindIII)
				<input name="tmplCloningMethod" type="radio" value="Gateway" checked="checked" />Gateway <sup>TM</sup>
			</td>
         </tr>   
         <tr>
           <th><span class="important">*</span> Insert sequence</th>
           <td><textarea name="tmplSequence" class="content_textarea2" style="WIDTH:333px;"></textarea></td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th><span class="important">*</span> Vector name</th>
           <td>
           		<s:select name="tmplVector" list="specDropDownMap['VECTOR'].dropDownDTOs" listKey="name" listValue="name" headerKey="PUC57" headerValue="PUC57"></s:select>
           		<input type="text" id="tmplVectorOther" name="tmplVectorOther" style="display: none;" class="NFText" size="20" />
           </td>
           <th><span class="important">*</span> Vector size(kb)</th>
           <td><input name="tmplVectorSize" type="text" class="NFText" onkeypress="return vectorSizeValid(event);"/></td>
         </tr>
         <tr>
           <th><span class="important">*</span> Resistance</th>
           		<td>
           			<s:select name="tmplResistance" list="dropDownMap['VECTOR_RESISTANCE']" listKey="value" listValue="value" headerKey="" headerValue=""></s:select>
           			<input type="text" id="tmplResistanceOther" name="tmplResistanceOther" style="display: none;" class="NFText" size="20" />
           		</td>
           <th><span class="important">*</span>Copy number</th>
           <td>
           		<input name="tmplCopyNo" type="radio" value="High" checked="checked" />
             	High　
               <input name="tmplCopyNo" type="radio" value="Low" />
               Low</td>
         </tr>
         <tr>
          <th>Vector sequence</th>
           <td><textarea name="tmplVectorSeq" class="content_textarea2" style="width: 333px;"></textarea></td>
           <th>Vector map</th>
           <td>
             <label><input type="file" name="upload" />
             </label><input name="tmplUploadBtn" type="button" class="style_botton" value="Upload" />           
           </td>
         </tr>
         <tr>
         	<th>&nbsp;</th>
         	<td>&nbsp;</td>
         	<th>&nbsp;</th>
         	<td><table id="fileListTable" name="fileListTable"></table></td>
         </tr>
         <tr>
         	<td colspan="4">&nbsp;</td>
         </tr>
         <s:if test='quoteItem.clsId == "9" && (quoteItem.parentId == null || quoteItem.parentId == "")'>
         <tr>
           <td colspan="2" align="center">
                <input type="hidden" id="cloneCatalogNo" value="${quoteItem.catalogNo}" />
           		<input type="button" class="style_botton6" value="Add New Associated Services" id="addMutaBtn" />
           </td>
           <td colspan="2" align="left"><input id="copyItem" type="button" class="style_botton" value="Copy" /></td>
         </tr>
         </s:if>
       </table>
 </form>