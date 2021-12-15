<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
<head>


 <meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>admin/recepcionist/Habitaciones</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"> 
<link rel="stylesheet" href="css/style2.css">
<link rel="stylesheet" href="css/all.css">
<style type="text/css">
</style>
 
</head>
<body>

<body>

<div class="container scroll-bar" >
    <div>
  		
        <c:if test="${mensajeOperacion != null}">
            <label class="text-success mb-3 fs-3 fw-bold"> ${mensajeOperacion} </label>
        </c:if>
        <c:if test="${mensajeError != null}">
            <label class="text-danger mb-3 fs-3 fw-bold"> ${mensajeError} </label>
        </c:if>
    </div>

<div class="row d-flex justify-content-center">
    <div class="col-12 col-lg-4 col-xl-4 col-xxl-4 w-25 h-75">
              <div class="card bg-success text-white shadow-lg">
                  <div class="card-header">
                    <div>
                          <span class="fs-1"> ${habitacionesLibres} </span>
                 </div>
                  </div>
                  <div class="card-body">
                      <div>
                          <i class="fas fa-door-open me-2"></i><span class="fw-bold">Libres</span> 
                         <!--  <input type="hidden" name="txt-estado" value="Libre">
                          <button type="submit" class="btn btn-success"><i class="fas fa-arrow-right"></i></button> -->
                      </div>
                  </div>
              </div>
    </div>
      
  <!---tarjeta de habitaciones ocupadas---------------------------------------------->

          <div class="col-12 col-lg-4 col-xl-4 col-xxl-4 w-25 h-75">
              <div class="card bg-danger text-white shadow-lg">
                  <div class="card-header">
                      <div>                          
                          <span class="fs-1"> ${habitacionesOcupadas} </span>                          
                      </div>
                  </div>
                  <div class="card-body">
                      <div>
                          <i class="fas fa-door-closed me-2"></i><span class="fw-bold">Ocupadas</span> 
                          <!-- <input type="hidden" name="txt-estado" value="ocupada">
                          <button type="submit" class="btn btn-danger"><i class="fas fa-arrow-right"></i></button> -->
                      </div>                        
                  </div>
              </div>
          </div>

  <!---tarjeta de habitaciones en limpieza-------------------------------------------->

          <div class="col-12 col-lg-4 col-xl-4 col-xxl-4 w-25 h-75">
              <div class="card bg-secondary text-white shadow-lg">
                  <div class="card-header">
                      <div>
                          <span class="fs-1"> ${habitacionesLimpieza} </span>                          
                      </div>
                  </div>
                  <div class="card-body">
                      <div>
                          <i class="fas fa-broom me-2"></i><span class="fw-bold">En limpieza</span>
                         <!--  <input type="hidden" name="txt-estado" value="En limpieza"> 
                          <button type="submit" class="btn btn-secondary"><i class="fas fa-arrow-right"></i></button> -->
                      </div>
                  </div>
              </div>
          </div>
         </div>

 <!--tabla de reservas------------------------------------------------------------------>
             
   <div class="row mt-4 ">
       <div class="col-12 col-lg-12 col-xl-12 col-xxl-12 d-flex justify-content-center overflow-auto">
            <div class="card shadow-lg col-xl-12">
                <div class="card-header">
                    <form method="get" action="Recepcion">
                        <input type="hidden" name="accion" value="Listar-habitaciones">
                    <div class="row d-flex justify-content-center mb-5 mt-5">
                        <div class="col-12  col-lg-4 col-xl-5">
                              <div class="me-5 d-flex justify-content-center">
                                <select class="form-select w-100 me-3 text-center" name="txt-tipo" id="select">
                                        <option selected="selected">Seleccione un tipo de habitacion</option>
                                        <option value="Matrimonial">Matrimonial</option>
                                        <option value="Doble">Doble</option>
                                        <option value="Tiple">Triple</option>
                                </select>
                                <input class="btn btn-success" type="submit" name="operacion" value="Buscar habitacion">
                              </div>
                         </div>
                     </div>
                     </form>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover  mt-30 text-center">
                           <thead> 
                            <tr>
                            	<th>Codigo</th>
                                <th>Nivel</th>
                                <th>Numero</th>
                                <th>Tipo de habitacion</th>
                                <th>Capacidad</th>
                                <th>Precio</th>
                                <th>Estado</th>
                           </tr>
                        </thead>
                        <tbody>
                        
                        <c:forEach var="hab" items="${habitaciones}">               	
                            <tr>
                            	<td>000${hab.ID} </td>
                                <td> ${hab.nivel} </td>
                                <td> ${hab.numHab} </td>
                                <td> ${hab.tipoHabitacion}</td>
                                <td> ${hab.capacidad} </td>
                                  <td><div class="">$ ${hab.precio}</div></td>
                                <td> ${hab.estado} </td>
                                <td><div>
                                <c:if test="${hab.estado == 'EN LIMPIEZA' }">                        	
                                    <a href="Recepcion?accion=liberarHabitacion&idHabitacion=${hab.ID}" class="btn btn-warning"><span class="ms-1">Liberar habitacion</span></a>						                                                   
                                </c:if>
                                </div></td>                
                             </tr>                    
                        </c:forEach>
                         
                        </tbody> 
                        </table>
                    </div>
                    <div class="row bg-primary">
                        <div class="col-12 col-lg-12 col-xl-12">
                            <div class="float-end">
                                <label class="fs-4 fw-bold">Total de registros: ${totalRegistros}</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>  

       </div>
  </div> 

</div> 



    <script src="bootstrap/js/popper.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
   <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>  -->
</body>
</html>

