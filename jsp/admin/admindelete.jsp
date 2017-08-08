<!DOCTYPE html>
<html>
	<head>
		<%@taglib prefix="C"  uri="http://java.sun.com/jsp/jstl/core"%>
		<%@page import="category.*"%>
		<%@page import="helper.*"%>
		<%@page session="false"%>
		<script>
			function retID(x){
				<C:forEach var="cat" items="${cList}">
					if("${cat.name}"==x)
						return ${cat.categoryID};
				</C:forEach>
			}	
		</script>
		<script>
			function retValidBookID(y){
				var vBookID=new Array();
				var a=0;
				<C:forEach var="book" items="${bList}">
					if(${book.categoryID}==y)
						vBookID[a++]="${book.name}";
				</C:forEach>
				return vBookID;
			}
		</script>
		<script>
			function valiadteBookDelete(){
				var x=document.forms["bookdelete"]["srchbook"].value;
				if (x==null || x==""||x=="book name"){
					alert("book name must be filled!!!!!!");
					return false;
				}
				else return true;
			}
			function retInBook(y,ar){
			//document.write(y+"<br>");
				for(var i=0;i<ar.length;i++){
					if(ar[i]==y)
						return true;
				}
				return false;
			}
			function changeBookList(){
				var x=document.forms["bookchoose"]["category"].value;
				var y=document.forms["bookchoose"]["book"];
				var arr=vBook=new Array();
				vBook=retValidBookID(retID(x));
				//document.write(retValidBookID(retID(x)));
				//document.write(y.length);
				for(var i=0;i<y.length;i++){
					if(!retInBook(y.options[i].text,vBook))
						y.remove(y.options[i].index);
					else 
						arr.push(y.options[i].text);
						//document.write(y.options[i].text+"---"+y.options[i].index+"<br>");
				}
				document.getElementById("hiii").innerHTML=arr;
			}
		</script>
		
		<link rel="stylesheet" href="http://localhost:8080/store/jsp/input.css"/>
		<title>DELETE</title>
	</head>
	<body>
		<iframe src="http://localhost:8080/store/header/header.html" width="1348px" height="151px"  scrolling="no" frameborder="0">
		</iframe>
		<p id="hiii"><p>
		<h2>DELETE CATEGORY</h2>
		<form action="">
			<fieldset>
				<select name="category">
					<C:forEach var="cat" items="${cList}">
						<option value="${cat.name}">${cat.name}</option>
					</C:forEach>
				</select>
				<input type="submit" value="Delete Category"/>
			</fieldset>
		</form>
		<h2>DELETE BOOK</h2>
		<form name="bookdelete" onsubmit="return valiadteBookDelete()" action="">
			<fieldset>
				<input type="text" name="srchbook" required value="book name"  onBlur="if(this.value=='')this.value='book name'" 
							onFocus="if(this.value=='book name')this.value=''"/>
				<input type="submit" value="Search Book"/>
			</fieldset>
		</form>
		<h2>OR</h2>
		<form name="bookchoose">
			<fieldset>
				<select name="category" onchange="changeBookList()">
					<C:forEach var="cat" items="${cList}">
						<option value="${cat.name}">${cat.name}</option>
					</C:forEach>
				</select>
				<select name="book">
					<C:forEach var="book" items="${bList}">
						<option value="${book.name}">${book.name}</option>
					</C:forEach>
				</select>
				<input type="submit" value="Delete Book"/>
			</fieldset>
		</form>
		</body>
</html>