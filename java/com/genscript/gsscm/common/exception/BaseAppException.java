package com.genscript.gsscm.common.exception;

public class BaseAppException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3874236093575263358L;
	
	/**
     * the code of the BaseAppException.
     */
	private String exceptionCode;
	
	/**
     * A public constructor for BaseAppException containing no arguments.
     *  
     */
    public BaseAppException() {
    }
    
    /**
     * A public constructor for BaseAppException specifying exception message.
     * <p>
     * 
     * @param msg
     *            exception message.
     * 
     *  @param code
     *  		  exception code.
     */
    public BaseAppException(String code) {
        this.exceptionCode = code;
    }
    
    /**
     * A public constructor of <code>BaseAppException</code> containing
     * message and root cause (as <code>Throwable</code>) of the exception.
     * 
     * @param code
     *            exception code.
     * @param e
     *            Throwable object.
     *  
     */
    public BaseAppException(String code, Throwable e) {
        this.exceptionCode = code;
        this.initCause(e);
    }
    /**
     * sets the root cause of the exception. Used for setting Java built in
     * exception in <code>BaseAppException</code>.
     * 
     * @param e
     *            Throwable object.
     *  
     */
    public void setCause(Throwable e) {
        this.initCause(e);
    }

    /*
     * Gets the class name and exception message.
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String s = getClass().getName();
        return s + ": " + exceptionCode;
    }


    
    public String getCode() {
        return exceptionCode;
    }
    
    public void setCode(String code) {
        this.exceptionCode = code;
    }
}
