package book;

import helper.AllCollection;

import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import database.DbConnect;

public class BookBind extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		AllCollection alc=new AllCollection();
		ResultSet rs1=null;
		try{
			rs1=DbConnect.execute("select bookName,author,price,publisher,discount,bookID,categoryID,details,ISBN from book");
			while(rs1.next()){
				Book b1=new Book();
				b1.setName(rs1.getString(1));
				b1.setAuthor(rs1.getString(2));
				b1.setPrice(rs1.getDouble(3));
				b1.setPublisher(rs1.getString(4));
				b1.setDiscount(rs1.getDouble(5));
				b1.setBookID(rs1.getInt(6));
				b1.setCategoryID(rs1.getInt(7));
				b1.setDetails(rs1.getString(8));
				b1.setISBN(rs1.getString(9));
				alc.getbList().add(b1);
			}
			rs1.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	    getServletContext().setAttribute("bList", alc.getbList());
	}
}
