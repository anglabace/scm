<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />

<link href="${global_css_url }SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url }tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>

<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="${global_js_url}util/util.js"></script>
</head>
<script type="text/javascript">
var tableId = "subStepList";
function subStepAddAndEdit(id){
	parent.$('#subStepAddDialog').dialog("option", "open",function(){
		var htmlStr = '<iframe src="serv/serv!newSubStepItem.action?stepId='+id+'&sessionServiceId=${sessionServiceId}" height="260" width="540" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		parent.$('#subStepAddDialog').html(htmlStr);
	});						  
	parent.$('#subStepAddDialog').dialog('open');
}
$(document).ready(function(){
	$('tr:odd >td').addClass('list_td2');
	$('#upTableTd').click(function(){
		rowUp = $( '#'+tableId+' .tr_click' ).prev() ;
		rowSelect = $( '#'+tableId+' .tr_click' ) ;
		trUpMove( rowSelect,rowUp);
	});
	$('#downTableTd').click(function(){
		rowDown = $( '#'+tableId+' .tr_click' ).next() ;
		rowSelect = $( '#'+tableId+' .tr_click' ) ;
		trDownMove( rowSelect,rowDown);
	});
	$('span[id^="quantity_"]').parent("td").click(function(){
		var objSpan = $(this).find("span");
		var trRowNum = $(this).parent("tr").prevAll().length;
		var quantity = objSpan.html();
		var text_quantity = $('input[name="text_quantity"]').val();
		if(text_quantity<=0 || isNaN(text_quantity)){
			text_quantity = 1;
		}
		var objPrev = $('input[name="text_quantity"]').parent("span");
		var trPrevNum = $('input[name="text_quantity"]').parent("span").parent("td").parent("tr").prevAll().length;
		if(trRowNum != trPrevNum){
			objPrev.html(text_quantity);
		}
		if(!isNaN(quantity)){
			objSpan.html('<input type="text" size="5" name="text_quantity" value="'+quantity+'" />');
		}
		
		$('input[name="text_quantity"]').focus();
	});
	function trUpMove( rowSelf , rowTarget )
	{
		if( rowTarget.html() == null )
		{
			return ;
		}
		rowTarget.addClass( 'tr_click' ) ;
		rowTarget.removeClass("list_tr2");
		rowSelf.removeClass( 'tr_click' ) ;
		exchangeRow( rowTarget , rowSelf ) ;
		if(rowSelf.prevAll().length%2){
			rowSelf.addClass("list_tr2");
		}
	}
	function trDownMove( rowSelf , rowTarget )
	{
		if( rowTarget.html() == null )
		{
			return ;
		}
		rowTarget.addClass( 'tr_click' ) ;
		rowTarget.removeClass("list_tr2");
		rowSelf.removeClass( 'tr_click' ) ;
		exchangeRow( rowTarget , rowSelf ) ;
		if(rowSelf.prevAll().length%2){
			rowSelf.addClass("list_tr2");
		}
	}
	function exchangeRow( rowTarget , rowSelf )
	{
		var seqNo=0;
		if (rowTarget.attr("tagName") == "TR" && rowSelf.attr("tagName") == "TR" &&  rowTarget.children("td").length == rowSelf.children("td").length)
		{
			//var temp = new Array();
			for (i=0;i<rowTarget.children("td").length;i++)
			{
				//item no need't sort
				if( i == 1 )
				{
					continue ;
				}
				temp=rowTarget.children("td").eq(i).html();
				rowTarget.children("td").eq(i).html(rowSelf.children("td").eq(i).html());
				rowSelf.children("td").eq(i).html(temp);
			}
			var selId = rowSelf.attr("id");
			var nextId = rowTarget.attr("id");
			$.ajax({
				type: "POST",
				url: "serv/serv!saveSeqNo.action?sessionServiceId=${sessionServiceId}",
				data: 'selId=' + selId+'&nextId='+nextId,
				dataType: 'json',
				success: function(msg){
					if(msg.message == 'success'){
						
					}else{
						alert("System error! Please contact system administrator for help.");
					}
				},
				error: function(msg){
					$('#deleteSubStepTrigger').attr("disabled", false);	
					alert("System error! Please contact system administrator for help.");
				}	
			});
			rowSelf.attr("id",rowTarget.attr("id"));
			rowTarget.attr("id",selId);
		}
		
		$( '#'+tableId ).find( '#qtyTd' ).bind("click",function(){ 
			qtyTdClick( $(this) , 1 ) ;
		}) ;

		$("#itemNoSpan").html((rowTarget.children("td").find('#tdItemNo').html()));
	}
	function selectOneItem(itemId)
	{
		var selectOneItemId = itemId;
		
		if(isNaN(noValue)) return;
	}
	function tableClick( tableObj )
	{
		var trObjs = tableObj.parent().find( 'tr' );
		trNo = tableObj.prevAll().length;
		var hasClassTrClick = tableObj.hasClass("tr_click");
		trObjs.each(function(){
			trObjs.removeClass("tr_click");
			if($(this).prevAll().length%2 ==1 ){
				$(this).addClass("list_tr2");
			}
		});
		tableObj.removeClass();
		tableObj.addClass("tr_click");
		if(!hasClassTrClick){	
			var itemId = tableObj.find("input[type='checkbox']").get(0).value;
		}
	}
	$('#'+tableId+' tr').live('click' ,function(){
		tableClick($(this));
	});
});
$(function(){
	parent.$('#subStepAddDialog').dialog({
		autoOpen: false,
    	height: 320,
    	width: 600,
    	modal: true,
    	bgiframe: true,
    	buttons: {}
	});

	$("#subStepAddDialogTrigger").click(function(){
		parent.$('#subStepAddDialog').dialog("option", "open",function(){
			var htmlStr = '<iframe src="serv/serv!newSubStepItem.action?id=${id}&sessionServiceId=${sessionServiceId}" height="260" width="540" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			parent.$('#subStepAddDialog').html(htmlStr);
		});						  
		parent.$('#subStepAddDialog').dialog('open');
	});

	$("#deleteSubStepTrigger").click(function(){
    	var objSeq=$('[name="subStepSeq"]:checkbox:checked');
		var lenSeq = objSeq.length;
		if(!lenSeq) {
			alert("Please select one item to continue your operation.");return;
		}
        if(!confirm("Are you sure to delete?")){
            return;
        }
		var delStr="";
		for(i=0;i<lenSeq;i++){
			delStr += $('[name="subStepSeq"]:checkbox:checked').get(i).value + ",";
		}
		if(delStr){delStr = delStr.slice(0,-1);}
		$.ajax({
			type: "POST",
			url: "serv/serv!delSubStepItem.action?sessionServiceId=${sessionServiceId}",
			data: 'delSubStep=' + delStr,
			dataType: 'json',
			success: function(msg){
				alert(msg.message);
				if(msg.message == 'success'){
					var del_category = delStr.split(",");
					for(var i=0;i<del_category.length;i++){
						$("#del_"+del_category[i]).remove();
					}
				}else if(hasException(msg)) {
					$('#deleteSubStepTrigger').attr("disabled", false);	
				}else{
					$('#deleteSubStepTrigger').attr("disabled", false);	
					alert("System error! Please contact system administrator for help.");
				}
			},
			error: function(msg){
				$('#deleteSubStepTrigger').attr("disabled", false);	
				alert("Failed to delete the category.Please contact system administrator for help.");
			}	
		});
		return false;
    });
});
function   cc(e)  
{  
	var   a   =   document.getElementsByName("subStepSeq");  
	for   (var   i=0;   i<a.length;   i++) {
		a[i].checked = e.checked;
	}
} 
</script>
<body>
<div  class="general_box" style="height:390px;padding:8px; ">
<div id="Protein_01_se" class="more2">

    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><table width="490" border="0" cellpadding="0" cellspacing="0" class="list_table" >
          <tr>
            <th width="67">
            <div align="left" class="quote_dele">
          <input name="subStepCh" type="checkbox" onclick="cc(this)" />
          <img src="images/file_delete.gif" alt="Delete" id="deleteSubStepTrigger" width="16" height="16" border="0" /></div>		  
		  <div class="down_up" id="upTableTd"><img src="images/up.jpg" width="10" height="8"  title="up" style="cursor:pointer" /></div>
          <div class="down_up2" id="downTableTd"><img src="images/down.jpg" width="10" height="8" title="down" style="cursor:pointer" /></div>
            </th>
            <th width="144">Step No</th>
            <th>Step Name</th>
            </tr>
        </table>
          <div class="list_box">
            <table width="490" border="0" cellpadding="0" cellspacing="0" class="list_table2" id="subStepList" >
	             <s:iterator value="subSteps" >
		              	<tr id="del_${stepId }">
		                	<td width="67"><input type="checkbox" name="subStepSeq" value="${stepId }" /></td>
		                	<td width="144"><a href="javascript:void(0);" onclick="subStepAddAndEdit('${stepId}');" title="${stepNo}">${stepNo }</a>&nbsp;</td>
		                	<td> ${name }</td>
		                </tr>
	               </s:iterator>
            </table>
          </div>
          
         <div align="center" style="width:507px;"><br />
         	<input name="subStepAddDialogTrigger" id="subStepAddDialogTrigger" type="button" class="style_botton" value="New" />
         </div></td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>