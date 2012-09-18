<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css"> 
body {
	background-image: url(${global_image_url}left_menu_bg.jpg);
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
} 
</style>  
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
</head>

<body oncontextmenu="return false" onselectstart="return false"
	oncopy="return false"> 
	<table width="6" height=100% border="0" align="center" cellpadding="0"
		cellspacing="0"> 
		<tr>
			<td width="7"><a href="#" onclick="show_hide_left();"> <img
					src="${global_image_url}left.jpg" alt="隐藏左框架" border="0" id="onmid">
			</a></td>
		</tr>
	</table>
</body>
<script type="text/javascript" language="javascript">  
	function show_hide_left() { 
		if (window.parent.document.getElementById("midFrame")) {
			var temp = window.parent.document.getElementById("midFrame").cols;
			if (temp == "0,11,*") {
				window.parent.document.getElementById("midFrame").cols = "220,11,*";
				document.all.onmid.src = "${global_image_url}left.jpg";
				document.all.onmid.alt = "隐藏左框架";
			} else {
				window.parent.document.getElementById("midFrame").cols = "0,11,*";
				document.all.onmid.src = "${global_image_url}right.jpg";
				document.all.onmid.alt = "显示左框架"
			}
		}
	}
</script>
</html>