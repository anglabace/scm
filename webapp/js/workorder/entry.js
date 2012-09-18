function changeOrder() {
   $.ajax({
		type: "POST",
		url: "workorder_entry!selectOrder.action",
		data: "id=" + $.trim($("#order_orderNo_text").val())+"&orderNo="+ $.trim($("#order_chinaOrderNo_text").val()),
		dataType: 'json',
		success: function(jsonObj, textStatus){
		    if (jsonObj.projectSupport != null) {
		      $("#order_projectSupport_text").val(jsonObj.projectSupport);
		    }
		    if (jsonObj.salesContact != null) {
		      $("#order_salesContact_text").val(jsonObj.salesContact);
		    }
		    if (jsonObj.techSupport != null) {
			      $("#order_techSupport_text").val(jsonObj.techSupport);
			}
		    if(jsonObj.priority!=null) {
		    	$("#workOrder_priority").val(jsonObj.priority);
		    	$("#hidden_priority").attr("value",jsonObj.priority);
		    }
		    $("#companyId_hidden").val(jsonObj.companyId);
		    $("#so_item_sel").get(0).options.length = 0;
		    if (jsonObj.itemList != null && jsonObj.itemList.length >= 1) {
			    $("#so_item_sel").get(0).options.add(new Option("Please select...", ""));
			    for (var i=0; i<jsonObj.itemList.length; i++) {
			         $("#so_item_sel").get(0).options.add(new Option(jsonObj.itemList[i].name, jsonObj.itemList[i].value));
			    }
		    }	
		},
		error: function(xhr, textStatus){
		   alert("Select error !");
		    $("#order_projectSupport_text").val("");
		    $("#order_salesContact_text").val("");
		    $("#order_techSupport_text").val("");
		   return;
		}
    });  
}

function selectProduct(type, id) {
     $.ajax({
		type: "POST",
		url: "workorder_entry!selectProduct.action",
		data: "id=" + id + "&type=" + type,
		dataType: 'json',
		success: function(jsonObj, textStatus){
		     $("#itemType_hidden").val(jsonObj.itemType);
		     $("#itemName_hidden").val(jsonObj.name);
		     $("#clsId_hidden").val(jsonObj.clsId);
		     $("#itemType_hidden").val(jsonObj.type);
		     $("#catalogNo_txt").val(jsonObj.catalogNo);
		     $("#catalogNo_txt").val(jsonObj.catalogNo+"-"+jsonObj.name);
		     //$("#quantity_txt").val(jsonObj.quantity);
		     $("#qtyUom_txt").val(jsonObj.qtyUom);
		     if (jsonObj.size != null) {
		       $("#size_txt").val(jsonObj.size);
		     }
		     if (jsonObj.sizeUom != null) {
		       $("#sizeUom_txt").val(jsonObj.sizeUom);						
		     }
		},
		error: function(xhr, textStatus){
		   alert("failure");
		   return;
		}
	 });   
}


function deleteFile() {
	var docIds = "";
	var indexArray = new Array();
	$("#fileListTable input[type='checkbox']").each(function(){
		if(this.checked) {
			if(docIds=="") {
				docIds = docIds +this.value;
			} else {
				docIds = docIds +","+this.value;
			}
			var trObj = $(this).parent().parent();
			indexArray.push(trObj.get(0).rowIndex);
		}
	});
   if(docIds=="") {
	   alert("Please select one file at least.");
	   return;
   }
   if (confirm("Are you confirm delete these files ?")) {   
        $.ajax({
			type: "POST",
			url: "workorder_entry!deleteFile.action",
			data: "sessId=" + $("#sessId").val() + "&docIds=" + docIds,
			success: function(data, textStatus) {
				alert("Success");
				for(var i =0;i<indexArray.length;i++) {
					$("#save_form").find("#fileListTable").get(0).deleteRow(indexArray[0]); 
				}
			},
			error: function(xhr, textStatus){
			   alert("failure");
			}
	   });   

   }
	
}

//获取附件列表html字符串
function getFileListHtml(documentList){
	  var tmpStr = "";
 	  $.each(documentList, function(key, value) {
		    tmpStr += '<tr>';
		    tmpStr += '<td>';
			tmpStr += '<input type="checkbox" value="'+key+'"/>';
			tmpStr += '</td>';
			tmpStr += '<td>';
			tmpStr += '<a  href="javascript:void(0);" onclick="downLoadFile(\''+value["filePath"]+'\',\''+value["docName"]+'\')">'+value.docName+'</a>';
			tmpStr += '</td>';
			tmpStr += '<td>Uploaded By<input name="textfield32255" value="'+value.modifyUser+'" type="text" class="NFText" size="20" readonly="readonly"/>';
			tmpStr += '</td>';
			var now = new Date().getFullYear()+"-"; 
			now = now + (new Date().getMonth()+1)+"-";
			now = now + new Date().getDate()+" ";
			tmpStr += '<td>Uploaded Date<input name="textfield3224" type="text" class="NFText" size="20" readonly="readonly" value="'+now+'" />';
			tmpStr += '</td>';
			tmpStr += '</tr>';   
      });
	  return tmpStr;
}
//发送邮件
function sendEmailForAssignGroup() {
	var workGroup = $("#workGroupIds").val();
	var searchNoteType = "VENDOR_CONFIRM_EMAIL";
	$('#instruction_dlg').dialog('option', "open", function() {
		var url = orderquoteObj.url("editNote") + "&searchNoteType="+searchNoteType+"&workGroupIds="+workGroup;
		var htmlStr = '<iframe src="'+url+'" height="350" width="579" scrolling="auto" style="border:0px;" frameborder="0"></iframe>';
		$('#instruction_dlg').html(htmlStr);
	});
	$('#instruction_dlg').dialog('open');
}

//发送邮件
function sendEmailForCreateWo() {
	var workGroup = $("#workGroupIds").val();
	var searchNoteType = "VENDOR_CONFIRM_EMAIL";
	$('#instruction_dlg').dialog('option', "open", function() {
		var url = orderquoteObj.url("editNote") + "&searchNoteType="+searchNoteType+"&workGroupIds="+workGroup;
		var htmlStr = '<iframe src="'+url+'" height="350" width="579" scrolling="auto" style="border:0px;" frameborder="0"></iframe>';
		$('#instruction_dlg').html(htmlStr);
	});
	$('#instruction_dlg').dialog('open');
}

//View Production Instruction
function view_production_ins() {
	var sessOrderNo = $("#order_orderNo_text").val();
	if(sessOrderNo=="") {
		alert("please select a Sales Order No");
		return;
	}
	$('#production_instruction_dlg').dialog('option',"open",function(){
		var url = "order_note!viewProductionIns.action?sessOrderNo="+sessOrderNo+"&searchNoteType=PRODUCTION";
		var htmlStr = '<iframe src="'+url+'" height="350" width="780" scrolling="auto" style="border:0px;" frameborder="0"></iframe>';
		$('#production_instruction_dlg').html(htmlStr);
	});
	$('#production_instruction_dlg').dialog('open');
}

$(function() { 
    //清除Status为'Received'的选项
    var count = $("#status_sel").get(0).options.length;        
    for(var i=0; i<count; i++) {
        if($("#status_sel").get(0).options[i].value == 'Received') {
            $("#status_sel").get(0).remove(i);
            break;
        }
    }  
      
	$("#upload_btn").click( function(){
		if(! $("#upload_file").val()){
			alert("Please select one file !");
			return;
		}
		$('#upload_btn').attr("disabled", true);
		var upURL = "workorder_entry!uploadFile.action" +"?sessId="+ $("#sessId").val() + "&docType=" + $('#pdt_docType_sel').fieldValue(); 
		//ajax form post
		var options = {
			success:function(data){
				var documentList = data;
				var tmpStr = getFileListHtml(documentList);
				$("#save_form").find("#fileListTable").append(tmpStr);
				var file = $("#upload_file");
				file.after(file.clone().val(""));
				file.remove();
		        $('#upload_btn').attr("disabled", false);
			},
			error: function(data){
				if(data.responseText){
					alert(data.responseText);
				}else{
					alert("Error");
				}
			},
			dataType:"json",
			resetForm:false,
			url: upURL,
			type: "post"
		};
		$("#save_form").ajaxForm(options);
		$("#save_form").submit();
	}); 
 
   
    $("#save_btn").click( function() {
	    var formStr = $("#save_form").serialize();
	    var operStatus = $("#operStatus").val();
	    var plateId = $("#plateId").val();
	    if($("#so_item_sel").val()=='') {
	    	alert("Please select one item.");
	    	return;
	    }
        $('#save_btn').attr("disabled", true);
        $.ajax({
			type: "POST",
			url: "workorder_entry!save.action",
			data: formStr,
			dataType: 'json',
			success: function(data, textStatus){
				if(hasException(data)){//有错误信息.
			           $('#save_btn').attr("disabled", false);				
				}else{                              
				  alert(data.message);
				  $('#save_btn').attr("disabled", false);
				  isSaved = true;
				  if(plateId!=null&&plateId!="") {
					  window.location = "ds_plate!plateEdit.action?id="+plateId;
				  } else {
					  var url = "workorder_entry!edit.action?id="+data.id+"&operation_method=edit";
					  if(operStatus!='') {
						  url = url+"&operStatus="+operStatus+"&fromQc=fromQc";
					  }
					  window.location=url;
				  }
				  
				 // window.location.reload();
				//  if(operStatus==null||operStatus=="") {
				///	  window.location = "workorder_proc!search.action";
				//  } else if(operStatus=="QC"){
				//	  window.location = "workorder_qc!search.action";
			//	  } else {
				//	  history.go(-1);
			//	  }
				  //window.location = "workorder_proc!search.action";//parent.location = parent.location;
				}
			},
			error: function(xhr, textStatus){
			   alert("failure");
			       $('#save_btn').attr("disabled", false);
			}
	   });    
    });

    $("#save_task_btn").click( function() {
    	if($("#workGroupIds").attr("disabled")!="disabled") {
    		var workGroups = $("#workGroupIds").val();
    		if(workGroups=="") {
    			alert("please assign some workGroups");
    			return;
    		}
    	}
	    var formStr = $("#save_form").serialize();
        $('#save_task_btn').attr("disabled", true);
        $.ajax({
			type: "POST",
			url: "workorder_entry!saveTask.action",
			data: formStr,
			dataType: 'json',
			success: function(data, textStatus){
				if(hasException(data)){//有错误信息.
			           $('#save_task_btn').attr("disabled", false);				
				}else{                              
				  alert(data.message);
				  $('#save_task_btn').attr("disabled", false);
				  sendEmailForAssignGroup();
				  isSaved = true;
				 // window.location = "workorder_proc!searchTask.action";//parent.location = parent.location;
				}
			},
			error: function(xhr, textStatus){
			   alert("failure");
			       $('#save_task_btn').attr("disabled", false);
			}
	   });    
    });

	$('.ui-datepicker').each(function(){
		$(this).datepicker(
		{
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
			changeYear: true
		}); 
	});
   $("#resultTable tr:odd").find("td").addClass("list_td2");

   //选中某个WorkCenter
   $("#work_center_sel").change ( function() {
      if ($(this).val() == "") {
         $("#center_super_sel").empty();
         return;
      }
               $.ajax({
		type: "POST",
		url: "workorder_entry!selectWorkCenter.action",
		data: "id=" + $(this).val(),
		dataType: 'json',
		success: function(jsonObj, textStatus){
		    $("#center_super_sel").empty();
		    $("#center_super_sel").append("<option value=''>Please select...</option>");
		    if(!isNaN(jsonObj.superId)&&jsonObj.superId!=null&&jsonObj.superId!=0) {
		    	$("#center_super_sel").append("<option value='"+jsonObj.superId+"'>"+jsonObj.superName+"</option>");
		    }
			$("#work_group_sel").empty();
		    $("#group_super_sel").empty();
			if (jsonObj.groupList.length >= 1) {
			    $("#work_group_sel").append("<option value=''>Please select...</option>");
			    for (var i=0; i<jsonObj.groupList.length; i++) {
			    	if(!isNaN(jsonObj.groupList[i].id)&&jsonObj.groupList[i].id!=null&&jsonObj.groupList[i].id!=0) {
			    		 $("#work_group_sel").append("<option value='"+jsonObj.groupList[i].id+"'>"+jsonObj.groupList[i].name+"</option>");
			    	}
			        
			    }
			}
		},
		error: function(xhr, textStatus){
		   alert("Select error !");
		   return;
		}
	});      
   });

   //选中某个WorkGroup
   $("#work_group_sel").change ( function() {
      if ($(this).val() == "") {
         $("#group_super_sel").empty();
         return;
      }
               $.ajax({
		type: "POST",
		url: "workorder_entry!selectWorkGroup.action",
		data: "id=" + $(this).val(),
		dataType: 'json',
		success: function(jsonObj, textStatus){
		    $("#group_super_sel").empty();
		    $("#group_super_sel").append("<option value=''>Please select...</option>");
		    if(!isNaN(jsonObj.superId)&&jsonObj.superId!=null&&jsonObj.superId!=0) {
		    	$("#group_super_sel").append("<option value='"+jsonObj.superId+"'>"+jsonObj.superName+"</option>");
		    }
		},
		error: function(xhr, textStatus){
		   alert("failure");
		   return;
		}
	});      
   });
   
   //选中某个OrderItem			    
   $("#so_item_sel").change(function() {
      if ($(this).val() == "") {
         return;
      }
      var id = $.trim($("#order_orderNo_text").val());
      if(id=="") {
    	  id = $.trim($("#order_chinaOrderNo_text").val());
      }
      $.ajax({
    	type: "POST",
  		url: "workorder_entry!judgeItem.action",
  		data: "id=" + id + "&itemNo=" +$(this).val(),
  		dataType: 'json',
  		success: function(data, textStatus){
  			var parentWorkOrderNo = "";
  			if(data.message=='no') {
  				alert("Please use first this item father item creating work order.");
  				$("#so_item_sel").val("");
  				return;
  			} else if(data.message!=null&&data.message!='ok') {
  				$("#parentWorkOrderNo").attr(value,data.message);
  			}
  		},
  		error:function(xhr, textStatus) {
  		   alert("failure");
 		   return;
  		},
  		async:false
      });
      $.ajax({
		type: "POST",
		url: "workorder_entry!selectOrderItem.action",
		data: "id=" + id + "&itemNo=" +$(this).val(),
		dataType: 'json',
		success: function(data, textStatus){
		     var jsonObj = data.orderItem;
		     $("#itemName_hidden").val(jsonObj.name);
		     $("#clsId_hidden").val(jsonObj.clsId);
		     $("#itemType_hidden").val(jsonObj.type);
		     $("#catalogNo_txt").val(jsonObj.catalogNo);
		     $("#quantity_txt").val(jsonObj.quantity);
		     $("#qtyUom_txt").val(jsonObj.qtyUom);
		     $("#size_txt").val(jsonObj.size);
		     $("#sizeUom_txt").val(jsonObj.sizeUom);
		},
		error: function(xhr, textStatus){
		   alert("failure");
		   return;
		}
	 });      
   });
   $("#warehouse_sel").change(function() {
	      if ($(this).val() == "") {
	         return;
	      }
	      $.ajax({
			type: "POST",
			url: "workorder_entry!selectWarehouse.action",
			data: "centerId="+$("#work_center_sel").val()+"&warehouseId="+$("#warehouse_sel").val(),
			dataType: 'json',
			success: function(data, textStatus){
				$("#route_sel").empty();
				if(data.routeList.length>0) {
					for(var i =0;i<data.routeList.length;i++) {
				    	 var option = "<option value="+data.routeList[i].id+">"+data.routeList[i].name+"</option>";
				    	 $("#route_sel").append(option);
				     }
				     if(data.defaultRouteId!=undefined||data.defaultRouteId!=null) {
				    	 $("#route_sel").val(data.defaultRouteId);
				     }
				}
				$("#routing_apply_btn").trigger("click");
			},
			error: function(xhr, textStatus){
			   alert("failure");
			   return;
			}
		 });      
	   });

   /**页面触发选择QC Group**/
   $("#qc_group_sel").change(function() {
      if ($(this).val() == "") {
         $("#qc_clerk_sel").get(0).options.length = 0;
         return;
      }
      $.ajax({
		type: "POST",
		url: "workorder_entry!selectQaQcGroup.action",
		data: "id=" + $(this).val(),
		dataType: 'json',
		success: function(data, textStatus){
		    var jsonObj = data.qaClerkList;
		    $("#qc_clerk_sel").get(0).options.length = 0;
		    if (jsonObj != null && jsonObj.length >= 1) {
			    $("#qc_clerk_sel").get(0).options.add(new Option("Please select...", ""));
			    for (var i=0; i<jsonObj.length; i++) {
			         $("#qc_clerk_sel").get(0).options.add(new Option(jsonObj[i].superName, jsonObj[i].userId));
			    }
		    }						
		},
		error: function(xhr, textStatus){
		   alert("failure");
		   return;
		},
		async:false
	 });      
   });	
   
   /**页面触发选择QA Group**/
   $("#qa_group_sel").change(function() {
      if ($(this).val() == "") {
         $("#qa_clerk_sel").get(0).options.length = 0;
         return;
      }
      $.ajax({
		type: "POST",
		url: "workorder_entry!selectQaQcGroup.action",
		data: "id=" + $(this).val(),
		dataType: 'json',
		success: function(data, textStatus){
		    var jsonObj = data.qaClerkList;
		    $("#qa_clerk_sel").get(0).options.length = 0;
		    if (jsonObj != null && jsonObj.length >= 1) {
			    $("#qa_clerk_sel").get(0).options.add(new Option("Please select...", ""));
			    for (var i=0; i<jsonObj.length; i++) {
			         $("#qa_clerk_sel").get(0).options.add(new Option(jsonObj[i].superName, jsonObj[i].userId));
			    }
		    }						
		},
		error: function(xhr, textStatus){
		   alert("failure");
		   return;
		},
		async:false
	 });      
   });	
   
   
   /**
   *页面上Routing Apply按钮.
   */
   $("#routing_apply_btn").click( function() {
      $('#routing_apply_btn').attr("disabled", true);
      var id = $("#route_sel").val();
      if(id==null||id=="") {
    	  id = 0;
      }
      var sessId = $("#sessId").val();
      $.ajax({
			type: "POST",
			url: "workorder_operation!applyRouting.action",
			data: "id="+id + "&sessId=" + sessId,
			success: function(data, textStatus){
			     var srcURL = $("#operation_list_frame").attr("src");
			     frames["operation_list_frame"].location = frames["operation_list_frame"].document.URL;
			     $('#routing_apply_btn').attr("disabled", false);						
			},
			error: function(xhr, textStatus){
			   alert("failure");
	           $('#routing_apply_btn').attr("disabled", false);
			   return;
			}
	 }); 
   });
   
   //删除选中的checkbox.
   $("#check_del").click( function() {   
       var param = "id=${param.id}"; 
       //alert($("#resultTable :checkbox:checked").length);
       if ($("#resultTable :checkbox:checked").length < 1) {
           alert('Please select one at least!');
	       return;
       }
 	   if (!confirm('Are you sure to delete?')) {
		   return;
	   }
	   $("#resultTable :checkbox:checked").each(function() {
		      param += "&centerResId=" + $(this).val();
		      var tdObj = $(this).parent();
	          var trObj = tdObj.parent();  
	          if (tdObj.children(":hidden").length >0) {
		          $('<input type="hidden" name="dettachGroupIdList" value="' + $(this).val() + '" />').appendTo($("#workCenter_form"));
		      }
	          trObj.remove();
	   });			
	   $("#resultTable tr:even").find("td").removeClass("list_td2");
	   $("#resultTable tr:odd").find("td").addClass("list_td2");			
   });//end of $("#check_del").click.

                 
    //选择Sales Order.      
     $('#sel_order_dlg').dialog({
		autoOpen: false,
		height: 440,
		width: 700,
		modal: true,
		bgiframe: true,
		buttons: {
		}
    });


    
    $("#sel_chinaOrder_btn").click( function() {
	    $('#sel_order_dlg').dialog("option", "open", function() {	
           		 var htmlStr = '<iframe src="workorder_entry!showPageForSearchOrder.action" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         $('#sel_order_dlg').html(htmlStr);
	    });
	    $('#sel_order_dlg').dialog('open');
    });
    
    //选择Product/Service.
    $('#sel_product_dlg').dialog({
		autoOpen: false,
		height: 440,
		width: 700,
		modal: true,
		bgiframe: true,
		buttons: {
		}
    });
  
    //显示选择Product的对话框
    $("#sel_product_btn").click( function() {
	    $('#sel_product_dlg').dialog("option", "open", function() {	
           		 var htmlStr = '<iframe src="workorder_entry!showProductForSelect.action" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         $('#sel_product_dlg').html(htmlStr);
	    });
	    $('#sel_product_dlg').dialog('open');
    });

    //查看Product的ShipCondition, StorageCondition
    $("#view_product_btn").click( function() {   
       if ($("#catalogNo_txt").val() == '') {
           alert("Please select Product/Service first !");
           return;
       } 
	    $('#show_product_dlg').dialog("option", "open", function() {	
         	 var url = "workorder_entry!showProductShipAndStorage.action?catalogNo=" + $("#catalogNo_txt").val() + "&type=" + $("#itemType_hidden").val();
         	 var htmlStr = '<iframe src="' + url + '" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
             $('#show_product_dlg').html(htmlStr);
	    });
	    $('#show_product_dlg').dialog('open');       
    
    });
       
    //选择Operation.
    $('#sel_operation_dlg').dialog({
		autoOpen: false,
		height: 300,
		width: 500,
		modal: true,
		bgiframe: true,
		buttons: {
		}
    });
    
    //Quality Assurance
    $("#product_qa_sel").change( function() {
        if ($("#product_qa_sel").val() == 'Failed') {
		    $('#product_qa_dlg').dialog("option", "open", function() {		    
	            var htmlStr = '<iframe src="workorder_entry!showProductQaReason.action" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
	            $('#product_qa_dlg').html(htmlStr);
		    });
		    $('#product_qa_dlg').dialog('open');
        }
    });
    
    $("#document_qa_sel").change( function() {
        if ($("#document_qa_sel").val() == 'Failed') {
		    $('#document_qa_dlg').dialog("option", "open", function() {		    
	            var htmlStr = '<iframe src="workorder_entry!showDocumentQaReason.action" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
	            $('#document_qa_dlg').html(htmlStr);
		    });
		    $('#document_qa_dlg').dialog('open');
        }
    });
    
    $('#product_qa_dlg').dialog({
		autoOpen: false,
		height: 320,
		width: 550,
		modal: true,
		bgiframe: true,
		buttons: {
		}
    });
    
    $('#document_qa_dlg').dialog({
		autoOpen: false,
		height: 320,
		width: 550,
		modal: true,
		bgiframe: true,
		buttons: {
		}
    });    
    $('#show_product_dlg').dialog({
		autoOpen: false,
		height: 480,
		width: 700,
		modal: true,
		bgiframe: true,
		buttons: {
		}
    }); 
    
    $('#instruction_dlg').dialog({
		autoOpen: false,
		height: 400,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open:function(){
			var searchNoteType = $('#instruction_dlg').dialog("option", "searchNoteType");
			var url = orderquoteObj.url("editNote") + "&searchNoteType="+searchNoteType;
			var htmlStr = '<iframe src="'+url+'" height="350" width="579" scrolling="auto" style="border:0px;" frameborder="0"></iframe>';
			$('#instruction_dlg').html(htmlStr);
		},
		close:function() {
			  window.location = "workorder_proc!searchTask.action";
		}
	});
    $('#production_instruction_dlg').dialog({
		autoOpen: false,
		height: 400,
		width: 800,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
    $('#instruction_update_dlg').dialog({
		autoOpen: false,
		height: 300,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
    
    $('#innerOrder_operation_dlg').dialog({
		autoOpen: false,
		height: 600,
		width: 830,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
    
  //Assign Work Group.      
    $('#work_group_assign_dlg').dialog({
		autoOpen: false,
		height: 300,
		width: 700,
		modal: true,
		bgiframe: true,
		buttons: {
		}
   });
    
	$('#change_work_order_status').dialog({
		autoOpen: false,
		height: 250,
		width: 620,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
	
	$('#create_lot_no_dlg').dialog({
		autoOpen: false,
		height: 320,
		width: 680,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
	
	$('#update_request_log_dlg').dialog({
		autoOpen: false,
		height: 250,
		width: 620,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
                             
 });

/*
* add by zhanghuibin
* */
function tableList_downLoad(name){
    var delivery_date = $("#scheduleEnd_date").val();
    var work_order = $("#workOrderNo").val();
    var order_no = $("#order_orderNo_text").val();
    var so_item_no = $("input[name='workOrder\\.soItemNo']").val();
    var  param = "delivery_date=" + delivery_date + "&work_order="+ work_order+ "&order_no=" + order_no + "&so_item_no=" + so_item_no;
    var url = "";
    if(name == "op"){
        url = "workorder_entry!downOperationTable.action?";
    }else if(name == "qc"){
        url = "workorder_entry!downOperationTable.action?";
    }
    isSaved = true;
    $("#fileDownLoad").attr('action',url + param);
    $("#fileDownLoad").target = "downLoadFile";
    $("#fileDownLoad").submit();
}