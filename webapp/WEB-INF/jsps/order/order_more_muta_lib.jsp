<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<form id="mutaLibForm" method="post">
<input type="hidden" name="itemId" />
<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="General_table">
         <tr>
           <th width="115"><span class="important">*</span>Construct name: </th>
           <td width="458" ><input name="constructName" type="text" class="NFText" size="25" value="${orderItem.mutationLibraries.constructName}"/></td>
           <th width="72">&nbsp;</th>
           <td width="144">&nbsp;</td>
           <td width="117">&nbsp;</td>
         </tr>
         <tr>
           <th width="115">Choose library type:</th>
           <td><input name="libraryType" type="radio" value="Site-directed mutagenesis libraries" checked="checked"/>Site-directed mutagenesis libraries<br/>  
	           <input name="libraryType" type="radio" value="Sequential permutation scanning libraries" />Sequential permutation scanning libraries<br/>
	           <input name="libraryType" type="radio" value="Randomized and degenerated libraries" />Randomized and degenerated libraries<br/>
           </td>
           <td colspan="3"></td>
         </tr>
         <tr>
           <td colspan="5">Can you provide the template?
	           	<input name="tmplFlag" type="radio" value="Y" />
	             Yes
	             <input name="tmplFlag" type="radio" value="N" checked="checked" />
	             No</td>
         </tr>
         <tr><td colspan="5">Template sequence and mutation: Please submit the sequence from the area of interest and use different character case for degenerated sties.</td>
		 </tr>
         <tr>
           <th>Example:</th>
           <td colspan="4"><div style="font-size:7pt;line-height:12px;">Name of the template<br/>
           		GAATTCTTAATTATATTAAATCAAAATAAAAATTTAAAATCACTCGATATACAATCTTGT<span style="color:red;">X</span>TGAATAGCTCTTGAAAGCTAGCAATCAGGAACTTATATATT<br/>
           		GAGTGATTTTTTGATTCCTTATCAATTGTAAATTATAGTAGAGAAGTCCATTTTGGAGATTATAAAGTTATTTTTATGTGTCTTTGACCAAAATAGTAACAT<br/>
           		CTCTTTCC<span style="color:red;">X</span>GGGTTGTGGCATGGCACTGTGGATCGGCAAACAGATTTCTGGCCCGCAAGGGCTGTTTTATGCGGACAAAGAATCTCTGGAAGCG<br/>
           		AAAGGTGCAAAAATCTATATGGAATCACCTGTCACTGCAATTGATTAT<span style="color:red;">X</span>ACGCGAAACGTGTAACAGCTCTGGTGAACGGACAGGAGCATGTGGAA<br/>
           		AGTTATGAGAAACTGATTCTGGCTAC</div>
             </td>
         </tr>
         <tr><td>&nbsp;</td>
           <td colspan="4">*Sequence of interest below:</td>
         </tr>
         <tr><td>&nbsp;</td>
           <td colspan="4"><textarea name="interestSequence" class="content_textarea2">${orderItem.mutationLibraries.interestSequence}</textarea></td>
         </tr>
         <tr><td>&nbsp;</td>
           <td colspan="4">Position and type of degenerated sites (if not specified in sequence field):</td>
         </tr>
         <tr><td>&nbsp;</td>
           <td colspan="4"><textarea name="degeneratedSites" class="content_textarea2">${orderItem.mutationLibraries.degeneratedSites}</textarea></td>
         </tr>
         <tr>
           <th>Deliverables: </th>
           <td>
	           	<input type="radio" name="tgtVectorName" value="Target vector same as template" disabled="disabled">
	           	<span style="color: rgb(128, 128, 128);">Target vector same as template</span><br/>
	            <input type="radio" name="tgtVectorName" checked="checked" value="PCR products">PCR products<br/>
	            <input type="radio" name="tgtVectorName" value="Other">Other
           </td>
           <td colspan="3">&nbsp;</td>
         </tr>
         <tr>
           <th>Plasmid Preparation: </th>
           <td><input name="plasmidPrepFlag" type="radio" value="N" checked="checked" />Standard delivery:
           		<input name="" type="text" class="NFText" value="4 ug" size="20" disabled="disabled"/>(Free of charge)
				<input name="stdPlasmidWt" type="hidden" value="4" />
				<input name="stdPlasmidWtUom" type="hidden" value="ug" /><br/>
				<input type="radio" name="plasmidPrepFlag" value="Y"/>Other			
			</td>
           <td rowspan="3">&nbsp;</td>
         </tr>
       </table>
 </form>