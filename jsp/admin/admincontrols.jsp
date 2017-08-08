<!DOCTYPE html>
<html>
	<head>
		<%@taglib prefix="C"  uri="http://java.sun.com/jsp/jstl/core"%>
		<%@page import="helper.*"%>
		<%@page import="java.util.*"%>
		<%@page import="book.*"%>
		<%@page session="false"%>
		<!--<link rel="stylesheet" href="http://localhost:8080/store/jsp/welcome.css"/>-->
		<link rel="stylesheet" href="http://localhost:8080/store/jsp/input.css"/>	
		<title>VIRTUAL BOOK STORE</title>
	</head>
	<body>
		<iframe src="http://localhost:8080/store/header/header.html" width="1348px" height="151px"  scrolling="no" frameborder="0">
		</iframe>
		<h2>Add Category</h2>
		<form action="http://localhost:8080/store/addcategory">
			<fieldset>
				<input type="text" name="categoryname"/>
				<input type="submit" value="Add Category"/>
				<p class="label" style="Color:red;">${addCategoryDetail}</p>
			</fieldset>	
		</form>
		<h2>Add Book To category</h2>
		<form action="http://localhost:8080/store/addbook" enctype="multipart/form-data" method="post">
			<fieldset>
				<table>
					<tr>
					<td><p class="label">NAME:</p></td>
					<td><input type="text" name="bookname"/></td>
					</tr>
					<tr>
					<td><p class="label">SELECT CATEGORY:</p></td>
					<td><select name="category">
					<C:forEach var="cat" items="${cList}">
						<option value="${cat.name}">${cat.name}</option>
					</C:forEach>
					</select></td>
					</tr>
					<tr>
					<td><p class="label">PRICE:</p></td>
					<td><input type="text" name="price"/></td>
					</tr>
					<tr>
					<td><p class="label">ISBN:</p></td>
					<td><input type="text" name="isbn"/></td>
					</tr>
					<tr>
					<td><p class="label">AUTHOR:</p></td>
					<td><input type="text" name="author"/></td>
					</tr>
					<tr>
					<td><p class="label">PUBLISHER:</p></td>
					<td><input type="text" name="publisher"/></td>
					</tr>
					<tr>
					<td><p class="label">THUMB:</p></td>
					<td><input type="file" name="thumb"/></td>
					</tr>
					<tr>
					<!--<td><p class="label">DATE:</p></td>
					<td><input type="date" name="date" required value="YYYY-MM-DD"  onBlur="if(this.value=='')this.value='YYYY-MM-DD'" 
							onFocus="if(this.value=='YYYY-MM-DD')this.value=''"/></td>
					</tr>-->
					<tr>
					<td><p class="label">INITIAL STOCK:</p></td>
					<td><input type="text" name="stock"/></td>
					</tr>
					<tr>
					<td><p class="label">DISCOUNT:</p></td>
					<td><input type="text" name="discount"/></td>
					</tr>
					<tr>
					<td><p class="label">DETAILS:</p></td>
					<td><input type="text" style="height:200px;" name="detail"/></td>
				
					<!---<td><textarea rows="5" cols="35">
					</textarea></td>-->
					</tr>
					<tr>
					<td><input type="reset"/></td>
					<td><input type="submit" value="Add Book"/></td>
					</tr>
				</table>
					<p class="label" style="Color:red;">${addBookMessageDetail}</p>
			</fieldset>	
		</form>
		<h2>Modify Category Name</h2>
		<form action="http://localhost:8080/store/ModifyCategory">
			<fieldset>
			<table>
				<tr>
					<td><p class="label">SELECT CATEGORY:</p></td>
					<td><select name="modcategory">
					<C:forEach var="cat" items="${cList}">
						<option value="${cat.name}">${cat.name}</option>
					</C:forEach>
					</select></td>
				</tr>
				<tr>
					<td><input type="text" name="chngcat" required value="changed name"  onBlur="if(this.value=='')this.value='changed name'" 
							onFocus="if(this.value=='changed name')this.value=''"/></td>
					<td><input type="submit" value="Change Name"/></td>
				</tr>
			</table>
			<p class="label" style="Color:red;">${catMessageDetail}</p>			
			</fieldset>	
		</form>	
		<h2>Modify Book<h2>
		<form action="http://localhost:8080/store/ModifyBook">
			<fieldset>
			<table>
				<input type="text" name="srchbook"required value="book name"  onBlur="if(this.value=='')this.value='book name'" 
							onFocus="if(this.value=='book name')this.value=''"/>
				<input type="submit" value="Search Book"/>
			</table>
			<C:if test="${!foundbook}">
			<p>${messageDetail}</p>
			</C:if>
			</fieldset>
		</form>
		<C:if test="${foundbook}">
			<C:set var="searchbookid" value="${bookfound.bookID}" scope="application"/>
			<p>Book Details</p>
			<form action="http://localhost:8080/store/UpdateBook" enctype="multipart/form-data" method="post">
				<fieldset>
				<table>
					<tr>
					<td><p class="label">Book ID:</p></td>
					<td>${bookfound.bookID}</td>
					<td><input type="text" disabled="disabled" value="${bookfound.bookID}" name="bokID"/></td>
					</tr>
					<tr>
					<td><p class="label">Category ID:</p></td>
					<td>${bookfound.categoryID}</td>
					<td><input type="text" disabled="disabled" value="${bookfound.categoryID}" name="catID"/></td>
					</tr>
					<tr>
					<td><p class="label">Book Name:</p></td>
					<td>${bookfound.name}</td>
					<td><input type="text" value="${bookfound.name}" name="bokname"/></td>
					</tr>
					<tr>
					<td><p class="label">Price :</p></td>
					<td>${bookfound.price}</td>
					<td><input type="text" value="${bookfound.price}" name="bokprice"/></td>
					</tr>
					<tr>
					<td><p class="label">ISBN:</p></td>
					<td>${bookfound.ISBN}</td>
					<td><input type="text" value="${bookfound.ISBN}" name="bokISBN"/></td>
					</tr>
					<tr>
					<td><p class="label">Author:</p></td>
					<td>${bookfound.author}</td>
					<td><input type="text" value="${bookfound.author}" name="bokauthor"/></td>
					</tr>
					<tr>
					<td><p class="label">Publisher:</p></td>
					<td>${bookfound.publisher}</td>
					<td><input type="text" value="${bookfound.publisher}" name="bokpublisher"/></td>
					</tr>
					<tr>
					<td><p class="label">Update Date:</p></td>
					<td>${bookfound.date}</td>
					<td><input type="date" name="bokdate" value="${bookfound.date}"/></td>
					</tr>
					<tr>
					<td><p class="label">Stock:</p></td>
					<td>${bookfound.stock}</td>
					<td><input type="text" value="${bookfound.stock}" name="bokstock"/></td>
					</tr>
					<tr>
					<td><p class="label">Discount:</p></td>
					<td>${bookfound.discount}</td>
					<td><input type="text" value="${bookfound.discount}" name="bokdiscount"/></td>
					</tr>
					<tr>
					<td><p class="label">Thumb:</p></td>
					<td><img src="http://localhost:8080/store/im?${bookfound.bookID}" width="194px" height="260px"/></td>
					<td><input type="file" name="bokthumb"/></td>
					</tr>
					<tr>
					<td><p class="label">Details:</p></td>
					<td><footer>${bookfound.details}</footer></td>
					<td><input type="text" style="height:200px;" value="${bookfound.details}" name="bokdetails"/></td>
					</tr>
				</table>
					<input type="submit" value="Modify Book"/>
					<p class="label" style="Color:red;">${bokMessageDetail}</p>
				</fieldset>
			</form>	
		</C:if>
	</body>
	<C:remove var="messageDetail"/>
	<C:remove var="addCategoryDetail"/>
	<C:remove var="catMessageDetail"/>
	<C:remove var="bokMessageDetail"/>
	<C:remove var="addBookMessageDetail"/>
	<C:if test="bokMessageDetail!=null">
		<C:remove var="bookfound"/>
		<C:remove var="foundbook"/>
	</C:if>	
</html>	
