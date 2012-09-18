<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table id="updateStatusTable"  border="0" align="center" cellpadding="0" cellspacing="0" class="General_table"  style="margin:10px auto 0px auto;">
	   <tr>
           <th width="140">Internal Supplier</th>
           <td width="200">
           <s:if test="workCenterList!=null">
           		<s:select list="workCenterList" id="sel_workCenter" listKey="id" listValue="name">
           		</s:select>
           	</s:if>
           </td>
        </tr>
        <tr>
            <td height="60" colspan="2"><div class="botton_box">
              <input type="button" name="Submit62" class="style_botton"  value="Select" onclick="createInnerOrder();"/>
              <input type="button" name="Submit622"  value="Cancel"  class="style_botton" onclick="$('#updateStatusTable').parent().dialog('close');" />
            </div></td>
        </tr>
	</table>