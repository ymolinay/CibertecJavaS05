package com.cibertec;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListadoProductosServlet", urlPatterns = "/ListadoProductosServlet")
public class ListadoProductosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String[]> productos = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id, nombre, precio, stock, descuento FROM Productos")) {

            while (resultSet.next()) {
                String[] producto = new String[5];
                producto[0] = String.valueOf(resultSet.getInt("id"));
                producto[1] = resultSet.getString("nombre");
                producto[2] = String.valueOf(resultSet.getDouble("precio"));
                producto[3] = String.valueOf(resultSet.getInt("stock"));
                producto[4] = String.valueOf(resultSet.getInt("descuento"));
                productos.add(producto);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error al listar los productos.", e);
        }

        request.setAttribute("productos", productos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}