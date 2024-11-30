package com.cibertec;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "GuardarProductoServlet", urlPatterns = "/GuardarProductoServlet")
public class GuardarProductoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String precioStr = request.getParameter("precio");
        String stockStr = request.getParameter("stock");
        String descuentoStr = request.getParameter("descuento");

        try {
            // Validaciones
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre del producto es obligatorio.");
            }

            double precio = Double.parseDouble(precioStr);
            int stock = Integer.parseInt(stockStr);
            int descuento = Integer.parseInt(descuentoStr);

            if (precio < 0) {
                throw new IllegalArgumentException("El precio no puede ser negativo.");
            }

            if (stock < 0) {
                throw new IllegalArgumentException("El stock no puede ser negativo.");
            }

            // Actualizar en la base de datos
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "UPDATE Productos SET nombre = ?, precio = ?, stock = ?, descuento = ? WHERE id = ?")) {

                statement.setString(1, nombre);
                statement.setDouble(2, precio);
                statement.setInt(3, stock);
                statement.setInt(4, descuento);
                statement.setInt(5, Integer.parseInt(id));
                statement.executeUpdate();
            }

            // Redirigir al listado
            response.sendRedirect("ListadoProductosServlet");

        } catch (IllegalArgumentException e) {
            // Manejar errores de validación
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("editar.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error al guardar el producto.", e);
        }
    }
}