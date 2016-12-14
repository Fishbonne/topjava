<%--
  Created by IntelliJ IDEA.
  User: Semen
  Date: 09.12.2016
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meals</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .exceed td {
            color: red
        }

        .notexceed td {
            color: black
        }
    </style>
</head>
<body>
<table class="tg">
    <tr>
        <th>ID</th>
        <th>Калории</th>
        <th>Дата</th>
        <th>Описание</th>
        <th>Превышение</th>
    </tr>
    <c:forEach items="${list}" var="meal">
        <c:choose>
            <c:when test="${true==meal.exceed}">
                <tr class="exceed">
            </c:when>
            <c:otherwise>
                <tr class="notexceed">
            </c:otherwise>
        </c:choose>
        <td>${meal.id}</td>
        <td>${meal.calories}</td>
        <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td>
        <td>${meal.description}</td>
        <c:choose>
            <c:when test="${true==meal.exceed}">
                <td>Да</td>
            </c:when>
            <c:otherwise>
                <td>Нет</td>
            </c:otherwise>
        </c:choose>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<form name="meals" action="<%request.getContextPath();%>meals" method="post">
    <label for="id">ID</label><br>
    <input type="text" name="id" id="id"><br>
    <label for="calories">Calories</label><br>
    <input type="text" name="calories" id="calories"><br>
    <label for="date">Date</label><br>
    <input type="datetime-local" name="date" id="date"><br>
    <label for="description">Description</label><br>
    <input type="text" name="description" id="description"><br>
    <input type="radio" name="operation" id="create" value="create" checked>
    <label for="create">Create</label><br>
    <input type="radio" name="operation" id="update" value="update">
    <label for="update">Update</label><br>
    <input type="radio" name="operation" id="delete" value="delete">
    <label for="delete">Delete</label><br><br>
    <input type="submit" value="Submit">
</form>

</body>
</html>
