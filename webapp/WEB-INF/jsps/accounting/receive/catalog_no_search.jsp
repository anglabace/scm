<%@ include file="/common/taglib.jsp"%>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript" src="${global_js_url}jquery/jquery.js"></script>
<style type="text/css" media="all">
@import "${global_url}Upimg/thickbox.css";
</style>
 <script   language="JavaScript" type="text/javascript">  
  function   gra(e)  
  {  
      var   a   =   document.getElementsByName("gran");  
      for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
  } 
   function   Pub(e)  
  {  
      var   a   =   document.getElementsByName("Publ");  
      for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
  }   
 </script>
 <script type="text/javascript">
 
$(function(){
		$("#Product").click(function(){
			$("#Product").removeClass();
			$("#Service").removeClass();
			$("#Product").addClass('TabbedPanelsTab TabbedPanelsTabSelected')
			$("#Service").addClass('TabbedPanelsTab')
		    $("#panel0").show();
		    $("#panel1").hide();
		    $("#pro").show();
		    $("#ser").hide();
		    //$("#panel1").location = "genscript!catalogNoSearch.action";
		    window.location = "genscript!catalogNoSearch.action";
		});
		$("#Service").click(function(){
	   		$("#Product").removeClass();
	    	$("#Service").removeClass();
	  		$("#Product").addClass('TabbedPanelsTab')
	  		$("#Service").addClass('TabbedPanelsTab  TabbedPanelsTabSelected')
            $("#panel0").hide();
	   		$("#panel1").show();
	   		$("#pro").hide();
	   		$("#ser").show();
	   		//$("#panel1").location = "ar_invoice/ar_invoice!servCatalogNoSearch.action";
	   	 window.location = "genscript!servCatalogNoSearch.action";
	   });
	   $("#selectCatalogNoBtn").click(function(){
		   var servCatalogNo = $(":radio:checked").val(); 
		   if (servCatalogNo != null) {
		    var name = $(":radio:checked").attr("name");
		    var uom = $(":radio:checked").attr("uom");
		       parent.setCatalogInfo(servCatalogNo,name,uom);
			   parent.$('#catalog_list_dlg').dialog("close");
		   }
	   });
})

$(function(){
 $("#list_table tr:nth-child(even) td").each(function(){
    $(this).addClass("list_td2");
 })
});

</script>
<body class="content" style="background-image:none;">
<div class="scm">
<div class="search_box">
 <div class="search_box_three">
 		<form action="ar_invoice/ar_invoice!catalogNoSearch.action" method="get" id="ProductSearchForm">
        <table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="pro">
            <tr>
            <th width="80">Product Catalog No</th>
            <td><input name="filter_EQS_catalogNo" value="${param.filter_EQS_catalogNo }" type="text"  class="NFText" size="20"/></td>
            <th width="100">Product Name </th>
            <td><input name="filter_LIKES_name" value="${param.filter_LIKES_name }" type="text"  class="NFText" size="20"/></td>
            <td><input name="Submit3" type="submit" class="style_botton" value="Search" /></td>
            </tr>
        </table>
        </form>   
        <form action="ar_invoice/ar_invoice!servCatalogNoSearch.action" method="post" id="ServiceSearchForm">
        <table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="ser" style="display:none;">
          <tr>
            <th width="80">Service Catalog No</th>
            <td><input name="filter_EQS_catalogNo" type="text"  class="NFText" size="20"/></td>
            <th width="100">Service Name </th>
            <td><input name="filter_LIKES_name" type="text"  class="NFText" size="20"/></td>
            <td><input name="Submit" type="submit" class="style_botton" value="Search" /></td>
          </tr>
        </table>
        </form>
</div></div>
     
<div id="TabbedPanels1" class="TabbedPanels">
<ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab TabbedPanelsTabSelected" tabindex="0" id="Product">Product</li>
    <li class="TabbedPanelsTab" tabindex="1" id="Service">Service</li>
</ul>
   <div class="TabbedPanelsContentGroup">
     <div style="display: block;" class="TabbedPanelsContent" id="panel0">
       <table width="670"  border="0" cellpadding="0" cellspacing="0" class="list_table" style=" margin-top:10px;">
                  <tr>
                    <th width="46">&nbsp;</th>
                    <th width="300">Product Catalog No</th>
                    <th>Product Name </th>
                    </tr>
       </table>
  <div style="width:687px; height:230px; overflow:scroll;">
    <table width="650"  border="0" cellpadding="0" cellspacing="0" id="list_table" class="list_table">
    <s:iterator value="productPageBean.result">
      <tr>
        <td width="46"><input type="radio" id="productCatalogNo"  value="${catalogNo}" name="${name }" uom="${uom }" /></td>
        <td width="300">${catalogNo}</td>
        <td>${name}</td>
        </tr>
      <tr>
    </s:iterator>
    </table>
  </div>
  <br/>
      <div class="grayr">
		<jsp:include page="/common/db_pager.jsp">
		<jsp:param value="${ctx}/ar_invoice!catalogNoSearch.action" name="moduleURL" />
		</jsp:include>
	  </div>
</div>

<div style="display:none;" class="TabbedPanelsContent" id="panel1">
 	<table width="670" border="0"  cellpadding="0" cellspacing="0" class="list_table"  style=" margin-top:10px;">
       <tr>
         <th width="46">&nbsp;</th>
       <th width="300">Service Catalog No</th>
       <th>Service  Name </th>
       </tr>
    </table>
     <div style="width:687px; height:230px; overflow:scroll;">
		<table width="670" border="0"  cellpadding="0" cellspacing="0" class="list_table">
        </table>
</div>
<br/>
	<div class="grayr">
		<jsp:include page="/common/db_pager.jsp">
		<jsp:param value="${ctx}/ar_invoice!servCatalogNoSearch.action" name="moduleURL" />
		</jsp:include>
	</div>
</div>
</div></div>
<div align="center"><br />
<input  type="button" value="Select" class="style_botton" id="selectCatalogNoBtn"/>
<input type="submit" value="Cancel"  class="style_botton" onclick="javascript:parent.$('#catalogNoSearch').dialog('close');"/>
</div>
</div>
</body>
