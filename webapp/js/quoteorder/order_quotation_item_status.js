$( function()
{
	$("#status option[value='"+status+"']").attr( "selected" , true ); 
	$('#statusConfirm').click
	(
		function()
		{
			var itemStatus = "" ;
			itemStatus = $("#status").find("option:selected").text() ;
			window.parent.$('#itemTable').find( 'tr:eq('+currentRowNumber+')' ).find( 'td:eq(4)' ).find( 'span' ).html( itemStatus ) ;
			window.parent.$( '#changeStatusDialog' ).dialog( 'close' )  ;
		}
	 ) ;
	
	$('#closeDialog').click
	(
		function()
		{
			window.parent.$( '#changeStatusDialog' ).dialog( 'close' )  ;
		}
	);	
	
	$( '#status' ).change(
		function()
		{
			if( $(this).val() == "ON HOLD" || $(this).val() == "CANCELED")
			{
				$('#updateStatusTable').find( 'tr:gt(0) :lt(13)' ).css( 'display' , 'none' );
			}
			else
			{
				$('#updateStatusTable').find( 'tr:gt(0) :lt(13)' ).css( 'display' , '' );
			}
		}
	) ;
}) ;