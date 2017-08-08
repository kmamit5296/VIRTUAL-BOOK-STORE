																																																																																																																																																																																																																					<!DOCTYPE html>
<html>
	<head>
		<%@taglib prefix="C"  uri="http://java.sun.com/jsp/jstl/core"%>
		<%@page import="helper.*"%>
		<%@page import="java.util.*"%>
		<%@page import="book.*"%>
		<link rel="stylesheet" href="http://localhost:8080/store/jsp/welcome.css"/>
		<title>VIRTUAL BOOK STORE</title>
	</head>
	<body>
		<iframe src="http://localhost:8080/store/header/header.html" width="1348px" height="151px"  scrolling="no" frameborder="0">
		</iframe>
		<table>
			<tr><th>CATEGORIES</th></tr>
			<C:forEach var="cat" items="${cList}">
				<tr><td id="catg"><a href="http://localhost:8080/store/jsp/category.jsp?${cat.categoryID}">${cat.name}</a></td></tr>
			</C:forEach>
		</table>
		<div id="actbod">
		<iframe src="http://localhost:8080/store/jsp/categorytemp.jsp?<%=new Integer(request.getQueryString())%>"
		width="1117px" height="<%=AllCollection.hSize(new Integer(request.getQueryString()),(ArrayList<Book>)application.getAttribute("bList"))%>" 
		scrolling="no" frameborder="0">
		</iframe>
		</div>
		<iframe src="http://localhost:8080/store/header/footer.jsp" width="1348px" height="450px" scrolling="no" frameborder="0">
		</iframe>
	</body>	
</html>
