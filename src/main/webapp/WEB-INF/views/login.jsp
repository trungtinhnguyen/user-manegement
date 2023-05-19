<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign in</title>
</head>
<body>
    <div class="main">
        <div class="header">
            <span>Sign in</span>
        </div>
        <div class="content">
            <form action="<c:url value='/j_spring_security_check'/>" method="post">
                <input type="text" name="username" id="username" placeholder="Username">
            <input type="password" name="password" id="password" placeholder="Password">
                <input type="submit" value="Sign in">
            </form>
        </div>
    </div>
</body>
</html>