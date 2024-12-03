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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "RegistroProductoServlet", urlPatterns = "/RegistroProductoServlet")
public class RegistroProductoServlet extends HttpServlet {

    private final ProductDao productoDAO = new ProductDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String precioStr = request.getParameter("precio");
        String stockStr = request.getParameter("stock");

        try {
            // Validaciones
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre del producto es obligatorio.");
            }

            double precio = Double.parseDouble(precioStr);
            int stock = Integer.parseInt(stockStr);

            if (precio < 0) {
                throw new IllegalArgumentException("El precio no puede ser negativo.");
            }

            if (stock < 0) {
                throw new IllegalArgumentException("El stock no puede ser negativo.");
            }

            // Lógica de descuento
            int descuento = 0;
            if (precio > 500) {
                descuento = 10; // Aplicar descuento automático
            }

            // Guardar en la base de datos
            try {
                Producto nuevoProducto = new Producto();
                nuevoProducto.setNombre(nombre);
                nuevoProducto.setPrecio(precio);
                nuevoProducto.setStock(stock);
                nuevoProducto.setDescuento(descuento);
                productoDAO.registrarProducto(nuevoProducto);
                // Redirigir al listado
                response.sendRedirect("ListadoProductosServlet");
            } catch (SQLException e) {
                throw new ServletException("Error al registrar el producto.", e);
            }

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("registro.jsp");
            dispatcher.forward(request, response);
        }
    }
}