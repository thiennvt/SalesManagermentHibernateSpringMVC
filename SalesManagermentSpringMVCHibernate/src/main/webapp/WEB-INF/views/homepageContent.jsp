

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page Content</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
</head>
<body>
	<section class="content">
		<p class="title_left">
			<a href="<c:url value="#"/>"
				style="text-decoration: none; color: black"><b
				class="fa fa-home" style="font-size: 15px"></b> Home</a> <span
				style="font-size: 20px; padding-left: 430px; color: blue">QUẢN
				LÍ BÁN HÀNG</span>
		</p>
		<div class="items">
			<iframe src="<c:url value="initShowWelcome.htm"/>" class="wallapper"
				name="display" frameBorder="0" target="display"></iframe>
		</div>
		<footer class="main-footer">
			<strong>Copyright &copy; 2016 <a href="#">Company</a>.
			</strong> All rights reserved.
		</footer>
	</section>
</body>
</html>
