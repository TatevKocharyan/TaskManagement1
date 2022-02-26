<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Task" %><%--
  Created by IntelliJ IDEA.
  User: DVO
  Date: 15.02.2022
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Manager Home</title>
</head>
<body>
<% List<User> users = (List<User>) request.getAttribute("users");%>
<% List<Task> tasks = (List<Task>) request.getAttribute("tasks");%>


<div style="width: 800px">
    <div style="width: 50%; float: left">
        Add User:<br>
        <form action="/userRegister" method="post">
            <input type="text" name="name" placeholder="name"><br>
            <input type="text" name="surname" placeholder="surname"><br>
            <input type="text" name="email" placeholder="email"><br>
            <input type="password" name="password" placeholder="password"><br>
            <select name="type">
                <option value="USER">USER</option>
                <option value="MANAGER">MANAGER</option>
            </select><br>
            <input type="submit" value="Register">
        </form>
    </div>




    <div style="width: 50%; float: left ">
        Add Task: <br>
        <form action="/addTask" method="post">
            <input type="text" name="name" placeholder="name"><br>
            <textarea name="description" placeholder="description"></textarea><br>
            <input type="date" name="date"><br>
            <select name="status">
                <option value="TODO">TODO</option>
                <option value="IN_PROGRESS">IN_PROGRESS</option>
                <option value="DONE">DONE</option>
            </select><br>
            <select name="user_id">
                <%
                    for (User user : users) {%>
                <option value="<%=user.getId()%>"><%=user.getName()%><%=user.getSurname()%></option>
                <%
                    }
                %>
            </select><br><br>
            <input type="submit" value="Add">
        </form>
    </div>


</div>




<div>
    <div>
        All Users:<br>
<table border="1">
    <tr>
        <td>name</td>
        <td>surname</td>
        <td>email</td>
        <td>status</td>
    </tr>
        <%
        for (User user : users) {%>
    <tr>
        <td><%=user.getName()%></td>
        <td><%=user.getSurname()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getUserType().name()%></td>
    </tr>
        <%
        }
    %>
</table>
    </div>


<br>


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


















</div>

</body>
</html>
