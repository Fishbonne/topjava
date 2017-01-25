<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h2>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form:form method="post" commandName="meal" action="${pageContext.request.contextPath}/meals/save">
        <form:hidden path="id" />

        <dl>
            <dt>DateTime:</dt>
            <dd><form:input path="dateTime"/></dd>
            <%--<dd><form:input path="dateTime" type="datetime-local"/></dd>--%>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><form:input path="description"/></dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><form:input path="calories"/></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form:form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
