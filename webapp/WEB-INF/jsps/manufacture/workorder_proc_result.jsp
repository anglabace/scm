<%@ include file="/common/taglib.jsp"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>Route Search Result</title>
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
		<link href="${global_css_url}vtip.css" rel="stylesheet" type="text/css" />
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
			language="javascript"></script>
		<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript" language="javascript"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
			<script type="text/javascript" language="javascript" src="${global_js_url}vtip.js"></script>
		<script language="JavaScript" type="text/javascript">
		var imagesUrl = '${global_image_url}';
$(document).ready(function(){
	 init();
    $("#resultTable tr:odd").find("td").addClass("list_td2");

    //复选框绑定: 全选|全不选
    $("#check_all").click( function() {
        var allChoiceVal = "";
       $(":checkbox").each(function() {
          this.checked = $("#check_all").get(0).checked;
          if(this.checked&&this.name=="delRouteId") {
              if(allChoiceVal=="") {
            	  allChoiceVal=this.value;
              } else {
            	  allChoiceVal = allChoiceVal+","+this.value;
              }
          }
       }); 
       parent.document.getElementById("choiceOption").value=allChoiceVal;     
    });
    
    $('#cancel_work_order').dialog({
		autoOpen: false,
		height: 200,
		width: 620,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
    
    //删除选中的checkbox.
    $("#check_del").click( function() {
        if ($(":checkbox:gt(0):checked").length < 1) {
           alert('Please select one at least!');
		   return;
        }
  		if (!confirm('Are you sure to delete?')) {
			return;
		}
  		$('#check_del').attr("disabled", true);	
  		$('#cancel_work_order').dialog("open");
        
		
    });
    
});
function del_workOrder() {
	var reason = $("#reason").val();
	if(reason=="") {
		alert("Please enter the reason.");
		$("#reason").focus();
		return;
	}
	$.ajax({
		type: "POST",
		url: "workorder_proc!deleteWo.action",
		data: "allChoiceVal="+parent.$("#choiceOption").val()+"&reason="+reason+"&roleName=Work Order Processing Manager",
		dataType: 'json',
		success: function(data){
			if(hasException(data)){//有错误信息.
 	            $('#check_del').attr("disabled", false);			
			}else {
				if(data.message=='success') {
					alert("Delete these work orders successfully");
				} else if(data.message=='fail') {
					alert("You don't have permission to cancel these work orders, please contact your supervisor.");	
				} else {
					alert('Unknown error');
				}
				$('#check_del').attr("disabled", false);
				$('#cancel_work_order').dialog("close");
				window.location.reload();
			}
		},
		error: function(msg){
			alert("Failed to delete work order!");
            $('#check_del').attr("disabled", false);	
            $('#cancel_work_order').dialog("close");
		}
	});    
}
//复选框选择事件
function choice(obj) {
	var allChoiceVal = parent.document.getElementById("choiceOption").value;
	var id = obj.value;
	if(obj.checked) {
		if(allChoiceVal=="") {
			allChoiceVal=id;
		} else {
			allChoiceVal=allChoiceVal+","+id;
		}
		
	} else {
		var allChoiceValArr = allChoiceVal.split(",");
		allChoiceVal ="";
		if(allChoiceValArr==null||allChoiceValArr==""||allChoiceValArr.length==0) {
			allChoiceVal=allChoiceVal+id;
		} else {
			$.each(allChoiceValArr,function(k,v) {
				if(v!=id) {
					if(allChoiceVal=="") {
						allChoiceVal=allChoiceVal+v;
					} else {
						allChoiceVal=allChoiceVal+","+v;
					}
					
				}
			});
		}
	}
	parent.document.getElementById("choiceOption").value=allChoiceVal;
}
//复选框初始化
function init() {
	var allChoiceVal = parent.document.getElementById("choiceOption").value;
	if(allChoiceVal!="") {
		var allChoiceValArr = allChoiceVal.split(",");
		$.each(allChoiceValArr,function(k,v) {
			var id = "#"+v;
			if($(id)!=null) {
				$(id).attr("checked",true);
			}
		});
	}
}

function lookUsOrder(id) {
	parent.viewOrder(id);
}
</script>
	</head>
	<body class="content" style="background-image: none;">
		<form action="route!delete.action" id="del_form">
			<div class="input_box">
				<div class="content_box">
					<table width="115%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div>
									<table width="98.7%" border="0" cellspacing="0" cellpadding="0"
										class="list_table">
										<tr>
											<th width="3%">
												<div align="left">
													<input name="checkbox2" type="checkbox" id="check_all" />
													<img style="cursor: pointer"
														src="${global_image_url}file_delete.gif" id="check_del"
														alt="Delete" width="16" height="16" border="0" />
												</div>
											</th>
											<th width="4%">
												Work Order No
											</th>
											<th width="5%">
											    Item Desc
											</th>
											<th width="5%">
												Type
											</th>
											<th width="5%">
												Status
											</th>
											<th width="12%">Comments</th>
											<th width="5%">
												Priority
											</th>
											<th width="5%">
												Source
											</th>
											<th width="5%">
												NanJing SO NO
											</th>
											<th width="5%">
												US SO NO
											</th>
											<th width="4%">
												Item No
											</th>
											<th width="10%">
												Product/Service
											</th>
											<th>
												Work Group
											</th>
											
											<th width="5%">
												Product QC
											</th>
											<th width="5%">
												Document QC
											</th>
											<th width="6%">
												Order Date
											</th>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="list_box" style="width:100%;height: 340px;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										class="list_table2" id="resultTable">
										<s:iterator value="workOrderPage.result">
											<tr>
												<td width="3%">
												<s:if test="status == 'Canceled'">
				   									<input type="checkbox" value="${orderNo}" id="${orderNo}" name="delRouteId" disabled="disabled"/>
				   						 		</s:if>
				       							<s:else>
				       								<input type="checkbox" value="${orderNo}" id="${orderNo}" name="delRouteId" onclick="choice(this);"/>
				       							</s:else>
												<s:if test='shippable!=null&&shippable.toLowerCase().equals("n")'>
													<img src="${global_image_url}green_point.png"/>
												</s:if>	
												</td>
												<td width="4%">
												<s:if test="status == 'Canceled'">
				   									<a href="workorder_entry!edit.action?id=${orderNo}&operation_method=view" target="_parent">${altOrderNo}</a>
				   						 		</s:if>
				       							<s:else>
				       								<a href="workorder_entry!edit.action?id=${orderNo}&operation_method=edit" target="_parent">${altOrderNo}</a>
				       							</s:else>
													
												</td>
												<td width="5%" align="center">
													<span  style="color:#7DC012;cursor: pointer" title="<s:property value='itemDesc' escape='false' />" class="vtip">Detail</span>
												</td>
												<td width="5%">
													${type}
												</td>
												<td width="5%" align="center">
													${status}
												</td>
												<td width="12%">
												<span title="${comment}">
													<s:if test="comment!=null&&comment.length()>20">
													<s:property  value="comment.substring(0,20)"/>...
													</s:if>
													<s:else>
														<s:property  value="comment"/>
													</s:else>
												</span>
												
												</td>
												<td width="5%">
													${priority}													
												</td>
												<td width="5%">
													${source}
												</td>
												<td width="5%">
													${soNo}
												</td>
												<td width="5%">
													<a href="javascript:void(0);" onclick="lookUsOrder('${srcSoNo}')">${srcSoNo}</a>
												</td>
												<td width="4%">
													${soItemNo}
												</td>
												<td width="10%">
													${catalogNoDesc}
												</td>
												<td>
												<span title="<s:property value='workGroupName'/>">
												<s:if test="workGroupName.length()>25"><s:property value="workGroupName.substring(0,25)"/>...</s:if>
												<s:else><s:property value="workGroupName"/></s:else>
												</span>
												</td>
												
												<td width="5%">
													${productQc}
												</td>
												<td width="5%">
													${documentQc}
												</td>
												<td width="6%">
													${orderDate}
                                                    <%--add by zhanghuibin 用于View opratation 取值--%>
                                                    <input type=hidden id="${orderNo}_scheduleEnd_date" name="scheduleEnd_date" value="${secheduleEnd}"/>
                                                    <input type=hidden id="${orderNo}_cls_id" name="cls_id" value="${clsId}"/>
                                                    <input type=hidden id="${orderNo}_so_no" name="so_no" value="${srcSoNo}"/>
                                                    <input type=hidden id="${orderNo}_so_item_no" name="so_item_no" value="${soItemNo}"/>
												</td>
											</tr>
										</s:iterator>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="grayr">
									<jsp:include page="/common/db_pager.jsp">
										<jsp:param value="${ctx}/workorder_proc!list.action" name="moduleURL" />
									</jsp:include>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
            <input type=hidden id="scheduleEnd_dates" name="delivery_dates" value=""/>
            <input type=hidden id="so_nos" name="order_nos" value=""/>
            <input type=hidden id="so_item_nos" name="so_item_nos" value=""/>
            <input type=hidden id="work_orders" name="work_orders" value=""/>
		</form>
		<div id="cancel_work_order" title="Delete Work Order" style="visible: hidden">
			<s:include value="cancel_workOrder.jsp"></s:include>
		</div>
	</body>
</html>
