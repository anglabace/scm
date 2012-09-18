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
			   parent.window.location = "resource!edit.action?resourceId=" + resourceId + "&operation_method=view";
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
		        var param = "id=${param.id}"; 
		        if ($(":checkbox:gt(0):checked").length < 1) {
		           alert('Please select one at least!');
				   return;
		        }
		  		if (!confirm('Are you sure to delete?')) {
					return;
				}
				$(":checkbox:gt(0):checked").each(function() {
				   param += "&groupResId=" + $(this).val();
				   var tdObj = $(this).parent();
                   var trObj = tdObj.parent();  
                   trObj.remove();
				});					
				$("#resultTable tr:even").find("td").removeClass("list_td2");
				$("#resultTable tr:odd").find("td").addClass("list_td2");
		        $.ajax({
					type: "POST",
					url: "work_group!deleteSelect.action",
					data: param,
					success: function(data){
						//parent.location = parent.location;
					},
					error: function(msg){
						alert("Delete Group Resource failed !");
		                $('#select_btn').attr("disabled", false);		
					}
				}); 					
		    });//end of $("#check_del").click.

        
			
            $("#new_res_btn").click( function() {
				parent.$('#sel_res_dlg').dialog("option", "open", function() {	
              		 var htmlStr = '<iframe src="resource!selectForGroup.action?groupId=${param.id}" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
              		parent.$('#sel_res_dlg').html(htmlStr);
				});
				parent.$('#sel_res_dlg').dialog('open');
            });
                         
        
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
        				changeQty(thisObj.parent("td").parent("tr").attr("wgSourceKey"));
        			});
        }
      
      //改变quantity
        function changeQty(itemId) {
        	var itemTr = $("#resultTable").find("tr[wgSourceKey='" + itemId + "']");
        	var qtyInput = $(itemTr).find("[name='qtyTd']").find("input");
        	if (qtyInput.attr('value') == "" || isNaN(qtyInput.attr('value'))) {
        		alert("Please enter the work group resource quantity.");
        		return;
        	}
        	if (qtyInput.attr('value') <= 0) {
        		alert("The work group resource quantity must be greater than zero.");
        		return;
        	}
        	if(qtyInput.attr('value') >1000000) {
        		alert("The work group resource quantity must be lower than 1000000.");
        		return;
        	}
        	var quantity = qtyInput.attr('value');
        	// 更新session
        	$.ajax( {
        		type : "POST",
        		url : "work_group!updateQty.action",
        		dataType : "json",
        		data :  "resKey="+itemId+"&quantity="+quantity+"&id="+$("#id").val(),
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
	<input type="hidden" name="id" id="id"
			value="${param.id}" />
					<table width="955" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<tr id="title_tr">
							<th width="46">
								<div align="left">
									<input name="checkbox2" type="checkbox" id="check_all" />
									<img style="cursor: pointer"
										src="${global_image_url}file_delete.gif" id="check_del"
										alt="Delete" width="16" height="16" border="0" />
								</div>
							</th>
							<th width="80">
								Resource No
							</th>
							<th width="200">
								Resource Name
							</th>
							<th width="50">
								Quantity
							</th>
							<th width="480">
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
							<s:iterator value="groupResourceMap">
							<s:if test="value!=null">
								<tr wgSourceKey="${key}">
									<td width="46" align="center">
										<input type="checkbox" value="${key}"
											name="groupResId" />
									</td>
									<td width="80">
									${value.resource.resourceNo}
									</td>
									<td width="200">
										<a href="javascript:void(0)"
											onclick="edit('${value.resource.resourceId}')">${value.resource.name}</a>
									</td>
									<td width="50" align="right">
										<div name="qtyTd">${value.quantity}</div>
									</td>
									<td width="480">
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