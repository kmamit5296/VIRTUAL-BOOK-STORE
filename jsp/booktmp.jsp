<!DOCTYPE html>
<html>
	<head>
		<%@page import="java.util.*"%>
		<%@page import="category.*"%>
		<%@page import="book.*"%>
		<%@page import="helper.*"%>
		<%@page session="false"%>
		<%@taglib prefix="C"  uri="http://java.sun.com/jsp/jstl/core"%>
		<link rel="stylesheet" href="http://localhost:8080/store/jsp/booktemp.css"/>
	</head>
	<body>
	<%!
		int catID;
		String catName;
		Category categ;
		AllCollection alcol;
		ArrayList<Book> fourList;
	%>
	<%
		application.getRequestDispatcher("/bind").include(request,response);
		catID=new Integer(request.getQueryString());
		categ=new Category();
		alcol=new AllCollection();
		catName=categ.retName(catID,application);
		fourList=alcol.bind4Ob(catID,(ArrayList<Book>)application.getAttribute("bList"));
	%>
    <div id="bod">
		<a  target="_parent" href="http://localhost:8080/store/jsp/category.jsp?<%=catID%>"><p id="hea"><%=catName%></p></a>
		<table>
			<tr>
			<C:forEach var="bok" items="<%=fourList%>">
				<td>
					<div class="contain">
						<a target="_parent" href="http://localhost:8080/store/jsp/bookdetail.jsp?${bok.bookID}">
						<p id="top">DISCOUNT:${bok.discount}%</p>
						<img src="http://localhost:8080/store/im?${bok.bookID}" width="194px" height="260px"/>
						<p class="pr"><del>${bok.price}</del></p>
						<p class="nm">${bok.name}</p>
						<p class="pr">${bok.price-bok.price*bok.discount/100}</p>
						<p class="nm">By: ${bok.author}</p>
						</a>
					</div>
				</td>
			</C:forEach>	
			</tr>
		</table>
		<a target="_parent" href="http://localhost:8080/store/jsp/category.jsp?<%=catID%>"><aside>see more....</aside><a>
	<C:remove var="fourList"/>
	</div>
	</body>
</html>