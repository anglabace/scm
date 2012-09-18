<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin-left:40px;height:180px;">
          <tr>
            <th height="26">
            	<div align="left">Status
	                <select name="updateStatus" id="updateStatus">                
	                 
	                </select>
           		</div>
            </th>
          </tr>
          <tr id="reasonTr1" style="display:none;">
            <th height="24" valign="top">
            	<div align="left">Choose the reason to update the Order Or Quotation Status: </div>
            </th>
          </tr>
          <tr id="reasonTr2"  style="display:none;">
		    <td>
				 <s:select id="updateStatusReason" name="updateStatusReason" list="dropDownList['STATUS_UPD_REASON']" listKey="text" listValue="text" cssStyle="width:250px;"></s:select> 
		    </td>
  		  </tr>
  		  <tr>
  		  	<th height="24" valign="top">
  		  		<div align="left">Note: </div>
  		  	</th>
  		  </tr>
          <tr>
            <th valign="top">
            	<textarea id="updateNote" name="updateNote" cols="70" rows="2" class="content_textarea"></textarea>
            </th>
          </tr>
          <tr>
	            <th valign="bottom"><div align="center" style="margin:10px;">
	              <input type="submit"  id="updateStatusConfirm" class="style_botton" value="Confirm" />&nbsp;&nbsp;
	              <input type="submit"  id="updateStatusCancel" value="Cancel"  class="style_botton" /> 
	            </div>
            </th>
          </tr>
</table>