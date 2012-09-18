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
			function edit(resourceId) {
				var operationId=$("#sessOperationId").val();
				var centerId=parent.$("#workCenterId_sel").val();
			   parent.window.location = "resource!edit.action?resourceId=" + resourceId + "&operationId="+operationId+"&centerId="+centerId+"&operation_method=view&workGroupShow=true";
			}			
				
            $(function() {
            	$('[name="qtyTd"]').bind("click", qtyTdClick);
			    $("#resultTable tr:odd").find("td").addClass("list_td2");
			
			    //复选框绑定: 全选|全不选
			    $("#check_all").click( function() {
			       $(":checkbox").each(function() {
			          this.checked = $("#check_all").get(0).checked;
			       });      
			    });
			    
			    //删除选中的checkbox.
			    $("#check_del").click( function() {    
                    var param = "sessOperationId=" + $("#sessOperationId").val();			   
			        if ($("#resultTable :checkbox:checked").length < 1) {
			           alert('Please select one at least!');
					   return;
			        }
			  		if (!confirm('Are you sure to delete?')) {
						return;
					}
					$("#resultTable :checkbox:checked").each(function() {
					   param += "&delResIdList=" + $(this).val();
					});		
			        $.ajax({
						type: "POST",
						url: "operation!deleteResource.action",
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

                //绑定保存按钮事件.
                $("#save_btn").click (function() {
                   if (! $('#operation_form').valid()) {
                      return false;
                   }
                   var formStr = $('#operation_form').serialize();
                   $("#resultTable :checkbox").each(function() {
                       formStr += "&groupIdList=" + $(this).val();
                   });
                   $('#save_btn').attr("disabled", true);
                   $.ajax({
						type: "POST",
						url: "operation!save.action",
						data: formStr,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){//有错误信息.
				 	           $('#save_btn').attr("disabled", false);				
							}else{                              
							  alert(data.message);
							  window.location = "operation!search.action";
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
					width: 700,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
				
                $("#new_res_btn").click( function() {
					parent.$('#sel_res_dlg').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="resource!selectForGroup.action?pageName=operation_resource_select&sessOperationId=${param.sessOperationId}" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				         parent.$('#sel_res_dlg').html(htmlStr);
					});
					parent.$('#sel_res_dlg').dialog('open');
                });
                
                function deleteCallBack() {
				    var ajaxData = "sessOperationId=${param.sessOperationId}";
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
						url:  "operation!updateSeqNo.action",
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
				    var ajaxData = "sessOperationId=${param.sessOperationId}";
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
						url:  "operation!updateSeqNo.action",
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
            
          //qty td click function
            function qtyTdClick() {
            	var thisObj = $(this);
            	var tableTdQty = thisObj;
            	var quantity = tableTdQty.html().replace('&nbsp;', '');
            	if (tableTdQty.html().search('INPUT') != -1
            			|| tableTdQty.html().search('input') != -1) {
            		return;
            	}
            	//display input element
            	tableTdQty
            			.html(
            					'<input name="qty1" id="qty1" type="text" class="NFText" value="' + quantity + '" size="1" />')
            			.find('input').change( function() {
            				changeQty(thisObj.parent("td").parent("tr").attr("opSourceKey"));
            			});
            }
          
          //改变quantity
            function changeQty(itemId) {
            	var itemTr = $("#resultTable").find("tr[opSourceKey='" + itemId + "']");
            	var qtyInput = $(itemTr).find("[name='qtyTd']").find("input");
            	if (qtyInput.attr('value') == "" || isNaN(qtyInput.attr('value'))) {
            		alert("Please enter the operation resource quantity.");
            		return;
            	}
            	if (qtyInput.attr('value') <= 0) {
            		alert("The operation resource quantity must be greater than zero.");
            		return;
            	}
            	if(qtyInput.attr('value') >1000000) {
            		alert("The operation resource quantity must be lower than 1000000.");
            		return;
            	}
            	var quantity = qtyInput.attr('value');
            	// 更新session
            	$.ajax( {
            		type : "POST",
            		url : "operation!updateQty.action",
            		dataType : "json",
            		data :  "resKey="+itemId+"&quantity="+quantity+"&sessOperationId="+$("#sessOperationId").val(),
            		async : false,
            		error : function() {
            			alert("System error! Please contact system administrator for help.");
            		},
            		success : function(data) {
            			parent.document.getElementById("resource_list_frame").src = parent.$("#resource_list_frame").attr("src");
            		}
            	});
            }
        </script>
	</head>
	<body>
		<input type="hidden" name="sessOperationId" id="sessOperationId"
			value="${param.sessOperationId}" />
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
				<th width="80">
					Seq No
				</th>
				<th width="200">
					Resource Name
				</th>
				<th width="50">
					Quantity
				</th>
				<th width="463">
					Description
				</th>
				<th>
					Status
				</th>
			</tr>
		</table>
		<div class="list_box" style="height: 240px;overflow: scroll;">
			<table width="955" border="0" cellpadding="0" cellspacing="0"
				class="list_table" id="resultTable">
				<s:iterator value="operationResMap" status="st">
				<s:if test="value!=null">
					<tr opSourceKey="${key}">
						<td width="65" align="center">
							<input type="checkbox" value="${key}" name="groupResId" />
						</td>
						<td width="80" align="center">
							${value.seqNo}
						</td>
						<td width="200">
							<a href="javascript:void(0)" onclick="edit('${value.resource.resourceId}')"
											 target="_parent">${value.resource.name}</a>
						</td>
						<td width="50" align="right">
							<div name="qtyTd">${value.quantity}</div>
						</td>
						<td width="463">
							${value.resource.description}
						</td>
						<td>
							${value.resource.status}
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