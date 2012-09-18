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
 function   cc(e)  
 {  
 	var   a   =   document.getElementsByName("mm2");  
 	for   (var   i=0;   i<a.length;   i++) {
 		if(!a[i].disabled){
 			a[i].checked = e.checked;
 		}
 	}
 } 
	function view_shipping_doc(orderNo1) {
		
			var chk =  document.getElementsByName("mm2");  
			var orderNo ="";
			var orderItemNo ="";
			var orderItemId = "";
			for(var i=0;i<chk.length;i++){
				if(chk[i].checked == true){
					var chks = chk[i].value;
					var chkss = chks.split(",");
					if(orderItemNo==""){
						orderNo = orderNo1;
						orderItemNo = chkss[0];
						orderItemId = chkss[1];
					}else{
						orderNo += ","+orderNo1;
						orderItemNo += "," + chkss[0];
						orderItemId += "," +chkss[1];
					}
				
  				}
			
			}
			if(orderNo==""||orderNo == null){
				alert("Please choose a Order Item.");
				return ;
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
			
			$('#shipping_document').dialog("option", "open", function() {
				var htmlStr = '<iframe src="shipping!shippingDocument.action?orderNo='+orderNo+'&itemNo='+orderItemNo+'" height="300" width="450" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				$('#shipping_document').html(htmlStr);
			});
			$('#shipping_document').dialog('open');
		}






    
	function openSearchDlg(orderNo) {
		var chk =  document.getElementsByName("mm2");  
		var orderItemNo ="";
		var orderItemId = "";
		for(var i=0;i<chk.length;i++){
			if(chk[i].checked == true){
				var chks = chk[i].value;
				var chkss = chks.split(",");
				if(orderItemNo==""){
					orderItemNo = chkss[0];
					orderItemId = chkss[1];
				}else{
					orderItemId += "," +chkss[1];
					orderItemNo += "," + chkss[0];
				}
			
			}
		
		}
		
		if(orderItemNo==""||orderItemNo == null){
			alert("Please choose a Order Item.");
			return ;
		}
		window.location.href = 'warehousing_shipment!viewPackSlip.action?orderNo='+orderNo+'&itemNo='+orderItemNo;
		
	}
 </script>
 

</head>
 

<body>

<table width="720" border="0" cellspacing="3" cellpadding="0" id="table11" bgcolor="#96BDEA">
  <tr>
    <td bgcolor="#FFFFFF"><table width="710" border="0" cellspacing="0" cellpadding="0">
     
      <tr>
        <td height="300" style="padding-left:20px;" valign="top"><table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td colspan="4" style="padding-top:10px;"><table width="657" border="0" cellpadding="0" cellspacing="0" class="list_table">
                  <tr>
                    <th width="30"><input type="checkbox" value="checkbox" name="mm" onclick="cc(this)" checked="checked"/></th>
                    <th width="122">Catalog No</th>
                    <th>Name</th>
                  </tr>
                </table></td>
              </tr>
            
              <tr>
                <td colspan="4"><div  style="width:677px; height:200px; overflow:scroll;">
                  <table width="657" border="0" cellpadding="0" cellspacing="0" class="list_table">
                  <s:iterator value="purchaseOrderItemList" var="pil">
                    <tr>
                      <td width="30" align="center">
                        <input type="checkbox" value="${pil.itemNo },${pil.id }" name="mm2" checked="checked"/>
                      </td>
                      <td width="123">${pil.catalogNo}</td>
                      <td>${pil.name}</td>
                    </tr>
                    </s:iterator>
                  </table>
                </div></td>
              </tr>
              
              <tr>
                <td height="40" colspan="4"><div align="center">
               <input id="sub3" name="sub" type="submit" class="style_botton2"  value="Print Packing List" onclick="openSearchDlg('${srcSoNo}')"/>
                  <input name="sub" type="submit" class="style_botton6" id="sub3" onclick="view_shipping_doc('${srcSoNo}')"  value="View/Print Product Document"/>
                </div></td>
              </tr>
            </table><br /></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
 
</body>
 <div id="shipping_document" title=" Files Document "
			style="visible: hidden" />
 <div align="center" id="vendor_search_dlg" style="visible: hidden;"></div>
</html>

