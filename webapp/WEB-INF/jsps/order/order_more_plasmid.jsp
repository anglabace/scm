<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript" type="text/javascript" src="${global_js_url}ajaxupload.js"></script>
<script>
$(function(){
	var antibioticResistance = $("#antibioticResistance").val();
	$("#resistance").find("option[value='"+antibioticResistance+"']").attr("selected", true);
	if($("#resistance").val()=='Other') {
		$("#txt_01").show();
		$("#txt_01").val(antibioticResistance);
	}
	var prepWeightStr = $("#prepWeightStr").val();
	if (prepWeightStr != undefined && prepWeightStr != "") {
		$("#prepWeightSel").find("option[value='"+prepWeightStr+"']").attr("selected", true);
		if ($("#prepWeightSel").val() == prepWeightStr) {
			$("#txt_04").hide();
			$("#prepUom").hide();
		} else {
			$("#prepWeightSel").find("option[value='0']").attr("selected", true);
			$("#txt_04").show();
			$("#txt_04").attr("value",prepWeightStr.split(" ")[0]);
			$("#prepUom").show();
		}
	} else {
		$("#prepWeightSel").find("option[value='100 ug']").attr("selected", true);
	}
	if($("#prepWeightSel").val()=='0') {
		$("#txt_04").show();
		$("#txt_04").attr("value",prepWeightStr.split(" ")[0]);
		$("#prepUom").show();
	} 
	if($("#restrictionAnalysis").val()!='') {
		var restrictionAnalysisArray = $("#restrictionAnalysis").val().split(",");
		for(var i=0;i<restrictionAnalysisArray.length;i++) {
			$('#right').get(0).options[i]= new Option(restrictionAnalysisArray[i],restrictionAnalysisArray[i]);
			
		}
		$('#right').get(0).selectedIndex = i-1;
	}
	
	$('#Quality_grade_help_dlg').dialog({
		autoOpen: false,
		height: 400,
		width: 800,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
		   $("#resistance").change(function(){
								 if($("#resistance").val()=="Other")
								 {
								    $("#txt_01").show();
								 }
								 else
								 {
									$("#txt_01").hide(); 
								 }
								});
		     $("#prepWeightSel").change(function(){
								 if($("#prepWeightSel").val()=="0" || $("#prepWeightSel").val()=="0.0")
								 {
								    $("#txt_04").show();
								    $("#txt_04").attr("disabled",false);
									$("#prepUom").show();
								 }
								 else
								 {
									$("#txt_04").hide(); 
									 $("#txt_04").attr("disabled",true);
										$("#prepUom").hide();
								 }
								});
		     $("#left").change(function(){
		    	   var fromObj = document.getElementById("left");
		    		for(var   i=fromObj.options.length-1;i> -1;i--) 
		    		{ 
		    			if(fromObj.options[i].selected) 
		    			{ 
		    				if(fromObj.options[i].value=="Other") {
		    					$("#txt_Other_restrictionAnalysis").show();
		    					return;
		    				}
		    			} 
		    		}
					$("#txt_Other_restrictionAnalysis").val("");
					$("#txt_Other_restrictionAnalysis").hide();
		     });
		   
		   $("#fragment").change(function(){							  
									$("#fragment1").hide();
									$("#fragment1").attr("disabled",true);
									$("#fragment2").hide();
									$("#fragment2").attr("disabled",true);
									$("#fragment3").hide();
									$("#fragment3").attr("disabled",true);
									$("#fragment4").hide();
									$("#fragment4").attr("disabled",true);
									if ($("#fragment").val()=='e.g.1')
									{
										$("#fragment1").show();
										$("#fragment1").attr("disabled",false);
									}
									else if($("#fragment").val()=='e.g.2')
								    {
										$("#fragment1").show();
										$("#fragment1").attr("disabled",false);
										$("#fragment2").show();
										$("#fragment2").attr("disabled",false);
									}
									else if($("#fragment").val()=='e.g.3')
								    {
										$("#fragment1").show();
										$("#fragment1").attr("disabled",false);
										$("#fragment2").show();
										$("#fragment2").attr("disabled",false);
										$("#fragment3").show();
										$("#fragment3").attr("disabled",false);
									}
									else if($("#fragment").val()=='e.g.4')
								    {
										$("#fragment1").show();
										$("#fragment1").attr("disabled",false);
										$("#fragment2").show();
										$("#fragment2").attr("disabled",false);
										$("#fragment3").show();
										$("#fragment3").attr("disabled",false);
										$("#fragment4").show();
										$("#fragment4").attr("disabled",false);
									}
								});
		   $("#fragment").change();
		   });
function quality_grade_help() {
	$("#Quality_grade_help_dlg").dialog("option","open",function(){
		var url = "order/order_more!qualityGradeHelp.action";
		var htmlStr = '<iframe id="newPurcharseOrderIframe" src="'+url+'" height="320" width="750" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		$('#Quality_grade_help_dlg').html(htmlStr);
	});
	$("#Quality_grade_help_dlg").dialog("open");
}

function   moveUp()     
{   
	var   theObj=document.getElementById("right"); 
	for(var   i=1;i <theObj.length;i++) 
	{ 
		if(   theObj.options[i].selected   &&   !theObj.options[i-1].selected   ) 
		{ 
			var tmp1 = new Option(theObj.options[i].text,theObj.options[i].value);
			var tmp2 = new Option(theObj.options[i-1].text,theObj.options[i-1].value);
			theObj.options[i]=tmp2; 
			theObj.options[i-1] = tmp1;
			theObj.options[i-1].selected=true;
		} 
	} 
}     
    
function   moveDown()     
{   
	var   theObj=document.getElementById("right"); 
	for(var   i=theObj.options.length-2;i> -1;i--) 
	{ 
		if(   theObj.options[i].selected   &&   !theObj.options[i+1].selected   ) 
		{ 
			var tmp1 = new Option(theObj.options[i].text,theObj.options[i].value);
			var tmp2 = new Option(theObj.options[i+1].text,theObj.options[i+1].value);
			theObj.options[i]=tmp2; 
			theObj.options[i+1] = tmp1;
			theObj.options[i+1].selected=true;
		} 
	} 
}     
    
    
function   moveLeftOrRight(left,right)     
{ 
	var fromObj = document.getElementById(left);
	var toObj = document.getElementById(right);
	var   lengthOfToObj=toObj.options.length; 
	for(var   i=fromObj.options.length-1;i> -1;i--) 
	{ 
		if(fromObj.options[i].selected) 
		{
			if(fromObj.options[i].value=="Other") {
				toObj.options[lengthOfToObj] = new Option($("#txt_Other_restrictionAnalysis").val(),$("#txt_Other_restrictionAnalysis").val());
				$("#txt_Other_restrictionAnalysis").val("");
				$("#txt_Other_restrictionAnalysis").hide();
			} else {
				toObj.options[lengthOfToObj]=new Option(fromObj.options[i].text,fromObj.options[i].value);
				fromObj.options[i]=null;
			}
			lengthOfToObj++;
		} 
	} 
}   
</script>
<form id="plasmidForm">
<input type="hidden" name="itemId" />
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table" >
         <tr class=".sub_disabled">
           <th width="159"><span class="important">*</span> Plasmid name</th>
           <td colspan="5"><s:textfield name="plasmidPreparation.plasmidName"  Class="NFText" size="20" /></td>
           <th width="136"><span class="important">*</span>Plasmid Size</th>
           <td width="201"><s:textfield name="plasmidPreparation.plasmidSize" Class="NFText" size="20" /></td>
         </tr>
         <tr class=".sub_disabled">
           <th><span class="important">*</span>Starting Material</th>
           <td colspan="5">
           <s:if test='plasmidPreparation.startingMaterical==null||plasmidPreparation.startingMaterical==""||plasmidPreparation.startingMaterical=="Prepare at GenScript"'>
           	<input type="radio" name="plasmidPreparation.startingMaterical" id="copy_num_l2" value="Prepare at GenScript" checked="checked"/>
           </s:if>
           <s:else>
           	<input type="radio" name="plasmidPreparation.startingMaterical" id="copy_num_l2" value="Prepare at GenScript"/>
           </s:else>
           Prepare at GenScript (Gene Synthesis and Subcloning)
          </td>
           <th><span class="important">*</span>Copy Number</th>
           <td>
           <s:if test="plasmidPreparation.copyNumber==null||plasmidPreparation.copyNumber==''||plasmidPreparation.copyNumber=='High'">
           	<input name="plasmidPreparation.copyNumber" id="copy_num_h" type="radio" value="High" checked="checked"/>High
           	<input type="radio" name="plasmidPreparation.copyNumber" id="copy_num_l" value="Low" />Low
           </s:if>
           <s:else>
           	<input name="plasmidPreparation.copyNumber" id="copy_num_h" type="radio" value="High"/>High
           	<input type="radio" name="plasmidPreparation.copyNumber" id="copy_num_l" value="Low"  checked="checked"/>Low
           </s:else>
           </td>
         </tr>
         <tr class=".sub_disabled">
           <th><span style="color:#06C; padding-bottom:1px;  cursor:pointer;margin-top:50px;"
            title="Sample Submission Requirement:
            Plasmid DNA template of >1 μg with a concentration of > 100 ng/μl
            Dissolved in water, TE buffer or dry in 1.5/0.5 ml centrifuge tube
            Bacteria stock on paper is not acceptable, for plasmid DNA, either in solution or on paper is acceptable
 "> Help</span></th>
           <td colspan="5">
           <s:if test="plasmidPreparation.startingMaterical=='Plasmid DNA template'">
           	<input type="radio" name="plasmidPreparation.startingMaterical" id="copy_num_l3" value="Plasmid DNA template" checked="checked"/>
           </s:if>
           <s:else>
           	<input type="radio" name="plasmidPreparation.startingMaterical" id="copy_num_l3" value="Plasmid DNA template"/>
           </s:else>
            Plasmid DNA template
           </td>
           <th>Typical yield</th>
           <td><s:textfield name="plasmidPreparation.typicalYield" Class="NFText" id="size3" size="20" /><span>e.g.**mg/L</span></td>
         </tr>
         <tr class=".sub_disabled">
         	<th valign="top">Fragment</th>
           <td colspan="5">
            <s:if test="dropDownMap['PLASMID_FRAGMENT']!=null&&dropDownMap['PLASMID_FRAGMENT'].size>0">
           	<s:select id="fragment"
           			name="fragmentSize"
           			list="dropDownMap['PLASMID_FRAGMENT']"  
           			listKey="value" 
           			listValue="text" 
           			cssStyle="width:157px;">
           	</s:select>
           </s:if>
           <s:else>
           	<select  name="fragmentSize" id="fragment">
           		<option value="e.g.0">e.g.0</option>
           	</select>
           </s:else>
           <div id="txt_03"><div id="txt_02">
           		<s:textfield name="plasmidPreparation.fragmentArray" id="fragment1" cssStyle="display:none;" disabled="true"/>
           		<s:textfield name="plasmidPreparation.fragmentArray" id="fragment2" cssStyle="display:none;" disabled="true"/>
           		<s:textfield name="plasmidPreparation.fragmentArray" id="fragment3" cssStyle="display:none;" disabled="true"/>
           		<s:textfield name="plasmidPreparation.fragmentArray" id="fragment4" cssStyle="display:none;" disabled="true"/>
           </div></div></td>
           <th><span class="important">*</span>Antibiotic Resistance</th>
           <td colspan="5">
           <s:if test="dropDownMap['PLASMID_ANTIBIOTIC_RESISTANCE']!=null&&dropDownMap['PLASMID_ANTIBIOTIC_RESISTANCE'].size>0">
           	<s:select id="resistance"
           			list="dropDownMap['PLASMID_ANTIBIOTIC_RESISTANCE']"  
           			listKey="value" 
           			listValue="text" 
           			cssStyle="width:157px;"
           			headerKey="Other"
           			headerValue="Other">
           	</s:select>
           </s:if>
           <s:else>
           	<select id="resistance" >
           		<option value="Other">Other</option>
           	</select>
           </s:else>
           <s:hidden name="plasmidPreparation.antibioticResistance" id="antibioticResistance"></s:hidden>
           <input  id="txt_01"   type="text" class="NFText" size="20" style="display:none;"/></td>
          
         </tr>
         <tr class=".sub_disabled">
           <th rowspan="3" valign="top"><span class="important">*</span>Restriction Analysis: QC Enzyme</th>
           <td colspan="2" rowspan="4">
           <s:if test="dropDownMap['PLASMID_RESTRICTION_ANALYSIS']!=null&&dropDownMap['PLASMID_RESTRICTION_ANALYSIS'].size>0">
           <select id="left" multiple style="height:100px">
			<s:iterator id="option1" value="dropDownMap['PLASMID_RESTRICTION_ANALYSIS']" status="index">
			<s:if test="#index.index==0">
				<option value="${option1.value}" selected="selected">${option1.text}</option>
			</s:if>
			<s:else>
				<option value="${option1.value}">${option1.text}</option>
			</s:else>
           	</s:iterator>
           		<option value="Other">Other</option>
           	</select>
           </s:if>
           <s:else>
           	<select id="left" multiple style="height:100px">
           		<option value="Other" selected="selected">Other</option>
           	</select>
           </s:else>
        </td>
           <td width="27" rowspan="4"><img src="images/bt_1.jpg" width="23" height="17" onclick="moveLeftOrRight('left','right')"/><br/>
           <img src="images/bt_2.jpg" width="23" height="17" onclick="moveLeftOrRight('right','left')"/></td>
           <td width="171" rowspan="4">
           	<select id="right" 
           			style="width:157px;height:100px"
           			multiple>
           	</select>
           	<s:hidden name="plasmidPreparation.restrictionAnalysis" id="restrictionAnalysis"/>
           	<input  id="txt_Other_restrictionAnalysis"   type="text" class="NFText" size="20" style="display:none;"/>
           </td>
           <td width="44" rowspan="4"><img src="images/bt_3.jpg" width="19" height="17" onclick="moveUp()"/><br/>
           <img src="images/bt_4.jpg" width="19" height="17" onclick="moveDown()"/></td>
           <th>&nbsp;</th>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th>&nbsp;</th>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th>&nbsp;</th>
           <td>&nbsp;</td>
         </tr>
         <tr class=".sub_disabled">
           <th valign="top"><span style="color:#06C; padding-bottom:1px;  cursor:pointer;margin-top:50px;"
            title="1-2 unique sites for Quality Control purpose
 "> Help</span></th>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
            <th>Storage  condition</th>
           <td colspan="5">
            <s:if test="dropDownMap['PLASMID_STORAGE_CONDITION']!=null&&dropDownMap['PLASMID_STORAGE_CONDITION'].size>0">
           	<s:select name="plasmidPreparation.storageCondition"
           			list="dropDownMap['PLASMID_STORAGE_CONDITION']"  
           			listKey="value" 
           			listValue="text" 
           			cssStyle="width:157px;">
           	</s:select>
           </s:if>
           <s:else>
           	<select  name="plasmidPreparation.storageCondition">
           		<option value="">please select...</option>
           	</select>
           </s:else>
           </td>
           <th>Desired buffer</th>
           <td>
            <s:if test="dropDownMap['PLASMID_DESIRED_BUFFER']!=null&&dropDownMap['PLASMID_DESIRED_BUFFER'].size>0">
           	<s:select 
           			name="plasmidPreparation.desiredBuffer"
           			list="dropDownMap['PLASMID_DESIRED_BUFFER']"  
           			listKey="value" 
           			listValue="text" 
           			cssStyle="width:157px;">
           	</s:select>
           </s:if>
           <s:else>
           	<select  name="plasmidPreparation.desiredBuffer">
           		<option value="">please select...</option>
           	</select>
           </s:else>
           <span style="color:#06C; padding-bottom:1px;  cursor:pointer;margin-top:50px;"
            title=" Standard deliver: Lyophilized for quantity ≤ 100 μg, liquid for &gt; 100 μg.

 "> Help</span></td>
         </tr>
         <tr class=".sub_disabled">
           <th rowspan="2" valign="top">Sequence </th>
           <td colspan="5" rowspan="2"><s:textarea name="plasmidPreparation.sequence" id="textarea3" Class="content_textarea2"></s:textarea></td>
           <th>&nbsp;</th>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <td>&nbsp;</td>
           <td>&nbsp;
           </td>
         </tr>
         
         <tr class=".sub_disabled">
         	<th>Map</th>
           <td colspan="5">
           	 <label><input type="file" name="upload" id="file"/></label>
             <input name="plasmidUploadBtn" id="plasmidUploadBtn" type="button" class="style_botton" value="Upload" />
           </td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr class=".sub_disabled">
         	<th>&nbsp;</th>
         	<td colspan="7">
	         	<table id="fileListTable" name="fileListTable"></table>
           	</td>
         </tr>
         
         <tr>
           <th valign="top"><span class="important">*</span>Quantity</th>
           <td colspan="5">
	           <select id="prepWeightSel" style="width:100px;">
	           		<option value="100 ug">100 ug</option>
	           		<option value="200 ug">200 ug</option>
	           		<option value="500 ug">500 ug</option>
	           		<option value="1 mg">1 mg</option>
	           		<option value="2 mg">2 mg</option>
	           		<option value="10 mg">10 mg</option>
	           		<option value="20 mg">20 mg</option>
	           		<option value="50 mg">50 mg</option>
	           		<option value="100 mg">100 mg</option>
	           		<option value="200 mg">200 mg</option>
	           		<option value="500 mg">500 mg</option>
	           		<option value="1000 mg">1000 mg</option>
	           		<option value="1500 mg">1500 mg</option>
	           		<option value="2000 mg">2000 mg</option>
	           		<option value="0">Other</option>
	           </select>
	           <s:hidden name="plasmidPreparation.prepWeightStr" id="prepWeightStr"></s:hidden>
           <input  id="txt_04" type="text" class="NFText" size="20" style="display:none;"/>
           <span id="prepUom" style="display:none;">mg</span>
           <span style="color:#06C; padding-bottom:1px;  cursor:pointer;margin-top:50px;"
            title="100 ug sufficient for 10 transient transformation"> Help</span></td>
            <th>Additional analysis </th>
           <td>
	           	<s:select name="plasmidPreparation.additionalAnalysis" list="dropDownMap['PLASMID_ADTL_SERVICE']" listKey="value" listValue="value" headerKey="" headerValue=""></s:select>
           	</td>
         </tr>
         <tr>
           <th rowspan="3" valign="top"><span class="important">*</span>Quality grade </th>
           <td colspan="5" rowspan="3">&nbsp;&nbsp;&nbsp;<a style="color:#06C; padding-bottom:1px;  cursor:pointer;margin-top:50px;"  onclick="quality_grade_help()"/>Help</a><br/>
           <s:if test="plasmidPreparation == null || plasmidPreparation.qualityGrade==null||plasmidPreparation.qualityGrade==''||plasmidPreparation.qualityGrade=='Research Grade'">
           	<input type="radio" name="plasmidPreparation.qualityGrade" value="Research Grade" checked="checked"/>
           </s:if>
           <s:else>
           	<input type="radio" name="plasmidPreparation.qualityGrade" value="Research Grade"/>
           </s:else> 
             Research Grade(Predominantly supercoiled)<br/>
            <s:if test="'SC Grade' == plasmidPreparation.qualityGrade">
           	<input type="radio" name="plasmidPreparation.qualityGrade" value="SC Grade" checked="checked"/>
           </s:if>
           <s:else>
           	<input type="radio" name="plasmidPreparation.qualityGrade" value="SC Grade"/>
           </s:else> 
            SC Grade (≥95% Supercoilded, ≤0.03 EU/ug Endotoxin) <br/>
             <s:if test="'Advanced SC Grade' == plasmidPreparation.qualityGrade">
           	<input type="radio" name="plasmidPreparation.qualityGrade" value="Advanced SC Grade" checked="checked"/>
           </s:if>
           <s:else>
           	<input type="radio" name="plasmidPreparation.qualityGrade" value="Advanced SC Grade"/>
           </s:else> 
                	Advanced SC Grade(≥95% Supercoilded, ≤0.005 EU/ug Endotoxin) <br />
            </td>
           <th>
           <s:checkbox id="checkbox2" name="plasmidPreparation.bioBurdenAssay"
			value='plasmidPreparation.bioBurdenAssay == "Y"' fieldValue="Y"></s:checkbox>
           		</th>
           <td>
             Bio-burden assay (additional charge) <span style="color:#06C; padding-bottom:1px;  cursor:pointer;margin-top:50px;"
            title=" Only test for E.coli"> Help</span>
          </td>
         </tr>
         <tr>
           <th><s:checkbox id="checkbox3" name="plasmidPreparation.customSequencing"
			value='plasmidPreparation.customSequencing == "Y"' fieldValue="Y"></s:checkbox>
           </th>
           <td>
             <label> Custom  Sequencing (additional charge)</label>
           </td>
         </tr>
         <tr>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
        
         <tr>
           <th valign="top">Required concentration</th>
           <td colspan="5"><s:textfield name="plasmidPreparation.requiredConcentration" Class="NFText" size="20" />
           <span style="color:#06C; padding-bottom:1px;  cursor:pointer;margin-top:50px;" title="Define your preferred concentration of plasmid DNA here. Our typical concentration is 0.5 – 5 mg/mL.
 ">Help</span></td>
          <th>Aliquot samples</th>
           <td><s:textfield name="plasmidPreparation.aliquotSamples" id="size5" Class="NFText" size="20" /></td>
         </tr>
         <tr class=".sub_disabled">
           <th valign="top">Comment</th>
           <td colspan="5"><s:textarea name="plasmidPreparation.comments" id="textarea2" Class="content_textarea2"></s:textarea></td>
           <th>&nbsp;</th>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th valign="top">&nbsp;</th>
           <td colspan="5">&nbsp;</td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
       </table>
       <div id="Quality_grade_help_dlg" title="Quality grade help"></div>
</form>