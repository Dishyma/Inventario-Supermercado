package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Conexion {
    public Connection getConexion() {

        String host = "localhost";
        String port = "3306";
        String db = "supermercadoinventario";
        String url = "jdbc:mysql://".concat(host).concat(":").concat(port).concat("/").concat(db);
        String user = "root";
        String pass = "1234";
        String classname = "com.mysql.cj.jdbc.Driver";

        try {

            Class.forName(classname);
            return DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
