<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}show_tag.js"></script>



<script>function aCheck1(){

document.getElementById("yes").style.display="block";
document.getElementById("no").style.display="none";  

}

function bCheck2(){

document.getElementById("yes").style.display="none"; 
document.getElementById("no").style.display="block";  
 
} 
function cCheck1(){

document.getElementById("other").style.display="none";
document.getElementById("puc").style.display="block";
}

function dCheck1(){

document.getElementById("other").style.display="block";  
document.getElementById("puc").style.display="none";
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

</script> 

<style type="text/css">
<!--
body {
	width:930px;
}

.hidlayer1 {
	font-size: 12px;
	position: absolute;
	z-index: 9999;
	left: 20%;
	top: 20%;
	display:none;
	height: 200px;
	width: 200px;
}

-->
</style>


</head>

<body>

<div id="dhtmlgoodies_tabView1" >
	 <div class="dhtmlgoodies_aTab">
	
         <table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                  <th width="17%"><span class="important">*</span>Name</th>
                  <td width="31%"><input name="textfield" type="text" class="NFText" size="25" /></td>
                  <th width="23%">Quantity</th>
                  <td width="29%"><select name="select2" style="width:157px">
                    <option>10-14 mg</option>
                  </select></td>
                </tr>
                
                <tr>
                  <th>Purity</th>
                  <td><select name="select3" style="width:157px">
                  </select></td>
                  <th>Sequence Length</th>
                  <td><input name="textfield32" type="text" class="NFText" size="25" /></td>
                </tr>
                <tr>
                  <th>Sequence</th>
                  <td colspan="3"><textarea name="textarea" class="content_textarea2"></textarea>
                    <img src="images/qa.jpg" title='To enter the peptide sequence, please use only a single-letter code or a multiple-letter code specified in the Amino Acid Code Table. If a multiple-letter code is selected, please place braces {} around this code, e.g.AC{GLY}{ORN}{d-GLY}.' width="12" height="12" />
<input name="Submit2" type="submit" class="style_botton" value="Select" onclick="window.parent.openiframe('Sequence_select.html','726','662','5%')"/>
                    <input name="Submit3" type="submit" class="style_botton" value="Validate" /></td>
                </tr>
                <tr>
                  <th>&nbsp;</th>
                  <td colspan="3">Convert all amino acids in the sequence to D-amino acids?
                  <input name="radiobutton2" type="radio" value="radiobutton" />
                  Yes
                  <input type="radio" name="radiobutton2" value="radiobutton" checked="checked" />
No</td>
                </tr>
                <tr>
                  <th valign="top">N-Terminal Modification</th>
                  <td><select name="select" style="width:157px">
                    <option>None</option>
                  </select></td>
                  <th>C-Terminal Modification</th>
                  <td><select name="select4">
                    <option>None</option>
                  </select></td>
                </tr>
                <tr>
                  <th>Other Modifications</th>
                  <td colspan="3"><select name="select10" style="width:157px">
                    <option>Abz</option>
                    <option>Abz/DNP</option>
                    <option>Abz/Tyr (3-NO2)</option>
                    <option>Amide Cyclic</option>
                    <option>BSA Conjugation</option>
                    <option>DABCYL</option>
                    <option>DABCYL/Glu(EDANS)-NH2</option>
                    <option>EDANS/DABCYL</option>
                    <option>Glu(EDANS)-NH2</option>
                    <option>KLH Conjugation</option>
                    <option>MCA/DNP</option>
                    <option>mini-PEG1</option>
                    <option>p-Nitroanilide</option>
                    <option>PEG2000 attached at Cysteine</option>
                    <option>PEG5000 attached at Cysteine</option>
                    <option>SuccinylationTyr (3-NO2)</option>
                    <option>Tyr (3-NO2)</option>
                    
                  </select>
                    <input name="Submit4" type="submit" class="style_botton" value="Add" />
                <input name="Submit5" type="submit" class="style_botton2" value="View/Modify" onclick="window.parent.openiframe('interest_view.html','620','310')"/></td>
                </tr>
                <tr>
                  <th>Disulfide Bridge</th>
                  <td colspan="3"><input name="textfield3" type="text" class="NFText" size="25" />
                    (e.g.,5-12)</td>
                </tr>
                <tr>
                  <th>Comment</th>
                  <td colspan="3"><input name="textfield8" type="text" class="NFText" size="80" /></td>
                </tr>
       </table>
    
</div>
  <div class="dhtmlgoodies_aTab">
    <table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table">
      <tr>
        <th width="17%">Purity</th>
        <td width="31%"><select name="select6" style="width:157px">
          <option value=">75%">>75%</option>
          <option value="Crude">Crude</option>
          <option value="Desalt">Desalt</option>
          <option value=">70%">>70%</option>
          <option value=">80%">>80%</option>
          <option value=">85%">>85%</option>
          <option value=">90%">>90%</option>
          <option value=">95%">>95%</option>
          <option value=">98%">>98%</option>
        </select></td>
        <th width="23%">Quantity</th>
        <td width="29%"><select name="select5" style="width:157px">
          <option>10-14 mg</option>
        </select></td>
      </tr>
      <tr>
        <th>Aliquoting to</th>
        <td><input name="textfield5" type="text" class="NFText" size="25" /></td>
        <th>&nbsp;</th>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <th>Sequence</th>
        <td colspan="3"><textarea name="textarea2" class="content_textarea2"></textarea>
          <img src="images/qa.jpg" title='Each line can contain only one peptide name and one sequence. The maximum 200 peptides can be entered once. The name and sequence must be seperated by a pipe ("|"), e.g., MyPeptide|ACGTGGGTTTCAAAA. Click the "Formatting" button, the default name (e.g., "Peptide#1", "Peptide#2"...) will be added for each sequence in order and the blank spaces will be removed from the sequence.' width="12" height="12" />
          <input name="Submit6" type="submit" class="style_botton" value="Formatting" /></td>
      </tr>
      <tr>
        <th valign="top">N-Terminal Modification</th>
        <td><select name="select5" style="width:157px">
          <option>None</option>
        </select></td>
        <th>C-Terminal Modification</th>
        <td><select name="select5">
          <option>None</option>
        </select></td>
      </tr>
      <tr>
        <th>Other Modifications</th>
        <td colspan="3"><input name="textfield4" type="text" class="NFText" size="25" />
          <input name="Submit6" type="submit" class="style_botton" value="Add" />
          <input name="Submit6" type="submit" class="style_botton2" value="View/Modify" onclick="window.parent.openiframe('interest_view.html','620','310')"/></td>
      </tr>
      <tr>
        <th>Disulfide Bridge</th>
        <td colspan="3"><input name="textfield4" type="text" class="NFText" size="25" />
          (e.g.,5-12)</td>
      </tr>
      <tr>
        <th>Comment</th>
        <td colspan="3"><input name="textfield4" type="text" class="NFText" size="80" /></td>
      </tr>
      <tr>
        <td height="40" colspan="4" align="center" valign="bottom"><input name="Submit" type="submit" class="style_botton" value="Create" /></td>
      </tr>
    </table>
  </div>
  <div class="dhtmlgoodies_aTab">
    <table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table">
            <tr>
        <th width="17%">Catalog No</th>
        <td width="31%"><input name="radiobutton2" type="radio" value="radiobutton" />
          SC1177
            <input type="radio" name="radiobutton2" value="radiobutton" />
            SC1487</td>
        <th width="23%">&nbsp;</th>
        <td width="29%">&nbsp;</td>
      </tr>
      <tr>
        <th width="17%"><span class="important">*</span>Name</th>
        <td width="31%"><input name="textfield6" type="text" class="NFText" size="25" /></td>
        <th width="23%">Quantity</th>
        <td width="29%"><select name="select7" style="width:157px">
          <option>1-4 mg</option>
        </select></td>
      </tr>
      <tr>
        <th>Purity</th>
        <td><select name="select7" style="width:157px">
        </select></td>
        <th>Modification</th>
        <td><input name="textfield6" type="text" class="NFText" size="25" /></td>
      </tr>
      <tr>
        <th>Sequence</th>
        <td colspan="3"><textarea name="textarea4" class="content_textarea2"></textarea>
          <img src="images/qa.jpg" title='Each line can contain only one peptide sequence. The maximum 500 sequences can be entered.' width="12" height="12" /></td>
      </tr>
      <tr>
        <th valign="top">Sequence Document</th>
        <td colspan="3"><label>
          <input type="file" name="fileField" id="fileField" />
        (.xls, .xlsx, .txt, .csv)</label></td>
      </tr>
      <tr>
        <th>Comment</th>
        <td colspan="3"><input name="textfield6" type="text" class="NFText" size="80" /></td>
      </tr>
       <tr>
        <td height="86" colspan="4" align="center" valign="bottom"><input name="Submit" type="submit" class="style_botton" value="Create" /></td>
      </tr>
    </table>
  </div>

<div class="dhtmlgoodies_aTab">
  <table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table">
    <tr>
      <th width="17%"><span class="important">*</span>Name</th>
      <td width="31%"><input name="textfield7" type="text" class="NFText" size="25" /></td>
      <th width="23%">Quantity</th>
      <td width="29%"><input name="textfield9" type="text" class="NFText" size="25" />
        mol</td>
    </tr>
    <tr>
      <th>Purity</th>
      <td><select name="select8" style="width:157px">
      </select></td>
      <th>Modification</th>
      <td><select name="select9" style="width:157px">
</select></td>
    </tr>
        <tr>
      <th>QC Report</th>
      <td><select name="select8" style="width:157px">
      </select></td>
      <th>Synthesis Membrane</th>
      <td><select name="select9" style="width:157px">
</select></td>
    </tr>
            <tr>
      <th>Delivert Format</th>
      <td><select name="select8" style="width:157px"></select></td>
      <th>&nbsp;</th>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <th>Sequence</th>
      <td colspan="3"><textarea name="textarea3" class="content_textarea2"></textarea>
        <img src="images/qa.jpg" title='Each line can contain only one peptide sequence. The maximum 500 sequences can be entered.' width="12" height="12" /></td>
    </tr>
    <tr>
      <th valign="top">Sequence Document</th>
      <td colspan="3"><label>
        <input type="file" name="fileField2" id="fileField2" />
        (.xls, .xlsx, .txt, .csv)</label></td>
    </tr>
    <tr>
      <th>Comment</th>
      <td colspan="3"><input name="textfield7" type="text" class="NFText" size="80" /></td>
    </tr>
     <tr>
        <td height="42" colspan="4" align="center" valign="bottom"><input name="Submit" type="submit" class="style_botton" value="Create" /></td>
      </tr>
  </table>
</div>

</div>

  <script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('Standard Peptide','Batch Order','Peptide Library','Peptide Array'),0,972,305);
</script>
</body>
</html>
