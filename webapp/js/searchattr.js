var A_ID = 0;
var A_NAME = 1;
var A_TYPE = 2;
var A_FATHER = 3;
var A_OPTIONS = 4;
var A_SON = 5;

function SearchAttribute(searchAttr, tableId, objectName) {
    this.searchAttr = searchAttr;
    this.tableId = tableId;
    this.objectName = objectName;
    this.onAJAX = 0;

// need test table id and first tr id

    this.element = function(name, id, type) {
        obj_id = this.tableId + "_" + name + "_" + id;
        if (type == 1)
            return(obj_id);
        else
            return(document.getElementById(obj_id));
    }

    this.getLastId = function() {
        var tr_id = $("#" + this.tableId + ">tbody>tr:last").attr("id");
        if (!tr_id) {
            tr_id = this.element("tr", 0, 1);
        }
        trIdArr = tr_id.split("_");
        return id = trIdArr[2];
    }

    this.add = function() {


        var id = this.getLastId();
        id++;

        var tr_id = this.element("tr", id, 1);
        var attr_id = this.element("attr", id, 1);
        var oper_id = this.element("oper", id, 1);
        var valueA_id = this.element("valueA", id, 1);
        var valueB_id = this.element("valueB", id, 1);

        var option_str = '';
        for (var i = 0; i < this.searchAttr.length; i++) {
            option_str += "<option title='" + this.searchAttr[i][A_TYPE] + "' value='" + this.searchAttr[i][A_ID] + "'>" + this.searchAttr[i][A_NAME] + "</option>";
        }

        str = "<tr id = '" + tr_id + "'>";
        str += "<td><select id='" + attr_id + "' name='attr'";
        str += "onchange='" + this.objectName + ".selectAttr(" + id + ")'>";
        str += "<option value=''>Select attribute</option>" + option_str + "</select></td>";
        str += "<td><select id='" + oper_id + "' style='display:none' name='opr'";
        str += "onchange='" + this.objectName + ".selectOper(" + id + ")'></select></td>";
        str += "<td><input id='" + valueA_id + "' style='display:none'  name='valueA'></td>";
        str += "<td><input id='" + valueB_id + "' style='display:none'  name='valueB'></td>";
        str += "<td><input type='button' value='Remove Criteria' "
        str += "onclick ='" + this.objectName + ".delAttr(" + id + ")'></td>";
        str += "</tr>";

        $('#' + this.tableId).append(str);
        return id;
    };

    this.addmy = function() {


        var id = this.getLastId();
        id++;


        var tr_id = this.element("tr", id, 1);
        var attr_id = this.element("attr", id, 1);
        var oper_id = this.element("oper", id, 1);
        var valueA_id = this.element("valueA", id, 1);
        var valueB_id = this.element("valueB", id, 1);

        var option_str = '';
        for (var i = 0; i < this.searchAttr.length; i++) {
            option_str += "<option title='" + this.searchAttr[i][A_TYPE] + "' value='" + this.searchAttr[i][A_ID] + "'>" + this.searchAttr[i][A_NAME] + "</option>";
        }

        str = "<tr id = '" + tr_id + "'>";
        str += "<td><select id='" + attr_id + "' name='attr'";
        str += "onchange='" + this.objectName + ".selectAttr(" + id + ")'>";
        str += "<option value=''>Select attribute</option>" + option_str + "</select></td>";
        str += "<td><select id='" + oper_id + "' style='display:none' name='opr'";
        str += "onchange='" + this.objectName + ".selectOper(" + id + ")'></select></td>";
        str += "<td><input id='" + valueA_id + "' style='display:none'  name='valueA'></td>";
        str += "<td><input id='" + valueB_id + "' style='display:none'  name='valueB'></td>";
        str += "<td><input type='button' value='Remove Criteria' "
        str += "onclick ='" + this.objectName + ".delmyAttr(" + id + ")'></td>";
        str += "</tr>";

        $('#' + this.tableId).append(str);
        return id;
    };

    this.setSpecialAttr = function(spAttr) {

        for (var i = 0; i < this.searchAttr.length; i++) {
            for (var j = 0; j < spAttr.length; j++) {

                if (this.searchAttr[i][A_ID] == spAttr[j][0]) {
                    this.searchAttr[i][A_TYPE] = spAttr[j][1];
                    this.searchAttr[i][A_OPTIONS] = spAttr[j][2];
                }
            }
        }
    }

    this.setDependingAttr = function(select1, select2, action) {
        for (var i = 0; i < this.searchAttr.length; i++) {
            if (this.searchAttr[i][A_ID] == select2) {
                this.searchAttr[i][A_TYPE] = "SUBSELECT";
                this.searchAttr[i][A_FATHER] = select1;
                this.searchAttr[i][A_OPTIONS] = action;
            }
        }
        for (var i = 0; i < this.searchAttr.length; i++) {
            if (this.searchAttr[i][A_ID] == select1) {
                this.searchAttr[i][A_SON] = select2;
            }
        }
    }

    this.depId = function(depType) {
        var trs = document.getElementById(this.tableId).getElementsByTagName("tr");
        for (var i = 1; trs[i]; i++) {
            if (trs[i].id) {
                trIdArr = trs[i].id.split("_");
                var id = trIdArr[2];
                var attr = this.element("attr", id)

                if (attr.value == depType) {
                    return id;
                }
            }
        }
        return 0;
    }

    this.selectOper = function(id) {
        var inputA = this.element("valueA", id);
        var operator = this.element("oper", id);
        //	var inputA=this.element("valueA",id);
        var inputB = this.element("valueB", id);

        inputA.style.display = 'block';

        if (operator.value == "between")
            inputB.style.display = 'block';
        else
            inputB.style.display = 'none';

    }

    this.changeToText = function(input) {
        var td = input.parentNode;
        var name = input.name;
        var id = input.id;
        td.removeChild(input);

        var newInput = document.createElement("INPUT");
        newInput.id = id;
        newInput.name = name;

        td.appendChild(newInput);

        return newInput;
    }

    this.changeToSelect = function(input, arr) {
        var td = input.parentNode;
        var name = input.name;
        var id = input.id;
        var val = input.value;
        td.removeChild(input);

        var newInput = document.createElement("SELECT");
        newInput.id = id;
        newInput.name = name;

        td.appendChild(newInput);

        opt_arr = new Array(arr.length);
        for (var i = 0; i < arr.length; i++) {
            opt_arr[i] = document.createElement("OPTION");
            opt_arr[i].text = arr[i][A_NAME];
            opt_arr[i].value = arr[i][A_ID];
            opt_arr[i].count = arr[i][A_TYPE];
            if ((!val && opt_arr[i].value == 'US') || ((val && opt_arr[i].value == val )))
                opt_arr[i].selected = true;
            newInput.options[newInput.options.length] = opt_arr[i];
        }
        return newInput;
    }

    this.setDepOnChange = function(fID, sID) {
        var object = this;
        this.element("valueA", fID).onchange = function() {
            object.selectAttr(sID)
        };
    }

    this.selectAttr = function(id) {

        var attr = this.element("attr", id);
        var inputA = this.element("valueA", id);

        var type = attr.options[attr.selectedIndex].title.toUpperCase();
        var oper_arr;

        inputA = this.changeToText(inputA);

        for (var i = 0; i < this.searchAttr.length; i++) {
            if (this.searchAttr[i][A_ID] == attr.value) {
                var currentAttr = this.searchAttr[i];
            }
        }

        switch (type) {
            case "TEXT":
                oper_arr = new Array("contain", "=");
                break;
            case "DATE":
                oper_arr = new Array("=", "between");
                break;
            case "DATETIME":
                oper_arr = new Array("=", "between");
                break;
            case "NUMERIC":
                oper_arr = new Array("=", ">", "<", ">=", "<=", "between");
                break;
            case "STRING":
                oper_arr = new Array("=", "end to", "start with", "contain");
                break;
            case "SELECT":
                this.changeToSelect(inputA, currentAttr[A_OPTIONS]);
                oper_arr = new Array("=");
                break;
            case "SUBSELECT":
                var depId = this.depId(currentAttr[A_FATHER]);
                var valueA = this.element("valueA", depId);
                var vA = "";
                if (valueA != undefined) {
                	vA = $.trim(valueA.value);
                }
               // alert("vA==" + vA);
                if (depId) {
                    this.setDepOnChange(depId, id);
                    if (valueA[valueA.selectedIndex].count > 0) {
                        var action = currentAttr[A_OPTIONS] + vA;
                    //    alert(action);
                        inputA.style.display = "none";
                        var object = this;
                        this.onAjax = 1;
                        $.ajax({
                            type: "GET",
                            url: action,
                            dataType: "json",
                            success: function(result) {
                                var options = new Array();
                                for (var i = 0; i < result.length; i++) {
                                    options[i] = new Array(result[i].stateCode, result[i].name);
                                }
                                if (options.length > 0)
                                    object.changeToSelect(inputA, options);
                                object.onAjax = 0;
                            },
                            error: function(result) {
                                alert(result.responseText);
                                object.onAjax = 0;
                            }
                        });
                    }

                }
                else {
                    this.delAttr(id, 0);
                    id = this.add();
                    this.element("attr", id).value = currentAttr[A_FATHER];
                    this.selectAttr(id);
                    this.add();
                    this.setDepOnChange(id, id + 1);
                    this.element("attr", id + 1).value = currentAttr[A_ID];
                    this.selectAttr(id + 1);
                }

                oper_arr = new Array("=");
                break;
            default:
                oper_arr = new Array("=");
                break;
        }

        var oper = this.element("oper", id);
        while (oper.options.length > 0) {
            $("#" + oper.id).empty();
        }
        var opt_arr = new Array(oper_arr.length);
        for (var i = 0; i < oper_arr.length; i++) {
            opt_arr[i] = document.createElement("OPTION");
            opt_arr[i].text = oper_arr[i];
            opt_arr[i].value = oper_arr[i];
            oper.options[oper.options.length] = opt_arr[i];
        }
        oper.style.display = 'block';


        this.selectOper(id);

    }

    this.delAttr = function(id, type) {
        var tmpArr = [];
        $("#advSearchTable tr").each(function(i, n) {
            tmpArr.push($(n).val());
        });
        if (type == 0) {
            $('#' + this.element("tr", id, 1)).remove();
        } else if (tmpArr.length > 2) {
            $('#' + this.element("tr", id, 1)).remove();
        }
    }

    this.delmyAttr = function(id, type) {
        var tmpArr = [];
        $("#mySearchTable1 tr").each(function(i, n) {
            tmpArr.push($(n).val());
        });
        if (type == 0) {
            $('#' + this.element("tr", id, 1)).remove();
        } else if (tmpArr.length > 2) {
            $('#' + this.element("tr", id, 1)).remove();
        }
    }

    this.clean = function(type) {
        type = typeof(type) == 'undefined' ? 0 : 1;
        for (var i = this.getLastId(); i > 0; i--) {
            this.delAttr(i, type);
            this.delmyAttr(i, type);
        }
    };


}

function MySearch(searchAttr, selectId) {
    this.searchAttr = searchAttr;	// the
    this.selectId = selectId;	// id of the container of select element;
    this.my_search_list;
    this.mySrchMaxCount;
    this.selectedIndex = 0;

    this.getMySearch = function(getMySearchUrl) {
        var object = this;
        this.onAjax = 1;
        $.ajax({
            type: "POST",
            url: getMySearchUrl,
            dataType: "json",
            success: function(result) {
                select = document.getElementById(object.selectId);
                $("#" + select.id).empty();

                if (!result.mySearchList) {
                    object.my_search_list = [];
                } else {
                    object.my_search_list = result.mySearchList;
                }
                object.mySrchMaxCount = result.mySrchMaxCount;

                opt_arr = new Array();
                for (var i = 0; i < object.my_search_list.length; i++) {
                    opt_arr[i] = document.createElement("OPTION");
                    opt_arr[i].text = object.my_search_list[i].name;
                    opt_arr[i].value = i;
                    select.options[select.options.length] = opt_arr[i];
                }
                select.selectedIndex = object.selectedIndex;
                //alert(select.value);
                object.showMySearch(select.value);
                object.onAjax = 0;
            },
            error: function(result) {
                alert(result.responseText);
                object.onAjax = 0;
            }
        });
    }

    this.showMySearch = function(key) {

        this.searchAttr.clean();
        if (this.my_search_list[0] != undefined) {
            var my_search = this.my_search_list[key].attrList;
            for (var i = 0; i < my_search.length; i++) {
                var id = this.searchAttr.addmy();

                this.searchAttr.element("attr", id).value = my_search[i].id;
                this.searchAttr.selectAttr(id);
                this.searchAttr.element("oper", id).value = my_search[i].operator;

                this.searchAttr.selectOper(id);
                this.searchAttr.element("valueA", id).value = $.trim(my_search[i].value1);
                this.searchAttr.element("valueB", id).value = $.trim(my_search[i].value2);
            }
        }
    }

    this.hasMysearch = function() {
        var mySrchNameId = arguments[0];	// my search name for
        //var findFlag = 0;
        if (this.my_search_list != undefined) {
            for (var i = 0; i < this.my_search_list.length; i++) {
                if ($.trim($('#' + mySrchNameId).attr('value')) == this.my_search_list[i].name) {
                    return true;
                }
            }
        }
        return false;
    }

    this.checkMysearchMaxNum = function() {
        if (this.my_search_list.length + 1 > this.mySrchMaxCount) {
            return false;
        }
        return true;
    }
}

function getOperatorString(opChar) {
    var opStr = 'EQ';
    opChar = opChar.toString().toUpperCase();
    if (opChar == '=') {
        opStr = 'EQ';
    }
    else if (opChar == '>') {
        opStr = 'GT';
    }
    else if (opChar == '<') {
        opStr = 'LT';
    }
    else if (opChar == 'LIKE') {
        opStr = 'LIKE';
    }
    else if (opChar == 'BETWEEN') {
        opStr = 'BETWEEN';
    }
    else if (opChar == '>=') {
        opStr = 'GE';
    }
    else if (opChar == '<=') {
        opStr = 'LE';
    }
    else if (opChar == '%LIKE' || opChar == 'START WITH') {
        opStr = 'PLIKE';
    }
    else if (opChar == 'LIKE%' || opChar == 'END TO') {
        opStr = 'SLIKE';
    }
    else if (opChar == '%LIKE%' || opChar == 'CONTAIN') {
        opStr = 'LIKE';
    }
    return opStr;
}

function getPropertyType(data_type) {
    var type = 'S';
    if (data_type == 'VARCHAR') {
        type = 'S';
    }
    else if (data_type == 'INT') {
        type = 'I';
    }
    else if (data_type == 'DATETIME' || data_type == 'DATE') {
        type = 'D';
    }
    else if (data_type == 'NUMERIC') {
        type = 'I';
    }
    else if (data_type == 'DOUBLE') {
        type = "N";
    }
    return type;
}

var attrs = new Array();

//Customer field map
attrs[1] = 'custNo';
attrs[2] = 'custRoleId';
attrs[3] = 'firstName';
attrs[4] = 'lastName';
attrs[6] = 'state';
attrs[7] = 'country';
attrs[8] = 'organizationName';
attrs[9] = 'territoryCode';
attrs[10] = 'groupCode';
attrs[11] = 'status';
attrs[76] = 'orderNo';
attrs[94] = 'altNo';

attrs[12] = "contactNo";
attrs[13] = "firstName";
attrs[14] = "lastName";
attrs[15] = "state";
attrs[16] = 'country';
attrs[17] = 'organizationName';
attrs[18] = 'territoryCode';
attrs[19] = 'groupCode';
attrs[20] = 'status';
attrs[95] = 'altNo';
//product&services
attrs[70] = 'catalogNo';
attrs[71] = 'name';
attrs[72] = 'description';
attrs[73] = 'catalogNo';
attrs[74] = 'name';
attrs[75] = 'description';
attrs[87] = 'type';
attrs[88] = 'status';
attrs[89] = 'type';
attrs[90] = 'status';
//orderSearch
attrs[21] = 'orderNo';
attrs[22] = 'status';
attrs[23] = 'custNo';
attrs[24] = 'orderType';
attrs[25] = 'orderDate';
attrs[26] = 'salesContact';
attrs[40] = 'poNumber';
attrs[41] = 'amount';
attrs[42] = 'customerConfirmDate';
attrs[43] = 'vendorConfirmDate';
attrs[44] = 'priority';
attrs[45] = 'email';
attrs[46] = 'firstName';
attrs[47] = 'lastName';
attrs[48] = 'organization';
attrs[49] = 'country';
attrs[50] = 'state';
attrs[51] = 'techSupport';
attrs[84] = 'companyName';
//quoteSearch
attrs[52] = 'quoteNo';
attrs[53] = 'status';
attrs[54] = 'custNo';
attrs[55] = 'quoteType';
attrs[56] = 'quoteDate';
attrs[57] = 'salesContact';
attrs[59] = 'amount';
attrs[62] = 'priority';
attrs[63] = 'email';
attrs[64] = 'firstName';
attrs[65] = 'lastName';
attrs[66] = 'organization';
attrs[67] = 'country';
attrs[68] = 'state';
attrs[69] = 'techSupport';
//purchase order search
attrs[78] = 'orderNo';
attrs[79] = 'status';
attrs[80] = 'vendorNo';
attrs[81] = 'orderDate';
attrs[82] = 'subTotal';
attrs[83] = 'purchaseContact';
function searchAct(selectObj) {
    var searchTag = selectObj.attr('id');
    var formObj = $('#' + searchTag + "Form");
    var currentId = "";

    var selectAttrStr = "";
    var selectOperStr = "";
    var selectValueAStr = "";
    var selectValueBStr = "";

    if (searchTag == "advancedSearch") {
        selectAttrStr = "advSearchTable_attr";
        selectOperStr = "advSearchTable_oper";
        selectValueAStr = "advSearchTable_valueA";
        selectValueBStr = "advSearchTable_valueB";
    }
    else {
        selectAttrStr = "mySearchTable1_attr";
        selectOperStr = "mySearchTable1_oper";
        selectValueAStr = "mySearchTable1_valueA";
        selectValueBStr = "mySearchTable1_valueB";
    }

    var fieldType = "";
    var formAction = "";


    var selectAttrVal = "";
    var selectOperVal = "";
    var selectValueAVal = "";
    var selectValueBVal = "";

    var actionUrl = "";
    $(":select [id*='" + selectAttrStr + "']").each(
            function() {
                currentId = $(this).attr('id').replace(selectAttrStr + "_", "");
                fieldType = $(this).find("option:selected").attr("title");
                //&filter_EQI_custNo=401&filter_LIKES_firstName=&filter_LIKES_lastName=
                selectAttrVal = attrs[$(this).val()];
                selectOperVal = $("#" + selectOperStr + "_" + currentId).val();
                selectValueAVal = $("#" + selectValueAStr + "_" + currentId).val();
                selectValueAVal=$.trim(selectValueAVal);
                selectValueBVal = $("#" + selectValueBStr + "_" + currentId).val();
                selectValueBVal=$.trim(selectValueBVal);

                if (selectValueAVal == "") {
                    return true;
                }
                else if (selectOperVal == "between" & selectValueBVal == "") {
                    return true;
                }

                selectOperVal = getOperatorString(selectOperVal);
                fieldType = getPropertyType(fieldType);
                if (selectOperVal == 'EQ' && fieldType == 'D') {
                    selectOperVal = "BETWEEN";
                    var aValue = selectValueAVal + " 00:00:00";
                    var bValue = selectValueAVal + " 23:59:59";
                    actionUrl += "&filter_" + "GE" + fieldType + "_" + selectAttrVal + "=" + aValue.replace(/%20/g, " ") + "&filter_" + "LE" + fieldType + "_" + selectAttrVal + "=" + bValue.replace(/%20/g, " ");
                }
                if (selectOperVal == "BETWEEN") {
                    if (selectAttrVal == "poNumber") {
                        actionUrl += "&searcher_" + selectOperVal + fieldType + "_" + selectAttrVal + "=" + "" + selectValueAVal;
                    } else if (selectAttrVal == "orderNo" && $("#searchType").val() != "OrderSearch" && $("#searchType").val() != "PurchaseOrderSearch") {
                        actionUrl += "&searcher_" + "GE" + fieldType + "_" + selectAttrVal + "=" + selectValueAVal + "&searcher_" + "LE" + fieldType + "_" + selectAttrVal + "=" + selectValueBVal;
                    } else {
                        actionUrl += "&filter_" + "GE" + fieldType + "_" + selectAttrVal + "=" + selectValueAVal + "&filter_" + "LE" + fieldType + "_" + selectAttrVal + "=" + selectValueBVal;
                    }
                }
                else {
                    if (selectAttrVal == "poNumber") {
                        actionUrl += "&searcher_" + selectOperVal + fieldType + "_" + selectAttrVal + "=" + "" + selectValueAVal;
                    } else if (selectAttrVal == "orderNo" && $("#searchType").val() != "OrderSearch" && $("#searchType").val() != "PurchaseOrderSearch") {
                        actionUrl += "&searcher_" + selectOperVal + fieldType + "_" + selectAttrVal + "=" + "" + selectValueAVal;
                    } else {
                        actionUrl += "&filter_" + selectOperVal + fieldType + "_" + selectAttrVal + "=" + "" + selectValueAVal;
                    }
                }
            }
            );
    if (actionUrl != "") {
        formAction = formObj.attr('_action');
        if (formAction.indexOf('?') > 0) {
            formObj.attr('action', formAction + actionUrl);
        }
        else {
            formObj.attr('action', formAction + "?" + actionUrl);
        }
//		alert( formAction + "?" + actionUrl ) ;
//		return ;
        formObj.submit();
    }

    return;
}

function downloadOligo(selectObj) {
    var searchTag = selectObj.attr('id');
    var formObj = $('#mySearchForm');
    var currentId = "";

    var selectAttrStr = "";
    var selectOperStr = "";
    var selectValueAStr = "";
    var selectValueBStr = "";

    if (searchTag == "advancedSearch") {
        selectAttrStr = "advSearchTable_attr";
        selectOperStr = "advSearchTable_oper";
        selectValueAStr = "advSearchTable_valueA";
        selectValueBStr = "advSearchTable_valueB";
    }
    else {
        selectAttrStr = "mySearchTable1_attr";
        selectOperStr = "mySearchTable1_oper";
        selectValueAStr = "mySearchTable1_valueA";
        selectValueBStr = "mySearchTable1_valueB";
    }

    var fieldType = "";
    var formAction = "";


    var selectAttrVal = "";
    var selectOperVal = "";
    var selectValueAVal = "";
    var selectValueBVal = "";

    var actionUrl = "";
    $(":select [id*='" + selectAttrStr + "']").each(
            function() {
                currentId = $(this).attr('id').replace(selectAttrStr + "_", "");
                fieldType = $(this).find("option:selected").attr("title");
                //&filter_EQI_custNo=401&filter_LIKES_firstName=&filter_LIKES_lastName=
                selectAttrVal = attrs[$(this).val()];
                selectOperVal = $("#" + selectOperStr + "_" + currentId).val();
                selectValueAVal = $("#" + selectValueAStr + "_" + currentId).val();
                selectValueAVal=$.trim(selectValueAVal);
                selectValueBVal = $("#" + selectValueBStr + "_" + currentId).val();
                selectValueBVal=$.trim(selectValueBVal);

                if (selectValueAVal == "") {
                    return true;
                }
                else if (selectOperVal == "between" & selectValueBVal == "") {
                    return true;
                }

                selectOperVal = getOperatorString(selectOperVal);
                fieldType = getPropertyType(fieldType);
                if (selectOperVal == 'EQ' && fieldType == 'D') {
                    selectOperVal = "BETWEEN";
                    var aValue = selectValueAVal + " 00:00:00";
                    var bValue = selectValueAVal + " 23:59:59";
                    actionUrl += "&filter_" + "GE" + fieldType + "_" + selectAttrVal + "=" + aValue.replace(/%20/g, " ") + "&filter_" + "LE" + fieldType + "_" + selectAttrVal + "=" + bValue.replace(/%20/g, " ");
                }
                if (selectOperVal == "BETWEEN") {
                    if (selectAttrVal == "poNumber") {
                        actionUrl += "&searcher_" + selectOperVal + fieldType + "_" + selectAttrVal + "=" + "" + selectValueAVal;
                    } else if (selectAttrVal == "orderNo" && $("#searchType").val() != "OrderSearch" && $("#searchType").val() != "PurchaseOrderSearch") {
                        actionUrl += "&searcher_" + "GE" + fieldType + "_" + selectAttrVal + "=" + selectValueAVal + "&searcher_" + "LE" + fieldType + "_" + selectAttrVal + "=" + selectValueBVal;
                    } else {
                        actionUrl += "&filter_" + "GE" + fieldType + "_" + selectAttrVal + "=" + selectValueAVal + "&filter_" + "LE" + fieldType + "_" + selectAttrVal + "=" + selectValueBVal;
                    }
                }
                else {
                    if (selectAttrVal == "poNumber") {
                        actionUrl += "&searcher_" + selectOperVal + fieldType + "_" + selectAttrVal + "=" + "" + selectValueAVal;
                    } else if (selectAttrVal == "orderNo" && $("#searchType").val() != "OrderSearch" && $("#searchType").val() != "PurchaseOrderSearch") {
                        actionUrl += "&searcher_" + selectOperVal + fieldType + "_" + selectAttrVal + "=" + "" + selectValueAVal;
                    } else {
                        actionUrl += "&filter_" + selectOperVal + fieldType + "_" + selectAttrVal + "=" + "" + selectValueAVal;
                    }
                }
            }
            );
    if (actionUrl != "") {
        formAction = formObj.attr('_action');
        if (formAction.indexOf('?') > 0) {
            formObj.attr('action', formAction + actionUrl);
        }
        else {
            formObj.attr('action', formAction + "?" + actionUrl);
        }
        window.open("order_extra!downLoadOligoInfo.action?" + actionUrl);
    }
    return;
}

$(document).ready(function() {
    $('#advancedSearch').click(
            function() {
           //alert("sssssssssss");
                searchAct($(this));
            }
    );
    $('#mySearch').click(
            function() {
                searchAct($(this));
            }
    );
    $('#downloadOligo').click(
            function() {
            	downloadOligo($(this));
            }
    );
});
