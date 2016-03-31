<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
	<p>Welcome b2a ya ${it.name} :D :D </p>
	<p>This is should be user home page</p>
	<p> You can show your current position on map and update your position on our database from <a href="/FCISquareApp/app/showLocation"> here</a>
	
	<p>Current implemented services
		"http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/signup ---
		{requires: name, email, pass}"</p>
	<p>and "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/login ---
		{requires: name, pass}"</p>
	<p>and "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/updatePosition ---
		{requires: id, lat, long}"</p>
	
	
	<p>you should implement Follow,	UnFollow, getFollowers and getLastPositionOfUser services </p>
	
	<p>and "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/followUser ---
	     {requires: followerid, followingid}"</p>
	 
	<p>and "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/unFollowUser ---
	     {requires: followerid, followingid}"</p>
	     
	<p>and "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/getFollowers ---
	     {requires: id}"</p>
	     
	<p>and "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/getFollowerPosition ---
	     {requires: id}"</p>
	     
	  <p> All parameters are post parameters </p>
	     
	
	
</body>
</html>
