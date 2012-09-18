$(document).ready(function(){
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
			var id = rowSelf.attr("id");
			rowSelf.attr("id",rowTarget.attr("id"));
			rowTarget.attr("id",id);
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