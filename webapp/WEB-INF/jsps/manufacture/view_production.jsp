<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/common/taglib.jsp"%>
	<head>
		<base id="myBaseId" href="${global_url}" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Order Instruction Management</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script src="${global_js_url}scm/config.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
		<script
			src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/jquery.form.js"
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
		$(function() { 
			
		}); 
		function show_edit(noteKey, searchNoteType){
			parent.$('#instruction_update_dlg').attr("title","Instruction/Notes for #"+noteKey);
			parent.$('#instruction_update_dlg').dialog("option","open", function(){
				var url = "order_note!loadProductionIns.action?noteKey="+noteKey + "&searchNoteType=" + searchNoteType;
				var htmlStr = '<iframe src="'+url+'" height="250" width="579" scrolling="auto" style="border:0px;" frameborder="0"></iframe>';
				parent.$('#instruction_update_dlg').html(htmlStr);
			});
			parent.$('#instruction_update_dlg').dialog('open'); 
		}
</script>

	</head>
<body>

 <table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table border="0" cellspacing="0" cellpadding="0">
              <tr>
                  <td style="padding-top:10px;"><table width="760" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <tr>
                      <th width="66">Order No</th>
                      <th width="80">Date</th>
                      <th width="105">Type</th>
                      <th width="68">Source</th>
                      <th width="55">Status</th>
                      <th width="60">Subject</th>
                      <th width="210">Description/Content</th>
                      <th >Created By </th>
                    </tr>
                  </table></td>
                </tr>
                <tr>
                  <td><div  style="width:777px; height:210px; overflow:scroll;">
                    <table width="760" border="0" cellpadding="0" cellspacing="0"  class="list_table">
                      <s:iterator value="noteMap" status="st">
                    <s:if test="#st.odd">
                    <tr>
                        <td width="66" ><a href="javascript:void(0)" onclick="show_edit('${key}', '${value.type}')">${value.orderNo}</a></td>
                        <td width="80" ><div align="center"><s:date name="value.instructionDate" format="yyyy-MM-dd" /></div></td>
                        <td width="105" >${value.type}</td>
                        <td width="68" >${value.source}</td>
                        <td width="55" >${value.status}</td>
                        <td width="60" >${value.subject}</td>
                        <td width="210" >${value.description}</td>
                        <td >${value.createUser}</td>
                      </tr>
                    </s:if>
                    <s:else>
                      <tr>
                        <td width="66" class="list_td2" ><a href="javascript:void(0)" onclick="show_edit('${key}', '${value.type}')">${value.orderNo}</a></td>
                        <td width="80" class="list_td2" ><div align="center"><s:date name="value.instructionDate" format="yyyy-MM-dd" /></div></td>
                        <td width="105" class="list_td2" >${value.type}</td>
                        <td width="68" class="list_td2" >${value.source}</td>
                        <td width="55" class="list_td2">${value.status}</td>
                        <td width="60" class="list_td2">${value.subject}</td>
                        <td width="210" class="list_td2">${value.description}</td>
                        <td class="list_td2">${value.createUser}</td>
                      </tr>
                    </s:else>
                    </s:iterator>
                    </table>
                  </div></td>
                </tr>
                <tr>
                  <td height="40"><div align="center">
                    <input id="sub2" type="submit" name="Submit2" value="Cancel" class="style_botton" onclick="parent.$('#production_instruction_dlg').dialog('close');"/>
                    </div></td>
                </tr>
              </table></td>
          </tr>
        </table> 
</body>
</html>