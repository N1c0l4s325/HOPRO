<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>admin/recepcionist/configuraciones</title>
<!-- <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"> -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="css/all.css">
<link rel="stylesheet" href="css/beginning.css">
<link rel="stylesheet" href="css/style2.css">

</head>
<body>

<div class="container">
<div class="row mt-5">
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
        <div class="col-12 col-lg-6 col-xl-12 d-flex justify-content-center">
                <div class="card shadow-lg col-xl-3">
                    <div class="card-header">
                        <a href="Recepcion?accion=inicio"><i class="fas fa-times-circle"></i></a>
                    </div>
                    <div class="card-body">
                        <form method="get" action="Recepcion">
                         <div class="form-group mb-3">
                             <label class="form-label" for="">Usuario</label>
                             <input class="form-control" type="text" value="${u.user}" readonly="readonly">
                         </div>
                         <div class="form-group mb-3">
                            <label class="form-label" for="">Contraseña</label>
                            <input class="form-control" type="password" value="${u.password}" readonly="readonly">
                        </div>
                        <div class="form-group mb-3">
                            <label class="form-label" for="">Usuario nuevo</label>
                            <input class="form-control" type="text" name="usuarioNuevo" >
                        </div>
                        <div class="form-group mb-3">
                            <label class="form-label" for="">Contraseña nueva</label>
                            <input class="form-control" name="cont1" type="password" >
                        </div>
                        <div class="form-group mb-3">
                            <label class="form-label" for="">Repetir contraseña </label>
                            <input class="form-control" name="cont2" type="password"  >
                        </div>
                        <div class="form-group mb-3 col-sm-12 d-flex justify-content-center">
                            <input type="hidden" name="idUsu" value="${u.ID}">
                            <input type="hidden" name="accion" value="configuraciones">
                           <button class="btn btn-success" type="submit" name="operacion" value="Actualizar usuario">Actualizar usuario</button>
                        </div>
                        </form>
                    </div>
                </div>
        </div>
    </div>
</div>
</body>
</html>

