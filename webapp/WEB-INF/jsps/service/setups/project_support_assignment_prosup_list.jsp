<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script  language="JavaScript" type="text/javascript">
//function mysearch () {
	//window.location.href = "ar_invoice!customerlist.action";
//}

function selectSup () {
	var item = $(":radio:checked"); 
	var len=item.length; 
	if(len>0){ 
		parent.$("#projectSupport").val($(':radio:checked').val());
		doCancel();
	} 
}

function doCancel(){
	window.parent.$('#searchProjectSupportDialog').dialog('close');
}
</script>
</head>
<body><br /><table width="100%" border="0" cellspacing="3" cellpadding="0" id="table11" >
<tr><td bgcolor="#FFFFFF">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td style="padding-left:20px;"><br />
          <table width="95%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td>
            <form method="post" target="_self" id="searchForm" action="project_support_assignment!searchProjectSupport.action">
            <input type="hidden" name="proSupPage.pageNo" value="${proSupPage.pageNo}" id="pageNo" />
            <input type="hidden" name="proSupPage.orderBy" value="${proSupPage.orderBy}" id="orderBy" />
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="project_support_table">
              <tr>
                <td align="center">projectSupport
                &nbsp;<input name="projectSupportAssignmentDto.projectSupport" type="text" value="${projectSupportAssignmentDto.projectSupport}" class="NFText" size="20"/>
              	&nbsp;<input type="submit" name="Submit5" value="Search" class="search_input" /></td>
              </tr>
            </table>
            </form>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                  <td style="padding-top:10px;"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <tr>
                      <th width="30">&nbsp;</th>
                      <th>projectSupport</th>
                    </tr>
                  </table></td>
                </tr>
                <tr>
                  <td>
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <s:iterator value="proSupPage.result" var="projectSupport">
                	 <tr>
                        <td  width="30" align="center">
                          <input type="radio" name="radio" id="custNo" value="${projectSupport[1]}" />
                        </td>
                     	<td>${projectSupport[1]}</td>
                     </tr>
                    </s:iterator>
                    </table>
                    </td>
                </tr>
                  <tr>
      <td><div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
			  <jsp:param value="${ctx}/order/project_support_assignment!searchProjectSupport.action" name="moduleURL"/>
			</jsp:include>	
		  </div>
	  </td>
    </tr>
                <tr>
                  <td height="40"><div align="center">
                    <input id="sub1" type="button" name="Submit1" value="Select" class="style_botton" onclick="selectSup()"/>
                    <input id="sub2" type="button" name="Submit2" value="Cancel" class="style_botton" onclick="doCancel()"/>
                    </div></td>
                </tr>
              </table></td>
          </tr>
        </table>          <br /></td>
      </tr>
    </table></td>
  </tr>
</table>

</body>
</html>
