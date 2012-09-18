/**
 * 
 * 
 */
var xmlHttp = false;
xmlHttp = CreateHTTPPoster();

function CreateHTTPPoster() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	}
	try {
		return new ActiveXObject('MSXML2.XMLHTTP.4.0');
	} catch (e) {
		try {
			return new ActiveXObject('MSXML2.XMLHTTP.3.0');
		} catch (e) {
			try {
				return new ActiveXObject('MSXML2.XMLHTTP.2.6');
			} catch (e) {
				try {
					return new ActiveXObject('MSXML2.XMLHTTP');
				} catch (e) {
					try {
						return new ActiveXObject('Microsoft.XMLHTTP');
					} catch (e) {
						return null;
					}
				}
			}
		}
	}
}
var randomNumber = Math.round(Math.random() * 10000);

function newPrivilege() {
	if (!formCheck()) {
		return;
	}

	parentCode = tree.getSelectedItemId();

	if (parentCode == "privilege") {
		parentCode = "0000";
	}

	privilegeTypeValue = privilegeTypeObj.options[privilegeTypeObj.selectedIndex].value;

	xmlHttp.open("GET",global_url
							+ "privilege!recordOperation.action?operationType=ADD&privilegeCode="
							+ privilegeCodeObj.value + "&privilegeName="
							+ encodeURIComponent(privilegeNameObj.value)
							+ "&privilegeAction="
							+ encodeURIComponent(privilegeActionObj.value)
							+ "&privilegeType=" + privilegeTypeValue
							+ "&privilegeAttr="
							+ encodeURIComponent(privilegeAttrObj.value)
							+ "&parentCode=" + parentCode + "&temp="
							+ randomNumber, true);
	xmlHttp.onreadystatechange = retOperationPrivilege;
	xmlHttp.send(null);
}

function modPrivilege() {
	var currentItemId = tree.getSelectedItemId();
	var privilegeId = tree.getUserData(currentItemId, "privilegeId");

	xmlHttp
			.open(
					"GET",
					global_url
							+ "privilege!recordOperation.action?operationType=EDIT&privilegeId="
							+ privilegeId + "&privilegeName="
							+ encodeURIComponent(privilegeNameObj.value)
							+ "&privilegeAttr="
							+ encodeURIComponent(privilegeAttrObj.value)
							+ "&privilegeAction="
							+ encodeURIComponent(privilegeActionObj.value)
							+ "&temp=" + randomNumber, true);
	xmlHttp.onreadystatechange = retOperationPrivilege;
	xmlHttp.send(null);
}

function delPrivilege() {
	var currentItemId = tree.getSelectedItemId();
	childItemStr = tree.getAllSubItems(currentItemId);
	var privilegeId = tree.getUserData(currentItemId, "privilegeId");
	if (childItemStr == "") {
		if (confirm('Are you sure want delete this item?')) {
			xmlHttp
					.open(
							"GET",
							global_url
									+ "privilege!recordOperation.action?operationType=DEL&privilegeId="
									+ privilegeId + "&temp=" + randomNumber,
							true);
			xmlHttp.onreadystatechange = retOperationPrivilege;
			xmlHttp.send(null);
		}
	} else {
		alert("System error! Please contact system administrator for help.");
	}
}

function retOperationPrivilege() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			var privilegeOperationXmlDoc = xmlHttp.responseXML;
			if (privilegeOperationXmlDoc!=null&&typeof (privilegeOperationXmlDoc.documentElement
					.getElementsByTagName("operation_info")) == "object") {
				parentCode = tree.getSelectedItemId();
				var privilegeOperationDataObj = privilegeOperationXmlDoc.documentElement
						.getElementsByTagName("operation_info");
				if(privilegeOperationDataObj!=null) {
					operationTag = privilegeOperationDataObj[0].childNodes[0].childNodes[0].nodeValue;
					operationRet = privilegeOperationDataObj[0].childNodes[1].childNodes[0].nodeValue;
				} else {
					operationTag = null;
					operationRet = null;
				}
				
				if (operationRet == 1) {
					if (operationTag == "ADD") {
						if (privilegeTypeValue == "MENU") {//UI operation picture
							img0 = "folderClosed";
							img1 = "folderOpen";
							img2 = "folderClosed";
						} else// UI operation picture
						{
							img0 = "book_titel";
							img1 = "book";
							img2 = "book_titel";
						}
						imgSuffix = ".gif";

						img0 = img0 + imgSuffix;
						img1 = img1 + imgSuffix;
						img2 = img2 + imgSuffix;
						tree.insertNewItem(tree.getSelectedItemId(),
								privilegeCodeObj.value, privilegeNameObj.value
										+ "(" + privilegeCodeObj.value + ")",
								0, img0, img1, img2, 'SELECT');
						tree.setUserData(privilegeCodeObj.value, "parentCode",
								parentCode);
						tree.setUserData(privilegeCodeObj.value,
								"privilegeType", privilegeTypeValue);
					} else if (operationTag == "EDIT") {
						tree.setItemText(privilegeCodeObj.value,
								privilegeNameObj.value + "("
										+ privilegeCodeObj.value + ")");
						tree.setUserData(privilegeCodeObj.value,
								"privilegeAttr", privilegeAttrObj.value);
						replaySetup();
					} else if (operationTag == "DEL") {
						tree.deleteItem(tree.getSelectedItemId(), true);
					}
				} else {
					alert("Opertaion fail");
				}
			}
		}
	}
}

/**
 * to sava privilege that user modify
 *
 */
function savePrivilege() {
	xmlHttp.open("GET", global_url
			+ "privilege/privilege!savePrivilegeList.action", true);
	xmlHttp.onreadystatechange = retSavePrivilege;
	xmlHttp.send(null);
}

function retSavePrivilege() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			var privilegeOperationXmlDoc = xmlHttp.responseXML;
			if (privilegeOperationXmlDoc!=null&&typeof (privilegeOperationXmlDoc.documentElement
					.getElementsByTagName("result")) == "object") {
				parentCode = tree.getSelectedItemId();
				var privilegeOperationDataObj = privilegeOperationXmlDoc.documentElement
						.getElementsByTagName("result");
				if(privilegeOperationDataObj!=null) {
					operationTag = privilegeOperationDataObj[0].childNodes[0].nodeValue;
				} else {
					operationTag = null;
				}
				
				if (operationTag) {
					alert("The privilege is saved.");
				} else {
					alert("System error! Please contact system administrator for help.");
				}
			}
		}
	}
}

function cancelPrivilege() {
	xmlHttp.open("GET", global_url
			+ "privilege/privilege!cancelPrivilegeAct.action", true);
	xmlHttp.onreadystatechange = retCancelPrivilege;
	xmlHttp.send(null);
}

function retCancelPrivilege() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			retText = xmlHttp.responseText;
			var privilegeOperationXmlDoc = xmlHttp.responseXML;
			if (privilegeOperationXmlDoc!=null&&typeof (privilegeOperationXmlDoc.documentElement
					.getElementsByTagName("result")) == "object") {
				parentCode = tree.getSelectedItemId();
				var privilegeOperationDataObj = privilegeOperationXmlDoc.documentElement
						.getElementsByTagName("result");
				if(privilegeOperationDataObj!=null) {
					operationTag = privilegeOperationDataObj[0].childNodes[0].nodeValue;
				} else {
					operationTag = null;
				}
				
				if (operationTag) {
					alert("The privilege is removed.");
				} else {
					alert("System error! Please contact system administrator for help.");
				}
			}
		}
	}
}

function formCheck() {
	var msg = "";

	privilegeNameObj = document.getElementById("privilegeName");
	privilegeCodeObj = document.getElementById("privilegeCode");
	privilegeTypeObj = document.getElementById("privilegeType");

	if (privilegeNameObj.value == "") {
		msg += "Please input privilege Name\r\n";
	}
	if (privilegeCodeObj.value == "") {
		msg += "Please input privilege Code\r\n";
	}
	if (privilegeTypeObj.options[privilegeTypeObj.selectedIndex].value == "") {
		msg += "Please choose privilege type\r\n";
	}
	if (tree.getItemText(privilegeCodeObj.value) != "") {
		msg += "Privilege Code duplicate \r\n";
	}
	//Can't select Menu privilege under UI privilege 
	if (privilegeTypeObj.options[privilegeTypeObj.selectedIndex].value == "MENU"
			& tree.getUserData(tree.getSelectedItemId(), "privilegeType") == "UI") {
		msg += "Can't select Menu privilege under UI privilege \r\n";
	}

	if (msg != "") {
		alert(msg);
		return false;
	} else {
		return true;
	}
}