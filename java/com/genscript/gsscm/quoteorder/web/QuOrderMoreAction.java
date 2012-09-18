package com.genscript.gsscm.quoteorder.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.biolib.entity.PeptideCode;
import com.genscript.gsscm.biolib.service.BiolibService;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.quoteorder.dto.EnzymeDTO;

@Results({
	@Result(name="enzyme_list", location="quoteorder/quorder_more_search_enzyme.jsp"),
	@Result(name = "viewSequenceList", location = "quoteorder/quoteorder_more_peptide_seq_select.jsp"),
	@Result(name = "showVocter", location = "quoteorder/puc57_map.jsp")
})
public class QuOrderMoreAction extends BaseAction<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7560392727877799606L;
	@Autowired
	public PublicService publicService;
	@Autowired
	private BiolibService biolibService;
	
	//private List<DropDownDTO> sequence;
	private List<EnzymeDTO> sequence;
	private List<PeptideCode> peptideCodeList;

	public String searchEnzyme() throws Exception{
		//sequence = publicService.getSpecDropDownList(SpecDropDownListName.SEQUENCE);
		//sequence = publicService.getEnzymeSequence();
		return "enzyme_list";
	}
	
	public String searchEnzymeList() throws Exception{
		//sequence = publicService.getSpecDropDownList(SpecDropDownListName.SEQUENCE);
		sequence = publicService.getEnzymeSequence();
		Struts2Util.renderJson(sequence);
		return null;
	}
	/**
	 * show Vecter Map
	 * 
	 * @return String
	 * throws Exception
	 */
	public String showVector(){
		return "showVocter";
	}
	
	public String viewSequenceList() throws Exception {
		peptideCodeList = biolibService.getPeptideCodeList();
		return "viewSequenceList";
	}
	
	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<EnzymeDTO> getSequence() {
		return sequence;
	}
	public void setSequence(List<EnzymeDTO> sequence) {
		this.sequence = sequence;
	}
	public List<PeptideCode> getPeptideCodeList() {
		return peptideCodeList;
	}

	public void setPeptideCodeList(List<PeptideCode> peptideCodeList) {
		this.peptideCodeList = peptideCodeList;
	}

}
