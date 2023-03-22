package com.interfaces;

import com.model.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IntCrudProducto {
    public Producto obtenerProductoPorId(int id) throws SQLException;
    public List<Producto> obtenerProductos() throws SQLException;
    public boolean agregarProducto(Producto producto);
    public boolean actuaProducto(Producto producto);
    public boolean deleteProducto(int id);

    void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    abstract String getServletInfo()// </editor-fold>
    ;
}
