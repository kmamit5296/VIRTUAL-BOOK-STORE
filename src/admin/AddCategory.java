package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.Category;
import database.DbConnect;

public class AddCategory extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String message,messageDetail;
	boolean isAdd=true;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Category adcat=new Category();
		PrintWriter out;
		adcat.setName(request.getParameter("categoryname"));
		if(adcat.getName()==""){
			isAdd=false;
			message="empty category name";
			messageDetail="category name not given in the specified";
		}
		if(isAdd==true){
			try{
				if(DbConnect.Update("INSERT INTO `category`(`categoryName`) VALUES ('"+adcat.getName()+"')")==1){
					message="category added";
					messageDetail="category sucessfully added";
				}
				else{	
					message="error in adding category1";
					messageDetail="error in adding categotry please try again";
				}
			}
			catch(ClassNotFoundException e){
				message="error in adding category2";
				messageDetail="error in adding categotry please try again";
			}
			catch(SQLException e){
				message="error in adding category3"+e;
				messageDetail="error in adding categotry please try again";
			}
		}
		out=response.getWriter();
		//out.println(message+" "+messageDetail);
		getServletContext().setAttribute("addCategoryDetail",message+"; "+messageDetail );
		response.sendRedirect("http://localhost:8080/store/jsp/admin/admincontrols.jsp");
	}
}
