<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Production Operations</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<link href="${global_css_url}seqno.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
		<script type="text/javascript" src="${global_js_url}tab-view.js"></script>
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
			function edit(resKey) {
				parent.$('#edit_com_dlg').dialog("option", "open", function() {	
				     var url = "workorder_operation!editWoComponent.action?sessWOPKey=${param.sessWOPKey}&sessComKey=" + resKey;
              		 var htmlStr = '<iframe src="' + url + '" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			         parent.$('#edit_com_dlg').html(htmlStr);
				});
				parent.$('#edit_com_dlg').dialog('open');
			}			
				
            $(function() {
 
			    $("#resultTable tr:odd").find("td").addClass("list_td2");
			
			    //复选框绑定: 全选|全不选
			    $("#check_all").click( function() {
			       $(":checkbox").each(function() {
			          this.checked = $("#check_all").get(0).checked;
			       });      
			    });
			    
			    //删除选中的checkbox.
			    $("#check_del").click( function() {    
                    var param = "sessWOPKey=" + $("#sessWOPKey").val();			   
			        if ($("#resultTable :checkbox:checked").length < 1) {
			           alert('Please select one at least!');
					   return;
			        }
			  		if (!confirm('Are you sure to delete?')) {
						return;
					}
					$("#resultTable :checkbox:checked").each(function() {
					   param += "&delROIdList=" + $(this).val();
					});		
                    $('#check_del').attr("disabled", true);
			        $.ajax({
						type: "POST",
						url: "workorder_operation!deleteComponent.action",
						dataType: 'text',
						data: param,
						success: function(data){
						    window.location = window.location;						   
						},
						error: function(msg){
							alert("Select failed !");	
						}
					}); 
	
			    });//end of $("#check_del").click.

               //验证form的逻辑
			   $('#operation_form').validate({
					errorClass:"validate_error",
					highlight: function(element, errorClass) {
						$(element).addClass(errorClass);
				    },
				    unhighlight: function(element, errorClass, validClass) {
				        $(element).removeClass(errorClass);
				    },
					invalidHandler: function(form, validator) {
						 $.each(validator.invalid, function(key,value){
				            alert(value);
				            $(this).find("name=[" + key + "]").focus();
				            return false;
				        });
					 },
					 rules: {
						 "operation.name": { required:true}
					 },
					 messages: {
						 "operation.name": { required : "Please enter the name !" }
					 },
					 errorPlacement: function(error, element) {
					 }
				});     
				
                $("#new_res_btn").click( function() {
					edit("");
                });
                
                             
                   //结果集行的单击事件
			   	$( '#resultTable tr').live( 'click' ,function(){
			        var trObj = $(this);
			        var trObjs = $(this).parent().find( 'tr' ) ;
					var hasClassTrClick = trObj.hasClass("tr_click");	
					trObjs.each(
						function(){
							trObjs.removeClass("tr_click"); 
						}
					) ;
					$("#resultTable tr:even").find("td").removeClass("list_td2");
					$("#resultTable tr:odd").find("td").addClass("list_td2");	
					//奇数行清除原有样式
					if ($(this).get(0).rowIndex % 2 == 1) {
						 trObj.children().removeClass();
					}
					trObj.addClass("tr_click"); 
				});
			
				//item上下移动事件
				$("#upTableTd").click(function(){
			        $('#upTableTd').attr("disabled", true);
			        $('#downTableTd').attr("disabled", true);
				    var rowSelect;
				    if ($( '#resultTable .tr_click' ).length < 1) {
				       return;
				    }
					rowSelect = $( '#resultTable .tr_click' ) ;
				    if (rowSelect.get(0).rowIndex < 1) {
				       alert("At the top position already !");
				       return;
				    }
					rowUp = $( '#resultTable .tr_click' ).prev() ;
					exchangeRow( rowUp , rowSelect ) ;
				});
				//向下移动
				$("#downTableTd").click(function(){	
			        $('#upTableTd').attr("disabled", true);
			        $('#downTableTd').attr("disabled", true);
				    var rowSelect;
					if ($( '#resultTable .tr_click' ).length < 1) {
				      rowSelect = $("#resultTable tr:eq(0)") ;
				    } else {	   
					   rowSelect = $( '#resultTable .tr_click' ) ;
					}
					if (rowSelect.get(0).rowIndex+1 == $('#resultTable tr').length) {
				       alert("At the bottom position already !");
				       return;
				    }
					rowDown = rowSelect.next() ;
					exchangeRow( rowDown , rowSelect ) ;
				});
			
				//上下移动后， 回调的函数.只改变两行在服务端的值.
				function exchangeRow( rowTarget , rowSelf ) {
				    var ajaxData = "sessWOPKey=${param.sessWOPKey}";
				    var keyList = "keyList=";
				    var seqNoList = "seqNoList=";
				    var tagHtml = rowTarget.html();
				    rowTarget.html(rowSelf.html());
				    rowTarget.children(":eq(1)").html(rowTarget.get(0).rowIndex+1);
					ajaxData += "&" + keyList + rowTarget.children(":eq(0)").children().val();
					ajaxData += "&" + seqNoList + (rowTarget.get(0).rowIndex+1);
			    
				    //alert(rowTarget.html());
				    rowSelf.html(tagHtml);
				    rowSelf.children(":eq(1)").html(rowSelf.get(0).rowIndex+1);				    
				    ajaxData += "&" + keyList + rowSelf.children(":eq(0)").children().val();
				    ajaxData += "&" + seqNoList + (rowSelf.get(0).rowIndex+1);
			
			  
				    rowSelf.removeClass("tr_click"); 
					$("#resultTable tr:even").find("td").removeClass("list_td2");
					$("#resultTable tr:odd").find("td").addClass("list_td2");	
					//奇数行清除原有样式
					if (rowTarget.get(0).rowIndex % 2 == 1) {
						 rowTarget.children().removeClass();
					}
					rowTarget.addClass("tr_click"); 
					$.ajax({
						url:  "workorder_operation!updateComSeqNo.action",
						data: ajaxData,
						success:function(data){
							$('#upTableTd').attr("disabled", false);
			                $('#downTableTd').attr("disabled", false);
						},
						error:function(){
							alert("Exchange error");
							$('#upTableTd').attr("disabled", false);
			                $('#downTableTd').attr("disabled", false);
						},
						async:false
					});
				}
          });
        </script>
	</head>
	<body>
		<input type="hidden" name="sessWOPKey" id="sessWOPKey"
			value="${param.sessWOPKey}" />
		<table width="955" border="0" cellpadding="0" cellspacing="0"
			class="list_table">
			<tr id="title_tr">
				<th width="65">
					<div class="quote_dele">
						<input name="checkbox2" type="checkbox" id="check_all" />
						<img style="cursor: pointer"
							src="${global_image_url}file_delete.gif" id="check_del"
							alt="Delete" width="16" height="16" border="0" />
					</div>

					<div id="upTableTd" class="down_up">
						<img src="images/up.jpg" width="8" height="8" title="up" />
					</div>
					<div id="downTableTd" class="down_up2">
						<img src="images/down.jpg" width="8" height="7" title="down" />
					</div>
				</th>
				<th width="78">
					Seq No
				</th>
				<th width="110">
					Component Name
				</th>
				<th width="84">
					Description
				</th>
				<th width="52">
					Cost
				</th>
				<th width="73">
					Currency
				</th>
				<th width="83">
					Cost Basis
				</th>
				<th width="76">
					Per Item Qty
				</th>
				<th width="55">
					UOM
				</th>
				<th width="61">
					Qty Used
				</th>
				<th>
					Storage Location
				</th>
			</tr>
		</table>
		<div class="frame_box3" style="height: 240px">
			<table width="955" border="0" cellpadding="0" cellspacing="0"
				class="list_table" id="resultTable">
				<s:iterator value="#request.requestMap" status="st">
					<tr>
						<td width="65" align="center">
							<input type="checkbox" value="${key}" name="groupComId" />
						</td>
						<td width="78">
							${value.seqNo}
						</td>
						<td width="110">
							<a href="#" onclick="edit('${key}');">${value.componentName}</a>
						</td>
						<td width="84">
							${value.description}
						</td>
						<td width="52" align="right">
							${value.cost}
						</td>
						<td width="73">
							${value.currencyCode}
						</td>
						<td width="83">
							${value.costBasis}
						</td>
						<td width="76" align="right">
							${value.perItemQuantity}
						</td>
						<td width="55">
							${value.uom}
						</td>
						<td width="61" align="right">
							${value.quantityUsed}
						</td>
						<td>
							${value.storageLoc}
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div align="center" style="padding: 10px;">
			<input id="new_res_btn" type="button" class="style_botton"
				value="New" />
		</div>

	</body>
</html>