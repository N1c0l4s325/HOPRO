<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/nav-bar.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/room.css">
<script src="js/jquery.js"></script>
</head>
<body>
<div id="principal-container">
	<jsp:include page="nav-bar.jsp"></jsp:include>
	<div id="container">
		<div id="imgs">
			<img src="img/descarga (2).jpg">
			<img src="img/descarga (4).jfif">
			<img src="img/descarga (5).jpg">

		</div>
		<div id="descript-room">
			<div id="titulo">
				<h1>Habitación simple </h1>
			</div>
			<div>
				<p><strong>1 cama matrimonial (2 personas) </strong> - Habitación con aire acondicionado,
					TV por cable, wifi, minibar y baño privado
					con ducha y artículos de aseo gratuitos.</p>
				<div><a href="login.html"><button>Reservar</button></a></div>
			</div>

		</div>
		<div id="double-descript-room">
			<div id="titulo">
				<h1>Habitación doble </h1>
			</div>
			<div>
				<p><strong>1 cama matrimonial y 1 cama individual (3 personas)</strong> - Habitación con aire acondicionado,
					TV por cable, wifi, minibar y baño privado
					con ducha y artículos de aseo gratuitos.</p>
				<div><a href="login.html"><button>Reservar</button></a></div>
			</div>
		</div>
		<div id="tiple-descript-room">
			<div id="titulo">
				<h1>Habitación tiple</h1>
			</div>
			<div>
				<p><strong>1 cama matrimonial y 2 camas individuales (4 personas)</strong> - Habitación con aire acondicionado,
					TV por cable, wifi, minibar y baño privado
					con ducha y artículos de aseo gratuitos.</p>
				<div><a href="login.html"><button>Reservar</button></a></div>
			</div>
		</div>

	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>