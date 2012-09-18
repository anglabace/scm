<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
<link href="stylesheet/openwin.css" rel="stylesheet" type="text/css" />
 
<script language="javascript" type="text/javascript" src="${global_js_url}jquery.js"></script>
<script type="text/javascript">
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
<form name="combineForm" action="shipments!combineSession.action">
                  <table width="600" border="0" cellpadding="0" cellspacing="0" class="General_table">
                     <tr>
    <th colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <th width="44%">Select the shipment combined to</th>
        <td width="56%">&nbsp;</td>
      </tr>
    </table></th>
    
    </tr>
       <tr>
            <th colspan="2"> <div align="center">(Note: All orders will be delivered through this single shipment after combined) </div></th>
            </tr>
       <tr>
    <th width="237">Shipment No  </th>
    <td width="463">
    <input type="hidden" name="idStr" value="${idStr}"/>
    <select name="shipmentId" id="shipmentId" onchange="selectBranch();">
            <c:forEach items="${soList}" var="rc">
				<option value="${rc.shipmentId }">
					${rc.shipmentNo }
				</option>
			</c:forEach>
    </select>
    
    </td>
    </tr>
  <tr>
    <th>Reason</th>
    <td><input name="reason" id="reason" onblur="checkLen();" type="text" value="${reason }" class="NFText" style="width:300px;" /></td>
    </tr>
  <tr>
    <td height="100" colspan="2">
      <div align="center">
        <input type="submit" name="Submit3" class="style_botton" value="Confirm"/>    
        <input type="button" name="Submit4" class="style_botton" value="Cancel" onclick="parent.$('#combine1').dialog('close');"/>
      </div></td>
    </tr>
</table>
</form>
</body>
</html>