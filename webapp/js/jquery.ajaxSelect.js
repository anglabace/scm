/*
联动下拉列表(jqery+ajax)
Author: lz
Syntax: $(element).ajaxSelect({
		});
select1:当前触发事件的下拉列表ID
select2:将要影响的下拉列表ID
url:ajax请求的地址
param:select1选择的值将传向后台的变量名
defaultOption:是否给select2设定默认值
listName:后台返回的list
selectName：赋值给option的test
selectValue：赋值给option的value
*/
(function($) {
  $.fn.ajaxSelect = function(options) {
	  var defaults = {   
			    select1     :"select1",
			    select2     :"select2",
			    	url		:"",
			    	param   :"",
			    	listName:"list",
			    	selectName:"name",
			    	selectValue:"value",
					defaultOption   :true				
	        };   
	  
	       options = $.extend(defaults, options);  
	        var select1="#"+options.select1;
	        var select2="#"+options.select2;
	        var addOption = function(data) {
	        	$(select2).empty();
	        	if(options.defaultOption) {
	        		var option = "<option value=''>please select...</option>";
	        		$(select2).append(option);
	        	}
	        	for(var i=0;i<data[options.listName].length;i++) {
	        		var option2 = "<option value='"+data[options.listName][i][options.selectValue]+"'>"+data[options.listName][i][options.selectName]+"</option>";
	        		$(select2).append(option2);
	        	}
	        	
	        };
	    	return  $(this).change(function(){   
	    		var dataStr = options.param+"="+$(select1).val();
	    		$.ajax({   
           		    url:options.url,   
           		    type:'POST',   
           		    dataType: 'json',   
           		    data:dataStr,   
           			beforeSend:function(xmlhttprequest){
           		 	},
           			success:function(data){
           		 		addOption(data);
           		 		$(select2).change();
           			},
           			error:function(data){
           				alert("出错了！");
           			}
           		});
	                return false;   
	            });    
  }
})(jQuery);
