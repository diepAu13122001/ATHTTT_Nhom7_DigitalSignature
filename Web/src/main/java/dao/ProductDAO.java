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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cart.ShoppingCartItem;
import io.WritePDF;
import model.Category;
import model.Customer;
import model.OrderDetail;
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

	public boolean insertProduct(String name, String des, double price, int categoryId, String image) {
		boolean result = true;
		try {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO `freshop_db`.`products` (`product_name`, `desciption`, `prices`, `category_id`, `image`,`isDelete`) "
							+ "VALUES (?, ?, ?, ?, ?, 0);");
			ps.setString(1, name);
			ps.setString(2, des);
			ps.setDouble(3, price);
			ps.setInt(4, categoryId);
			ps.setString(5, image);
			ps.executeUpdate();
			//

		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	public boolean updateProduct(String name, String des, double price, int categoryId, String image, int id) {
		boolean result = true;
		try {
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE products set product_name = ?, desciption = ?, prices= ?, category_id = ?, image = ?  "
					+ " where id = ?");
							
			ps.setString(1, name);
			ps.setString(2, des);
			ps.setDouble(3, price);
			ps.setInt(4, categoryId);
			ps.setString(5, image);
			ps.setInt(6, id);
			ps.executeUpdate();
			//

		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	public boolean deleteProduct(int id) {
		boolean result = true;
		try {
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE products set isDelete = 1 "
					+ " where id = ?");							
			ps.setInt(1, id);
			ps.executeUpdate();
			//

		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	public boolean updateOrder(int id, String status) {
		try {
			PreparedStatement ps = conn
					.prepareStatement("UPDATE orders SET status = ?,date_modified = ?" + " where id = ? ");
			ps.setString(1, status);
			ps.setString(2, WritePDF.formatDate(LocalDateTime.now()));
			ps.setInt(3, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Map<String, String> getStutusOrder() {
		try {
			Map<String, String> map = new HashMap<>();
			PreparedStatement ps = conn.prepareStatement("select * from status_order");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("status_code"), rs.getString("status_name"));
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
			PreparedStatement ps = conn.prepareStatement("select * from orders inner join status_order on \r\n"
					+ "orders.status = status_order.status_code  order by id desc");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String dateCreate = rs.getString("issue_date");
				String phoneNum = rs.getString("phone_num");
				String address = rs.getString("address");
				double grandTotal = rs.getDouble("grand_price");
				String status = rs.getString("status");
				String statusName = rs.getString("status_name");
				String nameReceiver = rs.getString("name_receiver");
				Orders order = new Orders();
				order.setId(id);
				order.setDateCreate(dateCreate);
				order.setPhoneNum(phoneNum);
				order.setAddress(address);
				order.setGrandPrice(grandTotal);
				order.setNameReceiver(nameReceiver);
				order.setStatus(status);
				order.setStatusName(statusName);
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Orders> getOrdersByUser(int userId) {
		List<Orders> orders = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from orders inner join status_order on "
					+ "orders.status = status_order.status_code where user_id = ? order by id desc");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String dateCreate = rs.getString("issue_date");
				String phoneNum = rs.getString("phone_num");
				String address = rs.getString("address");
				double grandTotal = rs.getDouble("grand_price");
				String status = rs.getString("status");
				String nameReceiver = rs.getString("name_receiver");
				String fileInvocie = rs.getString("file_invoice");
				String statusName = rs.getString("status_name");
				Orders order = new Orders();
				order.setId(id);
				order.setDateCreate(dateCreate);
				order.setPhoneNum(phoneNum);
				order.setAddress(address);
				order.setGrandPrice(grandTotal);
				order.setNameReceiver(nameReceiver);
				order.setStatus(status);
				order.setFileInvoice(fileInvocie);
				order.setStatusName(statusName);
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Orders getOrderById(int id) {
		Orders order = new Orders();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from orders inner join status_order on \r\n"
					+ "orders.status = status_order.status_code where orders.id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ids = rs.getInt("id");
				int userId = rs.getInt("user_id");
				int shipId = rs.getInt("ship_id");
				String dateCreate = rs.getString("issue_date");
				String phoneNum = rs.getString("phone_num");
				String address = rs.getString("address");
				double grandTotal = rs.getDouble("grand_price");
				String status = rs.getString("status");
				double discount = rs.getDouble("discount");
				String email = rs.getString("email");
				String addressDetail = rs.getString("address_detail");
				String nameReceiver = rs.getString("name_receiver");
				String fileInvocie = rs.getString("file_invoice");
				String statusName = rs.getString("status_name");
				order.setStatusName(statusName);
				order.setId(ids);
				order.setUserId(userId);
				order.setDateCreate(dateCreate);
				order.setPhoneNum(phoneNum);
				order.setAddress(address);
				order.setGrandPrice(grandTotal);
				order.setStatus(status);
				order.setEmail(email);
				order.setDiscount(discount);
				order.setAddressDetail(addressDetail);
				order.setFileInvoice(fileInvocie);
				Shipping shipping = getShippingById(shipId);
				order.setShipping(shipping);
				order.setNameReceiver(nameReceiver);

			}
			return order;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<OrderDetail> getOrderDetails(int id) {
		List<OrderDetail> orderDetails = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(" select * from order_detail where order_id = ?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				OrderDetail orderDetail = new OrderDetail();
				int quantity = rs.getInt("quantity");
				int productId = rs.getInt("product_id");
				orderDetail.setQuantity(quantity);
				Product product = getProductById(productId);
				orderDetail.setProduct(product);
				orderDetails.add(orderDetail);
			}
			return orderDetails;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateFileInvoice(int id, String fileInvoice) {
		try {
			PreparedStatement ps = conn
					.prepareStatement("UPDATE `freshop_db`.`orders` SET `file_invoice` = ? WHERE `id` = ?;");
			ps.setString(1, fileInvoice);
			ps.setInt(2, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateStatus(int id, String status) {
		try {
			PreparedStatement ps = conn
					.prepareStatement("UPDATE `freshop_db`.`orders` SET `status` = ? WHERE `id` = ?;");
			ps.setString(1, status);
			ps.setInt(2, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int insertOrders(int userId, String name, String phoneNumber, String email, String address,
			String desAddress, LocalDateTime dateIssue, List<ShoppingCartItem<Product>> orders, double discount,
			int shipId, double grandTotal, String status, String fileInvoice) {

		try {

			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO `freshop_db`.`orders` (`user_id`, `issue_date`, `phone_num`, `email`, `ship_id`, `grand_price`, `address`, `address_detail`, `discount`, `name_receiver`, `status`, `file_invoice`,`date_modified`)"
							+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);\r\n" + "");
			ps.setInt(1, userId);
			ps.setString(2, WritePDF.formatDate(dateIssue));
			ps.setString(3, phoneNumber);
			ps.setString(4, email);
			ps.setInt(5, shipId);
			ps.setDouble(6, grandTotal);
			ps.setString(7, address);
			ps.setString(8, desAddress);
			ps.setDouble(9, discount);
			ps.setString(10, name);
			ps.setString(11, status);
			ps.setString(12, fileInvoice);
			ps.setString(13, WritePDF.formatDate(dateIssue));
			ps.execute();
			ps = conn.prepareStatement(" SELECT LAST_INSERT_ID()");
			ResultSet rs = ps.executeQuery();
			int lastId = 0;
			if (rs.next()) {
				lastId = rs.getInt(1);
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
			return lastId;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return -1;

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
			if (!search.equals("")) {
				search1 = "%" + search + "%";
			}
			System.out.println(search1);
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
			System.out.println("Kq: " + resultList.size());
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
							+ " from products inner join categories on products.category_id = categories.id  where products.isDelete = 0 order by products.id limit ?,?");
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

	public List<Product> filterProduct(int index, int quantity, String search, int category, double minPrice,
			double maxPrice) {

		List<Product> resultList = new ArrayList<Product>();
		try {
			PreparedStatement ps = null;
			if (search != null) {
				String sql = "select products.id,products.product_name, products.desciption, products.prices, products.image, categories.category_name\r\n"
						+ " from products inner join categories on products.category_id = categories.id "
						+ "where (products.product_name like ? or categories.category_name like ?) and products.isDelete = 0 "
						+ "order by products.id limit ?,?";
				String search1 = "";
				if (!search.equals("")) {
					search1 = "%" + search + "%";
				}
				ps = conn.prepareStatement(sql);
				ps.setString(1, search1);
				ps.setString(2, search1);
				ps.setInt(3, index * quantity);
				ps.setInt(4, quantity);
			} else {
				if (category > 0) {
					String sql = "select products.id,products.product_name, products.desciption, products.prices, products.image, categories.category_name\r\n"
							+ " from products inner join categories on products.category_id = categories.id  "
							+ "where products.category_id = ? and products.isDelete = 0 " 
							+ "order by products.id\r\n" + "limit ?,?";

					ps = conn.prepareStatement(sql);
					ps.setInt(1, category);
					ps.setInt(2, index * quantity);
					ps.setInt(3, quantity);
				} else {
					if (minPrice >= 0 && maxPrice >= 0) {
						String sql = "select products.id,products.product_name, products.desciption, products.prices, products.image, categories.category_name\r\n"
								+ " from products inner join categories on products.category_id = categories.id "
								+ "where products.price >= ? and product.price <= ? and products.isDelete = 0 " 
								+ "order by products.id\r\n"
								+ "limit ?,?";

						ps = conn.prepareStatement(sql);
						ps.setDouble(1, maxPrice);
						ps.setDouble(index, maxPrice);
						ps.setInt(3, index * quantity);
						ps.setInt(4, quantity);
					} else {
						String sql = "select products.id,products.product_name, products.desciption, products.prices, products.image, categories.category_name\r\n"
								+ " from products inner join categories on products.category_id = categories.id where products.isDelete = 0 "
								+ "order by products.id \r\n" + "limit ?,?";
						System.out.println("DM tao đay");
						ps = conn.prepareStatement(sql);
						ps.setInt(1, index * quantity);
						ps.setInt(2, quantity);
					}
				}
			}

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
				product.setCategory_name(rs.getString("category_name").trim());
				resultList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public int getTotalProduct(String search, int categoryId, double minPrice, double maxPrice) {
		try {
			PreparedStatement ps = null;
			if (search != null) {
				String sql = "select COUNT(*)\r\n"
						+ " from products inner join categories on products.category_id = categories.id "
						+ "where (products.product_name like ? or categories.category_name like ?) and products.isDelete = 0";						
				String search1 = "";
				if (!search.equals("")) {
					search1 = "%" + search + "%";
				}
				ps = conn.prepareStatement(sql);
				ps.setString(1, search1);
				ps.setString(2, search1);
			} else {

				if (categoryId >= 0) {
					ps = conn.prepareStatement("select COUNT(*) from products where category_id = ? and products.isDelete = 0");
					ps.setInt(1, categoryId);
				} else {
					if (minPrice >= 0 && maxPrice >= 0) {
						ps = conn.prepareStatement("select COUNT(*) from products where price >= ? and price <=? and products.isDelete = 0");
						ps.setDouble(1, minPrice);
						ps.setDouble(2, maxPrice);
					} else {
						ps = conn.prepareStatement("select COUNT(*) from products where products.isDelete = 0");
					}
				}
			}
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
		int a = productDAO.getTotalProduct(null, -1, -1, -1);
		System.out.println(a);
	}
}
