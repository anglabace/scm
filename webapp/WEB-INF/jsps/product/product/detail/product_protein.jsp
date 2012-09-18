<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<style>
.part_k {
	width: 950px;
	height: auto;
	float: left;
	margin: 0px;
	padding: 0px;
}
</style>
</head>
<body>
	<div id="Protein" class="part_k">
		<form name="detailForm" id="detailForm">
			<table width="950" border="0" cellpadding="0" cellspacing="0"
				class="General_table">
				<tr>
					<th width="190">Accession No</th>
					<td width="285"><input name="productDTO.protein.accessionNo"
						type="text" class="NFText" size="30"
						value="${productDTO.protein.accessionNo }" />
					</td>
					<th width="190">Cas No</th>
					<td width="285"><input name="productDTO.protein.casNo"
						type="text" class="NFText" size="30"
						value="${productDTO.protein.casNo }" />
					</td>
				</tr>
				<tr>
					<th width="190">Source</th>
					<td width="285"><input name="productDTO.protein.source"
						type="text" class="NFText" size="30"
						value="${productDTO.protein.source }" />
					</td>
					<th width="190">Species</th>
					<td width="285"><input name="productDTO.protein.species"
						type="text" class="NFText" size="30"
						value="${productDTO.protein.species }" />
					</td>
				</tr>
				<tr>
					<th>Molecular Weight</th>
					<td><input name="productDTO.protein.molecularWeight"
						type="text" class="NFText" size="30"
						value="${productDTO.protein.molecularWeight }" />
					</td>
					<th>Measured Molecular Weight</th>
					<td><input name="productDTO.protein.measuredMolecularWeight"
						type="text" class="NFText" size="30"
						value="${productDTO.protein.measuredMolecularWeight }" />
					</td>
				</tr>
				<tr>
					<th>Sequence</th>
					<td><textarea name="productDTO.protein.sequence"
							class="content_textarea2" style="width: 250PX;">${productDTO.protein.sequence }</textarea>
					</td>
					<th>Sequence Analysis</th>
					<td><textarea name="productDTO.protein.sequenceAnalysis"
							class="content_textarea2" style="width: 250PX;">${productDTO.protein.sequenceAnalysis }</textarea>
					</td>
				</tr>
				<tr>
					<th>Purification</th>
					<td><input name="productDTO.protein.purification" type="text"
						class="NFText" size="30"
						value="${productDTO.protein.purification }" />
					</td>
					<th>Purity</th>
					<td><input name="productDTO.protein.purity" type="text"
						class="NFText" size="30" value="${productDTO.protein.purity }" />
					</td>
				</tr>
				<tr>
					<th>Reaction Buffer</th>
					<td><input name="productDTO.protein.reactionBuffer"
						type="text" class="NFText" size="30"
						value="${productDTO.protein.reactionBuffer }" />
					</td>
					<th>Substrate</th>
					<td><input name="productDTO.protein.substrate" type="text"
						class="NFText" size="30" value="${productDTO.protein.substrate }" />
					</td>
				</tr>
				<tr>
					<th>Dimers</th>
					<td><input name="productDTO.protein.dimers" type="text"
						class="NFText" size="30" value="${productDTO.protein.dimers }" />
					</td>
					<th>Endotoxin Level</th>
					<td><input name="productDTO.protein.endotoxinLevel"
						type="text" class="NFText" size="30"
						value="${productDTO.protein.endotoxinLevel }" />
					</td>
				</tr>
				<tr>
					<th>Specific Activity</th>
					<td><textarea name="productDTO.protein.specificActivity"
							class="content_textarea2" style="width: 250PX;">${productDTO.protein.specificActivity }</textarea>
					</td>
					<th>Specificity</th>
					<td><textarea name="productDTO.protein.specificity"
							class="content_textarea2" style="width: 250PX;">${productDTO.protein.specificity }</textarea>
					</td>
				</tr>
				<tr>
					<th>Formulation</th>
					<td><textarea name="productDTO.protein.formulation"
							class="content_textarea2" style="width: 250PX;">${productDTO.protein.formulation }</textarea>
					</td>
					<th>Reconstitution</th>
					<td><textarea name="productDTO.protein.reconstitution"
							class="content_textarea2" style="width: 250PX;">${productDTO.protein.reconstitution }</textarea>
					</td>
				</tr>
				<tr>
					<th>Appearance</th>
					<td><input name="productDTO.protein.appearance" type="text"
						class="NFText" size="30" value="${productDTO.protein.appearance }" />
					</td>
					<th>Component</th>
					<td><input name="productDTO.protein.component" type="text"
						class="NFText" size="30" value="${productDTO.protein.component }" />
					</td>
				</tr>
				<tr>
					<th rowspan="2">Solubility</th>
					<td rowspan="2"><textarea name="productDTO.protein.solubility"
							class="content_textarea2" style="width: 250PX;">${productDTO.protein.solubility }</textarea>
					</td>
					<th>Concentration</th>
					<td><input name="productDTO.protein.concentration" type="text"
						class="NFText" size="30"
						value="${productDTO.protein.concentration }" />
					</td>
				</tr>
				<tr>
					<th>&nbsp;</th>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<th>Stability</th>
					<td><textarea name="productDTO.protein.stability"
							class="content_textarea2" style="width: 250PX;">${productDTO.protein.stability }</textarea>
					</td>
					<th>Quality Control</th>
					<td><textarea name="productDTO.protein.qualityControl"
							class="content_textarea2" style="width: 250PX;">${productDTO.protein.qualityControl }</textarea>
					</td>
				</tr>
				<tr>
					<th>Quantitation</th>
					<td><textarea name="productDTO.protein.quantitation"
							class="content_textarea2" style="width: 250PX;">${productDTO.protein.quantitation }</textarea>
					</td>
					<th>Isoelectric Point</th>
					<td><textarea name="productDTO.protein.isoelectricPoint"
							class="content_textarea2" style="width: 250PX;">${productDTO.protein.isoelectricPoint }</textarea>
					</td>
				</tr>
				<tr>
					<th>UV</th>
					<td><input name="productDTO.protein.uv" type="text"
						class="NFText" size="30" value="${productDTO.protein.uv }" />
					</td>
					<th>&nbsp;</th>
					<td>&nbsp;</td>
				</tr>  
				<tr>
					<th>Physical Appearance</th>
					<td><input name="productDTO.protein.physicalAppearance"
						type="text" class="NFText" size="30"
						value="${productDTO.protein.physicalAppearance }" />
					</td>
					<th>Usage</th>
					<td><input name="productDTO.protein.usage" type="text"
						class="NFText" size="30" value="${productDTO.protein.usage }" />
					</td>
				</tr>
				<s:if test="productDTO.antibody.productId = null">
					<input type="hidden" name="productDTO.antibody.productId"
						value="${id}" />
				</s:if>
				<s:else>
					<input type="hidden" name="productDTO.antibody.productId"
						value="${productDTO.antibody.productId}" />
				</s:else>
			</table>
		</form>
	</div>
</body>
</html>