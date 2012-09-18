function checkSeq(checkObj){
	var checkSeq = checkObj.value;
	if (checkSeq == ''){
		return false;
	}else{
		checkSeq = checkSeq.replace(/\W/g,"");
		checkSeq = checkSeq.replace(/_/g,"");
		checkSeq = checkSeq.replace(/\d/g,"");
	}
	checkObj.value = checkSeq.toUpperCase();
	checkSeq = checkObj.value;
	var REG = /[BD-FH-RU-Z]/i;
	var needError = REG.test(checkSeq);
	if (needError == true){
		alert ('Your sequence contains sequence out of A, G, C, T !');
		checkObj.focus();
	}
}

//add by zhanghuibin
function vectorChange(){
    if($("#vectorName").val()=="Other"){
        $("#vectorOther").show();
    }else{
        $("#vectorOther").hide();
    }
}