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
		           	  allChoiceVal = allChoiceVal+","+this.value;
		             }
				 });
				 var sessionId = parent.$("#sessionId").val();
				$.ajax({
					type: "POST",
					url: "account_sales_tax!deleteState.action",
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
	 $("#newStateId").click( function() {
	        var sessionId = parent.$("#sessionId").val();
	        var formStr = "sessionId="+sessionId;
	        parent.window.location="account_sales_tax!loadState.action?"+formStr;
	    });
});

function edit(stateId) {
    var sessionId = parent.$("#sessionId").val();
    var formStr = "sessionId="+sessionId+"&stateSessionId="+stateId;
    parent.window.location="account_sales_tax!loadState.action?"+formStr;
}
</script>
</head>
<body class="content" style="background-image: none;">
<div class="input_box">
<div class="content_box">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="list_table">
        <tr>
          <th width="46"> <div align="left">
            <input name="checkbox" type="checkbox"  id="check_all"/>
            <img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" id="check_del"/>
            </div></th>
          <th width="157">States/Provinces</th>
          <th width="222">Code</th>
          <th width="206">Tax Rate</th>
          <th width="136">Status</th>
          <th>Description</th>
        </tr>
      </table>
      <div class="list_box" style="height: 240px;">
       <table id="resultTable" width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  class="list_table">
       			<c:set var="rowcount" value="1"></c:set>
           <s:iterator value="stateMap">
               <c:if test="${rowcount mod 2==0}">
                   <c:set var="tdclass" value=" class=' list_td2'"></c:set>
               </c:if>
               <c:if test="${rowcount mod 2 ==1}">
                   <c:set var="tdclass" value=""></c:set>
               </c:if>
               <tr>
                   <td width="46" ${tdclass}>
                       <div align="left">
                           <div align="left">
                               <input type="checkbox" value="${key}" name="rateId"/>
                           </div>
                       </div>
                   </td>
                   <td width="157" ${tdclass} align="center">
                       <div align="center">&nbsp;<a href="javascript:void(0);"
                                                    onclick="edit('${key}')">${value.name} </a></div>
                   </td>
                   <td width="222" ${tdclass} align="center">&nbsp;${value.stateCode}</td>
                   <td width="206" ${tdclass} align="center">&nbsp;${value.taxRate }</td>
                   <td width="136" ${tdclass} align="center">&nbsp;${value.status}</td>
                   <td ${tdclass} align="center">&nbsp;${value.description}</td>
               </tr>
               <c:set var="rowcount" value="${rowcount + 1 }"></c:set>
           </s:iterator>
  				</table>
      </div>
      <div align="center" style="padding:10px;"><input name="Submit2" id="newStateId" type="button" class="style_botton" value="New" /></div>
      </div>
      </div>
</body>
</html>