<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8)">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>admin/Login</title>
</head>
<!-- <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"> -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="css/index_login.css">

<body class="bg-light d-flex justify-content-center">
	<div>  		
  		<c:if test="${mensajeError != null}">
  			<label class="text-danger mb-3 fs-3 fw-bold"> ${mensajeError} </label>
  		</c:if>
  		</div>
	<div class="container mt-5 col-lg-4">
	<div class="card shadow-lg p-3 mb-5 bg-body rounded col-sm-10">
			<div class="card-body">
				<form action="Session" method="POST" class="form-sign text-center">
					<div class="form-group text-center">
						<h3 class="fs-1">LOGIN</h3>
					
					</div>
					<div class="form-group">
						<label for="txt-user">Usuario:</label>
						<input type="text" name="txt-user" id="txt-user" class="form-control text-center">
					</div>			

					<div class="form-group">
						<label for="txt-pass">Contraseña:</label>
						<input type="password" name="txt-pass" id="txt-pass" class="form-control text-center">
					</div>
					<div class="mt-2 mb-2">
						<c:if test="${mje != null}">
							<label class="text-danger fs-6 fw-bolder"> ${mje} </label>		
						</c:if>			
					</div>
					<div class="form-group w-100 col-sm-4">
						
						<input type="submit" class="btn btn-primary mt-4 w-50" name="accion" value="Ingresar">						
					</div>		
				</form>
			</div>
		</div>
		
	</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<!-- <script src="../bootstrap/js/popper.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script> -->
</body>
</html>
