	  <div style="height:200px;width:955px;">
	  
    <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table" style="TABLE-LAYOUT:fixed;word-break:break-all">
      <tr>
        <th width="40"><div align="left">
            <input type="checkbox" name="checkbox112"  onclick="cc(this, 'prm')"/>
        <a href="javascript:void(0)" onclick="del_coupon()"><img src="${global_image_url}file_delete.gif" width="16" height="16" /></a></div></th>
        <th width="100">Gift Card Code</th>
        <th width="200">Gift Card Name</th>
        <th width="150">Price </th>
        <th>Description</th>
        </tr>
    </table>
    <div class="frame_box">
      <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table" style="TABLE-LAYOUT:fixed;word-break:break-all">
		<tbody id="couponSearchResult">
      </table>
		<div style="margin-left:350px;" id="coupon_indicator"></div>
    </div>
	<div class="grayr" id="couponSearchPager">&nbsp;<jsp:include page="/common/db_pager.jsp">
					<jsp:param value="${ctx}/quote_order!listCoupon.action"
						name="moduleURL" />
				</jsp:include></div>
  </div>
