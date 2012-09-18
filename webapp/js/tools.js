

function viewCus(){
var customerNo = $("#customerNo").val();
if(customerNo == ''){
  alert("Customer no can not be null");
  return;
}
       $('#view_customer').dialog("option", "open", function() { 
        	var htmlStr = '<iframe src="http://10.168.2.181:8080/scm/customer/customer!edit.action?operation_method=view&custNo='+customerNo+'" height=420" width="750"  style="border:0px" frameborder="0"></iframe>';
         	$('#view_customer').html(htmlStr);
		});
		$('#view_customer').dialog('open');
}

function viewCus2(){
var customerNo = $("#custNo").val();
if(customerNo == ''){
  alert("Customer no can not be null");
  return;
}
       $('#view_customer').dialog("option", "open", function() { 
        	var htmlStr = '<iframe src="http://10.168.2.181:8080/scm/customer/customer!edit.action?operation_method=view&custNo='+customerNo+'" height=420" width="750"  style="border:0px" frameborder="0"></iframe>';
         	$('#view_customer').html(htmlStr);
		});
		$('#view_customer').dialog('open');
}

function viewShipment(){
var shipmentNo = $("#shipmentNo").val();
if(shipmentNo == ''){
  alert("Shipment No can not null!");
  return ;
}
$('#view_shipment').dialog("option", "open", function() { 
var url = "http://10.168.2.181:8080/scm/shipments!getShipInfo.action?operation_method=view&shipmentNo="+shipmentNo;
        	var htmlStr = '<iframe  src="'+url+'" height=430" width="750"  style="border:0px" frameborder="0"></iframe>';
         	$('#view_shipment').html(htmlStr);
		});
		$('#view_shipment').dialog('open');
}


function viewOrder(){
      var orderNo = $("#orderNo").val();
				if(orderNo == '' || orderNo == null){
				  alert("Order no can not be null");
				  return;
				}  
	$('#view_order').dialog("option", "open", function() { 
      var url = "http://10.168.2.181:8080/scm/order!edit.action?operation_method=view&orderNo="+orderNo;
        	var htmlStr = '<iframe src="'+url+'" height=400" width="750"  style="border:0px" frameborder="0"></iframe>';
         	$('#view_order').html(htmlStr);
		});
				     
		$('#view_order').dialog('open');
}


function viewInvoiceInfo(){
var invoiceNo = $("#invoiceId").val();
  if(invoiceNo == '' || invoiceNo == null){
    alert("Invoice No Can not be null");
    return;
  }
  $('#view_invoice').dialog("option", "open", function() { 
      var url = "ar_invoice!edit.action?invoiceId="+invoiceNo+"&invoiceNo="+invoiceNo+"&method=dialog";
        	var htmlStr = '<iframe src="'+url+'" height=400" width="750"  style="border:0px" frameborder="0"></iframe>';
         	$('#view_invoice').html(htmlStr);
		});
				     
		$('#view_invoice').dialog('open');
}

function viewSupplierInfo(){
var vendorNo = $("#vendorNo").val();
if(vendorNo == ''){
  alert("Vendor no can not be null");
  return;
}
       $('#view_supplier').dialog("option", "open", function() { 
        	var htmlStr = '<iframe src="http://10.168.2.181:8080/scm/customer/customer!edit.action?operation_method=view&custNo='+vendorNo+'" height=420" width="750"  style="border:0px" frameborder="0"></iframe>';
         	$('#view_supplier').html(htmlStr);
		});
		$('#view_supplier').dialog('open');
}

function viewPurchaseOrder(){
      var orderNo = $("#orderNo").val();
				if(orderNo == null || orderNo == ''){
				  alert("Order no can not be null");
				  return;
	}  
var url = "http://10.168.2.181:8080/scm/order!edit.action?orderNo="+orderNo+"&purchaseOrderFlag=1&operation_method=view";
       $('#view_order').dialog("option", "open", function() { 
        	var htmlStr = '<iframe src="'+url+'" height=420" width="750"  style="border:0px" frameborder="0"></iframe>';
         	$('#view_order').html(htmlStr);
		});
		$('#view_order').dialog('open');
}

function viewApInvoice(){
	 var invoiceNo = $("#invoiceId").val();
					if(invoiceNo == null || invoiceNo == ''){
					  alert("Invoice no can not be null");
					  return;
		}  
	$('#view_invoice').dialog("option", "open", function() { 
	      var url = "ap_invoice!edit.action?invoiceId="+invoiceNo+"&invoiceNo="+invoiceNo+"&method=dialog";
	        	var htmlStr = '<iframe src="'+url+'" height=400" width="750"  style="border:0px" frameborder="0"></iframe>';
	         	$('#view_invoice').html(htmlStr);
			});
		$('#view_invoice').dialog('open');
}

/**
create by ZHOUYONG
get all table data
其中 arr[] 为列名，必须按照表格的顺序写 否则会出错，而且长度必须和表格的一行长度相同，tableId为表格的Id值
*/
function getTableData(tableId, arr) {
//var arr =  ["invoiceId","invoiceNo","itemNo","catalogNo","name","qty","uom","size","price","amount","tax"];
	var json = "[";
	var i = 0;
	$("#" + tableId + "   tr  td").each(function () {
		var value = "";
		if (i % arr.length == 0) {
			json += "{";
			i = 0;
		}
		var key = arr[i];
		value = $(this).html();
		if (value.indexOf("checkbox") != -1) {
			if (value.indexOf("/") == -1) {
				value = value.replace(">", "/>");
			}
			var dom = createXml(value);
			value = $(dom.documentElement).attr("value");
		} else {
			if (value.indexOf("<a") != -1) {
				var dom = createXml(value);
				value = dom.documentElement.childNodes[0].nodeValue;
			} else {
				value = value.replace(/&nbsp;/, "");
			}
		}
		json += key + ":'" + value + "'";
		if (i == (arr.length - 1)) {
			json += "},";
		} else {
			json += ",";
		}
		i++;
	});
	json += "]";
	json = json.replace(",]", "]");
	//alert(json);
	eval("data=" + json);
	return data;
}

//parse string to xml
function createXml(str) {
	if (document.all) {
		var xmlDom = new ActiveXObject("Microsoft.XMLDOM");
		xmlDom.loadXML(str);
		return xmlDom;
	} else {
		return new DOMParser().parseFromString(str, "text/xml");
	}
}

/*，如果没有选中 返回NULL
获取选种行表格的数据，返回数组
*/
function getCheckedTableData(tableId,arr){
var obj = null; //临时数据
var data = new Array(); //存放结果集
    var slibs = null;//兄弟节点
	var p = null; //父节点
	
	$("#"+tableId+"   tr  td :checked").each(function(idx){
	p = $(this).parent();
	obj = new Object();
	var value = $(p).html();
	if(value.indexOf("/") == -1){
	 value = value.replace(">","/>");
	}
	var dom = createXml(value);
   value = $(dom.documentElement).attr("value");
    eval("obj."+arr[0]+"='"+value+"'");//将所有结果以字符串形式保留
    slibs = $(p).siblings();
	for(var k=0;k<slibs.length;k++){
	value = slibs[k].innerHTML;
            if(value.indexOf("<a")!=-1){
	           var dom = createXml(value);
	           value = dom.documentElement.childNodes[0].nodeValue;
	       }else{
            value = value.replace(/&nbsp;/,'');
	 	   }
		    eval("obj."+arr[k+1]+"='"+value+"'");
	 
	}
     data[idx] = obj;
	   });
	   
}

//query list by sql
function initSelect(){
  	$('select[sql]').each(function(){
		var sql = $(this).attr('sql');
		var val = $(this).attr("val");
	    var blank = $(this).attr("blank");
		var dom = $(this);
	    $.ajax({
	        url : "fill_select!fillSelect.action",
	        data: {sql : sql},
	        type : 'post',
	        success : function(html){
	           if(blank){
	           $(dom).html('<option value="" ></option>'+html);
	           }
	           else{
	           $(dom).html(html);
	           }
	          if(val!='' && val !='undefined'){
	          $(dom).val(val);
	          }
	        },
	        error: function(){
	          alert("error");
	        }
	    });  
	});	
	
	$('select[sqlField]').each(function(){
		var sqlField = $(this).attr('sqlField');
		var val = $(this).attr("val");
	    var blank = $(this).attr("blank");
		var dom = $(this);
	    $.ajax({
	        url : "fill_select!fillSelect2.action",
	        data: {sqlField : sqlField},
	        type : 'post',
	        success : function(html){
	           if(blank){
	           $(dom).html('<option value="" ></option>'+html);
	           }
	           else{
	           $(dom).html(html);
	           }
	          if(val!='' && val !='undefined'){
	          $(dom).val(val);
	          }
	        },
	        error: function(){
	          alert("error");
	        }
	    });  
	});	
}

//query List by Interface
function initSelect2(){
  	$('select[field]').each(function(){
		var field = $(this).attr('field');
		var val = $(this).attr("val");
		var blank = $(this).attr("blank");
		var dom = $(this);
	    $.ajax({
	        url : "fill_select!getList.action",
	        data: {field : field},
	        type : 'post',
	        success : function(html){
	         if(blank){
	           $(dom).html('<option value="" ></option>'+html);
	           }
	           else{
	           $(dom).html(html);
	           }
	          if(val!='' && val !='undefined'){
	          $(dom).val(val);
	          }
	        },
	        error: function(){
	          alert("error");
	        }
	    });  
	});	
}


//自动填充表单值
function initData(formId,obj) {
		$("#"+formId+" input[id]").each(function(){
		var type = $(this).attr("type");
		var id = $(this).attr("id");
		var str = eval("obj."+id);
			if (type=="text" || type=="hidden"){
			   if(str=='&nbsp;' || str == null)
			       str='';			       
			   $(this).val(str);   
			}
			if(type=="checkbox"){
			  this.checked = (str == '1');
			}
			if(type=="radio"){
			 if(str ==$(this).val() ||str ==$(this).attr("datasrc")){
			     this.checked = true;
			    }
			}
		});
		
		$("#"+formId+" textarea[id]").each(function(){
		var id = $(this).attr("id");
		 var str =  eval("obj."+id);
			if(str=='&nbsp;' || str==null)
			       str='';	
			str = str.replace(/&nbsp;/g,' ');
			str = str.replace(/<mm>/g,' ');
			str = str.replace(/&lt;/g,'<');
			str = str.replace(/&gt;/g,'>');
			$(this).val(str);
		});
		
	$("#"+formId+" select").each(function(){
	        var id = $(this).attr("id");
	 	    var value =  eval("obj."+id);
		    for(var i=0;i<this.options.length;i++){
		    	if(this.options[i].value==value){
		    		this.options[i].selected=true;
		    		break;
		    	}
		    }
		  });
		  
	
}



function clearForm(formId){
        $("#"+formId+" input[id]").each(function(){
        	var type = $(this).attr("type");
        	if(type == 'checkbox'){
        	  $(this).removeAttr("checked");
        	}
        	if(type=="radio"){
			     this.checked = false;
			}
			else{
            $(this).val('');
            }
         });
		$("#"+formId+" textarea[id]").each(function(){
            $(this).html('');
		});

}


/*验证表单不能为空，需要在input 标签中添加属性desc 例如<input type="text" id="userid" desc='员工编号' required='true'>*/
function checkFormNotNull(formId){
var flag = true;
	$("#"+formId+" input").each(function(){
		if($(this).attr("required")){
		if($(this).val()==''){
			alert($(this).attr("desc")+" can not be null!");
			$(this).val('');
			$(this).focus();
			flag = false;  //作为标识
			return false; //推出jquery的方法 return false 相当于java中break
		}
		}
	}); 
	return flag;
}

function getCheckBoxValue(name){
	var obj = document.getElementsByName(name);
	var result = new Array();
	var j=0;
	  for(var i=0; i<obj.length; i++){
		  if(obj[i].checked){
			  result[j++]=obj[i].value;
		  }
		}
	  return result;
	}
	
	
		/*
create by zhouyong
通过正则表达式 进行表单验证
需要添加属性 desc 描述信息 
添加属性 checkType 检验类型 比如手机号检验 Ip检验等等
required='true' 表明该字段不能为空
支持ip,email,mobile,phone,integer,float,digit,idcard(身份证验证)
*/
function checkFormByReq(formId){
var flag = true;
   
   $("#"+formId+" input").each(function(i){
     var type = $(this).attr("checkType");
     var desc = $(this).attr("desc");
     if(!desc){return true; } //相当于continue
     var val = $(this).val();
     var required = $(this).attr("required");
     
      if(required && val==''){
      alert(desc+" Can not be null !");
      flag = false;
      $(this).val('');
      $(this).focus();
      return false;
      }
     if(type == 'ip' && val!=''){ //ip地址
       flag = isIp(val);
       if(!flag) {
       alert(desc+" invalid ");
       $(this).val('');
       $(this).focus();
        flag = false;
       return false;
       }
     }
     else if(type == 'phone' && val!=''){//普通电话
       flag = isPhone(val);
       if(!flag) {
       alert(desc+" invalid !");
       $(this).val('');
       $(this).focus();
        flag = false;
       return false;
       }
     }
     else if(type == 'email' && val!=''){//email
     flag = isEmail(val);
       if(!flag) {
       alert(desc+" invalid !");
       $(this).val('');
       $(this).focus();
        flag = false;
       return false;
       }
     }
     else if(type == 'mobile' && val!=''){//手机
     flag = isMobile(val);
       if(!flag) {
       alert(desc+" invalid !");
       $(this).val('');
       $(this).focus();
        flag = false;
       return false;
       }
     }
     else if(type == 'float'){ //小数
       flag = isFloat(val);
       
       if(!flag) {
       alert(desc+" invalid !");
       $(this).val('');
       $(this).focus();
        flag = false;
       return false;
       }
     }
     else if(type =='integer'){ //整数
     flag = isInt(val);
       if(!flag) {
       alert(desc+" invalid !");
       //$(this).val('0');
       $(this).focus();
        flag = false;
       return false;
       }
     }
     else if(type == 'idcard' && val!=''){ //身份证
       flag = isIdCard(val);
       if(!flag) {
       alert(desc+" invalid !");
       $(this).val('');
       $(this).focus();
        flag = false;
       return false;
       }
     }
   });
   return flag;
}


//必须以下划线 数字或�1�7�字母开处1�7 传入id倄1�7
function checkEmail(id) {
	var emailStr=document.getElementById(id).value;
	return isEmail(emailStr);
}

function isEmail(val){
var emailPat=/^([a-zA-Z0-9_])+@([a-zA-Z0-9_])+(\.[a-zA-Z0-9_])+/;
return matchArray=emailPat.test(val);
}
//电话号码判断 区号3-4位，号码7-8位 中间有-
  function isPhone(val){
      var reg = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
       return reg.test(val);
     }

/*手机号码格式验证*/
function isMobileNumber(id) { 
	var phone =$("#"+id).val(); //document.getElementById(id).value;
	return isMobile(phone);
}

function isPhoneNumber(id){
var val = $("#"+id).val();
return isPhone(val);
}

function isMobile(phone){
	var pattern =  /^1[358]\d{9}$/; 
	return pattern.test(phone);
}

/*检查输入的IP格式是否正确*/
function isIp(value){
	var p = /\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}/;
  return p.test(value);
}

//判断是否是中文1�7 true为中文1�7 false不为中文
function isChinese(val){
	var pat = /[\u4e00-\u9fa5]+/;
	return  pat.test(val);
}
/*判断身份证号码格式是否正确*/
function isIdCard(val){
 var p =  /(^\d{15}$)|(^\d{17}(\d|X)$)/;
 return p.test(val);
}

//判断丄1�7个字符是否是数字
function isDigit(character) 
{
	  return (character >= "0" && character <= "9");
}

//判断是否是整敄1�7 >0 && <99
function isInt(val){
	    var pattern=/^\d+$/;
		var flag = pattern.test(val);
return flag;
}

//判断是否是float
function checkNum(txt){
	if(txt.search("^-?\\d+(\\.\\d+)?$")!=0){
	    return false;
	}
	return true;
}

function isFloat(val){
	 var p =  /^\d+(\.\d+)?$/;
	 
     return p.test(val);
}

 function selectAll(id){
   $("#"+id+" input[type=checkbox]").each(function(){
       $(this).attr("checked","checked");
   });
 }
 
  function unselectAll(id){
   $("#"+id+"  input[type=checkbox]").each(function(){
       $(this).removeAttr("checked");
   });
 }
 
 function unselect(id){
   $("#"+id+" input[type=checkbox]").each(function(){
   if($(this).attr("checked"))
       $(this).removeAttr("checked");
       else{
        $(this).attr("checked","checked");
       }
   });
 }
 
 function getCurrentTime(){
   var d = new Date();
   return formatDate(d,false);
 }
 
  function formatDate(date,isTime){
   var sj = ""
  var ayear = date.getYear();
  var amonth = date.getMonth() + 1;
  var aday = date.getDate();
  var ss = ""
  if(ayear<100)
	  ayear = "19" + ayerar;
  if(amonth<10)
	  amonth = "0" + amonth;
  if(aday<10)
	  aday = "0" + aday;
	 
	 if(isTime){
	  var hours = date.getHours();
  var min = date.getMinutes();
  var seconds = date.getSeconds();
    if(hours<10){
      hours = '0'+hours;
    }
    if(min<10){
      min = '0'+min; 
    }
    if(min<10){
    	seconds = '0'+min; 
    }
    sj = hours+':'+min+':'+seconds;
       ss = ayear + "-" + amonth + "-" +aday+" "+sj;
   return ss;
	 } else{
	 return ayear + "-" + amonth + "-" +aday;
	 }
	  
}

//清空表单
function clearForm(formId){
  	$("#"+formId+" input[id]").each(function(){
		var type = $(this).attr("type");
			if(type=="checkbox"){
			  this.checked = false;
			}
			if(type=="radio"){
			     this.checked = false;
			}
			else{
			 $(this).val('');
			}
		});
		
		$("#"+formId+" textarea[id]").each(function(){
			$(this).val('');
		});
		
	$("#"+formId+" select").each(function(){
           $(this).val('');
		  });
 }