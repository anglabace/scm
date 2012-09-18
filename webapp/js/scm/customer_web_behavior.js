/**
 * This file contain several functions for customer web behavior page
 */

function showWebBehavior(custNo){
	var href = 'customer/customer/viewCustMoreInfoPersonalAct?custNo='+custNo;
	//href += '&allData='+allData;
	//href += '&defaultFlags='+defaultFlags;
	parent.$('#showWBTrigger').val(href);
	parent.$('#showWBTrigger').click();
}

function searchClickStream(){   
	//alert ($('clickFrom').value);
	//alert ("search!" + $('clickFrom').attr('value'));   
	//alert ("search!" + $('clickFrom').val());
	//alert ("search!" + $('clickFrom').text());d
	     
	//var dateFrom = document.getElementById("clickFrom").value;
	//var dateTo = document.getElementById("clickTo").value;
	/*
	var getdate = /(\d+)\-(\d+)\-(\d+)/;
	var month, day, year;
	getdate.test(document.getElementById("clickFrom").value);
	if (! RegExp.$1 || ! RegExp.$2 || ! RegExp.$3){
		alert ("Please input correct from date."); 
		return;
	}else{
		year = RegExp.$1;
		month = RegExp.$2;
		day = RegExp.$3;

		if (month.length == 1)
			month = "0" + month;
		if (day.length == 1)
			day = "0" + day;
		dateFrom = year.toString() + "-" + month.toString() + "-" + day.toString();
	}
	getdate.test(document.getElementById("clickTo").value);
	//alert (RegExp.$1 + '^' + RegExp.$2 + '^' + RegExp.$3);
	if (! RegExp.$1 || ! RegExp.$2 || ! RegExp.$3){
		alert ("Please input correct to date."); 
		return;
	}else{
		year = RegExp.$1;
		month = RegExp.$2;
		day = RegExp.$3;

		if (month.length == 1)
			month = "0" + month;
		if (day.length == 1)
			day = "0" + day;
		dateTo = year.toString() + "-" + month.toString() + "-" + day.toString();
	}
	*/
	//alert (dateFrom + " " + dateTo);
	//getClickStream(1,dateFrom, dateTo);
}

function searchAnalyse(){
	
	var dateFrom = $('#analyseFrom').val();
	var dateTo = $('#analyseTo').val();;
	var period = $('#period_1').val();

	var search_type = document.getElementsByName("r_analyse_time_type");
	if (search_type[1].checked){
		var temp = $('#last_period_type').val();
		var beginendArr = temp.split(";");
		dateFrom = beginendArr[0];
		dateTo = beginendArr[1];
	}
	
	$("#beginDate").attr("value", dateFrom);
	$("#endDate").attr("value", dateTo);
	$("#period").attr("value", period);
	/*
	document.getElementById('beginDate').value = dateFrom;
	document.getElementById('endDate').value = dateTo;
	document.getElementById('period').value = period;
	//document.getElementById('period_name').innerHTML = period_name;
	*/
	getAnalyseReport(1,dateFrom, dateTo, period); 
}

//analyse result paging, useless after 05-10
function searchAnalysePager(page_no){
	var dateFrom = document.getElementById('beginDate').value;
	var dateTo = document.getElementById('endDate').value;
	var period = document.getElementById('period').value;
	getAnalyseReport(page_no,dateFrom, dateTo, period); 
}

function getAnalyseReport(page_no, dateFrom, dateTo, period) { 
	var cust_no = $('#custNo').attr('value');
	var search = "yes";
	if (! page_no)
		page_no = 1;

	var reqUrl = "customer_web_behavior!getAnalysisReport.action?custNo=" + cust_no+"&page_no="+page_no;
	if (dateFrom && dateTo){
		reqUrl += "&beginDate=" + dateFrom + "&endDate=" + dateTo;
	}else{
		alert ("Error! Start or end date not defined!"); 
		return;
	}
	if (dateFrom > dateTo){
		alert ("Error! End date smaller than start date!"); 
		return; 
	}
	if (period){
		reqUrl += "&period=" + period;
	}else{
		alert ("Error! Period not defined!"); 
	}
	//alert (reqUrl);

	$('#analyse_indicator').html("<div><img src='scm/../images/indicator.gif' /></div>");
	$('#analyse_result').html('');
	$.ajax({
		type: "POST",
		url: reqUrl,
		success: function(data, textStatus){
			//error ...
			//alert (data);
			if (data.indexOf('Fatal error') > 0){
				alert ("Fatal error.");
				$('#analyse_indicator').html(""); 
				return;
			}
     		var msg = eval('(' + data + ')');

			img_html = "<img src='../images/temp/" + msg.dataFileName + ".png' />";
			$('#report_img').html(img_html);
     		
			var i = -1;
			for ( i in msg.analysisReportList ) {
				var content = "<tr>";

				content += getTD(i,msg.analysisReportList[i].fromDate + ' ~ ' + msg.analysisReportList[i].toDate, '25%');
				content += getTD(i,msg.analysisReportList[i].visit, '10%');
				content += getTD(i,msg.analysisReportList[i].refered, '10%');
				content += getTD(i,msg.analysisReportList[i].searching, '15%');
				content += getTD(i,msg.analysisReportList[i].pageView, '20%');
				content += getTD(i,msg.analysisReportList[i].avgViewTime, '20%');
				content += "</tr>";
				$('#analyse_result').append(content);
			} 
			$('#analyse_indicator').html(""); 
			if (i == -1){
				$('#analyse_result').html('No data found.'); 
			}
			//$('#analyse_pager').html(msg.pager);
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
			$('#analyse_result').html("");
		}
	});
}
function getStat(){
	var cust_no = $('#cust_no').attr('value');
	//get upper result
	var reqUrl = "customer/customer_web_behavior!viewAccessStaticAct.action?cust_no=" + cust_no;
	$.ajax({
		type: "POST",
		url: reqUrl,
		success: function(data, textStatus){
     		var msg = eval('(' + data + ')');
			if (msg.message.visitTotal != undefined)
				document.getElementById('visitTotal').value = msg.message.visitTotal;
			if (msg.message.firstVisit!= undefined)
				document.getElementById('firstVisit').value = msg.message.firstVisit;
			if (msg.message.lastVisit != undefined)
				document.getElementById('lastVisit').value = msg.message.lastVisit;
			if (msg.message.visitPagesTotal != undefined)
				document.getElementById('visitPagesTotal').value = msg.message.visitPagesTotal;
			if (msg.message.avgStaySecd != undefined)
				document.getElementById('avgStaySecd').value = msg.message.avgStaySecd;
			//var msg = JSON.parse(data);
			/*
			$('#visitTotal').attr('value',msg.visitTotal;
			$('#firstVisit').attr('value',msg.firstVisit);
			$('#lastVisit').attr('value',msg.lastVisit);
			$('#visitPagesTotal').attr('value',msg.visitPagesTotal);
			$('#avgStaySecd').attr('value', msg.avgStaySecd);
			*/
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


