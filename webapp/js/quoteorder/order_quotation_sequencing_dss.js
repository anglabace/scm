// JavaScript Document
//plate layout序列编号
var plateRNums = [];
var plateCNums = [];
var plateChar  = ['A','B','C','D','E','F','G','H'];
var a = 1;
var c = 0;
var t = 0;
for(;a<=12;a++){
	for(c=0;c<plateChar.length;c++){
		plateRNums[t++] = plateChar[c] + a;
	}
}
t = 0;
c = 0;
for(;c<plateChar.length;c++){
	for(a=1;a<=12;a++){
		plateCNums[t++] = plateChar[c] + a;
	}
}
//显示隐藏层
function showDiv(id,obj)
{
	$("div[id^='tab0']").removeClass();
	$("div[id^='tab0']").addClass("webone_contm_tabta");
	$(obj).removeClass();
	$(obj).addClass("webone_contm_tabtb");
	$("div[id^='ctab0']").hide();
	$("#ctab0"+id).show();
	$("table[id^='tab_detail']").hide();
	if(id==2){
		$("#tab_detail1").show();
	}
}
function showDetail(obj)
{
	var id = $(obj).parent().parent().parent().parent().parent().attr("id").split("_").pop();
	$("table[id^='tab_detail']").hide();
	$("#tab_detail"+id).show();
	var tObj  = $("#div_plate .plate_new");
	var tObj2 = $("#div_plate .plate_new2");
	
	tObj.removeClass();
	tObj.addClass("plate_new");
	tObj2.removeClass();
	tObj2.addClass("plate_new");
	$(obj).removeClass();
	$(obj).addClass("plate_new2");	
}
//定制 Tube Entry
function customTube(obj)
{
	//判断输入的数字是否正确
	var nums = parseInt($("#t_nums").val());
	if(nums<=0||isNaN(nums)){
		alert("Please input the correct nums.");	
		$("#t_nums").select();
		return false;
	}
	//(限制不超过100个)
	var maxSize = 100;
	if(maxSize<nums){
		alert("Even a hundred largest record.Pleas retype(<="+maxSize+").");
		return false;
	}
	$("#range_to").val(nums);
	$(obj).val("Customize");
	var start = 1;
	var obj   = null;
	var tbody = null;
	var td    = null;
	$("#tube_table").empty();
	var trHtml = '<tr><td width="22" bgcolor="#FFFFFF"><center>#</center></td>';
	trHtml += '<td width="70" bgcolor="#FFFFFF" valign="bottom" onmouseover="create(this,\'Please enter your primer name, maximum 8 characters, use only letters and numbers, do not use space, this is the name you will use to label your primer tubes; if you select to use Genscriptstandard primers, choose the primer from dropdown list.\');" style="cursor:pointer;" onMouseOut="del();"><center>Sample Name*<br /><a href="javascript:void(0);" onclick="setEQSampleName();"><img src="images/deng.jpg" border="0" /></a>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="setPSSampleName();"><img src="images/down.jpg" border="0" /></a></center></td>';
	trHtml += '';
	trHtml += '<td width="70" bgcolor="#FFFFFF" valign="bottom" onmouseover="create(this,\'Please choose from Plasmid, Purified PCR product and Unpurified PCR product.\');" style="cursor:pointer;" onMouseOut="del();"><center>Sample type*<br /><a href="javascript:void(0);" onclick="setEQSampleType();"><img src="images/deng.jpg" border="0" /></a></center></td>';
	trHtml += '';
	trHtml += '<td width="50" bgcolor="#FFFFFF" valign="bottom" onmouseover="create(this,\'Please enter your sample concentration by ng/&micro;l, please follow our sample submission guidelines to dilute your sample, if you are not sure about the concentration of your sample, choose Concentration Measurement option and we will measure it for you.\');" style="cursor:pointer;" onMouseOut="del();"><center>Sample Conc.<br /> ng/&micro;l<br /><a href="javascript:void(0);" onclick="setEQSampleConc();"><img src="images/deng.jpg" border="0" /></a></center></td>';
	trHtml += '<td valign="bottom" width="70" bgcolor="#FFFFFF" onmouseover="create(this,\'If this option is chosen, Genscript will perform concentration analysis on your sample and make dilution before loading it to reaction to make sure best reaction condition is reached, additional charges apply, see details in Pricing, terms and conditions page.\');" style="cursor:pointer;" onMouseOut="del();"><center>Concentration Measurement<br /><a href="javascript:void(0);" onclick="setEQConc();"><img src="images/deng.jpg" border="0" /></a></center></td>';
	trHtml += '<td width="70" valign="bottom" bgcolor="#FFFFFF" onmouseover="create(this,\'Please choose from premixed, standard or enclosed primers, if you already added primers to the samples, please select Premixed, if you would like to use the large selection of our Universal primers free of charge, select Standard, if you provide your own primers in a separate tube, please select Enclosed, please follow our sample submission guidelines for best primer concentration.\');" style="cursor:pointer;" onMouseOut="del();"><center>Primer Type*<br /><a href="javascript:void(0);" onclick="setEQPrimerType();"><img src="images/deng.jpg" border="0" /></a></center></td>';
	trHtml += '<td width="70" valign="bottom" bgcolor="#FFFFFF" onmouseover="create(this,\'Please enter your primer name, maximum 8 characters, use only letters and numbers, do not use space, this is the name you will use to label your primer tubes; if you select to use Genscriptstandard primers, choose the primer from dropdown list.\');" style="cursor:pointer;" onMouseOut="del();"><center>Primer Name*<br /><a href="javascript:void(0);" onclick="setEQPrimerName();"><img src="images/deng.jpg" border="0" /></a>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="setPSPrimerName();"><img src="images/down.jpg" border="0" /></a></center></td>';
	trHtml += '<td valign="bottom" width="42" bgcolor="#FFFFFF" onmouseover="create(this,\'If this option is chosen, Genscript will try different conditions to read your sample should it fails on first try, additional charges apply, see details in Pricing, terms and conditions page\');" style="cursor:pointer;" onMouseOut="del();"><center>Porwer Read<br /><a href="javascript:void(0);" onclick="setEQPorwerRead();"><img src="images/deng.jpg" border="0" /></a></center></td>';
	trHtml += '<td valign="bottom" width="46" bgcolor="#FFFFFF" onmouseover="create(this,\'If this option is chosen, Genscript will analyze your sequencing result, provide alignment and deliver the contig, you may provide reference sequence for the alignment by copy it into the box, additional charges apply, see details in Pricing, terms and conditions page\');" style="cursor:pointer;" onMouseOut="del();"><center>Data Analysis</center></td>';
	trHtml += '<td  valign="bottom" width="70" bgcolor="#FFFFFF" onmouseover="create(this,\'Please let us know if the sequence need any special handling or it is known to be difficult to sequence, Genscript have developed special technology to work around known issues such as high GC content or secondary structures.\');" style="cursor:pointer;" onMouseOut="del();"><center>Special Request<br /><a href="javascript:void(0);" onclick="setEQNotes();"><img src="images/deng.jpg" border="0" /></a></center></td>';
	trHtml += '<td width="40" valign="bottom" bgcolor="#FFFFFF" onmouseover="create(this,\'Please provide us the length of your template in base pairs (bp), this helps us in choosing the right reaction condition in order to get best reading\');" style="cursor:pointer;" onMouseOut="del();"><center>Template size(bp)<br /><a href="javascript:void(0);" onclick="setEQSampleSize();"><img src="images/deng.jpg" border="0" /></a></center></td>';
	trHtml += '<td valign="bottom" width="70" bgcolor="#FFFFFF" onmouseover="create(this,\'Please provide the name of the vector for plasmid DNA, this helps Genscript to choose best reaction condition for your sample.\');" style="cursor:pointer;" onMouseOut="del();"><center>Vector Name<br /><a href="javascript:void(0);" onclick="setEQVectorName();"><img src="images/deng.jpg" border="0" /></a></center></td>';
	trHtml += '<td valign="bottom" width="70" bgcolor="#FFFFFF" style="cursor:pointer;" ><center>Primer Conc<br /><a href="javascript:void(0);" onclick="setEQPrimerConc();"><img src="images/deng.jpg" border="0" /></a></center></td>';
	trHtml += '<td valign="bottom" width="70" bgcolor="#FFFFFF" style="cursor:pointer;" ><center>Resistance<br /><a href="javascript:void(0);" onclick="setEQResistance();"><img src="images/deng.jpg" border="0" /></a></center></td>';
	trHtml += '<td valign="bottom" width="70" bgcolor="#FFFFFF" style="cursor:pointer;" ><center>Price<br /><a href="javascript:void(0);" onclick="setEQPrice();"><img src="images/deng.jpg" border="0" /></a></center></td>';
	trHtml += '<td bgcolor="#FFFFFF">&nbsp;</td></tr>';
	$("#tube_table").html(trHtml);
	
	for(var i=1;i<=nums;i++){
		obj = $("#copy_tr").clone(true);
		obj.removeAttr("id");
		tbody = obj.children();
		//属性赋值
		td = tbody.children().children(); 
		//编号
		td.eq(0).html("<center>"+start+"</center>");
		//simple name
		td.eq(1).html('<input class="NFText" type="text" name="sample_name_'+start+'" style="width:100px;"/>');
		//sample type
		td.eq(2).children("select").eq(0).attr("name","sample_type_"+start);
		td.eq(2).children("select").eq(0).attr("id","sample_type_"+start);
		//sample conc
		td.eq(3).html('<input class="NFText" type="text" name="sample_conc_'+start+'" style="width:50px;"/>');
		//code
		td.eq(4).html('<input class="sInput" type="checkbox" name="conc_'+start+'" value="1" style="width:30px;"/>');
		//primer type
		td.eq(5).children("select").eq(0).attr("name","primer_type_"+start);
		td.eq(5).children("select").eq(0).attr("id","primer_type_"+start);
		//primer name
		td.eq(6).html('<input class="NFText myplist" type="text" name="primer_name_'+start+'" style="width:100px;"/>');
		//pwrwer read
		td.eq(7).html('<input type="checkbox" name="pwrwer_read_'+start+'"  class="sInput" style="width:30px;" value="1"/>');
		//data analysis
		td.eq(8).html('<input type="checkbox" name="data_analysis_'+start+'"  class="sInput" style="width:30px;"  value="1" onclick="referenceSequence(this);" /><span id="span_'+start+'" style="display: none;"><input class="NFText" style="height:50px;" type="text" name="ref_seq_'+start+'" onblur="hideSpan(\''+start+'\');" style="width:100px;"/></span>');
		//notes
		td.eq(9).children("select").eq(0).attr("name","notes_"+start);
		td.eq(9).children("select").eq(0).attr("id","notes_"+start);
		//sample size
		td.eq(10).html('<input class="NFText" type="text" name="sample_size_'+start+'" style="width:40px;"/>');
		//vector name
		td.eq(11).html('<input class="NFText" type="text" name="vector_name_'+start+'" style="width:70px;"/>');
		//Primer Conc
		td.eq(12).html('<input class="NFText" type="text" name="primer_conc_'+start+'" style="width:70px;"/>');
		//Resistance
		td.eq(13).html('<input class="NFText" type="text" name="resistance_'+start+'" style="width:70px;"/>');
		//price
		td.eq(14).html('<input class="NFText" type="text" name="price_'+start+'" style="width:70px;"/>');
		//sessItemKey
		td.eq(15).html('<img src="images/x.jpg" onclick="delTr(this);" /><input class="NFText" type="hidden" name="sessItemKey_'+start+'" style="width:70px;"/>');
		$("#tube_table tr:last").after(tbody.html());
		start++;
		obj = null;
	}
}

function isIE() {
//	if(isIE) {
//	   a = document.createElement("<a name=\""+ this.titleid + "\"></a>"); 
//	} else {
//	   var a = document.createElement("a");
//	   a.name = this.titleid;
//	}
	if(document.all) return true;
	return false;
}

//添加 Tube Entry
function addTube()
{
	//判断输入的数字是否正确
	var nums = parseInt($("#add_tube_nums").val());
	if(nums<=0||isNaN(nums)){
		alert("Please input the correct nums.");	
		$("#add_tube_nums").select();
		return false;
	}
	//查询目前已经有的记录(限制不超过100个)
	var maxSize = 100;
	var trSize = $("#tube_table tr").size() - 1;
	var hasSize = maxSize - trSize;
	if(hasSize<nums){
		alert("The maximum number of rows for tube is 100.");
		return false;
	}
	var start = trSize + 1;
	var obj   = null;
	var tbody = null;
	var td    = null;
	for(var i=1;i<=nums;i++){
		obj = $("#copy_tr").clone(true);
		obj.removeAttr("id");
		tbody = obj.children();
		//属性赋值
		td = tbody.children().children(); 
		//编号
		td.eq(0).html("<center>"+start+"</center>");
		//simple name
		td.eq(1).html('<input class="NFText" type="text" name="sample_name_'+start+'" style="width:100px;"/>');
		//sample type
		td.eq(2).children("select").eq(0).attr("name","sample_type_"+start);
		td.eq(2).children("select").eq(0).attr("id","sample_type_"+start);
		//sample conc
		td.eq(3).html('<input class="NFText" type="text" name="sample_conc_'+start+'" style="width:50px;"/>');
		//code
		td.eq(4).html('<input class="sInput" type="checkbox" value="1" name="conc_'+start+'" style="width:30px;"/>');
		//primer type
		td.eq(5).children("select").eq(0).attr("name","primer_type_"+start);
		td.eq(5).children("select").eq(0).attr("id","primer_type_"+start);
		//primer name
		td.eq(6).html('<input class="NFText myplist" type="text" name="primer_name_'+start+'" style="width:100px;"/>');
		//pwrwer read
		td.eq(7).html('<input type="checkbox" name="pwrwer_read_'+start+'"  class="sInput" style="width:30px;" value="1" />');
		//data analysis
		td.eq(8).html('<input type="checkbox" name="data_analysis_'+start+'"  class="sInput" style="width:30px;" value="1" onclick="referenceSequence(this);" /><span id="span_'+start+'" style="display: none;"><input class="NFText" style="height:50px;" type="text" name="ref_seq_'+start+'" onblur="hideSpan(\''+start+'\');" style="width:100px;"/></span>');
		//notes
		td.eq(9).children("select").eq(0).attr("name","notes_"+start);
		td.eq(9).children("select").eq(0).attr("id","notes_"+start);
		//sample size
		td.eq(10).html('<input class="NFText" type="text" name="sample_size_'+start+'" style="width:40px;"/>');
		//vector name
		td.eq(11).html('<input class="NFText" type="text" name="vector_name_'+start+'" style="width:70px;"/>');
		//Primer Conc
		td.eq(12).html('<input class="NFText" type="text" name="primer_conc_'+start+'" style="width:70px;"/>');
		//Resistance
		td.eq(13).html('<input class="NFText" type="text" name="resistance_'+start+'" style="width:70px;"/>');
		//price
		td.eq(14).html('<input class="NFText" type="text" name="price_'+start+'" style="width:70px;"/>');
		//sessItemKey
		td.eq(15).html('<img src="images/x.jpg" onclick="delTr(this);" /><input class="NFText" type="hidden" name="sessItemKey_'+start+'" style="width:70px;"/>');
		$("#tube_table tr:last").after(tbody.html());
		start++;
		obj = null;
	}
	if(trSize+nums<=100){
		$("#range_to").val(trSize+nums);
	}
}

//删除行
function delTrBak(obj)
{
	var tr = $(obj).parent().parent();
	var tbody = tr.parent();
	if(tbody.children().length==2){
		alert("Finally a banned removed");
		return false;	
	}
	//当前行号
	var line = parseInt(tr.children().eq(0).text());
	var nextAll = tr.nextAll();
	//删除当前行
	tr.remove();
	//当前行以下重新计数
	var nums  = nextAll.length;
	var start = 0;
	for(var i=0;i<nums;i++){
		start = line + i;
		//属性赋值
		td = nextAll.eq(i).children(); 
		//编号
		td.eq(0).html("<center>"+start+"</center>");
		//simple name
		td.eq(1).children().attr("name","sample_name_"+start);
		//sample type
		td.eq(2).children("select").eq(0).attr("name","sample_type_"+start);
		//sample conc
		td.eq(3).children().attr("name","sample_conc_"+start);
		//code
		td.eq(4).children().attr("name","code_"+start);
		//primer type
		td.eq(5).children("select").eq(0).attr("name","primer_type_"+start);
		//primer name
		td.eq(6).children().attr("name","primer_name_"+start);
		//pwrwer read
		td.eq(7).children().attr("name","pwrwer_read_"+start);
		//data analysis
		td.eq(8).children().eq(1).attr("name","ref_seq_"+start);
		td.eq(8).children().eq(0).attr("name","data_analysis_"+start);
		//notes
		td.eq(9).children("select").eq(0).attr("name","notes_"+start);
		//sample size
		td.eq(10).children().attr("name","sample_size_"+start);
		start++;
	}
}

//删除行
function delTr(obj)
{
	var tr = $(obj).parent().parent();
	var td = tr.children();
	//simple name
	td.eq(1).children().val("");
	//sample type
	td.eq(2).children("select").eq(0)[0].options[0].selected = true;
	//sample conc
	td.eq(3).children().val("");
	//code
	td.eq(4).children().attr("checked",false);
	//primer type
	td.eq(5).children("select").eq(0)[0].options[0].selected = true;
	//primer name
	td.eq(6).children().val("");
	//pwrwer read
	td.eq(7).children().attr("checked",false);
	//data analysis
	td.eq(8).children().eq(1).val("");
	td.eq(8).children().eq(0).attr("checked",false);
	//notes
	td.eq(9).children("select").eq(0)[0].options[0].selected = true;
	//sample size
	td.eq(10).children().val("");
	//vector name
	td.eq(11).children().val("");
	//primer Conc
	td.eq(12).children().val("");
	//resistance
	td.eq(13).children().val("");
	//price
	td.eq(14).children().val("");
}

//格式化数字
function formatNums(num,len)
{
	var size = 0; 
	num = num.toString();
	size = num.length;
	var diff = len - size;
	var t = '';
	for(var i=0;i<diff;i++){
		t += '0';	
	}
	num = t + num;
	return num;
}

//设置代码,以第一个为准
function setEQCode()
{
	var code = $("#ctab01 input[name^='code_']");
	var len  = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;	
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==""){
		code.eq(start).val("TUB0001");
	}
	var reg = /^[A-Z]{3}[0-9]{4}/;
	if(reg.test(code.eq(start).val())==false){
		alert("Please input the correct code.(e.g. TUB0001)");
		code.eq(start).select();
		return false;
	}
	//code的值要有全有否则全没有
	for(i=start+1;i<=end;i++){
		code.eq(i).val(code.eq(start).val());
	}
}

//设置代码,以第一个为准
function setEQConc()
{
	var code = $("#ctab01 input[name^='conc_']");
	var len  = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;	
		}
	}
	var check = code.eq(start).attr("checked");
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i).attr("checked",check);
	}
}

//设置代码,以第一个为准
function setPSCode()
{
	var code  = $("#ctab01 input[name^='code_']");
	var len   = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==""){
		code.eq(start).val("TUB0001");
	}
	var reg = /^[A-Z]{3}[0-9]{4}/;
	if(reg.test(code.eq(start).val())==false){
		alert("Please input the correct code.(e.g. TUB0001)");
		code.eq(start).select();
		return false;
	}
	var nums  = parseInt(code.eq(start).val().substr(3,4));
	var prev  = code.eq(start).val().substr(0,3);
	var index = 0;
	
	//code的值要有全有否则全没有
	var t = 1;
	for(i=start+1;i<=end;i++){
		index = nums + t++;
		index = formatNums(index,4);
		code.eq(i).val(prev+index);
	}
}

//设置Sample Name,以第一个为准
function setEQSampleName()
{
	var code = $("#ctab01 input[name^='sample_name_']");
	var len  = code.length;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==""){
		alert("Please input the correct sample name.");
		code.eq(start).select();
		return false;
	}
	//code的值要有全有否则全没有
	for(i=start+1;i<=end;i++){
		code.eq(i).val(code.eq(start).val());
	}
}

//设置代码,以第一个为准
function setPSSampleName()
{
	var code  = $("#ctab01 input[name^='sample_name_']");
	var len   = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
		}
		if(start>len){
			start = len - 1;	
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==""){
		alert("Please input the correct sample name.");
		code.eq(start).select();
		return false;
	}
	var nums  = 0;
	var prev  = code.eq(start).val();
	var index = 0;
	
	//code的值要有全有否则全没有
	var t = 1;
	for(i=start;i<=end;i++){
		index = nums + t++;
		index = formatNums(index,2);
		code.eq(i).val(prev+index);
	}
}

//设置Sample Type,以第一个为准
function setEQSampleType()
{
	var code = $("#ctab01 select[id^='sample_type_']");
	var len  = code.length;
	if (len == 0) {
		code = $("#ctab01 select[name^='sample_type_']");
		len  = code.length;
	}
	var start = 0;
	var end   = len -1;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end   = len - 1;	
			start = 0;	
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==0){
		alert("Please select the correct sample type.");
		code.eq(start).select();
		return false;
	}
	var sId = code.eq(start)[0].selectedIndex;
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i)[0].options[sId].selected = true;
	}
}

//设置Sample Conc,以第一个为准
function setEQSampleConc()
{
	var code = $("#ctab01 input[name^='sample_conc_']");
	var len  = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;	
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==""){
		code.eq(start).val("100");
	}
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i).val(code.eq(start).val());
	}
}

//设置Sample Size,以第一个为准
function setEQSampleSize()
{
	var code = $("#ctab01 input[name^='sample_size_']");
	var len  = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;	
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==""){
		code.eq(start).val("10");
	}
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i).val(code.eq(start).val());
	}
}

//设置Vector name,以第一个为准
function setEQVectorName()
{
	var code = $("#ctab01 input[name^='vector_name_']");
	var len  = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;	
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==""){
		code.eq(start).val("10");
	}
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i).val(code.eq(start).val());
	}
}

//设置Sample Type,以第一个为准
function setEQPrimerType()
{
	var code = $("#ctab01 select[id^='primer_type_']");
	var len  = code.length;
	if (len == 0) {
		code = $("#ctab01 select[name^='primer_type_']");
		len  = code.length;
	}
	var start = 0;
	var end   = len -1;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end   = len - 1;	
			start = 0;	
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==0){
		alert("Please select the correct primer type.");
		code.eq(start).select();
		return false;
	}
	var sId = code.eq(start)[0].selectedIndex;
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i)[0].options[sId].selected = true;
	}
	//判断是不是选择了Standard (Free)
	var selectList;
	if(code.eq(start).val()=="Standard (Free)"){
		for(i=start;i<=end;i++){
			selectList = $("#primer_list").clone(true);
			selectList.children("select").attr("name","primer_name_"+(i+1));
			code.eq(i).parent().next().html(selectList.html());
		}
	}
	else{
		for(i=start;i<=end;i++){
			code.eq(i).parent().next().html('<input class="NFText myplist" type="text" name="primer_name_'+(i+1)+'" style="width:100px;"/>');
		}
	}		
}

//设置Primer Name,以第一个为准
function setEQPrimerName()
{
	var code = $("#ctab01").find("[name^='primer_name_']");
	if(code.length==0){
		code = $("#ctab01 select[class^='custClon myplist']");
	}
	var len  = code.length;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;
		}
	}
	if(code.eq(start).attr("type")=="text"){
		//如果为空系统自动生成
		if(code.eq(start).val()==""){
			alert("Please input the correct primer name.");
			code.eq(start).select();
			return false;
		}
		//code的值要有全有否则全没有
		for(i=start+1;i<=end;i++){
			code.eq(i).val(code.eq(start).val());
		}
	}else{
		if(code.eq(start).val()==0){
			alert("Please select the correct primer name.");
			code.eq(start).select();
			return false;
		}
		var sId = code.eq(start)[0].selectedIndex;
		//code的值要有全有否则全没有
		for(i=start;i<=end;i++){
			try {
				code.eq(i)[0].options[sId].selected = true;
			} catch (exception) {
				code.eq(i)[0].value = code.eq(start)[0].value;
			}
		}
	}
}

//设置代码,以第一个为准
function setPSPrimerName()
{
	var code  = $("#ctab01 input[name^='primer_name_']");
	var len   = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
		}
		if(start>len){
			start = len - 1;	
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==""){
		alert("Please input the correct primer name.");
		code.eq(start).select();
		return false;
	}
	var nums  = 0;
	var prev  = code.eq(start).val();
	var index = 0;
	
	//code的值要有全有否则全没有
	var t = 1;
	for(i=start;i<=end;i++){
		index = nums + t++;
		index = formatNums(index,2);
		code.eq(i).val(prev+index);
	}
}

//设置Porwer Read,以第一个为准
function setEQPorwerRead()
{
	var code = $("#ctab01 input[name^='pwrwer_read_']");
	var len  = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;	
		}
	}
	var check = code.eq(start).attr("checked");
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i).attr("checked",check);
	}
}


//设置Data Analysis,以第一个为准
function setEQDataAnalysis()
{
	var code = $("#ctab01 input[name^='data_analysis_']");
	var len  = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;	
		}
	}
	var check = code.eq(start).attr("checked");
	var name  = "";
	var input = "";
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i).attr("checked",check);
		if(check){
			name  = 	code.eq(i).attr("name");
			name  = name.replace("data_analysis_","ref_seq_");
			input = '<input class="NFText" type="text" name="'+name+'" style="width:100px;"/>';
			code.eq(i).next().remove();
			code.eq(i).after(input);
		}else{
			code.eq(i).next().remove();
		}
	}
}

//设置Sample Type,以第一个为准
function setEQNotes()
{
	var code = $("#ctab01 select[id^='notes_']");
	var len  = code.length;
	if (len == 0) {
		code = $("#ctab01 select[name^='notes_']");
		len  = code.length;
	}
	var start = 0;
	var end   = len -1;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end   = len - 1;	
			start = 0;	
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==0){
		alert("Please select the correct notes.");
		code.eq(start).select();
		return false;
	}
	var sId = code.eq(start)[0].selectedIndex;
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i)[0].options[sId].selected = true;
	}
}

//设置Primer Name
function selectPrimerName(obj)
{
	var val     = $(obj).val();
	var td      = $(obj).parent().next();
	var input   = $(obj).parent().next().children("input");
	var selectO = $(obj).parent().next().children("select");
	var selectList = $("#primer_list").clone(true);
	if(input.length==0){
		var name = selectO.attr("name");
	}else{
		var name = input.attr("name");
	}
	if(val!=0){
		if(val=="Standard (Free)"){
			selectList.children("select").attr("name",name);
			td.html(selectList.html());
		}else{
			td.html('<input class="NFText myplist"  type="text" name="'+name+'" id="'+name+'" style="width:100px;"/>');
		}
	}else{
		td.html('<input class="NFText myplist" type="text" name="'+name+'" id="'+name+'" style="width:100px;"/>');
	}
}

//设置Primer Conc,以第一个为准
function setEQPrimerConc()
{
	var primerConc = $("#ctab01 input[name^='primer_conc']");
	var len  = primerConc.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;	
		}
	}
	//primerConc的值要有全有否则全没有
	for(i=start;i<=end;i++){
		primerConc.eq(i).val(primerConc.eq(start).val());
	}
}

//设置Resistance,以第一个为准
function setEQResistance()
{
	var resistance = $("#ctab01 input[name^='resistance']");
	var len  = resistance.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;	
		}
	}
	//resistance的值要有全有否则全没有
	for(i=start;i<=end;i++){
		resistance.eq(i).val(resistance.eq(start).val());
	}
}

//设置price,以第一个为准
function setEQPrice()
{
	var price = $("#ctab01 input[name^='price']");
	var len  = price.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='range']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#range_from").val()) - 1;
		end   = parseInt($("#range_to").val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;	
		}
	}
	//price的值要有全有否则全没有
	for(i=start;i<=end;i++){
		price.eq(i).val(price.eq(start).val());
	}
}

//显示reference sequence
function referenceSequence(obj)
{
	var name = $(obj).attr("name");
	name = name.replace("data_analysis_","ref_seq_");
	var spanId = name.replace("ref_seq_","");
	var input = "";
	var refSeq = $("#span_"+spanId);
	if (refSeq.length>0) {
		document.getElementById("span_"+spanId).style.display= "block"; 
	} else {
		input = '<span id="span_'+spanId+'"><input class="NFText" style="height:50px;" type="text" name="'+name+'" onblur="hideSpan(\''+spanId+'\');" style="width:100px;"/></span>';
	}
	if($(obj).attr("checked")){
		$(obj).after(input);	
	}else{
		$(obj).nextAll().remove();
		input = '<span id="span_'+spanId+'" style="display: none;"><input class="NFText" style="height:50px;" type="text" name="'+name+'" onblur="hideSpan(\''+spanId+'\');" style="width:100px;"/></span>';
		$(obj).after(input);	
	}
}

function hideSpan(id)
{
	$("#span_"+id).hide();
	$("#span_"+id).after("<a href='javascript:void(0);' onclick='showSpan(\""+id+"\");'>"+$("#span_"+id+" > input").val().substr(0,5)+"</a>");
}

function showSpan(id)
{
	$("#span_"+id).show();
	$("#span_"+id).next().remove();
	$("#span_"+id+" > input").focus();
}

//tube submit
function tubeSubmit()
{
	var len = $("#tube_table tr").length;
	$("#tube_len").val(len-1);
	//表单提交验证
	//sample name是否为空
	var sampleName = 	$("#tube_table input[name^='sample_name_']");
	var sampleType = 	$("#tube_table select[id^='sample_type_']");
	var primerType = 	$("#tube_table select[id^='primer_type_']");
	var primerName = 	$("#tube_table .myplist");
	var sampleConc = 	$("#tube_table input[name^='sample_conc_']");
	var conc = 			$("#tube_table input[name^='conc_']");
	var pwrwerRead = 	$("#tube_table input[name^='pwrwer_read_']");	
	var dataAnalysis = 	$("#tube_table input[name^='data_analysis_']");	
	var refSeq = 		$("#tube_table input[name^='ref_seq_']");	
	var notes = 		$("#tube_table select[id^='notes_']");
	var sampleSize = 	$("#tube_table input[name^='sample_size_']");
	var vectorName = 	$("#tube_table input[name^='vector_name_']");
	var primerConc = 	$("#tube_table input[name^='primer_conc_']");
	var resistance = 	$("#tube_table input[name^='resistance_']");
	var price = 		$("#tube_table input[name^='price_']");
	var sessItemKey = 	$("#tube_table input[name^='sessItemKey_']");
	for(var i=0;i<len - 1;i++){
		if(sampleName.eq(i).val()==""){
			alert("Please input sample name!");
			sampleName.eq(i).focus();
			return false;
		}
		if(sampleType.eq(i).val()=="0"){
			alert("Please select sample type!");	
			sampleType.eq(i).focus();
			return false;
		}
		var checked = conc.eq(i).attr("checked");
		if(isNaN(sampleConc.eq(i).val()) && checked==false){
			alert("please enter correct number for Sample Conc!");	
			sampleConc.eq(i).select();
			return false;
		}
		if(isNaN(sampleSize.eq(i).val())){
			alert("please enter correct number for template size!");	
			sampleSize.eq(i).select();
			return false;
		}
		if(primerType.eq(i).val()=="0"){
			alert("Please select primer type!");	
			primerType.eq(i).focus();
			return false;
		}
		if(primerType.eq(i).val()!="Standard (Free)"){
			if(primerName.eq(i).val()==""){
				alert("Please input primer name!");
				primerName.eq(i).focus();
				return false;
			}
		}else{
			if(primerName.eq(i).val()=="0"){
				alert("Please select primer name!");	
				primerName.eq(i).focus();
				return false;
			}
		}
		if(sampleType.eq(i).val()=="Unpurified PCR" && primerType.eq(i).val()=="Premixed"){
			alert("the primer type cannot be Premixed for Unpurified PCR samples!");	
			primerType.eq(i).focus();
			return false;
		}
		
		if(sampleConc.eq(i).val()=="" && checked==false){
			if(confirm("you have not specify concentration of sample #"+i+"#, we need to know the concentration before running the sample, do you want Genscript to run concentration test for you?\n Click OK to add concentration test to your order, click cancel to go back and input concentration!")){	
				conc.eq(i).attr("checked",true);
			}else{
				sampleConc.eq(i).focus();
				return false;
			}
		}
		if(isNaN(price.eq(i).val())){
			alert("please enter correct number for price!");	
			price.eq(i).focus();
			return false;
		}
	}
	//查询有多少条记录
	var primerType = $("#tube_table select[id^='primer_type_']");
	var primerNameForLabe = [];
	var t          = '';
	for(var i=1;i<=primerType.length;i++){
		if(primerType.eq(i-1).val()=="Enclosed"){
			primerNameForLabe.push($("input[name='primer_name_"+i+"']").val());
		}else{
			primerNameForLabe.push(0);
		}
	}
	//去除重复
	for(var i=0;i<primerNameForLabe.length;i++){
		for(var j=i+1;j<primerNameForLabe.length;j++){
			if(primerNameForLabe[i]==primerNameForLabe[j]){
				primerNameForLabe[j] = 0;
			}
		}
	}
	var showHtml = '';
	var label    = 'LAB';
	var code     = '';
	for(var i=0;i<primerNameForLabe.length;i++){
		//label编号
		code = label + formatNums(i+1,2);
		if(primerNameForLabe[i]!=0){
			showHtml += '<tr><td bgcolor="#FFFFFF"><center>'+primerNameForLabe[i]+'</center></td><td bgcolor="#FFFFFF"><center>'+code+'</center></td></tr>';
			$("#labe_code").val($("#labe_code").val()+','+code);
			$("#labe_name").val($("#labe_name").val()+','+primerNameForLabe[i]);
		}
	}
	
	//保存DNA Sequencing, Save data to session  add by Zhang Yong 2011-11-03
	var sampleNameArr = [];
	var sampleTypeArr = [];
	var sampleConcArr = [];
	var flagConcMeasArr = [];
	var primerTypeArr = [];
	var primerNameArr = [];
	var flagPowerReadArr = [];
	var flagDataAnasArr = [];
	var dataAnasArr = [];
	var specialRequestArr = [];
	var templateSizeArr = [];
	var vectorNameArr = [];
	var primerConcArr = [];
	var resistanceArr = [];
	var priceArr = [];
	var sessItemKeyArr = [];
	var commentArr = [];
	var sampleName_DB = "";
	var sampleType_DB = "";
	var sampleConc_DB = "";
	var flagConcMeas_DB = 0;
	var primerType_DB = "";
	var primerName_DB = "";
	var flagPowerRead_DB = 0;
	var flagDataAnas_DB = 0;
	var dataAnas_DB = "";
	var specialRequest_DB = "";
	var templateSize_DB = 0;
	var vectorName_DB = "";
	var primerConc_DB = "";
	var resistance_DB = "";
	var price_DB = null;
	var sessItemKey_DB = "";
	var comment_DB = $("#comment").val();
	for(var i=0;i<len - 1;i++){
		sampleName_DB = sampleName.eq(i).val();
		sampleType_DB = sampleType.eq(i).val();
		sampleConc_DB = sampleConc.eq(i).val();
		flagConcMeas_DB = 0;
		if (conc.eq(i).attr("checked")) {
			flagConcMeas_DB = 1;
		}
		primerType_DB = primerType.eq(i).val();
		primerName_DB = primerName.eq(i).val();
		flagPowerRead_DB = 0;
		if (pwrwerRead.eq(i).attr("checked")) {
			flagPowerRead_DB = 1;
		}
		flagDataAnas_DB = 0;
		if (dataAnalysis.eq(i).attr("checked")) {
			flagDataAnas_DB = 1;
		}
		dataAnas_DB = refSeq.eq(i).val();
		if (dataAnas_DB == undefined) {
			dataAnas_DB = "";
		}
		specialRequest_DB = notes.eq(i).val();
		if(!isNaN(sampleSize.eq(i).val())){
			templateSize_DB = sampleSize.eq(i).val();
		}
		vectorName_DB = vectorName.eq(i).val();
		primerConc_DB = primerConc.eq(i).val();
		resistance_DB = resistance.eq(i).val();
		if(!isNaN(price.eq(i).val())){
			price_DB = price.eq(i).val();
		}
		if (sessItemKey.eq(i) == undefined || sessItemKey.eq(i).val() == undefined) {
			sessItemKey_DB = "";
		} else {
			sessItemKey_DB = sessItemKey.eq(i).val();
		}
		sampleNameArr.push(sampleName_DB);
		sampleTypeArr.push(sampleType_DB);
		sampleConcArr.push(sampleConc_DB);
		flagConcMeasArr.push(flagConcMeas_DB);
		primerTypeArr.push(primerType_DB);
		primerNameArr.push(primerName_DB);
		flagPowerReadArr.push(flagPowerRead_DB);
		flagDataAnasArr.push(flagDataAnas_DB);
		dataAnasArr.push(dataAnas_DB);
		specialRequestArr.push(specialRequest_DB);
		templateSizeArr.push(templateSize_DB);
		vectorNameArr.push(vectorName_DB);
		primerConcArr.push(primerConc_DB);
		resistanceArr.push(resistance_DB);
		priceArr.push(price_DB);
		sessItemKeyArr.push(sessItemKey_DB);
		commentArr.push(comment_DB);
	}
	var saveMsg;
	var urlStr = "";
	urlStr = orderquoteObj.type+"_more!saveTubeDNASequencing.action?"+orderquoteObj.sessNoName+"="+orderquoteObj.sessNoValue;
	if ("quote" == orderquoteObj.type) {
		urlStr = orderquoteObj.type+"_more!saveTubeDNASequencing.action?"+orderquoteObj.sessNoName+"="+orderquoteObj.sessNoValue;
	}
	var dnsTube = {"sampleNameArr":sampleNameArr,"sampleTypeArr":sampleTypeArr,"sampleConcArr":sampleConcArr,
		"flagConcMeasArr":flagConcMeasArr,"primerTypeArr":primerTypeArr,"primerNameArr":primerNameArr,
		"flagPowerReadArr":flagPowerReadArr,"flagDataAnasArr":flagDataAnasArr,"dataAnasArr":dataAnasArr,
		"specialRequestArr":specialRequestArr,"templateSizeArr":templateSizeArr,"vectorNameArr":vectorNameArr,
		"primerConcArr":primerConcArr,"resistanceArr":resistanceArr,"priceArr":priceArr,"sessItemKeyArr":sessItemKeyArr,
		"commentArr":commentArr};
	$.ajax({
	    type: "POST",
	    url: urlStr,
	    data: dnsTube,
	    dataType: "json",
	    async: false,
	    success: function(rs){
	    	if (rs != undefined && rs.message != undefined) {
	    		saveMsg = rs.message;
	    	} else {
				saveMsg = true;
				alert("Your Tube have been saved successful!");
				//刷新item list
				parent.document.getElementById("itemListIframe").src = orderquoteObj.url("getItemList");
	    	}
		},
		error: function(data){
			saveMsg = data.responseText;
		}
	});
	return false;
}

//编辑提交验证
function tubeEdit()
{
	if($("#sample_name").val()==""){
		alert("Please input the sample name.");
		$("#sample_name").focus();
		return false;
	}
	if($("#sample_type").val()==0){
		alert("Please select the sample type.");
		$("#sample_type").focus();
		return false;
	}
	if($("#primer_type").val()==0){
		alert("Please select the primer type.");
		$("#primer_type").focus();
		return false;
	}
	if($("#primer_name").val()==0||$("#primer_name").val()==""){
		alert("Please input the primer name.");
		$("#primer_name").focus();
		return false;
	}
	
	return true;
}

//设置Edit Primer Name
function selectPrimerNameC(obj)
{
	var val        = $(obj).val();
	var td         = $(obj).parent().next();
	var selectList = $("#primer_list").clone(true);
	if(val!=0){
		if(val=="Standard (Free)"){
			td.html(selectList.html());
		}else{
			td.html('<input class="NFText myplist" type="text" name="primer_name" id="primer_name" style="width:100px;"/>');
		}
	}else{
		td.html('<input class="NFText myplist" type="text" name="primer_name" id="primer_name" style="width:100px;"/>');
	}
}

//显示reference sequence
function referenceSequenceE(obj)
{
	var input = '<input class="NFText" type="text" name="ref_seq" style="width:100px;"/>';
	if($(obj).attr("checked")){
		$(obj).after(input);	
	}else{
		$(obj).nextAll().remove();
	}
}

//订制板子
function customPlate(obj)
{
	var nums = $("#plate_nums").val();
	if(nums==""||isNaN(nums)){
		alert("Please input the correct nums");
		$("#plate_nums").select();
		return false;
	}
	var plateObj = $("#copy_plate").clone(true);
	$("#div_plate").empty();
	//添加plate
	for(var i=1;i<=nums;i++){
		$("#div_plate").append("<div class='tube_line' id='plate_div_"+i+"'>"+plateObj.html()+"</div>");
	}
	var detailObj;
	//添加plage detail
	$("#plate_form").empty();
	for(i=1;i<=nums;i++){
		detailObj = $("#plate_detail").clone(true);
		bTab = detailObj.find("table[name='tab_detail']");
		bTab.removeAttr("name");
		bTab.attr("id","tab_detail"+i);
		//添加行数量
		bInp = bTab.find("input[name='add_p_tube_nums']");
		bInp.removeAttr("name");
		bInp.attr("id","add_p_tube_nums_"+i);
		//详情表单
		bT = bTab.find("table[name='plate_table']");
		bT.removeAttr("name");
		bT.attr("id","plate_table_"+i);
		//p_code
		//pc = bTab.find("input[name='p_code']");
		//pc.attr("name","p_code_"+i);
		//pc.attr("id","p_code_"+i);
		//p_name
		pn = bTab.find("input[name='p_name']");
		pn.attr("name","p_name_"+i);
		pn.attr("id","p_name_"+i);
		//plate_layout
		pn = bTab.find("select[name='plate_layout']");
		pn.attr("name","plate_layout_"+i);
		pn.attr("id","plate_layout_"+i);
		//plate_range
		pr = bTab.find("input[name='p_range']");
		pr.attr("name","p_range_"+i);
		//p_range_from
		pf = bTab.find("input[name='p_range_from']");
		pf.attr("name","p_range_from_"+i);
		pf.attr("id","p_range_from_"+i);
		//p_range_to
		pt = bTab.find("input[name='p_range_to']");
		pt.attr("name","p_range_to_"+i);
		pt.attr("id","p_range_to_"+i);
		if(i!=1){
			bTab.hide();
		}
		$("#plate_form").append(bTab);
		addPTubeII(i);
	}
	$(obj).val("Customize");
	var tObj  = $("#div_plate .plate_new");
	tObj.eq(0).removeClass().addClass("plate_new2");
	$("#plate_form").append('<table border="0" cellspaceing="0" cellpadding="0"><tr><td><input type="hidden" name="plate_id_nums" id="plate_id_nums" value="0" /><input type="hidden" name="plate_item_nums" id="plate_item_nums" /><input type="hidden" name="op" value="add_plate" /><input type="image" src="images/imgSave.gif" /></td></tr></table>');
}

//设置Sample Name,以第一个为准
function setPEQSampleName(obj)
{
	var id   = $(obj).parent().parent().parent().parent().parent().attr("id").split("_").pop();
	var code = $(obj).parent().parent().parent().parent().find("input[name^='sample_name_"+id+"_']");
	var len  = code.length;
	if($("input[name='p_range_"+id+"']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#p_range_from_"+id).val()) - 1;
		end   = parseInt($("#p_range_to_"+id).val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==""){
		alert("Please input the correct sample name.");
		code.eq(start).select();
		return false;
	}
	//code的值要有全有否则全没有
	for(i=start+1;i<=end;i++){
		code.eq(i).val(code.eq(start).val());
	}
}

//设置代码,以第一个为准
function setPPSSampleName(obj)
{
	var id   = $(obj).parent().parent().parent().parent().parent().attr("id").split("_").pop();
	var code  = $(obj).parent().parent().parent().parent().find("input[name^='sample_name_"+id+"_']");
	var len   = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='p_range_"+id+"']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#p_range_from_"+id).val()) - 1;
		end   = parseInt($("#p_range_to_"+id).val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
		}
		if(start>len){
			start = len - 1;	
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==""){
		alert("Please input the correct sample name.");
		code.eq(start).select();
		return false;
	}
	var nums  = 0;
	var prev  = code.eq(start).val();
	var index = 0;
	
	//code的值要有全有否则全没有
	var t = 1;
	for(i=start;i<=end;i++){
		index = nums + t++;
		index = formatNums(index,2);
		code.eq(i).val(prev+index);
	}
}

//设置Sample Type,以第一个为准
function setPEQSampleType(obj)
{
	var id   = $(obj).parent().parent().parent().parent().parent().attr("id").split("_").pop();
	var code = $(obj).parent().parent().parent().parent().find("select[name^='sample_type_"+id+"_']");
	var len  = code.length;
	if (len == 0) {
		code = $(obj).parent().parent().parent().parent().find("select[id^='sample_type_"+id+"_']");
		len  = code.length;
	}
	var start = 0;
	var end   = len -1;
	if($("input[name='p_range_"+id+"']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#p_range_from_"+id).val()) - 1;
		end   = parseInt($("#p_range_to_"+id).val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end   = len - 1;	
			start = 0;	
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==0){
		alert("Please select the correct sample type.");
		code.eq(start).select();
		return false;
	}
	var sId = code.eq(start)[0].selectedIndex;
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i)[0].options[sId].selected = true;
	}
}

//设置Sample Conc,以第一个为准
function setPEQSampleConc(obj)
{
	var id   = $(obj).parent().parent().parent().parent().parent().attr("id").split("_").pop();
	var code = $(obj).parent().parent().parent().parent().find("input[name^='sample_conc_"+id+"_']");
	var len  = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='p_range_"+id+"']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#p_range_from_"+id).val()) - 1;
		end   = parseInt($("#p_range_to_"+id).val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;	
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==""){
		code.eq(start).val("100");
	}
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i).val(code.eq(start).val());
	}
}

//设置Sample Size,以第一个为准
function setPEQSampleSize(obj)
{
	var id   = $(obj).parent().parent().parent().parent().parent().attr("id").split("_").pop();
	var code = $(obj).parent().parent().parent().parent().find("input[name^='sample_size_"+id+"_']");
	var len  = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='p_range_"+id+"']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#p_range_from_"+id).val()) - 1;
		end   = parseInt($("#p_range_to_"+id).val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;	
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==""){
		code.eq(start).val("10");
	}
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i).val(code.eq(start).val());
	}
}

//设置Sample Type,以第一个为准
function setPEQPrimerType(obj)
{
	var id   = $(obj).parent().parent().parent().parent().parent().attr("id").split("_").pop();
	var code = $(obj).parent().parent().parent().parent().find("select[name^='primer_type_"+id+"_']");
	var len  = code.length;
	if (len == 0) {
		code = $(obj).parent().parent().parent().parent().find("select[id^='primer_type_"+id+"_']");
		len  = code.length;
	}
	var start = 0;
	var end   = len -1;
	if($("input[name='p_range_"+id+"']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#p_range_from_"+id).val()) - 1;
		end   = parseInt($("#p_range_to_"+id).val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end   = len - 1;	
			start = 0;	
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==0){
		alert("Please select the correct primer type.");
		code.eq(start).select();
		return false;
	}
	var sId = code.eq(start)[0].selectedIndex;
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i)[0].options[sId].selected = true;
	}
	//判断是不是选择了Standard (Free)
	var selectList;
	if(code.eq(start).val()=="Standard (Free)"){
		for(i=start;i<=end;i++){
			selectList = $("#primer_list").clone(true);
			selectList.children("select").attr("name","primer_name_"+id+"_"+(i+1));
			code.eq(i).parent().next().html(selectList.html());
		}
	}
	else
	{
		for(i=start;i<=end;i++){
			code.eq(i).parent().next().html('<input class="NFText myplist" type="text" name="primer_name_'+id+"_"+(i+1)+'" style="width:100px;"/>');
		}
	}
}

//设置Primer Name,以第一个为准
function setPEQPrimerName(obj)
{
	var id   = $(obj).parent().parent().parent().parent().parent().attr("id").split("_").pop();
	var code = $(obj).parent().parent().parent().parent().find(".myplist");
	var len  = code.length;
	if($("input[name='p_range_"+id+"']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#p_range_from_"+id).val()) - 1;
		end   = parseInt($("#p_range_to_"+id).val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;
		}
	}
	if(code.eq(start).attr("type")=="text"){
		//如果为空系统自动生成
		if(code.eq(start).val()==""){
			alert("Please input the correct primer name.");
			code.eq(start).select();
			return false;
		}
		//code的值要有全有否则全没有
		for(i=start+1;i<=end;i++){
			code.eq(i).val(code.eq(start).val());
		}
	}else{
		var sId = code.eq(start)[0].selectedIndex;
		//code的值要有全有否则全没有
		for(i=start;i<=end;i++){
			code.eq(i)[0].options[sId].selected = true;
		}
	}
}

//设置代码,以第一个为准
function setPPSPrimerName(obj)
{
	var id   = $(obj).parent().parent().parent().parent().parent().attr("id").split("_").pop();
	var code  = $(obj).parent().parent().parent().parent().find("input[name^='primer_name_"+id+"_']");
	var len   = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='p_range_"+id+"']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#p_range_from_"+id).val()) - 1;
		end   = parseInt($("#p_range_to_"+id).val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
		}
		if(start>len){
			start = len - 1;	
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==""){
		alert("Please input the correct primer name.");
		code.eq(start).select();
		return false;
	}
	var nums  = 0;
	var prev  = code.eq(start).val();
	var index = 0;
	
	//code的值要有全有否则全没有
	var t = 1;
	for(i=start;i<=end;i++){
		index = nums + t++;
		index = formatNums(index,2);
		code.eq(i).val(prev+index);
	}
}

//设置Porwer Read,以第一个为准
function setPEQPorwerRead(obj)
{
	var id   = $(obj).parent().parent().parent().parent().parent().attr("id").split("_").pop();
	var code = $(obj).parent().parent().parent().parent().find("input[name^='pwrwer_read_"+id+"_']");
	var len  = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='p_range_"+id+"']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#p_range_from_"+id).val()) - 1;
		end   = parseInt($("#p_range_to_"+id).val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;	
		}
	}
	var check = code.eq(start).attr("checked");
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i).attr("checked",check);
	}
}

//设置Conc,以第一个为准
function setPEQConc(obj)
{
	var id   = $(obj).parent().parent().parent().parent().parent().attr("id").split("_").pop();
	var code = $(obj).parent().parent().parent().parent().find("input[name^='conc_"+id+"_']");
	var len  = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='p_range_"+id+"']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#p_range_from_"+id).val()) - 1;
		end   = parseInt($("#p_range_to_"+id).val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;	
		}
	}
	var check = code.eq(start).attr("checked");
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i).attr("checked",check);
	}
}

//设置Data Analysis,以第一个为准
function setPEQDataAnalysis(obj)
{
	var id   = $(obj).parent().parent().parent().parent().parent().attr("id").split("_").pop();
	var code = $(obj).parent().parent().parent().parent().find("input[name^='data_analysis_"+id+"_']");
	var len  = code.length;
	var start = 0;
	var end   = len -1;
	if($("input[name='p_range_"+id+"']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#p_range_from_"+id).val()) - 1;
		end   = parseInt($("#p_range_to_"+id).val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;	
		}
	}
	var check = code.eq(start).attr("checked");
	var name  = "";
	var input = "";
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i).attr("checked",check);
		if(check){
			name  = 	code.eq(i).attr("name");
			name  = name.replace("data_analysis_"+id+"_","ref_seq_"+id+"_");
			input = '<input class="NFText" type="text" name="'+name+'" style="width:100px;"/>';
			code.eq(i).next().remove();
			code.eq(i).after(input);
		}else{
			code.eq(i).next().remove();
		}
	}
}

//设置Sample Type,以第一个为准
function setPEQNotes(obj)
{
	var id   = $(obj).parent().parent().parent().parent().parent().attr("id").split("_").pop();
	var code = $(obj).parent().parent().parent().parent().find("select[name^='notes_"+id+"_']");
	var len  = code.length;
	if (len == 0) {
		code = $(obj).parent().parent().parent().parent().find("select[id^='notes_"+id+"_']");
		len  = code.length;
	}
	var start = 0;
	var end   = len -1;
	if($("input[name='p_range_"+id+"']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#p_range_from_"+id).val()) - 1;
		end   = parseInt($("#p_range_to_"+id).val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end   = len - 1;	
			start = 0;	
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==0){
		alert("Please select the correct notes.");
		code.eq(start).select();
		return false;
	}
	var sId = code.eq(start)[0].selectedIndex;
	//code的值要有全有否则全没有
	for(i=start;i<=end;i++){
		code.eq(i)[0].options[sId].selected = true;
	}
}

//删除行
function delPTrBak(obj)
{
	var id   = $(obj).parent().parent().parent().parent().attr("id").split("_").pop();
	var tr = $(obj).parent().parent();
	var tbody = tr.parent();
	if(tbody.children().length==2){
		alert("Finally a banned removed");
		return false;	
	}
	//当前行号
	var line = tr.children().eq(1).children().attr("name");
	line = parseInt(line.split("_").pop());
	var nextAll = tr.nextAll();
	//删除当前行
	tr.remove();
	//当前行以下重新计数
	var nums  = nextAll.length;
	var start = 0;
	for(var i=0;i<nums;i++){
		start = line + i;
		//属性赋值
		td = nextAll.eq(i).children(); 
		//编号
		if($("#plate_layout_"+id).val()==1){
			td.eq(0).html("<center>"+plateRNums[start-1]+"</center>");
		}
		if($("#plate_layout_"+id).val()==2){
			td.eq(0).html("<center>"+plateCNums[start-1]+"</center>");
		}
		//simple name
		td.eq(1).children().attr("name","sample_name_"+id+"_"+start);
		//sample type
		td.eq(2).children("select").eq(0).attr("name","sample_type_"+id+"_"+start);
		//sample conc
		td.eq(3).children().attr("name","sample_conc_"+id+"_"+start);
		//sample size
		td.eq(4).children().attr("name","sample_size_"+id+"_"+start);
		//primer type
		td.eq(5).children("select").eq(0).attr("name","primer_type_"+id+"_"+start);
		//primer name
		td.eq(6).children().attr("name","primer_name_"+id+"_"+start);
		//pwrwer read
		td.eq(7).children().attr("name","pwrwer_read_"+id+"_"+start);
		//data analysis
		td.eq(8).children().eq(1).attr("name","ref_seq_"+id+"_"+start);
		td.eq(8).children().eq(0).attr("name","data_analysis_"+id+"_"+start);
		//notes
		td.eq(9).children("select").eq(0).attr("name","notes_"+id+"_"+start);
		start++;
	}
}

//删除行
function delPTr(obj)
{
	var tr = $(obj).parent().parent();
	var td = tr.children();
	//simple name
	td.eq(1).children().val("");
	//sample type
	td.eq(2).children("select").eq(0)[0].options[0].selected = true;
	//sample conc
	td.eq(3).children().val("");
	//sample size
	td.eq(4).children().val("");
	//primer type
	td.eq(5).children("select").eq(0)[0].options[0].selected = true;
	//primer name
	td.eq(6).children().val("");
	//pwrwer read
	td.eq(7).children().attr("checked",false);
	//data analysis
	td.eq(8).children().eq(1).val("");
	td.eq(8).children().eq(0).attr("checked",false);
	//notes
	td.eq(9).children("select").eq(0)[0].options[0].selected = true;
	//vector name
	td.eq(10).children().val("");
}

//添加 Tube Entry
function addPTube(obj)
{
	var idNums = $(obj).parent().parent().parent().parent().attr("id");
	
	idNums = idNums.substr(10,1);
	//判断输入的数字是否正确
	var nums = parseInt($("#add_p_tube_nums_"+idNums+"").val());
	if(nums<=0||isNaN(nums)){
		alert("Please input the correct nums.");	
		$("#add_p_tube_nums_"+idNums+"").select();
		return false;
	}
	//查询目前已经有的记录(限制不超过100个)
	var maxSize = 96;
	var trSize = $("#plate_table_"+idNums+" tr").size() - 1;
	var hasSize = maxSize - trSize;
	if(hasSize<nums){
		alert("The maximum number of rows for a plate is 96.");
		return false;
	}
	var start = trSize + 1;
	var obj   = null;
	var tbody = null;
	var td    = null;
	
	for(var i=1;i<=nums;i++){
		obj = $("#copy_p_tr").clone(true);
		obj.removeAttr("id");
		tbody = obj.children();
		//属性赋值
		td = tbody.children().children(); 
		//编号
		if($("#plate_layout_"+idNums).val()==1){
			td.eq(0).html("<center>"+plateRNums[start-1]+"<input type='hidden' value='"+plateRNums[start-1]+"' name='num_"+idNums+"_"+i+"' /></center>");
		}
		if($("#plate_layout_"+idNums).val()==2){
			td.eq(0).html("<center>"+plateCNums[start-1]+"<input type='hidden' value='"+plateCNums[start-1]+"' name='num_"+idNums+"_"+i+"' /></center>");
		}
		//simple name
		td.eq(1).html('<input class="NFText" type="text" name="sample_name_'+idNums+'_'+start+'" style="width:100px;"/>');
		//sample type
		td.eq(2).children("select").eq(0).attr("name","sample_type_"+idNums+"_"+start);
		//sample conc
		td.eq(3).html('<input class="NFText" type="text" name="sample_conc_'+idNums+'_'+start+'" style="width:50px;"/>');
		//sample size
		td.eq(4).html('<input class="NFText" type="text" name="sample_size_'+idNums+'_'+start+'" style="width:40px;"/>');
		//primer type
		td.eq(5).children("select").eq(0).attr("name","primer_type_"+idNums+"_"+start);
		//primer name
		td.eq(6).html('<input class="NFText myplist" type="text" name="primer_name_'+idNums+'_'+start+'" style="width:100px;"/>');
		//pwrwer read
		td.eq(7).html('<input type="checkbox" name="pwrwer_read_'+idNums+'_'+start+'"  class="sInput" style="width:30px;" value="1" />');
		//data analysis
		td.eq(8).html('<input type="checkbox" name="data_analysis_'+idNums+'_'+start+'"  class="sInput" style="width:30px;" value="1" onclick="referenceSequence(this);" /><span id="span_'+idNums+'_'+start+'" style="display: none;"><input class="NFText" style="height:50px;" type="text" name="ref_seq_'+idNums+'_'+start+'" onblur="hideSpan(\''+idNums+'_'+start+'\');" style="width:100px;"/></span>');
		//notes
		td.eq(9).children("select").eq(0).attr("name","notes_"+idNums+"_"+start);
		//code
		$("#plate_table_"+idNums+" tr:last").after(tbody.html());
		start++;
		obj = null;
	}
	if(parseInt($("#p_range_to_"+idNums).val())+nums<=96){
		$("#p_range_to_"+idNums).val(parseInt($("#p_range_to_"+idNums).val())+nums);
	}
}

//add tube autocomplete 
function addPTubeII(id)
{
	var idNums = id;
	//判断输入的数字是否正确
	var nums = 96;
	//查询目前已经有的记录(限制不超过100个)
	var maxSize = 96;
	var trSize = $("#plate_table_"+idNums+" tr").size() - 1;
	var hasSize = maxSize - trSize;
	if(hasSize<nums){
		alert("The maximum number of rows for a plate is 96.");
		return false;
	}
	var start = trSize + 1;
	var obj   = null;
	var tbody = null;
	var td    = null;
	
	for(var i=1;i<=nums;i++){
		obj = $("#copy_p_tr").clone(true);
		obj.removeAttr("id");
		tbody = obj.children();
		//属性赋值
		td = tbody.children().children(); 
		//编号
		if($("#plate_layout_"+idNums).val()==1){
			td.eq(0).html("<center>"+plateRNums[start-1]+"<input type='hidden' value='"+plateRNums[start-1]+"' name='num_"+idNums+"_"+i+"' /></center>");
		}
		if($("#plate_layout_"+idNums).val()==2){
			td.eq(0).html("<center>"+plateCNums[start-1]+"<input type='hidden' value='"+plateCNums[start-1]+"' name='num_"+idNums+"_"+i+"' /></center>");
		}
		//simple name
		td.eq(1).html('<input class="NFText" type="text" name="sample_name_'+idNums+'_'+start+'" style="width:100px;"/>');
		//sample type
		td.eq(2).children("select").eq(0).attr("name","sample_type_"+idNums+"_"+start);
		td.eq(2).children("select").eq(0).attr("id","sample_type_"+idNums+"_"+start);
		//sample conc
		td.eq(3).html('<input class="NFText" type="text" name="sample_conc_'+idNums+'_'+start+'" style="width:50px;"/>');
		//conc
		td.eq(4).html('<input class="sInput" type="checkbox" name="conc_'+idNums+'_'+start+'" style="width:40px;"/>');
		//primer type
		td.eq(5).children("select").eq(0).attr("name","primer_type_"+idNums+"_"+start);
		td.eq(5).children("select").eq(0).attr("id","primer_type_"+idNums+"_"+start);
		//primer name
		td.eq(6).html('<input class="NFText myplist" type="text" name="primer_name_'+idNums+'_'+start+'" style="width:100px;"/>');
		//pwrwer read
		td.eq(7).html('<input type="checkbox" name="pwrwer_read_'+idNums+'_'+start+'"  class="sInput" style="width:30px;" value="1" />');
		//data analysis
		td.eq(8).html('<input type="checkbox" name="data_analysis_'+idNums+'_'+start+'"  class="sInput" style="width:30px;" value="1" onclick="referenceSequence(this);" /><span id="span_'+idNums+'_'+start+'" style="display: none;"><input class="NFText" style="height:50px;" type="text" name="ref_seq_'+idNums+'_'+start+'" onblur="hideSpan(\''+idNums+'_'+start+'\');" style="width:100px;"/></span>');
		//notes
		td.eq(9).children("select").eq(0).attr("name","notes_"+idNums+"_"+start);
		td.eq(9).children("select").eq(0).attr("id","notes_"+idNums+"_"+start);
		//sample size
		td.eq(10).html('<input class="NFText" type="text" name="sample_size_'+idNums+'_'+start+'" style="width:40px;"/>');
		//code
		$("#plate_table_"+idNums+" tr:last").after(tbody.html());
		start++;
		obj = null;
	}
	if(parseInt($("#p_range_to_"+idNums).val())+nums<=96){
		$("#p_range_to_"+idNums).val(parseInt($("#p_range_to_"+idNums).val())+nums);
	}
}

//change plate layout
function calcNums(obj)
{
	var nums = $(obj).parent().parent().parent().parent().attr("id");
	nums = nums.substr(10,1);
	var tr  = $("#plate_table_"+nums+" tr");
	var len = tr.size();
	var td;
	for(var i=1;i<len;i++){
		td = tr.eq(i).children("td");
		if($(obj).val()==1){
			td.eq(0).html("<center>"+plateRNums[i-1]+"<input type='hidden' value='"+plateRNums[i-1]+"' name='num_"+nums+"_"+i+"' /></center>");
		}
		if($(obj).val()==2){
			td.eq(0).html("<center>"+plateCNums[i-1]+"<input type='hidden' value='"+plateCNums[i-1]+"' name='num_"+nums+"_"+i+"' /></center>");
		}
	}
}

//remove plate
function removePlate(obj)
{
	if(confirm("Are sure you want to delete?")){
		var nums = $("div[id^='plate_div_']").size();
		if(nums==1){
			alert("Finally a banned removed");
			return false;	
		}
		var	id = $(obj).parent().parent().parent().parent().parent().attr("id").split("_").pop();
		$("#plate_div_"+id).remove();
		$("#tab_detail"+id).remove();
		$("table[id^='tab_detail']").eq(0).show();
	}
}

//plate form submit
function checkPlateForm()
{
	var obj = $("table[id^='tab_detail']");
	if (obj == null || obj.size()<=0) {
		return false;
	}
	var id,idStr;
	idStr = "";
	var trSize = 0;
	var trStr  = "";
	
	var saveMsg = false;
	var dnsPlateArr = [];
	var urlArr = [];
	for(var j=0;j<obj.size();j++){
		id = obj.eq(j).attr("id").substr(10);
		var pName = $("#p_name_"+id).val();
		if(pName == ""){
			alert("Please input plate name!");
			$("table[id^='tab_detail']").hide();
			$("#tab_detail"+id).show();
			$("#p_name_"+id).focus();
			$("#div_plate .plate_new2").removeClass().addClass("plate_new");
			$("#plate_div_"+id+" .plate_new").removeClass().addClass("plate_new2");
			return false;
		}
		var plateLayout = $("#plate_layout_"+id).val();
		//表单提交验证
		var sampleName   = $("#plate_table_"+id+" input[name^='sample_name_']");
		var sampleType 	 = $("#plate_table_"+id+" select[id^='sample_type_']");
		var sampleConc 	 = $("#plate_table_"+id+" input[name^='sample_conc_']");
		var conc 		 = $("#plate_table_"+id+" input[name^='conc_']");
		var primerType   = $("#plate_table_"+id+" select[id^='primer_type_']");
		var primerName   = $("#plate_table_"+id+" .myplist");
		var pwrwerRead 	 = $("#plate_table_"+id+" input[name^='pwrwer_read_']");	
		var dataAnalysis = $("#plate_table_"+id+" input[name^='data_analysis_']");	
		var refSeq 		 = $("#plate_table_"+id+" input[name^='ref_seq_']");	
		var notes 		 = $("#plate_table_"+id+" select[id^='notes_']");
		var sampleSize 	 = $("#plate_table_"+id+" input[name^='sample_size_']");
		var sessItemKey  = $("#plate_table_"+id+" input[name^='sessItemKey_']");
		var len = sampleName.length;
		var realLen = 0;

		//保存DNA Sequencing, Save data to session  add by Zhang Yong 2011-11-09
		var sampleNameArr = [];
		var sampleTypeArr = [];
		var sampleConcArr = [];
		var flagConcMeasArr = [];
		var primerTypeArr = [];
		var primerNameArr = [];
		var flagPowerReadArr = [];
		var flagDataAnasArr = [];
		var dataAnasArr = [];
		var specialRequestArr = [];
		var templateSizeArr = [];
		var sessItemKeyArr = [];
		var sampleName_DB = "";
		var sampleType_DB = "";
		var sampleConc_DB = "";
		var flagConcMeas_DB = 0;
		var primerType_DB = "";
		var primerName_DB = "";
		var flagPowerRead_DB = 0;
		var flagDataAnas_DB = 0;
		var dataAnas_DB = "";
		var specialRequest_DB = "";
		var templateSize_DB = 0;
		var sessItemKey_DB = "";
		for(var i=0;i<len;i++){
			if(sampleName.eq(i).val()==""&&primerType.eq(i).val()=="0"&&(primerName.eq(i).val()==""||primerName.eq(i).val()==undefined)){
				continue;
			}
			if(sampleName.eq(i).val()==""){
				alert("Please input sample name!");	
				$("table[id^='tab_detail']").hide();
				$("#tab_detail"+id).show();
				sampleName.eq(i).focus();
				$("#div_plate .plate_new2").removeClass().addClass("plate_new");
				$("#plate_div_"+id+" .plate_new").removeClass().addClass("plate_new2");
				return false;
			}
			if(primerType.eq(i).val()=="0"){
				alert("Please select primer type!");					
				$("table[id^='tab_detail']").hide();
				$("#tab_detail"+id).show();
				primerType.eq(i).focus();
				$("#div_plate .plate_new2").removeClass().addClass("plate_new");
				$("#plate_div_"+id+" .plate_new").removeClass().addClass("plate_new2");
				return false;
			}
            if(primerType.eq(i).val()!="Standard (Free)"){
				if(primerName.eq(i).val()==""){
					alert("Please input primer name!");	
					$("table[id^='tab_detail']").hide();
					$("#tab_detail"+id).show();
					primerName.eq(i).focus();
					$("#div_plate .plate_new2").removeClass().addClass("plate_new");
				    $("#plate_div_"+id+" .plate_new").removeClass().addClass("plate_new2");
					return false;
				}
			}else{
				if(primerName.eq(i).val()=="0")
				{	
					alert("Please select a primer name!");	
					$("table[id^='tab_detail']").hide();
					$("#tab_detail"+id).show();
					primerName.eq(i).focus();
					$("#div_plate .plate_new2").removeClass().addClass("plate_new");
				    $("#plate_div_"+id+" .plate_new").removeClass().addClass("plate_new2");
					return false;
				}					
			}
            sampleName_DB = sampleName.eq(i).val();
    		sampleType_DB = sampleType.eq(i).val();
    		sampleConc_DB = sampleConc.eq(i).val();
    		flagConcMeas_DB = 0;
    		if (conc.eq(i).attr("checked")) {
    			flagConcMeas_DB = 1;
    		}
    		primerType_DB = primerType.eq(i).val();
    		primerName_DB = primerName.eq(i).val();
    		flagPowerRead_DB = 0;
    		if (pwrwerRead.eq(i).attr("checked")) {
    			flagPowerRead_DB = 1;
    		}
    		flagDataAnas_DB = 0;
    		if (dataAnalysis.eq(i).attr("checked")) {
    			flagDataAnas_DB = 1;
    		}
    		dataAnas_DB = refSeq.eq(i).val();
    		if (dataAnas_DB == undefined) {
    			dataAnas_DB = "";
    		}
    		specialRequest_DB = notes.eq(i).val();
    		if(!isNaN(sampleSize.eq(i).val())){
    			templateSize_DB = sampleSize.eq(i).val();
    		}
    		if (sessItemKey.eq(i) == undefined || sessItemKey.eq(i).val() == undefined) {
    			sessItemKey_DB = "";
    		} else {
    			sessItemKey_DB = sessItemKey.eq(i).val();
    		}
    		sampleNameArr.push(sampleName_DB);
    		sampleTypeArr.push(sampleType_DB);
    		sampleConcArr.push(sampleConc_DB);
    		flagConcMeasArr.push(flagConcMeas_DB);
    		primerTypeArr.push(primerType_DB);
    		primerNameArr.push(primerName_DB);
    		flagPowerReadArr.push(flagPowerRead_DB);
    		flagDataAnasArr.push(flagDataAnas_DB);
    		dataAnasArr.push(dataAnas_DB);
    		specialRequestArr.push(specialRequest_DB);
    		templateSizeArr.push(templateSize_DB);
    		sessItemKeyArr.push(sessItemKey_DB);
            realLen++;
		}
		if (realLen == 0) {
			alert("Please input sample name!");	
			$("table[id^='tab_detail']").hide();
			$("#tab_detail"+id).show();
			sampleName.eq(0).focus();
			$("#div_plate .plate_new2").removeClass().addClass("plate_new");
			$("#plate_div_"+id+" .plate_new").removeClass().addClass("plate_new2");
			return false;
		}
		var dnsPlate = {"sampleNameArr":sampleNameArr,"sampleTypeArr":sampleTypeArr,"sampleConcArr":sampleConcArr,
				"flagConcMeasArr":flagConcMeasArr,"primerTypeArr":primerTypeArr,"primerNameArr":primerNameArr,
				"flagPowerReadArr":flagPowerReadArr,"flagDataAnasArr":flagDataAnasArr,"dataAnasArr":dataAnasArr,
				"specialRequestArr":specialRequestArr,"templateSizeArr":templateSizeArr,"sessItemKeyArr":sessItemKeyArr};
		dnsPlateArr.push(dnsPlate);
		var urlStr = "";
		urlStr = orderquoteObj.type+"_more!savePlateDNASequencing.action?"+orderquoteObj.sessNoName+"="+orderquoteObj.sessNoValue+"&pName="+pName+"&plateLayout="+plateLayout;
		if ("quote" == orderquoteObj.type) {
			urlStr = orderquoteObj.type+"_more!savePlateDNASequencing.action?"+orderquoteObj.sessNoName+"="+orderquoteObj.sessNoValue+"&pName="+pName+"&plateLayout="+plateLayout;
		}
		urlArr.push(urlStr);
		idStr += id+",";
		trSize = $("#plate_table_"+id+" tr").size() - 1;
		trStr += trSize+",";
	}
	for(var i=0;i<obj.size();i++){
		$.ajax({
		    type: "POST",
		    url: urlArr[i],
		    data: dnsPlateArr[i],
		    dataType: "json",
		    async: false,
		    success: function(rs){
		    	if (rs != undefined && rs.message != undefined) {
		    		alert(rs.message);
		    		return false;
		    	} else {
					saveMsg = true;
				}
			},
			error: function(data){
				saveMsg = data.responseText;
			}
		});
	}
	idStr = idStr.substr(0,idStr.length-1);
	trStr = trStr.substr(0,trStr.length-1);
	
	$("#plate_id_nums").val(idStr);
	$("#plate_item_nums").val(trStr);
	if (saveMsg == true) {
		alert("Your Plate have been saved successful!");
		//刷新item list
		parent.document.getElementById("itemListIframe").src = orderquoteObj.url("getItemList");
	}
	return false;
}

//tube submit
function tubeEditAll()
{
	var ids = $("#ids").val();
	var idA = ids.split(",");
	for(var i=0;i<idA.length;i++){
		if($("#sample_name_"+idA[i]).val()==""){
			alert("Please input sample name!");	
			$("#sample_name_"+idA[i]).focus();
			return false;
		}
		if($("#sample_type_"+idA[i]).val()==""){
			alert("Please select sample type!");	
			$("#sample_type_"+idA[i]).focus();
			return false;
		}
		if($("#primer_type_"+idA[i]).val()==""){
			alert("Please select primer type!");	
			$("#primer_type_"+idA[i]).focus();
			return false;
		}
		if($("#primer_type_"+idA[i]).val()=="Standard (Free)"){
			if($("#primer_name_"+idA[i]).val()==0){
				alert("Please select primer name!");	
				$("#primer_name_"+idA[i]).focus();
				return false;
			}
		}else{
			if($("#primer_name_"+idA[i]).val()==""){
				alert("Please input primer name!");	
				$("#primer_name_"+idA[i]).focus();
				return false;
			}
		}
	}
	return true;
}

//plate submit
function plateEditAll()
{
	var ids = $("#ids").val();
	var idA = ids.split(",");
	for(var i=0;i<idA.length;i++){
		if($("#sample_name_"+idA[i]).val()==""){
			alert("Please input sample name!");	
			$("#sample_name_"+idA[i]).focus();
			return false;
		}
		if($("#sample_type_"+idA[i]).val()==""){
			alert("Please select sample type!");	
			$("#sample_type_"+idA[i]).focus();
			return false;
		}
		if($("#primer_type_"+idA[i]).val()==""){
			alert("Please select primer type!");	
			$("#primer_type_"+idA[i]).focus();
			return false;
		}
		if($("#primer_type_"+idA[i]).val()=="Standard (Free)"){
			if($("#primer_name_"+idA[i]).val()==0){
				alert("Please select primer name!");	
				$("#primer_name_"+idA[i]).focus();
				return false;
			}
		}else{
			if($("#primer_name_"+idA[i]).val()==""){
				alert("Please input primer name!");	
				$("#primer_name_"+idA[i]).focus();
				return false;
			}
		}
	}
	return true;
}

//设置代码,以第一个为准
function setPEQVectorName(obj)
{
	var id   = $(obj).parent().parent().parent().parent().parent().attr("id").split("_").pop();
	var code = $(obj).parent().parent().parent().parent().find("input[name^='vector_name_"+id+"_']");
	var len  = code.length;
	if($("input[name='p_range_"+id+"']:checked").val()=="all"){
		start = 0;
		end   = len - 1;
	}else{
		start = parseInt($("#p_range_from_"+id).val()) - 1;
		end   = parseInt($("#p_range_to_"+id).val()) - 1;
		if(end>len-1){
			end = len - 1;	
		}
		if(end<start){
			end = len - 1;	
			start = 0;
		}
	}
	//如果为空系统自动生成
	if(code.eq(start).val()==""){
		alert("Please input the correct vector name.");
		code.eq(start).select();
		return false;
	}
	//code的值要有全有否则全没有
	for(i=start+1;i<=end;i++){
		code.eq(i).val(code.eq(start).val());
	}
	
}
var oo = true;
function create(obj,tips)
{
	if(oo==true){
		oo = false;
		var div =document.createElement("div");
		div.className = "f";
		div.id = "float_div";
		div.innerHTML = tips;
		document.body.appendChild(div);
		var left  = obj.offsetLeft - 20;
		var top   = obj.offsetTop + 10;
		var width = obj.offsetWidth;
		while (obj=obj.offsetParent){
			left += obj.offsetLeft;
			top  += obj.offsetTop;
		}
		$("#float_div").css("left",left+width);
		$("#float_div").css("top",top);
		div.onmouseout = del;
	}
}

function del()
{
	$("#float_div").remove();
	oo = true;
}