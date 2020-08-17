/**
 * 
 */
package com.SIP.SIP_Application.models;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Prince Bose
 *
 */

@Entity
@Table(name="stock_holdings")
public class StockHolding extends AbstractEntity {
	
	private String symbol;
	private int sharesOwned;
	private int ownerId;
	
	/**
	 * Past transactions in this stock
	 */
	

}
