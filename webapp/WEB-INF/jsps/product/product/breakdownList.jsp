<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.tr_hover {
	background-color: #D1F1FA;
}

.tr_click {
	background-color: #84E5FF;
}

.tr_del {
	background-color: #E6E6E6;
}

.tr_even {
	background-color: #e3edf9;
}
</style>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}scm/pdtServ_seq.js"></script>
<script>
	var tableId = "compositeListTable";
	var pdtId = parent.$("#psId").val();
	$(document).ready(function() {
		sortTR("compositeListTable");
		$('#compositeListTable').find('tr:odd').addClass("list_tr2");
		var a = document.getElementsByName("cmpsId");
		for ( var i = 0; i < a.length; i++)
			a[i].checked = false;
	});

	function sortTR(tableId) {
		var tablestr = "";
		var table = $('#' + tableId);
		var trlen = table.children().children("tr").length;
		for ( var i = 0; i < trlen; i++) {
			var tempval = $(table.children().children("tr")[0]).find(
					"[span[id^='seqNo_']").html();
			var temptr = $(table.children().children("tr")[0]);
			for ( var j = 0; j < trlen - i; j++) {
				var temp;
				temp = $(table.children().children("tr")[j]);
				if (temp != null) {
					if (tempval > temp.find("[span[id^='seqNo_']").html()) {
						temptr = temp;
						tempval = temp.find("[span[id^='seqNo_']").html();
					}
				} else {
					temptr = temp;
				}
			}

			tablestr += "<tr>" + temptr.remove().html() + "</tr>";
		}
		table.html(tablestr);
	}

	function cc(e) {
		var a = document.getElementsByName("cmpsId");
		for ( var i = 0; i < a.length; i++)
			a[i].checked = e.checked;
	}

	function get_checked_str(name) {
		var a = document.getElementsByName(name);
		var str = '';
		for ( var i = 0; i < a.length; i++) {
			if (a[i].checked) {
				str += a[i].value + ',';
			}
		}
		if (str) {
			return str.substring(0, str.length - 1);
		}
		return null;
	}
	function addCompositeDialog() {
		parent
				.$('#compositeDialog')
				.dialog(
						{
							autoOpen : false,
							height : 400,
							width : 720,
							modal : true,
							bgiframe : true,
							buttons : {},
							open : function() {
								var psId = parent.$("#psId").val();
								arrCatNo = document
										.getElementsByName("cmpsCatNo");
								lenCatNo = arrCatNo.length;
								var noListStr = "";
								for ( var i = 0; i < lenCatNo; i++) {
									noListStr += arrCatNo[i].value + ",";
								}
								if (noListStr) {
									noListStr = noListStr.substring(0,
											noListStr.length - 1);
								}
								var htmlStr = '<iframe src="product/component!input.action?psId='
										+ psId
										+ '&catNo=&name=&noList='
										+ noListStr
										+ '&sessionPSID=${sessionPSID}" height="340" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';

								parent.$('#compositeDialog').html(htmlStr);
							}
						});
		parent.$('#compositeDialog').dialog('open');
	}

	$(function() {
		$('#deleteItem').click(function() {
			var itemStr = get_checked_str("cmpsId");
			if (confirm("Are you sure to delete?")) {
				deleteComponent(itemStr);
			}
		});
	});
	function deleteComponent(itemStr) {
		$
				.ajax({
					type : "POST",
					url : "product/component!delete.action",
					data : "itemId=" + itemStr
							+ "&psId=${psId}&sessionPSID=${sessionPSID}",
					success : function(msg) {
						location.reload();
					},
					/*
					                            trObj = $("#compositeListTable").find("tr");
					                            trObj.each(function(){
					                                    if($(this).find(":checkbox").attr("checked") == true){
					                                            $(this).remove();
					                                    }
					                            });
					                            seq = 1;
					                            trObj = $("#compositeListTable").find("tr");
					                            trObj.each(function(){
					                                    $(this).find("span[id^='seqNo_']").html(seq);
					                                    seq++;
					                            });*/

					error : function(msg) {
						alert("System error! Please contact system administrator for help.");
					}
				});
	}
</script>
</head>

<body>
	<form id="compositeForm">
		<table width="970" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="35" valign="top" class="blue_price"><div
						align="center">Composite Item Components List</div>
				</td>
			</tr>
			<tr>
				<td><table width="940" border="0" cellpadding="0"
						cellspacing="0" class="list_table">
						<tr>
							<th width="66">
								<div class="quote_dele">
									<input name="cmpsCheck" type="checkbox" onclick="cc(this)" />
									<img src="images/file_delete.gif" id="deleteItem" alt="Delete"
										width="16" height="16" border="0" />
								</div>
								<div id="upTableTd" class="down_up" style="margin: 2px 0;">
									<img src="images/up.jpg" width="8" height="8" title="up"
										style="cursor: pointer" />
								</div>
								<div id="downTableTd" class="down_up2">
									<img src="images/down.jpg" width="8" height="7" title="down"
										style="cursor: pointer" />
								</div></th>
							<th width="60">Seq No</th>
							<th width="70">Catalog No</th>
							<th>Item</th>
							<th>Specification</th>
							<th width="70">Quantity</th>
							<th width="120">Lead Time( Days)</th>
							<th width="70">Price</th>
						</tr>
					</table>
					<div class="frame_pr">
						<table width="940" border="0" cellpadding="0" cellspacing="0"
							class="list_table3" id="compositeListTable">
							<s:iterator value="breakdownList" status="breakdownList">
								<tr>
									<td width="66"><input type="checkbox" name="cmpsId"
										id="cmpsId" value="${key}" />
									</td>
									<td width="60"><span
										id="seqNo_<s:property value="value.id"/>"><s:property
												value="value.listSeq" />
									</span>
									</td>
									<td width="70"><span
										id="catalogNo_<s:property value="value.id"/>"><s:property
												value="value.cpntCatalogNo" />
									</span>&nbsp;<input type="hidden" name="cmpsCatNo"
										value="<s:property value="value.cpntCatalogNo"/>" />
									</td>
									<td><span id="item_<s:property value="value.id"/>"><s:property
												value="value.item" />
									</span>
									</td>
									<td><span id="specification_<s:property value="value.id"/>"><s:property
												value="value.specification" />
									</span>
									</td>
									<td width="70"><span
										id="quantity_<s:property value="value.id"/>"><s:property
												value="value.quantity" />
									</span>&nbsp;</td>
									<td width="120"><span
										id="leadTime_<s:property value="value.id"/>"><s:property
												value="value.leadTime" />
									</span>&nbsp;</td>
									<td width="71"><span
										id="price_<s:property value="value.id"/>"><s:property
												value="value.symbol" />
											<s:property value="value.price" />
									</span>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div></td>
			</tr>
			<tr>
				<td><div align="center">
						<input name="addAct" type="button" class="style_botton"
							value="Add" onclick="addCompositeDialog()" />
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
