<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listado de Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Listado de Productos</h1>
        <a href="registro.jsp" class="btn btn-success mb-3">Registrar Nuevo Producto</a>

        <div class="row">
            <% List<String[]> productos = (List<String[]>) request.getAttribute("productos"); %>
            <% if (productos != null && !productos.isEmpty()) { %>
                <% for (String[] producto : productos) { %>
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-body">
                                <h5 class="card-title"><%= producto[1] %></h5>
                                <p class="card-text">
                                    Precio: <strong>S/ <%= producto[2] %></strong><br>
                                    Stock: <strong><%= producto[3] %></strong><br>
                                    Descuento: <strong><%= producto[4] %>%</strong>
                                </p>
                                <a href="CargarProductoServlet?id=<%= producto[0] %>" class="btn btn-warning">Editar</a>
                                <a href="EliminarProductoServlet?id=<%= producto[0] %>" class="btn btn-danger"
                                   onclick="return confirm('¿Estás seguro de que deseas eliminar este producto?');">Eliminar</a>
                            </div>
                        </div>
                    </div>
                <% } %>
            <% } else { %>
                <div class="alert alert-warning" role="alert">
                    No hay productos registrados.
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>