<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/gs.util.js"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>
<script src="${global_js_url}scm/Date.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/setting_quoteorder.js"></script>
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />

<script language="javaScript" type="text/javascript">
	var GB_ROOT_DIR = "./greybox/";
	var baseUrl = "${ctx}";
</script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}newwindow.js"></script>
</head>

<body class="content">
	<div class="scm">
		<div class="title_content">
			<div class="title_new">
				<a href="javascript:void(0);"
					onclick="toggleShowMorea('total_title');" id="total_titleItem"><img
					src="${global_url}images/arrow1.jpg" /> </a>Quotation/Order Settings
			</div>
		</div>
		<div class="search_box" id="total_title">
			<div class="single_search">
				<div id="paramHiddenSources" style="display: block">
					<table border="0" cellpadding="0" cellspacing="0"
						class="General_table">
						<tr>
							<td>Source Code</td>
							<td><input id="filter_LIKES_code" type="text" class="NFText"
								size="15" />
							</td>
							<td>Source Name</td>
							<td width="100"><input id="filter_LIKES_name" type="text"
								class="NFText" size="15" /></td>
							<td>Description</td>
							<td width="100"><input id="filter_LIKES_description"
								type="text" class="NFText" size="15" /></td>
							<td><input id="btnSourceSrch" type="button"
								onclick="searchSource(1)" value="Search" class="search_input" />
							</td>
						</tr>
					</table>

				</div>
				<div id="paramHiddenCatalog" style="display: none">
					<table border="0" cellpadding="0" cellspacing="0"
						class="General_table">
						<tr>
							<th>Catalog ID</th>
							<td><input name="textfield2" type="text" class="NFText"
								size="15" />
							</td>
							<td>Catalog Name</td>
							<td width="100"><input name="textfield22" type="text"
								class="NFText" size="15" /></td>
							<td>Description</td>
							<td width="100"><input name="textfield2222" type="text"
								class="NFText" size="15" /></td>
							<td><input id="paramSearch3" type="submit"
								name="paramSearch3" value="Search" class="search_input" />
							</td>
						</tr>
					</table>
				</div>
				<div id="paramHiddenCustomer" style="display: none">
					<table border="0" cellpadding="0" cellspacing="0"
						class="General_table">
						<tr>
							<td>Notice Name</td>
							<td><input name="textfield2" type="text" class="NFText"
								size="15" />
							</td>
							<td>Description</td>
							<td><input name="textfield22" type="text" class="NFText"
								size="30" /></td>
							<td><input id="paramSearch" type="submit" name="paramSearch"
								value="Search" class="search_input" />
							</td>
						</tr>
					</table>
				</div>
				<div id="paramHiddenOrder" style="display: none">
					<table border="0" cellpadding="0" cellspacing="0"
						class="General_table">
						<tr>
							<td>Type Name</td>
							<td><input name="textfield2" type="text" class="NFText"
								size="15" /></td>
							<td>Description</td>
							<td><input name="textfield223" type="text" class="NFText"
								size="30" /></td>
							<td><input id="paramSearch" type="submit" name="paramSearch"
								value="Search" class="search_input" />
							</td>
						</tr>
					</table>
				</div>
				<div id="paramHiddenGift" style="display: none">
					<table border="0" cellpadding="0" cellspacing="0"
						class="General_table">
						<tr>
							<td>Message Type</td>
							<td><select name="select3" style="width: 150px;">
							</select>
							</td>
							<td>Description</td>
							<td><input name="textfield2232" type="text" class="NFText"
								size="30" /></td>
							<td><input id="paramSearch" type="submit" name="paramSearch"
								value="Search" class="search_input" /> <input type="button"
								id="paramNew" name="paramNew" value="  New  "
								class="search_input" /></td>
						</tr>
					</table>
				</div>
				<div id="paramHiddenPromotions" style="display: none">
					<table border="0" cellpadding="0" cellspacing="0"
						class="General_table">
						<tr>
							<th>Promotion Code</th>
							<td><input id="filter_LIKES_prmtCode" type="text"
								class="NFText" size="15" />
							</td>
							<th>Description</th>
							<td><input id="filter_LIKES_description1" type="text"
								class="NFText" size="30" /></td>
							<td><input type="button" onclick="searchPromotion(1)"
								value="Search" class="search_input" /></td>
						</tr>
					</table>
				</div>
				<div id="paramHiddenCoupon" style="display: none;">
					<table border="0" cellpadding="0" cellspacing="0"
						class="General_table">
						<tr>
							<th>Gift Card Code</th>
							<td><input id="filterLIKEScode" type="text" class="NFText"
								size="15" />
							</td>

							<td><input id="paramSearch2" type="button"
								onclick="searchCoupon(1)" name="paramSearch2" value="Search"
								class="search_input" />
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div id="dhtmlgoodies_tabView1">

			<!--- sourcie list  -->
			<div class="dhtmlgoodies_aTab">
				<div id="source_list">
					<!-- source lise search result Info Here -->
					<%@ include file="quoteorder_source_list.jsp"%>
				</div>
				<p align="center">
					<input type="hidden" id="editSourceDialogTrigger" />
				</p>
				<div id="source_add_dialog" title="Create new source"></div>
				<div id="source_edit_dialog" title="Edit source"></div>

			</div>


			<!--- Customer Notice list  -->
			<div class="dhtmlgoodies_aTab">
				<table width="955" border="0" cellpadding="0" cellspacing="0"
					class="list_table">
					<tr>
						<th width="46"><div align="left">
								<input type="checkbox" name="checkbox113" onclick="ntc(this)" />
								<a href="#"><img src="${global_image_url}file_delete.gif"
									width="16" height="16" /> </a>
							</div></th>
						<th width="150">Notice Type</th>
						<th width="150">Name</th>
						<th>Description</th>
					</tr>
				</table>
				<div class="frame_box">
					<table width="955" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<tr>
							<td width="46"><input name="ntc" type="checkbox"
								value="checkbox" /></td>
							<td width="150">&nbsp;</td>
							<td width="150">&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="list_td2"><input type="checkbox" name="ntc"
								value="checkbox" /></td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="ntc" value="checkbox" /></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="list_td2"><input type="checkbox" name="ntc"
								value="checkbox" /></td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="ntc" value="checkbox" /></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="list_td2"><input type="checkbox" name="ntc"
								value="checkbox" /></td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="ntc" value="checkbox" /></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="list_td2"><input type="checkbox" name="ntc"
								value="checkbox" /></td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="ntc" value="checkbox" /></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="list_td2"><input type="checkbox" name="ntc"
								value="checkbox" /></td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="ntc" value="checkbox" /></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="list_td2"><input type="checkbox" name="ntc"
								value="checkbox" /></td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="ntc" value="checkbox" /></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="list_td2"><input type="checkbox" name="ntc"
								value="checkbox" /></td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
						</tr>
					</table>
				</div>
				<div class="grayr">
					<span class="disabled"> &lt; </span><span class="current">1</span><a
						href="#">2</a><a href="#?page=3">3</a><a href="#?page=4">4</a><a
						href="#?page=5">5</a><a href="#?page=6">6</a><a href="#?page=7">7</a>...<a
						href="#?page=199">199</a><a href="#?page=200">200</a><a
						href="#?page=2"> &gt; </a>
				</div>
			</div>

			<!--- Order Types list  -->
			<div class="dhtmlgoodies_aTab">
				<table width="955" border="0" cellpadding="0" cellspacing="0"
					class="list_table">
					<tr>
						<th width="46"><div align="left">
								<input type="checkbox" name="checkbox115" onclick="typ(this)" />
								<a href="#"><img src="${global_image_url}file_delete.gif"
									width="16" height="16" /> </a>
							</div></th>
						<th width="150">Order Type Code</th>
						<th>name</th>
					</tr>
				</table>
				<div class="frame_box">
					<table width="955" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<tr>
							<td width="46"><input name="typ" type="checkbox"
								value="checkbox" /></td>
							<td width="150">&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="list_td2"><input type="checkbox" name="typ"
								value="checkbox" /></td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="typ" value="checkbox" /></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="list_td2"><input type="checkbox" name="typ"
								value="checkbox" /></td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="typ" value="checkbox" /></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="list_td2"><input type="checkbox" name="typ"
								value="checkbox" /></td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="typ" value="checkbox" /></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="list_td2"><input type="checkbox" name="typ"
								value="checkbox" /></td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="typ" value="checkbox" /></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="list_td2"><input type="checkbox" name="typ"
								value="checkbox" /></td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="typ" value="checkbox" /></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="list_td2"><input type="checkbox" name="typ"
								value="checkbox" /></td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="typ" value="checkbox" /></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="list_td2"><input type="checkbox" name="typ"
								value="checkbox" /></td>
							<td class="list_td2">&nbsp;</td>
							<td class="list_td2">&nbsp;</td>
						</tr>
					</table>
				</div>
				<div class="grayr">
					<span class="disabled"> &lt; </span><span class="current">1</span><a
						href="#">2</a><a href="#?page=3">3</a><a href="#?page=4">4</a><a
						href="#?page=5">5</a><a href="#?page=6">6</a><a href="#?page=7">7</a>...<a
						href="#?page=199">199</a><a href="#?page=200">200</a><a
						href="#?page=2"> &gt; </a>
				</div>
			</div>


			<!--- promtion list  -->
			<div class="dhtmlgoodies_aTab">
				<!-- prmotion search result here -->
				<%@ include file="quoteorder_promotion_list.jsp"%>
				<p align="center">
					<input type="hidden" id="editPromotionDialogTrigger" />
				</p>
				<div id="promotion_add_dialog" title="Create new promotion"></div>
				<div id="promotion_edit_dialog" title="Edit promotion"></div>

			</div>

			<!--- Coupon list  -->
			<div class="dhtmlgoodies_aTab">
				<!-- coupon search result here -->
				<%@ include file="quoteorder_coupon_list.jsp"%>
				<p align="center">
					<input type="hidden" id="editCouponDialogTrigger" />
				</p>
				<div id="coupon_add_dialog" title="Create new gift card"></div>
				<div id="coupon_edit_dialog" title="Edit gift card"></div>

			</div>
			<div class="button_box">
				<div id="sourceNew" style="display: ">
					<a href="javascript:void(0)" title="Create New Source"
						rel="gb_page_center[730,  260]"><input type="button"
						id="source_add_btn" value="New" class="search_input" /> </a> <input
						type="button" value="Save" class="search_input" onclick="save(1)" />
					<input type="hidden" id="current_tab_name" value="Source" /> <input
						type="button" value="Cancel" class="search_input"
						onclick="clear_session(1)" />
				</div>
				<div id="NoticeNew" style="display: none">
					<input type="button" id="paramNew" name="paramNew" value="New"
						class="search_input" /> <input type="button" value="Save"
						class="search_input" onclick="save(1)" /> <input type="hidden"
						id="current_tab_name" value="Source" /> <input type="button"
						value="Cancel" class="search_input" onclick="clear_session(1)" />
				</div>
				<div id="TypeNew" style="display: none">
					<input type="button" id="paramNew" name="paramNew" value="New"
						class="search_input" /> <input type="button" value="Save"
						class="search_input" onclick="save(1)" /> <input type="hidden"
						id="current_tab_name" value="Source" /> <input type="button"
						value="Cancel" class="search_input" onclick="clear_session(1)" />
				</div>
				<div id="promotionNew" style="display: none">
					<a href="javascript:void(0)" title="New Promotion"
						rel="gb_page_center[680,  400]"> <input type="button"
						id="promotion_add_btn" value="New" class="search_input" /> </a> <input
						type="button" value="Save" class="search_input" onclick="save(1)" />
					<input type="hidden" id="current_tab_name" value="Source" /> <input
						type="button" value="Cancel" class="search_input"
						onclick="clear_session(1)" />
				</div>

				<div id="giftNew" style="display: none">
					<a href="javascript:void(0)" title="New Coupon"
						rel="gb_page_center[500,  300]"> <input type="button"
						id="coupon_add_btn" value="New" class="search_input" /> </a> <input
						type="button" value="Save" class="search_input" onclick="save(1)" />
					<input type="hidden" id="current_tab_name" value="Source" /> <input
						type="button" value="Cancel" class="search_input"
						onclick="clear_session(1)" />
				</div>

			</div>

		</div>



		<script type="text/javascript">
			function cc(e, name) {
				var a = document.getElementsByName(name);
				for ( var i = 0; i < a.length; i++)
					a[i].checked = e.checked;
			}
			//initTabs('dhtmlgoodies_tabView1',Array('Sources','Catalog Codes','Customer Notice','Order Types','Gift Messages','Promotions'),0,998,340);
			initTabs('dhtmlgoodies_tabView1',
					Array('Sources', 'Customer Notice', 'Order Types',
							'Promotions', 'Gift Card'), 0, 998, 340);
			$("#Sources").click(function() {
				check_session();
				$("#paramHiddenSources").show();
				$("#paramHiddenCatalog").hide();
				$("#paramHiddenCustomer").hide();
				$("#paramHiddenOrder").hide();
				$("#paramHiddenGift").hide();
				$("#paramHiddenPromotions").hide();
				$("#paramHiddenCoupon").hide();
				$("#current_tab_name").val('Source');
				searchSource(1);
				$("#sourceNew").show();
				$("#NoticeNew").hide();
				$("#TypeNew").hide();
				$("#promotionNew").hide();
				$("#giftNew").hide(); 
				
				
			});
			$('#tabTabdhtmlgoodies_tabView1_1').click(function() {
				check_session();
				$("#paramHiddenSources").hide();
				$("#paramHiddenCatalog").hide();
				$("#paramHiddenCustomer").show();
				$("#paramHiddenOrder").hide();
				$("#paramHiddenGift").hide();
				$("#paramHiddenPromotions").hide();
				$("#paramHiddenCoupon").hide();
				$("#current_tab_name").val('CustomerNotice');

				$("#sourceNew").hide();
				$("#NoticeNew").show();
				$("#TypeNew").hide();
				$("#promotionNew").hide();
				$("#giftNew").hide(); 
				
			});
			$('#tabTabdhtmlgoodies_tabView1_2').click(function() {
				check_session();
				$("#paramHiddenSources").hide();
				$("#paramHiddenCatalog").hide();
				$("#paramHiddenCustomer").hide();
				$("#paramHiddenOrder").show();
				$("#paramHiddenGift").hide();
				$("#paramHiddenPromotions").hide();
				$("#paramHiddenCoupon").hide();
				$("#current_tab_name").val('OrderTypes');
				

				$("#sourceNew").hide();
				$("#NoticeNew").hide();
				$("#TypeNew").show();
				$("#promotionNew").hide();
				$("#giftNew").hide(); 
			});
			$('#Promotions').click(function() {
				check_session();
				$("#paramHiddenSources").hide();
				$("#paramHiddenCatalog").hide();
				$("#paramHiddenCustomer").hide();
				$("#paramHiddenOrder").hide();
				$("#paramHiddenGift").hide();
				$("#paramHiddenPromotions").show();
				$("#current_tab_name").val('Promotion');
				$("#paramHiddenCoupon").hide();
				searchPromotion(1);
				

				$("#sourceNew").hide();
				$("#NoticeNew").hide();
				$("#TypeNew").hide();
				$("#promotionNew").show();
				$("#giftNew").hide(); 
			});
			$("#paramHiddenCatalog").click(function() {
				check_session();
				$("#paramHiddenSources").hide();
				$("#paramHiddenCatalog").show();
				$("#paramHiddenCustomer").hide();
				$("#paramHiddenOrder").hide();
				$("#paramHiddenGift").hide();
				$("#paramHiddenPromotions").hide();
				$("#paramHiddenCoupon").hide();
			});
			$("#paramHiddenGift").click(function() {
				check_session();
				$("#paramHiddenSources").hide();
				$("#paramHiddenCatalog").hide();
				$("#paramHiddenCustomer").hide();
				$("#paramHiddenOrder").hide();
				$("#paramHiddenGift").show();
				$("#paramHiddenPromotions").hide();
				$("#paramHiddenCoupon").hide(); 

				
			});
			$('#tabTabdhtmlgoodies_tabView1_4').click(function() {
				check_session();
				$("#paramHiddenSources").hide();
				$("#paramHiddenCatalog").hide();
				$("#paramHiddenCustomer").hide();
				$("#paramHiddenOrder").hide();
				$("#paramHiddenGift").hide();
				$("#paramHiddenPromotions").hide();
				$("#paramHiddenCoupon").show();
				$("#current_tab_name").val('Coupon');
				searchCoupon(1);
				$("#sourceNew").hide();
				$("#NoticeNew").hide();
				$("#TypeNew").hide();
				$("#promotionNew").hide();
				$("#giftNew").show(); 
			});
		</script>
</body>
</html>
