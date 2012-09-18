<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglib.jsp"%>
<html>

<head>
<title>menu</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css">
<link href="${global_css_url}menu.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
</head>

<script language="JavaScript"><!--
	var global_url = "${global_url}" ;
	var global_image_url = "${global_image_url}";
	
	function c(srcelement) {
		targetelement=$('#'+srcelement);

		if ( targetelement.css( "display" ) == "none" ) {
			targetelement.css( "display" , '' ) ;
			$("#pic_"+srcelement).attr( "src" , global_image_url+"ad.gif" ) ;
		} else {
			targetelement.css( "display" , "none" ) ;
			$("#pic_"+srcelement).attr( "src" , global_image_url+"ar.gif" ) ;
		}
	}
	
	function setTopLink( privilegeCode , privilegeName , privilegeAttr)
	{
		if (privilegeAttr != '') {
			var param = "" ;
			param = "privilegeCode="+privilegeCode ;
			var topLink = "&gt;&gt;" ; 
			var sendJson = "{privilegeCode:"+privilegeCode+"}";
			$.getJSON("privilege!parentPath.action?callback=?",{privilegeCode:privilegeCode}, function(data) {
				for(var i = 0;i<data.length;i++){
					topLink = "&gt;&gt;" + data[i].privilegeName + topLink;
				}
				topLink = topLink + "<a href='"+privilegeAttr+"' target='mainFrame' >"+privilegeName+"</a>" ;
				parent.topFrame.setTopLink(topLink);
			});
		}
	}

	function setMyLink( myPrvCode , privilegeName , privilegeAttr)
	{
		if (privilegeAttr != '') {
			var topLink = "&gt;&gt;" ; 
			var sendJson = "{myPrvCode:"+myPrvCode+"}";
			$.getJSON("privilege!parentPath.action?callback=?",{myPrvCode:myPrvCode}, function(data) {
				for(var i = 0;i<data.length;i++){
					topLink = "&gt;&gt;" + data[i].privilegeName + topLink;
				}
				topLink = topLink + "<a href='"+privilegeAttr+"' target='mainFrame' >"+privilegeName+"</a>" ;
				parent.topFrame.setTopLink(topLink);
			});
		}
	}

	function changeTopLink (privilegeName) {
		$.ajax({
			type: "POST",
			url: "privilege!findPrivilege.action",
			dataType: "json",
			data:"privName="+privilegeName,
			success: function(data){
				if (data.privilege != undefined && data.privilege != null) {
					setMyLink(data.privilege.privilegeCode , data.privilege.privilegeName , data.privilege.privilegeAttr);
					if (data.prvdtolist != undefined && data.prvdtolist != null && data.prvdtolist.length > 0) {
						for (var i=data.prvdtolist.length;i>0;i--) {
							var srcelement = "subTree_" + data.prvdtolist[i-1].privilegeId;
							targetelement=$('#'+srcelement);
							if ( targetelement.css( "display" ) == "none" ) {
								targetelement.css( "display" , '' ) ;
								$("#pic_"+srcelement).attr( "src" , global_image_url+"ad.gif" ) ;
							}
						}
					}
					cur("tree_"+data.privilege.privilegeId);
				}
			}
		});
	}

	function setSubTopLink( privilegeCode , privilegeName , privilegeAttr)
	{
		parent.topFrame.setTopLink("&gt;&gt;" + privilegeName);
	}

	function cur(itemname) {
		var lastitem = readCookie("lastmenu");
		if (lastitem){
			if (document.getElementById(lastitem) != null) {
				//reset the color
				document.getElementById(lastitem).style.color="#383838";
				document.getElementById(lastitem).style.fontWeight="normal";
				/*document.getElementById(lastitem).style.backgroundImage="url(images/text.gif)";*/
				document.getElementById(lastitem).style.backgroundColor="";
			}
		}
	
		document.getElementById(itemname).style.color="#013F96";
		document.getElementById(itemname).style.fontWeight="bold";
		document.getElementById(itemname).style.backgroundColor="#E6EFF8";
		/*document.getElementById(itemname).style.backgroundImage="url(images/ar.gif)";*/
		setCookie("lastmenu", itemname);
	
	}
	
	function readCookie(name) {
		var cookieValue = "";
		var search = name + "=";
		if(document.cookie.length > 0)	{
			offset = document.cookie.indexOf(search);
			if (offset != -1)	{
				offset += search.length;
				end = document.cookie.indexOf(";", offset);
				if (end == -1) end = document.cookie.length;
				cookieValue = unescape(document.cookie.substring(offset, end))
			}
		}
		return cookieValue;
	}
	
	function setCookie(sName, sValue, oExpires, sPath, sDomain, bSecure) {
		var sCookie = sName + "=" + encodeURIComponent(sValue);
		if (oExpires) {
			sCookie += "; expires=" + oExpires.toGMTString();
		}
		if (sPath) {
			sCookie += "; path=" + sPath;
		}
		if (sDomain) {
			sCookie += "; domain=" + sDomain;
		}
		if (bSecure) {
			sCookie += "; secure";
		}
		document.cookie = sCookie;
	}

</script>
<body ><table width="213" border="0" cellspacing="0" cellpadding="0" height="100%"  style=" background:url(${global_image_url}table_left_bg.jpg) repeat-x 0px 84px; " >
<tr><td  class="left_td"><img src="${global_image_url}1111.gif" width="213" height="84">
<div id="body">
<ul style="word-wrap: break-word;">
${treeBody}
</ul>
</div>
</td>
  </tr>
</table>
</body>
</html>