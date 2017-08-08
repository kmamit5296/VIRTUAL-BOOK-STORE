package admin;
import java.io.DataInputStream;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
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

import category.Category;
import database.DbConnect;

import book.Book;

public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String message,messageDetail;
	boolean isAdd=true;
	String s1,s2,s3[],s4[];
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		isAdd=true;
		Book adbook=new Book();
		Category hcat=new Category();
		PrintWriter out=response.getWriter();
		int i=0;
		s4=s3=new String[10];
		Calendar curdate=Calendar.getInstance();
		DataInputStream dis=null;
		ServletFileUpload sfu=new ServletFileUpload();
		byte databyte[]=null;
		int byteread=0,tot=0;
		try{
			FileItemIterator i1=sfu.getItemIterator(request);
			while(i1.hasNext()){
				FileItemStream fis=i1.next();
				if(!fis.isFormField()){
					if(fis.getContentType().equals("image/jpeg")){
						dis=new DataInputStream(fis.openStream());
						databyte=new byte[request.getContentLength()];
						while(tot<request.getContentLength()){
							byteread=dis.read(databyte,tot,request.getContentLength());
							tot+=byteread;
						}
					}
				}
				else{
					s3[i]=fis.getFieldName();
					s4[i++]=Streams.asString(fis.openStream());
					}
				}
			}
			catch(FileUploadException e){
				e.printStackTrace();
			}
		System.out.println(s3[0].equals("")+" "+s3[4].equals("")+s3[5].equals("")+s3[8].equals("")+s3[3].equals(""));
		if(s3[0].equals("")||s3[4].equals("")||s3[5].equals("")||s3[8].equals("")||s3[3].equals("")){
			isAdd=false;
			message="empty fields";
			messageDetail="fields are empty please fill all the fields";	
		}
		else{
			adbook.setName(s3[0]);
			adbook.setAuthor(s3[4]);
			adbook.setPublisher(s3[5]);
			adbook.setDetails(s3[8]);
			adbook.setISBN(s3[3]);
		}
		hcat.setName(s3[1]);
		hcat.setCategoryID(hcat.retID(hcat.getName(),getServletContext()));
		if(isAdd==true){
			try{
				adbook.setPrice(new Double(s3[2]));
				adbook.setStock(new Integer(s3[6]));
				adbook.setDiscount(new Double(s3[7]));
				}	
			catch(NumberFormatException e){
				isAdd=false;
				message="incorrect fields value";
				messageDetail="please specify correct field value(price,inital stock,discount)";
			}	
		}	
		/*out.println(hcat.getCategoryID());
		out.println(adbook.getName());
		out.println(adbook.getPrice());
		out.println(adbook.getISBN());
		out.println(adbook.getAuthor());
		out.println( adbook.getPublisher());
		out.println(adbook.getDetails());
		out.println(databyte);
		out.println(curdate.get(Calendar.YEAR)+"-"+(curdate.get(Calendar.MONTH)+1)+"-"+curdate.get(Calendar.DAY_OF_MONTH));
		out.println(adbook.getStock());
		out.println(adbook.getDiscount());*/
		if(isAdd==true){
			try {
				java.sql.PreparedStatement ps;
				DbConnect.loadDriverClass();
				Connection connect=DbConnect.createconnection();
				ps=connect.prepareStatement("INSERT INTO `book`" +
						"(`categoryID`, `bookName`, `price`, `ISBN`, `author`, `publisher`, `details`, `thumb`,`updateDate`, `stock`, `discount`)" +
						" VALUES (?,?,?,?,?,?,?,?,'"+curdate.get(Calendar.YEAR)+"-"+(curdate.get(Calendar.MONTH)+1)+"-"+curdate.get(Calendar.DAY_OF_MONTH)+"',?,?)");
				ps.setInt(1,hcat.getCategoryID());
				ps.setString(2,adbook.getName());
				ps.setDouble(3,adbook.getPrice());
				ps.setString(4,adbook.getISBN());
				ps.setString(5,adbook.getAuthor());
				ps.setString(6,adbook.getPublisher());
				ps.setString(7,adbook.getDetails());
				ps.setBytes(8, databyte);
				ps.setInt(9,adbook.getStock());
				ps.setDouble(10,adbook.getDiscount());
				if(ps.executeUpdate()==1){
					if(dis==null){
						message="book added";
						messageDetail="book added sucessfully but image not added";
					}
					else
						message="book added sucessfully";
						messageDetail="book added sucessfully to the specified category";
				}
				ps.close();
				connect.close();
				
			} catch (ClassNotFoundException|SQLException e) {
				message="error in updating book";
				messageDetail="error in adding book pleasse restart and try again";
				e.printStackTrace();
			}
			
		}
		getServletContext().setAttribute("addBookMessageDetail",message+"; "+messageDetail );
		response.sendRedirect("http://localhost:8080/store/jsp/admin/admincontrols.jsp");
	}
}
