	  <div style="height:200px;width:955px;">
	  
    <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table" style="TABLE-LAYOUT:fixed;word-break:break-all">
      <tr>
        <th width="40"><div align="left">
            <input type="checkbox" name="checkbox112"  onclick="cc(this, 'prm')"/>
        <a href="javascript:void(0)" onclick="del_promotion()"><img src="${global_image_url}file_delete.gif" width="16" height="16" /></a></div></th>
        <th width="80">Code</th>
        <th width="296">Description</th>
        <th width="90">Type </th>
        
        <th width="60">RFM</th>
        <th width="80">Source Key </th>
        
        <th width="90">Effective Date </th>
        <th width="90">Expiration Date </th>
        </tr>
    </table>
    <div class="frame_box">
      <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table" style="TABLE-LAYOUT:fixed;word-break:break-all">
		<tbody id="promotionSearchResult">
      </table>
		<div style="margin-left:350px;" id="prmo_indicator"></div>
    </div>
    <div id="giftCatalogDialog" title="Please Choose Catalog"></div>
	<div id="categoryCatalogDialog" title="Please Choose Catalog"></div>
	<div id="customer_search_dlg" title="Search Customer"></div>
    <div id="selectCategoryDialog" title="Please Choose Category"></div>
	<div class="grayr" id="promoSearchPager">&nbsp;<jsp:include page="/common/db_pager.jsp">
					<jsp:param value="${ctx}/quote_order!listPromotion.action"
						name="moduleURL" />
				</jsp:include></div>
  </div>
