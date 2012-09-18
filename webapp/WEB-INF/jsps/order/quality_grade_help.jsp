<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />



<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
 <script language="javascript">
 $(function(){	
		$( '#closebutton' ).click(
			function()
			{
				parent.window.$( '#Quality_grade_help_dlg' ).dialog( 'close' ) ;
			}
		) ;
	}) ;
   function a(obj){
     var _selectedvalue = obj.value;
  if(_selectedvalue==''){
     _selectedvalue = '-1';
  }

  for(i=1;i<=5;i++){
    var divobj = document.getElementById('tb'+i);
    if(parseInt(_selectedvalue)==i){
       divobj.style.display = 'block';
    }else{
       divobj.style.display = 'none';
    }
  }
  
  if(document.getElementById("selecttb").value==3||document.getElementById("selecttb").value==4)
  {
	document.getElementById("cond2").style.display="block";
	document.getElementById("cond1").style.display="none";
  }
  else
  {
   document.getElementById("cond2").style.display="none";
	document.getElementById("cond1").style.display="block"; 
   }
  
   }
  function change_a()
  {
	document.getElementById("select_value").options.length = 0;   
	
  if(document.getElementById("select_look").value=='Category')
  {
    var slt=document.getElementById("select_value");
   var objOption=document.createElement("option");
   objOption.value='0';
   objOption.text='Gene';
   slt.options.add(objOption);
  }
  else
  {
   document.getElementById("select_value").options.length = 0;   
   }

   
   
	
  
   
    
  }
  
  
    function change_b()
  {
	   document.getElementById("select_value").options.length = 0;   
	  	  
     if(document.getElementById("select_lookb").value=='Category')
    { 
   
     var slt=document.getElementById("select_value");
     var objOption=document.createElement("option");
     objOption.value='0';
     objOption.text='Gene1';
    slt.options.add(objOption);   
   }
   else
   {
	   document.getElementById("select_value").options.length = 0;   
   }


  }

 
 </script>

<script>
function changetitle()
{

  var hh=document.getElementsByTagName('td');
  for (i=0;i<hh.length;i++)
  {
     if (hh[i].className=="caption")
	 {
	   	 hh[i].innerHTML="Related Item to Mouse Anti-Human";
		 
		document.frames[0].location.replace("item_select.html"); 
	 }
  }

}
</script>


</head>
<body>

   <table  width="100%" border="0" cellspacing="0" cellpadding="0" >
     <tr>
       <td><table width="630" border="0" cellspacing="0" cellpadding="0">
         <tr>
             <td colspan="4" style="padding-top:10px;"><table width="700" border="0" cellpadding="0" cellspacing="0" class="list_table">
            
           
               <tr>
                 <th width="70">QC Items</th>
                 <th width="141"> Research Grade</th>
                 <th width="67">SC Grade</th>
                 <th width="71">Advanced SC Grade</th>
                 
               </tr>
             </table></td>
           </tr>
           <tr>
             <td colspan="4"><div  style="width:717px; height:210px; overflow:scroll;">
               <table width="700" border="0" cellpadding="0" cellspacing="0" class="list_table">
                 <tr>
                   
                   <td  width="70">&nbsp;</td>
                   <td width="141">Predominantly Supercoiled </td>
                   <td width="66">≥95% Supercoilded, ≤0.03 EU/ug Endotoxin</td>
                   <td width="70">≥95% Supercoilded, ≤0.005 EU/ug Endotoxin</td>
                   
                 </tr>
                 <tr>
                  
                   <td class="list_td2">Appearance</td>
                   <td class="list_td2"><input name="input" type="checkbox" value="" checked="checked" disabled="disabled"/></td>
                   <td class="list_td2"><input name="input9" type="checkbox" value="" checked="checked" disabled="disabled"/></td>
                   <td class="list_td2"><input name="input14" type="checkbox" value="" checked="checked" disabled="disabled"/></td>
                  
                 </tr>
                 <tr>
                   
                   <td>A260/280</td>
                   <td><span class="list_td2">
                     <input name="input2" type="checkbox" value="" checked="checked" disabled="disabled"/>
                   </span></td>
                   <td><span class="list_td2">
                     <input name="input10" type="checkbox" value="" checked="checked" disabled="disabled"/>
                   </span></td>
                   <td><span class="list_td2">
                     <input name="input15" type="checkbox" value="" checked="checked" disabled="disabled"/>
                   </span></td>
                  
                 </tr>
                 <tr>
                   
                   <td class="list_td2">Supercoiled content</td>
                   <td class="list_td2"><input name="input3" type="checkbox" value="" checked="checked" disabled="disabled"/></td>
                   <td class="list_td2"><input name="input11" type="checkbox" value="" checked="checked" disabled="disabled"/></td>
                   <td class="list_td2"><input name="input16" type="checkbox" value="" checked="checked" disabled="disabled"/></td>
                  
                 </tr>
                 <tr>
                  
                   <td>Residual RNA</td>
                   <td><span class="list_td2">
                     <input name="input4" type="checkbox" value="" checked="checked" disabled="disabled"/>
                   </span></td>
                   <td><span class="list_td2">
                     <input name="input12" type="checkbox" value="" checked="checked" disabled="disabled"/>
                   </span></td>
                   <td><span class="list_td2">
                     <input name="input17" type="checkbox" value="" checked="checked" disabled="disabled"/>
                   </span></td>
                   
                 </tr>
                 <tr>
                   
                   <td class="list_td2">Genomic DNA</td>
                   <td class="list_td2"><input name="input5" type="checkbox" value="" checked="checked" disabled="disabled"/></td>
                   <td class="list_td2"><input name="input13" type="checkbox" value="" checked="checked" disabled="disabled"/></td>
                   <td class="list_td2"><input name="input18" type="checkbox" value="" checked="checked" disabled="disabled"/></td>
                   
                 </tr>
                 <tr>
                   
                   <td>Concentrations</td>
                   <td><span class="list_td2">
                     <input name="input6" type="checkbox" value="" checked="checked" disabled="disabled"/>
                   </span></td>
                   <td><span class="list_td2">
                     <input name="input24" type="checkbox" value="" checked="checked" disabled="disabled"/>
                   </span></td>
                   <td><span class="list_td2">
                     <input name="input19" type="checkbox" value="" checked="checked" disabled="disabled"/>
                   </span></td>
                   
                 </tr>
                 <tr>
                   
                   <td class="list_td2">Restriction Analysis*</td>
                   <td class="list_td2"><input name="input7" type="checkbox" value="" checked="checked" disabled="disabled"/></td>
                   <td class="list_td2"><input name="input23" type="checkbox" value="" checked="checked" disabled="disabled"/></td>
                   <td class="list_td2"><input name="input20" type="checkbox" value="" checked="checked" disabled="disabled"/></td>
                   
                 </tr>
                 <tr>
                   
                   <td>Endotoxin Assay(LAL test)</td>
                   <td><span class="list_td2">
                     <input name="input8" type="checkbox" value="" checked="checked" disabled="disabled"/>
                   </span></td>
                   <td><span class="list_td2">
                     <input name="input22" type="checkbox" value="" checked="checked" disabled="disabled"/>
                   </span></td>
                   <td><span class="list_td2">
                     <input name="input21" type="checkbox" value="" checked="checked" disabled="disabled"/>
                   </span></td>
                  
                 </tr>
                 
                 
               </table>
             </div></td>
           </tr>
           
             </table></td>
           </tr>
           <tr>
             <td colspan="4" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <td width="4%" height="40">&nbsp;</td>
                 <td width="96%">*Please enter 1-2 unique sites for QC purpose,additional charge may apply for rare enzymes</td>
               </tr>
             </table></td>
           </tr>
           <tr>
             <td height="40" colspan="4"><div align="center">
               <!-- <input name="Submit" type="submit" class="style_botton"  value="Select" onclick="window.location.href='item_select_1_new.html'"/> -->
               <input type="submit" name="Submit2" id = "closebutton" value="Close" class="style_botton"  />
             </div></td>
           </tr>
         </table>
</body>
</html>
