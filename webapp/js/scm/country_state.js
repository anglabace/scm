/** 
 * This is used to display chain dropdown menu: country->state->city
 * @copyright GenScript Corp.
 */
var countryStateZip = {};
countryStateZip.infos = [];
countryStateZip.isInit = false;
countryStateZip.init = function(allInfos){
	var infos = eval(allInfos);
	countryStateZip.infos = infos;
	countryStateZip.isInit = true;
}

countryStateZip.getAllCountrys = function(){
	if(countryStateZip.isInit == false){
		return;
	}
	var rtCountrys = [];
	for(var c in countryStateZip.infos){
		var countryCode = countryStateZip.infos[c].countryCode;
		var countryName = countryStateZip.infos[c].name;
		rtCountrys.push({"countryCode":""+countryCode, "countryName":""+countryName});
	}
	return rtCountrys;
};

countryStateZip.getStatesByCountrys = function(countryCodes){
	if(countryStateZip.isInit == false){
		return;
	}
	var rtStates = [];
	for(var i in countryStateZip.infos){
		var info = countryStateZip.infos[i];
		for(var c in countryCodes){
			if(info.countryCode == countryCodes[c]){
				for(var s in info.allStateDTOs){
					var stateCode = info.allStateDTOs[s].stateCode;
					var stateName = info.allStateDTOs[s].name;
					rtStates.push({"stateCode":""+stateCode, "stateName":""+stateName, "countryCode":""+info.countryCode, "countryName":""+info.name});
				}
				break;
			}else{
				continue;
			}
		}
	}
	return rtStates;
};

countryStateZip.getZipCodeList = function(countryCodeStr, stateCodeStr, ajax, ajaxUrl){
	if(countryStateZip.isInit == false){
		return;
	}
	var tmpUrl = "util/get_all_country_state!getZipCodeList.action";
	if(ajaxUrl == ""){
		var data = "countryCodeStr="+countryCodeStr+"&stateCodeStr="+stateCodeStr+"&ajax="+ajax;
		tmpUrl += '?'+data;
	}else{
		tmpUrl = ajaxUrl;
	}
	var zips = [];
	$.ajax({
		  type: "get",
		  url: tmpUrl,
		  success: function(data){
			zips = data;
		  },
		  dataType: "json",
		  async: false,
		  error:function(data){
		  }
	});
	return zips;
};

