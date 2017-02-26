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
<!-- Navigation -->
<c:import url="header.jsp"/>


<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Blog Post Content Column -->
        <div class="col-lg-8">

            <!-- Blog Post -->

            <!-- Title -->
            <h1>QUESTION TITLE</h1>

            <!-- Author -->
            <p class="lead">
                <small><a href="">
                    USER LOGIN</a> </small>
            </p>

            <!-- Date/Time -->
            <p>
                <span class="glyphicon glyphicon-time"></span> CREATING TIME
                <span class="glyphicon glyphicon-star-empty"></span> THEME
                    <a class="btn btn-default btn-xs"
                       href="">
                        <fmt:message key="delete"/>
                    </a>
                
            </p>

            <hr>


            <!-- Post Content -->
            QUESTION 
            <hr>

            <!-- Blog Comments -->

            <!-- Comments Form -->

                <div class="well">
                    <h4>
                        <fmt:message key="answer.leave"/>:
                    </h4>
                    <form role="form" action="" method="POST">
                        <div class="form-group">
                            <textarea class="form-control" rows="3" name="new_answer"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">
                            <fmt:message key="answer"/>
                        </button>
                    </form>
                </div>

            <hr>

            <!-- Posted Comments -->

            <!-- Comment -->
                <div class="media">
                    <div class="media-body">
                        <h4 class="media-heading">
                            <form action="" method="post">
                                <a href="">
                                    USER LOGIN
                                </a>
                                <small>CREATING TIME</small>
                                
                                <a class="btn btn-default btn-xs"
                                   href="">
                                    <fmt:message key="delete"/>
                                </a>
                               
                                <button type="submit" class="btn btn-default btn-xs">
                                    +1
                                </button>
                                <button type="submit" class="btn btn-default btn-xs">
                                    -1
                                </button>
                            </form>
                        </h4>
                        ANSWER
                    </div>
                </div>
                <hr>


        </div>

        <!-- Blog Sidebar Widgets Column -->
        <c:import url="sidebar.jsp"/>

    </div>
    <!-- /.row -->

    <hr>

    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Copyright &copy; Your Website 2014</p>
            </div>
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
