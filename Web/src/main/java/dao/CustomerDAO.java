package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import model.Customer;

public class CustomerDAO {
	DBConnect db;
	Connection conn;

	public CustomerDAO() throws SQLException {
		db = new DBConnect();
		conn = db.getConnection();
	}

	public boolean insertCustomer(Customer customer,int accountGoogle) {
		boolean result = true;
		try {
			PreparedStatement ps = conn.prepareStatement("insert into users values(?,?,?,?,md5(?),?)");
			ps.setInt(1, customer.getId());
			ps.setString(2, customer.getFirstName());
			ps.setString(3, customer.getLastName());
			ps.setString(4, customer.getEmail());
			ps.setString(5, customer.getPassword());
			ps.setInt(6, accountGoogle);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			ps.executeUpdate();
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	public void insertRole(String role, int idCustomer) {
		try {
			PreparedStatement ps = conn.prepareStatement("insert into role_user(user_id, role_name) values(?,?)");
			ps.setInt(1, idCustomer);
			ps.setString(2, role);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getRole(int idUser) {
		String role = "";
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ROLE_USER WHERE id = ?;");
			ps.setInt(1, idUser);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				role = rs.getString("role_name").trim();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}

	public List<Customer> getListCustomer() {

		List<Customer> resultList = new ArrayList<Customer>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM KHACHHANG;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String makh = rs.getString(1).trim();
				String lastName = rs.getString(2).trim();
				String firstName = rs.getString(3).trim();
				String emails = rs.getString(4).trim();
				String pass = rs.getString(5).trim();
				// Customer kh = new Customer(makh, firstName, lastName, emails, pass);
				// resultList.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public Customer getCustomerById(String id) {

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ids = rs.getInt(1);
				String lastName = rs.getString(2).trim();
				String firstName = rs.getString(3).trim();
				String emails = rs.getString(4).trim();
				String pass = rs.getString(5).trim();
				Customer customer = new Customer();
				customer.setId(ids);
				customer.setEmail(emails);
				customer.setLastName(lastName);
				customer.setFirstName(firstName);
				customer.setPassword(pass);
				return customer;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public Customer findByEmail(String email, int ag) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email = ? and account_google = ?");
			ps.setString(1, email);
			ps.setInt(2, ag);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ids = rs.getInt(1);
				String lastName = rs.getString(2).trim();
				String firstName = rs.getString(3).trim();
				String emails = rs.getString(4).trim();
				String pass = rs.getString(5).trim();
				Customer customer = new Customer();
				customer.setId(ids);
				customer.setEmail(emails);
				customer.setLastName(lastName);
				customer.setFirstName(firstName);
				customer.setPassword(pass);
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean checkLogin(String email, String pass) {

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email = ? and password = md5(?) and account_google = 0");
			ps.setString(1, email);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

		}
		return false;
	}

	public boolean isEmailExist(String email) {

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email = ? and account_google = 0");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

		}
		return false;
	}
	public boolean isAccountGoogleExist(String email) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email = ? and account_google = 1");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

		}
		return false;
	}
	public int removeReview(List<String> ids) {
		int count = 0;
		try {
			PreparedStatement ps;
			for (String id : ids) {
				ps = conn.prepareStatement("delete from NHANXET where MANX =?");
				ps.setString(1, id);
				int row = ps.executeUpdate();
				count += row;
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

//	public void remove() {
//		db.remove();
//	}
	public static void main(String[] args) throws SQLException {
		List<String> ids = new ArrayList<String>();
		ids.add("NX-8EBkJqL");
		ids.add("NX-biyZkhC");
		CustomerDAO dao = new CustomerDAO();
		System.out.println(dao.removeReview(ids));
	}

}
