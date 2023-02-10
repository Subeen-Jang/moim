<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>footer</title>
<style>
body{
	margin:0;
	padding:0;
}
#footer{
	width:100vw;
	background-color:#33363C;
	padding-top:40px;
}
.footer_con {
	width:1280px;
	height:180px;
	margin:0 auto;
	display:flex;
	align-items:baseline;
	justify-content: space-between;
}
#brand{
	width:108px;
	height:100px;
	border:1px solid #white;
}
.sns{
	display:flex;
	justify-content: space-between;
}
.insta, .fb{
	width:45px;
	height:45px;
	background:white;
}
h1{
	color:white;
	opacity: 0.3;
	margin:0;
	margin-bottom:10px;
	font-size: 36px;
}

#footer #menu ul {
	width:500px;
	display:flex;
	align-items:flex-start;
	justify-content: space-around;
	color:white;
	cursor:pointer;
	padding:0;
}
#footer #menu li{
	opacity: 0.6;
}
#footer #menu li a{
	text-decoration: none;
	color:white;
}
#footer li{
	list-style-type:none;
}

#footer #info {
	color:white;
	opacity: 0.6;
}
#footer #info li{
	margin:6px 0;
}
</style>
</head>
<body>
<div id="footer">
<div class="footer_con">
<div id="brand">
<h1>MOIM</h1>
<div class="sns">
	<div class="insta"><a href=""></a></div>
	<div class="fb"><a href=""></a></div>
</div>
</div>
<div id="menu">
<ul>
	<li><a href="/moim/company.jsp">회사소개</a></li>
	<li>이용약관</li>
	<li>개인정보처리방침</li>
	<li><a href="/moim/noimg/notiList.jsp?idx_info=<%="0"%>&category=<%="1"%>">공지사항</a></li>
</ul>
</div>
<div id="info">
<ul>
	<li>서울특별시 서대문구 대현동</li>
	<li>TEL : 02-123-4567</li> 
	<li>Email : moim@moininc.com</li>
	<li>COPYRIGHT &copy MOIM INC. ALL RIGHTS RESERVED</li>
</ul>
</div>
</div>
</div>
</body>
</html>