<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<form id="rnaForm" method="post">
<input type="hidden" name="itemId" />
<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="General_table">
         <tr>
           <th width="115"><span class="important">*</span>Gene Name: </th>
           <td width="458" ><input name="geneName" type="text" class="NFText" size="25" /></td>
           <th width="72">&nbsp;</th>
           <td width="144">&nbsp;</td>
           <td width="117">&nbsp;</td>
         </tr>
        <tr>
           <th><span class="important">*</span>Vector Name </th>
           <td>
	           <s:select cssStyle="width:157px" id="vectorName" name="vectorName"
						list="dropDownMap['VECTOR_LIST']" listKey="value"
						listValue="value" onchange="vectorChange();"/>
               &nbsp;&nbsp;&nbsp;<input type="text" id="vectorOther" name="vectorOther" style="display: none;" class="NFText" size="20" />
			</td>
           <th><span class="important">*</span>Quantity</th>
           <td>
           	 <select name="quantity">
				<option value="MiniPrep (4 ug)" selected="selected">MiniPrep (4 ug)</option>
			 </select>
			</td>
         </tr>
         <tr>
           <th valign="top"><span class="important">*</span>Insert Sequence (From 5' To 3') </th>
           <td><textarea name="sequenceInsert" id="sequenceInsert" class="content_textarea2" onblur="checkSeq(this)"></textarea></td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th>Comments </th>
           <td><input name="comments" id="comments" class="NFText" size="75" type="text"></td>
		   <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th>Plasmid Preparation </th>
           <td>
	           <input name="plasmidPrepFlag" type="radio" value="N" checked="checked" />
	           Standard delivery:
	           <input name="" type="text" class="NFText" value="4 ug" size="20" />
				(Free of charge)
				<input name="stdPlasmidWt" type="hidden" value="4" />
				<input name="stdPlasmidWtUom" type="hidden" value="ug" />				
			</td>
           <td>
	           	<input type="radio" name="plasmidPrepFlag" value="Y"  />
				Other
			</td>
           <td rowspan="2">&nbsp;</td>
           <td rowspan="2">&nbsp;</td>
         </tr>
       </table>
 </form>