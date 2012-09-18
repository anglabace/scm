<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<body>
	<font size="3">Not Received Items :</font><font color="blue"> ${notReceivedItem }</font><br>
	<font size="3">Over Due Items :</font><font color="blue"> ${expiredItem }</font>
	
             <div class="list_box" style="height:100px;background-color:#FFF;width:425px;font-size:12px;font-weight:bold;color:red;">
             Order:
             <s:iterator value="notesMap['orderNotes']" id="note">
                 <a href="javascript:void(0)" onclick="show_edit('${note.noteIds}', 'SHIPMENT')"><font color="red">${note.orderNote}</font></a>;
             </s:iterator>
             <br/>
             Customer:
             <s:iterator value="notesMap['custNote']" id="note">
                 <a href="javascript:void(0)" onclick="show_note_other('${note.id}', 'CUSTOMER')"><font color="red">${note.description}</font></a>;
             </s:iterator>
             <br/>
             Department:
             <s:if test="notesMap['deptNote'] != null">
             <s:iterator value="notesMap['deptNote']" id="note">
                 <a href="javascript:void(0)" onclick="show_note_other('${note[0]}', 'DEPT')"><font color="red">${note[1]}</font></a>;
             </s:iterator>
             </s:if>
             <br/>
             Division:
             <s:if test="notesMap['diviNote'] != null">
             <s:iterator value="notesMap['diviNote']" id="note">
                 <a href="javascript:void(0)" onclick="show_note_other('${note[0]}', 'DIVISION')"><font color="red">${note[1]}</font></a>;
             </s:iterator>
             </s:if>
             <br/>
             Organization:
             <s:if test="notesMap['orgNote'] != null">
             <s:iterator value="notesMap['orgNote']" id="note">
                 <a href="javascript:void(0)" onclick="show_note_other('${note[0]}', 'ORG')"><font color="red">${note[1]}</font></a>;
             </s:iterator>
             </s:if>
             <br/>
         </div>
</body>
</html>