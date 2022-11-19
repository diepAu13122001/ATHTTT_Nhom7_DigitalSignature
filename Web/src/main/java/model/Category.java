package model;

public class Category {
	String idCate;
	String nameCate;
	int numOfProduct;
	public Category(String idCate, String nameCate,int numOfProduct) {
		super();
		this.idCate = idCate;
		this.nameCate = nameCate;
		this.numOfProduct = numOfProduct;
	}
	public String getIdCate() {
		return idCate;
	}
	public void setIdCate(String idCate) {
		this.idCate = idCate;
	}
	public String getNameCate() {
		return nameCate;
	}
	@Override
	public String toString() {
		return "Category [idCate=" + idCate + ", nameCate=" + nameCate + ", numOfProduct=" + numOfProduct + "]";
	}
	public void setNameCate(String nameCate) {
		this.nameCate = nameCate;
	}
	public int getNumOfProduct() {
		return numOfProduct;
	}
	public void setNumOfProduct(int numOfProduct) {
		this.numOfProduct = numOfProduct;
	}

	
	
}
