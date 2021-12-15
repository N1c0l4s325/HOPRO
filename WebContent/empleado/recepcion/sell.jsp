<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vender</title>
 <!--    <link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css"> -->
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="recepcionist/style2.css">
    <link rel="stylesheet" href="css/all.css">    
</head>
<body>
<%-- <%@ include file = "index.jsp" %> --%>
<div class="container">

		<div>  		
  		<c:if test="${mensajeOperacion != null}">
  			<label class="text-success mb-3 fs-3 fw-bold"> ${mensajeOperacion} </label>
  		</c:if>
  		<c:if test="${mensajeError != null}">
  			<label class="text-danger mb-3 fs-3 fw-bold"> ${mensajeError} </label>
  		</c:if>
  		</div>   
  <div class="container-fluid">
<div class="row d-flex mt-4">
         <div class="col-12 col-sm-12 col-lg-5 col-xl-5">
            <div class="card shadow-lg m-3">
                <div class="card-body">
                      <div class="form-group mb-3">
                            <label class="fw-bold">Datos del cliente de la reserva</label>
                        </div>
                            <form action="Recepcion" method="get">
                       	    <div class="form-group ">
                       	     <div class="col-sm-6 d-flex me-3 mt-3 mb-3">
                            <select class="form-select text-center" name="habitacion" aria-label="Default select example">
										<option value="" selected>Habitacion</option>
										<c:forEach var="h" items= "${habitacion}">
										<option value="${h.ID}">${h.nivel}${h.numHab}</option>
										</c:forEach>																		
							</select>                           
                            </div>                           
                            <div class="col-sm-6 d-flex me-3">                      
                            <input class="form-control" type="text" value="${c.identification}" name="documento" placeholder="Identificación">
                            <input class="form-control" type="hidden" name="operacion" value="buscarCliente">
                            <input type="hidden" name="accion" value="mostrar-consumos">                              
                            <input class="btn btn-outline-info" type="submit" value="Buscar">
                            </div>                           
                        </div>
                        <div>
                        	 <input class="form-control w-50 mt-3" type="text" value="${c.name} ${c.surName}" readonly="readonly">
                        </div>
                        </form>
            		<c:if test="${p != null }">
                        <div class="form-group mt-3 mb-3">
                            <label class="fw-bold">Datos del producto</label>
                        </div>                        
                        <div class="form-group d-flex">
                            <div class="col-sm-2 d-flex me-3">
                            <input class="form-control" type="text"  name="idProducto" value="${p.ID}" placeholder="Codigo" readonly="readonly">
                          
                            </div>
                            <div class="col-sm-8 me-3">
                                <input class="form-control" type="text" value="${p.descripcion}" readonly="readonly">
                            </div>
                        </div>
                        <form action="Recepcion" method="get">
                        <div class="form-group d-flex mt-3">
                            <div class="col-sm-5 me-5">
                            	<label class="form-label">Precio</label>
                                <input class="form-control" type="text" value="${p.precioVenta}" readonly="readonly" placeholder="$ 0.00">
                            </div>
                     
                            <div class="col-sm-2 me-4">
                            	<label class="form-label">Cantidad</label>
                                <input class="form-control tex" type="number" value="1" name="cantidad">
                            </div>
                             <div class="col-sm-2">
                            	 <label class="form-label">Stock</label>                            
                                <input class="form-control" type="text" value="${p.stock}" readonly="readonly" placeholder="stock">
                            </div>
                        </div>
                       
                        <div class="form-group mt-3">
									<select class="form-select" name="estado" aria-label="Default select example">
										<option selected>Una opcion de pago</option>
										<option value="falta pagar">FALTA PAGAR</option>
										<option value="pagado">PAGADO</option>										
									</select>
						</div>
                        <div class="form-group d-flex mt-3">
                        	<input type="hidden" name="idProducto" value="${p.ID}">
                        	<input type="hidden" name="idReserva" value="${idReserva}">
                        	<input type="hidden" name="habitacion" value="${hab.ID}">
                         	<input type="hidden" name="documento" value="${c.identification}">
                            <input class="btn btn-success" type="submit" name="accion" value="Agregar producto">
                        </div>
                       </form> 
                  </c:if>                  
                   
                </div>
            </div>

         </div>
         <div class="col-12 col-sm-12 col-lg-7 col-xl-7">
                <div class="card shadow-lg m-3">
                    <div class="card-body">
                     <form method="get" action="Recepcion">
                     	<input type="hidden" name="accion" value="mostrar-consumos">
                     	 <input type="hidden"  name="operacion" value="listarProducto">
                     	 <input type="hidden"  name="documento" value="${c.identification}">
                            <div class="d-flex justify-content-center mb-4">
                                <div class="col-sm-6 me-3">
                                    <input class="form-control" type="text" name="producto">
                                                                   
                                 </div>
                                <div class="col-sm-4">
                                	 <button class="btn btn-success" type="submit">Buscar producto</button>     
                                </div>
                            </div>
                        </form>
                        <div class="table-responsive overflow-auto">
                            <table class="table table-stripped text-center">
                                <thead>
                                    <tr>
                                        <th>Codigo Pdto.</th>
                                        <th>Tipo de alimento</th>
                                        <th>Marca</th>
                                        <th>Descripción</th>                        
                                        <th>Stock</th>         
                                       </tr>
                                </thead>
                                <c:forEach var="pro" items="${productos}">
                                    <tr>
                                        <td> ${pro.ID} </td>
                                        <td> ${pro.tipoAlimento}</td>
                                        <td> ${pro.marca} </td>
                                        <td>${pro.descripcion}</td>
                                        <c:choose>
                                        <c:when test="${pro.stock == 0}">
                                           <td class="text-danger"><c:out value="No hay stock"></c:out></td>
                                        </c:when>
                                         <c:when test="${pro.stock != 0}">
                                           <td>${pro.stock}</td>
                                        </c:when>
                                         </c:choose>
                                        <td>
                                        	<a class="btn btn-warning" href="Recepcion?accion=mostrar-consumos&operacion=seleccionarProducto&idProducto=${pro.ID}&documento=${c.identification}&habitacion=${hab.ID}">Seleccionar</a>
                                        </td>
                                    </tr>
                                 </c:forEach>   
                            </table>
                        </div>
                    </div>
                </div>


         </div>
    </div>

<div class="row mt-2">
    <div class="col-12 col-lg-6 col-xl-12">
        <div class="card mt-3 shadow-lg">
        <%-- <div class="card-header">
        <c:if test="${mensajeConsumo != null}">
        <label class="fs-4 text-danger">${mensajeError}</label>	
        </c:if>
        <div class="d-flex justify-content-center">
        <form action="Recepcion" method="get">       
        <input type="hidden" name="accion" value="mostrar-consumos">
        <div class="form-group col-lg-12 d-flex">
       		<div class="col-sm-4 me-3">
       		 <select class="form-select w-100" name="habitacion" aria-label="Default select example">
 					 <option selected>Habitacion</option>
  					<c:forEach var="hab" items= "${habitacion}">
  					<option value="${hab.ID}">${hab.nivel}${hab.numHab}</option>
  					</c:forEach>
			</select>
       		</div>	
        	<div class="col-sm-6 d-flex">
        	 <input class="form-control mb-3 me-3" type="text" name="documento">        	
			<div>
				 <input type="hidden" name="operacion"  value="BuscarConsumos">	
				 <input class="btn btn-success" type="submit"  value="Buscar">
			</div>	
        	</div>
        </div>            
        </form>
        </div>
        </div> --%>
            <div class="card-body m-2">
                               
               <c:if test="${consumo != null}">
                    <div class="row">
                         <div class="col-12 col-xl-12">
                            <div class="table-responsive">
                                <table class="table table-stripped text-center">
                                    <thead>
                                        <tr>
                                        <th>N° reserva</th>
                                        <th>Estado de reserva</th>
                                        <th>Fecha y hora de pedido</th>
                                        <th>Descripcion</th>
                                        <th>Precio de venta</th>
                                        <th>Cantidad</th>
                                        <th>Precio final</th>
                                        <th>Estado</th>       
                                        </tr>
                                    </thead>
                                     <c:forEach var="con" items="${consumo}">
                                        <tr>
                                        	<td> ${con.reserva.id} </td>
                                        	<td> ${con.reserva.estado} </td>
                                            <td> ${con.fechaHoraPedido} </td>
                                            <td> ${con.producto.descripcion} </td>
                                            <td> ${con.producto.precioVenta} </td>
                                            <td> ${con.cantidad} </td>
                                            <td> ${con.saldo} </td>
                                            <c:choose>
                                            <c:when test="${con.estado == 'pagado'}">
                                            <td class="text-uppercase text-success"> ${con.estado} </td>
                                            </c:when>
                                            <c:when test="${con.estado == 'falta pagar'}">
                                            <td class="text-uppercase text-danger"> ${con.estado} </td>
                                            </c:when>
                                            </c:choose>
                                            <td> 
                                                <a href="Recepcion?accion=eliminar_consumo&ID_consumo=${con.ID}&documento=${c.identification}" class="text-decoration-none"><i class="fas fa-trash-alt"></i><span class="ms-2">Eliminar</span></a> 
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
            </div>
        </div>
            	 <div class="col-12 col-xl-12 border bg-primary">
                    <div class="float-end">
                        <label class="fs-5 me-5 fw-bold">Total</label>
                        <label class="fs-5 me-5 fw-bold">$ ${total} </label>
                    </div>
                 </div>
            
         </div>
        </c:if>
         
        </div>
    </div>
</div>
</div>
</div>

</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
