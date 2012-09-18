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
			function edit(operationId) {
			   parent.window.location = "operation!edit.action?id="+operationId+"&operation_method=view"
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
                    var param = "sessRouteId=" + $("#sessRouteId").val();			   
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
			        $.ajax({
						type: "POST",
						url: "route!deleteOperation.action",
						dataType: 'text',
						data: param,
						success: function(data){
						   // window.location = window.location;
						   	$("#resultTable :checkbox:checked").each(function() {
					           var trObj = $(this).parent().parent();
					           $("#resultTable").get(0).deleteRow(trObj.get(0).rowIndex); 
					        });	
						    deleteCallBack();
						   
						},
						error: function(msg){
							alert("Select failed !");
			                $('#select_btn').attr("disabled", false);		
						}
					}); 
	
			    });//end of $("#check_del").click.

               //验证form的逻辑
			   $('#route_form').validate({
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
						 "route.name": { required:true}
					 },
					 messages: {
						 "route.name": { required : "Please enter the name !" }
					 },
					 errorPlacement: function(error, element) {
					 }
				});

                //绑定保存按钮事件.
                $("#save_btn").click (function() {
                   if (! $('#route_form').valid()) {
                      return false;
                   }
                   var formStr = $('#route_form').serialize();
                   $("#resultTable :checkbox").each(function() {
                       formStr += "&groupIdList=" + $(this).val();
                   });
                   $('#save_btn').attr("disabled", true);
                   $.ajax({
						type: "POST",
						url: "route!save.action",
						data: formStr,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){//有错误信息.
				 	           $('#save_btn').attr("disabled", false);				
							}else{                              
							  alert(data.message);
							  window.location = "route!search.action";
							  //window.history.go(-1);
							}
						},
						error: function(xhr, textStatus){
						   alert("failure");
				 	       $('#save_btn').attr("disabled", false);
						   return;
						}
					});
                
                });//end of {$("#save_btn").click};            
                
            	$('#sel_res_dlg').dialog({
					autoOpen: false,
					height: 440,
					width: 800,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
				
                $("#new_res_btn").click( function() {
					parent.$('#sel_res_dlg').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="operation!selectForRoute.action?pageName=route_operation_select&sessRouteId=${param.sessRouteId}" height="100%" width="800" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				         parent.$('#sel_res_dlg').html(htmlStr);
					});
					parent.$('#sel_res_dlg').dialog('open');
                });
                
                function deleteCallBack() {
				    var ajaxData = "sessRouteId=${param.sessRouteId}";
				    var keyList = "keyList=";
				    var seqNoList = "seqNoList=";
              		$('#resultTable tr').each(
						function(){
   				            $(this).children(":eq(1)").html($(this).get(0).rowIndex+1);
   				            ajaxData += "&" + keyList + $(this).children(":eq(0)").children().val();
   				            ajaxData += "&" + seqNoList + ($(this).get(0).rowIndex+1);
						}
					) ;
					$("#resultTable tr:even").find("td").removeClass("list_td2");
					$("#resultTable tr:odd").find("td").addClass("list_td2");	
					$.ajax({
						url:  "route!updateSeqNo.action",
						data: ajaxData,
						success:function(data){
							//window.location = window.location;
						},
						error:function(){
							alert("Exchange error");
						},
						async:false
					});
                }
                             
            
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
	$( '#upTableTd' ).click(function(){
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
	$('#downTableTd' ).click(function(){	
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

				//上下移动后， 回调的函数.
				function exchangeRow( rowTarget , rowSelf ) {
				    var ajaxData = "sessRouteId=${param.sessRouteId}";
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
						url:  "route!updateSeqNo.action",
						data: ajaxData,
						success:function(data){
							//window.location = window.location;
						},
						error:function(){
							alert("Exchange error");
						},
						async:false
					});
				}
            });
        </script>
	</head>
	<body>
		<input type="hidden" name="sessRouteId" id="sessRouteId"
			value="${param.sessRouteId}" />
		<table width="990" border="0" cellpadding="0" cellspacing="0"
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
				<th width="100">
					Seq No
				</th>
				<th width="253">
					Operation Name
				</th>
				<th width="100">
					Status
				</th>
				<th width="100">
					Setup Time
				</th>
				<th width="100">
					Runtime
				</th>
				<th width="100">
					UOM
				</th>
				<th>
					Work Center
				</th>
			</tr>
		</table>
		<div class="list_box" style="height: 240px;overflow: scroll;">
			<table width="990" border="0" cellpadding="0" cellspacing="0"
				class="list_table" id="resultTable">
				<s:iterator value="roMap">
				<s:if test="value!=null">
					<tr>
						<td width="65" align="center">
							<input type="checkbox" value="${key}" id="${value.operation.id}" name="routeOperationId" />
						</td>
						<td width="100">
							${value.seqNo}
						</td>
						<td width="253">
							<a href="operation!edit.action?id=${value.operation.id}" target="_parent">${value.operation.name}</a>
						</td>
						<td width="100">
							${value.operation.status}
						</td>
						<td width="100">
							${value.operation.setupTime}
						</td>
						<td width="100">
							${value.operation.runTime}
						</td>
						<td width="100">
							${value.operation.uom}
						</td>					
						<td>
							${value.operation.workCenterName}
						</td>
					</tr>
				</s:if>
					
				</s:iterator>
			</table>
		</div>
		<div align="center" style="padding: 10px;">
			<saveButton:saveBtn parameter="${operation_method}"
				disabledBtn='<input type="button" name="Submit123" value="New" class="style_botton" disabled="disabled" />'
				saveBtn='<input type="button" name="Submit123" value="New" class="style_botton" id="new_res_btn" />'
			/> 
		</div>
	</body>
</html>