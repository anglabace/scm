function IsURL(str_url){
	str = /^[a-za-z]+:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/; 
	if (str.test(str_url))
	{
		return true; 
	}else{ 
		return false; 
	}
}

function checkmail(mail){
	str = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
	if(str.test(mail)){
		return true;
	}else{
		return false;
	}
}
function datebj(d1,d2){
	d1Arr=d1.split('-');
	d2Arr=d2.split('-');
	v1=new Date(d1Arr[0],d1Arr[1],d1Arr[2]);
	v2=new Date(d2Arr[0],d2Arr[1],d2Arr[2]);
	return (d1<=d2);
}

function toggleCheck()
{
	var checkObj = arguments[0];
	var checkName = arguments[1];
	if(checkObj.checked)
	{
		$("input[name='"+checkName+"']").each(function(index){
			this.checked="checked";
		});
	}else
	{
		$("input[name='"+checkName+"']").each(function(index){
			this.checked="";
		});
	}
}