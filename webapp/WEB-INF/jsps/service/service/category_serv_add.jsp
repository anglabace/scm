<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
<style type="text/css">
<!--
body {
	width: 570px;
	margin: 5px auto;
}
-->
</style>
<script language="javascript">
	function a(obj) {
		var selectedvalue = obj.value;
		if (selectedvalue == '') {
			selectedvalue = '-1';
		}
		for (i = 1; i <= 8; i++) {
			var divobj = document.getElementById('tb' + i);
			if (divobj) {
				if (parseInt(selectedvalue) == i) {
					divobj.style.display = 'block';
				} else {
					divobj.style.display = 'none';
				}
			}
		}
	}

	function get_checked_str2(name) {
		var a = document.getElementsByName(name);
		var str = '';
		if (a.length == 0) {
			alert("Please select one item to continue your operation.");
			return;
		}
		var symbol = "";
		var url = "serv/serv!addServiceToCategory.action";
		var ids = "";
		for ( var i = 0; i < a.length; i++) {
			if (a[i].checked) {
				var id = a[i].value;
				if (id != null) {
					ids += id + ",";
					symbol += $("#symbol_" + id).val() + ",";
				}
			}
		}
		var idss = ids.substring(0, ids.length - 1);
		url += "?serviceKey=" + idss
				+ "&sessionCategoryId=${sessionCategoryId}";
		return url;
	}
	/*     function getBack(namew) {
	        var htmlStr = "";
	        var a = document.getElementsByName(namew);
	        for (var i = 0; i < a.length; i++) {
	            if (a[i].checked) {
	                var id = a[i].value;
	                var catalogNo = $("#catalogNo_" + a[i].value).val();
	                var type = $("#type_" + a[i].value).val();
	                var name = $("#name_" + a[i].value).val();
	                var shortDesc = $("#shortDesc_" + a[i].value).val();
	                var status1 = $("#status1_" + a[i].value).val();
	                var size = $("#size_" + a[i].value).val();
	                var modifyDate = $("#modifyDate_" + a[i].value).val();
	                var creationDate = $("#creationDate_" + a[i].value).val();
	                var symbol = $("#symbol_" + a[i].value).val();
	                var ctgId = parent.$("#ctgId").val();
	                htmlStr += '<tr id="del_'+a[i].value+'"><td width="56"><input type="checkbox" name="ids" value="'+a[i].value+'" />&nbsp;</td><td width="86" style="word-break:break-all;word-wrap:break-word;width:86">'+catalogNo+'&nbsp;</td> <td width="85">'+type+'&nbsp;</td> <td width="134" style="word-break:break-all;word-wrap:break-word;width:134">'+name+'&nbsp;</td> <td width="240" style="word-break:break-all;word-wrap:break-word;width:240">'+shortDesc+'&nbsp;</td><td width="81">'+status1+'&nbsp;</td><td width="72">'+size+'&nbsp;</td><td width="72">'+modifyDate+'&nbsp;</td><td>'+creationDate+'&nbsp;</td></tr>';
	            }
	        }
	        parent.$("#catPdtServList_iframe").contents().find('#catPdtServList').prepend(htmlStr);
	        parent.$('#catPdtServAddDialog').dialog('close');
	        parent.$('#catPdtServAddDialog').dialog('destory');
	    } */
	$(function() {
		categoryType = parent.$("#categoryType").val();
		$('tr:even >td').addClass('list_td2');
		var sessionCategoryId = $('#sessionCategoryId').val(); 
	 
		$("[name='selectTrigger']")
				.click( 
						function() {
							var url2 = get_checked_str2("item_add"); 
							if (url2 == null)
								return;
							//alert(url2);
							$.ajax({
										type : "POST",
										dataType : "json",
										url : url2,
									    success : function(data) { 
												 
												if (data.message == "ok") {
													parent
															.$(
																	'#catPdtServAddDialog')
															.dialog('close');
													parent
															.$(
																	'#catPdtServAddDialog')
															.dialog('destory');
													alert("Success.");
													parent.window.location.href = "serv/service_category!input.action?categoryId="
															+ sessionCategoryId
															+ "&callBackName=categoryCreationForm&operation_method=edit&dodo=second";
												} else if (data.message == "error") {
													alert("The service line has already contains this service ，please choose another one.");
												} 
											},
										error : function(msg) {
											alert("System error! Please contact system administrator for help.");
										}
									});
							$('[id^="standardPrice_"]').focus(
									function() {
										$(this).parent("td").parent("tr").find(
												'[id="item_add"]:radio').attr(
												"checked", true);
									});
							$('[id^="limitPrice_"]').focus(
									function() {
										$(this).parent("td").parent("tr").find(
												'[id="item_add"]:radio').attr(
												"checked", true);
									});

						});
	});
	function propNameChange() {
		var p = $("#propName option:selected").val();
		if (p == "checkType") {

			$("#type1Select").empty();
			var html = '<select name="srchOperator" style="width:100px"><option value="EQ" selected="selected">=</option></select>';
			$("#type1Select").append(html);
			$("#type1Div").empty();
			$("#type1Div").append($("#divSelected").html());

		} else {
			$("#type1Select").empty();

			$("#type1Select").append($("#typeSelect").html());
			$("#type1Div").empty();
			var html = '<input type="text" name="propValue" class="NFText" size="14" value="" />';
			$("#type1Div").append(html);
		}
	}
</script>
</head>
<body>
	<form method="get" action="">
		<table width="570" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>&nbsp;</td>
				<td>Lookup On</td>
				<td>Condition</td>
				<td>Value</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>Services Item</td>
				<td><select id="propName" name="propName" style="width: 100px"
					onchange="propNameChange()">
						<s:if test="propName==\"name\"">
							<option value="name" selected="selected">Services Name</option>
							<option value="catalogNo">Catalog No</option>
							<option value="checkType">Type</option>
						</s:if>
						<s:elseif test="propName==\"type\"">
							<option value="name">Services Name</option>
							<option value="catalogNo">Catalog No</option>
							<option value="checkType" selected="selected">Type</option>
						</s:elseif>
						<s:else>
							<option value="name">Services Name</option>
							<option value="catalogNo" selected="selected">Catalog No</option>
							<option value="checkType">Type</option>
						</s:else>
				</select></td>
				<td><s:if test="propName==\"type\"">
						<div id="type1Select">
							<select name="srchOperator" style="width: 100px">
								<option value="EQ" selected="selected">=</option>
							</select>
						</div>
					</s:if> <s:else>
						<div id="type1Select">
							<select name="srchOperator" style="width: 100px">
								<s:if test="srchOperator==\"EQ\"">
									<option value="EQ" selected="selected">=</option>
								</s:if>
								<s:else>
									<option value="EQ">=</option>
								</s:else>
								<s:if test="srchOperator==\"GT\"">
									<option value="GT" selected="selected">&gt;</option>
								</s:if>
								<s:else>
									<option value="GT">&gt;</option>
								</s:else>
								<s:if test="srchOperator==\"GE\"">
									<option value="GE" selected="selected">&gt;=</option>
								</s:if>
								<s:else>
									<option value="GE">&gt;=</option>
								</s:else>
								<s:if test="srchOperator==\"LT\"">
									<option value="LT" selected="selected">&lt;</option>
								</s:if>
								<s:else>
									<option value="LT">&lt;</option>
								</s:else>
								<s:if test="srchOperator==\"LE\"">
									<option value="LE" selected="selected">&lt;=</option>
								</s:if>
								<s:else>
									<option value="LE">&lt;=</option>
								</s:else>
								<s:if test="srchOperator==\"NE\"">
									<option value="NE" selected="selected">!=</option>
								</s:if>
								<s:else>
									<option value="NE">!=</option>
								</s:else>
								<s:if test="srchOperator==\"LIKE\"">
									<option value="LIKE" selected="selected">LIKE</option>
								</s:if>
								<s:else>
									<option value="LIKE">LIKE</option>
								</s:else>
							</select>
						</div>
					</s:else> <input type="hidden" name="type" value="Services" /></td>
				<td><s:if test="propName==\"type\"">
						<div id="type1Div">
							<s:select id="propValue" name="propValue" list="dropDownDTO"
								listKey="name" listValue="name" value="" cssStyle="width:125px;"
								headerKey="" headerValue=""></s:select>
						</div>

					</s:if> <s:else>
						<div id="type1Div">
							<input type="text" name="propValue" class="NFText" size="14"
								value="${propValue }" />
						</div>
					</s:else></td>
				<td><input type="submit" class="style_botton" value="Search" />
				</td>
			</tr>
		</table>
		<input type="hidden" id="sessionCategoryId" name="sessionCategoryId"
			value="${sessionCategoryId}" />
	</form>
	<table width="570" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="4" style="padding-top: 10px;">
				<table width="550" border="0" cellpadding="0" cellspacing="0"
					class="list_table">
					<tr>
						<th width="28"></th>
						<th width="165">Ctlg No</th>
						<th width="160">Name</th>
						<th width="125">Type</th>
						<th>Size</th>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td colspan="4" style="padding-bottom: 20px;">
				<div style="width: 567px; height: 150px; overflow: scroll;">
					<table width="550" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<s:iterator value="pageBean.result">
							<tr>
								<td width="28">
									<div align="center">
										<input type="checkbox" value="${serviceId}" name="item_add"
											id="item_add" />
									</div></td>
								<td width="165">
									<div align="left">${catalogNo}&nbsp;</div></td>
								<td width="160">${name}&nbsp;</td>
								<td width="125">${type}&nbsp;</td>
								<td align="right">&nbsp;${size}${uom } <input type="hidden"
									id="catalogNo_${serviceId}" value="${catalogNo}" /> <input
									type="hidden" id="type_${serviceId}" value="${type}" /> <input
									type="hidden" id="name_${serviceId}" value="${name}" /> <input
									type="hidden" id="shortDesc_${serviceId}"
									value="${description}" /> <input type="hidden"
									id="status1_${serviceId}" value="${status}" /> <input
									type="hidden" id="size_${serviceId}" value="${size}" /> <input
									type="hidden" id="symbol_${serviceId}" value="${symbol}" /> <input
									type="hidden" id="modifyDate_${serviceId}"
									value="<s:date name=" modifyDate"
                                format="yyyy-MM-dd" />" />
									<input type="hidden" id="creationDate_${serviceId}"
									value="<s:date name=" creationDate"
                                format="yyyy-MM-dd" />" />
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<div class="grayr">
					<jsp:include page="/common/db_pager.jsp">
						<jsp:param value="${ctx }/serv/serv!catServAddAct.action"
							name="moduleURL" />
					</jsp:include>
				</div></td>
		</tr>
		<tr>
			<td colspan="4">
				<div align="center">
					<input type="hidden" id="catId" value="${catId}" /> <input
						type="button" name="selectTrigger" value="Select"
						class="style_botton" /> <input type="button" value="Close"
						class="style_botton"
						onclick="parent.$('#catPdtServAddDialog').dialog('close');" />
				</div></td>
		</tr>
	</table>
	<div id="divSelected" style="display: none">
		<s:select id="propValue" name="propValue" list="dropDownDTO"
			listKey="name" listValue="name" value="" cssStyle="width:125px;"
			headerKey="" headerValue=""></s:select>
	</div>
	<div id="typeSelect" style="display: none">
		<select name="srchOperator" style="width: 100px">
			<s:if test="srchOperator==\"EQ\"">
				<option value="EQ" selected="selected">=</option>
			</s:if>
			<s:else>
				<option value="EQ">=</option>
			</s:else>
			<s:if test="srchOperator==\"GT\"">
				<option value="GT" selected="selected">&gt;</option>
			</s:if>
			<s:else>
				<option value="GT">&gt;</option>
			</s:else>
			<s:if test="srchOperator==\"GE\"">
				<option value="GE" selected="selected">&gt;=</option>
			</s:if>
			<s:else>
				<option value="GE">&gt;=</option>
			</s:else>
			<s:if test="srchOperator==\"LT\"">
				<option value="LT" selected="selected">&lt;</option>
			</s:if>
			<s:else>
				<option value="LT">&lt;</option>
			</s:else>
			<s:if test="srchOperator==\"LE\"">
				<option value="LE" selected="selected">&lt;=</option>
			</s:if>
			<s:else>
				<option value="LE">&lt;=</option>
			</s:else>
			<s:if test="srchOperator==\"NE\"">
				<option value="NE" selected="selected">!=</option>
			</s:if>
			<s:else>
				<option value="NE">!=</option>
			</s:else>
			<s:if test="srchOperator==\"LIKE\"">
				<option value="LIKE" selected="selected">LIKE</option>
			</s:if>
			<s:else>
				<option value="LIKE">LIKE</option>
			</s:else>
		</select>
	</div>
</body>
</html>