	/* show/hide tag */
	function toggleShowMore(objId,divID){
		var oId = document.getElementById(divID);	// no item suffix
		var obj = document.getElementById(objId);
		var divDisplay = oId.style.display;
		if (divDisplay == "none") {
			oId.style.display = "";
//				oLink.style.background="url(images/ad.gif) no-repeat left 2px";
//				oLink.innerHTML = "&nbsp;&nbsp;&nbsp;";
			obj.src= baseUrl + "images/ad.gif";
//				oLink.href = 'javascript:void(0)';
		} else { 
			oId.style.display = "none";
//				oLink.style.background="url(images/ar.gif) no-repeat left 2px";
//				oLink.innerHTML = "&nbsp;&nbsp;&nbsp;";
			//oLink.href = '#' + divID.substring(8, divID.length);
//				oLink.href='javascript:void(0)';
			obj.src= baseUrl + "images/ar.gif";
		}
	}

	function toggle_showmore(objId, divID){
	    var obj = document.getElementById(objId);
		var oId = document.getElementById(divID);	// no item suffix
		if (obj.src.indexOf('ad.gif') > 0){
			obj.src=baseUrl + "images/ar.gif";
			oId.style.display = "none"; 
		}else{
			obj.src=baseUrl + "images/ad.gif";
			oId.style.display = "block"; 
		}
	}
	
	function toggleShowMore_img(obj, divID){
		var oId = document.getElementById(divID);	// no item suffix
		if (obj.src.indexOf('ad.gif') > 0){
			obj.src= "images/ar.gif";
			oId.style.display = "none"; 
		}else{
			obj.src="images/ad.gif";
			oId.style.display = "block"; 
		}
	}

	//Function to determine the X value of the 'key' link
	function mouseY(evt) {
		
		var iStartFrom = 20;
		var iFloorJiggery = 0;
		
		if (evt.pageY) {
			iStartFrom = evt.pageY;
		} else if (evt.clientY) {
		   iStartFrom = evt.clientY + (document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop);
		}
		
		//Using this floor, it will stop the popup from jumping about (give or take 20px).
		iFloorJiggery = iStartFrom / 20;
		iFloorJiggery = Math.floor(iFloorJiggery);
		iStartFrom = iFloorJiggery * 20;
		
		return iStartFrom;
	}
	
	//Used to display the applications key
	function showAppKey(id, event){
		
		var oDiv = document.getElementById(id);
		
		//IE or Firefox?
		if(window.event) {
			var oEvt = window.event;
		} else { 
			var oEvt = event;
		}
		
		oDiv.style.top = mouseY(oEvt);
		oDiv.style.visibility = 'visible';	
	}
	
	//Used to hide the applications key
	function hideAppKey(id){
		
		var oDiv = document.getElementById(id);
		oDiv.style.visibility = 'hidden';
	}
