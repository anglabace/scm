<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
			src="${global_js_url}ajax.j	s"></script>
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
<script type="text/javascript" language="javascript">
	function confirm1(){
		var str = "";
		var str = get_checked_str("shipClerkName");
		
        if(str == null || str == ""){
				alert('Please choose a record & records!');
				return;
		}
        $("#shipClerk").val(str);
    	document.unassignForm.submit();
	}

	function get_checked_str(name)
	{
		var a = document.getElementsByName(name);
		var str = '';
		for   (var   i=0;   i<a.length;   i++)
		{
			if(a[i].checked)
			{
				str += a[i].value+',';
			}
		}
		return str.substring(0,str.length-1);
	}
</script>
</head>
<body><br/>
    <form action="work_station!unassign.action" id="unassignForm" name="unassignForm"  target="orderItem">
    	<input type="hidden" name="shippmentId" value="${itemNo }" />
    	<input type="hidden" name="chk" value="${cks }" />
    	<input type="hidden" name="shipClerk" id="shipClerk" value="" />
              <table  align="center" border="0" cellpadding="0" cellspacing="0">
              <tr>
              <td>
              <div class="list_box" style="height:200px;background-color:#FFF;width:500px;">
                      <table width="450" border="0" cellpadding="0" cellspacing="0">
		                <tr align="center">
		                <th valign="top"><div id="">Shipping Clerk to Assign:</div></th>
		                <td></td>
		                </tr>
		                <tr>
		                <th></th>
		                <td align="left">       
						  <c:forEach var="wh" items="${shipList}">
								
									<input type="checkbox" name="shipClerkName" id="shipClerkName" value="${wh.userId}"/>${wh.employee.employeeName}(Receiver/Picker/Packer)<br/>
								
							</c:forEach>
						 
		                  <div id="orderTr" style="display:none;margin-top:5px;">
		                    <textarea name="textarea" cols="" rows="" class="content_textarea2"></textarea>
		                  </div></td>
		              </tr>
		              </table>
              </div>
              </td>
              </tr>
              <tr>
                <td height="80" colspan="2"><div align="center"><br />
                  <input type="button"" name="Submit2"  class="search_input" value="Confirm" onclick="confirm1()"/>
                  <input  type="button" name="Cancel" value="Cancel" class="search_input" onclick="parent.$('#shipClerk').dialog('close');"/>
                </div></td>
              </tr>
            </table>
              <br />
              <br />
    </form>          
</body>
</html>