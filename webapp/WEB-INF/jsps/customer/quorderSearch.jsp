<script>
	$(function() {
		$('#resetButton').click(function() {
			$('#toDate').attr('value', '');
			$('#fromDate').attr('value', '');
			$('#toAmount').attr('value', '');
			$('#fromAmount').attr('value', '');
			$('#status').val('');
		});
	})
</script>
<form id="quorderSearch" name="quorderSearch"
	action="cust_qu_order!showQuorderListByCustNo.action">
	<table width="207" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="2" class="order_date">View <c:choose>
					<c:when test="${codeType =='order'}">Order</c:when>
					<c:otherwise>Quote</c:otherwise>
				</c:choose>s with</td>
		</tr>
		<tr>
			<td width="63" align="right">Status</td>
			<td><select name="status" id="status">
					<option value="">Select</option>
					<s:iterator value="statusList">
						<s:iterator value="dropDownDTOs">
							<option value="${name}" label="${name}">${name}</option>
						</s:iterator>
					</s:iterator>
			</select> <script>
				$('#status option[value="${status}"]').attr('selected', true);
			</script></td>
		</tr>
		<tr>
			<td align="right">Amount</td>
			<td><input name="fromAmount" id="fromAmount" type="text"
				class="NFText" size="5" value="${fromAmount}" />~ <input
				name="toAmount" id="toAmount" type="text" class="NFText" size="5"
				value="${toAmount}" /></td>
		</tr>
	</table>

	<table width="207" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="4" class="order_date"><c:choose>
					<c:when test="${codeType =='order'}">Order</c:when>
					<c:otherwise>Quote</c:otherwise>
				</c:choose> Date</td>
		</tr>
		<tr>
			<td align="right">From</td>
			<td width="80"><input name="fromDate" id="fromDate" type="text"
				class="ui-datepicker"
				style="width: 80px;; border: 1px solid #A7AAB9; padding: 0px 3px; margin: 0px 2px"
				value="<s:date name="fromDate" format="yyyy-MM-dd" />" />
			</td>
			<td align="right">To</td>
			<td width="80"><input name="toDate" id="toDate" type="text"
				class="ui-datepicker"
				style="width: 80px;; border: 1px solid #A7AAB9; padding: 0px 1px; margin: 0px 1px"
				value="<s:date name="toDate" format="yyyy-MM-dd" />" />
			</td>
		</tr>
		<%--  <tr>
    <td ailgn="right">To</td>
    <td><input name="toDate" id="toDate" type="text"  class="ui-datepicker" style="width:100px;;border:1px solid #A7AAB9;padding: 0px 1px;margin:0px 1px"  value="<s:date name="toDate" format="yyyy-MM-dd" />"/></td>
    <td></td>
  </tr> --%>
		<tr>
			<td height="48" colspan="4"><div align="center">
					<input type="button" class="style_botton" name="resetButton"
						id="resetButton" value="Reset" /> <input type="submit"
						class="style_botton" name="ProcessButton" id="ProcessButton"
						value="Process" /> <input type="hidden" name="codeType"
						id="codeType" value="${codeType}" /> <input type="hidden"
						name="custNo" id="custNo" value="${custNo}" /> <input
						type="hidden" name="searchType" id="searchType" value="1" />
				</div>
			</td>
		</tr>
	</table>
</form>