package com.genscript.gsscm.common.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.*;

public class SaveButtTag extends TagSupport{
	private static final long serialVersionUID = -2485371296927860057L;
	private String parameter = "";
	private String saveBtn = "";
	private String disabledBtn = "";

	public int doStartTag() throws JspException {    
		try {
	    	JspWriter out = pageContext.getOut();   
	    	if (parameter != null && !("").equals(parameter.trim())) {
	    		if (("edit").equals(parameter.trim())) {
		    		out.print(saveBtn);
	    		} else if (("view").equals(parameter.trim())) {
	    			out.print(disabledBtn);
	    		} else {
	    			out.print(disabledBtn+"<script language=\"javascript\" type=\"text/javascript\">alert(\"This record is being edited by "+parameter.trim()+", you can only view it.\");</script>");
	     		}
	    	} else {
	    		out.print(saveBtn);
	    	}
	    } catch (java.io.IOException e) {
	      throw new JspTagException(e.getMessage());
	    }
	    return SKIP_BODY; 
	}
	 
	  
    public void setParameter(String parameter) {
	    this.parameter = parameter;
    } 
  
    public String getParameter() {
	    return parameter;
    }


	public String getSaveBtn() {
		return saveBtn;
	}


	public void setSaveBtn(String saveBtn) {
		this.saveBtn = saveBtn;
	}


	public String getDisabledBtn() {
		return disabledBtn;
	}


	public void setDisabledBtn(String disabledBtn) {
		this.disabledBtn = disabledBtn;
	}

}
