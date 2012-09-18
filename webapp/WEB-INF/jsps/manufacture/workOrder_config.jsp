<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
	.row_odd{background-color:#FFFFFF;}
	.row_even{background-color:#EEF2FD;}
	.tr_hover{background-color:#D1F1FA;}  
	.tr_click{background-color:#84E5FF;}  
	.tr_del{background-color:#E6E6E6;}  
	.tr_{background-color:#E6E6E6;}  
</style>

<script language="javascript">
//全局变量用于order quote 通用模块
var orderquoteObj = [];
orderquoteObj.sessNoValue = "${sessWorkOrderNo}";
orderquoteObj.ajaxUrls = {
						  "saveNote":"workorder_entry!saveMail.action?sessWorkOrderNo="+orderquoteObj.sessNoValue,
						  "editNote":"workorder_entry!sendMail.action?sessWorkOrderNo="+orderquoteObj.sessNoValue
						  };
//例子：orderquoteObj.url("getItemDetail");
orderquoteObj.url = function (urlName){
	return orderquoteObj.ajaxUrls[urlName];
};
</script>