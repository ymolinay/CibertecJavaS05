<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Editar Producto</h1>

        <!-- Mostrar mensaje de error -->
        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= error %>
            </div>
        <% } %>

        <!-- Formulario -->
        <%
            String id = request.getParameter("id");
            String nombre = (String) request.getAttribute("nombre");
            String precio = (String) request.getAttribute("precio");
            String stock = (String) request.getAttribute("stock");
            String descuento = (String) request.getAttribute("descuento");
        %>
        <form action="GuardarProductoServlet" method="POST">
            <input type="hidden" name="id" value="<%= id %>">
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre del Producto</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="<%= nombre %>" required>
            </div>
            <div class="mb-3">
                <label for="precio" class="form-label">Precio</label>
                <input type="number" step="0.01" class="form-control" id="precio" name="precio" value="<%= precio %>" required>
            </div>
            <div class="mb-3">
                <label for="stock" class="form-label">Stock</label>
                <input type="number" class="form-control" id="stock" name="stock" value="<%= stock %>" required>
            </div>
            <div class="mb-3">
                <label for="descuento" class="form-label">Descuento</label>
                <input type="number" class="form-control" id="descuento" name="descuento" value="<%= descuento %>">
            </div>
            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
        </form>
    </div>
</body>
</html>