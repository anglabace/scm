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
	<div id="Molecule" class="part_k">
		<form name="detailForm" id="detailForm">
			<table width="950" border="0" cellpadding="0" cellspacing="0"
				class="General_table">
				<tr>
					<th width="190">Molecular Formula</th>
					<td width="285"><input
						name="productDTO.molecule.molecularFormula" type="text"
						class="NFText" size="30"
						value="${productDTO.molecule.molecularFormula }" /></td>
					<th width="190">Source</th>
					<td width="285"><input name="productDTO.molecule.source"
						type="text" class="NFText" size="30"
						value="${productDTO.molecule.source }" /></td>
				</tr>
				<tr>
					<th>Culture Medium</th>
					<td><input name="productDTO.molecule.cultureMedium"
						type="text" class="NFText" size="30"
						value="${productDTO.molecule.cultureMedium }" /></td>
					<th>Freeze Medium</th>
					<td><input name="productDTO.molecule.freezeMedium" type="text"
						class="NFText" size="30"
						value="${productDTO.molecule.freezeMedium }" /></td>
				</tr>
				<tr>
					<th>Species</th>
					<td><input name="productDTO.molecule.species" type="text"
						class="NFText" size="30" value="${productDTO.molecule.species }" />
					</td>
					<th>Tissue</th>
					<td><input name="productDTO.molecule.tissue" type="text"
						class="NFText" size="30" value="${productDTO.molecule.tissue }" />
					</td>
				</tr>
				<tr>
					<th>Clone</th>
					<td><input name="productDTO.molecule.clone" type="text"
						class="NFText" size="30" value="${productDTO.molecule.clone }" />
					</td>
					<th>Purity</th>
					<td><input name="productDTO.molecule.purity" type="text"
						class="NFText" size="30" value="${productDTO.molecule.purity }" />
					</td>
				</tr>
				<tr>
					<th>Reaction Buffer</th>
					<td><input name="productDTO.molecule.reactionBuffer"
						type="text" class="NFText" size="30"
						value="${productDTO.molecule.reactionBuffer }" /></td>
					<th rowspan="2">Sterility</th>
					<td rowspan="2"><textarea name="productDTO.molecule.sterility"
							class="content_textarea2" style="width: 250PX;">${productDTO.molecule.sterility }</textarea>
					</td>
				</tr>
				<tr>
					<th>Band Range</th>
					<td><input name="productDTO.molecule.bandRange" type="text"
						class="NFText" size="30" value="${productDTO.molecule.bandRange }" />
					</td>
				</tr>
				<tr>
					<th>Specific Activity</th>
					<td><textarea name="productDTO.molecule.specificActivity"
							class="content_textarea2" style="width: 250PX;">${productDTO.molecule.specificActivity }</textarea>
					</td>
					<th>Specificity</th>
					<td><textarea name="productDTO.molecule.specificity"
							class="content_textarea2" style="width: 250PX;">${productDTO.molecule.specificity }</textarea>
					</td>
				</tr>
				<tr>
					<th>Formulation</th>
					<td><textarea name="productDTO.molecule.formulation"
							class="content_textarea2" style="width: 250PX;">${productDTO.molecule.formulation }</textarea>
					</td>
					<th>Reconstitution</th>
					<td><textarea name="productDTO.molecule.reconstitution"
							class="content_textarea2" style="width: 250PX;">${productDTO.molecule.reconstitution }</textarea>
					</td>
				</tr>
				<tr>
					<th>Appearance</th>
					<td><input name="productDTO.molecule.appearance" type="text"
						class="NFText" size="30"
						value="${productDTO.molecule.appearance }" /></td>
					<th>Color</th>
					<td><input name="productDTO.molecule.color" type="text"
						class="NFText" size="30" value="${productDTO.molecule.color }" />
					</td>
				</tr>
				<tr>
					<th rowspan="2">Calculation</th>
					<td rowspan="2"><textarea
							name="productDTO.molecule.calculation" class="content_textarea2"
							style="width: 250PX;">${productDTO.molecule.calculation }</textarea>
					</td>
					<th>Concentration</th>
					<td><input name="productDTO.molecule.concentration"
						type="text" class="NFText" size="30"
						value="${productDTO.molecule.concentration }" /></td>
				</tr>
				<tr>
					<th>&nbsp;</th>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<th>Stability</th>
					<td><textarea name="productDTO.molecule.stability"
							class="content_textarea2" style="width: 250PX;">${productDTO.molecule.stability }</textarea>
					</td>
					<th>Quality Control</th>
					<td><textarea name="productDTO.molecule.qualityControl"
							class="content_textarea2" style="width: 250PX;">${productDTO.molecule.qualityControl }</textarea>
					</td>

				</tr>

				<tr>


					<th>Recommended loading volume</th>
					<td><textarea name="productDTO.molecule.volume"
							class="content_textarea2" style="width: 250PX;">${productDTO.molecule.volume }</textarea>
					</td>

					<th></th>
					<td></td>
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
