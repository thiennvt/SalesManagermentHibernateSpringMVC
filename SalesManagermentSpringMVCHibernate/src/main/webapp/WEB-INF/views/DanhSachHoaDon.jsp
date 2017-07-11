
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hóa đơn</title>
<link rel="stylesheet"
	href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>">

<script type="text/javascript"
	src="<c:url value="/resources/jquery/jquery.min.js"/>"></script>
<script type="text/javascript"
	src='<c:url value = "/resources/bootstrap/js/bootstrap.js"/>'></script>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
</head>
<body>
	<div class="center">
		<div>
			<spring:url value="initGetAll/?type=xls" var="xlsURL" />
			<spring:url value="initGetAll/?type=xlsx" var="xlsxURL" />
			
			<div class="button_left" style="margin-top: 15px">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button"
						data-toggle="dropdown">
						Xuất file Excel <span class="caret"></span>
					</button>
					<ul class="dropdown-menu format" style="margin-left: -15px;">
						<li><a href="${xlsURL}">.xls</a></li>
						<li><a href="${xlsxURL}">.xlsx</a></li>
					</ul>
				</div>
			</div>
			<p style="color: red; font-size: 20px; padding-left: 45%">
				<b>Hóa đơn</b>
			</p>
			<div class="search_right">
				<s:form action="xuLiTimKiem.htm" commandName="hoadon" method="GET">
					<s:input path="ngayLap" type="text"
						placeholder="Search information" />
					<button style="cursor: pointer">Search</button>
				</s:form>
			</div>
		</div>
		<section>
			<table border="1">
				<thead>
					<tr class="infor">
						<th style="text-align: center;">Mã HĐ</th>
						<th style="text-align: center;">Ngày lập</th>
						<th style="text-align: center;">Mã SP</th>
						<th style="text-align: center;">Tên SP</th>
						<th style="text-align: center;">Số lượng</th>
						<th style="text-align: center;">Giá tiền</th>
						<th style="text-align: center;">Khuyến mại(%)</th>
						<th style="text-align: center;">VAT</th>
						<th style="text-align: center;">Tổng tiền</th>
						<th style="text-align: center;">Mã người mua</th>
						<th style="text-align: center;">Tên người mua</th>
						<th style="text-align: center;">Điểm tích</th>
						<th style="text-align: center;">Xếp hạng</th>
						<th style="text-align: center;">Mã hân viên</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="hoadon" items="${listHoadon}">
						<tr>
							<td>${hoadon.mahd}</td>
							<td>${hoadon.ngayLap}</td>
							<td>${hoadon.sanpham.masp}</td>
							<td>${hoadon.sanpham.tensp}</td>
							<td>${hoadon.soluong}</td>
							<td>${hoadon.sanpham.gia}</td>
							<td>${hoadon.sanpham.khuyenmai}</td>
							<td>${hoadon.vat}</td>
							<td>${hoadon.tongtien}</td>
							<td>${hoadon.khachhang.makh}</td>
							<td>${hoadon.khachhang.fullName}</td>
							<td>${hoadon.khachhang.diemtich}</td>
							<td>${hoadon.khachhang.xephang}</td>
							<td>${hoadon.nhanvien.manv}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
	</div>
</body>
</html>

