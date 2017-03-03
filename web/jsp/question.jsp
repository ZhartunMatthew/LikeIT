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
            <h1><c:out value="${question.title}"/></h1>

            <!-- Author -->
            <p class="lead">
                <small><a href="/controller?command=showprofile&userid=${question.user.id}">
                    <c:out value="${question.user.login}"/></a> </small>
            </p>

            <!-- Date/Time -->
            <p>
                <span class="glyphicon glyphicon-time"></span> <c:out value="${question.creatingTime}"/>
                <span class="glyphicon glyphicon-star-empty"></span> <c:out value="${question.theme.name}"/>
                <c:if test="${sessionScope.user.id == question.user.id || sessionScope.user.role == 1}">

                    <a class="btn btn-default btn-xs"
                       href="/controller?command=deletequestion&questionid=${question.id}">
                        <fmt:message key="delete"/>
                    </a>
                </c:if>
            </p>

            <hr>


            <!-- Post Content -->
            ${content}
            <hr>

            <!-- Blog Comments -->

            <!-- Comments Form -->
            <c:if test="${sessionScope.user != null}">

                <div class="well">
                    <h4>
                        <fmt:message key="answer.leave"/>:
                    </h4>
                    <form role="form" action="/controller?command=addanswer" method="POST">
                        <div class="form-group">
                            <textarea class="form-control" rows="3" name="new_answer"></textarea>
                        </div>
                        <input type="hidden" value="${question.id}" name="questionid">
                        <button type="submit" class="btn btn-primary">
                            <fmt:message key="answer"/>
                        </button>
                    </form>
                </div>
            </c:if>

            <hr>

            <!-- Posted Comments -->

            <!-- Comment -->
            <c:forEach var="answer" items="${answers}">
                <div class="media">
                    <div class="media-body">
                        <h4 class="media-heading">
                            <form action="/controller" method="post">
                                <a href="/controller?command=showprofile&userid=${answer.user.id}">
                                    <c:out value="${answer.user.login}"/>
                                </a>
                                <small><c:out value="${answer.creatingTime}"/></small>
                                <c:if test="${sessionScope.user.id == answer.user.id || sessionScope.user.role == 1}">

                                    <a class="btn btn-default btn-xs"
                                       href="/controller?command=deleteanswer&answerid=${answer.id}&questionid=${question.id}">
                                        <fmt:message key="delete"/>
                                    </a>

                                </c:if>
                                <c:if test="${sessionScope.user != null}">
                                    <input id="request_mark${answer.id}" type="hidden" name="mark" value="">
                                    <input type="hidden" value="estimate" name="command">
                                    <input type="hidden" name="answer_author" value="${answer.user.id}">
                                    <input type="hidden" name="answer" value="${answer.id}">
                                    <input type="hidden" name="question" value="${question.id}">

                                    <button type="submit" class="btn btn-default btn-xs"
                                            onclick="document.getElementById('request_mark' + ${answer.id}).value=1">
                                        +1
                                    </button>
                                    <button type="submit" class="btn btn-default btn-xs"
                                            onclick="document.getElementById('request_mark' + ${answer.id}).value=-1">
                                        -1
                                    </button>
                                </c:if>
                            </form>
                        </h4>
                        <c:out value="${answer.answer}"/>
                    </div>
                </div>
                <hr>
            </c:forEach>


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
