package model;



public class Review {
	int id;
	String content;
	String rating;
	String dateReview;
	String nameCustomer;
	public String getNameCustomer() {
		return nameCustomer;
	}
	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getDateReview() {
		return dateReview;
	}
	public void setDateReview(String dateReview) {
		this.dateReview = dateReview;
	}
	
	
	

	
}
