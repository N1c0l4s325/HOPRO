<%@ page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reservas</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"> 
     <link rel="stylesheet" href="css/style2.css">
     <link rel="stylesheet" href="css/all.css">
   <!--   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> -->
     
</head>
<body>
 <%-- <%@ include file = "index.jsp" %> --%>
<div class="container" >  
   
       
 	<div class="col-sm-3">
 	<label class="form-label">ID empleado</label>
    <input class="form-control w-25 d-inline " type="text" value="00${idEmpleado}" readonly="readonly">
 	</div>
        
        <div>
  		<c:if test="${mje != null}">
  			<label class="text-danger mb-3 fs-3 fw-bold"> ${mje}</label>
  		</c:if>
  		<c:if test="${mensajeOperacion != null}">
  			<label class="text-success mb-3 fs-3 fw-bold"> ${mensajeOperacion} </label>
  		</c:if>
  		<c:if test="${mensajeError != null}">
  			<label class="text-danger mb-3 fs-3 fw-bold"> ${mensajeError} </label>
  		</c:if>
  	</div>
  
  	 <form action="Recepcion" method="get">
  	 <c:if test="${rv == null}">
        <div class="bg-info fw-bold mb-3">           
        <div class="row m-3 d-flex justify-content-center">        
            <div class="col-12 col-lg-4 col-xl-6 col-xxl-6 mt-3 mb-3">
                <div>
                    <label class="form-label">Identificacion</label>
                    <div class="d-flex col-sm-6 justify-content-center" >
                    <input class="form-control" type="text" name="cliente" value="${c.identification}">
                      <input type="hidden" name="idEmpleado" value="${idEmpleado}">
                    <input type="hidden" name="accion" value="mostrar-reservas">
                    <button class="btn btn-success w-50 ms-3" type="submit" name="operacion" value="buscarCliente">Buscar</button> 
                    </div>                                          
                </div>
            </div>
             <div class="col-12 col-lg-4 col-xl-6 col-xxl-4 mt-3 d-flex">
                <div class="me-3">
                    <label class="form-label">Apellido</label>
                    <input class="form-control text-center" type="text" name="apellido" value="${c.surName}" readonly="readonly" >                       
                </div>           
                <div>
                    <label class="form-label text-center">Nombre</label>
                    <input class="form-control text-center" type="text" name="nombre" value="${c.name}" readonly="readonly">
                       
                </div>
            </div>
        </div>       
      	</div>
      	</c:if>
    </form> 
    <form action="Recepcion" method="get">
      <div class="fw-bold bg-primary">       
        	<div class="row m-3">
        		 <c:if test="${rv == null }">	
                 <div class="col-12 col-lg-4 col-xl-4 col-xxl-4 mb-3 mt-3">
                         
                            <label class="mb-2">Seleccione habitacion</label>
                            <select class="form-select mb-2 w-75 text-center" name="habitacion" aria-label="Default select example">
                               <option selected value="${rv.id_habitacion}">${rv.habitacion.nivel} ${rv.habitacion.numHab}</option>
                               <c:forEach var="hab" items="${habitaciones}">
                               <option value="${hab.ID}"> ${hab.nivel} ${hab.numHab}</option>
                               </c:forEach>                           
                           </select>                                                
                    </div>
                   </c:if>
					<div class="col-12 col-lg-4 col-xl-4 col-xxl-4 d-flex mb-3 mt-3">
						<div class="me-3">
							<label class="form-label ">Fecha Reserva</label> <input
								class="form-control w-100" type="date"
								value="${rv.fechaReserva}" name="fechaReserva">
						</div>
						<div>
							<label class="form-label">Fecha salida</label> <input
								class="form-control w-100" type="date" value="${rv.fechaSalida}"
								name="fechaSalida">
						</div>
					</div>

				</div>               
            </div> 	
     <div class="row">
        <div class="col-12 col-lg-4 col-xl-12 col-xxl-4 d-flex ">
        <input type="hidden" name="idEmpleado" value="${idEmpleado}">
        <input type="hidden" name="idCliente" value="${c.ID}">
        <input type="submit" class="btn btn-success fw-1 me-5" name="accion" value ="Realizar reserva">      
        	<c:if test="${rv.id !=null}">
        		<input type="hidden" value="${rv.id}" name="idReserva">
        	</c:if>
            <c:if test="${rv.id !=null}">
            <input type="hidden" name="habitacion" value="${rv.id_habitacion}">
        	<input type="submit" class="btn btn-info fw-1" name="accion"  value ="Actualizar reserva">       
        	<a href="Recepcion?accion=mostrar-reservas&idEmpleado=${idEmpleado}&operacion=inicio" class="btn btn-danger ms-5">Cancelar</a>
        </c:if>
        </div>
     </div>
     </form>
     
    <div class="row">
        <form action = "Recepcion" method="get">
        <div class="col-12 ">
            <div class="row">
                        
                <div id="busquedaCliente" class="col-12 col-lg-12 col-xl-12 col-xxl-12 m-3 d-flex justify-content-center  ">
                    <input type="hidden" name="accion" value="mostrar-reservas">
                    <input type="hidden" name="operacion" value="filtrarReservas">
                    <div class="">
                        <input class="form-control w-100" type="text" name="cliente" placeholder="Dni, pasaporte">
                    </div>
                    <div class="ms-3">
                    
                        <button class="btn btn-success" type="submit"><i class="fas fa-search"></i></button>
                    </div>
              
                </div>
            </div>
        </div> 
        </form>       
    </div>
           <!--table-->
     <div class="row bg-light">
        <div class="col-12 col-lg-12 col-xl-12 col-xxl-12 d-flex justify-content-center">
         
            <div class="table-responsive">
                <table class="table table-stripped text-center">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Habitacion</th>
                            <th>Identificacion</th>
                            <th>Cliente</th>
                            <th>Fecha Reserva</th>
                            <th>Fecha ingreso</th>
                            <th>Fecha salida</th>
                            <th>Estado</th>
                       
                        </tr>
                    </thead>
                    <tbody class="w-fila">
                    <c:forEach var="rv" items="${reservas}">
                        <tr>
                            <td class="fw-bold">${rv.id} </td> 
                            <td> ${rv.habitacion.nivel}${rv.habitacion.numHab} </td>
                            <td> ${rv.persona.identification} </td>
                           <td><div style="width: 230px"><label class="fw-bold">${rv.persona.surName}</label> ${rv.persona.name} </div></td> 
                            <td><div style="width:150px"> <fmt:formatDate value="${rv.fechaReserva}" pattern="dd/MM/yyyy"/>		</div></td> 
                            <td>
                            <c:choose>
                            	<c:when test="${rv.fechaIngreso == null }">
                            		 <div style="width:150px"><label class="text-danger"> Sin ingreso </label> </div>
                            	</c:when>
                            	<c:when test="${rv.fechaIngreso != null}">
                            		 <div class="text-success fw-bold" style="width:150px"><fmt:formatDate value="${rv.fechaIngreso}" pattern="dd/MM/yyyy" type="date" timeStyle="hh:mm"/>	</div>
                            	</c:when> 
                            </c:choose>                            
                            </td>
                            <td><div style="width:150px"><fmt:formatDate value="${rv.fechaSalida}" pattern="dd/MM/yyyy"/></div></td>
                            <td><div style="width: 150px">  ${rv.estado}</div></td>
                            <td>
                            <div style="width: 380px">
                            	<a href="Recepcion?accion=eliminar_reserva&IdReserva=${rv.id}&IdHabitacion=${rv.id_habitacion}&idEmpleado=${idEmpleado}" class="text-decoration-none"><i class="fas fa-trash-alt"></i><span class="ms-2">Eliminar</span></a> 
                                <a href="Recepcion?accion=editar_reserva&idReserva=${rv.id}&idEmpleado=${usuario.ID}" class="text-decoration-none ms-1"><i class="far fa-edit"></i><span class="ms-1 me-2">Editar</span></a>
                                <c:if test="${rv.fechaIngreso == null}">
                                	<a href="Recepcion?accion=confirmar-reserva&idReserva=${rv.id}&idEmpleado=${idEmpleado}" class="text-decoration-none"><i class="fas fa-check-square"></i><span class="ms-1 me-5">Confirmar ingreso</span></a>
                            	</c:if>
                            </div>
                                
                                  
                            </td>
                            
                        </tr>
                      </c:forEach>  
                    </tbody>
                </table>
            </div>
           
        </div>            
    </div>
    
    <div class="row bg-primary">
    	<div class="col-12 col-lg-12 col-xl-12">
    	<c:choose>
    	<c:when test="${totalRegistros == 0}">
    		<label class="fs-5 fw-bold float-end">Numero de registros: 0</label>	
    	</c:when>
    	<c:when test="${totalRegistros != 0}">
    		<label class="fs-5 fw-bold float-end">Numero de registros: ${totalRegistros}</label>	
    	</c:when>
    	</c:choose>   	
    	</div>
    </div>   
</div> 
   <!--  <script src="bootstrap/js/popper.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>   -->
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
</body>
</html>


