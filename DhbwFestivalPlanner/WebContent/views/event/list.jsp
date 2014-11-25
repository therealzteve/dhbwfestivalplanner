<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%-- JSP Importe --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>








	<tiles:insertTemplate   template="/views/style/template/main.jsp">
		
		<%-- Page Title: --%>
		<tiles:putAttribute name="pageTitle" value="Deine Events:"></tiles:putAttribute>
		
		
		
		<%-- Body of List Page --%>
		<tiles:putAttribute name="content">
			<p>
			<h2>Current events:</h2>
			<table>
				<tr>
					<th>ID</th>
					<th>Name</th>
				</tr>
				<c:forEach items="${events}" var="event">
					<tr>
						<td>${event.id}</td>
						<td>${event.name}</td>
						<td>${event.creator.name}</td>
					</tr>
				</c:forEach>
			</table>
			</p>
		</tiles:putAttribute>
		
	</tiles:insertTemplate>