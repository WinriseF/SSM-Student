<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>User Details</title>
    <style>body { font-family: sans-serif; }</style>
</head>
<body>
<h1>用户信息</h1>
<c:if test="${not empty user}">
    <p><strong>ID:</strong> ${user.id}</p>
    <p><strong>姓名:</strong> ${user.name}</p>
    <p><strong>年龄:</strong> ${user.age}</p>
</c:if>
<c:if test="${empty user}">
    <p style="color: red;">对不起，没有找到该用户的信息。</p>
</c:if>
</body>
</html>