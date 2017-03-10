
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <style>
        body {
            margin: 0; /* Убираем отступы */
        }
        .parent {
            margin: 20%; /* Отступы вокруг элемента */
            background: #D3D3D3; /* Цвет фона */
            padding: 10px; /* Поля вокруг текста */
        }
        .child {
            border: 3px solid #666; /* Параметры рамки */
            padding: 10px; /* Поля вокруг текста */
            margin: 10px; /* Отступы вокруг */
        }
    </style>
</head>
<body>
<div class="parent">
<table align="center">
    <tr>
        <form method="get" action="/searchid">
            Search by ID or CODE:
            <input type="text" name="searchid">
            <input type="submit" value="Search">
        </form>
    </tr>

    <tr>
        <form method="POST" action="/upload" enctype="multipart/form-data" >
            Upload new nomenclature:
            <input type="file" name="file" id="file" /> <br/>
            <input type="submit" value="Upload" name="upload" id="upload" />
        </form>
    </tr>

    <tr>
        <a href="/showall"> Show All Data.
        </a>
    </tr>
    <br>
    <tr>
        <a href="/deletePage"> Delete nomenclature from DB.
        </a>
    </tr>
</table>
</div>
</body>
</html>
