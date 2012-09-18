<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
          <table id="updateStatusTable" width="650" border="0" align="center" cellpadding="0" cellspacing="0" class="General_table">
             <tr>
               <th width="138">Lot No</th>
               <td width="350">
               <s:select list="workOrder.workOrderLotList" id="lotNo" listKey="id" listValue="lotNo" headerValue="Add Lot No" headerKey="" onchange="lotNoSel();">
			   </s:select>
			   <span id="showCreate"><input type="button" class="style_botton" value="Create" onclick="createLot()"/></span>
               <span id="showRemove">
               <input type="button" class="style_botton" value="Remove" onclick="removeLot()"/>
               <input type="button" class="style_botton" value="Edit" onclick="createLot();"/>
               </span>
               </td>
               <th>&nbsp;</th>
              <td>&nbsp;</td>
             </tr>
             <tr>
              <th width="128" valign="top">Department Code</th>
              <td width="95"><select name="select15" id="departmentCode" style=" width:55px;">
                <option value="A" selected="selected">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
                <option value="D">D</option>
                <option value="E">E</option>
                <option value="F">F</option>
                <option value="G">G</option>
                <option value="H">H</option>
                <option value="I">I</option>
                <option value="J">J</option>
                <option value="K">K</option>
                <option value="L">L</option>
                <option value="M">M</option>
                <option value="N">N</option>
                <option value="O">O</option>
                <option value="P">P</option>
                <option value="Q">Q</option>
                <option value="R">R</option>
                <option value="S">S</option>
                <option value="T">T</option>
                <option value="U">U</option>
                <option value="V">V</option>
                <option value="W">W</option>
                <option value="X">X</option>
                <option value="Y">Y</option>
                <option value="Y">Z</option>
              </select></td>
              <th width="119">Work Group Code</th>
              <td width="158"><select name="select" id="workGroupCode" style=" width:55px;">
 
<option value="0" selected="selected">0</option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="A">A</option>
<option value="B">B</option>
<option value="C">C</option>
<option value="D">D</option>
<option value="E">E</option>
<option value="F">F</option>
<option value="G">G</option>
<option value="H">H</option>
<option value="I">I</option>
<option value="J">J</option>
<option value="K">K</option>
<option value="L">L</option>
<option value="M">M</option>
<option value="N">N</option>
<option value="O">O</option>
<option value="P">P</option>
<option value="Q">Q</option>
<option value="R">R</option>
<option value="S">S</option>
<option value="T">T</option>
<option value="U">U</option>
<option value="V">V</option>
<option value="W">W</option>
<option value="X">X</option>
<option value="Y">Y</option>
<option value="Z">Z</option>
 
              </select></td>
             </tr>
             <tr>
             <th valign="top">Lot Description</th>
             <td><input name="lotDescription" id="lotDesc" type="text" class="NFText"/></td>
             <th>Quantity</th>
              <td><input name="quantity" id="quantity" type="text" class="NFText"/></td>
             </tr>
             <tr>
             <th>Storage Temperature</th>
             <td><select id="storageTemp" name="storageTemp">
             <option value="">Please select</option>

			 <option value="25">
			 25
			</option>
			<option value="4">4
			</option>
			<option value="-20">-20
			</option>
			<option value="-80">-80
			</option>
			<option value="-196">-196
			</option>
             </select></td>
             <th>Shipping Temperature</th>
             <td><select id="shipTemp" name="shipTemp">
             <option value="">Please select</option>

			 <option value="25">
			 25
			</option>
			<option value="4">4
			</option>
			<option value="-20">-20
			</option>
			<option value="-80">-80
			</option>
			<option value="-196">-196
			</option>
             </select></td>
             </tr>
            <tr>
              <th valign="top">Date Created</th>
              <td><input name="textfield" id="creationDate" type="text" class="NFText" size="15" readonly="readonly"/></td>
              <th>Created By</th>
              <td><input name="textfield2" id="createdByName" type="text" class="NFText" size="15" readonly="readonly" value="${createdByName}" /></td>
            </tr>
          
            <tr>
              <th valign="top">&nbsp;</th>
              <td colspan="3">&nbsp;</td>
            </tr>
            <tr>
              <td colspan="4"><div align="center"><br />
                <input type="button" name="Submit2"  class="style_botton" value="Confirm" onclick="confirmLotNo()"/>
                <input  type="button" name="Cancel" value="Cancel" class="style_botton" onclick="$('#create_lot_no_dlg').dialog('close');"/>
              </div></td>
            </tr>
          </table>
          <br />
          <br /><br />
 
</body>
