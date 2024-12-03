package com.cibertec.dao.impl;

import com.cibertec.DBConnection;
import com.cibertec.dao.ProductDao;
import com.cibertec.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public List<Producto> listarProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM Productos";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                productos.add(new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getInt("descuento")
                ));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }

    @Override
    public Producto obtenerProducto(int id) throws SQLException {
        Producto producto = null;
        String query = "SELECT * FROM Productos WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getInt("stock"),
                            rs.getInt("descuento")
                    );
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    @Override
    public void registrarProducto(Producto producto) throws SQLException {
        String query = "INSERT INTO Productos (nombre, precio, stock, descuento) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setDouble(3, producto.getStock());
            ps.setDouble(4, producto.getDescuento());
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editarProducto(Producto producto) throws SQLException {
        String query = "UPDATE Productos SET nombre = ?, precio = ?, stock = ?, descuento = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setDouble(3, producto.getStock());
            ps.setDouble(4, producto.getDescuento());
            ps.setDouble(5, producto.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminarProducto(int id) throws SQLException {
        String query = "DELETE FROM Productos WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
