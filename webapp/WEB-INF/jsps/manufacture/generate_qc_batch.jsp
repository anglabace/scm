<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script type="text/javascript">
function confirm() {
	var url = "workorder_proc!confrim.action";
	var batchNo = $("#batchNo").val();
	var batchType =$("#batchType").val();
	var status = $("#status").val();
	var batchFunction = $("#batchFunction").val();
	var orderNoStrs = $("#orderNoStr").val();
	var dataParam ="woBatcheDto.batchNo="+batchNo+"&woBatcheDto.batchType="+batchType+"&woBatcheDto.status="
	+status+"&woBatcheDto.orderNoStr="+orderNoStrs+"&woBatcheDto.batchFunction="+batchFunction;
	if(batchNo==null||batchNo=="") {
		alert("batchNo is null!");
		$("#batchNo").focus();
		return;
	}
	$.ajax({
		type: "POST",
		url: url,
		data: dataParam,
		dataType: "json",
		success: function(data){
			if(hasException(data)){//有错误信息.
		           $('#sub1').attr("disabled", false);				
			} else {
				alert(data.message);
				window.parent.location.reload();
				$("#frame12").dialog("close");
			}
		},
		error: function(msg){
			alert("Error:batch failed!!");
			window.$ = window.parent.$;
			$("#frame12").dialog("close");
		}
	});
	
}
function cancel() {
	window.parent.location.reload();
	$("#frame12").dialog("close");
}
function GenerateNo() {
	var initializationTime=(new Date()).getTime();
	var random = parseInt(10*Math.random());
	var bathNo=initializationTime+random;
	$("#batchNo").val(bathNo);
}
</script>
</head>
<body>
<table width="600" border="0" cellspacing="3" cellpadding="0" id="table11" bgcolor="#96BDEA">

  <tr>
    <td bgcolor="#FFFFFF"><table width="600" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="384" style="padding-left:30px;"><br />
          <table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
           
              <tr>
                <td height="22" colspan="3">
                The following items will be added in one batch for the process of Quality Assurance</td>
                </tr>
              <tr>
                <th width="15%"> QC Batch No</th>
                <td width="20%"><s:textfield name="woBatcheDto.batchNo" class="NFText" size="20" id="batchNo" readonly="true"/></td>
                <td width="65%"><input name="Submit3" type="button" class="style_botton" value="Generate" onclick="GenerateNo();"/></td>
              </tr>
            </table>
              <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td colspan="4" style="padding-top:10px;"><table width="530" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <tr>
                      <th width="96">Work Order No</th>
                      <th width="96">Order No</th>
                      <th width="96">Item No</th>
                      <th>Product</th>
                      
                    </tr>
                  </table></td>
                </tr>
                <tr>
                  <td colspan="4"><div  style="width:547px; height:210px; overflow:scroll;">
                    <table width="530" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <s:iterator value="workOrderList" status="st">
                    	<s:if test="#st.odd">
                    		<tr>
	                        <td  width="96"><a href="workorder_entry!edit.action?id=${orderNo}&operation_method=view" target="_blank"><s:property value="orderNo"/></a></td>
	                        <td  width="96"><s:property value="orderNo"/></td>
	                        <td width="96"><s:property value="soItemNo"/></td>
	                        <td><s:property value="modifyUser"/></td>
	                        </tr>
                    	</s:if>
                    	<s:else>
                    		<tr>
	                        <td class="list_td2"><a href="workorder_entry!edit.action?id=${orderNo}&operation_method=view" target="_blank"><s:property value="orderNo"/></a></td>
	                        <td class="list_td2"><s:property value="orderNo"/></td>
	                        <td class="list_td2"><s:property value="soItemNo"/></td>
	                        <td class="list_td2"><s:property value="modifyUser"/></td>
	                        </tr>
                    	</s:else>
                    </s:iterator>
                    </table>
                  </div></td>
                </tr>
            
                <tr>
                  <td height="40" colspan="4"><div align="center">
                    <input id="sub1" name="Submit1" type="button" class="style_botton"  value="Confirm" onclick="confirm();"/>
                    <input id="sub2" name="Submit1" type="button" class="style_botton"  value="cancel" onclick="cancel();"/>
                  </div></td>
                </tr>
              </table></td>
          </tr>
        </table>          <br /></td>
      </tr>
    </table></td>
  </tr>
  <s:hidden name="woBatcheDto.batchType" id="batchType"></s:hidden>
  <s:hidden name="woBatcheDto.status" id="status"></s:hidden>
  <s:hidden name="woBatcheDto.batchFunction" id="batchFunction"></s:hidden>
  <s:hidden name="woBatcheDto.orderNoStr" id="orderNoStr"></s:hidden>
</table>
</body>
</html>