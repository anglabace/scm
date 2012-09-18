
 /*弹出customer list界面*/
    function clickCustomer() {
        $('#new_customer_dlg').dialog("option", "open", function() {
            var htmlStr = '<iframe id="iframe1" src="genscript!customerlist.action" height=430" width="560" scrolling="no" style="border:0px" frameborder="0"></iframe>';
            $('#new_customer_dlg').html(htmlStr);
        });
        $('#new_customer_dlg').dialog('open');
    }
 $(function() {
     $('#new_customer_dlg').dialog({
         autoOpen: false,
         height: 480,
         width: 700,
         modal: true,
         bgiframe: true,
         buttons: {
         }
     });

     $('#orgAddDialogTrigger').click(function() {
         dataHolderWin.jQuery.data(dataHolderWin.document.body, 'disableNew', 1);
         dataHolderWin.$('#orgDialogWindow').dialog('open');
         dataHolderWin.jQuery.data(dataHolderWin.document.body, 'orgLoc', self);
         dataHolderWin.jQuery.data(dataHolderWin.document.body, 'orgIdStr', 'orgId');
         dataHolderWin.jQuery.data(dataHolderWin.document.body, 'orgNameStr', 'orgName');
     });

     $('#editTrigger').click(function() {
         $("#orgName").attr("readonly", false);
         alert("Now  you can enter an organization name!");
     });

     $("#orgName").blur(function() {
         var orgname = $("#orgName").val();
         if (orgname != "") {
             $.ajax({
                 type: "POST",
                 url : "basedata/org_picker!checkOrg.action?orgName="
                         + orgname,
                 dataType : "json",
                 success : function(data, textStatus) {
                     if (data.message != 'ok') {
                         alert(data.message);
                         $("#orgName").val("");
                         $("#orgName").focus();
                         return false;
                     }
                 }
             });

         }
     });
 });

 // Quotation or Order 表现页面
function showType(){
    var type = $("input:checked[name='reportType']").val();
    if(type == "quotation"){
        $("#quotationShow").css("display", "");
        $("#orderShow1").css("display", "none");
        $("#orderShow2").css("display", "none");
        $("#reportDataDto\\.type").attr("value", "quotation");
    }else if(type=="order"){
        $("#quotationShow").css("display", "none");
        $("#orderShow1").css("display", "");
        $("#orderShow2").css("display", "");
        $("#reportDataDto\\.type").attr("value", "order");
    }
    $("input[name^='reportDataDto\\.status']").each(function(){
        $(this).attr("checked",false)
    });
}

 //时间验证，不允许太大范围的区间
 function validData(){
     var flag = 0;
     $("input[name='reportDataDto\\.column']").each(function() {
         if ($(this).attr("checked")) {
             flag++;
         }
     });
     if (flag < 1) {
         alert("Please select Output Parameters !");
         return false;
     }
     if($("#reportDataDto\\.periodType").length <= 0) return true;
     //验证fromDate ToDate
     var dateRange = $("#reportDataDto\\.dataRange").val();
     var dataFrom = $("#reportDataDto\\.dataFrom").val();
     var dataTo = $("#reportDataDto\\.dataTo").val();
     var perType = $("#reportDataDto\\.periodType").val();
     if(dateRange == "" && perType == "day"){
         alert("Date Range is 'all' not allowed Period Type is 'Days'!");
         return false;
     }else if(dateRange == "" && perType == "week"){
         alert("Date Range is 'all' not allowed Period Type is 'Weeks'!");
         return false;
     }else if(dateRange == "" && perType == "month"){
         alert("Date Range is 'all' not allowed Period Type is 'Months'!");
         return false;
     }else if(dateRange == "quarter" && perType == "day"){
         alert("Date Range is 'Quarter' not allowed Period Type is 'Days'!");
         return false;
     }else if(dateRange == "year" && perType == "day"){
         alert("Date Range is 'Year' not allowed Period Type is 'Days'!");
         return false;
     }else if(dateRange == "lQuarter" && perType == "day"){
         alert("Date Range is 'Last Quarter' not allowed Period Type is 'Days'!");
         return false;
     }else if(dateRange == "lYear" && perType == "day"){
         alert("Date Range is 'Last Year' not allowed Period Type is 'Days'!");
         return false;
     }else if(dateRange == "custom"){
         var fromYear = dataFrom.split("-")[0];
         var fromMonth = dataFrom.split("-")[1];
         var toYear = dataTo.split("-")[0];
         var toMonth = dataTo.split("-")[1];
         if((parseInt(toMonth) - parseInt(fromMonth) >= 3) && perType == "day"){
             alert("Date Range not allowed Period Type is 'Days'!");
             return false;
         }else if(parseInt(toYear) - parseInt(fromYear) >= 1 && perType == "day"){
             alert("Date Range not allowed Period Type is 'Days'!");
             return false;
         }
     }
     return true;
 }

 function showDateRange() {
     if ($(this).val() == "custom") {
         $("#dateFromTD").css("display", "");
         $("#dateToTD").css("display", "");
     } else {
         $("#dateFromTD").css("display", "none");
         $("#dateToTD").css("display", "none");
     }
 }

 function getWorkGroupsByWorkCenter() {
     $.ajax({
         type: "POST",
         url : "report/report!getWorkGroupsByAjax.action?work_center_id=" + $("#workCenterId").val(),
         dataType : "json",
         success : function(data, textStatus) {
             document.getElementById("workGroupId").options.length   =   0;
             for(var i in data){
                 var workGroup = data[i];
                 $("#workGroupId").append("<option value='" + workGroup.id + "'>" + workGroup.name + "</option>");
             }
         }
     });
 }

 //Includes step items
 function stepChange(obj){
     if(obj.checked){
         obj.value = "Y";
     }else{
         obj.value = "N";
     }
 }