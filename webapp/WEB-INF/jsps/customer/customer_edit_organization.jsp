<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<div style="width:100%; height:320px;  overflow:auto">
<form id="customerOrgForm" class="customerForm">
<table width="950" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<div class="invoice_title" style="padding-top:0px;">
    <img src="images/ad.gif" width="11" height="11" onclick="toggleShowMore_img(this, 'Organiza');" id="OrganizaItem"
         style="cursor: pointer;"/>Organization
</div>
<div id="Organiza" style="display:block;">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
        <tr>
            <td width="21%" align="right">
                <span class="important">*</span>Organization
            </td>
            <td width="35%">
                <input name="organization.orgId" id="orgId" type="hidden" value="${customerDetail.organization.orgId}"/>
                <input name="organization.name" id="orgName" value="${customerDetail.organization.name}" type="text"
                       class="NFText" size="35" readonly="readonly"/>
                <img id="org_2Trigger" src="images/search.gif" width="16" height="16" align="absmiddle"/>
                 <%--<img id="editTrigger2" src="images/b_edit.jpg" width="16" height="16" align="absmiddle" />--%>
            </td>
            <td width="12%" align="right">Description</td>
            <td>
                <input id="orgDescription" name="organization.description" type="text"
                       value="${customerDetail.organization.description}" class="NFText" size="35" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td align="right">Category</td>
            <td>
                <s:select id="categoryId" name="organization.categoryId" value="customerDetail.organization.categoryId"
                          cssStyle="width:250px" list="specDropDownList['ORGANIZATION_CATEGORY'].dropDownDTOs"
                          listKey="id" listValue="name" headerKey="" headerValue="Select Category"
                          disabled="true"></s:select>
            </td>
            <td align="right"><span class="important">*</span>Type</td>
            <td>
                <s:select name="organization.typeId" value="customerDetail.organization.typeId" id="orgType"
                          list="specDropDownList['ORGANIZATION_TYPE'].dropDownDTOs" listKey="id" listValue="name"
                          headerKey="" headerValue="Select Type" disabled="true"></s:select>
            </td>
        </tr>

        <tr>
            <td align="right">Language Code</td>
            <td>
                <s:select id="orgLangCode" name="organization.langCode"
                          list="specDropDownList['LANGUAGE_CODE'].dropDownDTOs" listKey="id" listValue="name"
                          headerKey="" headerValue="" value="customerDetail.organization.langCode"
                          disabled="true"></s:select>
            </td>
            <td align="right">Web</td>
            <td><input id="orgWeb" name="organization.web" type="text" value="${customerDetail.organization.web}"
                       class="NFText" size="35" readonly="readonly"/></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>
                <s:checkbox id="orgActive" name="organization.activeFlag" value='true' fieldValue="Y"
                            onclick="return false;" disabled="true"></s:checkbox>
                ACTIVE
            </td>
            <td>&nbsp;</td>

            <td>&nbsp;</td>
        </tr>
    </table>
</div>
<div class="invoice_title">
    <img src="images/ar.gif" width="11" height="11" onclick="toggleShowMore_img(this, 'Organization2');"
         id="Organization2Item" style="cursor: pointer;"/>
    Division
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table" id="Organization2"
       style="display:none;">
    <tr>
        <td width="21%" align="right">Division</td>
        <td width="35%">
            <input type="hidden" name="division.divisionId" id="divId" value="${customerDetail.division.divisionId}"/>
            <input name="division.name" id="divName" value="${customerDetail.division.name}" type="text" class="NFText"
                   size="35" readonly="readonly"/>
            <img id="divDialogTrigger" src="images/search.gif" width="16" height="16" align="absmiddle"/>
        </td>
        <td width="12%" align="right">Description</td>
        <td><input name="division.description" value="${customerDetail.division.description}" type="text" class="NFText"
                   size="35" readonly="readonly"/></td>
    </tr>
    <tr>
        <td align="right">Supervisor</td>
        <td colspan="3"><input name="division.supervisor" value="${customerDetail.division.supervisor}" type="text"
                               class="NFText" size="35" readonly="readonly"/></td>
    </tr>
    <tr>
        <td align="right">Language Code</td>
        <td colspan="3">
            <s:select name="division.langCode" list="specDropDownList['LANGUAGE_CODE'].dropDownDTOs" listKey="id"
                      listValue="name" headerKey="" headerValue="" value="customerDetail.division.langCode"
                      disabled="true"></s:select>
        </td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td colspan="3">
            <s:checkbox name="division.activeFlag" value='customerDetail.division.activeFlag == "Y"' fieldValue="Y"
                        disabled="true"></s:checkbox>
            ACTIVE
        </td>
    </tr>
</table>
<div class="invoice_title">
    <img src="images/ar.gif" width="11" height="11" onclick="toggleShowMore_img(this, 'Organization3');"
         id="Organization3Item" style="cursor: pointer;"/>Department
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table" id="Organization3"
       style="display:none;">
    <tr>
        <td width="21%" align="right">Department</td>
        <td width="35%">
            <input type="hidden" name="department.deptId" id="deptId" value="${customerDetail.department.deptId}"/>
            <input name="department.name" id="deptName" value="${customerDetail.department.name}" type="text"
                   class="NFText" size="35" readonly="readonly"/>
            <img id="deptDialogTrigger" src="images/search.gif" width="16" height="16" align="absmiddle"/></td>
        <td width="12%" align="right">Description</td>
        <td>
            <input name="department.description" value="${customerDetail.department.description}" type="text"
                   class="NFText" id="description" size="35" readonly="readonly"/>
        </td>
    </tr>
    <tr>
        <td align="right">Department Function</td>
        <td>
            <s:select cssStyle="width:210px" name="department.deptFuncId" id="deptDeptFunc"
                      list="specDropDownList['DEPARTMENT_FUNCTION'].dropDownDTOs" listKey="id" listValue="name"
                      headerKey="" headerValue="" value="customerDetail.department.deptFuncId"
                      disabled="true"></s:select>
        </td>
        <td align="right">Supervisor</td>
        <td><input name="department.supervisor" type="text" class="NFText" size="35" id="supervisor"
                   value="${customerDetail.department.supervisor}" readonly="readonly"/></td>
    </tr>
    <tr>
        <td align="right">Office</td>
        <td><input name="department.office" type="text" class="NFText" size="35" id="office"
                   value="${customerDetail.department.office}" readonly="readonly"/></td>
        <td align="right">Lab</td>
        <td><input name="department.lab" type="text" class="NFText" size="35" id="lab"
                   value="${customerDetail.department.lab}" readonly="readonly"/></td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>
             <s:checkbox name="department.activeFlag" value='customerDetail.department.activeFlag == "Y"' fieldValue="Y"
                        disabled="true"></s:checkbox>
            ACTIVE
        </td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
</table>
<div class="invoice_title">
    <img src="images/ar.gif" width="11" height="11" onclick="toggleShowMore_img(this, 'Organization4');"
         id="Organization4Item" style="cursor: pointer;"/>Location
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table" id="Organization4"
       style="display:none;">
<tr>
    <td width="21%" align="right">Location Type</td>
    <td width="29%">
        <select id="locType" style="width:209px;">
            <option value="orgLocTable">Organization</option>
            <option value="divLocTable">Division</option>
            <option value="deptLocTable">Department</option>
        </select>
    </td>
    <td align="right">
				     <span id="sameAsOrgLocDiv" style='display:none'>
				     <input type="radio" name="sameas" id="sameAsOrgLoc" readonly="readonly">Same as the organization
				     </span>
                     <span id="sameAsDivLocDiv" style='display:none'>
                     <input type="radio" name="sameas" id="sameAsDivLoc" readonly="readonly"/>Same as the division
                     </span>
    </td>
</tr>
<tr>
    <td colspan="3">
        <!-- organization location start -->
        <table id="orgLocTable" width="100%" border="0" cellpadding="0" cellspacing="0" style="margin:0px"
               class="General_table">
            <tr>
                <td width="21%" align="right">Phone</td>
                <td width="29%">
                    <div align="left">
                        <input name="organization.phone" id="orgLocPhone" type="text"
                               value="${customerDetail.organization.phone}" class="NFText" size="17"
                               readonly="readonly"/>
                        Ext
                        <input name="organization.phoneExt" id="orgLocPhoneExt"
                               value="${customerDetail.organization.phoneExt}" type="text" class="NFText" size="6"
                               readonly="readonly"/>
                    </div>
                </td>
                <td width="12%" align="right">Alt</td>
                <td align="right">
                    <div align="left">
                        <input name="organization.altPhone" id="orgLocAltPhone"
                               value="${customerDetail.organization.altPhone}" type="text" class="NFText" size="17"
                               readonly="readonly"/>
                        Ext
                        <input name="organization.altPhoneExt" id="orgLocAltPhoneExt"
                               value="${customerDetail.organization.altPhoneExt}" type="text" class="NFText" size="6"
                               readonly="readonly"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td align="right">Fax</td>
                <td align="right">
                    <div align="left">
                        <input name="organization.fax" id="orgLocFax" value="${customerDetail.organization.fax}"
                               type="text" class="NFText" size="17" readonly="readonly"/>
                        Ext
                        <input name="organization.faxExt" id="orgLocFaxExt"
                               value="${customerDetail.organization.faxExt}" type="text" size="6" class="NFText"
                               readonly="readonly"/>
                    </div>
                </td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td rowspan="2" valign="top" align="right">Address</td>
                <td><input name="organization.addrLine1" id="orgLocAddr1"
                           value="${customerDetail.organization.addrLine1}" type="text" class="NFText" size="35"
                           readonly="readonly"/></td>
                <td align="right">City</td>
                <td><input name="organization.city" id="orgLocCity" value="${customerDetail.organization.city}"
                           type="text" class="NFText" size="35" readonly="readonly"/></td>
            </tr>
            <tr>
                <td><input name="organization.addrLine2" id="orgLocAddr2"
                           value="${customerDetail.organization.addrLine2}" type="text" class="NFText" size="35"
                           readonly="readonly"/></td>
                <td align="right">Zip Code</td>
                <td><input name="organization.zipCode" id="orgLocZip" value="${customerDetail.organization.zipCode}"
                           type="text" class="NFText" size="35" readonly="readonly"/></td>

            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input name="organization.addrLine3" id="orgLocAddr3"
                           value="${customerDetail.organization.addrLine3}" type="text" class="NFText" size="35"
                           readonly="readonly"/></td>
                <td align="right">State</td>
                <td>
                    <select name="organization.state" id="orgLocState" style="width:209px;" disabled="disabled"></select>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td align="right">Country</td>
                <td>
                    <select name="organization.country" id="orgLocCountry" style="width:209px;" disabled="disabled">
                        <option value="US">United States</option>
                    </select>
                </td>
            </tr>
        </table>
        <!-- organization location end -->

        <!-- division location start -->
        <table id="divLocTable" style="display:none" width="100%" border="0" cellpadding="0" cellspacing="0"
               class="General_table" style="margin:0px">
            <tr>
                <td width="21%" align="right">Phone</td>
                <td width="29%" align="right">
                    <div align="left">
                        <input name="division.phone" id="divLocPhone" type="text"
                               value="${customerDetail.division.phone}" class="NFText" size="17" readonly="readonly"/>
                        Ext
                        <input name="division.phoneExt" id="divLocPhoneExt" value="${customerDetail.division.phoneExt}"
                               type="text" class="NFText" size="6" readonly="readonly"/>
                    </div>
                </td>
                <td width="12%" align="right">Alt</td>
                <td align="right">
                    <div align="left">
                        <input name="division.altPhone" id="divLocAltPhone" value="${customerDetail.division.altPhone}"
                               type="text" class="NFText" size="17" readonly="readonly"/>
                        Ext
                        <input name="division.altPhoneExt" id="divLocAltPhoneExt"
                               value="${customerDetail.division.altPhoneExt}" type="text" class="NFText" size="6" readonly="readonly"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td align="right">Fax</td>
                <td align="right">
                    <div align="left">
                        <input name="division.fax" id="divLocFax" value="${customerDetail.division.fax}" type="text"
                               class="NFText" size="17" readonly="readonly"/>
                        Ext
                        <input name="division.faxExt" id="divLocFaxExt" value="${customerDetail.division.faxExt}"
                               type="text" size="6" class="NFText" readonly="readonly"/>
                    </div>
                </td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td rowspan="2" valign="top" align="right">Address</td>
                <td><input name="division.addrLine1" id="divLocAddr1" value="${customerDetail.division.addrLine1}"
                           type="text" class="NFText" size="35" readonly="readonly"/></td>
                <td align="right">City</td>
                <td><input name="division.city" id="divLocCity" value="${customerDetail.division.city}" type="text"
                           class="NFText" size="35" readonly="readonly"/></td>
            </tr>
            <tr>
                <td><input name="division.addrLine2" id="divLocAddr2" value="${customerDetail.division.addrLine2}"
                           type="text" class="NFText" size="35" readonly="readonly"/></td>
                <td align="right">Zip Code</td>
                <td><input name="division.zipCode" id="divLocZip" value="${customerDetail.division.zipCode}" type="text"
                           class="NFText" size="35" readonly="readonly"/></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input name="division.addrLine3" id="divLocAddr3" value="${customerDetail.division.addrLine3}"
                           type="text" class="NFText" size="35" readonly="readonly"/></td>
                <td align="right">State</td>
                <td>
                    <select name="division.state" id="divLocState" value="${customerDetail.division.state}"
                            style="width:209px;" disabled="disabled">
                    </select>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td align="right">Country</td>
                <td>
                    <select name="division.country" id="divLocCountry" style="width:209px;" disabled="disabled">
                        <option value="">United States</option>
                    </select>
                </td>
            </tr>
        </table>
        <!-- division location end -->

        <!-- department location start -->
        <table id="deptLocTable" style="display:none" width="100%" border="0" cellpadding="0" cellspacing="0"
               class="General_table" style="margin:0px">
            <tr>
                <td width="21%" align="right">Phone</td>
                <td width="29%" align="right">
                    <div align="left">
                        <input name="department.phone" id="deptLocPhone" type="text" class="NFText" size="17"
                               value="${customerDetail.department.phone}" readonly="readonly"/>
                        Ext
                        <input name="department.phoneExt" id="deptLocPhoneExt" type="text" class="NFText" size="6"
                               value="${customerDetail.department.phoneExt}" readonly="readonly"/>
                    </div>
                </td>
                <td width="12%" align="right">Alt</td>
                <td align="right">
                    <div align="left">
                        <input name="department.altPhone" id="deptLocAltPhone" type="text" class="NFText" size="17"
                               value="${customerDetail.department.altPhone}" readonly="readonly"/>
                        Ext
                        <input name="department.altPhoneExt" id="deptLocAltPhoneExt" type="text" class="NFText" size="6"
                               value="${customerDetail.department.altPhoneExt}" readonly="readonly"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td align="right">Fax</td>
                <td align="right">
                    <div align="left">
                        <input name="department.fax" id="deptLocFax" type="text" class="NFText" size="17"
                               value="${customerDetail.department.fax}" readonly="readonly"/>
                        Ext
                        <input name="department.faxExt" id="deptLocFaxExt" type="text" size="6" class="NFText"
                               value="${customerDetail.department.faxExt}" readonly="readonly"/>
                    </div>
                </td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td rowspan="2" valign="top" align="right">Address</td>
                <td><input name="department.addrLine1" id="deptLocAddr1" type="text" class="NFText" size="35"
                           value="${customerDetail.department.addrLine1}" readonly="readonly"/></td>
                <td align="right">City</td>
                <td><input name="department.city" id="deptLocCity" type="text" class="NFText" size="35"
                           value="${customerDetail.department.city}" readonly="readonly"/></td>
            </tr>
            <tr>
                <td><input name="department.addrLine2" id="deptLocAddr2" type="text" class="NFText" size="35"
                           value="${customerDetail.department.addrLine2}" readonly="readonly"/></td>
                <td align="right">Zip Code</td>
                <td><input name="department.zipCode" id="deptLocZip" type="text" class="NFText" size="35"
                           value="${customerDetail.department.zipCode}" readonly="readonly"/></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input name="department.addrLine3" id="deptLocAddr3" type="text" class="NFText" size="35"
                           value="${customerDetail.department.addrLine3}" readonly="readonly"/></td>
                <td align="right">State</td>
                <td>
                    <select name="department.state" id="deptLocState"  style="width:209px;" disabled="disabled">
                    </select>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td align="right">Country</td>
                <td>
                    <select name="department.country" id="deptLocCountry" style="width:209px;" disabled="disabled">
                        <option value="">United States</option>
                    </select>
                </td>
            </tr>
        </table>
        <!-- department location end
        <select name="department.state" id="deptLocState" style="width:209px;">
                </select>

        -->
    </td>
</tr>
</table>
</td>
</tr>
</table>
</form>
</div>