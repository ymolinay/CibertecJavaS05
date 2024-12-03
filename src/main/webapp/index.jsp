<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cibertec.model.Producto" %>
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

            <% List<Producto> productos = (List<Producto>) request.getAttribute("productos"); %>

            <% if (productos != null && !productos.isEmpty()) { %>
                <% for (Producto producto : productos) { %>
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-body">
                                <h5 class="card-title"><%= producto.getNombre() %></h5>
                                <p class="card-text">
                                    Precio: <strong>S/ <%= producto.getPrecio() %></strong><br>
                                    Stock: <strong><%= producto.getStock() %></strong><br>
                                    Descuento: <strong><%= producto.getDescuento() %>%</strong>
                                </p>
                                <a href="CargarProductoServlet?id=<%= producto.getId() %>" class="btn btn-warning">Editar</a>
                                <a href="EliminarProductoServlet?id=<%= producto.getId() %>" class="btn btn-danger"
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