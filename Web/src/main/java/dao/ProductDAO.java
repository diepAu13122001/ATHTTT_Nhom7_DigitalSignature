package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import cart.ShoppingCartItem;
import io.WritePDF;
import model.Category;
import model.Customer;
import model.Orders;
import model.Product;
import model.ProductCategory;
import model.Review;
import model.Shipping;

public class ProductDAO {
	DBConnect db;
	Connection conn;

	public ProductDAO() throws SQLException {
		db = new DBConnect();
		conn = db.getConnection();
	}

	public boolean insertProduct(String id, String name, String des, double price, String linkI, String idCato,
			String linkL) {
		boolean result = true;
		try {

			PreparedStatement ps = conn.prepareStatement("insert into products values(?,?,?,?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, des);
			ps.setDouble(4, price);
			ps.setString(5, linkI);
			ps.setString(6, idCato);
			ps.setString(7, linkL);
			ps.executeUpdate();
			//

		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	public Shipping getShippingById(int id) {
		try {
			PreparedStatement ps = conn.prepareStatement("select * from shipping where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ids = rs.getInt("id");
				String type = rs.getString("type");
				double price = rs.getDouble("price");
				Shipping shipping = new Shipping();
				shipping.setId(ids);
				shipping.setPrice(price);
				shipping.setType(type);
				return shipping;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public List<Orders> getOrders() {
		List<Orders> orders = new ArrayList<>();
		try {		
			PreparedStatement ps = conn.prepareStatement("select * from orders");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String dateCreate = rs.getString("issue_date");
				String phoneNum = rs.getString("phone_num");
				String address = rs.getString("address");
				double grandTotal = rs.getDouble("grand_price");
				int payment = rs.getInt("payment");
				int authentication = rs.getInt("authentication");
				Orders order = new Orders();
				order.setId(id);
				order.setDateCreate(dateCreate);
				order.setPhoneNum(phoneNum);
				order.setAddress(address);
				order.setGrandPrice(grandTotal);
				order.setPayment(payment);
				order.setAuthentication(authentication);
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertOrders(int userId, String name, String phoneNumber, String email, String address,
			String desAddress, int payement, LocalDateTime dateIssue, List<ShoppingCartItem<Product>> orders,
			double discount, int shipId, double grandTotal) {
		boolean result = true;
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO "
					+ "`freshop_db`.`orders` (`user_id`, `issue_date`, `phone_num`, `email`, `ship_id`, `payment`, `grand_price`, `address`, `address_detail`, `discount`, `authentication`)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			ps.setInt(1, userId);
			ps.setString(2, WritePDF.formatDate(dateIssue));
			ps.setString(3, phoneNumber);
			ps.setString(4, email);
			ps.setInt(5, shipId);
			ps.setInt(6, payement);
			ps.setDouble(7, grandTotal);
			ps.setString(8, address);
			ps.setString(9, desAddress);
			ps.setDouble(10, discount);
			ps.setDouble(11, 0);
			ps.execute();
			ps = conn.prepareStatement(" SELECT LAST_INSERT_ID()");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int lastId = rs.getInt(1);
				System.out.println(lastId + " last id");
				for (ShoppingCartItem<Product> cartItem : orders) {
					ps = conn.prepareStatement("insert into order_detail values(?,?,?)");
					ps.setInt(1, cartItem.getItem().getIdProduct());
					ps.setInt(2, lastId);
					ps.setInt(3, cartItem.getQuantity());
					ps.execute();

				}
			}
			ps.close();
			conn.commit();

		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;

	}

	public List<Category> getListCategories() {
		List<Category> resultList = new ArrayList<Category>();
		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT products.category_id,categories.category_name , COUNT(*) AS count \r\n"
							+ "FROM  products inner join categories on products.category_id = categories.id\r\n"
							+ "GROUP  BY products.category_id ;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("category_id");
				String name = rs.getString("category_name");
				int quantity = rs.getInt("count");
				Category category = new Category(id, name, quantity);
				resultList.add(category);
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

//	public List<Product> getListProduct() {
//		List<Product> resultList = new ArrayList<Product>();
//		try {
//			conn = db.getConnection();
//			PreparedStatement ps = conn.prepareStatement(
//					" select products.id,products.product_name, products.desciption, products.prices, products.image, categories.category_name\r\n"
//							+ "from products inner join categories on products.category_id = categories.id");
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				int ids = rs.getInt("id").trim();
//				String name = rs.getString("product_name").trim();
//				String description = rs.getString("desciption").trim();
//				double price = Double.parseDouble(rs.getString("prices"));
//				String linkImage = rs.getString("image").trim();
//				Product product = new Product();
//				product.setIdProduct(ids);
//				product.setDescription(description);
//				product.setNameProduct(name);
//				product.setPrice(price);
//				product.setImage(linkImage);
//				product.setCategory_name(rs.getString(6).trim());
//				resultList.add(product);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return resultList;
//	}

	public List<Category> numProductofCategory() {
		List<Category> resultList = new ArrayList<Category>();
		try {

			PreparedStatement ps = conn
					.prepareStatement("select MALOAI, COUNT(*) AS NUM " + "From SANPHAM " + "Group by MALOAI");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				int count = Integer.parseInt(rs.getString(2));
				Category category = new Category(id, "", count);
				resultList.add(category);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public Product getProductById(int id) {
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select products.id,products.product_name, products.desciption, products.prices, products.image, categories.category_name\r\n"
							+ " from products inner join categories on products.category_id = categories.id where products.id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ids = rs.getInt("id");
				String name = rs.getString("product_name").trim();
				String description = rs.getString("desciption").trim();
				double price = Double.parseDouble(rs.getString("prices"));
				String linkImage = rs.getString("image").trim();
				Product product = new Product();
				product.setIdProduct(ids);
				product.setDescription(description);
				product.setNameProduct(name);
				product.setPrice(price);
				product.setImage(linkImage);
				product.setCategory_name(rs.getString(6).trim());
				return product;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

//	public void remove() {
//		db.remove();
//	}
	public List<Product> getListSearch(String search, double startValue, double endValue) {

		List<Product> resultList = new ArrayList<Product>();
		try {

			PreparedStatement ps = conn.prepareStatement(
					"select products.id,products.product_name, products.desciption, products.prices, products.image, categories.category_name\r\n"
							+ " from products inner join categories on products.category_id = categories.id where categories.id = ? or products.product_name like ? or categories.category_name like ?");
			String search1 = "";
			if (search != "") {
				search1 = "%" + search + " %";
			}

			ps.setString(1, search);
			ps.setString(2, search1);
			ps.setString(3, search1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ids = rs.getInt("id");
				String name = rs.getString("product_name").trim();
				String description = rs.getString("desciption").trim();
				double price = Double.parseDouble(rs.getString("prices"));
				String linkImage = rs.getString("image").trim();
				Product product = new Product();
				product.setIdProduct(ids);
				product.setDescription(description);
				product.setNameProduct(name);
				product.setPrice(price);
				product.setImage(linkImage);
				product.setCategory_name(rs.getString(6).trim());
				resultList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public List<Product> pagdingProduct(int index, int quantity) {

		List<Product> resultList = new ArrayList<Product>();
		try {

			PreparedStatement ps = conn.prepareStatement(
					"select products.id,products.product_name, products.desciption, products.prices, products.image, categories.category_name\r\n"
							+ " from products inner join categories on products.category_id = categories.id order by products.id\r\n"
							+ "limit ?,?");
			ps.setInt(1, index * quantity);
			ps.setInt(2, quantity);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ids = rs.getInt("id");
				String name = rs.getString("product_name").trim();
				String description = rs.getString("desciption").trim();
				double price = Double.parseDouble(rs.getString("prices"));
				String linkImage = rs.getString("image").trim();
				Product product = new Product();
				product.setIdProduct(ids);
				product.setDescription(description);
				product.setNameProduct(name);
				product.setPrice(price);
				product.setImage(linkImage);
				product.setCategory_name(rs.getString(6).trim());
				resultList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public int getTotalProduct() {
		try {

			PreparedStatement ps = conn.prepareStatement("select COUNT(*) from products");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Review> getListReviews(int idProduct) {
		List<Review> resultList = new ArrayList<Review>();
		try {

			PreparedStatement ps = conn.prepareStatement(
					"select reviews.id, reviews.contents,reviews.stars,reviews.date_review,users.first_name "
							+ " from reviews inner join users on reviews.user_id = users.id  where  product_id = ?");
			ps.setInt(1, idProduct);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String content = rs.getString("contents");
				String star = rs.getString("stars");
				String name = rs.getString("first_name");
				// LocalDateTime dateReview = LocalDateTime.now();
				Date date = rs.getDate("date_review");
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:MM:ss");
				String dateStr = df.format(date);
				System.out.println(date);
				Review review = new Review();
				review.setId(id);
				review.setContent(content);
				review.setRating(star);
				review.setDateReview(dateStr);
				review.setNameCustomer(name);
				resultList.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public int numOfReview(String id) {
		try {

			PreparedStatement ps = conn
					.prepareStatement("select  COUNT(MAKH) \r\n" + "from NHANXET\r\n" + "where MASP=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void insertReview(String content, int star, Date dateReview, int idProduct, int idCustomer) {
		try {

			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO `freshop_db`.`reviews` (`user_id`, `product_id`, `contents`, `stars`, `date_review`) VALUES (?,?,?,?,?);");
			ps.setInt(1, idCustomer);
			ps.setInt(2, idProduct);
			ps.setString(3, content);
			ps.setInt(4, star);
			ps.setDate(5, dateReview);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertCategory(String id, String name) {
		try {

			PreparedStatement ps = conn.prepareStatement("insert into LOAI values(?,?)");
			ps.setString(1, id);
			ps.setString(2, name);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeCategory(String[] ids) {
		try {
			for (String id : ids) {
				PreparedStatement ps = conn.prepareStatement("delete from LOAI where MALOAI = ?");
				ps.setString(1, id);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ProductCategory> getNameCateogorys() {
		List<ProductCategory> resultList = new ArrayList<ProductCategory>();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select MASP, TENLOAI from SANPHAM inner join LOAI on SANPHAM.MALOAI = LOAI.MALOAI\r\n");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("MASP");
				String name = rs.getString("TENLOAI");
				ProductCategory product = new ProductCategory(id, name);
				resultList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public void updateCategory(String id, String name) {
		try {
			PreparedStatement ps = conn.prepareStatement("update LOAI SET TENLOAI = ? where MALOAI = ? ");
			ps.setString(1, name);
			ps.setString(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Category findCategoryById(String id) {
		Category category = null;
		try {

			PreparedStatement ps = conn.prepareStatement("select * from LOAI where MALOAI = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String ids = rs.getString("MALOAI");
				String name = rs.getString("TENLOAI");
				category = new Category(ids, name, 0);
				return category;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void udpateProduct(String id, String name, String des, String price, String linkI, String category,
			String linkL) {

		try {
			PreparedStatement ps;
			double prices = Double.parseDouble(price);
			ps = conn.prepareStatement(
					"update SANPHAM set TENSP = ?, MOTA = ?, GIA = ?, MALOAI = ?, LINK_IMAGE= ?, LINK_LIST = ?  where MASP = ? ");
			ps.setString(1, name);
			ps.setString(2, des);
			ps.setDouble(3, prices);
			ps.setString(4, category);
			ps.setString(5, linkI);
			ps.setString(6, linkL);
			ps.setString(7, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		ProductDAO productDAO = new ProductDAO();
		System.out.println(productDAO.getOrders().size());
	}
}
