/**
 * 
 */
package com.SIP.SIP_Application.models;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * @author Prince Bose
 *
 */
public class Stock {

	/**
	 * 
	 */
	private final String symbol;
	private final float price;
	private final String name;
	
	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param symbol
	 * @param price
	 * @param name
	 */
	public Stock(String symbol, String name, float price) {
		this.symbol = symbol;
		this.price = price;
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", price=" + price + ", name=" + name+ "]";
	}

	//URL  to fetch quotes - AlphaVantage Finance - Yahoo Deprecated
	private static final String urlHeadOverview = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=";
	private static final String urlTail = "&apikey=L1OXEBVWLBDFAMHU&datatype=csv";
	private static final String urlHeadPrice = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=";
	public static Stock lookupStock(String symbol) throws StockLookupException{
		
		//build URL to query Yahoo Finance
		URL url_Company_Overview = null;
		URL url_EndPoint_Price = null;
		
		try {
			url_Company_Overview = new URL(urlHeadOverview+symbol+urlTail);
			url_EndPoint_Price = new URL(urlHeadPrice+symbol+urlTail);
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
			throw new StockLookupException("Problem resolving URL", symbol);
		}
		

		//Fetch CSV data
		CSVParser parser1,parser2;
		Map<String,String> stockInfo = new HashMap<String, String>();
		try {
			parser1 = CSVParser.parse(url_Company_Overview, Charset.forName("UTF-8"), CSVFormat.DEFAULT);
			parser2 = CSVParser.parse(url_EndPoint_Price,Charset.forName("UTF-8"), CSVFormat.DEFAULT);
			
			//Parse and save records as a list
			List<CSVRecord> CompanyInfo = (parser1.getRecords());
			List<CSVRecord> PriceInfo = (parser2.getRecords());
			
			//get Symbol, Name and Price
			stockInfo.put("Symbol", CompanyInfo.get(1).get(0).split(":")[1]);
			stockInfo.put("Name", CompanyInfo.get(3).get(0).split(":")[1]);
			stockInfo.put("Price", PriceInfo.get(1).get(4));
			
			parser1.close();
			parser2.close();
		}
		catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
			throw new StockLookupException("Problem parsing the fetched data",symbol);
		}
		catch(IOException e) {
			e.printStackTrace();
			throw new StockLookupException("Problem parsing the fetched data",symbol);
		}
		
		if(stockInfo.get("Name").equals("N/A")||stockInfo.get("Symbol").equals("N/A")) {
			System.out.println("NA");
			throw new StockLookupException("Not a valid stock symbol",symbol);
		}

		return new Stock(stockInfo.get("Symbol"),stockInfo.get("Name"),Float.parseFloat(stockInfo.get("Price")));
		
	}
	
	public static void main(String args[]) throws StockLookupException{
		System.out.println(Stock.lookupStock("AAPL"));
		
	}

}
