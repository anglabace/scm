<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table id="updateStatusTable"  border="0" align="center" cellpadding="0" cellspacing="0" class="General_table"  style="margin:10px auto 0px auto;">
	  <tr>
	    <th>
	    	<div align="left">New Status:</div>
	    </th>
	    <td>
	    <select id="status_sel_dlg" style="width: 207px;">
			<option value="New">New</option>
			<option value="In Production">In Production</option>
			<option value="Production Complete">Production Complete</option>
	    </select>
	    </td>
	  </tr>
	  <tr>
	    <th colspan="2">
	    	<div align="left"><span style="color:red">*</span>Enter the reason</div>
	    </th>
	  </tr>
	    <tr>
	      <th valign="top">&nbsp;</th>
	      <td><textarea name="reason" id="reason" class="content_textarea2"></textarea></td>
	    </tr>
	    <tr>
	    <td colspan="2">
	    	<div align="center"><br />
		       <input type="button"   class="style_botton" value="Confirm" onclick="modifyStatus();" />
		       <input  type="button" name="Cancel" value="Cancel" class="style_botton" onclick="$('#change_work_order_status').dialog('close');" />
	    	</div>
	    </td>
	  </tr>
	</table>