<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>

<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css"/>
		<script src="${global_js_url}scm/config.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
		<script type="text/javascript"
			src="${global_js_url}jquery/jquery.form.js"></script>
		<script src="${global_js_url}jquery/jquery.validate.js"
			type="text/javascript"></script>
<script type="text/javascript">
	function viewOrderNote (noteId) {
		window.open("order_note/order_note!viewOrderNote.action?noteKey=682&searchNoteType=SHIPMENT");
	}

	function show_edit(noteKey, searchNoteType){
		var params = '?noteKey='+noteKey + '&searchNoteType=' + searchNoteType;
		$('#instruction_view_dlg').dialog("option","params", params);
		$('#instruction_view_dlg').dialog('open'); 
	}

	$(document).ready(function(){
		$('#instruction_view_dlg').dialog({
			autoOpen: false,
			height: 400,
			width: 600,
			modal: true,
			bgiframe: true,
			buttons: {
			},
			open:function(){
				var params = $('#instruction_view_dlg').dialog("option", "params");
				var url = "order_note/order_note!viewOrderNote.action" + params;
		        var htmlStr = '<iframe src="'+url+'" height="350" width="570" scrolling="no" style="border:0px;" frameborder="0"></iframe>';
				$('#instruction_view_dlg').html(htmlStr);
			}
		});
	});
</script>
</head><body>
<!-- poped instruction display dialog -->
<div id="instruction_view_dlg" title="Instruction/Note"> </div> 
<table id="table11" bgcolor="#96bdea" border="0" cellpadding="0" cellspacing="3">
  <tbody><tr>
    <td bgcolor="#ffffff"><table border="0" cellpadding="0" cellspacing="0">
      <tbody><tr>
        <td align="left" background="images/header_bg.gif" valign="top" height="39"><div class="line_l_big">View Shipment Notes</div>
          <div class="line_r_big" onclick="window.parent.closeiframe()"><img src="images/w_close.gif" width="11" height="11"/>Close</div></td>
      </tr>
      <tr>
        <td style="padding-left: 20px; padding-right: 20px;"><br/>
          <table border="0" cellpadding="0" cellspacing="0">
          <tbody><tr>
            <td><table border="0" cellpadding="0" cellspacing="0">
              <tbody><tr>
                  <td style="padding-top: 10px;"><table class="list_table" border="0" cellpadding="0" cellspacing="0" width="760">
                    <tbody><tr>
						<th width="50">OrderNo</th>
						<th width="60">Date</th>
						<th width="70">Type</th>
						<th width="70">Source</th>
						<th width="60">Status</th>
						<th width="100">Subject</th>
						<th width="231">Description/Content</th>
						<th>Created By </th>
                    </tr>
                  </tbody></table></td>
                </tr>
                <tr>
                  <td><div style="width: 777px; height: 210px; overflow: scroll;">
                    <table class="list_table" border="0" cellpadding="0" cellspacing="0" width="760">
                      <tbody>
                      <c:set var="count" value="0"/>
                      <c:forEach var="orderNotesDTO" items="${orderNotesList}">
                      <c:if test="${count mod 2==0}">
                      <tr>
                      	<td class="list_td2" width="50">${orderNotesDTO.orderNo }</td>
                        <td class="list_td2" width="60"><div align="center">${orderNotesDTO.noteDate }</div></td>
                        <td class="list_td2" width="70">${orderNotesDTO.type }</td>
                        <td class="list_td2" width="70">${orderNotesDTO.source }</td>
                        <td class="list_td2" width="60">&nbsp;</td>
                        <td class="list_td2" width="100">&nbsp;</td>
                        <td class="list_td2" width="231"><a href="javascript:void(0)" onclick="show_edit('${orderNotesDTO.noteId}', '${orderNotesDTO.type}')">${orderNotesDTO.description }</a></td>
                        <td class="list_td2">${orderNotesDTO.modifiedBy.loginName }</td>
                      </tr>
                      </c:if>
                      <c:if test="${count mod 2==1}">
                      <tr>
                      	<td width="50">${orderNotesDTO.orderNo }</td>
                        <td width="60"><div align="center">${orderNotesDTO.noteDate }</div></td>
                        <td width="70">${orderNotesDTO.type }</td>
                        <td width="70">${orderNotesDTO.source }</td>
                        <td width="60">&nbsp;</td>
                        <td width="100">&nbsp;</td>
                        <td width="231"><a href="javascript:void(0)" onclick="show_edit('${orderNotesDTO.noteId}', '${orderNotesDTO.type}')">${orderNotesDTO.description }</a></td>
						<td>${orderNotesDTO.modifiedBy.loginName }</td>
                      </tr>
                      </c:if>
                      <c:set var="count" value="${count+1 }"></c:set>
                      </c:forEach>
                      
                    </tbody></table>
                  </div></td>
                </tr>
                <tr>
                  <td height="40"><div align="center">
                    <input id="sub1" name="Submit1" class="style_botton" value="Select" type="submit"/>
                    <input id="sub2" name="Submit2" value="Cancel" class="style_botton" onclick="window.parent.closeiframe()" type="submit"/>
                    </div></td>
                </tr>
              </tbody></table></td>
          </tr>
        </tbody></table>          </td>
      </tr>
    </tbody></table></td>
  </tr>
</tbody></table>

</body></html>