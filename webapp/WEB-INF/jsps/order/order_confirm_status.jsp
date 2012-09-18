<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin-left:40px;height:180px; width: 440px;">
       <tr>
         <th height="26">
         	Status
         </th>
         <td>
             <select id="updateConfirmStatus" style="width:160px;">
              	<option value="CC">Customer Confirmed</option>
              </select>
         </td>
       </tr>
    <s:if test="order.status == 'RV'">
    <tr>
        <th height="26">
            Update Reason
        </th>
        <td>
           <s:select id="update_reason_sel" name="update_reason_sel" list="updateReasonSel" listKey="value" listValue="text" style="width:160px"/>
        </td>
    </tr>
    <tr>
        <th height="26">
            <font color="red">*</font> Item Update Reason
        </th>
        <td>
            <textarea rows="3" cols="2" id="item_update_reason" name="item_update_reason" style="width:320px"></textarea>
        </td>
    </tr>
    <tr>
        <th height="26">
            <font color="red">*</font>Order Update Reason
        </th>
        <td>
            <textarea rows="3" cols="2" id="order_update_reason" name="order_update_reason" style="width:320px"></textarea>
        </td>
    </tr>
    </s:if>
       <tr>
          <th valign="bottom"></th>
           <td>
               <div align="center" style="margin:10px;">
            <input type="button"  class="style_botton" value="Confirm" id="infoUpdateStatusConfirm" />&nbsp;&nbsp;
            <input type="button"  value="Cancel"  class="style_botton" onclick="$('#confirmStatusDialog').dialog('close');" />
          </div>
           </td>
      </tr>
</table>