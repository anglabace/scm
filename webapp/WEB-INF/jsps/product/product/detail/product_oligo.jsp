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
<div id="Oligo" class="part_k">
<form name="detailForm" id="detailForm">
  <table width="950" border="0" cellpadding="0" cellspacing="0"  class="General_table" >
    <tr>
      <th width="190" rowspan="2">Sequence</th>
      <td width="285" rowspan="2"><textarea name="productDTO.oligo.sequence" class="content_textarea2" style="width:250PX;">${productDTO.oligo.sequence }</textarea></td>
      <th width="190">Modification</th>
      <td width="285"><input name="productDTO.oligo.modification" type="text" class="NFText" size="30" value="${productDTO.oligo.modification }"/></td>
    </tr>
    <tr>
      <th width="180">&nbsp;</th>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <th>Stability</th>
      <td><textarea name="productDTO.oligo.stability" class="content_textarea2" style="width:250PX;">${productDTO.oligo.stability }</textarea></td>
      <th>&nbsp;</th>
      <td>&nbsp;</td>
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