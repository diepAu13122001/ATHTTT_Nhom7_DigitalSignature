package model;

import java.lang.invoke.StringConcatFactory;
import java.util.List;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class Orders {
	int id;
	int userId;
	Customer customer;
	String dateCreate;
	String nameReceiver;
	String note;
	String phoneNum;
	String email;
	String address;
	String addressDetail;
	double grandPrice;
	double discount;
	String fileInvoice;
	String cancelTime;
	Shipping shipping;
	String status;
	String statusName;
	int parent;
	List<OrderDetail> orderDetails;
	public double totalPrice() {
		double result=0;
		for(OrderDetail orderDetail : orderDetails) {
			result+=orderDetail.quantity*orderDetail.product.price;
		}
		return result;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getParent() {
		return parent;
	}
	public String getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getFileInvoice() {
		return fileInvoice;
	}
	public void setFileInvoice(String fileInvoice) {
		this.fileInvoice = fileInvoice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String status() {
		String result = "";
		switch (status) {
		case "NA":
			result = "Ch??a x??c th???c";
			break;
		case "NP":
			result = "Ch??a thanh to??n";
			break;
		case "PS":
			result = "???? thanh to??n";
			break;
		case "SS":
			result = "???? giao h??ng";
			break;
		case "CO":
			result = "???? hu???";
			break;

		default:
			break;
		}
		return result;
	}
	
	
	
}
