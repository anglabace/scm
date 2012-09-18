<%@ include file="/common/taglib.jsp"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>Quality Control Search Result</title>
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}vtip.css" rel="stylesheet" type="text/css" />
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
			language="javascript"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script type="text/javascript" language="javascript" src="${global_js_url}vtip.js"></script>
		<script language="JavaScript" type="text/javascript">
		var imagesUrl = '${global_image_url}';
$(document).ready(function(){
	 init();
    $("#resultTable tr:even").find("td").addClass("list_td2");
    

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
    
    //删除选中的checkbox.
    $("#check_del").click( function() {    
        if ($(":checkbox:gt(0):checked").length < 1) {
           alert('Please select one at least!');
		   return;
        }
  		if (!confirm('Are you sure to delete?')) {
			return;
		}
		alert("功能还没做 ！");
		return;
        $('#check_del').attr("disabled", true);	
		$.ajax({
			type: "POST",
			url: "route!delete.action",
			data: $("#del_form").serialize(),
			dataType: 'json',
			success: function(data){
				if(hasException(data)){//有错误信息.
	 	            $('#check_del').attr("disabled", false);			
				}else {                        
					alert(data.message);
				    window.location.reload();
				}
			},
			error: function(msg){
				alert("Delete Work Center failed !");
                $('#check_del').attr("disabled", false);		
			}
		});    
    });
    
});

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

</script>
	</head>
	<body class="content" style="background-image: none;">
		<form action="route!delete.action" id="del_form">
			<div class="input_box">
				<div class="content_box">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div>
									<table width="98.5%" border="0" cellspacing="0" cellpadding="0"
										class="list_table2" id="resultTable">
										<tr>
											<th width="4%">
												<div align="left">
													<input name="checkbox2" type="checkbox" id="check_all" />
												</div>
											</th>
											<th width="5%">
												Work Order No
											</th>
											<th width="5%">
												US SO NO
											</th>
											<th width="5%">
												NanJing SO NO
											</th>
											<th width="4%">
												Item No
											</th>
											<th width="7%">
											    Item Desc
											</th>
											<th width="10%">
												Product/Service
											</th>
											<th width="5%">
												Status
											</th>
											<th width="5%">
												Product QC
											</th>
											<th width="7%">
												Document QC
											</th>
											<th>
												Work Group
											</th>
											<th width="5%">
												QC Group
											</th>
											<th width="6%">
												QC Clerk
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
										<div class="list_box" style="width:100%;height: 340px;overflow:scroll">
									    <table width="100%" border="0" cellspacing="0" cellpadding="0"
										class="list_table2" id="resultTable">
										<s:iterator value="workOrderPage.result">
											<tr>
												<td width="4%">
													<input type="checkbox" value="${orderNo}" id="${orderNo}"
														name="delRouteId" onclick="choice(this);"/>
												</td>
												
												<td width="5%">
													<a
														href="workorder_entry!edit.action?id=${orderNo}&fromQc=fromQc&operation_method=edit&operStatus=QC"
														target="_parent">${altOrderNo}</a>
												</td>
												<td width="5%">
													${srcSoNo}
												</td>
												<td width="5%">
													${soNo}
												</td>
												<td width="4%">
													${soItemNo}
												</td>
												<td width="7%">
													<span  style="color:#7DC012;cursor: pointer" title="<s:property value='itemDesc' escape='false' />" class="vtip">Detail</span>
												</td>
												<td width="10%">
													${catalogNoDesc}
												</td>
												<td width="5%">
													${status}
												</td>
												<td width="5%">
													${productQc}
												</td>
												<td width="7%">
													${documentQc}
												</td>
												<td>
													${workGroupName}
												</td>
												<td width="5%">
													${qcGroupName}
												</td>
												<td width="6%">
													${qcClerkName}
												</td>
												
												<td width="6%">
													${orderDate}
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
										<jsp:param value="${ctx}/workorder_qc!list.action"
											name="moduleURL" />
									</jsp:include>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</body>
</html>
