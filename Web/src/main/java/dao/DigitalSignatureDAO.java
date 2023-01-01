package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import io.WritePDF;
import model.DigitalSignature;

public class DigitalSignatureDAO {
	DBConnect db;
	Connection conn;

	public DigitalSignatureDAO() throws SQLException {
		db = new DBConnect();
		conn = db.getConnection();
	}

	public boolean insertDigitalSignature(int userId, int ordersId, String hashString) {
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

	public boolean inserKeyUser(int userId, String publicKey) {
		try {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO `freshop_db`.`key_user` (`user_id`, `public_key`, `active`,`date_create`) VALUES (?, ?, ?,?);");
			ps.setInt(1, userId);
			ps.setString(2, publicKey);
			ps.setInt(3, 1);
			ps.setString(4, WritePDF.formatDate(LocalDateTime.now()));
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean inserDigitalSignature(int userId, int idOrder, String signature, String publicKey) {
		try {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO `freshop_db`.`digital_signature` (`user_id`, `order_id`, `signature`, `public-key`) VALUES (?,?,?,?);\r\n"
							+ "");
			ps.setInt(1, userId);
			ps.setInt(2, idOrder);
			ps.setString(3, signature);
			ps.setString(4, publicKey);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateDigitalSignature(int userId, int idOrder, String signature, String publicKey) {
		try {
			PreparedStatement ps = conn.prepareStatement(
					"update `freshop_db`.`digital_signature` set `signature` = ?, `public-key` = ? where `user_id` = ?  and `order_id` = ?;\r\n"
							+ "");
			ps.setString(1, signature);
			ps.setString(2, publicKey);
			ps.setInt(3, userId);
			ps.setInt(4, idOrder);
			
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateStatus(int userId, int idOrder, String status) {
		try {
			PreparedStatement ps = conn.prepareStatement(
					"update `freshop_db`.`digital_signature` set `status` = ? where `user_id` = ?  and `order_id` = ?;\r\n"
							+ "");
			ps.setString(1, status);
			ps.setInt(2, userId);
			ps.setInt(3, idOrder);
			
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public String getHashString(int userId, int ordersId) {
		try {
			PreparedStatement ps = conn
					.prepareStatement("select hash_string from digital_signature where user_id = ? and order_id = ?");
			ps.setInt(1, userId);
			ps.setInt(2, ordersId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("hash_string");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public DigitalSignature getDigitalSignature(int idUser,int idOrder) {
		try {
			PreparedStatement ps = conn
					.prepareStatement("select * from digital_signature where user_id = ? and order_id = ?");
			ps.setInt(1, idUser);
			ps.setInt(2, idOrder);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				DigitalSignature digitalSignature = new DigitalSignature();
				digitalSignature.setIdOrder(rs.getInt("user_id"));
				digitalSignature.setIdUser(rs.getInt("order_id"));
				digitalSignature.setSignture(rs.getString("signature"));
				digitalSignature.setPublicKey(rs.getString("public-key"));
				digitalSignature.setStatus(rs.getString("status"));
				return digitalSignature;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean checkExistKey(int userId) {
		try {
			PreparedStatement ps = conn.prepareStatement("select * from key_user where user_id = ?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) throws SQLException {
		DigitalSignatureDAO digitalSignatureDAO = new DigitalSignatureDAO();
		System.out.println(digitalSignatureDAO.getDigitalSignature(1000, 63).getStatus());
	}

}
