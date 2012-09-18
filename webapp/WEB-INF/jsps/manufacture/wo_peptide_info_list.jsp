<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" /> 
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
              	<td><a href="download.action?filePath=${wordDoc.filePath}&fileName=${wordDoc.docName}">${wordDoc.docName}</a></td>
              </tr>
                <tr>
                  <td><table width="98.7%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <tr>
                      <th width="10%">Order-Item</th>
                      <th width="9%">Work Order No</th>
                      <th width="10%">Name</th>
                      <th width="9%">MW</th>
                      <th width="12%">Lot.No</th>
                      <th width="9%">Real Quantity</th>
                      <th width="10%">Aliqo</th>
                      <th width="12%">Appearance</th>
                      <th width="9%">Destination</th>
                      <th width="10%">Real Purity</th>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td><div  style="width:100%; height:210px; overflow:scroll;">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <s:iterator value="peptideTempList" status="st">
                    	<s:if test="#st.odd">
                    		<tr>
                    			<td  width="10%">
                    				${orderId}
                    			</td>
                    			<td  width="9%">
                    				${Wo}
                    			</td>
                    			<td  width="10%">
                    				${name}
                    			</td>
                    			<td  width="9%">
                    				${mw}
                    			</td>
                    			<td  width="12%">
                    				${lotNo}
                    			</td>
                    			<td  width="9%">
                    				${realQuantity}
                    			</td>
                    			<td  width="10%">
                    				${aliq}
                    			</td>
                    			<td  width="12%">
                    				${appearance}
                    			</td>
                    			<td  width="9%">
                    				${destination}
                    			</td>
                    			<td  width="10%">
                    				${realPurity}
                    			</td>
                    		</tr>
                    	</s:if>
                    	<s:else>
                    		<tr>
                    			<td class="list_td2">
                    				${orderId}
                    			</td>
                    			<td  class="list_td2">
                    				${Wo}
                    			</td>
                    			<td class="list_td2">
                    				${name}
                    			</td>
                    			<td class="list_td2">
                    				${mw}
                    			</td>
                    			<td class="list_td2">
                    				${lotNo}
                    			</td>
                    			<td  class="list_td2">
                    				${realQuantity}
                    			</td>
                    			<td  class="list_td2">
                    				${aliq}
                    			</td>
                    			<td  class="list_td2">
                    				${appearance}
                    			</td>
                    			<td  class="list_td2">
                    				${destination}
                    			</td>
                    			<td  class="list_td2">
                    				${realPurity}
                    			</td>
                    		</tr>
                    	</s:else>
                    </s:iterator>
                    </table>
                  </div></td>
                </tr>
             </table>
</body>
</html>
