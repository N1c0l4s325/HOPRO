<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/formRegister.css">
<link rel="stylesheet" href="css/all.css">
<title>Insert title here</title>
</head>
<body>
<div class="principal-container">

<jsp:include page="nav-bar.jsp"></jsp:include>
<div class="container-second">
	<div class="container-form">
	<form class="form-register" id="form-register" action="Usuario"  method="get">

		<div class="form-group" id="group-name">
			<label for="name" class="form-label">Nombres</label>
			<div class="form_register-input">
			  <input type="text" name="name" id="name" class="form-input">
			  <i class="form-icon-estate fas fa-times-circle"></i>
			</div>
			<p class="form_register-input-error">Este campo debe contener solo letras</p>
		</div>
	
		<div class="form-group" id="group-surName">
			<label for="surName" class="form-label">Apellido</label>
			<div class="form_register-input">
			  <input type="text" name="surName" id="surName" class="form-input">
			  <i class="form-icon-estate fas fa-times-circle"></i>
			</div>
			<p class="form_register-input-error">Este campo debe contener letras</p>
		</div>
		
		<div class="form-group" id="group-country">
			<label for="numberIde" class="form-label">Dni o Pasaporte</label>
			<div class="form_register-input">
			  <input type="text" name="numberIde" id="numberIde" class="form-input">
			  <i class="form-icon-estate fas fa-times-circle"></i>
			</div>
			<p class="form_register-input-error">Este campo debe contener solo numeros</p>
		</div>
	
		<div class="form-group" id="group-country">
			<label for="country" class="form-label">Pais de origen</label>
			<div class="form_register-input">
			  <input type="text" name="country" id="country" class="form-input">
			  <i class="form-icon-estate fas fa-times-circle"></i>
			</div>
			<p class="form_register-input-error">Por favor, ingrese pais de origen</p>
		</div>

		<div class="form-group" id="group-address">
			<label for="address" class="form-label">Direccion</label>
			<div class="form_register-input">
			  <input type="text" name="address" id="address" class="form-input">
			  <i class="form-icon-estate fas fa-times-circle"></i>
			</div>
			<p class="form_register-input-error">Por favor, ingrese direccion</p>
		</div>

		<div class="form-group" id="group-phone">
			<label for="phone" class="form-label">Telefono</label>
			<div class="form_register-input">
			  <input type="text" name="phone" id="phone" class="form-input">
			  <i class="form-icon-estate fas fa-times-circle"></i>
			</div>
			<p class="form_register-input-error">Por favor, ingrese telefono</p>
		</div>	
		<div class="form-group" id="group-email">
			<label for="email" class="form-label">Email</label>
			<div class="form_register-input">
			  <input type="text" name="email" id="email" class="form-input">
			  <i class="form-icon-estate fas fa-times-circle"></i>
			</div>
			<p class="form_register-input-error">Por favor, ingrese nombre sin numeros</p>
		</div>
		<div class="form-group-session" id="group-session" >
		<div class="form-group" id="group-user">
			<label for="user" class="form-label">Usuario</label>
			<div class="form_register-input">
			  <input type="text" name="user" id="user" class="form-input">
			  <i class="form-icon-estate fas fa-times-circle "></i>
			</div>
			<p class="form_register-input-error">El usuario ya esta en uso</p>
			
		</div>
		<div class="form-group-password" id="group-password">
			<label for="password" class="form-label">ContraseÃ±a</label>
			<div class="form_register-input">
			  <input type="password" name="password" id="password" class="form-input">
			  <i class="form-icon-estate fas fa-times-circle"></i>
			</div>
			<p class="form_register-input-error"></p>
		</div>
		<div class="form-group-password" id="group-user">
			<label for="confirm-password" class="form-label">Confirmar contraseña</label>
			<div class="form_register-input">
			  <input type="password" name="user" id="confirm-password" class="form-input">
			  <i class="form-icon-estate fas fa-times-circle"></i>
			</div>
			<p class="form_register-input-error">Ambas contraseñas deben coincidir</p>
		</div>
	 </div>
	 <div class="form-group-warning" id="group-botton">
		<p class="form-group-warning"><i class="fas fa-exclamation-triangle"></i><b>Error: por favor, rellena el formulario correctamente.</b></p>
	</div>
	 <div class="form-group form-group-btn-register">
		<button type="submit" class="form-btn">Registrarse</button>
	</div>
	</form>
	
	
</div>
</div>
</div>
	<jsp:include page="footer.jsp"></jsp:include>
	
</div>
</body>
</html>