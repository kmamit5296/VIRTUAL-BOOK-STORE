<!DOCTYPE html>
<html>
	<head>
		<%@page import="java.sql.*"%>
		<%@page import="database.DbConnect"%>
		<%@taglib prefix="C"  uri="http://java.sun.com/jsp/jstl/core"%>
		<link rel="stylesheet" href="http://localhost:8080/store/jsp/booktemp.css"/>
	</head>
	<body>
		<%! 
			ResultSet rs;
		%>
	<div id="bod">
		<a  href=""><p id="hea">UPCOMING</p></a>
		<table>
			<tr>
				<td>
					<div class="contain">
		<%	
			int a=0;
			rs=DbConnect.execute("select bookID from book ;");
			if(rs.next())
				a=rs.getInt(1);
		%>
		
						<a href="">
						<p id="top">DISCOUNT:10%</p>
						<img src="http://localhost:8080/store/im?<%=a%>"/>
						<p class="nm">${bok.name}</p>
						<p id="pr">${bok.price}</p>
						<p class="nm">By: ${bok.author}</p>
						</a>
					</div>
				</td>
				<td>
					<div class="contain">
		<%	
			if(rs.next())
				a=rs.getInt(1);
		%>
			<C:remove var="id"/>	
		<%	
			session.setAttribute("id",a);
			
		%>
						<a href="">
						<p id="top">DISCOUNT:10%</p>
						<img src="http://localhost:8080/store/im?<%=a%>"/>
						<p class="nm">${bok.name}</p>
						<p id="pr">${bok.price}</p>
						<p class="nm">By: ${bok.author}</p>
						</a>
					</div>
				</td>
				<td>
					<div class="contain">
		<%	
			if(rs.next())
				a=rs.getInt(1);
		%>
			<C:remove var="id"/>	
		<%	
			session.setAttribute("id",a);
			DbConnect.close1();
			rs.close();
		%>
						<a href="">
						<p id="top">DISCOUNT:10%</p>
						<img src="http://localhost:8080/store/im?<%=a%>"/>
						<p class="nm">${bok.name}</p>
						<p id="pr">${bok.price}</p>
						<p class="nm">By: ${bok.author}</p>
						</a>
					</div>
				</td>
			</tr>	
		</table>
	</div>	
	</body>
</html>