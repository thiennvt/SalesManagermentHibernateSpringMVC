
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page Menu Left</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
</head>
<body>
	<div id="menu" class="clearfix">
		<header class="clearfix">
			<h5 class="mainmenu">
				<strong>MAIN MENU</strong>
			</h5>
		</header>
		<nav>
			<ul>
				<li><a href="<c:url value="/hoadon/initGetAll.htm"/>"
					target="display"><i class="fa fa-user"></i> Quản lí đơn hàng</a></li>
			</ul>
		</nav>
	</div>
</body>
</html>
