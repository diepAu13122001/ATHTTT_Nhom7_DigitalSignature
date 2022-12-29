package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;


public class DigitalSignatureDAO {
	DBConnect db;
	Connection conn;
	
	public DigitalSignatureDAO() throws SQLException {
		db = new DBConnect();
		conn = db.getConnection();
	}
	public boolean insertDigitalSignature(int userId,int ordersId,String hashString) {
		try {
			PreparedStatement ps = conn.prepareStatement("insert into digital_signature values(?,?,?)");
			ps.setInt(1, userId);
			ps.setInt(2, ordersId);
			ps.setString(3, hashString);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean inserKeyUser(int userId,String publicKey) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `freshop_db`.`key_user` (`user_id`, `public_key`, `active`) VALUES (?, ?, ?);");
			ps.setInt(1, userId);
			ps.setString(2, publicKey);
			ps.setInt(3, 1);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public String getHashString(int userId,int ordersId) {
		try {
			PreparedStatement ps = conn.prepareStatement("select hash_string from digital_signature where user_id = ? and order_id = ?");
			ps.setInt(1, userId);
			ps.setInt(2, ordersId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString("hash_string");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean checkExistKey(int userId) {
		try {
			PreparedStatement ps = conn.prepareStatement("select * from key_user where user_id = ?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static void main(String[] args) throws SQLException {
		DigitalSignatureDAO digitalSignatureDAO = new DigitalSignatureDAO();
		System.out.println(digitalSignatureDAO.getHashString(1003, 22));
	}
	
}
