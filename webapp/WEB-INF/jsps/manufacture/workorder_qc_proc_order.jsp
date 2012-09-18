<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Quality Control</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
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
		<script src="${global_js_url}newwindow.js" type="text/javascript"></script>
		<script type="text/javascript">
			var global_url = "${global_url}" ;
		    function checkForm() {
		       $("#order_no_txt").val($.trim($("#order_no_txt").val()));
		       $("#so_no_txt").val($.trim($("#so_no_txt").val()));
		    
		    }
		
 $(function() {	               
     $("#product_status_sel").change(function() {
        if ($("#product_status_sel").val() == 'Failed') {
            $("#product_status_note").show();
            $("#product_status_area").show();
        } else {
            $("#product_status_note").hide();
            $("#product_status_area").hide();        
        }
     });

     $("#doc_status_sel").change(function() {
        if ($("#doc_status_sel").val() == 'Failed') {
            $("#doc_status_note").show();
            $("#doc_status_area").show();
        } else {
            $("#doc_status_note").hide();
            $("#doc_status_area").hide();        
        }
     });
     
     //Process按钮处理
     $("#process_btn").click( function() {
            var param = $("#process_from").serialize();
		    param = param +"&orderNoStrs="+parent.$("#choiceOption").val();
		    $("#process_from").html("<div align='center' width='100%'><img src='images/indicator.gif' /></div>");
	        $.ajax({
				type: "POST",
				url: "workorder_qc!processOrder.action",
				data: param,
				success: function(jsonObj, textStatus){
				   alert("Process sucessful !");
	               parent.$('#batch_order_dlg').dialog('close');
	               parent.document.location.reload();
				},
				error: function(xhr, textStatus){
				   alert("Select error !");
				   return;
				}
	       }); 
     });
 });//end of $(function() {}...

    
        </script>
	</head>
	<body>
		<form action="workorder_qc!processOrder.action" id="process_from">
			<table width="420" border="0" cellpadding="0" cellspacing="0"
				class="General_table" style="margin: 10px auto 0px auto;">
				<tr>
					<th width="188">
						&nbsp;
					</th>
					<th width="222">
						&nbsp;
					</th>
				</tr>
				<tr>
					<th>
						Product/Service QC Status
					</th>
					<td>
						<select name="goodsQcStatus" id="product_status_sel">
							<option>
								Passed
							</option>
							<option>
								Failed
							</option>
							<option>
								Partial
							</option>
						</select>

					</td>
				</tr>
				<tr>
					<th>
						Production Documents QC Status
					</th>
					<td>
						<select name="docQcStatus" id="doc_status_sel">
							<option selected="selected">
								Passed
							</option>
							<option>
								Failed
							</option>
							<option>
								Partial
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<th colspan="3" id="product_status_note" style="display: none;">
						<div align="left">
							Choose the reason to reject the products/service:
						</div>
					</th>
				</tr>
				<tr>
					<td colspan="3" id="product_status_area" style="display: none;">
						<div align="center">
							<textarea name="goodsQcRejectReason" class="content_textarea2"></textarea>
						</div>
					</td>
				</tr>
				<tr>
					<th colspan="3" id="doc_status_note" style="display: none;">
						<div align="left">
							Choose the reason to reject the production documents:
						</div>
					</th>
				</tr>
				<tr>
					<td colspan="3" id="doc_status_area" style="display: none;">
						<div align="center">
							<textarea name="docQcRejectReason" class="content_textarea2"></textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3" valign="top">
						<div class="botton_box">
							<br />
							<input name="Submit22" type="button" class="style_botton"
								value="Process" id="process_btn" />
							<input type="button" name="close2" value="Cancel"
								class="style_botton"
								onclick="parent.$('#batch_order_dlg').dialog('close');" />
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>