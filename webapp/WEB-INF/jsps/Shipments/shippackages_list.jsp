<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User list</title>
		<base href="${global_url}" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet"
			type="text/css" media="all" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet"
			type="text/css" />
		<script language="javascript" type="text/javascript"
			src="${global_js_url}ajax.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}tab-view.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}TabbedPanels.js"></script>
		<script type="text/javascript">var GB_ROOT_DIR = "./greybox/";</script>
		<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
		<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
		<script type="text/javascript"
			src="${global_js_url}greybox/gb_scripts.js"></script>
	</head>

	<body class="content">
		<script type="text/javascript">
		<c:if test="${request.sign == 1}">
			alert(" You search's user not exist !");
		</c:if>
</script>
		<table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
    <tr>

      <th width="62">Pkg No </th>
      <th width="114">Tracking No</th>
      <th width="201">Description</th>
      <th width="75">Status</th>
      <th width="68">Ship Via</th>
      <th width="68">Ship To</th>

      <th width="68">Ship Date</th>
      <th width="92">Shipping Clerk</th>
      <th>Warehouse </th>
      <th width="92">Packaging Error</th>
      </tr>
  </table>
	  <div class="frame_box" style="height:200px; ">

	    <table width="955" border="0" cellspacing="0" cellpadding="0" class="list_table2">
	      <tr>
	        <td width="62" align="center"><a href="ship_conf_detail.html">20</a></td>
	        <td width="114">1Z8V41V86696064186</td>
	        <td width="201">Order #926188</td>
	        <td width="75">Ready to Ship</td>
	        <td width="68">&nbsp;</td>
	        <td width="68">&nbsp;</td>
	        <td width="68">&nbsp;</td>
	        <td width="92">&nbsp;</td>
	        <td>&nbsp;</td>
	        <td width="92">NO</td>
          </tr>
 	 </table>
	  </div>
    <div class="grayr">
				<jsp:include page="/common/db_pager.jsp">
					<jsp:param value="${ctx}/shipments!getpackagesInfo.action" name="moduleURL" />
				</jsp:include>
			</div>
	</body>
</html>