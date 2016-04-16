<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${movie.title} - tiny IMDB</title>
</head>
<body>
	<h2 align="center">${movie.title}</h2>

	<br />
	<table border="1" cellpadding="10">
		<caption>Detail Information</caption>
		<tr>
			<th>Poster</th>
			<th width="20%">Title</th>
			<th>Year</th>
			<th>Brief</th>
			<th>Genre</th>
			<th>Runtime</th>
			<th>Rating</th>
		</tr>
		<tr>
			<td><img src="../moviepic/${movie.movieId}" /></td>
			<td>${movie.title}</td>
			<td>${movie.year}</td>
			<td>${movie.brief}</td>
			<td><c:forEach var="genre" items="${movie.genreSet}">
			${genre} <br />
			</c:forEach></td>
			<td>${movie.runtime}min</td>
			<td>${movie.rating}</td>
		</tr>
		<tr><td colspan="7"> </td></tr>
		<tr>
			<th  colspan="7">
			Director List
			</th>
		</tr>
		<tr>
			<td  colspan="7">
			<c:forEach var="director" items="${movie.directorList}">
			<a href="../director/${director.artistId}">${director.name}</a> <br />
			</c:forEach>
			</td>
		</tr>
		<tr><td colspan="7"> </td></tr>
		<tr>
			<th  colspan="7">
			Writer List
			</th>
		</tr>
		<tr>
			<td  colspan="7">
			<c:forEach var="writer" items="${movie.writerList}">
			<a href="../writer/${writer.artistId}">${writer.name}</a> <br />
			</c:forEach>
			</td>
		</tr>
		<tr><td colspan="7"> </td></tr>
		<tr>
			<th  colspan="3">
			Actor Name
			</th>
			<th  colspan="4">
			Character Name
			</th>
		</tr>
		<c:forEach var="movieChar" items="${movie.movieCharList}">
		<tr>
			<td  colspan="3">
			<a href="../actor/${movieChar.actor.artistId}">${movieChar.actor.name}</a>
			</td>
			<td  colspan="4">
			${movieChar.name}
			</td>
		</tr>
		</c:forEach>
		
		<tr><td colspan="7"> </td></tr>
		<tr>
			<th  colspan="7">
			Comment List
			</th>
		</tr>
		<c:forEach var="comment" items="${movie.commentList}">
		<tr>
			<td>
			Rating: ${comment.rating}
			</td>
			<td>
			Posted by ${comment.user.loginName}
			</td>
			<td  colspan="2">
			${comment.title}
			</td>
			<td  colspan="3">
			${comment.date} ${comment.time}
			</td>
		</tr>
		<tr>
			<td colspan="7">
			${comment.content}
			</td>
		</tr>
		<tr><td colspan="7"> </td></tr>
		</c:forEach>
	</table>
	
	<p>
	<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
		<form action="<c:url value='j_spring_security_check' />" method="post">
			Please login to post comment. <br/>
			Username: <input type="text" name="username" required="required" /> <br/>
			Password: <input type="password" name="password" required="required" /> <br/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" value="Login" />
		</form>
	</sec:authorize>
	</p>
	
	<p>
	<sec:authorize access="hasRole('ROLE_USER')">
		<form action="../postComment" method="post">
			<input type="text" name="title" 
			placeholder="A title for your comment..." required="required" /> <br/>
			<textarea name="content" placeholder="Please input your comment..." 
			required="required"></textarea> <br/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" value="Post" />
		</form>
	</sec:authorize>
	</p>

</body>
</html>