<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style >
.errors{
color:red;
}

</style>
<title>Admin page</title>
</head>
<body>










            
                <div class="main-container">
                    <div class="container-fluid new-transaction-content">

                        <form:form action="newAdmin.html" method="post" modelAttribute="admin">
                            <div class="row form well">
                                
                                <div class="col-sm-12 well-sm">
                                    <table class="" width="100%">
                                    <tr>
                                        <td  colspan="2"><label>User Name: </label>	<form:input type="text" path="userName" placeholder="username"/><br />
																		<form:errors path="userName" class="errors" /><br />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td ><label>First Name: </label><form:input type="text" path="firstName" placeholder="Firstname"/><br />
<form:errors path="firstName" class="errors" /><br />
                                        </td>
                                        <td ><label>Last Name: </label><form:input type="text" path="lastName" placeholder="lastName"/><br />
<form:errors path="lastName" class="errors" /><br />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><label>Password: </label><form:input type="password" path="password" placeholder="password"/><br />
<form:errors path="password" class="errors" /><br />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Contact No: </label>
                                            <form:input type="text" path="contactNo" placeholder="contact no"/><br />
<form:errors path="contactNo" class="errors" />
                                        </td>
                                                                            </table>
                                </div>
                                <input class="button save" type="submit" value="submit" id="save_new_user"/><br />
                                
                                
                            </div>
                            
                        </form:form>                            
  
                    </div>
                </div>







</body>
</html>