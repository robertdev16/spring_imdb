<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${writer.name} - tiny IMDB</title>
</head>
<body>
	<h2 align="center">Writer Information</h2>
	<br />
	<table border="1" cellpadding="10">
		<tr>
			<td rowspan="2"><img src="../artistpic/${writer.artistId}/0" /></td>
			<td>${writer.name}</td>
			<td>${writer.birthDate}</td>
			<td>${writer.birthPlace}</td>
		</tr>
		<tr>
			<td colspan="3">${writer.biography}</td>
		</tr>
		<tr>
			<td colspan="4">
				<c:forEach var="idx" begin="1" end="${picNum}" step="1">
					<img src="../artistpic/${writer.artistId}/${idx}" />  
				</c:forEach>
			</td>
		</tr>
		<tr><td colspan="4"> </td></tr>
		<tr>
			<th  colspan="4">
			Movie List
			</th>
		</tr>
		<tr>
			<td colspan="4">
				<c:forEach var="movie" items="${writer.movieList}">
					<a href="../movie/${movie.movieId}">${movie.title} (${movie.year})</a> <br />
				</c:forEach>
			</td>
		</tr>
	</table>

</body>
</html>