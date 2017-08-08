<!DOCTYPE html>
<html>
	<%!
		int bookDetailID;
		AllCollection ac;
	%>
	<%
		bookDetailID=new Integer(request.getQueryString());
		ac=new AllCollection();
		ac.bindBookObject(bookDetailID,(ArrayList<Book>)application.getAttribute("bList"),application);
	%>
	<head>
		<%@page import="helper.*"%>
		<%@page import="java.util.*"%>
		<%@page import="book.*"%>
		<%@taglib prefix="C"  uri="http://java.sun.com/jsp/jstl/core"%>
		<link rel="stylesheet" href="http://localhost:8080/store/jsp/bookdetail.css"/>
		<title>${bookPageDetail.name}</title>
	</head>
	<body>
		<iframe src="http://localhost:8080/store/header/header.html" width="1348px" height="151px"  scrolling="no" frameborder="0">
		</iframe>
		<ul><li>
		<table>
			<tr><th>CATEGORIES</th></tr>
			<C:forEach var="cat" items="${cList}">
				<tr><td><a href="http://localhost:8080/store/jsp/category.jsp?${cat.categoryID}">${cat.name}</a></td></tr>
			</C:forEach>
		</table>
		</li></ul>
		<ul><li>
		<h1>${bookPageDetail.name}</h1>
		<table id="detail">
			<tr>
				<td>Name:</td>
				<td>${bookPageDetail.name}</td>
			</tr>
			<tr>
				<td>Price:</td>
				<td>${bookPageDetail.price}</td>
			</tr>
			<tr>
				<td>ISBN:</td>
				<td>${bookPageDetail.ISBN}</td>
			</tr>
			<tr>
				<td>Author:</td>
				<td>${bookPageDetail.author}</td>
			</tr>
			<tr>
				<td>Publisher:</td>
				<td>${bookPageDetail.publisher}</td>
			</tr>
			<tr>
				<td>Discount:</td>
				<td>${bookPageDetail.discount}%</td>
			</tr>
			<tr>
				<td>Detail:</td>
				<td><p>${bookPageDetail.details}</p></td>
			</tr>
		</table>
		</li></ul>
		<ul><li>
		<p>IMAGE:</p>
		<img src="http://localhost:8080/store/im?${bookPageDetail.bookID}" width="240px" height="300px"/>
		</li>
		<li>
		<a href=""><aside><b>ADD TO CART</b></aside></a><br/>
		<a href=""><aside><b>BUY</a></b></a></aside></li>
		<li><del>Rs.${bookPageDetail.price}</del> Rs.${bookPageDetail.price-bookPageDetail.price*bookPageDetail.discount/100}</li>
		</ul>
		<iframe src="http://localhost:8080/store/header/footer.jsp" width="1348px" height="450px" scrolling="no" frameborder="0">
		</iframe>
	</body>
	<C:remove var="bookPageDetail"/>
</html>