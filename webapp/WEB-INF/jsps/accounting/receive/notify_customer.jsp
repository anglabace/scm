<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${global_js_url}tools.js"></script>
<script type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>

<script type="text/javascript">
  function closeDlg(){
  window.parent.$("#sendMail").dialog('close');
  }
  
  function sendMail(){
  var flag = checkFormNotNull("form1");
  if(!flag) return;
      $("#form1").attr("action","send_mail!sendMail.action");
      $("#form1").submit();
  }
  
  function checkForm(){
    return checkFormNotNull("form1");
  }
  
  $(document).ready(function(){
     initSelect(); //自动填充select
     initContent();
                 //var email = window.parent.email;
               //var email = "415150657@qq.com";
               //$("#Cc").val("zy@inforich.com.cn");
                //$("#To").html("<option value='"+email+"'>"+email+"</option>");
  });
  
  function checkTo(value){
    var flag = isEmail(value);
    if(flag){
    $("#send").attr("disabled","");
    }
  }
  
  function getContent(){
    var val = $("#select3").val();
    $("#content").html(val);
  }
  
  /*检查抄送地址是否正确，如果为空，则正确，如果不为空，并且其中有一个地址不正确则不正确*/
  function checkCc(value){
  var flag = false;
  if(value == ''){
  flag = true;
  }else{
     if(value.indexOf(",")==-1){
     flag = isEmail(value);
     }else{
       var arr = value.split(",");
       for(var i=0;i<arr.length;i++){
          if(arr[i] == ''){continue;}
          if(!isEmail(arr[i])){
            flag = false;
            break;
          }else{
            flag = true;
          }
       }
    }
    }
    if(flag){
    $("#send").attr("disabled","");
    }else{
     alert("CC Address is invalid!");
     $("#Cc").val("");
    $("#send").attr("disabled","disabled");
    }
  
  }
  
  <%
 if( request.getAttribute("foo") != null){
  %>
  $(document).ready(function(){
    <%if(request.getAttribute("foo").equals("success")){ %>
    alert("Mail Send Success");
    window.parent.$("#sendMail").dialog('close');
    <%}else{%> 
        alert("${foo}");
    <%}%>
  });
  <%}%>

function addFile(){

}

function deleteFile(){
 $("#attach").val('');
}

//init content to send
function initContent(){
 $.ajax({
	        url : "fill_select!initContentToSend.action",
	        type : 'post',
	        success : function(html){
	           $("#select3").html(html);
	        },
	        error: function(){
	          alert("error");
	        }
	    });  
}

</script>

</head>

<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<body>

<table width="550" border="0" cellspacing="3" cellpadding="0" id="table11" >
  <tr>
    <td bgcolor="#FFFFFF"><table width="650" border="0" cellspacing="0" cellpadding="0" >
 
      <tr>
        <td style="padding-left:20px;"><br />
        
        
        <form name="form1" id="form1" method="post" enctype ="multipart/form-data">
          <table width="590" height="217" border="0" cellpadding="0" cellspacing="0" class="General_table">
            <tr>
              <th>&nbsp;</th>
              <th>To</th>
              <td>
             <!--  
              <input type="text" id="To" name="To"  class="NFText" onblur="checkTo(this.value)" desc="Recipient" required="true"/>
             -->
             
             <input type="hidden" id="custNo" name="custNo" value="<%=request.getParameter("cust_no")%>"/>
             
              <select id="To" name="To"  desc="Recipient" required="true" sql="Select bus_email,bus_email from customer.customer_addresses where addr_type = 'CONTACT' and cust_no = <%=request.getParameter("cust_no")%>">
                  <option></option>
              </select>
              </td>
              
              
              <td>&nbsp;</td>
            </tr>
            <tr>
              <th width="54">&nbsp;</th>
              <th width="140">CC</th>
              <td><input name="Cc" type="text" id="Cc"  onblur="checkCc(this.value)"  class="NFText"/></td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <th>&nbsp;</th>
              <th>
              <span style="color:red">*</span>
              Subject</th>
              <td><input name="Subject" type="text" id="Subject"    class="NFText" desc="Subject" required="true"/></td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <th >&nbsp;</th>
              <th >Content to send</th>
              <td width="385">
              <select name="select3" id="select3" style="width:200px" onchange="getContent()" >
               <option value=""></option>
              </select></td>
              <td width="11">&nbsp;</td>
            </tr>
            <tr>
              <th >&nbsp;</th>
              <th >&nbsp;</th>
              <th ><textarea name="Content"  id="content" class="content_textarea2" desc="Content" required="true" style="width:375px;"></textarea></th>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <th >&nbsp;</th>
              <th >Attachment</th>
              <td><input name="upload" type="file" id="attach"    class="NFText"/>   
                           <input type="submit" name="Submit4" class="style_botton" value="Delete" onclick="deleteFile()" /></td>
              <td>&nbsp;</td>
            </tr>
            <!--  
            <tr>
              <th >&nbsp;</th>
              <td >&nbsp;</td>
              <td><input name="textfield2" type="text" id="textfield2"    class="NFText"/>              
                <input type="submit" name="Submit5" class="style_botton" value="Upload" /></td>
              <td>&nbsp;</td>
            </tr>
            -->
            <tr>
              <td height="24" colspan="4"><div align="center">
                <input type="button" id="send" class="style_botton" value="Send" onclick="sendMail()" />
                <input type="button" id="cancel" class="style_botton" value="Cancel" onclick="closeDlg()"/>
              </div></td>
            </tr>
          </table>    
          </form>
          
                <br /></td>
      </tr>
    </table></td>
  </tr>
</table>

</body>
</html>
