<%@page import="model.Usuarios"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%-- <%@ page import="model.Usuarios" %>   
 <%
    	/* HttpSession sesion;
         	sesion = request.getSession();
         	Usuarios ac = (Usuarios) sesion.getAttribute("usuario");
         	if(sesion.getAttribute("usuario")==null){         		
         		request.getRequestDispatcher("../../login.jsp").forward(request, response);
         } */
         	
    %>
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../../recepcionist/style2.css">
<link rel="stylesheet" href="../../css/all.css">
</head>
<body>
<div class="container-fluid">

<nav class="navbar navbar-expand-xl navbar-dark bg-info">
  <div class="container">
    <a class="navbar-brand" href="#"><img style="width:50px; height: 50px;" src="../../img/unnamed.png"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0 text-dark">
        <li class="nav-item">
          <a class=" btn btn-outline-light border-none" style=" margin-left: 10px ;border:none"  aria-current="page" href="/../../Recepcion?accion=inicio">Inicio</a>
        </li>
        <li class="nav-item">
          <a class=" btn btn-outline-light border-none" style=" margin-left: 10px ;border:none" href="/../../Recepcion?accion=mostrar-clientes">Clientes</a>
        </li>
        <li class="nav-item">
          <a class=" btn btn-outline-light border-none" style=" margin-left: 10px ;border:none"  href="/../../Recepcion?accion=Listar-habitaciones">Habitaciones</a>
        </li>
        <li class="nav-item">
          <a class=" btn btn-outline-light border-none" style=" margin-left: 10px ;border:none"  href="/../../Recepcion?accion=mostrar-reclamos">Reclamos</a>
        </li>
        <li class="nav-item">
          <a class=" btn btn-outline-light border-none" style=" margin-left: 10px ;border:none"  href="/../../Recepcion?accion=mostrar-reservas">Reservas</a>
        </li>
        <li class="nav-item">
          <a class=" btn btn-outline-light border-none" style=" margin-left: 10px ;border:none"  href="/../../Recepcion?accion=mostrar-consumo">Ventas</a>
        </li>
        
      </ul>      
    </div>
  </div>
  
<div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
    ${recepcionista.acceso.tipoAccess}
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
    <li><a class="dropdown-item" href="#">${recepcionista.persona.name}</a></li>
    <li><a class="dropdown-item" href="#">${recepcionista.persona.surName}</a></li>
     <li><hr class="dropdown-divider"></li>
   	 <li><a class="dropdown-item btn" href="#">Cerrar Session</a></li>
   
  </ul>
</div>
</nav>







</div>	

<script src="../../bootstrap/js/popper.min.js"></script>
<script src="../../bootstrap/js/bootstrap.min.js"></script>
</body>
</html>