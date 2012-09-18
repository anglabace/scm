<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base id="myBaseId" href="${global_url}" />
<title>scm more detail</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />

<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}show_tag.js"></script>

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.validate.js" ></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}expland.js"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
<script src="${global_js_url}jquery.funkyUI.js" type="text/javascript"></script>
<s:include value="order_config.jsp"></s:include>
<script type="text/javascript">
var itemNum = 0;
var sessNoName = "sessOrderNo";
var batchUrlObj = {
    geneSynSaveUrl : "order_more!saveGeneSynthesis.action",
    searchEnzymeUrl : "qu_order_more!searchEnzyme.action",
    custCloningSaveUrl : "order_more!saveCustCloning.action",
    createItemUrl : "order_item!saveBatchSessionItem.action",
    batchSaveUrl : "order_more!saveBatchGeneSynthesis.action",
    itemList : "order_item!list.action",
    batchCustNo : "${batchCustNo}",
    sessNO : "${sessOrderNo}"
}

</script>
<script language="javascript" type="text/javascript" src="${global_js_url}quoteorder/gene_batch.js"></script>
<style>
	#tt{position:absolute; display:block; background:url(images/tt_left.gif) top left no-repeat}
	#tttop {display:block; height:5px; margin-left:5px;  overflow:hidden}
	#ttcont {display:block; padding:2px 12px 3px 7px; margin-left:5px; background:#666; color:#FFF}
	#ttbot {display:block; height:5px; margin-left:5px;  overflow:hidden}
</style>
<script>
var sessOrderNo = "${sessOrderNo}";
var itemId = "${itemId}";
var codeType = "${codeType}";
var global_url = "${global_url}";

function prepSel(){
    var prepSel = $("input[name='plasmidPrepFlag']:checked");
    if(prepSel.val()=="Y"){
       $("#prepWeightSel").attr("disabled", false);
    }else{
       $("#prepWeightSel").attr("disabled", true);
    }
}

function closeBatch(){
    parent.$('#itemAddBatch').dialog( 'close' ) ;
}
</script>
</head>
<body>
	 <!-- ******************************Gene Synthesis******************************************************* -->
     <form id="geneSynthesisForm" method="post">
         <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
             <tr>
               <td colspan="5"> 1.Enter sequences in multiple FASTA txt format (or from excel file, the first column is
                   name, the second column id sequence) as shown in example below, can include restriction sites and
                   stop codon if needed.<br/>
                   2.Only DNA sequences can be entered, error message will be given for characters other than A, T, C or
                   G.<br/>
                   3.Extra 5' or 3' sequence can be entered in “5' sequence” and “3' sequence” field, and will be added
                   to all sequences.<br/>
                   4.In the subcloning interface, a discount rate can be entered. The system will calculate the
                   subcloning price for each gene based on size, and then apply the discount rate to subcloning price.<br/>
                   5.If direct cloning option is checked, no subcloning item will be generated; the gene will be put
                   into the destination vector directly.<br/>
                   6.All gene sequences are subject to analysis after they are entered, for non-complex genes, the
                   default price gene synthesis will be set by the system. For complicated genes, price will be set as
                   TBD, account manager have to analyze these genes manually and set price.
                   <br/><br/>
               </td>
          </tr>
         </table>
         <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
             <tr>
                 <th width="100px">5' Sequence:</th>
                 <td>
                     <input name="sequence5" type="text" class="NFText" size="23" id="sequence5"/>
                     <input type="button" class="style_botton2" value="Search Enzyme" id="searchEnzymeTrigger5"/>
                 </td>
                 <th>3' Sequence:</th>
                 <td><input name="sequence3" type="text" class="NFText" size="23" id="sequence3"/></td>
                 <td><input type="button" class="style_botton2" value="Search Enzyme" id="searchEnzymeTrigger3"/></td>
             </tr>
             <tr>
                 <th><span class="important">*</span>DNA Sequence:</th>
                 <td colspan="4">
                     <input name="sequenceType" type="hidden" value="DNA"/>
                     <input name="bpPrice" type="hidden" value="${geneSynthesis.bpPrice }"/>
                     <input name="bpCost" type="hidden" value="${geneSynthesis.bpCost }"/>
                     <input name="codeOptmzFlag" type="hidden" value="N"/>
                     <s:textarea name="sequenceContent" id="sequenceContent" Class="content_textarea2" cssStyle="height:150px;width:360px"/>
                 </td>
             </tr>
             <tr>
                 <th>Cloning Strategy</th>
                 <td colspan="4">
                     <input name="cloningFlag" id="cloningFlag" type="radio" value="N" checked="checked" serviceName="custCloning" onclick="delBatchCloning();"/>
                     <input name="stdVectorName" type="text" class="NFText" value="pUC57" size="20" readonly="readonly"/>
                     Cloning site
                     <input name="cloningSite" type="text" class="NFText" size="20"/>
                     <a href="qu_order_more!showVector.action" target="_blank"> pUC57 vector map</a>
                 </td>
             </tr>
             <tr>
                 <th></th>
                 <td colspan="4">(Example:BamHI-Hindlll,<em>Eco</em>R V; Note:If leave it blank,an available site will
                     be chosen to insert the gene by GenScript,usually <em>Eco</em>R V).
                 </td>
             </tr>
             <tr>
                 <th></th>
                 <td colspan="4">
                     <input type="radio" name="cloningFlag" value="Y" serviceName="custCloning" onclick="showCloning()"/>
                     Other
                 </td>
             </tr>
             <tr>
                 <th></th>
                 <td colspan="4">
                     <input type="radio" name="cloningFlag" value="D" serviceName="custCloning" onclick="showDirectCloning()"/>
                     Direct Cloning
                 </td>
             </tr>
             <tr>
                 <th width="50px">Plasmid Preparation</th>
                 <td colspan="4">
                     <input name="plasmidPrepFlag" type="radio" value="N" checked="checked"  onclick="prepSel();"/>
                     Standard delivery:
                     <input name="" type="text" class="NFText" value="4 ug" size="20" disabled="disabled"/>
                     (Free of charge)
                     <input name="stdPlasmidWt" type="hidden" value="4"/>
                     <input name="stdPlasmidWtUom" type="hidden" value="ug"/>
                 </td>
             </tr>
             <tr>
                 <th></th>
                 <td colspan="4">
                     <input type="radio" name="plasmidPrepFlag" value="Y" onclick="prepSel();"/>
                     Customer plasmid Preparation
                     <select id="prepWeightSel" style="width:80px;" disabled onchange="quoteWeightChange();">
                         <option value="100 ug">100 ug</option>
                         <option value="200 ug">200 ug</option>
                         <option value="500 ug">500 ug</option>
                         <option value="1 mg">1 mg</option>
                         <option value="2 mg">2 mg</option>
                         <option value="10 mg">10 mg</option>
                         <option value="20 mg">20 mg</option>
                         <option value="50 mg">50 mg</option>
                         <option value="100 mg" selected="selected">100 mg</option>
                         <option value="200 mg">200 mg</option>
                         <option value="500 mg">500 mg</option>
                         <option value="1000 mg">1000 mg</option>
                         <option value="1500 mg">1500 mg</option>
                         <option value="2000 mg">2000 mg</option>
                         <option value="0">Other</option>
                     </select>
                     <s:hidden name="plasmidPreparation.prepWtUom" id="prepWtUom" value="mg"/><div id="prepDiv" style="display:none"><input type="text" name="plasmidPreparation.prepWeightStr" id="prepWeightStr" value="100"/>mg</div>
                     <input id="txt_04" type="text" class="NFText" size="20" style="display:none;"/>
                 </td>
             </tr>
             <tr>
                 <th></th>
                 <td colspan="3">
                     Quality grade:&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="plasmidPreparation.qualityGrade" value="Research Grade"/>Research Grade(Predominantly supercoiled)<br/>
                     <div style="margin-left:80px">
                      <input type="radio" name="plasmidPreparation.qualityGrade" value="SC Grade" checked="checked"/>SC Grade (≥95% Supercoilded, ≤0.03 EU/ug Endotoxin) <br/>
                      <input type="radio" name="plasmidPreparation.qualityGrade" value="Advanced SC Grade"/>Advanced SC Grade(≥95% Supercoilded, ≤0.005 EU/ug Endotoxin) <br/>
                      </div>
                 </td>
             </tr>
             <tr>
                 <th></th>
                 <td colspan="3" align="center">
                     <br/>
                     <input type="button" class="style_botton" value="Add" id="addServiceBtn"/>
                     <input type="button" class="style_botton" value="Closed" id="cancel" onclick="closeBatch();"/>
                 </td>
             </tr>
         </table>
     </form>

     <input id="sessOrderNo" type="hidden" value="${sessOrderNo}" />
     <input id="itemId" name="itemId" type="hidden" value="${itemId}" />
     <input id="validateFlag" type="hidden" value="1" />
     <div id="searchEnzymeDialog" title="Search Enzyme"></div>
<form action="" method="post" id="downloadForm">
<input name="fileName" id="myFileName" type="hidden"/>
<input name="filePath" id="myFilePath" type="hidden"/>
</form>
</body>
</html>
