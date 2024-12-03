package com.cibertec;

import com.cibertec.dao.ProductDao;
import com.cibertec.dao.impl.ProductDaoImpl;
import com.cibertec.model.Producto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CargarProductoServlet", urlPatterns = "/CargarProductoServlet")
public class CargarProductoServlet extends HttpServlet {

    private final ProductDao productoDAO = new ProductDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);
            Producto producto = productoDAO.obtenerProducto(id);

            request.setAttribute("producto", producto);
            RequestDispatcher dispatcher = request.getRequestDispatcher("editar.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al cargar un producto", e);
        }
    }
}