<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<tbody>
	<tr>
	<th width="32"><input type="radio" name="radio" id="radio2" value="radio" /></th>
                  <th width="128">Supplier Invoice No</th>
                  <th width="59">Supplier</th>
                  <th width="46">PO  No</th>
                  <th width="99">Invoice Amount</th>
                  <th width="82">Paid Amount</th>
                  <th width="110">Discount Amount</th>
                  <th width="57">Balance</th>
                  <th width="56" >Currency</th>

	</tr>
	<c:set var="rowcount" value="1"></c:set>
	<s:iterator value="invoiceList">
		<c:if test="${rowcount mod 2 == 0}">
			<c:set var="tdclass" value=" class='list_td2'"></c:set>
		</c:if>
		<c:if test="${rowcount mod 2 == 1}">
			<c:set var="tdclass" value=""></c:set>
		</c:if>

		<tr ${tdclass }>
			<td align="center" width="30"    ${tdclass}   >
				<input type="radio" name="Invoice_NO" onclick="resetValue()"
					id="Invoice_NO" value="${invoiceId}" />
			</td>
			<td ${tdclass}  >
				${invoiceId}
			</td>
			<td ${tdclass}  >
				${vendorNo}
			</td>
			<td ${tdclass}  >
				${orderNo}
			</td>
		<td align="right"  ${tdclass}  >
				${symbol}${totalAmount}
			</td>
			<td align="right"  ${tdclass}  >
				${symbol}${paidAmt}
			</td>
			<td align="right" ${tdclass}  >
				${discount}
			</td>

			<td align="right" ${tdclass}  >
				${balance}
			</td>
			<td align="right" ${tdclass}  >
				${currency}
			</td>
		</tr>
		<c:set var="rowcount" value="${rowcount+1}"></c:set>
	</s:iterator>
</tbody>