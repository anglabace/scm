
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Quotation Management</title>
<base id="myBaseId" href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}expland.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>

<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
	language="javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.form.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js"
	type="text/javascript"></script>
<script src="${global_js_url}scm/config.js" type="text/javascript"></script>
<script type="text/javascript" src="${global_js_url}scm/orgPicker.js"></script>
<script src="${global_js_url}/recordTime.js" type="text/javascript"></script>
<script src="${global_js_url}initlefttop.js" type="text/javascript"></script>
<script src="${global_js_url}jquery.funkyUI.js" type="text/javascript"></script>
<s:include value="quote_config.jsp"></s:include>
<script>
            (function ($) {

            
    $.format = (function () {

        var parseMonth = function (value) {

            switch (value) {
            case "Jan":
                return "01";
            case "Feb":
                return "02";
            case "Mar":
                return "03";
            case "Apr":
                return "04";
            case "May":
                return "05";
            case "Jun":
                return "06";
            case "Jul":
                return "07";
            case "Aug":
                return "08";
            case "Sep":
                return "09";
            case "Oct":
                return "10";
            case "Nov":
                return "11";
            case "Dec":
                return "12";
            default:
                return value;
            }
        };

        var parseTime = function (value) {
            var retValue = value;
            if (retValue.indexOf(".") !== -1) {
                retValue = retValue.substring(0, retValue.indexOf("."));
            }

            var values3 = retValue.split(":");

            if (values3.length === 3) {
                hour = values3[0];
                minute = values3[1];
                second = values3[2];

                return {
                        time: retValue,
                        hour: hour,
                        minute: minute,
                        second: second
                    };
            } else {
                return {
                    time: "",
                    hour: "",
                    minute: "",
                    second: ""
                };
            }
        };

        return {
            date: function (value, format) {
                //value = new java.util.Date()
                //2009-12-18 10:54:50.546
                try {
                    var year = null;
                    var month = null;
                    var dayOfMonth = null;
                    var time = null; //json, time, hour, minute, second
                    if (typeof value.getFullYear === "function") {
                        year = value.getFullYear();
                        month = value.getMonth() + 1;
                        dayOfMonth = value.getDate();
                        time = parseTime(value.toTimeString());
                    } else if (value.search(/\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.?\d{0,3}\+\d{2}:\d{2}/) != -1) { // 2009-04-19T16:11:05+02:00
                     var values = value.split(/[T\+-]/);

                     year = values[0];
                     month = values[1];
                     dayOfMonth = values[2];
                     time = parseTime(values[3].split(".")[0]);
                    } else {
                        var values = value.split(" ");

                        switch (values.length) {
                        case 6://Wed Jan 13 10:43:41 CET 2010
                            year = values[5];
                            month = parseMonth(values[1]);
                            dayOfMonth = values[2];
                            time = parseTime(values[3]);
                            break;
                        case 2://2009-12-18 10:54:50.546
                            var values2 = values[0].split("-");
                            year = values2[0];
                            month = values2[1];
                            dayOfMonth = values2[2];
                            time = parseTime(values[1]);
                            break;
                        default:
                            return value;
                        }
                    }

                    var pattern = "";
                    var retValue = "";
                    //Issue 1 - variable scope issue in format.date
//Thanks jakemonO
                    for (var i = 0; i < format.length; i++) {
                        var currentPattern = format.charAt(i);
                        pattern += currentPattern;
                        switch (pattern) {
                        case "dd":
                            retValue += dayOfMonth;
                            pattern = "";
                            break;
                        case "MM":
                            retValue += month;
                            pattern = "";
                            break;
                        case "yyyy":
                            retValue += year;
                            pattern = "";
                            break;
                        case "HH":
                            retValue += time.hour;
                            pattern = "";
                            break;
                        case "hh":
                            retValue += (time.hour === 0 ? 12 : time.hour < 13 ? time.hour : time.hour - 12);
                            pattern = "";
                            break;
                        case "mm":
                            retValue += time.minute;
                            pattern = "";
                            break;
                        case "ss":
                            retValue += time.second;
                            pattern = "";
                            break;
                        case "a":
                            retValue += time.hour > 12 ? "PM" : "AM";
                            pattern = "";
                            break;
                        case " ":
                            retValue += currentPattern;
                            pattern = "";
                            break;
                        case "/":
                            retValue += currentPattern;
                            pattern = "";
                            break;
                        case ":":
                            retValue += currentPattern;
                            pattern = "";
                            break;
                        default:
                            if (pattern.length === 2 && pattern.indexOf("y") !== 0) {
                                retValue += pattern.substring(0, 1);
                                pattern = pattern.substring(1, 2);
                            } else if ((pattern.length === 3 && pattern.indexOf("yyy") === -1)) {
                                pattern = "";
                            }
                        }
                    }
                    return retValue;
                } catch (e) {
                    return value;
                }
            }
        };
    }());
}(jQuery));
			var operation_method = "${operation_method}";
            var isSaved = false;
            window.onbeforeunload = function() {
                if(isSaved === false){
                    return 'Do you want to leave without saving data?';
                }
            }
            var prefShipMthd;
            $(document).ready(function(){
				if (operation_method != "edit") {
					$('input').attr("disabled",true);
					$('button').attr("disabled",true);
					$('select').attr("disabled",true);
				}
                
            	$('#promotion_edit_dialog').dialog({
            		autoOpen: false,
            		height: 510,
            		width: 800,
            		modal: true,
            		bgiframe: true,
            		buttons: {
            		}
            	});

            	
                prefShipMthd = $("#prefShipMthd").val();
                var quoteNo = '${quote.quoteNo}';
            	var quorderStatus = "${quote.status}";
                if (quoteNo == '') {//新增时以下操作不允许.
                    $("#actionListId option[value=REPEAT_NEW_QUOTE]").remove();
                    $("#actionListId option[value=cancel_quote]").remove();
                    $("#actionListId option[value=REOPEN]").remove();
                }
                if(quorderStatus == "CN" ){
                	$("#actionListId option[value=REPEAT_NEW_QUOTE]").remove();
                }

            	var isSalesManager = "${isSalesManager}";

            	
            	//if (isSalesManager != "true") {
            		//$("#actionListId option[value=cancel_quote]").remove();
                //} else {
					if (quorderStatus != "NW" && quorderStatus != "OP" && quorderStatus != "OH") {
						$("#actionListId option[value=cancel_quote]").remove();
					}
                //}
                if (quorderStatus != "CO" && quorderStatus != "CW" && quorderStatus != "VD"&&quorderStatus != "OH") {
                	$("#actionListId option[value=REOPEN]").remove();
                }
                if (quorderStatus == "CO" || quorderStatus == "CW" || quorderStatus == "VD") {
                    if (isSalesManager != "true") {
                    	$("#actionListId option[value=REOPEN]").remove();
                    }
                }
            });

            function del_quote(name){
                var noData = "quoteNos=${sessQuoteNo}";
                var status = $("#statusReason").val();
                var statusReason = $("#statusReason").find("option:selected").text();
                var comment = $("#comment").val();
                if(comment == ""){
                    alert("Please enter the Note.");
                    $("#comment").focus();
                    return;
                }
                $.ajax({
                    type: "POST",
                    url: "quote/quote!delete.action",
                    data: noData+'&status='+status+'&statusReason='+statusReason+'&comment='+comment,
                    success: function(msg){
                        if(msg == 'success'){
                            $('#cancelDialog').dialog('close');
                            alert('Cancel quote successfully');
                            window.location = "quote/quote_search.action";
                        }else if(msg == 'error'){
                            alert("You don't have permission to cancel quote, please contact your supervisor.");
                        }else {
                            alert('Unknown error');
                        }
                    },
                    error: function(msg){
                        alert("Failed to cancel the quotation.");
                    }
                });
                return false;
            }

            function changeOrderQuoteStatus(quorderStatus){
            	if( quorderStatus == "NW" || quorderStatus == "RV"|| quorderStatus == "OP" || quorderStatus == "BO" || quorderStatus == "PB" ){
            		orderquoteObj.editFlag = true;
            	}else{
            		orderquoteObj.editFlag = false;
            	}
            }
            changeOrderQuoteStatus("${quote.status}");

            var custNo = '${order.custNo}';
        </script>
<script src="${global_js_url}scm/gs.util.js" type="text/javascript"></script>
<script src="${global_js_url}quoteorder/order_quotation_dialog.js"
	type="text/javascript"></script>
<script src="${global_js_url}quoteorder/order_quotation_top.js"
	type="text/javascript"></script>
</head>
<body class="content"
	onload="recordTime();rtnPreLeftTop('Quotation Processing');">
	<input type="hidden" id="hiddenBusEmail" value="${busEmail}" />
	<input type="hidden" id="salesBusEmail"
		value="${quote.salesContactEmail}" />
	<div class="scm">
		<div class="title_content">
			<input type="hidden" id="quorderStatus" name="quorderStatus"
				value="${quote.status}" />
			<s:if test="quoteNo == 0">
				<div class="title">
					New Quotation &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${fullName} (Customer
					#${custNo}) <input type="hidden" value="${quoteNo}" name="quoteNo"
						id="quoteNo" />
				</div>
			</s:if>
			<s:else>
				<div class="title">
					Quote #${quoteNo} -
					<s:if test="quote.status == 'CO'">
						<font color="Red">Canceled</font>
					</s:if>
					&nbsp;&nbsp;&nbsp;${fullName} (Customer #
					<c:if test="${custNo > 0}">
						<a
							href="customer/customer!edit.action?custNo=${custNo}&operation_method=view"
							target="_blank">${custNo}</a>
					</c:if>
					<c:if test="${custNo <= 0}">${custNo}</c:if>
					) <input type="hidden" value="${quoteNo}" name="quoteNo"
						id="quoteNo" />
				</div>
			</s:else>
		</div>
		<input type="hidden" id="tmpAdd" name="tmpAdd" />
		<div class="input_box">
			<div class="content_box">
				<iframe name="itemListIframe" id="itemListIframe"
					src="quote_item!list.action?sessQuoteNo=${sessQuoteNo}&tempStatusStr=${tempStatus}"
					width="1000" style="overflow-y: hidden;" frameborder="0"
					scrolling="auto"></iframe>
			</div>
		</div>
	</div>
	<input type="hidden" name="fullName" id="fullName" value="${fullName}" />
	<input type="hidden" name="tmpStatus" id="tmpStatus"
		value="quote.status" />
	<div class="new_item" style="text-align: center">
		<s:if test="quote.status == 'CO'">
			<input type="button" name="AddItemTrigger" id="AddItemTrigger"
				value="Add New Item" class="search_input" disabled="disabled" />&nbsp;&nbsp;
                <input type="button" name="AddBatchItemTrigger"
				id="AddBatchItemTrigger" value="Add Batch Item" class="search_input"
				disabled="disabled" />&nbsp;&nbsp;
            </s:if>
		<s:else>
			<input type="button" name="AddItemTrigger" id="AddItemTrigger"
				value="Add New Item" class="search_input" />&nbsp;&nbsp;
                <input type="button" name="AddBatchItemTrigger"
				id="AddBatchItemTrigger" value="Add Batch Item" class="search_input" />&nbsp;&nbsp;
            </s:else>
		<input type="hidden" id="prefShipMthd" name="prefShipMthd"
			value="${prefShipMthd}" />
	</div>


	<form name="form1" id="form1">
		<saveButton:saveBtn parameter="${operation_method}"
			disabledBtn='<select name="menu1" id="actionListId" style="width: 250px;" disabled="disabled"><option selected="selected">Select Operation</option></select>'
			saveBtn='
	<select name="menu1" id="actionListId" style="width: 250px;">
		<option >Select Operation</option>
		<option value="GEN_FOLLOWUP_EMAIL" title="Generate Follow-up Emails" >Generate Follow-up Emails</option>
		<option value="PRINT_QUOTE">Create Quotation Document</option>
		<option value="PRINT_TXT">Print Quotation TXT</option>
		<option value="PRINT_DOCUMENT">Print Quotation DOCUMENT</option>
		<option value="SAVE_TMPL" title="Save My Template">Save as My Template</option>
		<option value="cancel_quote" title="Cancel Quote">Cancel Quote</option>
		<option value="REOPEN" title="Reopen Quote">Reopen Quote</option>
		<option value="REPEAT_NEW_QUOTE" title="Repeat as New Quote">Repeat as New Quote</option>
	</select>
	' />
	</form>
	<div id="dhtmlgoodies_tabView1">
		<div class="dhtmlgoodies_aTab">
			<iframe name="itemDetailIframe" id="itemDetailIframe" src=""
				height="320" width="950" style="" frameborder="0" scrolling="no"></iframe>
		</div>

		<div id="updateItemStatusDialog" style="display: none;">
			<s:include value="../quoteorder/quoteorder_item_update_status.jsp"></s:include>
		</div>
		<div id="printOrderQuDialog" title="Print Quote"></div>
		<div id="TxtOrderQuDialog" title="Quote Txt"></div>
		<div id="DocumentOrderQuDialog" title="Quote Txt"></div>
		<!-- poped sequence select dialog -->
		<div id="seqSelectDlg" title="Select" style="display: none;"></div>
		<div id="itemStatusHistoryDialog" title="Item Status History"
			style="display: none;"></div>
		<div id="allItemDetailsDialog" title="" style="display: none;"></div>
		<div id="itemMoreInfoDialog" title="Catalog Info"
			style="display: none;"></div>

		<div class="dhtmlgoodies_aTab">
			<iframe id="moreDetailIframe" width="100%" height="370"
				frameborder="0"></iframe>
		</div>

		<div class="dhtmlgoodies_aTab">
			<iframe id="addrIframe" width="970px" height="350px" frameborder="0"></iframe>
			<div id="changeAddressDialog" title="Select Address"></div>
			<div id="orgDialogWindow" title="Organization Lookup"></div>
		</div>
		<div class="dhtmlgoodies_aTab">
			<iframe id="multiAddrIframe"
				_src="quorder/quorderAddress/showMultiAddrAct?quorderNo=${quoteNo}&itemId=${item.quoteItemId}&codeType=quote"
				width="970px" height="350px" frameborder="0"></iframe>
			<div id="changeAddressDialog2" title="Select Address"></div>
		</div>
		<div class="dhtmlgoodies_aTab" id="salesIFrame">
			<iframe id="salesInformationIframe" name="salesInformationIframe"
				src="quote!getInformation.action?sessQuoteNo=${sessQuoteNo}&tempStatus=${tempStatus}"
				_src="quote!getInformation.action?sessQuoteNo=${sessQuoteNo}&tempStatus=${tempStatus}"
				width="100%" height="340" frameborder="0"></iframe>
		</div>

		<!-- Instruction/Notes pane begin  -->
		<div class="dhtmlgoodies_aTab" id="instructionTab">
			<iframe name="instructionIframe" id="instructionIframe"
				_src="quote_note!list.action?sessQuoteNo=${sessQuoteNo}&tempStatusStr=${tempStatus}&custNo=${quote.custNo}"
				height="300" width="984" scrolling="no" style="border: 0px"
				frameborder="0"></iframe>
		</div>

		<!-- Packing pane begin  -->
		<div class="dhtmlgoodies_aTab" id="packingTab">
			<iframe id="packagingIframe"
				_src="quote_package!list.action?custNo=${custNo}&sessQuoteNo=${quoteNo}&tempStatusStr=${tempStatus}"
				height="350" width="984" scrolling="no" style="border: 0px"
				frameborder="0"></iframe>
			<div id="packageModifyDialog" title=""></div>
		</div>
		<!-- poped confirm order dialog -->
		<div id="confirmStatusDialog" title="Confirm Quote"
			style="display: none;">
			<iframe id="confirmStatusIframe"
				src="quote!updateQuoteStatus.action?custNo=${custNo}&sessQuoteNo=${quoteNo}&tempStatusStr=${tempStatus}"
				height="250" width="620" scrolling="no" style="border: 0px"
				frameborder="0"></iframe>
		</div>
		<!-- Packing pane end  -->
		<div class="dhtmlgoodies_aTab">
			<iframe id="totalIframe" name="totalIframe"
				_src="quote/quoteTotal/showQuoteTotalAct?custNo=${custNo}&quoteNo=${quoteNo}&fullName=${fullName}"
				height="375" width="984" scrolling="no" style="border: 0px"
				frameborder="0"></iframe>
		</div>
		<script type="text/javascript">
    initTabs('dhtmlgoodies_tabView1',Array('Item Detail','More Detail','Addresses','Multi-Ship/Gift Msg','Sales Information','Instructions/Notes','Packaging','Quotation Total'),${defaultTab},998,375);
            </script>


		<div class="button_box">
			<form enctype="multipart/form-data" method="post" id="quoteSaveAll">
				<saveButton:saveBtn parameter="${operation_method}"
					disabledBtn='<input type="button" value="Save" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" disabled="disabled" />'
					saveBtn='<input type="button" value="Save" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" onclick="saveTimeOut();" />' />
				<s:if test='operation_method == "view"'>
					<input name="button2" type="button" value="Close"
						class="search_input"
						style="background-image: url(images/input_searchbg2.jpg); width: 120px;"
						onclick="window.close();" />
				</s:if>
				<s:else>
					<input name="button2" type="button" value="Cancel"
						class="search_input"
						style="background-image: url(images/input_searchbg2.jpg); width: 120px;"
						onclick="window.location.reload();" />
				</s:else>
				<s:if test="tempStatus == 'CO'">
					<input id="viewCustomerTrigger" type="button" value="View Customer"
						class="search_input"
						style="background-image: url(images/input_searchbg2.jpg); width: 120px;"
						custNo="${custNo}" disabled="disabled" />
					<input id="btn_convert2order" type="button"
						value="Convert to Order" class="search_input"
						style="background-image: url(images/input_searchbg2.jpg); width: 120px;"
						disabled="disabled" />

				</s:if>
				<s:else>
					<saveButton:saveBtn parameter="${operation_method}"
						disabledBtn='<input id="btn_convert2order" type="button" value="Convert to Order" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" disabled="disabled" />'
						saveBtn='<input id="btn_convert2order" type="button" value="Convert to Order" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" />' />
					<input id="viewCustomerTrigger" type="button" value="View Customer"
						class="search_input"
						style="background-image: url(images/input_searchbg2.jpg); width: 120px;"
						custNo="${custNo}" />
				</s:else>
			</form>
		</div>
	</div>
	<div id="itemLookupDialog" title="Item Lookup"></div>
	<div id="itemSelectDialog"></div>
	<div id="changeStatusDialog"></div>
	<div id="updateStatusDialog"></div>
	<div id="updateMainStatusDialog"></div>
	<div id="viewStatusHistoryDialog" title="QuoteStatus History"></div>
	<div id="viewSpePriceDialog" title="Special Price"></div>
	<div id="viewCustomerDialog" title="${fullName} (Customer #${custNo})">
	</div>
	<div id="searchEnzymeDialog" title="Search Enzyme"></div>
	<div id="orfCloneListDialog" title="Search Orf Clone"></div>
	<div id="itemAddBatch" title="Batch Quotation"></div>
	<div id="selectBatch" title="Select Batch Quotation"></div>

	<!-- poped instruction dialog -->
	<div id="instruction_dlg" title="Add Instruction/Note"></div>

	<!-- poped instruction display dialog -->
	<div id="instruction_update_dlg" title="Instruction/Note"></div>

	<!-- poped cancel quote dialog -->
	<div id="cancelDialog" title="Cancel Quote" style="display: none;">
		<s:include value="quote_update_status.jsp"></s:include>
	</div>
	<!-- poped update quote schedule delivery dialog -->
	<div id="updateQuoteScheduleDeliveryDialog"
		title="Update Quote Schedule Delivery"></div>

	<!-- poped cancel quote dialog -->
	<div id="reopenDialog" title="Reopen Quote" style="display: none;">
	</div>

	<!-- print quote dialog -->
	<div id="printDialog" title="Print Quote"></div>
	<div id="returnQuoteDialog" title="Return Quote"></div>
	<div id="createCardsAccountDialog" title="Cards in Account"></div>

	<!-- saveQuoteTmplDialog -->
	<div id="saveTmplDialog" title="Save as My Template"
		style="overflow: no; overflow-x: hidden; overflow-y: hidden;"></div>

	<input id="itemMaxNo" name="itemMaxNo" type="hidden"
		value="${itemMaxNo}" />
	<input type="hidden" name="selectCheckBoxValue"
		id="selectCheckBoxValue" value="" />
	<input type="hidden" name="totalAmount" id="totalAmount" value="0" />
	<input type="hidden" name="totalDiscount" id="totalDiscount" value="0" />
	<input type="hidden" name="totalTax" id="totalTax" value="0" />
	<input type="hidden" name="shipAmt" id="shipAmt" value="${shipAmt}" />
	<input type="hidden" id="now_page" value="${now_page}" />
	<input type="hidden" id="projectSupport" value="${projectSupport}" />
	<input type="hidden" id="mail_type" />
	<input type="hidden" id="only_email" />
	<input type="hidden" id="email_edit_type" />
	<input type="hidden" id="email_all_data" />
	<input type="hidden" id="email_temp_id" />
	<div id="batchCloning" title="Batch Quotation"></div>
	<div id="viewQuoteRefDialog" title="View Quote"></div>
	<div id="viewOrderRefDialog" title="View Order"></div>
	<input type="hidden" id="confirmStatus" value="${confirmStatus}" />
	<input type="hidden" id="confirmStatusText"
		value="${confirmStatusText}" />
	<div id="promotion_edit_dialog" title="View promotion"></div>
</body>
<script language="javascript" type="text/javascript">
//add by zhanghuibin
var batchCloningId = "";
        $('#AddBatchItemTrigger').click( function() {
        	var tmpFlag = updateMoreInfo();
			if(tmpFlag == false){
				var moreDetailIndex = getTabIndexBy("More Detail");
		       	if(moreDetailIndex != -1){
		       		showTab('dhtmlgoodies_tabView1', moreDetailIndex);
		       	}
		        unBlock();
				return;
			}
			$('#selectBatch').dialog( 'open' ) ;
			$( '#selectBatch' ).attr( 'innerHTML' , '<iframe  src="quote_more!showBatchType.action" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="no" ></iframe>' ) ;
		}) ;

    $( '#itemAddBatch' ).dialog({
			autoOpen: false ,
			height: 600 ,
			width: 820 ,
			modal: true ,
			//Div top display
			bgiframe: true
	}) ;

     $( '#selectBatch' ).dialog({
			autoOpen: false ,
			height: 200 ,
			width: 500 ,
			modal: true ,
			//Div top display
			bgiframe: true
	}) ;

    function showAddBatch(){
        $('#selectBatch').dialog( 'close') ;
        $('#itemAddBatch').dialog( 'open' ) ;
        $( '#itemAddBatch' ).attr( 'innerHTML' , '<iframe  src="quote_more!showBachItem.action?custNo='+orderquoteObj.custNo+'&sessQuoteNo='+orderquoteObj.sessNoValue+'" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="Yes" ></iframe>' ) ;
    }

  $( '#batchCloning').dialog({
			autoOpen: false ,
			height: 465 ,
			width: 820 ,
			modal: true ,
			//Div top display
            close : function(event, ui){
                 if (batchCloningId == "") {
                     $("#orderBatch").contents().find("#cloningFlag").attr("checked", true);
                 }
            },
			bgiframe: true
	}) ;
 function showBatchCloning(sessionNo, itemIds){
     $('#batchCloning').dialog( 'open' ) ;
     $( '#batchCloning' ).attr( 'innerHTML' , '<iframe  src="quote_more!showBatchCloningStrategy.action?sessQuoteNo='+sessionNo+'&itemIds='+itemIds + '" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="Yes" ></iframe>' ) ;

 }
</script>
</html>