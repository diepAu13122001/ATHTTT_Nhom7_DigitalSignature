package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class DBConnect {

	ResourceBundle resourceBundle = ResourceBundle.getBundle("config\\config");
	Connection connection;


	public Connection getConnection() {
		try {
			Class.forName(resourceBundle.getString("driverName"));
			String url = resourceBundle.getString("url");
			String user = resourceBundle.getString("user");
			String password = resourceBundle.getString("password");
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void executeUpdate(String sql) throws Exception{
		Connection connect =getConnection();
		PreparedStatement ps = connect.prepareStatement(sql);
		ps.executeUpdate();
		
	}
	public ResultSet executeQuery(String sql) throws Exception{
		Connection connect =getConnection();
		PreparedStatement ps = connect.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	public static void main(String[] args) throws SQLException {
		DBConnect connect = new DBConnect();
		Connection connection =  connect.getConnection();
		
	}
	
}
