package com.supermercadoinventario;

import com.model.Producto;
import com.model.ProductoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "/ProductoServlet", value = "/ProductoServlet")
public class ProductoServlet extends HttpServlet {
   ProductoDAO productoDAO = new ProductoDAO();
   Producto producto = new Producto();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("ISO-8859-1");
        String nombre, cantidad, proveedor, id;
        boolean respuestaBaseDeDatos;

        String accion = request.getParameter("accion");

        switch (accion) {
            case "guardar":
                nombre = request.getParameter("txtNombre");
                cantidad = request.getParameter("txtCantidad");
                proveedor = request.getParameter("txtProveedor");
                

                if (!nombre.isEmpty() && !cantidad.isEmpty() && !proveedor.isEmpty()) {
                    producto.setNombre(nombre);
                    producto.setCantidad(cantidad);
                    producto.setProveedor(proveedor);
                    respuestaBaseDeDatos = productoDAO.agregarProducto(producto);
                    if (respuestaBaseDeDatos) {
                        request.setAttribute("mensaje", "<div class=\"alert alert-success\">\n"
                                + "    <strong>Correcto!</strong> Se ha realizado el guardado del producto con exito.");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {
                        request.setAttribute("mensaje", "<div class=\"alert alert-danger\">\n"
                                + "    <strong>Algo fallo!</strong> No se logro guardar el producto.");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }

                } else {
                    request.setAttribute("mensaje", "<div class=\"alert alert-danger\">\n"
                            + "    <strong>Algo fallo!</strong> Alguno de los campos esta en blanco.");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }

                break;
            case "eliminar":
                id = request.getParameter("id");
                if (!id.isEmpty()) {
                    respuestaBaseDeDatos = productoDAO.deleteProducto(Integer.parseInt(id));
                    if (respuestaBaseDeDatos) {
                        request.setAttribute("mensaje", "<div class=\"alert alert-success\">\n"
                                + "    <strong>Correcto!</strong> Se ha realizado el eliminacion del producto con exito.");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {
                        request.setAttribute("mensaje", "<div class=\"alert alert-danger\">\n"
                                + "    <strong>Algo fallo!</strong> No se logro eliminar el producto.");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }

                } else {
                    request.setAttribute("mensaje", "<div class=\"alert alert-danger\">\n"
                            + "    <strong>Algo fallo!</strong> Alguno de los campos esta en blanco.");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;
            case "preactualizar":
                id = request.getParameter("id");
                nombre = request.getParameter("nombre");
                cantidad = request.getParameter("cantidad");
                proveedor = request.getParameter("proveedor");
                if (!id.isEmpty()) {
                    request.setAttribute("mensaje", "");
                    request.setAttribute("id", id);
                    request.setAttribute("accion", "editar");
                    request.setAttribute("nombre", nombre);
                    request.setAttribute("cantidad", cantidad);
                    request.setAttribute("proveedor", proveedor);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    request.setAttribute("mensaje", "<div class=\"alert alert-danger\">\n"
                            + "    <strong>Algo fallo!</strong> Alguno de los campos esta en blanco.");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;

            case "actualizar":
                id = request.getParameter("txtid");
                nombre = request.getParameter("txtNombre");
                cantidad = request.getParameter("txtCantidad");
                proveedor = request.getParameter("txtProveedor");
                if (!nombre.isEmpty() && !cantidad.isEmpty() && !proveedor.isEmpty()) {
                    producto.setId(Integer.parseInt(id));
                    producto.setNombre(nombre);
                    producto.setCantidad(cantidad);
                    producto.setProveedor(proveedor);

                    respuestaBaseDeDatos = productoDAO.actuaProducto(producto);
                    if (respuestaBaseDeDatos) {
                        request.setAttribute("mensaje", "<div class=\"alert alert-success\">\n"
                                + "    <strong>Correcto!</strong> Se ha realizado la actulizacion del produdcto con exito.");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {
                        request.setAttribute("mensaje", "<div class=\"alert alert-danger\">\n"
                                + "    <strong>Algo fallo!</strong> No se logro actualizar el produdcto.");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }

                } else {
                    request.setAttribute("mensaje", "<div class=\"alert alert-danger\">\n"
                            + "    <strong>Algo fallo!</strong> Alguno de los campos esta en blanco.");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
