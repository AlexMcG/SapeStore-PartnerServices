<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
			<div class="results">
				<c:forEach items="${resultBookList}" var="book">
					<table>
						<tr>
							<td>Title: ${book.bookTitle}</td>
						</tr>
						<tr>
							<td>Author: ${book.bookAuthor}</td>
						</tr>
						<tr>
							<td>ISBN: ${book.isbn}</td>
						</tr>
						<tr>
							<td>Image: <img src="${pageContext.request.contextPath}/${book.bookThumbImage}"></td>
						</tr>
						<tr>
							<td>category ID: ${book.categoryId}</td>
						</tr>
						<tr>
							<td>Price: $${book.bookPrice}</td>
						</tr>
						<br><br>
					</table>
				</c:forEach>
			</div>
</body>
</html>
