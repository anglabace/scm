<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<form id="customServiceForm">
<input type="hidden" name="itemId" />
	<table class="General_table" width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<th width="11%"><span class="important">*</span>Description</th>
			<td width="44%"><input type="text" id="customDesc" name="customDesc"
				class="NFText" size="34"/></td>
				<th width="18%">&nbsp;</th>
        		<td width="27%">&nbsp;</td>
        <td width="27%">&nbsp;</td>
		</tr>
		<tr>
			<th>Service Detail</th>
			<td><textarea name="comments"
					class="content_textarea2"></textarea>
			</td>
			<td>&nbsp;</td>
              <td>&nbsp;</td>
		</tr>
	</table>
</form>