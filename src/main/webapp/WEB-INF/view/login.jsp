<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/css/app.css" />"  rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
<script src="<c:url value="/resources/js/app.js" />"></script>
<title>Insert title here</title>
</head>
    <body class="login">

        <div id="login">

            <form class="panel panel-box form" method="post" action="<c:url value="/login" />">
                <div class="form-row">
                    <input class="form-large" type="text" name="username" placeholder="username">
                </div>
                <div class="form-row">
                    <input class="form-large" type="password" name="password" placeholder="password">
                </div>
                <div class="form-row">
                    <p style="color:red">${error}</p>
                </div>
                <div class="form-row">
                    <input type="submit" value="Login" class="button" id="admin_login_button" />
                </div>
                <div class="form-row">
                    <label class="float-left"><input type="checkbox"> Remember Me</label>
                    <a class="float-right" href="<c:url value="/login" />">Forgot Password?</a>
                </div>
            </form>

        </div>

    

</body>
    
    
    
    
    
</html>
