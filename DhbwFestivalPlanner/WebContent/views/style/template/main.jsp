<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>	<tiles:insertAttribute name="pageTitle"></tiles:insertAttribute></title>
</head>
<body>
 <div id="toolbar">Hier kommt die Login Logout Toolbar hin</div>

	<h1><tiles:insertAttribute name="pageTitle"></tiles:insertAttribute></h1>
	<tiles:insertAttribute name="content"></tiles:insertAttribute>    
</body>
</html>