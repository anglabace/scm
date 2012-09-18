<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table id="updateStatusTable" width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="General_table"  style="margin:10px auto 0px auto;">
<tr>
	    <th colspan="2">
	    	<div align="left">Please click this format(workOrderNo,usSalesOrderNo_itemNo,operation_name,status) to update the work order operation of state input(eg:123,88800123_1,test,In Production)</div>
	    </th>
	  </tr>
	    <tr>
	      <td colspan="2"><textarea name="customConfirmInfo" id="customConfirmInfo" rows="30" cols="110"></textarea></td>
	    </tr>
	    <tr>
	    <td colspan="2">
	    	<div align="center"><br />
	    	<input type="button"   class="style_botton3" value="Select Operation..." onclick="selectOperation();" />
		       <input type="button"   class="style_botton" value="Confirm" onclick="customUpdateOperation();" />
		       <input  type="button" name="Cancel" value="Cancel" class="style_botton" onclick="$('#updateStatusTable').parent().dialog('close');" />
	    	</div>
	    </td>
	  </tr>
</table>