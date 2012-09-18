<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Gift Card Process</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script src="${global_js_url}table.js" type="text/javascript"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}util/util.js"></script>
<script language="JavaScript" type="text/javascript">
	$(document).ready(function() {
		$('tr:even >td').addClass('list_td2');
	});
</script>
</head>

<body class="content" style="background-image: none;">
	<div id="frame12" style="display: none;" class="hidlayer1">
		<iframe id="hidkuan" name="hidkuan" src="" width="668" height="425"
			frameborder="0" allowtransparency="true"></iframe>
	</div>

	<div class="scm">
		<div class="title_content">
			<div class="title_new">
				<a href="javascript:void(0);"
					onclick="toggleShowMorea('total_title');" id="total_titleItem"><img
					src="${global_url}images/arrow1.jpg" /> </a>&nbsp;Available Cards
				Sending List
			</div>
		</div>
		<div class="search_box" id="total_title">
			<div class="single_search">
				<div id="paramHiddenSources" style="display: block">
					<form action="${ctx }/customer/gift_card!sendList.action">
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="200">Customer Number</th>
								<td><input name="filter_LIKES_custNo" type="text"
									class="NFText" size="20"
									value="${param['filter_LIKES_custNo']}" />
								</td>
								<td><input id="paramSearch" type="submit"
									name="paramSearch" value="Search" class="search_input" />
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div class="input_box" id="table_usa">
			<table width="1010" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<div style="margin-right: 17px;">
							<table width="993" border="0" cellspacing="0" cellpadding="0"
								class="list_table">
								<tr>
									<th width="152">Customer No</th>
									<th width="209">Points</th>
									<th width="187">Gift Card Value</th>
								<!-- 	<th width="187">Card</th> -->
									<th width="187"  align="center">Order No</th>
									<th>Button</th>
								</tr>
							</table>
						</div></td>
				</tr>
				<tr>
					<td>
						<div class="list_box">
							<table width="993" border="0" cellspacing="0" cellpadding="0"
								class="list_table2">
								<s:iterator value="cphpage.result">
									<tr>
										<td width="152" align="center">${custNo }</td>
										<td width="209" align="center">${points }</td>
										<td width="187" align="center">${giftCardValue }</td>
										<%-- <td width="187">${cardNo }</td> --%>
										<td width="187"  align="center">${orderNo }</td>
										<td align="center"><a href="javascript:void(0)"
											onclick="window.openiframe('customer/gift_card!SelectCardsByValue.action?orderNo=${orderNo }&Sendid=${id }&CustNo=${custNo }&CardValue=${giftCardValue }','690','500')">
												<input type="button" name="getCard" value="send" /> </a></td>
									</tr>
								</s:iterator>
							</table>
						</div></td>
				</tr>

				<tr>
					<td>
						<div class="grayr">
							<jsp:include page="/common/db_pager.jsp">
								<jsp:param value="${ctx }/customer/gift_card!sendList.action"
									name="moduleURL" />
							</jsp:include>
						</div></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>

