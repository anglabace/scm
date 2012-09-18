function hasException(data) {
   if (data != undefined && data.hasException == 'Y') {
      alert(data.exception.messageDetail + "\r\n" + data.exception.action);
      return true;
   } else {
      return false;
   }  
 }
 
 function contains(array,str) {
	 if(array!=null&&array!='') {
		 for(var i =0;i<array.length;i++) {
			 if(array[i]==str) {
				 return true;
			 }
		 }
	 }
	 return false;
 }
 
 //去除数组中重复的元素
 function uniquelize(strArray) {
	 var ra = new Array();
	    for(var i = 0; i < strArray.length; i ++){
	        if(!ra.contains(strArray[i])){
	            ra.push(strArray[i]);
	        }
	    }
	    return ra;
 }