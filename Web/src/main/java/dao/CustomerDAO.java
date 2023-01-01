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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.itextpdf.text.xml.simpleparser.NewLineHandler;

import model.Customer;
import model.OrderDetail;
import model.Orders;

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
			ps.execute();
			ps = conn.prepareStatement(" SELECT LAST_INSERT_ID()");
			ResultSet rs = ps.executeQuery();
			int lastId = 0;
			if (rs.next()) {
				lastId = rs.getInt(1);
				ps = conn.prepareStatement("insert into role_user(user_id, role_name) values(?,?)");
				ps.setInt(1, lastId);
				ps.setString(2, "USER");
				ps.execute();
				
			}
			
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
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ROLE_USER WHERE user_id = ?;");
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

	public List<Customer> getCustomers() {

		List<Customer> resultList = new ArrayList<Customer>();
		Map<Integer, Integer> mapOrders = new HashMap<>();
		Map<Integer, Integer> mapReviews = new HashMap<>();
		try {
			PreparedStatement ps = conn.prepareStatement("select users.id, users.last_name, users.first_name,users.email\r\n"
					+ "from users inner join role_user on users.id = role_user.user_id \r\n"
					);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id"); 
				String lastName = rs.getString("last_name").trim();
				String firstName = rs.getString("first_name").trim();
				String emails = rs.getString("email").trim();
				Customer customer =new Customer();
				customer.setId(id);
				customer.setLastName(lastName);
				customer.setFirstName(firstName);
				customer.setEmail(emails);
				resultList.add(customer);
			
			}
			ps = conn.prepareStatement("select user_id, count(user_id) as number_orders\r\n"
					+ "from orders where status_active = 'public'\r\n"
					+ "group by user_id;");
			ResultSet rs2 = ps.executeQuery();

			while (rs2.next()) {
				int id = Integer.parseInt(rs2.getString("user_id").trim()); 
				int numberOrders =  Integer.parseInt( rs2.getString("number_orders").trim());
				mapOrders.put(id, numberOrders);
											
			}
			ps = conn.prepareStatement("select user_id, count(user_id) as number_reviews\r\n"
					+ "from reviews\r\n"
					+ "group by user_id;");
			ResultSet rs3 = ps.executeQuery();

			while (rs3.next()) {
				int id = Integer.parseInt(rs3.getString("user_id").trim()); 
				int numberReviews =  Integer.parseInt( rs3.getString("number_reviews").trim());
				mapReviews.put(id, numberReviews)		;				
			}
			for(Customer customer: resultList) {
				Integer id1 = mapOrders.get(customer.getId());
				Integer id2 = mapReviews.get(customer.getId());
				customer.setNumberOrders(id1!=null?id1:0);
				customer.setNumberReviews(id2!=null?id2:0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}
	public Customer getInfo(int idUser) {
		Customer customer = new Customer();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from users inner join role_user on users.id = role_user.user_id  where users.id = ?");
			ps.setInt(1, idUser);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String lastName = rs.getString("last_name").trim();
				String firstName = rs.getString("first_name").trim();
				String emails = rs.getString("email").trim();
				int accountGoogle = rs.getInt("account_google");
				String roleName = rs.getString("role_name");
				String phoneNum  = rs.getString("phone_num");
				customer.setId(id);
				customer.setLastName(lastName);
				customer.setFirstName(firstName);
				customer.setEmail(emails);
				customer.setAccountGoogle(accountGoogle);
				customer.setRoleName(roleName);
				customer.setPhoneNum(phoneNum);
			
			}
			ps = conn.prepareStatement("select count(*) as num_orders from orders where user_id = ? and status_active = 'public'");
			ps.setInt(1, idUser);
			ResultSet rs2 = ps.executeQuery();
			if (rs2.next()) {
				customer.setNumberOrders(rs2.getInt("num_orders"));											
			}
			ps = conn.prepareStatement("select count(*) as num_reviews from reviews where user_id = ?");
			ps.setInt(1, idUser);
			ResultSet rs3 = ps.executeQuery();
			if (rs3.next()) {
				customer.setNumberReviews(rs3.getInt("num_reviews"));											
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	public Customer getCustomerById(int id) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users inner join role_user on users.id = role_user.user_id  where users.id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ids = rs.getInt("id");
				String lastName = rs.getString("last_name").trim();
				String firstName = rs.getString("first_name").trim();
				String emails = rs.getString("email").trim();
				String pass = rs.getString("password").trim();
				int accountGoogle = rs.getInt("account_google");
				String roleName = rs.getString("role_name");
				Customer customer = new Customer();
				customer.setRoleName(roleName);
				customer.setId(ids);
				customer.setEmail(emails);
				customer.setLastName(lastName);
				customer.setFirstName(firstName);
				customer.setPassword(pass);
				customer.setAccountGoogle(accountGoogle);			
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
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users inner join role_user on users.id = role_user.user_id where users.email = ? and users.account_google = ?");
			ps.setString(1, email);
			ps.setInt(2, ag);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ids = rs.getInt("id");
				String lastName = rs.getString("last_name").trim();
				String firstName = rs.getString("first_name").trim();
				String emails = rs.getString("email").trim();
				String pass = rs.getString("password").trim();
				int accountGoogle = rs.getInt("account_google");
				String roleName = rs.getString("role_name");
				Customer customer = new Customer();
				customer.setRoleName(roleName);
				customer.setId(ids);
				customer.setEmail(emails);
				customer.setLastName(lastName);
				customer.setFirstName(firstName);
				customer.setPassword(pass);
				customer.setAccountGoogle(accountGoogle);			
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
		CustomerDAO customerDAO = new CustomerDAO();
		for(Customer customer : customerDAO.getCustomers()) {
			System.out.println(customer);
		}
	}

}
