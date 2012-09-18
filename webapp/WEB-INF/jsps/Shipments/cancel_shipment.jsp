<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Cancel shipment</title>
<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
<link href="stylesheet/openwin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function confirm(){
	    var packageId = document.getElementById("packageId").value;
	    var reason = document.getElementById("reason").value;
	    var methodId = parent.document.getElementById("sm").value;
	    if(reason==""||reason==null){
			alert("Please enter the Reason.");
			return ;
		}
	    if(methodId==""||methodId==null){
			alert("The Shipment Method is requeied.");
			return ;
		}
	    document.forms[0].action = "shipments!doCancelShipment.action?methodId="+methodId+"&packageId="+packageId+"&reason="+reason;
	    document.forms[0].submit();
	}
	function Len(str)
	{
	     var i,sum;
	     sum=0;
	     for(i=0;i<str.length;i++)
	     {
	         if ((str.charCodeAt(i)>=0) && (str.charCodeAt(i)<=255))
	             sum=sum+1;
	         else
	             sum=sum+2;
	     }
	     return sum;
	}
	function checkLen(){
	   reason = document.getElementById('reason').value;
	   if(Len(reason)>255)
	   {
	    alert("Reason length too long!");
	    return false;
	   }
	
	}
</script>
</head>
<body>
<form name="fr" method="post">
                  <table width="600" border="0" cellpadding="0" cellspacing="0" class="General_table">
                       <tr>
    <th colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <th width="46%">Select the shipment to be cancelled</th>
        <td width="54%">&nbsp;</td>
      </tr>
    </table></th>
    </tr>
                    <tr>
    <th width="151">Tracking No</th>
    <td width="449">
    <select name="pDto.packageId" id="packageId" onchange="selectTr();">
            <c:forEach var="rc" items="${trlist}">
				<option value="${rc.trackingNo==''?0:rc.packageId }">
					${rc.trackingNo }
				</option>
			</c:forEach>
    </select>
    </tr>
  <tr>
    <th>Reason </th>
    <td><input id="reason" onblur="checkLen();" type="text" class="NFText" style="width:300px;" /></td>
    </tr>
  <tr>
    <td height="100" colspan="2">
      <div align="center">
        <input type="button" name="Submit3" class="style_botton" value="Confirm" onclick="confirm();"/>    
        <input type="button" name="Submit" class="style_botton" value="Cancel" onclick="parent.$('#cancel1').dialog('close');"/>
      </div></td>
    </tr>
</table>
</form>
</body>
</html>