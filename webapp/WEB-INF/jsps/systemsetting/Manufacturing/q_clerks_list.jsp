
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
			url: "q_clerks!delete.action",
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
        <th width="46">
        <div align="left">
				<input type="checkbox" name="checkbox11" id="check_all"/>
		         <a href="#" title="Delete Group" rel="gb_page_center[600,  180]" onclick="deleteSel()"><img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" /></a>
		</div>
		</th>
        <th width="141">Group Name</th>
        <th width="171">Description</th>
        <th width="82">Status</th>
        <th width="196">Group Function</th>
        <th width="109">Supervisor</th>
        <th width="120">Modified Date</th>
        <th>Modified By</th>
         </tr>

    </table>
    </div></td>
  </tr>
  <tr>
    <td>
	<div class="list_box">
      <table width="993" border="0" cellspacing="0" cellpadding="0" class="list_table2">
      <s:if test="qaGroupPage!=null&&qaGroupPage.result!=null">
      <s:iterator value="qaGroupPage.result" status="st">
      <s:if test="#st.odd">
       <tr>
          <td width="46"><input type="checkbox" value="${id}" name="delRouteId" onclick="choice(this)"/></td>
          <td width="141"><a href="q_clerks!load.action?groupId=<s:property value='id'/>" target="_parent">${groupName}</a></td>
          <td width="171">${description}</td>
          <td width="82" align="center">${status}</td>
          <td width="196">${groupFunction}</td>
          <td width="109">${supervisor}</td>
          <td width="120"><div align="center"><s:date name="modifyDate" format="yyyy-MM-dd"/></div></td>
          <td>${modifyUser}</td>
        </tr>
      </s:if>
      <s:else>
        <tr>
          <td class="list_td2"><input type="checkbox" value="${id}" name="delRouteId" onclick="choice(this)"/></td>
          <td class="list_td2"><a href="q_clerks!load.action?groupId=<s:property value='id'/>" target="_parent">${groupName}</a></td>
          <td class="list_td2">${description}</td>
          <td class="list_td2" align="center">${status}</td>
          <td class="list_td2">${groupFunction}</td>
          <td class="list_td2">${supervisor}</td>
          <td class="list_td2"><div align="center"><s:date name="modifyDate" format="yyyy-MM-dd"/></div></td>
          <td>${modifyUser}</td>
        </tr>
      </s:else>
      </s:iterator>
      </s:if>
      </table>
	</div>
  </tr>
  <tr><td><div class="grayr"> 
  	  <jsp:include page="/common/db_pager.jsp">
			<jsp:param value="${ctx}/q_clerks!list.action" name="moduleURL" />
	  </jsp:include>
  </div></td></tr>
</table>
</div>
</div>    
</body>
</html>