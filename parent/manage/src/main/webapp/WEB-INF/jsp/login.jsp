<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<head>
	<title>admin</title>
	<!-- Meta tag Keywords -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8" />
	<meta name="keywords" content=""/>
	
	<script>
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
	</script>
	
	<!-- Meta tag Keywords -->
	<!-- css files -->
	<link rel="stylesheet" href="../css/style.css" type="text/css" media="all" />

</head>

<body>
	<!-- bg effect -->
	<div id="bg">
		<canvas></canvas>
		<canvas></canvas>
		<canvas></canvas>
	</div>
	<!-- //bg effect -->
	<!-- title -->
	<h1>Mall Admin Login</h1>
	<!-- //title -->
	<!-- content -->
	<div class="sub-main-w3">
		<form action="/" method="post">
			<h2>Login Now
				<i class="fa fa-long-arrow-down"></i>
			</h2>
			<div class="form-style-agile">
				<label>
					<i class="fa fa-user"></i>
					Username
				</label>
				<input placeholder="Username" name="name" type="text" value="admin" required="">
			</div>
			
			<div class="form-style-agile">
				<label>
					<i class="fa fa-unlock-alt"></i>
					Password
				</label>
				<input placeholder="Password" name="password" type="password" required="">
			</div>
			
			<!-- checkbox -->
			<div class="wthree-text">
				<ul>
					<li>
						<label class="anim">
							<input type="checkbox" class="checkbox" required="">
							<span>Stay Signed In</span>
						</label>
					</li>
					<li>
						<a href="#">Forgot Password?</a>
					</li>
				</ul>
			</div>
			
			<!-- //checkbox -->
			<input type="submit" value="Log In">
		</form>
	</div>
	<!-- //content -->

	<!-- copyright -->
	<div class="footer">
		<p>Copyright &copy; 2018.Company name All rights reserved.</p>
	</div>
	<!-- //copyright -->

	<!-- Jquery -->
	<script src="../js/jquery-3.3.1.min.js"></script>
	<!-- //Jquery -->

	<!-- effect js -->
	<script src="../js/canva_moving_effect.js"></script>
	<!-- //effect js -->

</body>

</html>