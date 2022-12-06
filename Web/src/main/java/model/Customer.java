package model;
public class Customer {
	int id;
	String firstName;
	String lastName;
	String email;
	String password;
	int numberOrders;
	int numberReviews;
	boolean isLogin;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getNumberOrders() {
		return numberOrders;
	}
	public void setNumberOrders(int numberOrders) {
		this.numberOrders = numberOrders;
	}
	public int getNumberReviews() {
		return numberReviews;
	}
	public void setNumberReviews(int numberReviews) {
		this.numberReviews = numberReviews;
	}
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", numberOrders=" + numberOrders + ", numberReviews=" + numberReviews
				+ ", isLogin=" + isLogin + "]";
	}
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Customer that = (Customer) o;
	    return id == that.id;
	}
	

}
