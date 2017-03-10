<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete nomenclature</title>
    <style>
        body {
            margin: 0; /* Убираем отступы */
        }
        .parent {
            margin: 20%; /* Отступы вокруг элемента */
            background: #D3D3D3; /* Цвет фона */
            padding: 10px; /* Поля вокруг текста */
        }
    </style>
</head>
<body>
<div class="parent">
<table valign="center">
    <tr>
        <a href="/main">
            Back to MAIN menu.
        </a>
    </tr>
    <br>
    <tr>
        ${msg}
    </tr>
    <br>
    <tr>
        <form id="deleteForm" action="/deleteNomenclature">
                <select name="nomenclatureSelect">
                    <c:forEach items="${requestScope.products}" var = "products">
                        <option>${products}</option>
                    </c:forEach>
                </select>
            <br>
            <br>
                <input type="submit" value="Delete" />
        </form>
    </tr>
</table>
</div>
</body>
</html>
