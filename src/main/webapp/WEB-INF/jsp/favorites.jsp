<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>My Favorites</title>

    <link href="${contextPath}/resources/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="${contextPath}/resources/static/css/search.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container" align="right">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        
        <h4>Hi ${pageContext.request.userPrincipal.name}! | <a href="${contextPath}/home">Home</a> | <a href="${contextPath}/favorites">My Giphys</a> | <a href="${contextPath}/category">Add Categories</a> | <a href="#" onclick="document.forms['logoutForm'].submit()">Logout</a></h4>	
    </c:if>

</div>
<hr>
<!-- /container -->
<div id="center" align="center">
<div id="categories"></div>
</div>
<div id="selectedcategory"></div>
<div id="favorites"></div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/static/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/static/js/favorites.js"></script>
</body>
</html>
