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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "CargarProductoServlet", urlPatterns = "/CargarProductoServlet")
public class CargarProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT nombre, precio, stock, descuento FROM Productos WHERE id = ?")) {

            statement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                request.setAttribute("id", id);
                request.setAttribute("nombre", resultSet.getString("nombre"));
                request.setAttribute("precio", String.valueOf(resultSet.getDouble("precio")));
                request.setAttribute("stock", String.valueOf(resultSet.getInt("stock")));
                request.setAttribute("descuento", String.valueOf(resultSet.getInt("descuento")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error al cargar el producto.", e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("editar.jsp");
        dispatcher.forward(request, response);
    }
}