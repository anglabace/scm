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
	            	  allChoiceVal = allChoiceVal+"-"+this.value;
	              }
	          }
	       }); 
	       parent.document.getElementById("choiceOption").value=allChoiceVal;  
	    });
	
}); 

//复选框选择事件
function choice(obj) {
	var allChoiceVal = parent.document.getElementById("choiceOption").value;
	var id = obj.value;
	if(obj.checked) {
		if(allChoiceVal=="") {
			allChoiceVal=id;
		} else {
			allChoiceVal=allChoiceVal+"-"+id;
		}
		
	} else {
		var allChoiceValArr = allChoiceVal.split("-");
		allChoiceVal ="";
		if(allChoiceValArr==null||allChoiceValArr==""||allChoiceValArr.length==0) {
			allChoiceVal=allChoiceVal+id;
		} else {
			$.each(allChoiceValArr,function(k,v) {
				if(v!=id) {
					if(allChoiceVal=="") {
						allChoiceVal=allChoiceVal+v;
					} else {
						allChoiceVal=allChoiceVal+"-"+v;
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
		var allChoiceValArr = allChoiceVal.split("-");
		$.each(allChoiceValArr,function(k,v) {
			var id = "#"+v;
			if($(id)!=null) {
				$(id).attr("checked",true);
			}
		});
	}
}
</script>
</head>
<body class="content" style="background-image: none;">
<div class="input_box">
<div class="content_box">
<table width="1010" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><div style="margin-right:17px;">
    <table width="993" border="0" cellspacing="0" cellpadding="0" class="list_table">
      <tr>
        <th width="46"><div align="left">
					    <input type="checkbox" id="check_all" name="checkbox11"/>
		       <a href="#" title="Delete Template" rel="gb_page_center[600,  180]" onclick="deleteSel()"><img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" /></a></div></th>
        <th width="75">Template ID</th>
        <th width="207">Template Name</th>
        <th width="127">Description</th>
        <th width="66">Status</th>
        <th width="120">Work  Function</th>
        <th width="109">Service Category</th>
       
        <th width="120">Document Template</th>
        <th>Modified Date</th>
         </tr>

    </table>
    </div></td>
  </tr>
  <tr>
    <td>
	<div class="list_box">
      <table width="993" border="0" cellspacing="0" cellpadding="0" class="list_table2">
      <s:if test="serviceDocTemplatePage!=null&&serviceDocTemplatePage.result!=null">
      <s:iterator value="serviceDocTemplatePage.result" status="st">
      <s:if test="#st.odd">
	      <tr>
	      	 <td width="46"><input type="checkbox" value="${templateId}" id="${templateId}" onclick="choice(this);" name=delRouteId/></td>
	      	 <td width="75">${templateId}</td>
	         <td width="207"><a href="document_templates!load.action?templateId=<s:property value='templateId'/>" target="_parent">${name}</a></td>
	         <td width="127">${description}</td>
	         <td width="66">${status}</td>
	         <td width="120">${function}</td>
	         <td width="109">${serviceCategory}</td>
	         <td width="120"><img src="images/book1.png" width="17" height="15" /><a href="download.action?fileName=<s:property value='docName'/>&filePath=<s:property value='filePath'/>">${docName}</a></td>
	         <td><div align="center"><s:date name="modifyDate" format="yyyy-MM-dd"/></div></td>
	      </tr>
      </s:if>
      <s:else>
	      <tr>
	          <td class="list_td2"><input type="checkbox" value="${templateId}" id="${templateId}" onclick="choice(this);" name=delRouteId/></td>
	      	  <td class="list_td2">${templateId}</td>
	          <td class="list_td2"><a href="document_templates!load.action?templateId=<s:property value='templateId'/>">${name}</a></td>
	          <td class="list_td2">${description}</td>
	          <td class="list_td2">${status}</td>
	          <td class="list_td2">${function}</td>
	          <td class="list_td2">${serviceCategory}</td>
	          <td class="list_td2"><img src="images/book1.png" width="17" height="15" /><a href="download.action?fileName=<s:property value='docName'/>&filePath=<s:property value='filePath'/>">${docName}</a></td>
	          <td class="list_td2"><div align="center"><s:date name="modifyDate" format="yyyy-MM-dd"/></div></td>
	      </tr>
      </s:else>
      </s:iterator>
      </s:if>
      </table>
	</div>
  </tr>

  <tr>
	  <td>
	  <div class="grayr">
	  <jsp:include page="/common/db_pager.jsp">
			<jsp:param value="${ctx}/document_templates!result.action" name="moduleURL" />
	  </jsp:include>
	  </div>
	  </td>
  </tr>
</table>
</div>
</div>      
</body>
</html>