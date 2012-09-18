<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.dialog.all.js"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script>
 $(function(){
	 $('.pickdate').each(function(){
			$(this).datepicker(
				{
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true,
					yearRange: '-100:+0'
				});
		});
 });
 </script>
<body>
<table id="updateStatusTable"  border="0" align="center" cellpadding="0" cellspacing="0" class="General_table"  style="margin:10px auto 0px auto;">
	  <tr>
	    <th>
	    	<div align="left">New Status:</div>
	    </th>
	    <td>
	    <select id="status_sel_dlg">
	    	<option value="In Production">In Production</option>
	    	<option value="Completed">Completed</option>
	    	<option value="Failed">Failed</option>
	    </select>
	    </td>
	    <th>Customized Start Date </th>
	    <td><input name="customStartDate_change"  id="customStartDate" type="text" class="pickdate NFText" style="width: 124px;"/></td>
	  </tr>
	  <tr>
	    <th>
	    	<div align="left">Comments</div>
	    </th>
	    <td colspan="3"><textarea  id="comments" class="content_textarea2"></textarea></td>
	  </tr>
	    <td colspan="4">
	    	<div align="center"><br />
		       <input type="button"   class="style_botton" value="Confirm" onclick="parent.modifyStatus();" />
		       <input  type="button" name="Cancel" value="Cancel" class="style_botton" onclick="parent.$('#update_status_dlg').dialog('close');" />
	    	</div>
	    </td>
	  </tr>
	</table>
	</body>
	</html>