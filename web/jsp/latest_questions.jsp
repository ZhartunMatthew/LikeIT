<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <!-- Bootstrap Core CSS -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="resources/bootstrap/css/blog-home.css" rel="stylesheet">
</head>
<body>
<div class="container">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-6 col-md-offset-1">

            <h1 class="page-header">
                <small>
                    <fmt:message key="questions.latest"/>
                </small>
            </h1>

            <!-- Third Blog Post -->

                <h2>
                    <a href="">
                        QUESTION TITLE
                    </a>
                </h2>
                <p class="lead">
                    <small> <a href=""/>
                    USER LOGIN
                    </a>
                    </small>
                </p>
                <p>
                    <span class="glyphicon glyphicon-time"></span> CREATING TIME
                    <span class="glyphicon glyphicon-star-empty"></span> THEME
                </p>
                <a class="btn btn-primary" href="">
                    <fmt:message key="question.read"/>
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>
                <hr>

                <h2>
                    <a href="">
                        QUESTION TITLE
                    </a>
                </h2>
                <p class="lead">
                    <small> <a href=""/>
                    USER LOGIN
                    </a>
                    </small>
                </p>
                <p>
                    <span class="glyphicon glyphicon-time"></span> CREATING TIME
                    <span class="glyphicon glyphicon-star-empty"></span> THEME
                </p>
                <a class="btn btn-primary" href="">
                    <fmt:message key="question.read"/>
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>
                <hr>

                <h2>
                    <a href="">
                        QUESTION TITLE
                    </a>
                </h2>
                <p class="lead">
                    <small> <a href=""/>
                    USER LOGIN
                    </a>
                    </small>
                </p>
                <p>
                    <span class="glyphicon glyphicon-time"></span> CREATING TIME
                    <span class="glyphicon glyphicon-star-empty"></span> THEME
                </p>
                <a class="btn btn-primary" href="">
                    <fmt:message key="question.read"/>
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>
                <hr>

            <hr>

        </div>

        <!-- Blog Sidebar Widgets Column -->
        <c:import url="/jsp/sidebar.jsp"/>
    <!-- /.row -->

    <hr>

    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Copyright &copy; Your Website 2014</p>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </footer>

</div>
<!-- /.container -->

<!-- jQuery -->
<script src="resources/bootstrap/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
