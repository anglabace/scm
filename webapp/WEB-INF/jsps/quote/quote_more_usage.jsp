<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<form id="geneUsageForm" name="geneUsageForm">
 <input id="itemId" type="hidden" name="itemId" value="${itemId}" />
<table width="100%" border="0" cellpadding="0" cellspacing="0">
         <tr>
           <td>
           	<input name="geneUsage" type="radio" value="Protein expression/analysis"/>
             	Protein expression/analysis<br />
             <div id="usage" style="display:block;margin-left:40px;">
	                <span class="important">*</span>Reading frame
	               <input type="radio" name="readingFrame" value="N" />
	               No request
	               <input name="readingFrame" type="radio" value="Y" />
	               Should be consistent client's requirement 
	               <span id="geneUsageTextDiv" style="display: none;">&nbsp;&nbsp;<input type="text" name="geneUsageText"/></span>
             </div>
             <div id="promo" style="display:none;height:3px;">&nbsp;</div>
             <input name="geneUsage" type="radio" value="Promoter assay" />
             Promoter assay <br />
             <input name="geneUsage" type="radio" value="RNAi, epigenetics &amp; gene regulation" />
             RNAi, epigenetics &amp; gene regulation <br />
             <input name="geneUsage" type="radio" value="Cloning" />
             Cloning <br />
             <input name="geneUsage" type="radio" value="DNA vaccines" />
             DNA vaccines <br />
             <input name="geneUsage" type="radio" value="Recombinant antibodies"/>
             Recombinant antibodies <br />
             <input name="geneUsage" type="radio" value="Others" />
             Others </td>
         </tr>
</table>
</form>