<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="org.example.util.SecurityUtils"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Insert title here</title>
</head>
<body>
    <div class="main">
        <div class="header">
            <span>User Manager</span>
        </div>
        <div class="body-div">
            <div class="profile">
                <table>
                    <tr>
                        <th>Username</th>
                        <td><%=SecurityUtils.getPrincipal().getUsername()%></td>
                    </tr>
                    <tr>
                        <th>Full name</th>
                        <td><%=SecurityUtils.getPrincipal().getFullName()%></td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td></td>
                    </tr>
                </table>
            </div>
            <div class="list">
                <table class="dataTable">
                    <tr>
                        <th>No.</th>
                        <th>Username</th>
                        <th>Full name</th>
                        <th>Email</th>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
