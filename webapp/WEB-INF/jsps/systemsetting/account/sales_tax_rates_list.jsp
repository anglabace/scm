<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp" %>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>scm</title>
<base  href="${global_url}" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}initlefttop.js" type="text/javascript"></script>
<script  language="JavaScript" type="text/javascript">
$(function(){
	$("#resultTable tr:odd").find("td").addClass("list_td2");
	  init();
	//复选框绑定: 全选|全不选
	    $("#check_all").click( function() {
	    	var allChoiceVal = "";
	       $(":checkbox").each(function() {
	          this.checked = $("#check_all").get(0).checked;
	          if(this.checked&&this.name=="rateId") {
	              if(allChoiceVal=="") {
	            	  allChoiceVal=this.value;
	              } else {
	            	  allChoiceVal = allChoiceVal+","+this.value;
	              }
	          }
	       });
	       parent.document.getElementById("choiceOption").value=allChoiceVal;
	    });

});

//复选框选择事件
function choice(obj) {
	var allChoiceVal = parent.document.getElementById("choiceOption").value;
	var id = obj.value;
	if(obj.checked) {
		if(allChoiceVal=="") {
			allChoiceVal=id;
		} else {
			allChoiceVal=allChoiceVal+","+id;
		}
		
	} else {
		var allChoiceValArr = allChoiceVal.split(",");
		allChoiceVal ="";
		if(allChoiceValArr==null||allChoiceValArr==""||allChoiceValArr.length==0) {
			allChoiceVal=allChoiceVal+id;
		} else {
			$.each(allChoiceValArr,function(k,v) {
				if(v!=id) {
					if(allChoiceVal=="") {
						allChoiceVal=allChoiceVal+v;
					} else {
						allChoiceVal=allChoiceVal+","+v;
					}
					
				}
			});
		}
	}
	parent.document.getElementById("choiceOption").value=allChoiceVal;
}

function deleteSel() {
	  if ($(":checkbox:gt(0):checked").length < 1) {
      alert('Please select one at least!');
		   return false;
   }
		if (!confirm('Are you sure to delete?')) {
			return false;
		}
		var allChoiceVal = parent.document.getElementById("choiceOption").value;
		$.ajax({
			type: "POST",
			url: "account_sales_tax!delete.action",
			data: "allChoiceVal="+allChoiceVal,
			dataType: 'json',
			success: function(data){
				if(hasException(data)){//有错误信息.
				}else {
					alert(data.message);
				    window.location.reload();
				}
			},
			error: function(msg){
				alert("Delete country failed !");
            $('#check_del').attr("disabled", false);
			}
		});
}

//复选框初始化
function init() {
	var allChoiceVal = parent.document.getElementById("choiceOption").value;
	if(allChoiceVal!="") {
		var allChoiceValArr = allChoiceVal.split(",");
		$.each(allChoiceValArr,function(k,v) {
			var id = "#"+v;
			if($(id)!=null) {
				$(id).attr("checked",true);
			}
		});
	}
}
</script>
</head>
<body onload="rtnPreLeftTop('Accounting');" class="content">

<div style="margin-right:17px">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="list_table">
  <tr>
    <th width="46">
<div align="left">
      <input name="checkbox" type="checkbox"  id="check_all" />
       <a title="Delete Country" rel="gb_page_center[600,  180]" onclick="deleteSel()">
    <img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0"/></a></div></th>
    <th width="71">Country</th>
    <th width="93">Country Code</th>
    <th width="64">Status</th>
    <th width="99">Currency</th>
    
    <th width="104">Currency Code</th>
    <th width="93">Tax Rate</th>
    <th width="96">Language</th>
    <th width="106">Language Code</th>
    <th width="87">Continent</th>
    <th>Description</th>
  </tr>
</table>
</div>
				<div class="list_box" style="height:340px;">
				<table id="resultTable" width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  class="list_table">
				<s:iterator value="countryPage.result">
					<tr>
			        <td width="46">
	      				<div align="left">
	      					<div align="left">
	       							<input type="checkbox" value="${countryId}" name="rateId"  onclick="choice(this);"/>
	   						</div>
	      				</div>
	      			</td>
				    <td width="71" align="center"><div align="center">&nbsp;<a href="systemsetting/account_sales_tax!load.action?id=${countryId}" target="_parent">${name} </a></div></td>
				    <td width="93" align="center">&nbsp;${countryCode}</td>
				    <td width="64" align="center">&nbsp;${status}</td>
				     <td width="99" align="center">&nbsp;${symbol}</td>
				     <td width="104" align="center">&nbsp;${currencyCode}</td>
				     <td width="93" align="center">&nbsp;${nationalRate}</td>
				     <td width="96" align="center">&nbsp;${langName}</td>
				     <td width="106" align="center">&nbsp;${langCode}</td>
				     <td width="87" align="center">&nbsp;${continent}</td>
				     <td>&nbsp;${description}</td>
				  	</tr>
				</s:iterator>
  				</table>
</div>
<div class="grayr">
	<jsp:include page="/common/db_pager.jsp">
	 	 <jsp:param value="systemsetting/account_sales_tax!list.action" name="moduleURL"/>
	</jsp:include>
</div>
<div class="button_box">
<input type="button" name="Submit16" value="New" class="search_input2"  onclick="parent.location.href='systemsetting/account_sales_tax!load.action'"/>
<input type="button" name="Submit193" value="View Uninvoiced Shipments" class="search_input3"  onclick="window.openiframe('search_uninvoiced.html','664','380')" disabled="disabled" style="display:none;"/>
</div>

</body>
</html>
