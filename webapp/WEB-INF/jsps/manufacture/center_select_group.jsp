

<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>WorkCenter Search Result</title>
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
			language="javascript"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script language="JavaScript" type="text/javascript">
function edit(workCenterId) {
   window.parent.location = "work_center!edit.action?id=" + workCenterId;
}		
		
$(document).ready(function(){
	$("select").each(function(){
			var changeWidth=false;
	   		var len = this.offsetWidth;
	   		if(len!=0) {
 	   		this.style.width = 'auto';
 	   		if(len<this.offsetWidth) {
 	   			changeWidth = true;
 	   		}
 	   		this.style.width=len+"px";
 	   		$(this).mousedown(function(){
 	   			if(changeWidth) {
 	   				this.style.width = 'auto';
 	   			}
 	   			});
 	   		$(this).blur(function() {this.style.width = len+"px";});
 	   		$(this).change(function(){this.style.width = len+"px";});
	   		}
	   		
	   	});
   	
    $("#resultTable tr:odd").find("td").addClass("list_td2");
    //删除前面select的值， 确保不重复
    var parentVal = "";
    parent.frames["resource_list_frame"].$("#resultTable :checkbox").each(function() {
        parentVal += $(this).val() + ",";
    });      
    $("#result_tbl :checkbox").each(function() {
          var tdObj = $(this).parent();
          var trObj = tdObj.parent();    
          if (parentVal.indexOf($(this).val()+",") != -1) {
             var hiddenTr = $("#hidden_tbl tr:eq(" + trObj.get(0).rowIndex + ")");
             //hiddenTr.remove();
             //trObj.remove();
          }
    });    
    //end of 删除前面select的值， 确保不重复
    
    //复选框绑定: 全选|全不选
    $("#check_all").click( function() {
       $("#result_tbl :checkbox").each(function() {
          this.checked = $("#check_all").get(0).checked;
       });      
    });
    
    //执行Select
    $("#select_btn").click( function() {    
       if ($("#result_tbl :checkbox:checked").length < 1) {
           alert('Please select one at least!');
		   return;
       }
       $('#select_btn').attr("disabled", true);
       var param = "id=${param.centerId}";
       $("#result_tbl :checkbox:checked").each(function() {
          var tdObj = $(this).parent();
          var trObj = tdObj.parent();  
          if (parentVal.indexOf($(this).val()+",") != -1) {
              alert("Has the work group '"+ trObj.children(":eq(1)").html() + "' already !");
          } else {
        	  param += "&groupIdList=" + $(this).val();
          }
       });
       $.ajax({
			type: "POST",
			url: "work_center!selectCallBack.action",
			dataType: 'json',
			data: param,
			success: function(data){
			    ranscan(data);
				//parent.location = parent.location;
			},
			error: function(msg){
				alert("Select failed !");
               $('#select_btn').attr("disabled", false);		
			}
		});  	         	       
       parent.$("#resultTable tr:odd").find("td").addClass("list_td2");//隔行变色
       parent.$('#sel_res_dlg').dialog('close');
    });//end of 执行Select;

  //动态改变父窗口表格
    function ranscan(jsonObj) {
    	$("#result_tbl :checkbox:checked").each(function() {
    		 var tdObj = $(this).parent();
             var trObj = tdObj.parent(); 
    		if (parentVal.indexOf($(this).val()+",") == -1) {
    			var hiddenTr = $("#hidden_tbl tr:eq(" + trObj.get(0).rowIndex + ")");
  	          parent.frames["resource_list_frame"].$("#resultTable").append(hiddenTr.clone());
            }
		  }); 
	      parent.frames["resource_list_frame"].$("#resultTable tr:odd").find("td").addClass("list_td2");//隔行变色  
          parent.$('#sel_res_dlg').dialog('close');
    }
    
    
});
</script>
	</head>
	<body>
		<form action="work_group!selectForCenter.action" method="get">
			<input type="hidden" name="centerId" value="${param.centerId}" />
			<table border="0" cellpadding="0" cellspacing="0"
				class="General_table">
				<tr>
					<td>
						Group Name
					</td>
					<td>
						<input name="filter_LIKES_name" value="${param.filter_LIKES_name}"
							type="text" class="NFText" size="20" />
					</td>
					<td>
						Description
					</td>
					<td>
						<input name="filter_LIKES_description"
							value="${param.filter_LIKES_description}" type="text"
							class="NFText" size="40" />
					</td>
					<td>
						<input name="Submit3" type="submit" class="style_botton"
							value="Search" />
					</td>
				</tr>
			</table>
		</form>
		<table width="660" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="4">
					<table width="640" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<tr>
							<th width="30">
								<input name="checkbox2" type="checkbox" id="check_all" />
							</th>
							<th width="129">
								Work Group Name
							</th>
							<th width="332">
								Description
							</th>
							<th>
								Status
							</th>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div style="width: 657px; height: 210px; overflow: scroll;">
						<table width="640" border="0" cellpadding="0" cellspacing="0"
							class="list_table" id="result_tbl">
							<s:iterator value="workGroupPage.result">
								<tr>
									<td width="30" align="center">
										<input type="checkbox" value="${id}" name="groupIdList" />
									</td>
									<td width="129">
										${name}
									</td>
									<td width="332">
										${description}
									</td>
									<td>
										${status}
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div class="grayr">
						<jsp:include page="/common/db_pager.jsp">
							<jsp:param value="${ctx}/group!selectForCenter.action"
								name="moduleURL" />
						</jsp:include>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="botton_box">
					<input name="Submit" type="button" id="select_btn"
						class="style_botton" value="Select" />
					<input type="submit" name="Submit2" value="Cancel"
						class="style_botton"
						onclick="parent.$('#sel_res_dlg').dialog('close');" />
				</td>
			</tr>
		</table>
		<table width="640" border="0" cellpadding="0" cellspacing="0"
			class="list_table" id="hidden_tbl" style="display: none">
			<s:iterator value="workGroupPage.result">
				<tr>
					<td width="46" align="center">
						<input type="checkbox" value="${id}" name="groupIdList" />
					</td>
					<td width="136">
						<a href="javascript:void(0)" onclick="edit('${id}')" target="_parent">${name}</a>
					</td>
					<td width="197">
						${description}
					</td>
					<td width="89">
						${status}
					</td>
					<td width="118">
						${superName}
					</td>
					<td width="119">
						${comment}
					</td>
					<td width="119" align="center">
						<s:date format="yyyy-MM-dd" name="modifyDate" />
					</td>
					<td>
						${modifyUser}
					</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>
