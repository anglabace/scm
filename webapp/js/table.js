function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	$("#mainForm").submit();
}

function jumpListPage(pageNo){
	$("#pageNo").val(pageNo);
	$("#listForm").submit();
}

function jumpPageOfCategory(pageOfCategoryNo) {
	$("#pageOfCategoryNo").val(pageOfCategoryNo);
	$("#mainForm").submit();
}

function jumpPageBean(pageBeanNo) {
	$("#pageBeanNo").val(pageBeanNo);
	$("#mainForm").submit();
}

function sort(orderBy, defaultOrder) {
	if ($("#orderBy").val() == orderBy) {
		if ($("#order").val() == "") {
			$("#order").val(defaultOrder);
		}
		else if ($("#order").val() == "desc") {
			$("#order").val("asc");
		}
		else if ($("#order").val() == "asc") {
			$("#order").val("desc");
		}
	}
	else {
		$("#orderBy").val(orderBy);
		$("#order").val(defaultOrder);
	}

	$("#mainForm").submit();
}

function search() {
	$("#order").val("");
	$("#orderBy").val("");
	$("#pageNo").val("1");

	$("#mainForm").submit();
}