<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reclamos</title>
    <!-- <link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css"> -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style2.css">
    <link rel="stylesheet" href="css/all.css">
       
        
</head>
<body>
<%-- <%@ include file = "index.jsp" %> --%>
<div class="container">
 	
 	<div class="col-sm-3">
 	<label class="form-label">ID empleado</label>
    <input class="form-control w-25 d-inline " type="text" value="00${idEmpleado}" readonly="readonly">
 	</div>
 	    <div>
  		<c:if test="${mje != null}">
  			<label class="text-danger mb-3 fs-3 fw-bold"> ${mje}</label>
  		</c:if>
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
  	
 <form method="get" action="Recepcion">
 <input type="hidden" name="accion" value="mostrar-reclamos">
 <input type="hidden" value="${idEmpleado}" name="idEmpleado">  	
 <c:if test="${detalleReclamo == null}">
 <div class="row border mt-3 bg-info">
     
            <div class="col-12 col-lg-4 col-xl-4 col-xxl-6 m-3">
                <label class="form-label fw-bold"> Identificación </label>
                <div class="d-flex">
                    <input class="form-control" type="text" name="cliente" value="${cl.identification}">
                    
                    <button class="btn btn-success ms-3" type="submit" name="operacion" value="buscarCliente">Buscar</button>
                </div>
            </div>
  
 	
            <div class="col-12 col-lg-12 col-xl-6 col-xxl-12 d-flex justify-content-center m-3">
                <div class="row">
                    <div class="col-12 col-lg-12 col-xl-12 col-xxl-12 d-flex ">
                        <div class="form-group me-5">
                            <label class="form-label fw-bold">Cliente</label>
                            <input class="form-control" type="text" value="${cl.name} ${cl.surName}" readonly="readonly">
                        </div>                      
                    </div>
                </div>
            </div>
            </div>
  
</c:if>
</form>
<form action="">
	<div class="row bg-primary mt-3">
		<div class="col-12 col-lg-12 col-xl-12 d-flex justify-content-center">
			  <c:if test="${reserva != null}">
                        <div class="form-group">
                        	<label class="form-label fw-bold">Habitacion</label>
                           	<select class="form-select text-center" style="width: 200px" name="idReserva" aria-label="Default select example">  							
  							<option selected></option>
  								<c:forEach var="r" items="${reserva}">
  								<option value="${r.id}">${r.habitacion.nivel} ${r.habitacion.numHab}</option>  								
  								</c:forEach>
							</select>
                        </div>
                        </c:if>
		</div>
    	<div class="col-12 col-xl-12 m-3 d-flex justify-content-center">
        <div class="input-group w-75">
            <span class="input-group-text">Detalle reclamo de huesped:</span>
            <textarea name="detalle"  class="form-control textarea-area" aria-label="With textarea" >${detalleReclamo.descripcion}</textarea>
          </div>
    	</div>
	</div>

 	<div class="row mt-3">
        <div class="col-12 col-lg-4 col-xl-12 col-xxl-4 d-flex ">
        <input type="hidden" value="${idEmpleado}" name="idEmpleado">
        <input type="hidden" value="${cl.identification}" name="documento">
        <input type="hidden" value="${detalleReclamo.ID}" name="idReclamo">
        <input type="submit" class="btn btn-success fw-1 me-5" name="accion" value ="Guardar reclamo">
   
        	<c:if test="${detalleReclamo.ID != null}"> 		
        	<input type="submit" class="btn btn-info fw-1" name="accion"  value ="Actualizar reclamo">       
        	<a href="Recepcion?accion=mostrar-reservas&idEmpleado=${idEmpleado}&operacion=inicio" class="btn btn-danger ms-5">Cancelar</a>
        </c:if>
        </div>
     </div>
</form>


 <c:if test="${reserva !=null}">
    <div id="contenido-reclamos" class="row mt-3 mb-5 h-25 border ">
        <div class="col col-lg-12 col-xl-12 col-xxl-12 bg-light">
        <h1 class="fs-3 text-uppercase">Reclamos realizados</h1>
        </div>
   </div>
   
  
      
    <!--Tabla de reclamos por la habitacion-->
    <div id="contenido-reclamos" class="table-responsive ">
        <table class="table border">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Descripcion</th>
                    <th> Hora y fecha de ingreso</th>
                    <th>Estado</th>
                    <th>Estado de la reserva</th>
                </tr>
            </thead>
            
            <c:forEach var="rmo" items="${reclamo}">
           
            <tr>
                <td> ${rmo.ID} </td>
                <td> ${rmo.descripcion} </td>
                <td> ${rmo.fechaIngreso} </td>
                <td >${rmo.estado}</td>
                <td >${rmo.reserva.estado}</td>
                <td>
                    <a href="Recepcion?accion=eliminar-reclamos&idReclamo=${rmo.ID}&idEmpleado=${idEmpleado}&documento=${cl.identification}" class="text-decoration-none"><i class="fas fa-trash-alt"></i><span class="ms-2">Eliminar</span></a> 
                    <a href="Recepcion?accion=editar-reclamos&IdReclamo=${rmo.ID}&idEmpleado=${idEmpleado}" class="text-decoration-none ms-1"><i class="far fa-edit"></i><span class="ms-1 me-5">Editar</span></a> 
                </td>
            </tr>
            </c:forEach>
        </table>
    </div>
     <div class="row bg-primary">
     		<div class="col-12 col-lg-12 col-xl-12">
     			<div class="float-end">
     			<label class="fw-bold fs-5 me-3">Total de registros: ${totalRegistros}</label>
     			</div>
     		</div>
     
     </div>     
      <div class="row bg-dark mb-3 d-flex">
          <div class="col-12 col-lg-12 col-lg-12 col-xxl-12 m-2">
                <div class="row">
                     
                     <div class="col-12 col-lg-2 col-xl-2 col-xxl-2">
                        <a href="Recepcion?accion=mostrar-reclamos&idEmpleado=${idEmpleado}&operacion=inicio" class="btn btn-success" > Despejar  tabla </a>
                    </div>                
                </div>
               
          </div>  
            
      </div>
</c:if>
      

</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<!-- <script src="../../bootstrap/js/popper.min.js"></script>
<script src="../../bootstrap/js/bootstrap.min.js"></script>--->

</body>
</html>


