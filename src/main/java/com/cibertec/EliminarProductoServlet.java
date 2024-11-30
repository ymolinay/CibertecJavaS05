package com.cibertec;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "EliminarProductoServlet", urlPatterns = "/EliminarProductoServlet")
public class EliminarProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Productos WHERE id = ?")) {

            // Establecer el ID para la eliminación
            statement.setInt(1, Integer.parseInt(id));
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error al eliminar el producto.", e);
        }

        // Redirigir al listado actualizado
        response.sendRedirect("ListadoProductosServlet");
    }
}