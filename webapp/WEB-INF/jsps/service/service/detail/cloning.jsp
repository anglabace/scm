<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}show_tag.js"></script>

<script language="javascript" type="text/javascript" src="${global_js_url}jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>

<script language="javascript" type="text/javascript" src="${global_js_url}expland.js"></script>


<!--greybox -->
    <script type="text/javascript">

var GB_ROOT_DIR = "./greybox/";

function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
</script>

<script type="text/javascript" src="greybox/AJS.js"></script>
    <script type="text/javascript" src="greybox/AJS_fx.js"></script>
    <script type="text/javascript" src="greybox/gb_scripts.js"></script>
    <link href="greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
  <script>
  function Target1(){
  document.getElementById("Target_same").style.display="none";
document.getElementById("Target_other").style.display="block";  
}
  function Target2(){
  document.getElementById("Target_same").style.display="block";
document.getElementById("Target_other").style.display="none";  
}
function usage1(){
document.getElementById("usage").style.display="block";  
document.getElementById("promo").style.display="none";
}
function usage2(){
document.getElementById("usage").style.display="none";  
document.getElementById("promo").style.display="block";
}
function usage3(){
document.getElementById("usage").style.display="none";  
document.getElementById("promo").style.display="block";
}
function usage4(){
document.getElementById("usage").style.display="none";  
document.getElementById("promo").style.display="block";
}function usage5(){
document.getElementById("usage").style.display="none";  
document.getElementById("promo").style.display="block";
}
function usage6(){
document.getElementById("usage").style.display="none";  
document.getElementById("promo").style.display="block";
}
function usage7(){
document.getElementById("usage").style.display="none";  
document.getElementById("promo").style.display="block";
}

function ORFone(){
document.getElementById("ORFonea").style.display="block";
document.getElementById("ORFoneb").style.display="none";

}

function ORFtwo(){
document.getElementById("ORFtwoa").style.display="block";
document.getElementById("ORFtwob").style.display="none";

}
function ORFoneB(){
document.getElementById("ORFoneb").style.display="block";
document.getElementById("ORFonea").style.display="none";
}

function ORFtwoB(){
document.getElementById("ORFtwob").style.display="block";
document.getElementById("ORFtwoa").style.display="none";
}

function showa()
{   
  // var s=document.cookie.split(";");
 // alert(s[1].replace(/(^\s*)|(\s*$)/g, ""));
  // if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='gene')
  // {
     showTab('dhtmlgoodies_tabView1', 2);
  // }
  // else
  // {
	 // showTab('dhtmlgoodies_tabView1', 5);
  // }
}





</script> 

<style type="text/css">
<!--
body {
	width:930px;
}
#text {margin:50px auto; width:500px}
.hotspot {color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer}
#tt {position:absolute; display:block; background:url(images/tt_left.gif) top left no-repeat}
#tttop {display:block; height:5px; margin-left:5px;  overflow:hidden}
#ttcont {display:block; padding:2px 12px 3px 7px; margin-left:5px; background:#666; color:#FFF}
#ttbot {display:block; height:5px; margin-left:5px;  overflow:hidden}
.orfone {
	margin: 0px;
	height: auto;
	display:block;
	padding-top: 0px;
	padding-right: 0px;
	padding-bottom: 0px;
	padding-left: 0px;
}
.orftwo {
	margin: 0px;
	height: auto;
	display:none;
	padding: 0px;
}

-->
</style>



<style type="text/css">
<!--
.hidlayer {
	font-size: 12px;
	height: 370px;
	width: 666px;
	position: absolute;
	z-index: 9999;
	left: 20%;
	top: 20%;
	display:none;
}
-->
</style>

</head>

<body>

<div id="dhtmlgoodies_tabView1" >
     <div class="dhtmlgoodies_aTab">
       <table width="100%"  border="0" cellpadding="0" cellspacing="0" class="General_table">
         <tr>
           <th width="115"><span class="important">*</span>Gene Name: </th>
           <td width="458" ><input name="textfield" type="text" class="NFText" size="25" />           </td>
           <th width="72">&nbsp;</th>
           <td width="144">&nbsp;</td>
           <td width="117">&nbsp;</td>
         </tr>
         <tr>
           <th>5' Sequence:</th>
           <td ><input name="textfield2" type="text" class="NFText" size="25" />
           <input name="Submit2" type="submit" class="style_botton2" value="Search Enzyme" /></td>
           <th>3' Sequence:</th>
           <td><input name="textfield22" type="text" class="NFText" size="25" /></td>
           <td><input name="Submit22" type="submit" class="style_botton2" value="Search Enzyme" /></td>
         </tr>
         <tr>
           <th><span class="important">*</span>Sequence:</th>
           <td><input name="Sequence1" type="radio" value="radiobutton" checked="checked" />
             DNA
             <input name="Sequence1" type="radio" value="radiobutton" />
             Protein
             <input name="Sequence1" type="radio" value="radiobutton" />
             Length<br/>
             <textarea name="textarea" class="content_textarea2"></textarea></td>
           <th>&nbsp;</th>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th>Codon Optimization </th>
           <td><input type="radio" name="radiobutton11" value="radiobutton" onclick="showTab('dhtmlgoodies_tabView1', 1, 'Others');"/>
             Yes
             <input name="radiobutton11" type="radio" value="radiobutton" checked="checked" />
             No</td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th rowspan="3">Cloning Strategy </th>
           <td><input name="radiobutton1" type="radio" value="radiobutton" checked="checked" />
             <input name="textfield10" type="text" class="NFText" value="pUC57" size="20" />
Cloning site
<input name="textfield5" type="text" class="NFText" size="20" />
             <a href="#"> pUC57 vecter map</a></td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <td colspan="4">(Example:BamHI-Hindlll,<em>Eco</em>R V; Note:If leave it blank,an available site will be chosen to insert the gene by GenScript,usually <em>Eco</em>R V).</td>
         </tr>
         <tr>
           <td colspan="4"><input type="radio" name="radiobutton1" value="radiobutton" onclick="showTab('dhtmlgoodies_tabView1', 2,'Cloning Strategy')"/>
Other</td>
         </tr>
         <tr>
           <th>Plasmid Preparation </th>
           <td><input name="radiobutton12" type="radio" value="radiobutton" checked="checked" />
           Standard delivery:
           <input name="textfield72" type="text" class="NFText" value="4 ug" size="20" />
(Free of charge)</td>
           <td><input type="radio" name="radiobutton12" value="radiobutton"  onclick="showTab('dhtmlgoodies_tabView1', 3, 'Others');"/>
Other</td>
           <td rowspan="2">&nbsp;</td>
           <td rowspan="2">&nbsp;</td>
         </tr>
         <tr>
           <th>Mutagenesis</th>
           <td><input type="checkbox" name="checkbox2" id="checkbox" /></td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <td colspan="5" align="center"><input name="Submit3" type="submit" class="style_botton6" value="Add New Associated Services" onclick="window.parent.openiframe('add_item_1.html',690,425)"/></td>
         </tr>
       </table>
</div>
     <div class="dhtmlgoodies_aTab">
       <table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table">
         <tr>
           <th width="21%"><span class="important">*</span> Host expression organism</th>
           <td colspan="2"><select name="select3">
               <option>Escherichia coli</option>
             </select>           </td>
           <th width="21%">Secondary expression organism</th>
           <td width="29%"><select name="select3">
           </select>
          <!--<span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:50px;">
		 <a href="codon_opt_help.html" title="Help" rel="gb_page_center[250,120]">
		<img src="images/qa.jpg" width="12" height="12" />
		 </a>
		 </span></td>-->
		 <span style="color:#900; padding-bottom:1px; border-bottom:1px dotted #900; cursor:pointer;margin-top:50px;" title="(If the secondary expression host is not closely related to the primary host,the optimization may result in a sequence with compromised codon usage index for both hosts. Selecting only the primary host will generate the most optimum sequence expression organism.) "> <img src="images/qa.jpg" width="12" height="12"/></span></td>
         </tr>
         
         <tr>
           <th>Optimization start position </th>
           <td colspan="2"><input name="textfield3" type="text" class="NFText" size="10" />
               <select name="select3" style="width:40px">
                 <option>bp</option>
             </select></td>
           <th>end position </th>
           <td><input name="textfield33" type="text" class="NFText" size="10" />
               <select name="select4" style="width:40px">
                 <option>bp</option>
             </select></td>
         </tr>
         <tr>
           <th>ORF start position </th>
           <td colspan="2"><input name="textfield32" type="text" class="NFText" size="10" />
             <select name="select7" style="width:40px">
               <option>bp</option>
             </select></td>
           <th>end position </th>
           <td><input name="textfield322" type="text" class="NFText" size="10" />
             <select name="select9" style="width:40px">
               <option>bp</option>
             </select></td>
         </tr>
         <tr>
           <th>Restriction sites to avoid </th>
           <td colspan="4"><input name="textfield4" type="text" class="NFText" size="37" />
             (example:BamHI,Hindlll(AAGCTT)) </td>
         </tr>
         <tr>
           <th>Restriction sites to keep </th>
           <td colspan="4"><input name="textfield42" type="text" class="NFText" size="37" />
             (example:BamHI,Hindlll(AAGCTT))</td>
         </tr>
         <tr>
           <th rowspan="2" valign="top">Stop codon needed </th>
           <td width="9%"><input name="radiobutton2" type="radio" value="radiobutton" checked="checked" />
             Yes</td>
           <td width="20%">Sequence:
             <input name="textfield422" type="text" class="NFText" size="15" />           </td>
           <td colspan="2" valign="top">&nbsp;</td>
         </tr>
         <tr>
           <td><input type="radio" name="radiobutton2" value="radiobutton" />
           No</td>
           <td>&nbsp;</td>
           <td colspan="2" valign="top">&nbsp;</td>
         </tr>
         <tr>
           <th>Comments</th>
           <td colspan="4"><input name="textfield423" type="text" class="NFText" size="100" /></td>
         </tr>
       </table>
     </div>
	 <div class="dhtmlgoodies_aTab">13</div>
     <div class="dhtmlgoodies_aTab">
       <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
         <tr>
           <th width="150"><span class="important">*</span> Insert name</th>
           <td colspan="3"><input name="textfield7" type="text" class="NFText" size="20" /></td>
         </tr>         
         <tr>
           <th><span class="important">*</span> Insert sequence</th>
           <td><textarea name="textarea5" class="content_textarea2" style="WIDTH:333px;"></textarea></td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th><span class="important">*</span> Vector name</th>
           <td><select name="select11">
           </select>           </td>
           <th><span class="important">*</span> Vector size(kb)</th>
           <td><input name="Input" type="text" class="NFText"  onkeypress="if ((event.keyCode < 48 || event.keyCode > 57) && event.keyCode != 45 && event.keyCode != 46) return event.returnValue = false;"/></td>
         </tr>
         <tr>
           <th><span class="important">*</span> Resistance</th>
           <td><select name=""></select></td>
           <th><span class="important">*</span>Copy number</th>
           <td><input name="rad_1" type="radio" value="" checked="checked" />
             High　
               <input name="rad_1" type="radio" value="" />
               Low</td>
         </tr>
         <tr>
          <th>Vector sequence</th>
           <td><textarea name="textarea6" class="content_textarea2" style="WIDTH:333px;"></textarea></td>
           <th>Vector map</th>
           <td>
             <label><input type="file" name="file2" /></label><input name="Submit232" type="submit" class="style_botton" value="Upload" />           </td>
         </tr>
       </table>
     </div>
	 <div class="dhtmlgoodies_aTab">
	   <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
         <tr>
           <th width="150">Target Insert Name</th>
           <td><input name="textfield9" type="text" class="NFText" size="20" />
(if different from the template insert name) </td>
         </tr>
		 <tr>
          <th><span class="important">*</span> Cloning method</th>
           <td colspan="3"><input name="" type="radio" value="" checked="checked" /> 
             Cloning site :
               <input name="textfield7" type="text" class="NFText" size="45" /> 
             (Example:BamHI-HindIII)
           <input name="" type="radio" value="" />Gateway <sup>TM</sup></td>
         </tr>
         <tr>
           <th rowspan="2"><span class="important">*</span>Target Insert Sequence</th>
           <td><input type="checkbox" name="checkbox" value="checkbox" />
Same as template insert</td>
         </tr>
         <tr>
           <td><textarea name="textarea5" class="content_textarea2" style="WIDTH:330px;"></textarea></td>
         </tr>
         <tr>
           <th><span class="important">*</span>Target vector</th>
           <td><input name="Target" type="radio" value="radiobutton" checked="checked" onclick="Target1()"/>
Same as template 
  <br />
<span class="important">*</span>Clone method
<input name="radiobutton" type="radio" value="radiobutton" checked="checked" />
Cloning site:
<input name="textfield222" type="text" class="NFText" size="10" />
(Example:BamHI-HindIII) 
<input type="radio" name="radiobutton" value="radiobutton" />
Gateway<sup>TM
</sup><br />
<input type="radio" name="Target" value="radiobutton" onclick="showTab('dhtmlgoodies_tabView1', 2, 'Others');"/>
Other
<div  id="div" style="display:none;margin-left:30px;">
  <table width="100%"  border="0" cellpadding="0" cellspacing="0" class="General_table">
    <tr> </tr>
  </table>
</div></td>
         </tr>
         <tr>
           <th>Plasmid Preparation </th>
           <td><input name="radiobutton4" type="radio" value="radiobutton" checked="checked" />
Standard delivery:
  <input name="textfield722" type="text" class="NFText" value="4 ug" size="20" />
  (Free of charge)<br />
<input type="radio" name="radiobutton" value="radiobutton" onclick="showTab('dhtmlgoodies_tabView1', 3, 'Others');"/>
Other</td>
         </tr>
       </table>
  </div>
     
	 <div class="dhtmlgoodies_aTab">
	   <table width="850"  border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin-left:50px; align:center">
	   <tr></tr>
       <tr>
         <th width="68">Vector map</th>
         <td width="782"><select name="select12">
           <option>Escherichia coli</option>
         </select>
           <input name="textfield232" type="text" class="NFText" size="25" />
           
           <input name="Submit4" type="submit" class="style_botton" value="Go" onclick="window.parent.openiframe('blast_search.html','756','415')"/>
           <a href="#">Blast Search </a></td>
       </tr>
       <tr>
         <td colspan="2">&nbsp;</td>
       </tr>
       <tr>
         <td colspan="2"><hr /></td>
       </tr>
       <tr>
         <td colspan="2">&nbsp;</td>
       </tr>
       <!--<tr>
           <td>&nbsp;</td>
           <td>
             <input name="Submit3" type="submit" class="style_botton3" value="Select Cloning Strategy" />
             <input name="Submit32" type="submit" class="style_botton3" value="Select Pasmid Prepare" />
           </td>
         </tr>-->
       <tr>
         <td  colspan="2" align="left"><div class="orfone">GN011278.MSC.NM_005098($776.00) 　 <a href="javascript:void(0)" onclick='ORFone()'>Cloning Strategy</a>　　<a href="javascript:void(0)" onclick='ORFoneB()'>Plasmid Preparation </a></div>
         </td>
       </tr>
       <tr>
         <td height="24" colspan="2"><div class="orftwo" id="ORFonea" style="display:none;">
             <input name="cloning1" type="radio" value="radiobutton" checked="checked"/>
             pDream 2.1 
             
             <input name="cloning1" type="radio" value="radiobutton"/>
             Other </div>
           <div class="orftwo" id="ORFoneb" style="display:none;">
             <input name="prep1" type="radio" value="radiobutton" checked="checked"/>
             Standard delivery: 4 ug (Free of charge)             　
             <input name="prep1" type="radio" value="radiobutton"/>
             Other</div></td>
       </tr>
     <tr>
           <td height="141" colspan="2" align="center" valign="bottom"><input name="Submit" type="submit" class="style_botton6" value="Add New Associated Services" onclick="window.parent.openwin2()"/></td>
         </tr>
     
       </table>
</div>
     
     
     <div class="dhtmlgoodies_aTab">
       <table width="100%"  border="0" cellpadding="0" cellspacing="0" class="General_table">

         <tr>
           <th width="150"><span class="important">*</span>Vector name </th>
           <td width="350"><select name="select10">
           
           <option value="" selected="selected"></option>
	<option value="pBC-SK+">pBC-SK+</option>
	<option value="pBluescript II KS+">pBluescript II KS+</option>
	<option value="pCDNA3.1(+)">pCDNA3.1(+)</option> 
	<option value="pcDNA-DEST40">pcDNA-DEST40</option>
	<option value="pCEP4">pCEP4</option>
	<option value="pCMV-Myc">pCMV-Myc</option>
	<option value="pCR2.1">pCR2.1</option>
	<option value="pDEST8">pDEST8</option>
	<option value="pDONR221">pDONR221</option>	
	<option value="pDream 2.1">pDream 2.1</option>
	<option value="pDsRed2-1">pDsRed2-1</option> 
	<option value="pEcoli-Cterm 6xHN">pEcoli-Cterm 6xHN</option>
	<option value="pEGFP-C3">pEGFP-C3</option>
	<option value="pEGFP-N1">pEGFP-N1</option>
	<option value="pET-11a">pET-11a</option>
	<option value="pET-11b">pET-11b</option>
	<option value="pET-11d">pET-11d</option>
	<option value="pET-12a">pET-12a</option>
	<option value="pET-14b">pET-14b</option>
	<option value="pET-15b">pET-15b</option>
	<option value="pET-16b">pET-16b</option>  
	<option value="pET-17b">pET-17b</option>
	<option value="pET-20b(+)">pET-20b(+)</option>
	<option value="pET-21a(+)">pET-21a(+)</option>
	<option value="pET-21b(+)">pET-21b(+)</option>
	<option value="pET-21d(+)">pET-21d(+)</option>
	<option value="pET-22b(+)">pET-22b(+)</option>
	<option value="pET-23a(+)">pET-23a(+)</option>
	<option value="pET-23d(+)">pET-23d(+)</option>
	<option value="pET-24a(+)">pET-24a(+)</option>
	<option value="pET-24b(+)">pET-24b(+)</option>
	<option value="pET-24c">pET-24c</option>
	<option value="pET-25b(+)">pET-25b(+)</option>
	<option value="pET-26b+">pET-26b+</option>
	<option value="pET-27b(+)">pET-27b(+)</option>
	<option value="pET-28a(+)">pET-28a(+)</option>
	<option value="pET-28b(+)">pET-28b(+)</option>
	<option value="pET-29b+">pET-29b+</option>
	<option value="pET-29a(+)">pET-29a(+)</option>
	<option value="pET-3a">pET-3a</option> 
	<option value="PET-30a(+)">PET-30a(+)</option>
	<option value="pET-30b(+)">pET-30b(+)</option>
	<option value="pET-32a(+)">pET-32a(+)</option>
	<option value="pET-32b+">pET-32b+</option>
	<option value="pET-39b">pET-39b</option>
	<option value="pET-3c">pET-3c</option>
	<option value="pET-3d">pET-3d</option>
	<option value="pET-41a(+)">pET-41a(+)</option>
	<option value="pET-41b(+)">pET-41b(+)</option>
	<option value="pET-43.1a(+)">pET-43.1a(+)</option>
	<option value="pET-44b(+)">pET-44b(+)</option>
	<option value="pET-45b+">pET-45b+</option>
	<option value="pET-49b+">pET-49b+</option>
	<option value="pET-52b">pET-52b</option>
	<option value="pET-9a">pET-9a</option>	
	<option value="pET-9d">pET-9d</option>
 <option value="pFastBac1">pFastBac1</option> 
	<option value="pFASTBAC-HT-A">pFASTBAC-HT-A</option> 
	<option value="pFASTBAC-HT-B">pFASTBAC-HT-B</option> 
	<option value="pFASTBAC-HT-C">pFASTBAC-HT-C</option> 
	<option value="pIRES2-EGFP">pIRES2-EGFP</option> 
	<option value="pPIC3.5K">pPIC3.5K</option> 
	<option value="pUC18">pUC18</option>
	<option value="pUC19">pUC19</option>
	<option value="other">Other</option>

           
           </select>           </td>
           <th width="100"><span class="important">*</span>Vector size(kb) </th>
           <td><input name="textfield8" type="text" class="NFText" size="20"  onkeypress="if ((event.keyCode < 48 || event.keyCode > 57) && event.keyCode != 45 && event.keyCode != 46) event.returnValue = false;"/></td>
         </tr>
         <tr>
           <th><span class="important">*</span>Resistance</th>
           <td><select name="select8">
           
           <option value="" selected="selected"></option> 
		<option value="Ampicillin">Ampicillin</option> 
		<option value="Blasticidin">Blasticidin</option>
		<option value="Chloramphenicol">Chloramphenicol</option>
		<option value="Clarithromycin">Clarithromycin</option>
		<option value="Kanamycin">Kanamycin</option>
		<option value="Spectomycin">Spectomycin</option>
		<option value="Tetracyclin">Tetracyclin</option> 
                      <option value="other">Other</option>

           
           </select>           </td>
           <th><span class="important">*</span>Copy number </th>
           <td><input type="radio" name="radiobutton" value="radiobutton" />
             High
             <input name="radiobutton" type="radio" value="radiobutton" checked="checked" />
             Low</td>
         </tr>
         <tr>
           <th>Vector sequence </th>
           <td><textarea name="textarea4" class="content_textarea2" style="WIDTH:330PX;"></textarea></td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th>Vector map </th>
           <td><label><input type="file" name="file3" /></label>
               <input name="Submit23" type="submit" class="style_botton" value="Upload" /></td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th><span class="important">*</span>Cloning method </th>
           <td><input name="radiobutton" id="cloning_site_r1" type="radio" value="radiobutton" checked="checked" />
             Cloneing site 
             
             <input name="textfield23" id="cloning_site" type="text" class="NFText" size="10" />
(Example:BamHI-HindIII)</td>
           <td><input type="radio" name="radiobutton" id="cloning_site_r2" value="radiobutton" />
Gateway<sup>TM</sup></td>
           <td>&nbsp;</td>
         </tr>
       </table>
     </div>
     
	  <div class="dhtmlgoodies_aTab">
       <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table" >
         <tr>
           <th width="229"><span class="important">*</span> Gene name </th>
           <td width="424"><input name="textfield6" id="size" type="text" class="NFText" size="20" /> </td>           
           <td width="70">&nbsp;</td>
           <td width="227">&nbsp;</td>
		 </tr>
         <tr>
           <th><span class="important">*</span>Vector Name </th>
           <td><select name="select">
				</select></td>
           <th><span class="important">*</span>Quantity </th>
           <td><select name="select">
				<option selected="selected">100ug</option>
				<option>50ug</option>
				</select></td>
         </tr>
         <tr>
           <th valign="top"><span class="important">*</span>Insert Sequence (From 5' To 3') </th>
           <td><textarea name="textarea2" id="seq" class="content_textarea2"></textarea></td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th>Comments </th>
           <td><input name="textfield6" id="size" type="text" class="NFText" size="75" /></td>
		   <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>        
       </table>
     </div>	
     
     
     <div class="dhtmlgoodies_aTab">
       <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table" >
         <tr>
           <th><span class="important">*</span> Plasmid name </th>
           <td><input name="size" id="size2" type="text" class="NFText" size="20" /></td>
           <th><span class="important">*</span>Size</th>
           <td><input name="textfield6" id="size" type="text" class="NFText" size="20" />           </td>
         </tr>
         <tr>
           <th><span class="important">*</span>Antibiotic Resistance</th>
           <td><select name="select6" id="resistance">
           </select></td>
           <th><span class="important">*</span>Copy number </th>
           <td><input name="radiobutton" id="copy_num_h" type="radio" value="radiobutton" checked="checked" />
             High
             <input type="radio" name="radiobutton" id="copy_num_l" value="radiobutton" />
             Low</td>
         </tr>
         <tr>
           <th valign="top">Sequence </th>
           <td><textarea name="textarea2" id="seq" class="content_textarea2"></textarea></td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
           <th>Map </th>
           <td colspan="3"><label><input type="file" name="file" id="file"/></label>
             <input name="Submit232" id="map" type="submit" class="style_botton" value="Upload" /></td>
         </tr>
         <tr>
           <th valign="top">Quantity</th>
           <td>
             <select name="select" style="width:50px;">
               <option>100</option>
               <option>200</option>
             </select>
             <select name="select13" style="width:50px;">
               <option>ug</option>
             </select></td>
           <th>Additional analysis </th>
           <td><select name="select2">
           </select></td>
         </tr>
         <tr>
           <th valign="top"><span class="important">*</span>Quality grade </th>
           <td colspan="3">
             <input type="radio" name="radiobutton" value="radiobutton" />
             Research Grade<br/>
               <input type="radio" name="radiobutton" value="radiobutton" />
             SC Grade(Endotoxin level &le;0.03 EU/ug) <br/>
               <input type="radio" name="radiobutton" value="radiobutton" />
               Advanced SC Grade (Endotoxin level &le; 0.005 EU/ug) <br />
             </td>
         </tr>
       </table>
     </div>
     
     
      
	 <div class="dhtmlgoodies_aTab">
       <table width="100%" border="0" cellpadding="0" cellspacing="0">
         <tr>
           <td><input name="radiobutton6" type="radio" value="radiobutton" checked="checked" onclick="usage1()"/>
             Protein expression/analysis<br />
             <div id="usage" style="display:block;margin-left:40px;"><span class="important">*</span>Reading frame
               <input type="radio" name="radiobutton" value="radiobutton" />
               No request
               <input name="radiobutton" type="radio" value="radiobutton" checked="checked" />
               Should be consistent client's requirement </div>
             <div id="promo" style="display:none;height:3px;">&nbsp;</div>
             <input name="radiobutton6" type="radio" value="radiobutton" onclick="usage2()"/>
             Promoter assay <br />
             <input name="radiobutton6" type="radio" value="radiobutton" onclick="usage3()"/>
             RNAi, epigenetics &amp; gene regulation <br />
             <input name="radiobutton6" type="radio" value="radiobutton" onclick="usage4()"/>
             Cloning <br />
             <input name="radiobutton6" type="radio" value="radiobutton" onclick="usage5()"/>
             DNA vaccines <br />
             <input name="radiobutton6" type="radio" value="radiobutton" onclick="usage6()"/>
             Recombinant antibodies <br />
             <input name="radiobutton6" type="radio" value="radiobutton" onclick="usage7()"/>
             Others </td>
         </tr>
       </table>
     </div>
     <div class="dhtmlgoodies_aTab">
       <table width="100%" border="0" cellspacing="0" cellpadding="0" class="General_table">
         <tr>
           <th width="20%">Other Description </th>
           <td width="80%"><input type="text" name="desc" value="" class="NFText"></input></td>
         </tr>
		 <tr>
           <th width="20%">Other Requirements </th>
           <td width="80%"><textarea name="textarea3" class="content_textarea2"></textarea></td>
         </tr>
		 <tr>
           <th width="20%">Service Detail Document </th>
           <td width="80%"><label><input type="file" name="qc_file" id="qc_file" size="55" maxlength="80" />
		   &nbsp;<input type="button" name="upload" value="Upload" class="style_botton"></input></label></td>
         </tr>
       </table>
     </div>
</div>

<script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('Gene Synthesis','Codon Optimization','Mutation Libraries','Template Info','Target Construct','ORF Clones','Cloning Strategy', 'miRNA/siRNA','Plasmid Preparation', 'Gene Usage','More Info'),0,972,305);

var s=document.cookie.split(";");


if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='gene')
{
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('ORF Clones');
	deleteTab('miRNA/siRNA');
	//showTab('dhtmlgoodies_tabView1', 1);
	document.cookie=null;
}
else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='gene1')
{
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('ORF Clones');
	deleteTab('miRNA/siRNA');
	showTab('dhtmlgoodies_tabView1', 2);
	document.cookie=null;
}


else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='gene2')
{
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('ORF Clones');
	deleteTab('miRNA/siRNA');
	showTab('dhtmlgoodies_tabView1', 3);
	document.cookie=null;
}



else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='gene1_ser')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	//deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	deleteTab('More Info');
	//showTab('dhtmlgoodies_tabView1', 2);
	document.cookie=null;
}


else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='cu')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('ORF Clones');
	deleteTab('miRNA/siRNA');
	//showTab('dhtmlgoodies_tabView1', 2);
	document.cookie=null;
}
else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='cu1')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('ORF Clones');
	deleteTab('miRNA/siRNA');
	showTab('dhtmlgoodies_tabView1', 3);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='cu1_ser')
{
    deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	//deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	deleteTab('More Info');
	//showTab('dhtmlgoodies_tabView1', 3);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='cu2')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('ORF Clones');
	deleteTab('miRNA/siRNA');
	showTab('dhtmlgoodies_tabView1', 2);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='cu2_ser')
{
    deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	//deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	deleteTab('More Info');
	//showTab('dhtmlgoodies_tabView1', 3);
	document.cookie=null;
}


else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='Mu')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('ORF Clones');
	deleteTab('miRNA/siRNA');
	//showTab('dhtmlgoodies_tabView1', 2);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='Mu1')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('ORF Clones');
	deleteTab('miRNA/siRNA');
	showTab('dhtmlgoodies_tabView1', 3);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='Mu1_ser')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	//deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	deleteTab('More Info');
	//showTab('dhtmlgoodies_tabView1', 3);
	document.cookie=null;
}


else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='Mu2')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('ORF Clones');
	deleteTab('miRNA/siRNA');
	showTab('dhtmlgoodies_tabView1', 2);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='Mu2_ser')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	//deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	deleteTab('More Info');
	//showTab('dhtmlgoodies_tabView1', 3);
	document.cookie=null;
}


else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='orf')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	//deleteTab('Gene Usage');
	deleteTab('miRNA/siRNA');
	//showTab('dhtmlgoodies_tabView1', 2);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='orf1')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	//deleteTab('Gene Usage');
	deleteTab('miRNA/siRNA');
	showTab('dhtmlgoodies_tabView1', 2);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='orf1_ser')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	//deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	deleteTab('More Info');
	//showTab('dhtmlgoodies_tabView1', 3);
	document.cookie=null;
}


else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='orf2')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	//deleteTab('Gene Usage');
	deleteTab('miRNA/siRNA');
	showTab('dhtmlgoodies_tabView1', 1);
	document.cookie=null;
}


else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='orf2_ser')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	//deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	deleteTab('More Info');
	//showTab('dhtmlgoodies_tabView1', 3);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='mir')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	//deleteTab('Plasmid Preparation');
	
	document.cookie=null;
}
else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='mir1')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	//deleteTab('Plasmid Preparation');
	showTab('dhtmlgoodies_tabView1', 1);
	document.cookie=null;
}
else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='mir1_ser')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	//deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	deleteTab('More Info');
	//showTab('dhtmlgoodies_tabView1', 3);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='mir2')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	deleteTab('Plasmid Preparation');
	showTab('dhtmlgoodies_tabView1', 2);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='mir2_ser')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	//deleteTab('Gene Usage');
	deleteTab('More Info');
	//showTab('dhtmlgoodies_tabView1', 3);
	document.cookie=null;
}


else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='cl')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('ORF Clones');
	deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	//showTab('dhtmlgoodies_tabView1', 2);
	document.cookie=null;
}
else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='cl1')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('ORF Clones');
	deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	showTab('dhtmlgoodies_tabView1', 0);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='cl1_ser')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	//deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	deleteTab('More Info');
	//showTab('dhtmlgoodies_tabView1', 3);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='cl2')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('ORF Clones');
	deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	showTab('dhtmlgoodies_tabView1', 1);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='cl2_ser')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	//deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	deleteTab('More Info');
	//showTab('dhtmlgoodies_tabView1', 3);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='pl')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	deleteTab('miRNA/siRNA');
	//showTab('dhtmlgoodies_tabView1', 1);
	document.cookie=null;
}
else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='pl1')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	deleteTab('miRNA/siRNA');
	//showTab('dhtmlgoodies_tabView1', 1);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='pl1_ser')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	//deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	deleteTab('More Info');
	//showTab('dhtmlgoodies_tabView1', 3);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='pl2')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	deleteTab('miRNA/siRNA');
	//showTab('dhtmlgoodies_tabView1', 1);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='pl2_ser')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	//deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	deleteTab('More Info');
	//showTab('dhtmlgoodies_tabView1', 3);
	document.cookie=null;
}


else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='other')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('Cloning Strategy');
	deleteTab('ORF Clones');
	deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	//deleteTab('More Info');
	//showTab('dhtmlgoodies_tabView1', 3);
	document.cookie=null;
}

else if(s[1].replace(/(^\s*)|(\s*$)/g, "")=='Plasmid')
{
	deleteTab('Gene Synthesis');
	deleteTab('Codon Optimization');
	deleteTab('Mutation Libraries');
	deleteTab('Template Info');
	deleteTab('Target Construct');
	deleteTab('ORF Clones');
	//deleteTab('Plasmid Preparation');
	deleteTab('miRNA/siRNA');
	deleteTab('Gene Usage');
	deleteTab('Cloning Strategy');
	//showTab('dhtmlgoodies_tabView1', 2);
	document.cookie=null;
}


</script>
</body>
</html>
