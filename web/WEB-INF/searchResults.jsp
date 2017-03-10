<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Results</title>
</head>
<body>
<a href="/main">
    Back to MAIN table.
</a>
<br>
<form method="get" action="/searchid">
    New search by ID or CODE:
    <input type="text" name="searchid">
    <input type="submit" value="Search">
</form>
<table border="1" style="font-size: 14px">
    <tr>
        <c:forEach items="${requestScope.data}" var = "temp_data">
    <tr>
        <td>
                ${temp_data.productCode}
        </td>

        <td>
                ${temp_data.id}
        </td>

        <td>
                ${temp_data.partCode}
        </td>

        <td>
                ${temp_data.name}
        </td>

        <td>
                ${temp_data.qty}
        </td>

        <td>
                ${temp_data.unit}
        </td>

        <td>
                ${temp_data.catalog}
        </td>

        <td>
                ${temp_data.ecName}
        </td>

        <td>
                ${temp_data.codeInCatalog}
        </td>

        <td>
                ${temp_data.limitation}
        </td>
    </tr>
    </c:forEach>
    </tr>
</table>
</body>
</html>
