<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Shipments</title>
	<%@ include file="/common/taglib.jsp"%>
	<base href="${global_url}" />
	<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
	<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
	<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
	<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
	<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
	<link href="${global_css_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css" />
	<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
	<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
	<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
	<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
	<script language="javascript" type="text/javascript" src="js/newwindow.js"></script>
	<script language="javascript" type="text/javascript"> var GB_ROOT_DIR = "./greybox/";</script>
	<script language="javascript" type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
	<script language="javascript" type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
	<script language="javascript" type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
	<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
	<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
	<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
	<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
	<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
	<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
	<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
	
	
	<script type="text/javascript" language="javascript">
		$(function() {
		    $('.ui-datepicker').each(function(){
					$(this).datepicker(
						{
							dateFormat: 'yy-mm-dd',
							changeMonth: true,
							changeYear: true
						});
				}); 
		});
		$(function() {            
		            	$('#cancel1').dialog({
							autoOpen: false,
							height: 290,
							width: 650,
							modal: true,
							bgiframe: true,
							buttons: {
							}
						});
		            });
		$(function() {            
		            	$('#combine1').dialog({
							autoOpen: false,
							height: 340,
							width: 700,
							modal: true,
							bgiframe: true,
							buttons: {
							}
						});
		            });
	            
		// combine shipments
		function combine() {
			var warehouse = document.getElementById('type').value;
			var cksObj = $('#appInfo').contents().find( '#cks' ) ;
			//alert(cksObj.length);
			var d1 = new Array();
			cksObj.each(
				function()
				{
					if( $(this).attr( 'checked' ) == true )
					{
						d1.push($( this ).val() ) ;
					}
		
				}
			) ;
		
			if(d1 == null || d1.length == 0){
			
				alert('Please choose a shipment!');
				return;
			}
			if(d1.length<2){
				alert("Choose two at least!");
				return false;
			}
		
			$('#combine1').dialog("option", "open", function() {	
		         var htmlStr = '<iframe src="shipments!combineShipments.action?cks='+d1+'" height="280" width="700" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		         $('#combine1').html(htmlStr);
			});
			$('#combine1').dialog('open');
		}
	
		// cancel shipments
		function cancel() {
			//var warehouse = $('#searchBody').contents().find( '#type' ).attr("value");
			var warehouse = document.getElementById('type').value;
			var cks = $('#appInfo').contents().find( '#cks' );
			var d1 = new Array();
			for(var i=0;i<cks.length;i++){
				if(cks[i].checked){
					d1.push(cks[i].value);
				}
			}
			if(d1.length==0){
				alert("Please choose one at least!");
				return false;	
			}
			
			$('#cancel1').dialog("option", "open", function() {
			        var htmlStr = '<iframe src="shipments!cancelShipments.action?warehouse='+warehouse+'&cks='+d1+'" height="280" width="700" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			        $('#cancel1').html(htmlStr);
			});
			$('#cancel1').dialog('open');
		}
	
		function check(){
			
			var orderNo=document.getElementById('orderNo').value;
			var custNo = document.getElementById('custNo').value;
			$("#orderNo").attr("value",$.trim(orderNo));
			$("#custNo").attr("value",$.trim(custNo));
			if(isNaN(orderNo) || isNaN(custNo)){
				alert("orderNo or custNo must is number !");
				return false;
			}else {
				document.shipSearchForm.submit();
			}
		}
		function refresh(){
			 $.ajax({
					url:"shipment/shipments!refresh.action",
					type:"post",
					data:"",
					dataType:"json",
					beforeSend:function(){
				 			
				 			$("#loading").show();
				 	},
				 	error:function(){alert('fault.');},
				   	complete: function(msg){
				 		$("#loading").hide();//
				   	},
				 					
					success:function(data){
						
							if(data.message == "success"){
								alert("The opertation has completed.");
								window.location.reload();
							}else{
									alert("System error!Please contact system administrator for help.");
							}
							

					},
					error:function(data){
						alert("System error! Please contact system administrator for help.");
					},
					async:false
				});
		}
		function selectBranch(){
			var type=document.getElementById('type').value;
			if(type == "MANUFACTURING"){
				//document.getElementById("cancel1").disabled="false";
				//document.getElementById("combine1").disabled="true";
				document.all["cancel"].style.display="none";
				document.all["combine"].style.display="none"; 
			}else{
				document.all["cancel"].style.display="";
				document.all["combine"].style.display=""; 
			}
			document.forms[0].action = "shipments!shipmentListSearch.action?type='"+type+"'";
		  	document.forms[0].submit();
		}
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
<body class="content" style="background-image:none;">
<div class="scm" style="overflow:auto; width: 1100PX">
<div class="title_content">
  <div class="title_new"><a href="javascript:void(0);" onclick="toggleShowMorea('total_title');" id="total_titleItem"><img src="${global_url}images/arrow1.jpg" /></a>&nbsp;&nbsp;Process Shipments</div>
</div>
<div style="display: none;" id="loading">
	<img src="images/loading.gif" height="100" width="100"/>
</div>
<div style="display:block;" class="search_box" id="total_title">&nbsp;
<form name="shipSearchForm" id ="shipSearchForm" action="shipments!shipmentListSearch.action" target="appInfo">
<table border="0" cellpadding="0" cellspacing="0" class="General_table" style="TABLE-LAYOUT: fixed">
        <tr>
          <td align="right">Warehouse</td>
          <td width="120"><span class="single_search_css">
            <select name="type" id="type" onchange="selectBranch();"  style="width: 150px">
				<c:forEach items="${wList}" var="o">
					<option value="${o.type }">
						${o.name}
					</option>
				</c:forEach>
			</select>
          </span></td>
          <td align="right">Shipping Clerk</td>
          <td width="120">
          <select name="shipschDTO.shippingClerk" id="shippingClerk"  style="width: 150px">
          	<c:if test="${isProductionManagerRole==true}">
									<option value="0">
										All
									</option>
							</c:if>
							<c:forEach items="${nList}" var="rc">
								<c:if test="${rc.userId==userId}">
									<option value="${rc.userId }" selected="selected">
										${rc.employee.employeeName }
									</option>
								</c:if>
								<c:if test="${rc.userId!=userId}">
									<option value="${rc.userId }">
										${rc.employee.employeeName }
									</option>
								</c:if>
							</c:forEach>
		  </select>
          </td>
          <td align="right"> Status</td>
          <td>
          <select name="shipschDTO.status"  id="status">
          	<option value="">
				All
			</option>
            <option value="Drafted">
					Drafted
				</option>
			
				
			
				<option value="Ready To Ship">
					Ready To Ship
				</option>
			
				<option value="Partial Ready To Ship">
					Partial Ready To Ship
				</option>
				
				<option value="Shipped">
					Shipped
				</option>
				
				<option value="Partial Shipped">
					Partial Shipped
				</option>
				<option value="Invalid">
					Invalid
				</option>
          </select></td>
        </tr>
       <tr></tr>
         <tr>
          <td align="right">Packaging Error</td>
          <td width="120">
	          <select name="error"  style="width: 150px">
	            <option value="">All</option>
	            <option value="YES">YES</option>
	            <option value="NO">NO</option>
	          </select>
          </td>
          <td align="right">Order Reference</td>
          <td width="120">
          <input id="orderNo" name="shipschDTO.orderNo" value="" type="text" class="NFText"  onKeyUp="value=value.replace(/\D+/g,'')"   style="width: 143px"/></td>
          
          <td align="right">Shipment Date</td>
          <td>
	        <input name="shipschDTO.shipDate" type="text" class="ui-datepicker" value="<s:date name="shipschDTO.shipDate" format="yyyy-MM-dd"/>" size="20" style="width: 124px;"  readonly="readonly"/>
          </td>
          
        </tr>
        <tr>
        	<td align="right">Customer No</td>
          <td width="120">
          <input id="custNo" name="shipschDTO.custNo" type="text" class="NFText" size="20" onKeyUp="value=value.replace(/\D+/g,'')"  style="width: 143px"/></td>
          <td align="right">Order Status</td>
          <td width="120">
         <select name="shipschDTO.orderStatus"  style="width: 150px">
	            <option value="">All</option>
	            <option value="CC">CC</option>
	            <option value="SH">SH</option>
	            <option value="RV">RV</option>
	            <option value="NW">NW</option>
	          </select>
	          
	      </td>
	      <td align="right">US PO NO</td>
      	 <td>
	        <input type="text" name="shipschDTO.poNo" class="NFText" style="width: 143px" onKeyUp="value=value.replace(/\D+/g,'')"/>
          </td>
        </tr> <tr>
        	<td align="right">Delever By</td>
          <td width="120"> <select name="shipschDTO.sendBy" id="sendBy"  style="width: 150px">
          	<c:if test="${isProductionManagerRole==true}">
									<option value="0"  selected="selected">
										All
									</option>
							</c:if>
							<c:forEach items="${nList}" var="rc">
								
									<option value="${rc.userId }">
										${rc.employee.employeeName }
									</option>
								
							</c:forEach>
		  </select></td>
         <td align="right"></td>
          <td width="120">
        		
	          
	      </td>
	      <td align="right"></td>
      	 <td>
	          </td>
        </tr>
        <tr>
        
          <td height="40" colspan="6" align="center" >
          <input type="button" name="Submit5" value="Search" class="search_input" onclick="check();" />
          <input type="button" name="Submit5" value="Refresh" class="search_input" onclick="refresh();" />
          
          </td>
        </tr>
    </table>
 </form>
</div>

	<iframe src="shipments!shipmentListSearch.action" name="appInfo" id="appInfo" width="100%" height="360px" frameborder="0" scrolling="no"></iframe>

	<div class="button_box">
		<!--<input type="button" name="cancel" id="cancel" value="Cancel Shipment" class="search_input2" onclick="cancel();" />
		--><input type="button" name="combine" id="combine" value="Combine Shipments" class="search_input2" onclick="combine();" />
	</div>
	
		<div id="cancel1" title="Cancel Shipment" style="visible: hidden;"></div>
		<div id="combine1" title="Combine Shipments" style="visible: hidden;"></div>
</div>
</body>
</html>