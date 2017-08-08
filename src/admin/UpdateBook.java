package admin;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import book.Book;

import category.Category;
import database.DbConnect;

public class UpdateBook extends HttpServlet{
	Book b2;
	Category c2;
	boolean isModified=true;
	String message;
	String s1[],s2[],s3,s4;
	Date checkDate(String dt){
		String d1=null,d2=null,d3=null;
		Calendar calnder=Calendar.getInstance();
		Date dat;
		calnder.clear();
		dt=dt.trim();
		if(dt.length()==10){
			d1=dt.substring(0,4);
			d2=dt.substring(5,7);
			d3=dt.substring(8,10);
		}
		else{
			isModified=false;
			message="please give correct date format(YYYY-MM-DD)!!!!!!!!!!!";
			System.out.println("hello"+"  "+dt.length()+"  "+dt);
		}
		if(isModified){
			try{
				calnder.set(new Integer(d1),new Integer(d2),new Integer(d3));
			}
			catch(Exception e){
				isModified=false;
				message="please give correct date format(YYYY-MM-DD)";
				e.printStackTrace();
			}
		}	
		dat=new Date(calnder.getTimeInMillis());
		return dat;
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		isModified=true;
		b2=new Book();
		c2=new Category();
		int i=0;
		s1=s2=new String[9];
		PrintWriter out=response.getWriter();
		ServletFileUpload sfu=new ServletFileUpload();
		DataInputStream dais=null;
		byte databyte[]=null;
		int byteread=0,tot=0;
		out.println(getServletContext().getAttribute("searchbookid"));
		try{
			FileItemIterator iter=sfu.getItemIterator(request);
			while (iter.hasNext()) {
				FileItemStream fis = iter.next();
				if (fis.isFormField()) {
					s1[i]=fis.getFieldName();
					s2[i++]=Streams.asString(fis.openStream());
				} 
				else {
					s3=fis.getContentType();
					s4=fis.getName();
					if(s3.equals("image/jpeg")&&(!s4.equals(""))){
						dais=new DataInputStream(fis.openStream());
						databyte=new byte[request.getContentLength()];
						while(tot<request.getContentLength()){
							byteread=dais.read(databyte,tot,request.getContentLength());
							tot+=byteread;
						}
					}
				}
			}
		}
		catch(FileUploadException e){
			isModified=false;
			message="error in uploading file try again!!!!!!!!!!";
			e.printStackTrace();
		}
		//out.println(s1[0]+" "+s1[2]+s1[3]+s1[4]+s1[8]);
		if(s1[0].equals("")||s1[2].equals("")||s1[3].equals("")||s1[4].equals("")||s1[8].equals("")){
			isModified=false;
			message="fields are empty please fill all fields";
		}
		else{
			b2.setName(s1[0]);
			b2.setISBN(s1[2]);
			b2.setAuthor(s1[3]);
			b2.setPublisher(s1[4]);
			b2.setDetails(s1[8]);
		}
		if(isModified){
			try{
				//b2.setBookID(new Integer(s1[0]));
				//b2.setCategoryID(new Integer(s1[1]));
				b2.setPrice(new Double(s1[1]));
				b2.setStock(new Integer(s1[6]));
				b2.setDiscount(new Double(s1[7]));
				}	
			catch(NumberFormatException e){
				isModified=false;
				message="incorrect fields value, please specify correct field value(price,inital stock,discount)";
				e.printStackTrace();
			}
		}
		checkDate(s1[5]);
		if(isModified){
			try {
				java.sql.PreparedStatement ps;
				DbConnect.loadDriverClass();
				Connection connect=DbConnect.createconnection();
				if(databyte==null)
					ps=connect.prepareStatement("update book set bookName=?, price=?, ISBN=?, author=?, publisher=?, details=?, updateDate=?, " +
						"stock=?, discount=? where bookID="+getServletContext().getAttribute("searchbookid"));
				else 
					ps=connect.prepareStatement("update book set bookName=?, price=?, ISBN=?, author=?, publisher=?, details=?, updateDate=?, " +
							"stock=?, discount=?, thumb=? where bookID="+getServletContext().getAttribute("searchbookid"));
				ps.setString(1, b2.getName());
				ps.setDouble(2, b2.getPrice());
				ps.setString(3, b2.getISBN());
				ps.setString(4,b2.getAuthor());
				ps.setString(5, b2.getPublisher());
				ps.setString(6, b2.getDetails());
				ps.setString(7,s1[5].trim());
				ps.setInt(8, b2.getStock());
				ps.setDouble(9, b2.getDiscount());
				if(databyte!=null)
					ps.setBytes(10, databyte);
				if(ps.executeUpdate()==1)
					message="book modified sucessfully!!!!!!!!!!!!!!!!!!!!";
				else 
					message="book not modified please try again!!!!!!!!!!!!!!!!!!!";
				ps.close();
				connect.close();
			} catch (ClassNotFoundException| SQLException e) {
				isModified=false;
				message="error in updating book try again";
				e.printStackTrace();
			}	
		}
		System.out.println(message);
		getServletContext().setAttribute("bokMessageDetail",message );
		response.sendRedirect("http://localhost:8080/store/jsp/admin/admincontrols.jsp");
		/*for(int n=0;n<9;n++){
			out.println(s1[n]+"   ");	
		}
		out.println(s3+"   ");
		out.println(s4+"   ");
		out.println(databyte);
		out.println(message);*/
	}
}