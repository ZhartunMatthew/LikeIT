<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <h4>
        <fmt:message key="question.input"/>
    </h4>
    <form action="/controller?command=addquestion" method="POST">
        <div class="form-group">
            <input class="form-control" name="title"
                   placeholder=<fmt:message key="title"/> >
        </div>
        <div class="form-group">

            <select name="themeid">
                <c:forEach var="theme" items="${themes}">
                    <option value="${theme.id}">${theme.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <textarea class="form-control" rows="6" name="problem" placeholder=<fmt:message key="question"/> ></textarea>
        </div>
        <button type="submit" class="btn btn-primary" name="submit">
            <fmt:message key="ready"/>
        </button>
    </form>
</div>
<c:import url="sidebar.jsp"/>

</div>
</body>
</html>
