<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
<link href="stylesheet/openwin.css" rel="stylesheet" type="text/css" />
 
<script language="javascript" type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
	// save combine info to session
	
	function confirm(){
		 var reason=document.getElementById("reason").value;
		 if(reason==""||reason==null){
				alert("Please enter the Reason.");
				return ;
			}
		location.href="shipments!doCancelCHShipment.action?packageId="+document.getElementById("packageId").value+"&reason="+document.getElementById("reason").value;
	}
	/*
	function check(){
		var packageId=document.getElementById("packageId").value;
		if(packageId==0){
			alert("No TrackingNo!");
			return false;
		//	parent.$('#cancel1').dialog('close');
		}
	}*/
</script>
 
</head>
 
<body>
<table width="600" border="0" cellpadding="0" cellspacing="3" bgcolor="#96BDEA">
  <tr>
    <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
     
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td style="padding-left:20px;"><table border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><br />
                  <br />
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
            <c:forEach var="rc" items="${trlist1}">
				<option value="${rc.trackingNo==''?0:rc.packageId }">
					${rc.trackingNo }
				</option>
			</c:forEach>
    </select>
    </tr>
  <tr>
    <th>Reason </th>
    <td><input id="reason" type="text" class="NFText" style="width:300px;" /></td>
    </tr>
  <tr>
    <td height="100" colspan="2">
      <div align="center">
        <input type="submit" name="Submit3" class="style_botton" value="Confirm" onclick="confirm();"/>    
        <input type="submit" name="Submit" class="style_botton" value="Cancel" onclick="javascript:history.go(-1);"/>
      </div></td>
    </tr>
</table></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>