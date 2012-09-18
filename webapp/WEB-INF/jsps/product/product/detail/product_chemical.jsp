<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
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
<body>
<div id="Chemical" class="part_k">
<form name="detailForm" id="detailForm">
  <table width="950" border="0" cellpadding="0" cellspacing="0"  class="General_table" >
       <tr>
      <th width="190">Cas No</th>
      <td width="285"><input name="productDTO.chemical.casNo" type="text" class="NFText" size="30" value="${productDTO.chemical.casNo }"/></td>
      <th width="190">Molecular Formula</th>
      <td width="285"><input name="productDTO.chemical.molecularFormula" type="text" class="NFText" size="30" value="${productDTO.chemical.molecularFormula}"/></td>
    </tr>
   
       <tr>
      <th width="190">Molecular Weight</th>
      <td width="285"><input name="productDTO.chemical.molecularWeight" type="text" class="NFText" size="30" value="${productDTO.chemical.molecularWeight}"/></td>
      <th width="190">Formulation</th>
      <td width="285"><input name="productDTO.chemical.formulation" type="text" class="NFText" size="30" value="${productDTO.chemical.formulation}"/></td>
    </tr>

  <tr>
      <th>Purity</th>
      <td><input name="productDTO.chemical.purity" type="text" class="NFText" size="30" value="${productDTO.chemical.purity}"/></td>
      <th>Optic Purity</th>
      <td><input name="productDTO.chemical.opticPurity" type="text" class="NFText" size="30" value="${productDTO.chemical.opticPurity}"/></td>
    </tr>

   
    <tr>
      <th width="190">Appearance </th>
      <td width="285"><input name="productDTO.chemical.appearance" type="text" class="NFText" size="30" value="${productDTO.chemical.appearance}"/></td>
      <th width="190">Color</th>
      <td width="285"><input name="productDTO.chemical.color" type="text" class="NFText" size="30" value="${productDTO.chemical.color}"/></td>
    </tr>
    <tr>
      <th>Boiling Point</th>
      <td><input name="productDTO.chemical.boilingPoint" type="text" class="NFText" size="30" value="${productDTO.chemical.boilingPoint}"/></td>
      <th>Melting Point</th>
      <td><input name="productDTO.chemical.meltingPoint" type="text" class="NFText" size="30" value="${productDTO.chemical.meltingPoint}"/></td>
    </tr>
    <tr>
      <th>Falsh Point</th>
      <td><input name="productDTO.chemical.flashPoint" type="text" class="NFText" size="30" value="${productDTO.chemical.flashPoint}"/></td>
      <th>Moisture</th>
      <td><input name="productDTO.chemical.moisture" type="text" class="NFText" size="30" value="${productDTO.chemical.moisture}"/></td>
    </tr>
    <tr>
      <th>Formazan</th>
      <td><input name="productDTO.chemical.formazan" type="text" class="NFText" size="30" value="${productDTO.chemical.formazan}"/></td>
      <th>Sensitivity</th>
      <td><input name="productDTO.chemical.sensitivity" type="text" class="NFText" size="30" value="${productDTO.chemical.sensitivity}"/></td>
    </tr>
    <tr>
      <th>Solubility</th>
      <td><textarea name="productDTO.chemical.solubility" class="content_textarea2" style="width:250PX;">${productDTO.chemical.solubility}</textarea></td>
      <th>Stability</th>
      <td><textarea name="productDTO.chemical.stability" class="content_textarea2" style="width:250PX;">${productDTO.chemical.stability}</textarea></td>
    </tr>
    <tr>
      <th>Molar Abso</th>
      <td><input name="productDTO.chemical.molarAbso" type="text" class="NFText" size="30" value="${productDTO.chemical.molarAbso}"/></td>
      <th>&nbsp;</th>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <th>Refractive</th>
      <td><input name="productDTO.chemical.refractive" type="text" class="NFText" size="30" value="${productDTO.chemical.refractive}"/></td>
      <th>TLC Analysis</th>
      <td><input name="productDTO.chemical.tlcAnalysis" type="text" class="NFText" size="30" value="${productDTO.chemical.tlcAnalysis}"/></td>
    </tr>
    <tr>
      <th>Transmittancy</th>
      <td><input name="productDTO.chemical.transmittancy" type="text" class="NFText" size="30" value="${productDTO.chemical.transmittancy}"/></td>
      <th>UV</th>
      <td><input name="productDTO.chemical.uv" type="text" class="NFText" size="30" value="${productDTO.chemical.uv}"/></td>
    </tr>
    <tr>
      <th>Infrared</th>
      <td><input name="productDTO.chemical.infrared" type="text" class="NFText" size="30" value="${productDTO.chemical.infrared}"/></td>
      <th>E0</th>
      <td><input name="productDTO.chemical.e0" type="text" class="NFText" size="30" value="${productDTO.chemical.e0}"/></td>
    </tr>
    <tr>
      <th>Ca</th>
      <td><input name="productDTO.chemical.ca" type="text" class="NFText" size="30" value="${productDTO.chemical.ca}"/></td>
      <th>Fe</th>
      <td><input name="productDTO.chemical.fe" type="text" class="NFText" size="30" value="${productDTO.chemical.fe}"/></td>
    </tr>
    <tr>
      <th>Pb</th>
      <td><input name="productDTO.chemical.pb" type="text" class="NFText" size="30" value="${productDTO.chemical.pb}"/></td>
      <th>Chloride</th>
      <td><input name="productDTO.chemical.chloride" type="text" class="NFText" size="30" value="${productDTO.chemical.chloride}"/></td>
    </tr>
    <tr>
      <th>PH</th>
      <td><input name="productDTO.chemical.ph" type="text" class="NFText" size="30" value="${productDTO.chemical.ph}"/></td>
      <th>Usable PH Range</th>
      <td><input name="productDTO.chemical.usablePhRange" type="text" class="NFText" size="30" value="${productDTO.chemical.usablePhRange}"/></td>
    </tr>
    <tr>
      <th>PKA</th>
      <td><input name="productDTO.chemical.pka" type="text" class="NFText" size="30" value="${productDTO.chemical.pka}"/></td>
      <th>Residue</th>
      <td><input name="productDTO.chemical.residue" type="text" class="NFText" size="30" value="${productDTO.chemical.residue}"/></td>
    </tr>
    <tr>
      <th>Sulfate</th>
      <td><input name="productDTO.chemical.sulfate" type="text" class="NFText" size="30" value="${productDTO.chemical.sulfate}"/></td>
      <th>Heavy Metals</th>
      <td><input name="productDTO.chemical.heavyMetals" type="text" class="NFText" size="30" value="${productDTO.chemical.heavyMetals}"/></td>
    </tr>
    <tr>
      <th>Amine</th>
      <td><input name="productDTO.chemical.amine" type="text" class="NFText" size="30" value="${productDTO.chemical.amine}"/></td>
      <th>Conductivity</th>
      <td><input name="productDTO.chemical.conductivity" type="text" class="NFText" size="30" value="${productDTO.chemical.conductivity}"/></td>
    </tr>
    <tr>
      <th>Dye Content</th>
      <td><input name="productDTO.chemical.dyeContent" type="text" class="NFText" size="30" value="${productDTO.chemical.dyeContent}"/></td>
      <th>Loss on Drying</th>
      <td><input name="productDTO.chemical.lossOnDrying" type="text" class="NFText" size="30" value="${productDTO.chemical.lossOnDrying}"/></td>
    </tr>
     <s:if test="productDTO.chemical.productId = null">
    	<input type="hidden" name="productDTO.chemical.productId" value="${id}" />
    </s:if>
    <s:else>
    	<input type="hidden" name="productDTO.chemical.productId" value="${productDTO.chemical.productId}" />
    </s:else>
  </table>
  </form>
</div>


</body>
</html>