<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title>Header</title>
    <link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="resources/bootstrap/css/font-awesome.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../resources/bootstrap/js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../resources/bootstrap/js/bootstrap.js/bootstrap.min.js"></script>
</head>
<body>
<div class="modal fade" id="basicModal" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">x</button>
                <h4 class="modal-title" id="myModalLabel">
                    <fmt:message key="login.loginform"/>
                </h4>
            </div>
            <form id="loginForm" class="form-signin" action="/controller" method="POST">
                <input type="hidden" value="login" name="command">
                <div class="modal-body">
                    <div class="form-group">
                        <input type="text" required name="input_login" id="inputLogin" class="form-control"
                               placeholder=<fmt:message key="login"/> >
                    </div>
                    <div class="form-group">
                        <input id="inputPassword" required name="input_password" type="password" class="form-control"
                               placeholder=<fmt:message key="login.password"/> >
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="button" data-dismiss="modal">
                        <fmt:message key="close"/>
                    </button>
                    <button class="btn btn-primary" type="submit" value="login" name="submit">
                        <fmt:message key="login.login"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="signUp" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">x</button>
                <h4 class="modal-title">
                    <fmt:message key="signup.form"/>
                </h4>
            </div>
            <form id="signUpForm" class="form-signin" action="/controller?command=signup" method="POST">
                <div class="modal-body">
                    <div class="form-group">
                        <input type="text" name="login" class="form-control" required
                               placeholder=<fmt:message key="login"/> >
                    </div>
                    <div class="form-group">
                        <input name="password" type="password" class="form-control" required
                               placeholder=<fmt:message key="login.password"/> >
                    </div>
                    <div class="form-group">
                        <input name="email" type="email" class="form-control"
                               placeholder=<fmt:message key="email"/> >
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="button" data-dismiss="modal">
                        <fmt:message key="close"/>
                    </button>
                    <button class="btn btn-primary" type="submit" name="submit">
                        <fmt:message key="signup"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<header class="container">
    <div class="row">
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <a class="navbar-brand">LikeIT</a>
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#responsive-menu">
                        <span class="sr-only">Open navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse" id="responsive-menu">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="/controller?command=showallquestions">
                                <fmt:message key="nav.questions"/>
                            </a>
                        </li>
                        <c:if test="${sessionScope.user != null}">
                            <li>
                                <a href="/controller?command=showprofile&userid=${sessionScope.user.id}">
                                    <fmt:message key="nav.myProfile"/>
                                </a>
                            </li>
                        </c:if>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <fmt:message key="nav.language"/><b class="caret"></b>
                            </a>
                                <ul class="dropdown-menu">
                                    <li><a href=/controller?command=changelanguage&lang=EN">English</a></li>
                                    <li><a href="/controller?command=changelanguage&lang=ru_RU">Русский</a></li>
                                </ul>
                        </li>
                    </ul>
                    <c:if test="${sessionScope.user == null}">
                        <form action="" class="navbar-form navbar-right">
                            <a class="btn btn-primary form-control" href="#" data-toggle="modal"
                               data-target="#basicModal">
                                <i class="fa fa-sign-in"></i>
                                <fmt:message key="login.login"/>
                            </a>
                        </form>
                        </form>
                        <form action="" class="navbar-form navbar-right">
                            <a class="btn btn-primary form-control" href="#" data-toggle="modal"
                               data-target="#signUp">
                                <i class="fa fa-sign-in"></i>
                                <fmt:message key="signup"/>
                            </a>
                        </form>
                    </c:if>
                    <c:if test="${sessionScope.user != null}">
                        <form action="/controller?command=logout" method="POST" class="navbar-form navbar-right">
                            <button class="btn btn-primary" type="submit" name="logout">
                                <fmt:message key="logout"/>
                            </button>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</header>
<section>
    <main>
        <!-- Registration form-->

    </main>
    <aside></aside>
</section>

</body>
</html>
