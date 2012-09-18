<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript"
	src="${global_js_url}SpryTabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" language="javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js"
	type="text/javascript"></script>
<script type="text/javascript" language="javascript"
	src="${global_js_url}searchattr.js"></script>
<script src="${global_js_url}searchForm.js" type="text/javascript"></script>
<script src="${global_js_url}initlefttop.js" type="text/javascript"></script>
<script>


var specialAttr=new Array();
var countryList = new Array();
<s:iterator value="countryList" status="itStatus">
countryList[${itStatus.count-1}] = new Array('${countryCode}','${name}','${stateCount}');
</s:iterator>
specialAttr[0] = new Array(49,'SELECT',countryList);

var statusList = new Array();
statusList[0] = new Array('ACTIVE','ACTIVE');
statusList[1] = new Array('INACTIVE','INACTIVE');
statusList[2] = new Array('SUSPENDED','SUSPENDED');
specialAttr[1] = new Array(11,'SELECT',statusList);

var search_attr = new Array();
<s:iterator value="attrList" status="itStatus">
search_attr[${itStatus.count-1}] = new Array('${attributeId}','${name}','${type}');
<c:if test='${not empty dropDownDTOs}'>
	var tmpArr = new Array();
	<s:iterator value="dropDownDTOs" status="itStatus">
	tmpArr[${itStatus.count-1}] = new Array('${id}','${name}');
	</s:iterator>
	var tmpLen = specialAttr.length;
	specialAttr[tmpLen] = new Array('${attributeId}', 'SELECT', tmpArr);
</c:if>
</s:iterator>

var custSearchAttr=new SearchAttribute(search_attr,'mySearchTable1','custSearchAttr');
custSearchAttr.setSpecialAttr(specialAttr);
custSearchAttr.setDependingAttr(49,50,'my_search!stateListByCountry.action?countryCode=');

var custMySearch=new MySearch(custSearchAttr,'mySearchSel');
custMySearch.getMySearch('my_search!mySearchList.action?searchType=OrderSearch');

// search in advanced search tab
var custSearchAttrAdv = new SearchAttribute(search_attr,'advSearchTable',"custSearchAttrAdv");
custSearchAttrAdv.setSpecialAttr(specialAttr);
custSearchAttrAdv.setDependingAttr(49,50,'my_search!stateListByCountry.action?countryCode=');

</script>

<script language="javascript">
function saveAdvcSrch(searchtableid, searchname){
	if($("#"+searchname).val() == ""){
		alert("Please enter the search template name you want to save.");
		return;
	}
	
	// check if the search name is duplicated;
	if(custMySearch.hasMysearch(searchname) == true){
		alert("The search template name is duplicated . Please enter new one.");
		return false;
	}
	
	// check if the user's search count is exceeded;
	if(custMySearch.checkMysearchMaxNum() == false){
		alert("You can only save "+custMySearch.mySrchMaxCount+" searches!!\n");
		return false;
	}

	$.ajax({
		type: "POST",
		url: "my_search!saveMysrch.action?searchType=OrderSearch",
		data: $('#'+searchtableid).serialize(),
		success: function(msg){
			alert( msg );	
			//refresh my search drop down list
			custMySearch.getMySearch('my_search!mySearchList.action?searchType=OrderSearch');
		},
		error: function (msg){
			alert("Failed to save your search template.");
		}
	});

	return false;
}
function saveMySrch(searchtableid, searchname)
{
	
	var searchId = $("#"+searchname).val();
	var realSearchname = $('#'+searchname+' option:selected').text();
	if(realSearchname == ""){
		alert("Please enter the search template name you want to save.");
		return;
	}
	$.ajax({
		type: "POST",
		url: "my_search!saveMysrch.action?searchType=OrderSearch&mySrchName="+realSearchname,
		data: $('#'+searchtableid).serialize(),
		success: function(msg){
			//refresh my search drop down list
			custMySearch.selectedIndex = searchId;
			custMySearch.getMySearch('my_search!mySearchList.action?searchType=OrderSearch');
			alert( msg );
		},
		error: function (msg){
			alert("Failed to save your search template.");
		}
	});

	return false;
}

function checkStr(){
	var quoteNo=document.getElementById("filterOrderNo").value.replace(/(^\s*)|(\s*$)/g, "");
	var custNo=document.getElementById("filterCustNo").value.replace(/(^\s*)|(\s*$)/g, "");
	var salesContact=document.getElementById("filterSalesContact").value.replace(/(^\s*)|(\s*$)/g, "");
	var form = document.getElementById("searchForm");
	document.getElementById("filterOrderNo").value=quoteNo;
	document.getElementById("filterCustNo").value=custNo;
	document.getElementById("filterSalesContact").value=salesContact;
	form.submit();
}

</script>
<script language="javascript">
// delete order mysearch 
function delCustMysrch(custmysrchid){
	var mysrchIndex = $('#'+custmysrchid).attr('value');
	var custmysrchvalue = custMySearch.my_search_list[mysrchIndex].searchId;
	
	$.ajax({
		type: "POST",
		url: "my_search!delMysrch.action",
		data: 'mySrchId='+custmysrchvalue,
		success: function(msg){
			alert( msg );
			//refresh my search drop down list
			custMySearch.getMySearch('my_search!mySearchList.action?searchType=OrderSearch');
		},
		error: function(msg){
			alert("Failed to remove your search template.");
		}
	});
	
	return false;
}

function toggleShowMore_img(obj,divID){
		var oId = document.getElementById(divID);
		if (obj.src.indexOf('arrow1.jpg') > 0){
			obj.src="${global_url}images/arrow.jpg";
			oId.style.display = "none"; 
		}else{
			obj.src="${global_url}images/arrow1.jpg";
			oId.style.display = "block"; 
		}
}
</script>
</head>
<body class="content" style="background-image: none;"
	onload="rtnPreLeftTop('Order Processing');">
	<div class="scm">
		<div class="title_content">
			<div
				style="padding-left: 20px; color: #5579A6; vertical-align: middle;">
				<img src="${global_url}images/arrow1.jpg"
					style="width: 16px; height: 17px; vertical-align: middle;"
					onclick="toggleShowMore_img(this,'search_box1');" />&nbsp;Process
				Order
			</div>
		</div>

		<div class="search_box" id="search_box1" style="display: block;">
			<div id="TabbedPanels1" class="TabbedPanels">
				<ul class="TabbedPanelsTabGroup">
					<li class="TabbedPanelsTab TabbedPanelsTabSelected" tabindex="0">Search</li>
					<li class="TabbedPanelsTab" tabindex="1">Advanced Search</li>
					<li class="TabbedPanelsTab" tabindex="2">My Search</li>
				</ul>

				<div class="TabbedPanelsContentGroup">

					<div style="display: block;"
						class="TabbedPanelsContent TabbedPanelsContentVisible">
						<form method="get" action="order/order_search.action"
							target="srchCustAct_iframe" id="searchForm">
							<input type="hidden" name="search_type" value="search" /> <input
								type="hidden" name="searchType" value="OrderSearch"
								id="searchType" />
							<table border="0">
								<tr>
									<td align="right">Order No</td>
									<td width="134"><input name="filter_EQI_orderNo"
										id="filterOrderNo" class="NFText" size="10" type="text" /></td>
									<td align="right">Customer No</td>
									<td width="124"><input name="filter_EQI_custNo"
										id="filterCustNo" class="NFText" size="20" type="text" /></td>
									<td align="right">Sales Contact</td>
									<td width="177"><input name="filter_LIKES_salesContact"
										id="filterSalesContact" class="NFText" size="25" type="text" />
									</td>
								</tr>
								<tr>
									<td>Internal Customer</td>
									<td><select style="width: 134px;" name="internalCustomer">
											<option value=""></option>
											<option value="1">GenScript USA</option>
									</select></td>
									<td colspan="4">&nbsp;</td>
								</tr>
							</table>
							<table width="725">
								<tr>
									<td height="26">&nbsp;</td>
								</tr>
							</table>

							<table width="725">
								<tr>
									<th><input type="button" value="Search"
										class="search_input" onclick="checkStr();" /></th>
								</tr>
							</table>

						</form>
					</div>

					<div style="display: none" class="TabbedPanelsContent">
						<form method="post" id="advancedSearchForm"
							action="order_search.action" _action="order_search.action"
							target="srchCustAct_iframe">
							<input type="hidden" name="search_type" value="ad_search" />
							<table border="0" id="advSearchTable">
								<tr>
									<td width="145">Attributes</td>
									<td width="145">Operator</td>
									<td width="145">Value A</td>
									<td width="145">Value B</td>
									<td width="230"></td>
								</tr>
							</table>
							<table width="480">
								<tr>
									<td align="center">&nbsp;&nbsp;<input type="button"
										onclick="custSearchAttrAdv.add();" value="Add Criteria" /></td>
									<td rowspan="4" align="right">Save as My Search <input
										name="mySrchName" id="mySrchName" type="text" class="NFText"
										size="10" /> <input type="button" name="Submit4" value="Save"
										onclick="return saveAdvcSrch('advancedSearchForm', 'mySrchName');" />
									</td>
								</tr>
							</table>

							<table width="725">
								<tr>
									<th><input type="button" id="advancedSearch"
										value="Search" class="search_input" /></th>
								</tr>
							</table>
						</form>
					</div>

					<div style="display: none" class="TabbedPanelsContent">
						<form method="post" id="mySearchForm" action="order_search.action"
							_action="order_search.action" target="srchCustAct_iframe">
							<input type="hidden" name="search_type" value="my_search" />
							<table width="725">
								<tr>
									<td align="right">My Search</td>
									<td align="left"><select name="mysearch_sel"
										id="mySearchSel"
										onchange="custMySearch.showMySearch(this.value);"></select> <input
										type="button" name="Submit5" value="Save"
										onclick="return saveMySrch('mySearchForm', 'mySearchSel');" />
										<input type="submit" name="Submit192" value="Delete"
										onclick="return delCustMysrch('mySearchSel')" /></td>
								</tr>
							</table>

							<table border="0" id="mySearchTable1" style="margin-left: 70px;">
								<tr>
									<td width="145">Attributes</td>
									<td width="145">Operator</td>
									<td width="145">Value A</td>
									<td width="145">Value B</td>
									<td width="250">&nbsp;</td>
								</tr>
							</table>
							<table width="480" style="margin-left: 70px;">
								<tr>
									<td align="left"><input type="button"
										onclick="custSearchAttr.addmy();" value="Add Criteria" /></td>
									<td align="right">&nbsp;</td>
								</tr>
							</table>
							<table width="725">
								<tr>
									<th><input type="button" name="mySearch" id="mySearch"
										value="Search" class="search_input" /></th>
									<th><input type="button" name="downloadOligo" id="downloadOligo"
										value="Download Oligo" class="search_input" /></th>
								</tr>
							</table>
						</form>
					</div>

				</div>

			</div>
		</div>
		<div class="input_box">
			<div class="content_box">
				<iframe id="srchCustAct_iframe" name="srchCustAct_iframe"
					src="order/order_search.action?searchType=OrderSearch" width="100%"
					height="630" frameborder="0" scrolling="no"></iframe>
			</div>
		</div>
	</div>
	<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
custSearchAttrAdv.add();
</script>
</body>
</html>