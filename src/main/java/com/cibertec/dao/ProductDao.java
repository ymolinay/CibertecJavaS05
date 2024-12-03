package com.cibertec.dao;

import com.cibertec.model.Producto;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    List<Producto> listarProductos() throws SQLException;
    Producto obtenerProducto(int id) throws SQLException;
    void registrarProducto(Producto producto) throws SQLException;
    void editarProducto(Producto producto) throws SQLException;
    void eliminarProducto(int id) throws SQLException;

}
