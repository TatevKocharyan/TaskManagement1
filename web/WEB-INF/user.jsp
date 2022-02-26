<%@ page import="model.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: DVO
  Date: 15.02.2022
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome User Home</title>
</head>
<body>
<% List<Task> tasks = (List<Task>) request.getAttribute("tasks");%>


    <div>
        All Tasks:<br>
        <table border="1">
            <tr>
                <td>name</td>
                <td>description</td>
                <td>deadline</td>
                <td>status</td>
                <td>user</td>
            </tr>

            <%
                for (Task task : tasks) {%>
            <tr>
                <td><%=task.getName()%></td>
                <td><%=task.getDescription()%></td>
                <td><%=task.getDeadline()%></td>
                <td><%=task.getTaskStatusType()%></td>
                <td><%=task.getUser().getName()+" "+task.getUser().getSurname()%></td>

            </tr>
            <%
                }
            %>



        </table>
    </div>
































</body>
</html>
