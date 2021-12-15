<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page import="model.Usuarios" %>  
  
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>admin/recepcionist/inicio</title>
<!-- <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"> -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="css/all.css">
<link rel="stylesheet" href="css/beginning.css">
<link rel="stylesheet" href="css/style2.css">
<style type="text/css">
div.frame{
	height: 500px;
	overflow: hidden;
}	
	
	
</style>
</head>
<body>
<div class="container-fluid">

 			<nav class="navbar navbar-expand-xl navbar-dark bg-info mb-3 sticky-top">
			
					
				<a class="navbar-brand ms-5" href="#"><img
					style="width: 50px; height: 50px;" src="img/unnamed.png"></a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				
			
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0 text-dark">
						
						<li class="nav-item"><a
							class=" btn btn-outline-light border-none"
							style="margin-left: 10px; border: none" aria-current="page"
							href="Recepcion?accion=inicio">Inicio</a></li>
							
							<li class="nav-item"><a
							class=" btn btn-outline-light border-none"
							style="margin-left: 10px; border: none"
							href="Recepcion?accion=mostrarCliente&operacion=inicio" target="frame">Clientes</a></li>
							
									
						<li class="nav-item"><a
							class=" btn btn-outline-light border-none"
							style="margin-left: 10px; border: none"
							href="Recepcion?accion=factura&operacion=inicio" target="frame">Facturacion</a></li>	
							
						<li class="nav-item"><a
							class=" btn btn-outline-light border-none"
							style="margin-left: 10px; border: none"
							href="Recepcion?accion=Listar-habitaciones&operacion=inicio" target="frame">Habitaciones</a>
						</li>
						
						<li class="nav-item"><a
							class=" btn btn-outline-light border-none"
							style="margin-left: 10px; border: none"
							href="Recepcion?accion=mostrar-reclamos&operacion=inicio&idEmpleado=${usuario.ID}" target="frame">Reclamos</a></li>
							
						<li class="nav-item"><a
							class=" btn btn-outline-light border-none"
							style="margin-left: 10px; border: none"
							href="Recepcion?accion=mostrar-reservas&idEmpleado=${usuario.ID}&operacion=inicio" target="frame">Reservas</a></li>
							
						<li class="nav-item"><a
							class=" btn btn-outline-light border-none"
							style="margin-left: 10px; border: none"
							href="Recepcion?accion=mostrar-consumos&operacion=inicio" target="frame">Ventas</a></li>				
							
					</ul>
				
		  <div class="dropdown me-5">
  			<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false">
   			 ${usuario.acceso.tipoAccess}
  			</button>
  			<ul class="dropdown-menu dropdown-menu-dark text-center" aria-labelledby="dropdownMenuButton2">
   				 <li><a class="dropdown-item disabled" href="#"><img alt="60" width="60" src="img/user.png"></a></li>
    			 <li><a class="dropdown-item disabled" href="#">${usuario.persona.name} ${usuario.persona.surName}</a></li>
    			 <li><hr class="dropdown-divider"></li>
    			 <li><a class="dropdown-item" href="Recepcion?accion=configuraciones&operacion=mostrar&idUsu=${usuario.ID}" >Configuraciones</a></li>
    			 <li>    			 
    			 <a class="dropdown-item fw-bold" href="Recepcion?accion=cerrarSesion">Cerrar Sesion</a>    			
    			 </li>
  			</ul>
		</div>
		</div>
	
		</nav>

	
   			<div class=" m-4 frame">
      			 <iframe name="frame" id="frame"  src="empleado/recepcion/bienvenida.jsp" style="width: 100%; height: 100%"></iframe>  			
			</div> 
</div>			
<!-- <script src="bootstrap/js/popper.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script> -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>

<%
	response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
	
	if(session.getAttribute("usuario") == null){
		
		response.sendRedirect(request.getContextPath()+"/login.jsp");
  	}

%> 