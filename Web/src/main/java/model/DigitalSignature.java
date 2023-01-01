package model;

public class DigitalSignature {
	int idUser;
	int idOrder;
	String signture;
	String publicKey;
	String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public String getSignture() {
		return signture;
	}
	public void setSignture(String signture) {
		this.signture = signture;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
}
