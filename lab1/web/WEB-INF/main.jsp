<%@page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
        <link rel="stylesheet" href="src/style/main.css">
        <title>Main</title>
    </head>
    <body>
        <%
            int size = (int) request.getAttribute("size");
            String[] name = (String[]) request.getAttribute("name");
            int[] count = (int[]) request.getAttribute("count");
            int[] id = (int[]) request.getAttribute("id");
        %>
        <div class="main">
        <form method="post" action="delete">
            <table class="table">
            <thead>
                <tr>
                    <th>Название</th>
                    <th>Количество</th>
                    <th><input type="submit" value="delete"></th>
                </tr>
            </thead>
                <% for(int i = 0; i < size; i++){ %>
                <tr>
                <td><%= name[i]%></td>
                <td><%= count[i]%></td>
                <td>
                    <input type="checkbox" name="delete" <%="value="+id[i]%>>
                </td>
                </tr>
                <% } %>
            </table>
            </form>
        <br>
        <div class="add">
            <form method="post" action="add">
                <input type="text" name="name_add">
                <input type="text" name="count_add">
                <input type="submit" value="add">
            </form>
        </div>
        </div>
    </body>
</html>