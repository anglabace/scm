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
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
<script language="JavaScript" type="text/javascript">
$(function(){
	$("#check_all").click( function() {
	       $(":checkbox").each(function() {
	          this.checked = $("#check_all").get(0).checked;
	       });  
	});
	 $("#check_del").click(function(){
		 if ($(":checkbox:gt(0):checked").length < 1) {
		      alert('Please select one to continue your operation!');
				   return;
		   }
				if (!confirm('Are you sure to delete?')) {
					return;
				}
				var allChoiceVal = "";
				 $("#resultTable :checkbox:checked").each(function() {
					 if(allChoiceVal=="") {
		           	  allChoiceVal=this.value;
		             } else {
		           	  allChoiceVal = allChoiceVal+"-"+this.value;
		             }
				 });
				 var sessionId = parent.$("#sessionId").val();
				$.ajax({
					type: "POST",
					url: "territory_assign!deleteTerr.action",
					data: "allChoiceVal="+allChoiceVal+"&sessionId="+sessionId,
					dataType: 'json',
					success: function(data){
						if(hasException(data)){//有错误信息.		
						}else {                
						    window.location.reload();
						}
					},
					error: function(msg){
						alert("System error! Please contact system administrator for help.");
		            $('#check_del').attr("disabled", false);		
					}
				});    
	 });
	 $("#new_terr_btn").click( function() {
		    var formStr = parent.$("#saleRepForm").serialize();
		    parent.$('#save_btn').attr("disabled", true);
	        var sessionId = parent.$("#sessionId").val();
	        $.ajax({
				type: "POST",
				url: "territory_assign!save2Session.action",
				data: formStr,
				success: function(data, textStatus){
					 isSaved = true;
					 parent.window.location = "territory_assign!loadTerr.action?sessionId="+sessionId+"&defaultTab=${defaultTab}";		
				},
				error: function(xhr, textStatus){
				   parent.$('#save_btn').attr("disabled", false);
				   alert("System error! Please contact system administrator for help.");
				}
		    });  
	        return;
	    });
});

function editTerr(terrSessionId) {
	var formStr = parent.$("#saleRepForm").serialize();
	parent.$('#save_btn').attr("disabled", true);
    var sessionId = parent.$("#sessionId").val();
    $.ajax({
		type: "POST",
		url: "territory_assign!save2Session.action",
		data: formStr,
		success: function(data, textStatus){
			 isSaved = true;
			 parent.window.location = "territory_assign!loadTerr.action?sessionId="+sessionId+"&terrSessionId="+terrSessionId+"&defaultTab=${defaultTab}";		
		},
		error: function(xhr, textStatus){
			parent.$('#save_btn').attr("disabled", false);
		   alert("System error! Please contact system administrator for help.");
		}
    });  
    return;
}
</script>
</head>
<body class="content" style="background-image: none;">
<div class="input_box">
<div class="content_box">
<table width="972" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><div style="margin-right:17px;">
            <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
              <tr>
                <th width="46"><div class="quote_dele">
                  <input type="checkbox" id="check_all" name="checkbox11"/>
		       <img src="${global_url}images/file_delete.gif" id="check_del" alt="Delete" width="16" height="16" border="0" /></div>
                  </th>
                 <th width="228">Territory Code</th>
                <th width="276">Territory Name</th>
                <th width="122">Status</th>
                <th>Description</th>
                </tr>
            </table>
          </div></td>
        </tr>
        <tr>
          <td><div class="list_box" style="height:250px;">
            <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table" id="resultTable">
            <s:if test="terrMap!=null">
		      <s:iterator value="terrMap" status="st">
		      <s:if test="#st.odd">
			      <tr>
			      	 <td width="46"><input type="checkbox" value="${key}" id="${key}"  name=delRouteId/></td>
			      	 <td width="228"><a href="#" onclick="editTerr('${key}')">${value.salesTerritory.territoryCode}</a></td>
			         <td width="276">${value.salesTerritory.territoryName}</td>
			         <td width="122">${value.salesTerritory.status}
			         </td>
			         <td>${value.salesTerritory.description}</td>
			      </tr>
		      </s:if>
		      <s:else>
		      	<tr>
		        <td class="list_td2"><input type="checkbox" value="${key}" id="${key}"  name=delRouteId/></td>
		        <td class="list_td2"><a href="#" onclick="editTerr('${key}')">${value.salesTerritory.territoryCode}</a></td>
		        <td class="list_td2">${value.salesTerritory.territoryName}</td>
		        <td class="list_td2">
					${value.salesTerritory.status}
				</td>
		        <td class="list_td2">${value.salesTerritory.description}</td>
		        </tr>
		      </s:else>
		      </s:iterator>
		      </s:if>
            </table>
          </div></td>
        </tr>
       
      </table>
      <div align="center" style="padding:10px;">
        <input name="Submit32" type="button" id="new_terr_btn" class="style_botton" value="New"/></div>
      <div align="center"></div>
</div>
</div>
</body>
</html>