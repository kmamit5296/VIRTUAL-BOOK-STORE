package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DbConnect;

import encoding.Encoding;

public class AddAdmin extends HttpServlet {
	String message,messageDetail,passAgain;
	boolean isReg=true;
	Administrator admin;
	Encoding e1;
	PrintWriter out;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		int a=0,b=0;
		admin=new Administrator();
		e1=new Encoding();
		try {
			out=response.getWriter();
		} catch (IOException e2) {	
		}
		admin.username=request.getParameter("uname");
		admin.email=request.getParameter("email");
		admin.ques1=request.getParameter("q1");
		admin.ques2=request.getParameter("q2");
		admin.ques3=request.getParameter("q3");
		admin.password=request.getParameter("pass");
		passAgain=request.getParameter("passAgain");
		out.println(request.getParameter("uname"));
		out.println(request.getParameter("pass"));
		out.println(request.getParameter("passAgain"));
		out.println(request.getParameter("email"));
		out.println(request.getParameter("q1"));
		out.println(request.getParameter("q2"));
		out.println(request.getParameter("q3"));
		out.println(admin.password.length());
		if(admin.username==""||admin.email==""||admin.ques1==""||admin.ques2==""||admin.ques3==""||admin.password==""){
			isReg = false;
			message = "Empty Fields";
			messageDetail = "Fields are empty please fill all entries";
			out.println(message+"<br>"+messageDetail);
		}
		try{
			if(((admin.password).length()>9)&&isReg){
				if(admin.password.equals(passAgain)){
					admin.password=e1.encoded(admin.password);
					
					a=DbConnect.Update("insert into administrator values('"+admin.username+"','"+admin.password+"','"+admin.email+"')");
					
					b=DbConnect.Update("insert into adminques values('"+admin.username+"','"+e1.encoded(admin.ques1)+"','"+e1.encoded(admin.ques2)+"','"+e1.encoded(admin.ques3)+"')");
					DbConnect.close1();
					if(a==1&&b==1){
						isReg=true;
						message="Registered";
						messageDetail="Sucessfully Registered now login with admin privilages and enjoy";
						out.println(message+"<br>"+messageDetail);
					}
				}
				else {
                     isReg = false;
                     message = "Passwords do not match";
                     messageDetail = "Please provide a matching passwords";
                     out.println(message+"<br>"+messageDetail);
				}
			}
			else{
				isReg = false;
				message = "Password length is less than 10 characters";
				messageDetail = "Please provide a passwords that has password length of minimum of 10 characters";
				out.println(message+"<br>"+messageDetail);
			}
		}
		catch (SQLIntegrityConstraintViolationException ex) {
			isReg = false;
            message="Username already exist";
            messageDetail = "You are trying username that already exist try other user name";
            out.println(message+"<br>"+messageDetail);
		} 
		catch (NoSuchAlgorithmException e) {
			isReg=false;
			message="password not encoded";
			messageDetail="Error in encoding password no suitable algorithm found";
			out.println(message+"<br>"+messageDetail);
		}
		catch(SQLException e){
			isReg=false;
			message="Database no found";
			messageDetail="Error in connecting to database";
			out.println(message+"<br>"+messageDetail);
		}
		catch (Exception ex) {
			isReg = false;
            message="Error in register";
            messageDetail = "There was a problem in registering your account please do retry again later...";
            out.println(message+"<br>"+messageDetail);
		}  
	}
}
