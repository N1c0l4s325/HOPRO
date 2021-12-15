<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style>
@media print{
	#cardCliente, #cardConsumos, #btnGuardar{
		display: none;
	}
}
</style>

    
<!DOCTYPE html>
<html>
<head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reservas</title>
     <!--    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">  -->
     <link rel="stylesheet" href="css/style2.css">
     <link rel="stylesheet" href="css/all.css">
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
     
</head>
<body>
<div class="container">
<div>
<c:if test="${mensajeOperacion != null}">
  			<div class="m-3">
  			<label class="m-3 fs-5 fw-bold text-success"> ${mensajeOperacion} </label>
  			</div>
  			
  		</c:if>
  		<c:if test="${mensajeError != null}">
  			<div class="m-3">
  			<label class="m-3 fs-5 fw-bold text-danger"> ${mensajeError} </label>
  			</div>
  			
  		</c:if>

</div>


<c:if test="${mensajeCliente != null}">
<label class="text-danger fs-5">${mensajeCliente}</label>
</c:if>
<div class="row d-flex mt-5 mb-3 d-flex justify-content-center">
<div id="cardCliente" class="col-12 col-lg-4 col-xl-4 col-xxl-5">
<div class="card shadow-lg">
<div class="card-body">
<form action="Recepcion" method="get">
<input type="hidden" value="factura" name="accion">
<label class="form-label"> Cliente</label>
<hr>
<label class="form-label">Identificacion</label>
<div class="form-group ">
<div class="col-sm-6 mb-3">
<select class="form-select" name="habitacion" aria-label="Default select example">
  <option value="" selected>Habitacion</option>
  <c:forEach var="hab" items="${habitacion}">
  <option value="${hab.ID}"> ${hab.nivel}${hab.numHab} </option>
 </c:forEach>
</select>
</div>
<div class="col-12 col-sm-10">
<div class="d-flex mb-3">
<input class="form-control" type="text" value="${cl.identification}" name="documento">
<input type="hidden" name="operacion" value="Cliente">

<input class="btn btn-success ms-3" type="submit" value="Buscar">
</div>
<input class="form-control ms-3" style="width: 200px;" type="text"  value="${cl.name} ${cl.surName}" readonly="readonly">
</div>
</div>
</form>
</div>
</div>
</div>
<div id="cardConsumos" class="col-12 col-lg-4 col-xl-4 col-xxl-5">
    <div class="card shadow-lg">
        <div class="card-body">
            <label class="form-label">Consumos</label>
            <hr>
            <form action="Recepcion?" method="get">
            <div class="">
            <input type="hidden" name="accion" value="factura">
            <input type="hidden" name="operacion" value="consumo">
            <input type="hidden" value="${idReserva}" name="idReserva">
             <input type="hidden" value="${cl.identification}" name="documento">
              <input type="hidden" value="${idHabitacion}" name="habitacion">
              <input type="hidden" value="${importeTotal}" name="importe">
           <input  class="btn btn-outline-warning fw-bold " type="submit" name="operacion" value="Consumos">
            </div>
            </form>
        </div>
        </div>
</div>

</div>
<div class="row d-flex mb-5 ocultar">
<form action="Recepcion" method="get">

<div id="btnGuardar" class="col-12 col-lg-4 col-xl-10">
<input type="hidden" name="idReserva" value="${idReserva}">
<input type="hidden" name="idHabitacion" value="${idHabitacion}">
<input type="hidden" name="numeroComprobate" value="${NumeroComprobante}">
<input type="hidden" name="fechaEmision" value=" ${fechaEmision}">
<div class="col-sm-4 mb-3 mt-3">
<select class="form-select" name="formaDePago" aria-label="Default select example">
  <option value="" selected> Formas de pago</option>
  <option value="tarjeta credito">Tarjeta credito</option>
  <option value="tarjeta debito">Debito</option>
  <option value="efectivo">Efectivo</option>
</select>
</div>
<div>
<input type="hidden" name="accion" value="factura">
<input class="btn btn-success" type="submit" name="operacion" value="Guardar factura">
<c:if test="${cl != null }">
<button class="btn btn-success" onclick="print()" >Imprimir factura</button>
<!-- <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#factruas"> ver facturas </button> -->
</c:if>
</div>
</div>
</form>
</div>
<div class="row">
<div class="col-12 col-lg-6 col-xl-12 col-xxl-12 ">
<div class="card">
<div class="card-body">
<div class="row">
<div class="col-12 col-lg-4 col-xl-9 d-flex ">
<div class="ms-3 mt-2 col-sm-1">
<img src="img/unnamed.png" style="width: 80px ; height: 80px ;">
</div>
<div class="mt-3 ms-5 col-sm-4">
<label class="fw-bold" >Direccion: Av. Sarmiento 123</label><br>
<label class="fw-bold">Telefono: 0260-4423658</label>
<label class="fw-bold">Email: hotelSanFelipe@gmail.com</label>
</div>
</div>
<div class="col-12 col-lg-4 col-xl-3 ">
<div class=" col-sm-4" style="width: 300px;">
<label class="fs-2 text-uppercase  d-block">factura</label>
</div>
<div class="col-sm-8">
<label class="" style="width: 300px;" >Fecha emision: ${fechaEmision} </label>
<label class="" style="width: 300px;" >N° factura: 001-${NumeroComprobante}</label>
</div>

</div>
</div>
<div class="row">
<div class="col-12 col-lg-12 col-xl-12">
<div class="d-inline">
<label>Señor/a</label>
<input class="form-control" value="${cl.surName} ${cl.name} " readonly="readonly">
</div>
<div class="">
    <label>Direccion</label>
    <input class="form-control"  value="${cl.address}" readonly="readonly">
    </div>
<div class="">
        <label>Documento</label>
        <input class="form-control w-25"  value="${cl.identification} " readonly="readonly">
        </div>
</div>
</div>
<div class="row mt-2">
<div class="col-12 col-lg-12 col-xl-12 ">
<table class="table border">
<thead class="bg-light">
<tr>
<th class="border" style="width: 10px;">Cantidad</th>
<th class="border text-center" style="width: 550px;">Detalle</th>
<th class="border text-center" style="width: 200px;">Precio unitario</th> 
<th class="border" style="width: 20px;">Noches</th>
<th class="border text-center" style="width: 80px;">Importe</th>
</tr>
</thead>
<c:forEach var="r" items="${reserva}">
<tr class="text-center">
<td> ${cantidad} </td>
<td> Reserva.Habitacion ${r.habitacion.tipoHabitacion} </td>
<td> ${r.habitacion.precio} </td>
<td> ${r.cantidadNoches} </td>
<td> <fmt:formatNumber type="number" pattern="####.##" value="${r.importe}"></fmt:formatNumber></td>
</tr>
</c:forEach>
<c:if test="${consumos != null}">
<c:forEach var="con" items="${consumos}">
<tr class="text-center">
<td> ${con.cantidad} </td>
<td> ${con.producto.descripcion} </td>
<td> ${con.producto.precioVenta} </td>
<td> - </td>
<td> <fmt:formatNumber type="number" pattern="####.##" value="${con.saldo}"></fmt:formatNumber></td>
</tr>
</c:forEach>
</c:if>
</table>
</div>
</div>
<div class="row bg-light">
<div class="col-12 col-lg-12 col-xl-12">
<div class="float-end me-3">
<label class=" fs-4 fw-bold" >Total</label>
<label class="fs-4 fw-bold ms-3">$ <fmt:formatNumber type="number" pattern="####.##" value="${importeTotal}"></fmt:formatNumber></label>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
</body>
</html>