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
        var groupId=$("#sessionGroupId").val();
        if(!groupId){
            groupId=$("#groupId").val();
        }
        if(!obj.val()){
            alert("Please select Product/Service!");
            obj.focus();
            return;
        }
        var reqUrl = "emarketing_group_srch!assign.action?assignValue="+obj.val()+"&sessionGroupId="+groupId;
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
        var groupId=$("#sessionGroupId").val();
        if(!groupId){
            groupId=$("#groupId").val();
        }
        var checkedObj=$("input[name='assigned']:checked");
        var groupIds="";
        for(i=0;i<checkedObj.length;i++){
            groupIds+=$(checkedObj[i]).val()+",";
        }
        if(groupIds==""){
            alert("Please select the Unassign Product/Service.");
            return;
        }
        var reqUrl = "emarketing_group_srch!unassign.action?unassignValue="+groupIds.substr(0, groupIds.length-1)+"&sessionGroupId="+groupId;
        $.ajax({
            type:"POST",
            url: reqUrl,
            success: function(data){
                $("#listShip").html(data);
            }

        });
        
    }

</script>
<script   language="JavaScript" type="text/javascript">
$(document).ready(function(){
    $('tr:even >td').addClass('list_td2');
});
function cc(e, name){
	var  a =   document.getElementsByName(name);
	for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;
}
//remove checked rows and return to_del id list
function getDelIds(boxs){
	var a = document.getElementsByName(boxs);
	var to_del = "";
	var total = a.length;
	for (var i=(total-1); i>=0; i--){
		if (a[i].checked ){
			to_del += a[i].value+",";
		}
	}
	return to_del;
}

function del_ship_clerk(){
	if(!confirm("Are you sure to delete?")){
		return;
	}
	var to_del = getDelIds('clerkId');
	var reqUrl = "shipping_clerks_srch!deleteClerk.action?clerkIds="+to_del+"&sessionGroupId="+${sessionGroupId};
	$.ajax({
	    type: "POST",
	    url: reqUrl,
		success:function(data){
			if (data.indexOf("SUCCESS") == 0){
				if (data.indexOf("##") > 0){
					var str = data.substr(data.indexOf("##")+2);
					alert (str + " can not be deleted!");
				}else{
					alert ("Deleted successfully.");
				}
				window.location.reload();
			}else{
				alert (data);
				return;
			}
		},
		async: false
	});
}
</script>

<body class="content" style="">
    
<div id="frame12" style="display:none;" class="hidlayer1">
<iframe id="hidkuan" name="hidkuan" src="" width="668" height="425" frameborder="0"  allowtransparency="true"></iframe>
</div>
<form action="emarketing_group_srch!save.action" method="post">
<div class="scm">
<div class="title_content">
  <div class="title">Group marketing1</div>
</div>

<div class="input_box">
    
		  <div class="content_box">
                      <input type="hidden" name="group.groupId" id="groupId" value="${group.groupId}"/>
                      <input type="hidden" name="sessionGroupId" id="sessionGroupId" value="${sessionGroupId}"/>
		      <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                          <tr>
                  <th width="164">Group Name</th>
                  <td width="416" colspan="2"><input name="group.groupName" value="${group.groupName}" type="text" class="NFText" size="25"/></td>
                  <th width="148">Status</th>

                  <td width="165"><select name="group.status" style="width:157px;" id="select">
                    <option selected="selected">ACTIVE</option>
                    <option>INACTIVE</option>
                  </select></td>
                </tr>
                      <tr>
                  <th>Description</th>

                  <td colspan="2"><input name="group.description" value="${group.description}" type="text" class="NFText" size="25"/></td>
                  <th>Supervisor</th>
                  <td>
                      <select name="group.supervisor" style="width:157px;">
                          <s:iterator status="supervisor" value="supervisor">
                              <option value="<s:property value="userId"/>"><s:property value="employee.employeeName"/></option>
                          </s:iterator>
                  </select>
                  </td>
                </tr>
                  <!--<tr>
                  <th valign="top">Group Function</th>
                  <td colspan="2"><input name="textfield3" type="text" class="NFText" value="" size="25" readonly="readonly" /></td>
                  <th>&nbsp;</th>

                  <td>&nbsp;</td>
                </tr>-->
                  <tr>
                  <th valign="top">Modified Date</th>
                  <td colspan="2"><input name="modifyDate" type="text" class="NFText" value="<s:date name="group.modifyDate" format="yyyy-MM-dd"/>" size="25" readonly="readonly" /></td>
                  <th>Modified By</th>
                  <td><input name="textfield7" type="text" class="NFText" value="${userName}" size="25" readonly="readonly" /></td>
                </tr>

                <tr>
                  <th valign="top">&nbsp;</th>
                  <td colspan="2">&nbsp;</td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                 <th valign="top">&nbsp;</th>
                  <td colspan="2">Product/Service Type to be Processed</td>

                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <th valign="top">&nbsp;</th>
                  <td colspan="2"><select id="typeName" name="typeName" style="width:157px;">
                         <s:iterator status="allcls" value="#request.allcls">
                             <option value="<s:property value="key"/>:<s:property value="value"/>"><s:property value="key"/></option>
                         </s:iterator>
                    </select>


                    <input type="button" name="Submit33"  class="style_botton" value="Assign" onclick="assign($('#typeName'))"/>
                    <input type="button" name="Submit"  class="style_botton" value="Unassign" onclick="unassign()"/>
                  </td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <th>&nbsp;</th>
                  <td width="249">
                     <table width="100%" border="0" cellspacing="0" cellpadding="0" id="listShip">
                      ${listStr}
                         </table>
                     
                  </td>
                    <th>&nbsp;</th>
                    <td>&nbsp;</td>

                </tr>

              </table>          
		</div>
    
  </div>
<div id="dhtmlgoodies_tabView1">
    <div class="dhtmlgoodies_aTab">
 <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
    <tr>

      <th width="46"><input name="checkbox42"   type="checkbox"   onclick="cc(this,'clerkId')" />
       <a href="javascript: return" title="Delete Clerk" rel="gb_page_center[600,  180]"><img src="${global_image_url}/file_delete.gif" alt="Delete" width="16" height="16" border="0" /></a></th>
      <th width="137">Clerk ID </th>
      <th width="170">Group Name</th>
      <th width="287">Assigned Product/Service</th>
      <th width="111">Status</th>
      <th width="198" >Comment</th>
      </tr>
  </table>
	  <div class="frame_box" style="height:170px; ">
	    <table width="955" border="0" cellspacing="0" cellpadding="0" class="list_table2">
                <s:iterator value="#request.allClerks" status="clerkList">
                <tr>
	        <td width="46"><input name="clerkId" type="checkbox" value="<s:property value="clerkId"/>"  onclick="cc(this)"/></td>
                <td width="136"><a href="emarketing_group_srch!inputClerk.action?sessionGroupId=${sessionGroupId}&clerkId=<s:property value="clerkId"/>"><s:property value="clerkId"/></a></td>
	        <td width="169"><s:property value="name"/></td>

	        <td width="288"><s:property value=""/></td>
	        <td width="110"><div align="center"><s:property value="status"/></div></td>
	        <td width="200"><s:property value="description"/></td>

                </tr>
                </s:iterator>	    
        </table>
	  </div>

    <div align="center" style="padding:10px;">
      <input type="button" name="Submit33"  class="style_botton" value="New" onclick="window.location.href='emarketing_group_srch!inputClerk.action?sessionGroupId=${sessionGroupId}'"/>

   </div>
    </div>

    </div>
</div>

<script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('Clerks'),0,998,250);
</script>

<div class="button_box">
    <input type="submit" name="Submit123"  value="<s:if test="#request.edit=='true'">Edit</s:if><s:else>Save</s:else>" class="search_input" />
      <input type="submit" name="Submit124" value="Cancel" class="search_input" onclick="window.location.href='shipping_clerk.html'"/>
</div>
 </form>
</body>
</html>

