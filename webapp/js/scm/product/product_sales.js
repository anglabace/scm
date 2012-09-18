$(document).ready(function(){
	$("#salersSearch1").click(function(){
		var pdtId = $("#pdtId").val();
		var topCount = $("#topCount").val();
		var lastDays = $("#lastDays").val();
		if(parseInt(topCount) != topCount){
			alert("Please enter the correct 'Top'.");
			return;
		}
		$.ajax({
			url:"product/productSales/getTopSalePersonAct?pdtId="+pdtId,
			data:"topCount="+topCount+"&lastDays="+lastDays,
			dataType:"json",
			success:function(data){
				var tmpStr = "";
				var tmpClass = "";
				for(var k=0; k<data.length; k++){
					if(k%2 == 0){
						tmpClass = "";
					}else{
						tmpClass = "list_td2";
					}
					tmpStr += '<tr><td class="'+tmpClass+'" width="200">&nbsp;'+data[k].salesName+'</td><td class="'+tmpClass+'" width="200">&nbsp;'+data[k].sellQuality+'</td></tr>';
				}
				$("#salersTable").html(tmpStr);
			},
			error:function(){
				alert("System error! Please contact system administrator for help.");
			},
			async:false
		});
	});
});