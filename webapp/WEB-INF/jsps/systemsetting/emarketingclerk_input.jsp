<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset {
	margin: 4px;
}
-->
</style>
<script  language="JavaScript" type="text/javascript">
    function assign(obj){
        var clerkId=$("#sessionClerkId").val();
        if(!clerkId){
            clerkId=$("#clerkId").val();
        }
        if(!obj.val()){
            alert("Please select Product/Service!");
            obj.focus();
            return;
        }
        var reqUrl = "shipping_clerks_srch!assign.action?assignValue="+obj.val()+"&sessionClerkId="+clerkId;
        //alert(reqUrl);
        $.ajax({
            type:"POST",
            url: reqUrl,
            success:function(data){
               // obj.remove($(obj).find(":selected"));
                 $("#listShip").html(data);
                 $(obj).find("option:selected").each(function(){$(this).remove();});
            }

        });
    }
    function unassign(){
        var clerkId=$("#sessionClerkId").val();
        if(!clerkId){
            clerkId=$("#clerkId").val();
        }
        var checkedObj=$("input[name='assigned']:checked");
        var checkedIds="";
        for(i=0;i<checkedObj.length;i++){
            checkedIds+=$(checkedObj[i]).val()+",";
        }
        if(checkedIds==""){
            alert("Please select the Unassign Product/Service.");
            return;
        }
        var reqUrl = "shipping_clerks_srch!unassign.action?unassignValue="+checkedIds.substr(0, checkedIds.length-1)+"&sessionClerkId="+clerkId;
        $.ajax({
            type:"POST",
            url: reqUrl,
            success: function(data){
                $("#listShip").html(data);
            }

        });
        
    }

</script>
<body class="content">
<div id="frame12" style="display:none;" class="hidlayer1">
<iframe id="hidkuan" name="hidkuan" src="emarketing_group_srch!getClerkUser.action" width="668" height="425" frameborder="0"  allowtransparency="true"></iframe>
</div>
<div class="scm">
<div class="title_content">
  <div class="title">New Clerk</div>
</div>
    <form action="emarketing_group_srch!saveClerk.action" method="post">
<div class="input_box">
		  <div class="content_box">
                      <input type="hidden" name="sessionGroupId" id="sessionGroupId" value="${sessionGroupId}"/>
	        <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                 <tr>
                  <th width="160">Name</th>
                  <td><input name="clerk.name" id="username" type="text" class="NFText" value="${clerk.name} " size="25" readonly="readonly" /><a href="javascript:void(0)" onclick="window.openiframe('emarketing_group_srch!getClerkUser.action',668,400)"><img src="${global_image_url}search.gif" width="16" height="16" align="absmiddle" /></a></td>
                 <th>Status</th>

                  <td><select name="clerk.status" style="width:157px;">
                    <option value="ACTIVE">ACTIVE</option>
                     <option value="INACTIVE">INACTIVE</option>
                  </select></td>
                </tr>
                <tr>
                  <!--<th width="160">Clerk Function</th>
                  <td><select name="shipClerk.clerkFunction" style="width:157px;">
                    <option value="Receiver">Receiver</option>
                    <option value="Picker">Picker</option>
                    <option value="Packer">Packer</option>
                    <option value="Picker/Packer">Picker/Packer</option>
                  </select></td>-->
                  <th width="150">Employee ID</th>
                  <td><input id="clerkId" name="clerk.clerkId" value="${clerk.clerkId}" type="text" class="NFText" size="25" readonly="readonly" /></td>
                  <th width="150">Supervisor</th>
                  <td><select name="clerk.supervisor" style="width:157px;">
                          <s:iterator status="supervisor" value="supervisor">
                              <option value="<s:property value="userId"/>"><s:property value="employee.employeeName"/></option>
                          </s:iterator>
                  </select></td>
                </tr>
                <tr>
                  <th rowspan="2" valign="top">Description</th>
                  <td rowspan="2" ><textarea name="clerk.description" value="${clerk.description}"  class="content_textarea2">${clerk.description}</textarea></td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>

                  <tr>
                    <th>Modified Date</th>
                    <td><input name="modifyDate" type="text" class="NFText" value="<s:date name="clerk.modifyDate" format="yyyy-MM-dd" />" size="25" readonly="readonly" /></td>
                    <th>Modified By </th>
                    <td><input name="userName" type="text" class="NFText" value="${userName}" size="25" readonly="readonly" /></td>
                </tr>
                 <tr>
                    <th>&nbsp;</th>

                    <td>&nbsp;</td>
                    <th>&nbsp;</th>
                    <td>&nbsp;</td>
                </tr>
                 
                 
                   <tr>
                    <th>&nbsp;</th>
                    <td>
                      </td>
                    <th>&nbsp;</th>
                    <td>&nbsp;</td>
                </tr>

              </table>
    </div>
  </div>
<div class="button_box">
    <input type="submit" name="Submit123"  value="<s:if test="clerk.clerkId!=null">Edit</s:if><s:else>Save</s:else>" class="search_input" />
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="javascript:history.go(-1)"/>
</div>
    </form>
</div>
</body>
</html>

