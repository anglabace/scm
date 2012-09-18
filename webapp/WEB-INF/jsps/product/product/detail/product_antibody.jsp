<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Antibody</title>
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
<div id="Antibody" class="part_k">
	<form name="detailForm" id="detailForm">
  <table width="950" border="0" cellpadding="0" cellspacing="0"  class="General_table" >
    <tr>
      <th width="190">Accession No</th>
      <td width="285"><input name="productDTO.antibody.accessionNo" type="text" class="NFText" size="30" value="${productDTO.antibody.accessionNo}"/></td>
      <th width="190">Antigen Species</th>
      <td width="285"><input name="productDTO.antibody.antigenSpecies" type="text" class="NFText" size="30" value="${productDTO.antibody.antigenSpecies}"/></td>
    </tr>
    <tr>
      <th width="180">Predicated Band Size</th>
      <td><input name="productDTO.antibody.predictedBandSize" type="text" class="NFText" size="30" value="${productDTO.antibody.predictedBandSize}"/></td>
      <th>Observed Band Size</th>
      <td><input name="productDTO.antibody.observedBandSize" type="text" class="NFText" size="30" value="${productDTO.antibody.observedBandSize}"/></td>
    </tr>
    <tr>
      <th>Host Species</th>
      <td><input name="productDTO.antibody.hostSpecies" type="text" class="NFText" size="30" value="${productDTO.antibody.hostSpecies}"/></td>
      <th>Purification</th>
      <td><input name="productDTO.antibody.purification" type="text" class="NFText" size="30" value="${productDTO.antibody.purification}"/></td>
    </tr>
    <tr>
      <th rowspan="2">Cross Reactivity</th>
      <td rowspan="2"><textarea name="productDTO.antibody.crossReactivity" class="content_textarea2" style="width:250PX;">${productDTO.antibody.crossReactivity}</textarea></td>
      <th>Species Reactivity</th>
      <td><input name="productDTO.antibody.speciesReactivity" type="text" class="NFText" size="30" value="${productDTO.antibody.speciesReactivity }"/></td>
    </tr>
    <tr>
      <th>&nbsp;</th>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <th>Conjugation</th>
      <td><input name="productDTO.antibody.conjugation" type="text" class="NFText" size="30" value="${productDTO.antibody.conjugation }"/></td>
      <th>&nbsp;</th>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <th>Clone ID</th>
      <td><input name="productDTO.antibody.cloneId" type="text" class="NFText" size="30" value="${productDTO.antibody.cloneId}"/></td>
      <th>Supplier Clone ID</th>
      <td><input name="productDTO.antibody.supplierCloneId" type="text" class="NFText" size="30" value="${productDTO.antibody.supplierCloneId }"/></td>
    </tr>
    <tr>
      <th>Immunogen</th>
      <td><input name="productDTO.antibody.immunogen" type="text" class="NFText" size="30" value="${productDTO.antibody.immunogen}"/></td>
      <th>Endotoxin Level</th>
      <td><input name="productDTO.antibody.endotoxinLevel" type="text" class="NFText" size="30" value="${productDTO.antibody.endotoxinLevel }"/></td>
    </tr>
    <tr>
      <th>Subclass</th>
      <td><input name="productDTO.antibody.subclass" type="text" class="NFText" size="30" value="${productDTO.antibody.subclass}"/></td>
      <th rowspan="2">Specificity</th>
      <td rowspan="2"><textarea name="productDTO.antibody.specificity" class="content_textarea2" style="width:250PX;">${productDTO.antibody.specificity }</textarea></td>
    </tr>
    <tr>
      <th>Concentration</th>
      <td><input name="productDTO.antibody.concentration" type="text" class="NFText" size="30" value="${productDTO.antibody.concentration }"/></td>
    </tr>
    <tr>
      <th>Fusionpartner</th>
      <td><textarea name="productDTO.antibody.fusionPartner" class="content_textarea2" style="width:250PX;">${productDTO.antibody.fusionPartner }</textarea></td>
      <th>Reconstitution</th>
      <td><textarea name="productDTO.antibody.reconstitution" class="content_textarea2" style="width:250PX;">${productDTO.antibody.reconstitution }</textarea></td>
    </tr>
    <tr>
      <th>Appearance</th>
      <td><input name="productDTO.antibody.appearance" type="text" class="NFText" size="30" value="${productDTO.antibody.appearance }"/></td>
      <th>Sensitivity</th>
      <td><input name="productDTO.antibody.sensitivity" type="text" class="NFText" size="30" value="${productDTO.antibody.sensitivity }"/></td>
    </tr>
    <tr>
      <th>Stability</th>
      <td><textarea name="productDTO.antibody.stability" class="content_textarea2" style="width:250PX;">${productDTO.antibody.stability }</textarea></td>
      <th>Quality Control</th>
      <td><textarea name="productDTO.antibody.qualityControl" class="content_textarea2" style="width:250PX;">${productDTO.antibody.qualityControl }</textarea></td>
    </tr>
    <s:if test="productDTO.antibody.productId = null">
    	<input type="hidden" name="productDTO.antibody.productId" value="${id}" />
    </s:if>
    <s:else>
    	<input type="hidden" name="productDTO.antibody.productId" value="${productDTO.antibody.productId}" />
    </s:else>
  </table>
  </form>
</div>
</body>
</html>