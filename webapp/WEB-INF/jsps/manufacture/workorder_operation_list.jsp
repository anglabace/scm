<%@ include file="/common/taglib.jsp"%>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
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
		<script src="${global_js_url}jquery/ui/ui.datepicker.js"
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

    function edit_operation(sessWOPKey) {
        var formStr = parent.$("#save_form").serialize();
        parent.$('#save_btn').attr("disabled", true);
        $.ajax({
			type: "POST",
			url: "workorder_entry!save2Session.action",
			data: formStr,
			dataType: 'text',
			success: function(data, textStatus){
			    parent.isSaved = true;
			    var url = "workorder_operation!edit.action?sessId=${param.sessId}&sessWOPKey=" + sessWOPKey;
		        parent.location = url;				
            },
			error: function(xhr, textStatus){
			   parent.$('#save_btn').attr("disabled", false);
			   alert("failure");
			}
	    });  
    }
    
    function edit_innerOrder(orderNo) {
    	var formStr = "orderNo="+orderNo;
    	$.ajax({
			type: "POST",
			url: "order!isExistOrder.action",
			data: formStr,
			dataType: 'json',
			success: function(data){
				if(data.message=="success") {
					window.open("order!edit.action?orderNo="+orderNo);
				} else {
					alert("The order #"+orderNo+" does't exist!");
					return;
				}			
            },
			error: function(xhr){
			   alert("failure");
			}
	    });  
    }
    
    function view_operation(orderNo) {
    	parent.$('#innerOrder_operation_dlg').dialog("option", "open", function() {	
     		 var htmlStr = '<iframe src="workorder_operation!innerOrderWorkOrderOperationList.action?srcSoNo='+orderNo+'" height="100%" width="800" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
	         parent.$('#innerOrder_operation_dlg').html(htmlStr);
		});
		parent.$('#innerOrder_operation_dlg').dialog('open');
    }
				
$(function() {
	$("[name='commentDiv']").bind("click", commentTdClick);
    var work_status = parent.$("#status_sel").val();
    if (work_status == "New"||work_status == "In Production") {
        $("[name='customStartDateDiv']").bind("click", customerStartDateTdClick);
    }
 	if($("#woStatus").val()!="New") {
        $('#upTableTd').attr("disabled", true);
        $('#downTableTd').attr("disabled", true);
 	}
 	$("#new_wo_btn").attr("disabled", true);
 	if(parent.$("#status_sel").val()=="New"||parent.$("#status_sel").val()=="In Production") {
 		$("#new_wo_btn").attr("disabled", false);
 	}
 	if(parent.$("#operStatus").val()=="QC") {
 		$("#operate_wo_btn").attr("disabled", true);
 		$("[name='commentDiv']").unbind("click");
 		$("[name='customStartDateDiv']").unbind("click");
 	}
 	if(parent.$("#workOrderNo").val()=="") {
 		$("#new_wo_btn").attr("disabled", true);
 		$("#operate_wo_btn").attr("disabled", true);
 	}
    $("#resultTable tr:odd").find("td").addClass("list_td2");
    
    $(".pickdate").live("mousemove",function(e){                
        $(this).datepicker({
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
			changeYear: true,
			yearRange: '-100:+0'
		});
    });

    //复选框绑定: 全选|全不选
    $("#check_all").click( function() {
       $(":checkbox").each(function() {
          this.checked = $("#check_all").get(0).checked;
       });      
    });
			    
    //删除选中的checkbox.
    $("#check_del").click( function() {    
       var param = "sessId=" + $("#sessId").val();			   
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
    $.ajax ({
			type: "POST",
			url: "workorder_operation!deleteOperation.action",
			dataType: 'text',
			data: param,
			success: function(data){
			    window.location = window.location;
			   	return;		   
			},
			error: function(msg){
				alert("Select failed !");
	               $('#select_btn').attr("disabled", false);		
			}
	 }); 
   });//end of $("#check_del").click.	

                      
                
   $('#sel_res_dlg').dialog({
		autoOpen: false,
		height: 440,
		width: 700,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
   
   $("#update_status_dlg").dialog({
		autoOpen: false,
		height: 300,
		width: 650,
		modal: true,
		bgiframe: true,
		buttons: {
		}
   }
	);
	
	/**New Operation为WorkOrder 选择一个新的Operation.
	*/
    $("#new_wo_btn").click( function() {
        if (parent.$("#work_center_sel").val() == "") {
          alert("Please select a work center !");
          return;
        }
	    var formStr = parent.$("#save_form").serialize();
        parent.$('#save_btn').attr("disabled", true);
        $.ajax({
			type: "POST",
			url: "workorder_entry!save2Session.action",
			data: formStr,
			success: function(data, textStatus){
				 parent.isSaved = true;
                 parent.location = "workorder_operation!selectOperation.action?sessId=${param.sessId}&id="+parent.$("#work_center_sel").val();		
			},
			error: function(xhr, textStatus){
			   parent.$('#save_btn').attr("disabled", false);
			   alert("failure");
			}
	    });  
        return;
    });  
	
    $("#operate_wo_btn").click(function(){
    	 if ($("#resultTable :checkbox:checked").length < 1) {
    	        alert('Please select one at least!');
    		       return;
    	    }
    	 $('#update_status_dlg').dialog("option", "open", function() {	
      		 var htmlStr = '<iframe id="updateStatusFrame" src="workorder_operation!batchOperationDlg.action" height="100%" width="100%" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
             $('#update_status_dlg').html(htmlStr);
    	});
    	 $("#update_status_dlg").dialog("open");
    	
    });
         
    //结果集行的单击事件
   	$( '#resultTable tr').live( 'click' ,function(){
        var trObj = $(this);
        if(this.id=='title_tr') {
        	return;
        }
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
	    if (rowSelect.get(0).rowIndex < 2) {
	       alert("At the top position already !");
		   $('#upTableTd').attr("disabled", false);
           $('#downTableTd').attr("disabled", false);
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
	      rowSelect = $("#resultTable tr:eq(1)") ;
	    } else {	   
		   rowSelect = $( '#resultTable .tr_click' ) ;
		}
		if (rowSelect.get(0).rowIndex+1 == $('#resultTable tr').length) {
	       alert("At the bottom position already !");
		   $('#upTableTd').attr("disabled", false);
           $('#downTableTd').attr("disabled", false);
	       return;
	    }
		rowDown = rowSelect.next() ;
		exchangeRow( rowDown , rowSelect ) ;
	});

	//上下移动后， 回调的函数.只改变两行在服务端的值.
	function exchangeRow( rowTarget , rowSelf ) {
	    var ajaxData = "sessId=${param.sessId}";
	    var keyList = "keyList=";
	    var seqNoList = "seqNoList=";
	    var tagHtml = rowTarget.html();
	    rowTarget.html(rowSelf.html());
		if(rowSelf.find(":checkbox").get(0).checked) {
			rowTarget.find(":checkbox").get(0).checked=true;
		}
	    rowTarget.children(":eq(1)").html(rowTarget.get(0).rowIndex);
		ajaxData += "&" + keyList + rowTarget.children(":eq(0)").children().val();
		ajaxData += "&" + seqNoList + (rowTarget.get(0).rowIndex);
    
	    //alert(rowTarget.html());
	    rowSelf.html(tagHtml);
	    rowSelf.children(":eq(1)").html(rowSelf.get(0).rowIndex);				    
	    ajaxData += "&" + keyList + rowSelf.children(":eq(0)").children().val();
	    ajaxData += "&" + seqNoList + (rowSelf.get(0).rowIndex);

  
	    rowSelf.removeClass("tr_click"); 
		$("#resultTable tr:even").find("td").removeClass("list_td2");
		$("#resultTable tr:odd").find("td").addClass("list_td2");	
		//奇数行清除原有样式
		if (rowTarget.get(0).rowIndex % 2 == 1) {
			 rowTarget.children().removeClass();
		}
		rowTarget.addClass("tr_click"); 
		$.ajax({
			url:  "workorder_operation!updateSeqNo.action",
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

function modifyStatus() {
	var param = "sessId=" + $("#sessId").val();			   
   
    var comments = $("#updateStatusFrame").contents().find("#comments").val();
	var status = $("#updateStatusFrame").contents().find("#status_sel_dlg").val();
	var customStartDate = $("#updateStatusFrame").contents().find("#customStartDate").val();
	if(status!=""||customStartDate!="") {
		param = param+"&comments="+comments+"&woStatus="+status+"&customStartDate="+customStartDate;
	    $("#resultTable :checkbox:checked").each(function() {
	 	   param += "&delROIdList=" + $(this).val();
	 	});		
	     $.ajax ({
	 			type: "POST",
	 			url: "workorder_operation!batchOperate.action",
	 			dataType: 'json',
	 			data: param,
	 			success: function(data){
	 				$("#update_status_dlg").dialog("close");
	 			   window.location.href=window.location.href;
	 			    if(data.message!='error') {
	 			    	alert("Batch operation successful.");
	 			    	if(data.message=='complete') {
	 			    		parent.$("#status_sel").val("Production Complete");
	 			    		parent.$("#hidden_status").attr("value","Production Complete");
	 			    	}
	 			    	if(data.message=='inprogress') {
	 			    		parent.$("#status_sel").val("In Production");
	 			    		parent.$("#hidden_status").attr("value","In Production");
	 			    	}
	 			    } else {
	 			    	alert("Batch operation failing.");
	 			    }
	 			   	return;		   
	 			},
	 			error: function(msg){
	 				$("#update_status_dlg").dialog("close");
	 				alert("batch failed !");		
	 			}
	 	 }); 
	} else {
		$("#update_status_dlg").dialog("close");
	}
	
}

function commentTdClick() {
	var thisObj = $(this);
	var tableTdComment = thisObj;
	var comment = tableTdComment.html().replace('&nbsp;', '');
	if (comment.search('INPUT') != -1
			|| comment.search('input') != -1) {
		return;
	}
	//display input element
	tableTdComment
			.html(
					'<input name="comment" id="comment" type="text" class="NFText" value="' + comment + '" size="24" />')
			.find('input').change( function() {
				changeComment(thisObj.parent("td").parent("tr").find("input:checkbox").val(),thisObj.parent("td").parent("tr").find("#comment").val());
			});
}

function customerStartDateTdClick() {
	if($("#customerStartDate")!=null) {
		$("#customerStartDate").parent().html($("#customerStartDate").val());
	}
	var thisObj = $(this);
	var tableTdComment = thisObj;
	var customerStartDate = tableTdComment.html().replace('&nbsp;', '');
	if (customerStartDate.search('INPUT') != -1
			|| customerStartDate.search('input') != -1) {
		return;
	}
	//display input element
	tableTdComment
			.html(
					'<input name="customerStartDate" id="customerStartDate" type="text" class="NFText pickdate" value="' + customerStartDate + '" size="24" />');
	tableTdComment.find('input').change( function() {
				changeCustomerStartDate(thisObj.parent("td").parent("tr").find("input:checkbox").val(),$("#customerStartDate").val(),customerStartDate);
			});

	
}

function changeComment(woOperationKey,commentValue) {
	// 更新session
	$.ajax( {
		type : "POST",
		url : "workorder_operation!changeOperationColumn.action",
		dataType : "json",
		data :  "sessId="+$("#sessId").val()+"&sessWOPKey="+woOperationKey+"&comments="+commentValue,
		async : false,
		error : function() {
			alert("System error! Please contact system administrator for help.");
		},
		success : function(data) {
			if(hasException(data)){
				
			}else{
				if(data.message!=null) {
					alert(data.message);
					window.location.reload();
					return;
				}
			}
		}
	});
}

function changeCustomerStartDate(woOperationKey,customerStartDateValue,originalStartDateValue) {
	// 更新session
	$.ajax( {
		type : "POST",
		url : "workorder_operation!changeOperationColumn.action",
		dataType : "json",
		data :   "sessId="+$("#sessId").val()+"&sessWOPKey="+woOperationKey+"&customStartDate="+customerStartDateValue,
		async : false,
		error : function() {
			alert("System error! Please contact system administrator for help.");
		},
		success : function(data) {
			if(hasException(data)){
				
			}else{
				if(data.message!=null) {
					alert(data.message);
					window.location.reload();
					
					
				}
			}
		}
	});
}
        </script>
	</head>
	<body>
		<input type="hidden" name="sessId" id="sessId"
			value="${param.sessId}" />
		<input type="hidden" name="woStatus" id="woStatus"
			value="${woStatus}" />
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
			<div class="list_box" style="width:100%;height: 240px;overflow: auto;">
				<table  border="0" cellpadding="0" cellspacing="0" id="resultTable"
					class="list_table" width="100%">
					<tr id="title_tr">
						<th width="6%">
							<div class="quote_dele">
								<input name="checkbox2" type="checkbox" id="check_all" />
								<img style="cursor: pointer"
									src="${global_image_url}file_delete.gif" id="check_del"
									alt="Delete" width="16" height="16" border="0" />
							</div>
		
							<div id="upTableTd" class="down_up">
								<img src="images/up.jpg"  height="8" title="up" />
							</div>
							<div id="downTableTd" class="down_up2">
								<img src="images/down.jpg"  height="7" title="down" />
							</div>
						</th>
						<th width="5%">
							Seq No
						</th>
						<th width="10%">
							Operation Name
						</th>
						
						<th width="5%">
							Status
						</th>
						
						<th width="4%">
							Setup Time
						</th>
						<th width="4%">
							Runtime
						</th>
						<th width="4%">
							UOM
						</th>
						<th>
						Comment
						</th>
						<th width="8%">
						Sch Start Date
						</th>
						<th width="8%">
						Sch Complete Date
						</th>
						<th width="8%">
						Act Start Date
						</th>
						<th width="8%">
						Act Complete Date
						</th>
							<th width="8%">
						Cust Start Date
						</th>
						<th width="8%">
						Cust Complete Date
						</th>
					</tr>
					
					<s:iterator value="woOperationMap">
					<tr>
						<td width="6%" align="center">
							<input type="checkbox" value="${key}" name="relId" />
						</td>
						<td width="5%">
							${value.seqNo}
						</td>
						<td width="10%">
						    <c:if test="${empty value.internalOrderNo}">
							   <a href="javascript:edit_operation('${key}')">${value.operation.name}</a>
							</c:if>
						    <c:if test="${! empty value.internalOrderNo}">
							   <a href="javascript:edit_innerOrder('${value.internalOrderNo}')">${value.internalOrderNo}</a>
							</c:if>
						</td>
					
						<td width="5%">
							${value.status}
							 <c:if test="${! empty value.internalOrderNo}">
							<input type="button" name="Submit" value="View" class="style_botton" onclick="view_operation('${value.internalOrderNo}');"/>
							</c:if>
						</td>
						
						<td width="4%">
							<c:if test="${empty value.operation.setupTime}">
							   0
							</c:if>
							<c:if test="${! empty value.operation.setupTime}">
							  ${value.operation.setupTime}
							</c:if>
						</td>
						<td width="4%">
							<c:if test="${empty value.operation.runTime}">
							   0
							</c:if>
							<c:if test="${! empty value.operation.runTime}">
							  ${value.operation.runTime}
							</c:if>
						</td>
						<td width="4%">
							${value.operation.uom}
						</td>
						<td>
						<div name="commentDiv">
							&nbsp;
							<s:if test="value.operation.comment!=null&&value.operation.comment.length()>23"><s:property value="value.operation.comment.substring(0,23)"/>...</s:if>
							<s:else><s:property value="value.operation.comment"/></s:else>
						   
						  </div>
						
							
							
						</td>					
						<td width="8%">
						<s:date format="yyyy-MM-dd HH:mm" name="value.exptdStartDate" />
						</td>
						<td width="8%">
							<s:date format="yyyy-MM-dd HH:mm" name="value.exptdEndDate" />
						</td>
						<td width="8%">
						<s:date format="yyyy-MM-dd HH:mm" name="value.actualStartDate" />
						</td>
						<td width="8%">
							<s:date format="yyyy-MM-dd HH:mm" name="value.actualEndDate" />
						</td>
						<td width="8%">
						<div name="customStartDateDiv">
						&nbsp;
						<s:date format="yyyy-MM-dd HH:mm" name="value.customStartDate" />
						</div>
						</td>
						<td width="8%">
							<s:date format="yyyy-MM-dd HH:mm" name="value.customEndDate" />
						</td>
					</tr>
				</s:iterator>
				</table>
			</div>
			</td>
		</tr>
		</table>
		<div align="center" style="padding: 10px;">
			<input id="new_wo_btn" type="button" class="style_botton"
				value="New" />
			
		</div>
		<div id="update_status_dlg" title="Batch  Operation">
		</div>
	</body>
</html>