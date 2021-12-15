/**
 * 
 */

$(document).ready(function(){
	
	
	
	document.getElementById("checkbox_fecha").addEventListener("click", MostrarFecha, false);
	
	document.getElementById("checkbox_cliente").addEventListener("click", MostrarCliente, false)
	
function MostrarFecha(){
	
	("#busquedafecha").removeClass("d-none");
}	
function MostrarCliente(){
	
	("#busquedaCliente").removeClass("d-none");
}		
	
	
	
	
	
	
	
	
})