<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
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
<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
<script src="${global_js_url}jquery.funkyUI.js" type="text/javascript"></script>
<style>
	#tt{position:absolute; display:block; background:url(images/tt_left.gif) top left no-repeat}
	#tttop {display:block; height:5px; margin-left:5px;  overflow:hidden}
	#ttcont {display:block; padding:2px 12px 3px 7px; margin-left:5px; background:#666; color:#FFF}
	#ttbot {display:block; height:5px; margin-left:5px;  overflow:hidden}
</style>

<s:include value="order_config.jsp"></s:include>
<script>
var sessOrderNo = "${sessOrderNo}";
var itemId = "${itemId}";
var codeType = "${codeType}";
var global_url = "${global_url}";
var showType = "${show}_${tab}";
var show = "${show}";

$(document).ready(function(){
	
	jQuery.validator.addMethod("isSequence", function(value, element, param) {
		  return this.optional(element) || /^([AGTCagtc])*$/.test(value);       
		}, "Please enter a valid sequence");
	
	// validate signup form on keyup and submit
	$("#geneSynthesisForm").validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).removeClass(errorClass);
	    },
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	        	var tmpIndex = getTabIndexBy("Gene Synthesis");
	        	if(tmpIndex != -1){
	        		showTab('dhtmlgoodies_tabView1', tmpIndex);
	        	}
	            alert(value);
	            return false;
	        });
		},
		rules: {
			geneName:{required:true},
			sequence: {required:true}
		},
		messages: {
			geneName:{required:"Please enter the 'Gene Name' in 'More Detail','Gene Synthesis' tab."},
			sequence: {required:"Please enter the 'sequence' in 'More Detail','Gene Synthesis' tab."}
		},
		errorPlacement: function(error, element) {
		}			
	});

	$("#cloningForm").validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).removeClass(errorClass);
	    },
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){//Cloning Strategy
	        	var tmpIndex = getTabIndexBy("Cloning Strategy");
	        	if(tmpIndex != -1){
	        		showTab('dhtmlgoodies_tabView1', tmpIndex);
	        	}
	            alert(value);
	            return false;
	        });
		},
		rules: {
//			tgtVectorSize:{required:true,digits:true},
            tgtVectorSize:{chkCloningOtherVectorSize:true},
            tgtResistance:{chkCloningOtherRes:true},
            tgtResistanceOther:{chkCloningOtherResOther:true},
			tgtCopyNo:{required:true}
		},
		messages: {
//			tgtVectorSize:{required:"Please enter the 'Vector size' in 'More Detail','Cloning Strategy' tab", digits:"Please enter a valid 'Vector size' in 'More Detail','Cloning Strategy' tab."},
            tgtVectorSize:{chkCloningOtherVectorSize:"Please enter the 'Vector size' in 'More Detail','Cloning Strategy' tab."},
			tgtResistance:{chkCloningOtherRes:"Please enter 'Resistance' in 'More Detail','Cloning Strategy."},
			tgtResistanceOther:{chkCloningOtherResOther:"Please enter 'Resistance' in 'More Detail','Cloning Strategy."},
			tgtCopyNo: {required:"Please select the 'Copy number' in 'More Detail','Cloning Strategy' tab."}
		},
		errorPlacement: function(error, element) {
		}
	});

     //add by zhanghuibin
    jQuery.validator.addMethod("chkCloningOtherVectorSize", function(value, element){
        if($("#tgtVector").val() == "Other" && $("input[name='tgtVectorSize']").val()==""){
            return false;
        }
        return true;
    }, "Please enter 'Vector size' in 'More Detail','Cloning Strategy.");

    jQuery.validator.addMethod("chkCloningOtherRes", function(value, element){
        if($("#tgtVector").val() == "Other" && $("#tgtResistance").val()==""){
            return false;
        }
        return true;
    }, "Please enter 'Resistance' in 'More Detail','Cloning Strategy.");

    jQuery.validator.addMethod("chkCloningOtherResOther", function(value, element){
        if($("#tgtVector").val() == "Other" && $("#tgtResistance").val()=="" && $("#tgtResistanceOther").val()==""){
            return false;
        }
        return true;
    }, "Please enter 'Resistance' in 'More Detail','Cloning Strategy.");

	$("#plasmidForm").validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).removeClass(errorClass);
	    },
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            return false;
	        });
		},
		rules: {
			"plasmidPreparation.plasmidName":{required:true},
			"plasmidPreparation.plasmidSize":{required:true,digits:true},
			"plasmidPreparation.copyNumber":{required:true},
			"plasmidPreparation.sequence":{isSequence:true}
		},
		messages: {
			"plasmidPreparation.plasmidName":{required:"Please enter the name"},
			"plasmidPreparation.plasmidSize":{required:"Please enter the size", digits:"Please enter a valid size"},	
			"plasmidPreparation.copyNumber": {required:"Please select the Copy number"},
			"plasmidPreparation.sequence":{isSequence:"Please enter a valid sequence."}
		},
		errorPlacement: function(error, element) {
		}
	});

	$("#tmplForm").validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).removeClass(errorClass);
	    },
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	        	var tmpIndex = getTabIndexBy("Template Info");
	        	if(tmpIndex != -1){
	        		showTab('dhtmlgoodies_tabView1', tmpIndex);
	        	}
	            alert(value);
	            return false;
	        });
		},
		rules: {
			tmplInsertName:{required:true},
			tmplSequence:{required:true}
		},
		messages: {
			tmplInsertName: {required:"Please select the 'Insert name' in 'More Detail','Template Info' tab."},
			tmplSequence: {required:"Please select the 'Insert sequence' in 'More Detail','Template Info' tab."}
		},
		errorPlacement: function(error, element) {
		}
	});

	$("#targetForm").validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).removeClass(errorClass);
	    },
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            return false;
	        });
		},
		rules: {
			variantSequence:{required:true},
			variantName:{required:true}
		},
		messages: {
			variantSequence: {required:"Please enter the 'sequence' in 'More Detail','Target Construct' tab."},
			variantName:{required:"Please enter the 'Target Insert Name' in 'More Detail','Target Construct' tab."}
		},
		errorPlacement: function(error, element) {
		}
	});

	$("#stdPeptideForm").validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).removeClass(errorClass);
	    },
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            return false;
	        });
		},
		rules: {
			seqLength:{digits:true},
			name:{required:true},
			sequence: { required:true},
			aliquoteVialQty: { digits:true}
		},
		messages: {
			seqLength:{digits:"Please enter a valid Sequence Length"},	
			name: {required:"Please enter the Standard Peptide Name"},
			sequence: {required:"Please enter the Sequence"},
			aliquoteVialQty: { digits:"Please enter a valid Aliquoting to"}
		},
		errorPlacement: function(error, element) {
		}
	});

	$("#batchPeptideForm").validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).removeClass(errorClass);
	    },
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            return false;
	        });
		},
		rules: {
			aliquoteVialQty:{digits:true},
			disulfideBridge:{digits:true},
			sequence: { required:true }
		},
		messages: {
			aliquoteVialQty:{digits:"Please enter a valid Disulfide Bridge"},
			disulfideBridge:{digits:"Please enter a valid Disulfide Bridge"},
			sequence: {required:"Please enter the Sequence"}	
		},
		errorPlacement: function(error, element) {
		}
	});

	/* $("#libraryPeptideForm").validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).removeClass(errorClass);
	    },
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            return false;
	        });
		},
		rules: {
			"peptide.name":{required:true}
		},
		messages: {
			"peptide.name": {required:"Please enter the Standard Peptide Name"}
		},
		errorPlacement: function(error, element) {
		}
	});

	$("#arrayPeptideForm").validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).removeClass(errorClass);
	    },
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            return false;
	        });
		},
		rules: {
			"peptide.name":{required:true},
			"peptide.quantity":{digits:true}
		},
		messages: {
			"peptide.name": {required:"Please enter the Standard Peptide Name"},
			"peptide.quantity":{digits:"Please enter a valid Peptide Quantity"}
		},
		errorPlacement: function(error, element) {
		}
	}); */

	$("#rnaForm").validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).removeClass(errorClass);
	    },
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            return false;
	        });
		},
		rules: {
			geneName:{required:true},
			vectorName:{required:true, chkVectorName:true},
			quantity:{required:true},
			sequenceInsert:{required:true}
		},
		messages: {
			geneName: {required:"Please enter the Gene Name in 'More Detail','miRNA/siRNA' tab."},
			vectorName:{required:"Please select a Vector Name in 'More Detail','miRNA/siRNA' tab."},
			quantity:{required:"Please select a Quantity in 'More Detail','miRNA/siRNA' tab."},
			sequenceInsert:{required:"Please enter the Sequence  in 'More Detail','miRNA/siRNA' tab."}
		},
		errorPlacement: function(error, element) {
		}
	});

    //add by zhanghuibin
    jQuery.validator.addMethod("chkVectorName", function(value, element) {
        if($("#vectorName").val()=="Other" && $("#vectorOther").val()==""){
            return false;
        }
        return true;
      }, "Please enter Vector Name in 'More Detail','miRNA/siRNA' tab.");

	$( '#searchOrfCloneTrigger' ).click( function() {
		var accessionNo = $("#search_accessionNo").attr("value");
		if (accessionNo == undefined || $.trim(accessionNo) == "") {
			return;
		}
		parent.$('#orfCloneListDialog').dialog( 'open' ) ;
		parent.$( '#orfCloneListDialog' ).attr( 'innerHTML' , '<iframe  src="qu_order!searchOrfClone.action?quorderNo='+orderquoteObj.sessNoValue+'&codeType='+orderquoteObj.type+'&accessionNo='+accessionNo+'" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="No" ></iframe>' ) ;
	}) ;
});
</script>
<script language="javascript" type="text/javascript" src="${global_js_url}quoteorder/order_quotation_moredetail.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}quoteorder/quoteorder_sales_information.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}quoteorder/order_quotation_rna.js"></script>
</head>

<body>

<div id="dhtmlgoodies_tabView1" >
	 <!-- ******************************Gene Synthesis******************************************************* -->
	<s:if test="show == 'gene'">
	     <div class="dhtmlgoodies_aTab">
	       	<s:include value="order_more_gene.jsp"></s:include>
		 </div>
	     <div class="dhtmlgoodies_aTab">
	       <s:include value="order_more_codon.jsp"></s:include>
	     </div>
	     <div class="dhtmlgoodies_aTab">
	       <s:include value="order_more_cloning.jsp"></s:include>
	     </div>
	     <div class="dhtmlgoodies_aTab">
	        <s:include value="order_more_plasmid.jsp"></s:include>
	     </div>
		 <div class="dhtmlgoodies_aTab">
	     	<s:include value="order_more_usage.jsp"></s:include>
	     </div>
	     <div class="dhtmlgoodies_aTab">
			<s:include value="order_more_moreinfo.jsp"></s:include>
	     </div>
     </s:if>
     <!-- *************************Cloning Strategy ************************************************************ -->
     
     <s:if test="show == 'cloning'">
 	<div class="dhtmlgoodies_aTab">
       <s:include value="order_more_template.jsp"></s:include>
     </div>
	<div class="dhtmlgoodies_aTab">
        <s:include value="order_more_target.jsp"></s:include>
     </div>
     <div class="dhtmlgoodies_aTab">
        <s:include value="order_more_cloning.jsp"></s:include>
     </div>
     <div class="dhtmlgoodies_aTab">
        <s:include value="order_more_plasmid.jsp"></s:include>
     </div>
     <div class="dhtmlgoodies_aTab">
        <s:include value="order_more_usage.jsp"></s:include>
     </div>
     <div class="dhtmlgoodies_aTab">
        <s:include value="order_more_moreinfo.jsp"></s:include>
     </div>
     </s:if>
     
     <!-- ************************** Plasmid Preparation *********************************************************** -->
     <s:if test="show == 'plasmid'">
          <div class="dhtmlgoodies_aTab">
	       <s:include value="order_more_plasmid.jsp"></s:include>
	     </div>
	     <div class="dhtmlgoodies_aTab">
	        <s:include value="order_more_moreinfo.jsp"></s:include>
	     </div>
     </s:if>
     <!-- **************************Mutagenesis *********************************************************** -->
     
     <s:if test="show == 'muta'">
     <div class="dhtmlgoodies_aTab">
       <s:include value="order_more_template.jsp"></s:include>
     </div>
	<div class="dhtmlgoodies_aTab">
        <s:include value="order_more_target.jsp"></s:include>
     </div>
     <div class="dhtmlgoodies_aTab">
        <s:include value="order_more_cloning.jsp"></s:include>
     </div>
     <div class="dhtmlgoodies_aTab">
        <s:include value="order_more_plasmid.jsp"></s:include>
     </div>
     <div class="dhtmlgoodies_aTab">
        <s:include value="order_more_usage.jsp"></s:include>
     </div>
     <div class="dhtmlgoodies_aTab">
        <s:include value="order_more_moreinfo.jsp"></s:include>
     </div>
     </s:if>
     
     <!-- ************************** ORF Clone Start *********************************************************** -->
     <s:if test="show == 'orfClone'">
	     <s:include value="order_more_orf_clone.jsp"></s:include>
     </s:if>
     <!-- ************************** ORF Clone End *********************************************************** -->
     
     <!-- ************************** Oligo Start *********************************************************** -->
     <s:if test="show == 'oligo'">
	     <div class="dhtmlgoodies_aTab">
	     	<s:include value="order_more_oligo_detail.jsp"></s:include>
	     </div>	
	     <div class="dhtmlgoodies_aTab">
	        <s:include value="order_more_oligo_batch_orders.jsp"></s:include>
	     </div>
     </s:if>
     <!-- ************************** Oligo End *********************************************************** -->
     
     <!-- ************************** Sequencing Start *********************************************************** -->
     <s:if test="show == 'sequencing'">
	     <s:include value="order_more_sequencing.jsp"></s:include>
     </s:if>
     <!-- ************************** Sequencing End *********************************************************** -->
     
<!-- ************************** Mutation Libraries Start *********************************************************** -->
     <s:if test="show == 'mutaLib'">
	     <div class="dhtmlgoodies_aTab">
	     	<s:include value="order_more_muta_lib.jsp"></s:include>
	     </div>
	     <div class="dhtmlgoodies_aTab">
       		<s:include value="order_more_template.jsp"></s:include>
	     </div>
	     <div class="dhtmlgoodies_aTab">
	       <s:include value="order_more_cloning.jsp"></s:include>
	     </div>
	     <div class="dhtmlgoodies_aTab">
	     	<s:include value="order_more_plasmid.jsp"></s:include>
	     </div>
	     <div class="dhtmlgoodies_aTab">
	     	<s:include value="order_more_usage.jsp"></s:include>
	     </div>
	     <div class="dhtmlgoodies_aTab">
	     	<s:include value="order_more_moreinfo.jsp"></s:include>
	     </div>
     </s:if>
<!-- ************************** Mutation Libraries End *********************************************************** -->
     
     <!-- ************************** Custom Service Start *********************************************************** -->
     <s:if test="show == 'customService'">
	     	<s:include value="order_more_custom_service.jsp"></s:include>
     </s:if>
     <!-- ************************** Custom Service End *********************************************************** -->
     
     
<!-- **************************Peptide *********************************************************** -->
 <s:if test="show == 'peptide' || show == 'librayPeptide' || show == 'arrayPeptide'">
 	<s:include value="order_more_peptide.jsp"></s:include>
 </s:if>
 
 <s:if test="show == 'rna'">
	     <div class="dhtmlgoodies_aTab">
	       	<s:include value="order_more_rna.jsp"></s:include>
		 </div>
	     <div class="dhtmlgoodies_aTab">
	        <s:include value="order_more_plasmid.jsp"></s:include>
	     </div>
		 <div class="dhtmlgoodies_aTab">
	     	<s:include value="order_more_usage.jsp"></s:include>
	     </div>
	     <div class="dhtmlgoodies_aTab">
			<s:include value="order_more_moreinfo.jsp"></s:include>
	     </div>
</s:if>
     <input id="saveMoreDetailTrigger" show="${show}" type="button" style="display:none;" />
     <input id="sessOrderNo" type="hidden" value="${sessOrderNo}" />
     <input id="itemId" type="hidden" value="${itemId}" />
     <input id="codeType" type="hidden" value="${codeType}" />
     <input id="validateFlag" type="hidden" value="1" />
     <%--add by zhanghuibin 初始化form数据，用于form数据比较--%>
     <input id="geneSynthesisForm_initData" type="hidden" value=""/>
     <input id="rnaForm_initData" type="hidden" value=""/>
     <input id="orfCloneForm_initData" type="hidden" value=""/>
     <input id="plasmidForm_initData" type="hidden" value=""/>
     <input id="oligoDetailForm_initData" type="hidden" value=""/>
     <input id="customServiceForm_initData" type="hidden" value=""/>
     <input id="codonForm_initData" type="hidden" value=""/>
     <input id="geneUsageForm_initData" type="hidden" value=""/>
     <input id="moreInfoForm_initData" type="hidden" value=""/>
     <input id="peptideForm_initData" type="hidden" value=""/>
     <input id="stdPeptideForm_initData" type="hidden" value=""/>
     <input id="pkgForm_initData" type="hidden" value=""/>
     <input id="mutaForm_initData" type="hidden" value=""/>
     <input id="mutaLibForm_initData" type="hidden" value=""/>
     <input id="cloningForm_initData" type="hidden" value=""/>
     <input id="antibodyForm_initData" type="hidden" value=""/>
     <%--<input id="_initData" type="hidden" value=""/>--%>
</div>

  <!-- **************************Protein&Bioprocess&Pharmeceutical&Antibody drug *********************************************************** -->
 <s:if test="show == 'pro'">
 	<s:include value="order_more_pkg.jsp"></s:include>
 </s:if>
 <!-- **************************Protein&Bioprocess&Pharmeceutical&Antibody drug sub...*********************************************************** -->
 <s:if test="show == 'prosub'">
 	<s:include value="order_more_pkg_sub.jsp"></s:include>
 </s:if>
 
 <!-- **************************antibody.*********************************************************************************************************** -->
 <s:if test="show == 'antibody'">
	<s:include value="order_more_antibody.jsp"></s:include>
</s:if>
<form action="" method="post" id="downloadForm">
<input name="fileName" id="myFileName" type="hidden"/>
<input name="filePath" id="myFilePath" type="hidden"/>
</form> 
<script type="text/javascript">
function init(showType){
	var tmpArr = showType.split("_");
	var show = tmpArr[0];
	if(tmpArr[1]){
		var tab = tmpArr[1];
	}else{
		var tab = "";
	}
	if(show == "gene"){
		initTabs('dhtmlgoodies_tabView1',Array('Gene Synthesis','Codon Optimization','Cloning Strategy','Plasmid Preparation', 'Gene Usage','More Info'), 0, 960, 260);
	}else if(show == "rna"){
		initTabs('dhtmlgoodies_tabView1',Array('miRNA/siRNA','Plasmid Preparation', 'Gene Usage','More Info'), 0, 960, 500);
	}else if(show == "cloning"){
		initTabs('dhtmlgoodies_tabView1',Array('Template Info','Target Construct','Cloning Strategy','Plasmid Preparation','Gene Usage','More Info'), 0, 960, 260);
	}else if(show == "plasmid"){
		initTabs('dhtmlgoodies_tabView1',Array('Plasmid Preparation','More Info'), 0, 960, 500);
	}else if(show == "muta"){
		initTabs('dhtmlgoodies_tabView1',Array('Template Info','Target Construct','Cloning Strategy','Plasmid Preparation','Gene Usage', 'More Info'), 0, 960, 260);
	}else if(show == "oligo"){
		initTabs('dhtmlgoodies_tabView1',Array('Details','Batch Orders'),0,960,650);
	}else if(show == "peptide"){
		initTabs('dhtmlgoodies_tabView1',Array('Standard Peptide','Batch Order'),0,965,290);
	}else if(show == "librayPeptide"){
		initTabs('dhtmlgoodies_tabView1',Array('Standard Peptide','Batch Order','Peptide Library'),0,965,290);
		deleteTab('Standard Peptide');
		deleteTab('Batch Order');
	}else if(show == "arrayPeptide"){
		initTabs('dhtmlgoodies_tabView1',Array('Standard Peptide','Batch Order','Peptide Library','Peptide Array'),0,965,290);
		deleteTab('Standard Peptide');
		deleteTab('Batch Order');
		deleteTab('Peptide Library');
	} else if (show == "mutaLib") {
		initTabs('dhtmlgoodies_tabView1',Array('Mutation Libraries','Template Info','Cloning Strategy','Plasmid Preparation', 'Gene Usage','More Info'), 0, 960, 420);
	}
	var title = getTitleByKey(tab);
	var tmpIndex = getTabIndexByTitle(title);
	if(tmpIndex != -1){
		showTab('dhtmlgoodies_tabView1', tmpIndex[1]);
	}
	initData(show);
	//$("#geneSynthesisForm").find("[name='stdVectorName']").attr("value","pUC57");
	
}

function initData(show){
	var re = /null/g;
	var dataStr = '${serviceListStr}'.replace(re, '\"\"').replace(/\n/gi, "").replace(/\r/gi, "");
	eval('var sers = '+dataStr+';');
	if(show == "gene"){
		if(sers.geneSynthesis){
			sers.geneSynthesis.itemId = sers.geneSynthesisItemId;
		}
		initDataGene(sers.geneSynthesis);
		if(sers.custCloning){
			sers.custCloning.itemId = sers.custCloningItemId;
		}
		initDataCloning(sers.custCloning);
		if(sers.plasmidPreparation){
			sers.plasmidPreparation.itemId = sers.plasmidPreparationItemId;
		}
		initPlasmidPreparation(sers.plasmidPreparation, 2);
        geneFormInitData(show);
	}else if(show == "rna"){
		if(sers.rna){
			sers.rna.itemId = sers.rnaItemId;
		}
		initDataRna(sers.rna);
		if(sers.plasmidPreparation){
			sers.plasmidPreparation.itemId = sers.plasmidPreparationItemId;
		}
		initPlasmidPreparation(sers.plasmidPreparation, 2);
        rnaFormInitData(show);

	}else if(show == "cloning"){
		if(sers.custCloning){
			sers.custCloning.itemId = sers.custCloningItemId;
		}
		if(sers.custCloning){
			sers.custCloning.itemId = sers.custCloningItemId;
		}
		initDataCloning(sers.custCloning);
		if(sers.plasmidPreparation){
			sers.plasmidPreparation.itemId = sers.plasmidPreparationItemId;
		}
		initPlasmidPreparation(sers.plasmidPreparation, 2);
        //add by zhanghuibin
        cloningFormInitData(show);
		var plasmidPrepFlag = $("#targetForm").find("[name='plasmidPrepFlag'][checked]").val();
		if(plasmidPrepFlag == "Y"){
			plasmidFormInitData(show);
		}
	}else if(show == "plasmid"){
		if(sers.plasmidPreparation){
			sers.plasmidPreparation.itemId = sers.plasmidPreparationItemId;
		}
		initPlasmidPreparation(sers.plasmidPreparation, 1);
        plasmidFormInitData(show);
	}else if(show == "muta"){
		if(sers.mutagenesis){
			sers.mutagenesis.itemId = sers.mutagenesisItemId;
		}
		initMutagenesis(sers.mutagenesis, 1, sers.parentId);
		if(sers.custCloning){
			sers.custCloning.itemId = sers.custCloningItemId;
		}
		initDataCloning(sers.custCloning);
		if(sers.plasmidPreparation){
			sers.plasmidPreparation.itemId = sers.plasmidPreparationItemId;
		}
		initPlasmidPreparation(sers.plasmidPreparation, 2);
		displayTabs();
        mutaFormInitData(show);
	}else if(show == "mutaLib"){
		if(sers.mutationLibraries){
			sers.mutationLibraries.itemId = sers.mutationLibrariesItemId;
		}
		initMutationLibraries(sers.mutationLibraries);
		if(sers.custCloning){
			sers.custCloning.itemId = sers.custCloningItemId;
		}
		initDataCloning(sers.custCloning);
		if(sers.plasmidPreparation){
			sers.plasmidPreparation.itemId = sers.plasmidPreparationItemId;
		}
		initPlasmidPreparation(sers.plasmidPreparation, 2);
		mutaLibFormInitData(show);
	}else if(show == "orfClone"){
		if(sers.orfClone){
			sers.orfClone.itemId = sers.orfCloneItemId;
		}
		initOrfClone(sers);
	}else if(show.indexOf("pro") > -1){
		initPkgService(sers.pkgServiceMap, sers.pkgServiceId);
        pkgFormInitData();
	}else if(show == "peptide"){
		if(sers.peptide){
			sers.peptide.itemId = sers.peptideItemId;
		}
		initPeptide(sers.peptide);
        formInitData("stdPeptideForm");
	}else if(show == "librayPeptide"){
		if(sers.peptide){
			sers.peptide.itemId = sers.peptideItemId;
		}
		initLibrayPeptide(sers.peptide);
	}else if(show == "arrayPeptide"){
		if(sers.peptide){
			sers.peptide.itemId = sers.peptideItemId;
		}
		initArrayPeptide(sers.peptide);
		
	}else if(show == "antibody"){
		if(sers.antibody){
			sers.antibody.itemId = sers.antibodyItemId;
		}
		initAntibody(sers.antibody);
        antibodyFormInitData(show);
	} else if(show == "oligo") {
		if(sers.oligo){
			sers.oligo.itemId = sers.oligoItemId;
		}
		initOligo(sers.oligo);
        formInitData("oligoDetailForm");
	} else if(show == "customService") {
		if(sers.customService){
			sers.customService.itemId = sers.customServiceItemId;
		}
		initCustomService(sers.customService);
        formInitData("customServiceForm");
	}
}

function displayTabs(){
	var disTabs = "${disableTabStr}";
	if(!disTabs){
		return;
	}
	var tmpArr = disTabs.split(",");
	var tmpTitle = "";
	for(var i=0; i<tmpArr.length; i++){
		tmpTitle = getTitleByKey(tmpArr[i]);
		disableTabByTitle(tmpTitle);	
	}
}

function formInitData(formName){
    if($("#" + formName + "_initData").attr("value")!=""){
        return true;
    }
    $("#" + formName + "_initData").attr("value", $("#" + formName).formSerialize());
}

function antibodyFormInitData(show){
	var antigenType = $("#antigenType").val();
	var antibodyName = $("#antibodyName").val();
	var customSequence = $("#customSequence").val();
	var comments = $("#antibodyComments").val();
	var dataStr = "antibody.antibodyName="+antibodyName+"&antibody.customSequence="+customSequence
					+"&antibody.comments="+comments+"&antibody.antigenType="+antigenType;
    $("#antibodyForm_initData").attr("value", dataStr);
}

//pkgForm页面数初始化
function pkgFormInitData(){
    var parentName = $("#parentName").val();
    var parentDescription = $("#parentDescription").val();
    $("#pkgForm_initData").attr("value", parentName + parentDescription);
}

function rnaFormInitData(show){
    formInitData("rnaForm");
    formInitData("geneUsageForm");
    formInitData("moreInfoForm");
    var plasmidPrepFlag = $("#rnaForm").find("[name='plasmidPrepFlag'][checked]").val();
    if (plasmidPrepFlag == "Y") {
        plasmidFormInitData(show);
    }
}

function geneFormInitData(show){
    formInitData("geneSynthesisForm");
    formInitData("codonForm");
    formInitData("geneUsageForm");
    formInitData("moreInfoForm");
    var cloningFlag = $("#geneSynthesisForm").find("[name='cloningFlag'][checked]").val();
    var plasmidPrepFlag = $("#geneSynthesisForm").find("[name='plasmidPrepFlag'][checked]").val();
    if (cloningFlag == "Y") {
        cloningFormInitData(show);
    }
    if (plasmidPrepFlag == "Y") {
       plasmidFormInitData(show);
    }
}

//
//function orfCloneFormInitData(){
    //var form = $("#orfCloneForm");
    //var cloningFlag = form.find("[name='cloningFlag'][checked]").val();
    //var plasmidPrepFlag = form.find("[name='plasmidPrepFlag'][checked]").val();
    //var orfCloneFormDate = "&cloningFlag="+cloningFlag+"&plasmidPrepFlag="+plasmidPrepFlag;
    //var usageData = "&"+$("#geneUsageForm").formSerialize();
	//var moreInfoData = "&"+$("#moreInfoForm").formSerialize();
    //orfCloneFormDate = orfCloneFormDate+usageData+moreInfoData;
    //$("#orfCloneForm_initData").attr("value", orfCloneFormDate)
//}

function mutaFormInitData(show){
    var moreInfoData = "&"+$("#moreInfoForm").formSerialize();
	var targetData = "&"+$("#targetForm").formSerialize();
	var usageData = "&"+$("#geneUsageForm").formSerialize();
    var tmplData = $("#tmplForm").formSerialize();
    var initData = tmplData+moreInfoData+targetData+usageData;
    $("#mutaForm_initData").attr("value", initData);
    //save cloning 校验
    var cloningData = $("#cloningForm").formSerialize();
    var cloningVaid = addFormNameToSerializeData("custCloning", cloningData, "itemId,upload");
    $("#cloningForm_initData").attr("value", cloningVaid);
    plasmidFormInitData(show);
}

function mutaLibFormInitData(show){
	var mutaLibtData = $("#mutaLibForm").formSerialize();
    var tmplData = "&"+$("#tmplForm").formSerialize();
	var usageData = "&"+$("#geneUsageForm").formSerialize();
    var moreInfoData = "&"+$("#moreInfoForm").formSerialize();
    var initData = mutaLibtData+tmplData+moreInfoData+usageData;
    $("#mutaLibForm_initData").attr("value", initData);
    var cloningFlag = $("#mutaLibForm").find("[name='cloningFlag'][checked]").val();
    var plasmidPrepFlag = $("#mutaLibForm").find("[name='plasmidPrepFlag'][checked]").val();
    if (cloningFlag == "Y") {
        cloningFormInitData(show);
    }
    if (plasmidPrepFlag == "Y") {
       plasmidFormInitData(show);
    }
}

function cloningFormInitData(show){
    var tmplData = "";
	var targetData = "";
	var usageData = "";
	var moreInfoData = "";
	var cloningData = "";
	var cloningFlag = "";
	var plasmidPrepFlag = "";
	if(show == "cloning"){
        tmplData = $("#tmplForm").formSerialize();
		 targetData = "&"+$("#targetForm").formSerialize();
		 usageData = "&"+$("#geneUsageForm").formSerialize();
		 moreInfoData = "&"+$("#moreInfoForm").formSerialize();
        cloningData = "&"+$("#cloningForm").formSerialize();
    }else{
        cloningData = $("#cloningForm").formSerialize();
    }
    var cloningStr = addFormNameToSerializeData("custCloning", tmplData+targetData+cloningData+usageData+moreInfoData, "itemId,upload");
    $("#cloningForm_initData").attr("value", cloningStr);
}

function plasmidFormInitData(show){
    if(show == "gene"){
		formInitData("geneSynthesisForm");
		formInitData("codonForm");
		formInitData("geneUsageForm");
		formInitData("moreInfoForm");
	} else if (show == "orfClone") {
		orfCloneFormInitData();
	}
    var moreInfoData = "";
    var plasmidForm = $("#plasmidForm");
	if(show == "plasmid"){
	    moreInfoData = "&"+$("#moreInfoForm").formSerialize();
	}
    var plasmidStr = addFormNameToSerializeData(null, plasmidForm.formSerialize()+moreInfoData, "itemId,upload");
    $("#plasmidForm_initData").attr("value", plasmidStr);
}
$(document).ready(function(){
    init(showType);
});
</script>
</body>
</html>
