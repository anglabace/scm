<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
 <script language="javascript"> 
 var tableId = "itemTable";
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
				var sessionFormulaItemId = $("#sessionFormulaItemId").val();
				$.ajax({
					type: "POST",
					url: "serv/price_rule!saveSeqFormulaItem.action?sessionFormulaItemId="+sessionFormulaItemId,
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
			var trNo = tableObj.prevAll().length;
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
 function   cc(e)  
 {
 	var   a   =   document.getElementsByName('ids');
 	for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
 } 
 	function confirm1(){
		window.parent.closeiframe();
 	}
	function changeArgumentType(){
		var argumentType = $("#argumentType option:selected").val();
		$("#argument").empty();
		var html;
		if(argumentType=="Constant"||argumentType=="Parameter"||argumentType=="Function"){
		    html = '<input type="text" id="argumentText" name="argumentText"/>';
		    $("#argument").append(html);
		}else if(argumentType=="Operator"){
			html = $("#argumentOption").html();
			$("#argument").append(html);
		}else{
			html = $("#argumentValue").html();
			$("#argument").append(html);
		}
	}
	function save(){
		var argumentType = $("#argumentType option:selected").val();
		var argument;
		var argumentText;
		if(argumentType=="Operator"||argumentType=="Value"){
			argument = $("#argument option:selected").val();
			argumentText = $("#argument option:selected").text();
			if(!argument){
				alert("Please enter the Argument.");return;
			}
		}else{
			argument = $("#argumentText").val();
			argumentText =argument;
			if(!argument){
				alert("Please enter the Argument.");return;
			}
		}
		var seqNo = $("#itemTable tr:last td:eq(1)").text();
		seqNo++;
		if(argument=="+"){
			argument= "%2B";
		}
		var sessionFormulaItemId = $("#sessionFormulaItemId").val();
		var url = "serv/price_rule!saveFormulaItem.action?priceFormulaItem.seqNo="+seqNo+"&priceFormulaItem.type="+argumentType+"&priceFormulaItem.value="+argument+"&sessionFormulaItemId="+sessionFormulaItemId;
		$.ajax({
			type:"POST",
			url:url,
			dataType:"json",
			success: function(msg){
				if(msg.message=="success"){
					 var html = '<tr id="'+msg.id+'">';
                     html += '<td  width="65"><input type="checkbox" value="'+msg.id+'" name="ids"/></td>';
                     html += '<td  width="105" align="center">'+seqNo+'</td>';
                     html += '<td  width="173" align="center">'+argumentType+'</td>';
                     html += '<td align="center">'+argumentText+'</td>';
                     html += '</tr>';
                     $("#itemTable").append(html);
				}else{
					alert("System error! Please contact system administrator for help.");
				}
			},
			error: function(msg){
				alert("System error! Please contact system administrator for help.");
			}
		});
	}
	
	function delFormulaItem(name){
		var del_nos = get_checked_str(name);
		if(del_nos == '')
		{
			alert('Please select one item to continue your operation.');
			return;
		}
		if(!confirm('Are you sure to delete?'))
		{
			return;
		}
		var sessionFormulaItemId = $("#sessionFormulaItemId").val();
		$.ajax({
			type:"POST",
			url:"serv/price_rule!delFormulaItem.action?delStr="+del_nos+"&sessionFormulaItemId="+sessionFormulaItemId,
			dataType:"json",
			success: function(msg){
				if(msg.message=="success"){
					var del = del_nos.split(",");
					for(var i=0;i<del.length;i++){
						var delval=$("#"+del[i]+" td:eq(1)").text();
						$("#"+del[i]).remove();
						var len = $("#itemTable tr") ;
						if(len.length>0){
							for(var j=0;j<len.length;j++){
								var val = $("#itemTable tr:eq("+j+") td:eq(1)").text();
								if(val>delval){
									$("#itemTable tr:eq("+j+") td:eq(1)").text($("#itemTable tr:eq("+j+") td:eq(1)").text()-1);
								}
							} 
						}
					}
				}else{
					alert("System error! Please contact system administrator for help.");	
				}
			},
			error: function(msg){
				alert("Failed to remove the item. Please contact system administrator for help.");
			}
		});
	}
	function get_checked_str(name)
	{
		var a = document.getElementsByName(name);
		var str = '';
		for   (var   i=0;   i<a.length;   i++)
		{
			if(a[i].checked)
			{
				str += a[i].value+',';
			}
		}
		return str.substring(0,str.length-1);
	}
	function delSession(){
		var sessionFormulaItemId = $("#sessionFormulaItemId").val();
		var url = "serv/price_rule!delItemSession.action?sessionFormulaItemId="+sessionFormulaItemId;
		$.ajax({
			type:"POST",
			url:url,
			dataType:"json",
			success: function(msg){
				if(msg.message=="success"){
					window.parent.closeiframe();
				}else{
					alert("System error! Please contact system administrator for help.");
				}
			},
			error: function(msg){
				alert("System error! Please contact system administrator for help.");
			}
		});
	}
 </script>

 
</head>
 

<body>
<table width="600" border="0" cellspacing="3" cellpadding="0" id="table11" bgcolor="#96BDEA">
  <tr>
    <td bgcolor="#FFFFFF"><table width="600" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="39" align="left" valign="top" background="js/greybox/header_bg.gif"><div class="line_l_new">Define Formula</div>
          <div class="line_r_new" onclick="delSession()"><img src="js/greybox/w_close.gif" width="11" height="11" />Close</div></td>
      </tr>
      <tr>
        <td style="padding-left:20px;"><br />
          <table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td height="22" colspan="5">Define the formula by entering the arguments sequentially and adding them into the following form:</td>
                </tr>
              <tr>
                <th>Argument Type</th>
                <td>
                	<s:select list="optionItemList" name="argumentType" id="argumentType" listValue="value" listKey="value" cssStyle="width:112px;" onchange="changeArgumentType()"></s:select>
                 </td>
                <th width="100" align="right">Argument</th>
                <td><div id="argument">
                			<input type="text" id="argumentText" name="argumentText"/>
                	</div></td>
                <td><input name="Submit3" type="submit" class="style_botton" value="Add" onclick="save()"/></td>
              </tr>
            </table>
              <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td colspan="4" style="padding-top:10px;"><table width="540" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <tr>
                      <th width="65">
                      <div class="quote_dele">
                <input name="checkbox2"   type="checkbox"   onclick="cc(this)" />
              <img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" onclick="delFormulaItem('ids')"/></div>
                 <div class="down_up" id="upTableTd"><img src="images/up.jpg" width="10" height="8"  title="up" style="cursor:pointer" /></div>
          		 <div class="down_up2" id="downTableTd"><img src="images/down.jpg" width="10" height="8" title="down" style="cursor:pointer" /></div>
         	 </th>
                      <th width="105">Seq No</th>
                      <th width="173">Argument Type</th>
                      <th>Argument</th>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td colspan="4"><div  style="width:557px; height:210px; overflow:scroll;">
                    <table width="540" border="0" cellpadding="0" cellspacing="0" class="list_table" id="itemTable">
                    <s:iterator value="priceFormulaItemsList">
                      <tr id="${priceFormulaItem.id}">
                        <td  width="65"><input type="checkbox" value="${priceFormulaItem.id}" name="ids"/></td>
                        <td  width="105" align="center">${priceFormulaItem.seqNo}</td>
                        <td  width="173" align="center">${priceFormulaItem.type}</td>
                        <s:if test="priceFormulaItem.type==\"Value\"">
                        	 <td align="center">${valueName}</td>
                        </s:if>
                        <s:else>
                         <td align="center">${priceFormulaItem.value}</td>
                        </s:else>
                       
                        </tr>
                     </s:iterator>
                    </table>
                  </div></td>
                </tr>
                <tr>
                  <td colspan="4" valign="top">&nbsp;</td>
                </tr>
                <tr>
                  <td height="40" colspan="4"><div align="center">
                    <input id="sub1" name="Submit1" type="submit" class="style_botton"  value="Confirm" onclick="confirm1()"/>
                    <input id="sub2" type="submit" name="Submit2" value="Cancel" class="style_botton" onclick="delSession()"/>
                  </div></td>
                </tr>
              </table></td>
          </tr>
        </table>          <br /></td>
      </tr>
    </table></td>
  </tr>
</table>
<input type="hidden" name="sessionFormulaItemId" id="sessionFormulaItemId" value="${sessionFormulaItemId}"/>
<input type="hidden" name="sessionPriceRuleId" id="sessionPriceRuleId" value="${sessionPriceRuleId}"/>
<div id="argumentValue" style="display: none;">
	<s:select list="attributesList" name="argumentText" id="argumentText" listValue="attributeName" listKey="id" cssStyle="width:112px;"></s:select>
</div>
<div id="argumentOption" style="display: none;">
	<s:select list="operationItemList" name="argumentText" id="argumentText" listValue="value" listKey="value" cssStyle="width:112px;"></s:select>
</div>
</body>
</html>

