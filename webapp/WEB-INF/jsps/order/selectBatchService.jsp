<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@ include file="/common/taglib.jsp"%>
<html>
<head>
</head>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<body>
   <table width="420" border="0" cellpadding="0" cellspacing="0">
     <tr>
       <td><table width="81%" border="0" cellpadding="0" cellspacing="0" class="General_table">
         <tr>
           <td width="39%">&nbsp;</td>
           <td width="61%">&nbsp;</td>
         </tr>
         <tr>
           <th>Batch Quotation Type</th>
           <td><select name="quBatch"  id='quBatch' onchange="">
             <option value="geneBatch">Gene Batch Quotation</option>
           </select></td>
         </tr>
         <tr>
           <td height="80" colspan="2"><div align="center">
             <input name="select" id="select" type="submit" class="style_botton"  value="Select"/>
             <input type="submit" name="cancel" id="cancel" value="Cancel" class="style_botton"  />
           </div></td>
         </tr>
       </table></td>
     </tr>
   </table>
</body>
<script language="javascript" type="text/javascript">
    $( '#cancel' ).click( function() {
			window.parent.$( '#selectBatch' ).dialog( 'close' )  ;
		});

    $( '#select' ).click( function() {
			window.parent.showAddBatch() ;
		});
</script>
</html>