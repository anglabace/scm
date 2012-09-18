$(function(){
	$("[title='discType']").each(function(i, n){
		if($(n).attr("checked")){
			if(i==2){
				var rdo = document.getElementsByName('rdo_specical_sell');
				if (rdo[0].checked){
					$('#specialDiscPercent').val("");
					$('#specialDiscPercent').attr('disabled', 'disabled');
					$('#discPrice').removeAttr('disabled');
				}else{
					$('#discPrice').val(""); 
					$('#discPrice').attr('disabled', 'disabled');
					$('#specialDiscPercent').removeAttr('disabled');
				}
			}else if(i == 3){
				var rdo = document.getElementsByName('rdo_button');
				if (rdo[0].checked){
					$('#discCateAmount').val("");
					$('#discOrderTotal2').val("");
					$('#discCategory2').val("");
					$('#discAmountPercentType').val("Percent");
					$('#discCateAmount').attr('disabled', 'disabled');
					$('#discOrderTotal2').attr('disabled', 'disabled');
					$('#discCatePercent').removeAttr('disabled');
					$('#discOrderTotal1').removeAttr('disabled');
					$('#discCategory2').attr('disabled', 'disabled');
					$('#discCategory1').removeAttr('disabled');
					$('#discCategoryImg2').css("color","#ccc").css("text-decoration","none");
				}else{
					$('#discCatePercent').val("");
					$('#discOrderTotal1').val("");
					$('#discCategory1').val("");
					$('#discAmountPercentType').val("Amount");
					$('#discCatePercent').attr('disabled', 'disabled');
					$('#discOrderTotal1').attr('disabled', 'disabled');
					$('#discCateAmount').removeAttr('disabled');
					$('#discOrderTotal2').removeAttr('disabled');
					$('#discCategory1').attr('disabled', 'disabled');
					$('#discCategory2').removeAttr('disabled');
					$('#discCategoryImg1').css("color","#ccc").css("text-decoration","none");
				}
			}
		}else{
			if(i==0){
				$('#discPercent').attr("disabled", "disabled");
				$('#orderTotalMin1').attr("disabled", "disabled");
				$('#discPercent').val('');
				$('#orderTotalMin1').val('');
			}else if(i==1){
				$('#discAmount').attr("disabled", "disabled");
				$('#orderTotalMin2').attr("disabled", "disabled");
				$('#discAmount').val("");
				$('#orderTotalMin2').val("");
			}else if(i==2){
				$('#discProdQty').attr("disabled", "disabled");
				//$('#discProd').attr("disabled", "disabled");
				$('#orderTotalMin3').attr("disabled", "disabled");
				$('#discNotation').attr("disabled", "disabled");
				$('#chk_special_sell').attr("disabled", "disabled");
				$("input[name='rdo_specical_sell']").attr("disabled", "disabled");
				$('#discPrice').attr("disabled", "disabled");
				$('#specialDiscPercent').attr("disabled", "disabled");
				$('#discCatalogImg').attr("disabled", "disabled");
				$('#discProdQty').val("");
				$('#discProd').val("");
				$('#orderTotalMin3').val("");
				$('#discNotation').val("");
				$('#discPrice').val("");
				$('#specialDiscPercent').val("");
			}else if(i==3){			
				$('#discCatePercent').attr("disabled", "disabled");
				$("input[name='discOrderTotal']").attr('disabled','disabled');
				$('#discCateAmount').attr("disabled", "disabled");
				$("input[name='rdo_button']").attr("disabled", "disabled");
				$('#discCategoryImg1').attr("disabled", "disabled");
				$('#discCategoryImg2').attr("disabled", "disabled");
				$('#discCategoryType').attr("disabled", "disabled");
				$('#discCategory1').attr('disabled', 'disabled');
				$('#discCategory2').attr('disabled', 'disabled');
				$('#discCatePercent').val("");
				$("input[name='discCategory']").val("");
				$("input[name='discOrderTotal']").val("");
				$('#discCateAmount').val("");
			}
		}
	});
	$("#chk_special_sell")
		.click(function(){
			$("#discPrice").val('');
			$("#specialDiscPercent").val('');
			if (this.checked){
				$("#discPrice").removeAttr('disabled');
				$("#specialDiscPercent").removeAttr('disabled');
				$("input[name='rdo_specical_sell']").removeAttr('disabled');
			}else{
				$("#discPrice").attr('disabled', 'disabled');
				$("#specialDiscPercent").attr('disabled', 'disabled');
				$("input[name='rdo_specical_sell']").attr('disabled','disabled');
			}
	});

	$("#shipDiscFlag")
		.click(function(){
			if (this.checked){
				$("#shipAmount").removeAttr('disabled');
				$("#shipMethod").removeAttr('disabled');
				$("#shipOrderTotal").removeAttr('disabled');
			}else{
				$("#shipAmount").attr('disabled', 'disabled');
				$("#shipMethod").attr('disabled', 'disabled');
				$("#shipOrderTotal").attr('disabled', 'disabled');
			}
	});

	$("input[name='rdo_specical_sell']")
		.click(function(){
			var rdo = document.getElementsByName('rdo_specical_sell');
			if (rdo[0].checked){
				$('#specialDiscPercent').val("");
				$('#specialDiscPercent').attr('disabled', 'disabled');
				$('#discPrice').removeAttr('disabled');
			}else{
				$('#discPrice').val(""); 
				$('#discPrice').attr('disabled', 'disabled');
				$('#specialDiscPercent').removeAttr('disabled');
			}
	});
	
	$("input[name='rdo_button']")
	.click(function(){
		var rdo = document.getElementsByName('rdo_button');
		if (rdo[0].checked){
			$('#discCateAmount').val("");
			$('#discOrderTotal2').val("");
			$('#discCategory2').val("");
			$('#discAmountPercentType').val("Percent");
			$('#discCateAmount').attr('disabled', 'disabled');
			$('#discOrderTotal2').attr('disabled', 'disabled');
			$('#discCategoryImg2').attr("disabled", "disabled");
			$('#discCategory2').attr('disabled', 'disabled');
			$('#discCategoryImg1').removeAttr('disabled');
			$('#discCatePercent').removeAttr('disabled');
			$('#discOrderTotal1').removeAttr('disabled');
			$('#discCategory1').removeAttr('disabled');
		}else{
			$('#discCatePercent').val("");
			$('#discOrderTotal1').val("");
			$('#discCategory1').val("");
			$('#discAmountPercentType').val("Amount");
			$('#discCatePercent').attr('disabled', 'disabled');
			$('#discOrderTotal1').attr('disabled', 'disabled');
			$('#discCategoryImg1').attr("disabled", "disabled");
			$('#discCategory1').attr('disabled', 'disabled');
			$('#discCategoryImg2').removeAttr('disabled');
			$('#discCateAmount').removeAttr('disabled');
			$('#discOrderTotal2').removeAttr('disabled');
			$('#discCategory2').removeAttr('disabled');
		}
});
});

function clear4t1(e){
	if(!e.checked){
		$('#discPercent').attr("disabled", "disabled");
		$('#orderTotalMin1').attr("disabled", "disabled");
		$('#discPercent').val('');
		$('#orderTotalMin1').val('');
	}else{
		$('#discPercent').removeAttr("disabled");
		$('#orderTotalMin1').removeAttr("disabled");
	}
	//document.getElementById('chk_special_sell').checked = false;
	//document.getElementsByName('rdo_specical_sell')[0].checked = false;
	//document.getElementsByName('rdo_specical_sell')[1].checked = false;

}
function clear4t2(e){
	if(!e.checked){
		$('#discAmount').attr("disabled", "disabled");
		$('#orderTotalMin2').attr("disabled", "disabled");
		$('#discAmount').val("");
		$('#orderTotalMin2').val("");
	}else{
		$('#discAmount').removeAttr("disabled");
		$('#orderTotalMin2').removeAttr("disabled");
	}

}
function clear4t3(e){
	if(!e.checked){
		$('#discProdQty').attr("disabled", "disabled");
		$('#discProd').attr("disabled", "disabled");
		$('#orderTotalMin3').attr("disabled", "disabled");
		$('#discNotation').attr("disabled", "disabled");
		$('#chk_special_sell').attr("disabled", "disabled");
		$("input[name='rdo_specical_sell']").attr("disabled", "disabled");
		$('#discPrice').attr("disabled", "disabled");
		$('#specialDiscPercent').attr("disabled", "disabled");
		$('#discCatalogImg').attr("disabled", "disabled");
		$('#discProdQty').val("");
		$('#discProd').val("");
		$('#orderTotalMin3').val("");
		$('#discNotation').val("");
		$('#discPrice').val("");
		$('#specialDiscPercent').val("");
	}else{
		$('#discProdQty').removeAttr("disabled");
		//$('#discProd').removeAttr("disabled");
		$('#orderTotalMin3').removeAttr("disabled");
		$('#discNotation').removeAttr("disabled");
		$('#chk_special_sell').removeAttr("disabled");
		$('#discCatalogImg').removeAttr("disabled");
		$("input[name='rdo_specical_sell']").removeAttr("disabled");
		//alert($("input[id='chk_special_sell']:checked").length);
		if($("input[id='chk_special_sell']:checked").length == 0){
			$("input[name='rdo_specical_sell']").attr("disabled", "disabled");
			$('#discPrice').attr("disabled", "disabled");
			$('#specialDiscPercent').attr("disabled", "disabled");
		}else{
			$('#discPrice').removeAttr("disabled");
			$('#specialDiscPercent').removeAttr("disabled");
		}
	}
}

function clear4t4(e){
	if(!e.checked){
		$('#discCatePercent').attr("disabled", "disabled");
		$("input[name='discCategory']").attr('disabled','disabled');
		$("input[name='discOrderTotal']").attr('disabled','disabled');
		$('#discCateAmount').attr("disabled", "disabled");
		$("input[name='rdo_button']").attr("disabled", "disabled");
		$('#discCategoryType').attr("disabled", "disabled");
		$('#discCategoryImg1').attr("disabled", "disabled");
		$('#discCategoryImg2').attr("disabled", "disabled");
		$('#discCatePercent').val("");
		$("input[name='discCategory']").val("");
		$("input[name='discOrderTotal']").val("");
		$('#discCateAmount').val("");
		$('#discAmountPercentType').val("");
	}else{
		$('#discCatePercent').removeAttr("disabled");
		$("input[name='discCategory']").removeAttr("disabled");
		$("input[name='discOrderTotal']").removeAttr("disabled");
		$('#discCategoryType').removeAttr("disabled");
		$('#discCategoryImg1').removeAttr("disabled");
		$('#discCategoryImg2').removeAttr("disabled");
		$('#discCateAmount').removeAttr("disabled");
		$("input[name='rdo_button']").removeAttr("disabled");
		$('#discAmountPercentType').val("");
	}
}

function discTypeStr(){
	var tmpArr = [];
	$("[title='discType']").each(function(i, n){
		if($(n).attr("checked")){
			tmpArr.push(1);
		}else{
			tmpArr.push(0);
		}
	});
	return tmpArr.join(";");
}

//update promotion in session
function update_promotion(){
	if($("#promotion_form").valid() == false) {
		return false;
	}
    /*
     add by zhanghuibin
     验证code不能重复
     * */
    var flag = true;
    var code = $("#prmtCode").val();
    if (code != "") {
        $.ajax({
            url: "quote_order_promotion!validDupCode.action?code=" + code,
            success:function(data) {
                if (data == "N") {
                    alert("The code of the repeat!");
                    flag = false;
                }
            },
            error: function() {
                alert("System error! Please contact system administrator for help.");
            },
            type:"POST",
            async:false,
            dataType:"text"
        });
    }
    if(!flag) return;
	var orderEffTo = new Date($('#orderEffTo').val().replace(/-/g, "/"));
	var orderEffFrom = new Date($('#orderEffFrom').val().replace(/-/g, "/"));
	if(orderEffTo < orderEffFrom){
		alert("Start Date must be earlier than End Date.");
		return;
	}
	var tmpFlag = '';
	$("[title='discType']").each(function(i, n){
		if($(n).attr("checked")){
			if(i==0){
				if (!$('#discPercent').val() ){
					alert ("This shouldn't be empty!");	
					$('#discPercent').focus();
					tmpFlag = 1;
					return false;
				}
				if ( ! $('#orderTotalMin1').val()){
					alert ("This shouldn't be empty!");	
					$('#orderTotalMin1').focus(); 
					tmpFlag = 1;
					return false;
				}
			}else if(i==1){
				if (! $('#discAmount').val() ){
					alert ("This shouldn't be empty!");	
					$('#discAmount').focus();
					return false;
				}
				if ( ! $('#orderTotalMin2').val()){
					alert ("This shouldn't be empty!");	
					$('#orderTotalMin2').focus(); 
					tmpFlag = 1;
					return false;
				}
			}else if(i==2){
				if (! $('#discProdQty').val() ){
					alert ("This shouldn't be empty!");	
					$('#discProdQty').focus();
					tmpFlag = 1;
					return false;
				}
				if (! $('#discProd').val() ){
					alert ("This shouldn't be empty!");	
					$('#discProd').focus();
					tmpFlag = 1;
					return false;
				}
				if ( ! $('#orderTotalMin3').val()){
					alert ("This shouldn't be empty!");	
					$('#orderTotalMin3').focus(); 
					tmpFlag = 1;
					return false;
				}
				if (document.getElementById('chk_special_sell').checked == true){
					if (! $('#discPrice').val() && ! $('#discPercent_t3').val()){
						alert ("Speical selling price shouldn't be empty!");	
						$('#discPrice').focus();
						tmpFlag = 1;
						return false;
					}
				}
			}else if(i==3){
				var rdo = document.getElementsByName('rdo_button');
				if (rdo[0].checked){
					if ( ! $('#discCatePercent').val()){
						alert ("This shouldn't be empty!");	
						$('#discCatePercent').focus(); 
						tmpFlag = 1;
						return false;
					}
					if ( ! $('#discOrderTotal1').val()){
						alert ("This shouldn't be empty!");	
						$('#discOrderTotal1').focus(); 
						tmpFlag = 1;
						return false;
					}
					if ( ! $('#discCategory1').val()){
						alert ("This shouldn't be empty!");	
						$('#discCategory1').focus(); 
						tmpFlag = 1;
						return;
					}
				}else{
					if ( ! $('#discCateAmount').val()){
						alert ("This shouldn't be empty!");	
						$('#discCateAmount').focus(); 
						tmpFlag = 1;
						return false;
					}
					if ( ! $('#discOrderTotal2').val()){
						alert ("This shouldn't be empty!");	
						$('#discOrderTotal2').focus();
						tmpFlag = 1;
						return false;
					}
					if ( ! $('#discCategory2').val()){
						alert ("This shouldn't be empty!");	
						$('#discCategory2').focus();
						tmpFlag = 1;
						return false;
					}
				}
			}
		}else{
		}
	});
	if(tmpFlag == 1){
		return;
	}
	//var discountType = document.getElementsByName('discType'); 
	//var discStr, orderTotalMin;
	//if (discountType[0].checked){
		

	var op_type = $("#opType").val(); 
	var action = 'quote_order_promotion!save.action'+ '?discType='+discTypeStr();
	var form = $("#promotion_form"); 
	var promoId = $('#id').val();
	var orderSourceStr = "";
	if ($("#orderSource").val()){
		var ttt = document.getElementById('orderSource');
		orderSourceStr = ttt.options[ttt.selectedIndex].text;
	}
	var rfmStr = "";
	if ($("#rfmValue").val()){
		var ttt = document.getElementById('rfmValue');
		rfmStr = ttt.options[ttt.selectedIndex].text;
	}

	var slt = document.getElementById('applyType');
	var appType = slt.options[slt.selectedIndex].value;
	//var linkStr = '<a href="javascript:void(0)" onclick="javascript:show_edit_promotion(\''+promoId+'\')">'+ $('#prmtCode').val()+'</a>';
	var options = {
		success:function(data) {
			//alert (data); 
			var s1 = data.split(',')[0];
			var s2 = data.split(',')[1];
			var linkStr = '<a href="javascript:void(0)" onclick="javascript:show_edit_promotion(\''+s2+'\')">'+ $('#prmtCode').val()+'</a>';
			if(s1 == "SUCCESS"){
				// refresh the promotion list
				if (op_type == 'add'){
					var content = "<tr id='prmt_row_"+s2+"'>"; 
					content += "<td width='40'><input type='checkbox' name='prm' value='"+s2+"' /></td>";

					content += "<td width='80'><span id='code_"+s2+"'>";
					content += linkStr;
					content += "</span></td>";

					content += "<td width='196'>&nbsp;<span id='desc_"+s2+"'>"+$('#description').val()+"</span></td>";
					content += "<td width='90'>&nbsp;<span id='apply_"+s2+"'>"+appType+"</span></td>";

					//content += "<td width='70'>&nbsp;<span id='discType_"+s2+"'>" + discStr + "</span></td>"; 

					content += "<td width='60'>&nbsp;<span id='rfm_"+s2+"'>"+rfmStr+"</span></td>";

					content += "<td width='80'>&nbsp;<span id='source_"+s2+"'>"+orderSourceStr+"</span></td>";
					//content += "<td width='100'>&nbsp;<span id='eff_"+s2+"'>"+orderTotalMin;
					//if ($('#orderTotalMax').val() != undefined){
					//	content += "~" + $('#orderTotalMax').val();
					//}
					//content += "</span></td>";

					content += "<td width='90'>&nbsp;<span id='from_"+s2+"'>";
					content += "</span></td>";

					content += "<td width='90'>&nbsp;<span id='to_"+s2+"'>";
					if ($('#orderEffTo').val() != undefined){
						var orderEffTo = new Date($('#orderEffTo').val().replace(/-/g, "/"));
						content += orderEffTo.format("yyyy-mm-dd");
					}
					content += "</span></td>";

					parent.$('#promotionSearchResult').append(content); 
					parent.$('#promotion_add_dialog').dialog('close'); 
				}else{
					parent.$('#code_'+s2).html(linkStr);
					parent.$('#desc_'+s2).html($('#description').val());
					parent.$('#apply_'+s2).html(appType);
					//parent.$('#discType_'+s2).html(discStr);
					parent.$('#rfm_'+s2).html(rfmStr);
					parent.$('#source_'+s2).html(orderSourceStr);

					//var total = orderTotalMin;
					//if ($('#orderTotalMax').val()){
					//	total += "~" + $('#orderTotalMax').val();
					//}
					//parent.$('#eff_'+s2).html(total);
					parent.$('#from_'+s2).html($('#orderEffFrom').val());
					parent.$('#to_'+s2).html($('#orderEffTo').val());
					parent.$('#promotion_edit_dialog').dialog('close'); 
				}
			}else{
				alert(data); 
			}
		}, 
		error: function(){
			alert('error...'); 
		}, 
		resetForm:false, 
		url:action,
		type:"POST" 
	}; 
	form.ajaxForm(options);
	form.submit();
}

function toggle_special(obj, which){
	if (obj.value && which == 'price'){
		$("input[name=rdo_specical_sell]").get(0).checked = true; 
		$('#discPercent_t3').val('');
	}
	if (obj.value && which == 'percent'){
		$("input[name=rdo_specical_sell]").get(1).checked = true; 
		$('#discPrice').val('');
	}
}
