<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="request" type="edu.school21.cinema.models.User"/>
<jsp:useBean id="authInfos" scope="request" type="java.util.List<edu.school21.cinema.models.AuthInfo>"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>profile</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        table {
            width: 60%;
        }
        th {
            width: 33%;
        }
    </style>
</head>
<body>
    <p>It's me:</p>
    <p>${user.name}</p>
    <p>${user.family}</p>
    <p>${user.email}</p>
    <table>
        <tr>
            <th>Date</th>
            <th>Time</th>
            <th>IP</th>
        </tr>
        <c:forEach items="${authInfos}" var="element">
            <tr>
                <td>${element.dateString}</td>
                <td>${element.timeString}</td>
                <td>${element.ip}</td>
            </tr>
        </c:forEach>
    </table>

    <form method="post" action="images" enctype="multipart/form-data">
        Choose a file: <input type="file" name="uploadFile"/><input type="submit" value="Upload"/>
    </form>
</body>
</html>
