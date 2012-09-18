<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>WorkCenter Search Result</title>
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			a:{
			   text-decoration:underline;
			}
		</style>
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
			language="javascript"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
			<script language="JavaScript" type="text/javascript">
			var excelUrl = "";
			function downloadExcel() {
	         	var sequence = $("#sequence").val();
            	var job_name = $("#job_name").val();
            	var tm_min = $("#tm_min").val();
            	var tm_max = $("#tm_max").val();
            	var ov_min = $("#ov_min").val();
            	var ol_min = $("#ol_min").val();
            	var ol_max = $("#ol_max").val();
            	var left_primer = $("#left_primer").val();
            	var right_primer = $("#right_primer").val();
				$("#updateStatusTable2").html("");
				$("#prmo_indicator").html("<img src='images/indicator.gif' />");
   
            	if(sequence=="") {
            		alert("Please enter sequence.");
            		return;
            	}
            	$.ajax({
            		type: "POST",
					url: "workorder_operation!downLoadExcel.action",
					data: "sequence=" + sequence+"&job_name="+job_name
						  +"&tm_min="+tm_min+"&tm_max="+tm_max+"&ov_min="
						  +ov_min+"&ol_min="+ol_min+"&ol_max="+ol_max+"&left_primer="+left_primer+"&right_primer="+right_primer,
					dataType:'json',
					success: function(data, textStatus){
						if(data.url==null) {
							alert("Create Excel File Error,please check your enter sequence.");
							parent.redirectTo();
						} else {
							excelUrl = data.url;
							var html = "<tr><th>Backbone</th><td>"+
							"<select name=\"batchBackbone\" id=\"batchBackbone\" style=\"width:157px\" onchange=\"batchBackboneChange()\">"+
							"<option value=\"DNA\">DNA</option>"+
							"<option value=\"Phosphorothioated DNA\">Phosphorothioated DNA</option>"+
							"<option value=\"RNA\">RNA</option>"+
							"<option value=\"Phosphorothioated RNA\">Phosphorothioated RNA</option>"+
							"<option value=\"2@-OMe-RNA\">2'-OMe-RNA</option>"+
							"<option value=\"Phosphorothioated 2@-OMe-RNA\">Phosphorothioated 2'-OMe-RNA</option>"+
							"</select></td></tr>"+
							"<tr><th>Synthesis scales</th><td>"+
							"<select name=\"batchSynthesisScales\" id=\"batchSynthesisScales\" style=\"width:157px\">"+
							"<option value=''></option>"+
							"<option value=\"50 nmol\">50 nmol</option>"+
							"<option value=\"100 nmol\">100 nmol</option>"+
							"<option value=\"200 nmol\">200 nmol</option>"+
							"<option value=\"1 umol\">1 umol</option>"+
							"<option value=\"5 umol\">5 umol</option>"+
							"<option value=\"10 umol\">10 umol</option>"+
							"</select></td></tr>"+
							"<tr><th>nMol/Vial</th><td>"+
							"<input name=\"aliquotingSize\" id=\"aliquotingSize\" type=\"text\" class=\"NFText\" size=\"25\" value=\"5 nMol\"/>"+
							"</input></td></tr>"+
							"<tr><th>Purification</th><td>"+
							"<div id=\"batchRnaPurificationSel\" style=\"display: block;\">"+
							"<select name=\"batchRnaPurification\" id=\"batchRnaPurification\" style=\"width:157px;\">"+
							"<option value=\"Desalt\">Desalt</option>"+
							"<option value=\"RNase Free HPLC\">RNase Free HPLC</option>"+
							"</select></div><div id=\"batchOtherPurificationSel\" style=\"display: none;\">"+
							"<select name=\"batchOtherPurification\" id=\"batchOtherPurification\" style=\"width:157px\">"+
							"<option value=\"Desalt\">Desalt</option>"+
							"<option value=\"RPC\">RPC</option>"+
							"<option value=\"PAGE\">PAGE</option>"+
							"<option value=\"HPLC\">HPLC</option>"+
							"</select></div></td></tr>";
							html = html+"<tr><td><a href='"+data.url+"' target='_blank' style='text-decoration:underline;'>DownLoadExcelFile</a>"+
										"</td><td><input type='button' class='style_botton3' value='Save & Submit to Oligo System' onclick='saveItem();'/></td></tr>";
							$("#updateStatusTable2").html(html);
							 
							$("#prmo_indicator").html("");
						}
						
					},
					error: function(xhr, textStatus){
						if(xhr.message!=null&&xhr.message!="") {
							alert(xhr.message);
						} else {
							alert("failure");
						}
					   return;
					}
            	});
            }
			function turnTo() {
				parent.redirectTo();
			}
			
			function saveItem() {
				var batchBackbone = $("#batchBackbone").val();
				var batchSynthesisScales = $("#batchSynthesisScales").val();
				var aliquotingSize = $("#aliquotingSize").val();
				var batchPurification = null;
				if (batchBackbone == null || batchBackbone == "" || batchBackbone == undefined) {
					alert("Please select a Backbone.");
					return;
				}
				
				if (batchBackbone.indexOf("RNA") >= 0) {
					batchPurification = $("#batchRnaPurification").val();
				} else {
					batchPurification = $("#batchOtherPurification").val();
				}
				if (batchPurification == null || batchPurification == "" || batchPurification == undefined) {
					alert("Please select a Purification.");
					return;
				}
				var workOrderSessNo = $("#workOrderSessNo").val();
				var workCenterId = $("#workCenterId").val();
				$("#updateStatusTable2").html("");
				$("#prmo_indicator").html("<img src='images/indicator.gif' />");
				$.ajax({
					type:"POST",
					url:"order!edit.action",
					data:"workOrderSessNo="+workOrderSessNo+"&batchType=batchOligo&workCenterId="+workCenterId,
					dataType:"json",
					success:function(data,textStatus) {
						if(data.sessOrderNo!=null) {
							saveBatchOligoForInternal(batchBackbone,batchSynthesisScales,batchPurification,aliquotingSize,data.custNo,data.sessOrderNo,excelUrl,workOrderSessNo);
						} else {
							alert("Save order failed.");
							parent.redirectTo();
						}
					},
					error:function(xhr, textStatus) {
						alert("Error.");
						parent.redirectTo();
					}
				});
			}
			function batchBackboneChange() {
				var batchBackbone = $("#batchBackbone").val();
				if (batchBackbone != null && batchBackbone.indexOf("RNA") >= 0) {
					$("#batchRnaPurificationSel").show();
					$("#batchOtherPurificationSel").hide();
				} else {
					$("#batchOtherPurificationSel").show();
					$("#batchRnaPurificationSel").hide();
				}
			}
			
			// add by lizhang for 
			function saveBatchOligoForInternal(batchBackbone,batchSynthesisScales,batchPurification,aliquotingSize,custNo,sessOrderNo,excelUrl,workOrderSessNo) {
				var refType = "";
			    //add by zhanghuibin
			    if(!createQuoteItemForInternal(batchBackbone,custNo,sessOrderNo)){
			    	parent.redirectTo();
			    }
			    $.ajax({
			    	type:"POST",
					url:"order_more!uploadFile.action",
					data:"itemId="+itemIds+ "&serviceName=oligo&refType="+refType+"&batchBackbone="+batchBackbone + "&batchSynthesisScales="+ batchSynthesisScales+"&batchPurification=" + batchPurification+"&batchAliquotingSize="+aliquotingSize+"&sessOrderNo="+sessOrderNo+"&excelUrl="+excelUrl+"&batchCustNo="+custNo,
					dataType:"json",
					success:function(data) {
						if (data!=undefined&&data.rtnMessage != undefined) {
							alert(data.rtnMessage);
							parent.redirectTo();
						}
						parent.window.location.href="order!edit.action?sessOrderNo="+sessOrderNo+"&workOrderSessNo="+workOrderSessNo+"&batchType=batchOligo";
						
					},
					error:function(xhr, textStatus) {
						alert("Error.");
						parent.redirectTo();
					}
			    	
			    });
			}
			
			//add by lizhang for internal
			function createQuoteItemForInternal(batchBackbone,custNo,sessOrderNo) {
				var flag = true;
			    var catalogNo = getCatalogNo(batchBackbone);
			    //创建sessionQuoteNo 以及 itemId
			    $.ajax({
					url:"order_item!saveBatchSessionItem.action",
			        type:"post",
			        dataType:"json",
					data:"sessOrderNo="+sessOrderNo+"&custNo=" + custNo + "&itemNum=1&catalogNo=" + catalogNo,
					success:function(data){
						itemIds = data.itemNOs;
					},
					error: function(){
						alert("System error! Please contact system administrator for help.");
			            flag=false;
					},
					async:false
				});
			    return flag;
			}
			
			//add by zhanghuibin
			function getCatalogNo(batchBackbone){
			    if("DNA" == batchBackbone){
			        return "SC1516";
			    }else if("Phosphorothioated DNA" == batchBackbone){
			        return "SC1517";
			    }else if("RNA" == batchBackbone){
			        return "SC1518";
			    }else if("Phosphorothioated RNA" == batchBackbone){
			       return "SC1519";
			    }else if("2@-OMe-RNA" == batchBackbone){
			        return "SC1527";
			    }else if("Phosphorothioated 2@-OMe-RNA" == batchBackbone){
			        return "SC1528";
			    }
			}
			
			</script>
			</head>
			<body>
<table id="updateStatusTable2"  border="0" align="center" cellpadding="0" cellspacing="0" class="General_table"  style="margin:10px auto 0px auto;">
		<tr>
		<td>Job Name: </td>
		<td><input type="text" name="job_name" id="job_name" class="NFText" value="${jobName}"/></td>
		</tr>
		<tr>
			<td colspan="2"><b>Parameters:</b></td>
		</tr>
		<tr>
		<td> Overlap Tm range: </td>
		<td>
		<input type="text" class="NFText" name="tm_min" id="tm_min" value="55" size="3"/> ~
              <input type="text" class="NFText" name="tm_max" id="tm_max" value="58" size="3"/>&deg;C
        </td>
		</tr>
		<tr>
			<td>Minimal overlap: </td>
			<td><input type="text" class="NFText" name="ov_min" id="ov_min" value="15" size="3"/> bp (12 ~ 20 bp)</td>
		</tr>
		<tr>
			<td> Oligo length range:</td>
			<td><input type="text" class="NFText" name="ol_min" id="ol_min" value="40" size="3"/> 
              ~<input type="text" class="NFText" name="ol_max" id="ol_max"  value="50" size="3"/> base</td>
		</tr>
		<tr>
			<td>5'-protection bases:</td>
			<td><input type="text" class="NFText" name="left_primer" id="left_primer" value="${sequence5}"/></td>
		</tr>
		<tr>
			<td>3'-protection bases:</td>
			<td><input type="text" class="NFText" name="right_primer" id="right_primer" value="${sequence3}"/></td>
		</tr>
	   <tr>
           <td width="140" align="left"><b>Please enter sequence:</b></td>
           <td width="200">
           &nbsp;
           </td>
        </tr>
        <tr>
        	<td colspan="2">
        	<textarea id="sequence" rows="15" cols="70">${sequence}</textarea>
        	</td>
        </tr>
        <tr>
            <td height="60" colspan="2"><div class="botton_box">
              <input type="button" name="Submit62" class="style_botton"  value="Send" onclick="downloadExcel();"/>
              <input type="button" name="Submit622"  value="Cancel"  class="style_botton" onclick="parent.$('#seq_input_dlg').dialog('close');parent.redirectTo();" />
            </div></td>
        </tr>
	</table>
	<div  id="prmo_indicator" align="center"></div>
	<s:hidden name="workCenterId" id="workCenterId"></s:hidden>
	<s:hidden name="sessId" id="workOrderSessNo"></s:hidden>
	</body>
	</html>