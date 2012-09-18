function openiframe(url,width,height,top,left){

	    if (document.getElementById("frame12"))
		{
			document.getElementById("frame12").style.display="block";			
		}
		else
		{
		  	window.parent.document.getElementById("frame12").style.display="block";			
		}
			
		document.getElementById("hidkuan").src=url;
		document.getElementById("hidkuan").style.width=width+"px";
		document.getElementById("hidkuan").style.height=height+"px";
		if (top==null)
		{
			document.getElementById("frame12").style.top='20%';
		}
		else
		{
			document.getElementById("frame12").style.top=document.documentElement.scrollTop+top+"px";			 		 
		}
		if (left!=null)
		{
			document.getElementById("frame12").style.left=left+"px";
		}
		
		 return false;  
		
	}
	
function openiframel(url,width,height,left){
	    if (document.getElementById("frame12"))
		{
			document.getElementById("frame12").style.display="block";			
		}
		else
		{
		  	window.parent.document.getElementById("frame12").style.display="block";			
		}
			
		document.getElementById("hidkuan").src=url;
		document.getElementById("hidkuan").width=width+"px";
		document.getElementById("hidkuan").height=height+"px";
		if (left==null)
		{
			document.getElementById("frame12").style.left='20%';
		}
		else
		{
		 
		 document.getElementById("frame12").style.left=left+"px";
		}
		
	}
	
function closeiframe()
{
	
	if (document.getElementById("frame12"))
		{
	       document.getElementById("frame12").style.display="none";
		}
		else
		{
			window.parent.document.getElementById("frame12").style.display="none";
		}
}


function delete_reason(url,txt,width,height,top,left){
	    if (document.getElementById("frame12"))
		{
			document.getElementById("frame12").style.display="block";			
		}
		else
		{
		  	window.parent.document.getElementById("frame12").style.display="block";			
		}
			
		document.getElementById("hidkuan").src=url;
		document.getElementById("hidkuan").style.width=width+"px";
		document.getElementById("hidkuan").style.height=height+"px";
		if (top==null)
		{
			document.getElementById("frame12").style.top='20%';
		}
		else
		{
		 document.getElementById("frame12").style.top=top;
		}
		
		
		document.getElementById("hidkuan").document.getElementById("delete_title").innerText=txt;
		document.getElementById("hidkuan").document.getElementById("delete_smalltitle").innerText=txt;
	}
	
function cc()
{}

function moveUp1()
{}
function moveDown1()
{}
function del()
{
   var s=document.getElementsByTagName("input");
   j=0;
   for(i=0;i<s.length;i++)
   {
	  if (s[i].name.indexOf('chk')!=-1)
	  {
		//alert(s[i].name+" "+s[i].checked);
		if(s[i].checked)
		{
		  j=j+1;
		}
	  }
   }
   if(j==0)
   {
	  alert('Please select one at least!') 
   }
   else
   {
	  alert('Are you sure to delete?') 
   }
   //if($([name^='chk']).attr("checked")==0)
   //{
	//   alert('Are you sure to delete?')
   //}
   //else
  // {
   //  alert('Please select one at least!')
  // }
}
function toggleShowMorea(divID){
			
			var oId = document.getElementById(divID);
			var oLink = document.getElementById(divID + 'Item');
			
			divDisplay = oId.style.display;
			if (divDisplay == "none") {
				oId.style.display = "";
				
				oLink.style.background="url(images/arrow1.jpg) no-repeat";
				oLink.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				
				oLink.href = 'javascript:void(0)';
			} else {
				oId.style.display = "none";
				oLink.style.background="url(images/arrow.jpg) no-repeat";
				oLink.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				oLink.href = 'javascript:void(0)';
			}
	}
function SetCookiea(name,value)//两个参数，一个是cookie的名子，一个是值
{
	var Days = 1; //此 cookie 将被保存 30 天
    var exp  = new Date();    //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
function getCookiea(name)//取cookies函数        
{
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
     if(arr != null) return unescape(arr[2]); return null;

}

function openc(str1,str2)
{


	  if (document.getElementById(str1).style.display=="none")
	  {
	    document.getElementById(str2).src="images/ad.gif";
	    document.getElementById(str1).style.display="block";
	  }
	  else
	  {
		  document.getElementById(str2).src="images/ar.gif";
	    document.getElementById(str1).style.display="none";
	  }
  
}
function fileinput(filename,textname)
{
       var textButton="<input type='text' name='"+textname+"' id='"+textname+"' class='NFText'/>  <input type='submit' name='button' id='button' value='Browse...' class='style_botton' />"
       $(textButton).insertBefore("#"+filename);
       $("#"+filename).change(function(){
           //alert($("#"+filename).val());
       $("#"+textname).val($("#"+filename).val());
     });
   }
   
