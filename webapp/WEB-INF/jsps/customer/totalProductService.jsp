
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="right">Total Products <c:choose>
			<c:when test="${codeType =='order'}">Ordered</c:when>
			<c:otherwise>Quoted</c:otherwise>
		</c:choose></td>
		<td><c:choose>
			<c:when test="${codeType =='order'}">
				<input name="totalProduct" id="totalProduct" type="text"
					class="NFText"
					value=<c:if test="${empty  orderProductTotal}">0</c:if>
					<c:if test="${!empty orderProductTotal}" >${orderProductTotal}</c:if> />
			</c:when>
			<c:otherwise>
				<input name="totalProduct" id="totalProduct" type="text"
					class="NFText"
					value=<c:if test="${empty  quoteProductTotal}">0</c:if>
					<c:if test="${!empty quoteProductTotal}" >${quoteProductTotal}</c:if> />
			</c:otherwise>
		</c:choose>
		<td align="right">Total Services <c:choose>
			<c:when test="${codeType =='order'}">Ordered</c:when>
			<c:otherwise>Quoted</c:otherwise>
		</c:choose></td>
		<td><c:choose>
			<c:when test="${codeType =='order'}">
			<input name="totalService" id="totalService" type="text"
			class="NFText"
			value=<c:if test="${empty  orderServiceTotal}">0</c:if>
			<c:if test="${!empty orderServiceTotal}" >${orderServiceTotal}</c:if> />
			</c:when>
			<c:otherwise>
			<input name="totalService" id="totalService" type="text"
			class="NFText"
			value=<c:if test="${empty  quoteServiceTotal}">0</c:if>
			<c:if test="${!empty quoteServiceTotal}" >${quoteServiceTotal}</c:if> />
			</c:otherwise>
		</c:choose>
		</td>
		<td><input type="button" name="createReport" class="style_botton2" id="createReport"
			value="Create Report" /></td>
	</tr>
</table>
<script language="javascript">
	$('#createReport')
			.click(
					function() {
						window.parent.$('#orderStatDialog').dialog({
							autoOpen : false,
							height : 665,
							width : 745,
							modal : true,
							bgiframe : true,
							buttons : {}
						});
						var custNo = "${custNo}";
						var codeType = "${codeType}";
						var status = $('#status').val();
						var toAmount = $('#toAmount').val();
						var fromAmount = $('#fromAmount').val();
						var toDate = $('#toDate').val();
						var fromDate = $('#fromDate').val();

						var url = "customer/cust_qu_order!showQuorderReport.action?codeType="
								+ codeType
								+ "&custNo="
								+ custNo
								+ "&status="
								+ status
								+ "&toAmount="
								+ toAmount
								+ "&fromAmount="
								+ fromAmount
								+ "&toDate="
								+ toDate + "&fromDate=" + fromDate;
						var htmlStr = '<iframe src="'+url+'" height="615" width="730" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
						window.parent.$('#orderStatDialog').html(htmlStr);
						window.parent.$('#orderStatDialog').dialog('open');
					});
</script>