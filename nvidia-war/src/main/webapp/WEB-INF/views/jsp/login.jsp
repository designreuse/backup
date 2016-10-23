<%@page import="java.util.Random"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%-- <spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
 --%>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>DGX1 | Login</title>

    <link href="resources/core/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/core/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="resources/core/css/animate.css" rel="stylesheet">
    <link href="resources/core/css/style2.css" rel="stylesheet">
    <link href="resources/core/css/style.css" rel="stylesheet">
    <link href="resources/core/img/NVLogo_2D_WT2.png" rel="icon"></link>

</head>

<body class="gray-bg">

    <div class="middle-box1 text-center loginscreen animated fadeInDown" style="padding-top: 40px">
       <div class="col-sm-4"></div>
			<div class="col-sm-4">
			
				  <div class="ibox-content" style="border-radius: 5px; border:none;">
				  <center><img src="resources/core/img/login_image.jpg" class="img-responsive" style="width: 358px; height:67px;"></center>
				  <h3 style="margin-top: 20px;">Welcome to DGX1</h3>
            <!-- <p>Perfectly designed and precisely prepared admin theme with over 50 pages with extra new web app views.
                
            </p>
			 -->		<p>Login to see it in action.</p>
					<hr>
					<form class="m-t" role="form" action="login" method="post">
						<div class="form-group">
							<input type="email" class="form-control" name="email" placeholder="Username">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" name="password" placeholder="Password">
						</div>
						<button type="submit" class="btn btn-primary login block full-width m-b">Login</button>
						<%-- <p>${name}</p> --%>
						<c:if test="${not empty name }">
						 <div class="alert alert-danger"> ${name}</div>
						</c:if>
						<a href="javascript:;" data-toggle="modal" data-target="#setting" class="forget_password"><small>Forgot password?</small></a>
<!-- 						<p class="text-muted text-center"><small>Do not have an account?</small></p>
						<a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a>
 -->					</form>
					<p class="m-t"> <small>Copyright &copy; 2016 NVIDIA Corporation</small> </p>
				</div>
			</div>
    </div>
	
	<div id="setting" class="modal fade" role="dialog">
	
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<div class="ibox-content">

                    <h2 class="font-bold">Forgot password</h2>

                    <p>
                        Enter your email address and your password will be reset and emailed to you.
                    </p>

                    <div class="row">

                        <div class="col-lg-12">
                            <form class="m-t" role="form" action="index.html">
                                <div class="form-group">
                                    <input type="email" class="form-control" placeholder="Email address" required="">
                                </div>

                                <button type="submit" class="btn btn-primary block full-width m-b">Send new password</button>

                            </form>
                        </div>
                    </div>
                </div>
				</div>
			</div>
	</div>
    <!-- Mainly scripts -->
    <script src="resources/core/js/jquery-2.1.1.js"></script>
    <script src="resources/core/js/bootstrap.min.js"></script>

</body>

</html>
