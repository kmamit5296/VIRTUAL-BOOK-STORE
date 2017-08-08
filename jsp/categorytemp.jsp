<DOCTYPE !html>
<html>
	<%!
		int catgID;
		String catgName;
		Category categ;
		AllCollection alcol;
	%>
	<%
		catgID=new Integer(request.getQueryString());
		categ=new Category();
		alcol=new AllCollection();
		catgName=categ.retName(catgID,application);
		alcol.bindAllOb(catgID,(ArrayList<Book>)application.getAttribute("bList"),application);
	%>
	<head>
		<%@page import="java.util.*"%>
		<%@page import="category.*"%>
		<%@page import="book.*"%>
		<%@page import="helper.*"%>
		<%@taglib prefix="C"  uri="http://java.sun.com/jsp/jstl/core"%>
		<link rel="stylesheet" href="http://localhost:8080/store/jsp/booktemp.css"/>
	</head>
	<body>
		<div id="bod" height="auto">
		<a target="_parent" href="http://localhost:8080/store/jsp/category.jsp?<%=catgID%>"><p id="hea"><%=catgName%></p></a>
		<table>
			<C:forEach var="lis4" items="${allcatbook}">
			<tr>
				<C:forEach var="bok" items="${lis4}">
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
			</C:forEach>	
		</table>
	<C:remove var="allcatbook"/>
	</div>
	</body>
</html>	