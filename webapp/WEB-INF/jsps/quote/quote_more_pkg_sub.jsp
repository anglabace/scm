<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<form id="pkgSubForm" name="pkgSubForm" method="post">
	<div class="extend_title">Step Items</div>
	<div class="extend_new">
		<div class="extend_line">
			<div class="extend" style="display:block;">
				<div class="extend_l">
					<a href="javascript:void(0);" onclick="openc('exce','img');" id="exce1Item">
					<img src="images/ad.gif" width="11" height="11" id="img"/></a>
				</div>
				<div class="extend_r">
					<div class="extnd_rt">${quoteItem.catalogNo}: <span class="css_blue_b">${quoteItem.name}</span></div>
				</div>
			</div>
			
			<div id="exce" class="extnd_an" style="display:block;" name="contentPkgSerDiv" catalogNo="${quoteItem.catalogNo}">
				<div class="extnd_ant">
					<span class="css_b">Select Services Detail</span> 
					<select id="subStepList" style="width:250px;" name="stepList">
						<option stepId="" description="" value="">please select</option>
						<s:if test="subSteps != null && subSteps.size >0">
					    <s:iterator value="subSteps">
					    	<c:if test='${"Y" == seqFlag}'>
						    	<option stepId="${stepId}" cost="0" price="0" seqFlag="${seqFlag}" description="${description}" value="${name}">${name}</option>
						    </c:if>
						    <c:if test='${"Y" != seqFlag}'>
						    	<option stepId="${stepId}" cost="${cost}" price="${retailPrice}" seqFlag="${seqFlag}" description="${description}" value="${name}">${name}</option>
						    </c:if>
					    </s:iterator>
					    </s:if>
					    <s:else>
					    	<option cost="0" price="0" seqFlag="N" description="" value="${quoteItem.name}">${quoteItem.name}</option>
					    </s:else>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;<span class="css_b">Cost：</span><span class="css_b" id="costUpSymbol"></span><input type="text" value="" size="12" name="cost">
					&nbsp;<span class="css_b">Price：</span><span class="css_b" id="priceUpSymbol"></span><input type="text" value="" size="12" name="price">&nbsp;
					<input type="button" value="Add" class="style_botton" name="addSerPkgBtn" parentId="${quoteItem.parentId}"
					itemId="${itemId}" stepListId="subStepList" descriptionId="description" catalogNo="${quoteItem.catalogNo}" itemName="${item}">
				</div>
				<div class="extnd_ant">
					<textarea class="content_textarea3" name="description" id="description"></textarea>
				</div>
				<div class="extnd_ant" id="text1_2">
				</div>
			</div>
		</div>
	</div>
 </form>