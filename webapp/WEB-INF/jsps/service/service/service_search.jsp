<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript" src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript" src="${global_js_url}jquery/jquery.js"></script> 
<script type="text/javascript" language="javascript" src="${global_js_url}searchattr.js"></script>
<script src="${global_js_url}util/util.js"
	type="text/javascript"></script>
<script>
var searchType = "ServiceSearch";

var search_attr = new Array();

var ServiceClassTypeList = new Array();
<s:iterator value ="ServiceClassTypeList" status ="itStatus">
ServiceClassTypeList[${itStatus.count-1}] = new Array('${name}', '${name}');
</s:iterator>
var specialAttr = new Array();

//specialAttr[0] = new Array(49,'SELECT',countryList);

var statusList = new Array();
statusList[0] = new Array('ACTIVE','ACTIVE');
statusList[1] = new Array('INACTIVE','INACTIVE');
statusList[2] = new Array('IN DEVELOP','IN DEVELOP');

//statusList[2] = new Array('SUSPENDED','SUSPENDED');
specialAttr[0] = new Array(90,'SELECT',statusList); 
specialAttr[1] = new Array(89, 'SELECT', ServiceClassTypeList);  
<s:iterator value="attrList" status="itStatus"> 
search_attr[${itStatus.count-1}] = new Array('${attributeId}','${name}','${type}');
<c:if test='${not empty dropDownDTOs}'>
var tmpArr = new Array();
<s:iterator value="dropDownDTOs" status="itStatus"> 
tmpArr[${itStatus.count-1}] = new Array('${name}','${name}');
</s:iterator>
var tmpLen = specialAttr.length;
specialAttr[tmpLen] = new Array('${attributeId}', 'SELECT', tmpArr);
</c:if>
</s:iterator>


var custSearchAttr=new SearchAttribute(search_attr,'mySearchTable1','custSearchAttr');
custSearchAttr.setSpecialAttr(specialAttr);
//custSearchAttr.setDependingAttr(16,15,'<!-- {$global_url} -->util/countryStateCity/getStateListByCountry?country=');

var custMySearch=new MySearch(custSearchAttr,'mySearchSel');
custMySearch.getMySearch('my_search!mySearchList.action?searchType=ServiceSearch');

// search in advanced search tab
var custSearchAttrAdv = new SearchAttribute(search_attr,'advSearchTable',"custSearchAttrAdv");
custSearchAttrAdv.setSpecialAttr(specialAttr);
//custSearchAttrAdv.setDependingAttr(16,15,'util/countryStateCity/getStateListByCountry?country=');

//add an attribute tr as default.

function saveAdvcSrch(searchtableid, searchname){
	var searchType = "ServiceSearch";
	// check if the search name is duplicated;
	var isDuplicated = false;
	if(custMySearch.hasMysearch(searchname) == true){
		isDuplicated = true;
		if(!confirm("Duplicated Mysearch Name!!!\nAre you sure to rewrite it?"))
		{
			return false;
		}
	}
	
	// check if the user's search count is exceeded;
	if(custMySearch.checkMysearchMaxNum() == false){
		if(isDuplicated == true && custMySearch.my_search_list.length <= custMySearch.mySrchMaxCount)
		{ }
		else
		{	
			alert("Sorry,You can only save "+custMySearch.mySrchMaxCount+" searches.");
			return false;
		}
	}
	$.ajax({
		type: "POST",
		url: "my_search!saveMysrch.action?searchType="+searchType,
		data: $('#'+searchtableid).serialize(),
		success: function(msg){
			if(msg == 'success')
			{
			}else if(msg != '')
			{
				alert(msg);
			}
			//refresh my search drop down list
			custMySearch.getMySearch('my_search!mySearchList.action?searchType='+searchType);
		},
		error: function (msg){
			alert("Failed to save the services search.Please contact system administrator for help.");
		}
	});

	return false;
}

function saveMySrch(searchtableid, searchname)
{
	var searchType = "ServiceSearch";
	var searchId = $("#"+searchname).val();
	var realSearchname = $('#'+searchname+' option:selected').text();
	$.ajax({
		type: "POST",
		url: "my_search!saveMysrch.action?mySrchName="+realSearchname+'&searchType='+searchType,
		data: $('#'+searchtableid).serialize(),
		success: function(msg){
			//refresh my search drop down list
			custMySearch.selectedIndex = searchId;
			custMySearch.getMySearch('my_search!mySearchList.action?searchType='+searchType);
			if(msg == 'success')
			{
			}
			else if (msg != '')
			{
				alert(msg);
			}
		},
		error: function (msg){
			alert("Failed to save the services search.Please contact system administrator for help.");
		}
	});

	return false;
}
// delete order mysearch 
function delCustMysrch(custmysrchid){
	var searchType = "ServiceSearch";
	if(!confirm('Are you sure to delete the selected item?'))
	{
		return false;
	}
	var mysrchIndex = $('#'+custmysrchid).attr('value');
	var custmysrchvalue = custMySearch.my_search_list[mysrchIndex].searchId;
	
	$.ajax({
		type: "POST",
		url: "my_search!delMysrch.action?searchType="+searchType,
		data: 'mySrchId='+custmysrchvalue,
		success: function(msg){
			if(msg == 'success')
			{
			}else if(msg != '')
			{
				alert(msg);
			}
			//refresh my search drop down list
			custMySearch.getMySearch('my_search!mySearchList.action?searchType='+searchType);
		},
		error: function(msg){
			alert("Failed to delete the order search.Please contact system administrator for help.");
		}
	});
	
	return false;
}

//搜索前去除文本框输入字符串两端的空格
function search() {
	var catNo = $("#catNo").val();
	$("#catNo").attr("value",$.trim(catNo));
	var pdtServName = $("#pdtServName").val();
	$("#pdtServName").attr("value",$.trim(pdtServName));
	var pdtServDesc = $("#pdtServDesc").val();
	$("#pdtServDesc").attr("value",$.trim(pdtServDesc));
	$("#mainForm").submit();
}

function excelSearch(){
	var url ="serv/serv!listExcel.action";
	var formStr = $('#mainForm').serialize();
	url+="?"+formStr;
	$("#excelSearchForm").attr("action",url);
	$("#excelSearchForm").submit();
}
function excelASearch(searchTag){
	var formObj = $( '#'+searchTag+"Form" ) ;
	var currentId = "" ;

	var selectAttrStr = "" ;
	var selectOperStr = "" ;
	var selectValueAStr = "" ;
	var selectValueBStr = "" ;
	
	if( searchTag == "advancedSearch" )
	{
		selectAttrStr = "advSearchTable_attr" ;
		selectOperStr = "advSearchTable_oper" ;
		selectValueAStr = "advSearchTable_valueA" ;
		selectValueBStr = "advSearchTable_valueB" ;
	}
	else
	{
		selectAttrStr = "mySearchTable1_attr" ;
		selectOperStr = "mySearchTable1_oper" ;
		selectValueAStr = "mySearchTable1_valueA" ;
		selectValueBStr = "mySearchTable1_valueB" ;
	}
	
	var fieldType = "" ;
	var formAction = "" ;
	
	
	var selectAttrVal = "" ;
	var selectOperVal = "" ;
	var selectValueAVal = "" ;
	var selectValueBVal = "" ;
	
	var actionUrl = "?" ;
	$( ":select [id*='"+selectAttrStr+"']" ).each(
			function()
			{
				currentId = $(this).attr( 'id' ).replace( selectAttrStr+"_" , "" ) ;
				fieldType = $(this).find("option:selected").attr("title") ;
				//&filter_EQI_custNo=401&filter_LIKES_firstName=&filter_LIKES_lastName=
				selectAttrVal = attrs[$(this).val()] ;
				selectOperVal = $( "#"+selectOperStr+"_"+currentId ).val() ;
				selectValueAVal = $( "#"+selectValueAStr+"_"+currentId ).val() ;
				selectValueBVal = $( "#"+selectValueBStr+"_"+currentId ).val() ;
				
				if( selectValueAVal == "" )
				{
					return true;
				}
				else if( selectOperVal == "between" & selectValueBVal == "" )
				{
					return true ;
				}
				
				selectOperVal = getOperatorString( selectOperVal ) ;
				fieldType = getPropertyType( fieldType ) ;
				if( selectOperVal == "BETWEEN" )
				{
					if(selectAttrVal == "poNumber"){
						actionUrl += "&searcher_"+selectOperVal+fieldType+"_"+selectAttrVal+"="+""+selectValueAVal;
					}else if(selectAttrVal == "orderNo" && $("#searchType").val()!= "OrderSearch" && $("#searchType").val()!= "PurchaseOrderSearch"){
						actionUrl += "&searcher_"+"GE"+fieldType+"_"+selectAttrVal+"="+selectValueAVal+"&searcher_"+"LE"+fieldType+"_"+selectAttrVal+"="+selectValueBVal;
					}else{
						actionUrl += "&filter_"+"GE"+fieldType+"_"+selectAttrVal+"="+selectValueAVal+"&filter_"+"LE"+fieldType+"_"+selectAttrVal+"="+selectValueBVal;
					}
				}
				else
				{
					if(selectAttrVal == "poNumber"){
						actionUrl += "&searcher_"+selectOperVal+fieldType+"_"+selectAttrVal+"="+""+selectValueAVal;
					}else if(selectAttrVal == "orderNo" && $("#searchType").val()!= "OrderSearch" && $("#searchType").val()!= "PurchaseOrderSearch"){
						actionUrl += "&searcher_"+selectOperVal+fieldType+"_"+selectAttrVal+"="+""+selectValueAVal;
					}else{
						actionUrl += "&filter_"+selectOperVal+fieldType+"_"+selectAttrVal+"="+""+selectValueAVal;
					}
				}
			}
		) ;
	var url ="serv/serv!listExcel.action"+actionUrl;
	$("#excelSearchForm").attr("action",url);
	$("#excelSearchForm").submit();
}
</script>
</head>

<body class="content" style="background-image:none;">

<div class="scm">
<div class="title_content">
  <div class="title">Service</div>
</div>


<div class="search_box">
  <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
      <li class="TabbedPanelsTab TabbedPanelsTabSelected" tabindex="0" >Search</li>
      <li class="TabbedPanelsTab" tabindex="1" >Advanced Search</li>
      <li class="TabbedPanelsTab" tabindex="2" >My Search</li>
    </ul>
    
    <div class="TabbedPanelsContentGroup">

	<div style="display: block;" class="TabbedPanelsContent TabbedPanelsContentVisible">
    <form id="mainForm" method="get" action="${ctx }/serv/serv.action" target="pdtServ_iframe">
    <input type="hidden" name="search_type" value="search" />
    <br />
    <table border="0">
    <tr>
    	<td width="106" align="right">Service Catalog No</td>
        <td width="134" ><input name="filter_LIKES_catalogNo" id="catNo" class="NFText" size="20" type="text" /></td>
        <td width="88" align="right">Name</td>
        <td width="124"><input name="filter_LIKES_name" id="pdtServName" class="NFText" size="20" type="text" /></td>
        <td width="126" align="right">Description</td>
        <td width="197"><input name="filter_LIKES_description" id = "pdtServDesc" class="NFText" size="25" type="text" /></td>
    </tr>
    </table>
    <table>
    	<tr><td height="20">&nbsp;</td></tr>
    </table>
    <table width="725">
    	<tr><th><input type="button" value="Search" class="search_input"  onclick="search();"/>
    			&nbsp;&nbsp;<input type="button" value="Export" class="search_input" onclick="excelSearch();"/> 
    		</th></tr>
    </table>
    
    </form>
    </div>
      
      <div style="display:none"  class="TabbedPanelsContent">
						<form method="post" id="advancedSearchForm"
							action="${ctx }/serv/serv.action" _action="serv/serv.action"
							target="pdtServ_iframe">
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
									<th><input type="button" value="Search"
										id="advancedSearch" class="search_input" /> &nbsp;&nbsp;<input
										type="button" value="Export" class="search_input"
										onclick="excelASearch('advancedSearch');" /></th>
								</tr>
							</table>
						</form>
					</div>
      
      <div style="display:none" class="TabbedPanelsContent">
      <form method="post" id="mySearchForm" action="${ctx }/serv/serv.action" _action="serv/serv.action" target="pdtServ_iframe">
	  <input type="hidden" name="search_type" value="my_search" />
	  <table width="725">
		<tr>
		<td align="right">My Search</td>
		<td align="left">
			<select name="mysearch_sel" id="mySearchSel" onchange="custMySearch.showMySearch(this.value);"></select>
			<input type="button" name="Submit5" value="Save" onclick="return saveMySrch('mySearchForm', 'mySearchSel');" />
			<input type="submit" name="Submit192" value="Delete"  onclick="return delCustMysrch('mySearchSel')" />
		</td>
		</tr>
	  </table>
	  <table border="0" id="mySearchTable1" style="margin-left:70px;">
		<tr>
		<td width="145">Attributes</td>
		<td width="145">Operator</td>
		<td width="145">Value A</td>
		<td width="145">Value B</td>
		<td width="250">&nbsp;</td>
		</tr>
	  </table>
		
	  <table width="480" style="margin-left:70px;">
		<tr>
		<td align="left">
		<input type="button" onclick="custSearchAttr.addmy();" value="Add Criteria" />
		</td>
		<td align="right">&nbsp;</td>
		</tr>
	  </table>
      
	  <table width="725">
		<tr>
		<th>
		<input type="submit"  value="Search" class="search_input" id="mySearch"/>
		&nbsp;&nbsp;<input type="button" value="Export" class="search_input" onclick="excelASearch('mySearch');"/> 
		</th>
		</tr>
		</table>
        </form>
      </div>
    </div>
  </div>
</div>
<div class="input_box">
		<div class="content_box">
			<iframe src="${ctx }/serv/serv.action" name="pdtServ_iframe" id="pdtServ_iframe" width="1010" height="560" frameborder="0" scrolling="no"></iframe>
		</div>
  </div>	
</div>	
<form id="excelSearchForm" method="post" action="" target="pdtServ_iframe">

</form>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
custSearchAttrAdv.add();
</script>
</body>
</html>