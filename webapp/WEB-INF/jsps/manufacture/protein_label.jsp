<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	$('#proteinForm').validate({
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
		 rules:{
			 'proteinLabelsDTO.proteinName': {required:true},
			 'proteinLabelsDTO.finalPrep': {required:true,number:true },
			 'proteinLabelsDTO.concentration': {required:true,number:true},
			 'proteinLabelsDTO.purity': {required:true,number:true},
			 'proteinLabelsDTO.plasmid3': { required:true,number:true },
			 'proteinLabelsDTO.plasmid4': { required:true,number:true },
			 'proteinLabelsDTO.plasmid5': { required:true}
		 },
		 messages: {
			 'proteinLabelsDTO.proteinName': { required:"Please enter the protein name." },
			 'proteinLabelsDTO.finalPrep': { required:"Please enter the finalPrep.",number:"Please enter a valid finalPrep." },
			 'proteinLabelsDTO.concentration': {required:"Please enter the concentration.",number:"Please enter a valid concentration." },
			 'proteinLabelsDTO.purity': { required:"Please enter the purity.",number:"Please enter a valid purity."  },
			 'proteinLabelsDTO.plasmid3': { required:"Please complete plasmid line input.",number:"Please enter a valid plasmid." },
			 'proteinLabelsDTO.plasmid4': { required:"Please complete plasmid line input.",number:"Please enter a valid plasmid." },
			 'proteinLabelsDTO.plasmid5': { required:"Please complete plasmid line input."}
		 },
		 errorPlacement: function(error, element) {
		 }
 });
});
function createLabel() {
    if($("#proteinForm").valid()==false) {
    	return;
    }
	var orderNo = $("#orderNo").val();
	  var formStr = $("#proteinForm").serialize();
      $('#create').attr("disabled", true);
      $.ajax({
			type: "POST",
			url: "workorder_proc!createProteinLabel.action",
			data: formStr,
			dataType: 'json',
			success: function(data, textStatus){
				if(hasException(data)){//有错误信息.
			           $('#create').attr("disabled", false);				
				}else{
					var str = "<input type='hidden' name='labelArray' id='hidden_"+orderNo+"' value='"+data.label+"'/>"+data.label;
					 parent.$("#printLabelFrame").contents().find("#"+orderNo).html(str);
				  $('#create').attr("disabled", false);
				  parent.$("#protein_labels_dlg").dialog("close");
				 // window.location = "workorder_proc!searchTask.action";//parent.location = parent.location;
				}
			},
			error: function(xhr, textStatus){
			   alert("failure");
			       $('#create').attr("disabled", false);
			}
	   });    
}
</script>



<style>
.add{
	margin: 0px;
	padding: 0px;
	font:11px/18px Arial, Helvetica, sans-serif;
	color:#06F;
	text-decoration:underline;
	}
.ab{
	margin: 0px;
	padding: 0px;
	font:11px/18px Arial, Helvetica, sans-serif;
	color:#F00;
	
}
</style>
</head>

<body>
<s:form id="proteinForm" method="post">
<s:hidden name="orderNo" id="orderNo"></s:hidden>
<table  border="0" cellpadding="0" cellspacing="0" class="General_table" width="100%">
          <tr>
            <th>Order ID</th>
            <td><input name="proteinLabelsDTO.orderNo" value="${proteinLabelsDTO.orderNo}" type="text"  size="20"  class="NFText" readonly="readonly"/><s:hidden name="proteinLabelsDTO.orderNo"/></td>
            <th>Item ID</th>
            <td><input name="proteinLabelsDTO.itemNo" value="${proteinLabelsDTO.itemNo}" type="text"  size="20"  class="NFText" readonly="readonly"/><s:hidden name="proteinLabelsDTO.itemNo"/></td>
            </tr>
          <tr>
            <th>Lot No</th>
            <td ><input name="proteinLabelsDTO.lotNo" value="${proteinLabelsDTO.lotNo}" type="text"  size="20"  class="NFText" readonly="readonly"/><s:hidden name="proteinLabelsDTO.lotNo"/></td>
            <th>Protein Name</th>
            <td valign="top"><input name="proteinLabelsDTO.proteinName" value="${proteinLabelsDTO.proteinName}" type="text" id="proteinName" size="38"  class="NFText"/></td>
            </tr>
          <tr>
             <th>Final Prep<span class="important">*</span></th>
            <td><input name="proteinLabelsDTO.finalPrep" type="text" value="${proteinLabelsDTO.finalPrep}"  class="NFText" id="finalPrep" size="2"/>
              ml/tube (ml/bottle)
            <th>Protein Concentration<span class="important">*</span></th>
            <td><input name="proteinLabelsDTO.lyophilizedFlg" type="radio" <s:if test="proteinLabelsDTO.lyophilizedFlg==false">checked</s:if>/>
              non-lyophilized
              <input type="radio" name="proteinLabelsDTO.lyophilizedFlg" <s:if test="proteinLabelsDTO.lyophilizedFlg==true">checked</s:if>/>
              lyophilized
              <input name="proteinLabelsDTO.concentration" type="text" value="${proteinLabelsDTO.concentration}"  class="NFText" id="finalPrep" size="2"/>
              mg/ml
              </td>
            </tr>
            
          <tr>
            <th >Purity<span class="important">*</span></th>
            <td><input name="proteinLabelsDTO.purity" type="text"  value="${proteinLabelsDTO.purity}" class="NFText" id="purity" size="5"/>
              %</td>
            <th>Fused With<span class="important">*</span></th>
            <td>
            	<select name="proteinLabelsDTO.fusedWith">
					<option value="tag free">tag free</option>
					<option value="N-His tag">N-His tag</option>
					<option value="C-His tag">C-His tag</option>
					<option value="His-GST tag">His-GST tag</option>
					<option value="His-Trx tag">His-Trx tag</option>
					<option value="His-TF tag">His-TF tag</option>
					<option value="GST-tag">GST-tag</option>

					<option value="Nus tag">Nus tag</option>
					<option value="MBP tag">MBP tag</option>
					<option value="Other">Other</option>

            	</select>
            </td>
          </tr>
          
          <tr>
            <th>Plasmid</th>
            <td colspan="3">
            	<select name="proteinLabelsDTO.plasmid1">
            		<option value="non-lyophilized" selected="selected">non-lyophilized</option>
					<option value="lyophilized">lyophilized</option>
            	</select>
            	<select name="proteinLabelsDTO.plasmid2">
					<option value="Expression Vector C1">pCold TF</option>
					<option value="Expression Vector C2">pCold I</option>
					<option value="Expression Vector C3">pCold II</option>
					<option value="Expression Vector C4">pCold III</option>
					<option value="Expression Vector C5">pCold IV</option>

					<option value="Expression Vector E1">pET15b</option>
					<option value="Expression Vector E2">pET28a</option>
					<option value="Expression Vector E3">pET30a</option>
					<option value="Expression Vector E4">pET32a</option>
					<option value="Expression Vector E5">pET39b</option>
					<option value="Expression Vector E6">pET43.1a</option>

					<option value="Expression Vector E7">pET16</option>
					<option value="Expression Vector E8">pET22b</option>
					<option value="Expression Vector E9">pET21a</option>
					<option value="Expression Vector E10">pET41a</option>
					<option value="Expression Vector X1">pGEX-4T-1</option>
					<option value="Expression Vector X2">pGEX-6P-1</option>

					<option value="pGS21a">pGS21a</option>
					<option value="Expression Vector M1">pMAL-p2x</option>
					<option value="Expression Vector M2">pMAL-c4x</option>
					<option value="Expression Vector S1">pSUMO-M</option>
					<option value="Expression Vector F1">pFastBac1</option>
					<option value="Expression Vector F2">pFastBacHTA</option>

					<option value="Expression Vector F3">pFastBacHTB</option>
					<option value="Expression Vector F4">pFastBacHTC</option>
					<option value="Expression Vector F5">pFastBac-DUAL</option>
					<option value="Expression Vector F6">pFGP67-1</option>
					<option value="Expression Vector F7">pFGP67-2</option>
					<option value="Expression Vector Y-1">pPICZaA</option>

					<option value="Expression Vector Y-2">pPICZA</option>
					<option value="Expression Vector Y-3">pPIC9K</option>
					<option value="Expression Vector Y-4">pPIC3.5K</option>
					<option value="Expression Vector Y-5">pAO815</option>
					<option value="Expression Vector Y-6">pYES2</option>
					<option value="pUC57">pUC57</option>

					<option value="Other">Other</option>
            		
            	</select>
              <input name="proteinLabelsDTO.plasmid3" value="${proteinLabelsDTO.plasmid3}" type="text"  class="NFText" id="plasmid3" size="5"/>
              μl 
  			<input name="proteinLabelsDTO.plasmid4"  value="${proteinLabelsDTO.plasmid4}" type="text"  class="NFText" id="plasmid4" size="5"/>
              ng/μl
  			<input name="proteinLabelsDTO.plasmid5"  value="${proteinLabelsDTO.plasmid5}" type="text"  class="NFText" id="plasmid5" size="5"/>
  			${proteinLabelsDTO.plasmid6}
              <s:hidden name="proteinLabelsDTO.plasmid6"/></td>
          </tr>
          <tr>
            <td colspan="4"><div  class="botton_box">
              <br />
               <input name="Submit22" type="button" id="create" class="style_botton" value="Create" onclick="createLabel();"/>
              <input type="button" name="Submit622" value="Cancel"  class="style_botton" onclick="parent.$('#protein_labels_dlg').dialog('close');"/>
            <br />
           </div></td>
          </tr>
</table>
</s:form>
</body>
</html>
