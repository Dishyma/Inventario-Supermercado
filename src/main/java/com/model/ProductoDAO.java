package com.model;

import com.conexion.Conexion;
import com.interfaces.IntCrudProducto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoDAO implements IntCrudProducto {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;


    @Override
    public Producto obtenerProductoPorId(int id) throws SQLException {
        Producto producto = new Producto();
        try{
            con = cn.getConexion();
            String sql = "SELECT * FROM productos WHERE id=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCantidad(rs.getString("cantidad"));
                producto.setProveedor(rs.getString("proveedor"));
                producto.setFechaIngreso(rs.getString("fechaIngreso"));
            }
        }catch (SQLException ex){
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto;
    }

    @Override
    public List<Producto> obtenerProductos() throws SQLException {
        List<Producto> productosList = new ArrayList<>();
        try{
            con = cn.getConexion();
            String sql = "SELECT * FROM productos";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCantidad(rs.getString("cantidad"));
                producto.setProveedor(rs.getString("proveedor"));
                producto.setFechaIngreso(String.valueOf(rs.getDate("fechaIngreso")));
                productosList.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productosList;
    }

    @Override
    public boolean agregarProducto(Producto producto) {
        try {
            con = cn.getConexion();
            String sql = "INSERT INTO  productos "
                    + "(nombre,cantidad,proveedor) VALUES (?,?,?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCantidad());
            ps.setString(3, producto.getProveedor());
            ps.execute();
            return Boolean.TRUE;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return Boolean.FALSE;
        }
    }

    @Override
    public boolean actuaProducto(Producto producto) {
        try {
            con = cn.getConexion();
            String sql = "UPDATE productos SET nombre=?, cantidad=?, proveedor=? WHERE id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCantidad());
            ps.setString(3, producto.getProveedor());
            ps.setInt(4, producto.getId());
            ps.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return Boolean.FALSE;
        }
    }

    @Override
    public boolean deleteProducto(int id) {
        try{
            con = cn.getConexion();
            String sql = "DELETE FROM productos WHERE id=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return Boolean.TRUE;
        }catch (SQLException ex){
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return Boolean.FALSE;
        }
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
