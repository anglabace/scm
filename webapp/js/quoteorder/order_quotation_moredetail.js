var tooltip=function(){
	var id = 'tt';
	var top = -130;
	var left = 3;
	var maxw = 300;
	var speed = 10;
	var timer = 20;
	var endalpha = 95;
	var alpha = 0;
	var tt,t,c,b,h;
	var ie = document.all ? true : false;
	return{
		show:function(v,w){
			if(tt == null){
				tt = document.createElement('div');
				tt.setAttribute('id',id);
				t = document.createElement('div');
				t.setAttribute('id',id + 'top');
				c = document.createElement('div');
				c.setAttribute('id',id + 'cont');
				
				b = document.createElement('div');
				b.setAttribute('id',id + 'bot');
				tt.appendChild(t);
				tt.appendChild(c);
				tt.appendChild(b);
				document.body.appendChild(tt);
				tt.style.opacity = 0;
				tt.style.filter = 'alpha(opacity=0)';
				document.onmousemove = this.pos;
			}
			tt.style.display = 'block';
			tt.style.position = 'absolute';
			c.innerHTML = v;
			tt.style.fontSize = '12px';
			tt.style.color = '#12097F';
			tt.style.fontWeight = 'bold';
			tt.style.backgroundColor = '#fff';
			tt.style.width = w ? w + 'px' : '200px';
			tt.style.border='1px solid #93969B';
			tt.style.padding='5px';
			if(!w && ie){
				t.style.display = 'none';
				b.style.display = 'none';
				tt.style.width = tt.offsetWidth;
				t.style.display = 'block';
				b.style.display = 'block';
			}
			if(tt.offsetWidth > maxw){tt.style.width = maxw + 'px';}
			h = parseInt(tt.offsetHeight) + top;
			clearInterval(tt.timer);
			tt.timer = setInterval(function(){tooltip.fade(1);},timer);
		},
		pos:function(e){
			var u = ie ? event.clientY + document.documentElement.scrollTop : e.pageY;
			var l = ie ? event.clientX + document.documentElement.scrollLeft : e.pageX;					
			tt.style.top = (u - h ) + 'px';
			tt.style.left = (l + left - 220) + 'px';			
		},		
		fade:function(d){
			var a = alpha;
			if((a != endalpha && d == 1) || (a != 0 && d == -1)){
				var i = speed;
				if(endalpha - a < speed && d == 1){
					i = endalpha - a;
				}else if(alpha < speed && d == -1){
					i = a;
				}
				alpha = a + (i * d);
				tt.style.opacity = alpha * .01;
				tt.style.filter = 'alpha(opacity=' + alpha + ')';
			}else{
				clearInterval(tt.timer);
				if(d == -1){tt.style.display = 'none';}
			}
		},
		hide:function(){
			clearInterval(tt.timer);
			tt.timer = setInterval(function(){tooltip.fade(-1);},timer);
		}
	};
}();

$.fn.enable_changed_form = function ( ) {
	 var _f = this;       
	 $(':text, :password, textarea', this).each(function() {         
	        $(this).attr('_value', $(this).val());         
	    });       
	       
	    $(':checkbox, :radio', this).each(function() {         
	        var _v = this.checked ? 'on' : 'off';         
	        $(this).attr('_value', _v);         
	    });       
	       
	    $('select', this).each(function() {
	    	if(this.options.length > 0 && this.selectedIndex != -1) {
	    		$(this).attr('_value', this.options[this.selectedIndex].value);       
	    	}
	    });  
};

$.fn.refresh_changed_form = function() {
    //modify by zhanghuibin 此函数不再使用
		/*var _f = this;
		 $(':text, :password, textarea', _f).each(function() {         
		        $(this).attr('_value', $(this).val());   
		    });       
		    $(':checkbox, :radio', _f).each(function() {         
		        var _v = this.checked ? 'on' : 'off';         
		        $(this).attr('_value', _v);         
		    });       
		       
		    $('select', _f).each(function() {
		    	if(this.options.length > 0 && this.selectedIndex != -1)
		        $(this).attr('_value', this.options[this.selectedIndex].value);         
		    });*/
};

$.fn.is_form_changed = function() {
	   var changed = false;
	   /* var f = this;
	   $(':text, :password, textarea', f).each(function() {
	       var _v = $(this).attr('_value');         
	       if(typeof(_v) == 'undefined')   _v = '';         
	       if(_v != $(this).val()) changed = true;         
	   });         
	      
	   $(':checkbox, :radio', f).each(function() {         
	       var _v = this.checked ? 'on' : 'off';       
	       if(_v != $(this).attr('_value')) changed = true;         
	   });         
	       
	   $('select', f).each(function() {         
	       var _v = $(this).attr('_value');         
	       if(typeof(_v) == 'undefined')   _v = '';
	       if(this.options.length > 0 && this.selectedIndex != -1 
	    		   && _v != this.options[this.selectedIndex].value) {
	    	   changed = true;       
	       }
	   });*/
   //判断不可用的不去比较
    if (this.formSerialize() != $(this.selector + "_initData").val()) {
        changed = true;
    }
    /*alert(changed)
    alert(this.selector + " -----1: " + this.formSerialize())
    alert(this.selector + " -----2: " + $(this.selector + "_initData").val())*/
    return changed;
};

$.fn.is_element_changed = function() {
	   var type = $(this).attr("type");
	   var changed = false;
	   if(type == "radio" || type == "checkbox"){
		   var _v = $(this).attr("checked") ? 'on' : 'off';
		   if(_v != $(this).attr('_value')) changed = true;
	   }else if(type == "text" || type == "password" || type == "textarea"){
		   var _v = $(this).attr('_value');         
	       if(typeof(_v) == 'undefined')   _v = '';      
	       if(_v != $(this).val()) changed = true;
	   }else if(type == "select"){
	       var _v = $(this).attr('_value');         
	       if(typeof(_v) == 'undefined')_v = '';         
	       if(this.options.length > 0 && _v != this.options[this.selectedIndex].value) 
	    	   changed = true;
	       if(this.options.length == 0 && _v != "")
	    	   changed = true;
	   }
	   return changed;         
};

String.prototype.replaceM = function(s){
	var re = /peptide\.modification=(?:.)*?\&/g;
	var rstr = "peptide.modification="+s+"&";
	return this.replace(re,rstr);
};

function openc(str1, str2){
    if (document.getElementById(str1).style.display=="none"){
 	    document.getElementById(str2).src="images/ad.gif";
 	    document.getElementById(str1).style.display="block";
 	  } else {
		document.getElementById(str2).src="images/ar.gif";
 	    document.getElementById(str1).style.display="none";
 	  }
}

function addFormNameToSerializeData (formName, str, excludedStr) {
	if(!formName){
		return str;
	}
	var tmpArr = str.split("&");
	var rtArr = [];
	var rtArr2 = [];
	var tmpStr = '';
	for(var i=0; i<tmpArr.length; i++){
		tmpStr = tmpArr[i];
		rtArr2 = tmpStr.split("=");
		if(excludedStr.indexOf(rtArr2[0]) != -1){
			continue;
		}
		rtArr.push(formName+"."+tmpArr[i]);
	}
	return rtArr.join("&");
}

var itemIds = "";

function getServiceName(tmpShow){
	var serviceName = "";
	if(tmpShow == "gene"){
		serviceName = "geneSynthesis";
	}else if(tmpShow == "rna"){
		serviceName = "rna";
	}else if(tmpShow == "plasmid"){
		serviceName = "plasmidPreparation";
	}else if(tmpShow == "muta"){
		serviceName = "mutagenesis";
	}else if(tmpShow == "cloning"){
		serviceName = "custCloning";
	}else if(tmpShow == "orfClone"){
		serviceName = "orfClone";
	} else if (tmpShow == "mutaLib") {
		serviceName = "mutaLib";
	}
	return serviceName;
}

function getRefType(tmpShow, self){
	var refType = "";
	var type = orderquoteObj.type;
	if(tmpShow == 'plasmid'){
		if(type == "order"){
			refType = "OIM_PLASMID";
		}else{
			refType = "QIM_PLASMID";
		}
	}else if(tmpShow == 'muta'){
		if(type == "order"){
			refType = "OIM_MUTA";
		}else{
			refType = "QIM_MUTA";
		}
	}else if(tmpShow == "cloning"){
		if(type == "order"){
			refType = "OIM_CUSTCLONING";
		}else{
			refType = "QIM_CUSTCLONING";
		}
	}else if(tmpShow == "libraryPeptide"){
		if(type == "order"){
			refType = "OIM_LIBRARY_PEPTIDE";
		}else{
			refType = "QIM_LIBRARY_PEPTIDE";
		}
	}else if(tmpShow == "arrayPeptide"){
		if(type == "order"){
			refType = "OIM_ARRAY_PEPTIDE";
		}else{
			refType = "QIM_ARRAY_PEPTIDE";
		}
	} else if (tmpShow == "orfClone"){
		if(type == "order"){
			refType = "OIM_ORFCLONE";
		}else{
			refType = "QIM_ORFCLONE";
		}
	}else if(tmpShow == "batchOligo"){
		if(type == "order"){
			refType = "OIM_OLIGO";
		}else{
			refType = "QIM_OLIGO";
		}
	} else if(tmpShow == "mutaLib") {
		if(type == "order"){
			refType = "OIM_MUTALIB";
		}else{
			refType = "QIM_MUTALIB";
		}
	}
	if(self == 1){
		refType += "_SELF";
	}else if(self == 2){
		refType += "_CS";
	}
	return refType;
}

/*
* 注意，如果修改这里的保存逻辑，请同步修改order_more.jsp的initData方法的逻辑，这两处的逻辑要求相同
* */
function saveMoreDetail(show){
	if(globalValidate(show) == false){
		return false;
	}
	var rtFlag = true;
	if(show == "gene"){
		var rtFlag = saveGeneSynthesis();
        if(!rtFlag) return false;
		var cloningFlag = $("#geneSynthesisForm").find("[name='cloningFlag'][checked]").val();
		var plasmidPrepFlag = $("#geneSynthesisForm").find("[name='plasmidPrepFlag'][checked]").val();
		var rtFlag1 = true;
		var rtFlag2 = true;
		if(cloningFlag == "Y"){
			rtFlag1 = saveCloning(cloningFlag);
		}
		if(rtFlag1 && (plasmidPrepFlag == "Y")){
			rtFlag2 = savePlasmid(plasmidPrepFlag);
		}
		if(rtFlag1 == false || rtFlag2 == false){
			rtFlag = false;
		}
	}else if(show == "rna"){
		var rtFlag = saveRna();
		var plasmidPrepFlag = $("#rnaForm").find("[name='plasmidPrepFlag'][checked]").val();
		var rtFlag1 = true;
		if(plasmidPrepFlag == "Y"){
			rtFlag1 = savePlasmid(plasmidPrepFlag);
		}
		if(rtFlag1 == false){
			rtFlag = false;
		}
//	} else if (show == "orfClone") {
//		var rtFlag = saveOrfClone();
//		var cloningFlag = $("#orfCloneForm").find("[name='cloningFlag'][checked]").val();
//		var plasmidPrepFlag = $("#orfCloneForm").find("[name='plasmidPrepFlag'][checked]").val();
//		var rtFlag1 = true;
//		var rtFlag2 = true;
//		if(cloningFlag == "Y"){
//			rtFlag1 = saveCloning(cloningFlag);
//		}
//		if(plasmidPrepFlag == "Y"){
//			rtFlag2 = savePlasmid(plasmidPrepFlag);
//		}
//		if(rtFlag1 == false || rtFlag2 == false){
//			rtFlag = false;
//		}
	} else if (show == "oligo") {
		var rtFlag = saveOligo();
	} else if (show == "customService") {
		var rtFlag = saveCustomService();
	}else if(show == "cloning"){
		var rtFlag = saveCloning("Y");
		var plasmidPrepFlag = $("#targetForm").find("[name='plasmidPrepFlag'][checked]").val();
		var rtFlag1 = true;
		if(plasmidPrepFlag == "Y"){
			rtFlag1 = savePlasmid(plasmidPrepFlag);
		}
		if(rtFlag1 == false){
			rtFlag = false;
		}
	}else if(show == "plasmid"){
		var rtFlag = savePlasmid("Y");
	}else if(show == "muta"){
		var rtFlag = saveMuta("Y");
		if (rtFlag) {
			var cloningFlag = $("#targetForm").find("[name='cloningFlag'][checked]").val();
			var plasmidPrepFlag = $("#targetForm").find("[name='plasmidPrepFlag'][checked]").val();
			var rtFlag1 = true;
			var rtFlag2 = true;
			if(cloningFlag == "Y"){
				rtFlag1 = saveCloning(cloningFlag);
			}
			if(plasmidPrepFlag == "Y"){
				rtFlag2 = savePlasmid(plasmidPrepFlag);
			}
			if(rtFlag1 == false || rtFlag2 == false){
				rtFlag = false;
			}
		}
	} else if(show == "mutaLib"){
		var rtFlag = saveMutationLibraries();
		if (rtFlag) {
			var tgtVectorName = $("#mutaLibForm").find("[name='tgtVectorName'][checked]").val();
			var cloningFlag = "N";
			if (tgtVectorName == "Other") {
				cloningFlag = "Y";
			}
			var plasmidPrepFlag = $("#mutaLibForm").find("[name='plasmidPrepFlag'][checked]").val();
			var rtFlag1 = true;
			var rtFlag2 = true;
			if(cloningFlag == "Y"){
				rtFlag1 = saveCloning(cloningFlag);
			}
			if(plasmidPrepFlag == "Y"){
				rtFlag2 = savePlasmid(plasmidPrepFlag);
			}
			if(rtFlag1 == false || rtFlag2 == false){
				rtFlag = false;
			}
		}
	} else if(show == "peptide"){
		var tmpFlag = checkOtherPeptide();
		if(tmpFlag != true){
			var rtFlag = saveStdPeptide("Y");
		}
	}else if(show == "antibody"){
		rtFlag = saveAntibody();
	}else if(show == "pro"){
		rtFlag = saveTopPkgService();
	}
	if(rtFlag == false){
		$("#validateFlag").val("0");
		//切换到More Detail tab中去。
	    /*var parentTmpIndex = parent.window.getTabIndexBy("More Detail");
       	if(parentTmpIndex != -1){
       		parent.window.showTab('dhtmlgoodies_tabView1', parentTmpIndex);
       	}*/
	}
	return rtFlag;
}

function globalValidate(show){
	$("#validateFlag").val("1");
	var tmpFlag = true;
	if(show == "gene"){
		tmpFlag = checkGeneSynthesis();
	}else if(show == "cloning"){
//		
	}else if(show == "plasmid"){
//		tmpFlag = checkPlasmid();
	} else if (show == "oligo") {
		tmpFlag = checkOligo();
	}
	if(tmpFlag){
		$("#validateFlag").val("1");
	}else{
		$("#validateFlag").val("0");
	}
	if($("#validateFlag").val() == "1"){
		return true;
	} else {
		return false;
	}
}

//校验customer Service
function checkCustomerService () {
	if($("#customServiceForm").valid()==false) {
		return false;
	}
	return true;
}

function checkPlasmid() {
	if($("#plasmidForm").valid()==false) {
		return false;
	}

	if($("#plasmidForm").find("[id='resistance']").val()=="Other"&&$("#plasmidForm").find("[id='txt_01']").val()!="") {
		$("#plasmidForm").find("[id='antibioticResistance']").attr("value",$("#plasmidForm").find("[id='txt_01']").val());
	}
	if($("#plasmidForm").find("[id='resistance']").val()!="Other") {
		$("#plasmidForm").find("[id='antibioticResistance']").attr("value",$("#plasmidForm").find("[id='resistance']").val());
	}
	
	if($("#plasmidForm").find("[id='prepWeightSel']").val()=="0"&&$("#plasmidForm").find("[id='txt_04']").val()!="") {
		$("#plasmidForm").find("[id='prepWeightStr']").attr("value",$("#plasmidForm").find("[id='txt_04']").val()+" "+$("#plasmidForm").find("[id='prepUom']").html());
	}
	if($("#plasmidForm").find("[id='prepWeightSel']").val()!="0") {
		$("#plasmidForm").find("[id='prepWeightStr']").attr("value",$("#plasmidForm").find("[id='prepWeightSel']").val());
	}
	
	var theObj=document.getElementById("right"); 
	var rightStr = "";
	for(var i=0;i<theObj.options.length;i++) {
		if(rightStr=="") {
			rightStr = rightStr+theObj.options[i].value;
		} else {
			rightStr = rightStr+","+theObj.options[i].value;
		}
		
	}
	$("#plasmidForm").find("[id='restrictionAnalysis']").attr("value",rightStr);
	
	if($("#plasmidForm").find("[id='antibioticResistance']").attr("disabled")==false&&$("#plasmidForm").find("[id='antibioticResistance']").val() == "" ){
		alert("Please select one antibioticResistance .");
		return false;
	}
	if($("#plasmidForm").find("[id='prepWeightStr']").attr("disabled")==false&&$("#plasmidForm").find("[id='prepWeightStr']").val()=="") {
		alert("Please select one quantity.");
		return false;
	}
	if($("#plasmidForm").find("[id='prepWeightStr']").attr("disabled")==false) {
		var prepWeightStr = $("#plasmidForm").find("[id='prepWeightStr']").val();
		if (prepWeightStr != undefined && isNaN(prepWeightStr.split(" ")[0])) {
			alert("Please enter one valid quantity.");
			return false;
		}
	}
	if($("#plasmidForm").find("[id='restrictionAnalysis']").attr("disabled")==false&&rightStr=="") {
		alert("Please select some restrictionAnalysis.");
		return false;
	}
	return true;	
}

function checkSubPlasmid() {
	//如果页面不包含plasmidForm，则返回。
	if(!$("#plasmidForm").attr("id")){
		return true;
	}
	var prepWeightSel = $("#plasmidForm").find("[id='prepWeightSel']").val();
	var txt_04 = $("#plasmidForm").find("[id='txt_04']").val();
	if (prepWeightSel == "0") {
		if (txt_04 == "" || txt_04 == "0") {
			alert("Please enter one Greater than 0 Quantity");
			return false;
		}
		if (isnumber(txt_04) == false) {
			alert("Please enter one valid Quantity");
			return false;
		}
		$("#plasmidForm").find("[id='prepWeightStr']").attr("value",txt_04+" "+$("#plasmidForm").find("[id='prepUom']").html());
	} else {
		$("#plasmidForm").find("[id='prepWeightStr']").attr("value",$("#plasmidForm").find("[id='prepWeightSel']").val());
	}
	var qualityGrade = $("#plasmidForm").find("[name='plasmidPreparation.qualityGrade'][checked]").val();
	if (qualityGrade == undefined || qualityGrade == "") {
		alert("Please select one 'Quality grade'.");
		return false;
	}
	return true;
}

function checkGeneSynthesis(){
	//如果页面不包含geneSynthesis，则返回。
	if(!$("#geneSynthesisForm").attr("id")){
		return true;
	}
	if(geneSequenceType == "Protein" && $("#geneSynthesisForm").find("[name='codeOptmzFlag'][checked]").val() == "N" ){
		alert("Fail to save 'Gene Synthesis', 'Codon Optimization' should be checked.");
		return false;
	}
	if($('#geneSynthesisForm').valid() == false){
		return false;
	}
	var geneSequenceType = $("#geneSynthesisForm").find("[name='sequenceType'][checked]").val();
	var geneSequence = $("#geneSynthesisForm").find("[name='sequence']").val();
	geneSequence = geneSequence.replace(/\n/gi, "").replace(/\r/gi, "").replace(/\s/g, "");
	if (geneSequenceType == "Protein") {
		if (geneSequence == ""){
			alert("Invalid character Sequence, please enter the correct Sequence.");
			return false;
		}
	} else if (geneSequenceType == "DNA") {
		geneSequence = geneSequence.replace(/\d/g, "");
		var reFirstCheck = /^([AGTCagtc])*$/;
		var firstCheck = reFirstCheck.test(geneSequence);
		if (firstCheck == false){
			alert("Invalid character Sequence, when you select  the Sequence type is 'DNA',the Sequence must match 'AGTCagtc'");
			return false;
		}
	} else if (geneSequenceType == "Length") {
		geneSequence = geneSequence.replace(/\D+/g, "");
		if (geneSequence == "") {
			alert("Invalid character Sequence, when you select  the Sequence type is 'Length', the Sequence content must be digital.");
			return false;
		}
		var reFirstCheck = /^([0123456789])*$/;
		var firstCheck = reFirstCheck.test(geneSequence);
		if (firstCheck == false) {
			alert("Invalid character Sequence, when you select  the Sequence type is 'Length', the Sequence content must be digital.");
			return false;
		}
	}
	$("#geneSynthesisForm").find("[name='sequence']").val(geneSequence);
	var cloningFlag = $("#geneSynthesisForm").find("[name='cloningFlag'][checked]").val();
	if(cloningFlag == "Y"){
		var tmpItemId = $("#cloningForm").find("[name='itemId']").val();
		if(tmpItemId != ""){
			if ($("#cloningForm").find("[name='tgtVector']").val() == "Other") {
				if ($("#cloningForm").valid() ==  false) {
					return false;
				}
			} else {
				var tgtCloningMethod = $("#cloningForm").find("[name='tgtCloningMethod'][checked]").val();
				if ("Standard" == tgtCloningMethod && $("#cloningForm").find("[name='tgtCloningSite']").val() == "") {
					alert("Please enter the 'Cloneing site' in 'More Detail','Cloning Strategy' tab.");
					return false;
				}
			}
		}
	}
	var plasmidPrepFlag = $("#geneSynthesisForm").find("[name='plasmidPrepFlag'][checked]").val();
	if(plasmidPrepFlag == "Y"){
		var tmpItemId = $("#plasmidForm").find("[name='itemId']").val();
		if(tmpItemId != "" && checkPlasmid() ==  false){
			return false;
		}
	}
	return true;
}

function check_batch_oligo_form(seqFileName, seqInputName) {
	var seq_file_1 = document.getElementById(seqFileName);
	var seqs1 = document.getElementById(seqInputName).value.replace(/^\s+|\s+$/g, '');

	if (!seq_file_1.value && !seqs1 ) {
		alert('Please upload oligo sequence file or paste the sequences!');
		return false;
	}
	if (seq_file_1 && seq_file_1.value && !/\.(xls|xlsx)$/i.exec(seq_file_1.value)) {
		alert('Wrong file type for Oligo sequence file!');
		return false;
	}
	return true;
}

function checkOligoSequence (type, sequence) {
	var re = /^(A|a|C|c|G|g|T|t)*$/; 
	if (type == 'RNA') {
		re = /^(A|a|C|c|G|g|U|u)*$/;  
	}
    if (!re.test(sequence)) {
        return false;
    }
}

function checkOligo () {
	//如果页面不包含geneSynthesis，则返回。
	if(!$("#oligoDetailForm").attr("id")){
		return true;
	}
	if($('#oligoDetailForm').valid() == false){
		return false;
	}
	var backbone = $("#oligoDetailForm").find("[name='backbone']").val();
	var sequence = $("#oligoDetailForm").find("[name='sequence']").val().replace(/\/.*?\//gi, "")
		.replace(/\{.*?\}/gi, "").replace(/\s/g, "").replace(/\n/gi, "").replace(/\r/gi, "");
	sequence = $.trim(sequence);
	if (sequence != "") {
		var type = "RNA";
		if (backbone == 'DNA') {
			sequence = sequence.replace(/(\(A\)|\(a\))/gi, "A").replace(/(\(C\)|\(c\))/gi, "C").replace(/(\(G\)|\(g\))/gi, "G").replace(/(\(T\)|\(t\))/gi, "T");
			type = "DNA";
		} else if (backbone == 'Phosphorothioated DNA') {
			sequence = sequence.replace(/(\(A\*\)|\(a\*\))/gi, "A").replace(/(\(C\*\)|\(c\*\))/gi, "C").replace(/(\(G\*\)|\(g\*\))/gi, "G").replace(/(\(T\*\)|\(t\*\))/gi, "T");
			type = "DNA";
		} else if (backbone == 'RNA') {
			sequence = sequence.replace(/(\(rA\)|\(ra\)|\(Ra\)|\(RA\))/gi, "A").replace(/(\(rC\)|\(rc\)|\(Rc\)|\(RC\))/gi, "C").replace(/(\(rG\)|\(rg\)|\(Rg\)|\(RG\))/gi, "G").replace(/(\(rU\)|\(ru\)|\(Ru\)|\(RU\))/gi, "U");
		} else if (backbone == 'Phosphorothioated RNA') {
			sequence = sequence.replace(/(\(rA\*\)|\(ra\*\)|\(Ra\*\)|\(RA\*\))/gi, "A").replace(/(\(rC\*\)|\(rc\*\)|\(Rc\*\)|\(RC\*\))/gi, "C").replace(/(\(rG\*\)|\(rg\*\)|\(Rg\*\)|\(RG\*\))/gi, "G").replace(/(\(rU\*\)|\(ru\*\)|\(Ru\*\)|\(RU\*\))/gi, "U");
		} else if (backbone == "2'-OMe-RNA") {
			sequence = sequence.replace(/(\(mA\)|\(ma\)|\(Ma\)|\(MA\))/gi, "A").replace(/(\(mC\)|\(mc\)|\(Mc\)|\(MC\))/gi, "C").replace(/(\(mG\)|\(Mg\)|\(Mg\)|\(MG\))/gi, "G").replace(/(\(mU\)|\(mu\)|\(Mu\)|\(MU\))/gi, "U");
		} else if (backbone == "Phosphorothioated 2'-OMe-RNA") {
			sequence = sequence.replace(/(\(mA\*\)|\(ma\*\)|\(Ma\*\)|\(MA\*\))/gi, "A").replace(/(\(mC\*\)|\(mc\*\)|\(Mc\*\)|\(MC\*\))/gi, "C").replace(/(\(mG\*\)|\(Mg\*\)|\(Mg\*\)|\(MG\*\))/gi, "G").replace(/(\(mU\*\)|\(mu\*\)|\(Mu\*\)|\(MU\*\))/gi, "U");
		}
		if (checkOligoSequence(type, sequence) == false) {
			alert("Sequence is incorrect, please check again in 'More Detail','Details' tab.");
			return false;
		}
	}
	var daPercent = $("#oligoDetailForm").find("[name='daPercent']").val();
	var dcPercent = $("#oligoDetailForm").find("[name='dcPercent']").val();
	var dgPercent = $("#oligoDetailForm").find("[name='dgPercent']").val();
	var dtPercent = $("#oligoDetailForm").find("[name='dtPercent']").val();
	if (daPercent != "" || dcPercent != "" || dgPercent != "" || dtPercent != "") {
		var totalPercent = parseInt(daPercent) + parseInt(dcPercent) + parseInt(dgPercent) + parseInt(dtPercent);
		if (totalPercent != 100) {
			alert("The sum of da,dc, dg, dt must equal 100 in 'More Detail' tab.");
			return false;
		}
	}
	return true;
}

function checkOrfClone () {
	var _return = true;
	//如果页面不包含orfClone，则返回。
	if(!$("#orfCloneForm").attr("id")){
		return _return;
	}
	var orfCloneForm = $("#orfCloneForm");
	var accessionSelectCheckBoxs = orfCloneForm.find(":checkbox[name*='service_check_orfclone'][checked=true]");
	if( accessionSelectCheckBoxs.size() != 0 ){
		accessionSelectCheckBoxs.each(function(){
			var orfCloneTable = $(this).parent().parent();
			var orfCloneTgtVector = orfCloneTable.find("[name='tgtVector']").val();
			if (orfCloneTgtVector == "Other") {
				var cloningForm = orfCloneTable.find("[name='orfClone_cloning_Form']");
				var tgtVectorOther = cloningForm.find("[name='tgtVectorOther']").val();
				if (tgtVectorOther == undefined || $.trim(tgtVectorOther) == "") {
					alert("Please enter the Vector name.");
					_return = false;
				} else {
					var tgtVectorSize = cloningForm.find("[name='tgtVectorSize']").val();
					if (tgtVectorSize == undefined || $.trim(tgtVectorSize) == "") {
						alert("Please enter the Vector size.");
						_return = false;
					} else {
						var tgtResistance = cloningForm.find("[name='tgtResistance']").val();
						if (tgtResistance == 'Other') {
							var tgtResistanceOther = cloningForm.find("[name='tgtResistanceOther']").val();
							if (tgtResistanceOther == undefined || $.trim(tgtResistanceOther) == "") {
								alert("Please enter the Resistance.");
								_return = false;
							}
						}
						var tgtCopyNo = cloningForm.find(":radio[name='tgtCopyNo'][checked=true]").val();
						if (tgtCopyNo == undefined || tgtCopyNo == "") {
							alert("Please select the Copy number.");
							_return = false;
						}
					}
				}
			}
    	});
		if (_return == false) {
			return _return;
		}
		var pla_type_orf = orfCloneForm.find(":radio[name='pla_type_orf'][checked=true]").val();
		if (pla_type_orf == "Other") {
			var prepWeightSel = orfCloneForm.find(":select[id='prepWeightSel']").val();
			if (prepWeightSel == "Other") {
				var prepWeightStr = orfCloneForm.find("[id='txt_04']").val();
				if (prepWeightStr == undefined || $.trim(prepWeightStr) == "") {
					alert("Please enter the Custom plasmid preparation quantity.");
					_return = false;
					return _return;
				} else if (prepWeightStr != undefined && checkRate(prepWeightStr) == false) {
					alert("Please enter one valid Custom plasmid preparation quantity.");
					_return = false;
					return false;
				}
			}
			var qualityGrade = orfCloneForm.find(":radio[name='qualityGrade'][checked=true]").val();
			if (qualityGrade == undefined || $.trim(qualityGrade) == "") {
				alert("Please select the Quality grade.");
				_return = false;
			}
		}
	} else {
		alert("Please select Accession No.");
		_return = false;
	}
	return _return;
}

function checkStdPeptide(){
	//如果页面不包含Peptide，则返回。
	if(!$("#stdPeptideForm").attr("id")){
		return true;
	}
	if($('#stdPeptideForm').valid() == false){
		return false;
	}
	return true;
}

function checkOtherPeptide(){
	var tmpFlag = false;
	//var selItemId = parent.$("#itemListIframe").contents().find("#itemTable tr input:checked").val();
	var selItemId = parent.frames['itemListIframe'].clickItemId;
	//alert(selItemId);
	$.ajax({
		url:orderquoteObj.url("checkOtherPeptide")+"&itemId="+selItemId,
		success:function(data){
			if(data == 'otherPeptide'){
				tmpFlag = true;
			}else{
				tmpFlag = false;
			}
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false
	});
	return tmpFlag;
}

function checkRna53(){
	var tmpFlag = false;
	//var selItemId = parent.$("#itemListIframe").contents().find("#itemTable tr input:checked").val();
	var tmpId = $("#rnaForm").find("[name='itemId']").val();
	var targetStr = $("#rnaForm").find("[name='vectorName']").find("option:selected").text();
	if(targetStr != 'Other'){
		var REG = /\((.*?)\)/;
		var result = targetStr.match(REG);
		var code = result[1];
		var seq = $("#rnaForm").find("[name='sequenceInsert']").val();
		$.ajax({
			url:orderquoteObj.url("checkRna")+"&code="+code+"&seq="+seq,
			success:function(data){
				if(data == 'ok'){
					tmpFlag = true;
				}else{
					alert(data);
					tmpFlag = false;
				}
			},
			error: function(){
				alert("System error! Please contact system administrator for help.");
			},
			type:"POST",
			async:false
		});
	}else{
        tmpFlag = true;
    }
	
	return tmpFlag;
}

function checkGeneUsage () {
	var geneUsage = $("#geneUsageForm").find("[name='geneUsage'][checked]").val();
	if (geneUsage == "Protein expression/analysis") {
		var readingFrame = $("#geneUsageForm").find("[name='readingFrame'][checked]").val();
		if (readingFrame == "Y") {
			var geneUsageText = $("#geneUsageForm").find("[name='geneUsageText']").val();
			if ($.trim(geneUsageText) == "") {
				alert("Please enter the textfield after 'Should be consistent client's requirement' in 'More Detail','Gene Usage' tab.");
				return false;
			}
		} else if (readingFrame == "N") {
		} else {
			alert("Please select a Reading frame in 'More Detail','Gene Usage' tab.");
			return false;
		}
	}
	return true;
}

function checkMutaLib () {
	var mutaLibForm = $("#mutaLibForm");
	//如果页面不包含Mutation Libraries，则返回。
	if(!mutaLibForm.attr("id")){
		return true;
	}
	//校验Mutation Libraries Tab
	if(mutaLibForm.valid() == false) {
		return false;
	} 
	//校验Template Info Tab
	var tmplFlag = mutaLibForm.find("[name='tmplFlag'][checked]").val();
	if (tmplFlag == "Y") {
		if(checkTmpl() == false){
			return false;
		}
	}
	//校验Cloning Strategy Tab
	var tgtVectorName = mutaLibForm.find("[name='tgtVectorName'][checked]").val();
	if(tgtVectorName == "Other"){
		var cloningItemId = $("#cloningForm").find("[name='itemId']").val();
		if(cloningItemId != ""){
			if ($("#cloningForm").find("[name='tgtVector']").val() == "Other") {
				if ($("#cloningForm").valid() ==  false) {
					return false;
				}
				var tgtVectorSize = $("#cloningForm").find("[name='tgtVectorSize']").val();
				if (tgtVectorSize == undefined || tgtVectorSize == "") {
					alert("Please enter the 'Vector size' in 'More Detail','Cloning Strategy' tab.");
					return false;
				}
				var tgtResistance = $("#cloningForm").find("[name='tgtResistance']").val();
				if (tgtResistance == undefined || tgtResistance == "") {
					alert("Please select the 'Resistance' in 'More Detail','Cloning Strategy' tab.");
					return false;
				} else if(tgtResistance == 'Other'){
					var tgtResistanceOther = $("#cloningForm").find("[name='tgtResistanceOther']").val();
					if (tgtResistanceOther == undefined || $.trim(tgtResistanceOther) == "") {
						alert("Please enter the 'Resistance' in 'More Detail','Cloning Strategy' tab.");
						return false;
					}
				}
			} else {
				var tgtCloningMethod = $("#cloningForm").find("[name='tgtCloningMethod'][checked]").val();
				if ("Standard" == tgtCloningMethod && $("#cloningForm").find("[name='tgtCloningSite']").val() == "") {
					alert("Please enter the 'Cloneing site' in 'More Detail','Cloning Strategy' tab.");
					return false;
				}
			}
		}
	}
	//校验Plasmid Preparation Tab
	var plasmidPrepFlag = mutaLibForm.find("[name='plasmidPrepFlag'][checked]").val();
	if(plasmidPrepFlag == "Y"){
		var tmpItemId = $("#plasmidForm").find("[name='itemId']").val();
		if(tmpItemId != "" && checkPlasmid() ==  false){
			return false;
		}
	}
	//校验Gene Usage Tab
	if (!checkGeneUsage()) {
		return false;
	}
	return true;
}

//检查Tmpl页面数据是否正确
function checkTmpl () {
	if(! $("#tmplForm").attr("id")){
		return true;
	}
	var tmpItemId = $("#tmplForm").find("[name='itemId']").val();
	if(tmpItemId == undefined || tmpItemId == "") {
		return true;
	}
	if ($("#tmplForm").valid() == false) {
		return false;
	}
	if ($("#tmplForm").find("[name='tmplVector']").val() == "Other") {
		if (checkTmplOther() == false) {
			return false;
		}
	}
	var tmplCloningMethod = $("#tmplForm").find("[name='tmplCloningMethod'][checked]").val();
	if (tmplCloningMethod == undefined || tmplCloningMethod == "") {
		alert("Please select the 'Cloning Method' in 'More Detail','Template Info' tab.");
		return false;
	}
	if (tmplCloningMethod == "Standard") {
		var tmplCloningSite = $("#tmplForm").find("[name='tmplCloningSite']").val();
		if (tmplCloningSite == undefined || $.trim(tmplCloningSite) == "") {
			alert("Please enter the 'Cloning Site' in 'More Detail','Template Info' tab.");
			return false;
		}
	}
}

function checkTmplOther () {
	var tmplVector = $("#tmplForm").find("[name='tmplVector']").val();
	if (tmplVector == 'Other') {
		var tmplVectorOther = $("#tmplForm").find("[name='tmplVectorOther']").val();
		if (tmplVectorOther == undefined || $.trim(tmplVectorOther) == "") {
			alert("Please enter the 'Vector name' in 'More Detail','Template Info' tab.");
			return false;
		}
		var tmplResistance = $("#tmplForm").find("[name='tmplResistance']").val();
		if (tmplResistance == undefined || tmplResistance == "") {
			alert("Please select the 'Resistance' in 'More Detail','Template Info' tab.");
			return false;
		} else if(tmplResistance == 'Other'){
			var tmplResistanceOther = $("#tmplForm").find("[name='tmplResistanceOther']").val();
			if (tmplResistanceOther == undefined || $.trim(tmplResistanceOther) == "") {
				alert("Please enter the 'Resistance' in 'More Detail','Template Info' tab.");
				return false;
			}
		}
		var tmplVectorSize = $("#tmplForm").find("[name='tmplVectorSize']").val();
		if (tmplVectorSize == undefined || tmplVectorSize == "") {
			alert("Please enter a valid 'Vector size' in 'More Detail','Template Info' tab.");
			return false;
		}
		var tmplCopyNo = $("#tmplForm").find("[name='tmplCopyNo'][checked]").val();
		if (tmplCopyNo == undefined || tmplCopyNo == "") {
			alert("Please select a 'Copy number' in 'More Detail','Template Info' tab.");
			return false;
		}
	}
}

function saveGeneSynthesis(){
	var form = $("#geneSynthesisForm");
	//validate
	if(checkGeneSynthesis() == false){
		return false;
	}
	if($("#geneSynthesisForm").is_form_changed() ==  false  
			&& $("#codonForm").is_form_changed() ==  false
			&& $("#geneUsageForm").is_form_changed() ==  false
			&& $("#moreInfoForm").is_form_changed() ==  false){
		return true;
	}
	var codonData = $("#codonForm").formSerialize();
	if( $(form).find("[name='codeOptmzFlag'][checked]").val() != "Y" ){
		var tmpArr = codonData.split("&");
		var tmpArr2 = [];
		var tmpVal = "";
		var tmpNew = [];
		for(var o in tmpArr){
			tmpArr2 = tmpArr[o].split("=");
			tmpVal = tmpArr2[0];
			tmpNew.push(tmpVal);
		}
		codonData = tmpNew.join("=&");
		codonData = "&"+codonData+"=";
	}else{
		codonData = "&"+codonData;
	}
	//校验Gene Usage Tab
	if (!checkGeneUsage()) {
		return false;
	}
	var usageData = "&"+$("#geneUsageForm").formSerialize();
	var moreInfoData = "&"+$("#moreInfoForm").formSerialize();
	var tmpId = $("#geneSynthesisForm").find("[name='itemId']").val();
	var tmpFlag = false;
	var cloneData = "";
	$.ajax({
		url:orderquoteObj.url("saveGene")+"&itemId="+tmpId,
		data:addFormNameToSerializeData("geneSynthesis", $(form).formSerialize()+codonData+usageData+moreInfoData, "itemId,upload"),
		success:function(data){
			refreshItem(data, tmpId, "");
			tmpFlag = true;
			$("#geneSynthesisForm").refresh_changed_form();
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false,
		dataType:"json"
	});
	return tmpFlag; 
}

function saveOrfClone () {
	var tmpFlag = false;
	if (checkOrfClone() == false) {
		return tmpFlag;
	}
	var orfCloneForm = $("#orfCloneForm");
	var tmpId = orfCloneForm.find("[name='itemId']").val();
	if (tmpId == null || tmpId == "") {
		tmpId = orfCloneForm.find("[id='orfCloneFormItemId']").html();
	}
	var plasmidPreparationData = "";
	var comment = encodeURIComponent(orfCloneForm.find(":textarea[name='comment']").val());
	//组装plasmidPreparationData
	var pla_type_orf = orfCloneForm.find(":radio[name='pla_type_orf'][checked=true]").val();
	if (pla_type_orf != "Other") {
		plasmidPreparationData = "prepWeightStr=10 ug";
	} else {
		var prepWeightSel = orfCloneForm.find(":select[id='prepWeightSel']").val();
		if (prepWeightSel == "Other") {
			plasmidPreparationData = "prepWeightStr="+orfCloneForm.find("[id='txt_04']").val();
		} else {
			plasmidPreparationData = "prepWeightStr="+prepWeightSel;
		}
		var qualityGrade = orfCloneForm.find(":radio[name='qualityGrade'][checked=true]").val();
		plasmidPreparationData += "&qualityGrade="+qualityGrade;
	}
	//组装ORF Clone信息，包含plasmidPreparation和custom clone
	var _i = 0;
	var accessionSelectCheckBoxs = orfCloneForm.find(":checkbox[name*='service_check_orfclone'][checked=true]");
	accessionSelectCheckBoxs.each(function(){
		var accessionInfo = $(this).val();
		var orfCloneTable = $(this).parent().parent();
		var orfCloneData = "accessionInfo="+accessionInfo+"&comments="+comment;
		//保存orfClone
		if (_i ==0) {
			if (_saveOrfClone(orfCloneData, tmpId) == false) {
				return tmpFlag;
			}
			var orfClone_cloning_itemId = orfCloneForm.find("[name='orfClone_cloning_itemId']").val();
			var orfClone_pla_itemId = orfCloneForm.find("[name='orfClone_pla_itemId']").val();
		} else {
			orfCloneData += "&preOrfCloneId="+tmpId;
			var _result = _saveOrfClone(orfCloneData, "");
			if (_result == false) {
				return tmpFlag;
			} else {
				tmpId = _result;
			}
			var orfClone_cloning_itemId = "";
			var orfClone_pla_itemId = "";
		}
		//保存cust Cloning
		var custCloningData = orfCloneTable.find("[name='orfClone_cloning_Form']").formSerialize();
		custCloningData += ("&tgtVector="+encodeURIComponent(orfCloneTable.find(":select[name='tgtVector']").val()));
		if (orfClone_cloning_itemId == undefined) {
			orfClone_cloning_itemId = "";
		}
		if (_saveCloning (custCloningData, "0", tmpId, orfClone_cloning_itemId) == false) {
			return tmpFlag;
		}
		//保存plasmidPreparation
		if (orfClone_pla_itemId == undefined) {
			orfClone_pla_itemId = "";
		}
		if (_savePlasmid(plasmidPreparationData, "0", tmpId, orfClone_pla_itemId) == false) {
			return tmpFlag;
		}
		_i++;
	});
	tmpFlag = true;
	return tmpFlag;
}

function _saveOrfClone (orfCloneData, tmpId) {
	var tmpFlag = false;
	$.ajax({
		url:orderquoteObj.url("saveOrfClone")+"&itemId="+tmpId,
		data:addFormNameToSerializeData("orfClone", orfCloneData, "itemId,upload"),
		success:function(data){
			if (hasException(data)) {
				return tmpFlag;
			}
			var itemInfo = [];
			for(var key in data){
				tmpId = key;
				break;
			}
			tmpFlag = true;
		},
		error: function(){
			alert("Save ORF Cloning error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false,
		dataType:"json"
	});
	if (tmpFlag == true) {
		return tmpId;
	}
	return tmpFlag;
}

function saveOligo() {
	//validate
	if(checkOligo() == false){
		return false;
	}
	if($("#oligoDetailForm").is_form_changed() ==  false ) {
		return true;
	}
	var form = $("#oligoDetailForm");
	var tmpId = form.find("[name='itemId']").val();
	var tmpFlag = false;
	$.ajax({
		url:orderquoteObj.url("saveOligo")+"&itemId="+tmpId,
		data:addFormNameToSerializeData("oligo", form.formSerialize(), "itemId,upload"),
		success:function(data){
			refreshItem(data, tmpId, "");
			tmpFlag = true;
			$("#oligoDetailForm").refresh_changed_form();
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false,
		dataType:"json"
	});
	return tmpFlag; 
}

function saveCustomService() {
	//validate
	if(checkCustomerService() == false){
		return false;
	}
	if($("#customServiceForm").is_form_changed() ==  false ) {
		return true;
	}
	var form = $("#customServiceForm");
	var tmpId = form.find("[name='itemId']").val();
	var tmpFlag = false;
	$.ajax({
		url:orderquoteObj.url("saveCustomService")+"&itemId="+tmpId,
		data:addFormNameToSerializeData("customService", form.formSerialize(), "itemId,upload"),
		success:function(data){
			refreshItem(data, tmpId, "");
			tmpFlag = true;
			$("#customServiceForm").refresh_changed_form();
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false,
		dataType:"json"
	});
	return tmpFlag; 
}

function saveRna(){
	//如果页面不包含geneSynthesis，则返回。
	if(!$("#rnaForm").attr("id")){
		return true;
	}
	if($('#rnaForm').valid() == false){
		return false;
	}
	var form = $("#rnaForm");
	//validate
	if($("#rnaForm").is_form_changed() ==  false
			&& $("#geneUsageForm").is_form_changed() ==  false
			&& $("#moreInfoForm").is_form_changed() ==  false){
		if (plasmidPrepFlag == "Y") {
			if (checkSubPlasmid() == true && $("#plasmidForm").is_form_changed() ==  false) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
	var plasmidPrepFlag = $("#rnaForm").find("[name='plasmidPrepFlag'][checked]").val();
	if (plasmidPrepFlag == "Y") {
		if(checkSubPlasmid() == false){
			return false;
		}
	}
	//校验Gene Usage Tab
	if (!checkGeneUsage()) {
		return false;
	}
	var usageData = "&"+$("#geneUsageForm").formSerialize();
	var moreInfoData = "&"+$("#moreInfoForm").formSerialize();
	var tmpId = $("#rnaForm").find("[name='itemId']").val();
	if(!checkRna53()){
		return false;
	}
	var tmpFlag = false;
	$.ajax({
		url:orderquoteObj.url("saveRna")+"&itemId="+tmpId,
		data:addFormNameToSerializeData("rna", $(form).formSerialize()+usageData+moreInfoData, "itemId,upload"),
		success:function(data){
			refreshItem(data, tmpId, "");
			tmpFlag = true;
			$("#rnaForm").refresh_changed_form();
		},
		error: function(){
			alert("Error");
		},
		type:"POST",
		async:false,
		dataType:"json"
	});
	return tmpFlag; 
}

function saveCloning(cloningFlag){
	//校验Gene Usage Tab
	if (!checkGeneUsage()) {
		return false;
	}
	if(show == "gene"){//判断gene是否发生改变，改变的话先保存父service
		if($("#geneSynthesisForm").is_form_changed() == true){
			if (saveGeneSynthesis() == false) {
				return false;
			}
		}
		var parentId = $("#geneSynthesisForm").find("[name='itemId']").val();
	} else if(show == "muta"){
		if($("#tmplForm").is_form_changed() == true || $("#targetForm").is_form_changed() == true){
			if (saveMuta("Y") == false) {
				return false;
			}
		}
		var parentId = $("#tmplForm").find("[name='itemId']").val();
	} else if (show == "orfClone") {
		if($("#orfCloneForm").is_form_changed() == true){
			if (saveOrfClone() == false) {
				return false;
			}
		}
		var parentId = $("#orfCloneForm").find("[name='itemId']").val();
		if (parentId == null || parentId == "") {
			parentId = $("#orfCloneForm").find("[id='orfCloneFormItemId']").html();
		}
	} else if (show == "mutaLib") {
		if($("#mutaLibForm").is_form_changed() == true){
			if (saveMutationLibraries() == false) {
				return false;
			}
		}
		var parentId = $("#mutaLibForm").find("[name='itemId']").val();
	}
	if(!parentId){
		parentId = "";
	}
	
	var cloningForm = $("#cloningForm");
    var tmpItemId = "";
	if(show == "cloning"){
		 tmpItemId = $("#tmplForm").find("[name='itemId']").val();
	}else{
		 tmpItemId = $("#cloningForm").find("[name='itemId']").val();
	}
	var removeFlag = 0;
	if(cloningFlag == "N"){
		removeFlag = 1;
	}
	if(show == 'cloning'){
		var tmplCloningMethod = $("#tmplForm").find("[name='tmplCloningMethod'][checked]").val();
		if (tmplCloningMethod == undefined || tmplCloningMethod == "") {
			alert("Please select the 'Cloning Method' in 'More Detail','Template Info' tab.");
			return false;
		}
		if (tmplCloningMethod == "Standard") {
			var tmplCloningSite = $("#tmplForm").find("[name='tmplCloningSite']").val();
			if (tmplCloningSite == undefined || $.trim(tmplCloningSite) == "") {
				alert("Please enter the 'Cloning Site' in 'More Detail','Template Info' tab.");
				return false;
			}
		}
		if ($("#tmplForm").valid() == false) {
			return false;
		}
		var tmplVector = $("#tmplForm").find("[name='tmplVector']").val();
		if (tmplVector == "Other") {
			if (checkTmplOther() == false) {
				return false;
			}
		}
		var tgtCloningMethod = $("#targetForm").find("[name='tgtCloningMethod'][checked]").val();
		if (tgtCloningMethod == undefined || tgtCloningMethod == "") {
			alert("Please select the 'Cloning Method' in 'More Detail','Target Construct' tab.");
			return false;
		}
		if (tgtCloningMethod == "Standard") {
			var tgtCloningSite = $("#targetForm").find("[name='tgtCloningSite']").val();
			if (tgtCloningSite == undefined || $.trim(tgtCloningSite) == "") {
				alert("Please enter the 'Cloning Site' in 'More Detail','Target Construct' tab.");
				return false;
			}
		}
		var tgtSeqSameFlag = $("#targetForm").find("[name='tgtSeqSameFlag'][checked]").val();
		if (tgtSeqSameFlag != "Y") {
			var tgtSequence = $("#targetForm").find("[name='tgtSequence']").val();
			if(tgtSequence == undefined || $.trim(tgtSequence) == "") {
				alert("Please enter the Target Insert Sequence or checked it in 'More Detail','Target Construct' tab.");
				return false;
			} 
		}
	}
	var tmplData = "";
	var targetData = "";
	var usageData = "";
	var moreInfoData = "";
	var cloningData = "";
	var cloningFlag = "";
	var plasmidPrepFlag = "";
	if(show == "cloning"){
		 removeFlag = 0;//cloning不存在删除情况。
		 tmplData = $("#tmplForm").formSerialize();
		 targetData = "&"+$("#targetForm").formSerialize();
		 usageData = "&"+$("#geneUsageForm").formSerialize();
		 moreInfoData = "&"+$("#moreInfoForm").formSerialize();
		 cloningFlag = $("#targetForm").find("[name='cloningFlag'][checked]").val();
		 plasmidPrepFlag = $("#targetForm").find("[name='plasmidPrepFlag'][checked]").val();
		 if (plasmidPrepFlag == "Y") {
			if(checkSubPlasmid() == false){
				return false;
			}
		 }
		if(cloningFlag == "Y"){
			if ($("#cloningForm").find("[name='tgtVector']").val() == "Other") {
				if ($("#cloningForm").valid() ==  false) {
					return false;
				}
				var tgtVector = $("#cloningForm").find("[name='tgtVector']").val();
				if (tgtVector == 'Other') {
					var tgtVectorOther = $("#cloningForm").find("[name='tgtVectorOther']").val();
					if (tgtVectorOther == undefined || $.trim(tgtVectorOther) == "") {
						alert("Please enter the Vector name.");
						return false;
					}
				}
				var tgtResistance = $("#cloningForm").find("[name='tgtResistance']").val();
				if (tgtResistance == 'Other') {
					var tgtResistanceOther = $("#cloningForm").find("[name='tgtResistanceOther']").val();
					if (tgtResistanceOther == undefined || $.trim(tgtResistanceOther) == "") {
						alert("Please enter the Resistance.");
						return false;
					}
				}
			} else {
				var tgtCloningMethod = $("#cloningForm").find("[name='tgtCloningMethod'][checked]").val();
				if ("Standard" == tgtCloningMethod && $("#cloningForm").find("[name='tgtCloningSite']").val() == "") {
					alert("Please enter the 'More Detail','Cloning Strategy','Cloneing site'.");
					return false;
				}
			}
			cloningData = "&"+$("#cloningForm").formSerialize();
		}
	} else{
		if($("#cloningForm").attr("id")){
			var tgtVector = $("#cloningForm").find("[name='tgtVector']").val();
			if (tgtVector == 'Other') {
				var tgtVectorOther = $("#cloningForm").find("[name='tgtVectorOther']").val();
				if (tgtVectorOther == undefined || $.trim(tgtVectorOther) == "") {
					alert("Please enter the Vector name.");
					return false;
				}
			}
			var tgtResistance = $("#cloningForm").find("[name='tgtResistance']").val();
			if (tgtResistance == 'Other') {
				var tgtResistanceOther = $("#cloningForm").find("[name='tgtResistanceOther']").val();
				if (tgtResistanceOther == undefined || $.trim(tgtResistanceOther) == "") {
					alert("Please enter the Resistance.");
					return false;
				}
			}
			var tgtCloningMethod = $("#cloningForm").find("[name='tgtCloningMethod'][checked]").val();
			if (tgtCloningMethod == undefined || tgtCloningMethod == "") {
				alert("Please select the 'Cloning Method' in 'More Detail','Cloning Strategy' tab.");
				return false;
			}
			if (tgtCloningMethod == "Standard") {
				var tgtCloningSite = $("#cloningForm").find("[name='tgtCloningSite']").val();
				if (tgtCloningSite == undefined || $.trim(tgtCloningSite) == "") {
					alert("Please enter the 'Cloning Site' in 'More Detail','Cloning Strategy' tab.");
					return false;
				}
			}
		}
		cloningData = $("#cloningForm").formSerialize();
	}
    // add by zhanghuibin 校验是否更改
    var validStr  = addFormNameToSerializeData("custCloning", tmplData+targetData+cloningData+usageData+moreInfoData, "itemId,upload");
    if((removeFlag == 0) && ($("#cloningForm_initData").val() == validStr)){
        return true;
    }
	var tmpFlag = false;
	$.ajax({
		url:orderquoteObj.url("saveCustCloning")+"&removeFlag="+removeFlag+"&parentId="+parentId+"&itemId="+tmpItemId,
		data:validStr,
		dataType:"json",
		success:function(data){
			if (hasException(data)) {
				return tmpFlag;
			}else if (data.message != undefined && data.message != "") {
				alert(data.message);
				return tmpFlag;
			}
			var itemId = "";
			var itemInfo = [];
			for(var key in data){
				itemId = key;
				itemInfo = data[key];
				break;
			}
			refreshItem(itemInfo, itemId, removeFlag);
			
			if(show == "cloning"){
				$("#tmplForm").find("[name='itemId']").val(itemId);
				if(removeFlag == 1){
					$("#tmplForm").find("[name='itemId']").val("");
				}
				$("form").refresh_changed_form();	
			}else{
				$("#cloningForm").find("[name='itemId']").val(itemId);
				if(removeFlag == 1){
					$("#cloningForm").find("[name='itemId']").val("");
				}
				$("#cloningForm").refresh_changed_form();
			}
			//if($("#cloningForm").attr("id")){
				//$("#cloningForm").find("[name='tgtVector']").trigger("change");
			//}
			tmpFlag = true;
			reSetItemNo(parent.$("#itemTable"));
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false
	});
	return tmpFlag;
}

//保存CustCloning，此方法仅供保存ORF Clone时用
function _saveCloning (custCloningData, removeFlag, parentId, tmpItemId) {
	var tmpFlag = false;
	$.ajax({
		url:orderquoteObj.url("saveCustCloning")+"&removeFlag="+removeFlag+"&parentId="+parentId+"&itemId="+tmpItemId,
		data:addFormNameToSerializeData("custCloning", custCloningData, "itemId,upload"),
		dataType:"json",
		success:function(data){
			if (hasException(data)) {
				return tmpFlag;
			}else if (data.message != undefined && data.message != "") {
				alert(data.message);
				return tmpFlag;
			}
			tmpFlag = true;
		},
		error: function(){
			alert("Save CustCloning error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false
	});
	return tmpFlag;
}

function savePlasmid(plasmidPrepFlag){
	if(show == "gene"){//判断gene是否发生改变，改变的话先保存父service
		if($("#geneSynthesisForm").is_form_changed() == true){
			if (saveGeneSynthesis() == false) {
				return false;
			}
		}
		var parentId = $("#geneSynthesisForm").find("[name='itemId']").val();
	} else if (show == "orfClone") {
		if($("#orfCloneForm").is_form_changed() == true){
			if (saveOrfClone() == false) {
				return false;
			}
		}
		var parentId = $("#orfCloneForm").find("[name='itemId']").val();
		if (parentId == null || parentId == "") {
			parentId = $("#orfCloneForm").find("[id='orfCloneFormItemId']").html();
		}
	} else if (show == "mutaLib") {
		if($("#mutaLibForm").is_form_changed() == true){
			if (saveMutationLibraries() == false) {
				return false;
			}
		}
		var parentId = $("#mutaLibForm").find("[name='itemId']").val();
	} else if(show == "muta"){
		if($("#tmplForm").is_form_changed() == true || $("#targetForm").is_form_changed() == true){
			if (saveMuta("Y") == false) {
				return false;
			}
		}
		var parentId = $("#tmplForm").find("[name='itemId']").val();
	} else if(show == "rna"){
		var parentId = $("#rnaForm").find("[name='itemId']").val();
	} else if(show == "cloning"){
		var parentId = $("#tmplForm").find("[name='itemId']").val();
	}
	var plasmidForm = $("#plasmidForm");
	var tmpItemId= $("#plasmidForm").find("[name='itemId']").val();
	var removeFlag = 0;
	if(plasmidPrepFlag == "N"){
		removeFlag = 1;
	}
	if(show == "plasmid"){
		if(removeFlag == 0 && tmpItemId != "" && checkPlasmid() == false){
			return false;
		}
	}
	if(!parentId){
		parentId = "";
	}
    var moreInfoData = "";
	if(show == "plasmid"){
	    moreInfoData = "&"+$("#moreInfoForm").formSerialize();
	}
    var validStr = addFormNameToSerializeData(null, plasmidForm.formSerialize()+moreInfoData, "itemId,upload");
    if((removeFlag == 0) && (validStr == $("#plasmidForm_initData").attr("value"))){
        return true;
    }
	var tmpFlag = false;
	$.ajax({
		url:orderquoteObj.url("savePlasmid")+"&removeFlag="+removeFlag+"&parentId="+parentId,
		data: validStr,
		dataType:"json",
		success:function(data){
			if (hasException(data)) {
				return tmpFlag;
			}
			var itemId = "";
			var itemInfo = [];
			for(var key in data){
				itemId = key;
				itemInfo = data[key];
				break;
			}
			$("#plasmidForm").find("[name='itemId']").val(itemId);
			refreshItem(itemInfo, itemId, removeFlag);
			if(removeFlag == 1){
				$("#plasmidForm").find("[name='itemId']").val("");
			}
			tmpFlag = true;
			$("#plasmidForm").refresh_changed_form();
			if(show == "plasmid"){
				$("#moreInfoForm").refresh_changed_form();
			}
			reSetItemNo(parent.$("#itemTable"));
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false
	});
	return tmpFlag;
}

//保存plasmid Preparation，此方法仅供保存ORF Clone时用
function _savePlasmid(plasmidPreparationData, removeFlag, parentId, itemId){
	var tmpFlag = false;
	$.ajax({
		url:orderquoteObj.url("savePlasmid")+"&removeFlag="+removeFlag+"&parentId="+parentId+"&itemId="+itemId,
		data: addFormNameToSerializeData("plasmidPreparation", plasmidPreparationData, "itemId,upload"),
		dataType:"json",
		success:function(data){
			if (hasException(data)) {
				return tmpFlag;
			}
			tmpFlag = true;
		},
		error: function(){
			alert("Save Plasmid Preparation error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false
	});
	return tmpFlag;
}

function saveMuta(mutaFlag){
	var tmplForm = $("#tmplForm");
	var removeFlag = 0;
	if(mutaFlag == "N"){
		removeFlag = 1;
	}
	var parentId = "";
	
	var tmpItemId= $("#tmplForm").find("[name='itemId']").val();
	var moreInfoData = "&"+$("#moreInfoForm").formSerialize();
	var targetData = "&"+$("#targetForm").formSerialize();
	var usageData = "&"+$("#geneUsageForm").formSerialize();
	var cloningFlag = $("#targetForm").find("[name='cloningFlag'][checked]").val();
	var plasmidPrepFlag = $("#targetForm").find("[name='plasmidPrepFlag'][checked]").val();
	if (plasmidPrepFlag == "Y") {
		if(checkSubPlasmid() == false){
			return false;
		}
	}
	var tmpFlag = false;
	if (show = "muta") {
		if($("#tmplForm").attr("id") && ($("#tmplForm").find("[id='parentId']").val() == undefined 
				|| $("#tmplForm").find("[id='parentId']").val() == '')){
			if ($("#tmplForm").valid() == false) {
				return false;
			}
			if ($("#tmplForm").find("[name='tmplVector']").val() == "Other") {
				if (checkTmplOther() == false) {
					return false;
				}
			}
			var tmplCloningMethod = $("#tmplForm").find("[name='tmplCloningMethod'][checked]").val();
			if (tmplCloningMethod == undefined || tmplCloningMethod == "") {
				alert("Please select the 'Cloning Method' in 'More Detail','Template Info' tab.");
				return false;
			}
			if (tmplCloningMethod == "Standard") {
				var tmplCloningSite = $("#tmplForm").find("[name='tmplCloningSite']").val();
				if (tmplCloningSite == undefined || $.trim(tmplCloningSite) == "") {
					alert("Please enter the 'Cloning Site' in 'More Detail','Template Info' tab.");
					return false;
				}
			}
		}
		if($("#targetForm").valid() == false) {
			return false;
		}
		if (cloningFlag == "Y") {
			if ($("#cloningForm").find("[name='tgtVector']").val() == "Other") {
				var tgtVectorOther = $("#cloningForm").find("[name='tgtVectorOther']").val();
				if (tgtVectorOther == undefined || $.trim(tgtVectorOther) == "") {
					alert("Please enter the Vector name.");
					return false;
				}
				if ($("#cloningForm").valid() ==  false) {
					return false;
				}
			} else {
				var tgtCloningMethod = $("#cloningForm").find("[name='tgtCloningMethod'][checked]").val();
				if ("Standard" == tgtCloningMethod && $("#cloningForm").find("[name='tgtCloningSite']").val() == "") {
					alert("Please enter the 'More Detail','Cloning Strategy','Cloneing site'.");
					return false;
				}
			}
		}
	} else {
		if($("#targetForm").valid() == false) {
			return false;
		} 
		if (cloningFlag == "Y") {
			if ($("#cloningForm").find("[name='tgtVector']").val() == "Other") {
				var tgtVectorOther = $("#cloningForm").find("[name='tgtVectorOther']").val();
				if (tgtVectorOther == undefined || $.trim(tgtVectorOther) == "") {
					alert("Please enter the Vector name.");
					return false;
				}
				var tgtResistance = $("#cloningForm").find("[name='tgtResistance']").val();
				if (tgtResistance == 'Other') {
					var tgtResistanceOther = $("#cloningForm").find("[name='tgtResistanceOther']").val();
					if (tgtResistanceOther == undefined || $.trim(tgtResistanceOther) == "") {
						alert("Please enter the Resistance.");
						return false;
					}
				}
				if ($("#cloningForm").valid() ==  false) {
					return false;
				}
			} else {
				var tgtCloningMethod = $("#cloningForm").find("[name='tgtCloningMethod'][checked]").val();
				if ("Standard" == tgtCloningMethod && $("#cloningForm").find("[name='tgtCloningSite']").val() == "") {
					alert("Please enter the 'More Detail','Cloning Strategy','Cloneing site'.");
					return false;
				}
			}
		}
	}
    var validData = $(tmplForm).formSerialize()+moreInfoData+targetData+usageData;
    if($("#mutaForm_initData").val() == validData){
        return true;
    }
	$.ajax({
		url:orderquoteObj.url("saveMuta")+"&removeFlag="+removeFlag+"&parentId="+parentId+"&itemId="+tmpItemId,
		data:addFormNameToSerializeData("mutagenesis", validData,"itemId,upload"),
		dataType:"json",
		success:function(data){
			if (hasException(data)) {
				return tmpFlag;
			}
			var itemId = "";
			var itemInfo = [];
			for(var key in data){
				itemId = key;
				itemInfo = data[key];
				break;
			}
			itemId = tmpItemId;
			$("#tmplForm").find("[name='itemId']").val(itemId);
			refreshItem(itemInfo, itemId, removeFlag);
			if(removeFlag == 1){
				$("#tmplForm").find("[name='itemId']").val("");
			}
			tmpFlag = true;
			$("#tmplForm").refresh_changed_form();
//			$("#moreInfoForm").refresh_changed_form();
			reSetItemNo(parent.$("#itemTable"));
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false
	});
	return tmpFlag;
}

function saveMutationLibraries(){
	if (checkMutaLib()==false) {
		return false;
	}
	var mutaLibForm = $("#mutaLibForm");
	var mutaLibItemId= mutaLibForm.find("[name='itemId']").val();
	var moreInfoData = "&"+$("#moreInfoForm").formSerialize();
	var tmplData = "&"+$("#tmplForm").formSerialize();
	var usageData = "&"+$("#geneUsageForm").formSerialize();
    var validData = mutaLibForm.formSerialize()+tmplData+moreInfoData+usageData;
    if($("#mutaLibForm_initData").val() == validData){
        return true;
    }
	var tmpFlag = false;
	$.ajax({
		url:orderquoteObj.url("saveMutaLib")+"&itemId="+mutaLibItemId,
		data:addFormNameToSerializeData("mutaLib", validData,"itemId,upload"),
		dataType:"json",
		success:function(data){
			if (hasException(data)) {
				return tmpFlag;
			} else if (data.message != undefined && data.message != "") {
				alert(data.message);
				return tmpFlag;
			}
			var itemId = "";
			var itemInfo = [];
			for(var key in data){
				itemId = key;
				itemInfo = data[key];
				break;
			}
			itemId = mutaLibItemId;
			$("#mutaLibForm").find("[name='itemId']").val(itemId);
			refreshItem(itemInfo, itemId, 0);
			tmpFlag = true;
			$("#mutaLibForm").refresh_changed_form();
//			$("#moreInfoForm").refresh_changed_form();
			reSetItemNo(parent.$("#itemTable"));
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false
	});
	return tmpFlag;
}

function saveTopPkgService () {
	var tmpFlag = false;
	var tmpItemId = $("#pkgId").val();
	if (tmpItemId == undefined || tmpItemId == "") {
		return true;
	}
	var parentName = $("#parentName").val();
	if (parentName == undefined || $.trim(parentName) == "") {
		alert("Please enter the Name in 'More Detail' tab.");
		return tmpFlag;
	}
	var parentDescription = $("#parentDescription").val();
	if (parentDescription == undefined || $.trim(parentDescription) == "") {
		alert("Please enter the Description in 'More Detail' tab.");
		return tmpFlag;
	}
    //add by zhanghuibin 验证是否修改
    var validStr =  parentName + parentDescription;
    if($("#pkgForm_initData").val() == validStr){
        return true;
    }
	var dataStr = "";
	parentDescription = encodeURIComponent(parentDescription);
	if(orderquoteObj.type == "quote"){
		dataStr = "quotePkgService.description="+parentDescription+"&quotePkgService.name="+parentName;
	} else {
		dataStr = "orderPkgService.description="+parentDescription+"&orderPkgService.name="+parentName;
	}
	$.ajax({
		url:orderquoteObj.url("savePkgService")+"&itemId="+tmpItemId,
		data:dataStr,
		dataType:"json",
		success:function(data){
			if (data.message != undefined && data.message != "") {
				alert(data.message);
				tmpFlag = false;
				return tmpFlag;
			}
			tmpFlag = true;
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false
	});
	return tmpFlag;
}

function savePkgService(parentId, tmpItemId, pkgName, description, catalogNo, 
		stepId, seqFlag, sequence, proteinSeq, cost, price, additionInfo1, additionInfo2, seqBpCost, seqBpPrice){
	if(!pkgName){
		alert("Please enter the Step name.");
		return false;
	}
	if (seqFlag != undefined && seqFlag == 'Y') {
		if (cost != undefined && cost != "" && checkRate(cost) == false) {
			alert("Please enter the valid Step Cost.");
			return false;
		}
		if (price != undefined && price != "" && checkRate(price) == false) {
			alert("Please enter the valid Step Price.");
			return false;
		}
		if (seqBpCost != undefined && seqBpCost != "" && checkRate(seqBpCost) == false) {
			alert("Please enter the valid Step bpCost.");
			return false;
		}
		if (seqBpPrice != undefined && seqBpPrice != "" && checkRate(seqBpPrice) == false) {
			alert("Please enter the valid Step bpPrice.");
			return false;
		}
		var hasSequence = true;
		var hasProtein = true;
		if (sequence == undefined || sequence == '' || sequence.indexOf("Enter Gene Sequence...") != -1) {
			sequence = "";
			hasSequence = false;
		}
		if (proteinSeq == undefined || proteinSeq == '' || proteinSeq.indexOf("Enter Protein Sequence...") != -1) {
			proteinSeq = "";
			hasProtein = false;
		}
		if (hasSequence == false && hasProtein == false) {
			alert("Please enter the Gene Sequence or Protein Sequence.");
			return false;
		}
		if (hasSequence == true) {
			sequence = sequence.replace(/\n/gi, "").replace(/\r/gi, "").replace(/\s/g, "");
			if (sequence == '') {
				alert("Invalid character Sequence, please enter the correct Gene Sequence.");
				return false;
			}
			sequence = sequence.replace(/\d/g, "");
			var reFirstCheck = /^([AGTCagtc])*$/;
			var firstCheck = reFirstCheck.test(sequence);
			if (firstCheck == false){
				alert("Invalid character Sequence, The Gene Sequence must match 'AGTCagtc'.");
				return false;
			}
		}
		if (hasProtein == true) {
			proteinSeq = proteinSeq.replace(/\n/gi, "").replace(/\r/gi, "").replace(/\s/g, "");
			if (proteinSeq == '') {
				alert("Invalid character Sequence, please enter the correct Protein Sequence.");
				return false;
			}
		}
	} else {
		if (cost == undefined || cost == "") {
			alert("Please enter the Step Cost.");
			return false;
		}
		if (checkRate(cost) == false) {
			alert("Please enter the valid Step Cost.");
			return false;
		}
		if (price == undefined || price == "") {
			alert("Please enter the Step Price.");
			return false;
		}
		if (checkRate(price) == false) {
			alert("Please enter the valid Step Price.");
			return false;
		}
	}
	if (cost == undefined || cost == "") {
		cost = 0;
	}
	if (price == undefined || price == "") {
		price = 0;
	}
	if (seqBpCost == undefined || seqBpCost == "") {
		seqBpCost = 0;
	}
	if (seqBpPrice == undefined || seqBpPrice == "") {
		seqBpPrice = 0;
	}
	catalogNo = encodeURIComponent(catalogNo);
	pkgName = encodeURIComponent(pkgName);
	if (sequence == undefined) {
		sequence = "";
	}
	sequence = encodeURIComponent(sequence);
	if (proteinSeq == undefined) {
		proteinSeq = "";
	}
	proteinSeq = encodeURIComponent(proteinSeq);
	if (additionInfo1 == undefined) {
		additionInfo1 = "";
	}
	additionInfo1 = encodeURIComponent(additionInfo1);
	if (additionInfo2 == undefined) {
		additionInfo2 = "";
	}
	additionInfo2 = encodeURIComponent(additionInfo2);
	
	var tmpFlag = false;
	if(orderquoteObj.type == "quote"){
		var dataStr = "quotePkgService.name="+pkgName+"&quotePkgService.description="+description
			+"&quotePkgService.catalogNo="+catalogNo+"&quotePkgService.seqType="+seqFlag
			+"&quotePkgService.sequence="+sequence+"&quotePkgService.cost="+cost
			+"&quotePkgService.unitPrice="+price+"&quotePkgService.seqBpCost="+seqBpCost
			+"&quotePkgService.seqBpPrice="+seqBpPrice+"&quotePkgService.seqFlag="+seqFlag
			+"&quotePkgService.proteinSeq="+proteinSeq+"&quotePkgService.additionInfo1="+additionInfo1
			+"&quotePkgService.additionInfo2="+additionInfo2;
	}else{
		var dataStr = "orderPkgService.name="+pkgName+"&orderPkgService.description="+description
			+"&orderPkgService.catalogNo="+catalogNo+"&orderPkgService.seqType="+seqFlag
			+"&orderPkgService.sequence="+sequence+"&orderPkgService.cost="+cost
			+"&orderPkgService.unitPrice="+price+"&orderPkgService.seqBpCost="+seqBpCost
			+"&orderPkgService.seqBpPrice="+seqBpPrice+"&orderPkgService.seqFlag="+seqFlag
			+"&orderPkgService.proteinSeq="+proteinSeq+"&orderPkgService.additionInfo1="+additionInfo1
			+"&orderPkgService.additionInfo2="+additionInfo2;
	}
	$.ajax({
		url:orderquoteObj.url("savePkgService")+"&parentId="+parentId+"&itemId="+tmpItemId,
		data:dataStr,
		dataType:"json",
		success:function(data){
			if (data.message != undefined && data.message != "") {
				alert(data.message);
				tmpFlag = false;
				return tmpFlag;
			}
			var itemId = "";
			var itemInfo = [];
			for(var key in data){
				itemId = key;
				itemInfo = data[key];
				break;
			}
			//itemId = tmpItemId;
			//设置itemId
			refreshItem(itemInfo, itemId, "");
			var contentDiv = $("div[name='contentPkgSerDiv'][catalogNo='"+itemInfo.catalogNo+"']");
			contentDiv.find("[name='addSerPkgBtn']").attr("itemId", itemId);
			tmpFlag = true;
			//reSetItemNo(parent.$("#itemTable"));
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false
	});
	return tmpFlag;
}

function saveAntibody(){
	var tmpFlag = false;
	var antibodyItemId = $("#antibodyItemId").val();
	var antigenType = $("#antigenType").val();
	var antibodyName = $("#antibodyName").val();
	var customSequence = $("#customSequence").val();
	var comments = $("#antibodyComments").val();
	if(antibodyName == ""){
		alert("Please enter the Antibody Name.");
		return tmpFlag;
	}
	var tmpFlagBefore = true;
	var beforei=0;
	$("#antibodyPeptideContainer").find("div[name='peptideMapDiv']").each(function(beforei, n){
		if(checkPeptideForAntibody(antibodyItemId, $(this).attr("itemId")) == false){
			tmpFlagBefore = false;
			return tmpFlagBefore;
		}
	});
	if(tmpFlagBefore == false){
		return tmpFlag;
	}
	
	var dataStr = "antibody.antibodyName="+antibodyName+"&antibody.customSequence="+customSequence
					+"&antibody.comments="+comments+"&antibody.antigenType="+antigenType;
    //add by zhanghuibin
    if(dataStr == $("#antibodyForm_initData").attr("value")){
        return true;
    }
	$.ajax({
		url:orderquoteObj.url("saveAntibody")+"&itemId="+antibodyItemId,
		data:dataStr,
		dataType:"json",
		success:function(data){
			var itemId = "";
			var itemInfo = [];
			for(var key in data){
				itemId = key;
				itemInfo = data[key];
				break;
			}
			//itemId = tmpItemId;
			//设置itemId
			refreshItem(itemInfo, itemId, "");
			var tmpFlag2 = true;
			var i=0;
			$("#antibodyPeptideContainer").find("div[name='peptideMapDiv']").each(function(i, n){
				if(tmpFlag2 == false){
					return;
				}
				tmpFlag2 = savePeptideForAntibody(antibodyItemId, $(this).attr("itemId"));
				if(tmpFlag2 == false){
					return;
				}
			});
			if(tmpFlag2 == true){
				tmpFlag = true;
			}
			
			$.ajax({
				url:orderquoteObj.url("getAntibodyPeptidePrice")+"&itemId="+antibodyItemId,
				data:dataStr,
				dataType:"json",
				success:function(data){
					var itemId = "";
					var itemInfo = [];
					for(var key in data){
						itemId = key;
						itemInfo = data[key];
						refreshItem(itemInfo, itemId, false);
						//break;
					}
					//itemId = tmpItemId;
					//设置itemId
					
					tmpFlag = true;
				},	
				error: function(){
					//alert("Error");
				},
				type:"POST",
				async:false
			});
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false
	});
	return tmpFlag;
}

function checkPeptideForAntibody(antibodyItemId, itemId){
	var tmpFlag = false;
	var antigenType = $("#antigenType").val();
	var dataStr = "";
	if(itemId){
		var peptideForm = $("#antibodyPeptideContainer").find("div[name='peptideMapDiv'][itemId='"+itemId+"']").find("form");
		if(peptideForm.find("[name='name']").val() == ""){
			alert("Please enter the Peptide Name.");
			return tmpFlag;
		}  
		var seqLength = peptideForm.find("[name='seqLength']").val();
		var sequence = peptideForm.find("[name='sequence']").val();
		if ((seqLength == undefined || seqLength == "" || seqLength == "0" || isNaN(seqLength) 
				|| parseFloat(seqLength) <=0) && (sequence == undefined || sequence == "")) {
			alert("Please enter the valid Peptide Sequence Length or Peptide Sequence.");
			return tmpFlag;
		}
		if (sequence != undefined && sequence != "") {
			var resultValidate = checkAntibodyPeptideSequenceValidateTrigger(peptideForm, sequence);
			if (resultValidate != true) {
				return tmpFlag;
			}
		}
	}
	tmpFlag = true;
	return tmpFlag;
}

function savePeptideForAntibody(antibodyItemId, itemId){
	var tmpFlag = false;
	var antigenType = $("#antigenType").val();
	var dataStr = "";
	if(itemId){
		var peptideForm = $("#antibodyPeptideContainer").find("div[name='peptideMapDiv'][itemId='"+itemId+"']").find("form");
		if(peptideForm.find("[name='name']").val() == ""){
			alert("Please enter the Peptide Name.");
			return tmpFlag;
		}  
		var seqLength = peptideForm.find("[name='seqLength']").val();
		var sequence = peptideForm.find("[name='sequence']").val();
		if ((seqLength == undefined || seqLength == "" || seqLength == "0" || isNaN(seqLength) 
				|| parseFloat(seqLength) <=0) && (sequence == undefined || sequence == "")) {
			alert("Please enter the valid Peptide Sequence Length or Peptide Sequence.");
			return tmpFlag;
		}
		if (sequence != undefined && sequence != "") {
			var resultValidate = checkAntibodyPeptideSequenceValidateTrigger(peptideForm, sequence);
			if (resultValidate != true) {
				return tmpFlag;
			}
		}
		var dataTmp = peptideForm.formSerialize();
        if(!peptideForm.is_form_changed()) return true;
		dataStr = addFormNameToSerializeData("peptide", dataTmp ,"");
	}
	$.ajax({
		url:orderquoteObj.url("savePeptideForAntibody")+"&antibodyItemId="+antibodyItemId+"&itemId="+itemId,
		data:dataStr,
		dataType:"json",
		success:function(data){
			if (hasException(data)) {
				return tmpFlag;
			}
			var itemId = "";
			var itemInfo = [];
			for(var key in data){
				itemId = key;
				itemInfo = data[key];
				break;
			}
			//设置itemId
			refreshItem(itemInfo, itemId, "");
			//查看是否是已经存在的
			if($("#antibodyPeptideContainer div[itemId='"+itemId+"']").size() == 0){
				$("#antibodyPeptideContainer").append('<div name="peptideMapDiv" itemId="'+itemId+'">'+$("#peptideExampleDiv").html()+"</div>");
				var lastDiv = $("#antibodyPeptideContainer").find("div[name='peptideMapDiv'][itemId='"+itemId+"']");
				var peptideSize = $("#antibodyPeptideContainer").find("div[name='peptideMapDiv']").size();
				lastDiv.find("#peptideSeqTitleSpan0").html("Peptide #"+peptideSize+"-"+antigenType+":");
				lastDiv.find("[name='sequence']").attr("id", itemId+"_sequence");
				lastDiv.find("[name='modification']").attr("id", itemId+"_modification");
				lastDiv.find("#peptideSeqTitleSpan0").parent().trigger("click");
				lastDiv.find("[name='quantity']").find("option[value='"+itemInfo.peptide.quantity+"']").attr("selected", true);
				lastDiv.find("[name='purity']").find("option[value='"+itemInfo.peptide.purity+"']").attr("selected", true);
				lastDiv.find("[name='comments']").val(itemInfo.peptide.comments);
				lastDiv.find("#peptideSeqTitleSpan0").parent().click();
			}
			tmpFlag = true;
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false
	});
	return tmpFlag;
}

function saveStdPeptide(stdPeptideFlag){
	var tmpFlag = false;
	var stdPeptideForm = $("#stdPeptideForm");
	var tmpItemId= $("#stdPeptideForm").find("[name='itemId']").val();
	var modification = $("#stdPeptideForm").find("[id='modifications1']").val();
	var dataStr = addFormNameToSerializeData("peptide", $(stdPeptideForm).formSerialize(), "itemId");
	var dataS = dataStr.replaceM(modification);
	if(checkStdPeptide() == false){
		return false;
	}
    if(!stdPeptideForm.is_form_changed()) return true;
	$.ajax({
		url:orderquoteObj.url("saveStdPeptide")+"&itemId="+itemId,
		data: dataS,
		dataType:"json",
		success:function(data){
			var itemId = "";
			var itemInfo = [];
			for(var key in data){
				itemId = key;
				itemInfo = data[key];
				break;
			}
			$("#stdPeptideForm").find("[name='itemId']").val(itemId);
			refreshItem(itemInfo, itemId, "");
			$("#stdPeptideForm").refresh_changed_form();
			reSetItemNo(parent.$("#itemTable"));
			tmpFlag = true;
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false
	});
	return tmpFlag;
}

//Save batch peptide
function saveBatchPeptide(){
    $.funkyUI({
            topWin : window.parent,
			showDialog:false
		});
	if($('#batchPeptideForm').valid() == false){
        $.unfunkyUI({topWin : window.parent});
		return false;
	}
	var tmpFlag = false;
	var batchPeptideForm = $("#batchPeptideForm");
	var tmpItemId= $("#batchPeptideForm").find("[name='itemId']").val();
	var modification = $("#batchPeptideForm").find("[id='modifications2']").val();
	var seqs = $("#batchPeptideForm").find("[name='sequence']").val();
	var dataStr = addFormNameToSerializeData("peptide", $(batchPeptideForm).formSerialize(), "itemId");
	var dataS = dataStr.replaceM(modification);
	$.ajax({
		url:orderquoteObj.url("saveBatchPeptide")+"&itemId="+itemId,
		data: dataS,
		dataType:"json",
		success:function(data){
            //modify by zhanghuibin
             refreshItemList();
		},
		error: function(data){
			if(data.responseText){
				alert(data.responseText);
			}else{
				alert("System error! Please contact system administrator for help.");
			}
			$.unfunkyUI({topWin : window.parent});
		},
		type:"POST",
		async:false
	});
    //$.unfunkyUI({topWin : window.parent});
	return tmpFlag;
}

//Save batch oligo
function saveBatchOligo(batchBackbone,batchSynthesisScales,batchPurification, batchOligoSequence,
		batchModification5,batchModification3, batchAliquotingInto, batchAliquotingSize) {
	var form = $("#oligoButchOligosForm");
	if (batchBackbone.indexOf("RNA") >= 0 && batchOligoSequence != "") {
		if (!isRNASequence(batchOligoSequence)) {
			alert("Please enter the correct Sequence");
			form.find("[id='batchOligoSequence']").focus();
			return false;
		}
	} else if (batchBackbone.indexOf("DNA") >= 0 && batchOligoSequence != "") {
		if (!isDNASequence(batchOligoSequence)) {
			alert("Please enter the correct Sequence");
			form.find("[id='batchOligoSequence']").focus();
			return false;
		}
	} 
	//var tmpId = $("#oligoButchOligosForm").find("[name='itemId']").val();
	var refType = getRefType("oligo", 0);
    //add by zhanghuibin
    if(!createQuoteItem(batchBackbone)){
        return false;
    }
	var dataStr = orderquoteObj.url("uploadServiceFile")+"&itemId="+itemIds
		+ "&serviceName=oligo&refType="+refType+"&batchBackbone="+batchBackbone + "&batchSynthesisScales="
		+ batchSynthesisScales+"&batchPurification=" + batchPurification+"&batchOligoSequence="
		+ batchOligoSequence+ "&batchModification5=" + batchModification5+"&batchModification3="
		+ batchModification3+"&batchAliquotingInto="+batchAliquotingInto + "&batchAliquotingSize="+batchAliquotingSize;
	// ajax form post
	var options = {
		url:dataStr,
		type:"post",
		dataType:"json",
		resetForm:false,
		async:false,
		success:function(data){
			var itemId = "";
			var itemInfo = [];
			for(var key in data){
				if (!data[key]) {
					alert("key="+key);
				}
				itemId = key;
				itemInfo = data[key];
				refreshItem(itemInfo, itemId, false);
				$("#oligoDetailForm").refresh_changed_form();
				reSetItemNo(parent.$("#itemTable"));
			}
			alert("Batch Oligo Successful.");
			//刷新item list
			parent.document.getElementById("itemListIframe").src = orderquoteObj.url("getItemList");
		},
		error:function(data){
			if(data.responseText){
				alert(data.responseText);
			}else{
				alert("System error! Please contact system administrator for help.");
			}
		}
	};
	form.ajaxForm(options);
	form.submit();
}

//add by zhanghuibin
function createQuoteItem(batchBackbone){
    var flag = true;
    var catalogNo = getCatalogNo(batchBackbone);
    //创建sessionQuoteNo 以及 itemId
    $.ajax({
		url:orderquoteObj.url("saveBatchSeesionItem"),
        type:"get",
        dataType:"json",
		data:"custNo=" + parent.orderquoteObj.custNo + "&itemNum=1&catalogNo=" + catalogNo,
		success:function(data){
			itemIds = data.itemNOs;
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
            flag=false;
		},
		async:false
	});
    return flag;
}

function copyItem () {
	if (saveMoreDetail(show) == false) {
		return false;
	}
	var form = $("#tmplForm");
	var copyItemId = form.find("[name='itemId']").val();
	// ajax form post
	$.ajax({
		url:orderquoteObj.url("copyItem"),
        type:"post",
        dataType:"json",
		data:"&itemId="+copyItemId,
		success:function(data){
			if (hasException(data)) {
				return tmpFlag;
			}
			parent.document.getElementById("itemListIframe").contentWindow.location.href = orderquoteObj.url("getItemList");
			var itemId = "";
			var itemInfo = [];
			for(var key in data){
				itemId = key;
				itemInfo = data[key];
				form.find("[name='itemId']").val(itemId);
				refreshItem(itemInfo, itemId, false);
				form.refresh_changed_form();
				reSetItemNo(parent.$("#itemTable"));
			}
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		async:false
	});
}

//添加多行Orf Clone Info到More Detail页面中
function copyOrfCloneInfo (accessionInfoObj) {
	var accessionInfo = accessionInfoObj.value;
	if (accessionInfo == undefined || accessionInfo == "") {
		return;
	}
	var accessionInfoArr = accessionInfo.split("@@@");
	var orfClonePage = this.parent.$("#moreDetailIframe").contents();
	var table1 = orfClonePage.find("#orfCloneTable");
	var copy_orf_tr = orfClonePage.find("#copy_orf_tr");
	copy_orf_tr.find("[name='service_check_orfclone']").val(accessionInfo);
	copy_orf_tr.find("[name='accessionTd']").html(accessionInfoArr[0].split(":")[1]);
	copy_orf_tr.find("[name='sequenceTypeTd']").html(accessionInfoArr[1].split(":")[1]);
	copy_orf_tr.find("[name='priceTd']").html(accessionInfoArr[2].split(":")[1]);
	copy_orf_tr.find("[name='display_sub_price']").html("$0.00");
	table1.append(copy_orf_tr.html());
	table1.find("[name='serviceAllCheck']").attr("checked", true);
}

function  orf_detail_tgtResistance_change (tgtResistance) {
	if($(tgtResistance).val() == "Other"){
		$(tgtResistance).parent().find("#tgtResistanceOther").css("display", "block"); 
	}else{
		$(tgtResistance).parent().find("#tgtResistanceOther").css("display", "none"); 
	}
}

//ORF Clone服务Subcloning into Other Vector切换vector
function orf_detail_vector_nameChange(orf_detail_vector_name_select) {
	var orf_detail_vector_name = $(orf_detail_vector_name_select).val();
	var orfCloneTable = $(orf_detail_vector_name_select).parent().parent();
	var accessionInfo = orfCloneTable.find("[name='service_check_orfclone'][checked]").val();
	var accessionInfoArr = accessionInfo.split("@@@");
	//accessionInfoArr[0]:accession;accessionInfoArr[1]:seqType;accessionInfoArr[2]:genePrice
	//accessionInfoArr[3]:subcloningPrice;accessionInfoArr[4]:geneCost;accessionInfoArr[5]:subcloningCost
	//accessionInfoArr[6]:realGenePrice;accessionInfoArr[7]:realSubCloningPrice;accessionInfoArr[8]:realGeneCost
	//accessionInfoArr[9]:realSubcloningCost;
	orfCloneTable.find("[name='priceTd']").html(parseFloat(accessionInfoArr[6].split(":")[1]).toFixed(2));
	if (orf_detail_vector_name == "pUC57") {
		orfCloneTable.find("[name='display_sub_price']").html("$0.00");
		orfCloneTable.find("[name='other_vector']").css("display","none"); 
	} else if (orf_detail_vector_name == "Other") {
		orfCloneTable.find("[name='display_sub_price']").html("TBD");
		orfCloneTable.find("[name='other_vector']").attr("disabled",false);
		orfCloneTable.find("[name='other_vector']").css("display","block");
	} else {
		orfCloneTable.find("[name='display_sub_price']").html(parseFloat(accessionInfoArr[7].split(":")[1]).toFixed(2));
		orfCloneTable.find("[name='other_vector']").css("display","none"); 
	}
	var accessionInfo_new = accessionInfoArr[0]+"@@@"+accessionInfoArr[1]
		+"@@@"+accessionInfoArr[2].split(":")[0]+":"+accessionInfoArr[6].split(":")[1]
		+"@@@"+accessionInfoArr[3].split(":")[0]+":"+accessionInfoArr[7].split(":")[1]
		+"@@@"+accessionInfoArr[4].split(":")[0]+":"+accessionInfoArr[8].split(":")[1]
		+"@@@"+accessionInfoArr[5].split(":")[0]+":"+accessionInfoArr[9].split(":")[1]
		+"@@@"+accessionInfoArr[6]+"@@@"+accessionInfoArr[7]
		+"@@@"+accessionInfoArr[8]+"@@@"+accessionInfoArr[9];
	orfCloneTable.find("[name='service_check_orfclone'][checked]").val(accessionInfo_new);
	//Cloning Site
	var clonesite = $(orf_detail_vector_name_select).find("option:selected").attr("clonesite");
	if (clonesite != undefined && clonesite != "") {
		orfCloneTable.find("#tgtCloningSite option").remove();
		orfCloneTable.find("#tgtCloningSite3 option").remove();
		orfCloneTable.find("#tgtCloningSite").append("<option value=''></option>");
		orfCloneTable.find("#tgtCloningSite3").append("<option value=''></option>");
		var cloneSiteArr = clonesite.split(", ");
		var cloneSiteStr = "";
		for (var i in cloneSiteArr) {
			cloneSiteStr += "<option value='"+cloneSiteArr[i]+"'>"+cloneSiteArr[i]+"</option>";
		}
		orfCloneTable.find("#tgtCloningSite").append(cloneSiteStr);
		orfCloneTable.find("#tgtCloningSite3").append(cloneSiteStr);
	}
}

//add by zhanghuibin
function getCatalogNo(batchBackbone){
    if("DNA" == batchBackbone){
        return "SC1516";
    }else if("Phosphorothioated DNA" == batchBackbone){
        return "SC1517";
    }else if("RNA" == batchBackbone){
        return "SC1518";
    }else if("Phosphorothioated RNA" == batchBackbone){
       return "SC1519";
    }else if("2@-OMe-RNA" == batchBackbone){
        return "SC1527";
    }else if("Phosphorothioated 2@-OMe-RNA" == batchBackbone){
        return "SC1528";
    }
}

function refreshItem(itemInfo, itemId, removeFlag){
	if(itemId){
		var parentId = itemInfo.parentId;
		if (itemInfo.preParentId != undefined && itemInfo.preParentId != "") {
			parentId = itemInfo.preParentId;
		}
		var itemTrs = parent.$("#itemListIframe").contents().find("#itemTable tr");
		if(removeFlag){
			itemTrs.each(function(i, n){
				if($(this).attr("itemId") == itemId){
					$(this).remove();
				}
			});
			parent.window.frames["itemListIframe"].setArrowImage();
			parent.window.frames["itemListIframe"].reSetItemNo();
			return;
		}
		var findFlag = 0;
		itemTrs.each(function(i, n){
			if($(this).attr("itemId") == itemId){
				findFlag = 1;
				updateItemHtmlData( $(this), itemId, itemInfo);
			}
		});
		//
		if(findFlag == 0){
			itemTrs.each(function(i, n){
				if($(this).attr("itemId") == parentId){
					findFlag = 1;
					$(this).after(getItemHtml(itemId));
					updateItemHtmlData( $(this).next(), itemId, itemInfo);
				}
			});
		}
		if(findFlag == 0){
			itemTrs.each(function(i, n){
				if($(this).attr("itemId") == parentId){
					findFlag = 1;
					$(this).after(getItemHtml(itemId));
					updateItemHtmlData( $(this).next(), itemId, itemInfo);
				}
			});
		}
		if(findFlag == 0){
			findFlag = 1;
			var lastTr = parent.$("#itemListIframe").contents().find("#itemTable tr:last");
			$(lastTr).after(getItemHtml(itemId));
			updateItemHtmlData( $(lastTr).next(), itemId, itemInfo);
	}
		parent.window.frames["itemListIframe"].setArrowImage();
		parent.window.frames["itemListIframe"].reSetItemNo();
		if(findFlag == 0){
			alert("Failed to add the new item.");
		}
	}
}

function removeItems(itemIdStr){
	if(itemIdStr == ""){
		return;
	}
	var ids = itemIdStr.split(",");
	var itemTrs = parent.$("#itemListIframe").contents().find("#itemTable tr");
	
	itemTrs.each(function(i, n){
		var tmpId = $(this).attr("itemId");
		for(var i=0; i<ids.length;i++){
			if( tmpId== ids[i]){
				$(this).remove();
				break;
			}
		}
	});
	if(ids.length > 0){
		parent.window.frames["itemListIframe"].setArrowImage();
		parent.window.frames["itemListIframe"].reSetItemNo();
	}
}

function getItemHtml(itemId){
	var tmpStr = "";
	tmpStr += '<tr itemId="'+itemId+'">' ;
    tmpStr += '<td width="65" >' ;
    tmpStr += '	<input type="checkbox" value="'+itemId+'" name="itemId"/>';
    tmpStr += '<input id="amount" name="amount" type="hidden" value="">' ;
    tmpStr += '<input id="unitPrice" name="unitPrice" type="hidden" value="">';
	tmpStr += '<input id="upSymbol" name="upSymbol" type="hidden" value="">' ;
    tmpStr += '<input id="discount" name="discount" type="hidden" value="">';
	tmpStr += '<input id="tax" name="tax" type="hidden" value="">' ;
	tmpStr += '<input id="type" name="type" type="hidden" value="">' ;
	tmpStr += '<input id="parentId" name="parentId" type="hidden" value="">' ;
    tmpStr += '</td>' ;
    tmpStr += '<td width="60"  align="center"><div name="tdItemNo" id="tdItemNo">' ;
    tmpStr += '</div></td>' ;
    tmpStr += '<td width="60"  align="center"><div name="tdCatalogNo" id="tdCatalogNo"></div><input id="catalogNoTd" name="catalogNoTd" type="hidden" value=""></td>' ;
    tmpStr += '<td width="250" ><div name="tdNameShort" id="tdNameShort" title=""></div></td>' ;
    tmpStr += '<td width="40"  align="center">' ;
    tmpStr += '<div name="changeStatus" id="changeStatus"></div>' ;
    tmpStr += '</td>' ;
    tmpStr += '<td width="45"  align="center">' ;
    tmpStr += '<div style="width:100%" id="qtyTd" name="qtyTd"></div>' ;
    tmpStr += '</td>' ;
    tmpStr += '<td width="40" >' ;
    tmpStr += '<div align="center" name="tdQtyUom" id="tdQtyUom">&nbsp;</div>' ;
    tmpStr += '</td>' ;
    tmpStr += '<td width="60" align="center">' ;
    tmpStr += '<div name="tdSizeQtyUom" id="tdSizeQtyUom">&nbsp;</div>' ;
    tmpStr += '  </td>' ;
    tmpStr += '<td width="60" align="right">' ;
    tmpStr += '<div name="tdSymbolAmount" id="tdSymbolAmount">&nbsp;' ;
    tmpStr += '</div></td>' ;
    tmpStr += '<td width="60" align="right">' ;
    tmpStr += '<div name="tdCost" id="tdCost">&nbsp;' ;
    tmpStr += '</div></td>' ;
    tmpStr += '<td width="60" align="right">' ;
    tmpStr += '<div name="tdSymbolUnitPrice" id="tdSymbolUnitPrice">&nbsp;</div>' ;
    tmpStr += '</td>' ;
    tmpStr += '<td width="60"  align="right">' ;
    tmpStr += '<div name="tdDiscount" id="tdDiscount">&nbsp;</div>' ;
    tmpStr += '</td>' ;
    tmpStr += '<td align="right">' ;
    tmpStr += '<div name="tdTax" id="tdTax" >&nbsp;</div>' ;
    tmpStr += '</td>' ;
    tmpStr += '</tr>' ;
    return tmpStr;
}

function updateItemHtmlData(trObj, itemId, itemInfo){
	var precision = 2;
	if(itemInfo != undefined && "JPY" == itemInfo.currencyCode){
		precision = 0;
	}
	trObj.find("[name='amount']").val(itemInfo.amount);
	trObj.find("[name='unitPrice']").val(itemInfo.unitPrice);
	trObj.find("[name='upSymbol']").val(itemInfo.upSymbol);
	trObj.find("[name='discount']").val(itemInfo.discount);
	trObj.find("[name='tax']").val(itemInfo.tax);
	trObj.find("[name='tdItemNo']").html(itemInfo.itemNo);
	var catalogNoShort = "";
	if(itemInfo != undefined && itemInfo.catalogNo != undefined && itemInfo.catalogNo.length > 9){
		catalogNoShort = itemInfo.catalogNo.substr(0, 9)+"...";
	}else{
		catalogNoShort = itemInfo.catalogNo; 
	}
	trObj.find("[name='tdCatalogNo']").html(catalogNoShort);
	trObj.find("[name='catalogNoTd']").val(itemInfo.catalogNo);
	var tmpPreImg = trObj.find("[name='tdNameShort']").find("img");
	var nameShort = "";
	if(itemInfo != undefined && itemInfo.name != undefined && itemInfo.name.length > 40){
		nameShort = itemInfo.nameShow.substr(0, 40)+"...";
	}else{
		nameShort = itemInfo.nameShow;
	}
	trObj.find("[name='tdNameShort']").html(nameShort);
	trObj.find("[name='tdNameShort']").prepend(tmpPreImg);
	trObj.find("[name='changeStatus']").html(itemInfo.status);
	trObj.find("[name='qtyTd']").html(itemInfo.quantity);
	trObj.find("[name='tdQtyUom']").html(itemInfo.qtyUom);
	trObj.find("[name='type']").val(itemInfo.type);
	trObj.find("[name='parentId']").val(itemInfo.parentId);
	
	trObj.find("[name='tdSizeQtyUom']").html(itemInfo.size);
	if(itemInfo.sizeUom){
		trObj.find("[name='tdSizeQtyUom']").append(itemInfo.sizeUom);
	}
	if(itemInfo != undefined && itemInfo.amount != undefined && parseFloat(itemInfo.amount) < 0){
		trObj.find("[name='tdSymbolAmount']").html("TBD");
	}else{
		trObj.find("[name='tdSymbolAmount']").html(itemInfo.amount.toFixed(precision));
	}
	if(itemInfo != undefined && itemInfo.unitPrice != undefined && parseFloat(itemInfo.unitPrice) < 0){
		trObj.find("[name='tdSymbolUnitPrice']").html("TBD");
	}else{
		trObj.find("[name='tdSymbolUnitPrice']").html(itemInfo.unitPrice.toFixed(precision));
	}
	trObj.find("[name='tdDiscount']").html(itemInfo.discount.toFixed(precision));
	if(itemInfo != undefined && itemInfo.cost != undefined && parseFloat(itemInfo.cost) < 0){
		trObj.find("[name='tdCost']").html("TBD");
	}else{
		trObj.find("[name='tdCost']").html(itemInfo.cost.toFixed(precision));
	}
	trObj.find("[name='tdTax']").html(itemInfo.tax.toFixed(precision));
	if(itemInfo != undefined && itemInfo.unitPrice != undefined && itemInfo.upSymbol && parseFloat(itemInfo.unitPrice) > 0 ){
		trObj.find("[name='tdSymbolAmount']").prepend(itemInfo.upSymbol);
		trObj.find("[name='tdSymbolUnitPrice']").prepend(itemInfo.upSymbol);
	}
	if(itemInfo != undefined && itemInfo.cost != undefined && itemInfo.upSymbol && parseFloat(itemInfo.cost) > 0 ){
		trObj.find("[name='tdCost']").prepend(itemInfo.upSymbol);
	}
	if(itemInfo.upSymbol){
		trObj.find("[name='tdDiscount']").prepend(itemInfo.upSymbol);
		trObj.find("[name='tdTax']").prepend(itemInfo.upSymbol);
	}
	if(itemInfo.tbdFlag && itemInfo.tbdFlag == 1){
		trObj.find("[name='tdSymbolAmount']").html("TBD");
		trObj.find("[name='tdSymbolUnitPrice']").html("TBD");
		trObj.find("[name='tdCost']").html("TBD");
	}
}

function reSetItemNo( itemTableObj ){
	var itemNo = 1 ;
	itemTableObj.find( "tr" ).each(
		function(){
			$(this).find('#tdItemNo').html( itemNo++ ) ;
		}
	);
}

//上传附件
function uploadServiceFile(itemId, serviceName, formId, refType, btnObj){
	if(!$(btnObj).parent().parent().find(":file").val() ){
		alert("Please select one file.");
		return;
	}
	var form = $("#"+formId);
	var tmpId = itemId;
	//ajax form post
	var options = {
		success:function(data){
			var documentList = data;
			var tmpStr = getFileListHtml(documentList);
			$(form).find("#fileListTable").append(tmpStr);
			$(form).find(":file").val("");
		},
		error: function(data){
			if(data.responseText){
				alert(data.responseText);
			}else{
				alert("System error! Please contact system administrator for help.");
			}
		},
		dataType:"json",
		resetForm:false,
		url:orderquoteObj.url("uploadServiceFile")+"&itemId="+tmpId+"&serviceName="+serviceName+"&refType="+refType,
		type:"POST"
	};
	form.ajaxForm(options);
	form.submit();
}

function check_peptide_form(seqFileName, seqInputName) {
	var seq_file_1 = document.getElementById(seqFileName);
	var seqs1 = document.getElementById(seqInputName).value.replace(/^\s+|\s+$/g, '');

	if (!seq_file_1.value && !seqs1 ) {
		alert('Please upload peptide sequence file or paste the sequences!');
		return false;
	}
	if (seq_file_1 && seq_file_1.value && !/\.(xls|xlsx|txt|csv)$/i.exec(seq_file_1.value)) {
		alert('Wrong file type for Crude Peptide Library sequence file!');
		return false;
	}
	return true;
}

function saveLibraryPeptide(itemId, serviceName, formId, refType){
    $.funkyUI({
        topWin : window.parent,
        showDialog:false
    });
	if(!check_peptide_form('libPeptideFile', 'libSequence')){
        $.unfunkyUI({topWin : window.parent});
        return false;
    }
	if(!$('#libraryPeptideForm').valid()){
        $.unfunkyUI({topWin : window.parent});
		return false;
	}
//	if($("input[type='radio'][name='peptide.catalogNo']:checked").length == 0){
//		alert("Please select the Catalog No.");
//		return;
//	}
	var form = $("#"+formId);
	var tmpId = itemId;
	// ajax form post
	var options = {
		url:orderquoteObj.url("uploadServiceFile")+"&itemId="+tmpId+"&serviceName="+serviceName+"&refType="+refType,
		type:"post",
		dataType:"json",
		resetForm:false,
		async:false,
		success:function(data){
		refreshItemList();
		},
		error:function(data){
			if(data.responseText){
				alert(data.responseText);
			}else{
				alert("System error! Please contact system administrator for help.");
                $.unfunkyUI({topWin : window.parent});
			}
			$.unfunkyUI({topWin : window.parent});
		}
	};
    //$.unfunkyUI({topWin : window.parent});
	form.ajaxForm(options);
	form.submit();
}

function emptyPeptide(itemId){
	$.ajax({
		url:orderquoteObj.url("returnItemMap")+"&itemId="+itemId,
		dataType:"json",
		success:function(data){
			var itemId = "";
			var itemInfo = [];
			for(var key in data){
				itemId = key;
				itemInfo = data[key];
				if(itemId){
				var parentId = itemInfo.parentId;
				var itemTrs = parent.$("#itemListIframe").contents().find("#itemTable tr");
					itemTrs.each(function(i, n){
						//alert($(this).attr("itemId"));
						if($(this).attr("itemId") == itemId){
							$(this).remove();
						}
					});
//					parent.window.frames["itemListIframe"].setArrowImage();
//					parent.window.frames["itemListIframe"].reSetItemNo();
			}
			}
		},
		error: function(data){
			if(data.responseText){
				alert(data.responseText);
			}else{
				alert("System error! Please contact system administrator for help.");
			}
		},
		type:"POST",
		async:false
	});
}

function updateLibraryPeptide(itemId, serviceName, formId, refType){
	if(!check_peptide_form('libPeptideFile', 'libSequence'))
		return;
//	if($("input[type='radio'][name='peptide.catalogNo']:checked").length == 0){
//		alert("Please select the Catalog No.");
//		return;
//	}
//	if(itemId){
//		var parentId = itemInfo.parentId;
//		var itemTrs = parent.$("#itemListIframe").contents().find("#itemTable tr");
//		if(removeFlag){
//			itemTrs.each(function(i, n){
//				if($(this).attr("itemId") == parentId){
//					$(this).remove();
//				}
//			});
//			parent.window.frames["itemListIframe"].setArrowImage();
//			parent.window.frames["itemListIframe"].reSetItemNo();
//			return;
//		}
//	}
	
	var form = $("#"+formId);
	var tmpId = itemId;
	// ajax form post
	var options = {
		url:orderquoteObj.url("uploadServiceFile")+"&itemId="+tmpId+"&serviceName=updateLibraryPeptide&refType="+refType,
		type:"post",
		dataType:"json",
		resetForm:false,
		async:false,
		success:function(data){
		var itemId = "";
		var itemInfo = [];
		for(var key in data){
			itemId = key;
			itemInfo = data[key];
			
			$("#libraryPeptideForm").find("[name='itemId']").val(itemId);
			refreshItem(itemInfo, itemId, false);
			$("#libraryPeptideForm").refresh_changed_form();
			reSetItemNo(parent.$("#itemTable"));
		}
		},
		error:function(data){
			if(data.responseText){
				alert(data.responseText);
			}else{
				alert("System error! Please contact system administrator for help.");
			}
		}
	};
	form.ajaxForm(options);
	form.submit();
}

function saveArrayPeptide(itemId, serviceName, formId, refType){
    $.funkyUI({
        topWin : window.parent,
        showDialog:false
    });
	if(!check_peptide_form('arrayPeptideFile', 'arraySequence')){
        $.unfunkyUI({topWin : window.parent});
        return ;
    }
	if($('#arrayPeptideForm').valid() == false){
        $.unfunkyUI({topWin : window.parent});
		return false;
	}
	var form = $("#"+formId);
	var tmpId = itemId;
	// ajax form post
	var options = {
		url:orderquoteObj.url("uploadServiceFile")+"&itemId="+tmpId+"&serviceName="+serviceName+"&refType="+refType,
		type:"post",
		dataType:"json",
		resetForm:false,
		async:false,
		success:function(data){
		refreshItemList();
		},
		error:function(data){
			if(data.responseText){
				alert(data.responseText);
			}else{
				alert("System error! Please contact system administrator for help.");
			}
            $.unfunkyUI({topWin : window.parent});
		}
	};
	form.ajaxForm(options);
	form.submit();
}

function updateArrayPeptide(itemId, serviceName, formId, refType){
	if(!check_peptide_form('arrayPeptideFile', 'arraySequence'))
		return;
	var form = $("#"+formId);
	var tmpId = itemId;
	// ajax form post
	var options = {
		url:orderquoteObj.url("uploadServiceFile")+"&itemId="+tmpId+"&serviceName=updateArrayPeptide&refType="+refType,
		type:"post",
		dataType:"json",
		resetForm:false,
		async:false,
		success:function(data){
		var itemId = "";
		var itemInfo = [];
		for(var key in data){
			itemId = key;
			itemInfo = data[key];
			$("#arrayPeptideForm").find("[name='itemId']").val(itemId);
			refreshItem(itemInfo, itemId, false);
			$("#arrayPeptideForm").refresh_changed_form();
			reSetItemNo(parent.$("#itemTable"));
		}
		},
		error:function(data){
			if(data.responseText){
				alert(data.responseText);
			}else{
				alert("System error! Please contact system administrator for help.");
			}
		}
	};
	form.ajaxForm(options);
	form.submit();
}

//删除附件
function deleteServiceFile(delBtnObj, filePath, serviceName, itemId){
	var tmpId = itemId;
	$.ajax({
		url:orderquoteObj.url("delServiceFile")+"&delFilePath="+filePath+"&serviceName="+serviceName+"&itemId="+tmpId,
		type:"post",
		success:function(data){
			if(data == "SUCCESS"){
				$(delBtnObj).parent().parent().remove();
			}else{
				if(data){
					alert(data);
				}else{
					alert("System error! Please contact system administrator for help.");
				}
			}
		},
		error:function(data){
			alert(data);
		}
	});
}

//获取附件列表html字符串
function getFileListHtml(documentList){
	var tmpStr = "";
	for(var o in documentList){
		var tmpDoc = documentList[o];
		tmpStr += '<tr>';
		tmpStr += '<td>';
		tmpStr += '<a target="_blank" onclick="fileDownLoad(\''+tmpDoc["filePath"]+'\',\''+tmpDoc["docName"]+'\');" style="cursor:hand">'+tmpDoc.docName+'</a>';
		tmpStr += '</td>';
		tmpStr += '<td><input type="button" filePath="'+tmpDoc.filePath+'" value="Delete" class="style_botton" /></td>';
		tmpStr += '</tr>';
	}
	return tmpStr;
}

function fileDownLoad (filePath, fileName) {
	$("#downloadForm").attr("action","download.action");
	$("#myFileName").val(fileName);
	$("#myFilePath").val(filePath);
	$("#downloadForm").submit();
}

//***********************************************************************************************
//初始化页面数据
//***********************************************************************************************
function initDataGene(gene){
	if(! $("#geneSynthesisForm").attr("id")){
		alert("System error! Please contact system administrator for help.");
		
	}
	if(!gene){
		gene = [];
	}
	if(gene.cloningReadyFlag == 'Y'){
		$("#geneSynthesisForm").find("[name='sequence']").attr("readonly","readonly")
	}
	$("#geneSynthesisForm").find("[name='itemId']").val(gene.itemId);	
	$("#geneSynthesisForm").find("[name='geneName']").val(gene.geneName);
	$("#geneSynthesisForm").find("[name='sequence5']").val(gene.sequence5);
	$("#geneSynthesisForm").find("[name='sequence3']").val(gene.sequence3);
	$("#geneSynthesisForm").find("[name='sequence']").val(gene.sequence);
	$("#geneSynthesisForm").find("[name='cloningSite']").val(gene.cloningSite);
	$("#geneSynthesisForm").find("[name='bpPrice']").val(gene.bpPrice);
	$("#geneSynthesisForm").find("[name='bpCost']").val(gene.bpCost);
	$("#geneSynthesisForm").find("[id='seqLength']").val(gene.seqLength);
	$("#geneSynthesisForm").find("[name='sequenceType'][value='"+gene.sequenceType+"']").attr("checked", true);
	
	$("#geneSynthesisForm").find("[name='codeOptmzFlag'][value='"+gene.codeOptmzFlag+"']").attr("checked", true);
	$("#geneSynthesisForm").find("[name='cloningFlag'][value='"+gene.cloningFlag+"']").attr("checked", true);
	$("#geneSynthesisForm").find("[name='plasmidPrepFlag'][value='"+gene.plasmidPrepFlag+"']").attr("checked", true);
	initGeneUsage(gene);
	initMoreInfo(gene);
	var tmpStr = "";
	if(gene.documentList){
		tmpStr = getFileListHtml(gene.documentList);
	}
	$("#moreInfoForm").find("[name='fileListTable']").html(tmpStr);
	
	if(gene.codeOptmzFlag == "N" || !gene.codeOptmzFlag){
		disableTabByTitle("Codon Optimization");
//		codonForm
	}else{
		//$("#codonForm").find("[name='hostExpsOrganism']").find("option[value='"+gene.hostExpsOrganism+"']").attr("selected", true);
		if (gene.hostExpsOrganism != undefined && gene.hostExpsOrganism.indexOf("Other") >=0) {
			$("#codonForm").find("[name='hostExpsOrganism']").find("option[value='Other']").attr("selected", true);
			document.getElementById("hostExpsOrgOther").style.display= "block"; 
			$("#codonForm").find("[id='hostExpsOrgOther']").val(gene.hostExpsOrganism.substring(6, gene.hostExpsOrganism.length));
		} else {
			$("#codonForm").find("[name='hostExpsOrganism']").find("option[value='"+gene.hostExpsOrganism+"']").attr("selected", true);
		}
		if (gene.scndExpsOrganism != undefined && gene.scndExpsOrganism.indexOf("Other") >=0) {
			$("#codonForm").find("[name='scndExpsOrganism']").find("option[value='Other']").attr("selected", true);
			document.getElementById("scndExpsOrganismOther").style.display= "block"; 
			$("#codonForm").find("[id='scndExpsOrganismOther']").val(gene.scndExpsOrganism.substring(6, gene.scndExpsOrganism.length));
		} else {
			$("#codonForm").find("[name='scndExpsOrganism']").find("option[value='"+gene.scndExpsOrganism+"']").attr("selected", true);
		}
		$("#codonForm").find("[name='scndExpsOrganism']").find("option[value='"+gene.scndExpsOrganism+"']").attr("selected", true);
		$("#codonForm").find("[name='optimizationStart']").val(gene.optimizationStart);
		$("#codonForm").find("[name='opStartPosUom']").find("option[value='"+gene.opStartPosUom+"']").attr("selected", true);
		$("#codonForm").find("[name='optimizationEnd']").val(gene.optimizationEnd);
		$("#codonForm").find("[name='opEndPosUom']").find("option[value='"+gene.opEndPosUom+"']").attr("selected", true);
		$("#codonForm").find("[name='orfStart']").val(gene.orfStart);
		$("#codonForm").find("[name='orfStartUom']").find("option[value='"+gene.orfStartUom+"']").attr("selected", true);
		$("#codonForm").find("[name='orfEnd']").val(gene.orfEnd);
		$("#codonForm").find("[name='orfEndUom']").find("option[value='"+gene.orfEndUom+"']").attr("selected", true);
		$("#codonForm").find("[name='rstSitesAvoid']").val(gene.rstSitesAvoid);
		$("#codonForm").find("[name='rstSitesKeep']").val(gene.rstSitesKeep);
		$("#codonForm").find("[name='stopCodonFlag'][value='"+gene.stopCodonFlag+"']").attr("checked", true);
		$("#codonForm").find("[name='stopCodon']").val(gene.stopCodon);
		$("#codonForm").find("[name='comment']").val(gene.comment);
	}
	
	var geneStdVectorName = $("#geneSynthesisForm").find("[name='stdVectorName']");
	if(gene.cloningFlag == "N" || !gene.cloningFlag){
		disableTabByTitle("Cloning Strategy");
		if($("#geneSynthesisForm").attr("itemId")){
			geneStdVectorName.attr("value",gene.stdVectorName);
			$("#geneSynthesisForm").find("[name='cloningSite']").attr("value",gene.cloningSite);
		}
	} else {
		if($("#geneSynthesisForm").attr("itemId")){
			geneStdVectorName.attr("value",gene.stdVectorName);
			$("#geneSynthesisForm").find("[name='cloningSite']").attr("value",gene.cloningSite);
			geneStdVectorName.attr("disabled",true);
			$("#geneSynthesisForm").find("[name='cloningSite']").attr("disabled",true);
		}
	}
	if (geneStdVectorName.attr("value") == undefined || geneStdVectorName.attr("value") == "") {
		geneStdVectorName.attr("value","pUC57");
	}
	if(gene.plasmidPrepFlag == "N" || !gene.plasmidPrepFlag){
		disableTabByTitle("Plasmid Preparation");
	}
}

function initDataRna(rna){
	if(! $("#rnaForm").attr("id")){
		alert("Fail to fetch the Form ID");
	}
	if(!rna){
		rna = [];
	}
	$("#rnaForm").find("[name='itemId']").val(rna.itemId);	
	$("#rnaForm").find("[name='geneName']").val(rna.geneName);
	$("#rnaForm").find("[name='sequenceInsert']").val(rna.sequenceInsert);
	$("#rnaForm").find("[name='comments']").val(rna.comments);
	//$("#rnaForm").find("[name='vectorName'] option[value='"+rna.vectorName+"']").attr("selected", true);
    // modify by zhanghuibin
    if(rna.vectorName.split(":").length > 1){
        $("#rnaForm").find("[name='vectorName'] option[value='"+rna.vectorName.split(":")[0]+"']").attr("selected", true);
        $("#rnaForm").find("[id='vectorOther']").attr("value", rna.vectorName.split(":")[1]);
        vectorChange();
    }else{
        $("#rnaForm").find("[name='vectorName'] option[value='"+rna.vectorName+"']").attr("selected", true);
    }
	$("#rnaForm").find("[name='quantity'] option[value='"+rna.quantity+"']").attr("selected", true);
	
	$("#rnaForm").find("[name='plasmidPrepFlag'][value='"+rna.plasmidPrepFlag+"']").attr("checked", true);
	
	initGeneUsage(rna);
	$("#moreInfoForm").find("[name='otherDescription']").val(rna.otherDescription);
	$("#moreInfoForm").find("[name='otherRequirement']").val(rna.otherRequirement);
	var tmpStr = "";
	if(rna.documentList){
		tmpStr = getFileListHtml(rna.documentList);
	}
	$("#moreInfoForm").find("[name='fileListTable']").html(tmpStr);
	
	if(rna.plasmidPrepFlag == "N" || !rna.plasmidPrepFlag){
		disableTabByTitle("Plasmid Preparation");
	}
}

function initDataCloning(cloning){
	if(!cloning){
		cloning = [];
	}
	if(!cloning.cloningFlag){
		cloning.cloningFlag = "N";
	}
	if(!cloning.plasmidPrepFlag){ 
	
		cloning.plasmidPrepFlag = "N";
	}
	//Cloning Strategy
	initCloning(cloning);
	if(show == "cloning"){
		//Template Info
		initTmpl(cloning,"");
		
		//target construct
		initTarget(cloning);
		//geneUsage
		initGeneUsage(cloning);
		//more info
		initMoreInfo(cloning);
		//
		if(cloning.cloningFlag == "N" || !cloning.cloningFlag){
			disableTabByTitle("Cloning Strategy");
		}
		if(cloning.plasmidPrepFlag == "N" || !cloning.plasmidPrepFlag){
			disableTabByTitle("Plasmid Preparation");
		}
	}
}


function initPlasmidPreparation(plasmid, displayType){
	if(! $("#plasmidForm").attr("id")){
		return;
	}
	if(!plasmid){
		plasmid = [];
	}
	$("#plasmidForm").find("[name='itemId']").val(plasmid.itemId);
	if(displayType == 1){
		$("#plasmidForm").find("[name='plasmidSize']").val(plasmid.plasmidSize);
		$("#plasmidForm").find("[name='antibioticResistance']").find("option[value='"+plasmid.antibioticResistance+"']").attr("selected", true);
		$("#plasmidForm").find("[name='copyNumber'][value='"+plasmid.copyNumber+"']").attr("checked", true);
		$("#plasmidForm").find("[name='sequence']").val(plasmid.sequence);
	}else{
		$("#plasmidForm").find("[name='plasmidPreparation.plasmidName']").attr("disabled", true);
		$("#plasmidForm").find("[name='plasmidPreparation.plasmidSize']").attr("disabled", true);
		$("#plasmidForm").find("[name='plasmidPreparation.antibioticResistance']").attr("disabled", true);
		$("#plasmidForm").find("[name='plasmidPreparation.copyNumber']").attr("disabled", true);
		$("#plasmidForm").find("[name='plasmidPreparation.sequence']").attr("disabled", true);
		$("#plasmidForm").find("[name='upload']").attr("disabled", true);
		$("#plasmidForm").find("[name='plasmidUploadBtn']").attr("disabled", true);
		$("#plasmidForm").find("[id='antibioticResistance']").attr("disabled", true);
		$("#plasmidForm").find("[id='resistance']").attr("disabled", true);
		$("#plasmidForm").find("[id='restrictionAnalysis']").attr("disabled", true);
		$("#plasmidForm").find("tr[class='.sub_disabled']").hide();
	}
	$("#plasmidForm").find("[name='prepWeight']").find("option[value='"+plasmid.prepWeight+" "+plasmid.prepWtUom+"']").attr("selected", true);
	$("#plasmidForm").find("[name='plasmidPreparation.additionalAnalysis']").find("option[value='"+plasmid.additionalAnalysis+"']").attr("selected", true);
	$("#plasmidForm").find("[name='qualityGrade'][value='"+plasmid.qualityGrade+"']").attr("checked", true);
	$("#plasmidForm").find("[name='Standard'][value='"+plasmid.Standard+"']").attr("checked", true);
	//处理现实文件列表
	var refType = getRefType("plasmid", 1);
	var tmpStr = "";
	if(plasmid.documentList){
		var tmpList = [];
		for(var p1 in plasmid.documentList){
			if(plasmid.documentList[p1]["refType"] == refType){
				tmpList.push(plasmid.documentList[p1]);
			}
		}
		tmpStr = getFileListHtml(tmpList);
	}
	$("#plasmidForm").find("[name='fileListTable']").html(tmpStr);
	//More Info
	if(show == "plasmid"){
		initMoreInfo(plasmid);
	}
}

//Mutagenesis
function initMutagenesis(muta, displayType, parentId){
	if(! $("#tmplForm").attr("id")){
		return;
	}
	if(!muta){
		muta = [];
	}
	initTmpl(muta, parentId);
	if(!muta.cloningFlag){
		muta.cloningFlag = "N";
	}
	if(!muta.plasmidPrepFlag){
		muta.plasmidPrepFlag = "N";
	}
	//target construct
	initTarget(muta);
	//geneUsage
	initGeneUsage(muta);
	//more info
	initMoreInfo(muta);
	//
	if(muta.cloningFlag == "N" || !muta.cloningFlag){
		disableTabByTitle("Cloning Strategy");
	}
	if(muta.plasmidPrepFlag == "N" || !muta.plasmidPrepFlag){
		disableTabByTitle("Plasmid Preparation");
	}
}

//MutationLibraries
function initMutationLibraries(mutationLibraries){
	if(! $("#mutaLibForm").attr("id")){
		return;
	}
	if(!mutationLibraries){
		mutationLibraries = [];
	}
	$("#mutaLibForm").find("[name='itemId']").val(mutationLibraries.itemId);	
	$("#mutaLibForm").find("[name='constructName']").val(mutationLibraries.constructName);	
	$("#mutaLibForm").find("[name='libraryType'][value='"+mutationLibraries.libraryType+"']").attr("checked", true);
	$("#mutaLibForm").find("[name='tmplFlag'][value='"+mutationLibraries.tmplFlag+"']").attr("checked", true);
	$("#mutaLibForm").find("[name='tgtVectorName'][value='"+mutationLibraries.tgtVectorName+"']").attr("checked", true);
	$("#mutaLibForm").find("[name='interestSequence']").val(mutationLibraries.interestSequence);	
	$("#mutaLibForm").find("[name='degeneratedSites']").val(mutationLibraries.degeneratedSites);
	initTmpl(mutationLibraries, "");
	if (!mutationLibraries.tmplFlag || mutationLibraries.tmplFlag == "N") {
		disableTabByTitle("Template Info");
		$("#mutaLibForm").find("[name='tgtVectorName'][value='Target vector same as template']").attr("disabled", true);
	} else if (mutationLibraries.tmplFlag == "Y") {
		$("#mutaLibForm").find("[name='tgtVectorName'][value='Target vector same as template']").attr("disabled", false);
	}
	if(!mutationLibraries.cloningFlag){
		mutationLibraries.cloningFlag = "N";
	}
	if(!mutationLibraries.plasmidPrepFlag){
		mutationLibraries.plasmidPrepFlag = "N";
	}
	//geneUsage
	initGeneUsage(mutationLibraries);
	//more info
	initMoreInfo(mutationLibraries);
	//clone
	if(mutationLibraries.tgtVectorName != "Other" && mutationLibraries.tgtVectorName != "other"){
		disableTabByTitle("Cloning Strategy");
	} else if (mutationLibraries.tgtVectorName == "Other" && mutationLibraries.tgtVectorName == "other") {
		$("#mutaLibForm").find("[name='tgtVectorName'][value='"+mutationLibraries.tgtVectorName+"']").attr("checked", true);
	}
	//plasmid
	if(mutationLibraries.plasmidPrepFlag == "N" || !mutationLibraries.plasmidPrepFlag){
		disableTabByTitle("Plasmid Preparation");
	} else if (mutationLibraries.plasmidPrepFlag == "Y") {
		$("#mutaLibForm").find("[name='plasmidPrepFlag'][value='"+mutationLibraries.plasmidPrepFlag+"']").attr("checked", true);
	}
}

//initialize the data of template
function initTmpl(ser, parentId){
	if(! $("#tmplForm").attr("id")){
		return;
	}
	if (ser == "") {
		var tmplVector = $("#tmplForm").find("[name='tmplVector']").val();
		if (tmplVector != undefined) {
			$("#tmplForm").find("[name='tmplVector']").trigger("change");
		}
		return;
	}
	$("#tmplForm").find("[name='itemId']").val(ser.itemId);
	$("#tmplForm").find("[id='parentId']").val(parentId);
	$("#tmplForm").find("[name='tmplInsertName']").val(ser.tmplInsertName);
	$("#tmplForm").find("[name='tmplCloningMethod'][value='"+ser.tmplCloningMethod+"']").attr("checked", true);
	if (ser.tmplCloningMethod == "Gateway") {
		$("#tmplForm").find("[name='tmplCloningSite']").attr("disabled",true);
	} else if (ser.tmplCloningMethod == "Standard") {
		$("#tmplForm").find("[name='tmplCloningSite']").val(ser.tmplCloningSite);
		$("#tmplForm").find("[name='tmplCloningSite']").attr("disabled",false);
	} else {
		$("#tmplForm").find("[name='tmplCloningSite']").attr("disabled",false);
		$("#tmplForm").find("[name='tmplCloningMethod'][value='Standard']").attr("checked", true);
		$("#tmplForm").find("[name='tmplCloningMethod'][value='Gateway']").attr("checked", false);
	}
	$("#tmplForm").find("[name='tmplSequence']").val(ser.tmplSequence);
	$("#tmplForm").find("[name='tmplVector']").find("option[value='"+ser.tmplVector+"']").attr("selected", true);
	if (ser.tmplVector != undefined && ser.tmplVector.indexOf("Other") >=0) {
		var disFlag = false;
		$("#tmplForm").find("[name='tmplVector']").find("option[value='Other']").attr("selected", true);
		document.getElementById("tmplVectorOther").style.display= "block"; 
		$("#tmplForm").find("[id='tmplVectorOther']").val(ser.tmplVector.substring(6, ser.tmplVector.length));
		$("#tmplForm").find("[name='tmplSequence']").val(ser.tmplSequence);
		$("#tmplForm").find("[name='tmplVectorSize']").val(ser.tmplVectorSize);
		$("#tmplForm").find("[name='tmplVectorSeq']").val(ser.tmplVectorSeq);
		if (ser.tmplResistance != undefined && ser.tmplResistance.indexOf("Other") >=0) {
			$("#tmplForm").find("[name='tmplResistance']").find("option[value='Other']").attr("selected", true);
			document.getElementById("tmplResistanceOther").style.display= "block"; 
			$("#tmplForm").find("[id='tmplResistanceOther']").val(ser.tmplResistance.substring(6, ser.tmplResistance.length));
		} else {
			$("#tmplForm").find("[name='tmplResistance']").find("option[value='"+ser.tmplResistance+"']").attr("selected", true);
		}
		$("#tmplForm").find("[name='tmplCopyNo'][value='"+ser.tmplCopyNo+"']").attr("checked", true);
		//处理现实文件列表
		var refType = getRefType(show, 1);
		var tmpStr = "";
		if(ser.documentList){
			var tmpList = [];
			for(var m1 in ser.documentList){
				if(ser.documentList[m1]["refType"] == refType){
					tmpList.push(ser.documentList[m1]);
				}
			}
			tmpStr = getFileListHtml(tmpList);
		}
		$("#tmplForm").find("[name='fileListTable']").html(tmpStr);
	} else {
		var disFlag = true;
		$("#tmplForm").find("[name='tmplVector']").find("option[value='"+ser.tmplVector+"']").attr("selected", true);
	}
	$("#tmplForm").find("[name='tmplResistance']").attr("disabled", disFlag);
	$("#tmplForm").find("[name='tmplVectorSize']").attr("disabled", disFlag);
	$("#tmplForm").find("[name='tmplCopyNo']").attr("disabled", disFlag);
	$("#tmplForm").find("[name='tmplVectorSeq']").attr("disabled", disFlag);
	$("#tmplForm").find("[name='upload']").attr("disabled", disFlag);
	$("#tmplForm").find("[name='tmplUploadBtn']").attr("disabled", disFlag);
}
//initialize the data of 'More Info' tab
function initMoreInfo(ser){
	if(! $("#moreInfoForm").attr("id")){
		return;
	}
	if (ser.otherDescription != null && ser.otherDescription != undefined && ser.otherDescription != "") {
		$("#moreInfoForm").find("[name='otherDescription']").val(ser.otherDescription.replace(/@@@\!\!\!/g,"'"));
	}
	if (ser.otherRequirement != null && ser.otherRequirement != undefined && ser.otherRequirement != "") {
		$("#moreInfoForm").find("[name='otherRequirement']").val(ser.otherRequirement.replace(/@@@\!\!\!/g,"'"));
	}
	var refType = getRefType(show, 0);
	var tmpStr = "";
	if(ser.documentList){
		var tmpList = [];
		for(var m1 in ser.documentList){
			if(ser.documentList[m1]["refType"] == refType){
				tmpList.push(ser.documentList[m1]);
			}
		}
		tmpStr = getFileListHtml(tmpList);
	}
	$("#moreInfoForm").find("[name='fileListTable']").html(tmpStr);
}
//initialize the data of 'Gene Usage' tab
function initGeneUsage(ser){
	if(!$("#geneUsageForm").attr("id")){
		return;
	}
	if(!ser.geneUsage){
		ser.geneUsage = "Protein expression/analysis";
		ser.readingFrame = "N";
	}
	$("#geneUsageForm").find("[name='geneUsage'][value='"+ser.geneUsage+"']").attr("checked", true);
	$("#geneUsageForm").find("[name='geneUsage'][value='"+ser.geneUsage+"']").trigger("click");
	if (ser.readingFrame != null && ser.readingFrame !="") {
		if (ser.readingFrame.indexOf("Y") >=0) {
			var readingFrames = ser.readingFrame.split(":");
			$("#geneUsageForm").find("[name='readingFrame'][value='"+readingFrames[0]+"']").attr("checked", true);
			$("#geneUsageForm").find("[name='geneUsageText']").val(readingFrames[1]);
			$("#geneUsageForm").find("[name='readingFrame'][value='"+readingFrames[0]+"']").trigger("click");
		} else {
			$("#geneUsageForm").find("[name='readingFrame'][value='"+ser.readingFrame+"']").attr("checked", true);
		}
	}
}
//initialize the data of "Target Construct" tab.
function initTarget(ser){
	if(!$("#targetForm").attr("id")){
		return;
	}
	if(show == "muta"){
		$("#targetForm").find("[name='variantName']").val(ser.variantName);
		$("#targetForm").find("[name='variantSequence']").val(ser.variantSequence);
	}else{
		$("#targetForm").find("[name='tgtInsertName']").val(ser.tgtInsertName);
		$("#targetForm").find("[name='tgtCloningMethod'][value='"+ser.tgtCloningMethod+"']").attr("checked", true);
		if (ser.tgtCloningMethod == "Gateway") {
			$("#targetForm").find("[name='tgtCloningSite']").attr("disabled", "true");
		} else {
			$("#targetForm").find("[name='tgtCloningSite']").val(ser.tgtCloningSite);
			$("#targetForm").find("[name='tgtCloningSite']").attr("disabled", false);
		}
		$("#targetForm").find("[name='tgtSeqSameFlag'][value='"+ser.tgtSeqSameFlag+"']").attr("checked", true);
		$("#targetForm").find("[name='tgtSequence']").val(ser.tgtSequence);
		if (ser.tgtSeqSameFlag == "Y") {
			$("#targetForm").find("[name='tgtSequence']").attr("disabled", "true");
		}
	}
	$("#targetForm").find("[name='cloningFlag'][value='"+ser.cloningFlag+"']").attr("checked", true);
	$("#targetForm").find("[name='plasmidPrepFlag'][value='"+ser.plasmidPrepFlag+"']").attr("checked", true);
}
//initialize the data of "Cloning Strategy"
function initCloning(ser){
	if(! $("#cloningForm").attr("id")){
		return;
	}
	if (ser == "") {
		var tgtVector = $("#cloningForm").find("[name='tgtVector']").val();
		if (tgtVector != undefined) {
			$("#cloningForm").find("[name='tgtVector']").trigger("change");
		}
		return;
	}
	$("#cloningForm").find("[name='itemId']").val(ser.itemId);
	if (ser.tgtVector != undefined && ser.tgtVector.indexOf("Other") >=0) {
		$("#cloningForm").find("[name='tgtVector']").find("option[value='Other']").attr("selected", true);
		document.getElementById("tgtVectorOther").style.display= "block"; 
		$("#cloningForm").find("[id='tgtVectorOther']").val(ser.tgtVector.substring(6, ser.tgtVector.length));
		if (ser.tgtResistance != undefined && ser.tgtResistance.indexOf("Other") >=0) {
			$("#cloningForm").find("[name='tgtResistance']").find("option[value='Other']").attr("selected", true);
			document.getElementById("tgtResistanceOther").style.display= "block"; 
			$("#cloningForm").find("[id='tgtResistanceOther']").val(ser.tgtResistance.substring(6, ser.tgtResistance.length));
		} else {
			$("#cloningForm").find("[name='tgtResistance']").find("option[value='"+ser.tgtResistance+"']").attr("selected", true);
		}
		$("#cloningForm").find("[name='tgtVectorSize']").val(ser.tgtVectorSize);
		$("#cloningForm").find("[name='tgtVectorSeq']").val(ser.tgtVectorSeq);
		$("#cloningForm").find("[name='tgtCopyNo'][value='"+ser.tgtCopyNo+"']").attr("checked", true);
		//Cloning Strategy文件列表
		var refType = getRefType("cloning", 2);
		var tmpStr = "";
		if(ser.documentList){
			var tmpList = [];
			for(var p1 in ser.documentList){
				if(ser.documentList[p1]["refType"] == refType){
					tmpList.push(ser.documentList[p1]);
				}
			}
			tmpStr = getFileListHtml(tmpList);
		}
		$("#cloningForm").find("[name='fileListTable']").html(tmpStr);
	} else {
		$("#cloningForm").find("[name='tgtVector']").find("option[value='"+ser.tgtVector+"']").attr("selected", true);
		$("#cloningForm").find("[name='tgtResistance']").val("");
	}
	if (show != "cloning") {
		$("#cloningForm").find("[name='tgtCloningMethod'][value='"+ser.tgtCloningMethod+"']").attr("checked", true);
		if (ser.tgtCloningMethod == "Gateway") {
			$("#cloningForm").find("[name='tgtCloningSite']").attr("disabled",true);
		} else {
			$("#cloningForm").find("[name='tgtCloningSite']").val(ser.tgtCloningSite);
			$("#cloningForm").find("[name='tgtCloningSite']").attr("disabled",false);
		}
	}
    var disFlag;
    if ($("#cloningForm").find("[name='tgtVector']").val() == "Other") {
        disFlag = false;
        document.getElementById("tgtVectorOther").style.display = "block";
    } else {
        disFlag = true;
        document.getElementById("tgtVectorOther").style.display = "none";
    }
    $("#cloningForm").find("[name='tgtVectorSize']").attr("disabled", disFlag);
    $("#cloningForm").find("[name='tgtResistance']").attr("disabled", disFlag);
    $("#cloningForm").find("[name='tgtCopyNo']").attr("disabled", disFlag);
    $("#cloningForm").find("[name='tgtVectorSeq']").attr("disabled", disFlag);
    $("#cloningForm").find("[name='upload']").attr("disabled", disFlag);
    $("#cloningForm").find("[name='cloningUploadBtn']").attr("disabled", disFlag);
}

//initialize the data of "orf clone"
function initOrfClone (sers) {
	var orfCloneForm = $("#orfCloneForm");
	if(!orfCloneForm.attr("id")){
		return;
	}
	if (sers == "" || !sers.orfClone || sers.orfClone.accessionNo == "") {
		return;
	}
	orfCloneForm.find("#orfCloneTable").append($("#copy_orf_tr").html());
	orfCloneForm.find("[name='itemId']").val(sers.orfClone.itemId);
	orfCloneForm.find("[name='orfClone_cloning_itemId']").val(sers.custCloningItemId);
	orfCloneForm.find("[name='orfClone_pla_itemId']").val(sers.plasmidPreparationItemId);
	orfCloneForm.find("[name='serviceAllCheck']").attr("checked", true);
	orfCloneForm.find("[name='service_check_orfclone']").attr("checked", true);
	orfCloneForm.find("[name='service_check_orfclone']").val(sers.orfClone.accessionInfo);
	orfCloneForm.find("[name='accessionTd']").html(sers.orfClone.accessionNo);
	if (sers.orfClone.seqType == "1") {
		orfCloneForm.find("[name='sequenceTypeTd']").html("Full Length");
	} else {
		orfCloneForm.find("[name='sequenceTypeTd']").html("ORF Sequence");
	}
	orfCloneForm.find("[name='priceTd']").html(sers.orfClone.genePrice);
	orfCloneForm.find("[name='display_sub_price']").html(sers.orfClone.subcloningPrice);
	orfCloneForm.find("[name='comment']").val(sers.orfClone.comments);
	//初始化CustomeClone相关信息
	if (sers.custCloning.tgtVector.indexOf("Other")>=0) {
		orfCloneForm.find("[name='other_vector']").css("display","block"); 
		if (sers.orfClone.subcloningPrice == undefined || sers.custCloning.tbdFlag == "1") {
			orfCloneForm.find("[name='display_sub_price']").html("TBD");
		}
		orfCloneForm.find("[name='other_vector']").attr("disabled", false);
		orfCloneForm.find("[name='tgtVector']").find("option[value='Other']").attr("selected", true);
		orfCloneForm.find("[name='tgtVectorOther']").val(sers.custCloning.tgtVector.substring(6, sers.custCloning.tgtVector.length));
		if (sers.custCloning.tgtResistance != undefined && sers.custCloning.tgtResistance.indexOf("Other") >=0) {
			orfCloneForm.find("[name='tgtResistance']").find("option[value='Other']").attr("selected", true);
			orfCloneForm.find("[name='tgtResistanceOther']").css("display","block"); 
			orfCloneForm.find("[id='tgtResistanceOther']").val(sers.custCloning.tgtResistance.substring(6, sers.custCloning.tgtResistance.length));
		} else {
			orfCloneForm.find("[name='tgtResistance']").find("option[value='"+sers.custCloning.tgtResistance+"']").attr("selected", true);
		}
		orfCloneForm.find("[name='tgtVectorSize']").val(sers.custCloning.tgtVectorSize);
		orfCloneForm.find("[name='tgtVectorSeq']").val(sers.custCloning.tgtVectorSeq);
		orfCloneForm.find("[name='tgtCopyNo'][value='"+sers.custCloning.tgtCopyNo+"']").attr("checked", true);
		//Cloning Strategy文件列表
		var refType = getRefType("cloning", 2);
		var tmpStr = "";
		if(sers.custCloning.documentList){
			var tmpList = [];
			for(var p1 in sers.custCloning.documentList){
				if(sers.custCloning.documentList[p1]["refType"] == refType){
					tmpList.push(sers.custCloning.documentList[p1]);
				}
			}
			tmpStr = getFileListHtml(tmpList);
		}
		orfCloneForm.find("[name='fileListTable']").html(tmpStr);
	} else {
		orfCloneForm.find("[name='other_vector']").css("display","none"); 
		orfCloneForm.find("[name='tgtVector']").find("option[value='"+sers.custCloning.tgtVector+"']").attr("selected", true);
		orfCloneForm.find("[name='other_vector']").attr("disabled", true);
	}
	orfCloneForm.find("[name='tgtCloningSite']").find("option[value='"+sers.custCloning.tgtCloningSite+"']").attr("selected", true);
	orfCloneForm.find("[name='tgtCloningSite3']").find("option[value='"+sers.custCloning.tgtCloningSite3+"']").attr("selected", true);
	//初始化plasmidPreparation
	if (sers.plasmidPreparation.prepWeightStr == "10 ug") {
		orfCloneForm.find("[name='pla_type_orf'][value='Standard delivery']").attr("checked", true);
		orfCloneForm.find("#txt_04").hide();
		orfCloneForm.find("#prepUom").hide();
		orfCloneForm.find("#prepWeightSel").attr("disabled", true);
		orfCloneForm.find("[name='qualityGrade']").attr("disabled", true);
	} else {
		orfCloneForm.find("[name='pla_type_orf'][value='Other']").attr("checked", true);
		orfCloneForm.find("#prepWeightSel").find("option[value='"+sers.plasmidPreparation.prepWeightStr+"']").attr("selected", true);
		if (sers.plasmidPreparation.prepWeightStr != orfCloneForm.find("#prepWeightSel").val()) {
			orfCloneForm.find("#prepWeightSel").find("option[value='Other']").attr("selected", true);
			orfCloneForm.find("#txt_04").show();
			orfCloneForm.find("#txt_04").attr("value",sers.plasmidPreparation.prepWeightStr.split(" ")[0]);
			orfCloneForm.find("#prepUom").show();
		} else {
			orfCloneForm.find("#txt_04").hide();
			orfCloneForm.find("#prepUom").hide();
		}
		orfCloneForm.find("[name='qualityGrade'][value='"+sers.plasmidPreparation.qualityGrade+"']").attr("checked", true);
	}
}

//initialize the data of "oligo"
function initOligo (oligo) {
	if(! $("#oligoDetailForm").attr("id")){
		alert("System error! Please contact system administrator for help.");
	}
	if(!oligo){
		oligo = [];
	}
	$("#oligoDetailForm").find("[name='itemId']").val(oligo.itemId);
	$("#oligoButchOligosForm").find("[name='itemId']").val(oligo.itemId);
	$("#oligoDetailForm").find("[name='oligoName']").val(oligo.oligoName);
	$("#oligoDetailForm").find("[name='aliquotingInto']").val(oligo.aliquotingInto);
	$("#oligoDetailForm").find("[name='aliquotingSize']").val(oligo.aliquotingSize);
//	var backboneSel = document.getElementsByName("backbone")[0];
//	for (var i=0;i<backboneSel.options.length; i++) {
//		if (backboneSel.options[i].value == oligo.backbone) {        
//			backboneSel.options[i].selected = true;
//            break;        
//        }
//	}
	$("#oligoDetailForm").find("[name='purification'] option[value='"+oligo.purification+"']").attr("selected", true);
	$("#oligoDetailForm").find("[name='synthesisScales'] option[value='"+oligo.synthesisScales+"']").attr("selected", true);
	if (oligo.sequence != null && oligo.sequence != undefined && oligo.sequence != "") {
		$("#oligoDetailForm").find("[name='sequence']").val(oligo.sequence.replace(/@@@\!\!\!/g,"'"));
	}
	$("#oligoDetailForm").find("[name='daPercent']").val(oligo.daPercent);
	$("#oligoDetailForm").find("[name='dcPercent']").val(oligo.dcPercent);
	$("#oligoDetailForm").find("[name='dgPercent']").val(oligo.dgPercent);
	$("#oligoDetailForm").find("[name='dtPercent']").val(oligo.dtPercent);
	$("#oligoDetailForm").find("[name='comments']").text(oligo.comments);
	
	var backbone = $("#oligoDetailForm").find("[name='backbone']").val();
	if (backbone != null && backbone.indexOf("RNA") >= 0) {
		$("#rnaPurificationSel").show();
		$("#rnaModification5Sel").show();
		$("#rnaInternalModificationSel").show();
		$("#rnaModification3Sel").show();
		$("#otherPurificationSel").hide();
		$("#otherModification5Sel").hide();
		$("#otherInternalModificationSel").hide();
		$("#otherModification3Sel").hide();
	} else {
		$("#otherPurificationSel").show();
		$("#otherModification5Sel").show();
		$("#otherInternalModificationSel").show();
		$("#otherModification3Sel").show();
		$("#rnaPurificationSel").hide();
		$("#rnaModification5Sel").hide();
		$("#rnaInternalModificationSel").hide();
		$("#rnaModification3Sel").hide();
	}
	var batchBackbone = $("#oligoButchOligosForm").find("[name='batchBackbone']").val();
	if (batchBackbone != null && batchBackbone.indexOf("RNA") >= 0) {
		$("#batchRnaPurificationSel").show();
		$("#batchRnaModification5Sel").show();
		$("#batchRnaModification3Sel").show();
		$("#batchOtherPurificationSel").hide();
		$("#batchOtherModification5Sel").hide();
		$("#batchOtherModification3Sel").hide();
	} else {
		$("#batchOtherPurificationSel").show();
		$("#batchOtherModification5Sel").show();
		$("#batchOtherModification3Sel").show();
		$("#batchRnaPurificationSel").hide();
		$("#batchRnaModification5Sel").hide();
		$("#batchRnaModification3Sel").hide();
	}
	
	if (oligo.othermodificationFlag5 == "Y") {
		$("#oligoDetailForm").find("[name='othermodificationFlag5']").attr("checked",true);
		$("#oligoDetailForm").find("[name='othermodification5']").attr("disabled","");
		$("#oligoDetailForm").find("[name='othermodification5']").text(oligo.othermodification5);
	} else {
		$("#oligoDetailForm").find("[name='othermodificationFlag5']").attr("checked",false);
		$("#oligoDetailForm").find("[name='othermodification5']").attr("disabled",true);
	}
	if (oligo.interOtherModificationFlag == "Y") {
		$("#oligoDetailForm").find("[name='interOtherModificationFlag']").attr("checked",true);
		$("#oligoDetailForm").find("[name='interOtherModification']").attr("disabled","");
		$("#oligoDetailForm").find("[name='interOtherModification']").text(oligo.interOtherModification);
	} else {
		$("#oligoDetailForm").find("[name='interOtherModificationFlag']").attr("checked",false);
		$("#oligoDetailForm").find("[name='interOtherModification']").attr("disabled",true);
	}
	if (oligo.othermodificationFlag3 == "Y") {
		$("#oligoDetailForm").find("[name='othermodificationFlag3']").attr("checked",true);
		$("#oligoDetailForm").find("[name='othermodification3']").attr("disabled","");
		$("#oligoDetailForm").find("[name='othermodification3']").text(oligo.othermodification3);
	} else {
		$("#oligoDetailForm").find("[name='othermodificationFlag3']").attr("checked",false);
		$("#oligoDetailForm").find("[name='othermodification3']").attr("disabled",true);
	}
}

//initialize the data of "pkg service"
function initPkgService(sers,pkgServiceId){
	if(!sers){
		return;
	}
	var ser = [];
	for(var o in sers){
		ser = sers[o];
		var itemId = o;
		var catalogNo = ser.catalogNo;
		var itemName = ser.itemName;
		var name = ser.name;
		var cost = ser.cost;
		var price = ser.unitPrice;
		var upSymbol = ser.upSymbol;
		var description = ser.description;
		if (pkgServiceId != undefined && pkgServiceId == itemId) {
			if($("#pkgForm").attr("id") && $("#parentName").attr("id") && $("#parentDescription").attr("id")){
				$("#parentName").val(name.replace(/@@/g, "'").replace(/~~/g, "\"").replace(/##/g, "\n"));
				$("#parentDescription").val(description.replace(/@@/g, "'").replace(/~~/g, "\"").replace(/##/g, "\n"));
				$("#pkgId").val(pkgServiceId);
			}
		} else {
			var contentDiv = $("div[name='contentPkgSerDiv'][catalogNo='"+catalogNo+"']");
			contentDiv.find("select option[value='"+name+"']").attr("selected", true);
			contentDiv.find("input[name='addSerPkgBtn']").val("Modify");
			contentDiv.find("input[name='cost']").val(cost);
			contentDiv.find("input[name='price']").val(price);
			contentDiv.find("span[id='costUpSymbol']").html(upSymbol);
			contentDiv.find("span[id='priceUpSymbol']").html(upSymbol);
			contentDiv.find("textarea").val(description.replace(/@@/g, "'").replace(/~~/g, "\"").replace(/##/g, "\n"));
			contentDiv.find("[name='addSerPkgBtn']").attr("itemId", itemId);
			if (ser.seqType != undefined && ser.seqType == 'Y') {
				var proteinAccession = "";
				if (itemName != undefined && itemName == 'Molecular Biology') {
					proteinAccession = '&nbsp;&nbsp;&nbsp;&nbsp;<span class="css_b">Protein Name：</span>'
					   	+'<input type="text" value="'+ser.additionInfo1+'" size="20" name="additionInfo1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
					   	+'<span class="css_b">Accession Number：</span><input type="text" value="'+ser.additionInfo2+'" size="20" name="additionInfo2">';
				}
				contentDiv.find("#text1_2").html(
					'<span class="css_b">Gene Sequence</span>'
						+'<textarea name="sequence" onblur="if(value == \'\'){value=\'Enter Gene Sequence...\';}" '
					   	+'onfocus="if(value ==\'Enter Gene Sequence...\') {value=\'\';}" class="content_textarea3" '
					   	+'id="sequence${id}">'+ser.sequence+'</textarea><br/><br/>'
					   	+'<span class="css_b">Protein Sequence</span>'
					   	+'<textarea name="proteinSeq" onblur="if(value == \'\'){value=\'Enter Protein Sequence...\';}" '
					   	+'onfocus="if(value ==\'Enter Protein Sequence...\') {value=\'\';}" class="content_textarea3" '
					   	+'id="sequence${id}">'+ser.proteinSeq+'</textarea><br/><br/>'
					   	+proteinAccession
					   	+'&nbsp;&nbsp;&nbsp;&nbsp;<span class="css_b">bpCost：</span>'
					   	+'<input type="text" value="'+ser.seqBpCost+'" size="12" name="seqBpCost">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
					   	+'<span class="css_b">bpPrice：</span><input type="text" value="'+ser.seqBpPrice+'" size="12" name="seqBpPrice">'
				);
			}
		}
	}
}
//
function initAntibody(ser){
	//alert("init antibodyitemId:>>"+ser.itemId);
	if(!ser){
		return;
	}
	if(!ser.antigenType){
		ser.antigenType = "GenScript Synthesized Peptide";
	}
	$("#antibodyName").val(ser.antibodyName);
	//初始化antigenType
	var antigenType = ser.antigenType;
	$("#antigenType").find("option[value='"+antigenType+"']").attr("selected", true);
	initAntiSequence(antigenType);
	if(parseInt(ser.itemId) == ser.itemId){
	//为已经保存到数据库中的数据
		$("#antibodyName").attr("readonly", true);
		$("#antigenType").attr("disabled", true);
	}else{
		$("#antibodyName").attr("readonly", false);
		$("#antigenType").attr("disabled", false);
	}
}

function initCustomService(customService){
	//alert("init antibodyitemId:>>"+ser.itemId);
	
	if(!customService){
		return;
	}
	$("#customServiceForm").find("[name='itemId']").val(customService.itemId);
	$("#customServiceForm").find("[name='customDesc']").val(customService.customDesc);
	$("#customServiceForm").find("[name='comments']").val(customService.comments);
}

function initPeptide(ser){
	if(!ser){
		return;
	}
	//alert(itemId);
	$("#stdPeptideForm").find("[name='itemId']").val(ser.itemId);
		//alert(ser[o]);
		$("#stdPeptideForm").find("[name='name']").val(ser.name);
		$("#stdPeptideForm").find("[name='quantity']").val(ser.quantity);
		$("#stdPeptideForm").find("[name='purity']").val(ser.purity);
		$("#stdPeptideForm").find("[name='seqLength']").val(ser.seqLength);
		$("#stdPeptideForm").find("[name='sequence']").val(ser.sequence);
		if(ser.aminoChangeFlag == 'Y'){
			$("#stdPeptideForm").find("[id='aminoChangeFlagY']").attr("checked", true);
		}else if(ser.aminoChangeFlag == 'N'){
			$("#stdPeptideForm").find("[id='aminoChangeFlagN']").attr("checked", true);
		}
		$("#stdPeptideForm").find("[name='nTerminal']").val(ser.nterminal);
		$("#stdPeptideForm").find("[name='cTerminal']").val(ser.cterminal);
		$("#stdPeptideForm").find("[name='aliquoteVialQty']").val(ser.aliquoteVialQty);
		$("#stdPeptideForm").find("[name='disulfideBridge']").val(ser.disulfideBridge);
		$("#stdPeptideForm").find("[name='comments']").val(ser.comments);
}

function initLibrayPeptide(ser){
	if(!ser){
		return;
	}
	var multiSeq = ser.sequence;
	
	var seqStr = "";
	var flag = false;
	var seqs = multiSeq.split(",");
		for(var i=0; i<seqs.length;i++){
			if(i!=seqs.length-1){
				seqStr = seqStr+seqs[i]+"\r";
				flag = true;
			}
			else
				seqStr = seqStr+seqs[i];
			}
	//alert(itemId);
	if(! flag){
		$("#libraryPeptideForm").find("[id='libPeptideCreateTrigger']").hide();
		$("#libraryPeptideForm").find("[id='libPeptideUpdateTrigger']").hide();
	}else{
		$("#libraryPeptideForm").find("[id='libPeptideCreateTrigger']").show();
		$("#libraryPeptideForm").find("[id='libPeptideUpdateTrigger']").show();
	}
	$(":radio[id=libraryCatalogNoSC1177]").attr("disabled", "disabled");
	$(":radio[id=libraryCatalogNoSC1487]").attr("disabled", "disabled");
	$("#libraryPeptideForm").find("[name='itemId']").val(ser.itemId);
		//alert(ser[o]);
		$("#libraryPeptideForm").find("[name='peptide.name']").val(ser.name);
		$("#libraryPeptideForm").find("[name='peptide.quantity']").val(ser.quantity);
		$("#libraryPeptideForm").find("[name='peptide.purity']").val(ser.purity);
		$("#libraryPeptideForm").find("[name='peptide.modification']").val(ser.modification);
		$("#libraryPeptideForm").find("[name='peptide.sequence']").val(seqStr);
		
		$("#libraryPeptideForm").find("[name='peptide.qcReport']").val(ser.qcReport);
		$("#libraryPeptideForm").find("[name='peptide.comments']").val(ser.comments);
}

//add by zhanghuibin
function vectorSizeValid(e) {
    var key = window.event ? e.keyCode : e.which;
    if(key == 8 || key == 46) return true;
    var keychar = String.fromCharCode(key);
    var reg = /\d|-/;
    var result = reg.test(keychar);
    if(!result)
    {
        return false;
    }
    else
    {
        return true;
    }
}

function initArrayPeptide(ser){
	if(!ser){
		return;
	}
	var multiSeq = ser.sequence;
	
	var seqStr = "";
	var seqs = multiSeq.split(",");
		for(var i=0; i<seqs.length;i++){
			if(i!=seqs.length-1)
				seqStr = seqStr+seqs[i]+"\r";
			else
				seqStr = seqStr+seqs[i];
			}
	//alert(itemId);
	$("#arrayPeptideForm").find("[name='itemId']").val(ser.itemId);
		//alert(ser[o]);
		$("#arrayPeptideForm").find("[name='peptide.name']").val(ser.name);
		$("#arrayPeptideForm").find("[name='peptide.quantity']").val(ser.quantity);
		$("#arrayPeptideForm").find("[name='peptide.purity']").val(ser.purity);
		$("#arrayPeptideForm").find("[name='modification']").val(ser.modification);
		$("#arrayPeptideForm").find("[name='peptide.sequence']").val(seqStr);
		
		$("#arrayPeptideForm").find("[name='peptide.qcReport']").val(ser.qcReport);
		$("#arrayPeptideForm").find("[name='peptide.synMembrane']").val(ser.synMembrane);
		$("#arrayPeptideForm").find("[name='peptide.deliveryFormat']").val(ser.deliveryFormat);
		$("#arrayPeptideForm").find("[name='peptide.comments']").val(ser.comments);
}

//用于antibody的相关显示
function initAntiSequence(antigenType){
	$("#peptideSeqTitleSpan").html(antigenType+":");
	if(antigenType == "GenScript Synthesized Peptide"){
		$("#peptideSeqTh").parent().hide();
	}else{
		$("#peptideSeqTh").parent().show();
	}
	if(antigenType == "Customer Provided Peptide"){
		$("#peptideSeqTh").html("Peptide Sequence");
	}else if(antigenType == "Protein"){
		$("#peptideSeqTh").html("Protein Sequence");
	}else if(antigenType == "Other"){
		$("#peptideSeqTh").html("Sequence");
	}
}

function changeAntigenType(selObj){
	var antigenType = $(selObj).val();
	var antibodyItemId = $("#antibodyItemId").val();
	initAntiSequence(antigenType);
	var ajaxFlag = false;
	$.ajax({
		url:orderquoteObj.url("changeAntigenType"),
		data:"antibodyItemId="+antibodyItemId+"&antigenType="+antigenType,
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.message == "SUCCESS"){
				removeItems(data.itemIdList.toString());
				ajaxFlag = true;
			}
		},
		error:function(){
			
		},
		async:false
	});
	if(ajaxFlag == false){
		alert("Failed to change the antigen-type.");
		window.location.reload();
		return;
	}
	$("#antibodyPeptideContainer").empty();
	if(antigenType == "GenScript Synthesized Peptide" || antigenType == "Customer Provided Peptide"){
		$("#newPeptideTd").show();
	}else {
		$("#newPeptideTd").hide();
	}
}
//*********************************************************************************************
function getTitleByKey(key){
	var tmpArr = {
			"gene":"Gene Synthesis",
			"codon":"Codon Optimization",
			"cloning":"Cloning Strategy",
			"plasmid":"Plasmid Preparation",
			"usage":"Gene Usuage",
			"moreinfo":"More Info",	
			"muta":"Template Info",
			"target":"Target Construct",
			"librayPeptide":"Peptide Library",
			"customService":"Custom Service",
			"mutaLib":"Mutation Libraries"
			};
	if(tmpArr[key]){
		return tmpArr[key];
	}else{
		return "";
	}
}

function getIndexByTitle(title){
	var tabIndex = getTabIndexByTitle(title);
	if(tabIndex == -1){
		return -1;
	}else{
		return tabIndex[1]; 
	}
}

function toggleDiv(){
	if($(this).next().get(0).style.display == "none"){
		$(this).find("img").attr("src", "images/ad.gif");
		$(this).next().show(); 
	}else{
		$(this).next().hide();
		$(this).find("img").attr("src", "images/ar.gif");
	}
}

function isnumber(str){ 
	if(""==str){ 
		return false; 
	} 
	var re = /^[0-9]*$/;  
    if (!re.test(str)) {
        return false;
    }
    return true;
}
//判断字符串是否为数字     //判断正整数 /^[1-9]+[0-9]*]*$/   
function checkRate(str) {
    var re = /^[0-9]+.?[0-9]*$/;  
    if (!re.test(str)) {
        return false;
    }
    return true;
}

function trimAllBlank (str) {
    var reg = /\s/g;     
    return str.replace(reg,""); 
}

function isRNASequence (str) {
	var reg = new RegExp("^(A|C|G|U|N|B|D|H|V|M|R|W|S|Y|K)*$");
	var seqArray = str.split("\r\n");
	var seq = "";
	for (var i=0;i<seqArray.length;i++) {
		var str = seqArray[i];
		if (str == "") {
			continue;
		}
		if (str.indexOf(">") >=0) {
			if (seq != "" && !((trimAllBlank(seq)).toUpperCase()).match(reg)) {
				return false;
			}
			str = str.substring(str.lastIndexOf(">")+1,str.length);
			if (str == "") {
				return false;
			}
			seq = "";
		} else {
			seq += str;
			if (i+1 == seqArray.length && seq != "" && !((trimAllBlank(seq)).toUpperCase()).match(reg)) {
					return false;
			}
		}
	}
	return true;
}

//validate sequecne for peptide
function checkAntibodyPeptideSequenceValidateTrigger (peptideForm, antibodyPeptideSequence) {
	var seq = antibodyPeptideSequence;
	var standard = "ALA,ARG,ASN,ASP,CYS,GLU,GLN,GLY,HCY,HIS,HSE,ILE,LEU,LYS,MET,NLE,NVA,ORN,PEN,PHE,PRO,SER,THR,TRP,TYR,VAL,pGLU,gamma-GLU,";
	var nme = "ALA,ILE,LEU,PHE,VAL,SER,THR,TYR,";	
	var others = "dnp-LYS,pTHR,pTYR,pSER,CIT,alpha-ABA,iso-ASP,Ac-LYS,2-Me-ALA,OXA,";
	var single = "ACDEFGHIKLMNPQRSTUVWXY";
	//trim	
	var trim = /\s+/g;
	seq = seq.replace(trim,"");
	if (seq.length == 0){
		alert("No amino acid sequence found.");
		return false;
	}
	//check invalid character
	var reFirstCheck = /[^a-zA-Z\-\{\}2]/;
	var firstCheck = reFirstCheck.exec(seq);
	if (firstCheck){
		alert("Invalid character '" + firstCheck+"'.");
		return false;
	}
	var originalSeq = seq;
	//start to validate
	var re = /\{\S+?\}/g;
	var standardArray = new Array();
	seq = seq.replace(re,function(matched){
			standardArray.push(matched.substring(1,matched.length-1));
			return "";
		});
	var toChkSeq;
	var unMatchedSeq;
	var regCheck;
	
	for (var i=0; i<standardArray.length;i++){
		if ( standardArray[i].substring(0,2).toLowerCase() == 'd-'){
			toChkSeq = standardArray[i].substring(2);
			regCheck = new RegExp(toChkSeq+",","gi");
			if (standard.search(regCheck)>-1){
				continue;
			}else{
				unMatchedSeq = standardArray[i].substring(0,2)+ toChkSeq;
				break;
			}
		}else if (standardArray[i].substring(0,4).toLowerCase() == 'nme-'){
			toChkSeq = standardArray[i].substring(4);
			regCheck = new RegExp(toChkSeq+",","gi");
			if (nme.search(regCheck)>-1){
				continue;
			}else{
				unMatchedSeq = standardArray[i].substring(4)+ toChkSeq;
				break;
			}
		}else{
			toChkSeq = standardArray[i];
			regCheck = new RegExp(toChkSeq+",","gi");
			if (standard.search(regCheck)>-1){
				continue;
			}else if(others.search(regCheck)>-1){
				continue;
			}else{
				unMatchedSeq = toChkSeq;
				break;
			}
		}
	}
	if (unMatchedSeq){
		alert("Invalid sequence " + "{"+unMatchedSeq+"}");
		return false;
	}

	for (var i=0;i<seq.length;i++){
		toChkSeq = seq.charAt(i);
		regCheck = new RegExp(toChkSeq,'gi');
		if (single.search(regCheck)== -1){
			unMatchedSeq = toChkSeq;
			break;
		}
	}
	if (unMatchedSeq){
		alert("Invalid sequence " +unMatchedSeq);
		return false;
	} else{
		var seqSource = originalSeq.toUpperCase();
		var dGang = /D\-/g;
		seqSource = seqSource.replace(dGang,'d-');
		var pGLU = /PGLU/g;
		seqSource = seqSource.replace(pGLU,'pGLU');
		var pTHR = /PTHR/g;
		seqSource = seqSource.replace(pTHR,'pTHR');
		var pSER = /PSER/g;
		seqSource = seqSource.replace(pSER,'pSER');
		var pTYR = /PTYR/g;
		seqSource = seqSource.replace(pTYR,'pTYR');
		var gamma = /GAMMA\-/g;
		seqSource = seqSource.replace(gamma,'gamma-');
		var alpha = /ALPHA\-/g;
		seqSource = seqSource.replace(alpha,'alpha-');
		var iso = /ISO\-/g;
		seqSource = seqSource.replace(iso,'iso-');
		var ac = /AC\-/g;
		seqSource = seqSource.replace(ac,'Ac-');
		var nme = /NME\-/g;
		seqSource = seqSource.replace(nme,'nme-');
		var me = /\-ME\-/g;
		seqSource = seqSource.replace(me,'-Me-');
		var dnp = /DNP\-/g;
		seqSource = seqSource.replace(dnp,'dnp-');

		peptideForm.find("[name='sequence']").val(seqSource);
		return true;
	}
	return false;
}

function isDNASequence (str) {
	var reg = new RegExp("^(A|C|G|T|N|B|D|H|V|M|R|W|S|Y|K)*$");
	var seqArray = str.split("\r\n");
	var seq = "";
	for (var i=0;i<seqArray.length;i++) {
		var str = seqArray[i];
		if (str == "") {
			continue;
		}
		if (str.indexOf(">") >=0) {
			if (seq != "" && !(seq.toUpperCase()).match(reg)) {
				return false;
			}
			str = str.substring(str.lastIndexOf(">")+1,str.length);
			if (str == "") {
				return false;
			}
			seq = "";
		} else {
			seq += str;
			if (i+1 == seqArray.length && seq != "" && !(seq.toUpperCase()).match(reg)) {
					return false;
			}
		}
	}
	return true;
}

function initStatus(){
	if( parent.window.orderquoteObj.editFlag == true ){
		/*$(":text").attr("readonly", false);
		$(":textarea").attr("readonly", false);
		$(":button").attr("disabled", false);
		$(":radio").attr("disabled", false);
		$("select").attr("disabled", false);*/
	}else{
		$(":text").attr("readonly", true);
		$(":textarea").attr("readonly", true);
		$(":button").attr("disabled", true);
		$(":radio").attr("disabled", true);
		$("select").attr("disabled", true);
	}
}

function changeAntiName () {
	var firstName = $("#antibodyPeptideContainer").find("[id='myName']").val();
	if(firstName != undefined && firstName != "" && trimAllBlank(firstName) != ""){
		$("#antibodyName").val("Anti-"+firstName);
	}
}

//add by zhanghuibin
function refreshItemList() {
    //add by zhanghuibin 选择选中item
    var trNum = window.parent.$('#itemListIframe').contents().find('#itemTable').find("tr").length;
    if (trNum == undefined || trNum == 0) {
        parent.window.orderquoteObj.originQty = 0;
    } else {
        parent.window.orderquoteObj.originQty = trNum - 1;
    }
    parent.window.orderquoteObj.triggerLast = "1";
    parent.$("#itemListIframe").attr("src", orderquoteObj.url("getItemList"));
}

$(document).ready(function(){
	$("#sequence5").change(function(){
		if(/[^atgcATGC]/.test($("#sequence5").val())){
			alert("The 5' and 3' sequence can only be A, T, C or G, please specify the base for N, R, Y or W.");
			return;
		}
	});
	$("#sequence3").change(function(){
		if(/[^atgcATGC]/.test($("#sequence3").val())){
			alert("The 5' and 3' sequence can only be A, T, C or G, please specify the base for N, R, Y or W.");
			return;
		}
	});
	$("#searchEnzymeTrigger5").click(function(){
		parent.$("#searchEnzymeDialog").dialog("option", "sequenceId", "sequence5");
		parent.$("#searchEnzymeDialog").dialog("open");
	});
	$("#searchEnzymeTrigger3").click(function(){
		parent.$("#searchEnzymeDialog").dialog("option", "sequenceId", "sequence3");
		parent.$("#searchEnzymeDialog").dialog("open");
	});
	//$('form').enable_changed_form(); 不需要备份数据
	
	$("#saveMoreDetailTrigger").click(function(){
		saveMoreDetail($(this).attr("show"));
	});
	
	$("#geneSynthesisForm").find("[name='sequenceType']").click (function () {
		var sequenceType = $("#geneSynthesisForm").find("[name='sequenceType'][checked]").val();
		if ("Protein" == sequenceType) {
			$("#geneSynthesisForm").find("[name='codeOptmzFlag'][value='Y']").attr("checked", true);
			enableTabByTitle("Codon Optimization");
		}
	});
	
	$("[name='codeOptmzFlag']").click(function(){
		var tmpIndex = getIndexByTitle("Codon Optimization");
		if($(this).val() == "Y"){
			enableTabByTitle("Codon Optimization");
			if(tmpIndex != -1)
			showTab('dhtmlgoodies_tabView1', tmpIndex);
		}else{
			disableTabByTitle("Codon Optimization");
		}
	});
	
	$("[name='cloningFlag']").click(function(){
		this.blur();
		//this.focus();
	});
	
	$("[name='plasmidPrepFlag']").click(function(){
		this.blur();
		//this.focus();
	});
	
	$("[name='cloningFlag']").change(function(){
		if(checkGeneSynthesis() == false || saveCloning($(this).val())== false){
			if($(this).val() == "Y"){
				$("[name='cloningFlag'][value='Y']").attr("checked", false);
				$("[name='cloningFlag'][value='N']").attr("checked", true);
			}else if($(this).val() == "N"){
				$("[name='cloningFlag'][value='N']").attr("checked", false);
				$("[name='cloningFlag'][value='Y']").attr("checked", true);
			}
            this.blur();
			return false;
		}
		var tmpIndex = getIndexByTitle("Cloning Strategy");
		if($(this).val() == "Y"){
			enableTabByTitle("Cloning Strategy");
			if(tmpIndex != -1) {
				showTab('dhtmlgoodies_tabView1', tmpIndex);
			}
			if($("#geneSynthesisForm").attr("itemId")){
				$("#geneSynthesisForm").find("[name='stdVectorName']").attr("disabled","true");
				$("#geneSynthesisForm").find("[name='cloningSite']").attr("disabled","true");
			}
		}else{
			disableTabByTitle("Cloning Strategy");
			if($("#geneSynthesisForm").attr("itemId")){
				$("#geneSynthesisForm").find("[name='stdVectorName']").attr("disabled","");
				$("#geneSynthesisForm").find("[name='cloningSite']").attr("disabled","");
			}
		}
		//saveCloning($(this).val());
	});
	
	$("[name='plasmidPrepFlag']").change(function(){
		if(checkGeneSynthesis() == false || checkMutaLib() == false || savePlasmid($(this).val()) == false){
			if($(this).val() == "Y"){
				$("[name='plasmidPrepFlag'][value='Y']").attr("checked", false);
				$("[name='plasmidPrepFlag'][value='N']").attr("checked", true);
			}else if($(this).val() == "N"){
				$("[name='plasmidPrepFlag'][value='N']").attr("checked", false);
				$("[name='plasmidPrepFlag'][value='Y']").attr("checked", true);
			}
            this.blur();
			return false;
		}
		var tmpIndex = getIndexByTitle("Plasmid Preparation");
		if($(this).val() == "Y"){
			enableTabByTitle("Plasmid Preparation");
			if(tmpIndex != -1)
				showTab('dhtmlgoodies_tabView1', tmpIndex);
		}else{
			disableTabByTitle("Plasmid Preparation");
		}
	});

	$("#cloningForm").find("[name='cloningUploadBtn']").click(function(){
		var tmpId = $("#cloningForm").find("[name='itemId']").val();
		var refType = getRefType("cloning", 2);
		uploadServiceFile(tmpId, "custCloning", "cloningForm", refType, this);
	});
	
	$("#plasmidForm").find("[name='plasmidUploadBtn']").click(function(){
		var tmpId = $("#plasmidForm").find("[name='itemId']").val();
		var refType = getRefType("plasmid", 1);
		uploadServiceFile(tmpId, "plasmidPreparation", "plasmidForm", refType, this);
	});

	$("#moreInfoForm").find("[name='moreInfoUploadBtn']").click(function(){
		var tmpId = itemId;
		var refType = getRefType(show, 0);
		var serviceName = getServiceName(show);
		uploadServiceFile(tmpId, serviceName, "moreInfoForm", refType, this);
	});

	$("#cloningForm").find("#fileListTable").click(function(e){
		var obj = e.target;
		if(obj.value == "Delete"){
			var filePath = $(obj).attr("filePath");
			var tmpId = $("#cloningForm").find("[name='itemId']").val();
			deleteServiceFile(obj, filePath, "custCloning", tmpId);
		}
	});
	
	$("#plasmidForm").find("#fileListTable").click(function(e){
		var obj = e.target;
		if(obj.value == "Delete"){
			var filePath = $(obj).attr("filePath");
			var tmpId = $("#plasmidForm").find("[name='itemId']").val();
			deleteServiceFile(obj, filePath, "plasmidPreparation", tmpId);
		}
	});
	
	//------------------Oligo start------------------------------//
	
	$("#oligoDetailForm").find("[id='backbone']").change(function(){
		var backbone = $("#oligoDetailForm").find("[id='backbone']").val();
		if (backbone.indexOf("RNA") >= 0) {
			$("#rnaPurificationSel").show();
			$("#rnaModification5Sel").show();
			$("#rnaInternalModificationSel").show();
			$("#rnaModification3Sel").show();
			$("#otherPurificationSel").hide();
			$("#otherModification5Sel").hide();
			$("#otherInternalModificationSel").hide();
			$("#otherModification3Sel").hide();
		} else {
			$("#rnaPurificationSel").hide();
			$("#rnaModification5Sel").hide();
			$("#rnaInternalModificationSel").hide();
			$("#rnaModification3Sel").hide();
			$("#otherPurificationSel").show();
			$("#otherModification5Sel").show();
			$("#otherInternalModificationSel").show();
			$("#otherModification3Sel").show();
		}
	});
	
	$("#oligoButchOligosForm").find("[id='batchBackbone']").change(function(){
		var batchBackbone = $("#oligoButchOligosForm").find("[id='batchBackbone']").val();
		if (batchBackbone != null && batchBackbone.indexOf("RNA") >= 0) {
			$("#batchRnaPurificationSel").show();
			$("#batchRnaModification5Sel").show();
			$("#batchRnaModification3Sel").show();
			$("#batchOtherPurificationSel").hide();
			$("#batchOtherModification5Sel").hide();
			$("#batchOtherModification3Sel").hide();
		} else {
			$("#batchOtherPurificationSel").show();
			$("#batchOtherModification5Sel").show();
			$("#batchOtherModification3Sel").show();
			$("#batchRnaPurificationSel").hide();
			$("#batchRnaModification5Sel").hide();
			$("#batchRnaModification3Sel").hide();
		}
	});
	
	$("#oligoDetailForm").find("[id='othermodificationFlag5']").click(function(){
		if (!$("#oligoDetailForm").find("[id='othermodificationFlag5']").attr("checked")) {
			$("#othermodification5").attr("disabled",true);
		} else {
			$("#othermodification5").removeAttr("disabled");
		}
	});
	
	$("#oligoDetailForm").find("[id='interOtherModificationFlag']").click(function(){
		if (!$("#oligoDetailForm").find("[id='interOtherModificationFlag']").attr("checked")) {
			$("#interOtherModification").attr("disabled",true);
		} else {
			$("#interOtherModification").removeAttr("disabled");
		}
	});
	
	$("#oligoDetailForm").find("[id='othermodificationFlag3']").click(function(){
		if (!$("#oligoDetailForm").find("[id='othermodificationFlag3']").attr("checked")) {
			$("#othermodification3").attr("disabled",true);
		} else {
			$("#othermodification3").removeAttr("disabled");
		}
	});
	
	$("#oligoDetailForm").find("[id='rnaModification5']").click(function(){
		var backbone = $("#oligoDetailForm").find("[name='backbone']").val();
		if (backbone == null || backbone.indexOf("RNA") < 0) {
			return;
		}
		var rnaModification5 = $("#oligoDetailForm").find("[id='rnaModification5']").val();
		if (rnaModification5 == undefined || rnaModification5 == "null" 
			|| rnaModification5 == null || rnaModification5 == "") {
			return;
		} else {
			var sequence = $("#oligoDetailForm").find("[name='sequence']").val();
			$("#oligoDetailForm").find("[name='sequence']").val(rnaModification5 + sequence);
		}
	});
	
	$("#oligoDetailForm").find("[id='selOtherModification5']").click(function(){
		var backbone = $("#oligoDetailForm").find("[name='backbone']").val();
		if (backbone == null || backbone.indexOf("DNA") < 0) {
			return;
		}
		var otherModification5 = $("#oligoDetailForm").find("[id='selOtherModification5']").val();
		if (otherModification5 == undefined || otherModification5 == "null" 
			|| otherModification5 == null || otherModification5 == "") {
			return;
		} else {
			var sequence = $("#oligoDetailForm").find("[name='sequence']").val();
			$("#oligoDetailForm").find("[name='sequence']").val(otherModification5 + sequence);
            /*$("#oligoDetailForm").find("[name='sequence']")[0].focus();
		    document.selection.createRange().text = otherModification5;
            $("#oligoDetailForm").find("[name='sequence']")[0].focus();*/
		}
	});
	
	$("#oligoDetailForm").find("[id='rnaInternalModification']").click(function(){
		var backbone = $("#oligoDetailForm").find("[name='backbone']").val();
		if (backbone == null || backbone.indexOf("RNA") < 0) {
			return;
		}
		var rnaInternalModification = $("#oligoDetailForm").find("[id='rnaInternalModification']").val();
		if (rnaInternalModification == undefined || rnaInternalModification == null 
				 || rnaInternalModification == "null" || rnaInternalModification == "") {
			return;
		} else {
			var sequence = $("#oligoDetailForm").find("[name='sequence']").val();
			$("#oligoDetailForm").find("[name='sequence']").val(sequence + rnaInternalModification);
		}
	});
	
	$("#oligoDetailForm").find("[id='otherInternalModification']").click(function(){
		var backbone = $("#oligoDetailForm").find("[name='backbone']").val();
		if (backbone == null || backbone.indexOf("DNA") < 0) {
			return;
		}
		var otherInternalModification = $("#oligoDetailForm").find("[id='otherInternalModification']").val();
		if (otherInternalModification == undefined || otherInternalModification == null 
				 || otherInternalModification == "null" || otherInternalModification == "") {
			return;
		} else {
			/*var sequence = $("#oligoDetailForm").find("[name='sequence']").val();
			$("#oligoDetailForm").find("[name='sequence']").val(sequence + otherInternalModification);*/
            $("#oligoDetailForm").find("[name='sequence']")[0].focus();
		    document.selection.createRange().text = otherInternalModification;
            $("#oligoDetailForm").find("[name='sequence']")[0].focus();
		}
	});
	
	$("#oligoDetailForm").find("[id='rnaModification3']").click(function(){
		var backbone = $("#oligoDetailForm").find("[name='backbone']").val();
		if (backbone == null || backbone.indexOf("RNA") < 0) {
			return;
		}
		var rnaModification3 = $("#oligoDetailForm").find("[id='rnaModification3']").val();
		if (rnaModification3 == undefined || rnaModification3 == null 
				 || rnaModification3 == "null" || rnaModification3 == "") {
			return;
		} else {
			var sequence = $("#oligoDetailForm").find("[name='sequence']").val();
			$("#oligoDetailForm").find("[name='sequence']").val(sequence + rnaModification3);
		}
	});
	
	$("#oligoDetailForm").find("[id='selOtherModification3']").click(function(){
		var backbone = $("#oligoDetailForm").find("[name='backbone']").val();
		if (backbone == null || backbone.indexOf("DNA") < 0) {
			return;
		}
		var otherModification3 = $("#oligoDetailForm").find("[id='selOtherModification3']").val();
		if (otherModification3 == undefined || otherModification3 == null 
				 || otherModification3 == "null" || otherModification3 == "") {
			return;
		} else {
			var sequence = $("#oligoDetailForm").find("[name='sequence']").val();
			$("#oligoDetailForm").find("[name='sequence']").val(sequence + otherModification3);
            /*$("#oligoDetailForm").find("[name='sequence']")[0].focus();
		    document.selection.createRange().text = otherModification3;
            $("#oligoDetailForm").find("[name='sequence']")[0].focus();*/
			//sel.text = otherModification3;
            //$("#oligoDetailForm").find("[name='sequence']").val(otherModification3)
		}
	});
	
	$("#oligoDetailForm").find("[name='chimericBases']").click(function(){
		var chimericBases = $("#oligoDetailForm").find("[name='chimericBases']").val();
		if (chimericBases == undefined || chimericBases == null 
				 || chimericBases == "null" || chimericBases == "") {
			return;
		} else {
			/*var sequence = $("#oligoDetailForm").find("[name='sequence']").val();
			$("#oligoDetailForm").find("[name='sequence']").val(sequence + chimericBases);*/
            $("#oligoDetailForm").find("[name='sequence']")[0].focus();
		    document.selection.createRange().text = chimericBases;
            $("#oligoDetailForm").find("[name='sequence']")[0].focus();
		}
	});
	
	$("#oligoDetailForm").find("[name='standardMixedMases']").click(function(){
		var standardMixedMases = $("#oligoDetailForm").find("[name='standardMixedMases']").val();
		if (standardMixedMases == undefined || standardMixedMases == null 
				 || standardMixedMases == "null" || standardMixedMases == "") {
			return;
		} else {
			/*var sequence = $("#oligoDetailForm").find("[name='sequence']").val();
			$("#oligoDetailForm").find("[name='sequence']").val(sequence + standardMixedMases);*/
            $("#oligoDetailForm").find("[name='sequence']")[0].focus();
		    document.selection.createRange().text = standardMixedMases;
            $("#oligoDetailForm").find("[name='sequence']")[0].focus();
		}
	});
	
	$("#oligourl").click(function(){
		var oligoUrl = $("#oligourl").html();
		if (oligoUrl == "Click to hide the modification menus") {
			$("#oligourl").html("Click to show the modification menus");
			$("#oligoa").hide();
		} else {
			$("#oligourl").html("Click to hide the modification menus");
			$("#oligoa").show();
		}
	});
	
	$("#oligoDetailForm").validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).removeClass(errorClass);
	    },
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            return false;
	        });
		},
		rules: {
			oligoName:{required:true},
			aliquotingInto:{digits:true},
			aliquotingSize:{number:true},
			backbone:{required:true},
			purification:{required:true},
			sequence:{required:true},
			daPercent:{digits:true},
			dcPercent:{digits:true},
			dgPercent:{digits:true},
			dtPercent:{digits:true}
		},
		messages: {
			oligoName: {required:"Please enter the Oligo Name in 'More Detail' tab"},
			aliquotingInto:{digits:"Please enter a valid Aliquoting into in 'More Detail' tab"},
			aliquotingSize:{number:"Please enter a valid nMol/Vial in 'More Detail' tab"},
			backbone:{required:"Please select a Backbone in 'More Detail' tab"},
			purification:{required:"Please select a Purification in 'More Detail' tab"},
			sequence:{required:"Please enter the Sequence in 'More Detail' tab"},
			daPercent:{digits:"Please enter a valid dA into in 'More Detail' tab"},
			dcPercent:{digits:"Please enter a valid dC into in 'More Detail' tab"},
			dgPercent:{digits:"Please enter a valid dG into in 'More Detail' tab"},
			dtPercent:{digits:"Please enter a valid dT into in 'More Detail' tab"}
		},
		errorPlacement: function(error, element) {
		}
	});
	
	$("#useMixedBase").click(function(){
		var daPercent = $("#oligoDetailForm").find("[name='daPercent']").val();
		var dcPercent = $("#oligoDetailForm").find("[name='dcPercent']").val();
		var dgPercent = $("#oligoDetailForm").find("[name='dgPercent']").val();
		var dtPercent = $("#oligoDetailForm").find("[name='dtPercent']").val();
		if (isnumber(daPercent) && isnumber(dcPercent) && isnumber(dgPercent) && isnumber(dtPercent)) {
			var totalPercent = parseInt(daPercent) + parseInt(dcPercent) + parseInt(dgPercent) + parseInt(dtPercent);
			if (totalPercent != 100) {
				alert("The sum of da,dc, dg, dt must equal 100 in 'More Detail' tab.");
				return;
			} else {
				var useMixedBaseSun = "{["+daPercent+"%A"+dcPercent+"%C"+dgPercent+"%G"+dtPercent+"%T]}";
				var sequence = $("#oligoDetailForm").find("[name='sequence']").val();
				$("#oligoDetailForm").find("[name='sequence']").val(sequence + useMixedBaseSun);
			}
		} else {
			alert("da,dc, dg, dt must be digits in 'More Detail' tab.");
			return;
		}
	});		
	
	//Oligo 批量处理
	$("#oligoButchOligosForm").find("[id='batchOrderBtn']").click(function(){
		var batchBackbone = $("#oligoButchOligosForm").find("[id='batchBackbone']").val();
		if (batchBackbone == null || batchBackbone == "" || batchBackbone == undefined) {
			alert("Please select a Backbone.");
			return;
		}
		var batchSynthesisScales = $("#oligoButchOligosForm").find("[id='batchSynthesisScales']").val();
//		if (batchSynthesisScales == null || batchSynthesisScales == "" || batchSynthesisScales == undefined) {
//			alert("Please select a Synthesis Scales.");
//			return;
//		}
		var batchOligoSequence = $("#oligoButchOligosForm").find("[id='batchOligoSequence']").val();
		if(!check_batch_oligo_form('oligoFile', 'batchOligoSequence')) {
			return;
		}
		var batchModification5 = null;
		var batchPurification = null;
		var batchModification3 = null;
		if (batchBackbone.indexOf("RNA") >= 0) {
			batchPurification = $("#oligoButchOligosForm").find("[id='batchRnaPurification']").val();
			batchModification5 = $("#oligoButchOligosForm").find("[id='batchRnaModification5']").val();
			batchModification3 = $("#oligoButchOligosForm").find("[id='batchRnaModification3']").val();
		} else {
			batchPurification = $("#oligoButchOligosForm").find("[id='batchOtherPurification']").val();
			batchModification5 = $("#oligoButchOligosForm").find("[id='batchOtherModification5']").val();
			batchModification3 = $("#oligoButchOligosForm").find("[id='batchOtherModification3']").val();
		}
		if (batchPurification == null || batchPurification == "" || batchPurification == undefined) {
			alert("Please select a Purification.");
			return;
		}
		var batchAliquotingInto = $("#oligoButchOligosForm").find("[id='batchAliquotingInto']").val();
		if (batchAliquotingInto != undefined && batchAliquotingInto != "" && !isnumber(batchAliquotingInto)) {
			alert("Please enter a numeric Aliquoting into.");
			return;
		}
		var batchAliquotingSize = $("#oligoButchOligosForm").find("[id='batchAliquotingSize']").val();
		if (batchAliquotingSize != undefined && batchAliquotingSize != "" && checkRate(batchAliquotingSize) == false) {
			alert("Please enter a double nMol/Vial.");
			return;
		}
		saveBatchOligo(batchBackbone, batchSynthesisScales, batchPurification, batchOligoSequence, 
				batchModification5, batchModification3, batchAliquotingInto, batchAliquotingSize);
	});	
	
	//------------------Oligo end------------------------------//
	
	//------------------Sequencing start------------------------------//
	$("[name='all_seq_item']").click(function(){
		if ( $(this).attr( 'checked' ) === true ) {
			$( ':check [name*="seq_item"]' ).attr( 'checked' , true ) ;
		} else {
			$( ':check [name*="seq_item"]' ).attr( 'checked' , false ) ;
			$(this).attr( 'checked' , false ) ;
		}
	});
	
	//------------------Sequencing end--------------------------------//
	
	//------------------Mutation Libraries start------------------------------//
	$("#mutaLibForm").find("[name='tmplFlag']").click(function(){
		this.blur();
	});
	
	$("#mutaLibForm").find("[name='tmplFlag']").change(function(){
		var tmpIndex = getIndexByTitle("Template Info");
		if($(this).val() == "Y"){
			enableTabByTitle("Template Info");
			if(tmpIndex != -1) {
				showTab('dhtmlgoodies_tabView1', tmpIndex);
			}
			$("#mutaLibForm").find("[name='tgtVectorName'][value='Target vector same as template']").attr("disabled", false);
		} else {
			disableTabByTitle("Template Info");
			$("#mutaLibForm").find("[name='tgtVectorName'][value='Target vector same as template']").attr("disabled", true);
		}
	});
	
	$("#mutaLibForm").find("[name='tgtVectorName']").click(function(){
		try {
			this.blur();
		} catch (exception) {
			return false;
		}
	});
	
	$("#mutaLibForm").find("[name='tgtVectorName']").change(function(){
		var cloningFlag = "N";
		if ($(this).val() == "Other") {
			cloningFlag = "Y";
		}
		if(!checkMutaLib() || !saveCloning(cloningFlag)){
			$("#mutaLibForm").find("[name='tgtVectorName'][value='PCR products']").attr("checked", true);
			var tmplFlag = $("#mutaLibForm").find("[name='tmplFlag'][checked]").val();
			if(tmplFlag == "Y"){
				$("#mutaLibForm").find("[name='tgtVectorName'][value='Target vector same as template']").attr("disabled", false);
			}else if(tmplFlag == "N"){
				$("#mutaLibForm").find("[name='tgtVectorName'][value='Target vector same as template']").attr("disabled", true);
			}
            this.blur();
			return false;
		}
		var tmpIndex = getIndexByTitle("Cloning Strategy");
		if(cloningFlag == "Y"){
			enableTabByTitle("Cloning Strategy");
			if(tmpIndex != -1) {
				showTab('dhtmlgoodies_tabView1', tmpIndex);
			}
		}
	});
	
	$("#mutaLibForm").validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).removeClass(errorClass);
	    },
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            return false;
	        });
		},
		rules: {
			constructName:{required:true},
			interestSequence:{required:true}
		},
		messages: {
			constructName: {required:"Please enter the Construct name in 'More Detail','Mutation Libraries' tab"},
			interestSequence:{required:"Please enter the Sequence in 'More Detail','Mutation Libraries' tab"}
		},
		errorPlacement: function(error, element) {
		}
	});
	//------------------Mutation Libraries end------------------------------//
	
	//------------------Custom Service start------------------------------//
	$("#customServiceForm").validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).removeClass(errorClass);
	    },
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            return false;
	        });
		},
		rules: {
			customDesc:{required:true}
		},
		messages: {
			customDesc: {required:"Please enter the Description in 'More Detail' tab."}
		},
		errorPlacement: function(error, element) {
		}
	});
	//------------------Custom Service end------------------------------//
	
	//------------------ORF Clone start------------------------------//
	//选中要添加的accession到Orf Clone More Detail页面
	$('#accessionSelect').click(function(){
		var accessionSelectCheckBoxs = $(":checkbox[name*='service_check_'][checked=true]");
		if( accessionSelectCheckBoxs.size() != 0 ){
			accessionSelectCheckBoxs.each(function(){
				copyOrfCloneInfo(this);
        	});
		}else{
			var orfCloneListDialogObj = window.parent.$('#orfCloneListDialog') ;
			orfCloneListDialogObj.dialog( 'close' ) ;
		}
	});  
	
	$("#orfCloneForm").find("[name='pla_type_orf']").click(function(){
		var pla_type_orf = $(this).val();
		var orfCloneForm = $("#orfCloneForm");
		orfCloneForm.find("#txt_04").val("");
		orfCloneForm.find("#txt_04").hide();
		orfCloneForm.find("#prepUom").hide();
		orfCloneForm.find("#prepWeightSel").find("option[value='100 ug']").attr("selected", true);
		orfCloneForm.find("[name='qualityGrade'][value='SC Grade']").attr("checked", true);
		if (pla_type_orf == "Other") {
			orfCloneForm.find("[id='prepWeightSel']").attr("disabled", false);
			orfCloneForm.find("[name='qualityGrade']").attr("disabled", false);
		} else {
			orfCloneForm.find("[id='prepWeightSel']").attr("disabled", true);
			orfCloneForm.find("[name='qualityGrade']").attr("disabled", true);
		}
	});	
	
	$("#orfCloneForm").find("[name='save_orf_clone_btn']").click(function(){
		if (saveOrfClone() == true) {
			//刷新item list
			parent.document.getElementById("itemListIframe").src = orderquoteObj.url("getItemList");
		}
	});
	
	//从js/quoteorder/order_quotation_add.js拷贝而来,此方法通用于checkbox的name以service_check_开头
	$( '#serviceAllCheck' ).click(function(){
		if ( $(this).attr( 'checked' ) === true ){
			$( ':check [name*="service_check_"]' ).attr( 'checked' , true ) ;
		} else {
			$( ':check [name*="service_check_"]' ).attr( 'checked' , false ) ;
			$(this).attr( 'checked' , false ) ;
		}
	}) ;
	
	$("#orfCloneForm").find("[id='prepWeightSel']").change(function(){
		 if($(this).val()=="Other") {
		    $("#txt_04").show();
		    $("#txt_04").attr("disabled",false);
			$("#prepUom").show();
		 } else {
			$("#txt_04").hide(); 
			$("#txt_04").attr("disabled",true);
			$("#prepUom").hide();
		 }
	});
	//------------------ORF Clone end------------------------------//
	
	//copyItem
	$("#tmplForm").find("[id='copyItem']").click(function(){
		var itemId = $("#tmplForm").find("[name='itemId']").val();
		var parentId = $("#tmplForm").find("[id='parentId']").val();
		if (itemId == undefined || itemId == "" || (parentId != undefined && parentId != "")) {
			return;
		}
		copyItem();
	});	
	
	$("#targetForm").find("[name='tgtCloningMethod']").click(function (){
		if ($(this).val() == "Gateway") {
			$("#targetForm").find("[name='tgtCloningSite']").attr("disabled",true);
		} else {
			$("#targetForm").find("[name='tgtCloningSite']").attr("disabled",false);
		}
	});
	
	$("#cloningForm").find("[name='tgtCloningMethod']").click(function (){
		if ($(this).val() == "Gateway") {
			$("#cloningForm").find("[name='tgtCloningSite']").val("");
			$("#cloningForm").find("[name='tgtCloningSite']").attr("disabled",true);
		} else {
			$("#cloningForm").find("[name='tgtCloningSite']").attr("disabled",false);
		}
	});
	
	$("#targetForm").find("[name='tgtSeqSameFlag']").click(function (){
		var tgtSeqSameFlag = $("#targetForm").find("[name='tgtSeqSameFlag']").attr("checked");
		if (!$("#targetForm").find("[name='tgtSequence']")) {
			return;
		}
		if (tgtSeqSameFlag) {
			$("#targetForm").find("[name='tgtSequence']").attr("disabled","true");
		} else {
			$("#targetForm").find("[name='tgtSequence']").attr("disabled",false);
		}
	});
	
	
	$("#moreInfoForm").find("#fileListTable").click(function(e){
		var obj = e.target;
		if(obj.value == "Delete"){
			var filePath = $(obj).attr("filePath");
			var tmpId = itemId;
			var serviceName = getServiceName(show);
			deleteServiceFile(obj, filePath, serviceName, tmpId);
		}
	});
	
	$("#tmplForm").find("[name='tmplUploadBtn']").click(function(){
		var tmpId = $("#tmplForm").find("[name='itemId']").val();
		var refType = getRefType(show, 1);
		var serviceName = getServiceName(show);
		uploadServiceFile(tmpId, serviceName, "tmplForm", refType, this);
	});
	
	$("#tmplForm").find("[name='tmplCloningMethod']").click(function (){
		if ($(this).val() == "Gateway") {
			$("#tmplForm").find("[name='tmplCloningSite']").val("");
			$("#tmplForm").find("[name='tmplCloningSite']").attr("disabled",true);
		} else {
			$("#tmplForm").find("[name='tmplCloningSite']").attr("disabled",false);
		}
	});
	
	$("#tmplForm").find("#fileListTable").click(function(e){
		var obj = e.target;
		if(obj.value == "Delete"){
			var filePath = $(obj).attr("filePath");
			var tmpId = itemId;
			var serviceName = getServiceName(show);
			deleteServiceFile(obj, filePath, serviceName, tmpId);
		}
	});
	
	$("#cloningForm").find("[name='tgtVector']").change(function(){
		if($(this).val() == "Other"){
			var disFlag = false;
			document.getElementById("tgtVectorOther").style.display= "block"; 
		}else{
			var disFlag = true;
			document.getElementById("tgtVectorOther").style.display= "none";
		}
		$("#cloningForm").find("[name='tgtResistance']").find("option[value='']").attr("selected", true);
		document.getElementById("tgtResistanceOther").style.display= "none";
		$("#cloningForm").find("[name='tgtVectorSize']").val("");
		$("#cloningForm").find("[name='tgtVectorSeq']").val("");
		$("#cloningForm").find("[name='tgtCopyNo'][value='High']").attr("checked", true);
		$("#cloningForm").find("[name='fileListTable']").html("");
		$("#cloningForm").find("[name='tgtVectorSize']").attr("disabled", disFlag);
		$("#cloningForm").find("[name='tgtResistance']").attr("disabled", disFlag);
		$("#cloningForm").find("[name='tgtCopyNo']").attr("disabled", disFlag);
		$("#cloningForm").find("[name='tgtVectorSeq']").attr("disabled", disFlag);
		$("#cloningForm").find("[name='upload']").attr("disabled", disFlag);
		$("#cloningForm").find("[name='cloningUploadBtn']").attr("disabled", disFlag);
	});
	
	$("#cloningForm").find("[name='tgtResistance']").change(function(){
		if($(this).val() == "Other"){
			document.getElementById("tgtResistanceOther").style.display= "block"; 
		}else{
			document.getElementById("tgtResistanceOther").style.display= "none"; 
		}
	});
	
	$("#codonForm").find("[name='hostExpsOrganism']").change(function(){
		if($(this).val() == "Other"){
			document.getElementById("hostExpsOrgOther").style.display= "block"; 
		}else{
			document.getElementById("hostExpsOrgOther").style.display= "none"; 
		}
	});
	$("#codonForm").find("[name='scndExpsOrganism']").change(function(){
		if($(this).val() == "Other"){
			document.getElementById("scndExpsOrganismOther").style.display= "block"; 
		}else{
			document.getElementById("scndExpsOrganismOther").style.display= "none"; 
		}
	});
	
	$("#tmplForm").find("[name='tmplVector']").change(function(){
		if ($(this).val() == "Other") {
			var disFlag = false;
			document.getElementById("tmplVectorOther").style.display= "block"; 
		} else {
			var disFlag = true;
			$("#tmplForm").find("[name='tmplVectorOther']").val("");
			document.getElementById("tmplVectorOther").style.display= "none"; 
		}
		$("#tmplForm").find("[name='tmplResistance']").find("option[value='']").attr("selected", true);
		$("#tmplForm").find("[name='tmplResistanceOther']").val("");
		document.getElementById("tmplResistanceOther").style.display= "none";
		$("#tmplForm").find("[name='tmplVectorSize']").val("");
		$("#tmplForm").find("[name='tmplVectorSeq']").val("");
		$("#tmplForm").find("[name='tmplCopyNo'][value='High']").attr("checked", true);
		$("#tmplForm").find("[name='fileListTable']").html("");
		$("#tmplForm").find("[name='tmplResistance']").attr("disabled", disFlag);
		$("#tmplForm").find("[name='tmplVectorSize']").attr("disabled", disFlag);
		$("#tmplForm").find("[name='tmplCopyNo']").attr("disabled", disFlag);
		$("#tmplForm").find("[name='tmplVectorSeq']").attr("disabled", disFlag);
		$("#tmplForm").find("[name='upload']").attr("disabled", disFlag);
		$("#tmplForm").find("[name='tmplUploadBtn']").attr("disabled", disFlag);
	});
	$("#tmplForm").find("[name='tmplResistance']").change(function(){
		if($(this).val() == "Other"){
			document.getElementById("tmplResistanceOther").style.display= "block"; 
		}else{
			document.getElementById("tmplResistanceOther").style.display= "none"; 
		}
	});
	
	$("#geneUsageForm").find("[name='geneUsage']").click(function(){
		if($(this).val() == "Protein expression/analysis"){
			$("#geneUsageForm").find("#usage").show();
		}else{
			$("#geneUsageForm").find("#usage").hide();
		}
	});
	
	$("#geneUsageForm").find("[name='readingFrame']").click(function(){
		if($(this).val() == "Y"){
			$("#geneUsageForm").find("#geneUsageTextDiv").show();
		}else{
			$("#geneUsageForm").find("#geneUsageTextDiv").hide();
		}
	});
	
	$("#addServiceBtn").click(function(){
		if(saveMoreDetail("gene") == false){
			return false;
		}
		var tmpId = $("#geneSynthesisForm").find("#geneItemId").val();
		if (tmpId == undefined || $.trim(tmpId) == "") {
			tmpId = $("#geneSynthesisForm").find("[name='itemId']").val();
		}
		var geneCatalogNo = $("#geneSynthesisForm").find("#geneCatalogNo").val();
		var url = "qu_order!showProductProductRelationForm.action?moreDetailFlag=1&quorderNo="+orderquoteObj.sessNoValue+"&codeType="+orderquoteObj.type+"&parentId="+tmpId+"&geneCatalogNo="+geneCatalogNo;
		parent.$('#itemSelectDialog').attr( 'innerHTML' , '<iframe src="'+url+'" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="No" ></iframe>' ) ;
		parent.$('#itemSelectDialog').dialog( "option" , "title" , "Item Lookup" ) ; 
		parent.$('#itemSelectDialog').dialog( 'open' ) ;
	});
	
	$("#addMutaBtn").click(function(){
		if(saveMoreDetail("cloning") == false){
			return false;
		}
		var tmpId = $("#tmplForm").find("[name='itemId']").val();
		var geneCatalogNo = $("#tmplForm").find("#cloneCatalogNo").val();
		var url = "qu_order!showProductProductRelationForm.action?moreDetailFlag=1&quorderNo="+orderquoteObj.sessNoValue+"&codeType="+orderquoteObj.type+"&parentId="+tmpId+"&geneCatalogNo="+geneCatalogNo;
		parent.$('#itemSelectDialog').attr( 'innerHTML' , '<iframe src="'+url+'" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="No" ></iframe>' ) ;
		parent.$('#itemSelectDialog').dialog( "option" , "title" , "Item Lookup" ) ; 
		parent.$('#itemSelectDialog').dialog( 'open' ) ;
	});
	
	$("#geneSynthesisForm").find("#mutaListSpan").click(function(e){
		var obj = e.target;
		if($(obj).attr("name") == "mutaA"){
			var itemId = $(obj).attr("itemId");
			parent.window.frames["itemListIframe"].itemTrClick(parent.$("#itemListIframe").contents().find("#itemTable tr[itemId='"+itemId+"']"));
		}
	});
	//Protein
	$("[name='stepList']").change(function(){
		var des = $(this).find("option:selected").attr("description");
		var cost = $(this).find("option:selected").attr("cost");
		var price = $(this).find("option:selected").attr("price");
		var seqFlag = $(this).find("option:selected").attr("seqFlag");
		var itemName = $(this).parent().find("input[name='addSerPkgBtn']").attr("itemName");
		$(this).parent().find("input[name='cost']").val(cost);
		$(this).parent().find("input[name='price']").val(price);
		$(this).parent().next().find("textarea").val(des);
		if (seqFlag != undefined && seqFlag == 'Y') {
			var proteinAccession = "";
			if (itemName != undefined && itemName == "Molecular Biology") {
				proteinAccession = '&nbsp;&nbsp;&nbsp;&nbsp;<span class="css_b">Protein Name：</span>'
				   	+'<input type="text" value="" size="20" name="additionInfo1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
				   	+'<span class="css_b">Accession Number：</span><input type="text" value="" size="20" name="additionInfo2">';
			}
			$(this).parent().parent().parent().find("[id='text1_2']").html(
				'<span class="css_b">Gene Sequence</span>'
					+'<textarea name="sequence" onblur="if(value == \'\'){value=\'Enter Gene Sequence...\';}"' 
				   	+'onfocus="if(value ==\'Enter Gene Sequence...\') {value=\'\';}" class="content_textarea3 "'
				   	+'id="sequence${id}">Enter Gene Sequence...</textarea><br/><br/>'
				   	+'<span class="css_b">Protein Sequence</span>'
				   	+'<textarea name="proteinSeq" onblur="if(value == \'\'){value=\'Enter Protein Sequence...\';}"' 
				   	+'onfocus="if(value ==\'Enter Protein Sequence...\') {value=\'\';}" class="content_textarea3 "'
				   	+'id="proteinSeq${id}">Enter Protein Sequence...</textarea><br/><br/>'
				   	+proteinAccession
				   	+'&nbsp;&nbsp;&nbsp;&nbsp;<span class="css_b">bpCost：</span>'
				   	+'<input type="text" value="0.24" size="12" name="seqBpCost">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
				   	+'<span class="css_b">bpPrice：</span><input type="text" value="0.35" size="12" name="seqBpPrice">'
			);
		} else {
			$(this).parent().parent().parent().find("[id='text1_2']").html("");
		}
	});
	
	$("[name='addSerPkgBtn']").click(function(){
		var stepListId = $(this).attr("stepListId");
		var descriptionId = $(this).attr("descriptionId");
		var itemId = $(this).attr("itemId");
		var catalogNo = $(this).attr("catalogNo");
		var parentId = $(this).attr("parentId");
		var des = $(this).parent().next().find("textarea").val();
		var pkgName = $("#"+stepListId).val();
		var stepId = $("#"+stepListId).find(":selected").attr("stepId");
		var seqFlag = $("#"+stepListId).find(":selected").attr("seqFlag");
		var cost = $(this).parent().find('input[name="cost"]').val();
		var price = $(this).parent().find('input[name="price"]').val();
		var text1_2Div = $(this).parent().next().next();
		var sequence = "";
		var proteinSeq = "";
		var additionInfo1 = "";
		var additionInfo2 = "";
		var seqBpCost = "";
		var seqBpPrice = "";
		if (text1_2Div != undefined && text1_2Div.html() != null && text1_2Div.html() != "") {
			sequence = text1_2Div.find("[name='sequence']").val();
			proteinSeq = text1_2Div.find("[name='proteinSeq']").val();
			additionInfo1 = text1_2Div.find("[name='additionInfo1']").val();
			additionInfo2 = text1_2Div.find("[name='additionInfo2']").val();
			seqBpCost = text1_2Div.find("[name='seqBpCost']").val();
			seqBpPrice = text1_2Div.find("[name='seqBpPrice']").val();
		}
		if(savePkgService(parentId, itemId, pkgName, des, catalogNo, stepId, seqFlag, sequence, proteinSeq, 
				cost, price, additionInfo1, additionInfo2, seqBpCost, seqBpPrice)){
			$("select[name='stepList']").each(function() {
				var selId = $(this).attr('id');
				if (selId != undefined && selId != stepListId) {
					selId = selId.substring(7,selId.length);
					var imgSrc = $("img[id='img"+selId+"']").attr('src');
					if (imgSrc != undefined && imgSrc=="images/ad.gif") {
						openc('exce'+selId,'img'+selId);
					}
				}
			});
			$(this).attr("value", "Modify");
		}
	});
	
	$("#pkgForm").find("[name='demoDes']").change(function(){
		var parentDescription = $("#pkgForm").find("[id='parentDescription']");
		if ($(this).attr("checked")) {
			parentDescription.attr("value", parentDescription.attr("value")+$(this).val());
		} else {
			parentDescription.attr("value", "");
			$("#pkgForm").find("[name='demoDes']").attr("checked", false);
		}
	});
	//antibody
	$("#antigenType").change(function(){
		changeAntigenType($(this));
	});
	$(".invoice_title").live("click", toggleDiv);
	$("#antibodyNewPeptideBtn").click(function(){
		var antibodyItemId = $("#antibodyItemId").val();
		savePeptideForAntibody(antibodyItemId, "");
	});
	
	$("#antibodyPeptideContainer").find("input[name='name']").blur(function () {
		$("#antibodyPeptideContainer").find("input[name='name']").each(function(i){
			var name = $(this).attr("value");
			if(name != undefined && name != "" && trimAllBlank(name) != ""){
				$("#antibodyName").val("Anti-"+name);
			}
			return false;
		});
	});
	//*************************************peptide*******************************************************************************************
	//select sequecne for peptide
	$('[id="sequenceSelectTrigger"]').live('click', function(){
		var sequenceId = $(this).prevAll("textarea").attr("id");
		parent.$('#seqSelectDlg').data("sequenceId", sequenceId);
		parent.$('#seqSelectDlg').dialog('open');
	});
	
	//select sequecne for antibodyPeptide
	$('[id="antibodyPeptideSequenceSelectTrigger"]').live('click', function(){
		var sequenceId = $(this).prevAll("textarea").attr("id");
		parent.$('#seqSelectDlg').data("sequenceId", sequenceId);
		parent.$('#seqSelectDlg').dialog('open');
	});
	
	//validate sequecne for peptide
	$('[id="sequenceValidateTrigger"]').live('click', function(){
		var seq = $(this).parent().find("[name='sequence']").val();
		var standard = "ALA,ARG,ASN,ASP,CYS,GLU,GLN,GLY,HCY,HIS,HSE,ILE,LEU,LYS,MET,NLE,NVA,ORN,PEN,PHE,PRO,SER,THR,TRP,TYR,VAL,pGLU,gamma-GLU,";
		var nme = "ALA,ILE,LEU,PHE,VAL,SER,THR,TYR,";	
		var others = "dnp-LYS,pTHR,pTYR,pSER,CIT,alpha-ABA,iso-ASP,Ac-LYS,2-Me-ALA,OXA,";
		var single = "ACDEFGHIKLMNPQRSTUVWXY";
		//trim	
		var trim = /\s+/g;
		seq = seq.replace(trim,"");
		var warn = $(this).parent().find('#warnSeqCheck').get(0);
		if (seq.length == 0){
			warn.innerHTML = "<b><font color='red'>No amino acid sequence found</font></b>";
			return;
		}
		//check invalid character
		var reFirstCheck = /[^a-zA-Z\-\{\}2]/;
		var firstCheck = reFirstCheck.exec(seq);
		if (firstCheck){
			warn.innerHTML = "<b>Invalid character <font color='red'>" + firstCheck+'</font></b>';
			return;
		}
		var originalSeq = seq;
		//start to validate
		var re = /\{\S+?\}/g;
		var standardArray = new Array();
		seq = seq.replace(re,function(matched){
				standardArray.push(matched.substring(1,matched.length-1));
				return "";
			});
		var toChkSeq;
		var unMatchedSeq;
		var regCheck;
		
		for (var i=0; i<standardArray.length;i++){
			if ( standardArray[i].substring(0,2).toLowerCase() == 'd-'){
				toChkSeq = standardArray[i].substring(2);
				regCheck = new RegExp(toChkSeq+",","gi");
				if (standard.search(regCheck)>-1){
					continue;
				}else{
					unMatchedSeq = standardArray[i].substring(0,2)+ toChkSeq;
					break;
				}
			}else if (standardArray[i].substring(0,4).toLowerCase() == 'nme-'){
				toChkSeq = standardArray[i].substring(4);
				regCheck = new RegExp(toChkSeq+",","gi");
				if (nme.search(regCheck)>-1){
					continue;
				}else{
					unMatchedSeq = standardArray[i].substring(4)+ toChkSeq;
					break;
				}
			}else{
				toChkSeq = standardArray[i];
				regCheck = new RegExp(toChkSeq+",","gi");
				if (standard.search(regCheck)>-1){
					continue;
				}else if(others.search(regCheck)>-1){
					continue;
				}else{
					unMatchedSeq = toChkSeq;
					break;
				}
			}
		}
		if (unMatchedSeq){
			warn.innerHTML = "<b>Invalid sequence <font color='red'>" + '{'+unMatchedSeq+'}</font></b>';
			return;
		}

		for (var i=0;i<seq.length;i++){
			toChkSeq = seq.charAt(i);
			regCheck = new RegExp(toChkSeq,'gi');
			if (single.search(regCheck)== -1){
				unMatchedSeq = toChkSeq;
				break;
			}
		}
		if (unMatchedSeq){
			warn.innerHTML = "<b>Invalid sequence <font color='red'>" +unMatchedSeq +"</font></b>";
			return;
		}else{
			var seqSource = originalSeq.toUpperCase();
			var dGang = /D\-/g;
			seqSource = seqSource.replace(dGang,'d-');
			var pGLU = /PGLU/g;
			seqSource = seqSource.replace(pGLU,'pGLU');
			var pTHR = /PTHR/g;
			seqSource = seqSource.replace(pTHR,'pTHR');
			var pSER = /PSER/g;
			seqSource = seqSource.replace(pSER,'pSER');
			var pTYR = /PTYR/g;
			seqSource = seqSource.replace(pTYR,'pTYR');
			var gamma = /GAMMA\-/g;
			seqSource = seqSource.replace(gamma,'gamma-');
			var alpha = /ALPHA\-/g;
			seqSource = seqSource.replace(alpha,'alpha-');
			var iso = /ISO\-/g;
			seqSource = seqSource.replace(iso,'iso-');
			var ac = /AC\-/g;
			seqSource = seqSource.replace(ac,'Ac-');
			var nme = /NME\-/g;
			seqSource = seqSource.replace(nme,'nme-');
			var me = /\-ME\-/g;
			seqSource = seqSource.replace(me,'-Me-');
			var dnp = /DNP\-/g;
			seqSource = seqSource.replace(dnp,'dnp-');

			$("#stdPeptideForm").find("[name='sequence']").val(seqSource);
			warn.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;<b><font color='blue'>Validated.</font></b>";
		}
	});
	
	//validate sequecne for peptide
	$('[id="antibodyPeptideSequenceValidateTrigger"]').live('click', function(){
		var seq = $(this).parent().find("[name='sequence']").val();
		var standard = "ALA,ARG,ASN,ASP,CYS,GLU,GLN,GLY,HCY,HIS,HSE,ILE,LEU,LYS,MET,NLE,NVA,ORN,PEN,PHE,PRO,SER,THR,TRP,TYR,VAL,pGLU,gamma-GLU,";
		var nme = "ALA,ILE,LEU,PHE,VAL,SER,THR,TYR,";	
		var others = "dnp-LYS,pTHR,pTYR,pSER,CIT,alpha-ABA,iso-ASP,Ac-LYS,2-Me-ALA,OXA,";
		var single = "ACDEFGHIKLMNPQRSTUVWXY";
		//trim	
		var trim = /\s+/g;
		seq = seq.replace(trim,"");
		var warn = $(this).parent().find('#warnSeqCheck').get(0);
		if (seq.length == 0){
			warn.innerHTML = "<b><font color='red'>No amino acid sequence found</font></b>";
			return false;
		}
		//check invalid character
		var reFirstCheck = /[^a-zA-Z\-\{\}2]/;
		var firstCheck = reFirstCheck.exec(seq);
		if (firstCheck){
			warn.innerHTML = "<b>Invalid character <font color='red'>" + firstCheck+'</font></b>';
			return false;
		}
		var originalSeq = seq;
		//start to validate
		var re = /\{\S+?\}/g;
		var standardArray = new Array();
		seq = seq.replace(re,function(matched){
				standardArray.push(matched.substring(1,matched.length-1));
				return "";
			});
		var toChkSeq;
		var unMatchedSeq;
		var regCheck;
		
		for (var i=0; i<standardArray.length;i++){
			if ( standardArray[i].substring(0,2).toLowerCase() == 'd-'){
				toChkSeq = standardArray[i].substring(2);
				regCheck = new RegExp(toChkSeq+",","gi");
				if (standard.search(regCheck)>-1){
					continue;
				}else{
					unMatchedSeq = standardArray[i].substring(0,2)+ toChkSeq;
					break;
				}
			}else if (standardArray[i].substring(0,4).toLowerCase() == 'nme-'){
				toChkSeq = standardArray[i].substring(4);
				regCheck = new RegExp(toChkSeq+",","gi");
				if (nme.search(regCheck)>-1){
					continue;
				}else{
					unMatchedSeq = standardArray[i].substring(4)+ toChkSeq;
					break;
				}
			}else{
				toChkSeq = standardArray[i];
				regCheck = new RegExp(toChkSeq+",","gi");
				if (standard.search(regCheck)>-1){
					continue;
				}else if(others.search(regCheck)>-1){
					continue;
				}else{
					unMatchedSeq = toChkSeq;
					break;
				}
			}
		}
		if (unMatchedSeq){
			warn.innerHTML = "<b>Invalid sequence <font color='red'>" + '{'+unMatchedSeq+'}</font></b>';
			return false;
		}

		for (var i=0;i<seq.length;i++){
			toChkSeq = seq.charAt(i);
			regCheck = new RegExp(toChkSeq,'gi');
			if (single.search(regCheck)== -1){
				unMatchedSeq = toChkSeq;
				break;
			}
		}
		if (unMatchedSeq){
			warn.innerHTML = "<b>Invalid sequence <font color='red'>" +unMatchedSeq +"</font></b>";
			return false;
		}else{
			var seqSource = originalSeq.toUpperCase();
			var dGang = /D\-/g;
			seqSource = seqSource.replace(dGang,'d-');
			var pGLU = /PGLU/g;
			seqSource = seqSource.replace(pGLU,'pGLU');
			var pTHR = /PTHR/g;
			seqSource = seqSource.replace(pTHR,'pTHR');
			var pSER = /PSER/g;
			seqSource = seqSource.replace(pSER,'pSER');
			var pTYR = /PTYR/g;
			seqSource = seqSource.replace(pTYR,'pTYR');
			var gamma = /GAMMA\-/g;
			seqSource = seqSource.replace(gamma,'gamma-');
			var alpha = /ALPHA\-/g;
			seqSource = seqSource.replace(alpha,'alpha-');
			var iso = /ISO\-/g;
			seqSource = seqSource.replace(iso,'iso-');
			var ac = /AC\-/g;
			seqSource = seqSource.replace(ac,'Ac-');
			var nme = /NME\-/g;
			seqSource = seqSource.replace(nme,'nme-');
			var me = /\-ME\-/g;
			seqSource = seqSource.replace(me,'-Me-');
			var dnp = /DNP\-/g;
			seqSource = seqSource.replace(dnp,'dnp-');

			$(this).parent().find("[name='sequence']").val(seqSource);
			warn.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;<b><font color='blue'>Validated.</font></b>";
		}
		return true;
	});
	
	$('[id="formattingTrigger"]').live('click', function(){
		var seq = $(this).parent().find("[name='sequence']").val();
		//trim	
		var trim = /^\s+|\s+$/g;
		seq = seq.replace(trim,"");
		var seqArr = seq.split("\n");
		var num = 1;
		for (var i=0; i<seqArr.length;i++){
			var reg = /\s+/g;
			seqArr[i] = seqArr[i].replace(reg,"");
			if(seqArr[i].indexOf("|") != -1){
				continue;
			}else{
				seqArr[i] = "Peptide#"+num+'|'+seqArr[i];
				num++;
			}
		}
		var tseq  = seqArr.join('\n');
		$(this).parent().find("[name='sequence']").val(tseq);
	});
	initStatus();
//	$("#geneSynthesisForm").find("[name='stdVectorName']").attr("readonly","readonly");
});