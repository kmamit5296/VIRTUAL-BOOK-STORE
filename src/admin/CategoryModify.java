package admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.Category;
import database.DbConnect;

public class CategoryModify extends HttpServlet{
	Category c1;
	String newName,message;
	boolean foundcat=true;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		c1=new Category();
		c1.setName(request.getParameter("modcategory"));
		newName=request.getParameter("chngcat");
		if(newName.equals("")){
			foundcat=false;
			message="category name not specified!!!!!!!!";	
		}
		else{
			try {
				if(DbConnect.Update("UPDATE category SET categoryName='"+newName+"' WHERE categoryName='"+c1.getName()+"'")==1)
					message="category name changed sucessfully!!!!!!!!!!!";
				else
					message="category not found!!!!!!!!!!!!!";
			}
			catch (ClassNotFoundException | SQLException e) {
				message="errror in connecting to database try again!!!!!!!!!!";
				e.printStackTrace();
			}
		}
		getServletContext().setAttribute("catMessageDetail",message );
		response.sendRedirect("http://localhost:8080/store/jsp/admin/admincontrols.jsp");
	}
}
