function checkAskDate(DateId){
	var StartDate = document.getElementById(DateId);
	if(!CheckDate(StartDate.value)){
		alert('Please enter a valid Date, the right format is "YYYY-MM-DD"');
		StartDate.focus();
		return false;
	}
	return true;
}

// date format is "MM/DD/YYYY"
// return: startDate > endDate  return 1;
//         startDate = endDate  return 0;
//         startDate < endDate  return -1;
function compareDate(startDate, endDate){
	if(startDate.length>0&&endDate.length>0){
		var arrStartDate = startDate.split("-");
		var arrEndDate = endDate.split("-");
		var allStartDate = new Date(arrStartDate[0], arrStartDate[1], arrStartDate[2]);
		var allEndDate = new Date(arrEndDate[0], arrEndDate[1], arrEndDate[2]);
		if(allStartDate.getTime() > allEndDate.getTime()){
			return 1;
		}else if(allStartDate.getTime() == allEndDate.getTime()){
			return 0;
		}else{
			return -1;
		}
	}
	return false;
}

//
function CheckDate(strDate){
	var re = new RegExp("^([0-9]{4})[-]{1}([0-9]{1,2})[-]{1}([0-9]{1,2})$");
	var ar;
	var res = true;

	if ((ar = re.exec(strDate)) != null){
		var i;
		i = parseFloat(ar[3]);
		// verify dd
		if (i <= 0 || i > 31){
			res = false;
		}
		i = parseFloat(ar[2]);
		// verify mm
		if (i <= 0 || i > 12){
			res = false;
		}
	}else{
		res = false;
	}

	if (!res){
		return false;
	}else{
		return true;
	}
	return res;
}

function getTodayDate()
{
	var s  = "";   // Declare variables.
	var d  = new Date();              // Create Date object with today's date.
	s += d.getFullYear();                       // Get year.
	s += "-"+(d.getMonth() + 1);          // Get month
	s += "-"+d.getDate();                 // Get day
	return s;
}