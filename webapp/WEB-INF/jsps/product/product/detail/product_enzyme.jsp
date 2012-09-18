<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>enzyme</title>
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
<div id="enzyme" class="part_k">
<form name="detailForm" id="detailForm">
  <table width="950" border="0" cellpadding="0" cellspacing="0"  class="General_table" >
    <tr>
      <th width="190">Purity</th>
      <td width="285"><input name="productDTO.enzyme.purity" type="text" class="NFText" size="30" value="${productDTO.enzyme.purity}"/></td>
      <th width="190">&nbsp;</th>
      <td width="285">&nbsp;</td>
    </tr>
    <tr>
      <th>Appearance</th>
      <td><input name="productDTO.enzyme.appearance" type="text" class="NFText" size="30" value="${productDTO.enzyme.appearance }"/></td>
      <th>Color</th>
      <td><input name="productDTO.enzyme.color" type="text" class="NFText" size="30" value="${productDTO.enzyme.color }"/></td>
    </tr>
    <tr>
      <th>Reaction Buffer</th>
      <td><input name="productDTO.enzyme.reactionBuffer" type="text" class="NFText" size="30" value="${productDTO.enzyme.reactionBuffer }"/></td>
      <th>Substrate</th>
      <td><input name="productDTO.enzyme.substrate" type="text" class="NFText" size="30" value="${productDTO.enzyme.substrate }"/></td>
    </tr>
    <tr>
      <th rowspan="2">Specific Activity</th>
      <td rowspan="2"><textarea name="productDTO.enzyme.specificActivity" class="content_textarea2" style="width:250PX;">${productDTO.enzyme.specificActivity}</textarea></td>
      <th>Concentration</th>
      <td><input name="productDTO.enzyme.concentration" type="text" class="NFText" size="30" value="${productDTO.enzyme.concentration}"/></td>
    </tr>
    <tr>
      <th>&nbsp;</th>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <th>Stability</th>
      <td><textarea name="productDTO.enzyme.stability" class="content_textarea2" style="width:250PX;">${productDTO.enzyme.stability}</textarea></td>
      <th>Quality Control</th>
      <td><textarea name="productDTO.enzyme.qualityControl" class="content_textarea2" style="width:250PX;">${productDTO.enzyme.qualityControl}</textarea></td>
    </tr>
        <s:if test="productDTO.enzyme.productId = null">
    	<input type="hidden" name="productDTO.enzyme.productId" value="${id}" />
    </s:if>
    <s:else>
    	<input type="hidden" name="productDTO.enzyme.productId" value="${productDTO.enzyme.productId}" />
    </s:else>
  </table>
  </form>
</div>
</body>
</html>