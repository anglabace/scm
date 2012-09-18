// init department dialog window
$(function() {
	$("#deptDialogWindow").dialog({
		bgiframe: true,
		autoOpen: false,
		height: 420,
		width: 670,			
		modal: true,
		beforeclose: function () {
			// this is an department picker hook, 
			// you can set the jQuery data in the "call" page
			if(!dataHolderWin) 
			{	
				return false;
			}

			if(dataHolderWin.jQuery.data(dataHolderWin.document.body, 'deptPickerData'))
			{
				var deptArray = dataHolderWin.jQuery.data(dataHolderWin.document.body, 'deptPickerData').split('::', 2);
				var deptid = deptArray[0];
				var deptname = deptArray[1];
				var deptLoc = dataHolderWin.jQuery.data(dataHolderWin.document.body, 'deptLoc');
				var elemIdDeptIds = dataHolderWin.jQuery.data(dataHolderWin.document.body, 'deptIdStr').split(':');
				var elemIdDeptNames = dataHolderWin.jQuery.data(dataHolderWin.document.body, 'deptNameStr').split(':');
				
				if(!deptLoc)
				{
					alert("System error! Please contact system administrator for help.");
					return false;
				}
				
				for(i=0; i<elemIdDeptIds.length; i++)
				{
					if(deptLoc.$('#'+elemIdDeptIds[i]).get(0))
					{
						deptLoc.$('#'+elemIdDeptIds[i]).attr('value', deptid);
					}
				}
				
				setFocus = false;
				focusElemId = '';
				for(i=0; i<elemIdDeptNames.length; i++)
				{
					if(deptLoc.$('#'+elemIdDeptNames[i]))
					{
						deptLoc.$('#'+elemIdDeptNames[i]).attr('value', deptname);
						if(setFocus === false)
						{
							deptLoc.$('#'+elemIdDeptNames[i]).focus();
							focusElemId = elemIdDeptNames[i];
							setFocus = true;
						}
					}
				}
				deptLoc.$('#'+focusElemId).trigger('blur');
					
				dataHolderWin.jQuery.data(dataHolderWin.document.body, 'deptPickerData', '');
				dataHolderWin.jQuery.data(dataHolderWin.document.body, 'deptLoc', '');
				dataHolderWin.jQuery.data(dataHolderWin.document.body, 'deptIdStr', '');
				dataHolderWin.jQuery.data(dataHolderWin.document.body, 'deptNameStr', '');
			}
		},
		close: function(){
			$('#deptDialogWindow').html('');
		},
		open: function () {
			var orgId = $('#orgId').val();
			var divId = $('#divId').val();
			var htmlStr = '<iframe name="deptDialog" id="deptDialog" src="basedata/org_picker!showDeptList.action?orgId='+orgId+'&divId='+divId+'" height="350" width="650" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			$('#deptDialogWindow').html(htmlStr);
		}
	});
});