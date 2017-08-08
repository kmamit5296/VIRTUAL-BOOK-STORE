<!DOCTYPE html>
<html>
	<head>
		<%@page session="false"%>
		<%@taglib prefix="C"  uri="http://java.sun.com/jsp/jstl/core"%>
		<title>VIRTUAL BOOK STORE</title>
		<link rel="stylesheet" href="http://localhost:8080/store/jsp/welcome.css"/>
	</head>
	<body>
	<%
		application.getRequestDispatcher("/cbind").include(request,response);
	%>
		<iframe src="http://localhost:8080/store/header/header.html" width="1348px" height="151px"  scrolling="no" frameborder="0">
		</iframe>
		<a href=""><img src="http://localhost:8080/store/images/advertisement/ad1.jpg" width="1340px" height="350px"/></a>
		<table>
			<tr><th>CATEGORIES</th></tr>
			<C:forEach var="cat" items="${cList}">
				<tr><td><a href="http://localhost:8080/store/jsp/category.jsp?${cat.categoryID}">${cat.name}</a></td></tr>
			</C:forEach>
		</table>
		<div id="actbod">
		<ul>
		<C:forEach var="i" begin="0" end="2" step="1">
			<li>
			<iframe src="http://localhost:8080/store/jsp/booktmp.jsp?${cList[i].categoryID}" width="1117" height="517" scrolling="no" frameborder="0">
			</iframe>
			</li>
		</C:forEach>
		</ul>
		</div>
		<iframe src="http://localhost:8080/store/header/footer.jsp" width="1348px" height="450px" scrolling="no" frameborder="0">
		</iframe>
	</body>
</html>