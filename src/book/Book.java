package book;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date; 

import com.mysql.jdbc.Blob;

import database.DbConnect;
public class Book implements Serializable {
	int bookID,categoryID,stock;
	String ISBN;
	String name,author,publisher,details;
	Blob thumb;
	Date date;
	double price,discount;
	public int getBookID(){
		return bookID;
	}
	public Blob getThumb(){
		return thumb;
	}
	public int getCategoryID(){
		return categoryID;
	}
	public int getStock(){
		return stock;
	}
	public String getISBN(){
		return ISBN;
	}
	public String getName(){
		return name;
	}
	public double getPrice(){
		return price;
	}
	public double getDiscount(){
		return discount;
	}
	public String getAuthor(){
		return author;
	}
	public String getPublisher(){
		return publisher;
	}
	public String getDetails(){
		return details;
	}
	public Date getDate(){
		return date;
	}
	public void setBookID(int bookID){
		 this.bookID=bookID;
	}
	public void setCategoryID(int categoryID){
		this.categoryID=categoryID;
	}
	public void setStock(int stock){
		this.stock=stock;
	}
	public void setISBN(String ISBN){
		this.ISBN=ISBN;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setPrice(double price){
		this.price=price;
	}
	public void setDiscount(double discount){
		this.discount=discount;
	}
	public void setAuthor(String author){
		this.author=author;
	}
	public void setPublisher(String publisher){
		this.publisher=publisher;
	}
	public void setDetails(String details){
		this.details=details;
	}
	public void setDate(Date date){
		this.date=date;
	}
	public void setThumb(java.sql.Blob thumb){
		this.thumb=(Blob)thumb;
	}
	   public int getBookId (String bookName) throws SQLException, ClassNotFoundException{
	        int id;
	        String getIdSQL = "SELECT  bookID " +
	                        "FROM  book " +
	                        "WHERE  bookName = '"+bookName+"'; ";
	        ResultSet executeQuery = DbConnect.execute(getIdSQL);
	        executeQuery.next();
	        id = executeQuery.getInt(1);
	        executeQuery.close();
	        DbConnect.close1();
	        return id;
	    }
	    public static void main(String v[]){
	    	Book b1=new Book();
	    	try {
				System.out.println(b1.getBookId("abc"));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
    }
    
}
