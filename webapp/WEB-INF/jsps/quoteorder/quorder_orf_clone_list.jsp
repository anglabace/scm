<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Search Orf Clone</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script type="text/javascript" language="javascript" src="${global_js_url}TabbedPanels.js"></script>
<script type="text/javascript" language="javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.validate.js" ></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
<s:if test="codeType == 'order'"><s:include value="../order/order_config.jsp"></s:include></s:if>
<s:else><s:include value="../quote/quote_config.jsp"></s:include></s:else>
<script>
var quorderNo = "${quorderNo}";
var codeType = "${codeType}";
</script>
<script src="${global_js_url}quoteorder/order_quotation_moredetail.js" type="text/javascript"></script>
</head>
<body class="content" style="background-image:none;">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="4" style="padding-top:10px;">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
      <tr>
        <th width="10%"> <input name="checkbox" name="serviceAllCheck" id="serviceAllCheck" type="checkbox"  /></th>
        <th width="30%">ORF Accession No.</th>
        <th width="30%">Sequence</th>
        <th>Price in pUC57</th>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="4" style="padding-bottom:20px; ">
    <div  style="width:100%; height:100%;">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="productList">
		<s:if test="refseq2orfpriceList != null && refseq2orfpriceList.size>0">
			<s:iterator value="refseq2orfpriceList" var="refseq2orfpriceDTO">
	        <tr>
	          <td width="10%">
	          	<div align="center">
	          	<s:if test='%{sequenceType == "Full Length"}'>
	          	  &nbsp;<input type="checkbox" value="accession:${accession}@@@seqType:${sequenceType}@@@genePrice:${syntheticPriceCDNA}@@@subcloningPrice:${vectorPriceCDNA-syntheticPriceCDNA}@@@geneCost:${syntheticCostCDNA}@@@subcloningCost:${vectorCostCDNA-syntheticCostCDNA}@@@realGenePrice:${syntheticPriceCDNA}@@@realSubCloningPrice:${vectorPriceCDNA-syntheticPriceCDNA}@@@realGeneCost:${syntheticCostCDNA}@@@realSubcloningCost:${vectorCostCDNA-syntheticCostCDNA}"  name="service_check_${accession}"/>
	            </s:if><s:else>
	          	  &nbsp;<input type="checkbox" value="accession:${accession}@@@seqType:${sequenceType}@@@genePrice:${syntheticPrice}@@@subcloningPrice:${vectorPrice-syntheticPrice}@@@geneCost:${syntheticCost}@@@subcloningCost:${vectorCost-syntheticCost}@@@realGenePrice:${syntheticPrice}@@@realSubCloningPrice:${vectorPrice-syntheticPrice}@@@realGeneCost:${syntheticCost}@@@realSubcloningCost:${vectorCost-syntheticCost}"  name="service_check_${accession}"/>
	            </s:else>
	            </div>
	          </td>
	          <td width="30%">${accession}</td>
	          <td width="30%">${sequenceType}</td>
	          <td width="30%"><s:if test='%{sequenceType == "Full Length"}'>${syntheticPriceCDNA}</s:if>
	          	<s:else>${syntheticPrice}</s:else></td>
	        </tr>
			</s:iterator>
		</s:if>
      </table>
    </div>
	</td>
  </tr>
  <tr>
    <td colspan="4">
      <div align="center">
      	<input name="accessionSelect" type="button" id="accessionSelect" class="style_botton"  value="Select" />
        <input type="button" name="closeDialog" id="closeDialog" value="Cancel" class="style_botton" />
      </div>
    </td>
  </tr>
</table>
<script>
$(document).ready(function(){
	
	$("#closeDialog").click(function () {
		parent.$('#orfCloneListDialog').dialog('close');
	});

	$("#accessionSelect").click(function () {
		parent.$('#orfCloneListDialog').dialog('close');
	});
});
</script>
</body>
</html>