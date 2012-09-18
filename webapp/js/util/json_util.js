/**
 * **************************************************************************************************************************
 * 处理json对象相关方法
 * @author zouyulu
 * **************************************************************************************************************************
 */

//对象转为json string
var JSON = {
jsonToString: function(obj){  
       var THIS = this;   
       switch(typeof(obj)){  
           case 'string':  
               return '"' + obj.replace(/(["\\])/g, '\\$1') + '"'; //'
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
                   return 'null';  
               }else{  
                   var string = [];  
                   for (var property in obj) string.push(THIS.jsonToString(property) + ':' + THIS.jsonToString(obj[property]));  
                   return '{' + string.join(',') + '}';  
               }  
           case 'number':  
//		       return obj;
        	   return '"' + obj + '"';
           case false:  
               return obj;  
       }
   }
}

/**
 * 需要使用 new 新建实例
 * @param data
 * @param saveId
 * @returns {JSON_MAP}
 */
function JSON_MAP(data){
	this.jsonMap = data;
	//this.jsonMapName = jsonMapName;
	//this.domEleId = domEleId;
	/**
	 * 增加一个json string
	 */
	this.addOneRow = function(key, jsonStr){
		this.jsonMap[key] = jsonStr;
		this.updateJsonMap();
	};
	
	/**
	 * 更新一条json string
	 */
	this.updateOneRow = function(key, jsonObj){
		this.jsonMap[key] = jsonObj;
		this.updateJsonMap();
	}
	
	/**
	 * 删除一条json string 记录
	 */
	this.deleteOneRow = function(key){
		var tmpJsonMap = [];
		for(var o in this.json_map){
			if(o == key){
				if(parseInt(o) == o){
					tmpJsonMap[o] = null;
				}else{
					continue;
				}
			}
		}
		this.jsonMap = tmpJsonMap;
		this.updateJsonMap();
	};
	
	/**
	 * 返回
	 */
	this.getJsonMapByKey = function(key){
		if(!key){
			return "";
		}else{
			return this.jsonMap[key];
		}
	};
	
	/**
	 * encodeURIComponent 加密过的。
	 */
	this.getJsonMapByKeyEnCode = function(key){
		return encodeURIComponent(JSON.jsonToString(this.getJsonMapByKey(key)));
	}
	
	/**
	 * 解码函数
	 */
	this.decodeURIComponent = function(encodeStr){
		return decodeURIComponent(encodeStr);
	}
	
	/**
	 * 转化为url方便传输数据
	 */
	this.conver2url = function(beanName){
		var parms = [];
		var index = 0;
		if(this.jsonMap){
			for(var k in this.jsonMap){
				for(var p in this.jsonMap[k]){
					if(this.jsonMap[k][p] == "null"|| this.jsonMap[k][p] == null){
						parms.push(beanName+"["+index+"]."+p+"=");
					}else{
						parms.push(beanName+"["+index+"]."+p+"="+this.jsonMap[k][p]);
					}
				}
				index ++;
			}
		}
		return parms.join("&");
	}
	
	/**
	 * 自动更新全局jsonMap
	 */
	this.updateJsonMap = function(){
		//alert("tt");
		//eval(this.jsonMapName+"=this.jsonMap;");
		//$("#"+domEleId).data(this.jsonMapName, this.jsonMap);
	}
}



