package com.cibertec;

import com.cibertec.dao.ProductDao;
import com.cibertec.dao.impl.ProductDaoImpl;
import com.cibertec.model.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListadoProductosServlet", urlPatterns = "/ListadoProductosServlet")
public class ListadoProductosServlet extends HttpServlet {
    private final ProductDao productoDAO = new ProductDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Producto> productos = productoDAO.listarProductos();
            request.setAttribute("productos", productos);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al listar los productos", e);
        }
    }
}