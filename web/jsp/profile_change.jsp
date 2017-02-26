<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title>Change Profile</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <!-- Bootstrap Core CSS -->
    <link href="../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../resources/bootstrap/css/blog-post.css" rel="stylesheet">
</head>
<body>
<c:import url="header.jsp"/>
<div class="well col-lg-offset-2 col-lg-5" >
    <form onsubmit="" action="" method="POST">
        <b><fmt:message key="login"/>:</b>
        <input type="text" class="form-control" required pattern="^[a-zA-Z]+[a-zA-Z0-9]{3,20}$"
               name="input_login" value="USER LOGIN">
        <b><fmt:message key="edit.oldpass"/>:</b>
            <input type="password" required class="form-control" name="old_pass">
        <b><fmt:message key="edit.newpass"/>:</b>
            <input type="password" required class="form-control" name="new_pass">
        <b><fmt:message key="email"/>:</b> <input type="email" required class="form-control" name="new_email" value="USER EMAIL">
        <h5>
        <button  class="btn btn-primary" type="submit" value="login" name="submit">
            <fmt:message key="save"/>
        </button>
        </h5>
    </form>
</div>
<c:import url="sidebar.jsp"/>

</div>
</body>
</html>
