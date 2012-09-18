<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
			language="javascript"></script>
<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
<script language="JavaScript" type="text/javascript">
$(function(){
	  init();
	//复选框绑定: 全选|全不选
	    $("#check_all").click( function() {
	    	var allChoiceVal = "";
	       $(":checkbox").each(function() {
	          this.checked = $("#check_all").get(0).checked;
	          if(this.checked&&this.name=="delRouteId") {
	              if(allChoiceVal=="") {
	            	  allChoiceVal=this.value;
	              } else {
	            	  allChoiceVal = allChoiceVal+","+this.value;
	              }
	          }
	       }); 
	       parent.document.getElementById("choiceOption").value=allChoiceVal;  
	    });
	  if(parent.$("#customFlag").val()==1) {
		  $("[name='status']").bind("click", statusTdClick);
	  }
	    
	
}); 

//复选框选择事件
function choice(obj) {
	var allChoiceVal = parent.document.getElementById("choiceOption").value;
	var id = obj.value;
	if(obj.checked) {
		if(allChoiceVal=="") {
			allChoiceVal=id;
		} else {
			allChoiceVal=allChoiceVal+","+id;
		}
		
	} else {
		var allChoiceValArr = allChoiceVal.split(",")
		allChoiceVal ="";
		if(allChoiceValArr==null||allChoiceValArr==""||allChoiceValArr.length==0) {
			allChoiceVal=allChoiceVal+id;
		} else {
			$.each(allChoiceValArr,function(k,v) {
				if(v!=id) {
					if(allChoiceVal=="") {
						allChoiceVal=allChoiceVal+v;
					} else {
						allChoiceVal=allChoiceVal+","+v;
					}
					
				}
			});
		}
	}
	parent.document.getElementById("choiceOption").value=allChoiceVal;
}

function deleteSel() {
	  if ($(":checkbox:gt(0):checked").length < 1) {
        alert('Please select one at least!');
		   return;
     }
		if (!confirm('Are you sure to delete?')) {
			return;
		}
		var allChoiceVal = parent.document.getElementById("choiceOption").value;
		$.ajax({
			type: "POST",
			url: "document_templates!delete.action",
			data: "allChoiceVal="+allChoiceVal,
			dataType: 'json',
			success: function(data){
				if(hasException(data)){//有错误信息.		
				}else {                        
					alert(data.message);
				    window.location.reload();
				}
			},
			error: function(msg){
				alert("Delete document template failed !");
              $('#check_del').attr("disabled", false);		
			}
		});    
}

//复选框初始化
function init() {
	var allChoiceVal = parent.document.getElementById("choiceOption").value;
	if(allChoiceVal!="") {
		var allChoiceValArr = allChoiceVal.split(",");
		$.each(allChoiceValArr,function(k,v) {
			var id = "#"+v;
			if($(id)!=null) {
				$(id).attr("checked",true);
			}
		});
	}
}

function statusTdClick() {
	var thisObj = $(this);
	var tableTdStatus = thisObj;
	var status = tableTdStatus.html().replace('&nbsp;', '');
	if (status.search('select') != -1
			|| status.search('SELECT') != -1) {
		return;
	}
	//display input element
	tableTdStatus
			.html('<select name="statusSel" id="statusSel"><option value="New">New</option><option value="In Production">In Production</option><option value="Completed">Completed</option><option value="Failed">Failed</option><option value="Hold">Hold</option></select>');
	$("#statusSel").val(status);
	$("#statusSel").focus();
	tableTdStatus.find('select').change( function() {
				changeSelect(thisObj,$("#statusSel").val());
			}).blur( function() {
				changeSelect(thisObj,$("#statusSel").val());
			});
	
}
function changeSelect(obj,status) {
	obj.html(status);
}
</script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><div style="overflow:scroll; width:100%;height:300px;">
    <table width="98%" border="0" cellspacing="0" cellpadding="0" class="list_table">
      <tr>
      	 <th width="5%">
			<div align="left">
				<input name="checkbox2" type="checkbox" id="check_all" />
			</div>
		 </th>
         <th width="8%">Work Order No</th>
         <th width="8%">China Sales Order No</th>
          <th width="8%">US Sales Order No</th>
         <th width="4%">Item No</th>
         <th>Work Group</th>
         <th width="12%">Operation</th>
         <th width="10%">Status</th>
         <th width="8%">Sch Start Date</th>
         <th width="8%">Sch Complete Date</th>
         <th width="8%">Cust Start Date</th>
         <th width="8%">Cust Complete Date</th>
         </tr>
 <s:if test="workOrderOperationList!=null">
      <s:iterator value="workOrderOperationList" status="st">
      <s:if test="#st.odd">
	      <tr>
	      	 <td width="5%">
				<input type="checkbox" value="${id}" id="${id}"
					name="delRouteId" onclick="choice(this);"/>
			 </td>
	      	 <td width="8%" name="workOrderNo">${altOrderNo}</td>
	         <td width="8%" name="soNo">${soNo}</td>
	         <td width="8%" name="srcSoNo">${srcSoNo}</td>
	         <td width="4%" name="soitemNo">${soItemNo}</td>
	         <td>${workGroupNames}</td>
	         <td width="12%" name="operationName">${operationName}</td>
	          <td width="10%" name="status">${status}</td>
	         <td width="8%">${exptdStartDate}</td>
	         <td width="8%">${exptdEndDate}</td>
	         <td width="8%" name="customStartDate">${customStartDate}</td>
	         <td width="8%" name="customEndDate">${customEndDate}</td>
	      </tr>
      </s:if>
      <s:else>
	      <tr>
	      		<td class="list_td2">
				<input type="checkbox" value="${id}" id="${id}"
					name="delRouteId" onclick="choice(this);"/>
				 </td>
	      	  <td class="list_td2" name="workOrderNo">${altOrderNo}</td>
	          <td class="list_td2" name="soNo">${soNo}</td>
	          <td class="list_td2" name="srcSoNo">${srcSoNo}</td>
	          <td class="list_td2" name="soitemNo">${soItemNo}</td>
	          <td class="list_td2">${workGroupNames}</td>
	          <td class="list_td2" name="operationName">${operationName}</td>
	          <td class="list_td2" name="status">${status}</td>
	          <td class="list_td2">${exptdStartDate}</td>
	          <td class="list_td2">${exptdEndDate}</td>
	          <td class="list_td2" name="customStartDate">${customStartDate}</td>
	         <td class="list_td2" name="customEndDate">${customEndDate}</td>
	      </tr>
      </s:else>
      </s:iterator>
      </s:if>
    </table></div></td>
  </tr>
  
  <tr>
	  <td>
	  <div class="grayr">
			Total:<s:property value="workOrderOperationList.size()"/>
	  </div>
	  </td>
  </tr>
  <s:hidden name="woOperationIds" id="woOperationIds"></s:hidden>
</table>    
</body>
</html>