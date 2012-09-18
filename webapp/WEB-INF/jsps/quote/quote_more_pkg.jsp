<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<form id="pkgForm" name="pkgForm" method="post">
	<div>
		<div class="extend_title"><span class="important">*</span>&nbsp;Name 
			<br/>
			<div style="padding-left: 30px;"><input id="parentName" type="text" class="NFText" size="50"/></div>
		</div>
		<div class="extend_title"><span class="important">*</span>&nbsp;Description<br/></div>
		<div style="padding-left: 30px;">
			<textarea id="parentDescription" class="content_textarea3"></textarea>
			<input type="hidden" id="pkgId"/>
		</div>
		<div style="padding-left: 30px;">
		<s:iterator value="packageDescriptArray">
			<input type="checkbox" name="demoDes" value="<s:property/>"><s:property/>
		</s:iterator>
		</div>
	</div>
	<div class="extend_title">Step Items</div>
	<s:iterator value="serviceSteps">
		<div class="extend_new">
			<div class="extend_line" id="Protein_01">
				<div class="extend" style="display:block;">
					<div class="extend_l">
						<a href="javascript:void(0);" onclick="openc('exce${id}','img${id}');" id="exce1Item">
						<img src="images/ar.gif" width="11" height="11" id="img${id}"/></a>
					</div>
					<div class="extend_r">
						<div class="extnd_rt">${intmdCatalogNo}: <span class="css_blue_b">${item}</span></div>
					</div>
				</div>
				
				<div id="exce${id}" class="extnd_an" style="display:none;" name="contentPkgSerDiv" catalogNo="${intmdCatalogNo}">
					<div class="extnd_ant">
						<span class="css_b">Select Services Detail</span> 
						<select id="select_${id}" style="width:250px;" name="stepList">
							<option stepId="" description="" value="">please select</option>
							<s:if test="stepDtoList != null && stepDtoList.size >0">
						    <s:iterator value="stepDtoList">
						    	<c:if test='${"Y" == seqFlag}'>
							    	<option stepId="${stepId}" cost="0" price="0" seqFlag="${seqFlag}" description="${description}" value="${name}">${name}</option>
							    </c:if>
							    <c:if test='${"Y" != seqFlag}'>
							    	<option stepId="${stepId}" cost="${cost}" price="${retailPrice}" seqFlag="${seqFlag}" description="${description}" value="${name}">${name}</option>
							    </c:if>
						    </s:iterator>
						    </s:if>
						    <s:else>
						    	<option cost="0" price="0" seqFlag="N" description="" value="${item}">${item}</option>
						    </s:else>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;<span class="css_b">Cost：</span><span class="css_b" id="costUpSymbol"></span><input type="text" value="" size="12" name="cost">
						&nbsp;<span class="css_b">Price：</span><span class="css_b" id="priceUpSymbol"></span><input type="text" value="" size="12" name="price">&nbsp;
						<input type="button" value="Add" class="style_botton" name="addSerPkgBtn" parentId="${itemId}"
						itemId=""  stepListId="select_${id}" descriptionId="description${id}" catalogNo="${intmdCatalogNo}" itemName="${item}">
					</div>
					<div class="extnd_ant">
						<textarea class="content_textarea3" name="description${id}" id="description${id}"></textarea>
					</div>
					<div class="extnd_ant" id="text1_2">
					</div>
				</div>
			</div>
		</div>
	</s:iterator>
 </form>