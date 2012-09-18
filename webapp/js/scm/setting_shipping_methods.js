$(function(){
	$('#saveAllTrigger').click(function(){
		var formStr = '';
		var id = document.getElementById('methodId').value;
		var urlStr = "shipping_method!save.action?id="+id;
		var hrefStr;
		if (!$('#methodCode').val()){
			alert ("Shipping method code should't be empty!");
			$('#methodCode').focus();
			return; 
		}
		if (!$('#name').val()){
			alert ("Shipping method name should't be empty!");
			$('#name').focus();
			return; 
		}
		formStr += $('#method_main').serialize() + "&";
		formStr += $('#method_general').serialize() + "&";
		var b_charge = $("#charge_frame").contents().find("#basic_charge_form");
		formStr += b_charge.serialize();
               // alert(formStr);
		var localIFrame = window.frames["charge_frame"].window.frames["total_range_frame"];
		if (localIFrame.document.getElementById('sepTotForAddr').checked){
			formStr += "&sepTotForAddr=Y";
		}else{
			formStr += "&sepTotForAddr=N";
		}

		$.ajax({
			type: "POST",
			url: urlStr,
			data: formStr,
			success: function(data, textStatus){
				var temp  = data.split('#');
				if (temp[0] == "SUCCESS"){
					alert ("Saved successfully.");
					$('#savedFlag').val('Y');
					location.href = "shipping_method!input.action?id="+temp[1]+"&activeTabIndex="+activeTabIndex['dhtmlgoodies_tabView1'];
				}else{
					alert ("There is already a shipping method has the same name exists, please choose another name.");
				}
			}
		});
	});
});

function get_rate(obj){
	var id = document.getElementById('id').value;
	var zoneId = obj.options[obj.selectedIndex].value;
	var zoneName = obj.options[obj.selectedIndex].text;
	var rateSlt = document.getElementById('standardRate');
	var reqUrl = 'shipping_method!getShipRateDropDown.action?zoneId='+zoneId+'&id='+id+'&zoneName='+zoneName;
	//alert (reqUrl);
	$.ajax({
		type: "POST",
		url: reqUrl,
		success: function(data, textStatus){
			if(data){
				rateSlt.options.length = 0;
				rateSlt.options.add(new Option('',''));
				var segment = data.split("#");
				if (segment.length > 1){
					for(i = 0; i < segment.length-1; i++){
						var part = segment[i].split("|");
						var uId = part[0];
						var uName = part[1];
						rateSlt.options.add(new Option(uName,uId));
					}

				}
				/*
				var rateDoc = window.frames['rate_frame'].document;
				var rateValue = rateDoc.getElementById('standardRateValue');
				rateValue.value = "";

				//update field value on page
				var zoneDoc = window.frames['zone_frame'].document;
				var zoneValue = zoneDoc.getElementById('standardZoneValue');
				zoneValue.value = zoneName;
				*/

				//refresh iframe rate_frame
				var zoneFrame = document.getElementById('zone_frame');
				//zoneFrame.src = zoneFrame.src;
				zoneFrame.src = 'shipping_method!listZone.action?id='+id;

				//refresh iframe rate_frame
				var rateFrame = document.getElementById('rate_frame');
				rateFrame.src = 'shipping_method!listRate.action?id='+id;
				//rateFrame.src = rateFrame.src;

			}
		},
		error: function(xhr, textStatus){
			alert("Failed to access the web server. Please contact system administrator for help.");
			if(textStatus == 'timeout')
			{
			}
			
			if(textStatus == 'parsererror')
			{
				tmp = xhr.responseText.split('{', 2);
				alert(tmp[0]);
			}
		}
	}); 
}

function change_rate(obj){
	var zoneSlt = document.getElementById('standardZone');
	var id = document.getElementById('id').value;
	var zoneId = zoneSlt.options[zoneSlt.selectedIndex].value;
	var rateName = obj.options[obj.selectedIndex].text;
	var rateId = obj.options[obj.selectedIndex].value;
	var reqUrl = 'shipping_method!getShipRateList.action?rateId='+rateId+'&id='+id+'&rateName='+rateName+'&zoneId='+zoneId;
	//alert (reqUrl);
	$.ajax({
		type: "POST",
		url: reqUrl,
		success: function(data, textStatus){
			var rateDoc = window.frames['rate_frame'].document;
			var rateValue = rateDoc.getElementById('standardRateValue');
			if(data == 'SUCCESS' || data == 'CLEAR'){
				//refresh iframe rate_frame
				var rateFrame = document.getElementById('rate_frame');
				rateFrame.src = rateFrame.src;
			/*
				//update field value on page
				var rateDoc = window.frames['rate_frame'].document;
				var rateValue = rateDoc.getElementById('standardRateValue');
				rateValue.value = rateName;
				*/
			}else{
				alert (data);
			}
		},
		error: function(xhr, textStatus){
			alert("Failed to access the web server. Please contact system administrator for help.");
			if(textStatus == 'timeout')
			{
			}
			if(textStatus == 'parsererror')
			{
				tmp = xhr.responseText.split('{', 2);
				alert(tmp[0]);
			}
		}
	}); 

}

function cancelAll(){
	if (confirm("Are you sure to cancel all the changes?")){
		$('#savedFlag').val('Y');
		window.location.reload()
	}
}

function get_track_url(slt){
	var carrier = slt.options[slt.selectedIndex].value;
	var reqUrl = 'shipping_method!getTrackUrl.action?carrier='+carrier;
	$.ajax({
		type: "POST",
		url: reqUrl,
		success: function(data, textStatus){
			$('#trackUrl').val(data);
		},
		error: function(xhr, textStatus){
			alert("Failed to access the web server. Please contact system administrator for help.");
			if(textStatus == 'timeout')
			{
			}
			
			if(textStatus == 'parsererror')
			{
				tmp = xhr.responseText.split('{', 2);
				alert(tmp[0]);
			}
		}
	}); 

	
}
