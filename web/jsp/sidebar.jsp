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
<div class="col-md-4">

    <!-- Blog Search Well -->
    <div class="well">

        <div class="input-group">
            <a class="btn btn-primary" href="">
                <fmt:message key="ask.question"/>
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
        </div>
    </div>


    <!-- Blog Categories Well -->
    <div class="well">
        <h4>
            <fmt:message key="opportunities"/>:
        </h4> <hr>
        <form onsubmit="" action="" method="POST">
            <b><fmt:message key="new.theme.create"/>:</b>
            <input type="text" class="form-control" required  name="new_theme"
                   placeholder=<fmt:message key="new.theme"/> >
            <h5>
                <button  class="btn btn-primary" type="submit" name="submit">
                    <fmt:message key="ready"/>
                </button>
            </h5>
        </form>
    </div>


    <!-- Side Widget Well
    <div class="well">
        <h4>Side Widget Well</h4>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus laudantium odit aliquam repellat tempore quos aspernatur vero.</p>
    </div>-->

</div>
</body>
</html>
