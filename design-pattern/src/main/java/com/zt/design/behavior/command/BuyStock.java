package com.zt.design.behavior.command;

/**
 * class BuyStock implements Order
 * 
 * @author zengtao
 *
 */
public class BuyStock implements Order {
	private Stock abcStock;

	public BuyStock(Stock abcStock) {
		this.abcStock = abcStock;
	}

	public void execute() {
		abcStock.buy();
	}
}