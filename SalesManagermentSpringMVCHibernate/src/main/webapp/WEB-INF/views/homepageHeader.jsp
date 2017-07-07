
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page Header</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
</head>
<body>
	<div class="main">
		<header class="_header">
			<a href="<c:url value="initShowhomepage.htm"/>" class="logo">BÀI TẬP<span style="font-weight: bold; color: red"> DEMO</span>
			</a>
			<nav class="itemright">
				<div class="dropdown">
					<div class="dropdown-content">
						<ul>
							<li><a href="#" target="display"><i
									class="fa fa-user-md"></i> Hồ sơ</a></li>
							<li><a href="#"><i class="fa fa-sign-out"></i> Đăng xuất</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</header>
	</div>
</body>
</html>
