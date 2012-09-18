<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
   <form id="moreInfoForm" name="moreInfoForm">
    <input id="itemId" type="hidden" name="itemId" value="${itemId}" />
       <table width="100%" border="0" cellspacing="0" cellpadding="0" class="General_table">
         <tr>
           <th width="20%">Other Description </th>
           <td width="80%">
           		<input type="text" name="otherDescription" value="" class="NFText" class="NFText"/>
           	</td>
         </tr>
		 <tr>
           <th width="20%">Other Requirements </th>
           <td width="80%">
           		<textarea name="otherRequirement" class="content_textarea2"></textarea>
           	</td>
         </tr>
		 <tr>
           <th width="20%">Service Detail Document </th>
           <td width="80%"><label>
           		<input type="file" name="upload" id="qc_file" size="55" maxlength="80" /></label>
		   &nbsp;<input type="button" name="moreInfoUploadBtn" value="Upload" class="style_botton" />
		   </td>
         </tr>
          <tr>
         	<th>&nbsp;</th>
         	<td colspan="1">
	         	<table id="fileListTable" name="fileListTable"></table>
           	</td>
         </tr>
       </table>
   </form>