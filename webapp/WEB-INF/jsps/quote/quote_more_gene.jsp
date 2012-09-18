<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<form id="geneSynthesisForm" method="post">
<input type="hidden" name="itemId" />
<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="General_table">
         <tr>
           <th width="115"><span class="important">*</span>Gene Name: </th>
           <td width="458" ><input name="geneName" type="text" class="NFText" size="25" value="${geneSynthesis.geneName}"/></td>
           <th width="72">&nbsp;</th>
           <td width="144">&nbsp;</td>
           <td width="117">&nbsp;</td>
         </tr>
         <tr>
           <th>5' Sequence:</th>
           <td>
           		<input name="sequence5" type="text" class="NFText" size="25" id="sequence5" />
           		<input type="button" class="style_botton2" value="Search Enzyme" id="searchEnzymeTrigger5" />
           	</td>
           <th>3' Sequence:</th>
           <td><input name="sequence3" type="text" class="NFText" size="25" id="sequence3" /></td>
           <td><input type="button" class="style_botton2" value="Search Enzyme" id="searchEnzymeTrigger3" /></td>
         </tr>
         <tr>
           <th rowspan="3"><span class="important">*</span>Sequence:</th>
           <td rowspan="3">
	           	<input name="sequenceType" type="radio" value="DNA" checked="checked" />
	             DNA
	             <input name="sequenceType" type="radio" value="Protein" />
	             Protein
	             <input name="sequenceType" type="radio" value="Length" />
	             Length<br/>
	             <textarea name="sequence" class="content_textarea2"></textarea>
           </td>
           <th>Price</th>
           <td colspan="2"><input name="bpPrice" class="NFText" size="25" type="text" value="${geneSynthesis.bpPrice }">
             (bp)</td>
         </tr>
         <tr>
           <th>Cost</th>
           <td colspan="2"><input name="bpCost" class="NFText" size="25" type="text" value="${geneSynthesis.bpCost }">(bp)</td>
         </tr>
         <tr>
           <th>Seq Length</th>
           <td colspan="2">
           <s:if test="quoteItem.geneSynthesis.seqLength != null">
           		<input id="seqLength" class="NFText" size="25" type="text" value="${quoteItem.geneSynthesis.seqLength}" disabled="disabled">
           </s:if>	
           <s:else>
           		<input id="seqLength"class="NFText" size="25" type="text" disabled="disabled">
           </s:else>		
           </td>
         </tr>
         <tr>
           <th>Codon Optimization</th>
           <td>
           	<input type="radio" name="codeOptmzFlag" value="Y" />
             Yes
             <input name="codeOptmzFlag" type="radio" value="N" checked="checked" />
             No
             </td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th rowspan="3">Cloning Strategy</th>
           <td>
	           	<input name="cloningFlag" type="radio" value="N" checked="checked" serviceName="custCloning" />
	            <input name="stdVectorName" type="text" class="NFText" size="20" />
				Cloning site
				<input name="cloningSite" type="text" class="NFText" size="20" />
	            <a href="qu_order_more!showVector.action" target="_blank"> pUC57 vector map</a>
            </td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <td colspan="4">(Example:BamHI-Hindlll,<em>Eco</em>R V; Note:If leave it blank,an available site will be chosen to insert the gene by GenScript,usually <em>Eco</em>R V).</td>
         </tr>
         <tr>
         <td colspan="4">
           	<input type="radio" name="cloningFlag" value="Y" serviceName="custCloning" />
			Other
		</td>
         </tr>
         <tr>
           <th>Plasmid Preparation </th>
           <td>
	           <input name="plasmidPrepFlag" type="radio" value="N" checked="checked" />
	           Standard delivery:
	           <input name="" type="text" class="NFText" value="4 ug" size="20" disabled="disabled"/>
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
         <tr>
           <td colspan="5" align="center">
                <input type="hidden" id="geneCatalogNo" value="${quoteItem.catalogNo}" />
                <s:if test="quoteItem.clsId == 3 || quoteItem.clsId == 9">
                	<input type="hidden" id="geneItemId" value="${itemId}" />
                </s:if>
           		<input type="button" class="style_botton6" value="Add New Associated Services" id="addServiceBtn" />
           </td>
         </tr>
       </table>
 </form>