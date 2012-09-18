package com.genscript.gsscm.customer.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.dto.CustomerPriceBeanDTO;

@Repository
public class CustomerPriceBeanDTODao extends HibernateDao<CustomerPriceBeanDTO,Integer> {
	
	private static final String hql = "SELECT distinct(cpb.productId), cpb.catalogNo, cpb.name, " +
			" ge.accessionNo, cpb.shortDesc, '' as code, cpb.upSymbol, cpb.unitPrice, " +
			" cpb.size, cpb.uom, cpb.qtyUom, cpb.upCatalogId, cpb.upCatalogName, ge.vector " + 
			" FROM CustomerPriceBean cpb, Gene ge WHERE cpb.productId = ge.productId AND cpb.custNo=:custNo ";
	
	/**
	 * 分页查询
	 * @author Zhang Yong
	 * @param cpbPage
	 * @param cpbDTO
	 * @return 分页对象
	 */
	@SuppressWarnings("unchecked")
	public Page<CustomerPriceBeanDTO> findMyPage (Page<CustomerPriceBeanDTO> cpbPage,
			CustomerPriceBeanDTO cpbDTO, Integer custNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custNo", custNo);
		StringBuffer sqlsbf = new StringBuffer();
		sqlsbf.append(hql);
		if (cpbDTO != null) {
			if (cpbDTO.getAccessionNo() != null) {
				sqlsbf.append(" AND ge.accessionNo like:accessionNo ");
				map.put("accessionNo", "%" + cpbDTO.getAccessionNo() + "%");
			} else if (cpbDTO.getCatalogNo() != null) {
				sqlsbf.append(" AND cpb.catalogNo like:catalogNo ");
				map.put("catalogNo", "%" + cpbDTO.getCatalogNo() + "%");
			} else if (cpbDTO.getName() != null || cpbDTO.getShortDesc() != null) {
				sqlsbf.append(" AND (cpb.name like:name OR cpb.shortDesc like:shortDesc) ");
				map.put("name", "%" + cpbDTO.getName() + "%");
				map.put("shortDesc", "%" + cpbDTO.getShortDesc() + "%");
			}
		}
		sqlsbf.append(" ORDER BY cpb.catalogNo ");
		cpbPage = this.findPage(cpbPage, sqlsbf.toString(), map);
		if (cpbPage != null && cpbPage.getResult() != null && cpbPage.getResult().size()   > 0) {
			List objDtoList = cpbPage.getResult();
			List<CustomerPriceBeanDTO> cpbDtoList = new ArrayList<CustomerPriceBeanDTO>();
			for (Object obj : objDtoList) {
				CustomerPriceBeanDTO cpbDto = new CustomerPriceBeanDTO();
				Object[] objs = (Object[]) obj;
				cpbDto.setCustNo(custNo);
				cpbDto.setProductId(Integer.parseInt(objs[0].toString()));
				cpbDto.setCatalogNo(objs[1] == null?null:objs[1].toString());
				cpbDto.setName(objs[2] == null?null:objs[2].toString());
				cpbDto.setAccessionNo(objs[3] == null?null:objs[3].toString());
				cpbDto.setShortDesc(objs[4] == null?null:objs[4].toString());
				cpbDto.setCode(null);
				cpbDto.setUpSymbol(objs[6] == null?null:objs[6].toString());
				cpbDto.setUnitPrice(objs[7] == null || ("").equals(objs[7].toString().trim())
						? null:Double.parseDouble(objs[7].toString()));
				cpbDto.setSize(objs[8] == null || ("").equals(objs[8].toString().trim())
						?0:Double.parseDouble(objs[8].toString()));
				cpbDto.setUom(objs[9] == null?null:objs[9].toString());
				cpbDto.setQtyUom(objs[10] == null?null:objs[10].toString());
				cpbDto.setUpCatalogId(objs[11] == null?null:objs[11].toString());
				cpbDto.setUpCatalogName(objs[12] == null?null:objs[12].toString());
				cpbDto.setVectorName(objs[13] == null?null:objs[12].toString());
				cpbDtoList.add(cpbDto);
			}
			cpbPage.setResult(cpbDtoList);
		}
		return cpbPage;
	}
}
