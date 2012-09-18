function initDateTr(dateVal){
	if(dateVal == 2){
		$("#dateSpan").show();
	}else{
		$("#effFrom").val("     -   -  ");
		$("#effTo").val("     -   -  ");
		$("#dateSpan").hide();
	}
}

$(document).ready(function(){
	$('.pickdate').each(function(){
		$(this).datepicker({
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
			changeYear: true,
			yearRange: '-100:+0',
			beforeShow: function () {
                setTimeout(
                    function () {
                        $('#ui-datepicker-div').css("top", '63px');
                    }, 100
                );
            }
		});
	});
	$("#shipAreaEdit").click(function(){
		var psId = $("#psId").val();
		var effFrom = $("#effFrom").val();
		var effTo = $("#effTo").val();
		if(effFrom>effTo){
			alert("Please select the correct time range.");
			return;
		}
		var shipAreaId = $("#shipAreaId").val();
		var shipInfoOld = $("#shipInfoOld").val();
                var sessionPSID=$("#sessionPSID").val();
		$.ajax({
			  type: "post",
			  url: "product/inventory!saveEdit.action?psId="+psId+"&effFrom="+effFrom+"&effTo="+effTo+"&shipAreaId="+shipAreaId+"&shipInfo="+shipInfoOld+"&type="+parent.pdtServType+"&sessionPSID="+sessionPSID,
			  data: "",
			  success: function(data){
			  	if(data){
				  	alert(data);
				}else{
					parent.document.getElementById("inventroyIframe").src = parent.$("#inventroyIframe").attr("src");
					parent.$('#shipAreaEditDialog').dialog('close');
				}
			  },
			  dataType: "text",
			  async: false 
		});
	});
	$("#shipAreaCancel").click(function(){
		parent.$('#shipAreaEditDialog').dialog('close');
	});
	
	var effFrom = $("#effFrom").val();
	var effTo = $("#effTo").val();
	if(effFrom == "" && effTo == ""){
		$("[name='dateRestriction'][value='1']").attr("checked", true);
		initDateTr(1);
	}else{
		$("[name='dateRestriction'][value='2']").attr("checked", true);
		initDateTr(2);
	}
});