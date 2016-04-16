<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${actor.name} - tiny IMDB</title>
</head>
<body>
	<h2 align="center">Actor Information</h2>
	<br />
	<table border="1" cellpadding="10">
		<tr>
			<td rowspan="2"><img src="../artistpic/${actor.artistId}/0" /></td>
			<td>${actor.name}</td>
			<td>${actor.birthDate}</td>
			<td>${actor.birthPlace}</td>
		</tr>
		<tr>
			<td colspan="3">${actor.biography}</td>
		</tr>
		<tr>
			<td colspan="4">
				<c:forEach var="idx" begin="1" end="${picNum}" step="1">
					<img src="../artistpic/${actor.artistId}/${idx}" />  
				</c:forEach>
			</td>
		</tr>
		<tr><td colspan="4"> </td></tr>
		<tr>
			<th  colspan="2">
			Movie List
			</th>
			<th  colspan="2">
			Character Name
			</th>
		</tr>
		<c:forEach var="moviechar" items="${actor.movieCharList}">
			<tr>
				<td colspan="2">
					<a href="../movie/${moviechar.movie.movieId}">${moviechar.movie.title} (${moviechar.movie.year})</a>
				</td>
				<td colspan="2">
					${moviechar.name}
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>