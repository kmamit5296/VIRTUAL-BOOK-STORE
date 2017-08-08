package database;
import java.sql.*;
public class DbConnect {
	ResultSet r1;
	static Connection con;
	static PreparedStatement p2;
	public static void loadDriverClass()throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	} 
	public static Connection createconnection()throws SQLException{
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtual book store?user=root");
		return con;
	}
	public static ResultSet execute(String url)throws SQLException,ClassNotFoundException{
		
		 DbConnect.loadDriverClass();
		 con=DbConnect.createconnection();
		 p2=con.prepareStatement(url);
		 return p2.executeQuery();
	}
	public static int Update(String url)throws SQLException,ClassNotFoundException{
		 
		 DbConnect.loadDriverClass();
		 con=DbConnect.createconnection();
		 p2=con.prepareStatement(url);
		 return p2.executeUpdate();
	}
	public static void close1() throws SQLException{
			con.close();
			p2.close();
	}
}
