<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Warehouse Manager's Workstation</title>
		<%@ include file="/common/taglib.jsp"%>
		<base href="${global_url}" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet"
			type="text/css" media="all" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet"
			type="text/css" />
		<script language="javascript" type="text/javascript"
			src="${global_js_url}ajax.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}tab-view.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}TabbedPanels.js"></script>
		<script language="javascript" type="text/javascript"
			src="js/newwindow.js"></script>

		<script type="text/javascript">
	var GB_ROOT_DIR = "./greybox/";
</script>
		<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
		<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
		<script type="text/javascript"
			src="${global_js_url}greybox/gb_scripts.js"></script>
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
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


<script language="javascript" type="text/javascript"  >	

 function listAll(obj, chk){
              if (chk == null)
			  {
			    chk = 'checkboxes';
			  }
			
			  var elems = obj.form.getElementsByTagName("INPUT");
			
			  for (var i=0; i < elems.length; i++)
			  {
			    if (elems[i].name == chk || elems[i].name == chk + "[]")
			    {
			      elems[i].checked = obj.checked;
			    }
			  }
          }
          
           function subAllDel(){			 
             document.forms[0].submit();
          }
    
    $(function() {            
           parent.$('#shipClerk').dialog({
					autoOpen: false,
					height: 350,
					width: 550,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });

	function openSearchDlg(itemNo) {
		parent.$('#shipClerk').dialog("option", "open", function() {	
	         var htmlStr = '<iframe src="work_station!shipClerk.action?cks='+itemNo+'" height="300" width="500" scrolling="no" style="border:0px" frameborder="0"></iframe>';
	        parent.$('#shipClerk').html(htmlStr);
		});
		parent.$('#shipClerk').dialog('open');
	
	}
    </script>
</head>
<body class="content" >
<form action="work_station!shipClerk.action" target="orderItem" name="workStationForm">
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
 			 <tr>
    <td>

		<div  style="height:100%;width=100%;">
		<div>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="list_table">
			<tr>
				 <th width="20" align="center">
        <div align="center">
	<input onclick='listAll(this, "cks")' type="checkbox"/>   
	     </div></th>
        <th width="10">Assigned</th>
        <th width="50">Assigned To</th>
        <th width="50">Sales Order No</th>
         <th width="40">US PO NO</th>
        <th width="30">Item No</th>
        <th width="40">Item Status</th>
        <th width="100">Item Name</th>
        <th width="50">Item Type</th>
        <th width="70">Order Date</th>
        <th width="70">Due Date</th> 
       
       
       
        <c:if test="${whName=='Genscript NJ Warehouse'}">
         <th width="70">Vendor</th></c:if>
        
			</tr>
			<c:set var="rowcount" value="1"></c:set>
			<s:iterator value="pageOrderItemDTO.result">
			<c:if test="${rowcount mod 2 == 0}">
                <c:set var="tdclass" value=" class='list_td2'"></c:set>
              </c:if>	
             <c:if test="${rowcount mod 2 == 1}">
                <c:set var="tdclass" value=""></c:set>
              </c:if>
			<tr>
					<td width="20" align="center" style="height: 20px;" ${tdclass}><input type="checkbox"   name ="cks"  id="cks" value="${shippmentId },${comment}"
						 /></td>
					<td width="10" ${tdclass} align="center"  >
					<c:if test="${comment==''}">
						no
					</c:if>
					<c:if test="${comment!=''}">
						yes
					</c:if>
					</td>
					<td style="width: 50px" ${tdclass}  align="center"><a href="javascript:void(0)" onclick="openSearchDlg('${shippmentId}');">${comment}</a></td>
				    <td style="width: 50px" ${tdclass} align="center">&nbsp;${orderNo}</td>
				    <td style="width: 40px" ${tdclass}>&nbsp;${usPoNo}</td>	
					<td style="width: 30px" ${tdclass}>&nbsp;${itemNo}</td>
					<td style="width: 40px" ${tdclass}>&nbsp;${status}</td>
					<td style="width: 100px" ${tdclass}>&nbsp;${name}</td>
					<td style="width: 50px" ${tdclass}>&nbsp;${type}:${relationType }</td>
					<td style="width: 70px" ${tdclass}>&nbsp;<s:date format="yyyy-MM-dd" name="orderDate"/></td>
					<td style="width: 70px" ${tdclass}>&nbsp;<s:date format="yyyy-MM-dd" name="exprDate"/></td>
					
					
					
					<c:if test="${whName=='Genscript NJ Warehouse'}">
					<td style="width: 70px" ${tdclass}>&nbsp;${vendorName}</td>	</c:if>
					
				</tr>		
		          <c:set var="rowcount" value="${rowcount+1}"></c:set>
			</s:iterator>
		</table>
		</div>
		<div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
				<jsp:param value="${ctx}/work_station!mwSearch.action" name="moduleURL" />
			</jsp:include></div>	
		</div>	
		</td>
	</tr>
</table>	
</form>
</body>
</html>