// init division dialog window
$(function() {
	$("#divDialogWindow").dialog({
		bgiframe: true,
		autoOpen: false,
		height: 420,
		width: 670,			
		modal: true,
		beforeclose: function () {
			// this is an division picker hook, 
			// you can set the jQuery data in the "call" page
			if(!dataHolderWin) 
			{	
				return false;
			}

			if(dataHolderWin.jQuery.data(dataHolderWin.document.body, 'divPickerData'))
			{
				var divArray = dataHolderWin.jQuery.data(dataHolderWin.document.body, 'divPickerData').split('::', 2);
				var divid = divArray[0];
				var divname = divArray[1];
				var divLoc = dataHolderWin.jQuery.data(dataHolderWin.document.body, 'divLoc');
				var elemIdDivIds = dataHolderWin.jQuery.data(dataHolderWin.document.body, 'divIdStr').split(':');
				var elemIdDivNames = dataHolderWin.jQuery.data(dataHolderWin.document.body, 'divNameStr').split(':');
				
				if(!divLoc)
				{
					alert("System error! Please contact system administrator for help.");
					return false;
				}
				
				for(i=0; i<elemIdDivIds.length; i++)
				{
					if(divLoc.$('#'+elemIdDivIds[i]).get(0))
					{
						divLoc.$('#'+elemIdDivIds[i]).attr('value', divid);
					}
				}
				
				setFocus = false;
				focusElemId = '';
				for(i=0; i<elemIdDivNames.length; i++)
				{
					if(divLoc.$('#'+elemIdDivNames[i]))
					{
						divLoc.$('#'+elemIdDivNames[i]).attr('value', divname);
						if(setFocus === false)
						{
							divLoc.$('#'+elemIdDivNames[i]).focus();
							focusElemId = elemIdDivNames[i];
							setFocus = true;
						}
					}
				}
				
				divLoc.$('#'+focusElemId).trigger('blur');
				
				dataHolderWin.jQuery.data(dataHolderWin.document.body, 'divPickerData', '');
				dataHolderWin.jQuery.data(dataHolderWin.document.body, 'divLoc', '');
				dataHolderWin.jQuery.data(dataHolderWin.document.body, 'divIdStr', '');
				dataHolderWin.jQuery.data(dataHolderWin.document.body, 'divNameStr', '');
			}
		},
		close: function(){
			$('#divDialogWindow').html('');
		},
		open: function () {
			var orgId = $('#orgId').val();
			var htmlStr = '<iframe name="divDialog" id="divDialog" src="basedata/org_picker!showDivList.action?orgId='+ orgId +'" height="350" width="650" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			$('#divDialogWindow').html(htmlStr);
		}
	});
});