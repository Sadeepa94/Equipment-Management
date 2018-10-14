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
                        <li class="active"><a href="">Users</a></li>
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
                    <a class="list-group-item active" href=""><span class="glyphicon glyphicon-user"></span> Users</a>
                    <a class="list-group-item" href="add_user.html"><span class="glyphicon glyphicon-plus-sign"></span> Add User</a>
                </div>
            </div>
            <div class="body">
            
                <div class="main-container">
                    <div class="container-fluid new-transaction-content">

                            <div class="row form">
                                <div class="col-sm-4 well-sm">
                                    <div class="well" id="barcode-readed">
                                        <span>Select a User :</span>
                                        <ul class="list-group mylist">  
											<c:forEach items="${users}" var="e">  
												<li id="${e.get(0)}" class="list-group-item user-list-item"><a>${e.get(1)} ${e.get(2)}</li></a>  
											</c:forEach>  
                                        </ul>
                                    </div>
                                </div>

								<form:form class=""  method="post" action="saveEditedUser" modelAttribute="user" enctype="multipart/form-data">

                                <div class="col-sm-8 well-sm">
                                    
                                    <table class="" width="100%">
                                    <tr>
                                        <td><label>User Id: </label><form:input id="uid" class="ntfe" type="text" placeholder="User Id" path="userId"/>
                                        <br /><form:errors path="userId" class="errors"/>
                                        </td>
                                        <td><label>Job Title: </label><form:input id="ujt" class="ntfe" type="text" placeholder="Job Title" path="jobTitle"/>
                                        <br /><form:errors path="jobTitle" class="errors"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  rowspan="3"><span class="col-sm-3 bbb" id="u_photo"></span></td>
                                    </tr>
                                    <tr>
                                        <td><label>First Name: </label><form:input id="ufname" class="ntfe" type="text" placeholder="First Name" path="firstName"/>
                                        <br /><form:errors path="firstName" class="errors"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>Last Name: </label><form:input id="ulname" class="ntfe" type="text" placeholder="Last Name" path="lastName"/>
                                        <br /><form:errors path="lastName" class="errors"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Change: </label>
                                            <form:input id="uphoto" class="ntfe" type="file" value="" path="photo_temp"/>
                                        </td>
                                        <td>
                                            <label>Contact No: </label>
                                            <form:input id="ucontact" class="ntfe" type="text" path="contactNo"/>
                                            <br /><form:errors path="contactNo" class="errors"/>
                                        </td>
                                    </tr>
                                    <tr>
                                    	<td><label>Address: </label></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <form:input id="uaddno" class="ntfe" type="text" placeholder="No" path="address.no"/>
                                            <form:input id="uaddtown" class="ntfe" type="text" placeholder="town" path="address.town"/>
                                        </td>
                                        <td>
                                        	<form:input id="uaddstreet" class="ntfe" type="text" placeholder="Street" path="address.street"/>
                                           <form:input id="uaddcity" class="ntfe" type="text" placeholder="city" path="address.city" />
                                        </td>
                                    </tr>
                                    
                                    </table>
                                </div>
                                <input class="button save" type="button" id="delete_user" value="Delete">
                                <input class="button save" type="submit" value="Save" id="save_user">
                        		</form:form>        
                                
                            </div>
                            
                        

                                                    
                            
                    </div>
                </div>
                <div class="footer">
                    <p>All rights Reserved. Stargate IT-Innovation </p>
                </div>
            </div>
        </div>
    </body>
</html>





                            
