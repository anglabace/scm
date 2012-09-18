package com.genscript.gsscm.product.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.Oligo;

@Repository
public class OligoDao extends HibernateDao<Oligo, Integer> {

	/**
	 * 查询Oligo sequence length价格
	 * @author Zhang Yong
	 * add date 2011-12-12
	 * @param backboneName
	 * @param seqLength
	 * @param synthesisScales(之内容必须为50nm这种格式)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Float[] getLengthPrice(String backboneName, Integer seqLength, String synthesisScales) {
		String sql = "select obi.price_"+synthesisScales+", obi.cost_" + synthesisScales +
				" from product.oligo_backbones ob, product.oligo_backbone_items obi " +
				" where ob.b_name =? and ob.id = obi.b_id and obi.b_start <=? " +
				" and obi.b_end >?";
		Query query = this.getSession().createSQLQuery(sql).setString(0, backboneName).setInteger(1, seqLength).setInteger(2, seqLength);
        @SuppressWarnings("rawtypes")
		List list = query.list();
        return getPriceAndCost(list);
	}
	
	/**
	 * 查询Oligo purifications价格
	 * @author Zhang Yong
	 * add date 2011-12-12
	 * @param purification
	 * @param synthesisScales(之内容必须为50nm这种格式)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Float[] getPurificationsPrice(String purification, String synthesisScales) {
		String sql = "select op.price_"+synthesisScales+", op.cost_" + synthesisScales +
				" from product.oligo_purifications op " +
				" where op.p_name =? ";
		Query query = this.getSession().createSQLQuery(sql).setString(0, purification);
        @SuppressWarnings("rawtypes")
		List list = query.list();
        return getPriceAndCost(list);
	}
	
	/**
	 * 查询Oligo Modifications价格
	 * @author Zhang Yong
	 * add date 2011-12-12
	 * @param modificationIds
	 * @param synthesisScales(之内容必须为50nm这种格式)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Float[] getModificationsPrice(String modificationIds, String synthesisScales) {
		String sql = "select om.price_"+synthesisScales+", om.cost_" + synthesisScales +
				" from product.oligo_modifications om " +
				" where om.id in("+modificationIds+")";
		Query query = this.getSession().createSQLQuery(sql);
        @SuppressWarnings("rawtypes")
		List list = query.list();
        return getPriceAndCost(list);
	}
	
	/**
	 * 查询Oligo chimeric bases价格
	 * @author Zhang Yong
	 * add date 2011-12-12
	 * @param codes
	 * @param synthesisScales(之内容必须为50nm这种格式)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Float[] getChimericBasesPrice(String codes, String synthesisScales) {
		String sql = "select ocb.price_"+synthesisScales+", ocb.cost_" + synthesisScales +
				" from product.oligo_chimeric_bases ocb " +
				" where ocb.c_code in("+codes+")";
		Query query = this.getSession().createSQLQuery(sql);
        @SuppressWarnings("rawtypes")
		List list = query.list();
        return getPriceAndCost(list);
	}
	
	/**
	 * 查询Oligo mixed bases价格
	 * @author Zhang Yong
	 * add date 2011-12-12
	 * @param codes
	 * @param synthesisScales(之内容必须为50nm这种格式)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Float[] getMixedBasesPrice(String codes, String synthesisScales) {
		String sql = "select omb.price_"+synthesisScales+", omb.cost_" + synthesisScales +
				" from product.oligo_mixed_bases omb " +
				" where omb.s_code in("+codes+")";
		Query query = this.getSession().createSQLQuery(sql);
        @SuppressWarnings("rawtypes")
		List list = query.list();
        return getPriceAndCost(list);
	}
	
	/**
	 * 处理price
	 * @author Zhang Yong
	 * add date 2011-12-12
	 * @param list
	 * @return
	 */
	private Float[] getPriceAndCost (List<Object> list) {
		if(list == null || list.isEmpty()){
            return null;
        }
		Float[] priceAndCosts = new Float[2];
		priceAndCosts[0] = 0f;
		priceAndCosts[1] = 0f;
        for (Object obj : list) {
        	Object[] objs = (Object[]) obj;
        	Float[] priceAndCost = new Float[2];
    		priceAndCost[0] = objs[0] == null?0:Float.parseFloat(objs[0].toString());
    		priceAndCost[1] = objs[1] == null?0:Float.parseFloat(objs[1].toString());
    		if ((priceAndCost[0] != null && priceAndCost[0].floatValue() <0) || (priceAndCost[1] != null && priceAndCost[1].floatValue() <0)) {
    			return priceAndCost;
    		}
    		priceAndCosts[0] += priceAndCost[0];
    		priceAndCosts[1] += priceAndCost[1];
        }
        return priceAndCosts;
	}
}
