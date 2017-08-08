package book;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.jdbc.Blob;

import database.DbConnect;

public class show extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		Book bok=new Book();
		ServletOutputStream out = res.getOutputStream();
		InputStream in=null;	
		int id=new Integer(req.getQueryString());
		try{
			ResultSet rs1=DbConnect.execute("select thumb from book where bookID='"+id+"'");
			/*isImg=rs1.next();
			if(isImg==false)
				in=getServletContext().getResourceAsStream("/images/default/noimage.jpg");
			else{
				bok.image=(Blob)rs1.getBlob(1);
			}
			if(bok.image.length()==0){
				in=getServletContext().getResourceAsStream("/images/default/noimage.jpg");
			}	
			else
				in= bok.image.getBinaryStream();*/
			//try{
				if(rs1.next()){
					bok.thumb=(Blob)rs1.getBlob(1);
					//System.out.println(bok.thumb);
				}
			//}
		/*	catch(NullPointerException e){
				in=getServletContext().getResourceAsStream("/images/default/noimage.jpg");
			}*/
			if(bok.thumb==null){
				in=getServletContext().getResourceAsStream("/images/default/noimage.jpg");
			}
			else
				in= bok.thumb.getBinaryStream();
			res.setContentType("image/gif");
			int length = 0;
			int size = 1024;
			byte[] buffer = new byte[size];
			while ((length = in.read(buffer)) != -1) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.flush();
			DbConnect.close1();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
