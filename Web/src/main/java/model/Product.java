package model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cart.Item;

public class Product implements Item{
	String idProduct;
	String nameProduct;
	String description;
	double price;
	String image;
	String category_name;
	int popular;
	public String getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price/10;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getPopular() {
		return popular;
	}
	public void setPopular(int popular) {
		this.popular = popular;
	}
	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", nameProduct=" + nameProduct + ", description=" + description
				+ ", price=" + price + ", image=" + image + ", category_name=" + category_name + ", popular=" + popular
				+ "]";
	}
	public int convertToInt(double num) {
		return (int) num;
	}
	
}
