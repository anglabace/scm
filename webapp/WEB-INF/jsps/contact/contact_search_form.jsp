<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Contact Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" language="javascript"
	src="${global_js_url}SpryTabbedPanels.js"></script>
<script type="text/javascript" language="javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>

<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>

<script type="text/javascript" language="javascript"
	src="${global_js_url}searchattr.js"></script>
<script src="${global_js_url}searchForm.js" type="text/javascript"></script>
<script>
$().ready(function() {
	$('.ui-datepicker').each(function() {
		$(this).datepicker({
			dateFormat : 'yy-mm-dd',
			changeMonth : true,
			changeYear : true
		});
	});
	$('#creationDate1').datepicker();
	$('#creationDate2').datepicker();
});
var specialAttr=new Array();
var countryList = new Array();
<s:iterator value="countryList" status="itStatus">
 countryList[${itStatus.count-1}] = new Array('${countryCode}','${name}','${stateCount}');
</s:iterator>
specialAttr[0] = new Array(16,'SELECT',countryList);

var statusList = new Array();
statusList[0] = new Array('ACTIVE','ACTIVE');
statusList[1] = new Array('INACTIVE','INACTIVE');
statusList[2] = new Array('SUSPENDED','SUSPENDED');
specialAttr[1] = new Array(20,'SELECT',statusList);

var search_attr = new Array();
<s:iterator value="attrList" status="itStatus">
search_attr[${itStatus.count-1}] = new Array('${attributeId}','${name}','${type}');
<c:if test='${not empty dropDownDTOs}'>
	var tmpArr = new Array();
	<s:iterator value="dropDownDTOs" status="itStatus">
	tmpArr[${itStatus.count-1}] = new Array(' ${id} ','${name} ');
	</s:iterator>
	var tmpLen = specialAttr.length;
	specialAttr[tmpLen] = new Array('${attributeId}', 'SELECT', tmpArr);
</c:if>
</s:iterator>
var search_attr = new Array();
<s:iterator value="attrList" status="itStatus">
search_attr[${itStatus.count-1}] = new Array('${attributeId}','${name}','${type}');
<c:if test='${not empty dropDownDTOs}'>
	var tmpArr = new Array();
	<s:iterator value="dropDownDTOs" status="itStatus">
	tmpArr[${itStatus.count-1}] = new Array(' ${id} ','${name} ');
	</s:iterator>
	var tmpLen = specialAttr.length;
	specialAttr[tmpLen] = new Array('${attributeId}', 'SELECT', tmpArr);
</c:if>
</s:iterator>

var custSearchAttr=new SearchAttribute(search_attr,'mySearchTable1','custSearchAttr');
custSearchAttr.setSpecialAttr(specialAttr);
custSearchAttr.setDependingAttr(16,15,'my_search!stateListByCountry.action?countryCode=');

var custMySearch=new MySearch(custSearchAttr,'mySearchSel');
custMySearch.getMySearch('my_search!mySearchList.action?searchType=ContactSearch');

// search in advanced search tab
var custSearchAttrAdv = new SearchAttribute(search_attr,'advSearchTable',"custSearchAttrAdv");
custSearchAttrAdv.setSpecialAttr(specialAttr);
custSearchAttrAdv.setDependingAttr(16,15,'my_search!stateListByCountry.action?countryCode=');

//Contact field map

</script>

<script type="text/javascript">
                        function checkNum(){
                            if(isNaN(document.searcheForm.filter_EQI_contactNo.value)){
                                alert(' failure !');
                               document.searcheForm.filter_EQI_contactNo.value="";
                               document.searcheForm.filter_EQI_contactNo.focus();
                                return false;
                            }
                              document.searcheForm.filter_EQI_contactNo.value =$.trim($("#contactNO").val());
                                document.searcheForm.filter_LIKES_firstName.value =$.trim($("#firstName").val());
                                 document.searcheForm.filter_LIKES_lastName.value =$.trim($("#lastName").val());
                               document.searcheForm.filter_LIKES_busEmail.value =$.trim($("#busEmail").val());
                                document.searcheForm.filter_LIKES_organizationName.value =$.trim($("#organizationName").val());
                            return true;
                        }

function saveAdvcSrch(searchtableid, searchname){
	// check if the search name is duplicated;
	var str = "'#"+searchname+"'";
	if($(str).val()=="") {
		alert("Please enter a search name.");
		return false;
	}
	if(custMySearch.hasMysearch(searchname) == true){
		alert("The search template name has been used already. Please enter new one.");
		return false;
	}
	
	// check if the user's search count is exceeded;
	if(custMySearch.checkMysearchMaxNum() == false){
		alert("You can only save "+custMySearch.mySrchMaxCount+" searches!!\n");
		return false;
	}

	$.ajax({
		type: "POST",
		url: "my_search!saveMysrch.action?searchType=ContactSearch",
		data: $('#'+searchtableid).serialize(),
		success: function(msg){
			alert( msg );
			//refresh my search drop down list
			custMySearch.getMySearch('my_search!mySearchList.action?searchType=ContactSearch');
		},
		error: function (msg){
			alert("Failed to save the search template. Please contact system administrator for help.");
		}
	});

	return false;
}
function saveMySrch(searchtableid, searchname)
{
	
	var searchId = $("#"+searchname).val();
	var realSearchname = $('#'+searchname+' option:selected').text();
	$.ajax({
		type: "POST",
		url: "my_search!saveMysrch.action?searchType=ContactSearch&mySrchName="+realSearchname,
		data: $('#'+searchtableid).serialize(),
		success: function(msg){
			//refresh my search drop down list
			custMySearch.selectedIndex = searchId;
			custMySearch.getMySearch('my_search!mySearchList.action?searchType=ContactSearch');
			alert( msg );
		},
		error: function (msg){
			alert("Failed to save the search template. Please contact system administrator for help.");
		}
	});

	return false;
}
</script>
<script language="javascript">
// delete contact mysearch 
function delCustMysrch(custmysrchid){
	var mysrchIndex = $('#'+custmysrchid).attr('value');
	var custmysrchvalue = custMySearch.my_search_list[mysrchIndex].searchId;
	
	$.ajax({
		type: "POST",
		url: "my_search!delMysrch.action?searchType=ContactSearch",
		data: 'mySrchId='+custmysrchvalue,
		success: function(msg){
			alert( msg );
			//refresh my search drop down list
			custMySearch.getMySearch('my_search!mySearchList.action?searchType=ContactSearch');
		},
		error: function(msg){
			alert("Failed to delete the search template. Please contact system administrator for help.");
		}
	});
	
	return false;
}

function new_contact(url, target)
{
	document.getElementById(target).src = url;
}

$(document).ready(function(){
	$("#combineDialog").dialog({
		autoOpen: false,
		height: 450,
		width: 780,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var url = "contact/contact/showCombineAct";
			var htmlStr = '<iframe src="'+url+'" height="400" width="740" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
			$('#combineDialog').html(htmlStr);
		}
	});
});
function toggleShowMore_img(obj,divID){
		var oId = document.getElementById(divID);
		if (obj.src.indexOf('arrow.jpg') > 0){
			obj.src="${global_url}images/title_bgewe.jpg";
			oId.style.display = "none"; 
		}else{
			obj.src="${global_url}images/arrow.jpg";
			oId.style.display = "block"; 
		}
}
</script>
</head>
<body class="content" style="background-image: none;">
	<div class="scm">
		<div class="title_content">
			<div
				style="padding-left: 20px; color: #5579A6; vertical-align: middle;">
				<img src="${global_url}images/arrow.jpg"
					style="width: 16px; height: 17px; vertical-align: middle;"
					onclick="toggleShowMore_img(this,'search_box1');" /> Contact
				Management
			</div>
		</div>
		<div class="search_box" id="search_box1" style="display: block;">
			<div id="TabbedPanels1" class="TabbedPanels">
				<ul class="TabbedPanelsTabGroup">
					<li class="TabbedPanelsTab TabbedPanelsTabSelected" tabindex="0">
						Search</li>
					<li class="TabbedPanelsTab" tabindex="1">Advanced Search</li>
					<li class="TabbedPanelsTab" tabindex="2">My Search</li>
				</ul>

				<div class="TabbedPanelsContentGroup">
					<div style="display: block;"
						class="TabbedPanelsContent TabbedPanelsContentVisible">
						<form method="get" action="contact/contact!list.action"
							name="searcheForm" target="srchCustAct_iframe" id="SearchForm"
							onsubmit="return checkNum();">
							<input type="hidden" name="search_type" value="search" /> <input
								type="hidden" name="searchType" value="ContactSearch"
								id="searchType" />
							<table border="0">
								<tr>
									<td>Contact No <input name="filter_EQI_contactNo"
										class="NFText" size="10" type="text" id="contactNO" /></td>
									<td>First Name <input name="filter_LIKES_firstName"
										class="NFText" size="12" type="text" id="firstName" /></td>
									<td>Last Name <input name="filter_LIKES_lastName"
										class="NFText" size="12" type="text" id="lastName" /></td>
									<td>Email <input name="filter_LIKES_busEmail"
										class="NFText" size="15" type="text" id="busEmail" /></td>
									<td>Organization <input
										name="filter_LIKES_organizationName" class="NFText" size="15"
										type="text" id="organizationName" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td>Start create date <input
										name="filter_GED_creationDate" type="text"  
										class="NFText ui-datepicker" size="15" readonly="readonly"
										id="creationDate1" />
									</td>
									<td>~</td>
									<td>End create date <input name="filter_LED_creationDate"  
										type="text" class="NFText ui-datepicker" size="15"
										readonly="readonly" id="creationDate2" />
									</td>
								</tr>
							</table>
							<table width="725">
								<tr>
									<td height="26">&nbsp;</td>
								</tr>
							</table>
							<table width="725">
								<tr>
									<td align="center"><input type="hidden"
										name="filter_EQS_status" value="ACTIVE" /> <input
										type="submit" value="Search" class="search_input" /> <a
										href="javascript:void(0);"
										onclick="new_contact('contact!edit.action', 'srchCustAct_iframe');">
											<input type="button" value="New Contact" class="search_input" />
									</a></td>
								</tr>
							</table>
						</form>
					</div>

					<div style="display: none" class="TabbedPanelsContent">
						<form method="post" id="advancedSearchForm"
							action="contact/contact!list.action"
							_action="contact/contact!list.action" target="srchCustAct_iframe">
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
									<td align="center">&nbsp;&nbsp; <input type="button"
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
									<td><input type="submit" id="advancedSearch"
										value="Search" class="search_input" /> <a
										href="javascript:void(0);"
										onclick="new_contact('contact!edit.action', 'srchCustAct_iframe');">
											<input type="button" value="New Contact" class="search_input" />
									</a></td>
								</tr>
							</table>
						</form>
					</div>

					<div style="display: none" class="TabbedPanelsContent">
						<form method="post" id="mySearchForm"
							action="contact/contact!list.action"
							_action="contact/contact!list.action" target="srchCustAct_iframe">
							<input type="hidden" name="search_type" value="my_search" />
							<table width="725">
								<tr>
									<td width="70px" align="right">My Search</td>
									<td align="left"><select name="mysearch_sel"
										id="mySearchSel"
										onchange="custMySearch.showMySearch(this.value);"></select> <input
										type="button" name="Submit5" value="Save"
										onclick="return saveMySrch('mySearchForm', 'mySearchSel');" />
										<input type="submit" name="Submit192" value="Delete"
										onclick="return delCustMysrch('mySearchSel')" /></td>
								</tr>
							</table>
							<table border=0 id="mySearchTable1" style="margin-left: 70px;">
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
									<td><input type="button" id="mySearch" value="Search"
										class="search_input" /> <a href="javascript:void(0);"
										onclick="new_contact('contact!edit.action', 'srchCustAct_iframe');">
											<input type="button" name="Submit20" value="New Contact"
											class="search_input" /> </a></td>
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
					src="contact!list.action" width="100%" height="630" frameborder="0"
					scrolling="no"></iframe>
			</div>
		</div>
	</div>
	<div id="combineDialog" title="Combine Accounts"></div>
	<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
custSearchAttrAdv.add();

</script>
</body>
</html>