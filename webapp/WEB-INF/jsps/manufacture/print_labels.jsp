<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" /> 
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript">
	function print() {
		window.location="workorder_proc!print.action?allChoiceVal="+$("#allChoiceVal").val();
	}
	function edit(orderNo,type) {
		if(type=='protein') {
			var url = "workorder_proc!proteinEdit.action?orderNo="+orderNo;
        	parent.$("#protein_labels_dlg").dialog("option","open",function(){
        		 var htmlStr = '<iframe src="'+url+'" height="270" width="530" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
        		 parent.$("#protein_labels_dlg").html(htmlStr);
            });
        	parent.$("#protein_labels_dlg").dialog("open");
		}
		if(type=='polyclonal antibody') {
			var url = "workorder_proc!polyclonalAntibodyEdit.action?orderNo="+orderNo;
        	parent.$("#polyclonal_antibody_labels_dlg").dialog("option","open",function(){
        		 var htmlStr = '<iframe src="'+url+'" height="100%" width="100%" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
        		 parent.$("#polyclonal_antibody_labels_dlg").html(htmlStr);
            });
        	parent.$("#polyclonal_antibody_labels_dlg").dialog("open");
		}
		if(type=='oligo') {
			var url = "workorder_proc!oligoEdit.action?orderNo="+orderNo;
        	parent.$("#oligo_labels_dlg").dialog("option","open",function(){
        		 var htmlStr = '<iframe src="'+url+'" height="100%" width="100%" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
        		 parent.$("#oligo_labels_dlg").html(htmlStr);
            });
        	parent.$("#oligo_labels_dlg").dialog("open");
		}
		if(type=='monoclonal antibody') {
			var url = "workorder_proc!monoclonalAntibodyInit.action?orderNo="+orderNo;
        	parent.$("#monoclonal_antibody_labels_dlg").dialog("option","open",function(){
        		 var htmlStr = '<iframe src="'+url+'" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
        		 parent.$("#monoclonal_antibody_labels_dlg").html(htmlStr);
            });
        	parent.$("#monoclonal_antibody_labels_dlg").dialog("open");
		}
	}
</script>
</head>
<body>
<form action="workorder_proc!print.action" method="post">
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td colspan="4" style="padding-top:10px;"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <tr>
                      <th width="10%">Work Order No</th>
                      <th width="90%">Label</th>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td colspan="4"><div  style="width:100%; height:210px; overflow:scroll;">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <s:iterator value="workOrderList" status="st">
                    	<s:if test="#st.odd">
                    		<tr>
                    			<td  width="10%">
                    				<a href="workorder_entry!edit.action?id=${orderNo}&operation_method=view" target="_blank">${orderNo}</a>
                    				<s:hidden name="orderNoArray">
                    					<s:param name="value">
                    						${orderNo}
                    					</s:param>
                    				</s:hidden>
                    			</td>
                        		<td width="90%">
                        			<span id="${orderNo}"><s:property value="labels" escape="false"/>
                        			<s:hidden name="labelArray" id="hidden_%{orderNo}">
                        				<s:param name="value">
                        					${labels}
                        				</s:param>
                        			</s:hidden>
                        			</span>
                        		</td>
                    		</tr>
                    	</s:if>
                    	<s:else>
                    		<tr>
                    			<td  class="list_td2">
                    				<a href="workorder_entry!edit.action?id=${orderNo}&operation_method=view" target="_blank">${orderNo}</a>
                    				<s:hidden name="orderNoArray">
                    					<s:param name="value">
                    						${orderNo}
                    					</s:param>
                    				</s:hidden>
                    			</td>
                        		<td class="list_td2">
                        		 <span id="${orderNo}"><s:property value="labels" escape="false"/>
                        		 <s:hidden name="labelArray" id="hidden_%{orderNo}">
                        				<s:param name="value">
                        					${labels}
                        				</s:param>
                        			</s:hidden>
                        			</span>
                        		 </td>
                        		 
                    		</tr>
                    	</s:else>
                    </s:iterator>
                    </table>
                  </div></td>
                </tr>
            
                <tr>
                  <td height="60" colspan="4"><div align="center">
                    <input id="sub1" name="Submit1" type="submit" class="style_botton"  value="Create"/>
					<input id="sub2" type="button" name="Submit2" value="Cancel" class="style_botton" onclick="parent.$('#print_labels_dlg').dialog('close');"/>
                  </div></td>
                </tr>
              </table>
</form>
</body>
</html>
