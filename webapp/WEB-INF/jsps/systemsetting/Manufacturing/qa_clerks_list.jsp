<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet"
	type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<link href="${global_css_url}seqno.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" language="javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript" language="javascript"
	src="${global_js_url}util/util.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
	type="text/javascript"></script>
<script type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	//复选框绑定: 全选|全不选
    $("#check_all").click( function() {
       $(":checkbox").each(function() {
          this.checked = $("#check_all").get(0).checked;
       }); 
    });
	/**New Operation为qaGroup 添加一个新的qaClerk.
	*/
    $("#new_wo_btn").click( function() {
	    var formStr = parent.$("#save_form").serialize();
        $.ajax({
			type: "POST",
			url: "q_clerks!saveGroupToSession.action",
			data: formStr,
			success: function(data, textStatus){
				 parent.isSaved = true;
				 var sessionGroupId = $("#sessionGroupId").val();
                 parent.location = "q_clerks!loadQaClerk.action?sessionGroupId="+sessionGroupId;		
			},
			error: function(xhr, textStatus){
			   alert("failure");
			}
	    });  
        return;
    });   
});
function editClerk(key) {
	var formStr = parent.$("#save_form").serialize();
    $.ajax({
		type: "POST",
		url: "q_clerks!saveGroupToSession.action",
		data: formStr,
		success: function(data, textStatus){
			 parent.isSaved = true;
			 var sessionGroupId = $("#sessionGroupId").val();
             parent.location = "q_clerks!loadQaClerk.action?sessionGroupId="+sessionGroupId+"&id="+key;		
		},
		error: function(xhr, textStatus){
		   alert("failure");
		}
    });     
}

function deleteSel() {
	  if ($(":checkbox:gt(0):checked").length < 1) {
      	   alert('Please select one at least!');
		   return;
   		}
		if (!confirm('Are you sure to delete?')) {
			return;
		}
		var allChoiceVal = "";
		$("input[name='delRouteId']:checked").each(function() {
	          allChoiceVal = allChoiceVal+this.value+",";
	     });
		allChoiceVal.substring(0,allChoiceVal.length-1); 
		var sessionGroupId = $("#sessionGroupId").val();
		$.ajax({
			type: "POST",
			url: "q_clerks!deleteClerk.action",
			data: "allChoiceVal="+allChoiceVal+"&sessionGroupId="+sessionGroupId,
			dataType: 'json',
			success: function(data){
				if(hasException(data)){//有错误信息.		
				}else {                        
					alert(data.message);
				    location.reload();
				}
			},
			error: function(msg){
				alert("Delete clerk failed !");
            	$('#check_del').attr("disabled", false);		
			}
		});    
}
</script>
</head>
<body>
<s:hidden name="sessionGroupId" id="sessionGroupId"/>
 <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
    <tr>
      <th width="5%"><input name="checkbox42"   type="checkbox" id="check_all"/>
        <a href="#" title="Delete Clerk" rel="gb_page_center[600,  180]" onclick="deleteSel()"><img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" /></a></th>
      <th width="15%">Clerk ID </th>
      <th width="65%">Clerk Name</th>
      <%--<th width="287">Assigned Product/Service</th>--%>
      <th width="15%">Status</th>
      <%--<th width="198" >Comment</th>--%>
     
      </tr>
  </table>
	  <div class="frame_box" style="height:170px; ">
	    <table width="955" border="0" cellspacing="0" cellpadding="0" class="list_table2">
	    <s:iterator value="qaClerkMap" status="st">
	    	<s:if test="#st.odd">
		    	 <tr>
			        <td width="5%"><input name="delRouteId" type="checkbox"  value="${key}"/></td>
			        <td width="15%"><a href="#" onclick="editClerk('<s:property value='key'/>')">${value.userId}</a></td>
			        <td width="65%">${value.clerkName}</td>
			        <%--<td width="288">${value.productService}</td>--%>
			        <td width="15%"><div align="center">${value.status}</div></td>
			        <%--<td width="200">${value.comment}</td>--%>

		          </tr>
	    	</s:if>
	    	<s:else>
	    		  <tr>
			        <td class="list_td2"><input name="delRouteId" type="checkbox" value="${key}"/></td>
			        <td class="list_td2"><a href="#" onclick="editClerk('<s:property value='key'/>')">${value.userId}</a></td>
			        <td class="list_td2">${value.clerkName}</td>
			        <%--<td class="list_td2">${value.productService}</td>--%>
			        <td class="list_td2"><div align="center">${value.status}</div></td>
			        <%--<td class="list_td2">${value.comment}</td>--%>
			        
		          </tr>
	    	</s:else>
	    </s:iterator>
        </table>
	  </div>
    
    <div align="center" style="padding:10px;">
      <input id="new_wo_btn" type="button" name="Submit33"  class="style_botton" value="New"/>
    </div>
</body>
</html>