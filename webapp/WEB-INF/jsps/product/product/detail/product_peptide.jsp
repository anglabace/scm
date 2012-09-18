<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<style>
.part_k{
	width:950px;
	height:auto;
	float: left;
	margin: 0px;
	padding: 0px;
}
</style>
</head>
<script type="text/javascript">
$(function(){
$("[name='vendor']").change(function(){
	if($("#vendor").val()==null||$("#vendor").val()==""){
		$("[name='vendorCatalog']").val("");
	}else{
		$("[name='vendorCatalog']").val($("#"+$("#vendor").val()).val());
	}
	
}); 
})
</script>
<body>
<div id="Peptide" class="part_k">
<form name="detailForm" id="detailForm">
<table width="950" border="0" cellpadding="0" cellspacing="0"  class="General_table" >
  <tr>
    <th width="190">Cas No</th>
    <td width="285" colspan="2"><input name="productDTO.peptide.casNo" type="text" class="NFText" size="30" value="${productDTO.peptide.casNo }"/></td>
    <th width="190">Molecular Formula</th>
    <td width="285"><input name="productDTO.peptide.molecularFormula" type="text" class="NFText" size="30" value="${productDTO.peptide.molecularFormula }"/></td>
  </tr>
  <tr>
    <th>Molecular Weight</th>
    <td colspan="2"><input name="productDTO.peptide.molecularWeight" type="text" class="NFText" size="30" value="${productDTO.peptide.molecularWeight }"/></td>
    <th>Measured Molecular Weight</th>
    <td><input name="productDTO.peptide.measuredMolecularWeight" type="text" class="NFText" size="30" value="${productDTO.peptide.measuredMolecularWeight }"/></td>
  </tr>
  <tr>
    <th>Residue No</th>
    <td colspan="2"><input name="productDTO.peptide.residueNo" type="text" class="NFText" size="30" value="${productDTO.peptide.residueNo}"/></td>
    <th>GMP</th>
    <td>
    <s:if test="productDTO.peptide.gmpFlag==1">
   		 <input type="checkbox" name="productDTO.peptide.gmpFlag" id="gmpFlag" value="1" checked="checked"/>
    </s:if>
    <s:else>
    	 <input type="checkbox" name="productDTO.peptide.gmpFlag" id="gmpFlag" value="1"/>
    </s:else>
    </td>
  </tr>
  <tr>
    <th rowspan="2">Sequence</th>
    <td colspan="2" rowspan="2"><textarea name="productDTO.peptide.sequence" class="content_textarea2" style="width:250PX;">${productDTO.peptide.sequence }</textarea></td>
    <th>Sequence Shortening</th>
    <td><input name="productDTO.peptide.sequenceShortening" type="text" class="NFText" size="30" value="${productDTO.peptide.sequenceShortening }"/></td>
  </tr>
  <tr>
    <th>&nbsp;</th>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th>Species</th>
    <td colspan="2"><input name="productDTO.peptide.species" type="text" class="NFText" size="30" value="${productDTO.peptide.species }"/></td>
    <th>Purity</th>
    <td><input name="productDTO.peptide.purity" type="text" class="NFText" size="30" value="${productDTO.peptide.purity }"/></td>
  </tr>
  <tr>
    <th>C-Terminal</th>
    <td colspan="2"><input name="productDTO.peptide.cterminal" type="text" class="NFText" size="30" value="${productDTO.peptide.cterminal }"/></td>
    <th>N-Terminal</th>
    <td><input name="productDTO.peptide.nterminal" type="text" class="NFText" size="30" value="${productDTO.peptide.nterminal }"/></td>
  </tr>
  <tr>
    <th>Dimers</th>
    <td colspan="2"><input name="productDTO.peptide.dimers" type="text" class="NFText" size="30" value="${productDTO.peptide.dimers }"/></td>
    <th>Endotoxin Level</th>
    <td><input name="productDTO.peptide.endotoxinLevel" type="text" class="NFText" size="30" value="${productDTO.peptide.endotoxinLevel }"/></td>
  </tr>
  <tr>
    <th>Specific Activity</th>
    <td colspan="2"><textarea name="productDTO.peptide.specificActivity" class="content_textarea2" style="width:250PX;">${productDTO.peptide.specificActivity }</textarea></td>
    <th>Specificity</th>
    <td><textarea name="productDTO.peptide.specificity" class="content_textarea2" style="width:250PX;">${productDTO.peptide.specificity }</textarea></td>
  </tr>
  <tr>
    <th>Disulfide Bridge</th>
    <td colspan="2"><textarea name="productDTO.peptide.disulfideBridge" class="content_textarea2" style="width:250PX;">${productDTO.peptide.disulfideBridge }</textarea></td>
    <th>Format Bridge</th>
    <td><textarea name="productDTO.peptide.formatBridge" class="content_textarea2" style="width:250PX;">${productDTO.peptide.formatBridge }</textarea></td>
  </tr>
  <tr>
    <th>Appearance</th>
    <td colspan="2"><input name="productDTO.peptide.appearance" type="text" class="NFText" size="30" value="${productDTO.peptide.appearance }"/></td>
    <th>Color</th>
    <td><input name="productDTO.peptide.color" type="text" class="NFText" size="30" value="${productDTO.peptide.color }"/></td>
  </tr>
  <tr>
    <th rowspan="2">Solubility</th>
    <td colspan="2" rowspan="2"><textarea name="productDTO.peptide.solubility" class="content_textarea2" style="width:250PX;">${productDTO.peptide.solubility }</textarea></td>
    <th>Concentration</th>
    <td><input name="productDTO.peptide.concentration" type="text" class="NFText" size="30" value="${productDTO.peptide.concentration }"/></td>
  </tr>
  <tr>
    <th>&nbsp;</th>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th>Stability</th>
    <td colspan="2"><textarea name="productDTO.peptide.stability" class="content_textarea2" style="width:250PX;">${productDTO.peptide.stability }</textarea></td>
    <th>Quality Control</th>
    <td><textarea name="productDTO.peptide.qualityControl" class="content_textarea2" style="width:250PX;">${productDTO.peptide.qualityControl }</textarea></td>
  </tr>
  <tr>
    <th>Catalog No from Other Vendors:</th>
    <td colspan="2">&nbsp;</td>
    <th>&nbsp;</th>
    <td>&nbsp;</td>	
  </tr>
  <tr>
    <th>Vendor</th>
    <td><select name="vendor" id="vendor" style="width:200px;">
      <option value=""></option>
      <option value="amercanpeptide">Amercanpeptide</option>
      <option value="bachem">Bachem</option>
      <option value="anaspec">Anaspec</option>
      <option value="phoenixpeptide">Phoenixpeptide</option>
    </select></td>
    <td>&nbsp;</td>
    <th>&nbsp;</th>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th>Catalog No</th>
    <td colspan="2"><input name="vendorCatalog" id="vendorCatalog" type="text" class="NFText" size="30"  value=""/></td>
    <th>&nbsp;</th>
    <td>&nbsp;</td>
  </tr>
  <s:if test="productDTO.antibody.productId = null">
    	<input type="hidden" name="productDTO.peptide.productId" value="${id}" />
    </s:if>
    <s:else>
    	<input type="hidden" name="productDTO.peptide.productId" value="${productDTO.antibody.productId}" />
    </s:else>
    <input type="hidden" name="productDTO.peptide.americanpeptideCatNo" id="amercanpeptide" value="${productDTO.peptide.americanpeptideCatNo}" />
    <input type="hidden" name="productDTO.peptide.anaspecCatNo" id="anaspec" value="${productDTO.peptide.anaspecCatNo}" />
    <input type="hidden" name="productDTO.peptide.bachemCatNo" id="bachem" value="${productDTO.peptide.bachemCatNo}" />
    <input type="hidden" name="productDTO.peptide.phoenixpeptideCatNo" id="phoenixpeptide" value="${productDTO.peptide.phoenixpeptideCatNo}" />
</table>
</form>
</div>
</body>
</html>