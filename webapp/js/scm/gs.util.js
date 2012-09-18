/**
 * Normal functions for element operation
 * @author LHC
 */
function setDefault(){
	var elementId = arguments[0];
	var elementVal = arguments[1];
	var elementType = $('#'+elementId).attr('type');
	
	// text 
	if(elementType == "text")	$('#'+elementId).attr('value', elementVal);
	
	//select-one
	if(elementType == "select-one")	$('#'+elementId).attr('value', elementVal);
	
	// TODO: other type of element
}

// this will be moved into other files;
$(document).ready(function(){
	$('.ui-datepicker-birth').each(function(){
		$(this).datepicker(
				{
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true,
					yearRange: '-80:+0'
				});
	});
	$('.ui-datepicker').each(function(){
		$(this).datepicker(
				{
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true
				});
	})
});

//return start and end date between given period
function getPeriodDate(period){
	var currentTime = new Date();
	var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var today = currentTime.getDay();
	//alert ('type:' + period);
	if (period == 'Since First Date'){
		dateFrom = "2009-01-01";
		dateTo = "2011-01-01";
	}else if (period == 'Last Year'){
		year--;
		dateFrom = year.toString() + "-01-01";
		dateTo = year.toString() + "-12-31";
	}else if (period == 'This Year'){
		dateFrom = year.toString() + "-01-01";
		dateTo =  formatDate(currentTime);
	}else if (period == 'Last Week'){
		temp =  disDate(currentTime, today);
		dateTo =  formatDate(temp);
		temp =  disDate(currentTime, 7+today);
		dateFrom =  formatDate(temp);
	}else if (period == 'This Week'){
		temp =  disDate(currentTime, today);
		dateTo =  formatDate(currentTime);
		temp =  disDate(currentTime, today-1);
		dateFrom =  formatDate(temp);
	}else if (period == 'Last Month'){
		if (month == 1){
			month = 12;
			year--;
		}else{
			month--;
		}
		month = check_format(month);
		dateFrom = year.toString() + '-' + month + "-01";
		dateTo = year.toString() + '-' + month + "-31";
	}else if (period == 'This Month'){
		month = check_format(month);
		dateFrom = year.toString() + '-' + month + "-01";
		dateTo =  formatDate(currentTime);
	}else if (period == 'Last Quarter'){
		if (month < 4){
			year--;
			dateFrom = year.toString() + '-10-01';
			dateTo = year.toString() + '-12-31'; 
		}else{
			if (month > 10){
				dateFrom = year.toString() + '-07-01';
				dateTo = year.toString() + '-09-30'; 
			}else if (month > 7){
				dateFrom = year.toString() + '-04-01';
				dateTo = year.toString() + '-06-30'; 
			}else{
				dateFrom = year.toString() + '-01-01';
				dateTo = year.toString() + '-03-30'; 
			}
		}
	}else if (period == 'This Quarter'){
		if (month < 4){
			dateFrom = year.toString() + '-10-01';
			dateTo = year.toString() + '-12-31'; 
		}else{
			if (month > 10){
				dateFrom = year.toString() + '-10-01';
				dateTo = year.toString() + '-12-30'; 
			}else if (month > 7){
				dateFrom = year.toString() + '-07-01';
				dateTo = year.toString() + '-09-30'; 
			}else if (month > 4){
				dateFrom = year.toString() + '-04-01';
				dateTo = year.toString() + '-06-30'; 
			}else{
				dateFrom = year.toString() + '-01-01';
				dateTo = year.toString() + '-03-30'; 
			}
		}
		dateFrom = "2010-01-01";
		dateTo = "2010-04-01";
	}else if (period == 'Last 6 Months'){
		if (month < 6){
			start_month = 12 - 7 + month;
			year--;
		}else{
			start_month = start_month - 6;
		}
		month--;
		dateFrom = year.toString() + '-' + start_month.toString() + "-01";
		dateTo = year.toString() + '-' + month.toString() + "-31";
	}
	//alert (dateFrom + '--' + dateTo);
	var result = new Array(dateFrom,dateTo);
	return result; 
}

//calculate the date of x days before, if days < 0, then x days later
function disDate(oDate,days){
	var ms = oDate.getTime();
	ms -= days*24*60*60*1000;
	return new Date(ms);
}

//return the date in yyyy-mm-dd format
function formatDate(currentTime){
	var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	if (day < 10)
		day = '0' + day.toString();
	if (month < 10)
		month = '0' + month.toString();
	return  year.toString()+'-'+ month.toString() +'-' + day.toString();

}
function check_format(num){
	if (num < 10)
		return ('0' + num.toString());

}

//set table width, sample code: customer_web_behavior.js
function getTD(row, value, width, href_text_length){
	var line_style = " class='list_td2'";
	if (row % 2 == 0){
		line_style = "";
	}
	if (value == undefined)
		value = ""; 

	if (width){
		if (href_text_length){
			htext = value;
			if (value.length > href_text_length){
				htext = value.substring(0, href_text_length) + ".."; 
			}
			return "<td " + line_style + " width='" + width + "'><a title='"+value+"' href='"+value+"' target='_blank'>" + htext + "</a></td>"; 
		}else{
			return "<td " + line_style + " width='" + width + "'>" + value + "</td>"; 
		}
	}else{
		return "<td " + line_style + ">"+ value + "</td>";
	}
}

//remove genscript in href
function shortenUrl(url){
	if (url.indexOf('ttp:'))
		url = url.replace("http://www.genscript.com","");
	if (url.indexOf('ttps:'))
		url = url.replace("https://www.genscript.com","");
	return url;

}

function check_number(obj){
	if (!obj.value){
		return;
	}
	if (isNaN(obj.value)){
		alert ("Should be number!");
		obj.value = "";
		obj.focus();
		return;
	}
}

//float number after . 
function formatFloat(src, pos)
{
    return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);
}

function  setAll(obj, targetName)  
{
	var a = document.getElementsByName(targetName);  
	for (var i=0; i<a.length; i++)  a[i].checked  =  obj.checked; 
}

//remove checked rows and return to_del id list
function getDelIds(boxs){
	var a = document.getElementsByName(boxs);
	var to_del = ""; 
	var total = a.length;
	for (var i=(total-1); i>=0; i--){
		if (a[i].checked ){
			to_del += a[i].value+"-"; 
		}
	}
	return to_del;
}
