<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>scm</title>
		<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
		<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}jquery/ui/ui.datepicker.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}jquery/jquery.datePicker.js"></script>

		<style type="text/css">
<!--
fieldset fieldset {
	margin: 2px;
}
-->
</style>
		
<script>

function word_onclick() 
{ 
var myDocApp =new ActiveXObject("word.Application"); 
myDocApp.visible = true; 
myDocApp.activate(); 
var myDoc = myDocApp.documents.open("${global_js_url}/print/111.doc");   
var str = word_proc.textarea.value; 
myDoc.formFields("test").range = str; 
  
} 


function   ff(url,fileName,oldFlag){
		if(url.substring(0,4)=="http"){
			$("#fileForm").attr('action',url);
	        $("#fileForm").submit();
	        return ;
		}else{
			url = "download.action?filePath="+url;
			$("#fileName").val(fileName);
			$("#oldFlag").val(oldFlag);
			$("#fileForm").attr('action',url);
	        $("#fileForm").submit();
		}
		
}

function pdfPrint(url, pars){ 
    var myAjax = new Ajax.Request(url, { 
        method: "post", 
        parameters: pars, 
        onSuccess: function(transport){ 
            var filenameArray = transport.responseText.evalJSON(); 
        bulkpdfPrint(filenameArray); 
        }, 
        onFailure: function(){ 
            alert("\u670d\u52a1\u5668\u6545\u969c\uff0c\u8bf7\u7a0d\u5019\u91cd\u8bd5"); 
        } 
    }); 
} 


function bulkpdfPrint(srcFiles){ 
   
    var pdf = "download.action?filePath=A.xml"; 
    if (pdf != undefined && pdf != null) {//判断pdf对象是否存在，如果存在就删除该对象 
        var parentNode = pdf.parentNode; 
        parentNode.removeChild(pdf); 
    } 
    var p = document.createElement("object"); 
    p.id = "createPDF"; 
    p.classid = "CLSID:CA8A9780-280D-11CF-A24D-444553540000"; 
    p.width = 1; 
    p.height = 1; 
	p.src = srcFiles; 
	document.body.appendChild(p); 
    p.printAll(); 
    
} 

function func_print(){
	var docTr = $("#docTable").children().children("tr").length;
	
	if(docTr == 0){
		alert("There is no file we need to print.");
		return;
	}
	var docs = get_checked_str("chk");
	if(docs==""){
		alert("There is no file we need to print.");
	}
	$.ajax({
		type:"POST",
		url:"shipment/shipping!printShippingDoc.action?docs="+docs,
		
		dataType:"json",
		success: function(msg){
			if(msg.message=="success"){
				alert("Print queue has been completed, please wait for the printer to complete all tasks.");
			}
			if(msg.message=="nonPrinter"){
				alert("Connect the printer abnormal.");
			}
			if(msg.message=="nonFile"){
				alert("File exceptions.");
			}
		},
		error: function(msg){
			alert("Connect the printer abnormal.");
		}
	});
}

function get_checked_str(name)
{
	var a = document.getElementsByName(name);
	var str = '';
	for   (var   i=0;   i<a.length;   i++)
	{
		if(a[i].checked)
		{
			str += a[i].value+';';
		}
	}
	return str.substring(0,str.length-1);
}

function print(){
	
	 var pdf = document.getElementById("createPDF"); 
	    if (pdf != undefined && pdf != null) {//判断pdf对象是否存在，如果存在就删除该对象 
	        var parentNode = pdf.parentNode; 
	        parentNode.removeChild(pdf); 
	    } 
	    var p = document.createElement("object"); 
	    p.id = "createPDF"; 
	    p.classid = "CLSID:CA8A9780-280D-11CF-A24D-444553540000"; 
	    p.width = 1; 
	    p.height = 1; 
		p.src = srcFiles; 
		document.body.appendChild(p); 
	    p.printAll(); 



}

function onClickCheckBox(doc){
	var docsvalue = "";
	var docs = $("#docName").val();
	if(docs==null||docs==""){
		docsvalue = doc;
	}else{
		var docNames = docs.split(";");
		var isAdd = "1";
		for(int i = 0;i<docNames.leath;i++){
			if(docNames[i]!=doc){
				if(docsvalue==""){
					docsvalue = docNames[i];
				}else{
					docsvalue +=";"+docNames[i];
				}
			}else{
				isAdd = "0";
			}
		}
		if(isAdd =="1"){
			if(docsvalue == ""){
				docsvalue = doc;
			}else{
				docsvalue +=";"+doc;
			}
		}
	}
	$("#docName").val(docsvalue);
}


function func_Zip(shippingLable){
	var docTr = $("#docTable").children().children("tr").length;
	if(docTr == 0){
		alert("There is no file we need to print.");
		return;
	}
	document.zipFrom.action="shipping!printShippingLabelTrackingNoUpload.action";
	$("#shippingLable").val(shippingLable);
	document.zipFrom.submit();
}

function   cc(e)  
{  
	var   a   =   document.getElementsByName("chk");  
	for   (var   i=0;   i<a.length;   i++) {
		if(!a[i].disabled){
			a[i].checked = e.checked;
		}
	}
} 
</script>
	</head>

	<body>
		<form action="" name="zipFrom" >
			<input type="hidden" name="shippingLable" id="shippingLable"/>
		</form>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="height: 150px">
				<s:if test="(shippingDocList==null&&shippingProductDocList==null)"><tr><td align="center"><span style="color:red">There is no document we can view or print.</span></td></tr></s:if>
		          <tr>
		            <td valign="top"><div  style="width:450px; height:180px; overflow:scroll; ">
		            <table border="0" cellpadding="0" cellspacing="0" class="General_table"  id="docTable">
		            <tr>
		                <th width="150">
				                	<input type="checkbox" name="chk1" id="chk1" value="" onclick="cc(this)"/>
		                </th>
		                <td width="208"> 
		               				
		                </td>
		                </tr>
		              <s:iterator value="shippingDocList">
		              <s:if test="filePath!=null">
		              <tr>
		                <th width="150">
		                	<s:if test="filePath.substring(0,4)==\"http\"">
				                	<input type="checkbox" name="chk" id="chk" value="" readonly="readonly"/>
				                </s:if>
				                <s:else>
				                	<input type="checkbox" name="chk" id="chk" value="${filePath},${isOldProdctFile}" checked="checked" onclick="onClickCheckBox('${filePath},${isOldProdctFile}')"/>
				            </s:else>
		                </th>
		                <td width="208"> 
		                <s:if test="filePath.substring(0,4)==\"http\"">
		                	<a href="${filePath}" target="_self"><font color="blue">${docName}</font></a>
		                </s:if>
		                <s:else>
		                	<a href="javaScript:void(0);" onclick="ff('${filePath}','${docName}','${isOldProdctFile}')" target="_self"><font color="blue">${docName}</font></a>
		                </s:else>
		                
		                </td>
		                </tr>
		                </s:if>
		       		  </s:iterator>
		       		   <s:iterator value="shippingProductDocList">
		       		    <s:iterator value="manuDoc">
		       		    <s:if test="filePath!=null">
			              <tr>
			                <th width="152">
			                	<s:if test="filePath.substring(0,4)==\"http\"">
				                	<input type="checkbox" name="chk" id="chk" value="" readonly="readonly"/>
				                </s:if>
				                <s:else>
				                	<input type="checkbox" name="chk" id="chk" value="${filePath},${isOldProdctFile}" checked="checked" onclick="onClickCheckBox('${filePath},${isOldProdctFile}')"/>
				                </s:else>
			                </th>
			                <td width="208"> 
			                	 <s:if test="filePath.substring(0,4)==\"http\"">
				                	<a href="${filePath}" target="_self"><font color="green">${docName}</font></a>
				                </s:if>
				                <s:else>
				                	<a href="javaScript:void(0);" onclick="ff('${filePath}','${docName}','${isOldProdctFile}')" target="_self"><font color="green">${docName}</font></a>
				                </s:else>
			                	</td>
			                </tr>
			                </s:if>
		                </s:iterator>
		       		  </s:iterator>
		       		  </table>
		       		  </div>
		       		  <table border="0" cellpadding="0" cellspacing="0" class="General_table">
		              <tr>
		              	<th width="200"></th>
		                <td width="508">
		                  <input type="hidden" id = "orderNo" value="${orderNo}"/>
		                  <input type="hidden" id = "itemNo" value="${itemNo}"/>
		                  <input type="hidden" id = "orderItemId" value="${orderItemId}"/>
		                  <input type="hidden" id = "docName" value=""/>
		                  <input type="submit" name="Submit622" value="Close"  class="style_botton" onclick="parent.$('#shipping_document').dialog('close');"/>
		                  <s:if test="((shippingDocList!=null)||(shippingProductDocList!=null))">
		                  	<input type="button" name="zipPrint" class="style_botton3"  value="Add to Archive(Zipfile)" onclick="func_Zip('${zipDoc}')"/>
		                  	
		                  	<input class="style_botton" type= "button"   name= "print"   value= "Print All "   onclick= "func_print()" /> 
		                  </s:if>
		                  <s:else>
		                  		<input type="submit" name="Submit"class="style_botton3"  value="Add To Active(Zipfile)" disabled="disabled"/>
		                  		
		                  		<input class="style_botton" type= "button"   name= "print"   value= "Print All "   onclick= "func_print()" disabled="disabled"/> 
		                  </s:else>
		                  
						  
		                	
		                </td>
		              </tr>
		            </table></td>
		          </tr>
		        </table>
		        <form action="" id="httpFileForm" method="post" target="_blank">
		        </form>
		        <form action="" id="fileForm" method="post">
      				<input name ="fileName" id="fileName" type="hidden"/>
      				<input name ="oldFlag" id="oldFlag" type="hidden"/>
     			</form>
		
	</body>
</html>