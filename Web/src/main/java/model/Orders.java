package model;

import java.util.List;

public class Orders {
	int id;
	int userId;
	Customer customer;
	String dateCreate;
	String nameReceiver;

	String phoneNum;
	String email;
	String address;
	String addressDetail;
	double grandPrice;
	double discount;
	Shipping shipping;
	int payment;
	int authentication;
	List<OrderDetail> orderDetails;
	public double totalPrice() {
		double result=0;
		for(OrderDetail orderDetail : orderDetails) {
			result+=orderDetail.quantity*orderDetail.product.price;
		}
		return result/10;
	}
	public String getNameReceiver() {
		return nameReceiver;
	}
	public void setNameReceiver(String nameReceiver) {
		this.nameReceiver = nameReceiver;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String addres) {
		this.address = addres;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public double getGrandPrice() {
		return grandPrice;
	}
	public void setGrandPrice(double grandPrice) {
		this.grandPrice = grandPrice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public Shipping getShipping() {
		return shipping;
	}
	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public int getAuthentication() {
		return authentication;
	}
	public void setAuthentication(int authentication) {
		this.authentication = authentication;
	}
	
	
}
