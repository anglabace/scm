<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<tbody>
	<tr>
		<th align="center" width="30">
			<input type="radio" name="Invoice_NO" onclick="resetValue()"
				id="Invoice_NO" value="0" />
		</th>
		<th width="71">
			Invoice No
		</th>
		<th width="79">
			Customer
		</th>
		<th width="70">
			Order No
		</th>
		<th width="101">
			Invoice Amount
		</th>
		<th width="92">
			Paid Amount
		</th>
		<th width="102">
			Discount Amount
		</th>

		<th width="107">
			Bad Debt
		</th>
		<th width="56">
			Balance
		</th>
		<th width="70">
			Currency
		</th>
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
			<td align="center" width="30" ${tdclass} >
				<input type="radio" name="Invoice_NO" onclick="resetValue()"
					id="Invoice_NO" value="${invoiceId}" />
			</td>
			<td ${tdclass} >
				${invoiceId}
			</td>
			<td ${tdclass} >
				${custNo}
			</td>
			<td ${tdclass} >
				${orderNo}
			</td>
			<td align="right"  ${tdclass} >
				${arInvoicePayment.symbol}${totalAmount}
			</td>
			<td align="right"   ${tdclass} >
				${arInvoicePayment.symbol}${paidAmount}
			</td>
			<td align="right"  ${tdclass} >
				${arInvoicePayment.symbol}${discount}
			</td>
			<td align="right"  ${tdclass} >
				${arInvoicePayment.symbol}${badDebt}
			</td>
			<td align="right" ${tdclass}  >
				${arInvoicePayment.symbol}${balance}
			</td>
			<td align="right" ${tdclass}   >
				${currency}
			</td>
		</tr>
		<c:set var="rowcount" value="${rowcount+1}"></c:set>
	</s:iterator>
</tbody>
 