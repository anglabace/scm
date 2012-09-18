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
<div id="Gene" class="part_k">
<form name="detailForm" id="detailForm">
  <table width="950" border="0" cellpadding="0" cellspacing="0"  class="General_table" >
    <tr>
      <th width="190">Accession No</th>
      <td width="285"><input name="productDTO.gene.accessionNo" type="text" class="NFText" size="30" value="${productDTO.gene.accessionNo}"/></td>
      <th width="190">Refseqid</th>
      <td width="285"><input name="productDTO.gene.refseqid" type="text" class="NFText" size="30" value="${productDTO.gene.refseqid}"/></td>
    </tr>
    <tr>
      <th>Cloning Site</th>
      <td><input name="productDTO.gene.cloningSite" type="text" class="NFText" size="30" value="${productDTO.gene.cloningSite}"/></td>
      <th>Vector</th>
      <td><input name="productDTO.gene.vector" type="text" class="NFText" size="30" value="${productDTO.gene.vector}"/></td>
    </tr>
    <tr>
      <th>Species</th>
      <td><input name="productDTO.gene.species" type="text" class="NFText" size="30" value="${productDTO.gene.species}"/></td>
      <th>&nbsp;</th>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <th>Sequence</th>
      <td><textarea name="productDTO.gene.sequence" class="content_textarea2" style="width:250PX;">${productDTO.gene.sequence}</textarea></td>
      <th>&nbsp;</th>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <th>Flank 3</th>
      <td><input name="productDTO.gene.flank3" type="text" class="NFText" size="30" value="${productDTO.gene.flank3}"/></td>
      <th>Flank 5</th>
      <td><input name="productDTO.gene.flank5" type="text" class="NFText" size="30" value="${productDTO.gene.flank5}"/></td>
    </tr>
    <tr>
      <th> Affiliation Catalog No</th>
      <td colspan="2"><input name="productDTO.gene.affiliation" type="text" class="NFText" size="30" value="${productDTO.gene.affiliation}"/>
      (GN000367,GN000368 )</td>
      <td>&nbsp;</td>
    </tr>
  </table>
  <input type="hidden" name="productDTO.gene.genemap" value="${productDTO.gene.genemap}" />
  <input type="hidden" name="productDTO.gene.organism" value="${productDTO.gene.organism}" />
   <s:if test="productDTO.gene.productId = null">
    	<input type="hidden" name="productDTO.gene.productId" value="${id}" />
    </s:if>
    <s:else>
    	<input type="hidden" name="productDTO.gene.productId" value="${productDTO.gene.productId}" />
    </s:else>
  </form>
</div>
</body>
</html>