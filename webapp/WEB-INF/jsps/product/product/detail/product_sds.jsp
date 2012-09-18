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
	<div id="Kit" class="part_k">
		<form name="detailForm" id="detailForm">
			<s:if test="productDTO.sdvector.productId = null">
				<input type="hidden" name="productDTO.sdvector.productId"
					value="${id}" />
			</s:if>
			<s:else>
				<input type="hidden" name="productDTO.sdvector.productId"
					value="${productDTO.sdvector.productId}" />
			</s:else>
			<table width="950" border="0" cellpadding="0" cellspacing="0"
				class="General_table" align="center">
				<tr>
					<td colspan="4" align="center"><b>Detail Map</b>
					</td>
				</tr>
				<tr>
					<td align="right">polylinker :</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.polylinkerStart"
						value="${productDTO.sdvector.polylinkerStart}" /> - <input
						type="text" class="NFText" size="15"
						name="productDTO.sdvector.polylinkerEnd"
						value="${productDTO.sdvector.polylinkerEnd}" /></td>

					<td align="right">u6:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.u6Start"
						value="${productDTO.sdvector.u6Start}" /> - <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.u6End"
						value="${productDTO.sdvector.u6End}" />
					</td>
				</tr>
				<tr>
					<td align="right">h1:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.h1Start"
						value="${productDTO.sdvector.h1Start}" />- <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.h1End"
						value="${productDTO.sdvector.h1End}" /></td>

					<td align="right">cmv:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.cmvStart"
						value="${productDTO.sdvector.cmvStart}" />- <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.cmvEnd"
						value="${productDTO.sdvector.cmvEnd}" />
					</td>
				</tr>
				<tr>
					<td align="right">cgfp :</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.cgfpStart"
						value="${productDTO.sdvector.cgfpStart}" /> - <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.cgfpEnd"
						value="${productDTO.sdvector.cgfpEnd}" />
					</td>

					<td align="right">sv40:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.sv40Start"
						value="${productDTO.sdvector.sv40Start}" /> - <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.sv40End"
						value="${productDTO.sdvector.sv40End}" />
					</td>
				</tr>
				<tr>
					<td align="right">zeo :</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.zeoStart"
						value="${productDTO.sdvector.zeoStart}" />- <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.zeoEnd"
						value="${productDTO.sdvector.zeoEnd}" />
					</td>

					<td align="right">hygro:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.hygroStart"
						value="${productDTO.sdvector.hygroStart}" /> - <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.hygroEnd"
						value="${productDTO.sdvector.hygroEnd}" /></td>
				</tr>
				<tr>
					<td align="right">neo :</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.neoStart"
						value="${productDTO.sdvector.neoStart}" /> - <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.neoEnd"
						value="${productDTO.sdvector.neoEnd}" /></td>

					<td align="right">pucori:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.pucOriStart"
						value="${productDTO.sdvector.pucOriStart}" /> - <input
						type="text" class="NFText" size="15"
						name="productDTO.sdvector.pucOriEnd"
						value="${productDTO.sdvector.pucOriEnd}" /></td>
				</tr>
				<tr>
					<td align="right">amp:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.ampStart"
						value="${productDTO.sdvector.ampStart}" /> - <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.ampEnd"
						value="${productDTO.sdvector.ampEnd}" /></td>

					<td align="right">p10:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.p10Start"
						value="${productDTO.sdvector.p10Start}" /> - <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.p10End"
						value="${productDTO.sdvector.p10End}" /></td>
				</tr>
				<tr>
					<td align="right">f1 :</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.f1Start"
						value="${productDTO.sdvector.f1Start}" /> - <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.f1End"
						value="${productDTO.sdvector.f1End}" />
					</td>

					<td align="right">t7Term :</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.t7TermStart"
						value="${productDTO.sdvector.t7TermStart}" /> - <input
						type="text" class="NFText" size="15"
						name="productDTO.sdvector.t7TermEnd"
						value="${productDTO.sdvector.t7TermEnd}" /></td>
				</tr>
				<tr>
					<td align="right">orf603 :</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.orf603Start"
						value="${productDTO.sdvector.orf603Start}" /> - <input
						type="text" class="NFText" size="15"
						name="productDTO.sdvector.orf603End"
						value="${productDTO.sdvector.orf603End}" /></td>

					<td align="right">orf1629:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.orf1629Start"
						value="${productDTO.sdvector.orf1629Start}" /> - <input
						type="text" class="NFText" size="15"
						name="productDTO.sdvector.orf1629End"
						value="${productDTO.sdvector.orf1629End}" /></td>
				</tr>
				<tr>
					<td align="right">l5TR:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.l5TRStart"
						value="${productDTO.sdvector.l5TRStart}" /> - <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.l5TREnd"
						value="${productDTO.sdvector.l5TREnd}" /></td>

					<td align="right">l3TR:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.l3TRStart"
						value="${productDTO.sdvector.l3TRStart}" /> - <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.l3TREnd"
						value="${productDTO.sdvector.l3TREnd}" /></td>
				</tr>
				<tr>
					<td align="right">psi:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.psiStart"
						value="${productDTO.sdvector.psiStart}" /> - <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.psiEnd"
						value="${productDTO.sdvector.psiEnd}" /></td>

					<td align="right">puromycin:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.puromycinStart"
						value="${productDTO.sdvector.puromycinStart}" /> - <input
						type="text" class="NFText" size="15"
						name="productDTO.sdvector.puromycinEnd"
						value="${productDTO.sdvector.puromycinEnd}" /></td>
				</tr>
				<tr>
					<td align="right">gentamicin:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.gentamicinStart"
						value="${productDTO.sdvector.gentamicinStart}" /> - <input
						type="text" class="NFText" size="15"
						name="productDTO.sdvector.gentamicinEnd"
						value="${productDTO.sdvector.gentamicinEnd}" /></td>

					<td align="right">cmvIe:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.cmvIeStart"
						value="${productDTO.sdvector.cmvIeStart}" /> - <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.cmvIeEnd"
						value="${productDTO.sdvector.cmvIeEnd}" /></td>
				</tr>
				<tr>
					<td align="right">pbr322Origin:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.pbr322OriginStart"
						value="${productDTO.sdvector.pbr322OriginStart}" /> - <input
						type="text" class="NFText" size="15"
						name="productDTO.sdvector.pbr322OriginEnd"
						value="${productDTO.sdvector.pbr322OriginEnd}" /></td>

					<td align="right">kanamycin:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.kanamycinStart"
						value="${productDTO.sdvector.kanamycinStart}" /> - <input
						type="text" class="NFText" size="15"
						name="productDTO.sdvector.kanamycinEnd"
						value="${productDTO.sdvector.kanamycinEnd}" /></td>
				</tr>
				<tr>
					<td align="right">sifluc:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.siflucStart"
						value="${productDTO.sdvector.siflucStart}" /> - <input
						type="text" class="NFText" size="15"
						name="productDTO.sdvector.siflucEnd"
						value="${productDTO.sdvector.siflucEnd}" /></td>

					<td align="right">sv40OriPromoter:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.sv40OriPromoterStart"
						value="${productDTO.sdvector.sv40OriPromoterStart}" /> - <input
						type="text" class="NFText" size="15"
						name="productDTO.sdvector.sv40OriPromoterEnd"
						value="${productDTO.sdvector.sv40OriPromoterEnd}" /></td>
				</tr>
				<tr>
					<td align="right">cole1Ori:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.cole1OriStart"
						value="${productDTO.sdvector.cole1OriStart}" /> - <input
						type="text" class="NFText" size="15"
						name="productDTO.sdvector.cole1OriEnd"
						value="${productDTO.sdvector.cole1OriEnd}" /></td>

					<td align="right">ires:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.iresStart"
						value="${productDTO.sdvector.iresStart}" /> - <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.iresEnd"
						value="${productDTO.sdvector.iresEnd}" /></td>
				</tr>
				<tr>
					<td align="right">mcs:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.mcsStart1"
						value="${productDTO.sdvector.mcsStart1}" /> - <input type="text"
						class="NFText" size="15" name="productDTO.sdvector.mcsEnd1"
						value="${productDTO.sdvector.mcsEnd1}" /></td>

					<td align="right">forward:</td>
					<td><input type="text" class="NFText" size="15"
						name="productDTO.sdvector.forwardPrimer"
						value="${productDTO.sdvector.forwardPrimer}" /> - <input
						type="text" class="NFText" size="15"
						name="productDTO.sdvector.reversePrimer"
						value="${productDTO.sdvector.reversePrimer}" /></td>
				</tr>
				<tr align="center">
					<td colspan="4"><br>
					<a href="${urllink}" target="_blank">Vector Sequence and
							Restriction Enzyme Map</a></td>
				</tr>
			</table>

		</form>
	</div>
</body>
</html>