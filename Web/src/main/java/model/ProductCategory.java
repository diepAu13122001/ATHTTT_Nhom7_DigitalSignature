package model;

public class ProductCategory {
	String idProduct;
	String namCategory;
	public ProductCategory(String idProduct, String namCategory) {
		super();
		this.idProduct = idProduct;
		this.namCategory = namCategory;
	}
	public String getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}
	public String getNamCategory() {
		return namCategory;
	}
	public void setNamCategory(String namCategory) {
		this.namCategory = namCategory;
	}
	
}
