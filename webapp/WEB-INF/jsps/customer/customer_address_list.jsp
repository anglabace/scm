<!-- {get_spec_selects value="ORIGINAL_SOURCE,TIME_ZONE"} -->
<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglib.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base id="myBaseId" href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script language="JavaScript" src="${global_js_url}scm/customerAddr.js?v=1" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function(){
    $('tr:even >td').addClass('list_td2');
    sortTR("address_list");
    //modify by zhanghuibin
//    parent.initTerritory();
});
function sortTR(tableId){
                var tablestr="";
                var table=$('#'+tableId);
                var trlen=table.children().children("tr").length;
                for(var i=0;i<trlen;i++){
                    var  tempval=$(table.children().children("tr")[0]).find("[div[id^='addrType']").html();
                    var temptr=$(table.children().children("tr")[0]);
                    for(var j=0;j<trlen-i;j++){
                        var temp;
                         temp=$(table.children().children("tr")[j]);
                         if(temp!=null){                           
                            if(tempval>temp.find("[div[id^='addrType']").html()){
                                temptr=temp;
                                tempval=temp.find("[div[id^='addrType']").html();
                            }
                        }
                        else{
                            temptr=temp;
                        }
                    }                   
                    tablestr+="<tr>"+temptr.remove().html()+"</tr>";
                }
                table.html(tablestr);
            }
</script>
</head>
<body>
	    <div style="height:200px;width:955px;">
	  <form action="customer/custAddr/rmCustAddrAct" id='address_del_form' method="post" name="address_del_form" >
	  <input type="hidden" id="defaultFlags" value="<!-- {$result.defaultFlags} -->" />
	  <input type="hidden" name="sessCustNo" id="sessCustNo" value="${sessCustNo}" />
	  
              <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                  <th width="46">
                  	<div align="left">
					   <input type="checkbox"  onclick="choose_all(this, 'addrIds')" />
			      	  	 <img src="${global_image_url}file_delete.gif" id="del_img" onclick="del_address('addrIds', 'address_del_form', 'cust_address!delete.action', '${custNo}');" width="16" height="16" />
			      	 </div>
			      </th>
                  <th width="100">Address Type</th>
                  <th width="150">Name</th>
                  <th width="60">Default</th>
                  <th  width="60">Status</th>
                  <th>Address Information</th>
                </tr>
              </table>
	   	    <div class="frame_box">
                <table border="0" cellpadding="0" cellspacing="0" class="list_table" width="955" id="address_list">
                  <s:iterator value="addrList" status="addrList">
                      <s:if test='operateType!="DEL"'>
                 <tr>
                    <td width="46">
                        <s:if test="addrId">
                            <input type="checkbox" name="addrIds" value="<s:property value="addrId"/>" />
                        </s:if>
                            <s:else>&nbsp;</s:else>
                    <input type="hidden" value="<s:property value="all_Data"/>" id="<s:property value="addrId"/>_allData" />
                    </td>
                    <td width="100" >
                        <div align="center" id="addrType_<s:property value="#addrList.index+1"/>"><s:property value="addrType"/>&nbsp;</div>
                    </td>
                    <td width="150">&nbsp;
                    	<a href="javascript:void(0);" onclick="show_edit_addrform('<s:property value="addrId"/>','${custNo}');"
						title="Edit Address">
                            <s:property value="lastName"/>, <s:property value="firstName"/>
                   	 	</a>
                    </td>
                    <td width="60" >
                    	<div align="center">
                                <input type="checkbox" <s:if test='defaultFlag=="Y"'> checked</s:if> disabled="disabled" onclick="return false;" />
                    		&nbsp;
                    	</div>
                    </td>
                    <td width="60" ><s:property value="status"/>&nbsp;</td>
                    <td><s:property value="addrLine1"/> <s:property value="addrLine2"/>  <s:property value="addrLine3"/>, <s:property value="city"/>, <s:property value="state"/> <s:property value="zipCode"/>, <s:property value="country"/>   &nbsp;</td>
                  </tr>
                  </s:if>
                  </s:iterator>
                </table>
               </div>
   	        </form>
   	        </div>

</body>
</html>