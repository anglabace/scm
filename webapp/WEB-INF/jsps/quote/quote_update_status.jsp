<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table id="updateStatusTable"  border="0" align="center" cellpadding="0" cellspacing="0" class="General_table"  style="margin:10px auto 0px auto;">
	  <tr>
	    <th colspan="2">
	    	<div align="left">Choose the reason to cancel the quote or delete the item:</div>
	    </th>
	  </tr>
	  <tr>
	    <th width="120" valign="top">&nbsp;</th>
	    <td  >
	      <select id="statusReason" name="statusReason" style="width:250px;">
		      <option value="CO" selected="selected">Order placed</option>
		      <option value="CO">Cannot get reply from customer</option>
		      <option value="CO">Stopped due to customer’s own reason</option>
		      <option value="CO">Went to other vendor (price)</option>
		      <option value="CO">Went to other vendor (turnaround time)</option>
		      <option value="CO">Went to other vendor (delivery)</option>
		      <option value="CO">Went to other vendor (other reasons)</option>
		      <option value="VD">Void</option>
		      <option value="CO">Other</option>
	      </select> 
	    </td>
	  </tr>
	    <tr>   
	    <th valign="top">Note:</th>
	    <td>&nbsp;</td>
	  </tr>
	    <tr>
	      <th valign="top">&nbsp;</th>
	      <td><textarea name="comment" id="comment" class="content_textarea2"></textarea></td>
	    </tr>
	    <tr>
	    <td colspan="2">
	    	<div align="center"><br />
		       <input type="button"   class="style_botton" value="Confirm" onclick="del_quote('quoteNo');" />
		       <input  type="button" name="Cancel" value="Cancel" class="style_botton" onclick="$('#updateStatusTable').parent().dialog('close');" />
	    	</div>
	    </td>
	  </tr>
	</table>