<%@page import="java.util.List"%>
<%@page import="com.model.Producto"%>
<%@page import="com.model.ProductoDAO"%>



<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    ProductoDAO daoProducto = new ProductoDAO();
    String mensaje = "", nombre = "", cantidad = "", proveedor = "", accion = "", id = "";
    List<Producto> listProductos = null;
    try {
        listProductos = daoProducto.obtenerProductos();
        mensaje = request.getAttribute("mensaje").toString();
        accion = request.getAttribute("accion").toString();
        if ("editar".contentEquals(accion)) {
            id = request.getAttribute("id").toString();
            nombre = request.getAttribute("nombre").toString();
            cantidad = request.getAttribute("cantidad").toString();
            proveedor = request.getAttribute("proveedor").toString();
        }
    } catch (Exception e) {
        mensaje =  "<div class=\"alert alert-warning\">\n"
                + "    <strong>Algo fallo!</strong> Lo sentimos al parecer un servicio o los datos no estan disponibles.";
        e.getMessage();
    }
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Inventario</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="./others/icon.png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Guardado y actualizacion de Inventario</h2>

    <form action = "ProductoServlet" method= "post" autocomplete="off">
        <input type="hidden" name="txtid" value="<%=id%>">
        <div class="form-group">
            <label for="txtNombre">Nombre</label>
            <input required type="text" class="form-control" name="txtNombre" id="txtNombre">
        </div>
        <div class="form-group">
            <label for="txtCantidad">Cantidad</label>
            <input required type="text" class="form-control" name="txtCantidad" id="txtCantidad">
        </div>
        <div class="form-group">
            <label for="txtProveedor">Proveedor</label>
            <input required type="text" class="form-control" name="txtProveedor" id="txtProveedor">
        </div>
        <%
            if ("editar".contentEquals(accion)) {  %>
        <button type="submit" name="accion" value="actualizar"  class="btn btn-success">
            <span class="glyphicon glyphicon-floppy-disk"></span> Actualizar
        </button>
        <%} else {%>
        <button type="submit" name="accion" value="guardar"  class="btn btn-success">
            <span class="glyphicon glyphicon-floppy-disk"></span> Guardar
        </button>
        <%}
        %>
    </form>

</div>
<div class="container">
    <h2>Lista de Inventario</h2>
    <p>Listado de Inventario </p>
    <input class="form-control" id="myInput" type="text" placeholder="Search..">
    <br>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th>Cantidad</th>
            <th>Proveedor</th>
            <th>Fecha de Ingreso</th>
        </tr>
        </thead>
            <% if (listProductos != null) {

                        for (Producto productoFor : listProductos) {%>
        <tbody id="myTable">
        <tr>
            <td><%=productoFor.getId()%></td>
            <td><%=productoFor.getNombre()%></td>
            <td><%=productoFor.getCantidad()%></td>
            <td><%=productoFor.getProveedor()%></td>
            <td><%=productoFor.getFechaIngreso()%></td>
            <td>
                <div class="btn-toolbar" role="toolbar">
                    <a href="ProductoServlet?accion=preactualizar&id=<%=productoFor.getId()%>&nombre=<%=productoFor.getNombre()%>&cantidad=<%=productoFor.getCantidad()%>&proveedor=<%=productoFor.getProveedor()%>"  class="btn btn-warning btn-xs">
                        <span class="glyphicon glyphicon-pencil"></span> Actualizar
                    </a>
                    <a href="ProductoServlet?accion=eliminar&id=<%=productoFor.getId()%>"  class="btn btn-danger btn-xs">
                        <span class="glyphicon glyphicon-minus-sign"></span> Eliminar
                    </a>
                </div>
            </td>
        </tr>
        </tbody>
        <%
                        }
            }
        %>
    </table>
</div>

<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
            });
        });
    });
</script>

</body>
</html>


</body>
</html>