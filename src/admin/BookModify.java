package admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DbConnect;

import book.Book;

public class BookModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Book b1;
	boolean found=true;
	String message;
	ResultSet rbs;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		b1=new Book();
		b1.setName(request.getParameter("srchbook"));
		if(b1.getName().equals("")){
			found=false;
			message="book name not specified!!!!!!!!";
		}
		else{
			try{
				rbs=DbConnect.execute("SELECT * FROM `book` WHERE bookName='"+ b1.getName()+"'");
				found=rbs.next();
				if(found){
					b1.setBookID(rbs.getInt(1));
					b1.setCategoryID(rbs.getInt(2));
					b1.setName(rbs.getString(3));
					b1.setPrice(rbs.getDouble(4));
					b1.setISBN(rbs.getString(5));
					b1.setAuthor(rbs.getString(6));
					b1.setPublisher(rbs.getString(7));
					b1.setDetails(rbs.getString(8));
					b1.setThumb(rbs.getBlob(9));
					b1.setDate(rbs.getDate(10));
					b1.setStock(rbs.getInt(11));
					b1.setDiscount(rbs.getDouble(12));
					System.out.println(b1.getDate());
				}
				else{
					message="Book with specified name not found!!!!!";
				}
				rbs.close();
				DbConnect.close1();
			}
			catch(ClassNotFoundException e){
				message="errror in connecting to database try again";
			}
			catch(SQLException e){
				message="errror in connecting to database try again";
			}
		}
		getServletContext().setAttribute("foundbook",found );
		if(found)
			getServletContext().setAttribute("bookfound",b1 );
		else
			getServletContext().setAttribute("messageDetail",message );
		response.sendRedirect("http://localhost:8080/store/jsp/admin/admincontrols.jsp");
	}
}	
