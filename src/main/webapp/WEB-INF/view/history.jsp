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
                        <li class="active"><a href="">Item Issues & Recievings History</a></li>
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
                    <a class="list-group-item" href=""><span class="glyphicon glyphicon-list-alt"></span> Items</a>
                    <a class="list-group-item" href="add_item.html"><span class="glyphicon glyphicon-plus"></span> Add Item</a>
                </div>
                <div class="list-group">
                    <a class="list-group-item" href="users.html"><span class="glyphicon glyphicon-user"></span> Users</a>
                    <a class="list-group-item" href="add_user.html"><span class="glyphicon glyphicon-plus-sign"></span> Add User</a>
                </div>
            </div>
            <div class="body">
            
                <div class="main-container">
                    <div class="container-fluid new-transaction-content">
								<form:form class=""  method="post" action="getTransactionHistory">
                                 	Start Date:<input type="date" name="sdate" value="${sdate}"/>
                                 	End Date: <input type="date" name="edate" value="${edate}"/>
                                 	<td><button id="change-app" type="submit" class="button confirm">Filter</button></td>
                                 </form:form>
                        		<div class="col-sm-12 well-sm">
                                    <div class="well" id="transaction-history">
                                        <span></span>
                                        <table class="table">
                                        	<tr>
                                        		<th>Meterial</th>
                                        		<th>Borrowed User</th>
                                        		<th>Borrowed Date</th>
                                        		<th>Hand_Overed Date</th>
                                        		<th>Current Condition</th>
                                        	</tr> 
											<c:forEach items="${transactions}" var="e">  
												<tr id="${e.getTransaction_id()}">
													<td>${e.getMeterial().getMeterial_name()}</td><td> ${e.getUser().getFirstName()} ${e.getUser().getLastName()} </td><td> ${e.getBorrowed_date()} </td><td> ${e.getHand_overed_date()} </td><td> ${e.getMeterial().getMeterial_condition()}</td>
												</tr>  
											</c:forEach>  
                                        </table>
                                    </div>
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





                            
