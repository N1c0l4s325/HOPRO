<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Clientes</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style2.css">
        <link rel="stylesheet" href="css/all.css">
     
</head>
<body>
<%-- <%@ include file = "index.jsp" %> --%>
<div class="container">

	 <form action="Recepcion" method="get" >        
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
  	<c:if test="${rv == null }">
        <div class="bg-info fw-bold shadow-lg">    
        <div class="row m-3 ">
        
            <div class="col-12 col-lg-4 col-xl-4 col-xxl-4 mt-3">
                <div>
                    <label class="form-label">Identificacion</label>
                    <input class="form-control" type="text" name="Iden" value="${cl.identification}">
                                          
                </div>
            </div>
            <div class="col-12 col-lg-4 col-xl-4 col-xxl-4 mt-3">
                <div class="">
                    <label class="form-label">Tipo de identificacion</label>
                    <select class="form-select" name="tipoIde" aria-label="Default select example">
                        <option selected></option>
                        <option value="Pasaporte">Pasaporte</option>
                        <option value="Dni">Dni</option>
                     </select>
                        
                </div>
            </div>
            <div class="col-12 col-lg-4 col-xl-4 col-xxl-4 mt-3">
                <div>
                    <label class="form-label">Nombre</label>
                    <input class="form-control" type="text" name="nombre" value="${cl.name}" >
                       
                </div>
            </div>
        </div>
        <div class="row m-3">
            <div class="col-12 col-lg-4 col-xl-4 col-xxl-4">
                <div>
                    <label class="form-label">Apellido</label>
                    <input class="form-control" type="text" name="apellido" value="${cl.surName}" >
                       
                </div>
            </div>
            <div class="col-12 col-lg-4 col-xl-4 col-xxl-4">
                <div>
                    <label class="form-label">Direccion</label>
                    <input class="form-control" type="text" name="direccion" value="${cl.address}">
                       
                </div>
            </div>
            <div class="col-12 col-lg-4 col-xl-4 col-xxl-4">
                <div>
                    <label class="form-label">Telefono</label>
                    <input class="form-control" type="text" name="telefono"  value="${cl.phone}">
                    
                </div>
            </div>
        </div>
        <div class="row m-3 ">
            <div class="col-12 col-lg-4 col-xl-4 col-xxl-4 mb-3">
                <div>
                    <label class="form-label">Email</label>
                    <input class="form-control" type="text" name="email" value="${cl.email}">
                    
                </div>
            </div>
            <div class="col-12 col-lg-4 col-xl-4 col-xxl-4 mb-3">
                <div>
                    <label class="form-label">Pais</label>
                    <input class="form-control" type="text" name="pais" value="${cl.country}">
                    
                </div>
            </div>
        </div>      
       </div>
       </c:if>
         <div class="row">
        <div class="col-12 col-lg-4 col-xl-12 col-xxl-4 d-flex ">
        <input type="submit" class="btn btn-success fw-1 me-5" name="accion" value ="Agregar cliente">
      
        	<c:if test="${cl.ID !=null}">
        	<input type="hidden" name="idPersona" value="${cl.ID}">
        	<input type="submit" class="btn btn-info fw-1" name="accion"  value ="Actualizar cliente">
       
        	<a href="Recepcion?accion=mostrarCliente" class="btn btn-danger ms-5">Cancelar</a>
        </c:if>
        </div>
     </div>
</form>
<c:if test="${cl == null}">	
		<div class="row">
			<div class="col-12 col-lg-12 col-xl-12 col-xxl-12">
				<form action="Recepcion" method="get">
					<input type="hidden" name="accion" value="mostrarCliente">
					<div class="d-flex justify-content-center">
						<input type="text" class="form-control me-3 w-25" name="filter">
						<input type="hidden" value="buscarCliente" name="operacion">
						<input class="btn btn-success" type="submit" value="Buscar">
					</div>
				
				</form>
			</div>	
		</div>
	
      <div class="row">
          <div class="col-12">
              <div class="row">
                  
                  <div class="col-12 col-lg-12 col-xl-12 col-xxl-12 mt-3">
                    <div class="table-responsive ">
                        <table class="table table-stripped text-center ">
                            <thead>
                                <tr>
                              <!--       <th>ID</th> -->                                    
                                    <th>Tipo de identificacion</th>
                                    <th>N° dni o pasaporte</th>
                                    <th>Cliente</th>
                                    <th>Direccion</th>
                                    <th>Telefono</th>
                                    <th>Email</th>
                                    <th>Pais</th>
                                    
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="cl" items="${cliente}">
                                <tr>
                                  <%--   <td class="fw-bold"><div class="w-130"> ${cl.ID} </div></td>    --%>                                 
                                    
                                    <td> ${cl.typeIdentification} </td>
                                    <td class="fw-bold"> ${cl.identification}</td>
                                    <td><div style="width: 200px"> ${cl.surName} ${cl.name}</div></td>
                                    <td><div style="width: 250px"> ${cl.address} </div></td>
                                    <td> ${cl.phone} </td>
                                    <td> ${cl.email} </td>
                                    <td> ${cl.country}</td>                                
                                    <td>
                                        <div style="width: 300px">
                                            <a href="Recepcion?accion=eliminarCliente&idCliente=${cl.ID}" class="text-decoration-none"><i class="fas fa-trash-alt"></i><span class="ms-2">Eliminar</span></a> 
                                            <a href="Recepcion?accion=editarCliente&idCliente=${cl.ID}" class="text-decoration-none ms-1"><i class="far fa-edit"></i><span class="ms-1">Editar</span></a>
                                           
                                        </div>
                                        
                                    </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                  </div>
              </div>                
         </div>
    </div>
    
</c:if>


</div>
<!-- <script src="../../bootstrap/js/popper.min.js"></script>
<script src="../../bootstrap/js/bootstrap.min.js"></script> -->
 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
