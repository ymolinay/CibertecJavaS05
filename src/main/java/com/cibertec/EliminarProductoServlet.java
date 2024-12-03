package com.cibertec;

import com.cibertec.dao.ProductDao;
import com.cibertec.dao.impl.ProductDaoImpl;
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

    private final ProductDao productoDAO = new ProductDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);

            productoDAO.eliminarProducto(id);
            response.sendRedirect("ListadoProductosServlet");
        } catch (SQLException e) {
            throw new ServletException("Error al eliminar el producto.");
        }
    }
}