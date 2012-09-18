<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_css_url}ui.all.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript">
	function updateStatus() {
		var url="ds_plate!updatePlateStatus.action";
		var data = "id="+$("#plateId").val()+"&status="+$("#status").val()+"&hasConcentrationItem="+$("#hasConcentrationItem").val();
		if($("#status").val()=="Concentration") {
			var concerntrationValues = "";
			var dsPlateItemsIds = "";
			$("#updateStatusTable").find("[name='concerntrationValue']").each(function(){
				var value = this.value;
				if(value=="") {
					value=" ";
				}
				if(concerntrationValues=="") {
					concerntrationValues = concerntrationValues +value;
				} else {
					concerntrationValues = concerntrationValues+","+value;
				}
				
			});
			
			$("#updateStatusTable").find("[name='id']").each(function(){
				if(dsPlateItemsIds=="") {
					dsPlateItemsIds = dsPlateItemsIds +this.value;
				} else {
					dsPlateItemsIds = dsPlateItemsIds+","+this.value;
				}
			});
			data =  data +"&dsPlateItemsIds="+dsPlateItemsIds+"&concerntrationValues="+concerntrationValues;
		}
		if($("#status").val()=="Sequencing") {
			data = data + "&controlValue="+$("#controlValue").val();
		}
		$.ajax({
			type: "POST",
			url:  url,
			data:data,
			dataType:"json",
			success: function(data){
				alert(data.message);
				window.location.reload();
			},
			error: function(msg){
				alert("failure");
			}
		});
	}
	
	function dataAnalysis() {
		window.location.href="ds_plate!dataAnalysis.action?id="+$("#plateId").val();
	}
</script>
</head>
<body class="content">
<div class="scm">
<div class="title_content">
<div class="title">
<s:if test="dsPlates.status=='Receiving'">
	View or Edit Receiving
</s:if>
<s:elseif test="dsPlates.status=='Concentration'">
View or Edit Concentration
</s:elseif>
<s:elseif test="dsPlates.status=='Add Primer'">
View or Edit Add Primer
</s:elseif>
<s:elseif test="dsPlates.status=='PCR'">
View or Edit PCR
</s:elseif>
<s:elseif test="dsPlates.status=='Purification'">
View or Edit Add Purification
</s:elseif>
<s:elseif test="dsPlates.status=='Sequencing'">
View or Edit Sequencing
</s:elseif>
<s:elseif test="dsPlates.status=='Finished'">
View or Edit Finished
</s:elseif>
</div>
</div>
<div class="input_box">
<table id="updateStatusTable"  width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="General_table"  style="margin:10px auto 0px auto;">
<tr>
    <td>Plate No:${dsPlates.plateNo}</td>
</tr>
<tr>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablebg" id="table_new1">
<tr>
		<th>&nbsp;</th>
        <th >01</th>
        <th >02</th>
        <th >03</th>
        <th >04</th>
        <th >05</th>
        <th >06</th>
        <th >07</th>
        <th >08</th>
        <th >09</th>
        <th >10</th>
        <th >11</th>
        <th >12</th>

</tr>
<s:iterator value="{'A','B','C','D','E','F','G','H'}" id='number' status="index">
<tr height="150">
<th>${number}</th>
<s:subset source="dsPlateItemsList"  start="#index.index*12" count="12">
<s:iterator id="woProcess">
<s:if test="id==null">
<td width="100">&nbsp;</td>
</s:if>
<s:else>
<td width="100">
	<a href="workorder_entry!edit.action?id=${workOrderNo}&operation_method=edit&plateId=${dsPlates.id}">${workOrderNo}</a><br>
	${orderNo}_${itemNo}<br>
	${orderDnaSequencing.code}<br>
	${orderDnaSequencing.sampleName}<br>
	${orderDnaSequencing.primerName}<br>
	<s:if test="orderDnaSequencing.flagConcMeas==1&&dsPlates.status=='Concentration'">
	<input type="text" name="concerntrationValue" value="${concerntrationValue}" class="NFText" size="8"/><br>
	<input type="hidden" name="id" value="${id}"/> 
	ng/ul
	</s:if>
</td>
</s:else>
</s:iterator>
</s:subset>
</tr> 
</s:iterator> 
</table>
</td>
</tr>
<tr>
    <td height="40" align="right"><a href="ds_plate!plateMap.action?id=${id}&plateNo=${dsPlates.plateNo}">Download Plate Map</a> 　　<a href="ds_plate!seqyencerFile.action?id=${id}&plateNo=${dsPlates.plateNo}">Download Sequencer File Names</a></td>
</tr>
<s:if test="dsPlates.status=='Sequencing'">
<tr>
	<td>Control Value
      <input name="controlValue" id="controlValue" value="${controlValue}" type="text" class="NFText" size="20" /></td>
</tr>
</s:if>
</table>
</div>
<div class="button_box">
<s:if test="dsPlates.status=='Receiving'">
	<input type="button" name="Submit124" value="Finish Receiving" class="search_input2" onclick="updateStatus();"/>
</s:if>
<s:elseif test="dsPlates.status=='Concentration'">
	<input type="button" name="Submit124" value="Confirm Concentration" class="search_input2" onclick="updateStatus();"/>
</s:elseif>
<s:elseif test="dsPlates.status=='Add Primer'">
	<input type="button" name="Submit124" value="Start PCR" class="search_input" onclick="updateStatus();"/>
</s:elseif>
<s:elseif test="dsPlates.status=='PCR'">
	<input type="button" name="Submit124" value="Finish PCR" class="search_input" onclick="updateStatus();"/>
</s:elseif>
<s:elseif test="dsPlates.status=='Purification'">
	<input type="button" name="Submit124" value="Finish Purification" class="search_input2" onclick="updateStatus();"/>
</s:elseif>
<s:elseif test="dsPlates.status=='Sequencing'">
	<input type="button" name="Submit124" value="Finish Sequencing" class="search_input2" onclick="updateStatus();"/>
</s:elseif>
<s:elseif test="dsPlates.status=='Finished'">
	<input type="button" name="Submit124" value="Data Analysis" class="search_input" onclick="dataAnalysis();"/>
</s:elseif>
  <input type="button" name="Submit" value="Back" class="search_input" onclick="window.location.href='ds_plate!plateSearch.action'"/>
  <input type="hidden" name="id" id="plateId" value="${dsPlates.id}"/>
  <input type="hidden" name="status" id="status" value="${dsPlates.status}"/>
  <input type="hidden" name="hasConcentrationItem" id ="hasConcentrationItem" value="${hasConcentrationItem}"/>

</div>
</div>
</body>
</html>