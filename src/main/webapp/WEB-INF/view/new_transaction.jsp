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
                        <li class="active"><a href="">New Transaction</a></li>
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
                    <a class="list-group-item active" href=""><span class="glyphicon glyphicon-transfer"></span> New Transaction</a>
                </div>
                <div class="list-group">
                    <a class="list-group-item" href="items.html"><span class="glyphicon glyphicon-list-alt"></span> Items</a>
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

                        <div id="myModal" class="modal" role="dialog" data-keyboard="false" data-backdrop="static">
                            <div class="modal-dialog modal-sm">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <a class="close glyphicon glyphicon-home" href="index.html"></a>
                                        <h4 class="modal-title"> Please Scan Barcode of Item</h4>
                                    </div>
                                    <div class="modal-body form">
                                        <input type="text" id="barcode-input" placeholder="Transaction will continue soon as you scanned the Barcode & hit Enter" onchange="scan_finish();"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div id="borrowersModal" class="modal" role="dialog">
                            <div class="modal-dialog modal-sm">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title"> Select Borrower</h4>
                                    </div>
                                    <div class="modal-body form">
                                        <ul class="list-group mylist">  
											<c:forEach items="${users}" var="e">  
												<li id="${e.get(0)}" class="list-group-item tr-user-list-item"><a>${e.get(1)} ${e.get(2)}</li></a>  
											</c:forEach>  
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <form:form class="transaction" method="post" action="saveTransaction" modelAttribute="transaction">
                            <div class="row form well">
                                <div class="col-sm-12 well-sm">
                                    <table class="" width="100%">
                                    <form:input type="hidden" id="tr-id" path="transaction_id"/>
                                    <tr>
                                        <td><label>Item Code: </label><form:input class="ntfe" type="text" id="tr-item-code" name="tr-item-code" placeholder="Item Code" path="meterial.meterial_id"/>
                                        <br /><form:errors path="meterial.meterial_id" class="errors" />
                                        </td>
                                        
                                        <td><label>Item Name: </label><form:input class="ntfe" type="text" id="tr-item-name" name="tr-item-name" placeholder="Item Name" path="meterial.meterial_name"/>
                                        <br /><form:errors path="meterial.meterial_name" class="errors" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Borrower Id: </label>
                                            <form:input class="ntfe" type="text" id="tr-user-id" name="tr-user-id" placeholder="Borrower Id" path="user.userId"/>
                                            <br /><form:errors path="user.userId" class="errors" />
                                        </td>
                                        <td>
                                        	or <input id=" " type="button" value="Select Borrower" data-toggle="modal" data-target="#borrowersModal"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Date of Borrow: </label>
                                            
                                            <form:input type="date" class="ntfe" id="tr-dob" name="tr-dob" path="borrowed_date"/>
                                            <br /><form:errors path="borrowed_date" class="errors" />
                                        </td>
                                        <td id="tr-pdor">
                                            <label>Date of Return: </label>
                                            <form:input type="date" class="ntfe" id="tr-dor" name="tr-dor" path="hand_overed_date"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td id="tr-pcwr" colspan="2">
                                            <label id="tr-mc-lbl"></label>
                                            <form:input class="ntfe" id="tr-mc" name="tr-cwr" type="text" placeholder="condition" path="meterial.meterial_condition"/>
                                        </td>

                                    </tr>
                                    </table>
                                </div>
                                <input class="button" type="submit" id="finish-transaction" value="Confirm">
                            </div>
                            
                        </form:form>
                                                    
                            
                    </div>
                    <h3 id="success"></h3>
                </div>
                <div class="footer">
                    <p>All rights Reserved. Stargate IT-Innovation </p>
                </div>
            </div>
        </div>
    </body>
</html>





                            
