package ch.bbcag.gamexchange.highlevel.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Item {

	private Long itemId;
	private String title;
	private String description;
	private Double price;
	private String foto;
	private String sellerName;
	private String buyerName;
	private String sold;
	
	public String toString() {
	    return "[" + getItemId() + "," + getTitle() + "," + getDescription() + "," + getPrice() + "," + getSellerName() + ","
	        + getBuyerName() + "," + getSold() + "]";
	}
	
	public boolean getItemById(int id) {
		ResultSet resultSet;
		Boolean success = false;

		return success;
	}

	public String insertItem() {
		String userId = null;
		String sqlQuery;

		return userId;
	}
	
	public boolean updateItem(Item item) {
		Boolean success = false;
		String sqlQuery;
		
		return success;
	}

	public boolean deleteItem(Item item) {
		Boolean success = false;

		return success;
	}
	
	public Long getItemId() {
		return itemId;
	}
	
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public String getSellerName() {
		return sellerName;
	}
	
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
	public String getBuyerName() {
		return buyerName;
	}
	
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	
	public String getSold() {
		return sold;
	}
	
	public void setSold(String sold) {
		this.sold = sold;
	}
}
