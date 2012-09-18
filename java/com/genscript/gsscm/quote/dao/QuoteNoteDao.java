package com.genscript.gsscm.quote.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.QuoteInstructionType;
import com.genscript.gsscm.quote.entity.QuoteNote;

@Repository
public class QuoteNoteDao extends HibernateDao<QuoteNote, Integer> {
	/**
	 * 根据quoteNo查找相关QuoteNote.
	 * 
	 * @param quoteNo
	 * @param type
	 * @return
	 */
	public List<QuoteNote> getQuoteNoteList(Integer quoteNo,
			QuoteInstructionType type) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_quoteNo", quoteNo);
		filterList.add(quoteFilter);
		if (type != null) {
			PropertyFilter typeFilter = new PropertyFilter("EQS_type", type);
			filterList.add(typeFilter);
		}
		List<QuoteNote> noteList = this.find(filterList);
		return noteList;
	}

	public String getFollowUpDate(Integer quoteNo) {
		String sql = "select noteDate from QuoteNote where type='FOLLOWUP_DATE'  and quoteNo=?   ORDER BY id DESC LIMIT 1";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date newDate = (Date) this.findUnique(sql, quoteNo);
		String date = "";
		if (newDate != null && !"".equals(newDate)) {
			date = sdf.format(newDate);
		}
		return date;
	}
}
