<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sistema Hotel</title>


<link rel="stylesheet" href="css/nav-bar.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet"  href="css/index.css">

<script src="js/jquery.js"></script>

</head>
<body >
<div class="principal-container">
	
<jsp:include page="nav-bar.jsp"></jsp:include>
	
	<div class="second-container">
		<div class="container-right">
			<div id= "AboutUs">		
			
				<h1 id="title">¿Quienes Somos?</h1>
				 <p>San Felipe es un Apart Hotel 3 estrellas ubicado en San Rafael, Mendoza.
				Situado en el Centro de la Ciudad, donde confluyen las más importantes Rutas que unen los distintos puntos turisticos de la región,	rodeado de importantes comercios y casino, a 8 km del Aeropuerto local, a minutos de la terminal de Ã³mnibus y destacadas Bodegas y ViÃ±edos. 
				Desde nuestra ubicación podrá acceder en menos de 40 minutos a sitios como Valle Grande, Los Reyunos, el Nihuil o poder realizar una escapada al Valle de Las LeÃ±as.</p>
			</div>
			<div class="blue-bun">
				<img id="img-container-right" src="img/moño.jpg">
			</div>
			<div class="container-carrusel">
					<img id="imgs-carrusel" src="img/desayuno.jfif">
			</div>
		</div>
	
	<div class="container-left">
		<div>
			<img id="img-container-left" src="img/L_5c1a3ccbd93a7_shutterstock_hotel3.jpg">
		</div>
		
		<div class="service">
			<h1>Servicios</h1>
			<p>
				Bienvenidos a San Felipe Hotel, un nuevo concepto en hotelería.
				Brindamos excelencia en materia de servicio cuidando los pequeños detalles.
				Creemos que cada huÃ©sped es especial, es por ello que nos distinguimos gracias a nuestra amabilidad,
				predisposición, respeto y profesionalismo.
		
			</p>
			</div>	
			<div class="icons-service">
				<ul class="img-service">
					<li>
						<img id="img-icons" src="img/wifi.png">
						<span>Internet y wifi gratis.</span>
					</li>
					<li>
						<img id="img-icons" src="img/tv.png">
						<span>Tv en todas las habitaciones.</span>
					</li>
					<li>
						<img id="img-icons" src="img/-pool_90362.png">
						<span>Piscina.</span>
					</li>
					<li>
						<img id="img-icons" src="img/resto.png">
						<span>Restaurant.</span>
					</li>
					<li>
						<img id="img-icons" src="img/parking_sign_signaling_automobile_parking_signs_car_icon_128576.png">
						<span>Cocheras monitoreadas las 24 hs. </span>
					</li>
				</ul>
			</div>
		
		
		</div>
	</div>
	
	
	
	
	<jsp:include page="footer.jsp"></jsp:include>
</div>


</body>
</html>