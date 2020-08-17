/**
 * 
 */
package com.SIP.SIP_Application.models;

/**
 * @author Prince Bose
 *
 */
@SuppressWarnings("serial")
public class StockLookupException extends Exception{

	private String symbol;
	public StockLookupException() {}
	
	public StockLookupException(String message,String symbol) {
		super(message);
		this.symbol = symbol;
	}
	
	public StockLookupException(String message,Throwable cause) {
		super(message,cause);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage()+"\n\t: Unable to lookup for stock "+symbol;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

}
