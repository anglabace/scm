<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}expland.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script language="JavaScript" type="text/javascript">
var GB_ROOT_DIR = "./greybox/";
$(function(){
	  init();
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

function deleteSel() {
	  if ($(":checkbox:gt(0):checked").length < 1) {
        alert('Please select one at least!');
		   return;
     }
		if (!confirm('Are you sure to delete?')) {
			return;
		}
		var allChoiceVal = parent.document.getElementById("choiceOption").value;
		$.ajax({
			type: "POST",
			url: "email_group!delete.action",
			data: "allChoiceVal="+allChoiceVal,
			dataType: 'json',
			success: function(data){
				if(hasException(data)){//有错误信息.
				}else {
					alert(data.message);
				    window.location.reload();
				}
			},
			error: function(msg){
				alert("Delete manufacturing clerks failed !");
              $('#check_del').attr("disabled", false);
			}
		});
}

//复选框初始化
function init() {
	var allChoiceVal = parent.document.getElementById("choiceOption").value;
	if(allChoiceVal!="") {
		var allChoiceValArr = allChoiceVal.split("-");
		$.each(allChoiceValArr,function(k,v) {
			var id = "#"+v;
			if($(id)!=null) {
				$(id).attr("checked",true);
			}
		});
	}
}
</script>
<script type="text/javascript" srcGB_ROOT_DIR="${global_js_url}greybox/gb_scripts.js"></script>
</head>
<body class="content" style="background-image: none;">
<div class="input_box">
<div class="content_box">
<table width="1010" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><div style="margin-right:17px;">
    <table width="993px" border="0" cellspacing="0" cellpadding="0" class="list_table">
      <tr>
        <th width="46px"><div align="left">
					    <input type="checkbox" id="check_all" name="checkbox11"/>
		       <a title="Delete Template" rel="gb_page_center[600,  180]" onclick="deleteSel()"><img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" /></a></div></th>
         <th width="114px">Email Group ID</th>
        <th width="239px">Email Group Name</th>
        <th width="95px">Description</th>
        <th width="177px">Status</th>
        <th width="133px">Group Function</th>
        <th width="189">Email Address</th>
         </tr>

    </table>
    </div></td>
  </tr>
  <tr>
    <td>
	<div class="list_box">
      <table width="993px" border="0" cellspacing="0" cellpadding="0" class="list_table2">
      <s:if test="emailGroupPage!=null&&emailGroupPage.result!=null">
      <s:iterator value="emailGroupPage.result" status="st">
          <s:set var="color" value="%{'list_td2'}"/>
      <s:if test="#st.odd">
          <s:set var="color" value="''"/>
       </s:if>
	      <tr>
	      	 <td width="46px" class="<s:property value="%{color}"/>" style="word-warp:break-word;word-break:break-all;"><input type="checkbox" value="${groupId}" id="${groupId}" onclick="choice(this);" name=delRouteId /></td>
	      	 <td width="114px" class="<s:property value="%{color}"/>" style="word-warp:break-word;word-break:break-all;"><a href="email_group!input.action?groupId=<s:property value='groupId'/>" target="_parent">${groupId}</a></td>
	         <td width="239px" class="<s:property value="%{color}"/>" style="word-warp:break-word;word-break:break-all;">${groupName}</td>
	         <td width="95px" class="<s:property value="%{color}"/>" style="word-warp:break-word;word-break:break-all;">${description}</td>
	         <td width="177px" class="<s:property value="%{color}"/>">${status}</td>
	         <td width="133px" class="<s:property value="%{color}"/>" style="word-warp:break-word;word-break:break-all;">${groupFunction}</td>
	         <td width="189px" class="<s:property value="%{color}"/>" style="word-warp:break-word;word-break:break-all;">${groupAddress}</td>
	      </tr>
      </s:iterator>
      </s:if>
      </table>
	</div>
  </tr>

  <tr>
	  <td>
	  <div class="grayr">
	  <jsp:include page="/common/db_pager.jsp">
			<jsp:param value="${ctx}/email_group!list.action" name="moduleURL" />
	  </jsp:include>
	  </div>
	  </td>
  </tr>
</table>
</div>
</div>
</body>
</html>