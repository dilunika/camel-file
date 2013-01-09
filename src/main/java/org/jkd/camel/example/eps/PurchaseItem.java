package org.jkd.camel.example.eps;

public class PurchaseItem {

	private String itemCode;
	
	private int quantity;
	
	private double amount;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "PurchaseItem [itemCode=" + itemCode + ", quantity=" + quantity
				+ ", amount=" + amount + "]";
	}
}
