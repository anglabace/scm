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
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>

<script language="javascript" type="text/javascript" src="${global_js_url}scm/product/manager_task.js?v=2"></script>
<script language="JavaScript" type="text/javascript">
	function product(nameOldVal,nameNewVal,nameReason,statusOldVal,statusNewVal,statusReason,unitCostOldVal,unitCostNewVal,unitCostReason){
		var html = "";	
		if(nameNewVal!=null&&nameNewVal!=""&&nameNewVal!="null"){
			html+="Names："+nameOldVal+"->"+nameNewVal+"<br>Reason："+nameReason+"<br>";
		}
		if(statusNewVal!=null&&statusNewVal!=""&&statusNewVal!="null"){
			html+="Status："+statusOldVal+"->"+statusNewVal+"<br>Reason："+statusReason+"<br>";
		}
		if(unitCostNewVal!=null&&unitCostNewVal!=""&&unitCostNewVal!="null"){
			html+="Unit Cost："+unitCostOldVal+"->"+unitCostNewVal+"<br>Reason："+unitCostReason;
		}
		tooltip.show(html);
	}
</script>
</head>
<body class="content" style="background-image:none;">

<div class="scm">
<div class="title_content">
  <div class="title">Manager's Task List </div>
</div>
<div class="search_box" style="font-weight:normal;">
 <div class="single_search" style="font-weight:normal;">
 <form id="searchForm" method="get" action="product/product!showManagerTaskList.action"> 
View
  <select id="approveType" name="approveType" style="width:250px;" initVal="${approveType}">
    <option value="Catalog">Catalog Change Requests</option>
    <option value="ProductCategory">Product Category Change Requests</option>
    <option value="ServiceCategory">Service Category Change Requests</option>
	<option value="Product">Product Change Requests</option>
	<option value="Service">Service Change Requests</option>
	<option value="ProductPrice">Product Price Change Requests</option>
	<option value="ServicePrice">Service Price Change Requests</option>
  </select>
  <input type="submit" value="Refresh" class="search_input" />
  </form>
 </div>
</div>

<div class="input_box">

<s:if test="approveType==\"Catalog\"">
<div id="CatalogDiv" >
  <div align="center"  class="blue_price">Catalog Change Requests</div>
  <br />
  <table width="1010" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td>
			    <div style="margin-right:17px;"><table width="1020" border="0" cellspacing="0" cellpadding="0" class="list_table2">
			      <tr>
			        <th width="48">
				        <div align="left">
				        	<input name="checkbox2" type="checkbox"  onclick="checkAll(this, 'requestId');" />
				        </div>
			        </th>
			        <th width="65">Catalog ID </th>
			        <th width="150">Name</th>
			        <th width="60">Status </th>
			        <th width="60">Type</th>
			        <th width="190">Description</th>
					<th width="70">Modify Info</th>
			        <th width="70">Modify Date  </th>
			        <th width="60">Currency </th>
			        <th width="110">Price Limit Control </th>
			        <th>Publish Date </th>
			        </tr>
			    </table>
			    </div>
		    </td>
		  </tr>
		  <tr>
		    <td> 
			    <div class="list_box" style="">
				    <table width="1020" border="0" cellspacing="0" cellpadding="0" class="list_table2" style="table-layout:fixed;word-break:break-all">
				    <s:iterator value="catalogDTOList">
				      <tr>
				        <td width="48">
				        	<input type="checkbox" value="${requestId}" name="requestId"/>
				        </td>
				        <td width="65" style="word-break:break-all;word-wrap:break-word;width:65">
				        	<a href="${ctx }/product/catalog!input.action?id=${id}&callBackName=catalogCreationForm&requestId=${requestId }&approvedMethod=approvedEdit&approvedName=${nameNewVal}&approvedStatus=${statusNewVal}&operation_method=edit">
				        	 ${catalogId}&nbsp;
				        	</a>
				        </td>
				        <td width="150" style="word-break:break-all;word-wrap:break-word;width:150">
							 ${catalogName}&nbsp;				        
				        </td>
				        <td width="60"><div align="center">${status}</div></td>
				        <td width="60"><div align="center">${type}</div></td>
				        <td width="190" style="word-break:break-all;word-wrap:break-word;width:190">${description}</td>
						<td width="70">
							<div align="center">
								<s:if test="nameNewVal!=null&&statusNewVal!=null">
								<span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:50px; font-size: 24px;" 
								onmouseover="tooltip.show('Name：${nameOldVal}->${nameNewVal}<br>Reason：${nameReason}<br>Status：${statusOldVal}->${statusNewVal}<br>Reason：${statusReason}');" 
								onmouseout="tooltip.hide();">
								
								</s:if>
								<s:elseif test="nameNewVal!=null">
								<span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:50px; font-size: 24px;" 
								onmouseover="tooltip.show('Name: ${nameOldVal}->${nameNewVal}<br>Reason：${nameReason}');" 
								onmouseout="tooltip.hide();">
								
								</s:elseif>
								<s:else>
								<span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:50px; font-size: 24px;" 
								onmouseover="tooltip.show('Status：${statusOldVal}->${statusNewVal}<br>Reason：${statusReason}');" 
								onmouseout="tooltip.hide();">
								
								</s:else>
								
								<img src="images/qa.jpg" width="12" height="12"/>
								</span>
							</div>
						</td>
				        <td width="70"><div align="center"><s:date name="modifyDate" format="yyyy-MM-dd" /></div></td>
				        <td width="60"><div align="center">${currencyCode}</div></td>
				        <td width="110">
				        	<div align="center">
				        	<s:if test="priceLimit==\"Y\"">Yes</s:if>
				        	<s:else>No</s:else>
				        	</div>
				        </td>
				        <td><div align="center"><s:date name="publishDate" format="yyyy-MM-dd" /></div></td>
				        </tr>
				       </s:iterator>
				    </table>
			    </div>
		    </td>
		  </tr>
     </table>
</div>
</s:if>
<s:elseif test="approveType==\"ProductCategory\"">
<div id="ProductCategoryDiv">
      <div align="center">
	      <span class="blue_price">Category Change Requests
	       </span>
      </div>
      <br />
      <table width="1027" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><div style="margin-right:17px;">
              <table width="1010" border="0" cellspacing="0" cellpadding="0" class="list_table2">
                <tr>
                  <th width="46">
                  	<div align="left">
                      <input name="checkbox2" type="checkbox" onclick="checkAll(this, 'requestId');" />
                     </div>
                  </th>
                  <th width="100">Category No </th>
                  <th width="200">Name</th>
                  <th width="230">Description</th>
				   <th width="70">Modify Info</th>
				  <th width="70">Status</th>
                  <th width="90">Asset Group </th>
                  <th>Modify Date </th>
                  <th >Creation Date </th>
                </tr>
              </table>
          </div></td>
        </tr>
        <tr>
          <td>
          <div class="list_box" style="height:160px;">
              <table width="1010" border="0" cellspacing="0" cellpadding="0" class="list_table2" style="table-layout:fixed;word-break:break-all">
              <s:iterator value="productCategoryDTOList">
                <tr>
                  <td width="46">
                  	<input type="checkbox" value="${requestId}" name="requestId"/>                  
                  </td>
                  <td width="100" style="word-break:break-all;word-wrap:break-word;width:100">
                  	<a href="${ctx }/product/product_category!input.action?categoryId=${categoryId}&requestId=${requestId }&approvedMethod=approvedEdit&approvedName=${nameNewVal}&approvedStatus=${statusNewVal}&callBackName=categoryCreationForm&operation_method=edit">
                  	 ${categoryNo}&nbsp;
                  	</a>
                  </td>
                  <td width="200" style="word-break:break-all;word-wrap:break-word;width:200">
					${name}&nbsp;				        
				</td>
                  <td width="230" style="word-break:break-all;word-wrap:break-word;width:230">${description}</td>				  
				  <td width="70">
				  	<div align="center">
				  				<s:if test="nameNewVal!=null&&statusNewVal!=null">
								<span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:10px; font-size: 24px;" 
								onmouseover="tooltip.show('Name: ${nameOldVal}->${nameNewVal}<br>Reason：${nameReason}<br>Status: ${statusOldVal}->${statusNewVal}<br>Reason：${statusReason}');"
								onmouseout="tooltip.hide();">
								
								</s:if>
								<s:elseif test="nameNewVal!=null">
								<span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:10px; font-size: 24px;" 
						onmouseover="tooltip.show('Name: ${nameOldVal}->${nameNewVal}<br>Reason：${nameReason}');"
						onmouseout="tooltip.hide();">
				
								</s:elseif>
								<s:else>
								<span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:10px; font-size: 24px;" 
						onmouseover="tooltip.show('Status: ${statusOldVal}->${statusNewVal}<br>Reason：${statusReason}');"
						onmouseout="tooltip.hide();">
					
								</s:else>
						<img src="images/qa.jpg" width="12" height="12"/>
					</span>
					</div>
				</td>				  
                  <td width="70">
                  	<div align="center">${status}</div>
                  </td>
                  <td width="90">${assetGroup}</td>
                  <td width="74">
                  	<div align="center"><s:date name="modifyDate" format="yyyy-MM-dd" /></div>
                  </td>
                  <td><div align="center"><s:date name="creationDate" format="yyyy-MM-dd" /></div></td>
                </tr>
               </s:iterator>
              </table>
          </div></td>
        </tr>
      </table>
  </div>
</s:elseif>

<s:elseif test="approveType==\"ServiceCategory\"">
<div id="ServiceCategoryDiv">
      <div align="center">
	      <span class="blue_price">Category Change Requests
	       </span>
      </div>
      <br />
      <table width="1027" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><div style="margin-right:17px;">
              <table width="1010" border="0" cellspacing="0" cellpadding="0" class="list_table2">
                <tr>
                  <th width="46">
                  	<div align="left">
                      <input name="checkbox2" type="checkbox"  onclick="checkAll(this, 'requestId');" />
                     </div>
                  </th>
                  <th width="100">Category No </th>
                  <th width="200">Name</th>
                  <th width="230">Description</th>
				   <th width="70">Modify Info</th>
				  <th width="70">Status</th>
                  <th width="90">Asset Group </th>
                  <th>Modify Date </th>
                  <th >Creation Date </th>
                </tr>
              </table>
          </div></td>
        </tr>
        <tr>
          <td>
          <div class="list_box" style="height:160px;">
              <table width="1010" border="0" cellspacing="0" cellpadding="0" class="list_table2" style="table-layout:fixed;word-break:break-all">
             <s:iterator value="serviceCategoryDTOList">
                <tr>
                  <td width="46">
                  	<input type="checkbox" value="${requestId}" name="requestId"/>                  
                  </td>
                  <td width="100" style="word-break:break-all;word-wrap:break-word;width:100">
                  	<a href="${ctx }/serv/service_category!input.action?categoryId=${categoryId}&callBackName=categoryCreationForm&operation_method=edit&requestId=${requestId }&approvedMethod=approvedEdit&approvedName=${nameNewVal}&approvedStatus=${statusNewVal}">
					${categoryNo}&nbsp;
					</a>
                  </td>
                  <td width="200" style="word-break:break-all;word-wrap:break-word;width:200">${name}&nbsp;</td>
                  <td width="230" style="word-break:break-all;word-wrap:break-word;width:230">${description}</td>				  
				  <td width="70">
				  	<div align="center">
						<s:if test="nameNewVal!=null&&statusNewVal!=null">
								<span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:10px; font-size: 24px;" 
								onmouseover="tooltip.show('Name: ${nameOldVal}->${nameNewVal}<br>Reason：${nameReason}<br>Status: ${statusOldVal}->${statusNewVal}<br>Reason：${statusReason}');"
								onmouseout="tooltip.hide();">
								
								</s:if>
								<s:elseif test="nameNewVal!=null">
								<span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:10px; font-size: 24px;" 
						onmouseover="tooltip.show('Name: ${nameOldVal}->${nameNewVal}<br>Reason：${nameReason}');"
						onmouseout="tooltip.hide();">
						
								</s:elseif>
								<s:else>
								<span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:10px; font-size: 24px;" 
						onmouseover="tooltip.show('Status: ${statusOldVal}->${statusNewVal}<br>Reason：${statusReason}');"
						onmouseout="tooltip.hide();">
						
								</s:else>
						<img src="images/qa.jpg" width="12" height="12"/>
					</span>
					</div>
				</td>				  
                  <td width="70">
                  	<div align="center">${status}</div>
                  </td>
                  <td width="90">${assetGroup}</td>
                  <td width="74">
                  	<div align="center"><s:date name="modifyDate" format="yyyy-MM-dd" /></div>
                  </td>
                  <td><div align="center"><s:date name="creationDate" format="yyyy-MM-dd" /></div></td>
                </tr>
               </s:iterator>
              </table>
          </div></td>
        </tr>
      </table>
  </div>
</s:elseif>
<s:elseif test="approveType==\"Product\"">
<div id="ProductDiv">
      <div align="center"><span class="blue_price">Product Change Requests
        </span>
      </div><br />
      <table width="1037" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>
	          <div style="margin-right:17px;">
	            <table width="1020" border="0" cellspacing="0" cellpadding="0" class="list_table2">
	                <tr>
	                  <th width="46"><div align="left">
	                      <input name="checkbox3" type="checkbox"  onclick="checkAll(this, 'requestId');" />
	                   </div></th>
	                  <th width="70">Catalog No</th>
	                  <th width="70">Type</th>
	                  <th width="150">Name</th>
	                  <th width="245">Description</th>
					  <th width="70">Modify Info</th>
	                  <th width="60">Status</th>
	                  <th width="40">Size</th>
	                  <th width="65">Unit Price </th>
	                  <th width="70">Modify Date </th>
	                  <th>Creation Date </th>
	                </tr>
	              </table>
	          </div>
          </td>
        </tr>
        <tr>
          <td><div class="list_box" style="height:160px" >
              <table width="1020" border="0" cellspacing="0" cellpadding="0" class="list_table2" style="table-layout:fixed;word-break:break-all">
              <s:iterator value="productDTOList">
                <tr>
                  <td width="46">
                  	<input type="checkbox" value="${requestId}" name="requestId"/>
                  </td>
                  <td width="70" style="word-break:break-all;word-wrap:break-word;width:70">
                  	<a href="${ctx}/product/product!input.action?id=${productId}&type=${type }&operation_method=edit&requestId=${requestId }&approvedMethod=approvedEdit&approvedName=${nameNewVal}&approvedStatus=${statusNewVal}&unitCostAppr=${unitCostNewVal }">
                  	${catalogNo}&nbsp;</a>
                  </td>
                  <td width="70">${type}</td>
                  <td width="150" style="word-break:break-all;word-wrap:break-word;width:150">${name}&nbsp;</td>
                  <td width="245" style="word-break:break-all;word-wrap:break-word;width:245">${description}</td>
                  <td width="70">
	                  	<div align="center">
							<span
							onmouseover="product('${nameOldVal}','${nameNewVal}','${nameReason}','${statusOldVal}','${statusNewVal}','${statusReason}','${unitCostOldVal}','${unitCostNewVal}','${unitCostReason}')" 
							onmouseout="tooltip.hide();">
							<img src="images/qa.jpg" width="12" height="12"/>
							</span>
						</div>
				</td>
				  <td width="60">${status}</td>
                  <td width="40">${size}${uom}</td>
                  <td width="65">
                  	<div align="right">${unitPrice}</div>
                  </td>
                  <td width="70">
                  	<div align="center"><s:date name="modifyDate" format="yyyy-MM-dd" /></div>
                  </td>
                  <td><div align="center"><s:date name="creationDate" format="yyyy-MM-dd" /></div></td>
                </tr>
                </s:iterator>
              </table>
          </div></td>
        </tr>
      </table>
  </div>
</s:elseif>

<s:elseif test="approveType==\"Service\"">
<div id="ServiceDiv">
      <div align="center"><span class="blue_price">Service Change Requests
        </span>
      </div><br />
	  <table width="1037" border="0" cellspacing="0" cellpadding="0">

  <tr>
  	<td>
  	<div style="margin-right:17px;">
	  	<table width="1020" border="0" cellspacing="0" cellpadding="0" class="list_table2">
		  	<tr>
			    <th width="46"><div align="left">
			      <input name="checkbox" type="checkbox"  onclick="checkAll(this, 'requestId');" />
			     </div>
			    </th>
			    <th width="70">Catalog No</th>
			    <th width="70">Type</th>
			    <th width="150">Name</th>
			    <th width="245">Description</th>
			    <th width="70">Modify Info</th>
				<th width="60">Status</th>
			    <th width="60">Size</th>
			   
			    <th width="100">Modify Date </th>
			    <th>Creation Date</th>
		    </tr>
		</table>
	</div>
	</td>
  </tr>
<tr>
  <td><div class="list_box" >
    <table width="1020" border="0" cellspacing="0" cellpadding="0" class="list_table2">
              <s:iterator value="serviceDTOList">
                <tr>
                  <td width="46">
                  	<input type="checkbox" value="${requestId}" name="requestId"/>
                  </td>
                  <td width="70" style="word-break:break-all;word-wrap:break-word;width:70">
                  	<a href="${ctx}/serv/serv!input.action?id=${serviceId}&operation_method=edit&requestId=${requestId }&approvedMethod=approvedEdit&approvedName=${nameNewVal}&approvedStatus=${statusNewVal}">
                  	 ${catalogNo}&nbsp;
                  	</a>
                  </td>
                  <td width="70">${type}</td>
                  <td width="150" style="word-break:break-all;word-wrap:break-word;width:150">${name}&nbsp;</td>
                  <td width="245" style="word-break:break-all;word-wrap:break-word;width:245">${description}</td>
                  <td width="70">
	                  	<div align="center">
	                  			<s:if test="nameNewVal!=null&&statusNewVal!=null">
								<span
							onmouseover="tooltip.show('Name: ${nameOldVal}->${nameNewVal}<br>Reason：${nameReason}<br>Status：${statusOldVal}->${statusNewVal}<br>Reason：${statusReason}');" 
							onmouseout="tooltip.hide();">	
						
								</s:if>
								<s:elseif test="nameNewVal!=null">
								<span 
							onmouseover="tooltip.show('Name: ${nameOldVal}->${nameNewVal}<br>Reason：${nameReason}');" 
							onmouseout="tooltip.hide();">
							
								</s:elseif>
								<s:else>
								<span 
							onmouseover="tooltip.show('Status：${statusOldVal}->${statusNewVal}<br>Reason：${statusReason}');" 
							onmouseout="tooltip.hide();">
							
								</s:else>
							<img src="images/qa.jpg" width="12" height="12"/>
							</span>
						</div>
				</td>
				  <td width="60">${status}</td>
                  <td width="60">${size}${uom}</td>
                  
                  <td width="100">
                  	<div align="center"><s:date name="modifyDate" format="yyyy-MM-dd" /></div>
                  </td>
                  <td><div align="center"><s:date name="creationDate" format="yyyy-MM-dd" /></div></td>
                </tr>
                </s:iterator>
              </table>
          </div></td>
        </tr>
      </table>
	</div>
</s:elseif>
<s:elseif test="approveType==\"ProductPrice\"">
<div id="ProductPriceDiv">
      <div align="center"><span class="blue_price">Product Price Change Requests
        </span>
      </div><br />
      <table width="1037" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>
	          <div style="margin-right:17px;">
	            <table width="1020" border="0" cellspacing="0" cellpadding="0" class="list_table2">
	                <tr>
	                  <th width="46"><div align="left">
	                      <input name="checkbox3" type="checkbox"  onclick="checkAll(this, 'requestId');" />
	                   </div></th>
	                  <th width="70">Catalog No </th>
	                  <th width="70">Catalog ID</th>
	                  <th width="150">Product Name</th>
	                  <th width="245">Category Name</th>
					  <th width="70">Modify Info</th>
	                  <th width="60">Currency</th>
	                  <th width="40">Limit Price</th>
	                  <th width="65">Request Date </th>
	                  <th width="70">Request By </th>
	                  <th>Creation Date </th>
	                </tr>
	              </table>
	          </div>
          </td>
        </tr>
        <tr>
          <td><div class="list_box" style="height:160px" >
              <table width="1020" border="0" cellspacing="0" cellpadding="0" class="list_table2">
              <s:iterator value="productPriceDTOList">
                <tr>
                  <td width="46">
                  	<input type="checkbox" value="${requestId}" name="requestId"/>
                  </td>
                  <td width="70" style="word-break:break-all;word-wrap:break-word;width:70">
                  	<a href="${ctx}/product/product!input.action?id=${productId}&type=${type }&operation_method=edit">
                  	${catalogNo}&nbsp;
                  	</a>
                  </td>
                  <td width="70" style="word-break:break-all;word-wrap:break-word;width:70">${catalogId}</td>
                  <td width="150" style="word-break:break-all;word-wrap:break-word;width:150">${name}</td>
                  <td width="245" style="word-break:break-all;word-wrap:break-word;width:245">${categoryName}</td>
                  <td width="70">
	                  	<div align="center">
							<span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:10px; font-size: 24px;" 
							onmouseover="tooltip.show('Price: ${priceOldVal}->${priceNewVal}<br>Reason：${priceReason}<br>');" 
							onmouseout="tooltip.hide();">
							<img src="images/qa.jpg" width="12" height="12"/>
							</span>
						</div>
				</td>
				  <td width="60">${currencyCode}</td>
                  <td width="40">${symbol}${limitPrice}</td>
                  <td width="65">
                  	<div align="center"><s:date name="modifyDate" format="yyyy-MM-dd" /></div>
                  </td>
                  <td width="70">
                  	<div align="right">${modifiedBy}</div>
                  </td>
                  <td><div align="center"><s:date name="creationDate" format="yyyy-MM-dd" /></div></td>
                </tr>
                </s:iterator>
              </table>
          </div></td>
        </tr>
      </table>
  </div>
</s:elseif>
<s:elseif test="approveType==\"ServicePrice\"">
<div id="ServicePriceDiv">
      <div align="center"><span class="blue_price">Service Price Change Requests
        </span>
      </div><br />
      <table width="1037" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>
	          <div style="margin-right:17px;">
	            <table width="1020" border="0" cellspacing="0" cellpadding="0" class="list_table2">
	                <tr>
	                  <th width="46"><div align="left">
	                      <input name="checkbox3" type="checkbox"  onclick="checkAll(this, 'requestId');" />
	                   </div></th>
	                  <th width="70">Catalog No </th>
	                  <th width="70">Catalog ID</th>
	                  <th width="150">Service Name</th>
	                  <th width="245">Category Name</th>
					  <th width="70">Modify Info</th>
	                 
	                  <th width="40">Limit Price</th>
	                  <th width="65">Request Date </th>
	                  <th width="70">Request By </th>
	                  <th>Creation Date </th>
	                </tr>
	              </table>
	          </div>
          </td>
        </tr>
        <tr>
          <td><div class="list_box" style="height:160px" >
              <table width="1020" border="0" cellspacing="0" cellpadding="0" class="list_table2">
              <s:iterator value="servicePriceDTOList">
                <tr>
                  <td width="46">
                  	<input type="checkbox" value="${requestId}" name="requestId"/>
                  </td>
                  <td width="70" style="word-break:break-all;word-wrap:break-word;width:70">
                  	
                  	${catalogNo}&nbsp;
                  
                  </td>
                  <td width="70" style="word-break:break-all;word-wrap:break-word;width:70">${catalogId}</td>
                  <td width="150" style="word-break:break-all;word-wrap:break-word;width:150">${serviceName}</td>
                  <td width="245" style="word-break:break-all;word-wrap:break-word;width:245">${categoryName}</td>
                  <td width="70">
	                  	<div align="center">
							<span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:10px; font-size: 24px;" 
							onmouseover="tooltip.show('Price: ${priceOldVal}->${priceNewVal}<br>Reason：${priceReason}<br>');" 
							onmouseout="tooltip.hide();">
							<img src="images/qa.jpg" width="12" height="12"/>
							</span>
						</div>
				</td>
				 
                  <td width="40">${symbol}${limitPrice}</td>
                  <td width="65">
                  	<div align="center"><s:date name="modifyDate" format="yyyy-MM-dd" /></div>
                  </td>
                  <td width="70">
                  	<div align="right">${requestedBy}</div>
                  </td>
                  <td><div align="center"><s:date name="creationDate" format="yyyy-MM-dd" /></div></td>
                </tr>
                </s:iterator>
              </table>
          </div></td>
        </tr>
      </table>
  </div>
</s:elseif>
</div></div>
<div align="center">
	<input id="approveBtn" type="button" class="search_input" value="Approve" />
	<input id="rejectBtn" type="button" class="search_input" value="Reject" />
</div>
<div style="display:none;" id="rejectDialog" >
	<table id="whole_table" width="408" border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin-left:50px;">
		  <tr>		   
			   <td width="364" height="18" style="font-size:12px">		
			   	Enter comments to reject the change request:
				</td>   	
			</tr>
			<tr>
			   <td height="63">
		      	<textarea id="rejectReason" name="rejectReason" class="content_textarea2"></textarea>
		      </td>
			</tr>
		  <tr>
			 <td>
			  <div align="center">
				<input id="rejectDialogConfirm" type="button" class="style_botton" value="Confirm" />     
				<input id="rejectDialogCancel" type="button" class="style_botton" value="Cancel" />
				</div>
			</td>
		</tr>
</table>
</div>
</body>
</html>
