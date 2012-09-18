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
        return;
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
                 parent.location = "workorder_operation!selectOperation.action?sessId=${param.sessId}";		
			},
			error: function(xhr, textStatus){
			   parent.$('#save_btn').attr("disabled", false);
			   alert("failure");
			}
	    });  
        return;
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
        </script>
	</head>
	<body>
		<input type="hidden" name="sessId" id="sessId"
			value="${param.sessId}" />
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
			<div class="list_box" style="width:100%;height: 240px;overflow: auto;">
				<table  border="0" cellpadding="0" cellspacing="0"
					class="list_table" width="100%">
					<tr id="title_tr">
						<th width="6%">
							<div class="quote_dele">
								<input name="checkbox2" type="checkbox" id="check_all" disabled="disabled"/>
								<img style="cursor: pointer"
									src="${global_image_url}file_delete.gif" id="check_del"
									alt="Delete" width="16" height="16" border="0" />
							</div>
		
							<div  class="down_up">
								<img src="images/up.jpg"  height="8" title="up" />
							</div>
							<div  class="down_up2">
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
						<td>
							<input type="checkbox" value="${key}" name="relId" disabled="disabled"/>
						</td>
						<td>
							${value.seqNo}
						</td>
						<td>
						    <c:if test="${empty value.internalOrderNo}">
							   ${value.operation.name}
							</c:if>
						    <c:if test="${! empty value.internalOrderNo}">
							   ${value.internalOrderNo}
							</c:if>
						</td>
					
						<td>
							${value.status}
						</td>
						
						<td>
							<c:if test="${empty value.operation.setupTime}">
							   0
							</c:if>
							<c:if test="${! empty value.operation.setupTime}">
							  ${value.operation.setupTime}
							</c:if>
						</td>
						<td>
							<c:if test="${empty value.operation.runTime}">
							   0
							</c:if>
							<c:if test="${! empty value.operation.runTime}">
							  ${value.operation.runTime}
							</c:if>
						</td>
						<td>
							${value.operation.uom}
						</td>
						<td>
							<span title="<s:property value='value.operation.comment'/>">
							<s:if test="value.operation.comment!=null&&value.operation.comment.length()>23"><s:property value="value.operation.comment.substring(0,23)"/>...</s:if>
							<s:else><s:property value="value.operation.comment"/></s:else>
							</span>
							
						</td>					
						<td>
						<s:date format="yyyy-MM-dd HH:mm" name="value.exptdStartDate" />
						</td>
						<td>
							<s:date format="yyyy-MM-dd HH:mm" name="value.exptdEndDate" />
						</td>
						<td>
						<s:date format="yyyy-MM-dd HH:mm" name="value.actualStartDate" />
						</td>
						<td>
							<s:date format="yyyy-MM-dd HH:mm" name="value.actualEndDate" />
						</td>
						<td>
						<s:date format="yyyy-MM-dd HH:mm" name="value.customStartDate" />
						</td>
						<td>
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
			<input disabled="disabled" type="button" class="style_botton"
				value="New" />
		</div>
	</body>
</html>