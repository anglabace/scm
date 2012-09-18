<%@ include file="/common/taglib.jsp" %>
<div style="height:200px;width:955px;">
    <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
        <tr>
            <th width="46">
                <div align="left">
                    <input type="checkbox" name="checkbox11" onclick="cc(this, 'sou1')"/>
                    <a href="javascript:void(0)" onclick="del_source()"><img src="${global_image_url}file_delete.gif"
                                                                             width="16" height="16"/></a></div>
            </th>
            <th width="150">Source Code</th>
            <th width="150">Name</th>
            <th>Description</th>
        </tr>
    </table>
    <div class="frame_box">
        <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
            <tbody id="sourceSearchResult">
            <s:iterator value="resultMap">
                <tr id="source_row_${key}">
                    <td width="46"><input type="checkbox" name="sou1" value="${key}"/></td>
                    <td width="150"><span id='code_${key}'>
	             		<a href="javascript:void(0)" onclick="javascript:show_edit_source('${key}')">${value.code}</a></span></td>
                    <td width="150">&nbsp;<span id='name_${key}'>${value.name}</span></td>
                    <td>&nbsp;<span id='desc_${key}'>${value.description}</span></td>
                </tr>
            </s:iterator>

        </table>
    </div>

    <div class="grayr" id="sourceSearchPager">&nbsp;
        <jsp:include page="/common/db_pager.jsp">
            <jsp:param value="${ctx}/quote_order.action"
                       name="moduleURL"/>
        </jsp:include>
    </div>
</div>
