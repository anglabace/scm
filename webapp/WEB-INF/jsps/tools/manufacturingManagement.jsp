<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />


<script language="javascript" type="text/javascript"
	src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script>
	$(function() {
		$("#table_new tr").each(function(i) {

			this.style.backgroundColor = [ "#ffffff", "#eee" ][i % 2]
		})

	})
</script>


</head>

<body class="content" style="background-image: none;">





	<div class="scm">
		<div class="title_content">
			<div class="title">Manufacturing Management</div>
		</div>
		<div class="input_box">
			<table width="998" border="0" cellspacing="0" cellpadding="0"
				class="tableurl">
				<tr>
					<th width="178">System Name</th>
					<th width="475">Entry</th>
					<th></th>
					<th>Description</th>
				</tr>
			</table>
			<table width="998" border="0" cellspacing="0" cellpadding="0"
				class="tableurl" id="table_new">
				
				<tr>
					<td width="178" class="tableurl_tda">Invoice System</td>
					<td width="475"><a
						href="https://www.genscript.com/ssl-bin/inventory/inventorymanager"
						target="_blank">https://www.genscript.com/ssl-bin/inventory/inventorymanager</a>
					</td>
					<td>Entry of inventory system</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">Supply Material</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/inventory/inventory_supply"
						target="_blank">https://www.genscript.com/ssl-bin/inventory/inventory_supply</a>
					</td>
					<td>They can input supply information (supplier, supply size,
						cost…) for each inventory</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">Inventory Order</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/order_inventory"
						target="_blank">https://www.genscript.com/ssl-bin/order_inventory</a>
					</td>
					<td>Entry to place inventory order</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">Inventory Order Tracking Stytem</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/supply/viewsupply?table=po.order_inventory&amp;sort=order_id"
						target="_blank">https://www.genscript.com/ssl-bin/supply/viewsupply?table=po.order_inventory&amp;sort=order_id</a>
					</td>
					<td>List all ongoing inventory orders MMD can track them
						weekly</td>
				</tr>

				<tr>
					<td class="tableurl_tdc">&nbsp;&nbsp;&nbsp;Waiting List</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/order_inventory?waitinglist=1"
						target="_blank">https://www.genscript.com/ssl-bin/order_inventory?waitinglist=1</a>
					</td>
					<td>A list to place inventory order for out of inventory
						products</td>
				</tr>
				<tr>
					<td class="tableurl_tda">Supply Manager</td>
					<td><a href="www.genscript.com/ssl-bin/supply/supplymanager"
						target="_blank">www.genscript.com/ssl-bin/supply/supplymanager</a>
					</td>
					<td>Entry of supply manager</td>
				</tr>
				<tr>
					<td class="tableurl_tdb">Update supplier of items</td>
					<td><a href="www.genscript.com/ssl-bin/supply/updatemanager"
						target="_blank">www.genscript.com/ssl-bin/supply/updatemanager</a>
					</td>
					<td>Update supply manager of one item</td>
				</tr>
				<tr>
					<td class="tableurl_tdc">&nbsp;&nbsp;&nbsp;Jinsirui Order
						Packing History</td>
					<td><a
						href="www.genscript.com/ssl-bin/supply/packing_history"
						target="_blank">www.genscript.com/ssl-bin/supply/packing_history</a>
					</td>
					<td>Check order item shipping history</td>
				</tr>
				<tr>
					<td>Internal Order Management</td>
					<td><a
						href="www.genscript.com/ssl-bin/internal_order/internal_manager"
						target="_blank">www.genscript.com/ssl-bin/internal_order/internal_manager</a>
					</td>
					<td>A place to manage internal order, approve internal order,
						update supply delivery date, send supply email</td>
				</tr>
				<tr>
					<td>My Request/Internal Quotation</td>
					<td><a href="https://www.genscript.com/ssl-bin/my_request"
						target="_blank">https://www.genscript.com/ssl-bin/my_request</a>
					</td>
					<td>Monitor cost of pre quote</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><a
						href="https://www.genscript.com/ssl-bin/pre_quote?op=search_pre_quote"
						target="_blank">https://www.genscript.com/ssl-bin/pre_quote?op=search_pre_quote</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Ordering system</td>
					<td><a
						href="www.genscript.com/ssl-bin/order_process/showdetail"
						target="_blank">www.genscript.com/ssl-bin/order_process/showdetail</a>
					</td>
					<td>Update/cancel inventory orders, update supply guaranteed
						date, update order item cost, send supply email</td>
				</tr>
				<tr>
					<td>Supply Confirm List</td>
					<td><a
						href="http://www.genscript.com/ssl-bin/order_process/preparesupply"
						target="_blank">http://www.genscript.com/ssl-bin/order_process/preparesupply</a>
					</td>
					<td>A list where they send supply email for all customer
						confirmed roders</td>
				</tr>






			</table>
		</div>

	</div>

</body>
</html>
