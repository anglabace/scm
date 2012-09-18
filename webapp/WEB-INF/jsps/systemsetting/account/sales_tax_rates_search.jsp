<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />

<script type="text/javascript" language="javascript" src="${global_js_url}SpryTabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${global_js_url}searchattr.js"></script>
<script src="${global_js_url}searchForm.js" type="text/javascript"></script>
<script src="${global_js_url}initlefttop.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>

<script language="javascript">
function check() {
	  var filter_LIKES_name = $("#filter_LIKES_name").val();
	  $("#filter_LIKES_name").attr("value",$.trim(filter_LIKES_name));
	  var filter_LIKES_countryCode = $("#filter_LIKES_countryCode").val();
	  $("#filter_LIKES_countryCode").attr("value",$.trim(filter_LIKES_countryCode));
	  return true;
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
<body class="content" style="background-image:none;" onload="rtnPreLeftTop('Accounting');">
<div class="scm">
<div class="title_content">
  <div class="title_new"><a href="javascript:void(0);" onclick="toggleShowMorea('total_title');" id="total_titleItem"><img src="${global_url}images/arrow1.jpg" /></a>&nbsp;National Tax Rates</div>
</div>

<div class="search_box" id="total_title" style="display:block;">
<form name="search_form" action="account_sales_tax!list.action" id="search_form" target="srchAccountSalesTax_iframe" onsubmit="return check();">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
	<tr>
		<td align="left">Country&nbsp;&nbsp;<input name="filter_LIKES_name" type="text" size="20" id="filter_LIKES_name" class="NFText"/>
		&nbsp;&nbsp;&nbsp;&nbsp;Country Code&nbsp;&nbsp;<input name="filter_LIKES_countryCode" id="filter_LIKES_countryCode" type="text" size="20" class="NFText"/>
		&nbsp;&nbsp;&nbsp;&nbsp;Status&nbsp;&nbsp;
		<select style="width: 70px;" name="filter_EQS_status" id="status">
					<option value="">All</option>
           		    <option value="ACTIVE">ACTIVE</option>
                	<option value="INACTIVE">INACTIVE</option>
              	</select>
		</td>
		</tr>
		<tr><td align="center" ><br /><input type="submit" name="searchButton" value="Search" class="search_input" /></td><td width="350"></td></tr>
</table>
<br/>
</form>
</div>
<input type="hidden" id="choiceOption"/> 
<div class="input_box">
		  <div class="content_box">
			<iframe id="srchAccountSalesTax_iframe" name="srchAccountSalesTax_iframe" src="systemsetting/account_sales_tax!list.action" width="100%" height="630" frameborder="0" scrolling="no" ></iframe>
		</div>
  </div>	
</div>
</body>
</html>