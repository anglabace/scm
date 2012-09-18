<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<table id="creditCardEditTb" width="400" border="0" cellspacing="0" cellpadding="0" class="General_table">
  <tr>
  	<td colspan="3">&nbsp;
  		<input type="hidden" name="ccId" />
  	</td>
  </tr>
  <tr>
    <th><span class="important">*</span>Card Type</th>
    <td colspan="2">
	    <s:select cssStyle="width:167px;" name="ccType" list="cardTypeList" listKey="value" listValue="text" ></s:select>
    </td>
  </tr>
  <tr>
    <th valign="top">
    	<span class="important">*</span>Card Number
    </th>
    <td colspan="2">
    	<input name="ccNo" id="ccNo" type="text"  class="pickdate NFText" value="" size="25" onkeypress="creditVilid();"/>
    </td>
  </tr>

    <tr>
    <th><span class="important">*</span>Name on the Card</th>
    <td colspan="2">
    	<input name="ccHolder" type="text" class="pickdate NFText" value="" size="25"/>
    </td>
  </tr>
  <tr>
  <th>CVC</th>
  <td>
  	<input name="ccCvc" type="password"  class="pickdate NFText" value="" size="25"/>
  </td>
  </tr>
   <tr>
  <th>
  	<span class="important">*</span>Expiration Date
  </th>
  <td> 
   	<select id="year" name="year"  style="width:81px">
  	<option value="" selected></option>
  	<option value="2010">2010</option>
  	<option value="2011">2011</option>
  	<option value="2012">2012</option>
  	<option value="2013">2013</option>
  	<option value="2014">2014</option>
  	<option value="2015">2015</option>
  	<option value="2016">2016</option>
  	<option value="2017">2017</option>
  	<option value="2018">2018</option>
  	<option value="2019">2019</option>
  	<option value="2020">2020</option>
  	</select>
  	<select id="month" name="month"  style="width:81px" >
  	<option value="" selected></option>
  	<option value="01">1</option>
  	<option value="02">2</option>
  	<option value="03">3</option>
  	<option value="04">4</option>
  	<option value="05">5</option>
  	<option value="06">6</option>
  	<option value="07">7</option>
  	<option value="08">8</option>
  	<option value="09">9</option>
  	<option value="10">10</option>
  	<option value="11">11</option>
  	<option value="12">12</option>
  	</select>
  </td>
  </tr>
  <tr>
    <th colspan="3">
      <div align="center">
        <input id="credtModifyBtn" type="button" class="style_botton" value="Add" />
        <input type="button" class="style_botton" value="Cancel" onclick="$('#customerCreditDialog').dialog('close');" />
      </div>
    </th>
  </tr>
</table>
<script>
  function creditVilid(){
      if($("#ccNo").val().length>29) event.returnValue = false;
  }
</script>
