function initCountryTable(countrys){
	var i = 1;
	$("#countryTable").html("");
	for(var c in countrys){
		var tmpVal = "'"+'{"countryCode":"'+countrys[c].countryCode+'", "stateCode":"", "zipCode":"", "countryName":"'+countrys[c].countryName+'"}'+"'";
		var tmpTr = '<tr><td width="30"><input _h='+tmpVal+' type="checkbox" name="country" value="'+countrys[c].countryCode+'" /></td><td width="80">'+i+'</td><td>'+countrys[c].countryName+'</td></tr>';
		$("#countryTable").append(tmpTr);
		i++;
	}
}

function initStateTable(countrys){
	var states = countryStateZip.getStatesByCountrys(countrys);
	var i = 1;
	$("#stateTable").html("");
	for(var s in states){
		var tmpVal = "'"+'{"countryCode":"'+states[s].countryCode+'", "stateCode":"'+states[s].stateCode+'", "zipCode":"", "countryName":"'+states[s].countryName+'", "stateName":"'+states[s].stateName+'"}'+"'";
		var tmpTr = '<tr><td width="30"><input _h='+tmpVal+' type="checkbox" value="'+states[s].stateCode+'" name="state" /></td><td width="80">'+i+'</td><td width="150">'+states[s].stateName+'</td><td width="240">'+states[s].countryName+'</td></tr>';
		$("#stateTable").append(tmpTr);
		i++;
	}
}

function initZipTable(ajaxUrl){
	$("#zipTable").html("");
	$("#pager").html("");
	if(ajaxUrl == ""){
		var countryCodes = [];
		var stateCodes = [];
		$(":checked[name='country']").each(function(i, n){
			countryCodes.push($(n).val());
		});
		$(":checked[name='state']").each(function(i, n){
			stateCodes.push($(n).val());
		});
		if(countryCodes.length == 0 || stateCodes.length == 0){
			return;
		}
		var countryCodeStr = countryCodes.toString();
		var stateCodeStr = stateCodes.toString();
	}else{
		var countryCodeStr = "";
		var stateCodeStr = "";
	}
	var zipsWithPager = countryStateZip.getZipCodeList(countryCodeStr, stateCodeStr, 'initZipTable', ajaxUrl);
	var zips = zipsWithPager.zipCodeList;
	var pager = zipsWithPager.pager;
	
	var i = 1;
	for(var s in zips){
		var tmpVal = "'"+'{"countryCode":"'+zips[s].country+'", "stateCode":"'+zips[s].state+'", "zipCode":"'+zips[s].zipCode+'", "countryName":"'+zips[s].countryName+'", "stateName":"'+zips[s].stateName+'"}'+"'";
		var tmpTr = '<tr><td width="30"><input _h='+tmpVal+' type="checkbox" value="'+zips[s].zipCode+'" name="zipCode" /></td><td width="80">'+i+'</td><td width="80">'+zips[s].zipCode+'</td><td width="150">'+zips[s].city+'</td><td width="50">'+zips[s].state+'</td><td>'+zips[s].country+'</td></tr>';
		$("#zipTable").append(tmpTr);
		i++;
	}

	if(i>1){
		$("#pager").html(pager);
	}else{
		$("#pager").html("");
	}
	
}

function initDateTr(dateVal){
	if(dateVal == 2){
		$("#dateSpan").show();
	}else{
		$("#effFrom").val("     -   -  ");
		$("#effTo").val("     -   -  ");
		$("#dateSpan").hide();
	}
}

function checkAll(allObj, checkName){
	var checked = '';
	if(allObj.checked){
		checked = true;
	}else{
		checked = false;
	}
	$("[name='"+checkName+"']").attr("checked", checked);
	if(!checked){
		$("#pager").html("");
	}
}

$(document).ready(function(){
	$('.pickdate').each(function(){
		$(this).datepicker(
			{
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
	})
	countryStateZip.init($("#init_countryStates").val());
	initCountryTable(countryStateZip.getAllCountrys());
	initStateTable("US");
	$("#countryTable,#countryTHTable").click(function(e){
		var tmpArr = [];
		$(":checked[name='country']").each(function(i, n){
			tmpArr.push($(n).val());
		});
		initStateTable(tmpArr);
	});
	
	$("#stateTable,#stateTHTable").click(function(e){
		initZipTable("");
	});
	$("#shipAreaSave").click(function(){
		var effFrom = $("#effFrom").val();
		var effTo = $("#effTo").val();
		if(effFrom>effTo){
			alert("Please select the correct time range.");
			return;
		}
		var tmpArr = [];
		$(":checked[name='zipCode']").each(function(i, n){
			tmpArr.push($(n).attr("_h"));
		});
		if(tmpArr.length == 0){
			$(":checked[name='state']").each(function(i, n){
				tmpArr.push($(n).attr("_h"));
			});
		}
		if(tmpArr.length == 0){
			$(":checked[name='country']").each(function(i, n){
				tmpArr.push($(n).attr("_h"));
			});
		}
		if(tmpArr.length == 0){
			parent.$('#shipAreaAddDialog').dialog('close');
			return;
		}
		var psId = $("#psId").val();
                var sessionServiceId=$("#sessionServiceId").val();
		var zipCodeStr = $("#zipCodeStr",parent.$("#inventroyIframe").contents().find("body")).val();
		$.ajax({
			  type: "post",
			  url: "serv/serv_inventory!save.action?psId="+psId+"&effFrom="+effFrom+"&effTo="+effTo+"&type="+parent.pdtServType+"&zipCodeStr="+zipCodeStr+"&sessionServiceId="+sessionServiceId,
			  data: 'areaList='+tmpArr.join("##"),
			  success: function(data){
			  	if(data){
				  	alert(data+" has been in the Relation Shipping Area");
				}else{
					parent.document.getElementById("inventroyIframe").src = parent.$("#inventroyIframe").attr("src");
					parent.$('#shipAreaAddDialog').dialog('close');
				}
			  },
			  dataType: "text",
			  async: false
		});
	});
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
});