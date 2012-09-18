<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
<script src ="${global_js_url }table.js" type="text/javascript"></script>
<style type="text/css">
<!--
body {
	width:570px; margin:5px auto;
}
-->
</style>
<script>
function   cc(e)  
{  
	var   a   =   document.getElementsByName("pdtServSeq");  
	for   (var   i=0;   i<a.length;   i++) {
		if(!a[i].disabled){
			a[i].checked = e.checked;
		}
	}
}

function check(id,no){
	var isFalse= true;
	for(var i=0;i<id.length;i++){
		if(id[i]==no){
			isFalse = false;
		}
	}
	return isFalse;
}

function save(name){
	var nos = get_checked_str(name);
	var no = nos.split(",");
	var html = "<tr>";
	var productIds = "";
	var productIdList = parent.$('#productIdList').val();
	var productIdss = productIdList.split(",");
	var j = 0;
	for(var i=0;i<no.length;i++){
		if(check(productIdss,no[i])){
			if(j%6==0){
	    		html += "</tr>";
	    		html += "<tr>";
	        }
			var productId = no[i];
			var catalogNo = $("#catalogNo_"+no[i]).val();
			html += "<td>";
			html += "<input name='"+productId+"' type='checkbox' id='"+productId+"' value='"+productId+"' disabled='disabled' checked='true'/>";
			html += catalogNo;
	    	html += "</td>";
	    	if(productIds==""){
	    		productIds +=productId;
	        }else{
	        	productIds +=","+productId;
	        }
	        j++;
		}
    	
	}
	html+="</tr>";
	if(productIds!=""){
		if(productIdList==null||productIdList==""||productIdList=="null"){
			productIdList=productIds;
		}else{
			productIdList += productIds;
		}
		parent.$('#documentProductTable').append(html);
		parent.$('#productIdList').val(productIdList);
	}
	parent.$('#addProductDialog').dialog('close');
}
function get_checked_str(name)
{
	var a = document.getElementsByName(name);
	var str = '';
	for   (var   i=0;   i<a.length;   i++)
	{
		if(a[i].checked)
		{
			str += a[i].value+',';
		}
	}
	return str.substring(0,str.length-1);
}
</script>
</head>
<body>
<form method="get" action="">
<table width="570" border="0" cellpadding="0" cellspacing="0">
      <tr>
                 <td width="91" style="padding:0px;">&nbsp;</td>
                 <td width="135" ><select name="item"  id='item' style="width:124px;">
                   <option  value='product'  selected="selected">Product Item</option>
                   <!--<option value='service'>Service Item</option>
                 --></select></td>
                 <td width="132" align="center">&nbsp;</td>
                 <td colspan="3" align="center">&nbsp;</td>
              </tr>
               <tr>
                 <th>Category Name</th>
                 <td><input name="categoryName" type="text" class="NFText" size="18" value="${categoryName}"/></td>
                 <th>Product/Service Name</th>
                 <td width="106"><input name="name" type="text" class="NFText" size="18" value="${name }"/></td>
                 <th width="70">Catalog No</th>
                 <td width="140"><input name="catalogNo" type="text" class="NFText" size="18" value="${catalogNo }"/></td>
               </tr>
          <tr>
            <td height="30" colspan="6" align="center"><input name="Submit3" type="submit" class="style_botton" value="Search" onclick=""/></td>
          </tr>

<!--<tr>
	<td>
		Catalog No
	</td>
	<td>
		<input type="text" name="catalogNo" value="${catalogNo}"/>
	</td>
	<td>
		Name
	</td>
	<td>
		<input type="text" name="name" value="${name}"/>
	</td>
	<td>
		<input type="submit" value="Search"/>
	</td>
</tr>

--></table>
<input type="hidden" name="docId" value="${docId}" />
</form>
  <table width="570" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="4" style="padding-top:10px;"><table width="550" border="0" cellpadding="0" cellspacing="0" class="list_table">
      <tr>
        <th width="28"><input name="pdtServCh" type="checkbox" onclick="cc(this)" /></th>
        <th width="100">Ctlg No</th>
        <th width="150"> Name </th>
        <th>Description</th>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="4" style="padding-bottom:20px; "><div  style="width:567px; height:150px; overflow:scroll;">
      <table width="550" border="0" cellpadding="0" cellspacing="0" class="list_table">
      	<s:iterator value="page.result">
        <tr>
          <td width="28"><div align="center">
              <input type="checkbox" value="${productId}" name="pdtServSeq" id="pdtSerSeq"/>
              <input type="hidden" id="catalogNo_${productId}" name="catalogNo_${productId}" value="${catalogNo}"/>
          </div></td>
          <td width="100"><div align="left">
          ${catalogNo}&nbsp;
          </div></td>
          <td width="150" align="left"><s:if test="%{name != null && name.length()>25}">
<s:property value="name.substring(0,25)+'...'"/></s:if><s:else>${name}</s:else>&nbsp;</td>
          <td>${description}</td>
        </tr>
        </s:iterator>
      </table>
    </div>
    <div class="grayr">
    	<div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
			  <jsp:param value="product/product!searchProductNotInDocument.action" name="moduleURL"/>
			</jsp:include>
		</div>
    </div>
    </td>
  </tr>
  <tr>
    <td colspan="4">
      <div align="center">
        <input type="button" name="selectTrigger" value="Select" class="style_botton" onclick="save('pdtServSeq')"/>
        <input type="button" value="Close" class="style_botton" onclick="parent.$('#addProductDialog').dialog('close');" />
      </div>
	</td>
  </tr>
</table>  
</body>
</html>