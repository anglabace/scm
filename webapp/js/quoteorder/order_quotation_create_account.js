
$(function()
{
	$( '#saveCardAcount' ).click(
		function()
		{
			if(!caFormCheck())
			{
				return ;
			}
			formObj = $( '#ccForm' ) ;
			//ajax form post
			var options = {
				success:function(data)
				{
					callbackAccount( data ) ;	
					alert( 'Save succeed' ) ;
					window.parent.$( '#createCardsAccountDialog' ).dialog( 'close' ) ;
				},
				error: function(){
					alert("System error! Please contact system administrator for help.");
				},
				resetForm:false,
				url:globalCfgObj.getUrl("saveCardAcount"),
				type:"POST",
				async: false , 
				dataType:"json"
			};
			formObj.ajaxForm(options);
			formObj.submit();
		}
	) ;
	
	$( '#cancelCardAcount' ).click(
		function()
		{
			window.parent.$( '#createCardsAccountDialog' ).dialog( 'close' ) ;
		}
	) ;
	
	function callbackAccount( cardAccount )
	{
		var ccInfo = "" ;
		ccInfo = "id:"+cardAccount.id+",ccType:"+cardAccount.ccType+",ccExprDate:"+cardAccount.ccExprDate+",ccNo:"+cardAccount.ccNo+",ccCvc:"+cardAccount.ccCvc+",accountHolder:"+cardAccount.accountHolder ;
		var cardListObj = window.parent.$( '#orderTotalIframe' ).contents().find('#cardList') ;

		cardListObj.append("<option value='"+ccInfo+"'>"+cardAccount.ccNo+"</option>"); 
	}
	
	function caFormCheck()
	{
		var msg = "" ;
		if( $('#ccType').val() == "" )
		{
			msg += "Please input Card Type \r\n" ;
		}
		if( $('#ccExprDate').val() == "" )
		{
			msg += "Please input Expiration Date \r\n" ;
		}
		if( $('#ccNo').val() == "" )
		{
			msg += "Please input Card Number \r\n" ;
		}
		if( $('#accountHolder').val() == "" )
		{
			msg += "Please input Name on the Card \r\n" ;
		}
		
		if( msg != "" )
		{
			alert( msg ) ;
			return false;
		}
		
		return true ;
	}
});
