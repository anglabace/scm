package com.genscript.gsscm.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public abstract class BaseAction <T> extends ActionSupport implements ModelDriven<T>, Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5431924321070927312L;
	

	/** Additions or deletions to change operation, in order to redirect the default way to re-open the action page of the result name.*/
	public static final String RELOAD = "reload";

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Action function, the default action function, the default calling list () function.
	 */
	@Override
	public String execute() throws Exception {
		return list();
	}

	//-- CRUD Action Function--//
	/**
	 * Action function, display a list of interface Entity.
	 * Proposed return SUCCESS.
	 */
	public abstract String list() throws Exception;

	/**
	 * Action function, display interface to add or modify the Entity.
	 * Proposed return INPUT.
	 */
	@Override
	public abstract String input() throws Exception;

	/**
	 * Action function, add or modify Entity. 
	 * Proposed return RELOAD.
	 */
	public abstract String save() throws Exception;

	/**
	 * Action function, delete the Entity.
	 * Proposed return RELOAD.
	 */
	public abstract String delete() throws Exception;

	//-- Preparable Function --//
	/**
	 * To achieve an empty prepare () function, to block all public secondary bound Action functions.
	 */
	public void prepare() throws Exception {
	}

	/**
	 * In input () before the implementation of the second binding.
	 */
	public void prepareInput() throws Exception {
		prepareModel();
	}

	/**
	 * In the save () before the implementation of the second binding.
	 */
	public void prepareSave() throws Exception {
		prepareModel();
	}

	/**
	 * Equivalent to prepare () internal function for prepardMethodName () function call. 
	 */
	protected abstract void prepareModel() throws Exception;

	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
