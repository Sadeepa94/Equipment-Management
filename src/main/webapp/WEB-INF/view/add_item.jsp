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
                    
                    <a class="navbar-brand" href="#"><img src="/IM_version1/resources/images/logo.jpg" style="max-height:40px; margin-top: -10px;"></a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="">Add Item</a></li>
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
                    <a class="list-group-item active" href="add_item.html"><span class="glyphicon glyphicon-plus"></span> Add Item</a>
                </div>
                <div class="list-group">
                    <a class="list-group-item" href="users.html"><span class="glyphicon glyphicon-user"></span> Users</a>
                    <a class="list-group-item" href="add_user.html"><span class="glyphicon glyphicon-plus-sign"></span> Add User</a>
                </div>
            </div>
            <div class="body">
            
                <div class="main-container">
                    <div class="container-fluid new-transaction-content">

                        <form:form class="" method="get" action="saveItem" modelAttribute="meterial">
                            <div class="row form well">
                                
                                <div class="col-sm-12 well-sm">
                                    <table class="" width="100%">
                                    <tr>
                                        <td><label>Item Code: </label><form:input class="ntfe" type="text" id="item-code" placeholder="Item Code" path="meterial_id"/>
                                        <br /><form:errors path="meterial_id" class="errors" />
                                        </td>
                                        <td>Hint- Last Item Id is: ${lastMeterial}  </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><label>Item Name: </label><form:input class="ntfe" type="text" id="item-name" placeholder="Item Name" path="meterial_name"/>
                                        <br /><form:errors path="meterial_name" class="errors" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  colspan="2">
                                            <label>Item Description:</label>
                                            <form:input class="ntfe" type="text" placeholder="Description" path="description"/>
                                            <br /><form:errors path="description" class="errors" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  colspan="2">
                                            <label>Condition:</label>
                                            <form:input class="ntfe" type="text" placeholder="condition" path="meterial_condition"/>
                                            <br /><form:errors path="meterial_condition" class="errors" />
                                            <form:input type="hidden" value="true" path="availability"/>
                                        </td>

                                    </tr>
                                    
                                    </table>
                                </div>
                                <input class="button save" type="submit" value="Save" id="save_new_item">
                                
                                
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





                            
