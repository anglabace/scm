
FORMCHECK_LANG_1 = '以下原因导致提交失败：';
var ErrorItems;
function Validator_turnBlack(s,a){
	try
	{
		s[a].style.color='black';
	}
	catch (e)
	{
	}
}
Validator = {
	Require : /.+/,
	Email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
	Phone : /^[0-9\(\)\+][0-9\-\(\)\+]{0,21}$/,
	Mobile : /^((\(\d{3}\))|(\d{3}\-))?13\d{9}$/,
	Url : /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
	Url1 : /^[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
	FileUrl :/^[a-zA-Z]:\\(\w+\\{0,1})*/,
	IdCard : /^\d{15}(\d{2}[A-Za-z0-9])?$/,
	Currency : /^\d+(\.\d+)?$/,
	Number : /^\d+$/,
	Numbertimesoften : /^[1-9][0-9]*0$/,
	Nickname : /^((\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*)|([A-Za-z0-9_]+))$/,	
	Password1 : /^(\w){6,20}$/,
	IP : /^(((2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[1-9]|25[0-5]){1}\.(2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9]|25[0-5]){1}\.(2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9]|25[0-5]){1}\.(2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9]|25[0-5]){1}\;)*(2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[1-9]|25[0-5]){1}\.(2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9]|25[0-5]){1}\.(2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9]|25[0-5]){1}\.(2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9]|25[0-5]){1}|\*)$/,
	Zip : /^[1-9]\d{5}$/,
	QQ : /^[1-9]\d{4,8}$/,
	Integer : /^[-\+]?\d+$/,
	PositiveInteger : /^(\+)?[1-9][0-9]*$/,
	Double : /^[-\+]?\d+(\.\d+)?$/,
	English : /^[A-Za-z]+$/,
	Engnum : /^[0-9A-Za-z]+$/,
	Chinese :  /^[\u0391-\uFFE5]+$/,
	UnSafe : /^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/,
	UnSpecialChars : "this.IsUnSpecialChars(value)",
	IsSafe : function(str){return !this.UnSafe.test(str);},
	SafeString : "this.IsSafe(value)",
	ValidTime : "this.IsValid(value)",
	Limit : "this.limit(value.length,getAttribute('min'),  getAttribute('max'))",
	LimitB : "this.limit(this.LenB(value), getAttribute('min'), getAttribute('max'))",
	LimitESP : "this.LimitES(this.LenB(value), getAttribute('min'), getAttribute('max') , getAttribute('escape') , value)",
	Date : "this.IsDate(value, getAttribute('min'), getAttribute('format'))",
	Repeat : "value == document.getElementsByName(getAttribute('to'))[0].value",
	Range : "parseFloat(getAttribute('min')) <= value && value <= parseFloat(getAttribute('max')) && value!==''",
	RangeInteger : "this.IsValidRange(value, parseInt(getAttribute('min')), parseInt(getAttribute('max')))",
	Compare : "this.compare(value,getAttribute('operator'),getAttribute('to'))",
	Custom : "this.Exec(value, getAttribute('regexp'), getAttribute('regmod'))",
	Group : "this.MustChecked(getAttribute('name'), getAttribute('min'), getAttribute('max'))",
	UserName : "this.IsUsername(value, getAttribute('max'))",
	ErrorItem : [document.forms[0]],
	ErrorMessage : [],
	ErrorMessage2 : [],
	Validate : function(theForm, mode){
		var obj = theForm || event.srcElement;
		var count = obj.elements.length;
		this.ErrorMessage.length = 1;
		this.ErrorItem.length = 1;
		this.ErrorItem[0] = obj;
		//this.ErrorMessage[0] = FORMCHECK_LANG_1 + "\t\t\t\t"
		this.ErrorMessage[0] = ""
		
		for(var i=0;i<count;i++)
		{
			if(this.IsDisabled(obj.elements[i]))
			{
				continue ;
			}
			if(obj.elements[i].type=="button"||obj.elements[i].type=="submit"||obj.elements[i].type=="reset")
			{
				continue ;
			}
			with(obj.elements[i]){
				sre = /^ +/;
				ere = / +$/;
				
				if(getAttribute('maxLength'))
				{
					if(this.LenB(value)>maxLength)
					{
						this.AddError(i, getAttribute("msg"));
						continue ;
					}
				}
				
				var _dataType = getAttribute("dataType");	
								
				if(typeof(_dataType) == "object" || typeof(this[_dataType]) == "undefined")  continue;
				try
				{
					value = value.replace(sre,'');
					value = value.replace(ere,'');
				}
				catch (e)
				{
					continue;
				}
				this.ClearState(obj.elements[i]);
				
				if(getAttribute("require") == "false" && value == "") continue;
				switch(_dataType)
				{
					case "Date" :
					case "Repeat" :
					case "Range" :
					case "Compare" :
					case "Custom" :
					case "Group" : 
					case "Limit" :
					case "LimitB" :
					case "SafeString" :
					case "UnSpecialChars" :
				    case "UserName" :
					case "Password2" :
					case "ValidTime" :
					case "RangeInteger" :
					case "LimitESP" :
					
						if(!eval(this[_dataType]))	{
							this.AddError(i, getAttribute("msg"));
						}
						break;
					default :
						if(_dataType=='Integer'&& ( getAttribute('max') && getAttribute( 'min') ))
						{
							if(getAttribute('min') == '')
							{
								min = 0
							}
							else
							{
								min = getAttribute('min') ;
							}
							if(!this.compare( parseInt( value ) , 'LessThanEqual' , getAttribute('max')))
							{
								this.AddError(i, getAttribute("msg"));
								break;
							}
							if(!this.compare( parseInt( value ) , 'GreaterThanEqual' , min ))
							{
								this.AddError(i, getAttribute("msg"));
								break;
							}
						}
						if(_dataType=="FileUrl")
						{
							if(!this[_dataType].test(value)){
								this.AddError(i, getAttribute("msg"));
								break ;
							}
							if( getAttribute('extName')=='' )
							{
								break ;
							}
							else
							{
								var ext = getAttribute('extName') ;
								if( !eval("value.match(/(\\."+ext+")$/i)" ) )
								{
									this.AddError(i, getAttribute("msg"));
								}
							}
						}
						if(!this[_dataType].test(value)){
							this.AddError(i, getAttribute("msg"));
						}
						break;
				}
			}
		}
		if(this.ErrorMessage.length > 1){
			mode = mode || 1;
			var errCount = this.ErrorItem.length;
			switch(mode){
			case -1 :
				return false;
				break;
			case 2 :
				ErrorItems = this.ErrorItem;
				for(var i=1;i<errCount;i++)
				{
					//oriColor = this.ErrorItem[i].style.color;
					this.ErrorItem[i].style.color = "red";
					//eval("var itemObj"+i+" ;");
					//eval("itemObj"+i+" = this.ErrorItem[i] ;") ;
				
					eval('this.ErrorItem[i].attachEvent( "onkeydown",  function(){Validator_turnBlack(ErrorItems,'+i+');});');  
					eval('this.ErrorItem[i].attachEvent( "onchange",  function(){Validator_turnBlack(ErrorItems,'+i+');});');  
				}
			case 1 :
				alert(this.ErrorMessage.join("\n"));
				this.ErrorItem[1].focus();
				break;
			case 3 :
				for(var i=1;i<errCount;i++){
				try{
					var span = document.createElement("SPAN");
					span.id = "__ErrorMessagePanel";
					span.style.color = "red";
					this.ErrorItem[i].parentNode.appendChild(span);
					span.innerHTML = this.ErrorMessage[i].replace(/\d+:/,"*");
					}
					catch(e){alert(e.description);}
				}
				this.ErrorItem[1].focus();
				break;
			default :
				alert(this.ErrorMessage.join("\n"));
				break;
			}
			return false;
		}
		return true;
	},
	turnBlack : function(i){
		this.ErrorItem[i].style.color='black';
	},
	limit : function(len, min, max){
		min = min || 0;
		max = max || Number.MAX_VALUE;
		return min <= len && len <= max;
	},
	LenB : function(str){
		return str.replace(/[^\x00-\xff]/g,"**").length;
	},
	LimitES : function( len,min, max , escape, value ){
		min = min || 0;
		max = max || Number.MAX_VALUE;
		var esp = new RegExp(escape,"g").test(value);
		return min <= len && len <= max && esp ;		
	},
	ClearState : function(elem){
		with(elem){
			if(style.color == "red")
				style.color = "";
			var lastNode = parentNode.childNodes[parentNode.childNodes.length-1];
			if(lastNode.id == "__ErrorMessagePanel")
				parentNode.removeChild(lastNode);
		}
	},
	AddError : function(index, str){
		this.ErrorItem[this.ErrorItem.length] = this.ErrorItem[0].elements[index];
		this.ErrorMessage[this.ErrorMessage.length] = this.ErrorMessage.length + "." + str;
	},
	Exec : function(op, reg, mod){
		if( !mod )
		{
			mod = "g" ;
		}
		return new RegExp(reg,mod).test(op);
	},
	compare : function(op1,operator,op2){
		switch (operator) {
			case "NotEqual":
				return (op1 != op2);
			case "GreaterThan":
				return (op1 > op2);
			case "GreaterThanEqual":
				return (op1 >= op2);
			case "LessThan":
				return (op1 < op2);
			case "LessThanEqual":
				return (op1 <= op2);
			default:
				return (op1 == op2);            
		}
	},
	MustChecked : function(name, min, max){
		var groups = document.getElementsByName(name);
		var hasChecked = 0;
		min = min || 1;
		max = max || groups.length;
		for(var i=groups.length-1;i>=0;i--)
			if(groups[i].checked) hasChecked++;
		return min <= hasChecked && hasChecked <= max;
	},
	IsDate : function(op, formatString){
		formatString = formatString || "ymd";
		var m, year, month, day;
		switch(formatString){
			case "ymd" :
				m = op.match(new RegExp("^((\\d{4})|(\\d{2}))([-./])(\\d{1,2})\\4(\\d{1,2})$"));
				if(m == null ) return false;
				day = m[6];
				month = m[5]--;
				year =  (m[2].length == 4) ? m[2] : GetFullYear(parseInt(m[3], 10));
				break;
			case "dmy" :
				m = op.match(new RegExp("^(\\d{1,2})([-./])(\\d{1,2})\\2((\\d{4})|(\\d{2}))$"));
				if(m == null ) return false;
				day = m[1];
				month = m[3]--;
				year = (m[5].length == 4) ? m[5] : GetFullYear(parseInt(m[6], 10));
				break;
			default :
				break;
		}
		if(!parseInt(month)) return false;
		month = month==12 ?0:month;
		var date = new Date(year, month, day);
        return (typeof(date) == "object" && year == date.getFullYear() && month == date.getMonth() && day == date.getDate());
		function GetFullYear(y){return ((y<30 ? "20" : "19") + y)|0;}
	},

	IsValid : function(str){
		if ((str>=0)&&(str<=5000))
		{
			return true;
		}
		return false;
	},
	IsValidRange : function(str, min, max){
		var patten = /^\d+$/  ;
		if (patten.exec(str))
		{
			if(min&&min > str)
			{
				return false ; 
			}
			if(max&&max < str)
			{
				return false ;
			}
			return true ;
		}
		else
		{
			return false ; 
		}
	},
	
	IsUsername : function(str, maxlen){
		var reg = '^[^\x20-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E]+$';
		var length = str.replace(/[^\x00-\xff]/g,"**").length;
		if ((!str.match(reg))||(length>maxlen))
		{
			return false ;
		}
		else 
		{
			return true ;
		}
	},
	IsUnSpecialChars : function(str)
	{
		var patten = /[-_\~!@#\$%\^&\*\(\)\[\]\{\}<>\?\\\/\'\"`]/ ;
		if (!patten.exec(str) && str!=='')
		{
			return true ;
		}
		else
		{
			return false ; 
		}
	},

	IsDisabled : function(obj)
	{
		if(obj.disabled)
		{
		   return true;
		}
		 if(obj.parentElement)
		 {
				   return this.IsDisabled(obj.parentElement)
		 }else
		 {
				   return false;
		 }
	},
	GetError : function()
	{
		this.ErrorMessage[0] = null;
		return "false:"+this.ErrorMessage.join('\n');
	}
 }

function SHDiv(divobj,linkobj,ifrId){
	eval('ifrobj = '+ifrId+';');
	if(divobj.style.display=="none"){
		divobj.style.display="block";
		linkobj.innerHTML = "-";
		
		if(ifrobj!=null)
		{
			if(ifrobj.document.body!=null)
			{
				sh = eval(ifrId+'.document.body.scrollHeight');
				document.getElementById(ifrId).style.height = eval(ifrId+'.document.body.scrollHeight');
			}
			if(document.getElementById(ifrId).src=="")
			{
				document.getElementById(ifrId).src=document.getElementById(ifrId).getAttribute('href');
			}
		}
	}
	else if(divobj.style.display=="block"){
		divobj.style.display="none";
		linkobj.innerHTML = "+";
	}
}