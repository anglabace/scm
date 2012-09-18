function getTypeFromProductInfo(productInfo){
	var tmpArr = productInfo.split(",");
	var tmpArr2 = [];
	var rtStr = "";
	for(var o in tmpArr){
		tmpArr2 = tmpArr[o].split(":");
		if(tmpArr2[0] == "type"){
			rtStr = tmpArr2[1];
			break;
		}
	}
	if(rtStr.toUpperCase() != "PRODUCT"){
		rtStr = "SERVICE";
	}
	return rtStr;
}

function getCatalogIdFromProductInfo(productInfo){
	var tmpArr = productInfo.split(",");
	var tmpArr2 = [];
	var rtStr = "";
	for(var o in tmpArr){
		tmpArr2 = tmpArr[o].split(":");
		if(tmpArr2[0] == "catalogId"){
			rtStr = tmpArr2[1];
			break;
		}
	}
	return rtStr;
}

function getCatalogNameFromProductInfo(productInfo){
	var tmpArr = productInfo.split(",");
	var tmpArr2 = [];
	var rtStr = "";
	for(var o in tmpArr){
		tmpArr2 = tmpArr[o].split(":");
		if(tmpArr2[0] == "catalogName"){
			rtStr = tmpArr2[1];
			break;
		}
	}
	return rtStr;
}

$(document).ready(function(){
		function isNum(str) {
			str = str.replace(/\s/g,""); 
				alert( str ) ;
			if(str.length > 0)
			{
				if(isNaN(str))
				{   
					return false;
				}
			}
		}
		
		function findCatalogList( pageTag ){
			var catalogNoListStr = "" ;
			if( pageTag == "parentPage" ){
				catalogNoListObj = $('[name*="catalogNoTd"]') ;	
			}else{
				catalogNoListObj = window.parent.$('[name*="catalogNoTd"]') ;
			}

			if( catalogNoListObj.length > 0 ){
				catalogNoListObj.each(function(){
						tableTd = $(this) ;
						catalogNoListStr += tableTd.attr( "value" ) + "," ;
					}
				);
				return catalogNoListStr.substr( 0 , ( catalogNoListStr.length - 1 ) )  ;
			}
		}

		$( '#itemSelect' ).click(function(){
				var itemSelectCheckBoxs = $(":checkbox[name*='itemAdd'][checked=true]");
				var itemSelectDialogObj2 = window.parent.$('#itemLookupDialog') ;
				if( itemSelectCheckBoxs.size() != 0 ){
					var tmpValues = [];
					var i = 0 ;
					var tmpArr = [] ;
					var urlParam = "" ;
					var checkCatalogList = "" ;
					var msg = "" ;
					var checkQty = "" ;
					var catalogNo = "" ;
					var currentCatalogNo = "" ;
                    var itemQtyArray = new Array();
                    var productIdArray = new Array();

					itemSelectCheckBoxs.each(function(){
							if( i == 0 ){
								i ++ ;
								tmpArr = this.value.split( "@@@" ) ;
								urlParam = "&productId=" +tmpArr[0] +"&name=" +tmpArr[1] ;
								currentCatalogNo = $(this).parent().parent().parent().find('#tdCatalogNo').text() ;
							}else{
								tmpValues.push(this.value);
							}
							checkQty = $(this).parent().parent().parent().find( '#quantity' ).val() ;
                            itemQtyArray.push(checkQty);
							catalogNo = $(this).parent().parent().parent().find('#tdCatalogNo').text().replace(/\s+/g,"");
                            productIdArray.push(this.value.split( "@@@" )[0]);
							/*$.ajax({
								type:"get",
								url:"qu_order!getPreImmuneItem.action?catalogNo="+catalogNo,
								dataType:"json",
								success:function(data){
									if(data == "None"){
										alert("System error! Please contact system administrator for help.");
									}else{
										//alert(data.name);
										addPreImmuItemList( 'productList' , 1, data, checkQty ) ;
										//window.parent.$('#salesInformationIframe').contents().find("#itemForm_orderSource").trigger("change");
										parent.window.frames["salesInformationIframe"].sourceSelectChage();
									}
								},
								error:function(data){},
								async:false
								});*/
                            if (catalogNo != undefined) {
                                checkCatalogList += catalogNo + ",";
                            }
							if( checkQty == "" ){
								msg += catalogNo+" qty is null or zero!\r\n" ;
							}
		        	});
					
		        	if( msg != "" ){
		        		alert( msg ) ;
		        		return ;
		        	}
					window.parent.$('#selectCheckBoxValue').attr( 'value' , tmpValues.join("%%%") ) ;
					var itemSelectDialogObj = window.parent.$('#itemSelectDialog');
					var catalogNoList = findCatalogList( "" ) ;	
					//alert( catalogNoList ) ;
					if( catalogNoList != "" && catalogNoList != undefined){
						catalogNoList = checkCatalogList+catalogNoList	 ;
					}else{
						catalogNoList = checkCatalogList.substr( 0 , ( checkCatalogList.length - 1 ) )  ;
					}
					$.ajax({
							type:"get",
							url:"qu_order!testProductProductRelationForm.action?custNo="+orderquoteObj.customerNo+"&quorderNo="+orderquoteObj.quorderNo+"&searchClass="+orderquoteObj.searchClass+"&catalogNoList="+catalogNoList+urlParam+"&codeType="+orderquoteObj.type+"&qty="+itemQtyArray.join(",")+"&productIds=" + productIdArray.join(","),
							dataType:"",
							success:function(data){
								if(data == "true"){
								itemSelectDialogObj2.dialog('close');
								itemSelectDialogObj.attr( 'innerHTML' , '<iframe src="qu_order!showProductProductRelationForm.action?custNo='+orderquoteObj.customerNo+'&quorderNo='+orderquoteObj.quorderNo+'&searchClass='+orderquoteObj.searchClass+'&catalogNoList='+catalogNoList+urlParam+'&codeType='+orderquoteObj.type+'" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="No" ></iframe>' ) ;
								itemSelectDialogObj.dialog( "option" , "title" , "Related Item to "+currentCatalogNo+" "+tmpArr[1] ) ; 
								setItemList( 'productList' , 1 ) ;
								//alert(window.parent.$('#salesInformationIframe').contents().find("#itemForm_orderSource").change());
								parent.window.frames["salesInformationIframe"].sourceSelectChage();
								}else if(data == "false"){
									itemSelectDialogObj2.dialog('close');
									itemSelectDialogObj.attr( 'innerHTML' , '<iframe src="qu_order!showProductProductRelationForm.action?custNo='+orderquoteObj.customerNo+'&quorderNo='+orderquoteObj.quorderNo+'&searchClass='+orderquoteObj.searchClass+'&catalogNoList='+catalogNoList+urlParam+'&codeType='+orderquoteObj.type+'" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="No" ></iframe>' ) ;
									itemSelectDialogObj.dialog( "option" , "title" , "Related Item to "+currentCatalogNo+" "+tmpArr[1] ) ; 
									itemSelectDialogObj.dialog( 'open' ) ;
									setItemList( 'productList' , 1 ) ;
									//window.parent.$('#salesInformationIframe').contents().find("#itemForm_orderSource").trigger("change");
									parent.window.frames["salesInformationIframe"].sourceSelectChage();
								}else{
                                    alert(data);
                                    return false;
                                }
								
							},
							error:function(data){},
							async:false
							});
					
				}else{
					alert( 'Please select items!' ) ;
				}
			}
		);

		$('#addItem').click(function(){
				addRelationItem( 1 ) ;
			}
		) ;
		
		function addRelationItem( needAddTag ){
			
			var selectCheckBoxValue = window.parent.$('#selectCheckBoxValue').attr( 'value' ) ;	
			var itemSelectDialogObj = window.parent.$('#itemSelectDialog') ;
			var msg = "" ;
			var checkQty = "" ;

			$(":checkbox[checked=true]").each(function(){
					catalogNo = $(this).parent().parent().parent().find('#tdCatalogNo').text() ;
					checkQty = $(this).parent().parent().parent().find( '#quantity' ).val() ;

					if( checkQty == "" ){
						msg += catalogNo+" qty is null or zero!\r\n" ;
					}
        		}
        	);
			
        	if( msg != "" ){
        		alert( msg ) ;
        		return ;
        	}
        	
        	if( needAddTag == 1 ){
				setItemList( 'relationProductList' , 2 ) ;
        	}
        	
			if( selectCheckBoxValue != "" ){
				
				var urlparam = "";
				var tmpArr1 = [] ;
				var tmpArr2 = [] ;
				var replaceStr = "" ;
				tmpArr1 = selectCheckBoxValue.split( "%%%" ) ;
				tmpArr2 = tmpArr1[0].split( "@@@" ) ;
				urlParam = "&productId="+tmpArr2[0]+"&name="+tmpArr2[1] + "&qty=" + checkQty+"&productIds=" + tmpArr2[0];
				if( typeof( tmpArr1[1] ) == "undefined" )
				{
					replaceStr = tmpArr2[0]+"@@@"+tmpArr2[1]+"@@@"+tmpArr2[2] ;
				}
				else
				{
					replaceStr = tmpArr2[0]+"@@@"+tmpArr2[1]+"@@@"+tmpArr2[2]+"%%%" ;
				}
				//alert( selectCheckBoxValue ) ;
				
				
				selectCheckBoxValue = selectCheckBoxValue.replace( replaceStr , "" ) ;
				window.parent.$('#selectCheckBoxValue').attr( 'value' , selectCheckBoxValue ) ;
				var catalogNoList = findCatalogList( "" ) ;
				catalogNoList = "" ;
				$.ajax({
							type:"get",
							url:"qu_order!testProductProductRelationForm.action?custNo="+orderquoteObj.customerNo+"&quorderNo="+orderquoteObj.quorderNo+"&searchClass="+orderquoteObj.searchClass+"&catalogNoList="+catalogNoList+urlParam+"&codeType="+orderquoteObj.type + "&productIds=" + productIdArray.join(","),
							dataType:"",
							success:function(data){
								if(checkQty!=""){
								}
								if(data == "true"){
									addRelationItem( 1 ) ;
									return ;
									//itemSelectDialogObj.dialog( 'close' ) ;
								//itemSelectDialogObj.attr( 'innerHTML' , '<iframe src="qu_order!showProductProductRelationForm.action?custNo='+orderquoteObj.customerNo+'&quorderNo='+orderquoteObj.quorderNo+'&searchClass='+orderquoteObj.searchClass+'&catalogNoList='+catalogNoList+urlParam+'&codeType='+orderquoteObj.type+'" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="No" ></iframe>' ) ;
								//itemSelectDialogObj.dialog( "option" , "title" , "Related Item to "+tmpArr2[2]+" "+tmpArr2[1] ) ;	
								}else if(data == "false"){
									itemSelectDialogObj.attr( 'innerHTML' , '<iframe src="qu_order!showProductProductRelationForm.action?custNo='+orderquoteObj.customerNo+'&quorderNo='+orderquoteObj.quorderNo+'&searchClass='+orderquoteObj.searchClass+'&catalogNoList='+catalogNoList+urlParam+'&codeType='+orderquoteObj.type+'" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="No" ></iframe>' ) ;
									itemSelectDialogObj.dialog( "option" , "title" , "Related Item to "+tmpArr2[2]+" "+tmpArr2[1] ) ;
								}else{
                                    alert(data);
                                    return false;
                                }
							},
							error:function(data){},
							async:false
							});
				
			}else{
				itemSelectDialogObj.dialog( 'close' ) ;
			}
		}
		
		$( ':checkbox[name*="itemAdd"]' ).click(function(){
				$('#sellingNotes').attr( "value" , this.value ) ;
			}
		) ;

		$('#closeDialog').click(function(){
				var itemSelectDialogObj = window.parent.$('#itemLookupDialog') ;
				itemSelectDialogObj.dialog( 'close' ) ;
			}
		);
		
		
		$('#closeRelationDialog').click(function(){
				addRelationItem( 0 ) ;
		});
		
		function formatSize( price ){
			var tmpArr = price.split( " " ) ;
			if(!tmpArr[0]){
				return 0;
			}
			return tmpArr[0] ;
		}
		
		function setItemList( tableId , type ){
			var ctlgNo = "";
			var name = "";
			var wholeName = "" ;
			var sellingType = "";
			var status = "CM";
			var qty = "";
			var sizeUom = "";
			var qtyUom = "";
			var size = "";
			var unitPrice = "";
			var amount = 0 ;
			var totalAmount = 0 ;
			var sprice = "";
			var discount = 0;
			var tax = 0;
			
			var i = 1 ;
			var itemListIframe = window.parent.$('#itemListIframe');
			var itemTableObj = itemListIframe.contents().find( '#itemTable' ) ;
			var itemTableDivObj = itemListIframe.contents().find( '#itemTableDiv' ) ;
			
			var itemNo = 0 ;
			var msg = "" ;
			var itemTrNum = 0 ;
			var classStr = "" ;
			var productInfo = "";
			var productType = "";
			var catalogId = "";
			var catalogName = "";
			var parentId ;
			var srcQuoteNo = "";
			var srcOrderNo = "";
			var srcItemNo = 0;
			var templateType = "";
			var serviceSplit = $("#serviceSplit").attr("checked");
			if( $( '#'+tableId+' tr' ).length > 0 )
			{
				var itemsArr = [];//Save data to session
				srcQuoteNo = $( '#'+tableId+' tr:first').find("td:first").find( "[name*='srcQuoteNo']" ).val();
				srcOrderNo = $( '#'+tableId+' tr:first').find("td:first").find( "[name*='srcOrderNo']" ).val();
				if(typeof(srcQuoteNo) == 'undefined'){
					srcQuoteNo = '';
				}
				if(typeof(srcOrderNo) == 'undefined'){
					srcOrderNo = '';
				}
				$( '#'+tableId+' tr' ).each(
					function ()
					{
						var tableTr = $(this) ;
						var tableCheckbox = tableTr.find("td:first").find( "[name*='itemAdd']" ) ;
						if( tableCheckbox.attr( 'checked' ) == true )
						{
							var tableTd = tableTr.find( 'td' ) ;
							var upPrecision = tableTr.find( '#upPrecision' ).val() ;
							var upSymbol = tableTr.find( '#upSymbol' ).val() ;
							ctlgNo = tableTr.find( '#tdCatalogNo' ).html().replace( "&nbsp;", "" ) ;
							name = tableTr.find( '#tdName' ).attr( 'title2' ) ;
							wholeName = tableTr.find( '#tdName' ).attr( 'title' ) ;
							qty = tableTr.find( '#quantity' ).attr('value') ;
							srcItemNo = tableTr.find( '#srcItemNo' ).val() ;
							size = tableTr.find( '#tdSizeUom' ).html().replace( "&nbsp;", "" ) ;
							size = formatSize( size ) ;
							unitPrice = tableTr.find( '#unitPrice' ).val() ;
							if(!unitPrice){
								unitPrice = 0;
							}
							amount = qty * unitPrice ;
							oNoobj = new Number( amount ) ;
							amount = oNoobj.toFixed( upPrecision ) ;
							upSymbol = tableTr.find( '#upSymbol' ).val() ;
							parentId = tableTr.find( '#parentId' ).val() ;
							if(!parentId){
								parentId = "";
							}
							sizeUom = tableTr.find('#uom').attr('value') ;
							qtyUom = tableTr.find('#qtyUom').attr('value') ;
							productInfo = tableTr.find( '#productInfo' ).attr( 'value' ) ;
							itemTrNum = itemTableObj.find("tr").length ;
							itemNo = itemTrNum+i  ;
							productType = getTypeFromProductInfo(productInfo);
							catalogId = getCatalogIdFromProductInfo(productInfo);
							catalogName = getCatalogNameFromProductInfo(productInfo);
							templateType = tableTr.find('#templateType').attr('value');
							if (templateType == undefined) {
								templateType = "";
							}
							productType = productType.toUpperCase();
							if(productType != "PRODUCT" && serviceSplit){
								var tmpCount = qty;
								qty = 1;
							}else{
								var tmpCount = 1;
							}
							for(var tmpI = 0; tmpI < tmpCount; tmpI ++){
								var tmpArr = {"catalogNo":ctlgNo, "name":wholeName,"quantity":qty, "size":size,"unitPrice":unitPrice,"upSymbol":upSymbol, "sizeUom":sizeUom,"qtyUom":qtyUom,"discount":discount, "tax":tax, "itemNo":itemNo, "amount":amount, "type":productType, "catalogId":catalogId, "catalogText":catalogId+" : " + catalogName, "parentId":parentId, "srcItemNo":srcItemNo, "templateType":templateType};
								itemsArr.push(tmpArr);   
							    totalAmount += parseInt( amount ) ;
							    
					            i++ ;
					            itemNo ++;
							}
							tableTd.parent( "tr" ).find( '[name*="itemAdd"]' ).attr( "checked" , false ) ;
							tableTd.parent( "tr" ).find( '[name*="quantity"]' ).val( "" ) ;
						}
					}
				) ;
				
				if( itemNo != 0 )
				{
					parentTotalAmount = window.parent.$('#totalAmount').attr( 'value'  ) ;
					parentTotalAmount = parseInt( parentTotalAmount ) + totalAmount ;
					window.parent.$('#totalAmount').attr( 'value' , parentTotalAmount ) ;
					
					var saveMsg = saveItems( itemsArr, srcQuoteNo, srcOrderNo);
					if(saveMsg != true && saveMsg != ""){
						alert(saveMsg);
						return;
					}else{
						parent.window.orderquoteObj.triggerLast = 1;
						parent.window.orderquoteObj.originQty = itemTrNum;
						parent.document.getElementById("itemListIframe").contentWindow.location.href = orderquoteObj.url("getItemList");
						window.parent.frames["totalIframe"].clearTotalAll();
					}
				}
			}
			return ;
		}
		
		function addPreImmuItemList( tableId , type , data ,checkQty){
			var ctlgNo = data.catalogNo;
			var name = data.name;
			var wholeName = data.name ;
			var sellingType = "";
			var status = "CM";
			var qty = parseInt(checkQty);
			var sizeUom = data.uom;
			var qtyUom = data.qtyUom;
			var size = data.size;
			var unitPrice = "";
			var amount = 0 ;
			var totalAmount = 0 ;
			var sprice = "";
			var discount = 0;
			var tax = 0;
			
			var i = 1 ;
			var itemListIframe = window.parent.$('#itemListIframe');
			var itemTableObj = itemListIframe.contents().find( '#itemTable' ) ;
			var itemTableDivObj = itemListIframe.contents().find( '#itemTableDiv' ) ;
			
			var itemNo = 0 ;
			var msg = "" ;
			var itemTrNum = 0 ;
			var classStr = "" ;
			var productInfo = "";
			var productType = "";
			var catalogId = data.catalogId;
			var catalogName = "";
			var parentId ;
			var srcQuoteNo = "";
			var srcOrderNo = "";
			var srcItemNo = 0;
			//var serviceSplit = $("#serviceSplit").attr("checked");
//			if( $( '#'+tableId+' tr' ).length > 0 )
//			{
				var itemsArr = [];//Save data to session
//				$( '#'+tableId+' tr' ).each(
//					function ()
//					{
//						var tableTr = $(this) ;
//						var tableCheckbox = tableTr.find("td:first").find( "[name*='itemAdd']" ) ;
//						if( tableCheckbox.attr( 'checked' ) == true )
//						{
//							var tableTd = tableTr.find( 'td' ) ;
//							var upPrecision = tableTr.find( '#upPrecision' ).val() ;
//							var upSymbol = tableTr.find( '#upSymbol' ).val() ;
//							ctlgNo = tableTr.find( '#tdCatalogNo' ).html().replace( "&nbsp;", "" ) ;
//							name = tableTr.find( '#tdName' ).attr( 'title2' ) ;
//							wholeName = tableTr.find( '#tdName' ).attr( 'title' ) ;
//							qty = tableTr.find( '#quantity' ).attr('value') ;
//							size = tableTr.find( '#tdSizeUom' ).html().replace( "&nbsp;", "" ) ;
//							size = formatSize( size ) ;
//							unitPrice = tableTr.find( '#unitPrice' ).val() ;
							if(!unitPrice){
								unitPrice = 0;
							}
							amount = qty * unitPrice ;
							oNoobj = new Number( amount ) ;
							amount = oNoobj.toFixed( 0 ) ;
							upSymbol = data.upSymbol ;
							//alert(data.upSymbol);
							parentId = "";
							
//							sizeUom = tableTr.find('#uom').attr('value') ;
//							qtyUom = tableTr.find('#qtyUom').attr('value') ;
							productInfo = 'name:'+data.name+',type:'+data.type+',taxStatus:,scheduleShip:,fullDesc: ,comment: ,clsId: ,clsName: ,sellingNote: ,'+'catalogId:'+data.catalogId+' ,catalogName:'+ ''  ;
							itemTrNum = itemTableObj.find("tr").length ;
							itemNo = itemTrNum+i  ;
							productType = getTypeFromProductInfo(productInfo);
							catalogId = getCatalogIdFromProductInfo(productInfo);
							catalogName = getCatalogNameFromProductInfo(productInfo);
							productType = productType.toUpperCase();
							if(productType != "PRODUCT" ){
								var tmpCount = 1;
							}
							for(var tmpI = 0; tmpI < tmpCount; tmpI ++){
								var tmpArr = {"catalogNo":ctlgNo, "name":wholeName,"quantity":qty, "size":size,"unitPrice":unitPrice,"upSymbol":upSymbol, "sizeUom":sizeUom,"qtyUom":qtyUom,"discount":discount, "tax":tax, "itemNo":itemNo, "amount":amount, "type":productType, "catalogId":catalogId, "catalogText":catalogId+" : " + catalogName, "parentId":parentId};
								itemsArr.push(tmpArr);   
							    totalAmount += parseInt( amount ) ;
							    
					            i++ ;
					            itemNo ++;
							}
							//tableTd.parent( "tr" ).find( '[name*="itemAdd"]' ).attr( "checked" , false ) ;
							//tableTd.parent( "tr" ).find( '[name*="quantity"]' ).val( "" ) ;
//						}
//					}
//				) ;
				
				if( itemNo != 0 )
				{
					parentTotalAmount = window.parent.$('#totalAmount').attr( 'value'  ) ;
					parentTotalAmount = parseInt( parentTotalAmount ) + totalAmount ;
					window.parent.$('#totalAmount').attr( 'value' , parentTotalAmount ) ;

					var saveMsg = saveItems( itemsArr, srcQuoteNo, srcOrderNo);
					if(saveMsg != true && saveMsg != ""){
						alert(saveMsg);
						return;
					}else{
						parent.window.orderquoteObj.triggerLast = 1;
						parent.document.getElementById("itemListIframe").contentWindow.location.href = orderquoteObj.url("getItemList");
						parent.document.getElementById("totalIframe").src = orderquoteObj.url("showTotal");
					}
				}
			
			return ;
		}
		
		var JSON = {
		jsonToString: function(obj){  
		       var THIS = this;   
		       switch(typeof(obj)){  
		           case 'string':  
		               return '"' + obj.replace(/(["\\])/g, '\\$1') + '"';  //'
		           case 'array':  
		               return '[' + obj.map(THIS.jsonToString).join(',') + ']';  
		           case 'object':  
		                if(obj instanceof Array){  
		                   var strArr = [];  
		                   var len = obj.length;  
		                   for(var i=0; i<len; i++){  
		                       strArr.push(THIS.jsonToString(obj[i]));  
		                   }  
		                   return '[' + strArr.join(',') + ']';  
		               }else if(obj==null){  
		                   return 'null' ;
		               }else{  
		                   var string = [] ;
		                   for (var property in obj) string.push(THIS.jsonToString(property) + ':' + THIS.jsonToString(obj[property]));  
		                   return '{' + string.join(',') + '}';  
		               }  
		           case 'number':  
//		               return obj;
		        	   return '"' + obj + '"';//modified by Yulu Zou 2010-04-15
		           case false:  
		               return obj;  
		       }
		   }
		};
		
		function saveItems(itemsArr, srcQuoteNo, srcOrderNo){
			var tmpArr = [];
			for(var i in itemsArr){
				tmpArr.push(JSON.jsonToString(itemsArr[i]));
			}
			var srcQuOrderNo;
			if(orderquoteObj.type == 'order'){
				srcQuOrderNo = srcOrderNo;
			}else{
				srcQuOrderNo = srcQuoteNo;
			}
			var itemsArrNew = {"items":tmpArr};
			var saveMsg;
			$.ajax({
			    type: "POST",
			    url: orderquoteObj.type+"_item!saveSessionItem.action?"+orderquoteObj.sessNoName+"="+orderquoteObj.quorderNo+"&custNo="+orderquoteObj.customerNo+"&srcQuOrderNo="+srcQuOrderNo,
			    data: itemsArrNew,
			    dataType: "json",
			    async: false ,
			    success: function(rs){
			    	if (rs != undefined && rs.message != undefined) {
			    		saveMsg = rs.message;
			    	} else {
						saveMsg = true;
			    	}
				},
				error: function(data){
					saveMsg = data.responseText;
				}
			    });
			return saveMsg;
		}
				
		$( '#allCheck' ).click(
			function()
			{
				if( $(this).attr( 'checked' ) === true )
				{
					$( ':check [name*="mm"]' ).attr( 'checked' , true ) ;
				}
				else
				{
					$( ':check [name*="mm"]' ).attr( 'checked' , false ) ;
					$(this).attr( 'checked' , false ) ;
				}
			}
		) ;

		$( '#itemAllCheck' ).click(
			function()
			{
				if( $(this).attr( 'checked' ) === true )
				{
					$( ':check [name*="itemAdd"]' ).attr( 'checked' , true ) ;
				}
				else
				{
					$( ':check [name*="itemAdd"]' ).attr( 'checked' , false ) ;
					$(this).attr( 'checked' , false ) ;
				}
			}
		) ;

		$( '#[name*="quantity"]' ).click(
			function()
			{
				$(this).parent("td").parent("tr").find( "[name*='itemAdd']" ).attr( "checked" , "true" ) ;
				if($(this).val() == ""){
					$(this).val("1");
				}
			}		       
		) ;
		$( ':check [name*="itemAdd"]' ).click(
				function(){
					if($(this).attr("checked") == false){
						$(this).parent("div").parent("td").parent("tr").find( "[name*='quantity']" ).attr("value", '');
						
					}else{
						$(this).parent("div").parent("td").parent("tr").find( "[name*='quantity']" ).attr("value", '1');
					}
				}
			);

		$('[id*="viewSpePrice"]').click(
			function()
			{
				var ctlgNo = $(this).parent( 'td' ).parent('tr').find( '#tdCatalogNo' ).html().replace( '&nbsp;' , '' ) ;
				var viewSpePriceDialogObj = window.parent.$('#viewSpePriceDialog') ;
				viewSpePriceDialogObj.attr( 'innerHTML' , '<iframe src="cust_spcl_prc!showSpecialPrice.action?custNo='+orderquoteObj.customerNo+'&catalogNo='+ctlgNo+'" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="No" ></iframe>' ) ;
				viewSpePriceDialogObj.dialog( 'open' ) ;
			}
		) ;
		
}) ;
