<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Movies in tiny IMDB storage</title>
</head>
<body>
	<h1 align="center">Movies in tiny IMDB storage:</h1>
	<form action="titleFilter" method="post">
		<input type="search" name="titleSearch" placeholder="Please input movie title..." />
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<input type="submit" value="Search" /> Current keyword: ${lastSearchTitle}
	</form>
	<br/>
	<table border="1" cellpadding="10">
		<caption>Movie List (Click on movie title for detail
			information)</caption>
		<tr>
			<th>Poster</th>
			<th width="20%">Title</th>
			<th>Year</th>
			<th>Brief</th>
			<th>Genre</th>
			<th>Runtime</th>
			<th>Rating</th>
			
		</tr>
		<c:forEach var="movie" items="${movies}">
			<tr>
				<td><img src="moviepic/${movie.movieId}" /></td>
				<td><a href="movie/${movie.movieId}">${movie.title}</a></td>
				<td>${movie.year}</td>
				<td>${movie.brief}</td>
				<td>
				<c:forEach var="genre" items="${movie.genreSet}">
				${genre} <br/>
				</c:forEach>
				</td>
				<td>${movie.runtime} min</td>
				<td>${movie.rating}</td>
				
			</tr>
		</c:forEach>
	</table>


</body>
</html>