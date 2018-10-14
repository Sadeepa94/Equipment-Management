<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/css/app.css" />"  rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
<script src="<c:url value="/resources/js/app.js" />"></script>
<style >
.errors{
color:red;
}

</style>


<title>Insert title here</title>
</head>
    <body class="main" onload="new_transaction_init();">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span> 
                    </button>
                    
                    <a class="navbar-brand" href="#"><img src="/resources/images/logo.png" style="max-height:40px; margin-top: -10px;"></a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="">Add User</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a><span class="glyphicon"></span> ${username}</a></li>
                        <li><a href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="main-wrap">
            <input id="ss" type="checkbox" onchange="toggle_ssl();" />
            <label for="ss" id="ssl"><span class="glyphicon glyphicon-chevron-left" id="sslg"></span></label>
            <div class="sidebar">
                <div class="list-group">
                    <a class="list-group-item" href="home.html"><span class="glyphicon glyphicon-home"></span> Home</a>
                    <a class="list-group-item" href="transaction.html"><span class="glyphicon glyphicon-transfer"></span> New Transaction</a>
                </div>
                <div class="list-group">
                    <a class="list-group-item" href="items.html"><span class="glyphicon glyphicon-list-alt"></span> Items</a>
                    <a class="list-group-item" href="add_item.html"><span class="glyphicon glyphicon-plus"></span> Add Item</a>
                </div>
                <div class="list-group">
                    <a class="list-group-item" href="users.html"><span class="glyphicon glyphicon-user"></span> Users</a>
                    <a class="list-group-item active" href=""><span class="glyphicon glyphicon-plus-sign"></span> Add User</a>
                </div>
            </div>
            <div class="body">
            
                <div class="main-container">
                    <div class="container-fluid new-transaction-content">

                        <form:form class=""  method="post" action="saveUser" modelAttribute="user" enctype="multipart/form-data">
                            <div class="row form well">
                                
                                <div class="col-sm-12 well-sm">
                                    <table class="" width="100%">
                                    <tr>
                                        <td><label>User Id: </label><form:input class="ntfe" type="text" placeholder="User Id" path="userId"/>
                                        <br /><form:errors path="userId" class="errors"/>
                                        </td>
                                        <td>Hint- Last User Id is: ${lastUser}  </td>
                                    </tr>
                                    <tr>
                                        <td ><label>First Name: </label><form:input class="ntfe" type="text" placeholder="First Name" path="firstName"/>
                                        <br /><form:errors path="firstName" class="errors"/>
                                        </td>
                                        <td ><label>Last Name: </label><form:input class="ntfe" type="text" placeholder="Last Name" path="lastName"/>
                                        </br ><form:errors path="lastName" cssClass="errors"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><label>Job Title: </label><form:input class="ntfe" type="text" placeholder="Job Title" path="jobTitle"/>
                                        <br /><form:errors class="errors" path="jobTitle" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Contact No: </label>
                                            <form:input class="ntfe" type="text" placeholder="Contact No" path="contactNo"/>
                                            <br /><form:errors path="contactNo" class="errors" />
                                        </td>
                                        <td>
                                            <label>User Image: </label>
                                            <form:input class="ntfe" type="file" path="photo_temp"/>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                    	<td><label>Address: </label></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <form:input class="ntfe" type="text" placeholder="No" path="address.no"/>
                                            <form:input class="ntfe" type="text" placeholder="town" path="address.town"/>
                                        </td>
                                        <td>
                                        	<form:input class="ntfe" type="text" placeholder="Street" path="address.street"/>
                                           <form:input class="ntfe" type="text" placeholder="city" path="address.city" />
                                        </td>
                                    </tr>
                                    
                                    </table>
                                </div>
                                <input class="button save" type="submit" value="Save" id="save_new_user">
                                
                                
                            </div>
                            
                        </form:form>                            
                          <h4>${success}</h4>  
                    </div>
                </div>
                <div class="footer">
                    <p>All rights Reserved. Stargate IT-Innovation </p>
                </div>
            </div>
        </div>
    </body>
</html>





                            
