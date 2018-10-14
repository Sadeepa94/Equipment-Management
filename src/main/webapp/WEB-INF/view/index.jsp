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
<title>Insert title here</title>
</head>

		<div id="nmeditModal" class="modal" role="dialog">
             <div class="modal-dialog modal-sm">
                 <div class="modal-content">
                     <div class="modal-header">
                         <h4 class="modal-title">Please Input Your Name:</h4>
                     </div>
                     <div class="modal-body form">
                         <table width="100%">
                             <tr>
                                 <td>
                                     <input type="text" style="width: 100%;" id="admin-fn" placeholder="First Name"/>
                                 </td>
                                 <td>
                                     <input type="text" style="width: 100%;" id="admin-ln" placeholder="Last Name"/>
                                 </td>
                             </tr>
                             <tr>
                             	<td></td>
                             	<td>
                                     <button id="change-an" class="button confirm">Confirm</button>
                                 </td>
                             </tr>
                             
                         </table>
                     </div>
                 </div>
             </div>
         </div>
         <div id="pneditModal" class="modal" role="dialog">
             <div class="modal-dialog modal-sm">
                 <div class="modal-content">
                     <div class="modal-header">
                         <h4 class="modal-title">Please Input Your phone number :</h4>
                     </div>
                     <div class="modal-body form">
                         <table width="100%">
                             <tr>
                                 <td style="width: 80%;">
                                     <input type="text" style="width: 100%;" id="admin-pn" placeholder="Phone Number" />
                                 </td>
                                 <td>
                                     <button id="change-apn" class="button confirm">Confirm</button>
                                 </td>
                             </tr>
                         </table>
                     </div>
                 </div>
             </div>
         </div>
         <div id="ppeditModal" class="modal" role="dialog">
             <div class="modal-dialog modal-sm">
                 <div class="modal-content">
                     <div class="modal-header">
                         <h4 class="modal-title">Choose another profile picture :</h4>
                     </div>
                     <div class="modal-body form">
                         <table width="100%">
                             <tr>
                             	 <form:form class=""  method="post" action="changeAdminPhoto" modelAttribute="admin_object" enctype="multipart/form-data">
                                 	<form:input type="hidden" id="admin-username" value="${username}" path="userName"/>
                                 	<td><form:input type="file" id="admin-pp" path="photo_temp"/></td>
                                 	<td><button id="change-app" type="submit" class="button confirm">Confirm</button></td>
                                 </form:form>
                             </tr>
                         </table>
                     </div>
                 </div>
             </div>
         </div>
         <div id="settingsModal" class="modal" role="dialog">
             <div class="modal-dialog modal-sm">
                 <div class="modal-content">
                     <div class="modal-header">
                         <h4 class="modal-title">Change Administrator Settings :</h4>
                     </div>
                     <div class="modal-body form">
                         <form class=""  method="post" action="<c:url value="/changeAdminSettings" />">
                         <table width="100%">
                             <tr>
                             	<input type="hidden" name="uname" id="uname" value="${username}"/>
                                 <td colspan="2"><input type="text" name="new_uname" id="new_un" placeholder="Input New UserName"/></td>
                             </tr>
                             <tr>
                                 <td><input type="text" name="old_pwd" id="old_pwd" placeholder="Input Old Password" /></td>
                                 <td><input type="password" name="new_pwd" id="new_pwd" placeholder="Input New Password" /></td>
                             </tr>
                             <tr>
                                 <td><input type="password" name="confirm_new_pwd" id="confirm_new_pwd" placeholder="Confirm New Password"/></td>
                                 <td><button id="edit-setting" type="submit" class="button confirm">Confirm</button></td>
                             </tr>
                         </table>
                         </form>
                     </div>
                 </div>
             </div>
         </div>





    <body class="main" onload="home_init();">
        
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
                        <li class="active"><a href="">Home</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a><span class="glyphicon"></span>${username}</a></li>
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
                    <a class="list-group-item active" href=""><span class="glyphicon glyphicon-home"></span> Home</a>
                    <a class="list-group-item" href="transaction.html"><span class="glyphicon glyphicon-transfer"></span> New Transaction</a>
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
        
                    <div class="container-fluid profile">
                        <div class="col-xs-9 well-lg" id="prf">
                            <span class="glyphicon glyphicon-user profile-logo col-sm-3"><img src="/resources/images/${admin.getPhoto()}" /></span>
                            <div class="col-sm-8 pdt">                        
                                <span class="profile-detail">${admin.getFirstName()} ${admin.getLastName()}<button id="editnm" class="glyphicon glyphicon-edit" data-toggle="modal" data-target="#nmeditModal"></button></span>
                                <span class="profile-detail">${admin.getContactNo()}<button id="editpn" class="glyphicon glyphicon-edit" data-toggle="modal" data-target="#pneditModal"></button></span>
                                <span id="advanced-settings-btn-container" class="profile-detail"><button id="advanced-settings" class="" data-toggle="modal" data-target="#settingsModal">Advanced Settings</button></span>
                            </div>
                            <button id="profile-pic" class="glyphicon glyphicon-edit" data-toggle="modal" data-target="#ppeditModal"></button>
                        </div>
                        <div id="dt">
                            

                            <div class="time">
                                <div id="dig1" class="dig">1</div>
                                <div id="dig2" class="dig">2</div>
                                <div id="dig3" class="dig">3</div>
                                <div id="dig4" class="dig">4</div>
                                <div id="dig5" class="dig">5</div>
                                <div id="dig6" class="dig">6</div>
                                <div id="dig7" class="dig">7</div>
                                <div id="dig8" class="dig">8</div>
                                <div id="dig9" class="dig">9</div>
                                <div id="dig10" class="dig">10</div>
                                <div id="dig11" class="dig">11</div>
                                <div id="dig12" class="dig">12</div>

                                <div id="hour1" class="hour"></div>
                                <div id="hour2" class="hour"></div>
                                <div id="hour3" class="hour"></div>
                                <div id="hour4" class="hour"></div>

                                <div id="min1" class="min"></div>
                                <div id="min2" class="min"></div>
                                <div id="min3" class="min"></div>
                                <div id="min4" class="min"></div>
                                <div id="min5" class="min"></div>

                                <div id="sec1" class="sec"></div>
                                <div id="sec2" class="sec"></div>
                                <div id="sec3" class="sec"></div>
                                <div id="sec4" class="sec"></div>
                                <div id="sec5" class="sec"></div>
                                <div id="sec6" class="sec"></div>
                            </div>
                            <p class="date"></p>
                            
                            
                        </div>
                    </div>

                    <div class="home-content">
                        <h4>Hello ${username}, What do you need to perform ?</h4>
                        <div class="row">
                        <div class="col-xs-3">
                            <a href="history.html" class="glyphicon glyphicon-transfer home-icon" data-toggle="tooltip" data-placement="bottom" title="View Issue Recieve History"></a>
                        </div>
                        <div class="col-xs-3">
                            <a href="items.html" class="glyphicon glyphicon-briefcase home-icon" data-toggle="tooltip" data-placement="bottom" title="View details of Items"></a>
                        </div>
                        <div class="col-xs-3">
                            <a href="users.html" class="glyphicon glyphicon-stats home-icon" data-toggle="tooltip" data-placement="bottom" title="View Stats of Users"></a>
                        </div>
                        <div class="col-xs-3">
                            <a href="add_user.html" class="glyphicon glyphicon-plus home-icon" data-toggle="tooltip" data-placement="bottom" title="Add New User to System"></a>
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
