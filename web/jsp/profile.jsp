<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title>Title</title>
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
    <p>
        <b><fmt:message key="login"/>: </b>
        USER LOGIN
    </p>
    <p>
        <b><fmt:message key="rating"/>: </b>
        USER RATING
    </p>
    <p>
        <b><fmt:message key="regdate"/> :</b>
        USER REG DATE
    </p>
    <p>
        <button  class="btn btn-default" type="submit" onclick=" location.href = ''">
                <fmt:message key="profile.edit"/>
        </button>
    </p>
</div>

<c:import url="sidebar.jsp"/>

</div>
<script src="resources/bootstrap/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
