package com.zt.design.behavior.command;

/**
 * class SellStock implements Order
 * 
 * @author zengtao
 *
 */
public class SellStock implements Order {
	private Stock abcStock;

	public SellStock(Stock abcStock) {
		this.abcStock = abcStock;
	}

	public void execute() {
		abcStock.sell();
	}
}