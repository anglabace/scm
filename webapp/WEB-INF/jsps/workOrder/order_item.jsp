<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Order Management</title>
		<script type="text/javascript" src="${global_js_url}AJS.js"></script>
		<script type="text/javascript" src="${global_js_url}AJS_fx.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}newwindow.js"></script>

		<script language="javascript" type="text/javascript"
			src="${global_js_url}ajax.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}tab-view.js"></script>


		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}gb_styles.css" rel="stylesheet"
			type="text/css" media="all" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
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
		<script type="text/javascript">
			var GB_ROOT_DIR = "./greybox/";
			function MM_jumpMenu(targ,selObj,restore){ //v3.0
			  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
			  if (restore) selObj.selectedIndex=0;
			}

			function selectAll(form,chkAll){
				var chk = form.elements;
				for(var i=0;i<chk.length;i++){
					chk[i].checked = chkAll.checked;
				}				
			}

	          $(function() {            
	             	$('#shipping_document').dialog({
	  					autoOpen: false,
	  					height: 350,
	  					width: 500,
	  					modal: true,
	  					bgiframe: true,
	  					buttons: {
	  					}
	  				});
	             });

	  			function view_shipping_doc(form,orderNo1) {
	  				var chk = form.elements;
	  				var orderNo ="";
	  				var orderItemNo ="";
	  				for(var i=0;i<chk.length;i++){
		  				if(chk[i].checked == true){
							if(orderItemNo==""){
								orderNo = orderNo1;
								orderItemNo = chk[i].value;
								
							}else{
								orderNo += ","+orderNo1;
								orderItemNo += "," + chk[i].value;
							}
							
			  			}
						
					}
					
	  				if(orderNo==""||orderNo == null){
	  					alert("Please choose a Order Item.");
	  					return ;
	  				}
	  				$('#shipping_document').dialog("option", "open", function() {	
	  				var htmlStr = '<iframe src="shipping!shippingDocument.action?orderNo='+orderNo+'&itemNo='+orderItemNo+'" height="300" width="450" scrolling="no" style="border:0px" frameborder="0"></iframe>';
	  				$('#shipping_document').html(htmlStr);
	  				});
	  				$('#shipping_document').dialog('open');
	  			}
		</script>
</head>
 

<body>
<table width="500" border="0" cellspacing="3" cellpadding="0" id="table11" bgcolor="#96BDEA">
  <tr>
    <td bgcolor="#FFFFFF"><table width="700" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="39" align="left" valign="top" background="greybox/header_bg.gif">
          </td>
      </tr>
      <tr>
        <td height="180" style="padding-left:20px;"><table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td colspan="4" style="padding-top:10px;"><table width="637" border="0" cellpadding="0" cellspacing="0" class="list_table">
                  <tr>
                    <th width="51"><input name="checkc_all" type="checkbox" onclick="selectAll(itemForm,this)" /></th>
                    <th width="122">Catalog No</th>
                    <th width="148">Name</th>
                  </tr>
                </table></td>
              </tr>
              <tr>
                <td colspan="4"><div  style="width:657px; height:150px; overflow:scroll;">
                <form id="itemForm" name="itemForm">
                  <table width="637" border="0" cellpadding="0" cellspacing="0" class="list_table">
                  	<s:iterator value="orderItemList">
                  	
                  
	                    <tr>
	                      <td width="50"><div align="center">
	                       	<input type="checkbox" name="chk" id="chk" value="${itemNo }" />
	                      </div></td>
	                      <td width="123">${catalogNo}</td>
	                      <td width="148">${name}</td>
	                    </tr>
	                </s:iterator>
                  </table>
                  </form>
                </div></td>
              </tr>
              <tr>
                <td height="40" colspan="4"><div align="center">
                  <input id="sub3" name="sub" type="submit" class="style_botton3"  value="View/Print Document" onclick="view_shipping_doc(itemForm,${orderNo})"/>
                </div></td>
              </tr>
            </table><br /></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
 <div id="shipping_document" title=" Files Document "
			style="visible: hidden" />
</body>
</html>

