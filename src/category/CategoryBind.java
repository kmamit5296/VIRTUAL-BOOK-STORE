package category;

import helper.AllCollection;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.DbConnect;

public class CategoryBind extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		AllCollection alc1=new AllCollection();
		ResultSet rs=null;
		try{
			rs=DbConnect.execute("select categoryName,categoryID from category");
			while(rs.next()){
				Category c1=new Category();
				c1.setName(rs.getString(1));
				c1.setCategoryID(rs.getInt(2));
				alc1.getcList().add(c1);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
				DbConnect.close1();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		getServletContext().setAttribute("cList", alc1.getcList());
	}
}
